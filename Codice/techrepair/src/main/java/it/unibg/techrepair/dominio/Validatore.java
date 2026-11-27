package it.unibg.techrepair.dominio;

import java.math.BigDecimal;

/**
 * Classe di utilita' (final) con le regole di validazione dell'input (Specifica sez. 7, RF-18). Ogni metodo solleva DatiNonValidiException.
 */
public final class Validatore {

    /**
     * 
     */
    private Validatore() {
        // TODO implement here
    }

    /**
     * @param valore 
     * @param campo
     */
    public static void validaObbligatorio(String valore, String campo) {
        // TODO implement here
    }

    /**
     * @param codiceFiscale
     */
    public static void validaCodiceFiscale(String codiceFiscale) {
        // TODO implement here
    }

    /**
     * @param telefono
     */
    public static void validaTelefono(String telefono) {
        // TODO implement here
    }

    /**
     * @param email
     */
    public static void validaEmail(String email) {
        // TODO implement here
    }

    /**
     * @param prezzo
     */
    public static void validaPrezzoUnitario(BigDecimal prezzo) {
        // TODO implement here
    }

    /**
     * costo non nullo, >= 0,00 e con al piu' 2 decimali. Il vincolo 'manodopera > 0 se il preventivo non ha ricambi' e' verificato da Preventivo.isInviabile() e Preventivo.invia().
     * @param costo
     */
    public static void validaCostoManodopera(BigDecimal costo) {
        // TODO implement here
    }

    /**
     * @param quantita
     */
    public static void validaIncrementoGiacenza(Integer quantita) {
        // TODO implement here
    }

    /**
     * @param giacenza 
     * @param reperibilita
     */
    public static void validaCoerenzaGiacenza(Integer giacenza, Reperibilita reperibilita) {
        // TODO implement here
    }

}