package it.unibg.techrepair.dominio;

import java.io.*;
import java.util.*;

/**
 * Senza identificativo proprio: il costruttore pubblico VoceRicambio(ricambio, stato) basta anche per la ricostruzione dal database (non tocca la giacenza).
 */
public class VoceRicambio {

    /**
     * Default constructor
     */
    public VoceRicambio() {
    }

    /**
     * 
     */
    private StatoVoce stato;


    /**
     * 
     */
    private Ricambio ricambio;

    /**
     * @param ricambio 
     * @param stato
     */
    public VoceRicambio(Ricambio ricambio, StatoVoce stato) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Boolean isImpegnato() {
        // TODO implement here
        return null;
    }

    /**
     * 
     */
    public void segnaImpegnato() {
        // TODO implement here
    }

    /**
     * @return
     */
    public BigDecimal getPrezzo() {
        // TODO implement here
        return null;
    }

}