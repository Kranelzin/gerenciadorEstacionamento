package enums;

/**
 *
 * @author marce
 */
public enum TipoCadastro {
    
    EMPRESA (0, "Empresa"),
    ADMIN (1, "Admin"),
    FUNCIONARIO (2, "Funcionario"),
    CLIENTE (3, "Cliente"),
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
