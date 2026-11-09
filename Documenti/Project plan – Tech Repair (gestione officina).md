# Project Plan – Tech Repair (gestione officina)

Tech Repair è il sistema che il nostro team di quattro persone sviluppa per il corso di Ingegneria del software (AA 2026/27); questo Project Plan ne fissa processo, ruoli, rischi e calendario.

## Versioni

|Versione|Data|Modifiche|
|-|-|-|
|1.0.0|12/10/2026|Versione iniziale|
|1.1.0|09/11/2026|Revisione e allineamento di date, ore e work package agli obiettivi per sprint (sezione 2)|

## 1. Introduction

Tech Repair gestisce il percorso di un dispositivo in riparazione, dall'accettazione in officina alla consegna e al pagamento.

**Scopo del documento**

Pianificare come sviluppiamo il sistema: Processo, organizzazione, rischi, risorse e calendario. Il piano è condiviso con il professore e l'esercitatrice tramite issue GitHub per l'approvazione.

**Attori**

Il sistema ha tre attori: Il Cliente, il Tecnico Riparatore e il Responsabile Magazzino.

**Funzionalità principali**

* **Accettazione:** Accettazione del dispositivo, creazione della scheda di riparazione e generazione della ricevuta di ingresso.
* **Diagnosi e preventivo:** Diagnosi del guasto, invio del preventivo e approvazione da parte del cliente.
* **Ricambi:** Ordine del pezzo di ricambio quando non è presente in magazzino.
* **Consegna e pagamento:** Consegna del dispositivo riparato e registrazione del pagamento.

**Flussi di eccezione**

Se il cliente rifiuta il preventivo, la scheda passa ad "Annullato" e si addebita solo il costo di diagnosi. Se il pezzo di ricambio è fuori produzione, il sistema notifica l'impossibilità di riparare.

**Perimetro**

Rientra tutto il flusso sopra, con dati persistiti in un database embedded e interfaccia solo a riga di comando (console), senza interfaccia grafica. Restano fuori pagamenti reali, invio reale di email o SMS (le notifiche sono simulate) e contabilità.

**Riferimenti**

Requisiti per il progetto di ingegneria del software AA 2026/27 (Gargantini, Bonfanti); materiale del corso, incluso il capitolo 2 del libro.

## 2. Process model

Seguiamo un processo iterativo e incrementale ispirato a Scrum, organizzato in time box (sprint) associati a milestone GitHub (quattro settimane a carico ridotto per lo sprint 0, due settimane ciascuno per gli sprint 1-4 e quattro giorni di margine finale per lo sprint 5), in cui requisiti e modelli vengono raffinati a ogni iterazione; il progetto è quasi completo il 4 gennaio 2027.

**Perché questo processo**

Il team è piccolo e ha poche ore a testa, quindi servono iterazioni brevi e verificabili. Il corso chiede di spendere almeno il 70% del tempo su requisiti, modellazione e progettazione, quindi i modelli UML precedono il codice. Lavorare a sprint ci permette di ricevere feedback dal professore tramite issue prima di consolidare le scelte.

**Ciclo di ogni sprint**

1. **Pianificazione:** Scegliamo le issue dello sprint e le assegniamo sulla board GitHub.
2. **Sviluppo e revisione:** Lavoro su branch dedicati, con pull request e code review.
3. **Chiusura:** Verifica dei criteri di accettazione e retrospettiva breve.

**Obiettivi per sprint** (date e ore nella sezione 12)

