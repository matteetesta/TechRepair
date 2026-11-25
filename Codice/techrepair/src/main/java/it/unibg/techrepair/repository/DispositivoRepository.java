package it.unibg.techrepair.repository;

import it.unibg.techrepair.dominio.Dispositivo;


/**
 * 
 */
public interface DispositivoRepository {



    /**
     * @param dispositivo
     */
    public void salva(Dispositivo dispositivo);

    /**
     * @param seriale 
     * @return
     */
    public Dispositivo trovaPerSeriale(String seriale);

    /**
     * @param seriale 
     * @return
     */
    public Boolean esisteSeriale(String seriale);

}