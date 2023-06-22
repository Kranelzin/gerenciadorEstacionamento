package banco.comandos;

import Repositorio.Biblioteca;
import banco.comandos.Conexao;
import exceptions.BancoException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author marce
 */
public class Consulta extends SqlComandosRetorno{
    
    private int pos = -1;
    
    protected Consulta(Conexao con){
        super(con);
    }
    
    @Override
    public void setSql(String sql){
        super.sql = sql;
    }
    
    @Override
    public void executarComando(Object... parametros){
        dados.clear();
        setPreparedStatement(parametros);
        try{
            rs = st.executeQuery();

            setResultado();
            fecharConexao();
        } 
        catch(SQLException e){
            con.fecharConexao();
            throw new BancoException("Erro ao realizar consulta: " + e.getMessage());
        }
    }
    
    public boolean fimConsulta(){
        pos++;
        return pos == dados.size();
    }
    
    public BigDecimal getBigDecimal(String nomeColuna) {
        return getValor(nomeColuna, BigDecimal.class);
    }
    
    public boolean getBoolean(String nomeColuna) {
        return getInt(nomeColuna) == 1;
    }

    public String getString(String nomeColuna) {
        return getValor(nomeColuna, String.class);
    }

    public int getInt(String nomeColuna) {
        return getValor(nomeColuna, Integer.class);
    }

    public Timestamp getTimestamp(String nomeColuna) {
        return getValor(nomeColuna, Timestamp.class);
    }

    private <Generico> Generico getValor(String nomeColuna, Class<Generico> tipoDado) {
        if (pos == -1) {
            throw new BancoException("Loop de consulta não iniciado, usar while(!fimConsulta()) para obter dados");
        }
        try {
            Object valor = dados.get(pos).get(nomeColuna);
            
            if (valor == null || !Biblioteca.verificarValorValido(tipoDado, valor))
                throw new BancoException("Tipo de valor ou nome inválido para a coluna: " + nomeColuna);
            
            return tipoDado.cast(valor);
            
        } catch (NullPointerException e) {
            throw new BancoException(e.getMessage(), nomeColuna);
        }
    }

}
