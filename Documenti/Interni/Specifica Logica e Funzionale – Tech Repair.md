# Specifica Logica e Funzionale – Tech Repair

## 1. Architettura dei Dati e Modello del Dominio

Il dominio applicativo è organizzato in quattro entità principali gerarchiche (`Cliente` $\rightarrow$ `Dispositivo` $\rightarrow$ `SchedaRiparazione` $\rightarrow$ `Preventivo`), supportate dalle entità per la gestione del magazzino (`Ricambio`, `OrdineAcquisto`) e della cassa (`Pagamento`).

| Entità | Identificativo | Attributi Principali | Regole di Dominio e Vincoli |
| :--- | :--- | :--- | :--- |
| **Cliente** | `idCliente` + `codiceFiscale` (univoco) | `nome`, `cognome`, `codiceFiscale`, `telefono`, `email` | - Registrato una sola volta in anagrafica e ricercabile tramite **Codice Fiscale**.<br>- Può possedere $1..N$ **Dispositivi**.<br>- Può avere più schede di riparazione attive contemporaneamente, purché su **dispositivi diversi**. |
| **Dispositivo** | `idDispositivo` (ID interno immutabile) | `idDispositivo`, `seriale` (S/N o IMEI, **univoco**), `marca`, `modello` | - Associato a un unico **Cliente**.<br>- Non memorizza la tipologia né il guasto dichiarato (che varia a ogni intervento).<br>- Il campo `seriale` è univoco nell'intero sistema; se durante una riparazione viene sostituita la scheda madre, il tecnico può aggiornare il `seriale` (purché non appartenga già a un altro dispositivo), mentre `idDispositivo` mantiene intatto il legame con lo storico.<br>- Può avere $1..N$ **Schede di Riparazione** nello storico, ma **al massimo una scheda attiva** (stato diverso da `CHIUSA`) in un dato istante. |
| **Scheda di Riparazione** | `idScheda` | `idScheda`, `dataIngresso`, `dataChiusura`, `guastoDichiarato`, `diagnosiTecnica`, `statoCorrente`, `storicoStati[]` | - Associata a un singolo **Dispositivo**.<br>- Creata all'accettazione, contiene il **guasto dichiarato** dal cliente e successivamente la **diagnosi tecnica**.<br>- Segue il ciclo di vita definito dalla State Machine.<br>- Collegata a $0..1$ **Preventivo**, a $0..1$ **Ordine di Acquisto** e a $1..2$ **Pagamenti**. |
| **Preventivo** | `idPreventivo` | `idPreventivo`, `statoPreventivo` (`BOZZA` / `INVIATO` / `APPROVATO` / `RIFIUTATO`), `dataInvio`, `costoManodopera`, `vociRicambio[]`, `totaleCalcolato`, `dataDecisione` | - Associato a una **Scheda di Riparazione**.<br>- Può prevedere **sola manodopera** ($0$ ricambi) oppure manodopera + $1..N$ ricambi distinti ($1$ unità per ricambio, scelti tra **Originali** o **Compatibili**).<br>- Ogni ricambio inserito nel preventivo è tracciato come **`IMPEGNATO`** (se scalato subito da $\text{giacenza} > 0$) oppure **`DA_ORDINARE`** (se inserito con $\text{giacenza} = 0$, soggetto a verifica del Magazziniere).<br>- Può essere creato in un secondo momento rispetto all'accettazione, salvato in **Bozza** e completato successivamente.<br>- Può essere modificato su richiesta del cliente **solo prima dell'approvazione**; una volta approvato è bloccato. |
| **Ricambio** | `codiceRicambio` | `codiceRicambio`, `nome`, `qualita` (`ORIGINALE` / `COMPATIBILE`), `prezzoUnitario` (fisso), `giacenza`, `reperibilita` (`DISPONIBILE` / `DA_VERIFICARE` / `ORDINABILE` / `FUORI_PRODUZIONE`) | - Tutti i componenti selezionabili dal tecnico sono pre-censiti a catalogo; solo il **Responsabile Magazzino** può creare nuovi ricambi a catalogo.<br>- Si assume che il `prezzoUnitario` a catalogo non cambi nel tempo.<br>- La `giacenza` viene decrementata ($-1$) non appena il ricambio (con $\text{giacenza} > 0$) viene aggiunto al preventivo (anche in bozza) e ripristinata ($+1$) **solo per i pezzi marcati come `IMPEGNATO`** qualora vengano rimossi o la scheda venga annullata/non riparabile. |
| **Ordine di Acquisto** | `idOrdine` | `idOrdine`, `idSchedaAssociata`, `dataOrdine`, `dataRicezione`, `ricambiOrdinati[]`, `statoOrdine` (`IN_CORSO` / `RICEVUTO`) | - Emesso dal **Responsabile Magazzino** solo dopo l'approvazione del preventivo per i ricambi marcati come `DA_ORDINARE`.<br>- È vincolato alla `SchedaRiparazione`: all'arrivo della merce, i pezzi passano direttamente allo stato `IMPEGNATO` per quella scheda senza transitare nella giacenza libera. |
| **Pagamento** | `idPagamento` | `idPagamento`, `data`, `importo` (`BigDecimal`), `tipo` (`DIAGNOSI_INIZIALE` / `SALDO_FINALE` / `RIMBORSO`) | - Registra i movimenti contabili simulati associati alla scheda. |

