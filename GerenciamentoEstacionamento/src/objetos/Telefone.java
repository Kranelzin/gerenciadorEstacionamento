package objetos;

import enums.TipoTelefone;

/**
 *
 * @author marce
 */

public class Telefone {
    private final String numero;
    private final TipoTelefone tipo;
    private final boolean temWhats;
    
    public Telefone(
        String numero, 
        TipoTelefone tipo, 
        boolean temWhats
    ) {
        this.numero = numero;
        this.tipo = tipo;
        this.temWhats = temWhats;
    }

    public String getNumero() {
        return numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public boolean temWhats() {
        return temWhats;
    }
    
    
}
