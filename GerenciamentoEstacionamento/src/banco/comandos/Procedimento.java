package banco.comandos;

import banco.comandos.Conexao;
import exceptions.BancoException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marce
 */
public class Procedimento extends SqlComandosRetorno{
    
    protected Procedimento(Conexao con){
        super(con);
    }
    
    public void executarProcedimento(String nomeProcedimento, Object... parametros){
        StringBuilder sql = new StringBuilder();
        sql
        .append("call ")
        .append(nomeProcedimento)
        .append("(");
        
        for(int i = 0; i < parametros.length; i++){
            
            if(i == parametros.length -1)
                sql.append("? )");
            else
                sql.append("?, ");
            
        }
        
        setSql(sql.toString());
        executarComando(parametros);
    }
    
    @Override
    public void setSql(String sql){
        super.sql = sql;
    }
    
    @Override
    public void executarComando(Object... parametros){
        
        dados.clear();
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
            con.rollback();
            throw new BancoException("Erro ao realizar insert: " + e.getMessage());
        }
    
    }
   
}