* **Sprint 0** (12 ottobre - 9 novembre, 16 ore di team): Setup del repository, Project Plan, avvio dei requisiti.
* **Sprint 1** (10-23 novembre, 32 ore): Specifica dei requisiti, use case diagram, class diagram.
* **Sprint 2** (24 novembre - 7 dicembre, 32 ore): State machine, sequence, communication o timing e activity diagram, prima generazione del codice da StarUML secondo un approccio MDA (Model-Driven Architecture), prime viste architetturali.
* **Sprint 3** (8-21 dicembre, 32 ore): Architettura (component e package diagram), design pattern, implementazione del nucleo.
* **Sprint 4** (22 dicembre - 4 gennaio, 32 ore): Implementazione completa, test JUnit, copertura e metriche, refactoring e documenti finali; il progetto è quasi pronto il 4 gennaio.
* **Sprint 5** (5-8 gennaio, nessuna ora aggiuntiva oltre al completamento del WP7): Margine per correzioni, revisione incrociata, prova su clone pulito e consegna entro l'8 gennaio.
* **Percorso critico:** Specifica dei requisiti (sprint 0-1) → Class diagram stabile (sprint 1-2) → Generazione del codice da StarUML (fine sprint 2) → Implementazione del nucleo e test (sprint 3-4).

Le differenze tra questo piano e il processo realmente seguito saranno riportate nel Documento 2 (Gestione del progetto).

## 3. Organization of the project

Il team è composto da quattro persone, ciascuna con un'area di responsabilità specifica, operando come una squadra agile senza una gerarchia rigida e coordinandosi per adattamento reciproco.

**Struttura interna**

Secondo uno stile di gestione orientato alla relazione e all'integrazione, tutte le decisioni operative e architetturali vengono prese per consenso all'interno del team. Nel caso in cui non si raggiunga un accordo entro la riunione settimanale, la decisione finale spetta al Project Manager. Quest'ultimo ha l'obbligo di motivare formalmente la scelta intrapresa tramite un commento dedicato all'interno dell'issue di riferimento.

**Interfacce esterne**

* **Docenti di riferimento:** Il Prof. Gargantini (`garganti`) e la Prof.ssa Bonfanti (`silviabonfanti`) dispongono dell'accesso completo al repository GitHub del progetto. Le richieste di approvazione formale e i feedback vengono gestiti tramite GitHub.
* **Project Plan:** Il Project Plan deve essere formalmente approvato dal professore almeno un mese prima della data d'esame prevista.

**Comunicazione**

* **GitHub:** Canale ufficiale ed esclusivo per il tracciamento delle decisioni, l'inoltro delle richieste di modifica al codice o ai modelli, e lo svolgimento delle attività di code review.
* **Riunione settimanale:** Incontro periodico del team finalizzato all'allineamento sullo stato di avanzamento delle attività (stato dei task sulla lavagna Kanban) e alla pianificazione degli obiettivi futuri.
* **Chat di gruppo:** Utilizzata unicamente per comunicazioni rapide, urgenze operative o scambi informativi interni. Questo canale non ha alcuna validità decisionale ai fini dello sviluppo del progetto.

## 4. Standards, guidelines, procedures

Tutto il lavoro passa da GitHub e segue regole comuni su repository, codice, modelli e documenti, per rendere chiaro chi ha fatto cosa.

**Repository e cartelle**

* **Proprietà del repository:** Creato dall'account `AleBenUni` e condiviso con i professori Gargantini e Bonfanti. La root del repository prende il nome del software (`TechRepair`) e non deve contenere file di configurazione dell'IDE (es. `.project`).
* **Struttura delle cartelle:**

  * **`Documenti/`:** Contiene la documentazione ufficiale e il file di progetto StarUML.
  * **`Codice/`:** Contiene i progetti Eclipse.
* **Nomenclatura progetti Eclipse:** Devono tassativamente essere usati nomi caratteristici o nomenclature. Sono vietati nomi generici.
* **Gestione `.gitignore`:** Il file `.gitignore` deve escludere i file `.class` e la cartella `target/`. I file `.project`, `.classpath` e `pom.xml` devono essere sempre committati per garantire l'allineamento dell'ambiente di sviluppo.

**Procedura Git e Kanban**

