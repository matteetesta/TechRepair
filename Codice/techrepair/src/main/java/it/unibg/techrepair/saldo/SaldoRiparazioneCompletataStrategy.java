package it.unibg.techrepair.saldo;

import java.math.BigDecimal;

/**
 * Restituisce max(0,00; totale - 10,00).
 */
public class SaldoRiparazioneCompletataStrategy implements CalcoloSaldoStrategy {

    /**
     * Default constructor
     */
    public SaldoRiparazioneCompletataStrategy() {
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