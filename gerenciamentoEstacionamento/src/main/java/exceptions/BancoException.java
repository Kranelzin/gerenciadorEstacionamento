package exceptions;

/**
 *
 * @author marce
 */
public class BancoException extends RuntimeException{
    public BancoException (String mensagem){
        super(mensagem);
    }
}
