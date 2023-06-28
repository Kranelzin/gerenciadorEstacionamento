package banco;

import Repositorio.Biblioteca;
import banco.comandos.Conexao;
import banco.comandos.Consulta;
import banco.comandos.Delete;
import banco.comandos.Insert;
import banco.comandos.Update;
import enums.Estados;
import enums.TipoEndereco;
import enums.TipoTelefone;
import enums.TipoUsuario;
import exceptions.LogarException;
import java.util.ArrayList;
import objetos.CidadeEstado;
import objetos.Empresa;
import objetos.Endereco;
import objetos.Telefone;
import objetos.UsuarioLogin;

/**
 *
 * @author marce
 */
public class BancoUsuario {
    
    //INSERIR INFORMAÇÕES
    
    public static void inerirNovoUsuario(
            Conexao con, 
            int empresaId,
            String login,
            String senha,
            TipoUsuario tipoUsuario,
            String nome, 
            String cpfCnpj, 
            ArrayList<String> emails, 
            ArrayList<Endereco> enderecos, 
            ArrayList<Telefone> telefones
    ){
    
        int usuarioId = inerirUsuario(con, empresaId, tipoUsuario, nome, cpfCnpj);
        inserirEmails(con, usuarioId, emails);
        inserirEnderecos(con, usuarioId, enderecos);
        inserirTelefones(con, usuarioId, telefones);
        inserirLoginUsuario(con, usuarioId, login, senha);

    }
    
