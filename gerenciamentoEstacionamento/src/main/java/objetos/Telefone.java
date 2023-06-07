package objetos;

/**
 *
 * @author marce
 */

public class Telefone {
    private final int numero;
    private final String tipo; //default 0;  0 = Celular, 1 = Fixo;
    private final boolean temWhats;
    
    public Telefone(
        int numero, 
        String tipo, 
        boolean temWhats
    ) {
        this.numero = numero;
        this.tipo = tipo;
        this.temWhats = temWhats;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean temWhats() {
        return temWhats;
    }
    
    
}
