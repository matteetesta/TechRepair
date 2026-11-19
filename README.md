# Tech Repair

Sistema per la gestione del flusso di lavoro di un'officina di riparazione di dispositivi elettronici, sviluppato per il corso di **Ingegneria del Software** (AA 2026/27 — Università degli Studi di Bergamo).

## Panoramica del sistema

**Tech Repair** supporta l'intero ciclo di vita di una riparazione, dall'accettazione del dispositivo al bancone fino alla riconsegna al cliente, coordinando il passaggio di consegne tra tre attori:

- **Tecnico Riparatore:** registra o ricerca cliente e dispositivo, apre la scheda di riparazione (con incasso anticipato della diagnosi e generazione della ricevuta di ingresso), inserisce la diagnosi tecnica, compone o modifica il preventivo, registra l'approvazione o il rifiuto del cliente, esegue la riparazione e gestisce la riconsegna con calcolo del saldo finale o del rimborso.
- **Responsabile Magazzino:** gestisce il catalogo dei ricambi (inserimento di nuovi componenti e rifornimento delle giacenze), verifica la reperibilità dei pezzi esauriti richiesti nei preventivi e gestisce gli ordini di acquisto verso i fornitori dopo l'approvazione del cliente.
- **Cliente:** attore esterno che interagisce con l'officina tramite il Tecnico Riparatore e riceve notifiche simulate nei punti chiave del processo (invio o modifica del preventivo, impossibilità di riparare per ricambio fuori produzione, riparazione completata).

## Documentazione del progetto

La documentazione ufficiale e i modelli UML si trovano nella cartella [`Documenti/`](Documenti/):

- **[Documento 1 – Project Plan](Documenti/Project%20plan%20%E2%80%93%20Tech%20Repair%20(gestione%20officina).md):** pianificazione del progetto, modello di processo, analisi dei rischi, work package, stima oraria e calendario degli sprint.
- **[Documento 2 – Gestione del progetto](Documenti/TR_GestioneProgetto_Documento2.md):** ciclo di vita iterativo e incrementale, piano di Configuration Management (IEEE 828), organizzazione del team e consuntivo degli sprint.
- **[Documento 3 – Specifica dei Requisiti (IEEE 830)](Documenti/TR_Requisiti_Documento3.md):** elicitazione dei requisiti, scenari d'uso, requisiti funzionali e non funzionali, attributi di qualità, casi d'uso, modello del dominio e matrice di tracciabilità.
- **Modelli e diagrammi UML:**
  - Progetto StarUML: [`Documenti/TR_DiagramsUML.mdj`](Documenti/TR_DiagramsUML.mdj)
  - [Use Case Diagram](Documenti/Immagini/UseCaseDiagram.svg)
  - [Class Diagram del Dominio](Documenti/Immagini/ClassDiagram_Dominio.svg)
- **[Specifica Logica e Funzionale (documento interno)](Documenti/Interni/Specifica%20Logica%20e%20Funzionale%20%E2%80%93%20Tech%20Repair.md):** dettaglio operativo di regole contabili, gestione scorte, macchina a stati della scheda e regole di validazione.

## Team e ruoli

| Membro | Ruolo | Responsabilità principali |
| :--- | :--- | :--- |
| **Matteo Testa** | Project Manager e Configuration Manager | Pianificazione, gestione board e issue, configurazione repository, registro ore |
| **Alice Rota** | Analista dei requisiti | Elicitazione e specifica dei requisiti, casi d'uso, scenari e flussi di eccezione |
| **Mattia Maggioni** | Architetto | Architettura del sistema, modellazione UML e design pattern |
| **Alessandro Benigni** | Responsabile sviluppo e test | Implementazione, test di unità, copertura e metriche |
