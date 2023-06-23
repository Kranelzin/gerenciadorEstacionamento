package controladores;

import banco.Logar;
import banco.comandos.Conexao;
import objetos.Empresa;
import objetos.UsuarioLogin;

/**
 *
 * @author marce
 */
public class CtrLogin {
    
    private static UsuarioLogin usuarioLogado;
    private static Empresa empresa;
    
    public static boolean logarUsuario(String login, String senha){
        Conexao con = new Conexao();
        con.abrirConexao(true);
        
        boolean retorno = Logar.validarLogin(con, login, senha);
        
        if(retorno)
            setUsuarioLogin(con);
        
        con.fecharConexao();
        return retorno;
    }
    
    private static void setUsuarioLogin(Conexao con){
        usuarioLogado = Logar.getUsuarioLogin(con);
        setEmpresa();
    }
    
    private static void setEmpresa(){
        empresa = Logar.getEmpresaUsuario();
    }
    
    public static Empresa getEmpresa(){
        return empresa;
    }
    
    public static UsuarioLogin getUsuarioLogado(){
        return usuarioLogado;
    }
    
}
