package gerenciamentoestacionamento;

import banco.Conexao;
import banco.Consulta;
import banco.Delete;
import banco.Insert;
import banco.Update;

/**
 *
 * @author marce
 */
public class GerenciamentoEstacionamento {

    public static void main(String[] args) {
       
        //PARA TODOS OS EXEMPLOS SEMPRE ABRIR A CONEXAO
        
        Conexao con = new Conexao();
        con.abrirConexao(); // se for somente para consulta, ou seja selects passar parametro somenteConsulta como true default é false
        //con.abrirConexao(true);
        
        //EXEMPLO DE CONSULTA
        
        Consulta consulta = con.novaConsulta();
        
        String sql = "Select * from usuario where usuario_id = ? ";
        
        consulta.setSql(sql); // é necessário setar o sql da pesquisa para depois executar o comando se não estoura um erro.
        
        int usuarioId = 1; //Variavel exemplo, vai ser utilizada para substituir o "?" na consulta
         
        consulta.executarComando(new Object[]{usuarioId}); // Cria um novo array de Object e preenche esse array com a variavel para substituir o "?" 
        
        String nome = consulta.getString("NOME"); // recupera o dado da tabela conforme o nome da coluna, caso o nome digitado esteja errado estoura um erro, para cada tipo de dado existe um get, exemplo consulta.getInt("NOME_COLUNA")
        
        //EXEMPLO DE INSERT
        
        Insert insert = con.novoInsert();
        
        sql = "Insert into usuario(tipo,nome,cpf_cnpj) values(?, ?, ?) ";
        
        insert.setSql(sql);
        
        int tipo = 0;
        String nomeCliente = "nome exemplo";
        String cpfCnpj = "1111111111";
        
        insert.executarComando(new Object[]{tipo, nomeCliente, cpfCnpj});
        
        int linhasAfetadasInsert = insert.getLinhasAfetadas(); //retorna o numero de linhas que mudaram no banco
        int usuarioIdInserido = insert.getRetornoInsert(); //usado para retornar o int da chave primaria inserida. Poderia ser utilizado para pegar o usuario_id por exemplo. como é esse caso.
        
        int usuarioIdInseridoEspecifico = insert.getRetornoInsert(2); //retorno de chave primaria numa linha especifica
        
        //EXEMPLO UPDATE
        
        Update update = con.novoUpdate();  
        
        sql = "update usuario set nome = ? where usuario_id = ?";
        
        String novoNome = "novo nome";
        
        update.setSql(sql);
        
        update.executarComando(new Object[]{novoNome, usuarioIdInserido});
        
        int linhasAfetadasUpdate = update.getLinhasAfetadas();
        
        //EXEMPLO DELETE
        
        Delete delete = con.novoDelete();
        
        sql = "delete from usuario where usuario_id = ?"; // esse comando apaga todas as linhas da tabela que corresponderem ao where
        delete.setSql(sql);
        
        
        delete.executarComando(new Object[]{usuarioIdInserido});
        
        int linhasAfetadasDelete = delete.getLinhasAfetadas();
        
        
        con.fecharConexao(); //importante fechar a conexao para que a transcação nao fique aberta. fechar somente apos executar todos os comandos.
        
    }
    
}
