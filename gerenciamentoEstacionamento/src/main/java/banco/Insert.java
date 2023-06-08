package banco;

import exceptions.BancoException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marce
 */
public class Insert extends SqlComandos{
    
    private int linhasAfetadas;
    
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
    
    public Object getRetornoInsert(String coluna){
        return getRetornoInsert(sql, 0);
    }
    public Object getRetornoInsert(String coluna, int linha){
        return dados.get(linha).get(coluna);
    }
    
    public int getLinhasAfetadas(){
        return linhasAfetadas;
    }
}
