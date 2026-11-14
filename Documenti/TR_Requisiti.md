# Tech Repair – Elicitazione dei requisiti e Specifica dei requisiti (IEEE 830)

*Versione 0.5 – Team Tech Repair, Corso di Ingegneria del Software AA 2026/27.*

| Versione | Data       | Modifiche                                                                                                     |
|----------|------------|---------------------------------------------------------------------------------------------------------------|
| 0.1      | 11/11/2026 | Prima bozza                                                                                                   |
| 0.2      | 12/11/2026 | Prime modifiche                                                                                               |
| 0.3      | 14/11/2026 | Revisione completa di elicitazione, requisiti database, attributi di qualità e modello dati                   |
| 0.4      | 14/11/2026 | Allineamento alla Specifica Logica (catalogo ricambi, giacenze ordini, notifica riparazione, tracciabilità)   |
| 0.5      | 14/11/2026 | Correzioni dopo revisione: notifica del preventivo, richiesta di verifica al magazzino, Consultazione, RF-05, RF-07, RF-18, Gestisci Catalogo Ricambi |

# Parte A – Elicitazione dei requisiti

## A.1 Obiettivo e approccio

Obiettivo dell'elicitazione è capire che cosa deve fare Tech Repair per supportare il percorso di un dispositivo in riparazione, dall'accettazione in officina alla consegna e al pagamento. Il dominio è quello di una piccola officina di riparazione di dispositivi, con un passaggio di consegne continuo tra cliente e tecnico.

L'approccio è basato su **scenari d'uso** dei tre attori, affiancati da analisi del dominio e revisione incrociata nel team. Le tecniche e le fonti usate sono:

- **Analisi del dominio e dei documenti:** ricostruzione del flusso tipico di un'officina (ricevuta di ingresso, scheda di lavoro, preventivo, ordine ricambi, ricevuta di pagamento) a partire dai requisiti del corso, dal Project Plan e dall'esperienza diretta dei membri del team come utenti di centri di assistenza.

- **Scenari e use case:** per ogni attore si descrivono situazioni concrete, con scenario principale e flussi alternativi.

- **Revisione incrociata (walkthrough):** gli use case sono riletti da almeno un altro membro prima di bloccare la specifica, come previsto dal Project Plan (rischio "requisiti ambigui").

