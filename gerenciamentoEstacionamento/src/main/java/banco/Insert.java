package banco;

import abstratas.SqlComandosRetorno;
import exceptions.BancoException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marce
 */
public class Insert extends SqlComandosRetorno{
    
    protected Insert(Conexao con){
        super(con);
    }
    
    @Override
    public void setSql(String sql){
        super.sql = sql;
    }
    
    @Override
    public void executarComando(Object... parametros){
        setPreparedStatement(Statement.RETURN_GENERATED_KEYS, parametros);
        try{
            linhasAfetadas = st.executeUpdate();
            if(linhasAfetadas > 0){
                rs = st.getGeneratedKeys();
                setResultado();
            }
            fecharConexao();
        }
        catch(SQLException e){
            throw new BancoException("Erro ao realizar insert: " + e.getMessage());
        }
    
    }
    
    public int getRetornoInsert(){
        return getRetornoInsert( 0);
    }
    public int getRetornoInsert(int linha){
        return (int) dados.get(linha).get("GENERATED_KEY");
    }
    
    public int getLinhasAfetadas(){
        return linhasAfetadas;
    }
}
