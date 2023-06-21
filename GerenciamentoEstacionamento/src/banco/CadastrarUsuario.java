package banco;

import banco.comandos.Conexao;
import banco.comandos.Insert;
import java.util.ArrayList;
import objetos.Endereco;
import objetos.Telefone;

/**
 *
 * @author marce
 */
public class CadastrarUsuario {
    
    public static void inerirNovoUsuario(
            Conexao con, 
            String nome, 
            String cpfCnpj, 
            ArrayList<String> emails, 
            ArrayList<Endereco> enderecos, 
            ArrayList<Telefone> telefones
    ){
    
        int usuarioId = inerirUsuario(con, nome, cpfCnpj);
        inserirEmails(con, usuarioId, emails);
        inserirEnderecos(con, usuarioId, enderecos);
        inserirTelefones(con, usuarioId, telefones);

    }
    
    private static int inerirUsuario(
        Conexao con, 
        String nome, 
        String cpfCnpj
    ){
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO USUARIO ( ")
        .append("  TIPO, ")
        .append("  NOME, ")
        .append("  CPF_CNPJ ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        insert.executarComando(new Object[]{nome, cpfCnpj});
        
        int usuarioId = insert.getRetornoInsert();
    
        return usuarioId;
    }

    private static void inserirTelefones(Conexao con, int usuarioId, ArrayList<Telefone> telefones) {
        
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO TEELEFONE ( ")
        .append("  NUMERO, ")
        .append("  TIPO, ")
        .append("  TEM_WHATS ")
        .append(") ")
        .append("VALUES ( ")
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
        .append("INSERT INTO TEELEFONE_USUARIO ( ")
        .append("  TELEFONE_ID, ")
        .append("  USUARIO_ID ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        
        insert.executarComando(new Object[]{usuarioId, telefoneId});
        
    }

    private static void inserirEmails(Conexao con, int usuarioId, ArrayList<String> emails) {
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
            int emailId = insert.getRetornoInsert();
            
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
        
        insert.executarComando(new Object[]{usuarioId, emailId});
    }

    private static void inserirEnderecos(Conexao con, int usuarioId, ArrayList<Endereco> enderecos) {
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO ENDERECO ( ")
        .append("  TIPO, ")
        .append("  CEP, ")
        .append("  LOUGRADOURO, ")
        .append("  NUMERO, ")
        .append("  COMPLEMENTO, ")
        .append("  BAIRRO, ")
        .append("  CIDADE, ")
        .append("  UF ")
        .append(") ")
        .append("VALUES ( ")
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
                        endereco.getCidade(),
                        endereco.getUf()
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
        
        insert.executarComando(new Object[]{usuarioId, enderecoId});
    }
}
