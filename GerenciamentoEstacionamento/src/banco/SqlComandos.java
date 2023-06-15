package banco;

import banco.Conexao;
import exceptions.BancoException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author marce
 */
public abstract class SqlComandos implements InterfaceSql{
    
    protected Conexao con;
    protected String sql;
    protected PreparedStatement st;
    protected int linhasAfetadas;
    
    protected SqlComandos(Conexao con){
        con.abrirConexao();
        this.con = con;
    }
    
    public int getLinhasAfetadas(){
        return linhasAfetadas;
    }
    
    protected void setPreparedStatement(Object... parametros){
        setPreparedStatement(0, parametros);
    }
    protected void setPreparedStatement(int generatedKeys, Object... parametros){
        if(sql == null)
            throw new BancoException("O sql não foi informado! ");
        st = con.getStatement(sql, generatedKeys);

        try{
            for (int i = 0; i < parametros.length; i++) {
                st.setObject(i + 1, parametros[i]);
            }
        }
        catch(SQLException e){
            throw new BancoException("Erro a resgatar statement: " + e.getMessage());
        }
        
    }
     
    protected void fecharConexao(){
        try{
            if(st != null)
                st.close();
        }
        catch(SQLException e){
            throw new BancoException("Erro ao fechar recursos do banco: " + e.getMessage());
        }
    }
}
