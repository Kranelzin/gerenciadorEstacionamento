package objetos;

import java.util.ArrayList;

/**
 *
 * @author marce
 */
public class UsuarioLogin extends Usuario{
    
    private final String login;
    private final String senha;
    
    public UsuarioLogin(
        int usuarioId, 
        int tipo, 
        String nomeUsuario, 
        String cpfCnpj, 
        ArrayList<String> emails,
        ArrayList<Endereco> endereco, 
        ArrayList<Telefone> telefone,
        String login,
        String senha,
        int empresaId
    
    ){
    
        super(
            usuarioId, 
            2, 
            nomeUsuario, 
            cpfCnpj, 
            emails,
            endereco, 
            telefone,
            empresaId
        );
        
        this.login = login;
        this.senha = senha;

    }
    
    public String getLogin(){
        return login;
    }
    
    public String getSenha(){
        return senha;
    }
}
