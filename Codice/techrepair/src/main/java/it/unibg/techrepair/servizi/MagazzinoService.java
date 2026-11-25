package it.unibg.techrepair.servizi;

import it.unibg.techrepair.repository.RicambioRepository;
import it.unibg.techrepair.repository.OrdineRepository;
import it.unibg.techrepair.dominio.Ricambio;
import it.unibg.techrepair.dominio.QualitaRicambio;
import it.unibg.techrepair.dominio.Reperibilita;
import it.unibg.techrepair.dominio.SchedaRiparazione;
import it.unibg.techrepair.dominio.OrdineAcquisto;

import java.util.List;
import java.math.BigDecimal;

/**
 * Use case Gestisci Catalogo Ricambi, verifica reperibilita' (Invia Preventivo) e Ordina Pezzo di Ricambio (lato Responsabile Magazzino).
 */
public class MagazzinoService {

    /**
     * Default constructor
     */
    public MagazzinoService() {
    }

    /**
     * 
     */
    private RicambioRepository ricambi;

    /**
     * 
     */
    private OrdineRepository ordini;

    /**
     * 
     */
    private SchedaService schedaService;

    /**
     * @param ricambi 
     * @param ordini 
     * @param schedaService
     */
    public MagazzinoService(RicambioRepository ricambi, OrdineRepository ordini, SchedaService schedaService) {
        // TODO implement here
    }

    /**
     * @param codiceRicambio 
     * @param nome 
     * @param qualita 
     * @param prezzoUnitario 
     * @param giacenza 
     * @param reperibilita 
     * @return
     */
    public Ricambio inserisciRicambio(String codiceRicambio, String nome, QualitaRicambio qualita, BigDecimal prezzoUnitario, Integer giacenza, Reperibilita reperibilita) {
        // TODO implement here
        return null;
    }

    /**
     * @param codiceRicambio 
     * @param quantita
     */
    public void rifornisciRicambio(String codiceRicambio, Integer quantita) {
        // TODO implement here
    }

    /**
     * @return
     */
    public List<Ricambio> consultaInventario() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<SchedaRiparazione> schedeInVerifica() {
        // TODO implement here
        return null;
    }

    /**
     * @param idScheda
     */
    public void confermaRicambiOrdinabili(Integer idScheda) {
        // TODO implement here
    }

    /**
     * @param idScheda 
     * @param codiceRicambio
     */
    public void segnalaFuoriProduzione(Integer idScheda, String codiceRicambio) {
        // TODO implement here
    }

    /**
     * @return
     */
    public List<SchedaRiparazione> schedeInAttesaRicambi() {
        // TODO implement here
        return null;
    }

    /**
     * @param idScheda 
     * @return
     */
    public OrdineAcquisto emettiOrdine(Integer idScheda) {
        // TODO implement here
        return null;
    }

    /**
     * Aggiorna ordine e scheda (registraRicezioneOrdine) e salva la schedaAssociata con schedaService.salva(...), in un'unica transazione.
     * @param idOrdine
     */
    public void registraRicezioneOrdine(Integer idOrdine) {
        // TODO implement here
    }

}