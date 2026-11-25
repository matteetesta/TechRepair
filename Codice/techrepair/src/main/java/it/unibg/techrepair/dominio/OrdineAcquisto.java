package it.unibg.techrepair.dominio;

import java.util.Set;
import java.time.LocalDate;

/**
 * 
 */
public class OrdineAcquisto {

    /**
     * Default constructor
     */
    public OrdineAcquisto() {
    }

    /**
     * 
     */
    private Integer idOrdine;

    /**
     * 
     */
    private LocalDate dataOrdine;

    /**
     * 
     */
    private LocalDate dataRicezione;

    /**
     * 
     */
    private StatoOrdine statoOrdine;

    /**
     * 
     */
    private SchedaRiparazione schedaAssociata;

    /**
     * 
     */
    private Set<Ricambio> ricambiOrdinati;

    /**
     * @param schedaAssociata 
     * @param ricambiOrdinati 
     * @param dataOrdine
     */
    public OrdineAcquisto(SchedaRiparazione schedaAssociata, Set<Ricambio> ricambiOrdinati, LocalDate dataOrdine) {
        // TODO implement here
    }

    /**
     * @param dataRicezione
     */
    public void registraRicezione(LocalDate dataRicezione) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Boolean isRicevuto() {
        // TODO implement here
        return null;
    }

    /**
     * Assegna l'identificativo generato dal database (layer persistenza).
     * @param idOrdine
     */
    public void setIdOrdine(Integer idOrdine) {
        // TODO implement here
    }

    /**
     * Factory per il layer persistenza.
     * @param idOrdine 
     * @param schedaAssociata 
     * @param ricambiOrdinati 
     * @param dataOrdine 
     * @param dataRicezione 
     * @param statoOrdine 
     * @return
     */
    public static OrdineAcquisto ricostruisci(Integer idOrdine, SchedaRiparazione schedaAssociata, Set<Ricambio> ricambiOrdinati, LocalDate dataOrdine, LocalDate dataRicezione, StatoOrdine statoOrdine) {
        // TODO implement here
        return null;
    }

}