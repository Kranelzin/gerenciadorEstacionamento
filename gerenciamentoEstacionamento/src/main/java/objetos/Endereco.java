package objetos;

/**
 *
 * @author marce
 */

public class Endereco {
    
    private final String tipo;
    private final int cep;
    private final String logradouro;
    private final int numero;
    private final String complemento;
    private final String bairro;
    private final String cidade;
    private final String uf;
    
    public Endereco(
        String tipo,
        int cep, 
        String logradouro, 
        int numero, 
        String complemento, 
        String bairro, 
        String cidade, 
        String uf
        ) {
        this.tipo = tipo;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getTipo(){
        return tipo;
    }
    
    public int getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }
}
