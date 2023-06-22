package objetos;

import enums.TipoEndereco;

/**
 *
 * @author marce
 */

public class Endereco {
    
    private TipoEndereco tipo;
    private int cep;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private CidadeEstado cidadeEstado;
    
    public Endereco(){};
    
    public Endereco(
        int cep,
        String logradouro, 
        CidadeEstado cidadeEstado,
        String bairro
    ){
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidadeEstado = cidadeEstado;
        this.bairro = bairro;
    };
    
    public Endereco(
        TipoEndereco tipo,
        int cep, 
        String logradouro, 
        int numero, 
        String complemento, 
        String bairro, 
        CidadeEstado cidadeEstado 
    ){
        this.tipo = tipo;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidadeEstado = cidadeEstado;
    }

    public TipoEndereco getTipo(){
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

    public CidadeEstado getCidadeEstado() {
        return cidadeEstado;
    }

    public boolean temCep() {
        return cep != 0;
    }
}
