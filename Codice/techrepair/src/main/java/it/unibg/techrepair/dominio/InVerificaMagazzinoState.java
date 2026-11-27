package it.unibg.techrepair.dominio;

/**
 * 
 */
class InVerificaMagazzinoState extends StatoSchedaState {

    /**
     * Default constructor
     */
    InVerificaMagazzinoState() {
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
    public void confermaRicambiOrdinabili(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @param scheda 
     * @param ricambio
     */
    @Override
    public void segnalaFuoriProduzione(SchedaRiparazione scheda, Ricambio ricambio) {
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