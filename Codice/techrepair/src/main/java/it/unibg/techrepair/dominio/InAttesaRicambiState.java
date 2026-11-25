package it.unibg.techrepair.dominio;

import java.io.*;
import java.util.*;

/**
 * 
 */
class InAttesaRicambiState extends StatoSchedaState {

    /**
     * Default constructor
     */
    InAttesaRicambiState() {
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
     * @param ordine
     */
    public void registraOrdine(SchedaRiparazione scheda, OrdineAcquisto ordine) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    public void registraRicezioneOrdine(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @return
     */
    public abstract StatoScheda getStato();

}