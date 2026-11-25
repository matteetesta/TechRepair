package it.unibg.techrepair.notifica;

import it.unibg.techrepair.dominio.SchedaObserver;
import it.unibg.techrepair.dominio.SchedaRiparazione;
import it.unibg.techrepair.dominio.EventoScheda;

import java.io.*;
import java.util.*;

/**
 * Registra tramite Log4j (logger dedicato con ConsoleAppender e FileAppender) le notifiche simulate al cliente.
 */
public class NotificaClienteObserver implements SchedaObserver {

    /**
     * Default constructor
     */
    public NotificaClienteObserver() {
    }

    /**
     * 
     */
    private static Logger LOGGER = LogManager.getLogger(NotificaClienteObserver.class);

    /**
     * @param scheda 
     * @param evento
     */
    public void aggiorna(SchedaRiparazione scheda, EventoScheda evento) {
        // TODO implement here
    }

    /**
     * @param scheda 
     * @param evento 
     * @return
     */
    private String componiMessaggio(SchedaRiparazione scheda, EventoScheda evento) {
        // TODO implement here
        return "";
    }

    /**
     * @param scheda 
     * @param evento
     */
    public void aggiorna(SchedaRiparazione scheda, EventoScheda evento) {
        // TODO implement SchedaObserver.aggiorna() here
    }

}