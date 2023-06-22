/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import Repositorio.Json;
import Repositorio.RequisicaoHttps;
import Repositorio.Retorno;
import Repositorio.RetornoCep;
import banco.comandos.Conexao;
import banco.comandos.Consulta;
import enums.Estados;
import enums.MetodoRequisicao;
import enums.TipoConteudo;
import exceptions.RequisicaoException;
import java.io.IOException;
import objetos.CidadeEstado;
import objetos.Endereco;

/**
 *
 * @author marce
 */
public class BuscarEndereco {
    
    public static CidadeEstado buscarCidadeEstado(String cidade, String uf){

        CidadeEstado cidadeEstado = null;
        
        Conexao con = new Conexao();
        con.abrirConexao();

        Consulta consulta = con.novaConsulta();

        StringBuilder sql = new StringBuilder();

        sql
        .append("SELECT ")
        .append("  CID.CIDADE_ID, ")
        .append("  CID.NOME, ")
        .append("  CID.IBGE, ")
        .append("  CID.ESTADO_ID ")
        .append("FROM CIDADE CID ")

        .append("INNER JOIN ESTADO EST ")
        .append("ON CID.ESTADO_ID = EST.ESTADO_ID ")

        .append("WHERE EST.SIGLA = ? ")
        .append("AND CID.NOME = ? ");


        consulta.setSql(sql.toString());

        consulta.executarComando(new Object[]{uf, cidade});

        while(!consulta.fimConsulta()){
            cidadeEstado = new CidadeEstado(
                consulta.getInt("CIDADE_ID"),
                consulta.getString("NOME"),
                consulta.getString("IBGE"),
                Estados.obterPorIndice(consulta.getInt("ESTADO_ID"))
            );
        }

        return cidadeEstado;
    }
    
    public static Endereco pesquisarEndereco(String cep){
        Endereco endereco = new Endereco();
        try{
            RequisicaoHttps requisicao = new RequisicaoHttps(
                "https://viacep.com.br/ws/" + cep + "/json/",
                MetodoRequisicao.GET,
                TipoConteudo.RAW
            );

            Retorno retorno = requisicao.executarRequisicao();

            if(retorno.isHouveErro())
                return endereco;
            
            RetornoCep retornoCep = Json.toObject(retorno.getDados(), RetornoCep.class);
            
            CidadeEstado cidadeEstado = buscarCidadeEstado(retornoCep.localidade, retornoCep.uf);
            
            if(cidadeEstado == null)
                throw new RequisicaoException("Nenhuma cidade encontrada com o endereço informado");
            
            endereco = new Endereco(
                Integer.parseInt(cep),
                retornoCep.logradouro,
                cidadeEstado,
                retornoCep.bairro
            );
            
            
        }
        catch(IOException e){
            throw new RequisicaoException("Falha ao pesquisar cep: " + e.getMessage());
        }
        
        return endereco;
    }
    
}
