package it.unibg.techrepair.repository;

import it.unibg.techrepair.dominio.OrdineAcquisto;

import java.io.*;
import java.util.*;

/**
 * 
 */
public interface OrdineRepository {


    /**
     * @param ordine
     */
    public void salva(OrdineAcquisto ordine);

    /**
     * @param idOrdine 
     * @return
     */
    public OrdineAcquisto trovaPerId(Integer idOrdine);

    /**
     * @param idScheda 
     * @return
     */
    public OrdineAcquisto trovaPerScheda(Integer idScheda);

}