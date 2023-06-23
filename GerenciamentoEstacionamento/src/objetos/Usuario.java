package objetos;

/**
 *
 * @author marce
 */

import enums.TipoUsuario;
import java.util.ArrayList;

public class Usuario {
    private final int usuarioId;
    private final TipoUsuario tipo;
    private final String nomeUsuario;
    private final String cpfCnpj;
    private final ArrayList<String> emails;
    private final ArrayList<Endereco> endereco;
    private final ArrayList<Telefone> telefone;
    private final int empresaId;
    
    
    public Usuario(
        int usuarioId, 
        TipoUsuario tipo, 
        String nomeUsuario, 
        String cpfCnpj, 
        ArrayList<String> emails,
        ArrayList<Endereco> endereco, 
        ArrayList<Telefone> telefone,
        int empresaId
    ) {
        this.usuarioId = usuarioId;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.cpfCnpj = cpfCnpj;
        this.emails = emails;
        this.endereco = endereco;
        this.telefone = telefone;
        this.empresaId = empresaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public ArrayList<Endereco> getEndereco() {
        return endereco;
    }

    public ArrayList<Telefone> getTelefone() {
        return telefone;
    }
    
    
    public int getEmpresaId(){
        return empresaId;
    }
 
}
