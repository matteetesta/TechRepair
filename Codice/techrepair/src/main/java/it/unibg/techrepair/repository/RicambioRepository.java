package it.unibg.techrepair.repository;

import it.unibg.techrepair.dominio.Ricambio;

import java.util.List;

/**
 * 
 */
public interface RicambioRepository {



    /**
     * @param ricambio
     */
    public void salva(Ricambio ricambio);

    /**
     * @param codiceRicambio 
     * @return
     */
    public Ricambio trovaPerCodice(String codiceRicambio);

    /**
     * @return
     */
    public List<Ricambio> trovaTutti();

}