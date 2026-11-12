# Tech Repair – Elicitazione dei requisiti e Specifica dei requisiti (IEEE 830)

*Bozza 0.1 – Team Tech Repair, AA 2026/27. I punti marcati sono ipotesi da validare con il team o con i docenti prima di bloccare la specifica.*

| Versione | Data       | Modifiche       |
|----------|------------|-----------------|
| 0.1      | 11/11/2026 | Prima bozza     |
| 0.2      | 12/11/2026 | Prime modifiche |

# Parte A – Elicitazione dei requisiti

## A.1 Obiettivo e approccio

Obiettivo dell'elicitazione è capire che cosa deve fare Tech Repair per supportare il percorso di un dispositivo in riparazione, dall'accettazione in officina alla consegna e al pagamento. Il dominio è quello di una piccola officina di riparazione di dispositivi, con un passaggio di consegne continuo tra cliente e tecnico.

L'approccio è basato su **scenari d'uso** dei tre attori, affiancati da analisi del dominio e revisione incrociata nel team. Le tecniche usate sono:

- **Analisi del dominio e dei documenti:** ricostruzione del flusso tipico di un'officina (ricevuta di ingresso, scheda di lavoro, preventivo, ordine ricambi, ricevuta di pagamento).

- **Scenari e use case:** per ogni attore si descrivono situazioni concrete, con scenario principale e flussi alternativi.

- **Revisione incrociata (walkthrough):** gli use case sono riletti da almeno un altro membro prima di bloccare la specifica, come previsto dal Project Plan (rischio "requisiti ambigui").

