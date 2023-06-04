package objetos;

/**
 *
 * @author marce
 */

public class Telefone {
    private final int numero;
    private final String tipo;
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
