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
        setEmpresa(con);
        CtrBoxVaga.setBoxVagas(con, empresa.getEmpresaId());
    }
    
    private static void setEmpresa(Conexao con){
        empresa = Logar.getEmpresaUsuario(con);
    }
    
    public static Empresa getEmpresa(){
        return empresa;
    }
    
    public static UsuarioLogin getUsuarioLogado(){
        return usuarioLogado;
    }
    
}
