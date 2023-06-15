package exceptions;

import objetos.Veiculo;

/**
 *
 * @author marce
 */
public class EstacionarVagaException extends Exception {
    
    public EstacionarVagaException(String mensagem){
        super(mensagem);
    }
    
    public EstacionarVagaException(boolean emUso, Veiculo veiculo) {
        super(emUso ? "A vaga já está em uso, pelo veiculo de placa: " + veiculo.getPlaca() : "A vaga já está liberada");
    }
}

