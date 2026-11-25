package it.unibg.techrepair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

/**
 * Test di verifica della configurazione del database embedded SQLite (RI-02, RQ-01, RQ-06).
 */
public class DatabaseSetupTest {

    @Test
    public void sqliteInMemoryConnectionShouldWorkAndPreserveCents() throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:")) {
            assertFalse(conn.isClosed());

            try (Statement st = conn.createStatement()) {
                st.execute("CREATE TABLE prova_importi (id INTEGER PRIMARY KEY, importo_centesimi INTEGER NOT NULL)");
            }

            BigDecimal atteso = new BigDecimal("10.00");
            long centesimi = atteso.movePointRight(2).longValueExact();

            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO prova_importi(id, importo_centesimi) VALUES (?, ?)")) {
                ps.setInt(1, 1);
                ps.setLong(2, centesimi);
                ps.executeUpdate();
            }

            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery("SELECT importo_centesimi FROM prova_importi WHERE id = 1")) {
                assertTrue(rs.next());
                BigDecimal letto = BigDecimal.valueOf(rs.getLong("importo_centesimi"), 2);
                assertEquals(atteso, letto);
            }
        }
    }
}
