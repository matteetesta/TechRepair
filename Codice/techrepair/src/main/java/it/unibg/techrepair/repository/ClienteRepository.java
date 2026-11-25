package it.unibg.techrepair.repository;

import it.unibg.techrepair.dominio.Cliente;

import java.io.*;
import java.util.*;

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