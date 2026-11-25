package it.unibg.techrepair.dominio;

import java.io.*;
import java.util.*;

/**
 * 
 */
class InDiagnosiState extends StatoSchedaState {

    /**
     * Default constructor
     */
    InDiagnosiState() {
    }

    /**
     * @return
     */
    public StatoScheda getStato() {
        // TODO implement here
        return null;
    }

    /**
     * @param scheda 
     * @param diagnosi
     */
    public void registraDiagnosi(SchedaRiparazione scheda, String diagnosi) {
        // TODO implement here
    }

    /**
     * @param scheda 
     * @param costo
     */
    public void impostaCostoManodopera(SchedaRiparazione scheda, BigDecimal costo) {
        // TODO implement here
    }

    /**
     * @param scheda 
     * @param ricambio
     */
    public void aggiungiRicambio(SchedaRiparazione scheda, Ricambio ricambio) {
        // TODO implement here
    }

    /**
     * @param scheda 
     * @param ricambio
     */
    public void rimuoviRicambio(SchedaRiparazione scheda, Ricambio ricambio) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    public void richiediVerificaMagazzino(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    public void inviaPreventivo(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    public void rinuncia(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @return
     */
    public abstract StatoScheda getStato();

}