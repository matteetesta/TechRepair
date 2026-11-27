package it.unibg.techrepair.saldo;

import java.math.BigDecimal;

/**
 * Restituisce 0,00.
 */
public class SaldoSchedaAnnullataStrategy implements CalcoloSaldoStrategy {

    /**
     * Default constructor
     */
    public SaldoSchedaAnnullataStrategy() {
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