Non è stato possibile coinvolgere gli stakeholder reali (ad esempio il titolare o un tecnico di un'officina). I ruoli di Cliente, Tecnico Riparatore e Responsabile Magazzino sono stati simulati dal team.

## A.2 Stakeholder e attori

| Stakeholder / attore   | Interesse                                              | Esigenze principali                                                                                                                |
|------------------------|--------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|
| Cliente                | Riavere il dispositivo funzionante a costo noto        | Ricevuta di ingresso, preventivo chiaro con possibilità di chiedere modifiche, approvare o rifiutare, essere avvisato dell'esito, pagamento o rimborso registrato |
| Tecnico Riparatore     | Lavorare in modo ordinato sulle riparazioni            | Ricerca cliente e dispositivo, scheda di riparazione con stato aggiornato, inserimento di diagnosi e costi, richiesta di ricambi   |
| Responsabile Magazzino | Avere ricambi disponibili senza fermare le riparazioni | Gestione catalogo, verifica della reperibilità dei ricambi esauriti, ordine dei pezzi mancanti, aggiornamento delle giacenze       |

## A.3 Scenari raccolti

**S1 – Riparazione completata (scenario principale).** Il cliente porta il dispositivo in officina; il tecnico cerca il cliente col codice fiscale, registra il dispositivo e il guasto, incassa i 10 € anticipati di diagnosi e il sistema crea la scheda e genera la ricevuta di ingresso. Il tecnico diagnostica il guasto e inserisce i costi (impegnando i ricambi disponibili); il preventivo è inviato al cliente, che lo approva tramite il tecnico. Il tecnico esegue la riparazione e la chiude; alla consegna il sistema registra il pagamento del saldo (scontando i 10 € iniziali, con spesa minima di 10 €).

**S2 – Pezzo non in magazzino.** Durante la composizione del preventivo il tecnico rileva che serve un ricambio esaurito. Il tecnico richiede la verifica al Responsabile Magazzino, che conferma che il pezzo è ordinabile; il tecnico invia quindi il preventivo al cliente. Dopo l'approvazione del preventivo, il Responsabile Magazzino ordina il pezzo; la riparazione prosegue all'arrivo del ricambio.

**S3 – Preventivo modificato o rifiutato.** Prima dell'approvazione il cliente può chiedere al tecnico di modificare il preventivo (voci o qualità originale/compatibile). Se il cliente rifiuta il preventivo (o rinuncia prima), i ricambi impegnati vengono rilasciati, la scheda passa ad "Annullata" e viene trattenuto solo il costo di diagnosi (10 €). Il dispositivo è riconsegnato non riparato.

**S4 – Pezzo fuori produzione.** Il ricambio non è più reperibile: il sistema rilascia eventuali ricambi impegnati, notifica al cliente l'impossibilità di riparare, la scheda passa a "Non riparabile" e alla riconsegna viene rimborsato il costo di diagnosi (10 €).

## A.4 Dal fabbisogno ai requisiti

Ogni scenario è scomposto in affermazioni verificabili ("il sistema deve…"), classificate in requisiti funzionali, requisiti del database, vincoli e attributi di qualità, e numerate nella Parte B. Gli use case principali sono:

- **Accetta Dispositivo e Crea Scheda** (include *Genera Ricevuta di Ingresso*)

- **Invia Preventivo** (extend *Ordina Pezzo di Ricambio* se il pezzo non è in magazzino)

- **Consegna Dispositivo Riparato** (include *Registra Pagamento*)

Ogni flusso di eccezione (preventivo rifiutato, pezzo fuori produzione, richiesta di modifica del preventivo) diventa un flusso alternativo di uno use case, una transizione della state machine, un frammento alt del sequence diagram e almeno un test JUnit, con tracciabilità verso il Documento 5 (Testing).

## A.5 Questioni aperte e decisioni del team

- **La diagnosi ha un costo fisso?** Sì, importo fisso di 10 € pagato sempre in anticipo all'accettazione; viene scontato dal totale se il preventivo è approvato (con spesa minima complessiva di 10 €), trattenuto se il cliente rifiuta o rinuncia, oppure rimborsato (-10 €) se il ricambio è fuori produzione.

- **Possono esserci più schede attive sullo stesso dispositivo?** No, un cliente può avere più dispositivi, ma ogni dispositivo può avere al massimo una scheda attiva (in stato diverso da chiusa) alla volta.

- **Il preventivo può essere salvato in bozza o modificato?** Il preventivo (anche di sola manodopera) può essere salvato in bozza e completato in seguito. Prima dell'approvazione, il cliente può chiedere aggiunta o rimozione di voci e scelta di qualità (originale o compatibile); dopo l'approvazione non è più modificabile.

- **Quando vengono scalate le scorte e ordinati i ricambi?** I ricambi disponibili (`giacenza > 0`) vengono impegnati (`-1`) subito all'inserimento nel preventivo e rilasciati (`+1`) se rimossi o se il preventivo viene rifiutato. I ricambi esauriti (`giacenza = 0`) vengono prima verificati dal Responsabile Magazzino come ordinabili e ordinati solo dopo l'approvazione del cliente.

- **Il cliente usa la console?** No, il cliente non usa mai la console ma comunica col tecnico che registra le operazioni. Resta uno dei tre attori, ma esterno e passivo (riceve notifiche simulate).

- **Cosa succede ai dispositivi non ritirati?** La gestione dei dispositivi non ritirati (vendita come ricondizionati) è esclusa dal perimetro del progetto.

# Parte B – Specifica dei requisiti software (struttura IEEE 830)

## 1. Introduzione

### 1.1 Scopo

Questo documento specifica i requisiti del sistema **Tech Repair** secondo lo standard IEEE 830 (*Recommended Practice for Software Requirements Specifications*). È destinato al team di sviluppo (progettazione, implementazione, test) e ai docenti che valutano il progetto.

### 1.2 Ambito del prodotto

Tech Repair gestisce il percorso di un dispositivo in riparazione: accettazione, creazione della scheda, diagnosi e preventivo, eventuale ordine di ricambi, riparazione, consegna e pagamento.

**Fuori ambito:** pagamenti reali, invio reale di email o SMS (le notifiche sono simulate), contabilità, interfaccia grafica, autenticazione con password, gestione dei dispositivi non ritirati.

### 1.3 Definizioni, acronimi e abbreviazioni

| Termine            | Significato                                                                                                                                                            |
|--------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Scheda             | Scheda di riparazione associata a un dispositivo e al cliente                                                                                                          |
| Preventivo         | Stima dei costi di riparazione (manodopera e ricambi) proposta al cliente                                                                                              |
| Costo di diagnosi  | Importo fisso (10 €) pagato all'accettazione; scontato dal totale del preventivo se approvato, trattenuto se rifiutato o rimborsato se il ricambio è fuori produzione. |
| Ricambio           | Pezzo necessario per la riparazione (originale o compatibile), con prezzo fisso e giacenza in magazzino                                                                |
| Ricambio impegnato | Ricambio disponibile in magazzino la cui giacenza viene decrementata e riservata per una scheda fin dalla composizione del preventivo                                  |

### 1.4 Riferimenti

- IEEE Std 830-1998, *Recommended Practice for Software Requirements Specifications*

- Requisiti per il progetto di ingegneria del software AA 2026/27 (Gargantini, Bonfanti)

- Documento 1 – Project Plan di Tech Repair

- Documento interno – Specifica Logica e Funzionale di Tech Repair

- Materiale del corso

### 1.5 Panoramica del documento

La sezione 2 descrive il prodotto in generale; la sezione 3 elenca i requisiti specifici (interfacce, funzionali raggruppati per funzione, prestazioni, requisiti logici del database, vincoli, attributi di qualità, altri requisiti); la sezione 4 descrive gli use case; la sezione 5 presenta il modello del dominio; la sezione 6 riporta la tracciabilità tra use case e requisiti.

## 2. Descrizione generale

### 2.1 Prospettiva del prodotto

Tech Repair è un'applicazione autonoma a riga di comando (console) con database embedded, senza dipendenze da servizi esterni da installare. Gestisce il passaggio di consegne tra cliente, tecnico e magazzino attraverso una scheda di riparazione che attraversa diversi stati.

### 2.2 Funzioni del prodotto

- **Accettazione:** cercare o registrare cliente e dispositivo, creare la scheda, registrare il pagamento anticipato della diagnosi e generare la ricevuta.

- **Diagnosi e preventivo:** registrare la diagnosi e i costi (anche in bozza), inviare o modificare il preventivo, registrare l'approvazione o il rifiuto del cliente.

- **Ricambi:** impegnare i pezzi disponibili, verificare la reperibilità dei pezzi mancanti e ordinarli dopo l'approvazione del preventivo.

- **Consegna e pagamento:** consegnare il dispositivo e registrare il pagamento del saldo o il rimborso.

- **Gestione delle eccezioni:** preventivo modificato o rifiutato, rinuncia anticipata, ricambio fuori produzione.

### 2.3 Caratteristiche degli utenti

| Utente                 | Competenze                                           | Frequenza d'uso |
|------------------------|------------------------------------------------------|-----------------|
| Cliente                | Nessuna competenza informatica richiesta             | No              |
| Tecnico Riparatore     | Uso di base di una console, conoscenza del dominio   | Quotidiana      |
| Responsabile Magazzino | Uso di base di una console, conoscenza del magazzino | Quotidiana      |

### 2.4 Vincoli generali

- Linguaggio Java moderno, progetto Maven, logging con Log4j.

- Database embedded; nessun percorso assoluto nel codice.

- Interfaccia a riga di comando.

- Modellazione UML con StarUML e rispetto delle regole di repository e documentazione del Project Plan.

### 2.5 Assunzioni e dipendenze

- Le notifiche al cliente sono simulate (ad esempio messaggi su console o log).

- Il pagamento è registrato nel sistema ma non eseguito realmente.

- Il sistema è usato da un operatore alla volta, in un'unica sede.

- Tutti i ricambi selezionabili sono pre-censiti a catalogo dal Responsabile Magazzino e il loro prezzo non cambia nel tempo.

- Un ricambio verificato come ordinabile resta ordinabile fino alla decisione del cliente sul preventivo.

## 3. Requisiti specifici

### 3.1 Interfacce esterne

| ID    | Requisito                                                                                                                                                                                                           |
|-------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| RI-01 | Il sistema deve offrire un'interfaccia a riga di comando con menu iniziale di scelta ruolo (senza password) per il Tecnico Riparatore e il Responsabile Magazzino; il Cliente non interagisce direttamente con il sistema. |
| RI-02 | Il sistema deve persistere i dati in un database embedded, senza richiedere servizi esterni.                                                                                                                        |
| RI-03 | Le notifiche al cliente devono essere simulate (messaggio su console e registrate in un log applicativo) e non inviate realmente.                                                                                   |

### 3.2 Requisiti funzionali

**Accettazione (Use Case: Accetta Dispositivo e Crea Scheda)**

| ID    | Requisito                                                                                                                                                                                                                                            | Attore  | Priorità |
|-------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|----------|
| RF-01 | Il sistema deve permettere al tecnico di cercare il cliente per codice fiscale e, se nuovo, registrarne i dati (nome, cognome, codice fiscale, telefono, email), e di selezionare o registrare il suo dispositivo (seriale univoco, marca, modello). | Tecnico | Alta     |
| RF-02 | Il sistema deve creare una scheda di riparazione con identificativo univoco, data di ingresso, guasto dichiarato e stato iniziale, rifiutando l'apertura se il dispositivo ha già una scheda attiva (in stato diverso da chiusa).                     | Tecnico | Alta     |
| RF-03 | Il sistema deve registrare il pagamento anticipato del costo di diagnosi (10 €) e generare la ricevuta di ingresso contenente identificativo della scheda, dati del cliente e del dispositivo.                                                       | Tecnico | Alta     |

**Diagnosi e preventivo (Use Case: Invia Preventivo)**

| ID    | Requisito                                                                                                                                                                                         | Attore  | Priorità |
|-------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|----------|
| RF-04 | Il sistema deve permettere al tecnico di registrare la diagnosi del guasto sulla scheda.                                                                                                          | Tecnico | Alta     |
| RF-05 | Il sistema deve permettere al tecnico di inserire o modificare (solo prima dell'approvazione) il preventivo con costi di manodopera ed eventuali ricambi (originali o compatibili), anche in bozza, calcolandone il totale. Se in un preventivo già inviato viene inserito un ricambio esaurito (`giacenza = 0`), il sistema deve riportare il preventivo in bozza e la scheda in verifica magazzino, senza richiedere nuova verifica per i ricambi già verificati come ordinabili e mantenuti. | Tecnico | Alta     |
| RF-06 | Il sistema deve rendere il preventivo disponibile al cliente (quando tutti i ricambi indicati sono impegnati o verificati come ordinabili), registrarne l'invio e notificarlo al cliente (anche in caso di modifica).                                 | Tecnico | Alta     |
| RF-07 | Il sistema deve permettere al tecnico di registrare la decisione del cliente di approvare o rifiutare il preventivo, memorizzandola con la data.                                          | Tecnico | Alta     |
| RF-08 | Il sistema deve bloccare modifiche al preventivo dopo l'approvazione e consentire la prosecuzione della riparazione solo dopo l'approvazione (e l'arrivo di eventuali ricambi da ordinare).       | Tecnico | Alta     |

**Ricambi (Use Case: Ordina Pezzo di Ricambio, extend di Invia Preventivo)**

| ID    | Requisito                                                                                                                                                                                                | Attore                 | Priorità |
|-------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------|----------|
| RF-09 | Il sistema deve verificare la disponibilità in magazzino dei ricambi inseriti nel preventivo, impegnando subito (`-1`) quelli disponibili e richiedendo la verifica del magazzino alla richiesta del tecnico per quelli esauriti. | Sistema                | Alta     |
| RF-10 | Se un ricambio è esaurito, il sistema deve permettere al Responsabile Magazzino di verificarne la reperibilità (ordinabile o fuori produzione) e, solo dopo l'approvazione del preventivo, registrare l'ordine di acquisto. | Responsabile Magazzino | Media    |
| RF-11 | Alla ricezione di un ordine, il sistema deve assegnare i ricambi direttamente alla scheda collegata (da "da ordinare" a "impegnato", senza alterare la giacenza libera) e ripristinare la giacenza (`+1`) dei soli ricambi impegnati in caso di rimozione, modifica, rifiuto o rinuncia. | Responsabile Magazzino | Media    |

**Riparazione, consegna e pagamento (Use Case: Consegna Dispositivo Riparato)**

| ID    | Requisito                                                                                                                                                                                           | Attore  | Priorità |
|-------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|----------|
| RF-12 | Il sistema deve permettere al tecnico di chiudere la riparazione (aggiornando eventualmente il seriale univoco del dispositivo), portando la scheda allo stato "Riparata" e notificando il completamento al cliente. | Tecnico | Alta     |
| RF-13 | Il sistema deve permettere di consegnare il dispositivo al cliente solo per schede nello stato “Riparata”, “Annullata” o “Non riparabile”.                                                          | Tecnico | Alta     |
| RF-14 | Alla riconsegna, il sistema deve calcolare e registrare con data il saldo come `max(0 €, totale preventivo - 10 €)` se "Riparata", `0 €` se "Annullata" o il rimborso di `-10 €` se "Non riparabile", e chiudere la scheda. | Tecnico | Alta     |

**Flussi di eccezione**

| ID    | Requisito                                                                                                                                                                                                          | Attore  | Priorità |
|-------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|----------|
| RF-15 | Se il cliente rifiuta il preventivo (o rinuncia prima dell'approvazione), il sistema deve portare la scheda allo stato "Annullata", rilasciare i ricambi impegnati (`+1`) e trattenere il solo costo di diagnosi. | Tecnico | Alta     |
| RF-16 | Se il ricambio è fuori produzione, il sistema deve rilasciare eventuali ricambi impegnati (`+1`), notificare al cliente l'impossibilità di riparare e portare la scheda nello stato “Non riparabile”.              | Sistema | Alta     |

**Consultazione**

| ID    | Requisito                                                                                                                                                           | Attore                           | Priorità |
|-------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------|----------|
| RF-17 | Il sistema deve permettere di consultare lo stato e lo storico di una scheda (per identificativo, codice fiscale, seriale o stato) e l'inventario dei ricambi.      | Tecnico / Responsabile Magazzino | Media    |

**Validazione e catalogo ricambi**

| ID    | Requisito                                                                                                                                                                                                   | Attore                 | Priorità |
|-------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------|----------|
| RF-18 | Il sistema deve validare gli input rifiutando: campi obbligatori vuoti; codici fiscali non di 16 caratteri alfanumerici o già presenti; telefoni non numerici; email non valide; seriali duplicati; codici ricambio duplicati; prezzi unitari non maggiori di zero; costi di manodopera o giacenze negativi; manodopera nulla quando non ci sono ricambi. | Sistema                | Alta     |
| RF-19 | Il sistema deve permettere al Responsabile Magazzino di inserire nuovi ricambi a catalogo (codice, nome, qualità originale/compatibile, prezzo unitario fisso, giacenza iniziale e reperibilità).           | Responsabile Magazzino | Media    |

### 3.3 Requisiti di prestazione

| ID    | Requisito                                                                                                                               |
|-------|-----------------------------------------------------------------------------------------------------------------------------------------|
| RP-01 | Non sono previsti requisiti di prestazione stringenti oltre al funzionamento interattivo su un PC standard con un operatore alla volta. |

### 3.4 Requisiti logici del database

| ID    | Requisito                                                                                                                                                                   |
|-------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| RD-01 | Il database deve persistere le entità Cliente, Dispositivo, Scheda di riparazione, Preventivo, Ricambio, Ordine di acquisto e Pagamento mantenendo le relazioni gerarchiche. |
| RD-02 | Il codice fiscale del cliente e il seriale del dispositivo devono essere univoci; ogni dispositivo può avere uno storico di schede ma al massimo una scheda attiva.         |
| RD-03 | Il preventivo deve distinguere i ricambi fisicamente impegnati da magazzino da quelli esauriti da ordinare, e gli importi monetari devono essere memorizzati con due decimali. |

### 3.5 Vincoli di progetto

| ID    | Requisito                                                                                                      |
|-------|----------------------------------------------------------------------------------------------------------------|
| RV-01 | Il codice deve essere in Java con Maven; i test con JUnit.                                                     |
| RV-02 | Gli eventi applicativi e le notifiche devono essere tracciati con Log4j, non con System.out.println (la console resta riservata all'interazione CLI). |
| RV-03 | Il sistema non deve contenere percorsi assoluti né dipendere da servizi installati localmente.                 |
| RV-04 | Lo stato della scheda deve seguire un insieme definito di stati e transizioni (state machine del Documento 4). |

### 3.6 Attributi di qualità del software

| Attributo                     | ID    | Requisito (Cosa significa, perché conta per l'officina, come si verifica)                                                                                                                                                                                             |
|-------------------------------|-------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Correttezza calcoli monetari  | RQ-01 | **Significa** che totale preventivo, saldo e rimborsi (con due decimali) sono sempre esatti senza errori di arrotondamento. **Conta** perché sbagliare un importo vuol dire far pagare male il cliente. **Si verifica** con test sui tre esiti (riparazione, rifiuto, fuori produzione). |
| Integrità dei dati            | RQ-02 | **Significa** che codice fiscale e seriale sono unici, un dispositivo ha al massimo una scheda attiva, le giacenze restano coerenti e i dati sopravvivono al riavvio. **Conta** per evitare schede duplicate o scorte errate. **Si verifica** con test che rifiutano i duplicati. |
| Usabilità della console       | RQ-03 | **Significa** che ogni menu mostra chiaramente le azioni disponibili e messaggi di errore comprensibili per input non validi. **Conta** per permettere a tecnico e magazziniere di operare rapidamente al bancone. **Si verifica** provando i flussi principali sulla console. |
| Manutenibilità                | RQ-04 | **Significa** che i package sono coesi e privi di cicli e le classi nascondono i dettagli interni (information hiding). **Conta** per facilitare modifiche ed evoluzioni future del sistema. **Si verifica** tramite analisi della struttura dei package e code review. |
| Testabilità e Tracciabilità   | RQ-05 | **Significa** che ogni requisito funzionale, flusso alternativo e notifica registrata a log è isolabile e controllabile. **Conta** per individuare subito eventuali regressioni nei flussi dell'officina. **Si verifica** associando almeno un test automatico a ogni requisito e flusso di eccezione. |
| Portabilità                   | RQ-06 | **Significa** che il sistema usa un database embedded e nessun percorso assoluto. **Conta** per poter eseguire l'applicazione su qualsiasi postazione dell'officina o di valutazione. **Si verifica** clonando il repository su un altro PC con JDK e avviando il progetto. |

*Nota sulla sicurezza:* La sicurezza (autenticazione tramite password) non è inclusa tra gli attributi di qualità poiché il sistema prevede un menu libero di scelta ruolo su singola postazione; si tratta di una scelta esplicita legata al perimetro del progetto.

### 3.7 Altri requisiti

| ID    | Requisito                                                                                                                   |
|-------|-----------------------------------------------------------------------------------------------------------------------------|
| RA-01 | Ogni modifica ai requisiti segue il processo di richiesta di modifica del Project Plan (issue, pull request, approvazione). |

## 4. Use case

### 4.1 Use case diagram

**\[inserire use case diagram\]**

Gli attori sono il Cliente, il Tecnico Riparatore e il Responsabile Magazzino. *Genera Ricevuta di Ingresso* e *Registra Pagamento* sono use case inclusi; *Ordina Pezzo di Ricambio* estende *Invia Preventivo*.

### 4.2 Descrizione degli use case

**Accetta Dispositivo e Crea Scheda**

| Campo               | Descrizione                                                                                                                                                                                                                                                                                                                                                                           |
|---------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Attore principale   | Tecnico Riparatore                                                                                                                                                                                                                                                                                                                                                                    |
| Attore secondario   | Cliente                                                                                                                                                                                                                                                                                                                                                                               |
| Condizioni iniziali | Il cliente si presenta in officina con un dispositivo da riparare.                                                                                                                                                                                                                                                                                                                    |
| Scenario principale | \(1\) Il tecnico cerca o registra i dati del cliente e del dispositivo e inserisce il guasto dichiarato. (2) Il sistema crea la scheda di riparazione con identificativo univoco, data di ingresso e stato iniziale. (3) Il sistema registra il pagamento anticipato della diagnosi (10 €) e genera la ricevuta di ingresso (use case incluso *Genera Ricevuta di Ingresso*). (4) Il tecnico consegna la ricevuta al cliente. |
| Flussi alternativi  | Dati obbligatori mancanti o non validi, oppure dispositivo con scheda già attiva: il sistema segnala l'errore e non crea la scheda.                                                                                                                                                                                                                                                   |
| Condizioni finali   | Esiste una scheda nello stato iniziale associata al dispositivo, con la ricevuta di ingresso generata.                                                                                                                                                                                                                                                                                |
| Requisiti collegati | RF-01, RF-02, RF-03, RF-18                                                                                                                                                                                                                                                                                                                                                            |

**Invia Preventivo**

| Campo | Descrizione |
|---|---|
| Attore principale | Tecnico Riparatore |
| Attore secondario | Cliente, Responsabile Magazzino |
| Condizioni iniziali | Esiste una scheda per il dispositivo accettato. |
| Scenario principale | (1) Il tecnico registra la diagnosi del guasto sulla scheda. (2) Il tecnico inserisce i costi di manodopera e ricambi (anche salvando in bozza) e il sistema calcola il totale. (3) Il sistema verifica la disponibilità in magazzino dei ricambi indicati e impegna quelli disponibili. (4) Il sistema rende il preventivo disponibile al cliente, ne registra l'invio e invia una notifica simulata al cliente. (5) Il cliente approva il preventivo tramite il tecnico che registra la decisione con data. (6) La riparazione può proseguire. |
| Flussi alternativi | **A**. Ricambio non disponibile in magazzino in bozza di preventivo: si attiva l'use case esteso *Ordina Pezzo di Ricambio*. **B**. Preventivo rifiutato dal cliente (o rinuncia): i ricambi impegnati vengono rilasciati, la scheda passa ad "Annullata" e si trattiene il solo costo di diagnosi. **C**. Ricambio fuori produzione: il sistema rilascia eventuali ricambi impegnati, notifica al cliente l'impossibilità di riparare e porta la scheda a "Non riparabile". **D**. Modifica del preventivo consentita solo prima dell’approvazione (con rilascio di eventuali ricambi sostituiti). Se i nuovi ricambi sono tutti disponibili, il sistema li impegna e invia al cliente una nuova notifica. Se viene inserito un ricambio esaurito (`giacenza = 0`), il preventivo torna in bozza e la scheda torna in verifica magazzino (i ricambi già verificati come ordinabili e mantenuti non richiedono nuova verifica). |
| Condizioni finali | Preventivo approvato e riparazione autorizzata, oppure scheda annullata o non riparabile. |
| Requisiti collegati | RF-04, RF-05, RF-06, RF-07, RF-08, RF-15, RF-16, RF-18 |

**Ordina Pezzo di Ricambio** (estende *Invia Preventivo*)

| Campo                    | Descrizione                                                                                                                                                                                                                                                                             |
|--------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Attore principale        | Responsabile Magazzino |
| Attore secondario | Tecnico Riparatore |
| Condizione di estensione | Durante la composizione della bozza in *Invia Preventivo*, un ricambio indicato non è disponibile in magazzino (`giacenza = 0`).                                                                                                                                                                               |
| Scenario principale      | \(1\) Il tecnico richiede la verifica al Responsabile Magazzino, che conferma che il pezzo è ordinabile. (2) Dopo l'approvazione del preventivo da parte del cliente, il Responsabile registra l'ordine di acquisto. (3) Alla ricezione del pezzo il Responsabile assegna il ricambio direttamente alla scheda collegata (da "da ordinare" a "impegnato", senza alterare la giacenza libera). (4) La riparazione prosegue. |
| Flussi alternativi       | Ricambio fuori produzione: l'ordine non viene registrato e si segue il flusso C di *Invia Preventivo*.                                                                                                                                                                                  |
| Condizioni finali        | Ordine registrato e ricambio assegnato alla scheda all'arrivo.                                                                                                                                                                                                                          |
| Requisiti collegati      | RF-09, RF-10, RF-11                                                                                                                                                                                                                                                                     |

**Consegna Dispositivo Riparato**

| Campo | Descrizione |
|---|---|
| Attore principale | Tecnico Riparatore |
| Attore secondario | Cliente |
| Condizioni iniziali | Il preventivo è stato approvato e la riparazione è stata eseguita, oppure la scheda è nello stato "Annullata" o "Non riparabile". |
| Scenario principale | (1) Il tecnico chiude la riparazione e la scheda passa a "Riparata". (2) Il tecnico avvia la consegna e il sistema la consente per una scheda riparata. (3) Il tecnico registra il pagamento del saldo calcolato come `max(0 €, totale preventivo - 10 €)` con data e scheda associata (use case incluso *Registra Pagamento*). (4) Il sistema chiude la scheda. |
| Flussi alternativi | **A**. Scheda annullata o non riparabile: il dispositivo è riconsegnato non riparato; se annullata il saldo è `0 €` (resta acquisito il costo di diagnosi), se non riparabile viene registrato il rimborso di `-10 €`. **B**. Scheda in uno stato che non consente la consegna (ad esempio in attesa di approvazione, in attesa di ricambio o in riparazione): il sistema rifiuta la consegna e mostra lo stato attuale della scheda. |
| Condizioni finali | Dispositivo consegnato, pagamento o rimborso registrato, scheda chiusa. |
| Requisiti collegati | RF-12, RF-13, RF-14 |

**Consultazione**

| Campo               | Descrizione                                                                                                         |
|---------------------|---------------------------------------------------------------------------------------------------------------------|
| Attore principale   | Tecnico Riparatore, Responsabile Magazzino                                                                          |
| Attore secondario   | Nessuno                                                                                                             |
| Condizioni iniziali | La scheda o il catalogo ricambi esistono nel sistema.                                                               |
| Scenario principale | \(1\) L'operatore inserisce il criterio di ricerca (identificativo scheda, codice fiscale, seriale o stato). (2) Il sistema mostra lo stato, lo storico della scheda o l'inventario ricambi. |
| Flussi alternativi  | Criterio o identificativo inesistente: il sistema mostra un messaggio di errore.                                    |
| Condizioni finali   | Nessuna modifica ai dati.                                                                                           |
| Requisiti collegati | RF-17                                                                                                               |

**Gestisci Catalogo Ricambi**

| Campo | Descrizione |
|---|---|
| Attore principale | Responsabile Magazzino |
| Attore secondario | Nessuno |
| Condizioni iniziali | Il Responsabile ha scelto il proprio ruolo dal menu iniziale. |
| Scenario principale | (1) Il Responsabile inserisce codice, nome, qualità (originale o compatibile), prezzo unitario, giacenza iniziale e reperibilità del ricambio. (2) Il sistema valida i dati e registra il ricambio a catalogo. (3) Il ricambio diventa selezionabile dal tecnico nei preventivi. |
| Flussi alternativi | Dati non validi o codice ricambio già presente: il sistema segnala l'errore e non crea il ricambio. |
| Condizioni finali | Il ricambio è presente a catalogo. |
| Requisiti collegati | RF-18, RF-19 |

**Use case inclusi:**
- *Genera Ricevuta di Ingresso* (incluso in *Accetta Dispositivo e Crea Scheda*): produce i dettagli della ricevuta con ID scheda, dati cliente e dispositivo (`RF-03`).
- *Registra Pagamento* (incluso in *Consegna Dispositivo Riparato*): registra saldo positivo/nullo o rimborso `-10 €` con data e scheda (`RF-14`).

## 5. Modello del dominio

**\[Inserire qui l'immagine del class diagram del dominio\]**

Il modello del dominio mostra i concetti dell'officina e le relazioni tra loro, senza dettagli di implementazione (il class diagram di design è nel Documento 4). Le classi principali, con gli attributi essenziali in notazione CamelCase, sono le seguenti:

| Classe                | Attributi essenziali                                                                    | Descrizione                                                                                                   |
|-----------------------|-----------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------|
| Cliente               | `idCliente`, `nome`, `cognome`, `codiceFiscale`, `telefono`, `email`                    | Persona che porta il dispositivo in officina e approva o rifiuta il preventivo tramite il Tecnico Riparatore. |
| Dispositivo           | `idDispositivo`, `seriale`, `marca`, `modello`                                          | Apparecchio da riparare associato al cliente, identificato da seriale univoco.                                |
| SchedaRiparazione     | `idScheda`, `dataIngresso`, `dataChiusura`, `guastoDichiarato`, `diagnosiTecnica`, `statoCorrente`, `storicoStati` | Pratica associata a un dispositivo, con identificativo, date, guasto dichiarato, diagnosi, stato e storico. |
| Preventivo            | `idPreventivo`, `statoPreventivo`, `dataInvio`, `costoManodopera`, `vociRicambio`, `totaleCalcolato`, `dataDecisione` | Stima dei costi (manodopera e ricambi impegnati/da ordinare) collegata alla scheda, con l'esito della decisione del cliente. |
| Ricambio              | `codiceRicambio`, `nome`, `qualita`, `prezzoUnitario`, `giacenza`, `reperibilita`       | Pezzo necessario per la riparazione (originale o compatibile), con prezzo fisso e giacenza in magazzino.      |
| OrdineAcquisto        | `idOrdine`, `idSchedaAssociata`, `dataOrdine`, `dataRicezione`, `ricambiOrdinati`, `statoOrdine` | Richiesta di ricambi non disponibili associata alla scheda, registrata dal Responsabile Magazzino.        |
| Pagamento             | `idPagamento`, `data`, `importo`, `tipo`                                                | Importo (diagnosi iniziale, saldo alla consegna o rimborso) collegato alla scheda.                            |
| TecnicoRiparatore     | `idOperatore`, `nome`                                                                   | Operatore del sistema che gestisce accettazione, diagnosi, preventivi, riparazioni e consegne.                |
| ResponsabileMagazzino | `idOperatore`, `nome`                                                                   | Operatore del sistema che gestisce il catalogo ricambi, le verifiche di reperibilità e gli ordini.            |

## 6. Tracciabilità

| Use case                          | Requisiti                 | Flusso alternativo / test                             |
|-----------------------------------|---------------------------|-------------------------------------------------------|
| Accetta Dispositivo e Crea Scheda | RF-01, RF-02, RF-03, RF-18| Dati non validi o scheda già attiva → test di validazione |
| Invia Preventivo                  | RF-04 – RF-08, RF-18      | Preventivo modificato (RF-05) o rifiutato (RF-15)     |
| Ordina Pezzo di Ricambio          | RF-09, RF-10, RF-11       | Pezzo fuori produzione (RF-16)                        |
| Consegna Dispositivo Riparato     | RF-12, RF-13, RF-14       | Consegna di scheda annullata (RF-13) o non riparabile con rimborso `-10 €` (RF-14, RF-16) |
| Consultazione                     | RF-17                     | Identificativo inesistente                            |
| Gestisci Catalogo Ricambi         | RF-18, RF-19              | Dati non validi o codice duplicato → test di validazione |

*I requisiti trasversali e non funzionali (`RI-01`–`RI-03`, `RP-01`, `RD-01`–`RD-03`, `RV-01`–`RV-04`, `RQ-01`–`RQ-06`, `RA-01`) sono verificati tramite suite di test JUnit, prova su clone pulito e revisioni del progetto.*
