# Documento 4 – Design

*Tech Repair – Team Tech Repair, Corso di Ingegneria del Software AA 2026/27.*

## Versioni

|Versione|Data|Modifiche|
|-|-|-|
|0.1|22/11/2026|Prima bozza (sprint 2): struttura del documento, capitolo di architettura (stile, package preliminari, decisioni, viste, valutazione), impostazione del design e dei pattern; segnaposto per i diagrammi e per le parti che dipendono da modelli e codice non ancora pronti|

## Come leggere questo documento

Le parti scritte sono complete rispetto alle informazioni oggi disponibili (Documento 3, Specifica Logica e Funzionale, Project Plan). Le parti in attesa sono segnalate da un riquadro di questo tipo:

> **DA COMPLETARE** · *Si aspetta:* che cosa manca · *Da:* issue o persona · *Quando:* sprint o data

Quando la dipendenza è risolta, il riquadro si toglie e si aggiunge una riga alla tabella delle versioni. Le immagini dei diagrammi sono lasciate come commento HTML con il percorso atteso: basta togliere il commento quando il file SVG è in `Documenti/Immagini/`.

## 1. Introduzione

### 1.1 Scopo

Il Documento 4 descrive il design di Tech Repair: come i requisiti del Documento 3 diventano modelli UML, un'architettura software e un progetto di dettaglio da cui si genera e si completa il codice. Corrisponde ai work package WP3 (modellazione UML) e WP4 (architettura e design) del Project Plan. È destinato al team di sviluppo (implementazione e test) e ai docenti che valutano il progetto.

### 1.2 Struttura e riferimenti

Il documento ha tre parti:

* **Modelling (capitolo 2):** i diagrammi UML realizzati con StarUML 5, ciascuno con una riga di contesto.
* **Software Architecture (capitolo 3):** stile architetturale, package, decisioni, viste e valutazione.
* **Software Design (capitolo 4):** principi di progetto, metriche, design pattern, descrizione dei componenti secondo IEEE 1016, tracciabilità.

