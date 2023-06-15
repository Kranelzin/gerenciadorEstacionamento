package abstratas;

import banco.Conexao;
import exceptions.BancoException;
import interfaces.InterfaceSql;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author marce
 */
public abstract class SqlComandosRetorno extends SqlComandos implements InterfaceSql{
    
    public ResultSet rs;
    public ArrayList<HashMap<String, Object>> dados = new ArrayList<>();
    public int linhasAfetadas;
    
    public SqlComandosRetorno(Conexao con){
        super(con);
    }
    
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
    
    @Override
    public void fecharConexao(){
        try{
            if(rs != null)
                rs.close();
            if(st != null)
                st.close();
        }
        catch(SQLException e){
            throw new BancoException("Erro ao fechar recursos do banco: " + e.getMessage());
        }
    }
    
}
