package objetos;

import Repositorio.RetornoCep;
import Repositorio.Json;
import Repositorio.RequisicaoHttps;
import Repositorio.Retorno;
import banco.comandos.Conexao;
import banco.comandos.Consulta;
import enums.Estados;
import enums.MetodoRequisicao;
import enums.TipoConteudo;
import enums.TipoEndereco;
import exceptions.RequisicaoException;
import java.io.IOException;

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
    private String cidade;
    private Estados uf;
    
    public Endereco(){};
    
    public Endereco(
        TipoEndereco tipo,
        int numero, 
        String complemento
    ){
        this.tipo = tipo;
        this.numero = numero;
        this.complemento = complemento;
    };
    
    public Endereco(
        TipoEndereco tipo,
        int cep, 
        String logradouro, 
        int numero, 
        String complemento, 
        String bairro, 
        String cidade, 
        Estados uf
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

    public String getCidade() {
        return cidade;
    }

    public Estados getUf() {
        return uf;
    }
    
    public void pesquisarEndereco(String cep){
    
        try{
            RequisicaoHttps requisicao = new RequisicaoHttps(
                "https://viacep.com.br/ws/" + cep + "/json/",
                MetodoRequisicao.GET,
                TipoConteudo.RAW
            );

            Retorno retorno = requisicao.executarRequisicao();

            if(retorno.isHouveErro())
                return;
            
            RetornoCep retornoCep = Json.toObject(retorno.getDados(), RetornoCep.class);
        
            this.cep = Integer.parseInt(cep);
            this.cidade = retornoCep.localidade;
            this.bairro = retornoCep.bairro;
            this.uf = Estados.obterPorDescricao(retornoCep.uf);
            this.logradouro = retornoCep.logradouro;
        }
        catch(IOException e){
            throw new RequisicaoException("Falha ao pesquisar cep: " + e.getMessage());
        }
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
        
        this.uf = Estados.obterPorDescricao(uf);
        this.cidade = cidade;
        this.logradouro = rua;
        
    }

    public boolean temCep() {
        return cep != 0;
    }
}
