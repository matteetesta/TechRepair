package it.unibg.techrepair.repository;

import it.unibg.techrepair.dominio.Dispositivo;

import java.io.*;
import java.util.*;

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