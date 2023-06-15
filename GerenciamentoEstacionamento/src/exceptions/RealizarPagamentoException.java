package exceptions;

import java.math.BigDecimal;

/**
 *
 * @author marce
 */
public class RealizarPagamentoException extends Exception{
    
    public RealizarPagamentoException(BigDecimal valorMensalidade){
        super("O valor pago pela mensalidade está incorreto! Valor da mensalidade: " + valorMensalidade);
    }
    
    public RealizarPagamentoException(String mensagem){
        super(mensagem);
    }
}
