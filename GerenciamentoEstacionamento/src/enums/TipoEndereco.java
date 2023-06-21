package enums;

/**
 *
 * @author marce
 */
public enum TipoEndereco {
    
    RESIDENCIAL(0, "RD"),
    COMERCIAL(1, "CO");
   
    private final int indice;
    private final String descricao;

    TipoEndereco(int indice, String descricao) {
        this.indice = indice;
        this.descricao = descricao;
    }

    public int getIndice() {
        return indice;
    }

    public String getDescricao() {
        return descricao;
    }
   
     public static TipoEndereco obterPorIndice(int indice) {
        TipoEndereco[] enderecos = TipoEndereco.values();
        
        for (TipoEndereco endereco : enderecos) {
            if (endereco.indice == indice)
                return endereco;
        }
        throw new IllegalArgumentException("Não foi encontrado nenhum tipo de endereco com o índice fornecido.");
    }

}
