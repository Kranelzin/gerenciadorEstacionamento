package banco.comandos;

import exceptions.BancoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

/**
 *
 * @author marce
 */
public class Conexao{
    
    private Connection conexao = null;
    private boolean somenteConsulta = false;
    
    public void abrirConexao(){
        abrirConexao(false);
    }
    
    public void abrirConexao(boolean somenteConsulta){
        if(conexao == null){
            this.somenteConsulta = somenteConsulta;
            try {
                Properties propriedades = carregarProperties();
                String url = propriedades.getProperty("url");
                conexao = DriverManager.getConnection(url, propriedades);
                if(!somenteConsulta)
                    conexao.setAutoCommit(false);
            } catch (SQLException e) {
                throw new BancoException("Erro ao conectar no banco: " + e.getMessage());
            }
        }
    }
    
    public void fecharConexao(){
        if(somenteConsulta){
            close();
            return;
        }
        
        try{
            conexao.commit();
        }
        catch(SQLException e){
            rollback();
            throw new BancoException("Erro ao dar commit: " + e.getMessage());
        }
        finally{
            close();
        }
    }
    
    public Consulta novaConsulta() {
        return new Consulta(this);
    }
    
    public Insert novoInsert(){
        return new Insert(this);
    }
    
    public Update novoUpdate(){
        return new Update(this);
    }
    
    public Delete novoDelete(){
        return new Delete(this);
    }
    
    protected PreparedStatement getStatement(String sql, int generatedKeys) {
        try{
            if(generatedKeys == Statement.RETURN_GENERATED_KEYS)
                return conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            else
                return conexao.prepareStatement(sql);
        }
        catch(SQLException e){
            throw new BancoException("Erro ao gerar statement: " + e.getMessage());
        }
    }
    
    private static Properties carregarProperties(){
        try(FileInputStream arquivo = new FileInputStream("src/banco/comandos/banco.properties")){
            Properties propriedades = new Properties();
            propriedades.load(arquivo);
            return propriedades;
        }catch(IOException e){
            throw new BancoException("Erro ao carregar propriedades: " + e.getMessage());
        }
    }
        
    private void close(){
        if(conexao != null){
            try{
                conexao.close();
            }
            catch(SQLException e){
                throw new BancoException("Erro ao fechar conexão com banco: " + e.getMessage());
            }
        }
    }
    
    protected void rollback(){
        try {
            conexao.rollback();
            close();
        } catch (SQLException e) {
            throw new BancoException("Erro ao dar rollback: " + e.getMessage());
        }
    }
    
}
