package banco;

import banco.Conexao;
import exceptions.BancoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author marce
 */
abstract class SqlComandos{
    
    public Conexao con;
    public String sql;
    public PreparedStatement st;
    public ResultSet rs;
    public ArrayList<HashMap<String, Object>> dados = new ArrayList<>();
    
    public SqlComandos(Conexao con){
        con.abrirConexao();
        this.con = con;
    }
    
    public abstract void setSql(String sql);
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
   
    public abstract void executarComando(Object... parametros);
    
    public void setResultado(){
        try{
            while(rs.next()){
                
                ResultSetMetaData metaData = rs.getMetaData();
                
                HashMap<String, Object> linhas = new HashMap<>();
                
                for (int i = 1; i <= metaData.getColumnCount(); i++) 
                    linhas.put(metaData.getColumnName(i),rs.getObject(i));
                
                dados.add(linhas);
            }
        }
        catch(SQLException e){
            throw new BancoException("Falha ao resgatar dados! " + e.getMessage());
        }
    }
    
    public void fecharConexao(){
        try{
            rs.close();
            st.close();
        }
        catch(SQLException e){
            throw new BancoException("Erro ao fechar recursos do banco: " + e.getMessage());
        }
    }
    
}
