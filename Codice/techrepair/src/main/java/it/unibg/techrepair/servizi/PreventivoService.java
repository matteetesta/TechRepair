package it.unibg.techrepair.servizi;

import it.unibg.techrepair.repository.RicambioRepository;
import it.unibg.techrepair.dominio.Ricambio;

import java.io.*;
import java.util.*;

/**
 * Use case Invia Preventivo (lato Tecnico).
 */
public class PreventivoService {

    /**
     * Default constructor
     */
    public PreventivoService() {
    }

    /**
     * 
     */
    private SchedaService schedaService;

    /**
     * 
     */
    private RicambioRepository ricambi;

    /**
     * @param schedaService 
     * @param ricambi
     */
    public PreventivoService(SchedaService schedaService, RicambioRepository ricambi) {
        // TODO implement here
    }

    /**
     * @param idScheda 
     * @param costo
     */
    public void impostaCostoManodopera(Integer idScheda, BigDecimal costo) {
        // TODO implement here
    }

    /**
     * @param idScheda 
     * @param codiceRicambio
     */
    public void aggiungiRicambio(Integer idScheda, String codiceRicambio) {
        // TODO implement here
    }

    /**
     * @param idScheda 
     * @param codiceRicambio
     */
    public void rimuoviRicambio(Integer idScheda, String codiceRicambio) {
        // TODO implement here
    }

    /**
     * @param idScheda
     */
    public void richiediVerificaMagazzino(Integer idScheda) {
        // TODO implement here
    }

    /**
     * @param idScheda
     */
    public void inviaPreventivo(Integer idScheda) {
        // TODO implement here
    }

    /**
     * @param idScheda 
     * @param codiciDaAggiungere 
     * @param codiciDaRimuovere 
     * @param costoManodopera
     */
    public void modificaPreventivo(Integer idScheda, Set<String> codiciDaAggiungere, Set<String> codiciDaRimuovere, BigDecimal costoManodopera) {
        // TODO implement here
    }

    /**
     * @param idScheda
     */
    public void approvaPreventivo(Integer idScheda) {
        // TODO implement here
    }

    /**
     * @param idScheda
     */
    public void rifiutaPreventivo(Integer idScheda) {
        // TODO implement here
    }

    /**
     * @param codiceRicambio 
     * @return
     */
    private Ricambio trovaRicambio(String codiceRicambio) {
        // TODO implement here
        return null;
    }

}