* **Branch `main` stabile (baseline):** Il ramo `main` rappresenta la linea di base (baseline) degli elementi di configurazione approvati (codice, modelli e documenti). Nessuna modifica viene effettuata direttamente su di esso.
* **Apertura dei branch:** Ogni attività parte da un'issue classificata tramite label, milestone e assegnatario. Il branch associato deve essere creato direttamente dall'interfaccia web dell'issue su GitHub, accettando la nomenclatura automatica predefinita.
* **Commit piccoli e frequenti:** Ogni commit deve avere un titolo esplicativo ed eventuale descrizione dettagliata. Sono vietati messaggi generici.
* **Una pull request per ogni branch:** Ogni branch richiede l'apertura di una pull request (PR), che risulterà nativamente collegata all'issue di origine grazie alla creazione guidata del ramo.
* **Gestione file StarUML (`.mdj`):** Essendo un file unico e non integrabile via merge testuale, è tassativo lavorarci una sola persona alla volta.

  * Chi inizia a lavorarci deve "prenotarlo" tramite il Project Manager che contrassegna la issue con una label "Critical Section".
  * Nessun altro può modificare il modello finché l'issue associata non passa nello stato "In Review".
  * È obbligatorio eseguire un `git pull` immediatamente prima di aprire StarUML.

**Pull request e review**

* **Nessun push diretto su `main`:** Ogni modifica passa da una pull request.
* **Approvazione mirata:** Per poter effettuare il merge, serve l'approvazione del Project Manager. In caso di sua indisponibilità, l'approvazione può essere concessa da un membro del team diverso dall'autore, scelto in base alla competenza specifica del task.
* **Strategia di merge:** Il merge viene eseguito solo dopo aver ottenuto l'approvazione. Si utilizza il merge normale (Create a merge commit) e non lo squash, per mantenere intatta la cronologia dei singoli commit nella storia del progetto.
* **Statistiche finali:** A fine progetto, il Documento 2 (Gestione del progetto) riporterà i dati analitici su commit, PR aperte e review effettuate per ciascun componente del team.

**Codice**

* **Tecnologie:** Linguaggio Java (adottando le convenzioni standard) e progetti gestiti tramite Maven con il codice sorgente posizionato in `src/main/java` e la suite di test in `src/test/java`.
* **Portabilità:** Divieto assoluto di inserire percorsi (path) assoluti. Il database deve essere di tipo embedded e l'applicazione non deve dipendere da servizi esterni da installare localmente.
* **Architettura del codice:** Package coesi e privi di cicli di dipendenza. Rispetto del principio dell'information hiding tramite l'uso appropriato dei modificatori `private` e `public`. Uso sistematico di interfacce, classi astratte e dell'annotazione `@Override` dove necessario.
* **Tracciamento:** Logging dell'applicazione gestito tramite Log4j (al posto di stampe con `System.out.println`), con le relative dipendenze (`log4j-api` e `log4j-core`) configurate nel `pom.xml` di Maven.

**Modelli e documenti**

* **Modellazione UML:** Gestita tramite StarUML 5. Il file di progetto contenente tutti i diagrammi deve essere salvato nel repository.
* **Documentazione finale:** Tutta la documentazione ufficiale viene organizzata e consegnata in un unico file PDF strutturato a capitoli, contenente le immagini dei diagrammi UML esportate direttamente nel testo.

## 5. Management activities

La strategia di gestione pone come priorità assoluta un'analisi e una progettazione solide, seguite da un'implementazione funzionante dei flussi principali del sistema. Il progetto deve essere in stato di quasi completamento entro il 4 gennaio 2027, con consegna definitiva fissata entro l'8 gennaio 2027.

**Obiettivi in ordine di priorità**

1. **Requisiti, modelli UML e progettazione:** Sviluppo di modelli e design completi, coerenti e non banali (che insieme alla gestione assorbono circa il 70% del monte ore totale).
2. **Implementazione software:** Sviluppo funzionante dei flussi principali dell'applicazione Tech Repair.
3. **Utilizzo effettivo di GitHub:** Applicazione rigorosa del flusso di lavoro tramite milestone, issue con label, branch, pull request e attività di code review.
4. **Qualità del codice:** Scrittura di test JUnit, misurazione della code coverage e monitoraggio delle metriche del codice.

**Assunzioni**

