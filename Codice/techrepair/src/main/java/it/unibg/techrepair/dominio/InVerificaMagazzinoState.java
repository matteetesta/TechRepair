package it.unibg.techrepair.dominio;

import java.io.*;
import java.util.*;

/**
 * 
 */
class InVerificaMagazzinoState extends StatoSchedaState {

    /**
     * Default constructor
     */
    InVerificaMagazzinoState() {
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
     */
    public void confermaRicambiOrdinabili(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @param scheda 
     * @param ricambio
     */
    public void segnalaFuoriProduzione(SchedaRiparazione scheda, Ricambio ricambio) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    public void rinuncia(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @return
     */
    public abstract StatoScheda getStato();

}