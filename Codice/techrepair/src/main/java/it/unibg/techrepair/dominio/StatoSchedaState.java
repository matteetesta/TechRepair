package it.unibg.techrepair.dominio;

import java.util.Set;
import java.math.BigDecimal;

/**
 * State: comportamento della SchedaRiparazione nello stato corrente. L'implementazione di default di ogni evento solleva OperazioneNonConsentitaException; gli stati concreti ridefiniscono solo le transizioni ammesse (Specifica sez. 5).
 */
abstract class StatoSchedaState {

    /**
     * Default constructor
     */
    StatoSchedaState() {
    }


    /**
     * @return
     */
    public abstract StatoScheda getStato();

    /**
     * Default: throw nonConsentita(scheda, "avviaDiagnosi"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda
     */
    public void avviaDiagnosi(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "registraDiagnosi"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda 
     * @param diagnosi
     */
    public void registraDiagnosi(SchedaRiparazione scheda, String diagnosi) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "impostaCostoManodopera"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda 
     * @param costo
     */
    public void impostaCostoManodopera(SchedaRiparazione scheda, BigDecimal costo) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "aggiungiRicambio"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda 
     * @param ricambio
     */
    public void aggiungiRicambio(SchedaRiparazione scheda, Ricambio ricambio) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "rimuoviRicambio"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda 
     * @param ricambio
     */
    public void rimuoviRicambio(SchedaRiparazione scheda, Ricambio ricambio) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "richiediVerificaMagazzino"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda
     */
    public void richiediVerificaMagazzino(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "confermaRicambiOrdinabili"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda
     */
    public void confermaRicambiOrdinabili(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "segnalaFuoriProduzione"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda 
     * @param ricambio
     */
    public void segnalaFuoriProduzione(SchedaRiparazione scheda, Ricambio ricambio) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "inviaPreventivo"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda
     */
    public void inviaPreventivo(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "modificaPreventivo"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda 
     * @param aggiunte 
     * @param rimozioni 
     * @param costoManodopera
     */
    public void modificaPreventivo(SchedaRiparazione scheda, Set<Ricambio> aggiunte, Set<Ricambio> rimozioni, BigDecimal costoManodopera) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "approvaPreventivo"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda
     */
    public void approvaPreventivo(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "rifiutaPreventivo"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda
     */
    public void rifiutaPreventivo(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "rinuncia"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda
     */
    public void rinuncia(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "registraOrdine"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda 
     * @param ordine
     */
    public void registraOrdine(SchedaRiparazione scheda, OrdineAcquisto ordine) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "registraRicezioneOrdine"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda
     */
    public void registraRicezioneOrdine(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "aggiornaSeriale"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda 
     * @param nuovoSeriale
     */
    public void aggiornaSeriale(SchedaRiparazione scheda, String nuovoSeriale) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "chiudiRiparazione"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda
     */
    public void chiudiRiparazione(SchedaRiparazione scheda) {
        // TODO implement here
    }

    /**
     * Default: throw nonConsentita(scheda, "consegna"); ridefinito solo dagli stati che ammettono l'evento.
     * @param scheda 
     * @return
     */
    public Pagamento consegna(SchedaRiparazione scheda) {
        // TODO implement here
        return null;
    }

    /**
     * Ricostruisce l'oggetto stato dal valore persistito statoCorrente.
     * @param stato 
     * @return
     */
    public static StatoSchedaState daStato(StatoScheda stato) {
        // TODO implement here
        return null;
    }

    /**
     * @param scheda 
     * @param operazione 
     * @return
     */
    protected OperazioneNonConsentitaException nonConsentita(SchedaRiparazione scheda, String operazione) {
        // TODO implement here
        return null;
    }

}