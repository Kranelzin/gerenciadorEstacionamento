package objetos;

/**
 *
 * @author marce
 */

import java.util.ArrayList;

public class Funcionario extends Usuario{
    
    private final String login;
    private final String senha;
    
    public Funcionario(
        int usuarioId, 
        String nomeUsuario, 
        int cpfCnpj, 
        String email,
        ArrayList<Endereco> endereco, 
        ArrayList<Telefone> telefone,
        String login,
        String senha
    ){
        super(
            usuarioId, 
            1, 
            nomeUsuario, 
            cpfCnpj, 
            email,
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