* **Impegno orario:** Ogni membro del team dedica 36 ore complessive (144 ore di team) tra il 12 ottobre 2026 e il 4 gennaio 2027: 4 ore a testa nello sprint 0 (12 ottobre - 9 novembre, 16 ore di team su 4 settimane) e 4 ore a settimana a testa per le 8 settimane degli sprint 1-4 (10 novembre 2026 - 4 gennaio 2027, 32 ore di team per sprint), equivalenti a 9 settimane lavorative da 4 ore pro capite, rispettando la forchetta di 30-40 ore indicata dal corso.
* **Standardizzazione:** Tutti i componenti del team utilizzano i medesimi strumenti di sviluppo e la stessa struttura concordata per il repository.
* **Fattori esterni (Dipendenze):** La pianificazione temporale è strettamente subordinata alla data di approvazione del Project Plan, alla calendarizzazione della presentazione orale e alle sessioni d'esame degli altri insegnamenti universitari.

**Monitoraggio e reporting (GitHub Projects)**

* **Bacheca Kanban (condivisa con il team e con i docenti):**

  * **ToDo board:** Vista operativa principale organizzata che mappa visivamente i task dall'ideazione fino alla chiusura.

    * **Backlog:** Raccolta iniziale di tutte le issue e delle idee.
    * **Ready:** Task rifiniti e pronti per essere presi in carico dal team.
    * **In Progress:** Attività attualmente in fase di sviluppo.
    * **In Review:** Codice o modelli sotto revisione tramite pull request.
    * **Done:** Task completati e validati.
  * **Team items:** Panoramica globale che mostra tutte le issue del progetto, i rispettivi assegnatari e la suddivisione del lavoro organizzata per ciascuna milestone.
  * **My items:** Vista personalizzata filtrata esclusivamente sulle issue assegnate al singolo utente, raggruppate in base alla milestone di riferimento.
  * **Roadmap:** Visualizzazione cronologica che ordina e traccia nel tempo la pianificazione di tutte le issue lungo una linea temporale.
* **Registro ore:** Registro contenente le ore di lavoro suddiviso in milestone e assegnatari.

  * **Ore stimate:** Ore stimate per il completamento dell'issue.
  * **Ore effettive:** Ore impiegate per il completamento dell'issue.

**Rituali di controllo**

* **Allineamento settimanale:** Riunione periodica volta a verificare lo stato di avanzamento dei task e a ridistribuire le attività in caso di colli di bottiglia.

## 6. Potential risks

I rischi più pesanti sono la sottostima del tempo di modellazione e la distribuzione non uniforme del carico; il registro viene rivisto a ogni fine sprint.

|Rischio|Probabilità|Impatto|Mitigazione|Responsabile|
|-|-|-|-|-|
|Sottostima del tempo di modellazione|Alta|Alto|Buffer nello sprint 2, review dei diagrammi a metà sprint|Architetto|
|Requisiti ambigui, incompleti o soggetti a fraintendimenti|Media|Alto|Review incrociata dei casi d'uso con il team prima di bloccare la specifica; definizione chiara di flussi principali ed eccezioni.|Analista dei Requisiti|
|Diagrammi UML troppo semplici|Media|Alto|Checklist degli elementi visti a lezione per state machine, sequence e activity; review incrociata|Architetto|
|Carico di lavoro non uniforme tra i membri|Media|Alto|Issue assegnate esplicitamente, registro ore, controllo settimanale|Project Manager|
|Implementazione frammentaria|Media|Alto|Perimetro ridotto, priorità ai flussi principali, test sui flussi|Responsabile sviluppo e test|
|Sovrapposizione con gli esami di altri corsi|Alta|Medio|Calendario condiviso, carico ridotto nello sprint 0 (12 ottobre - 9 novembre, 16 ore di team su quattro settimane) e margine negli sprint 4 e 5 (22 dicembre - 8 gennaio)|Project Manager|
|Uso scorretto di Git, conflitti di merge|Media|Medio|Branch per issue, pull request obbligatorie, breve formazione iniziale|Project Manager|
|Cicli tra package nel codice|Media|Medio|Controllo con JDepend o Structure101 a fine sprint|Architetto|
|File StarUML o `.project` non committati|Bassa|Alto|Commit regolari, prova di clone pulito prima della consegna|Project Manager|

