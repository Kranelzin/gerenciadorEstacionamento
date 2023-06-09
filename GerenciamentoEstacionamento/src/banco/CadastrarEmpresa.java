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
public class CadastrarEmpresa {
    
    public static int inerirNovaEmpresa(
            Conexao con, 
            String nomeRazaoSocial, 
            String cpfCnpj, 
            ArrayList<String> emails, 
            ArrayList<Endereco> enderecos, 
            ArrayList<Telefone> telefones
    ){
    
        int empresaId = inerirEmpresa(con, nomeRazaoSocial, cpfCnpj);
        inserirEmails(con, empresaId, emails);
        inserirEnderecos(con, empresaId, enderecos);
        inserirTelefones(con, empresaId, telefones);

        return empresaId;
    }
    
    private static int inerirEmpresa(
        Conexao con, 
        String nomeRazaoSocial, 
        String cpfCnpj
    ){
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO EMPRESA ( ")
        .append("  NOME_RAZAO_SOCIAL, ")
        .append("  CPF_CNPJ ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        insert.executarComando(new Object[]{nomeRazaoSocial, cpfCnpj});
        
        int empresaId = insert.getRetornoInsert();
    
        return empresaId;
    }

    private static void inserirTelefones(Conexao con, int empresaId, ArrayList<Telefone> telefones) {
        
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
            
            inserirTelefoneEmpresa(con, empresaId, telefoneId);
            
        }
        
        
    }

    private static void inserirTelefoneEmpresa(Conexao con, int empresaId, int telefoneId) {
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO TELEFONE_EMPRESA ( ")
        .append("  TELEFONE_ID, ")
        .append("  EMPRESA_ID ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        
        insert.executarComando(new Object[]{telefoneId, empresaId});
        
    }

    private static void inserirEmails(Conexao con, int empresaId, ArrayList<String> emails) {
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
            
            inserirEmailEmpresa(con, empresaId, emailId);
            
        }
    }

    private static void inserirEmailEmpresa(Conexao con, int empresaId, int emailId) {
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO EMAIL_EMPRESA ( ")
        .append("  EMAIL_ID, ")
        .append("  EMPRESA_ID ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        
        insert.executarComando(new Object[]{emailId, empresaId});
    }

    private static void inserirEnderecos(Conexao con, int empresaId, ArrayList<Endereco> enderecos) {
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
            
            inserirEnderecoEmpresa(con, empresaId, enderecoId);
            
        }
    }

    private static void inserirEnderecoEmpresa(Conexao con, int empresaId, int enderecoId) {
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO ENDERECO_EMPRESA ( ")
        .append("  ENDERECO_ID, ")
        .append("  EMPRESA_ID ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        
        insert.executarComando(new Object[]{enderecoId, empresaId});
    }
}
