package banco;

import exceptions.BancoException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author marce
 */
public class Consulta extends SqlComandosRetorno{
    
    private int pos = -1;
    
    protected Consulta(Conexao con){
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
            rs = st.executeQuery();

            setResultado();
            fecharConexao();
        } 
        catch(SQLException e){
            throw new BancoException("Erro ao realizar consulta: " + e.getMessage());
        }
    }
    
    public boolean fimConsulta(){
        pos++;
        if(pos == dados.size())
            return true;
        return false;
    }
    
    public BigDecimal getBigDecimal(String nomeColuna){
        try{
            return (BigDecimal) dados.get(pos).get(nomeColuna);
        }
        catch(NullPointerException e){
            throw new BancoException(e.getMessage(), nomeColuna);
        }
    }
    
    public String getString(String nomeColuna){
       try{
            return (String) dados.get(pos).get(nomeColuna);
        }
        catch(NullPointerException e){
            throw new BancoException(e.getMessage(), nomeColuna);
        }
    }
    
    public int getInt(String nomeColuna){
        try{
            return (int) dados.get(pos).get(nomeColuna);
        }
        catch(NullPointerException e){
            throw new BancoException(e.getMessage(), nomeColuna);
        }
    }
    
    public Timestamp getTimestamp(String nomeColuna){
        try{
            return (Timestamp) dados.get(pos).get(nomeColuna);
        }
        catch(NullPointerException e){
            throw new BancoException(e.getMessage(), nomeColuna);
        }
    }
}
