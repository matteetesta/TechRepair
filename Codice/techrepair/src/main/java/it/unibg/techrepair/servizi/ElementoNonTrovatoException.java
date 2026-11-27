package it.unibg.techrepair.servizi;

import java.lang.RuntimeException;


/**
 * Identificativo o criterio inesistente.
 */
public class ElementoNonTrovatoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public ElementoNonTrovatoException() {
    }

    /**
     * @param messaggio
     */
    public ElementoNonTrovatoException(String messaggio) {
        // TODO implement here
    }

}