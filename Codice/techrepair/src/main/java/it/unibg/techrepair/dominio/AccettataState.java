package it.unibg.techrepair.dominio;

import java.io.*;
import java.util.*;

/**
 * 
 */
class AccettataState extends StatoSchedaState {

    /**
     * Default constructor
     */
    AccettataState() {
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
    public void avviaDiagnosi(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @param scheda 
     * @param costo
     */
    public void impostaCostoManodopera(SchedaRiparazione scheda, BigDecimal costo) {
        // TODO implement here
    }

    /**
     * @param scheda 
     * @param ricambio
     */
    public void aggiungiRicambio(SchedaRiparazione scheda, Ricambio ricambio) {
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