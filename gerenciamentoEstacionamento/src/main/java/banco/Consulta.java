package banco;

import exceptions.BancoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marce
 */
public class Consulta {
    private Connection conexao;
    private Conexao con;
    private String sql;
    private PreparedStatement st;
    private ResultSet rs;
    private HashMap<String, Object> dados = new HashMap<String, Object>();
    
    public Consulta(Conexao con) throws SQLException{
        this.con = con;
        this.conexao = con.getConexao();
    }
    
    public void setSql(String sql){
        this.sql = sql;
    }
    
    public void pesquisar(Object... parametros){
        st = con.getStatement(sql);

        try{
            for (int i = 0; i < parametros.length; i++) {

                    st.setObject(i + 1, parametros[i]);

            }

            rs = st.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();

            while(rs.next()){
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    dados.put(metaData.getColumnName(i),rs.getObject(i));
                }
            }
            
            con.close();
        }
        catch(SQLException e){
            throw new BancoException("Erro ao realizar consulta: " + e.getMessage());
        }
        
    }
    
    public HashMap<String, Object> getDados(){
        return dados;
    } 
    
}
