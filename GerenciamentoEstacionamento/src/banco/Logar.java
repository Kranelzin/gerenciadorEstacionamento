package banco;

import banco.comandos.Conexao;
import banco.comandos.Consulta;
import exceptions.LogarException;
import exceptions.SenhaException;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import objetos.Empresa;
import objetos.Endereco;
import objetos.Telefone;
import objetos.UsuarioLogin;


/**
 *
 * @author marce
 */
public class Logar {
    
    private static final String KEY = "FizzTheTrickster"; //mudar isso dps
    private static final String ALGORITHM = "AES";
    private static int usuarioId = -1;
    private static int empresaId = -1;
    
    private static String codificarSenha(String senha){
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(senha.getBytes());
            return DatatypeConverter.printBase64Binary(encryptedBytes);
        } catch (Exception e) {
            throw new SenhaException("Falha ao codificar senha: " + e.getMessage());
        }
    }
    
    private static String decodificarSenha(String encryptedPassword){
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(DatatypeConverter.parseBase64Binary(encryptedPassword));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new SenhaException("Falha ao decodificar senha: " + e.getMessage());
        }
        
    }
    
    public static boolean validarLogin(String login, String senha){
        boolean retorno = false;
        
        String senhaCodificada = codificarSenha(senha);
        
        Conexao con = new Conexao();
        con.abrirConexao(true);
        
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
        
        con.fecharConexao();
        
        return retorno;
    }

    public UsuarioLogin getUsuarioLogin() {
        
        Conexao con = new Conexao();
        con.abrirConexao(true);
        
        ArrayList<String> emails = buscarEmailsUsuario(con);
        
        ArrayList<Telefone> telefones = buscarTelefonesUsuario(con);
        
        ArrayList<Endereco> enderecos = buscarEnderecosUsuario(con);
        
        UsuarioLogin usuarioLogado = buscarUsuarioLogado(con, emails, telefones, enderecos);
        
        con.fecharConexao();
        
        return usuarioLogado;
        
    }

    private ArrayList<String> buscarEmailsUsuario(Conexao con) {
        
        ArrayList<String> emails = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  EML.EMAIL ")
                
        .append("FROM EMAILS EML ")                
                
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

    private ArrayList<Telefone> buscarTelefonesUsuario(Conexao con) {
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
                consulta.getInt("NUMERO"),
                consulta.getInt("TIPO"),
                consulta.getBoolean("TEM_WHATS")
            );
            
            telefones.add(telefone);
        }
        
        return telefones;
        
    }

    private ArrayList<Endereco> buscarEnderecosUsuario(Conexao con) {
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
                consulta.getInt("TIPO"),
                consulta.getInt("CEP"),
                consulta.getString("LOGRADOURO"),
                consulta.getInt("NUMERO"),
                consulta.getString("COMPLEMENTO"),
                consulta.getString("BAIRRO"),
                consulta.getString("CIDADE"),
                consulta.getString("UF")
            );
            
            enderecos.add(endereco);
        }
        
        return enderecos;
    }

    private UsuarioLogin buscarUsuarioLogado(
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
                consulta.getInt("TIPO"),
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

    public Empresa getEmpresaUsuario() {
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

    private ArrayList<String> buscarEmailsEmpresa(Conexao con) {
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

    private ArrayList<Telefone> buscarTelefonesEmpresa(Conexao con) {
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
            Telefone telefone = new Telefone(
                consulta.getInt("NUMERO"),
                consulta.getInt("TIPO"),
                consulta.getBoolean("TEM_WHATS")
            );
        
            telefones.add(telefone);
        }
        
        return telefones;
        
    }

    private ArrayList<Endereco> buscarEnderecosEmpresa(Conexao con) {
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
                consulta.getInt("TIPO"),
                consulta.getInt("CEP"),
                consulta.getString("LOGRADOURO"),
                consulta.getInt("NUMERO"),
                consulta.getString("COMPLEMENTO"),
                consulta.getString("BAIRRO"),
                consulta.getString("CIDADE"),
                consulta.getString("UF")
            );
            
            enderecos.add(endereco);
        }
        
        return enderecos;
    }

    private Empresa buscarEmpresaUsuario(
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
