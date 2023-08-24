package objetos;

/**
 *
 * @author marce
 */

import enums.TipoUsuario;
import java.util.ArrayList;

public class Admin extends UsuarioLogin{
    
    public Admin(
        int usuarioId, 
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
            TipoUsuario.ADMIN, 
            nomeUsuario, 
            cpfCnpj, 
            emails,
            endereco, 
            telefone,
            login,
            senha,
            empresaId,
            ativo
        );
        
    }
    
}
