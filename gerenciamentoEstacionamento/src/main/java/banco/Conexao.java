package banco;

import exceptions.BancoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Properties;

/**
 *
 * @author marce
 */
public class Conexao {
    
    private static Connection conexao = null;
    
    public static Connection getConexao(){
        if(conexao == null){
            
            try {
                Properties propriedades = carregarProperties();
                String url = propriedades.getProperty("url");
                conexao = DriverManager.getConnection(url, propriedades);
            } catch (SQLException e) {
                throw new BancoException("Erro ao conectar no banco: " + e.getMessage());
            }
        }
        return conexao;
    }
    
    public static void close(){
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

    protected PreparedStatement getStatement(String sql) {
        try{
            return conexao.prepareStatement(sql);
        }
        catch(SQLException e){
            throw new BancoException("Erro ao gerar statement: " + e.getMessage());
        }
    }

    public Consulta novaConsulta() {
        try{
            return new Consulta(this);
        }  
        catch(SQLException e){
            throw new BancoException("Erro ao criar consulta: " + e.getMessage());
        }
    }
    
}
