package it.unibg.techrepair.repository;

import it.unibg.techrepair.dominio.SchedaRiparazione;
import it.unibg.techrepair.dominio.StatoScheda;

import java.util.List;

/**
 * 
 */
public interface SchedaRepository {


    /**
     * Persiste in un'unica transazione JDBC l'intero aggregato (SchedaRiparazione, CambioStato, Preventivo, VoceRicambio, Pagamento, OrdineAcquisto) insieme a giacenza e reperibilita dei Ricambio coinvolti.
     * @param scheda
     */
    public void salva(SchedaRiparazione scheda);

    /**
     * @param idScheda 
     * @return
     */
    public SchedaRiparazione trovaPerId(Integer idScheda);

    /**
     * @param stato 
     * @return
     */
    public List<SchedaRiparazione> trovaPerStato(StatoScheda stato);

    /**
     * @param codiceFiscale 
     * @return
     */
    public List<SchedaRiparazione> trovaPerCodiceFiscale(String codiceFiscale);

    /**
     * @param seriale 
     * @return
     */
    public List<SchedaRiparazione> trovaPerSeriale(String seriale);

}