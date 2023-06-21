package enums;

/**
 *
 * @author marce
 */
public enum TipoCadastro {
    
    EMPRESA (0, "Empresa"),
    ADMIN (1, "Admin"),
    FUNCIONARIO (2, "Funcionario"),
    CLIENTE (3, "Cliente");
    
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
}
