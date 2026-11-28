# Documento 4 – Design

## Versioni

|Versione|Data|Modifiche|
|-|-|-|
|0.1|22/11/2026|Prima bozza (sprint 2): struttura del documento, capitolo di architettura (stile, package preliminari, decisioni, viste, valutazione), impostazione del design e dei pattern; segnaposto per i diagrammi e per le parti che dipendono da modelli e codice non ancora pronti|
|0.2|26/11/2026|Integrati class diagram di design e state machine dal file StarUML `TR\_DiagramsUML.mdj`: sezioni 2.2 e 2.3 scritte; nomi dei package e dipendenze di §3.2 – §3.6 allineati al modello; §4.1 – §4.7 aggiornate con classi, operazioni, metriche calcolate dal modello ed elenco delle classi da generare; aggiunta AD-08; chiusa DA-02; aperte DA-04 e DA-05|
|0.3|28/11/2026|Integrati i dati del codice generato: §4.6 riscritta con commit e modifiche manuali reali; §3.3, §3.4 (AD-03 con SQLite; chiuse DA-01 e DA-03; aggiornata DA-04; aperta DA-06), §4.3 (confronto modello-codice), §4.4.1, §4.4.4 e §4.5 aggiornate; attivate le figure SVG di `Documenti/Immagini/` in §2.2, §2.3 e §4.4.4|

## Come leggere questo documento

Le parti scritte sono complete rispetto alle informazioni oggi disponibili (Documento 3, Specifica Logica e Funzionale, Project Plan). Le parti in attesa sono segnalate da un riquadro di questo tipo:

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* che cosa manca · \*Da:\* issue o persona · \*Quando:\* sprint o data

Quando la dipendenza è risolta, il riquadro si toglie e si aggiunge una riga alla tabella delle versioni. Le immagini dei diagrammi sono lasciate come commento HTML con il percorso atteso: basta togliere il commento quando il file SVG è in `Documenti/Immagini/`.

