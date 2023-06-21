package enums;

/**
 *
 * @author marce
 */
public enum TipoTelefone {
    
    FIXO(0, "RD"),
    MOVEL(1, "CO");
    

    private final int indice;
    private final String descricao;

    TipoTelefone(int indice, String descricao) {
        this.indice = indice;
        this.descricao = descricao;
    }

    public int getIndice() {
        return indice;
    }

    public String getDescricao() {
        return descricao;
    }
   
    public static TipoTelefone obterPorIndice(int indice) {
        TipoTelefone[] telefones = TipoTelefone.values();
        
        for (TipoTelefone telefone : telefones) {
            if (telefone.indice == indice)
                return telefone;
        }
        throw new IllegalArgumentException("Não foi encontrado nenhum tipo de telefone com o índice fornecido.");
    }

}
