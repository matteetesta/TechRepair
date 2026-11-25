package it.unibg.techrepair.dominio;

import java.util.Set;

/**
 * 
 */
public class Cliente {

    /**
     * Default constructor
     */
    public Cliente() {
    }

    /**
     * 
     */
    private Integer idCliente;

    /**
     * 
     */
    private String nome;

    /**
     * 
     */
    private String cognome;

    /**
     * 
     */
    private String codiceFiscale;

    /**
     * 
     */
    private String telefono;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private Set<Dispositivo> dispositivi;

    /**
     * @param nome 
     * @param cognome 
     * @param codiceFiscale 
     * @param telefono 
     * @param email
     */
    public Cliente(String nome, String cognome, String codiceFiscale, String telefono, String email) {
        // TODO implement here
    }

    /**
     * Invocato solo dal costruttore di Dispositivo.
     * @param dispositivo
     */
    void aggiungiDispositivo(Dispositivo dispositivo) {
        // TODO implement here
    }

    /**
     * Assegna l'identificativo generato dal database (layer persistenza).
     * @param idCliente
     */
    public void setIdCliente(Integer idCliente) {
        // TODO implement here
    }

}