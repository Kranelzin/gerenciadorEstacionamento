package exceptions;

/**
 *
 * @author marce
 */
public class BancoException extends RuntimeException{
    public BancoException (String mensagem){
        super(mensagem);
    }
    
    public BancoException(String mensagem, String coluna){
        super("Erro ao resgatar dado da consulta. Chave de busca inválida: " + coluna+ "\n" + "Erro: " + mensagem);
    }
}
