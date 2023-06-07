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
        ArrayList<String> emails,
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