---

## 2. Attori, Ruoli e Funzionamento della Console (CLI)

L'applicazione gira in un'unica istanza su terminale a riga di comando (CLI) con database embedded, usata da un operatore alla volta. All'avvio presenta un **Menu Principale di selezione ruolo senza password** (`1. Tecnico Riparatore | 2. Responsabile Magazzino | 0. Esci`), consentendo il cambio rapido di ruolo in qualsiasi momento.

1. **Tecnico Riparatore (Operatore CLI):**
   * Registra/ricerca clienti e dispositivi, crea la scheda di riparazione, registra l'incasso anticipato dei $10,00\text{ EUR}$ di diagnosi e genera la ricevuta di ingresso.
   * Compila la diagnosi tecnica, crea/salva in bozza/invia il preventivo, lo modifica prima dell'approvazione, registra la decisione del cliente (approvazione, rifiuto o rinuncia anticipata).
   * Esegue la riparazione (aggiornando eventualmente il seriale del dispositivo), chiude l'intervento, registra la riconsegna e il saldo/rimborso finale, e consulta le schede.
2. **Responsabile Magazzino (Operatore CLI):**
   * Inserisce nuovi ricambi a catalogo e consulta l'inventario del magazzino.
   * Per le schede nello stato `IN_VERIFICA_MAGAZZINO`, verifica con il fornitore se i ricambi marcati come `DA_ORDINARE` ($\text{giacenza} = 0$) sono **Ordinabili** o **Fuori Produzione** e registra l'esito a sistema.
   * Per le schede nello stato `IN_ATTESA_RICAMBI` (dopo l'approvazione del cliente), emette l'**Ordine di Acquisto** e ne registra la ricezione, sbloccando la riparazione.
3. **Cliente (Attore Secondario – Non utilizza la Console):**
   * Interagisce esclusivamente tramite il Tecnico Riparatore.
   * Riceve dal sistema le **notifiche simulate** (stampate su console e registrate tramite Log4j grazie al pattern *Observer*) in tre eventi:
     1. Invio del preventivo (o di una sua modifica);
     2. Impossibilità di riparare per ricambio fuori produzione;
     3. Riparazione completata (dispositivo pronto per il ritiro).

---

## 3. Regole Economiche e Costo Minimo di 10,00 EUR (Pattern Strategy)

Il costo di diagnosi è fisso e pari a **$10,00\text{ EUR}$**, **versato sempre in anticipo dal cliente all'accettazione**.
Se la riparazione viene eseguita o se il cliente rinuncia/rifiuta il preventivo, **la spesa minima complessiva trattenuta dall'officina è sempre di almeno $10,00\text{ EUR}$**:
* Se il `Totale Preventivo` ($P = \text{Ricambi} + \text{Manodopera}$) è $\ge 10,00\text{ EUR}$, i $10,00\text{ EUR}$ di diagnosi sono interamente scontati e alla consegna il cliente paga la differenza ($P - 10,00\text{ EUR}$).
* Se il `Totale Preventivo` è $< 10,00\text{ EUR}$ (es. una riparazione di sola manodopera da $5,00\text{ EUR}$), la differenza fino a $10,00\text{ EUR}$ (es. $5,00\text{ EUR}$) copre la diagnostica: avendo già pagato $10,00\text{ EUR}$ all'ingresso, alla consegna il cliente paga un saldo di **$0,00\text{ EUR}$**.

Il calcolo del saldo finale alla consegna (implementato tramite il design pattern **Strategy**) segue la seguente tabella:

| Esito della Pratica | Versato all'Accettazione | Costo Totale Preventivo ($P$) | Saldo alla Consegna | Regola Contabile Applicata |
| :--- | :---: | :---: | :---: | :--- |
| **1a. Riparazione Completata ($P \ge 10\text{ EUR}$)** | $+10,00\text{ EUR}$ | $P \ge 10,00\text{ EUR}$ | **$P - 10,00\text{ EUR}$** | Diagnosi scontata per intero; il cliente paga solo l'eccedenza rispetto ai $10,00\text{ EUR}$ anticipati. |
| **1b. Riparazione Completata ($P < 10\text{ EUR}$)** | $+10,00\text{ EUR}$ | $0 < P < 10,00\text{ EUR}$ | **$0,00\text{ EUR}$** | Spesa minima complessiva di $10,00\text{ EUR}$ ($P$ di riparazione + $(10 - P)$ di diagnosi), già coperta dall'anticipo. |
| **2. Preventivo Rifiutato o Rinuncia Cliente** | $+10,00\text{ EUR}$ | $0,00\text{ EUR}$ | **$0,00\text{ EUR}$** | L'officina trattiene i $10,00\text{ EUR}$ già incassati all'accettazione. Il cliente ritira il dispositivo non riparato senza ulteriori addebiti. |
| **3. Ricambio Fuori Produzione** | $+10,00\text{ EUR}$ | $0,00\text{ EUR}$ | **$-10,00\text{ EUR}$ (Rimborso)** | Impossibilità tecnica non imputabile al cliente: l'officina **rimborsa i $10,00\text{ EUR}$** versati all'accettazione. |

---

## 4. Flusso Operativo Dettagliato e Gestione Scorte

### Fase 1: Accettazione del Dispositivo
1. Il Tecnico cerca il cliente per **Codice Fiscale**:
   * Se **nuovo**, registra `nome`, `cognome`, `codiceFiscale`, `telefono`, `email` e aggiunge il dispositivo (`seriale`, `marca`, `modello`).
   * Se **esistente**, seleziona il cliente e sceglie un suo dispositivo già registrato oppure ne aggiunge uno nuovo.
2. Il sistema verifica che il `seriale` sia univoco e che per il dispositivo selezionato **non esista già una scheda attiva** (stato diverso da `CHIUSA`).
3. Il Tecnico inserisce il **guasto dichiarato**, registra l'incasso anticipato di **$10,00\text{ EUR}$** (`DIAGNOSI_INIZIALE`) e il sistema crea la scheda nello stato `ACCETTATA`, generando la **Ricevuta di Ingresso**.

### Fase 2: Diagnosi e Composizione del Preventivo (Bozza e Verifica Magazzino)
1. Anche in un momento successivo all'accettazione, il Tecnico prende in carico la scheda (stato `IN_DIAGNOSI`) e registra la **diagnosi tecnica**.
2. Il Tecnico crea o aggiorna il **Preventivo** in stato `BOZZA` (salvabile e riprendibile in seguito) inserendo il costo di manodopera ed eventuali ricambi dal catalogo (*Originali* o *Compatibili*):
   * **Sola manodopera ($0$ ricambi):** Nessun impegno di magazzino richiesto; il preventivo può essere inviato subito.
   * **Ricambio già a catalogo come `FUORI_PRODUZIONE`:** Il sistema blocca il preventivo, rilascia in magazzino ($+1$) eventuali altri pezzi già marcati come `IMPEGNATO` nella bozza, porta la scheda a `NON_RIPARABILE` e invia la notifica simulata al cliente.
   * **Ricambio disponibile ($\text{giacenza} > 0$):** Appena aggiunto alla bozza, il sistema **scala immediatamente la giacenza ($\text{giacenza} - 1$)** e marca il pezzo nel preventivo come **`IMPEGNATO`**. Se il tecnico rimuove il pezzo dalla bozza, la giacenza torna subito a $+1$.
   * **Ricambio esaurito ($\text{giacenza} = 0$):** Il pezzo viene aggiunto alla bozza come **`DA_ORDINARE`** (senza decrementare la giacenza che è già a $0$) e, alla richiesta di verifica da parte del tecnico, la scheda passa allo stato `IN_VERIFICA_MAGAZZINO`.
     * Il **Responsabile Magazzino** verifica la reperibilità dal fornitore:
       * Se **Fuori Produzione**: aggiorna il ricambio a catalogo come `FUORI_PRODUZIONE`; il sistema rilascia ($+1$) solo gli eventuali altri pezzi della scheda che erano marcati come `IMPEGNATO`, porta la scheda a `NON_RIPARABILE` e invia la notifica simulata al cliente.
       * Se **Ordinabile**: conferma la reperibilità a sistema (si assume che il pezzo resti ordinabile fino alla valutazione del cliente) e la scheda torna nello stato `IN_DIAGNOSI` pronta per l'invio del preventivo.
3. Quando il preventivo in bozza ha tutti i ricambi `IMPEGNATO` o verificati come `ORDINABILE`, il Tecnico lo invia: il preventivo passa a `INVIATO`, la scheda passa a `IN_ATTESA_APPROVAZIONE` e parte la notifica simulata al cliente.

### Fase 3: Valutazione del Cliente, Modifiche e Annullamento
1. **Rinuncia anticipata del cliente (prima dell'invio del preventivo):**
   * Dagli stati `ACCETTATA`, `IN_DIAGNOSI` o `IN_VERIFICA_MAGAZZINO`, se il cliente rinuncia alla riparazione, il Tecnico annulla la scheda: il sistema rilascia in magazzino ($+1$) gli eventuali ricambi marcati come `IMPEGNATO` nella bozza, porta la scheda ad `ANNULLATA` e trattiene i $10,00\text{ EUR}$ iniziali.
2. **Modifica del Preventivo (consentita solo nello stato `IN_ATTESA_APPROVAZIONE`):**
   * Su richiesta del cliente (aggiunta/rimozione voci o cambio qualità *Originale/Compatibile*), il Tecnico modifica il preventivo.
   * I ricambi rimossi o sostituiti che erano marcati come `IMPEGNATO` vengono rilasciati subito in magazzino ($+1$).
   * Se i nuovi ricambi inseriti sono tutti disponibili ($\text{giacenza} > 0$), vengono scalati ($-1$) e marcati come `IMPEGNATO`; il sistema ricalcola il totale, invia la nuova notifica simulata e la scheda resta in `IN_ATTESA_APPROVAZIONE` (eventuali ricambi `DA_ORDINARE` già verificati come `ORDINABILE` e mantenuti nel preventivo non richiedono una nuova verifica).
   * Se almeno un nuovo ricambio inserito ha $\text{giacenza} = 0$, viene marcato come `DA_ORDINARE`, il preventivo torna in `BOZZA` e la scheda **retrocede a `IN_VERIFICA_MAGAZZINO`** in attesa della conferma del Magazziniere (dopo la quale il Tecnico potrà reinviarlo).
3. **Rifiuto del Preventivo:**
   * Il Tecnico registra il rifiuto: il preventivo passa a `RIFIUTATO`, tutti i ricambi marcati come **`IMPEGNATO`** vengono rilasciati in magazzino ($+1$) e la scheda passa allo stato `ANNULLATA`.
4. **Approvazione del Preventivo:**
   * Il Tecnico registra l'approvazione: il preventivo passa ad `APPROVATO` e viene bloccato definitivamente (non sono più ammessi modifiche o annullamenti).
   * Se non ci sono ricambi `DA_ORDINARE` ($0$ ricambi oppure tutti già `IMPEGNATO`), la scheda passa direttamente a `IN_RIPARAZIONE`.
   * Se è presente almeno un ricambio marcato come `DA_ORDINARE`, la scheda passa a `IN_ATTESA_RICAMBI`.

### Fase 4: Ordine di Acquisto e Arrivo Ricambi
1. Per le schede in `IN_ATTESA_RICAMBI`, il **Responsabile Magazzino** registra l'**Ordine di Acquisto** (`IN_CORSO`) vincolato a quella scheda per i ricambi `DA_ORDINARE`.
2. Alla consegna del fornitore, il Responsabile Magazzino registra la ricezione dell'ordine (`RICEVUTO`): i pezzi arrivati vengono **assegnati direttamente alla scheda collegata** (passando da `DA_ORDINARE` a `IMPEGNATO`, lasciando invariata la giacenza libera del magazzino) e la scheda passa automaticamente allo stato `IN_RIPARAZIONE`.

### Fase 5: Riparazione, Consegna e Chiusura
1. **Esecuzione Riparazione:** Il Tecnico esegue l'intervento sulla scheda `IN_RIPARAZIONE`. In caso di sostituzione della scheda madre, può aggiornare il `seriale` del dispositivo (previa verifica di univocità). Al termine chiude la riparazione: la scheda passa a `RIPARATA` e parte la notifica simulata al cliente.
2. **Riconsegna e Regolazione Contabile:** Il Tecnico avvia la consegna per una scheda in stato `RIPARATA`, `ANNULLATA` o `NON_RIPARABILE`:
   * Se `RIPARATA`: registra il pagamento (`SALDO_FINALE`) pari a $\max(0,00\text{ EUR},\; \text{Totale Preventivo} - 10,00\text{ EUR})$.
   * Se `ANNULLATA`: registra la riconsegna a saldo $0,00\text{ EUR}$ (`SALDO_FINALE`).
   * Se `NON_RIPARABILE`: registra il **rimborso di $-10,00\text{ EUR}$** (`RIMBORSO`) al cliente.
3. Conclusa la registrazione, la scheda passa allo stato finale **`CHIUSA`**.

---

## 5. Tabella Completa degli Stati e delle Transizioni (State Machine)

| Stato di Partenza | Evento / Azione | Condizione (Guardia) / Effetto sulle Scorte | Stato di Arrivo | Operatore |
| :--- | :--- | :--- | :--- | :--- |
| *(Inizio)* | Creazione scheda e incasso $10,00\text{ EUR}$ | Nessuna scheda attiva sullo stesso dispositivo | **`ACCETTATA`** | Tecnico |
| `ACCETTATA` | Avvio diagnosi tecnica | — | **`IN_DIAGNOSI`** | Tecnico |
| `ACCETTATA` | Rinuncia anticipata del cliente | — | **`ANNULLATA`** | Tecnico |
| `ACCETTATA` | Salvataggio bozza preventivo | Ricambi con $\text{giacenza} > 0$ scalati ($-1$, `IMPEGNATO`) | **`IN_DIAGNOSI`** | Tecnico |
| `IN_DIAGNOSI` | Salvataggio bozza preventivo | Ricambi con $\text{giacenza} > 0$ scalati ($-1$, `IMPEGNATO`) | **`IN_DIAGNOSI`** | Tecnico |
| `IN_DIAGNOSI` | Richiesta verifica ricambi esauriti | Almeno un ricambio ha $\text{giacenza} = 0$ (`DA_ORDINARE`) | **`IN_VERIFICA_MAGAZZINO`** | Tecnico |
| `IN_DIAGNOSI` | Rilevato ricambio fuori produzione | Ricambio già `FUORI_PRODUZIONE`; rilascio ($+1$) pezzi `IMPEGNATO` | **`NON_RIPARABILE`** | Tecnico |
| `IN_DIAGNOSI` | Invio preventivo al cliente | $0$ ricambi, oppure tutti `IMPEGNATO` o verificati `ORDINABILE` | **`IN_ATTESA_APPROVAZIONE`** | Tecnico |
| `IN_DIAGNOSI` | Rinuncia anticipata del cliente | Rilascio ($+1$) eventuali pezzi `IMPEGNATO` in bozza | **`ANNULLATA`** | Tecnico |
| `IN_VERIFICA_MAGAZZINO` | Conferma pezzi ordinabili | Tutti i ricambi `DA_ORDINARE` verificati come `ORDINABILE` | **`IN_DIAGNOSI`** *(pronto per invio)* | Resp. Magazzino |
| `IN_VERIFICA_MAGAZZINO` | Rilevato pezzo fuori produzione | Almeno un pezzo è `FUORI_PRODUZIONE`; rilascio ($+1$) pezzi `IMPEGNATO` | **`NON_RIPARABILE`** | Resp. Magazzino |
| `IN_VERIFICA_MAGAZZINO` | Rinuncia anticipata del cliente | Rilascio ($+1$) eventuali pezzi `IMPEGNATO` in bozza | **`ANNULLATA`** | Tecnico |
| `IN_ATTESA_APPROVAZIONE` | Modifica preventivo (pezzi disponibili) | Rilascio ($+1$) vecchi pezzi `IMPEGNATO`; nuovi pezzi tutti `IMPEGNATO` | **`IN_ATTESA_APPROVAZIONE`** | Tecnico |
| `IN_ATTESA_APPROVAZIONE` | Modifica preventivo (pezzo esaurito) | Rilascio ($+1$) vecchi pezzi `IMPEGNATO`; nuovo pezzo `DA_ORDINARE` ($\text{giacenza} = 0$) | **`IN_VERIFICA_MAGAZZINO`** | Tecnico |
| `IN_ATTESA_APPROVAZIONE` | Rifiuto del preventivo | Rilascio ($+1$) in magazzino dei soli pezzi `IMPEGNATO` | **`ANNULLATA`** | Tecnico |
| `IN_ATTESA_APPROVAZIONE` | Approvazione del preventivo | Nessun pezzo `DA_ORDINARE` ($0$ ricambi o tutti già `IMPEGNATO`) | **`IN_RIPARAZIONE`** | Tecnico |
| `IN_ATTESA_APPROVAZIONE` | Approvazione del preventivo | Almeno un pezzo verificato è `DA_ORDINARE` | **`IN_ATTESA_RICAMBI`** | Tecnico |
| `IN_ATTESA_RICAMBI` | Registrazione ordine e arrivo merce | Ordine ricevuto; pezzi `DA_ORDINARE` passano a `IMPEGNATO` sulla scheda | **`IN_RIPARAZIONE`** | Resp. Magazzino |
| `IN_RIPARAZIONE` | Chiusura intervento tecnico | Eventuale aggiornamento `seriale` valido e univoco | **`RIPARATA`** | Tecnico |
| `RIPARATA` | Consegna dispositivo e saldo | Pagamento $\max(0,00\text{ EUR},\; \text{Totale} - 10,00\text{ EUR})$ registrato | **`CHIUSA`** | Tecnico |
| `ANNULLATA` | Riconsegna dispositivo non riparato | Saldo $0,00\text{ EUR}$ registrato | **`CHIUSA`** | Tecnico |
| `NON_RIPARABILE` | Riconsegna dispositivo e rimborso | Rimborso $-10,00\text{ EUR}$ registrato | **`CHIUSA`** | Tecnico |

---

## 6. Mappatura dei Design Pattern (Allineamento con il Project Plan)

Per soddisfare i requisiti architetturali del Project Plan, il sistema adotta i seguenti tre design pattern:

1. **Pattern State (`StatoScheda`):**
   * Gestisce il comportamento della `SchedaRiparazione` in base allo stato corrente, abilitando solo le transizioni valide (es. impedisce la modifica del preventivo nello stato `IN_RIPARAZIONE` o la consegna nello stato `IN_DIAGNOSI`) e sollevando eccezioni per le operazioni non consentite.
2. **Pattern Strategy (`CalcoloSaldoStrategy`):**
   * Incapsula l'algoritmo di calcolo del saldo finale alla consegna attraverso tre strategie concrete:
     * `SaldoRiparazioneCompletataStrategy`: restituisce $\max(0,00\text{ EUR},\; \text{Totale Preventivo} - 10,00\text{ EUR})$.
     * `SaldoSchedaAnnullataStrategy`: restituisce $0,00\text{ EUR}$.
     * `RimborsoNonRiparabileStrategy`: restituisce $-10,00\text{ EUR}$.
3. **Pattern Observer (`SchedaObserver` / `NotificaClienteObserver`):**
   * Disaccoppia le transizioni di stato della `SchedaRiparazione` dalla generazione delle notifiche simulate al cliente (invio preventivo, pezzo fuori produzione, riparazione conclusa), tracciando ogni invio tramite **Log4j**.

---

## 7. Regole di Validazione Input e Casi di Test (per JUnit)

Per garantire la copertura con test JUnit nel Documento 5 (Testing), il sistema applica le seguenti validazioni:

1. **Validazione Anagrafica Cliente:**
   * `codiceFiscale`: obbligatorio, esattamente $16$ caratteri alfanumerici, univoco per ogni cliente.
   * `nome`, `cognome`: obbligatori, non vuoti.
   * `telefono`: obbligatorio, composto da sole cifre numeriche (ed eventuale `+` iniziale).
   * `email`: obbligatoria, formato valido contenente `@` e dominio.
2. **Validazione Dispositivo e Scheda:**
   * `seriale`, `marca`, `modello`, `guastoDichiarato`, `diagnosiTecnica`: obbligatori, non nulli e non composti da soli spazi.
   * `seriale`: univoco nell'intero database (sia in registrazione sia in caso di sostituzione scheda madre).
   * **Blocco doppia scheda attiva:** divieto di aprire una nuova scheda se il dispositivo ha già una scheda in stato diverso da `CHIUSA`.
3. **Validazione Preventivo, Magazzino e Valori Monetari:**
   * `costoManodopera`, `prezzoUnitario`: tipo `BigDecimal` con $2$ cifre decimali; $\text{costoManodopera} \ge 0,00\text{ EUR}$ (ma se $0$ ricambi, il `costoManodopera` deve essere $> 0,00\text{ EUR}$); $\text{prezzoUnitario} > 0,00\text{ EUR}$.
   * `giacenza`: intero $\ge 0$; verifica automatica che il rifiuto di un preventivo non incrementi mai la giacenza dei pezzi marcati come `DA_ORDINARE`.
   * **Blocco modifiche post-approvazione:** qualsiasi tentativo di modificare o annullare un preventivo già `APPROVATO` solleva un'eccezione.
   * **Blocco consegna non autorizzata:** qualsiasi tentativo di consegnare un dispositivo con scheda in stato diverso da `RIPARATA`, `ANNULLATA` o `NON_RIPARABILE` viene rifiutato.