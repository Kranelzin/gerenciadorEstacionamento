package objetos;

/**
 *
 * @author marce
 */

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Pagamento {
    private final BigDecimal valor;
    private final Timestamp dataPagamento;
    
    public Pagamento(
            BigDecimal valor, 
            Timestamp dataPagamento
    ){
        this.valor = valor;
        this.dataPagamento = dataPagamento;
    }
    
    public BigDecimal getValor(){
        return valor;
    }
    
    public Timestamp getDataPagamento(){
        return dataPagamento;
    }
}
