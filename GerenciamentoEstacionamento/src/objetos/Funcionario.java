package objetos;

/**
 *
 * @author marce
 */

import java.util.ArrayList;

public class Funcionario extends UsuarioLogin{
    
    private static int NIVEL_ACESSO = 1;
    
    public Funcionario(
        int usuarioId, 
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
            NIVEL_ACESSO, 
            nomeUsuario, 
            cpfCnpj, 
            emails,
            endereco, 
            telefone,
            login,
            senha,
            empresaId
        );
        
    }
   
}
