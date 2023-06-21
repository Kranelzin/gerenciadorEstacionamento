package enums;

/**
 *
 * @author marce
 */
public enum TipoUsuario {
    
    CLIENTE(0, "CLI"),
    FUNCIONARIO(1, "FUN"),
    ADMIN(2, "ADM");
    

    private final int indice;
    private final String descricao;

    TipoUsuario(int indice, String descricao) {
        this.indice = indice;
        this.descricao = descricao;
    }

    public int getIndice() {
        return indice;
    }

    public String getDescricao() {
        return descricao;
    }
   
    public static TipoUsuario obterPorIndice(int indice) {
        TipoUsuario[] usuarios = TipoUsuario.values();
        
        for (TipoUsuario usuario : usuarios) {
            if (usuario.indice == indice)
                return usuario;
        }
        throw new IllegalArgumentException("Não foi encontrado nenhum tipo de telefone com o índice fornecido.");
    }

}
