package it.unibg.techrepair.dominio;

import java.lang.RuntimeException;


/**
 * Sollevata dal Validatore (RF-18).
 */
public class DatiNonValidiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public DatiNonValidiException() {
    }

    /**
     * @param messaggio
     */
    public DatiNonValidiException(String messaggio) {
        // TODO implement here
    }

}