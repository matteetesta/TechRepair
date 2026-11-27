package it.unibg.techrepair.dominio;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 
 */
public class Pagamento {

    /**
     * Default constructor
     */
    public Pagamento() {
    }

    /**
     * 
     */
    private Integer idPagamento;

    /**
     * 
     */
    private LocalDate data;

    /**
     * 
     */
    private BigDecimal importo;

    /**
     * 
     */
    private TipoPagamento tipo;


    /**
     * @param importo 
     * @param tipo 
     * @param data
     */
    public Pagamento(BigDecimal importo, TipoPagamento tipo, LocalDate data) {
        // TODO implement here
    }

    /**
     * Assegna l'identificativo generato dal database (layer persistenza).
     * @param idPagamento
     */
    public void setIdPagamento(Integer idPagamento) {
        // TODO implement here
    }

}