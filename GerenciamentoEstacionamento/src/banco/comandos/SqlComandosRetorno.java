package banco.comandos;

import banco.comandos.InterfaceSql;
import banco.comandos.SqlComandos;
import banco.comandos.Conexao;
import exceptions.BancoException;
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
    
    protected ResultSet rs;
    protected ArrayList<HashMap<String, Object>> dados = new ArrayList<>();
    
    protected SqlComandosRetorno(Conexao con){
        super(con);
    }
    
    protected void setResultado(){
        try{
            while(rs.next()){
                
                ResultSetMetaData metaData = rs.getMetaData();
                
                HashMap<String, Object> linhas = new HashMap<>();
                
                for (int i = 1; i <= metaData.getColumnCount(); i++){
                    linhas.put(metaData.getColumnName(i),rs.getObject(i) instanceof Boolean ? ((boolean) rs.getObject(i) ? 1 : 0) : rs.getObject(i));
                }
                dados.add(linhas);
            }
        }
        catch(SQLException e){
            con.rollback();
            throw new BancoException("Falha ao resgatar dados! " + e.getMessage());
        }
    }
    
    @Override
    protected void fecharConexao(){
        try{
            if(rs != null)
                rs.close();
            if(st != null)
                st.close();
        }
        catch(SQLException e){
            con.rollback();
            throw new BancoException("Erro ao fechar recursos do banco: " + e.getMessage());
        }
    }
    
}
