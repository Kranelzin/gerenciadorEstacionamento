package Repositorio;

/**
 *
 * @author marce
 */
public class Retorno {
    private boolean houveErro;
    private String mensagem;
    private String dados;

    public void setHouveErro(boolean houveErro) {
        this.houveErro = houveErro;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public boolean isHouveErro() {
        return houveErro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDados() {
        return dados;
    }
    
    public void definirErro(String mensagem){
        houveErro = true;
        this.mensagem = mensagem;
    }
    
}
