package it.unibg.techrepair.dominio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * 
 */
public class Preventivo {

    /**
     * 
     */
    private Integer idPreventivo;

    /**
     * 
     */
    private StatoPreventivo statoPreventivo;

    /**
     * 
     */
    private LocalDate dataInvio;

    /**
     * 
     */
    private BigDecimal costoManodopera;

    /**
     * 
     */
    private LocalDate dataDecisione;


    /**
     * 
     */
    private List<VoceRicambio> vociRicambio;

    /**
     * 
     */
    public Preventivo() {
        // TODO implement here
    }

    /**
     * @param costo
     */
    public void impostaCostoManodopera(BigDecimal costo) {
        // TODO implement here
    }

    /**
     * IMPEGNATO con giacenza > 0 (ricambio.impegna()), altrimenti DA_ORDINARE; rifiuta duplicati.
     * @param ricambio 
     * @return
     */
    public VoceRicambio aggiungiVoce(Ricambio ricambio) {
        // TODO implement here
        return null;
    }

    /**
     * Rilascia (+1) solo le voci IMPEGNATO.
     * @param ricambio
     */
    public void rimuoviVoce(Ricambio ricambio) {
        // TODO implement here
    }

    /**
     * 
     */
    public void rilasciaRicambiImpegnati() {
        // TODO implement here
    }

    /**
     * Calcolato a ogni chiamata: costoManodopera + somma(voce.getPrezzo()). totaleCalcolato e' derivato: nessun setter.
     * @return
     */
    public BigDecimal calcolaTotale() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Boolean haVociDaOrdinare() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Set<Ricambio> getRicambiDaOrdinare() {
        // TODO implement here
        return null;
    }

    /**
     * Porta le voci DA_ORDINARE a IMPEGNATO senza toccare la giacenza.
     */
    public void assegnaRicambiOrdinati() {
        // TODO implement here
    }

    /**
     * true se ogni voce e' IMPEGNATO o riferita a un ricambio ORDINABILE e, quando vociRicambio e' vuota, costoManodopera > 0,00.
     * @return
     */
    public Boolean isInviabile() {
        // TODO implement here
        return null;
    }

    /**
     * BOZZA -> INVIATO. Solleva DatiNonValidiException se vociRicambio e' vuota e costoManodopera <= 0,00.
     * @param data
     */
    public void invia(LocalDate data) {
        // TODO implement here
    }

    /**
     * 
     */
    public void riportaInBozza() {
        // TODO implement here
    }

    /**
     * @param data
     */
    public void approva(LocalDate data) {
        // TODO implement here
    }

    /**
     * @param data
     */
    public void rifiuta(LocalDate data) {
        // TODO implement here
    }

    /**
     * Solleva OperazioneNonConsentitaException se il preventivo e' APPROVATO o RIFIUTATO.
     */
    private void verificaModificabile() {
        // TODO implement here
    }

    /**
     * Assegna l'identificativo generato dal database (layer persistenza).
     * @param idPreventivo
     */
    public void setIdPreventivo(Integer idPreventivo) {
        // TODO implement here
    }

    /**
     * Factory per il layer persistenza: non modifica le giacenze dei ricambi.
     * @param idPreventivo 
     * @param statoPreventivo 
     * @param dataInvio 
     * @param dataDecisione 
     * @param costoManodopera 
     * @param vociRicambio 
     * @return
     */
    public static Preventivo ricostruisci(Integer idPreventivo, StatoPreventivo statoPreventivo, LocalDate dataInvio, LocalDate dataDecisione, BigDecimal costoManodopera, List<VoceRicambio> vociRicambio) {
        // TODO implement here
        return null;
    }

}