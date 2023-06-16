package objetos;

/**
 *
 * @author marce
 */

import java.util.ArrayList;

public class Admin extends UsuarioLogin{
    
    private static int NIVEL_ACESSO = 2;
    
    public Admin(
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
