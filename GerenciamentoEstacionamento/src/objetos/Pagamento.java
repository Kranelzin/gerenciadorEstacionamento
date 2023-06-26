package objetos;

/**
 *
 * @author marce
 */

import java.math.BigDecimal;
import java.util.Date;

public class Pagamento {
    private final BigDecimal valor;
    private final Date dataPagamento;
    
    public Pagamento(
            BigDecimal valor, 
            Date dataPagamento
    ){
        this.valor = valor;
        this.dataPagamento = dataPagamento;
    }
    
    public BigDecimal getValor(){
        return valor;
    }
    
    public Date getDataPagamento(){
        return dataPagamento;
    }
}
