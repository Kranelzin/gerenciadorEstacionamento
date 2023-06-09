package interfaces;

/**
 *
 * @author marce
 */
public interface InterfaceSql {
    
    void setSql(String sql);
    
    void executarComando(Object... parametros);
}
