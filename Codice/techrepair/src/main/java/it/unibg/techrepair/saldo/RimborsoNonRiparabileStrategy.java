package it.unibg.techrepair.saldo;

import java.math.BigDecimal;

/**
 * Restituisce -10,00 (rimborso della diagnosi).
 */
public class RimborsoNonRiparabileStrategy implements CalcoloSaldoStrategy {

    /**
     * Default constructor
     */
    public RimborsoNonRiparabileStrategy() {
    }

    /**
     * @param totalePreventivo 
     * @return
     */
    @Override
    public BigDecimal calcolaSaldo(BigDecimal totalePreventivo) {
        // TODO implement here
        return null;
    }

}