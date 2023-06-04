package objetos;

/**
 *
 * @author marce
 */

import enums.Meses;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Cliente extends Usuario{
    
    private ArrayList<BoxVaga> boxVagas;
    private boolean mensalista;
    private Mensalidade mensalidade;
    private ArrayList<Pagamento> pagamentos;
    private ArrayList<Veiculo> veiculos;
    
    public Cliente(
        int usuarioId, 
        int tipo, 
        String nomeUsuario, 
        int cpfCnpj, 
        String email,
        ArrayList<Endereco> endereco, 
        ArrayList<Telefone> telefone,
        ArrayList<Veiculo> veiculos
    ){
        super(
            usuarioId, 
            0, 
            nomeUsuario, 
            cpfCnpj, 
            email,
            endereco, 
            telefone
            );

        this.veiculos = veiculos;
    }
    
    
    public ArrayList<BoxVaga> getBoxVaga(){
        return boxVagas;
    }
    
    public ArrayList<Pagamento> getPagamentos(){
        return pagamentos;
    }
    
    public Mensalidade getMensalidade(){
        return mensalidade;
    }
    
    public boolean isMensalista(){
        return mensalista;
    }
    
    public ArrayList<Veiculo> getVeiculos(){
        return veiculos;
    }
    
    public void adicionarVeiculo(Veiculo veiculo){
        veiculos.add(veiculo);
    }
    
    public void realizarPagamento(BigDecimal valor, Timestamp data, Meses mesReferencia, int anoReferencia){
        realizarPagamento(valor, data, mesReferencia, anoReferencia, false);
    }
    
    public void realizarPagamento(BigDecimal valor, Timestamp data, Meses mesReferencia, int anoReferencia, boolean pagamentoMensalidade){
        if(mensalista && pagamentoMensalidade)
            mensalidade.realizarPagamento(valor, data, mesReferencia, anoReferencia);
        pagamentos.add(new Pagamento(valor, data));
    }
    
    public void cadastrarPagamentoMensal(int diaVencimentoPagamento, Timestamp dataInicio){
        mensalista = true;
        mensalidade = new Mensalidade(diaVencimentoPagamento, dataInicio);
    }
    
    public void setBoxVaga(ArrayList<BoxVaga> boxVaga){
        this.boxVagas = boxVaga;
    }
    
    public void setVeiculos(ArrayList<Veiculo> veiculos){
        this.veiculos = veiculos;
    }
                
}
