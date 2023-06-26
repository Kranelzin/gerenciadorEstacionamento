package objetos;

/**
 *
 * @author marce
 */

import enums.Meses;
import java.math.BigDecimal;
import java.util.Date;

public class PagamentoMensalidade extends Pagamento{
    private final Meses mesReferencia;
    private final int anoReferencia;
    private final boolean pago;
    
    public PagamentoMensalidade(
            BigDecimal valor,
            Date dataPagamento,
            Meses mesReferencia,
            int anoReferencia

    ){
        super(valor, dataPagamento);
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        pago = dataPagamento != null;
    }
    
    public Meses getMesReferencia(){
        return mesReferencia;
    }
    
    public int getAnoReferencia(){
        return anoReferencia;
    }
    
    public boolean getPago(){
        return pago;
    }
}