Riferimenti: Documento 1 (Project Plan, versione 1.1.3), Documento 2 (Gestione del progetto), Documento 3 (Requisiti, versione 1.0), Specifica Logica e Funzionale di Tech Repair, IEEE 1471 (descrizione dell'architettura), IEEE 1016 (descrizione del design).

### 1.3 Stato di avanzamento

|Sezione|Stato|Cosa si aspetta|
|-|-|-|
|2.1 Use case e class diagram del dominio|Rimando al Documento 3|Niente|
|2.2 Class diagram di design|In attesa|Issue «Class diagram di design in StarUML» (sprint 2, entro 7/12)|
|2.3 State machine|In attesa|Issue «State machine della scheda di riparazione in StarUML»|
|2.4 Sequence diagram|In attesa|Issue «Sequence diagram dei flussi principali in StarUML»|
|2.5 Activity diagram, 2.6 Communication o timing|In attesa|Issue «Activity e communication/timing diagram in StarUML»|
|2.7 Package e component diagram|Parziale|Package: testo in §3.3, diagramma StarUML dopo la conferma del team. Component diagram: sprint 3 (WP4)|
|3.1 – 3.6 Architettura|Scritta (bozza)|Approvazione del team in riunione; scelta del database (issue «Generazione del codice da StarUML e setup Maven con database embedded»); verifica sul codice in sprint 4|
|4.1 – 4.3 Approccio, principi, metriche|Scritti|Valori delle metriche: WMC dopo il class diagram, il resto in sprint 4|
|4.4 Design pattern|Scritta (concetto e applicazione)|Nomi definitivi delle classi e figure dal class diagram di design|
|4.5 Descrizione dei componenti (IEEE 1016)|Parziale|Decomposizione e dipendenze scritte; interfacce dopo il class diagram; dettaglio in sprint 3-4|
|4.6 Classi generate da StarUML|In attesa|Generazione del codice (fine sprint 2)|
|4.7 Tracciabilità|Scritta per gruppi funzionali|Nomi di classi e test (Documento 5)|
|4.8 Verifica del design|Scritta (bozza)|Integrazione con la checklist del corso|

## 2. Modelling

I diagrammi sono realizzati con StarUML 5 nel file di progetto in `Documenti/` e esportati in SVG in `Documenti/Immagini/`. Questo capitolo contiene solo i diagrammi, con una riga di contesto, la fonte e le verifiche di coerenza. L'ordine di lavoro sul file `.mdj` segue la regola della Critical Section (Documento 2, §3.4): prima il class diagram di design, poi state machine, sequence, activity e communication, infine package e component diagram; la generazione del codice va per ultima.

### 2.1 Diagrammi già prodotti (Documento 3)

Lo use case diagram (Documento 3, §4.1) e il class diagram del dominio (Documento 3, §5) non sono riprodotti qui. Il class diagram di design li estende.

### 2.2 Class diagram di design

Estende il class diagram del dominio con operazioni, visibilità, classi di servizio, interfacce dei repository e i tre pattern (State, Strategy, Observer). È il modello da cui StarUML genera una sola volta le classi Java (Documento 2, §2.6).

<!-- ![Class Diagram di Design](Immagini/ClassDiagram_Design.svg) -->

> **DA COMPLETARE** · *Si aspetta:* il diagramma · *Da:* issue «Class diagram di design in StarUML» · *Quando:* prima della generazione del codice (fine sprint 2, entro 7/12)

Verifiche di coerenza da fare quando il diagramma viene inserito:

* Sono presenti tutte le entità del Documento 3 (§5): Cliente, Dispositivo, SchedaRiparazione, Preventivo, Ricambio, OrdineAcquisto, Pagamento, VoceRicambio, CambioStato.
* Sono presenti le enumerazioni della Specifica Logica: `StatoScheda`, `StatoVoce` (IMPEGNATO, DA_ORDINARE), stato del preventivo (BOZZA, INVIATO, APPROVATO, RIFIUTATO), qualità (ORIGINALE, COMPATIBILE), reperibilità (DISPONIBILE, DA_VERIFICARE, ORDINABILE, FUORI_PRODUZIONE), stato dell'ordine (IN_CORSO, RICEVUTO), tipo di pagamento (DIAGNOSI_INIZIALE, SALDO_FINALE, RIMBORSO).
* I pattern usano i nomi della Specifica: `StatoSchedaState` (distinto da `StatoScheda` per evitare collisioni nella generazione), `CalcoloSaldoStrategy` con le tre strategie concrete, `SchedaObserver` e `NotificaClienteObserver`.
* Gli importi sono `BigDecimal` a due decimali e `CambioStato.data` è `LocalDateTime`.
* Gli attributi sono `private` e i package rispettano le dipendenze del §3.3 (nessun ciclo).

### 2.3 State machine della scheda di riparazione

Rappresenta il ciclo di vita della scheda. Fonte: tabella degli stati e delle transizioni della Specifica Logica (§5).

<!-- ![State Machine della Scheda](Immagini/StateMachine_Scheda.svg) -->

> **DA COMPLETARE** · *Si aspetta:* il diagramma · *Da:* issue «State machine della scheda di riparazione in StarUML» · *Quando:* sprint 2

Verifiche di coerenza:

* Dieci stati: ACCETTATA, IN_DIAGNOSI, IN_VERIFICA_MAGAZZINO, IN_ATTESA_APPROVAZIONE, IN_ATTESA_RICAMBI, IN_RIPARAZIONE, RIPARATA, ANNULLATA, NON_RIPARABILE, CHIUSA.
* Ventitré transizioni, tante quante le righe della tabella della Specifica (inclusa la creazione della scheda), con evento, guardia ed effetto sulle scorte.
* Ogni flusso di eccezione (preventivo rifiutato, pezzo fuori produzione, modifica del preventivo, rinuncia anticipata) compare come transizione (Documento 2, §2.4).
* Gli stati finali operativi ANNULLATA e NON_RIPARABILE arrivano a CHIUSA solo con la riconsegna.

### 2.4 Sequence diagram dei flussi principali

Tre diagrammi, uno per flusso. Le lifeline seguono i livelli del §3.3 (`cli`, `service`, `domain`, `persistence`) e ogni flusso di eccezione è un frammento `alt`.

#### 2.4.1 Accettazione e creazione scheda

Fonte: use case «Accetta Dispositivo e Crea Scheda» (Documento 3, §4.2) e Specifica Logica, §4 (fase 1). Deve mostrare la ricerca del cliente per codice fiscale (ramo cliente nuovo o esistente), la verifica di seriale univoco e assenza di scheda attiva, la creazione della scheda in stato ACCETTATA, il pagamento anticipato DIAGNOSI_INIZIALE di 10 € e la generazione della ricevuta. Frammenti `alt`: dati non validi, dispositivo con scheda già attiva. Requisiti: RF-01, RF-02, RF-03, RF-18.

<!-- ![Sequence – Accettazione](Immagini/Sequence_Accettazione.svg) -->

> **DA COMPLETARE** · *Si aspetta:* il diagramma · *Da:* issue «Sequence diagram dei flussi principali in StarUML» · *Quando:* sprint 2

#### 2.4.2 Preventivo con verifica magazzino

Fonte: use case «Invia Preventivo», flussi A-D (Documento 3, §4.2) e Specifica Logica, §4 (fasi 2 e 3). Deve mostrare la diagnosi, l'inserimento delle voci, l'impegno di un ricambio disponibile (giacenza −1), il ricambio esaurito che diventa DA_ORDINARE con passaggio a IN_VERIFICA_MAGAZZINO e verifica del Responsabile Magazzino, l'invio con notifica al cliente (Observer) e la decisione del cliente. Frammenti `alt`: ricambio disponibile, esaurito, fuori produzione; modifica del preventivo; rifiuto; approvazione con o senza ricambi da ordinare. Requisiti: RF-04 – RF-10, RF-15, RF-16, RF-18.

<!-- ![Sequence – Preventivo con verifica magazzino](Immagini/Sequence_Preventivo.svg) -->

> **DA COMPLETARE** · *Si aspetta:* il diagramma · *Da:* stessa issue · *Quando:* sprint 2

#### 2.4.3 Consegna e pagamento

Fonte: use case «Consegna Dispositivo Riparato» e Specifica Logica, §4 (fase 5). Deve mostrare la chiusura della riparazione con notifica, la consegna consentita solo per schede RIPARATA, ANNULLATA o NON_RIPARABILE, la scelta della strategia di calcolo del saldo (pattern Strategy), la registrazione del pagamento o del rimborso e la chiusura della scheda. Frammenti `alt`: riparata, annullata, non riparabile, stato che non consente la consegna, seriale duplicato in aggiornamento. Requisiti: RF-12, RF-13, RF-14, RF-18.

<!-- ![Sequence – Consegna e pagamento](Immagini/Sequence_Consegna.svg) -->

> **DA COMPLETARE** · *Si aspetta:* il diagramma · *Da:* stessa issue · *Quando:* sprint 2

> **Copertura da decidere:** l'issue prevede questi tre flussi, ma lo use case «Ordina Pezzo di Ricambio» (RF-11) non è coperto da nessun sequence diagram. Si può inserire come frammento `ref` nel diagramma 2.4.2 o in un quarto diagramma, oppure coprirlo nell'activity diagram.

### 2.5 Activity diagram del flusso officina

Mostra il percorso completo di un dispositivo, dall'accettazione alla chiusura, con corsie (swimlane) per Tecnico Riparatore, Responsabile Magazzino e Sistema, e nodi decisionali per gli esiti: preventivo approvato, rifiutato, ricambio esaurito, ricambio fuori produzione. Il Cliente è un attore passivo e compare come destinatario delle notifiche.

<!-- ![Activity Diagram del flusso officina](Immagini/Activity_Officina.svg) -->

> **DA COMPLETARE** · *Si aspetta:* il diagramma · *Da:* issue «Activity e communication/timing diagram in StarUML» · *Quando:* sprint 2

### 2.6 Communication o timing diagram

Scenario proposto: **pezzo non in magazzino** (scenario S2 del Documento 3, Parte A), perché coinvolge tutti e tre gli attori, un cambio di stato e la notifica Observer. Per questo scenario il communication diagram è più adatto del timing diagram, che serve a mostrare vincoli di tempo che qui non ci sono. La scelta finale spetta all'autore dell'issue.

<!-- ![Communication Diagram – pezzo non in magazzino](Immagini/Communication_PezzoNonInMagazzino.svg) -->

> **DA COMPLETARE** · *Si aspetta:* il diagramma · *Da:* stessa issue · *Quando:* sprint 2

### 2.7 Package diagram e component diagram

La struttura preliminare dei package è descritta in forma testuale nel §3.3. Il diagramma StarUML va fatto solo dopo che il team ha confermato quella struttura, ma i package servono prima che il class diagram di design sia chiuso, perché da essi dipende la struttura delle classi generate. Se si modella in StarUML, l'issue richiede la label *Critical Section* e va messa in sequenza con le altre.

<!-- ![Package Diagram](Immagini/Package_Diagram.svg) -->
<!-- ![Component Diagram](Immagini/Component_Diagram.svg) -->

> **DA COMPLETARE** · *Si aspetta:* conferma del team sui package (§3.3), poi il diagramma; il component diagram dopo la generazione del codice · *Da:* issue «Prime viste architetturali e struttura del Documento 4» (package), WP4 (component) · *Quando:* package in sprint 2, component diagram in sprint 3 (8-21/12)

## 3. Software Architecture

### 3.1 Contesto e driver architetturali

L'architettura nasce dal bilanciamento tra requisiti funzionali e requisiti di qualità e si descrive in funzione degli stakeholder e delle loro preoccupazioni (IEEE 1471).

|Stakeholder|Preoccupazioni (concern)|Viste che li servono|
|-|-|-|
|Tecnico Riparatore, Responsabile Magazzino (utenti della console)|Flussi di lavoro corretti, messaggi d'errore chiari (RQ-03), dati persistenti (RQ-02)|Logica, scenari|
|Cliente (attore passivo)|Importi esatti (RQ-01), notifiche (RF-06, RF-12, RF-16)|Scenari, processi|
|Team di sviluppo|Sapere dove implementare cosa, testare la logica (RQ-05)|Sviluppo, logica|
|Chi mantiene il sistema|Modificabilità, package senza cicli (RQ-04), portabilità (RQ-06)|Sviluppo|
|Docenti|Coerenza tra modelli, codice e documenti, tracciabilità|Tutte, tracciabilità (§4.7)|

Gli attributi di qualità del Documento 3 (§3.6) guidano le scelte architetturali così:

|Attributo|Implicazione sull'architettura|
|-|-|
|RQ-01 Correttezza dei calcoli monetari|Calcolo del saldo isolato in una strategia dedicata, importi in `BigDecimal`|
|RQ-02 Integrità dei dati|Regole di integrità nel dominio, vincoli di unicità sul database|
|RQ-03 Usabilità della console|Interfaccia separata dalla logica, nessuna regola di business nella console|
|RQ-04 Manutenibilità|Livelli, interfacce, package senza cicli, information hiding|
|RQ-05 Testabilità e tracciabilità|Logica indipendente dalla console, repository sostituibili nei test|
|RQ-06 Portabilità|Database embedded, nessun percorso assoluto|

Vincoli dati (Documento 3, §2.4 e Project Plan): Java con Maven, Log4j, database embedded, interfaccia solo a riga di comando, un operatore alla volta in una sola sede, notifiche simulate.

### 3.2 Stile architetturale

**Scelta: architettura a livelli (layered), in un'unica applicazione standalone**, descritta con lo schema problema – contesto – modello di sistema – componenti – connettori – struttura di controllo.

* **Problema:** servizi distinti organizzabili gerarchicamente: interazione con l'operatore, regole dell'officina, accesso ai dati.
* **Contesto:** un solo processo Java, un operatore alla volta (Documento 3, §2.5), nessun servizio esterno da installare.
* **Modello di sistema:** una gerarchia di livelli in cui ogni livello usa solo quelli sottostanti e quello inferiore non conosce quelli superiori.
* **Componenti:** gruppi di classi, uno per package.
* **Connettori:** chiamate di metodo.
* **Struttura di controllo:** un solo thread di controllo, guidato dai menu della console.
* **Variante adottata:** il dominio è visibile a tutti i livelli ma non dipende da nessuno. Limitare la visibilità al solo livello adiacente costringerebbe a duplicare le entità a ogni livello.

Nel sistema compaiono anche altri due stili, perché in pratica un'architettura è quasi sempre una miscela di stili:

* **Invocazione implicita** per le notifiche (pattern Observer): la scheda genera eventi senza sapere quale componente reagirà.
* **Repository** per la persistenza: dati di lunga durata la cui integrità è importante, con un componente di memoria e componenti computazionali che lo usano.

Stili considerati e scartati:

* **Programma principale con subroutine:** decomposizione per funzioni con dati condivisi, che rende difficile cambiare la rappresentazione dei dati.
* **Pipes and filters:** non c'è un flusso continuo di dati da trasformare.
* **Client-server o microservizi:** il sistema è standalone e usato da un operatore. Un'applicazione monolitica è la scelta più semplice e meno costosa nelle prime fasi di un progetto.
* **MVC:** serve a separare interfacce utente flessibili. Con la sola console la separazione tra `cli` e `service` ottiene lo stesso scopo e consente di sostituire la console in futuro (scenario CC2, §3.6).

I livelli sono:

|Livello|Responsabilità|Package|
|-|-|-|
|Presentazione|Menu di scelta ruolo, lettura dell'input, stampa dei risultati e dei messaggi d'errore. Nessuna regola di business|`cli`|
|Logica applicativa|Un servizio per gruppo di casi d'uso: orchestra entità e repository|`service`|
|Dominio|Entità, enumerazioni, regole di integrità, pattern State, Strategy e Observer, interfacce dei repository|`domain`|
|Persistenza|Implementazione dei repository sul database embedded|`persistence`|

Un package aggiuntivo, `app`, contiene l'avvio dell'applicazione e assembla i componenti (crea le implementazioni di `persistence` e le passa ai servizi): non è un livello, ma è l'unico punto che conosce tutti gli altri.

### 3.3 Struttura a package preliminare

> **Proposta preliminare:** da validare con il team (riunione del lunedì 23/11) prima di chiudere il class diagram di design. Il nome del package radice e il numero di progetti Maven (uno solo o più moduli) sono fissati nell'issue «Generazione del codice da StarUML e setup Maven con database embedded»; la struttura a package vale in entrambi i casi.

```
techrepair
├── app             avvio dell'applicazione e assemblaggio dei componenti
├── cli             interfaccia console (menu ruolo, menu Tecnico, menu Magazzino)
├── service         logica applicativa (casi d'uso)
├── domain          entità, enumerazioni, State, Observer
│   ├── saldo       pattern Strategy per il calcolo del saldo
│   └── repository  interfacce di accesso ai dati
└── persistence     implementazione dei repository sul database embedded
```

#### 3.3.1 Dipendenze ammesse

|Package|Può usare|
|-|-|
|`app`|Tutti|
|`cli`|`service`, `domain`|
|`service`|`domain`, `domain.saldo`, `domain.repository`|
|`persistence`|`domain`, `domain.repository`|
|`domain.repository`|`domain`|
|`domain.saldo`|Nessun package del progetto|
|`domain`|Nessun package del progetto|

Con queste regole il `service` dipende dalle interfacce dei repository e non conosce il database scelto: è ciò che rende il database sostituibile (scenario CC1, §3.6).

#### 3.3.2 Regole di struttura

1. Nessun ciclo tra package (Project Plan, §4): controllo con JDepend o Structure101 a fine sprint.
2. Information hiding: classi `package-private` quando non servono fuori dal package, attributi `private`, interfacce pubbliche minime.
3. Il database si raggiunge solo da `persistence`.
4. La console (`System.in` e `System.out`) si usa solo in `cli`; tracciamento ed eventi passano da Log4j (RV-02).
5. Nessun percorso assoluto in nessun package (RQ-06).

#### 3.3.3 Cicli: punti di attenzione

* **State e Observer richiedono che la scheda e i suoi stati/osservatori si conoscano a vicenda.** Se fossero in package diversi si creerebbe un ciclo, quindi `StatoSchedaState`, `SchedaObserver` e `SchedaRiparazione` restano nello stesso package `domain` (un ciclo dentro un solo package è accettabile).
* **Strategy:** per non creare un ciclo tra `domain` e `domain.saldo`, la strategia riceve l'importo (`BigDecimal`) e non l'entità, e a scegliere la strategia in base all'esito è il `service`. Questa regola va recepita nel class diagram di design.

> **DA COMPLETARE** · *Si aspetta:* conferma del team sui package, nome del package radice e numero di progetti Maven · *Da:* riunione del lunedì e issue «Generazione del codice da StarUML e setup Maven con database embedded» · *Quando:* prima di chiudere il class diagram di design (sprint 2)

### 3.4 Decisioni architetturali

Le decisioni importanti sono registrate con uno schema fisso (problema, decisione, stato, presupposti, alternative, motivazione, implicazioni, appunti). Le decisioni del team si prendono per consenso; se non si raggiunge l'accordo decide il Project Manager con un commento nell'issue (Documento 2, §4.2).

**AD-01 – Architettura a livelli**

|Elemento|Descrizione|
|-|-|
|Problema|Strutturare il sistema in modo manutenibile, testabile e portabile (RQ-04, RQ-05, RQ-06), con quattro persone che lavorano in parallelo|
|Decisione|Livelli `cli`, `service`, `domain`, `persistence`, più il package di avvio `app`; dominio condiviso|
|Stato|Proposta, da approvare dal team|
|Presupposti|Un solo processo, un operatore alla volta, prestazioni non stringenti (RP-01)|
|Alternative|Programma principale con subroutine, MVC, client-server o microservizi|
|Motivazione|Accoppiamento debole tra livelli: console e database si cambiano senza toccare le regole; la logica si testa con JUnit senza console; il lavoro si divide per package|
|Implicazioni|Più classi e interfacce; ogni operazione attraversa i livelli (effetto sulle prestazioni trascurabile per RP-01); servono controlli sui cicli|
|Appunti|Nessuno|

**AD-02 – Applicazione standalone con interfaccia console**

|Elemento|Descrizione|
|-|-|
|Problema|Come interagiscono con il sistema Tecnico e Responsabile Magazzino|
|Decisione|Applicazione Java a riga di comando con menu iniziale di scelta ruolo, senza password|
|Stato|Approvata (RI-01, perimetro del Project Plan)|
|Presupposti|Il Cliente non usa mai il sistema, comunica con il tecnico|
|Alternative|Interfaccia grafica o web (fuori perimetro)|
|Motivazione|Perimetro del progetto e ore a disposizione|
|Implicazioni|La sicurezza con password non è inclusa (Documento 3, §3.6); `cli` deve restare sostituibile|
|Appunti|Nessuno|

**AD-03 – Persistenza su database embedded dietro interfacce repository**

|Elemento|Descrizione|
|-|-|
|Problema|Persistere i dati (RD-01, RD-02, RD-03) senza servizi esterni da installare (RI-02, RQ-06)|
|Decisione|Database embedded raggiunto solo da `persistence`, attraverso repository le cui interfacce stanno in `domain.repository`|
|Stato|Principio approvato; prodotto del database in sospeso (DA-01)|
|Presupposti|Un operatore alla volta; dati che devono sopravvivere al riavvio (RQ-02)|
|Alternative|File piatti (poco affidabili per l'integrità), database con server esterno (violerebbe RI-02 e RQ-06)|
|Motivazione|Portabilità, integrità, sostituibilità del database|
|Implicazioni|Il prodotto va scelto con criteri chiari: solo dipendenza Maven, file in percorso relativo, importi decimali esatti, transazioni, licenza libera|
|Appunti|Corrisponde allo stile Repository (§3.2)|

**AD-04 – Tre design pattern: State, Strategy, Observer**

|Elemento|Descrizione|
|-|-|
|Problema|Il comportamento della scheda dipende dallo stato (RV-04), il calcolo del saldo varia con l'esito (RF-14), le notifiche devono essere disaccoppiate dalla scheda (RI-03)|
|Decisione|State per la scheda, Strategy per il saldo, Observer per le notifiche|
|Stato|Approvata (Project Plan, §8; Specifica Logica, §6)|
|Presupposti|Stati, esiti e canali di notifica possono aumentare|
|Alternative|`if`/`switch` sullo stato e sull'esito, chiamata diretta del codice di notifica|
|Motivazione|Nuovi stati, esiti e canali si aggiungono con nuove classi senza toccare quelle esistenti|
|Implicazioni|Più classi; i cicli tra scheda, stati e osservatori vanno gestiti (§3.3.3)|
|Appunti|Dettaglio nel §4.4|

**AD-05 – Log4j e notifiche simulate**

|Elemento|Descrizione|
|-|-|
|Problema|Tracciare eventi e notifiche senza usare la console, riservata all'interazione (RV-02, RI-03)|
|Decisione|Log4j per eventi e notifiche; nessun `System.out.println` fuori da `cli`|
|Stato|Approvata|
|Presupposti|Le notifiche sono simulate, nessun invio reale|
|Alternative|`System.out.println` (vietato), `java.util.logging`|
|Motivazione|Requisito del Project Plan e del Documento 3|
|Implicazioni|Dipendenze `log4j-api` e `log4j-core` nel `pom.xml`; configurazione senza percorsi assoluti|
|Appunti|Log4j, JUnit e il driver del database sono le librerie esterne Maven del progetto|

**AD-06 – Generazione del codice una sola volta da StarUML (MDA parziale)**

|Elemento|Descrizione|
|-|-|
|Problema|Partire dal modello senza sovrascrivere il codice scritto a mano|
|Decisione|Le classi Java si generano una sola volta, a modello stabile (fine sprint 2), con un commit dedicato; poi si lavora sul codice|
|Stato|Approvata (Project Plan, §8; Documento 2, §2.6)|
|Presupposti|Il class diagram di design è stabile prima della generazione|
|Alternative|Scrivere tutto a mano; rigenerare più volte (sovrascriverebbe il codice a mano)|
|Motivazione|Coerenza tra modello e codice con costo contenuto|
|Implicazioni|Dopo la generazione la manutenzione avviene sul codice e non sul modello; nomi scelti per evitare collisioni (per esempio `StatoSchedaState`); elenco delle classi generate nel §4.6|
|Appunti|La generazione richiede la label *Critical Section*|

**AD-07 – Tipi per importi e date**

|Elemento|Descrizione|
|-|-|
|Problema|Evitare errori di arrotondamento sugli importi e ordinare gli eventi nello stesso giorno (RQ-01)|
|Decisione|`BigDecimal` a due decimali per gli importi, `LocalDateTime` per `CambioStato`|
|Stato|Approvata (Specifica Logica, §1)|
|Presupposti|Nessuna valuta diversa dall'euro|
|Alternative|`double`, `LocalDate`|
|Motivazione|`double` introduce errori di arrotondamento; `LocalDate` non distingue eventi nella stessa giornata|
|Implicazioni|Operazioni aritmetiche con i metodi di `BigDecimal`|
|Appunti|Nessuno|

#### Decisioni aperte

|ID|Questione|Si aspetta|Proposta provvisoria|
|-|-|-|-|
|DA-01|Prodotto del database embedded|Issue «Generazione del codice da StarUML e setup Maven con database embedded»|Valutare i candidati con i criteri di AD-03|
|DA-02|Dove si stampa su console il messaggio di notifica: l'Observer sta in `domain` e non può usare la console (regola 4 del §3.3.2)|Class diagram di design|`NotificaClienteObserver` traccia con Log4j; un secondo osservatore in `cli` stampa il messaggio, e `app` li registra entrambi|
|DA-03|Package radice e numero di progetti Maven|Issue «Generazione del codice da StarUML e setup Maven con database embedded»|Package radice `techrepair`|

### 3.5 Viste architetturali

Si usa il modello 4+1: ogni vista ha un punto di vista e diagrammi UML associati.

|Vista|Serve a|Diagrammi|Stato|
|-|-|-|-|
|Logica|Sviluppatori e docenti: elementi principali e loro interazioni|Class diagram di design (§2.2), state machine (§2.3)|In attesa dei diagrammi|
|Sviluppo|Sviluppatori e chi mantiene: moduli, package, livelli|Package diagram, component diagram (§2.7); struttura testuale in §3.3|Package in attesa del diagramma, component in sprint 3|
|Processi|Comportamento dei flussi|Sequence, communication, activity (§2.4 – §2.6)|In attesa dei diagrammi|
|Distribuzione|Allocazione su nodi fisici|Nessuno|Non necessaria (vedi sotto)|
|Scenari (+1)|Guida delle altre quattro viste|Use case (Documento 3, §4)|Disponibile|

* **Vista dei processi:** serve soprattutto quando c'è concorrenza significativa. Qui c'è un solo thread e un operatore alla volta (Documento 3, §2.5): i diagrammi di questa vista descrivono il comportamento dei flussi, non il parallelismo.
* **Vista di distribuzione:** non viene prodotta. L'applicazione gira su un solo PC, il database embedded è nello stesso processo e non ci sono servizi esterni (RQ-06); un deployment diagram non aggiungerebbe informazioni.
* **Scenari guida:** gli use case *Accetta Dispositivo e Crea Scheda*, *Invia Preventivo* (il più ricco, con verifica magazzino, modifica, rifiuto e fuori produzione), *Ordina Pezzo di Ricambio* e *Consegna Dispositivo Riparato*. Corrispondono ai sequence diagram del §2.4.
* **Altre viste:** la vista a moduli corrisponde a decomposizione, uso e livelli del §3.3; la vista di allocazione (incarico di lavoro) corrisponde ai ruoli del Project Plan (§7).

### 3.6 Valutazione dell'architettura

Si usa una valutazione a scenari in forma ridotta, senza un ATAM completo. Gli scenari sono gli use case (S1 – S4 del Documento 3, Parte A) e i casi di cambiamento seguenti.

|Scenario|Cosa cambia|Esito|
|-|-|-|
|CC1 – Sostituire il database embedded|Solo `persistence` (dipendenza Maven e configurazione)|Supportato grazie ad AD-03|
|CC2 – Sostituire la console con un'interfaccia grafica|Solo `cli` e `app`|Supportato, purché nessuna regola di business stia in `cli`|
|CC3 – Notifiche reali via email o SMS|Un nuovo osservatore concreto, registrato in `app`|Supportato grazie ad AD-04|
|CC4 – Nuovo esito con un calcolo del saldo diverso|Una nuova strategia e la sua selezione nel `service`|Supportato grazie ad AD-04|
|CC5 – Nuovo stato della scheda|Enumerazione, nuova classe di stato, transizioni, state machine, menu|Supportato ma non localizzato: punto di sensibilità|
|CC6 – Più operatori contemporanei o più sedi|Persistenza e concorrenza|Non supportato: fuori dalle assunzioni del Documento 3 (§2.5)|

**Punti di sensibilità, compromessi e rischi** (vocabolario ATAM):

* **Punto di sensibilità:** le interfacce dei repository, da cui dipendono modificabilità e testabilità; la coerenza tra giacenza e reperibilità, da cui dipende l'integrità dei dati.
* **Punto di compromesso:** livelli e interfacce migliorano la modificabilità ma aumentano il numero di classi e il costo di ogni operazione. Per RP-01 il costo in prestazioni è accettabile.
* **Rischi:** cicli tra package causati da State e Observer (§3.3.3); un `service` che accumula troppe responsabilità (antipattern God class, §4.4.5).

La valutazione è fatta sul progetto e non sul codice: può solo prevedere il comportamento del sistema.

> **DA COMPLETARE** · *Si aspetta:* verifica sul codice (cicli, dipendenze e scenari di cambiamento) · *Da:* Responsabile sviluppo e test, con JDepend o Structure101 · *Quando:* sprint 4 (22/12 – 4/1)

## 4. Software Design

### 4.1 Approccio

Il design è orientato agli oggetti e procede in tre passi, che si ripetono: identificare gli oggetti, determinarne attributi e servizi, determinare le relazioni.

|Passo|Contributo nel progetto|Stato|
|-|-|-|
|Oggetti|Modello del dominio (Documento 3, §5; Specifica Logica, §1)|Scritto|
|Attributi|Specifica Logica, §1 (attributi, tipi, vincoli)|Scritto|
|Servizi (operazioni)|Class diagram di design, ricavate anche dagli scenari dei sequence diagram|In attesa|
|Relazioni|Associazioni del class diagram di design|In attesa|

Dal modello del dominio non emergono gerarchie di specializzazione tra le entità: l'ereditarietà compare solo nei pattern (State, Strategy), quindi gli alberi di ereditarietà restano poco profondi. Il design aggiunge al dominio le classi di soluzione (servizi, repository, console). Class diagram, sequence diagram e state machine si raffinano a vicenda, come previsto dal processo iterativo del Documento 2.

### 4.2 Principi e regole di progetto

I principi di progetto seguenti diventano regole del team (proposta, da approvare insieme ad AD-01).

* **Astrazione.** Astrazione dei dati: ogni entità incapsula stato e operazioni. Astrazione procedurale: ogni caso d'uso è un metodo di un servizio. Le parti che possono variare stanno dietro un'interfaccia: repository, strategia, osservatore, stato.
* **Modularità.** Coesione: ogni classe ha un solo motivo di cambiamento; lo scopo si deve poter descrivere in una frase senza "e" né riferimenti temporali. Accoppiamento: si passano solo dati semplici o oggetti di dominio tramite metodi, mai variabili globali né accesso diretto ai campi di altri oggetti.
* **Information hiding.** Attributi `private`, classi `package-private` se possibile. Ogni package nasconde un segreto: `cli` il formato di input e output, `persistence` lo schema e il prodotto del database, `domain` le regole del dominio.
* **Complessità.** Metodi corti e complessità ciclomatica massima di 10 per metodo (soglia di McCabe). Si usano più metriche insieme, perché nessuna basta da sola.
* **Struttura del sistema.** Grafo dei package aciclico e a livelli. Si applica la legge di Demetra come euristica: un metodo invia messaggi solo ai propri parametri, ai propri attributi e a sé stesso.
* **Ripartizione delle responsabilità.** Le regole che proteggono le invarianti (giacenza non negativa, reperibilità coerente, preventivo bloccato dopo l'approvazione) stanno nelle entità; ai servizi spetta solo l'orchestrazione tra più entità e repository. Serve a evitare una God class.
* **Validazione.** Le regole di RF-18 stanno nel dominio e nei servizi, non nella console: così si testano con JUnit senza input da tastiera.

### 4.3 Metriche di design

|Livello|Metriche|
|-|-|
|Package|Accoppiamento afferente (Ca) ed efferente (Ce), instabilità I = Ce/(Ce+Ca), astrazione A = Na/Nc, presenza di cicli|
|Classe|WMC, CBO, RFC, LCOM. DIT e NOC si raccolgono ma si interpretano con cautela|
|Metodo|Complessità ciclomatica (massimo 10), righe di codice|

Strumenti: JDepend o Structure101 per i package (Project Plan, §6); per le metriche di classe e di metodo uno strumento di metriche da scegliere in sprint 4 (Project Plan, §11).

**Valori attesi dalle regole del §3.3:** `domain` non dipende da nessuno (Ce = 0, quindi I vicino a 0, package stabile); `app` non è usato da nessuno (Ca = 0, quindi I = 1); `cli` e `persistence` hanno instabilità alta, `service` intermedia.

> **DA COMPLETARE** · *Si aspetta:* WMC (con complessità di ogni metodo posta a 1, cioè il numero di metodi) calcolabile già dal class diagram; tutte le altre misure richiedono il codice · *Da:* Responsabile sviluppo e test · *Quando:* WMC dopo il class diagram, il resto in sprint 4. Da decidere in riunione dove riportare i valori: in questo documento (design) o nel Documento 5 (il Project Plan assegna le metriche al WP6)

### 4.4 Design pattern

Ogni pattern è descritto con contesto, problema e soluzione, poi l'applicazione a Tech Repair.

#### 4.4.1 State

* **Contesto:** la scheda attraversa dieci stati con ventitré transizioni, e le operazioni ammesse dipendono dallo stato.
* **Problema:** evitare `if`/`switch` sullo stato ripetuti in ogni operazione e impedire le operazioni illegali (modificare il preventivo in IN_RIPARAZIONE, consegnare in IN_DIAGNOSI).
* **Soluzione:** ogni stato è un oggetto che implementa la stessa interfaccia, decide quali operazioni sono ammesse e quale stato segue; la scheda delega allo stato corrente. Le operazioni non consentite sollevano un'eccezione.
* **Applicazione:** interfaccia `StatoSchedaState` (nome distinto dall'enumerazione `StatoScheda` per evitare collisioni nella generazione), con una classe concreta per ogni stato (nomi nel class diagram). L'enumerazione `StatoScheda` resta il valore persistito e ogni cambio di stato aggiunge un `CambioStato` allo storico. Guardie ed effetti sulle scorte seguono la tabella della Specifica Logica (§5).
* **Requisiti:** RV-04, RF-08, RF-13, RF-15, RF-16.
* **Punto di attenzione:** la reciproca conoscenza tra scheda e stati impone che stiano nello stesso package (§3.3.3).

#### 4.4.2 Strategy

* **Contesto:** alla consegna il saldo si calcola in modo diverso secondo l'esito.
* **Problema:** isolare l'algoritmo monetario (RQ-01), renderlo testabile da solo ed evitare una catena di `if` nel servizio di consegna.
* **Soluzione:** una famiglia di algoritmi dietro la stessa interfaccia, scelta da chi la usa.
* **Applicazione:** interfaccia `CalcoloSaldoStrategy` con tre strategie concrete, scelte dal `service` secondo lo stato della scheda:

|Strategia|Esito|Saldo|
|-|-|-|
|`SaldoRiparazioneCompletataStrategy`|Riparata|max(0 €, totale − 10 €)|
|`SaldoSchedaAnnullataStrategy`|Annullata|0 €|
|`RimborsoNonRiparabileStrategy`|Non riparabile|−10 € (rimborso)|

* **Requisiti:** RF-14, RQ-01.
* **Casi limite per i test:** totale 25 € dà saldo 15 €; totale 10 € dà 0 €; totale 7 € dà 0 €; annullata dà 0 €; non riparabile dà −10 €.
* **Punto di attenzione:** la strategia riceve l'importo e non l'entità, per non creare cicli (§3.3.3).

#### 4.4.3 Observer

* **Contesto:** tre eventi producono una notifica simulata al cliente: invio del preventivo (anche in caso di modifica), impossibilità di riparare per ricambio fuori produzione, riparazione completata.
* **Problema:** la scheda non deve sapere come né dove notificare (log, console, in futuro email o SMS) e deve poter aggiungere canali senza modifiche (scenario CC3).
* **Soluzione:** la scheda (soggetto) avvisa gli osservatori registrati a ogni evento; ciascun osservatore decide cosa farne.
* **Applicazione:** interfaccia `SchedaObserver` e implementazione `NotificaClienteObserver`, che traccia ogni notifica con Log4j. La stampa del messaggio su console è la decisione aperta DA-02.
* **Requisiti:** RF-06, RF-12, RF-16, RI-03, RV-02.

#### 4.4.4 Collocazione dei pattern nel modello e nel codice

I tre pattern sono nei package indicati dal §3.3: State e Observer in `domain`, Strategy in `domain.saldo`.

> **DA COMPLETARE** · *Si aspetta:* figure del class diagram con i pattern, nomi definitivi delle classi concrete di State e posizione nei package generati · *Da:* issue «Class diagram di design in StarUML» e generazione del codice · *Quando:* fine sprint 2

#### 4.4.5 Antipattern da evitare

* **God class:** un servizio o la scheda che accentra tutte le responsabilità. Rimedio: invarianti nelle entità, orchestrazione nei servizi divisi per gruppo di casi d'uso (§4.2).
* **Swiss Army Knife:** interfacce troppo ampie. Rimedio: un repository per entità, interfacce piccole.
* **Golden Hammer:** usare i pattern dove il problema non c'è. Per questo i pattern sono tre, ciascuno con un problema preciso.
* **Problema dei ravioli:** oggetti che si inviano messaggi senza regole. Rimedio: livelli e dipendenze del §3.3.

### 4.5 Descrizione dei componenti (IEEE 1016)

Lo standard descrive ogni componente con dieci attributi (identificazione, tipo, scopo, funzione, subordinati, dipendenze, interfaccia, risorse, elaborazione, dati), raggruppati in quattro viste. In Tech Repair il componente è il package (in alternativa si potrebbe usare un modulo Maven); le classi sono sottocomponenti quando i dettagli sono noti.

#### 4.5.1 Vista di decomposizione

I subordinati sono preliminari: i nomi definitivi vengono dal class diagram di design.

|Package|Tipo|Scopo|Funzione|Subordinati|
|-|-|-|-|-|
|`app`|Package di avvio|Avviare l'applicazione|Crea le implementazioni di `persistence`, i servizi e gli osservatori, e avvia la console|Classe principale|
|`cli`|Package|Interazione con gli operatori (RI-01)|Menu di scelta ruolo, menu del Tecnico e del Responsabile Magazzino, lettura dell'input, stampa dei risultati e degli errori|Menu principale, menu Tecnico, menu Magazzino|
|`service`|Package|Casi d'uso|Orchestra entità e repository per accettazione, preventivo e verifica magazzino, ordini e catalogo, consegna e pagamento, consultazione|Un servizio per gruppo funzionale|
|`domain`|Package|Modello del dominio|Entità, enumerazioni, regole di integrità, State, Observer|Cliente, Dispositivo, SchedaRiparazione, CambioStato, Preventivo, VoceRicambio, Ricambio, OrdineAcquisto, Pagamento, enumerazioni, `StatoSchedaState`, `SchedaObserver`, `NotificaClienteObserver`|
|`domain.saldo`|Sotto-package|Calcolo del saldo|Strategy per gli esiti|`CalcoloSaldoStrategy` e le tre strategie concrete|
|`domain.repository`|Sotto-package|Contratto di accesso ai dati|Interfacce dei repository|Un'interfaccia per ciascuna entità persistita|
|`persistence`|Package|Persistenza (RI-02, RD-01 – RD-03)|Implementa i repository sul database embedded, gestisce connessione e schema|Implementazioni dei repository|

#### 4.5.2 Vista delle dipendenze

Le dipendenze tra package sono nella tabella del §3.3.1. Risorse esterne:

|Package|Risorse|
|-|-|
|`cli`|JDK (`System.in`, `System.out`)|
|`domain`, `service`|Log4j (`log4j-api`)|
|`persistence`|Database embedded e relativo driver|
|`app`|Log4j (`log4j-core`) per la configurazione|
|Test|JUnit|

#### 4.5.3 Vista di interfaccia

> **DA COMPLETARE** · *Si aspetta:* operazioni pubbliche, parametri, eccezioni e vincoli (pre e post-condizioni) delle interfacce principali: repository, servizi, State, Strategy, Observer · *Da:* class diagram di design · *Quando:* sprint 2, dopo il diagramma

#### 4.5.4 Vista di dettaglio

> **DA COMPLETARE** · *Si aspetta:* algoritmi (calcolo del totale del preventivo, impegno e rilascio della giacenza, transizioni di stato), inizializzazione, gestione delle eccezioni, schema dei dati nel database · *Da:* implementazione e scelta del database (DA-01) · *Quando:* sprint 3 e sprint 4

### 4.6 Classi generate da StarUML

Il Project Plan chiede che il Documento 4 riporti l'elenco delle classi generate, con package e modifiche manuali successive.

|Classe|Package|Origine|Modifiche manuali successive|
|-|-|-|-|
|—|—|—|—|

> **DA COMPLETARE** · *Si aspetta:* la generazione del codice e il suo commit dedicato (indicare l'hash del commit) · *Da:* issue «Generazione del codice da StarUML e setup Maven con database embedded» · *Quando:* fine sprint 2; le modifiche manuali si aggiornano in sprint 3 e 4

### 4.7 Tracciabilità requisiti–design

Per gruppi funzionali; i nomi di classe e i test si aggiungono quando i diagrammi e il Documento 5 sono pronti.

|Requisiti|Componenti|Pattern|Diagrammi|Test (Documento 5)|
|-|-|-|-|-|
|Accettazione: RF-01 – RF-03|`cli`, servizio di accettazione, `domain` (Cliente, Dispositivo, scheda, Pagamento), `persistence`|—|Sequence accettazione|Da compilare|
|Diagnosi e preventivo: RF-04 – RF-08|Servizio preventivo, `domain` (Preventivo, VoceRicambio)|State (blocco dopo l'approvazione), Observer (notifica di invio)|Sequence preventivo, state machine|Da compilare|
|Ricambi e magazzino: RF-09 – RF-11, RF-19|Servizi magazzino e ordini, `domain` (Ricambio, OrdineAcquisto)|State (IN_VERIFICA_MAGAZZINO, IN_ATTESA_RICAMBI)|Sequence preventivo, communication, activity|Da compilare|
|Consegna e pagamento: RF-12 – RF-14|Servizio di consegna, `domain` (Pagamento)|Strategy, Observer (riparazione completata)|Sequence consegna|Da compilare|
|Eccezioni: RF-15, RF-16|`domain`, servizi|State, Observer|State machine, frammenti `alt`|Da compilare|
|Consultazione: RF-17|Servizio di consultazione, repository|—|—|Da compilare|
|Validazione: RF-18|`domain` e servizi (non `cli`)|—|—|Da compilare|
|Interfacce: RI-01 – RI-03|`cli`, `persistence`, Observer e Log4j|Observer|—|Da compilare|
|Database: RD-01 – RD-03|`persistence`, `domain.repository`|—|Class diagram di design|Da compilare|
|Vincoli: RV-01 – RV-04|`pom.xml`, Log4j, State|State|State machine|Da compilare|
|Qualità: RQ-01 – RQ-06|Vedi §3.1|Strategy (RQ-01)|—|Da compilare|

### 4.8 Verifica del design

Come previsto dal Project Plan (§9), il design si verifica con revisioni sistematiche: ogni diagramma passa dalla review di almeno un altro membro prima del merge, e a fine sprint si controlla la coerenza tra modelli, codice e documenti. Checklist di revisione dei diagrammi (bozza, da integrare con la checklist del corso):

* **Class diagram:** visibilità di attributi e operazioni, molteplicità, nomi di ruolo, composizione e aggregazione dove hanno senso, enumerazioni, nessun ciclo tra package.
* **State machine:** stato iniziale e finale, evento, guardia ed effetto su ogni transizione, tutte le transizioni della Specifica, nessuno stato irraggiungibile.
* **Sequence diagram:** lifeline coerenti con i livelli, messaggi sincroni e di ritorno, barre di attivazione, frammenti `alt` per ogni flusso di eccezione.
* **Activity diagram:** nodo iniziale e finale, decisioni con condizioni, corsie per attore.
* **Communication diagram:** messaggi numerati nell'ordine di invio.
* **Trasversale:** i nomi dei diagrammi coincidono con quelli del class diagram e della Specifica; ogni flusso di eccezione ha una transizione, un frammento `alt` e almeno un test (Documento 2, §2.4).

> **DA COMPLETARE** · *Si aspetta:* la checklist del corso, per integrare e sostituire la bozza · *Da:* materiale del corso · *Quando:* prima della prima review dei diagrammi
