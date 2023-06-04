package objetos;

import java.util.ArrayList;

/**
 *
 * @author marce
 */
public class Empresa {
    private final int empresaId;
    private final String empresaNome;
    private final int cpfCnpj;
    private final ArrayList<String> emails;
    private final ArrayList<Telefone> telfones;
    private final Endereco endereco;
    private ArrayList<Admin> admins;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<Cliente> clientes;

    public Empresa(
        int empresaId, 
        String empresaNome, 
        int cpfCnpj, 
        ArrayList<String> emails, 
        ArrayList<Telefone> telfones,
        Endereco endereco
    ) {
        this.empresaId = empresaId;
        this.empresaNome = empresaNome;
        this.cpfCnpj = cpfCnpj;
        this.emails = emails;
        this.telfones = telfones;
        this.endereco = endereco;
    }
    
    public int getEmpresaId() {
        return empresaId;
    }

    public String getEmpresaNome() {
        return empresaNome;
    }

    public int getCpfCnpj() {
        return cpfCnpj;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public ArrayList<Telefone> getTelfones() {
        return telfones;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    
    public void adicionarAdmin(Admin admin){
        admins.add(admin);
    }
    
    public boolean removerAdmin(Admin admin){
        boolean retorno = false;
        for(Admin adm : admins){
            if(adm == admin){
                admins.remove(adm);
                retorno = true;
            }
        }
        
        return retorno;
    }
    
    public void adicionarFuncionario(Admin funcionario){
        admins.add(funcionario);
    }
    
    public boolean removerAdmin(Funcionario funcionario){
        boolean retorno = false;
        for(Funcionario fun : funcionarios){
            if(fun == funcionario){
                funcionarios.remove(fun);
                retorno = true;
            }
        }
        
        return retorno;
    }
    
    public void adicionarCliente(Cliente cliente){
        clientes.add(cliente);
    }
    
    
}
