package objetos;

import Repositorio.RetornoCep;
import Repositorio.Json;
import Repositorio.RequisicaoHttps;
import Repositorio.Retorno;
import banco.comandos.Conexao;
import banco.comandos.Consulta;
import enums.MetodoRequisicao;
import enums.TipoConteudo;
import java.io.IOException;

/**
 *
 * @author marce
 */

public class Endereco {
    
    private int tipo; //default 0;  0 = Residencial, 1 = Comercial;
    private int cep;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    
    public Endereco(){};
    
    public Endereco(
        int tipo,
        int numero, 
        String complemento
    ){
        this.tipo = tipo;
        this.numero = numero;
        this.complemento = complemento;
    };
    
    public Endereco(
        int tipo,
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

    public int getTipo(){
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
    
    public void pesquisarEndereco(int cep) throws IOException{
    
        RequisicaoHttps requisicao = new RequisicaoHttps(
                "viacep.com.br/ws/" + cep + "/json/",
                MetodoRequisicao.GET,
                TipoConteudo.RAW
        );
        
        Retorno retorno = requisicao.executarRequisicao();
        
        RetornoCep retornoCep = Json.toListObjectr(retorno.getDados());
        
        this.cep = cep;
        this.cidade = retornoCep.localidade;
        this.bairro = retornoCep.bairro;
        this.uf = retornoCep.uf;
        this.logradouro = retornoCep.logradouro;
    }
    
    public void pesquisarCep(String uf, String cidade, String rua){
        
        Conexao con = new Conexao();
        con.abrirConexao();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  CID.CEP ")
        .append("FROM CIDADE CID")
                
        .append("INNER JOIN ESTADO EST ")
        .append("ON EST.ESTADO_ID = CID.CIDADE_ID ")
                
        .append("WHERE EST.UF = ? ")
        .append("AND CID.NOME = ? ")
        .append("AND CID.LOURADOURO = ? ");
        
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{uf, cidade, rua});
        
        while(!consulta.fimConsulta()){
            this.cep = consulta.getInt("CEP");
            this.bairro = consulta.getString("BAIRRO");
            break;
        }
        
        this.uf = uf;
        this.cidade = cidade;
        this.logradouro = rua;
        
    }
}
