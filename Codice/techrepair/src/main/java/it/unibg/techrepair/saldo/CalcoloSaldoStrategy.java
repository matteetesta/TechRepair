package it.unibg.techrepair.saldo;

import java.io.*;
import java.util.*;

/**
 * Strategy: calcola il saldo da registrare alla consegna a partire dal totale del preventivo. Indipendente dalle classi del dominio.
 */
public interface CalcoloSaldoStrategy {

    /**
     * 
     */
    public static BigDecimal COSTO_DIAGNOSI = new BigDecimal("10.00");

    /**
     * @param totalePreventivo 
     * @return
     */
    public BigDecimal calcolaSaldo(BigDecimal totalePreventivo);

}