package banco;

import exceptions.BancoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marce
 */
public class Conexao{
    
    private Connection conexao = null;
    
    public void abrirConexao(){
        if(conexao == null){
            
            try {
                Properties propriedades = carregarProperties();
                String url = propriedades.getProperty("url");
                conexao = DriverManager.getConnection(url, propriedades);
                conexao.setAutoCommit(false);
            } catch (SQLException e) {
                throw new BancoException("Erro ao conectar no banco: " + e.getMessage());
            }
        }
    }
    
    public void close(){
        if(conexao != null){
            try{
                conexao.close();
            }
            catch(SQLException e){
                throw new BancoException("Erro ao fechar conexão com banco: " + e.getMessage());
            }
        }
    }
    
    private static Properties carregarProperties(){
        try(FileInputStream arquivo = new FileInputStream("src/main/java/banco/banco.properties")){
            Properties propriedades = new Properties();
            propriedades.load(arquivo);
            return propriedades;
        }catch(IOException e){
            throw new BancoException("Erro ao carregar propriedades: " + e.getMessage());
        }
    }

    public PreparedStatement getStatement(String sql, int generatedKeys) {
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
    
    protected void commit(){
        try {
            conexao.commit();
        } catch (SQLException e) {
            throw new BancoException("Erro ao dar commit: " + e.getMessage());
        }
    }
    
    protected void rollback(){
        try {
            conexao.rollback();
        } catch (SQLException e) {
             throw new BancoException("Erro ao dar rollback: " + e.getMessage());
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
    
}
