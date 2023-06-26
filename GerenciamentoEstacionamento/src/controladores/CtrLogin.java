package controladores;

import banco.BancoUsuario;
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
        
        int usuarioId = BancoUsuario.validarLogin(con, login, senha);
        
        boolean retorno = usuarioId != -1;
        
        if(retorno)
            setUsuarioLogin(con, usuarioId);
        
        con.fecharConexao();
        return retorno;
    }
    
    private static void setUsuarioLogin(Conexao con, int usuarioId){
        
        usuarioLogado = BancoUsuario.getUsuarioLogin(con, usuarioId);
        setEmpresa(con, usuarioLogado.getUsuarioId(), usuarioLogado.getEmpresaId());
        CtrBoxVaga.setBoxVagas(con, empresa.getEmpresaId());
    }
    
    private static void setEmpresa(Conexao con, int usuarioId, int empresaId){
        empresa = BancoUsuario.getEmpresaUsuario(con, usuarioId, empresaId);
    }
    
    public static Empresa getEmpresa(){
        return empresa;
    }
    
    public static UsuarioLogin getUsuarioLogado(){
        return usuarioLogado;
    }
    
}
