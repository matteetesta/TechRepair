package it.unibg.techrepair.dominio;

import java.io.*;
import java.util.*;

/**
 * 
 */
class InAttesaApprovazioneState extends StatoSchedaState {

    /**
     * Default constructor
     */
    InAttesaApprovazioneState() {
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
     * @param aggiunte 
     * @param rimozioni 
     * @param costoManodopera
     */
    public void modificaPreventivo(SchedaRiparazione scheda, Set<Ricambio> aggiunte, Set<Ricambio> rimozioni, BigDecimal costoManodopera) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    public void approvaPreventivo(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    public void rifiutaPreventivo(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @return
     */
    public abstract StatoScheda getStato();

}