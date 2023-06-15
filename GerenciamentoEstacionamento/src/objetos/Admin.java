package objetos;

/**
 *
 * @author marce
 */

import java.util.ArrayList;

public class Admin extends Usuario{
    
    private final String login;
    private final String senha;
    
    public Admin(
        int usuarioId, 
        int tipo, 
        String nomeUsuario, 
        String cpfCnpj, 
        ArrayList<String> emails,
        ArrayList<Endereco> endereco, 
        ArrayList<Telefone> telefone,
        String login,
        String senha
    ){
        super(
            usuarioId, 
            2, 
            nomeUsuario, 
            cpfCnpj, 
            emails,
            endereco, 
            telefone
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
