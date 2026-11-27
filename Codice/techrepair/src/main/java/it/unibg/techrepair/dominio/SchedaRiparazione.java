package it.unibg.techrepair.dominio;

import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 
 */
public class SchedaRiparazione {

    /**
     * Default constructor
     */
    public SchedaRiparazione() {
    }

    /**
     * 
     */
    private Integer idScheda;

    /**
     * 
     */
    private LocalDate dataIngresso;

    /**
     * 
     */
    private LocalDate dataChiusura;

    /**
     * 
     */
    private String guastoDichiarato;

    /**
     * 
     */
    private String diagnosiTecnica;

    /**
     * 
     */
    private StatoScheda statoCorrente;

    /**
     * 
     */
    private Dispositivo dispositivo;

    /**
     * 
     */
    private List<Pagamento> pagamenti;

    /**
     * 
     */
    private List<CambioStato> storicoStati;

    /**
     * 
     */
    private Preventivo preventivo;

    /**
     * 
     */
    private OrdineAcquisto ordineAcquisto;

    /**
     * 
     */
    private StatoSchedaState stato;

    /**
     * 
     */
    private Set<SchedaObserver> osservatori;

    /**
     * Crea la scheda in ACCETTATA, invoca dispositivo.aggiungiScheda(this) e registra il Pagamento DIAGNOSI_INIZIALE di 10,00 EUR.
     * @param dispositivo 
     * @param guastoDichiarato 
     * @param dataIngresso
     */
    public SchedaRiparazione(Dispositivo dispositivo, String guastoDichiarato, LocalDate dataIngresso) {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.avviaDiagnosi(this, ...).
     */
    public void avviaDiagnosi() {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.registraDiagnosi(this, ...).
     * @param diagnosi
     */
    public void registraDiagnosi(String diagnosi) {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.impostaCostoManodopera(this, ...).
     * @param costo
     */
    public void impostaCostoManodopera(BigDecimal costo) {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.aggiungiRicambio(this, ...).
     * @param ricambio
     */
    public void aggiungiRicambio(Ricambio ricambio) {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.rimuoviRicambio(this, ...).
     * @param ricambio
     */
    public void rimuoviRicambio(Ricambio ricambio) {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.richiediVerificaMagazzino(this, ...).
     */
    public void richiediVerificaMagazzino() {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.confermaRicambiOrdinabili(this, ...).
     */
    public void confermaRicambiOrdinabili() {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.segnalaFuoriProduzione(this, ...).
     * @param ricambio
     */
    public void segnalaFuoriProduzione(Ricambio ricambio) {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.inviaPreventivo(this, ...).
     */
    public void inviaPreventivo() {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente. Al termine della modifica il preventivo deve rispettare Preventivo.isInviabile() (manodopera > 0 senza ricambi).
     * @param aggiunte 
     * @param rimozioni 
     * @param costoManodopera
     */
    public void modificaPreventivo(Set<Ricambio> aggiunte, Set<Ricambio> rimozioni, BigDecimal costoManodopera) {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.approvaPreventivo(this, ...).
     */
    public void approvaPreventivo() {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.rifiutaPreventivo(this, ...).
     */
    public void rifiutaPreventivo() {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.rinuncia(this, ...).
     */
    public void rinuncia() {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.registraOrdine(this, ...).
     * @param ordine
     */
    public void registraOrdine(OrdineAcquisto ordine) {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.registraRicezioneOrdine(this, ...).
     */
    public void registraRicezioneOrdine() {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.aggiornaSeriale(this, ...).
     * @param nuovoSeriale
     */
    public void aggiornaSeriale(String nuovoSeriale) {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.chiudiRiparazione(this, ...).
     */
    public void chiudiRiparazione() {
        // TODO implement here
    }

    /**
     * Delega allo stato corrente: stato.consegna(this, ...).
     * @return
     */
    public Pagamento consegna() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Boolean isAttiva() {
        // TODO implement here
        return null;
    }

    /**
     * @param osservatore
     */
    public void aggiungiOsservatore(SchedaObserver osservatore) {
        // TODO implement here
    }

    /**
     * @param osservatore
     */
    public void rimuoviOsservatore(SchedaObserver osservatore) {
        // TODO implement here
    }

    /**
     * Aggiorna stato e statoCorrente e aggiunge un CambioStato con data e ora correnti.
     * @param nuovoStato
     */
    void cambiaStato(StatoSchedaState nuovoStato) {
        // TODO implement here
    }

    /**
     * @param evento
     */
    void notificaOsservatori(EventoScheda evento) {
        // TODO implement here
    }

    /**
     * Restituisce il preventivo, creandolo in BOZZA se assente.
     * @return
     */
    Preventivo ottieniPreventivo() {
        // TODO implement here
        return null;
    }

    /**
     * 
     */
    void rilasciaRicambiImpegnati() {
        // TODO implement here
    }

    /**
     * @param importo 
     * @param tipo 
     * @return
     */
    Pagamento registraPagamento(BigDecimal importo, TipoPagamento tipo) {
        // TODO implement here
        return null;
    }

    /**
     * @param diagnosi
     */
    void impostaDiagnosi(String diagnosi) {
        // TODO implement here
    }

    /**
     * @param ordine
     */
    void impostaOrdine(OrdineAcquisto ordine) {
        // TODO implement here
    }

    /**
     * Restituisce stato, ricostruendolo con StatoSchedaState.daStato(statoCorrente) se nullo.
     * @return
     */
    private StatoSchedaState statoAttuale() {
        // TODO implement here
        return null;
    }

    /**
     * Assegna l'identificativo generato dal database (layer persistenza).
     * @param idScheda
     */
    public void setIdScheda(Integer idScheda) {
        // TODO implement here
    }

    /**
     * Factory per il layer persistenza: ricostruisce una scheda salvata senza registrare pagamenti, cambi di stato o notifiche e senza il controllo sulla scheda attiva.
     * @param idScheda 
     * @param dispositivo 
     * @param dataIngresso 
     * @param dataChiusura 
     * @param guastoDichiarato 
     * @param diagnosiTecnica 
     * @param statoCorrente 
     * @param storicoStati 
     * @param pagamenti 
     * @param preventivo 
     * @param ordineAcquisto 
     * @return
     */
    public static SchedaRiparazione ricostruisci(Integer idScheda, Dispositivo dispositivo, LocalDate dataIngresso, LocalDate dataChiusura, String guastoDichiarato, String diagnosiTecnica, StatoScheda statoCorrente, List<CambioStato> storicoStati, List<Pagamento> pagamenti, Preventivo preventivo, OrdineAcquisto ordineAcquisto) {
        // TODO implement here
        return null;
    }

}