Non è stato possibile coinvolgere gli stakeholder reali (ad esempio il titolare o un tecnico di un'officina). I ruoli di Cliente, Tecnico Riparatore e Responsabile Magazzino sono stati simulati dal team.

## A.2 Stakeholder e attori

| Stakeholder / attore   | Interesse                                              | Esigenze principali                                                                                                                |
|------------------------|--------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|
| Cliente                | Riavere il dispositivo funzionante a costo noto        | Ricevuta di ingresso, preventivo chiaro con possibilità di approvare o rifiutare, essere avvisato dell'esito, pagamento registrato |
| Tecnico Riparatore     | Lavorare in modo ordinato sulle riparazioni            | Scheda di riparazione con stato aggiornato, inserimento di diagnosi e costi, richiesta di ricambi                                  |
| Responsabile Magazzino | Avere ricambi disponibili senza fermare le riparazioni | Verifica della disponibilità, ordine dei pezzi mancanti, aggiornamento delle giacenze                                              |

## A.3 Scenari raccolti

**S1 – Riparazione completata (scenario principale).** Il cliente porta il dispositivo in officina; il sistema crea la scheda e genera la ricevuta di ingresso. Il tecnico diagnostica il guasto e inserisce i costi; il preventivo è inviato al cliente, che lo approva tramite il tecnico. Il tecnico esegue la riparazione e la chiude; alla consegna il sistema registra il pagamento.

**S2 – Pezzo non in magazzino.** Durante il preventivo il tecnico rileva che serve un ricambio assente. Il sistema segnala la mancanza al Responsabile Magazzino, che ordina il pezzo; la riparazione prosegue all'arrivo del ricambio.

**S3 – Preventivo rifiutato.** Il cliente rifiuta il preventivo: la scheda passa ad "Annullato" e viene addebitato solo il costo di diagnosi. Il dispositivo è riconsegnato non riparato.

**S4 – Pezzo fuori produzione.** Il ricambio non è più reperibile: il sistema notifica al cliente l'impossibilità di riparare e la scheda viene chiusa di conseguenza.

## A.4 Dal fabbisogno ai requisiti

Ogni scenario è scomposto in affermazioni verificabili ("il sistema deve…"), classificate in requisiti funzionali, vincoli e attributi di qualità, e numerate nella Parte B. Gli use case principali sono:

- **Accetta Dispositivo e Crea Scheda** (include *Genera Ricevuta di Ingresso*)

- **Invia Preventivo** (extend *Ordina Pezzo di Ricambio* se il pezzo non è in magazzino)

- **Consegna Dispositivo Riparato** (include *Registra Pagamento*)

Ogni flusso di eccezione (preventivo rifiutato, pezzo fuori produzione, richiesta di modifica del preventivo) diventa un flusso alternativo di uno use case, una transizione della state machine, un frammento alt del sequence diagram e almeno un test JUnit, con tracciabilità verso il Documento 5 (Testing).

## A.5 Questioni aperte e decisioni del team

- **Costo di diagnosi:** importo fisso, incluso nel totale se il preventivo è approvato, addebitato da solo se la riparazione non viene eseguita.

- **Modifica del preventivo:** il cliente può chiedere aggiunta o rimozione di voci e scelta di qualità o originalità dei componenti; il tecnico aggiorna e rinvia il preventivo.

- **Cliente e console:** il cliente non usa mai la console, comunica col tecnico che registra. Resta uno dei tre attori, ma esterno e passivo.

# Parte B – Specifica dei requisiti software (struttura IEEE 830)

## 1. Introduzione

### 1.1 Scopo

Questo documento specifica i requisiti del sistema **Tech Repair** secondo lo standard IEEE 830 (*Recommended Practice for Software Requirements Specifications*). È destinato al team di sviluppo (progettazione, implementazione, test) e ai docenti che valutano il progetto

### 1.2 Ambito del prodotto

Tech Repair gestisce il percorso di un dispositivo in riparazione: accettazione, creazione della scheda, diagnosi e preventivo, eventuale ordine di ricambi, riparazione, consegna e pagamento.

**Fuori ambito:** pagamenti reali, invio reale di email o SMS (le notifiche sono simulate), contabilità, interfaccia grafica, gestione dei dispositivi non ritirati.

### 1.3 Definizioni, acronimi e abbreviazioni

| Termine           | Significato                                                                                                                            |
|-------------------|----------------------------------------------------------------------------------------------------------------------------------------|
| Scheda            | Scheda di riparazione associata a un dispositivo e al cliente                                                                          |
| Preventivo        | Stima dei costi di riparazione (manodopera e ricambi) proposta al cliente                                                              |
| Costo di diagnosi | Importo fisso della diagnosi; incluso nel totale del preventivo se approvato, addebitato da solo se la riparazione non viene eseguita. |
| Ricambio          | Pezzo necessario per la riparazione                                                                                                    |

### 1.4 Riferimenti

- IEEE Std 830-1998, *Recommended Practice for Software Requirements Specifications*

- Requisiti per il progetto di ingegneria del software AA 2026/27 (Gargantini, Bonfanti)

- Documento 1 – Project Plan di Tech Repair

- Materiale del corso

### 1.5 Panoramica del documento

La sezione 2 descrive il prodotto in generale; la sezione 3 elenca i requisiti specifici (interfacce, funzionali raggruppati per funzione, prestazioni, vincoli, attributi di qualità, altri requisiti); la sezione 4 descrive gli use case; la sezione 5 presenta il modello del dominio; la sezione 6 riporta la tracciabilità tra use case e requisiti.

## 2. Descrizione generale

### 2.1 Prospettiva del prodotto

Tech Repair è un'applicazione autonoma, senza dipendenze da servizi esterni da installare. Gestisce il passaggio di consegne tra cliente, tecnico e magazzino attraverso una scheda di riparazione che attraversa diversi stati.

### 2.2 Funzioni del prodotto

- **Accettazione:** creare la scheda con dati dispositivo e cliente.

- **Diagnosi e preventivo:** registrare la diagnosi e i costi, inviare il preventivo, registrare l'approvazione o il rifiuto del cliente.

- **Ricambi:** verificare la disponibilità e ordinare il pezzo mancante.

- **Consegna e pagamento:** consegnare il dispositivo riparato e registrare il pagamento.

- **Gestione delle eccezioni:** preventivo modificato o rifiutato, ricambio fuori produzione.

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

## 3. Requisiti specifici

### 3.1 Interfacce esterne

| ID    | Requisito                                                                                                                                                                                 |
|-------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| RI-01 | Il sistema deve offrire un'interfaccia a riga di comando con menu testuali per il Tecnico Riparatore e il Responsabile Magazzino; il Cliente non interagisce direttamente con il sistema. |
| RI-02 | Il sistema deve persistere i dati in un database embedded, senza richiedere servizi esterni.                                                                                              |
| RI-03 | Le notifiche al cliente devono essere simulate (messaggio su console e tracciate con Log4j) e non inviate realmente.                                                                      |

### 3.2 Requisiti funzionali

**Accettazione (Use Case: Accetta Dispositivo e Crea Scheda)**

| ID    | Requisito                                                                                                                                                                                                                                            | Attore  | Priorità |
|-------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|----------|
| RF-01 | Il sistema deve permettere al tecnico di cercare il cliente per codice fiscale e, se nuovo, registrarne i dati (nome, cognome, codice fiscale, telefono, email), e di selezionare o registrare il suo dispositivo (seriale univoco, marca, modello). | Tecnico | Alta     |
| RF-02 | Il sistema deve creare una scheda di riparazione con identificativo univoco, data di ingresso e stato iniziale.                                                                                                                                      | Tecnico | Alta     |
| RF-03 | Il sistema deve generare la ricevuta di ingresso contenente identificativo della scheda, dati del cliente e del dispositivo.                                                                                                                         | Tecnico | Alta     |

**Diagnosi e preventivo (Use Case: Invia Preventivo)**

| ID    | Requisito                                                                                                                                               | Attore  | Priorità |
|-------|---------------------------------------------------------------------------------------------------------------------------------------------------------|---------|----------|
| RF-04 | Il sistema deve permettere al tecnico di registrare la diagnosi del guasto sulla scheda.                                                                | Tecnico | Alta     |
| RF-05 | Il sistema deve permettere al tecnico di inserire i costi (manodopera e ricambi) e calcolare il totale del preventivo.                                  | Tecnico | Alta     |
| RF-06 | Il sistema deve rendere il preventivo disponibile al cliente e registrarne l'invio.                                                                     | Tecnico | Alta     |
| RF-07 | Il sistema deve permettere al tecnico di registrare la decisione del cliente di approvare o rifiutare il preventivo e registrare la decisione con data. | Tecnico | Alta     |
| RF-08 | Il sistema deve consentire la prosecuzione della riparazione solo dopo l'approvazione del preventivo.                                                   | Tecnico | Alta     |

**Ricambi (Use Case: Ordina Pezzo di Ricambio, extend di Invia Preventivo)**

| ID    | Requisito                                                                                                                   | Attore                 | Priorità |
|-------|-----------------------------------------------------------------------------------------------------------------------------|------------------------|----------|
| RF-09 | Il sistema deve verificare la disponibilità in magazzino dei ricambi indicati nel preventivo.                               | Sistema                | Alta     |
| RF-10 | Se un ricambio non è disponibile, il sistema deve permettere al Responsabile Magazzino di registrare un ordine di acquisto. | Responsabile Magazzino | Media    |
| RF-11 | Il sistema deve aggiornare le giacenze alla ricezione di un ricambio e al suo utilizzo in una riparazione.                  | Responsabile Magazzino | Media    |

**Riparazione, consegna e pagamento (Use Case: Consegna Dispositivo Riparato)**

| ID    | Requisito                                                                                                                                  | Attore  | Priorità |
|-------|--------------------------------------------------------------------------------------------------------------------------------------------|---------|----------|
| RF-12 | Il sistema deve permettere al tecnico di chiudere la riparazione, portando la scheda allo stato "Riparata".                                | Tecnico | Alta     |
| RF-13 | Il sistema deve permettere di consegnare il dispositivo al cliente solo per schede nello stato “Riparata”, “Annullato” o “Non riparabile”. | Tecnico | Alta     |
| RF-14 | Il sistema deve registrare il pagamento (importo, data, scheda associata) e chiudere la scheda.                                            | Tecnico | Alta     |

**Flussi di eccezione**

| ID    | Requisito                                                                                                                                              | Attore  | Priorità |
|-------|--------------------------------------------------------------------------------------------------------------------------------------------------------|---------|----------|
| RF-15 | Se il cliente rifiuta il preventivo, il sistema deve portare la scheda allo stato "Annullato" e addebitare solo il costo di diagnosi.                  | Cliente | Alta     |
| RF-16 | Se il ricambio è fuori produzione, il sistema deve notificare al cliente l'impossibilità di riparare e portare la scheda nello stato “Non riparabile”. | Sistema | Alta     |

**Consultazione**

| ID    | Requisito                                                                                                      | Attore  | Priorità |
|-------|----------------------------------------------------------------------------------------------------------------|---------|----------|
| RF-17 | Il sistema deve permettere di consultare lo stato e lo storico di una scheda a partire dal suo identificativo. | Tecnico | Media    |

### 3.3 Requisiti di prestazione

| ID    | Requisito                                                                                                                               |
|-------|-----------------------------------------------------------------------------------------------------------------------------------------|
| RP-01 | Non sono previsti requisiti di prestazione stringenti oltre al funzionamento interattivo su un PC standard con un operatore alla volta. |

### 3.4 Vincoli di progetto

| ID    | Requisito                                                                                                      |
|-------|----------------------------------------------------------------------------------------------------------------|
| RV-01 | Il codice deve essere in Java con Maven; i test con JUnit.                                                     |
| RV-02 | Gli eventi applicativi devono essere tracciati con Log4j, non con System.out.println.                          |
| RV-03 | Il sistema non deve contenere percorsi assoluti né dipendere da servizi installati localmente.                 |
| RV-04 | Lo stato della scheda deve seguire un insieme definito di stati e transizioni (state machine del Documento 4). |

### 3.5 Attributi di qualità del software

| Attributo      | ID    | Requisito                                                                                                                                                      |
|----------------|-------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Correttezza    | RQ-01 | Il totale del preventivo e l'importo del pagamento devono essere calcolati senza errori di arrotondamento (uso di tipi adatti ai valori monetari (BigDecimal). |
| Affidabilità   | RQ-02 | I dati confermati devono sopravvivere a un riavvio dell'applicazione.                                                                                          |
| Usabilità      | RQ-03 | Ogni menu deve mostrare le azioni disponibili e messaggi di errore comprensibili in caso di input non valido.                                                  |
| Manutenibilità | RQ-04 | I package devono essere coesi e privi di cicli; le classi devono nascondere i dettagli interni (information hiding).                                           |
| Testabilità    | RQ-05 | Ogni requisito funzionale deve essere verificabile da almeno un test; ogni flusso di eccezione ha almeno un test JUnit.                                        |
| Portabilità    | RQ-06 | Il sistema deve funzionare su qualsiasi macchina con JDK, dopo un clone pulito del repository.                                                                 |

### 3.6 Altri requisiti

| ID    | Requisito                                                                                                                   |
|-------|-----------------------------------------------------------------------------------------------------------------------------|
| RA-01 | Ogni modifica ai requisiti segue il processo di richiesta di modifica del Project Plan (issue, pull request, approvazione). |

## 4. Use case

### 4.1 Use case diagram

**\[inserire use case diagram\]**

Gli attori sono il Cliente, il Tecnico Riparatore e il Responsabile Magazzino. *Genera Ricevuta di Ingresso* e *Registra Pagamento* sono use case inclusi; *Ordina Pezzo di Ricambio* estende *Invia Preventivo*.

### 4.2 Descrizione degli use case

**Accetta Dispositivo e Crea Scheda**

| Campo               | Descrizione                                                                                                                                                                                                                                                                                                                                        |
|---------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Attore principale   | Tecnico Riparatore                                                                                                                                                                                                                                                                                                                                 |
| Attore secondario   | Cliente                                                                                                                                                                                                                                                                                                                                            |
| Condizioni iniziali | Il cliente si presenta in officina con un dispositivo da riparare.                                                                                                                                                                                                                                                                                 |
| Scenario principale | \(1\) Il tecnico registra i dati del cliente e del dispositivo e il guasto dichiarato. (2) Il sistema crea la scheda di riparazione con identificativo univoco, data di ingresso e stato iniziale. (3) Il sistema genera la ricevuta di ingresso (use case incluso *Genera Ricevuta di Ingresso*). (4) Il tecnico consegna la ricevuta al cliente. |
| Flussi alternativi  | Dati obbligatori mancanti o non validi: il sistema segnala l'errore e non crea la scheda.                                                                                                                                                                                                                                                          |
| Condizioni finali   | Esiste una scheda nello stato iniziale associata al dispositivo, con la ricevuta di ingresso generata.                                                                                                                                                                                                                                             |
| Requisiti collegati | RF-01, RF-02, RF-03                                                                                                                                                                                                                                                                                                                                |

**Invia Preventivo**

| Campo | Descrizione |
|---|---|
| Attore principale | Tecnico Riparatore |
| Attore secondario | Cliente, Responsabile Magazzino |
| Condizioni iniziali | Esiste una scheda per il dispositivo accettato. |
| Scenario principale | (1) Il tecnico registra la diagnosi del guasto sulla scheda. (2) Il tecnico inserisce i costi di manodopera e ricambi e il sistema calcola il totale. (3) Il sistema verifica la disponibilità in magazzino dei ricambi indicati. (4) Il sistema rende il preventivo disponibile al cliente e ne registra l'invio. (5) Il cliente approva il preventivo tramite il tecnico che registra la decisione con data. (6) La riparazione può proseguire. |
| Flussi alternativi | **A**. Ricambio non disponibile in magazzino: si attiva l'use case esteso *Ordina Pezzo di Ricambio*.<br>**B**. Preventivo rifiutato dal cliente: la scheda passa ad "Annullato" e si addebita solo il costo di diagnosi.<br>**C**. Ricambio fuori produzione: il sistema notifica al cliente l'impossibilità di riparare e chiude la scheda di conseguenza.<br>**D**. Modifica del preventivo consentita solo prima dell’approvazione. |
| Condizioni finali | Preventivo approvato e riparazione autorizzata, oppure scheda annullata o chiusa per impossibilità di riparare. |
| Requisiti collegati | RF-04, RF-05, RF-06, RF-07, RF-08, RF-15, RF-16 |

**Ordina Pezzo di Ricambio** (estende *Invia Preventivo*)

| Campo                    | Descrizione                                                                                                                                                                                                        |
|--------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Attore principale        | Responsabile Magazzino                                                                                                                                                                                             |
| Condizione di estensione | Durante *Invia Preventivo* un ricambio indicato non è disponibile in magazzino.                                                                                                                                    |
| Scenario principale      | \(1\) Il sistema segnala la mancanza al Responsabile Magazzino. (2) Il Responsabile registra l'ordine di acquisto. (3) Alla ricezione del pezzo il Responsabile aggiorna le giacenze. (4) La riparazione prosegue. |
| Flussi alternativi       | Ricambio fuori produzione: l'ordine non viene registrato e si segue il flusso C di *Invia Preventivo*.                                                                                                             |
| Condizioni finali        | Ordine registrato e giacenze aggiornate all'arrivo del ricambio.                                                                                                                                                   |
| Requisiti collegati      | RF-09, RF-10, RF-11                                                                                                                                                                                                |

**Consegna Dispositivo Riparato**

| Campo | Descrizione |
|---|---|
| Attore principale | Tecnico Riparatore |
| Attore secondario | Cliente |
| Condizioni iniziali | Il preventivo è stato approvato e la riparazione è stata eseguita, oppure la scheda è stata annullata. |
| Scenario principale | (1) Il tecnico chiude la riparazione e la scheda passa a "Riparata". (2) Il tecnico avvia la consegna e il sistema la consente per una scheda riparata. (3) Il tecnico registra il pagamento con importo, data e scheda associata (use case incluso *Registra Pagamento*). (4) Il sistema chiude la scheda. |
| Flussi alternativi | **A**. Scheda annullata: il dispositivo è riconsegnato non riparato e il pagamento riguarda solo il costo di diagnosi.<br>**B**. Scheda in uno stato che non consente la consegna (ad esempio in attesa di approvazione, in attesa di ricambio o in riparazione): il sistema rifiuta la consegna e mostra lo stato attuale della scheda. |
| Condizioni finali | Dispositivo consegnato, pagamento registrato, scheda chiusa. |
| Requisiti collegati | RF-12, RF-13, RF-14 |

**Consultazione**

| Campo               | Descrizione                                                                                                         |
|---------------------|---------------------------------------------------------------------------------------------------------------------|
| Attore principale   | Tecnico Riparatore                                                                                                  |
| Attore secondario   | Cliente                                                                                                             |
| Condizioni iniziali | La scheda esiste.                                                                                                   |
| Scenario principale | \(1\) Il tecnico inserisce l'identificativo della scheda. (2) Il sistema mostra lo stato e lo storico della scheda. |
| Flussi alternativi  | Identificativo inesistente: il sistema mostra un messaggio di errore.                                               |
| Condizioni finali   | Nessuna modifica ai dati.                                                                                           |
| Requisiti collegati | RF-17                                                                                                               |

## 5. Modello del dominio

**\[Inserire qui l'immagine del class diagram del dominio\]**

Il modello del dominio mostra i concetti dell'officina e le relazioni tra loro, senza dettagli di implementazione (il class diagram di design è nel Documento 4). Le classi principali, con gli attribute essenziali, sono le seguenti:

| Classe                                      | Descrizione                                                                                                   |
|---------------------------------------------|---------------------------------------------------------------------------------------------------------------|
| Cliente                                     | Persona che porta il dispositivo in officina e approva o rifiuta il preventivo tramite il Tecnico Riparatore. |
| Dispositivo                                 | Apparecchio da riparare (tipo, marca/modello, guasto dichiarato).                                             |
| Scheda di riparazione                       | Pratica associata a un dispositivo, con identificativo, data di ingresso e stato.                             |
| Preventivo                                  | Stima dei costi (manodopera e ricambi) collegata alla scheda, con l'esito della decisione del cliente.        |
| Ricambio                                    | Pezzo necessario per la riparazione, con la giacenza in magazzino.                                            |
| Ordine di acquisto                          | Richiesta di un ricambio non disponibile, registrata dal Responsabile Magazzino.                              |
| Pagamento                                   | Importo registrato alla consegna e collegato alla scheda.                                                     |
| Tecnico Riparatore e Responsabile Magazzino | Operatori del sistema che eseguono le attività sulle schede e sul magazzino.                                  |

## 6. Tracciabilità

| Use case                          | Requisiti           | Flusso alternativo / test             |
|-----------------------------------|---------------------|---------------------------------------|
| Accetta Dispositivo e Crea Scheda | RF-01, RF-02, RF-03 | Dati non validi → test di validazione |
| Invia Preventivo                  | RF-04 – RF-08       | Preventivo rifiutato (RF-15)          |
| Ordina Pezzo di Ricambio          | RF-09, RF-10, RF-11 | Pezzo fuori produzione (RF-16)        |
| Consegna Dispositivo Riparato     | RF-12, RF-13, RF-14 | Consegna di scheda annullata (RF-13)  |
| Consultazione                     | RF-17               | Identificativo inesistente            |
