package it.unibg.techrepair.repository;

import it.unibg.techrepair.dominio.Cliente;


/**
 * 
 */
public interface ClienteRepository {


    /**
     * @param cliente
     */
    public void salva(Cliente cliente);

    /**
     * @param codiceFiscale 
     * @return
     */
    public Cliente trovaPerCodiceFiscale(String codiceFiscale);

}