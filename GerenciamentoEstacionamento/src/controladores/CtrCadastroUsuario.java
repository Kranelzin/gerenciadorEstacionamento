package controladores;

import banco.CadastrarUsuario;
import banco.comandos.Conexao;
import enums.TipoUsuario;
import java.util.ArrayList;
import objetos.Endereco;
import objetos.Telefone;

/**
 *
 * @author marce
 */
public class CtrCadastroUsuario {
    
    private static String login;
    private static String senha;
    private static TipoUsuario tipoUsuario;
    private static String nome;
    private static String cpfCnpj;
    private static ArrayList<Endereco> enderecos;
    private static ArrayList<String> emails;
    private static ArrayList<Telefone> telefones;
    
    public static void cadastroInfoBasica(
        String cadastroLogin,
        String cadastroSenha,
        String cadastroNomeRazaoSocial, 
        String cadastroCpfCnpj, 
        ArrayList<String> cadastroEmails,
        ArrayList<Telefone> cadastroTelefones,
        TipoUsuario cadastroTipoUsuario
    ){
        login = cadastroLogin;
        senha = cadastroSenha;
        nome = cadastroNomeRazaoSocial;
        cpfCnpj = cadastroCpfCnpj;
        emails = cadastroEmails;
        telefones = cadastroTelefones;
        tipoUsuario = cadastroTipoUsuario;
    }
    
    public static void cadastroInfoEndereco(ArrayList<Endereco> cadastroEnderecos){
        enderecos = cadastroEnderecos;
    }
   
    public static void cadastrarNovoUsuario(Conexao con, int empresaId){
 
        CadastrarUsuario.inerirNovoUsuario(con, empresaId, login, senha, tipoUsuario, nome, cpfCnpj, emails, enderecos, telefones);
        
    }
    
    public static void cadastrarNovoUsuario(){
        Conexao con = new Conexao();
        con.abrirConexao();
        
        int empresaId = CtrLogin.getEmpresa().getEmpresaId();
        
        CadastrarUsuario.inerirNovoUsuario(con, empresaId, login, senha, tipoUsuario, nome, cpfCnpj, emails, enderecos, telefones);
        
    }
    
}
