package it.unibg.techrepair.dominio;

import java.io.*;
import java.util.*;

/**
 * 
 */
class InRiparazioneState extends StatoSchedaState {

    /**
     * Default constructor
     */
    InRiparazioneState() {
    }

    /**
     * @return
     */
    public StatoScheda getStato() {
        // TODO implement here
        return null;
    }

    /**
     * @param scheda 
     * @param nuovoSeriale
     */
    public void aggiornaSeriale(SchedaRiparazione scheda, String nuovoSeriale) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    public void chiudiRiparazione(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @return
     */
    public abstract StatoScheda getStato();

}