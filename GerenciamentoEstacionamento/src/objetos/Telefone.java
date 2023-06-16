package objetos;

/**
 *
 * @author marce
 */

public class Telefone {
    private final int numero;
    private final int tipo; //default 0;  0 = Celular, 1 = Fixo;
    private final boolean temWhats;
    
    public Telefone(
        int numero, 
        int tipo, 
        boolean temWhats
    ) {
        this.numero = numero;
        this.tipo = tipo;
        this.temWhats = temWhats;
    }

    public int getNumero() {
        return numero;
    }

    public int getTipo() {
        return tipo;
    }

    public boolean temWhats() {
        return temWhats;
    }
    
    
}
