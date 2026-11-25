package it.unibg.techrepair.dominio;

/**
 * 
 */
class InRiparazioneState extends StatoSchedaState {

    /**
     * Default constructor
     */
    InRiparazioneState() {
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
     * @param nuovoSeriale
     */
    @Override
    public void aggiornaSeriale(SchedaRiparazione scheda, String nuovoSeriale) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    @Override
    public void chiudiRiparazione(SchedaRiparazione scheda) {
        // TODO implement here
    }


}