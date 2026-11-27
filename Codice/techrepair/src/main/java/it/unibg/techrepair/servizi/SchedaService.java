package it.unibg.techrepair.servizi;

import it.unibg.techrepair.repository.SchedaRepository;
import it.unibg.techrepair.dominio.SchedaObserver;
import it.unibg.techrepair.repository.DispositivoRepository;
import it.unibg.techrepair.dominio.SchedaRiparazione;
import it.unibg.techrepair.dominio.Dispositivo;
import it.unibg.techrepair.dominio.Pagamento;
import it.unibg.techrepair.dominio.StatoScheda;

import java.util.List;

/**
 * Ciclo di vita della scheda lato Tecnico: accettazione, diagnosi, riparazione, consegna e consultazione.
 */
public class SchedaService {

    /**
     * Default constructor
     */
    public SchedaService() {
    }

    /**
     * 
     */
    private SchedaRepository schede;

    /**
     * 
     */
    private SchedaObserver osservatore;

    /**
     * 
     */
    private DispositivoRepository dispositivi;



    /**
     * @param schede 
     * @param dispositivi 
     * @param osservatore
     */
    public SchedaService(SchedaRepository schede, DispositivoRepository dispositivi, SchedaObserver osservatore) {
        // TODO implement here
    }

    /**
     * @param dispositivo 
     * @param guastoDichiarato 
     * @return
     */
    public SchedaRiparazione apriScheda(Dispositivo dispositivo, String guastoDichiarato) {
        // TODO implement here
        return null;
    }

    /**
     * @param idScheda 
     * @return
     */
    public String generaRicevutaIngresso(Integer idScheda) {
        // TODO implement here
        return "";
    }

    /**
     * @param idScheda
     */
    public void avviaDiagnosi(Integer idScheda) {
        // TODO implement here
    }

    /**
     * @param idScheda 
     * @param diagnosi
     */
    public void registraDiagnosi(Integer idScheda, String diagnosi) {
        // TODO implement here
    }

    /**
     * @param idScheda
     */
    public void registraRinuncia(Integer idScheda) {
        // TODO implement here
    }

    /**
     * @param idScheda 
     * @param nuovoSeriale
     */
    public void aggiornaSeriale(Integer idScheda, String nuovoSeriale) {
        // TODO implement here
    }

    /**
     * @param idScheda
     */
    public void chiudiRiparazione(Integer idScheda) {
        // TODO implement here
    }

    /**
     * @param idScheda 
     * @return
     */
    public Pagamento consegnaDispositivo(Integer idScheda) {
        // TODO implement here
        return null;
    }

    /**
     * @param idScheda 
     * @return
     */
    public SchedaRiparazione cercaPerId(Integer idScheda) {
        // TODO implement here
        return null;
    }

    /**
     * @param codiceFiscale 
     * @return
     */
    public List<SchedaRiparazione> cercaPerCodiceFiscale(String codiceFiscale) {
        // TODO implement here
        return null;
    }

    /**
     * @param seriale 
     * @return
     */
    public List<SchedaRiparazione> cercaPerSeriale(String seriale) {
        // TODO implement here
        return null;
    }

    /**
     * @param stato 
     * @return
     */
    public List<SchedaRiparazione> cercaPerStato(StatoScheda stato) {
        // TODO implement here
        return null;
    }

    /**
     * @param scheda
     */
    void salva(SchedaRiparazione scheda) {
        // TODO implement here
    }

}