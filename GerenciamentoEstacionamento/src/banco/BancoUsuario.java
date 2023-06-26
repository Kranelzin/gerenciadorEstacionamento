package banco;

import Repositorio.Biblioteca;
import banco.comandos.Conexao;
import banco.comandos.Insert;
import enums.TipoUsuario;
import java.util.ArrayList;
import objetos.Endereco;
import objetos.Telefone;

/**
 *
 * @author marce
 */
public class BancoUsuario {
    
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
    
    private static int inerirUsuario(
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

    private static void inserirTelefones(Conexao con, int usuarioId, ArrayList<Telefone> telefones) {
        
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

    private static void inserirEmails(Conexao con, int usuarioId, ArrayList<String> emails) {
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

    private static void inserirEnderecos(Conexao con, int usuarioId, ArrayList<Endereco> enderecos) {
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
}
