package it.unibg.techrepair.dominio;

import java.lang.RuntimeException;

import java.io.*;
import java.util.*;

/**
 * Sollevata da StatoSchedaState per le transizioni non ammesse.
 */
public class OperazioneNonConsentitaException extends RuntimeException {

    /**
     * Default constructor
     */
    public OperazioneNonConsentitaException() {
    }

    /**
     * @param statoCorrente 
     * @param operazione
     */
    public OperazioneNonConsentitaException(StatoScheda statoCorrente, String operazione) {
        // TODO implement here
    }

    /**
     * Per i vincoli di dominio non legati allo stato della scheda (preventivo bloccato, giacenza esaurita, scheda gia' attiva sul dispositivo).
     * @param messaggio
     */
    public OperazioneNonConsentitaException(String messaggio) {
        // TODO implement here
    }

}