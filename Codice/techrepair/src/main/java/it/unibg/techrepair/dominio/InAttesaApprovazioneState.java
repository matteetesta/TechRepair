package it.unibg.techrepair.dominio;

import java.util.Set;
import java.math.BigDecimal;

/**
 * 
 */
class InAttesaApprovazioneState extends StatoSchedaState {

    /**
     * Default constructor
     */
    InAttesaApprovazioneState() {
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
     * @param aggiunte 
     * @param rimozioni 
     * @param costoManodopera
     */
    @Override
    public void modificaPreventivo(SchedaRiparazione scheda, Set<Ricambio> aggiunte, Set<Ricambio> rimozioni, BigDecimal costoManodopera) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    @Override
    public void approvaPreventivo(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * @param scheda
     */
    @Override
    public void rifiutaPreventivo(SchedaRiparazione scheda) {
        // TODO implement here
    }


}