package controladores;

import banco.CadastrarUsuario;
import banco.comandos.Conexao;
import java.util.ArrayList;
import objetos.Endereco;
import objetos.Telefone;

/**
 *
 * @author marce
 */
public class CtrCadastroAdmin {
    
    private static String nome;
    private static String cpfCnpj;
    private static ArrayList<Endereco> enderecos;
    private static ArrayList<String> emails;
    private static ArrayList<Telefone> telefones;
    
    public static void cadastroInfoBasica(
        String cadastroNomeRazaoSocial, 
        String cadastroCpfCnpj, 
        ArrayList<String> cadastroEmails,
        ArrayList<Telefone> cadastroTelefones
    ){
        nome = cadastroNomeRazaoSocial;
        cpfCnpj = cadastroCpfCnpj;
        emails = cadastroEmails;
        telefones = cadastroTelefones;
        
    }
    
    public static void cadastroInfoEndereco(ArrayList<Endereco> cadastroEnderecos){
        enderecos = cadastroEnderecos;
    }
   
    protected static void cadastrarNovoAdmin(Conexao con){
 
        CadastrarUsuario.inerirNovoUsuario(con, nome, cpfCnpj, emails, enderecos, telefones);
        
    }
    
}
