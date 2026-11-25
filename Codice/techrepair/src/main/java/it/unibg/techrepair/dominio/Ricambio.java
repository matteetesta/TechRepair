package it.unibg.techrepair.dominio;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Ricambio {

    /**
     * Default constructor
     */
    public Ricambio() {
    }

    /**
     * 
     */
    private String codiceRicambio;

    /**
     * 
     */
    private String nome;

    /**
     * 
     */
    private QualitaRicambio qualita;

    /**
     * 
     */
    private BigDecimal prezzoUnitario;

    /**
     * 
     */
    private Integer giacenza;

    /**
     * 
     */
    private Reperibilita reperibilita;



    /**
     * @param codiceRicambio 
     * @param nome 
     * @param qualita 
     * @param prezzoUnitario 
     * @param giacenza 
     * @param reperibilita
     */
    public Ricambio(String codiceRicambio, String nome, QualitaRicambio qualita, BigDecimal prezzoUnitario, Integer giacenza, Reperibilita reperibilita) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Boolean isDisponibile() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Boolean isOrdinabile() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Boolean isFuoriProduzione() {
        // TODO implement here
        return null;
    }

    /**
     * Precondizione giacenza > 0, altrimenti OperazioneNonConsentitaException. giacenza - 1; a 0 la reperibilita' passa a DA_VERIFICARE.
     */
    public void impegna() {
        // TODO implement here
    }

    /**
     * giacenza + 1; la reperibilita' torna DISPONIBILE.
     */
    public void rilascia() {
        // TODO implement here
    }

    /**
     * @param quantita
     */
    public void rifornisci(Integer quantita) {
        // TODO implement here
    }

    /**
     * 
     */
    public void segnaOrdinabile() {
        // TODO implement here
    }

    /**
     * 
     */
    public void segnaFuoriProduzione() {
        // TODO implement here
    }

    /**
     * 
     */
    private void aggiornaReperibilita() {
        // TODO implement here
    }

}