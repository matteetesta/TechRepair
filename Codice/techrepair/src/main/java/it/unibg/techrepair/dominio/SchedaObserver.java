package it.unibg.techrepair.dominio;

/**
 * Observer: riceve gli eventi notificabili di una SchedaRiparazione.
 */
public interface SchedaObserver {



    /**
     * @param scheda 
     * @param evento
     */
    public void aggiorna(SchedaRiparazione scheda, EventoScheda evento);

}