package objetos;

/**
 *
 * @author marce
 */

import Repositorio.Biblioteca;
import enums.Meses;
import enums.TipoUsuario;
import exceptions.EstacionarVagaException;
import exceptions.RealizarPagamentoException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Cliente extends Usuario{
    
    private ArrayList<BoxVaga> boxVagas = new ArrayList<>();
    private boolean mensalista;
    private Mensalidade mensalidade;
    private ArrayList<Pagamento> pagamentos = new ArrayList<>();
    private ArrayList<Veiculo> veiculos = new ArrayList<>();
    
    public Cliente(
        int usuarioId, 
        String nomeUsuario, 
        String cpfCnpj, 
        ArrayList<String> emails,
        ArrayList<Endereco> endereco, 
        ArrayList<Telefone> telefone,
        ArrayList<Veiculo> veiculos,
        int empresaId
    ){
        super(
            usuarioId, 
            TipoUsuario.CLIENTE, 
            nomeUsuario, 
            cpfCnpj, 
            emails,
            endereco, 
            telefone,
            empresaId
            );

        this.veiculos = veiculos;
    }
    
    
    public ArrayList<BoxVaga> getBoxVagas(){
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
    
    public void realizarPagamento(BigDecimal valor, Timestamp data, Meses mesReferencia, int anoReferencia) throws RealizarPagamentoException{
        realizarPagamento(valor, data, mesReferencia, anoReferencia, false);
    }
    
    public void realizarPagamento(BigDecimal valor, Timestamp data, Meses mesReferencia, int anoReferencia, boolean pagamentoMensalidade) throws RealizarPagamentoException{
        if(mensalista && pagamentoMensalidade)
            mensalidade.realizarPagamento(valor, data, mesReferencia, anoReferencia);
        pagamentos.add(new Pagamento(valor, data));
    }
    
    public void cadastrarPagamentoMensal(Mensalidade mensalidade, ArrayList<BoxVaga> boxVagas){
        mensalista = true;
        this.mensalidade = mensalidade;
        
        for(BoxVaga boxVaga : boxVagas){
            boxVaga.setReservada(true);
            if(!this.boxVagas.contains(boxVaga)){
                this.boxVagas.add(boxVaga);
            }
        }
        
    }
    
    public void cancelarMensalidade(Date dataFim){
        mensalista = false;
        mensalidade.canelarMensalidade(dataFim);
        
        for(BoxVaga boxVaga : boxVagas){
            if(boxVaga.isReservada())
                boxVaga.setReservada(false);
        }
        
        boxVagas = null;
    }
    
    public void setBoxVaga(ArrayList<BoxVaga> boxVaga){
        this.boxVagas = boxVaga;
    }
    
    public void setVeiculos(ArrayList<Veiculo> veiculos){
        this.veiculos = veiculos;
    }
   
    public void liberarVaga(Timestamp dataHoraSaida, BoxVaga vagaLiberar) throws EstacionarVagaException{   
        for(BoxVaga boxVaga : boxVagas){
            if(boxVaga.getCodigoVaga().contentEquals(vagaLiberar.getCodigoVaga()) && !boxVaga.isReservada()){
                boxVaga.liberarVaga(dataHoraSaida);
                boxVagas.remove(boxVaga);
                break;
            }
        }
    }
    
    public void estacionarVaga(Timestamp dataHoraSaida, BoxVaga boxVaga, Veiculo veiculo) throws EstacionarVagaException{
        if(!boxVaga.isEmUso() && (!boxVaga.isReservada() || boxVagas.contains(boxVaga)))
            boxVaga.estacionarVaga(dataHoraSaida, veiculo);
        else
            throw new EstacionarVagaException(true, boxVaga.getVeiculo());
    }

    public ArrayList<Veiculo> getVeiculosLotadosVagas() {
        
        ArrayList<Veiculo> veiculosEmVaga = new ArrayList<>();

        for(BoxVaga boxVaga : boxVagas){
            if(boxVaga.getVeiculo() != null){
                veiculosEmVaga.add(boxVaga.getVeiculo());
            }
        }
        
        return veiculosEmVaga;
    }

    public BoxVaga getBoxVagaPorVeiculo(Veiculo veiculo) throws Exception {
        BoxVaga boxVagaVeiculo = null; 
        
        String[] componentes = {
            veiculo.getPlaca(),
            veiculo.getMarca(),
            veiculo.getModelo()
        };
        
        for(BoxVaga boxVaga : boxVagas){
            String[] componentesComparar = {
                boxVaga.getVeiculo().getPlaca(),
                boxVaga.getVeiculo().getMarca(),
                boxVaga.getVeiculo().getModelo()
            };
            
            if(Biblioteca.verificarContemNosComponentes(componentes, componentesComparar)){
                boxVagaVeiculo = boxVaga;
            }
        }
        
        return boxVagaVeiculo;
    }
                
}
