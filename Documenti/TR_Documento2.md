# Documento 2

## 1\. Introduzione

Questo documento descrive come il team Tech Repair gestisce il progetto: processo iterativo e incrementale, Configuration Management su GitHub e organizzazione del team. È la prima versione, scritta nello sprint 1; i dati a consuntivo (ore effettive, statistiche di commit, PR e review, scostamenti dal piano) si aggiungono nello sprint 4.

Il Documento 2 dettaglia le regole già fissate nel Project Plan (Documento 1, versione 1.1.2 del 16/11/2026) senza modificarle: calendario, ore e ruoli sono gli stessi. Corrisponde al work package WP2 del piano, in carico al Project Manager e all'Analista dei requisiti.

**Versioni**

|Versione|Data|Modifiche|
|-|-|-|
|0.1|16/11/2026|Prima bozza: ciclo di vita, Configuration Management, organizzazione del team|
|0.2|16/11/2026|Allineamento ai capitoli 3, 4 e 5 del libro: confronto con altri modelli, schema del piano SCM, coordinamento e stili di gestione|
|1.0|17/11/2026|Correzione imprecisioni|

## 2\. Software Life Cycle

Il team segue un processo iterativo e incrementale ispirato a Scrum: sprint a durata fissa, ciascuno legato a una milestone GitHub, in cui requisiti e modelli vengono raffinati a ogni iterazione. Il progetto è quasi completo il 4 gennaio 2027 e la consegna è fissata all'8 gennaio 2027.

### 2.1 Perché questo modello

* **Team piccolo, poche ore.** Quattro persone con 36 ore ciascuna (144 ore di team) richiedono iterazioni brevi e verificabili.
* **Modelli prima del codice.** Il progetto chiede di spendere almeno il 70% del tempo su requisiti, modellazione e progettazione: nel piano i work package WP1-WP4 assorbono 101 ore su 144 (70,1%).

Il processo è *iterativo* perché lo stesso artefatto viene ripreso e migliorato in più sprint (il class diagram nasce nello sprint 1 ed è consolidato nello sprint 2). È *incrementale* perché ogni sprint aggiunge un deliverable nuovo: requisiti, poi modelli, design, codice, test. Rispetto a Scrum il piano non prevede Product Owner e Scrum Master né riunioni giornaliere: il coordinamento passa dalla riunione settimanale e dalla bacheca Kanban (sezione 3.6).

Il processo scelto è una combinazione. Non abbiamo usato per esempio il modello a cascata, poichè presuppone requisiti stabili e fasi che si chiudono una dopo l'altra, mentre qui requisiti e modelli si rivedono a ogni sprint in base al feedback. Un metodo agile puro evita il design anticipato, ma il corso chiede di dedicare almeno il 70% del tempo a requisiti, modelli e progettazione. Il processo adottato tiene quindi l'analisi e la modellazione UML in anticipo (la parte guidata dal piano) e consegna a incrementi con feedback frequente (la parte agile). Ha molti punti in comune con RUP: è iterativo, usa UML e parte dai casi d'uso. Con quattro persone, infine, un metodo leggero è adatto.

### 2.2 Sprint e milestone

Le ore sono di team (4 persone) e sommano a 144.

* **Sprint 0** · 12 ottobre - 9 novembre · 16 ore (quattro settimane a carico ridotto): setup del repository, Project Plan, avvio dei requisiti.
* **Sprint 1** · 10 - 23 novembre · 32 ore: specifica dei requisiti, use case diagram, class diagram.
* **Sprint 2** · 24 novembre - 7 dicembre · 32 ore: state machine, sequence, communication o timing e activity diagram; prima generazione del codice da StarUML con approccio MDA (Model-Driven Architecture); prime viste architetturali.
* **Sprint 3** · 8 - 21 dicembre · 32 ore: component e package diagram, design pattern, implementazione del nucleo.
* **Sprint 4** · 22 dicembre - 4 gennaio · 32 ore: implementazione completa, test JUnit, copertura e metriche, refactoring e documenti finali.
* **Sprint 5** · 5 - 8 gennaio · nessuna ora aggiuntiva: margine per correzioni, revisione incrociata, prova su clone pulito e consegna entro l'8 gennaio.

