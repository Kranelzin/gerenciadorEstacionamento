package banco.comandos;

import banco.comandos.Conexao;
import exceptions.BancoIntegridadeException;
import java.sql.SQLException;

/**
 *
 * @author marce
 */
public class Delete extends SqlComandos{
    
    public Delete(Conexao con){
        super(con);
    }
    
    @Override
    public void setSql(String sql){
        super.sql = sql;
    }
    
    @Override
    public void executarComando(Object... parametros){
    
    setPreparedStatement(parametros);
         try{
            linhasAfetadas = st.executeUpdate();
            fecharConexao();
        }
        catch(SQLException e){
            con.rollback();
            throw new BancoIntegridadeException("Erro ao realizar delete: " + e.getMessage());
        }
    }
}
