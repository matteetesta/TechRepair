package it.unibg.techrepair.dominio;

import java.io.*;
import java.util.*;

/**
 * 
 */
class NonRiparabileState extends StatoSchedaState {

    /**
     * Default constructor
     */
    NonRiparabileState() {
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
     * @return
     */
    public Pagamento consegna(SchedaRiparazione scheda) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public abstract StatoScheda getStato();

}