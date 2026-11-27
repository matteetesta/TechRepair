package it.unibg.techrepair.dominio;

import java.math.BigDecimal;

/**
 * 
 */
class AccettataState extends StatoSchedaState {

    /**
     * Default constructor
     */
    AccettataState() {
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
     */
    @Override
    public void avviaDiagnosi(SchedaRiparazione scheda) {
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
     */
    @Override
    public void rinuncia(SchedaRiparazione scheda) {
        // TODO implement here
    }


}