## 7. Staffing

Quattro persone con ruoli di responsabilità distinti, ma tutte coinvolte in modellazione, codice e test, per 36 ore ciascuna (4 ore a testa nello sprint 0 e 4 ore a settimana a testa negli sprint 1-4, pari a 9 settimane lavorative equivalenti da 4 ore).

|Ruolo|Responsabilità principali|Membro|
|-|-|-|
|Project Manager e Configuration Manager|Pianificazione, board, issue, release, ordine del repository, registro ore|Matteo Testa|
|Analista dei requisiti|Specifica dei requisiti, use case, scenari ed eccezioni|Alice Rota|
|Architetto|Architettura, modellazione UML (class, state machine, sequence, communication o timing, activity, component e package diagram), design pattern|Mattia Maggioni|
|Responsabile sviluppo e test|Implementazione, test JUnit, copertura, metriche|Alessandro Benigni|

I ruoli indicano chi risponde di un'area, non chi la svolge da solo: Ogni diagramma e ogni classe passano per la review di almeno un'altra persona.

Ogni membro deve dare un contributo significativo. A fine progetto ciascuno valuta in privato il proprio impegno e quello dei compagni, come previsto dal corso.

## 8. Methods and techniques

Usiamo i metodi visti a lezione per ogni attività, con UML come linguaggio comune tra requisiti, design e codice.

* **Requisiti:** Elicitazione e analisi del dominio dell'officina attraverso scenari d'uso dei tre attori, specifica secondo IEEE 830, definizione degli attributi di qualità del software (Software Quality), use case con scenario principale ed eccezioni; ogni flusso di eccezione (per esempio preventivo rifiutato, pezzo fuori produzione) diventa un flusso alternativo di uno use case, una transizione della state machine, un frammento `alt` del sequence diagram e almeno un test JUnit, con tracciabilità tra il Documento 3 (Requisiti) e il Documento 5 (Testing).
* **Modellazione:** Diagrammi UML realizzati con StarUML 5 (use case, class, state machine, sequence, communication o timing, activity, component e package diagram).
* **Generazione del codice:** Prima versione delle classi Java generata una sola volta dal class diagram con StarUML secondo un approccio MDA (Model-Driven Architecture) parziale, a modello stabile (fine sprint 2), per non sovrascrivere codice scritto a mano; poi completata a mano, compresi tutti i corpi dei metodi. La generazione ha un commit dedicato e il Documento 4 (Design) riporta l'elenco delle classi generate, con package e modifiche manuali successive.
* **Design:** Almeno due design pattern visti a lezione (tra cui i candidati State per la scheda di riparazione, Observer per le notifiche al cliente e Strategy per il calcolo dei costi).
* **Implementazione:** Java moderno, Maven, Log4j, database embedded, interfaccia solo a riga di comando (console).
* **Test:** JUnit per i test di unità, con misura di copertura; un test per ogni flusso alternativo degli use case, con l'elenco di test, scenario ed esito atteso nel Documento 5 (Testing).
* **Refactoring:** Attività svolte e documentate nel Documento 6 (Maintenance) durante lo sprint 4, con eventuali correzioni nello sprint 5.
* **Configuration management:** GitHub con issue, branch, pull request e code review.

## 9. Quality assurance

La qualità si controlla con revisioni sistematiche, test automatici e metriche, verificati a ogni fine sprint.

* **Revisioni:**

  * Ogni pull request è rivista da almeno un altro membro prima del merge.
  * Ogni diagramma UML è controllato con una checklist degli elementi visti a lezione.
  * Coerenza tra modelli, codice e documenti verificata a fine sprint.
* **Test:**

  * Test JUnit per le classi e per i flussi principali.