La consegna è fissata almeno cinque giorni prima della presentazione, che quindi non può cadere prima del 13 gennaio 2027.

Gli sprint sono dei time box: durata e data di consegna sono fisse e, se manca tempo, si riduce il perimetro e non la scadenza. Per questo si parte dai flussi principali e i requisiti del Documento 3 hanno una priorità (Alta o Media).

### 2.3 Ciclo di ogni sprint

1. **Pianificazione.** Il team sceglie le issue dello sprint e le assegna sulla board GitHub.
2. **Sviluppo e revisione.** Si lavora su branch dedicati, con pull request e code review (sezione 3.3).
3. **Chiusura.** Si verificano i criteri di accettazione e si fa una retrospettiva breve.

Uno sprint è accettato quando le sue issue sono chiuse o riassegnate con motivazione e i documenti dello sprint sono aggiornati e committati. A fine sprint il team verifica anche la coerenza tra modelli, codice e documenti e rivede il registro dei rischi del Project Plan.

### 2.4 Percorso critico e tracciabilità

Il percorso critico è: specifica dei requisiti (sprint 0-1), class diagram stabile (sprint 1-2), generazione del codice da StarUML (fine sprint 2), implementazione del nucleo e test (sprint 3-4). Per questo la generazione del codice avviene una sola volta, a modello stabile, con un commit dedicato: così non si sovrascrive codice scritto a mano.

Ogni flusso di eccezione (preventivo rifiutato, pezzo fuori produzione, richiesta di modifica del preventivo) diventa un flusso alternativo di uno use case, una transizione della state machine, un frammento `alt` del sequence diagram e almeno un test JUnit. Il legame tra Documento 3 (Requisiti) e Documento 5 (Testing) resta quindi verificabile a ogni sprint.

### 2.5 Evidenze di iterazione finora

* **Project Plan.** Quattro versioni in cinque settimane: 1.0.0 (12/10), 1.1.0 (09/11, riallineamento di date, ore e work package agli obiettivi per sprint), 1.1.1 (14/11, correzione del proprietario del repository, allineamento dei flussi di eccezione, chiarimento del WP2) e 1.1.2 (16/11, label solo per le issue importanti, Priority board, riunione del lunedì).
* **Requisiti.** Sette versioni tra l'11 e il 15 novembre (0.1-0.7), con correzioni dopo revisione (0.5), rimozione di classi che non sono entità salvate (0.6) e allineamento del modello del dominio al class diagram (0.7). I diagrammi UML sono ancora da inserire nel documento.
* **Sprint 0.** La scadenza era il 9 novembre, ma la milestone è ancora aperta (1 issue su 9 non chiusa al 16 novembre) perché si attende l'approvazione del Project Plan da parte del Prof. Gargantini. È il primo scostamento rispetto al calendario iniziale; date e ore sono state riallineate nella revisione 1.1.0 del piano.

### 2.6 Corrispondenza con Scrum, Kanban e MDA

Gli elementi del processo corrispondono a Scrum e Kanban:

* **Product Backlog:** la colonna *Backlog* della board, con tutte le issue e le idee.
* **Sprint e Sprint Backlog:** ogni sprint è una milestone GitHub con date e ore di team; le sue issue sono lo Sprint Backlog.
* **Sprint Planning:** il primo passo del ciclo di sprint (sezione 2.3).
* **Daily Scrum:** non previsto; lo sostituisce la riunione settimanale del lunedì.
* **Sprint Review e Retrospective:** a fine sprint si verificano i criteri di accettazione e si fa una retrospettiva breve; i docenti possono dare feedback tramite issue.
* **Ruoli:** non ci sono Product Owner e Scrum Master. Il Project Manager coordina e il ruolo del cliente è simulato dal team, perché non è stato possibile coinvolgere stakeholder reali (Documento 3, Parte A).
* **Kanban:** la board ha le colonne Backlog, Ready, In Progress, In Review e Done (sezione 3.6).

