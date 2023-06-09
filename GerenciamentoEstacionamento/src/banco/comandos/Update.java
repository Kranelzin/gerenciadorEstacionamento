package banco.comandos;

import banco.comandos.SqlComandos;
import banco.comandos.Conexao;
import exceptions.BancoException;
import java.sql.SQLException;

/**
 *
 * @author marce
 */
public class Update extends SqlComandos{
    
    public Update(Conexao con){
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
            throw new BancoException("Erro ao realizar update: " + e.getMessage());
        }
        
    }
    
}
