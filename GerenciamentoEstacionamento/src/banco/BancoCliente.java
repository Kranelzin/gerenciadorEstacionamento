package banco;

import banco.comandos.Conexao;
import banco.comandos.Consulta;
import banco.comandos.Insert;
import banco.comandos.Update;
import enums.TipoUsuario;
import java.util.ArrayList;
import objetos.BoxVaga;
import objetos.Cliente;
import objetos.Endereco;
import objetos.Mensalidade;
import objetos.Telefone;
import objetos.Veiculo;

/**
 *
 * @author marce
 */
public class BancoCliente {
    
    public static Cliente inerirNovoCliente(
            Conexao con, 
            int empresaId,
            TipoUsuario tipoUsuario,
            String nome, 
            String cpfCnpj, 
            ArrayList<String> emails, 
            ArrayList<Endereco> enderecos, 
            ArrayList<Telefone> telefones,
            ArrayList<Veiculo> veiculos
    ){
    
        int usuarioId = inerirUsuario(con, empresaId, tipoUsuario, nome, cpfCnpj);
        inserirEmails(con, usuarioId, emails);
        inserirEnderecos(con, usuarioId, enderecos);
        inserirTelefones(con, usuarioId, telefones);
        inserirVeiculoUsuario(con, usuarioId, veiculos);
        
        Cliente cliente = new Cliente(
            usuarioId,
            nome,
            cpfCnpj,
            emails,
            enderecos,
            telefones,
            veiculos,
            empresaId
        );
        
        return cliente;
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

    private static int inserirVeiculo(Conexao con, Veiculo veiculo) {
        
        int veiculoId = 0;
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  VEICULO_ID, ")
        .append("  MODELO, ")
        .append("  MARCA ")

        .append("FROM VEICULO ")
        .append("WHERE MODELO = ? ")
        .append("AND MARCA = ? ");
        
        consulta.setSql(sql.toString());

        consulta.executarComando(new Object[]{veiculo.getModelo(), veiculo.getMarca()});

        while(!consulta.fimConsulta()){
            veiculoId = consulta.getInt("VEICULO_ID");
        }

        if(veiculoId == 0){
            veiculoId = inserirNovoVeiculo(con, veiculo);
        }
        
        return veiculoId;
    }
    
     private static int inserirNovoVeiculo(Conexao con, Veiculo veiculo) {
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO VEICULO ( ")
        .append("  MODELO, ")
        .append("  MARCA ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        
        insert.executarComando(new Object[]{veiculo.getModelo(), veiculo.getMarca()});
        
        int veiculoId = insert.getRetornoInsert();
        
        return veiculoId;
    }

    private static void inserirVeiculoUsuario(Conexao con, int usuarioId, ArrayList<Veiculo> veiculos) {
        
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO VEICULO_USUARIO ( ")
        .append("  VEICULO_ID, ")
        .append("  USUARIO_ID, ")
        .append("  PLACA ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
        
        insert.setSql(sql.toString());
        
        for(Veiculo veiculo : veiculos){
            int veiculoId = inserirVeiculo(con, veiculo);
            
            insert.executarComando(new Object[]{veiculoId, usuarioId, veiculo.getPlaca()});
        }
    }

    public static void inserirMensalidade(Conexao con, int usuarioId, Mensalidade mensalidade) {
        
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO MENSALIDADE ( ")
        .append("  USUARIO_ID, ")
        .append("  DATA_INICIO, ")
        .append("  DIA_VENCIMENTO, ")
        .append("  VALOR_MENSALIDADE ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
        
        insert.setSql(sql.toString());
        
        insert.executarComando(new Object[]{usuarioId, mensalidade.getDataInicio(), mensalidade.getDiaVencimento(), mensalidade.getValorMensalidade()});
        
    }

    public static void cadastrarBoxVagasReservadas(Conexao con, int usuarioId, ArrayList<BoxVaga> boxVagas) {
        
        Update update = con.novoUpdate();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("UPDATE BOX_VAGA SET ")
        .append("  USUARIO_ID = ?, ")
        .append("  RESERVADA = TRUE ")

        .append("WHERE CODIGO = ? ")
        .append("AND VAGA = ? ");
        
        update.setSql(sql.toString());
        
        for(BoxVaga boxVaga : boxVagas){
            update.executarComando(new Object[] {usuarioId, boxVaga.getCodigo(), boxVaga.getVaga()});
        }
        
    }
}
