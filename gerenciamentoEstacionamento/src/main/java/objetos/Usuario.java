package objetos;

/**
 *
 * @author marce
 */

import java.util.ArrayList;

public class Usuario {
    private final int usuarioId;
    private final int tipo; //default 0;  0 = Cliente, 1 = Funcionario; 2 = Gerente;
    private final String nomeUsuario;
    private final int cpfCnpj;
    private final String email;
    private final ArrayList<Endereco> endereco;
    private final ArrayList<Telefone> telefone;
    
    
    public Usuario(
        int usuarioId, 
        int tipo, 
        String nomeUsuario, 
        int cpfCnpj, 
        String email,
        ArrayList<Endereco> endereco, 
        ArrayList<Telefone> telefone
    ) {
        this.usuarioId = usuarioId;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public int getTipo() {
        return tipo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public int getCpfCnpj() {
        return cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Endereco> getEndereco() {
        return endereco;
    }

    public ArrayList<Telefone> getTelefone() {
        return telefone;
    }
 
}
