package enums;

/**
 *
 * @author marce
 */
public enum TipoCadastro {
    
    CLIENTE (0, "Cliente"),
    FUNCIONARIO (1, "Funcionario"),
    ADMIN (2, "Admin"),
    EMPRESA (3, "Empresa"),
    BOXVAGA (4, "BoxVaga");
    
    private int indice;
    private String descricao;
    
    TipoCadastro(int indice, String descricao){
        this.indice = indice;
        this.descricao = descricao;
    }
    
    public int getIndice(){
        return indice;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public static TipoCadastro obterPorIndice(int indice) {
        TipoCadastro[] cadastros = TipoCadastro.values();
        
        for (TipoCadastro cadastro : cadastros) {
            if (cadastro.indice == indice)
                return cadastro;
        }
        
        throw new IllegalArgumentException("Não foi encontrado nenhum tipo de cadastro com o índice fornecido.");
    }
}
