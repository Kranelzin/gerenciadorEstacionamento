package objetos;

import enums.Estados;

/**
 *
 * @author marce
 */
public class CidadeEstado {
    private int cidadeId;
    private String cidade;
    private String codIbge;
    private Estados estado;

    public CidadeEstado(
        int cidadeId, 
        String cidade, 
        String codIbge, 
        Estados estado
    ){
        this.cidadeId = cidadeId;
        this.cidade = cidade;
        this.codIbge = codIbge;
        this.estado = estado;
    }

    public int getCidadeId() {
        return cidadeId;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCodIbge() {
        return codIbge;
    }

    public Estados getEstado() {
        return estado;
    }
    
}