    public static int inerirUsuario(
        Conexao con, 
        int empresaId,
        TipoUsuario tipoUsuario,
        String nome, 
        String cpfCnpj
    ){
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO USUARIO ( ")
        .append("  EMPRESA_ID, ")
        .append("  TIPO, ")
        .append("  NOME, ")
        .append("  CPF_CNPJ ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        insert.executarComando(new Object[]{empresaId, tipoUsuario.getIndice(), nome, cpfCnpj});
        
        int usuarioId = insert.getRetornoInsert();
    
        return usuarioId;
    }

    public static void inserirTelefones(Conexao con, int usuarioId, ArrayList<Telefone> telefones) {
        
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO TELEFONE ( ")
        .append("  NUMERO, ")
        .append("  TIPO, ")
        .append("  TEM_WHATS ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        
        for(Telefone telefone : telefones){
            insert.executarComando(new Object[]{telefone.getNumero(), telefone.getTipo().getIndice(), telefone.temWhats()});
            int telefoneId = insert.getRetornoInsert();
            
            inserirTelefoneUsuario(con, usuarioId, telefoneId);
            
        }
        
    }

    private static void inserirTelefoneUsuario(Conexao con, int usuarioId, int telefoneId) {
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO TELEFONE_USUARIO ( ")
        .append("  TELEFONE_ID, ")
        .append("  USUARIO_ID ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        
        insert.executarComando(new Object[]{telefoneId, usuarioId});
        
    }

    public static void inserirEmails(Conexao con, int usuarioId, ArrayList<String> emails) {
        int emailId = 0;
        
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO EMAIL ( ")
        .append("  EMAIL ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        
        for(String email : emails){
            insert.executarComando(new Object[]{email});
            emailId = insert.getRetornoInsert();
            
            inserirEmailUsuario(con, usuarioId, emailId);
            
        }
    }

    private static void inserirEmailUsuario(Conexao con, int usuarioId, int emailId) {
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO EMAIL_USUARIO ( ")
        .append("  EMAIL_ID, ")
        .append("  USUARIO_ID ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        
        insert.executarComando(new Object[]{emailId, usuarioId});
    }

    public static void inserirEnderecos(Conexao con, int usuarioId, ArrayList<Endereco> enderecos) {
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO ENDERECO ( ")
        .append("  TIPO, ")
        .append("  CEP, ")
        .append("  LOGRADOURO, ")
        .append("  NUMERO, ")
        .append("  COMPLEMENTO, ")
        .append("  BAIRRO, ")
        .append("  CIDADE_ID, ")
        .append("  ESTADO_ID ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        
        for(Endereco endereco : enderecos){
            insert.executarComando(
                new Object[]{
                    endereco.getTipo().getIndice(), 
                    endereco.getCep(), 
                    endereco.getLogradouro(),
                    endereco.getNumero(),
                    endereco.getComplemento(),
                    endereco.getBairro(),
                    endereco.getCidadeEstado().getCidadeId(),
                    endereco.getCidadeEstado().getEstado().getIndice()
                }
            );
            
            int enderecoId = insert.getRetornoInsert();
            
            inserirEnderecoUsuario(con, usuarioId, enderecoId);
            
        }
    }

    private static void inserirEnderecoUsuario(Conexao con, int usuarioId, int enderecoId) {
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO ENDERECO_USUARIO ( ")
        .append("  ENDERECO_ID, ")
        .append("  USUARIO_ID ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        
        insert.executarComando(new Object[]{enderecoId, usuarioId});
    }

    private static void inserirLoginUsuario(Conexao con, int usuarioId, String login, String senha) {
        Insert insert = con.novoInsert();
        
        String senhaCodificada = Biblioteca.codificarSenha(senha);
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO USUARIO_LOGIN ( ")
        .append("  LOGIN, ")
        .append("  SENHA, ")
        .append("  USUARIO_ID ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        
        insert.executarComando(new Object[]{login,senhaCodificada , usuarioId});
    }
    
    //BUSCAR INFORMAÇÕES
    
    public static int validarLogin(Conexao con, String login, String senha){
        int usuarioId = -1;
        
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
        }
        
        return usuarioId;
    }

    public static UsuarioLogin getUsuarioLogin(Conexao con, int usuarioId) {
        
        ArrayList<String> emails = buscarEmailsUsuario(con, usuarioId);
        
        ArrayList<Telefone> telefones = buscarTelefonesUsuario(con, usuarioId);
        
        ArrayList<Endereco> enderecos = buscarEnderecosUsuario(con, usuarioId);
        
        UsuarioLogin usuarioLogado = buscarUsuarioLogin(con, usuarioId, emails, telefones, enderecos);
        
        return usuarioLogado;
        
    }

    public static ArrayList<String> buscarEmailsUsuario(Conexao con, int usuarioId) {
        
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

    public static ArrayList<Telefone> buscarTelefonesUsuario(Conexao con, int usuarioId) {
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

    public static ArrayList<Endereco> buscarEnderecosUsuario(Conexao con, int usuarioId) {
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
        .append("  CID.CIDADE_ID, ")
        .append("  CID.NOME, ")
        .append("  CID.IBGE, ")
        .append("  CID.ESTADO_ID ")
                
        .append("FROM ENDERECO END ")    
                
        .append("INNER JOIN CIDADE CID ")
        .append("ON END.CIDADE_ID = CID.CIDADE_ID ")
                
        .append("INNER JOIN ESTADO EST ")
        .append("ON CID.ESTADO_ID = EST.ESTADO_ID ")

        .append("INNER JOIN ENDERECO_USUARIO ENU ")
        .append("ON END.ENDERECO_ID = ENU.ENDERECO_ID ")
        
        .append("INNER JOIN USUARIO USU ")
        .append("ON ENU.USUARIO_ID = USU.USUARIO_ID ")
                
        .append("WHERE USU.USUARIO_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{usuarioId});
        
        while(!consulta.fimConsulta()){
            
            CidadeEstado cidadeEstado = new CidadeEstado(
                consulta.getInt("CIDADE_ID"),
                consulta.getString("IBGE"),
                consulta.getString("NOME"),
                Estados.obterPorIndice(consulta.getInt("ESTADO_ID"))
            );
            
            Endereco endereco = new Endereco(
                TipoEndereco.obterPorIndice(consulta.getInt("TIPO")),
                consulta.getInt("CEP"),
                consulta.getString("LOGRADOURO"),
                consulta.getInt("NUMERO"),
                consulta.getString("COMPLEMENTO"),
                consulta.getString("BAIRRO"),
                cidadeEstado
            );
            
            enderecos.add(endereco);
        }
        
        return enderecos;
    }

    private static UsuarioLogin buscarUsuarioLogin(
        Conexao con, 
        int usuarioId,
        ArrayList<String> emails, 
        ArrayList<Telefone> telefones, 
        ArrayList<Endereco> enderecos
    ) {
        
        int empresaId = -1;
        
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

    public static Empresa getEmpresaUsuario(Conexao con, int usuarioId, int empresaId) {

        if(empresaId == -1)
            throw new LogarException("Empresa inválida!");
        
        ArrayList<String> emails = buscarEmailsEmpresa(con, empresaId);
        
        ArrayList<Telefone> telefones = buscarTelefonesEmpresa(con, empresaId);
        
        ArrayList<Endereco> enderecos = buscarEnderecosEmpresa(con, empresaId);
        
        Empresa empresa = buscarEmpresaUsuario(con, usuarioId, empresaId, emails, telefones, enderecos);
        
        return empresa;
    }

    private static ArrayList<String> buscarEmailsEmpresa(Conexao con, int empresaId) {
        ArrayList<String> emails = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  EML.EMAIL ")
                
        .append("FROM EMAIL EML ")                
                
        .append("INNER JOIN EMAIL_EMPRESA EME ")
        .append("ON EML.EMAIL_ID = EME.EMAIL_ID ")
        
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

    private static ArrayList<Telefone> buscarTelefonesEmpresa(Conexao con, int empresaId) {
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

    private static ArrayList<Endereco> buscarEnderecosEmpresa(Conexao con, int empresaId) {
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
        .append("  CID.CIDADE_ID, ")
        .append("  CID.NOME, ")
        .append("  CID.IBGE, ")
        .append("  CID.ESTADO_ID ")
                
        .append("FROM ENDERECO END ")    
                
        .append("INNER JOIN CIDADE CID ")
        .append("ON END.CIDADE_ID = CID.CIDADE_ID ")
                
        .append("INNER JOIN ESTADO EST ")
        .append("ON CID.ESTADO_ID = EST.ESTADO_ID ")          

        .append("INNER JOIN ENDERECO_EMPRESA ENE ")
        .append("ON END.ENDERECO_ID = ENE.ENDERECO_ID ")
        
        .append("INNER JOIN EMPRESA EMP ")
        .append("ON ENE.EMPRESA_ID = EMP.EMPRESA_ID ")
                
        .append("WHERE EMP.EMPRESA_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{empresaId});
        
        while(!consulta.fimConsulta()){
            
            CidadeEstado cidadeEstado = new CidadeEstado(
                consulta.getInt("CIDADE_ID"),
                consulta.getString("IBGE"),
                consulta.getString("NOME"),
                Estados.obterPorIndice(consulta.getInt("ESTADO_ID"))
            );
            
            Endereco endereco = new Endereco(
                TipoEndereco.obterPorIndice(consulta.getInt("TIPO")),
                consulta.getInt("CEP"),
                consulta.getString("LOGRADOURO"),
                consulta.getInt("NUMERO"),
                consulta.getString("COMPLEMENTO"),
                consulta.getString("BAIRRO"),
                cidadeEstado
            );
            
            enderecos.add(endereco);
        }
        
        return enderecos;
    }

    private static Empresa buscarEmpresaUsuario(
        Conexao con,
        int usuarioId,
        int empresaId,
        ArrayList<String> emails, 
        ArrayList<Telefone> telefones, 
        ArrayList<Endereco> enderecos
    ) {
        Empresa empresa = null;
        
        Consulta consulta = con.novaConsulta();
      
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  EMP.NOME_RAZAO_SOCIAL, ")
        .append("  EMP.CPF_CNPJ ")
                
        .append("FROM EMPRESA EMP ")
                
        .append("INNER JOIN USUARIO USU ")
        .append("ON EMP.EMPRESA_ID = USU.EMPRESA_ID ")

        .append("WHERE USU.USUARIO_ID = ? ");
        
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

    public static void UpdateUsuario(
        Conexao con, 
        int usuarioId, 
        String login, 
        String senha, 
        String nome, 
        String cpfCnpj, 
        ArrayList<String> emails, 
        ArrayList<Telefone> telefones, 
        ArrayList<Endereco> enderecos
    ) {
        updateUsuarioLogin(con, login, senha, usuarioId);
        updateInfoUsuario(con, usuarioId, nome, cpfCnpj);
        updateEmails(con, usuarioId, emails);
        updateTelefones(con, usuarioId, telefones);
        updateEnderecos(con, usuarioId, enderecos);
       
    }

    private static void updateUsuarioLogin(Conexao con, String login, String senha, int usuarioId) {
        Update update = con.novoUpdate();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("UPDATE USUARIO_LOGIN ")
        .append("SET LOGIN = ?, ")
        .append("SENHA = ? ")
        .append("WHERE USUARIO_ID = ? ");
        
        update.setSql(sql.toString());
        
        update.executarComando(new Object[] {login, senha, usuarioId});
    }

    public static void updateInfoUsuario(Conexao con, 
        int usuarioId, 
        String nome, 
        String cpfCnpj
    ) {
        Update update = con.novoUpdate();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("UPDATE USUARIO ")
        .append("SET NOME_RAZAO_SOCIAL = ?, ")
        .append("CPF_CNPJ = ? ")
        .append("WHERE USUARIO_ID = ? ");
        
        update.setSql(sql.toString());
        
        update.executarComando(new Object[] {nome, cpfCnpj, usuarioId});
    }

    private static void updateEmails(Conexao con, int usuarioId, ArrayList<String> emails) {
        
        ArrayList<Integer> emailsId = getEmailsId(con, usuarioId);
        
        deletarTabelaEmailUsuario(con, usuarioId);
        deletarTabelaEmail(con, emailsId);
        inserirEmails(con, usuarioId, emails);
        
    }

    private static ArrayList<Integer> getEmailsId(Conexao con, int usuarioId) {
        
        ArrayList<Integer> emailsId = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  EML.EMAIL_ID ")
                
        .append("FROM EMAILS EML ")
                
        .append("INNER JOIN EMAIL_USUARIO EMU ")
        .append("ON EML.EMAIL_ID = EMU.EMAIL_ID ")

        .append("WHERE EMU.USUARIO_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{usuarioId});
        
        while(!consulta.fimConsulta()){
            emailsId.add(consulta.getInt("EMAIL_ID"));
        }
        
        return emailsId;
    }

    private static void deletarTabelaEmailUsuario(Conexao con, int usuarioId) {
        Delete deletar = con.novoDelete();
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("DELETE ")
        .append("FROM EMAIL_USUARIO ")  

        .append("WHERE USUARIO_ID = ? " );
        
        deletar.setSql(sql.toString());
        
        deletar.executarComando(new Object[]{usuarioId});
        
    }

    private static void deletarTabelaEmail(Conexao con, ArrayList<Integer> emailsId) {
        Delete deletar = con.novoDelete();
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("DELETE ")
        .append("FROM EMAIL ")  

        .append("WHERE EMAIL_ID IN (" )
        .append(Biblioteca.inserirListaSql(emailsId))
        .append(")");
        
        deletar.setSql(sql.toString());
        
        deletar.executarComando();
        
    }

    private static void updateTelefones(Conexao con, int usuarioId, ArrayList<Telefone> telefones) {
        
        ArrayList<Integer> telefonesId = getTelefonesId(con, usuarioId);
        deletarTabelaTelefoneUsuario(con, usuarioId);
        deletarTabelaTelefone(con, telefonesId);
        inserirTelefones(con, usuarioId, telefones);
    }

    private static ArrayList<Integer> getTelefonesId(Conexao con, int usuarioId) {
        ArrayList<Integer> telefonesId = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  TEL.TELEFONE_ID ")
                
        .append("FROM TELEFONE TEL ")
                
        .append("INNER JOIN TELEFONE_USUARIO TEU ")
        .append("ON TEL.TELEFONE_ID = TEU.TELEFONE_ID ")

        .append("WHERE TEU.USUARIO_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{usuarioId});
        
        while(!consulta.fimConsulta()){
            telefonesId.add(consulta.getInt("TELEFONE_ID"));
        }
            
        return telefonesId;
    }

    private static void deletarTabelaTelefoneUsuario(Conexao con, int usuarioId) {
        
        Delete deletar = con.novoDelete();
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("DELETE ")
        .append("FROM TELEFONE_USUARIO ")  

        .append("WHERE USUARIO_ID = ? " );
        
        deletar.setSql(sql.toString());
        
        deletar.executarComando(new Object[]{usuarioId});
    }

    private static void deletarTabelaTelefone(Conexao con, ArrayList<Integer> telefonesId) {
        Delete deletar = con.novoDelete();
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("DELETE ")
        .append("FROM TELEFONE ")  

        .append("WHERE TELEFONE_ID IN (" )
        .append(Biblioteca.inserirListaSql(telefonesId))
        .append(")");
        
        deletar.setSql(sql.toString());
        
        deletar.executarComando();
    }

    private static void updateEnderecos(Conexao con, int usuarioId, ArrayList<Endereco> enderecos) {
        ArrayList<Integer> enderecosId = getEnderecosId(con, usuarioId);
        deletarTabelaEnderecoUsuario(con, usuarioId);
        deletarTabelaEndereco(con, enderecosId);
        inserirEnderecos(con, usuarioId, enderecos);
    }

    private static  ArrayList<Integer> getEnderecosId(Conexao con, int usuarioId) {
        ArrayList<Integer> enderecosId = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  END.ENDERECO_ID ")
                
        .append("FROM ENDERECO END ")
                
        .append("INNER JOIN ENDERECO_USUARIO EDU ")
        .append("ON END.ENDERECO_ID = EDU.ENDERECO_ID ")

        .append("WHERE EDU.USUARIO_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{usuarioId});
        
        while(!consulta.fimConsulta()){
            enderecosId.add(consulta.getInt("ENDERECO_ID"));
        }
            
        return enderecosId;
    }

    private static void deletarTabelaEnderecoUsuario(Conexao con, int usuarioId) {
        Delete deletar = con.novoDelete();
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("DELETE ")
        .append("FROM ENDERECO_USUARIO ")  

        .append("WHERE USUARIO_ID = ? " );
        
        deletar.setSql(sql.toString());
        
        deletar.executarComando(new Object[]{usuarioId});
        
    }

    private static void deletarTabelaEndereco(Conexao con, ArrayList<Integer> enderecosId) {
        Delete deletar = con.novoDelete();
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("DELETE ")
        .append("FROM ENDERECO ")  

        .append("WHERE ENDERECO_ID IN (" )
        .append(Biblioteca.inserirListaSql(enderecosId))
        .append(")");
        
        deletar.setSql(sql.toString());
        
        deletar.executarComando();
    }

    public static UsuarioLogin buscarUsuarioNome(Conexao con, String nomeUsuario) {
        
        int usuarioId = 0;
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  USUARIO_ID ")
                
        .append("FROM USUARIO ")

        .append("WHERE NOME = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{nomeUsuario});
        
        while(!consulta.fimConsulta()){

            usuarioId = consulta.getInt("USUARIO_ID");

        }
        
        UsuarioLogin usuario = getUsuarioLogin(con, usuarioId);
        
        return usuario;
    }
}
