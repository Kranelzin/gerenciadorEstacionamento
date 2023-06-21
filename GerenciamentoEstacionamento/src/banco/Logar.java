package banco;

import Repositorio.Biblioteca;
import banco.comandos.Conexao;
import banco.comandos.Consulta;
import enums.Estados;
import enums.TipoEndereco;
import enums.TipoTelefone;
import enums.TipoUsuario;
import exceptions.LogarException;
import java.util.ArrayList;
import objetos.Empresa;
import objetos.Endereco;
import objetos.Telefone;
import objetos.UsuarioLogin;


/**
 *
 * @author marce
 */
public class Logar {
    
    private static int usuarioId = -1;
    private static int empresaId = -1;
    
    public static boolean validarLogin(Conexao con, String login, String senha){
        boolean retorno = false;
        
        String senhaCodificada = Biblioteca.codificarSenha(senha);
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  USUARIO_ID ")
                
        .append("FROM USUARIO_LOGIN ")                

        .append("WHERE LOGIN = ? ")
        .append("AND SENHA = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{login, senhaCodificada});
        
        while(!consulta.fimConsulta()){
            usuarioId = consulta.getInt("USUARIO_ID");
            retorno = true;
        }
        
        return retorno;
    }

    public static UsuarioLogin getUsuarioLogin(Conexao con) {
        
        ArrayList<String> emails = buscarEmailsUsuario(con);
        
        ArrayList<Telefone> telefones = buscarTelefonesUsuario(con);
        
        ArrayList<Endereco> enderecos = buscarEnderecosUsuario(con);
        
        UsuarioLogin usuarioLogado = buscarUsuarioLogado(con, emails, telefones, enderecos);
        
        con.fecharConexao();
        
        return usuarioLogado;
        
    }

    private static ArrayList<String> buscarEmailsUsuario(Conexao con) {
        
        ArrayList<String> emails = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  EML.EMAIL ")
                
        .append("FROM EMAIL EML ")                
                
        .append("INNER JOIN EMAIL_USUARIO EMU ")
        .append("ON EML.EMAIL_ID = EMU.EMAIL_ID ")
        
        .append("INNER JOIN USUARIO USU ")
        .append("ON EMU.USUARIO_ID = USU.USUARIO_ID ")
                
        .append("WHERE USU.USUARIO_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{usuarioId});
        
        while(!consulta.fimConsulta()){
            String email = consulta.getString("EMAIL");
            emails.add(email);
        }
        
        return emails;
        
    }

    private static ArrayList<Telefone> buscarTelefonesUsuario(Conexao con) {
        ArrayList<Telefone> telefones = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  TEL.NUMERO, ")
        .append("  TEL.TIPO, ")
        .append("  TEL.TEM_WHATS ")
        .append("FROM TELEFONE TEL ")                

        .append("INNER JOIN TELEFONE_USUARIO TEU ")
        .append("ON TEL.TELEFONE_ID = TEU.TELEFONE_ID ")
        
        .append("INNER JOIN USUARIO USU ")
        .append("ON TEU.USUARIO_ID = USU.USUARIO_ID ")
                
        .append("WHERE USU.USUARIO_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{usuarioId});
        
        while(!consulta.fimConsulta()){
            
            Telefone telefone = new Telefone(
                consulta.getString("NUMERO"),
                TipoTelefone.obterPorIndice(consulta.getInt("TIPO")),
                consulta.getBoolean("TEM_WHATS")
            );
            
            telefones.add(telefone);
        }
        
        return telefones;
        
    }

    private static ArrayList<Endereco> buscarEnderecosUsuario(Conexao con) {
        ArrayList<Endereco> enderecos = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  END.TIPO, ")
        .append("  END.CEP, ")
        .append("  END.LOGRADOURO, ")
        .append("  END.NUMERO, ")
        .append("  END.COMPLEMENTO, ")
        .append("  END.BAIRRO, ")
        .append("  END.CIDADE, ")
        .append("  END.UF ")
                
        .append("FROM ENDERECO END ")                

        .append("INNER JOIN ENDERECO_USUARIO ENU ")
        .append("ON END.ENDERECO_ID = ENU.ENDERECO_ID ")
        
        .append("INNER JOIN USUARIO USU ")
        .append("ON ENU.USUARIO_ID = USU.USUARIO_ID ")
                
        .append("WHERE USU.USUARIO_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{usuarioId});
        
        while(!consulta.fimConsulta()){
            
            Endereco endereco = new Endereco(
                TipoEndereco.obterPorIndice(consulta.getInt("TIPO")),
                consulta.getInt("CEP"),
                consulta.getString("LOGRADOURO"),
                consulta.getInt("NUMERO"),
                consulta.getString("COMPLEMENTO"),
                consulta.getString("BAIRRO"),
                consulta.getString("CIDADE"),
                Estados.obterPorDescricao(consulta.getString("UF"))
            );
            
            enderecos.add(endereco);
        }
        
        return enderecos;
    }

    private static UsuarioLogin buscarUsuarioLogado(
        Conexao con, 
        ArrayList<String> emails, 
        ArrayList<Telefone> telefones, 
        ArrayList<Endereco> enderecos
    ) {
        UsuarioLogin usuarioLogado = null;
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  USU.NOME, ")
        .append("  USU.TIPO, ")
        .append("  USU.CPF_CNPJ, ")
        .append("  USU.EMPRESA_ID, ")
        .append("  USL.LOGIN, ")
        .append("  USL.SENHA ")
                
        .append("FROM USUARIO USU ")
                
        .append("INNER JOIN USUARIO_LOGIN USL ")
        .append("ON USU.USUARIO_ID = USL.USUARIO_ID ")

        .append("WHERE USU.USUARIO_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{usuarioId});
        
        while(!consulta.fimConsulta()){
            
            empresaId = consulta.getInt("EMPRESA_ID");
            
            usuarioLogado = new UsuarioLogin(
                usuarioId,
                TipoUsuario.obterPorIndice(consulta.getInt("TIPO")),
                consulta.getString("NOME"),
                consulta.getString("CPF_CNPJ"),
                emails,
                enderecos,
                telefones,
                consulta.getString("LOGIN"),
                consulta.getString("SENHA"),
                empresaId
            );
        }
        
        return usuarioLogado;
    }

    public static Empresa getEmpresaUsuario() {
        Conexao con = new Conexao();
        con.abrirConexao(true);
        
        if(empresaId == -1)
            throw new LogarException("Empresa inválida!");
        
        ArrayList<String> emails = buscarEmailsEmpresa(con);
        
        ArrayList<Telefone> telefones = buscarTelefonesEmpresa(con);
        
        ArrayList<Endereco> enderecos = buscarEnderecosEmpresa(con);
        
        Empresa empresa = buscarEmpresaUsuario(con, emails, telefones, enderecos);
        
        con.fecharConexao();
        
        return empresa;
    }

    private static ArrayList<String> buscarEmailsEmpresa(Conexao con) {
        ArrayList<String> emails = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  EML.EMAIL ")
                
        .append("FROM EMAILS EML ")                
                
        .append("INNER JOIN EMAIL_EMPRESA EME ")
        .append("ON EML.EMAIL_ID = EMU.EMAIL_ID ")
        
        .append("INNER JOIN EMPRESA EMP ")
        .append("ON EME.EMPRESA_ID = EMP.EMPRESA_ID ")
                
        .append("WHERE EMP.EMPRESA_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{empresaId});
        
        while(!consulta.fimConsulta()){
            String email = consulta.getString("EMAIL");
            emails.add(email);
        }
        
        return emails;
    }

    private static ArrayList<Telefone> buscarTelefonesEmpresa(Conexao con) {
        ArrayList<Telefone> telefones = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  TEL.NUMERO, ")
        .append("  TEL.TIPO, ")
        .append("  TEL.TEM_WHATS ")
        .append("FROM TELEFONE TEL ")                

        .append("INNER JOIN TELEFONE_EMPRESA TEE ")
        .append("ON TEL.TELEFONE_ID = TEE.TELEFONE_ID ")
        
        .append("INNER JOIN EMPRESA EMP ")
        .append("ON TEE.EMPRESA_ID = EMP.EMPRESA_ID ")
                
        .append("WHERE EMP.EMPRESA_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{empresaId});
        
        while(!consulta.fimConsulta()){
            
            TipoTelefone tipo = TipoTelefone.obterPorIndice(consulta.getInt("TIPO"));
            
            Telefone telefone = new Telefone(
                consulta.getString("NUMERO"),
                tipo,
                consulta.getBoolean("TEM_WHATS")
            );
        
            telefones.add(telefone);
        }
        
        return telefones;
        
    }

    private static ArrayList<Endereco> buscarEnderecosEmpresa(Conexao con) {
        ArrayList<Endereco> enderecos = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  END.TIPO, ")
        .append("  END.CEP, ")
        .append("  END.LOGRADOURO, ")
        .append("  END.NUMERO, ")
        .append("  END.COMPLEMENTO, ")
        .append("  END.BAIRRO, ")
        .append("  END.CIDADE, ")
        .append("  END.UF ")
                
        .append("FROM ENDERECO END ")                

        .append("INNER JOIN ENDERECO_EMPRESA ENE ")
        .append("ON END.ENDERECO_ID = ENE.ENDERECO_ID ")
        
        .append("INNER JOIN EMPRESA EMP ")
        .append("ON ENE.EMPRESA_ID = EMP.EMPRESA_ID ")
                
        .append("WHERE EMP.EMPRESA_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{empresaId});
        
        while(!consulta.fimConsulta()){
            
            Endereco endereco = new Endereco(
                TipoEndereco.obterPorIndice(consulta.getInt("TIPO")),
                consulta.getInt("CEP"),
                consulta.getString("LOGRADOURO"),
                consulta.getInt("NUMERO"),
                consulta.getString("COMPLEMENTO"),
                consulta.getString("BAIRRO"),
                consulta.getString("CIDADE"),
                Estados.obterPorDescricao(consulta.getString("UF"))
            );
            
            enderecos.add(endereco);
        }
        
        return enderecos;
    }

    private static Empresa buscarEmpresaUsuario(
        Conexao con, 
        ArrayList<String> emails, 
        ArrayList<Telefone> telefones, 
        ArrayList<Endereco> enderecos
    ) {
        Empresa empresa = null;
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  NOME_RAZAO_SOCIAL, ")
        .append("  CPF_CNPJ ")
                
        .append("FROM EMPRESA ")

        .append("WHERE EMPRESA_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{usuarioId});
        
        while(!consulta.fimConsulta()){
            
            empresa = new Empresa(
                empresaId,
                consulta.getString("NOME_RAZAO_SOCIAL"),
                consulta.getString("CPF_CNPJ"),
                emails,
                telefones,
                enderecos
            );
        }
        
        return empresa;
    }
    

}
