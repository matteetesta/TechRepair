package it.unibg.techrepair.dominio;

import java.math.BigDecimal;

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
    @Override
    public StatoScheda getStato() {
        // TODO implement here
        return null;
    }

    /**
     * @param scheda 
     * @param diagnosi
     */
    @Override
    public void registraDiagnosi(SchedaRiparazione scheda, String diagnosi) {
        // TODO implement here
    }

    /**
     * @param scheda 
     * @param costo
     */
    @Override
    public void impostaCostoManodopera(SchedaRiparazione scheda, BigDecimal costo) {
        // TODO implement here
    }

    /**
     * @param scheda 
     * @param ricambio
     */
    @Override
    public void aggiungiRicambio(SchedaRiparazione scheda, Ricambio ricambio) {
        // TODO implement here
    }

    /**
     * @param scheda 
     * @param ricambio
     */
    @Override
    public void rimuoviRicambio(SchedaRiparazione scheda, Ricambio ricambio) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    @Override
    public void richiediVerificaMagazzino(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    @Override
    public void inviaPreventivo(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    @Override
    public void rinuncia(SchedaRiparazione scheda) {
        // TODO implement here
    }


}