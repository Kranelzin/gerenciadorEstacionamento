package controladores;

import banco.BancoUsuario;
import banco.comandos.Conexao;
import enums.TipoUsuario;
import java.util.ArrayList;
import objetos.Endereco;
import objetos.Telefone;
import objetos.UsuarioLogin;

/**
 *
 * @author marce
 */
public class CtrUsuario {
    
    private static UsuarioLogin usuarioLogin;
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
 
        BancoUsuario.inerirNovoUsuario(con, empresaId, login, senha, tipoUsuario, nome, cpfCnpj, emails, enderecos, telefones);
        
    }
    
    public static void cadastrarNovoUsuario(){
        Conexao con = new Conexao();
        con.abrirConexao();
        
        int empresaId = CtrLogin.getEmpresa().getEmpresaId();
        
        BancoUsuario.inerirNovoUsuario(con, empresaId, login, senha, tipoUsuario, nome, cpfCnpj, emails, enderecos, telefones);
        
        con.fecharConexao();
        
    }
    
    public static UsuarioLogin getUsuarioLogin(){
        return usuarioLogin;
    }

    public static void UpdateUsuario() {
        Conexao con = new Conexao();
        con.abrirConexao();
        
        BancoUsuario.UpdateUsuario(con, usuarioLogin.getUsuarioId(), login, senha, nome, cpfCnpj, emails, telefones, enderecos);
        
        con.fecharConexao();
    }
    
}
