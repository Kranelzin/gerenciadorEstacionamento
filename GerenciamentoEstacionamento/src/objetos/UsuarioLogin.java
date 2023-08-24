package objetos;

import enums.TipoUsuario;
import java.util.ArrayList;

/**
 *
 * @author marce
 */
public class UsuarioLogin extends Usuario{
    
    private final String login;
    private final String senha;
    private final boolean ativo;
    
    public UsuarioLogin(
        int usuarioId, 
        TipoUsuario tipo, 
        String nomeUsuario, 
        String cpfCnpj, 
        ArrayList<String> emails,
        ArrayList<Endereco> endereco, 
        ArrayList<Telefone> telefone,
        String login,
        String senha,
        int empresaId,
        boolean ativo
    
    ){
    
        super(
            usuarioId, 
            tipo, 
            nomeUsuario, 
            cpfCnpj, 
            emails,
            endereco, 
            telefone,
            empresaId
        );
        this.ativo = ativo;
        this.login = login;
        this.senha = senha;

    }
    
    public String getLogin(){
        return login;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public boolean isAtivo(){
        return ativo;
    }
}
