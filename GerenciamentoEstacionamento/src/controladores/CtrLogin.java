package controladores;

import banco.Logar;
import objetos.Empresa;
import objetos.UsuarioLogin;

/**
 *
 * @author marce
 */
public class CtrLogin {
    
    private static Logar loginUsuario = new Logar();
    private static UsuarioLogin usuarioLogado;
    private static Empresa empresa;
    private static final int ACESSO_ADMIN = 2;
    private static final int ACESSO_FUNCIONARIO = 1;
    
    public static boolean logarUsuario(String login, String senha){
        
        boolean retorno = loginUsuario.validarLogin(login, senha);
        
        if(retorno)
            setUsuarioLogin();
        
        return retorno;
    }
    
    private static void setUsuarioLogin(){
        usuarioLogado = loginUsuario.getUsuarioLogin();
        setEmpresa();
    }
    
    private static void setEmpresa(){
        empresa = loginUsuario.getEmpresaUsuario();
    }
    
}