Il piano prevede inoltre un approccio MDA (Model-Driven Architecture) parziale. I requisiti e il modello del dominio del Documento 3 sono il livello più astratto (CIM, indipendente dal calcolo); il class diagram di design è il modello da cui StarUML genera una sola volta le classi Java; il resto, compresi i corpi dei metodi, si scrive a mano. Non è quindi un MDA completo: dopo la generazione la manutenzione avviene sul codice e non sul modello.

## 3\. Configuration Management

Tutto il lavoro passa da GitHub e nessuna modifica arriva su `main` senza issue, pull request e review: così ogni scelta resta tracciata e si vede chi ha fatto cosa. La sezione segue lo schema del piano di gestione della configurazione del capitolo 4 del libro (standard IEEE 828): gestione, attività (identificazione, controllo, contabilità dello stato e audit, sezioni 3.2-3.7), calendario, risorse e manutenzione del piano.

### 3.1 Gestione SCM: organizzazione, responsabilità e autorità

* **Configuration Manager.** Matteo Testa e Alessandro Benigni: tengono in ordine il repository, gestiscono board e issue, prenotano il file StarUML (sezione 3.4), valuta le richieste di modifica e approvano i merge.
* **Comitato di controllo.** In un progetto di quattro persone non c'è un Configuration Control Board separato: ne svolge la funzione il Project Manager, sentito il team. Se non è disponibile, l'approvazione di una PR può darla un altro membro, diverso dall'autore, scelto per competenza.
* **Tutti i membri.** Ognuno lavora su branch propri e modifica la baseline solo con una pull request.
* **Docenti.** Il Prof. Gargantini e la Prof.ssa Bonfanti hanno accesso completo al repository e possono dare approvazioni e feedback tramite GitHub.

### 3.2 Identificazione degli elementi di configurazione e baseline

Gli elementi di configurazione sono i sei documenti del progetto, il file StarUML con i diagrammi, il codice e i progetti Maven, i test JUnit e il `README.md`. Il ramo `main` è la baseline: contiene solo elementi approvati, cioè rivisti con una pull request, e si modifica solo con la procedura di controllo delle sezioni 3.3-3.5. Su di esso vengono fatte direttamente solo modifiche minime che non necessitano di review.

Il repository è organizzato così:

* **Proprietà e accessi.** Creato dall'account `matteetesta` e condiviso con il Prof. Gargantini (`garganti`) e la Prof.ssa Bonfanti (`silviabonfanti`).
* **Root e cartelle.** La root si chiama `TechRepair` e non contiene file di configurazione dell'IDE (per esempio `.project`). `Documenti/` contiene la documentazione ufficiale e il file StarUML; `Codice/` contiene i progetti Eclipse.
* **Progetti Eclipse.** Sono progetti Maven con nomi caratteristici, mai generici. I file `.project`, `.classpath` e `pom.xml` vanno sempre committati, perché tengono allineato l'ambiente di sviluppo.
* **`.gitignore`.** Esclude i file `.class` e la cartella `target/`.
* **Portabilità.** Niente percorsi assoluti e database embedded, così il repository funziona su qualsiasi postazione.

### 3.3 Controllo: dall'issue al merge

1. **Issue.** Ogni attività parte da un'issue con milestone e assegnatario. Le label sono riservate alle issue di particolare importanza: quelle in uso sono *documentation*, *enhancement*, *bug* e *Critical Section* (quest'ultima per le issue che modificano il file StarUML, sezione 3.4).
2. **Branch.** Si crea dall'interfaccia web dell'issue, accettando il nome automatico proposto da GitHub.
3. **Commit.** Piccoli e frequenti, con titolo esplicativo ed eventuale descrizione; i messaggi generici sono vietati.
4. **Pull request.** Una per ogni branch, collegata in automatico all'issue di origine. L'issue passa a *In Review*.
5. **Review e approvazione.** Almeno un altro membro rivede la PR. Per il merge serve l'approvazione del Project Manager; se non è disponibile, la dà un membro diverso dall'autore, scelto per competenza sul task.
6. **Merge.** Solo dopo l'approvazione, con *Create a merge commit* e non con lo squash, per conservare la cronologia dei singoli commit. L'issue passa a *Done*.

