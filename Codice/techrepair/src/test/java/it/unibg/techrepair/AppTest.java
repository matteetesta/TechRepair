package it.unibg.techrepair;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 * Test di base per verifica avvio applicazione e configurazione Log4j2.
 */
public class AppTest {

    @Test
    public void log4jShouldBeConfiguredAtInfoLevel() {
        Logger logger = LogManager.getLogger(App.class);
        assertNotNull(logger);
        assertTrue(logger.isInfoEnabled());
    }

    @Test
    public void appMainShouldRunWithoutErrors() {
        assertDoesNotThrow(() -> App.main(new String[0]));
    }
}