* **Criteri di accettazione di uno sprint:**

  * Le issue dello sprint sono chiuse o riassegnate con motivazione.
  * I documenti dello sprint sono aggiornati e committati.

## 10. Work packages

Il lavoro è diviso in sette work package, ciascuno con ore, sprint e responsabile; la somma delle ore per work package e per sprint coincide con il budget di 144 ore e con gli obiettivi della sezione 2.

* **WP1 – Gestione e setup** (14 ore: 10 nello sprint 0 e 4 di riepilogo negli sprint 1-4; Project Manager): Configurazione del repository, board, Project Plan, registro ore e riepiloghi di fine sprint. **Deliverable:** Documento 1 (Project Plan) e repository configurato.
* **WP2 – Requisiti** (22 ore: 6 nello sprint 0 e 16 nello sprint 1; Project Manager e Analista dei requisiti): Software Life Cycle, Configuration Management, People Management and Team Organization. Avvio ed elicitazione dei requisiti nello sprint 0; specifica secondo IEEE 830, definizione degli attributi di Software Quality, use case con scenari ed eccezioni nello sprint 1. **Deliverable:** Stesura iniziale del Documento 2 (Gestione del progetto, completato con i dati a consuntivo nello sprint 4), Documento 3 (Requisiti) e use case diagram.
* **WP3 – Modellazione UML** (35 ore: 15 nello sprint 1 e 20 nello sprint 2; Architetto): Class diagram (realizzato nello sprint 1 e consolidato nello sprint 2), state machine, sequence, communication o timing e activity diagram, prima generazione del codice da StarUML a fine sprint 2. **Deliverable:** Progetto StarUML con i diagrammi (distribuiti tra il Documento 3 (Requisiti) e il Documento 4 (Design)) e prime classi Java generate.
* **WP4 – Architettura e design** (30 ore: 11 nello sprint 2 e 19 nello sprint 3; Architetto): Prime viste architetturali nello sprint 2; component e package diagram, stile architetturale, design pattern e libreria esterna Maven nello sprint 3. **Deliverable:** Documento 4 (Design).
* **WP5 – Implementazione** (24 ore: 12 nello sprint 3 e 12 nello sprint 4; Responsabile sviluppo e test): Progetti Eclipse Maven, implementazione del nucleo nello sprint 3 e completamento del codice generato, database embedded, logging e interfaccia a riga di comando nello sprint 4. **Deliverable:** Codice funzionante dei flussi principali.
* **WP6 – Testing** (10 ore, sprint 4; Responsabile sviluppo e test): Test JUnit, misura di copertura, metriche sul codice. **Deliverable:** Suite di test e Documento 5 (Testing).
* **WP7 – Rifinitura e presentazione** (9 ore, sprint 4 con margine di completamento nello sprint 5; Project Manager): Refactoring documentato nel Documento 6 (Maintenance), completamento dei documenti finali, `README.md`, revisione incrociata, prova su clone pulito, presentazione e consegna entro l'8 gennaio. **Deliverable:** Documento 6 (Maintenance), repository pronto per la consegna e slide.

Ogni work package parte dai deliverable del precedente (requisiti, modelli, design, codice, test); gestione e rifinitura accompagnano l'intero progetto.

## 11. Resources

Le risorse sono quattro persone per 36 ore ciascuna e strumenti tutti gratuiti, quindi il budget si misura solo in ore.

* **Persone:** Quattro membri, 144 ore in totale (sezione 12).
* **Sviluppo:** JDK, Eclipse, Maven, JUnit, Log4j come dipendenza Maven, database embedded, interfaccia solo a riga di comando.
* **Modellazione:** StarUML 5, versione gratuita.
* **Collaborazione:** GitHub per repository, issue, project board, pull request e code review.
* **Analisi del codice:** Strumenti di metriche e copertura.
* **Hardware:** I computer personali dei membri e un PC dedicato per la demo all'esame orale.

## 12. Budget and schedule