Il push diretto su `main` è ammesso in caso di modifiche minime che non richiedono una review. La cronologia intera serve anche a fine progetto: il Documento 2 riporterà commit, PR aperte e review per ciascun componente.

### 3.4 Controllo: il file StarUML (`.mdj`)

Il file StarUML è unico e non si può unire con un merge testuale: serve quindi un blocco esclusivo (lock), come per gli elementi in modifica descritti nel libro. Ci lavora una sola persona alla volta.

* Chi vuole modificarlo lo prenota tramite il Project Manager, che contrassegna l'issue con la label *Critical Section*.
* Nessun altro tocca il modello finché quell'issue non passa a *In Review*.
* Prima di aprire StarUML si esegue sempre `git pull`.

La regola copre due rischi del registro: conflitti di merge e perdita di lavoro sul modello. Ha un costo: due issue che toccano il modello non possono essere in lavorazione insieme, e le issue sui diagrammi vanno pianificate in sequenza.

### 3.5 Controllo: richieste di modifica

Ogni modifica a requisiti, piano o documenti nasce come issue nella milestone attuale e passa da una pull request.

1. **Proposta.** Chi propone apre un'issue con il motivo e l'impatto su requisiti, modelli, codice e ore.
2. **Valutazione.** Il Project Manager, sentito il team, valuta la richiesta: può approvarla, rifiutarla (avvisando chi l'ha proposta) o rinviarla in attesa di più informazioni.
3. **Approvazione e tracciamento.** La modifica approvata si implementa su un branch e si rivede con pull request.

### 3.6 Contabilità dello stato: la board su GitHub Projects

Lo stato di ogni attività è sempre visibile sulla bacheca, condivisa con il team e con i docenti, che ha sei viste:

* **ToDo board:** Backlog, Ready, In Progress, In Review, Done.
* **Team items:** tutte le issue con assegnatari, divise per milestone.
* **My items:** le sole issue dell'utente, raggruppate per milestone.
* **Roadmap:** le issue lungo una linea temporale.
* **Registro ore:** ore stimate ed effettive di ogni issue, per milestone e assegnatario.
* **Priority board:** le issue divise per priorità; per ora nessuna issue ha una priorità assegnata.

Ogni settimana la riunione di allineamento verifica lo stato dei task e ridistribuisce le attività in caso di colli di bottiglia. La cronologia dei commit e delle PR completa il quadro: dice quali modifiche sono state fatte, da chi e perché.

### 3.7 Audit e verifiche

* Ogni pull request è rivista da almeno un altro membro prima del merge.
* Ogni diagramma UML è controllato con una checklist degli elementi visti a lezione.
* A fine sprint si verifica la coerenza tra modelli, codice e documenti.
* Prima della consegna si fa una prova su clone pulito: si clona il repository su un altro PC con JDK e si avvia il progetto.
* A fine progetto si riportano le statistiche di commit, PR e review per componente (sezione 5).

### 3.8 Calendario SCM

Le attività di configurazione seguono il ritmo degli sprint:

* **Inizio sprint:** le issue dello sprint sono assegnate sulla board e legate alla milestone.
* **Durante lo sprint:** ogni attività passa da branch, pull request e review.
* **Chiusura sprint:** le issue sono chiuse o riassegnate con motivazione, i documenti dello sprint sono aggiornati e committati e la coerenza tra modelli, codice e documenti è verificata.
* **Fine sprint 2:** il codice si genera da StarUML una sola volta, a modello stabile, con un commit dedicato, per non sovrascrivere codice scritto a mano.
* **Sprint 5 (5-8 gennaio 2027):** prova su clone pulito, revisione incrociata e consegna entro l'8 gennaio.

### 3.9 Risorse SCM

* **Strumenti.** Git è un sistema di controllo delle versioni distribuito: ogni membro ha una copia completa della storia e può lavorare anche senza connessione. GitHub ospita il repository remoto, le milestone, le issue, le pull request e la board. Maven costruisce il progetto, StarUML 5 produce i modelli, Eclipse è l'IDE.
* **Funzioni coperte.** GitHub svolge le funzioni di uno strumento SCM: archiviazione e versioni (repository e commit), rami (branch), audit (storia di commit e PR), tracciabilità (issue, branch e PR collegati), gestione del processo (board Kanban) e supporto al team (review e label *Critical Section*). La costruzione del sistema è affidata a Maven.
* **Persone.** I quattro membri del team.

### 3.10 Manutenzione del piano di gestione della configurazione

Questa sezione si aggiorna come ogni altro documento: una modifica nasce come issue e passa da una pull request (sezione 3.5). I documenti con tabella versioni in testa (Project Plan, Requisiti, questo documento) aggiungono una riga a ogni revisione, con data e modifiche; la storia di Git fa da storico fine. La consegna finale è un unico PDF diviso in capitoli con i sei documenti, con le immagini dei diagrammi UML esportate nel testo. Dopo la consegna si correggono eventuali errori, senza creare nuovi documenti né nuovi progetti Java.

## 4\. People management e organizzazione del team

Il team ha quattro persone, ciascuna con un'area di responsabilità, e lavora come una squadra agile senza gerarchia rigida: le decisioni si prendono per consenso e, se il consenso manca, decide il Project Manager motivando la scelta.

### 4.1 Ruoli

|Membro|Ruolo|Responsabilità principali|
|-|-|-|
|Matteo Testa|Project Manager e Configuration Manager|Pianificazione, board, issue, release, ordine del repository, registro ore|
|Alice Rota|Analista dei requisiti|Specifica dei requisiti, use case, scenari ed eccezioni|
|Mattia Maggioni|Architetto|Architettura, modellazione UML (class, state machine, sequence, communication o timing, activity, component e package diagram), design pattern|
|Alessandro Benigni|Responsabile sviluppo e test|Implementazione, test JUnit, copertura, metriche|

Un ruolo indica chi risponde di un'area, non chi la svolge da solo: tutti partecipano a modellazione, codice e test, e ogni diagramma e ogni classe passano per la review di almeno un'altra persona. I ruoli sono anche i responsabili dei rischi del Project Plan (per esempio l'Architetto per la sottostima del tempo di modellazione, il Project Manager per il carico non uniforme).

### 4.2 Stile di gestione e decisioni

Il piano indica come meccanismo di coordinamento l'adattamento reciproco: i membri si coordinano parlandosi e adattandosi, senza supervisione diretta né procedure rigide. Lo stile di gestione è quello orientato alla relazione e all'integrazione: le decisioni operative e architetturali si prendono per consenso nel team. È una scelta adatta a un lavoro innovativo e interdipendente come la modellazione, ma ha due punti deboli, che il team contrasta così:

* Se non si raggiunge un accordo entro la riunione settimanale, la decisione finale spetta al Project Manager, che la motiva con un commento nell'issue di riferimento. La riunione ha un orario fisso (14:00 - 16:00).
* Gli obiettivi del progetto, in ordine di priorità, sono scritti nel Project Plan e valgono per tutti; le issue sono assegnate esplicitamente e le ore si registrano per issue.

Così nessuna discussione resta bloccata e la motivazione rimane consultabile.

Tra le forme di organizzazione del team, la più vicina è il team agile: poche persone, canali di comunicazione brevi, nessuna gerarchia rigida e revisioni tra pari. Un team agile ha bisogno di autodisciplina, che qui è sostenuta dalle regole di GitHub (issue assegnate, review, merge approvato).

### 4.3 Comunicazione

* **GitHub** è il canale ufficiale ed esclusivo per tracciare decisioni, richieste di modifica e code review.
* **Riunione settimanale:** allinea lo stato dei task sulla bacheca Kanban e pianifica gli obiettivi successivi. Si tiene ogni lunedì dalle 14:00 alle 16:00.
* **Chat di gruppo:** solo per comunicazioni rapide e urgenze, senza validità decisionale. Una decisione presa in chat vale quindi solo dopo che è stata riportata in un'issue.

### 4.4 Interfacce esterne

I docenti di riferimento, Prof. Gargantini e Prof.ssa Bonfanti, hanno accesso completo al repository e comunicano con il team tramite GitHub. Il Project Plan deve essere approvato dal professore almeno un mese prima della data d'esame; poiché la presentazione non può cadere prima del 13 gennaio 2027, l'approvazione serve entro metà dicembre.

### 4.5 Impegno orario e carico

Ogni membro dedica 36 ore tra il 12 ottobre 2026 e il 4 gennaio 2027: 4 ore nello sprint 0 e 4 ore a settimana per le 8 settimane degli sprint 1-4.

Il carico dipende anche dagli esami degli altri insegnamenti, che sono il rischio più probabile del piano. Le contromisure sono un calendario condiviso, il carico ridotto dello sprint 0 e il margine finale degli sprint 4 e 5. Per evitare che il lavoro si concentri su pochi, le issue sono assegnate esplicitamente, le ore si registrano per issue e il carico si controlla ogni settimana.

### 4.6 Contributo individuale

Ogni membro deve dare un contributo significativo. A fine progetto ciascuno valuta in privato il proprio impegno e quello dei compagni, come previsto dal corso; le statistiche oggettive per componente (commit, PR, review) si riporteranno in questo documento nello sprint 4.

Le statistiche si leggono insieme al registro ore e non diventano una misura di produttività.

## 5\. Dati a consuntivo

Questa sezione si compila a fine progetto (sprint 4): le ore stimate vengono dal Project Plan, le altre colonne dal registro ore e dalle statistiche di GitHub.

### 5.1 Ore per sprint e per membro

La stima per ciascun membro è di 4 ore nello sprint 0 e 8 ore in ciascuno degli sprint 1-4, per un totale di 36 ore.

|Sprint|Periodo|Ore stimate (team)|Testa|Rota|Maggioni|Benigni|Ore effettive (team)|
|-|-|-|-|-|-|-|-|
|Sprint 0|12 ott - 9 nov|16|8.30|0|0|4.30|13|
|Sprint 1|10 - 23 nov|32|10|9|5|10|34|
|Sprint 2|24 nov - 7 dic|32||||||
|Sprint 3|8 - 21 dic|32||||||
|Sprint 4|22 dic - 4 gen|32||||||
|Sprint 5|5 - 8 gen|0||||||
|**Totale**||**144**||||||

### 5.2 Contributo di ciascun componente

|Membro|Ruolo|Ore effettive|Commit|PR aperte|Review effettuate|
|-|-|-|-|-|-|
|Matteo Testa|Project Manager e Configuration Manager|||||
|Alice Rota|Analista dei requisiti|||||
|Mattia Maggioni|Architetto|||||
|Alessandro Benigni|Responsabile sviluppo e test|||||
|**Totale**||||||

### 5.3 Scostamenti dal piano

|Sprint|Scostamento rispetto al piano|Causa|Azione|
|-|-|-|-|
|Sprint 0|Scadenza del 9 novembre superata: 1 issue su 9 ancora aperta al 16/11|Attesa dell'approvazione del Project Plan da parte del Prof. Gargantini|Date, ore e work package riallineati nel Project Plan 1.1.0; milestone da chiudere dopo l'approvazione del piano|
|Sprint 1|Chiuso il 18/11, cinque giorni prima della scadenza del 23/11|Lavoro dello sprint completato in anticipo|Sprint 2 avviato subito, senza modificare il Project Plan|
|Sprint 2|Avvio anticipato dal 24/11 al 18/11, con scadenza del 7/12 e 32 ore invariate|Sprint 1 concluso prima|Tempo extra usato come margine per il blocco del file StarUML|
|Sprint 3||||
|Sprint 4||||
|Sprint 5||||



