package it.unibg.techrepair.servizi;

import it.unibg.techrepair.repository.ClienteRepository;
import it.unibg.techrepair.repository.DispositivoRepository;
import it.unibg.techrepair.dominio.Cliente;
import it.unibg.techrepair.dominio.Dispositivo;


/**
 * Use case Accetta Dispositivo e Crea Scheda (anagrafica).
 */
public class ClienteService {

    /**
     * Default constructor
     */
    public ClienteService() {
    }

    /**
     * 
     */
    private ClienteRepository clienti;

    /**
     * 
     */
    private DispositivoRepository dispositivi;

    /**
     * @param clienti 
     * @param dispositivi
     */
    public ClienteService(ClienteRepository clienti, DispositivoRepository dispositivi) {
        // TODO implement here
    }

    /**
     * @param codiceFiscale 
     * @return
     */
    public Cliente cercaPerCodiceFiscale(String codiceFiscale) {
        // TODO implement here
        return null;
    }

    /**
     * @param nome 
     * @param cognome 
     * @param codiceFiscale 
     * @param telefono 
     * @param email 
     * @return
     */
    public Cliente registraCliente(String nome, String cognome, String codiceFiscale, String telefono, String email) {
        // TODO implement here
        return null;
    }

    /**
     * @param cliente 
     * @param seriale 
     * @param marca 
     * @param modello 
     * @return
     */
    public Dispositivo registraDispositivo(Cliente cliente, String seriale, String marca, String modello) {
        // TODO implement here
        return null;
    }

}