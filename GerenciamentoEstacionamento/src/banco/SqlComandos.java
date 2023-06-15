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
    
    public Conexao con;
    public String sql;
    public PreparedStatement st;
    
    public SqlComandos(Conexao con){
        con.abrirConexao();
        this.con = con;
    }
    
    public void setPreparedStatement(Object... parametros){
        setPreparedStatement(0, parametros);
    }
    public void setPreparedStatement(int generatedKeys, Object... parametros){
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
