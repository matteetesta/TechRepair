package it.unibg.techrepair;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Punto di ingresso dell'applicazione Tech Repair.
 */
public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.info("Avvio applicazione Tech Repair");
    }
}
