package it.unibg.techrepair.dominio;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Dispositivo {

    /**
     * Default constructor
     */
    public Dispositivo() {
    }

    /**
     * 
     */
    private Integer idDispositivo;

    /**
     * 
     */
    private String seriale;

    /**
     * 
     */
    private String marca;

    /**
     * 
     */
    private String modello;

    /**
     * 
     */
    private Cliente proprietario;

    /**
     * 
     */
    private List<SchedaRiparazione> storicoSchede;

    /**
     * Invoca proprietario.aggiungiDispositivo(this).
     * @param seriale 
     * @param marca 
     * @param modello 
     * @param proprietario
     */
    public Dispositivo(String seriale, String marca, String modello, Cliente proprietario) {
        // TODO implement here
    }

    /**
     * @return
     */
    public SchedaRiparazione getSchedaAttiva() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Boolean haSchedaAttiva() {
        // TODO implement here
        return null;
    }

    /**
     * Aggiunge la scheda a storicoSchede. Solleva OperazioneNonConsentitaException("Il dispositivo ha gia' una scheda attiva.") solo se la scheda aggiunta e' attiva (scheda.isAttiva()) e il dispositivo ne ha gia' una attiva: le schede CHIUSE caricate dal database si aggiungono sempre.
     * @param scheda
     */
    void aggiungiScheda(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * Invocato solo da InRiparazioneState; l'univocita' e' verificata dal servizio.
     * @param nuovoSeriale
     */
    void aggiornaSeriale(String nuovoSeriale) {
        // TODO implement here
    }

    /**
     * Assegna l'identificativo generato dal database (layer persistenza).
     * @param idDispositivo
     */
    public void setIdDispositivo(Integer idDispositivo) {
        // TODO implement here
    }

}