## 1\. Introduzione

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
|2.2 Class diagram di design|Scritta, con figure|Correzione della molteplicità dei pagamenti nel modello (§2.2)|
|2.3 State machine|Scritta, con figura|Due punti da decidere (§2.3, DA-05)|
|2.4 Sequence diagram|In attesa|Issue «Sequence diagram dei flussi principali in StarUML» (in lavorazione sul suo branch, non ancora in `main`); le operazioni da usare nei messaggi sono nel modello|
|2.5 Activity diagram, 2.6 Communication o timing|In attesa|Issue «Activity e communication/timing diagram in StarUML»|
|2.7 Package e component diagram|Parziale|Struttura dei package nel modello (§3.3); package diagram da disegnare; component diagram in sprint 3 (WP4)|
|3.1 – 3.6 Architettura|Scritta, allineata al modello|Approvazione del team sui punti di §3.4 (in particolare DA-04, DA-05 e DA-06); verifica sul codice completo in sprint 4|
|4.1 – 4.2 Approccio, principi|Scritti|Niente|
|4.3 Metriche|Parziale|WMC, A, Ca, Ce, I e NOC calcolati dal modello; A, Ca, Ce e I confrontati con il codice generato; le altre misure quando i corpi dei metodi sono scritti (sprint 4)|
|4.4 Design pattern|Scritta, con figure|Niente|
|4.5 Descrizione dei componenti (IEEE 1016)|Parziale|Decomposizione, dipendenze e interfacce scritte; vista di dettaglio (schema del database e corpi dei metodi) in sprint 3-4|
|4.6 Classi generate da StarUML|Scritta (codice generato, PR #43)|Aggiornare le modifiche manuali man mano che si scrivono i corpi dei metodi|
|4.7 Tracciabilità|Scritta con le classi del modello|Colonna dei test dal Documento 5|
|4.8 Verifica del design|Scritta (bozza)|Integrazione con la checklist del corso|

## 2\. Modelling

I diagrammi sono realizzati con StarUML 5 nel file di progetto in `Documenti/` e esportati in SVG in `Documenti/Immagini/`. Questo capitolo contiene solo i diagrammi, con una riga di contesto, la fonte e le verifiche di coerenza. L'ordine di lavoro sul file `.mdj` segue la regola della Critical Section (Documento 2, §3.4): prima il class diagram di design, poi state machine, sequence, activity e communication, infine package e component diagram; la generazione del codice va per ultima.

### 2.1 Diagrammi già prodotti (Documento 3)

Lo use case diagram (Documento 3, §4.1) e il class diagram del dominio (Documento 3, §5) non sono riprodotti qui. Il class diagram di design li estende.

### 2.2 Class diagram di design

Nel file `TR\_DiagramsUML.mdj`, modello `design`, il class diagram di design è diviso in cinque diagrammi, per non avere una sola figura illeggibile. Estende il class diagram del dominio con operazioni, visibilità, classi di servizio, interfacce dei repository e i tre pattern (State, Strategy, Observer). Il package radice è `it.unibg.techrepair`. È il modello da cui StarUML ha generato una sola volta le classi Java (Documento 2, §2.6): la generazione è del 25/11/2026 (§4.6).

|Diagramma|Contenuto|
|-|-|
|`classDiagramDesignEntita`|Le nove entità del dominio con operazioni e visibilità, più `StatoSchedaState` e `SchedaObserver` collegati alla scheda|
|`classDiagramDesignEnumValidatoreEccezioni`|Le otto enumerazioni, `Validatore` e le tre eccezioni|
|`classDiagramDesignState`|Pattern State: `StatoSchedaState` (classe astratta) e le dieci classi di stato|
|`classDiagramDesignStrategyObserver`|Pattern Strategy (interfaccia e tre strategie, con gli stati che le usano) e pattern Observer (`SchedaObserver`, `EventoScheda`, `NotificaClienteObserver`)|
|`classDiagramDesignServizi`|I quattro servizi, le cinque interfacce dei repository, `ElementoNonTrovatoException` e le dipendenze da `Validatore`|

!\[Class Diagram di Design – Entità](Immagini/classDiagramDesignEntita.svg)

!\[Class Diagram di Design – Enumerazioni, validatore, eccezioni](Immagini/classDiagramDesignEnumValidatoreEccezioni.svg)

!\[Class Diagram di Design – State](Immagini/classDiagramDesignState.svg)

!\[Class Diagram di Design – Strategy e Observer](Immagini/classDiagramDesignStrategyObserver.svg)

!\[Class Diagram di Design – Servizi](Immagini/classDiagramDesignServizi.svg)

**Contenuto del modello per package**

|Package|Tipi|Contenuto|
|-|-|-|
|`dominio`|32|8 enumerazioni, 9 entità, `Validatore`, 2 eccezioni, `StatoSchedaState` con 10 stati concreti, `SchedaObserver`|
|`saldo`|4|`CalcoloSaldoStrategy` e le tre strategie concrete|
|`notifica`|1|`NotificaClienteObserver`|
|`repository`|5|Un'interfaccia per ciascuno dei cinque aggregati persistiti: Cliente, Dispositivo, Scheda, Ricambio, Ordine|
|`servizi`|5|`ClienteService`, `SchedaService`, `PreventivoService`, `MagazzinoService`, `ElementoNonTrovatoException`|
|`persistenza`|0|Vuoto: classi da scrivere a mano (database SQLite, AD-03)|

In totale 47 tipi e 209 operazioni (39 classi e interfacce con operazioni, più le enumerazioni).

**Verifiche di coerenza sul modello** (eseguite sul file del 26/11/2026, in parte con un controllo automatico sul file `.mdj`)

* Sono presenti tutte le entità del Documento 3 (§5): Cliente, Dispositivo, SchedaRiparazione, Preventivo, Ricambio, OrdineAcquisto, Pagamento, VoceRicambio, CambioStato, con gli attributi e i nomi della Specifica Logica (§1).
* Le sette enumerazioni della Specifica hanno esattamente i valori previsti (`StatoScheda` con dieci valori, `StatoPreventivo`, `StatoVoce`, `QualitaRicambio`, `Reperibilita`, `StatoOrdine`, `TipoPagamento`). Il modello aggiunge `EventoScheda` (PREVENTIVO\_INVIATO, RICAMBIO\_FUORI\_PRODUZIONE, RIPARAZIONE\_COMPLETATA) per l'Observer.
* I pattern usano i nomi della Specifica: `StatoSchedaState` (distinto da `StatoScheda`), `CalcoloSaldoStrategy` con le tre strategie, `SchedaObserver` e `NotificaClienteObserver`. `StatoSchedaState` è una classe astratta e non un'interfaccia, perché fornisce il comportamento predefinito (§4.4.1).
* Gli importi sono `BigDecimal` e `CambioStato.data` è `LocalDateTime`. Le due cifre decimali non si esprimono nel diagramma: le garantiscono `Validatore` (costo di manodopera e prezzo unitario) e il codice.
* Tutti gli attributi sono `private`. Le 11 operazioni usate solo dentro il package sono `package-private` (per esempio `Cliente.aggiungiDispositivo`, `SchedaRiparazione.cambiaStato`, `SchedaService.salva`).
* Associazioni, ruoli e molteplicità coincidono con la Specifica (§1), tranne il punto 1 qui sotto. Le composizioni sono quattro: la scheda con i pagamenti, con gli stati e con il preventivo, e il preventivo con le voci.
* I package non hanno cicli (analisi sui tipi di attributi, parametri, associazioni e dipendenze del modello; da rifare sul codice, §3.3.2).
* Ogni regola di validazione della Specifica (§7) ha una collocazione: i campi anagrafici, il prezzo, il costo, la giacenza e la coerenza giacenza-reperibilità in `Validatore`; l'unicità del codice fiscale e del seriale nei repository; la scheda attiva unica in `Dispositivo`; il blocco del preventivo approvato in `Preventivo.verificaModificabile`; la consegna non autorizzata nel comportamento predefinito di `StatoSchedaState`; la manodopera positiva senza ricambi in `Preventivo.isInviabile` e `Preventivo.invia`.
**Punti da correggere o decidere**
1. **Pagamenti:** nel modello l'associazione tra scheda e pagamento ha molteplicità `1..\*`, mentre la Specifica (§1) prevede da uno a due pagamenti (anticipo e saldo o rimborso). Va corretta in `1..2` nel modello.
2. **Doppia rappresentazione dello stato:** `SchedaRiparazione` ha l'enumerazione `statoCorrente` (il valore salvato) e l'oggetto `stato` (un `StatoSchedaState`). `cambiaStato` aggiorna entrambi e `statoAttuale()` ricostruisce l'oggetto dall'enumerazione. Serve un test che verifichi che i due valori coincidano sempre.
3. **Accesso per la persistenza:** sei metodi `setId...` pubblici e tre factory statiche `ricostruisci` permettono a `persistenza` di caricare le entità senza passare dai costruttori. È un compromesso accettato, perché `persistenza` sta in un altro package; va annotato come tale (AD-03).
4. **Attributo derivato:** `Preventivo.totaleCalcolato` è derivato. Il generatore lo ha scritto come campo e nel codice è stato tolto a mano (§4.6); il valore si ottiene sempre da `calcolaTotale()`.
5. **Un solo osservatore:** `SchedaService` riceve un solo `SchedaObserver` (molteplicità 1). Se i canali diventano più di uno serve un osservatore composito (scenario CC3, §3.6).

### 2.3 State machine della scheda di riparazione

Nel modello è `smSchedaRiparazione`, associata alla classe `SchedaRiparazione`, con il diagramma `stateMachineSchedaRiparazione`. Rappresenta il ciclo di vita della scheda. Fonte: tabella degli stati e delle transizioni della Specifica Logica (§5). I trigger sono le 18 operazioni pubbliche di `SchedaRiparazione`, che delegano al pattern State (§4.4.1); ogni cambio di stato aggiunge un `CambioStato` allo storico.

!\[State Machine della Scheda di Riparazione](Immagini/stateMachineSchedaRiparazione.svg)

**Elementi del diagramma**

* **Dieci stati:** ACCETTATA, IN\_DIAGNOSI, IN\_VERIFICA\_MAGAZZINO, IN\_ATTESA\_APPROVAZIONE, IN\_ATTESA\_RICAMBI, IN\_RIPARAZIONE, RIPARATA, ANNULLATA, NON\_RIPARABILE, CHIUSA.
* **Pseudostati:** uno iniziale, due nodi di scelta (`esitoModifica` e `esitoApprovazione`) e uno stato finale dopo CHIUSA.
* **Azioni di ingresso:**
|Stato|Azione di ingresso|
|-|-|
|IN\_ATTESA\_APPROVAZIONE|`preventivo.invia(oggi)`; notifica PREVENTIVO\_INVIATO|
|RIPARATA|Notifica RIPARAZIONE\_COMPLETATA|
|ANNULLATA|`rilasciaRicambiImpegnati()`|
|NON\_RIPARABILE|`rilasciaRicambiImpegnati()`; notifica RICAMBIO\_FUORI\_PRODUZIONE|
|CHIUSA|`dataChiusura = oggi`|

Rientrare in IN\_ATTESA\_APPROVAZIONE dopo una modifica del preventivo riesegue l'azione di ingresso: è ciò che produce la nuova notifica prevista dalla Specifica (§4, fase 3).

**Transizioni del diagramma e righe della Specifica (§5)**

Le righe della Specifica sono numerate da 1 a 23 nell'ordine della sua tabella.

|#|Da → A|Evento|Guardia|Effetto|Riga|
|-|-|-|-|-|-|
|1|iniziale → ACCETTATA|—|—|`registraPagamento(10,00, DIAGNOSI\_INIZIALE)`|1|
|2|ACCETTATA → IN\_DIAGNOSI|`avviaDiagnosi`|—|—|2|
|3|ACCETTATA → IN\_DIAGNOSI|`aggiungiRicambio`, `impostaCostoManodopera`|—|aggiorna la bozza del preventivo|4|
|4|ACCETTATA → ANNULLATA|`rinuncia`|—|—|3|
|5|IN\_DIAGNOSI → IN\_DIAGNOSI|`registraDiagnosi`, `impostaCostoManodopera`, `rimuoviRicambio`|—|aggiorna la bozza (+1 se IMPEGNATO)|5|
|6|IN\_DIAGNOSI → IN\_DIAGNOSI|`aggiungiRicambio`|ricambio non FUORI\_PRODUZIONE|`preventivo.aggiungiVoce()`|5|
|7|IN\_DIAGNOSI → IN\_VERIFICA\_MAGAZZINO|`richiediVerificaMagazzino`|almeno un ricambio DA\_ORDINARE|—|6|
|8|IN\_VERIFICA\_MAGAZZINO → IN\_DIAGNOSI|`confermaRicambiOrdinabili`|—|ricambi DA\_ORDINARE → ORDINABILE|10|
|9|IN\_DIAGNOSI → NON\_RIPARABILE|`aggiungiRicambio`|ricambio FUORI\_PRODUZIONE|—|7|
|10|IN\_DIAGNOSI → IN\_ATTESA\_APPROVAZIONE|`inviaPreventivo`|`preventivo.isInviabile()`|—|8|
|11|IN\_DIAGNOSI → ANNULLATA|`rinuncia`|—|—|9|
|12|IN\_VERIFICA\_MAGAZZINO → NON\_RIPARABILE|`segnalaFuoriProduzione`|—|`ricambio.segnaFuoriProduzione()`|11|
|13|IN\_VERIFICA\_MAGAZZINO → ANNULLATA|`rinuncia`|—|—|12|
|14|IN\_ATTESA\_APPROVAZIONE → `esitoModifica`|`modificaPreventivo`|—|rilascio (+1) dei ricambi IMPEGNATO rimossi|13 – 15|
|15|`esitoModifica` → IN\_ATTESA\_APPROVAZIONE|—|nuovi ricambi tutti disponibili|impegna i nuovi ricambi (−1)|13|
|16|`esitoModifica` → IN\_VERIFICA\_MAGAZZINO|—|nuovo ricambio con giacenza = 0|nuovo ricambio DA\_ORDINARE; `preventivo.riportaInBozza()`|14|
|17|`esitoModifica` → NON\_RIPARABILE|—|nuovo ricambio FUORI\_PRODUZIONE|—|15|
|18|IN\_ATTESA\_APPROVAZIONE → ANNULLATA|`rifiutaPreventivo`|—|`preventivo.rifiuta(oggi)`|16|
|19|IN\_ATTESA\_APPROVAZIONE → `esitoApprovazione`|`approvaPreventivo`|—|`preventivo.approva(oggi)`|17 – 18|
|20|`esitoApprovazione` → IN\_RIPARAZIONE|—|nessun ricambio DA\_ORDINARE|—|17|
|21|`esitoApprovazione` → IN\_ATTESA\_RICAMBI|—|almeno un ricambio DA\_ORDINARE|—|18|
|22|IN\_ATTESA\_RICAMBI → IN\_ATTESA\_RICAMBI|`registraOrdine`|—|ordine IN\_CORSO|19|
|23|IN\_ATTESA\_RICAMBI → IN\_RIPARAZIONE|`registraRicezioneOrdine`|ordine IN\_CORSO|ordine RICEVUTO; `preventivo.assegnaRicambiOrdinati()`|19|
|24|IN\_RIPARAZIONE → IN\_RIPARAZIONE|`aggiornaSeriale`|seriale univoco|`dispositivo.aggiornaSeriale(nuovoSeriale)`|20|
|25|IN\_RIPARAZIONE → RIPARATA|`chiudiRiparazione`|—|—|20|
|26|RIPARATA → CHIUSA|`consegna`|—|`registraPagamento(max(0, totale − 10,00), SALDO\_FINALE)`|21|
|27|ANNULLATA → CHIUSA|`consegna`|—|`registraPagamento(0,00, SALDO\_FINALE)`|22|
|28|NON\_RIPARABILE → CHIUSA|`consegna`|—|`registraPagamento(−10,00, RIMBORSO)`|23|
|29|CHIUSA → finale|—|—|—|—|

Il diagramma ha quindi 29 transizioni a fronte delle 23 righe della Specifica. Le sei in più non sono nuove regole, ma il modo di disegnare le stesse righe: i due nodi di scelta spezzano la modifica (3 righe in 4 transizioni) e l'approvazione (2 righe in 3); tre righe sono divise in due transizioni (il salvataggio della bozza in IN\_DIAGNOSI, ordine e arrivo merce, aggiornamento del seriale e chiusura); e c'è la transizione verso lo stato finale.

**Verifiche di coerenza** (eseguite sul file del 26/11/2026)

* Dieci stati, tutti raggiungibili dallo stato iniziale.
* Tutte le 23 righe della Specifica compaiono come transizioni, con evento, guardia ed effetto sulle scorte.
* Ogni flusso di eccezione è una transizione (Documento 2, §2.4): rifiuto del preventivo (18), fuori produzione (9, 12, 17), modifica del preventivo (14 – 17), rinuncia anticipata (4, 11, 13).
* ANNULLATA e NON\_RIPARABILE arrivano a CHIUSA solo con la consegna (27, 28), e CHIUSA non ha altre uscite.
* Per ciascuno dei dieci stati, gli eventi accettati nel diagramma coincidono con le operazioni ridefinite dalla classe di stato corrispondente nel class diagram (controllo automatico). Le 18 operazioni di `SchedaRiparazione` compaiono tutte come trigger.
**Punti da decidere**
1. **Ricambio fuori produzione aggiunto da ACCETTATA.** La transizione 3 non ha la guardia «ricambio non FUORI\_PRODUZIONE» che hanno le transizioni 6 e 9 di IN\_DIAGNOSI, e la Specifica (§5, riga 7) parte solo da IN\_DIAGNOSI. Se il tecnico aggiunge subito un ricambio fuori produzione, il comportamento non è definito (DA-05).
2. **Effetto della transizione 5.** L'effetto «aggiorna la bozza (+1 se IMPEGNATO)» è attaccato a tre eventi, ma il rilascio (+1) vale solo per `rimuoviRicambio`. Conviene separare i tre eventi in transizioni distinte o riscrivere l'effetto. È un'imprecisione di notazione, non di logica.

### 2.4 Sequence diagram dei flussi principali

Tre diagrammi, uno per flusso. Le lifeline seguono i livelli del §3.3 (`cli`, `servizi`, `dominio`, `persistenza`) e ogni flusso di eccezione è un frammento `alt`.

#### 2.4.1 Accettazione e creazione scheda

Fonte: use case «Accetta Dispositivo e Crea Scheda» (Documento 3, §4.2) e Specifica Logica, §4 (fase 1). Deve mostrare la ricerca del cliente per codice fiscale (ramo cliente nuovo o esistente), la verifica di seriale univoco e assenza di scheda attiva, la creazione della scheda in stato ACCETTATA, il pagamento anticipato DIAGNOSI\_INIZIALE di 10 € e la generazione della ricevuta. Frammenti `alt`: dati non validi, dispositivo con scheda già attiva. Requisiti: RF-01, RF-02, RF-03, RF-18.

Operazioni del modello da usare nei messaggi: `ClienteService.cercaPerCodiceFiscale`, `registraCliente`, `registraDispositivo`; `SchedaService.apriScheda`, `generaRicevutaIngresso`; `DispositivoRepository.esisteSeriale`.

<!-- !\[Sequence – Accettazione](Immagini/Sequence\_Accettazione.svg) -->

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* il diagramma · \*Da:\* issue «Sequence diagram dei flussi principali in StarUML» · \*Quando:\* sprint 2

#### 2.4.2 Preventivo con verifica magazzino

Fonte: use case «Invia Preventivo», flussi A-D (Documento 3, §4.2) e Specifica Logica, §4 (fasi 2 e 3). Deve mostrare la diagnosi, l'inserimento delle voci, l'impegno di un ricambio disponibile (giacenza −1), il ricambio esaurito che diventa DA\_ORDINARE con passaggio a IN\_VERIFICA\_MAGAZZINO e verifica del Responsabile Magazzino, l'invio con notifica al cliente (Observer) e la decisione del cliente. Frammenti `alt`: ricambio disponibile, esaurito, fuori produzione; modifica del preventivo; rifiuto; approvazione con o senza ricambi da ordinare. Requisiti: RF-04 – RF-10, RF-15, RF-16, RF-18.

Operazioni del modello da usare nei messaggi: `SchedaService.avviaDiagnosi`, `registraDiagnosi`; `PreventivoService.impostaCostoManodopera`, `aggiungiRicambio`, `rimuoviRicambio`, `richiediVerificaMagazzino`, `inviaPreventivo`, `modificaPreventivo`, `approvaPreventivo`, `rifiutaPreventivo`; `MagazzinoService.schedeInVerifica`, `confermaRicambiOrdinabili`, `segnalaFuoriProduzione`; `SchedaObserver.aggiorna`.

<!-- !\[Sequence – Preventivo con verifica magazzino](Immagini/Sequence\_Preventivo.svg) -->

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* il diagramma · \*Da:\* stessa issue · \*Quando:\* sprint 2

#### 2.4.3 Consegna e pagamento

Fonte: use case «Consegna Dispositivo Riparato» e Specifica Logica, §4 (fase 5). Deve mostrare la chiusura della riparazione con notifica, la consegna consentita solo per schede RIPARATA, ANNULLATA o NON\_RIPARABILE, la scelta della strategia di calcolo del saldo (pattern Strategy), la registrazione del pagamento o del rimborso e la chiusura della scheda. Frammenti `alt`: riparata, annullata, non riparabile, stato che non consente la consegna, seriale duplicato in aggiornamento. Requisiti: RF-12, RF-13, RF-14, RF-18.

Operazioni del modello da usare nei messaggi: `SchedaService.aggiornaSeriale`, `chiudiRiparazione`, `consegnaDispositivo`; `StatoSchedaState.consegna` e la strategia scelta dallo stato (`CalcoloSaldoStrategy.calcolaSaldo`).

<!-- !\[Sequence – Consegna e pagamento](Immagini/Sequence\_Consegna.svg) -->

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* il diagramma · \*Da:\* stessa issue · \*Quando:\* sprint 2

> \*\*Copertura da decidere:\*\* l'issue prevede questi tre flussi, ma lo use case «Ordina Pezzo di Ricambio» (RF-11) non è coperto da nessun sequence diagram. Il modello ha già le operazioni (`MagazzinoService.schedeInAttesaRicambi`, `emettiOrdine`, `registraRicezioneOrdine`). Si può inserire come frammento `ref` nel diagramma 2.4.2 o in un quarto diagramma, oppure coprirlo nell'activity diagram.

### 2.5 Activity diagram del flusso officina

Mostra il percorso completo di un dispositivo, dall'accettazione alla chiusura, con corsie (swimlane) per Tecnico Riparatore, Responsabile Magazzino e Sistema, e nodi decisionali per gli esiti: preventivo approvato, rifiutato, ricambio esaurito, ricambio fuori produzione. Il Cliente è un attore passivo e compare come destinatario delle notifiche.

<!-- !\[Activity Diagram del flusso officina](Immagini/Activity\_Officina.svg) -->

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* il diagramma · \*Da:\* issue «Activity e communication/timing diagram in StarUML» · \*Quando:\* sprint 2

### 2.6 Communication o timing diagram

Scenario proposto: **pezzo non in magazzino** (scenario S2 del Documento 3, Parte A), perché coinvolge tutti e tre gli attori, un cambio di stato e la notifica Observer. Per questo scenario il communication diagram è più adatto del timing diagram, che serve a mostrare vincoli di tempo che qui non ci sono. La scelta finale spetta all'autore dell'issue.

<!-- !\[Communication Diagram – pezzo non in magazzino](Immagini/Communication\_PezzoNonInMagazzino.svg) -->

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* il diagramma · \*Da:\* stessa issue · \*Quando:\* sprint 2

### 2.7 Package diagram e component diagram

La struttura dei package è nel modello StarUML (`design / it / unibg / techrepair`) ed è descritta nel §3.3: `dominio`, `saldo`, `notifica`, `repository`, `servizi` e `persistenza` (ancora vuoto). Non esiste un package diagram disegnato: si ricava trascinando i package in un nuovo diagramma e mostrando le dipendenze del §3.3.1. I package `cli` e `app` non sono nel modello (DA-04). Se si modifica il modello in StarUML, l'issue richiede la label *Critical Section* e va messa in sequenza con le altre.

<!-- !\[Package Diagram](Immagini/Package\_Diagram.svg) -->

<!-- !\[Component Diagram](Immagini/Component\_Diagram.svg) -->

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* il package diagram (dal modello esistente), la decisione su `cli` e `app` (DA-04) e, dopo la generazione del codice, il component diagram · \*Da:\* issue «Prime viste architetturali e struttura del Documento 4» (package), WP4 (component) · \*Quando:\* package in sprint 2, component diagram in sprint 3 (8-21/12)

## 3\. Software Architecture

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
* **MVC:** serve a separare interfacce utente flessibili. Con la sola console la separazione tra `cli` e `servizi` ottiene lo stesso scopo e consente di sostituire la console in futuro (scenario CC2, §3.6).
I livelli e i package che li realizzano, con i nomi del modello StarUML:

|Livello|Responsabilità|Package|
|-|-|-|
|Presentazione|Menu di scelta ruolo, lettura dell'input, stampa dei risultati e dei messaggi d'errore. Nessuna regola di business|`cli` (non ancora nel modello)|
|Logica applicativa|Quattro servizi che orchestrano entità e repository: `ClienteService`, `SchedaService`, `PreventivoService`, `MagazzinoService`|`servizi`|
|Dominio|Entità, enumerazioni, regole di integrità, pattern State, interfaccia `SchedaObserver`, validazione ed eccezioni; il calcolo del saldo (pattern Strategy) è in un package a parte|`dominio`, `saldo`|
|Adattatori del dominio|`notifica` implementa l'interfaccia `SchedaObserver`; `repository` contiene i contratti di accesso ai dati, definiti dal dominio e implementati dalla persistenza|`notifica`, `repository`|
|Persistenza|Implementazione JDBC dei repository sul database embedded|`persistenza` (ancora vuoto)|

I package `notifica` e `repository` applicano l'inversione delle dipendenze: il dominio definisce le interfacce e non conosce chi le realizza, quindi `servizi` e `dominio` non dipendono né dal database né dal canale di notifica. Un package aggiuntivo, `app` (non ancora nel modello), contiene l'avvio dell'applicazione e assembla i componenti: crea le implementazioni di `persistenza` e `notifica` e le passa ai servizi. Non è un livello, ma è l'unico punto che conosce tutti gli altri.

### 3.3 Struttura a package

> \*\*Adottata nel modello:\*\* questa è la struttura dei package del class diagram di design (file `TR\_DiagramsUML.mdj` del 26/11/2026), che sostituisce la proposta iniziale (`domain`, `service`, `persistence`, con `saldo` e `repository` come sotto-package di `domain`). Va approvata dal team insieme ad AD-01. Il package radice è `it.unibg.techrepair`; il codice è un solo progetto Maven (`Codice/techrepair`, `groupId` `it.unibg`, `artifactId` `techrepair`, Java 17), con questa stessa struttura a package.

```
it.unibg.techrepair
├── app           avvio dell'applicazione e assemblaggio dei componenti (non ancora nel modello)
├── cli           interfaccia console (non ancora nel modello)
├── servizi       ClienteService, SchedaService, PreventivoService, MagazzinoService
├── dominio       entità, enumerazioni, State, SchedaObserver, Validatore, eccezioni
├── saldo         pattern Strategy per il calcolo del saldo
├── notifica      NotificaClienteObserver (Log4j)
├── repository    interfacce di accesso ai dati
└── persistenza   implementazione JDBC dei repository su SQLite (nessuna classe nel codice generato)
```

#### 3.3.1 Dipendenze

|Package|Usa|Fonte|
|-|-|-|
|`app`|Tutti|Regola|
|`cli`|`servizi`, `dominio`|Regola|
|`servizi`|`dominio`, `repository`|Modello|
|`dominio`|`saldo`|Modello|
|`notifica`|`dominio`|Modello|
|`repository`|`dominio`|Modello|
|`persistenza`|`repository`, `dominio`|Regola (package vuoto)|
|`saldo`|Nessun package del progetto|Modello|

Con queste dipendenze i servizi usano le interfacce dei repository e non conoscono il database scelto: è ciò che rende il database sostituibile (scenario CC1, §3.6). Allo stesso modo `SchedaService` riceve un `SchedaObserver` nel costruttore e non conosce `notifica` (scenario CC3). Dentro `servizi`, `PreventivoService` e `MagazzinoService` usano `SchedaService` e non il contrario.

#### 3.3.2 Regole di struttura

1. Nessun ciclo tra package (Project Plan, §4). Sul modello non ce ne sono (analisi sui tipi di attributi, parametri, associazioni e dipendenze); sul codice generato l'analisi degli `import` (28/11/2026) non ne trova (§4.3); con i corpi dei metodi si ricontrolla con JDepend o Structure101.
2. Information hiding: classi `package-private` quando non servono fuori dal package, attributi `private`, interfacce pubbliche minime.
3. Il database si raggiunge solo da `persistenza`.
4. La console (`System.in` e `System.out`) si usa solo in `cli`. Le notifiche passano da Log4j: il logger dedicato di `NotificaClienteObserver` ha un ConsoleAppender e un FileAppender (RV-02, RI-03; AD-05).
5. Nessun percorso assoluto in nessun package (RQ-06), compreso quello del file di log.

#### 3.3.3 Cicli: punti di attenzione

* **State e Observer richiedono che la scheda e i suoi stati e osservatori si conoscano a vicenda.** `StatoSchedaState`, i dieci stati concreti, `SchedaObserver` e `SchedaRiparazione` stanno nello stesso package `dominio` (un ciclo dentro un solo package è accettabile). L'implementazione `NotificaClienteObserver` sta fuori, in `notifica`, e dipende da `dominio` ma non viceversa: nessun ciclo.
* **Strategy:** `saldo` non dipende da nessuno, perché le strategie ricevono l'importo (`BigDecimal`) e non l'entità. La strategia è scelta dalle tre classi di stato `RiparataState`, `AnnullataState` e `NonRiparabileState` nell'operazione `consegna`, e non dal servizio come ipotizzato all'inizio. Per questo `dominio` dipende da `saldo` (una sola direzione).
* **Repository:** le interfacce usano i tipi del dominio e il dominio non conosce i repository.

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* approvazione del team sulla struttura a package (di fatto già adottata dal codice generato); classi dei package `cli` e `app` (DA-04); classi di `persistenza` · \*Da:\* riunione del lunedì; implementazione dei livelli mancanti · \*Quando:\* sprint 3

### 3.4 Decisioni architetturali

Le decisioni importanti sono registrate con uno schema fisso (problema, decisione, stato, presupposti, alternative, motivazione, implicazioni, appunti). Le decisioni del team si prendono per consenso; se non si raggiunge l'accordo decide il Project Manager con un commento nell'issue (Documento 2, §4.2).

**AD-01 – Architettura a livelli**

|Elemento|Descrizione|
|-|-|
|Problema|Strutturare il sistema in modo manutenibile, testabile e portabile (RQ-04, RQ-05, RQ-06), con quattro persone che lavorano in parallelo|
|Decisione|Package `cli`, `servizi`, `dominio`, `saldo`, `notifica`, `repository`, `persistenza`, più il package di avvio `app`, sotto la radice `it.unibg.techrepair`; dominio condiviso|
|Stato|Adottata nel class diagram di design (26/11/2026), da approvare dal team|
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
|Decisione|Database embedded SQLite (driver `org.xerial:sqlite-jdbc` 3.54.1.0) raggiunto solo da `persistenza` con JDBC, attraverso repository le cui interfacce stanno in `repository`. `SchedaRepository.salva` salva in una sola transazione l'intera scheda (stati, preventivo, voci, pagamenti, ordine) con giacenza e reperibilità dei ricambi coinvolti|
|Stato|Adottata: SQLite è nel `pom.xml` (issue #32, PR #43, fusa in `main` il 27/11/2026); le classi di `persistenza` non sono ancora scritte|
|Presupposti|Un operatore alla volta; dati che devono sopravvivere al riavvio (RQ-02)|
|Alternative|File piatti (poco affidabili per l'integrità), database con server esterno (violerebbe RI-02 e RQ-06)|
|Motivazione|Portabilità, integrità, sostituibilità del database|
|Implicazioni|Criteri: solo dipendenza Maven (soddisfatto); file in percorso relativo (il `.gitignore` esclude `data/` e `\*.db`: da confermare nel codice di `persistenza`); importi decimali esatti: SQLite non ha un tipo decimale, quindi gli importi si salvano come interi in centesimi (`INTEGER`), come verifica `DatabaseSetupTest` (10,00 € diventa 1000 centesimi e torna `BigDecimal` con due decimali); transazioni (previste in `SchedaRepository.salva`); licenza libera. Le entità espongono `setId...` e factory `ricostruisci` per il caricamento dal database (compromesso sull'information hiding, §2.2)|
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
|Implicazioni|Più classi (dieci stati, tre strategie); i cicli tra scheda, stati e osservatori vanno gestiti (§3.3.3); `dominio` dipende da `saldo` perché sono gli stati a scegliere la strategia|
|Appunti|Dettaglio nel §4.4|

**AD-05 – Log4j e notifiche simulate**

|Elemento|Descrizione|
|-|-|
|Problema|Tracciare eventi e notifiche senza usare la console, riservata all'interazione (RV-02, RI-03)|
|Decisione|Log4j per eventi e notifiche; nessun `System.out.println` fuori da `cli`. `NotificaClienteObserver` usa un logger dedicato con ConsoleAppender (le notifiche si vedono in console, RI-03) e FileAppender (traccia su file)|
|Stato|Approvata|
|Presupposti|Le notifiche sono simulate, nessun invio reale|
|Alternative|`System.out.println` (vietato), `java.util.logging`|
|Motivazione|Requisito del Project Plan e del Documento 3|
|Implicazioni|Dipendenze `log4j-api` e `log4j-core` nel `pom.xml`; configurazione senza percorsi assoluti, anche per il FileAppender. Nel codice (28/11/2026) il `log4j2.xml` configura solo il root logger con Console e file `logs/techrepair.log`: il logger dedicato alle notifiche non è ancora configurato (DA-06)|
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
|Implicazioni|Dopo la generazione la manutenzione avviene sul codice e non sul modello; nomi scelti per evitare collisioni (per esempio `StatoSchedaState`); campo derivato `Preventivo.totaleCalcolato` tolto a mano dopo la generazione; elenco delle classi generate nel §4.6|
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

**AD-08 – Validazione ed errori con eccezioni non controllate**

|Elemento|Descrizione|
|-|-|
|Problema|Applicare le regole di RF-18 senza duplicarle e senza metterle in `cli`|
|Decisione|`Validatore` (classe di utilità con soli metodi statici) solleva `DatiNonValidiException`; `OperazioneNonConsentitaException` segnala un'operazione non ammessa dallo stato o da un vincolo di dominio; `ElementoNonTrovatoException` (in `servizi`) segnala un identificativo o un criterio inesistente. Le tre estendono `RuntimeException`|
|Stato|Presente nel modello, da approvare dal team|
|Presupposti|Gli errori arrivano fino a `cli`, che li mostra all'operatore|
|Alternative|Eccezioni controllate, codici di ritorno|
|Motivazione|Firme dei metodi semplici, regole in un solo punto, test con `assertThrows`|
|Implicazioni|`cli` deve intercettare le tre eccezioni e stampare un messaggio chiaro (RQ-03); i test JUnit verificano ogni regola della Specifica (§7)|
|Appunti|Nessuno|

#### Decisioni aperte

|ID|Questione|Si aspetta|Proposta provvisoria|
|-|-|-|-|
|DA-01|Prodotto del database embedded|—|Chiusa: SQLite con driver `sqlite-jdbc` (AD-03, PR #43)|
|DA-02|Notifiche su console senza usare `System.out` fuori da `cli`|Conferma del team|Chiusa nel modello: `NotificaClienteObserver` usa un logger Log4j con ConsoleAppender e FileAppender (AD-05). Resta la riserva del punto 5 del §2.2: `SchedaService` ha un solo osservatore|
|DA-03|Package radice e numero di progetti Maven|—|Chiusa: radice `it.unibg.techrepair`, un solo progetto Maven in `Codice/techrepair`|
|DA-04|Le classi di `cli` e di `app` non sono nel modello|Team|Scriverle a mano sul codice già generato: sono console e avvio. Nel codice esiste solo `App`, che stampa il log di avvio ed è nel package radice `it.unibg.techrepair` e non in `app`: va deciso se spostarla|
|DA-05|Ricambio fuori produzione aggiunto allo stato ACCETTATA (§2.3, punto 1)|Team, con aggiornamento della Specifica (§5)|Aggiungere la guardia anche alla transizione da ACCETTATA e una transizione verso NON\_RIPARABILE, oppure far passare la scheda sempre da IN\_DIAGNOSI|
|DA-06|Configurazione Log4j: il `log4j2.xml` del codice ha solo il root logger con Console e file, quindi anche i log tecnici vanno in console e si mescolano al menu della `cli`|Team|Un logger dedicato alle notifiche (`it.unibg.techrepair.notifica`) con Console e file, e il root logger solo su file|

### 3.5 Viste architetturali

Si usa il modello 4+1: ogni vista ha un punto di vista e diagrammi UML associati.

|Vista|Serve a|Diagrammi|Stato|
|-|-|-|-|
|Logica|Sviluppatori e docenti: elementi principali e loro interazioni|Class diagram di design (§2.2), state machine (§2.3)|Diagrammi nel modello; SVG da esportare|
|Sviluppo|Sviluppatori e chi mantiene: moduli, package, livelli|Package diagram, component diagram (§2.7); struttura testuale in §3.3|Package nel modello, diagramma da disegnare; component in sprint 3|
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
|CC1 – Sostituire il database embedded|Solo `persistenza` (dipendenza Maven e configurazione)|Supportato grazie ad AD-03|
|CC2 – Sostituire la console con un'interfaccia grafica|Solo `cli` e `app`|Supportato, purché nessuna regola di business stia in `cli`|
|CC3 – Notifiche reali via email o SMS|Un nuovo osservatore concreto in `notifica`, registrato in `app`; con più canali serve un osservatore composito, perché `SchedaService` ne riceve uno solo|Supportato grazie ad AD-04, con una piccola aggiunta|
|CC4 – Nuovo esito con un calcolo del saldo diverso|Una nuova strategia in `saldo` e la sua scelta nella classe di stato interessata|Supportato grazie ad AD-04|
|CC5 – Nuovo stato della scheda|Enumerazione `StatoScheda`, nuova sottoclasse di `StatoSchedaState`, `StatoSchedaState.daStato`, transizioni nelle classi di stato esistenti, state machine, menu|Supportato ma non localizzato: punto di sensibilità|
|CC6 – Più operatori contemporanei o più sedi|Persistenza e concorrenza|Non supportato: fuori dalle assunzioni del Documento 3 (§2.5)|

**Punti di sensibilità, compromessi e rischi** (vocabolario ATAM):

* **Punto di sensibilità:** le interfacce dei repository, da cui dipendono modificabilità e testabilità; la coerenza tra giacenza e reperibilità, da cui dipende l'integrità dei dati.
* **Punto di compromesso:** livelli e interfacce migliorano la modificabilità ma aumentano il numero di classi e il costo di ogni operazione. Per RP-01 il costo in prestazioni è accettabile.
* **Rischi:** cicli tra package causati da State e Observer, assenti nel modello ma possibili nel codice (§3.3.3); un servizio che accumula troppe responsabilità: `SchedaService` ha 14 operazioni ed è il candidato God class da tenere d'occhio con le metriche (§4.3, §4.4.5).
La valutazione è fatta sul progetto e non sul codice: può solo prevedere il comportamento del sistema.

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* verifica sul codice (cicli, dipendenze e scenari di cambiamento) · \*Da:\* Responsabile sviluppo e test, con JDepend o Structure101 · \*Quando:\* sprint 4 (22/12 – 4/1)

## 4\. Software Design

### 4.1 Approccio

Il design è orientato agli oggetti e procede in tre passi, che si ripetono: identificare gli oggetti, determinarne attributi e servizi, determinare le relazioni.

|Passo|Contributo nel progetto|Stato|
|-|-|-|
|Oggetti|Modello del dominio (Documento 3, §5; Specifica Logica, §1)|Scritto|
|Attributi|Specifica Logica, §1 (attributi, tipi, vincoli)|Scritto|
|Servizi (operazioni)|Class diagram di design: 209 operazioni, da verificare con i sequence diagram|Scritto|
|Relazioni|Associazioni, generalizzazioni e dipendenze del class diagram di design|Scritto|

Dal modello del dominio non emergono gerarchie di specializzazione tra le entità: l'ereditarietà compare solo nei pattern (dieci stati che estendono `StatoSchedaState`, tre strategie e un osservatore che realizzano un'interfaccia) e nelle tre eccezioni che estendono `RuntimeException`, quindi gli alberi di ereditarietà hanno profondità 1. Il design aggiunge al dominio le classi di soluzione (servizi, repository, console). Class diagram, sequence diagram e state machine si raffinano a vicenda, come previsto dal processo iterativo del Documento 2.

### 4.2 Principi e regole di progetto

I principi di progetto seguenti diventano regole del team (proposta, da approvare insieme ad AD-01).

* **Astrazione.** Astrazione dei dati: ogni entità incapsula stato e operazioni. Astrazione procedurale: ogni caso d'uso è un metodo di un servizio. Le parti che possono variare stanno dietro un'interfaccia: repository, strategia, osservatore, stato.
* **Modularità.** Coesione: ogni classe ha un solo motivo di cambiamento; lo scopo si deve poter descrivere in una frase senza "e" né riferimenti temporali. Accoppiamento: si passano solo dati semplici o oggetti di dominio tramite metodi, mai variabili globali né accesso diretto ai campi di altri oggetti.
* **Information hiding.** Attributi `private`, classi `package-private` se possibile. Ogni package nasconde un segreto: `cli` il formato di input e output, `persistenza` lo schema e il prodotto del database, `dominio` le regole del dominio.
* **Complessità.** Metodi corti e complessità ciclomatica massima di 10 per metodo (soglia di McCabe). Si usano più metriche insieme, perché nessuna basta da sola.
* **Struttura del sistema.** Grafo dei package aciclico e a livelli. Si applica la legge di Demetra come euristica: un metodo invia messaggi solo ai propri parametri, ai propri attributi e a sé stesso.
* **Ripartizione delle responsabilità.** Le regole che proteggono le invarianti (giacenza non negativa, reperibilità coerente, preventivo bloccato dopo l'approvazione) stanno nelle entità; ai servizi spetta solo l'orchestrazione tra più entità e repository. Serve a evitare una God class.
* **Validazione.** Le regole di RF-18 stanno nel dominio e nei servizi, non nella console: così si testano con JUnit senza input da tastiera.
**Applicazione nel class diagram di design** (verificata sul modello del 26/11/2026)
* **Astrazione:** le parti che variano stanno dietro interfacce o classi astratte: cinque repository, `CalcoloSaldoStrategy`, `SchedaObserver`, `StatoSchedaState`.
* **Information hiding:** tutti gli attributi sono `private`; 11 operazioni sono `package-private`. Eccezioni documentate: sei metodi `setId...` pubblici e tre factory statiche `ricostruisci`, che servono a `persistenza` per caricare le entità (AD-03).
* **Ripartizione delle responsabilità:** le invarianti stanno nelle entità (`Ricambio.impegna` richiede giacenza positiva, `Preventivo.verificaModificabile` blocca i preventivi approvati o rifiutati, `Dispositivo.aggiungiScheda` impedisce una seconda scheda attiva); i servizi orchestrano più entità e repository.
* **Validazione (RF-18):** concentrata in `Validatore`, nel dominio; `cli` non contiene regole (AD-08).

### 4.3 Metriche di design

|Livello|Metriche|
|-|-|
|Package|Accoppiamento afferente (Ca) ed efferente (Ce), instabilità I = Ce/(Ce+Ca), astrazione A = Na/Nc, presenza di cicli|
|Classe|WMC, CBO, RFC, LCOM. DIT e NOC si raccolgono ma si interpretano con cautela|
|Metodo|Complessità ciclomatica (massimo 10), righe di codice|

Strumenti: JDepend o Structure101 per i package (Project Plan, §6); per le metriche di classe e di metodo uno strumento di metriche da scegliere in sprint 4 (Project Plan, §11).

**Valori calcolati dal modello di design (26/11/2026)**

I valori seguenti sono stime dal diagramma: contano i tipi usati in attributi, parametri, associazioni, generalizzazioni e dipendenze. Il codice avrà più dipendenze (corpi dei metodi), quindi vanno rifatti con gli strumenti in sprint 4. Nc è il numero di tipi del package (classi, interfacce, enumerazioni) e Na il numero di interfacce e classi astratte; Ca e Ce contano le classi esterne al package.

|Package|Nc|Na|A|Ca|Ce|I|
|-|-|-|-|-|-|-|
|`dominio`|32|2|0,06|10|3|0,23|
|`saldo`|4|1|0,25|3|0|0,00|
|`repository`|5|5|1,00|4|6|0,60|
|`notifica`|1|0|0,00|0|3|1,00|
|`servizi`|5|0|0,00|0|16|1,00|

* **Lettura:** `dominio` e `saldo` sono stabili (I vicino a 0): gli altri dipendono da loro e non viceversa, come richiede AD-01. `servizi` e `notifica` hanno I = 1 perché nessuno li usa: è atteso per i package di livello alto (nel codice saranno usati solo da `cli` e `app`). `repository` è completamente astratto (A = 1), com'è giusto per un package di soli contratti. `dominio` ha astrazione bassa perché contiene soprattutto entità concrete.
* **Cicli:** nessuno tra i package del modello.
* **`persistenza`, `cli`, `app`:** non nel modello, quindi senza valori. Per la regola di AD-01, `app` dovrà avere Ca = 0 e I = 1.
**WMC (numero di operazioni, con ogni metodo di peso 1, costruttori inclusi)**

|Classe|WMC|
|-|-|
|`SchedaRiparazione`|32|
|`StatoSchedaState`|21|
|`Preventivo`|17|
|`SchedaService`|14|
|`Ricambio`, `PreventivoService`, `MagazzinoService`|10|
|`Validatore`|9|
|`InDiagnosiState`|8|
|`Dispositivo`|6|
|Tutte le altre|5 o meno|

Il WMC totale è 209 operazioni su 39 classi e interfacce. Il valore di `SchedaRiparazione` (32) è il più alto, ma 18 operazioni sono semplici deleghe allo stato corrente e 7 sono operazioni di supporto `package-private`; con peso 1 per metodo la stima è per eccesso. `StatoSchedaState` ha 21 operazioni perché dichiara le 18 operazioni di evento più `getStato`, `daStato` e `nonConsentita`. `SchedaService` (14) è il servizio più carico e va tenuto d'occhio (§4.4.5).

* **NOC:** `StatoSchedaState` ha 10 sottoclassi; `RuntimeException` ha 3 sottoclassi del progetto.
* **DIT:** massimo 1 tra le classi del progetto (le eccezioni sono più profonde solo per le classi del JDK).
**Confronto con il codice generato (28/11/2026)**

Dagli `import` del codice generato (`main` al commit `f6d71d3`; 2.776 righe, commenti e righe vuote compresi) ho ricavato le dipendenze tra classi e ricalcolato Ca e Ce con gli stessi criteri:

|Package|Ca modello|Ca codice|Ce modello|Ce codice|
|-|-|-|-|-|
|`dominio`|10|10|3|0|
|`saldo`|3|0|0|0|
|`repository`|4|4|6|6|
|`notifica`|0|0|3|3|
|`servizi`|0|0|16|15|

* **Cicli:** nessuno tra i package del codice generato.
* **Differenze:** il codice contiene un sottoinsieme delle dipendenze del modello, perché i corpi dei metodi sono ancora vuoti (190 metodi con `TODO implement here`). La dipendenza `dominio → saldo` comparirà quando le classi `RiparataState`, `AnnullataState` e `NonRiparabileState` scriveranno `consegna`; anche la differenza di una dipendenza in `servizi` è di questo tipo. A, Nc, Na e I dei package `repository`, `notifica` e `servizi` coincidono con il modello.
* Il package radice contiene solo `App` (nessuna dipendenza).

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* CBO, RFC, LCOM, complessità ciclomatica e righe di codice, che richiedono i corpi dei metodi; ricalcolo di Ca, Ce e I con JDepend o Structure101 quando i corpi sono scritti · \*Da:\* Responsabile sviluppo e test · \*Quando:\* sprint 4. Da decidere in riunione dove riportare i valori: in questo documento (design) o nel Documento 5 (il Project Plan assegna le metriche al WP6)

### 4.4 Design pattern

Ogni pattern è descritto con contesto, problema e soluzione, poi l'applicazione a Tech Repair.

#### 4.4.1 State

* **Contesto:** la scheda attraversa dieci stati; la Specifica ha 23 righe di transizione, che nel diagramma sono 29 transizioni (§2.3). Le operazioni ammesse dipendono dallo stato.
* **Problema:** evitare `if`/`switch` sullo stato ripetuti in ogni operazione e impedire le operazioni illegali (modificare il preventivo in IN\_RIPARAZIONE, consegnare in IN\_DIAGNOSI).
* **Soluzione:** ogni stato è un oggetto che decide quali operazioni sono ammesse e quale stato segue; la scheda delega allo stato corrente. Le operazioni non consentite sollevano un'eccezione.
* **Applicazione:** `StatoSchedaState` è una classe astratta (nome distinto dall'enumerazione `StatoScheda` per evitare collisioni nella generazione). Dichiara le 18 operazioni di evento e il comportamento predefinito di ciascuna è sollevare `OperazioneNonConsentitaException` tramite `nonConsentita(scheda, operazione)`; le dieci classi concrete ridefiniscono solo le operazioni ammesse. `SchedaRiparazione` espone le stesse 18 operazioni e delega allo stato corrente; `cambiaStato` aggiorna sia l'oggetto stato sia l'enumerazione `statoCorrente` (il valore persistito) e aggiunge un `CambioStato` con data e ora. `StatoSchedaState.daStato(StatoScheda)` ricostruisce l'oggetto stato dal valore salvato. Guardie ed effetti sulle scorte seguono la tabella della Specifica (§5) e il diagramma del §2.3. Nel codice generato `StatoSchedaState` e le dieci classi concrete sono `package-private`, mentre entità, servizi e interfacce sono pubblici.
* **Requisiti:** RV-04, RF-08, RF-13, RF-15, RF-16.
* **Punto di attenzione:** la reciproca conoscenza tra scheda e stati impone che stiano nello stesso package (§3.3.3).
Operazioni ammesse da ciascuno stato (coincidono con gli eventi del diagramma, controllo automatico sul modello):

|Stato|Classe|Operazioni ridefinite|
|-|-|-|
|ACCETTATA|`AccettataState`|`avviaDiagnosi`, `impostaCostoManodopera`, `aggiungiRicambio`, `rinuncia`|
|IN\_DIAGNOSI|`InDiagnosiState`|`registraDiagnosi`, `impostaCostoManodopera`, `aggiungiRicambio`, `rimuoviRicambio`, `richiediVerificaMagazzino`, `inviaPreventivo`, `rinuncia`|
|IN\_VERIFICA\_MAGAZZINO|`InVerificaMagazzinoState`|`confermaRicambiOrdinabili`, `segnalaFuoriProduzione`, `rinuncia`|
|IN\_ATTESA\_APPROVAZIONE|`InAttesaApprovazioneState`|`modificaPreventivo`, `approvaPreventivo`, `rifiutaPreventivo`|
|IN\_ATTESA\_RICAMBI|`InAttesaRicambiState`|`registraOrdine`, `registraRicezioneOrdine`|
|IN\_RIPARAZIONE|`InRiparazioneState`|`aggiornaSeriale`, `chiudiRiparazione`|
|RIPARATA|`RiparataState`|`consegna` (strategia `SaldoRiparazioneCompletataStrategy`)|
|ANNULLATA|`AnnullataState`|`consegna` (strategia `SaldoSchedaAnnullataStrategy`)|
|NON\_RIPARABILE|`NonRiparabileState`|`consegna` (strategia `RimborsoNonRiparabileStrategy`)|
|CHIUSA|`ChiusaState`|Nessuna|

#### 4.4.2 Strategy

* **Contesto:** alla consegna il saldo si calcola in modo diverso secondo l'esito.
* **Problema:** isolare l'algoritmo monetario (RQ-01), renderlo testabile da solo ed evitare una catena di `if` nel servizio di consegna.
* **Soluzione:** una famiglia di algoritmi dietro la stessa interfaccia, scelta da chi la usa.
* **Applicazione:** interfaccia `CalcoloSaldoStrategy` nel package `saldo`, con la costante `COSTO\_DIAGNOSI` (10,00 €) e una sola operazione `calcolaSaldo(totalePreventivo)`, e tre strategie concrete. A scegliere la strategia sono le tre classi di stato che ammettono la consegna, nella loro operazione `consegna`:
|Strategia|Esito|Scelta da|Saldo|
|-|-|-|-|
|`SaldoRiparazioneCompletataStrategy`|Riparata|`RiparataState`|max(0 €, totale − 10 €)|
|`SaldoSchedaAnnullataStrategy`|Annullata|`AnnullataState`|0 €|
|`RimborsoNonRiparabileStrategy`|Non riparabile|`NonRiparabileState`|−10 € (rimborso)|
* **Requisiti:** RF-14, RQ-01.
* **Casi limite per i test:** totale 25 € dà saldo 15 €; totale 10 € dà 0 €; totale 7 € dà 0 €; annullata dà 0 €; non riparabile dà −10 €.
* **Punto di attenzione:** la strategia riceve l'importo e non l'entità, per non creare cicli: `saldo` ha Ce = 0 (§4.3).

#### 4.4.3 Observer

* **Contesto:** tre eventi producono una notifica simulata al cliente: invio del preventivo (anche in caso di modifica), impossibilità di riparare per ricambio fuori produzione, riparazione completata.
* **Problema:** la scheda non deve sapere come né dove notificare (log, console, in futuro email o SMS) e deve poter aggiungere canali senza modifiche (scenario CC3).
* **Soluzione:** la scheda (soggetto) avvisa gli osservatori registrati a ogni evento; ciascun osservatore decide cosa farne.
* **Applicazione:** interfaccia `SchedaObserver` con l'operazione `aggiorna(scheda, evento)` e enumerazione `EventoScheda` (PREVENTIVO\_INVIATO, RICAMBIO\_FUORI\_PRODUZIONE, RIPARAZIONE\_COMPLETATA). `SchedaRiparazione` tiene gli osservatori registrati (`aggiungiOsservatore`, `rimuoviOsservatore`) e li avvisa con `notificaOsservatori`, nelle azioni di ingresso degli stati IN\_ATTESA\_APPROVAZIONE, NON\_RIPARABILE e RIPARATA (§2.3). L'implementazione `NotificaClienteObserver`, nel package `notifica`, compone il messaggio e lo scrive con un logger Log4j dedicato con ConsoleAppender e FileAppender: così la notifica compare in console e resta tracciata su file (DA-02 chiusa, AD-05). `SchedaService` riceve un osservatore nel costruttore.
* **Requisiti:** RF-06, RF-12, RF-16, RI-03, RV-02.
* **Punto di attenzione:** `SchedaService` accetta un solo osservatore; per più canali serve un osservatore composito (§2.2, punto 5).

#### 4.4.4 Collocazione dei pattern nel modello e nel codice

|Pattern|Package|Diagramma|
|-|-|-|
|State|`dominio`|`classDiagramDesignState`|
|Strategy|`saldo` (scelta in `dominio`)|`classDiagramDesignStrategyObserver`|
|Observer|Interfaccia in `dominio`, implementazione in `notifica`|`classDiagramDesignStrategyObserver`|

!\[Pattern State](Immagini/classDiagramDesignState.svg)

!\[Pattern Strategy e Observer](Immagini/classDiagramDesignStrategyObserver.svg)

Verificato sul codice generato (28/11/2026): le classi dei tre pattern stanno nei package indicati nella tabella.

#### 4.4.5 Antipattern da evitare

* **God class:** un servizio o la scheda che accentra tutte le responsabilità. Rimedio: invarianti nelle entità, orchestrazione in quattro servizi divisi per gruppo di casi d'uso (§4.2). Il candidato è `SchedaService` (14 operazioni, §4.3).
* **Swiss Army Knife:** interfacce troppo ampie. Rimedio: un repository per ciascun aggregato persistito (cinque) e interfacce piccole.
* **Golden Hammer:** usare i pattern dove il problema non c'è. Per questo i pattern sono tre, ciascuno con un problema preciso.
* **Problema dei ravioli:** oggetti che si inviano messaggi senza regole. Rimedio: livelli e dipendenze del §3.3.

### 4.5 Descrizione dei componenti (IEEE 1016)

Lo standard descrive ogni componente con dieci attributi (identificazione, tipo, scopo, funzione, subordinati, dipendenze, interfaccia, risorse, elaborazione, dati), raggruppati in quattro viste. In Tech Repair il componente è il package (in alternativa si potrebbe usare un modulo Maven); le classi sono sottocomponenti quando i dettagli sono noti.

#### 4.5.1 Vista di decomposizione

I subordinati dei package del modello sono quelli del class diagram di design; `app` e `cli` non sono ancora nel modello.

|Package|Tipo|Scopo|Funzione|Subordinati|
|-|-|-|-|-|
|`app`|Package di avvio|Avviare l'applicazione|Crea le implementazioni di `persistenza` e `notifica`, i servizi, e avvia la console|Classe `App`: oggi nel package radice e limitata al log di avvio (DA-04)|
|`cli`|Package|Interazione con gli operatori (RI-01)|Menu di scelta ruolo, menu del Tecnico e del Responsabile Magazzino, lettura dell'input, stampa dei risultati e degli errori|Menu principale, menu Tecnico, menu Magazzino|
|`servizi`|Package|Casi d'uso|Orchestra entità e repository: anagrafica e accettazione, ciclo di vita e consegna, preventivo, catalogo, verifica e ordini|`ClienteService`, `SchedaService`, `PreventivoService`, `MagazzinoService`, `ElementoNonTrovatoException`|
|`dominio`|Package|Modello del dominio|Entità, enumerazioni, regole di integrità, State, interfaccia Observer, validazione|Cliente, Dispositivo, SchedaRiparazione, CambioStato, Preventivo, VoceRicambio, Ricambio, OrdineAcquisto, Pagamento, otto enumerazioni, `StatoSchedaState` e dieci stati, `SchedaObserver`, `Validatore`, `DatiNonValidiException`, `OperazioneNonConsentitaException`|
|`saldo`|Package|Calcolo del saldo|Strategy per gli esiti|`CalcoloSaldoStrategy` e le tre strategie concrete|
|`notifica`|Package|Notifiche simulate (RI-03)|Implementa l'osservatore e scrive con Log4j|`NotificaClienteObserver`|
|`repository`|Package|Contratto di accesso ai dati|Interfacce dei repository|Un'interfaccia per ciascuno dei cinque aggregati persistiti|
|`persistenza`|Package|Persistenza (RI-02, RD-01 – RD-03)|Implementa i repository con JDBC sul database SQLite, gestisce connessione, schema e transazioni|Nessuna classe nel codice generato: implementazioni dei repository, connessione e schema da scrivere|

#### 4.5.2 Vista delle dipendenze

Le dipendenze tra package sono nella tabella del §3.3.1. Risorse esterne:

|Package|Risorse|
|-|-|
|`cli`|JDK (`System.in`, `System.out`)|
|`notifica`|Log4j (`log4j-api`)|
|`dominio`, `saldo`, `servizi`, `repository`|Solo JDK (`java.math.BigDecimal`, `java.time`), nel modello e nel codice generato|
|`persistenza`|SQLite tramite `org.xerial:sqlite-jdbc` 3.54.1.0|
|Configurazione|Log4j (`log4j-core` 2.26.1) con `log4j2.xml` in `src/main/resources`: Console e file `logs/techrepair.log` (percorso relativo, cartella esclusa da `.gitignore`)|
|Test|JUnit Jupiter 5.11.0 (BOM; `api`, `engine`, `params`); `log4j2-test.xml` solo con Console|
|Build|Maven, Java 17 (`maven.compiler.release`), plugin a versione fissata|

Nota sul logging: il root logger ha sia Console sia file, quindi anche i log tecnici arrivano in console e si mescolano ai menu della `cli`. Il logger dedicato alle notifiche previsto da AD-05 non è ancora configurato (DA-06).

#### 4.5.3 Vista di interfaccia

Operazioni pubbliche dal class diagram di design. `?` indica un risultato che può mancare (molteplicità 0..1), `\[]` una lista.

**Repository** (package `repository`)

|Interfaccia|Operazioni|
|-|-|
|`ClienteRepository`|`salva(cliente)`, `trovaPerCodiceFiscale(codiceFiscale): Cliente?`|
|`DispositivoRepository`|`salva(dispositivo)`, `trovaPerSeriale(seriale): Dispositivo?`, `esisteSeriale(seriale): Boolean`|
|`SchedaRepository`|`salva(scheda)`, `trovaPerId(idScheda): SchedaRiparazione?`, `trovaPerStato(stato): SchedaRiparazione\[]`, `trovaPerCodiceFiscale(codiceFiscale): SchedaRiparazione\[]`, `trovaPerSeriale(seriale): SchedaRiparazione\[]`|
|`RicambioRepository`|`salva(ricambio)`, `trovaPerCodice(codiceRicambio): Ricambio?`, `trovaTutti(): Ricambio\[]`|
|`OrdineRepository`|`salva(ordine)`, `trovaPerId(idOrdine): OrdineAcquisto?`, `trovaPerScheda(idScheda): OrdineAcquisto?`|

`SchedaRepository.salva` salva in un'unica transazione l'intera scheda con stati, preventivo, voci, pagamenti, ordine e giacenza dei ricambi coinvolti.

**Servizi** (package `servizi`)

|Servizio (dipendenze nel costruttore)|Operazioni pubbliche|
|-|-|
|`ClienteService` (`ClienteRepository`, `DispositivoRepository`)|`cercaPerCodiceFiscale(codiceFiscale): Cliente?`, `registraCliente(nome, cognome, codiceFiscale, telefono, email): Cliente`, `registraDispositivo(cliente, seriale, marca, modello): Dispositivo`|
|`SchedaService` (`SchedaRepository`, `DispositivoRepository`, `SchedaObserver`)|`apriScheda(dispositivo, guastoDichiarato): SchedaRiparazione`, `generaRicevutaIngresso(idScheda): String`, `avviaDiagnosi(idScheda)`, `registraDiagnosi(idScheda, diagnosi)`, `registraRinuncia(idScheda)`, `aggiornaSeriale(idScheda, nuovoSeriale)`, `chiudiRiparazione(idScheda)`, `consegnaDispositivo(idScheda): Pagamento`, `cercaPerId(idScheda)`, `cercaPerCodiceFiscale(codiceFiscale): \[]`, `cercaPerSeriale(seriale): \[]`, `cercaPerStato(stato): \[]`|
|`PreventivoService` (`SchedaService`, `RicambioRepository`)|`impostaCostoManodopera(idScheda, costo)`, `aggiungiRicambio(idScheda, codiceRicambio)`, `rimuoviRicambio(idScheda, codiceRicambio)`, `richiediVerificaMagazzino(idScheda)`, `inviaPreventivo(idScheda)`, `modificaPreventivo(idScheda, codiciDaAggiungere\[], codiciDaRimuovere\[], costoManodopera)`, `approvaPreventivo(idScheda)`, `rifiutaPreventivo(idScheda)`|
|`MagazzinoService` (`RicambioRepository`, `OrdineRepository`, `SchedaService`)|`inserisciRicambio(codiceRicambio, nome, qualita, prezzoUnitario, giacenza, reperibilita): Ricambio`, `rifornisciRicambio(codiceRicambio, quantita)`, `consultaInventario(): Ricambio\[]`, `schedeInVerifica(): \[]`, `confermaRicambiOrdinabili(idScheda)`, `segnalaFuoriProduzione(idScheda, codiceRicambio)`, `schedeInAttesaRicambi(): \[]`, `emettiOrdine(idScheda): OrdineAcquisto`, `registraRicezioneOrdine(idOrdine)`|

`MagazzinoService.registraRicezioneOrdine` aggiorna ordine e scheda e salva la scheda in un'unica transazione.

**Dominio e pattern**

* `SchedaRiparazione`: le 18 operazioni di evento elencate nel §4.4.1, più `isAttiva()`, `aggiungiOsservatore(osservatore)`, `rimuoviOsservatore(osservatore)`. Il costruttore crea la scheda in ACCETTATA, la collega al dispositivo e registra il pagamento DIAGNOSI\_INIZIALE di 10,00 €.
* `StatoSchedaState`: le 18 operazioni di evento con parametro `scheda`, `getStato()` e `daStato(stato)`.
* `CalcoloSaldoStrategy`: `calcolaSaldo(totalePreventivo): BigDecimal`. `SchedaObserver`: `aggiorna(scheda, evento)`.
**Eccezioni** (tutte non controllate)

|Eccezione|Quando è sollevata|
|-|-|
|`DatiNonValidiException`|Da ogni metodo di `Validatore`; da `Preventivo.invia` se non ci sono ricambi e il costo di manodopera è ≤ 0|
|`OperazioneNonConsentitaException`|Dal comportamento predefinito di `StatoSchedaState` (operazione non ammessa dallo stato); da `Dispositivo.aggiungiScheda` (scheda attiva già presente); da `Preventivo` (modifica di un preventivo APPROVATO o RIFIUTATO); da `Ricambio.impegna` (giacenza non positiva)|
|`ElementoNonTrovatoException`|Dai servizi, per identificativo o criterio inesistente|

**Vincoli documentati nel modello**

* `Ricambio.impegna`: richiede giacenza > 0; giacenza − 1; a 0 la reperibilità diventa DA\_VERIFICARE. `Ricambio.rilascia`: giacenza + 1; la reperibilità torna DISPONIBILE.
* `Preventivo.aggiungiVoce`: la voce è IMPEGNATO se la giacenza è positiva (e impegna il ricambio), altrimenti DA\_ORDINARE; rifiuta i duplicati. `rimuoviVoce` rilascia (+1) solo le voci IMPEGNATO.
* `Preventivo.isInviabile`: ogni voce è IMPEGNATO o riferita a un ricambio ORDINABILE e, senza voci, il costo di manodopera è > 0,00. `invia`: BOZZA → INVIATO.
* `Preventivo.calcolaTotale`: costo di manodopera più somma dei prezzi delle voci, calcolato a ogni chiamata (nessun setter).
* `Preventivo.assegnaRicambiOrdinati`: porta le voci DA\_ORDINARE a IMPEGNATO senza toccare la giacenza.
* `Dispositivo.aggiungiScheda`: eccezione solo se la scheda aggiunta è attiva e il dispositivo ne ha già una; le schede CHIUSE caricate dal database si aggiungono sempre.

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* precondizioni e postcondizioni delle operazioni dei servizi (il modello le documenta per le entità ma non per i servizi), da ricavare dai sequence diagram · \*Da:\* issue «Sequence diagram dei flussi principali in StarUML» · \*Quando:\* sprint 2, dopo i diagrammi

#### 4.5.4 Vista di dettaglio

**Già noto dal setup del database (PR #43).** Il driver è `sqlite-jdbc` e la connessione si apre con un URL `jdbc:sqlite:...`. `DatabaseSetupTest` usa un database in memoria (`jdbc:sqlite::memory:`) e verifica che un importo di 10,00 € si salvi come intero in centesimi (colonna `INTEGER`, 1000) e si rilegga come `BigDecimal` con due decimali (`BigDecimal.valueOf(1000, 2)`). Questo è il modo scelto per tenere esatti gli importi (RQ-01) con un database senza tipo decimale. Il file del database non è ancora definito nel codice; il `.gitignore` esclude `data/`, `\*.db` e i file `-journal`, `-wal`, `-shm`.

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* schema dei dati (tabelle, chiavi, tipi), percorso del file del database, inizializzazione dello schema, transazioni in `persistenza`; algoritmi (calcolo del totale del preventivo, impegno e rilascio della giacenza, transizioni di stato) e gestione delle eccezioni nei corpi dei metodi · \*Da:\* implementazione di `persistenza`, dominio e servizi · \*Quando:\* sprint 3 e sprint 4

### 4.6 Classi generate da StarUML

Il Project Plan chiede che il Documento 4 riporti l'elenco delle classi generate, con package e modifiche manuali successive.

**Generazione e commit** (issue #32, branch `32-generazione-del-codice-da-staruml-e-setup-maven-con-database-embedded`)

|Passo|Commit|Data|Contenuto|
|-|-|-|-|
|Generazione dal modello|`632da6a`|25/11/2026|47 file Java generati da StarUML (2.856 righe)|
|Sistemazione e SQLite|`283181a`|25/11/2026|Correzioni perché il codice compili, dipendenza `sqlite-jdbc`, `.gitignore`, `DatabaseSetupTest`|
|Fusione in `main`|`f6d71d3`|27/11/2026|Merge della pull request #43|

Il codice sta in `Codice/techrepair` (progetto Maven, Java 17).

|Package Java|Tipi|Origine (diagramma)|
|-|-|-|
|`it.unibg.techrepair.dominio`|32|`classDiagramDesignEntita`, `classDiagramDesignEnumValidatoreEccezioni`, `classDiagramDesignState`|
|`it.unibg.techrepair.saldo`|4|`classDiagramDesignStrategyObserver`|
|`it.unibg.techrepair.notifica`|1|`classDiagramDesignStrategyObserver`|
|`it.unibg.techrepair.repository`|5|`classDiagramDesignServizi`|
|`it.unibg.techrepair.servizi`|5|`classDiagramDesignServizi`|
|`it.unibg.techrepair.persistenza`|0|Da scrivere a mano (database SQLite)|

I 47 tipi generati coincidono con quelli del modello (controllo automatico: nessun tipo in più né in meno). Nel codice ci sono inoltre `App` (nel package radice) e i test `AppTest` e `DatabaseSetupTest`.

* **`dominio`:** enumerazioni `StatoScheda`, `StatoPreventivo`, `StatoVoce`, `QualitaRicambio`, `Reperibilita`, `StatoOrdine`, `TipoPagamento`, `EventoScheda`; entità `Cliente`, `Dispositivo`, `SchedaRiparazione`, `CambioStato`, `Pagamento`, `OrdineAcquisto`, `Preventivo`, `VoceRicambio`, `Ricambio`; `Validatore`, `DatiNonValidiException`, `OperazioneNonConsentitaException`; `StatoSchedaState`, `AccettataState`, `InDiagnosiState`, `InVerificaMagazzinoState`, `InAttesaApprovazioneState`, `InAttesaRicambiState`, `InRiparazioneState`, `RiparataState`, `AnnullataState`, `NonRiparabileState`, `ChiusaState`; `SchedaObserver`.
* **`saldo`:** `CalcoloSaldoStrategy`, `SaldoRiparazioneCompletataStrategy`, `SaldoSchedaAnnullataStrategy`, `RimborsoNonRiparabileStrategy`.
* **`notifica`:** `NotificaClienteObserver`.
* **`repository`:** `ClienteRepository`, `DispositivoRepository`, `SchedaRepository`, `RicambioRepository`, `OrdineRepository`.
* **`servizi`:** `ClienteService`, `SchedaService`, `PreventivoService`, `MagazzinoService`, `ElementoNonTrovatoException`.
**Modifiche manuali dopo la generazione** (commit `283181a`)

|Dove|Modifica|Motivo|
|-|-|-|
|39 file su 47|Gli `import java.io.\*` e `import java.util.\*` generici sono sostituiti da import espliciti (per esempio `java.math.BigDecimal`, `java.time.LocalDate`)|Il generatore non importa i tipi realmente usati; serve perché il codice compili|
|Stati, strategie, osservatore|Aggiunto `@Override` ai metodi che ridefiniscono (38 occorrenze)|Controllo del compilatore sulle ridefinizioni|
|Le dieci classi di stato concrete|Tolta la dichiarazione `abstract getStato()`, ripetuta dal generatore|Nelle sottoclassi il metodo è concreto, la dichiarazione astratta impedirebbe la compilazione|
|`NotificaClienteObserver`|Tolto il metodo `aggiorna` duplicato; aggiunti gli import di Log4j; `LOGGER` reso `final`|Il generatore ripete il metodo dell'interfaccia realizzata|
|`Preventivo`|Tolto il campo `totaleCalcolato` (§2.2, punto 4)|Attributo derivato: il valore si ottiene da `calcolaTotale()`|
|`Preventivo`, `Validatore`|Tolto il costruttore predefinito vuoto generato in più|Il modello ha già il proprio costruttore (pubblico in `Preventivo`, privato in `Validatore`)|

Altri file toccati nello stesso commit: `pom.xml` (dipendenza `sqlite-jdbc`), `.gitignore` (database locale), nuovo `DatabaseSetupTest`.

**Da scrivere**

* Tutti i corpi dei metodi: nel codice ci sono 190 metodi con `// TODO implement here`.
* Le classi di `persistenza`, di `cli` e di `app` (DA-04).
* Controlli di coerenza sul codice: i sei `setId...` e le tre factory `ricostruisci` previsti dal modello (§2.2, punto 3) sono presenti.

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* aggiornamento di questa tabella con le modifiche manuali che si aggiungeranno scrivendo i corpi dei metodi e le classi mancanti · \*Da:\* Responsabile sviluppo e test · \*Quando:\* sprint 3 e sprint 4

### 4.7 Tracciabilità requisiti–design

Per gruppi funzionali, con le classi del modello. I gruppi vanno confrontati con il Documento 3 prima della consegna; i test si aggiungono quando c'è il Documento 5.

|Requisiti|Componenti|Pattern|Diagrammi|Test (Documento 5)|
|-|-|-|-|-|
|Accettazione: RF-01 – RF-03|`ClienteService`, `SchedaService.apriScheda` e `generaRicevutaIngresso`; `dominio` (Cliente, Dispositivo, SchedaRiparazione, Pagamento); `cli`; `persistenza`|—|Class diagram (entità, servizi), state machine (transizione 1), sequence accettazione|Da compilare|
|Diagnosi e preventivo: RF-04 – RF-08|`SchedaService` (diagnosi), `PreventivoService`; `dominio` (Preventivo, VoceRicambio)|State (blocco dopo l'approvazione), Observer (notifica di invio)|Class diagram (entità, servizi, state), sequence preventivo, state machine|Da compilare|
|Ricambi e magazzino: RF-09 – RF-11, RF-19|`MagazzinoService`; `dominio` (Ricambio, OrdineAcquisto)|State (IN\_VERIFICA\_MAGAZZINO, IN\_ATTESA\_RICAMBI)|Class diagram, sequence preventivo, communication, activity|Da compilare|
|Consegna e pagamento: RF-12 – RF-14|`SchedaService` (`chiudiRiparazione`, `consegnaDispositivo`); `saldo`; `dominio` (Pagamento)|Strategy, Observer (riparazione completata)|Class diagram (strategy e observer), sequence consegna|Da compilare|
|Eccezioni: RF-15, RF-16|Classi di stato, `PreventivoService.rifiutaPreventivo`, `SchedaService.registraRinuncia`, `MagazzinoService.segnalaFuoriProduzione`|State, Observer|State machine, frammenti `alt`|Da compilare|
|Consultazione: RF-17|`SchedaService` (`cercaPer...`), `MagazzinoService.consultaInventario`, repository (`trovaPer...`)|—|Class diagram (servizi)|Da compilare|
|Validazione: RF-18|`Validatore`, `DatiNonValidiException`, `OperazioneNonConsentitaException`, usati dai servizi (non da `cli`)|—|Class diagram (enumerazioni, validatore, eccezioni)|Da compilare|
|Interfacce: RI-01 – RI-03|`cli`, `persistenza`, `SchedaObserver` e `NotificaClienteObserver` (Log4j)|Observer|Class diagram (strategy e observer)|Da compilare|
|Database: RD-01 – RD-03|`persistenza`, `repository` (cinque interfacce)|—|Class diagram (servizi)|Da compilare|
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
**Esito dei controlli (diagrammi del 26/11/2026, codice del 28/11/2026)**
* **Class diagram di design:** entità, enumerazioni, nomi dei pattern, tipi degli importi, visibilità, molteplicità e assenza di cicli tra package sono conformi (§2.2). Da correggere: molteplicità dei pagamenti (`1..\*` invece di `1..2`). Da annotare: doppia rappresentazione dello stato, accesso per la persistenza, attributo derivato `totaleCalcolato`, osservatore unico.
* **State machine:** stato iniziale e finale presenti; evento, guardia ed effetto sulle transizioni; tutte le 23 righe della Specifica coperte; nessuno stato irraggiungibile; eventi coerenti con le classi di stato (§2.3). Da decidere: ricambio fuori produzione aggiunto da ACCETTATA (DA-05); effetto della transizione 5.
* **Codice generato (28/11/2026):** 47 tipi su 47 coincidenti con il modello; sei `setId...` e tre `ricostruisci` come previsto; nessun ciclo tra package (§4.3); il package `persistenza` è vuoto e 190 metodi sono ancora da implementare. Da decidere: configurazione dei log (DA-06).
* **Diagrammi non ancora in `main`:** sequence (in lavorazione sul branch della sua issue), activity, communication, package e component.

> \*\*DA COMPLETARE\*\* · \*Si aspetta:\* la checklist del corso, per integrare e sostituire la bozza · \*Da:\* materiale del corso · \*Quando:\* prima della prima review dei diagrammi