Il budget è di 144 ore (36 a testa: 4 ore nello sprint 0 di quattro settimane e 4 ore a settimana per le 8 settimane degli sprint 1-4, dal 12 ottobre 2026 al 4 gennaio 2027, equivalenti a 9 settimane lavorative da 4 ore a testa) e oltre il 70% (101 ore su 144, pari al 70,1% nei WP1-WP4) va a gestione, requisiti, modellazione e design.

|Sprint|Periodo|WP1|WP2|WP3|WP4|WP5|WP6|WP7|Totale ore di team|
|-|-|-|-|-|-|-|-|-|-|
|Sprint 0|12 ottobre - 9 novembre 2026|10|6|—|—|—|—|—|16|
|Sprint 1|10 - 23 novembre 2026|1|16|15|—|—|—|—|32|
|Sprint 2|24 novembre - 7 dicembre 2026|1|—|20|11|—|—|—|32|
|Sprint 3|8 - 21 dicembre 2026|1|—|—|19|12|—|—|32|
|Sprint 4|22 dicembre 2026 - 4 gennaio 2027|1|—|—|—|12|10|9|32|
|Sprint 5|5 - 8 gennaio 2027|—|—|—|—|—|—|(compl.)|0|
|**Totale**||**14**|**22**|**35**|**30**|**24**|**10**|**9**|**144**|

* **Le ore sono una stima iniziale:** Le aggiorniamo a fine sprint con il registro ore. La forchetta di 30-40 ore del corso equivale a 3-4 ore per settimana lavorativa equivalente.
* **Il calendario proposto copre sei sprint:** I primi cinque (sprint 0-4, dal 12 ottobre 2026 al 4 gennaio 2027) portano il progetto quasi a compimento; lo sprint 4 (22 dicembre 2026 - 4 gennaio 2027) e lo sprint 5 (5-8 gennaio 2027, senza ore aggiuntive oltre al completamento del WP7) coprono le vacanze natalizie e garantiscono un margine finale per correzioni, revisione incrociata, prova su clone pulito e consegna.
* **La consegna interna è fissata all'8 gennaio 2027:** Il progetto completo va consegnato almeno cinque giorni prima della presentazione, quindi la presentazione non può cadere prima del 13 gennaio 2027.

## 13. Changes

Ogni modifica a requisiti, piano o documenti nasce come issue GitHub e passa da una pull request, così resta tracciata.

**Richieste di modifica**

1. **Proposta:** Chi propone apre un'issue con motivo, impatto su requisiti, modelli, codice e ore.
2. **Valutazione:** Il Project Manager, sentito il team, valuta la richiesta.
3. **Approvazione e tracciamento:** La modifica approvata viene implementata tramite un branch e rivista con pull request.

**Versioning dei documenti**

Il Project Plan ha una tabella delle versioni in testa: Ogni revisione aggiunge una riga con data e modifiche. I documenti nel repository sono versionati con Git, quindi la cronologia dei commit fa da storico.

## 14. Delivery

Consegniamo un repository GitHub completo e funzionante, con tutta la documentazione, almeno cinque giorni prima della presentazione.

**Cosa consegniamo**

* **Project Plan:** Approvato dal professore almeno un mese prima dell'esame.
* **Relazione finale in un unico PDF diviso in capitoli:** Documento 1 (Project Plan), Documento 2 (Gestione del progetto), Documento 3 (Requisiti), Documento 4 (Design), Documento 5 (Testing), Documento 6 (Maintenance).
* **Codice e test:** Progetti Eclipse Maven con codice, test JUnit e istruzioni per eseguirli.
* **Modelli UML:** File StarUML con tutti i diagrammi.

**Come**

* **Consegna su GitHub:** Tutto committato nel repository condiviso con i professori Gargantini e Bonfanti.
* **Correzioni post-consegna:** Dopo la consegna si possono correggere eventuali errori, senza creare nuovi documenti né nuovi progetti Java.

**Presentazione**

Il tempo a disposizione per quattro persone è di 35 minuti, più circa 10 per le domande. Portiamo un PC con il progetto in esecuzione per demo ed esecuzione dei test.

