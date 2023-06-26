package controladores;

import banco.BancoCliente;
import banco.comandos.Conexao;
import enums.TipoUsuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import objetos.BoxVaga;
import objetos.Cliente;
import objetos.Endereco;
import objetos.Mensalidade;
import objetos.Telefone;
import objetos.Veiculo;
/**
 *
 * @author marce
 */
public class CtrCliente {
    
    private static Cliente cliente;
    private static String nome;
    private static String cpfCnpj;
    private static ArrayList<Endereco> enderecos;
    private static ArrayList<String> emails;
    private static ArrayList<Telefone> telefones;
    private static ArrayList<BoxVaga> boxVagas;
    private static Mensalidade mensalidade;
    private static ArrayList<Veiculo> veiculos;

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
    
    public static void cadastroInfoVeiculo(ArrayList<Veiculo> cadastroVeiculos){
        veiculos = cadastroVeiculos;
    }

    public static void cadastroInfoMensalidade(Date dataInicio, int diaVencimento, ArrayList<BoxVaga> cadastroBoxVagas, BigDecimal valor) {
        
        boxVagas = cadastroBoxVagas;
        
        valor = valor.multiply(new BigDecimal(boxVagas.size()));
        
        Mensalidade cadastroMensalidade = new Mensalidade(
            diaVencimento,
            dataInicio,
            valor
        );
        
        mensalidade = cadastroMensalidade;
    }

    public static void cadastrarCliente() {
        Conexao con = new Conexao();
        con.abrirConexao();
        
        Cliente cliente = BancoCliente.inerirNovoCliente(
            con, 
            CtrLogin.getEmpresa().getEmpresaId(), 
            TipoUsuario.CLIENTE, 
            nome, 
            cpfCnpj, 
            emails, 
            enderecos, 
            telefones, 
            veiculos
        );
        
        if(mensalidade != null){
            cliente.cadastrarPagamentoMensal(mensalidade, boxVagas);
            BancoCliente.inserirMensalidade(con, cliente.getUsuarioId(), cliente.getMensalidade());
            BancoCliente.cadastrarBoxVagasReservadas(con, cliente.getUsuarioId(), boxVagas);
        }
        
        con.fecharConexao();
    }

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(String nomeCliente) {
        
        Conexao con = new Conexao();
        
        con.abrirConexao(true);
        
        cliente = BancoCliente.buscarCliente(con, nomeCliente);
        
        con.fecharConexao();
        
    }

}
