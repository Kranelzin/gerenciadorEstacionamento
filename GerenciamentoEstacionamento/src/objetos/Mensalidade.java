package objetos;

/**
 *
 * @author marce
 */

import enums.Meses;
import exceptions.RealizarPagamentoException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Mensalidade{
    private BigDecimal valorMensalidade;
    private final Date dataInicio;
    private Date dataFim;
    private boolean ativa;
    private final int diaVencimentoPagamento;
    private ArrayList<PagamentoMensalidade> pagamentosRealizados;
    private ArrayList<PagamentoMensalidade> pagamentos;
    
    public Mensalidade (int diaVencimentoPagamento, Date dataInicio, BigDecimal valorMensalidade){
        this.diaVencimentoPagamento = diaVencimentoPagamento;
        this.dataInicio = dataInicio;
        this.valorMensalidade = valorMensalidade;
        ativa = true;
    }
    
    public boolean isAtiva(){
        return ativa;
    }
    public BigDecimal getValorMensalidade(){
        return valorMensalidade;
    }
    
    public int getDiaVencimento(){
        return diaVencimentoPagamento;
    }
    
    public Date getDataInicio(){
        return dataInicio;
    }
    
    public Date getDataFim() {
        return dataFim;
    }
    
    public ArrayList<PagamentoMensalidade> getMesesPagamento(){
        
        Date data = new Date();
        Calendar dataAtual = new GregorianCalendar();
        dataAtual.setTime(data);
        
        int diaAtual = dataAtual.get(Calendar.DAY_OF_MONTH);
        
        if(diaAtual <= diaVencimentoPagamento)
            dataAtual.add(Calendar.MONTH, -1);
                    
        Calendar dataInicioMens = new GregorianCalendar();
        dataInicioMens.setTime(dataInicio);
        
        if(dataFim != null)
            dataAtual.setTime(dataFim);
        
        while(dataInicioMens.before(dataAtual)){
            int mes = dataInicioMens.get(Calendar.MONTH + 1);
            int ano =  dataInicioMens.get(Calendar.YEAR);
            PagamentoMensalidade pagamento = getMesPago(mes, ano);
            pagamentos.add(pagamento);
            dataInicioMens.add(Calendar.MONTH, 1);
        }
        
        return pagamentos;
    }
    
    private PagamentoMensalidade getMesPago(int mes, int ano){
        for(PagamentoMensalidade pagamento : pagamentosRealizados){
            if(pagamento.getMesReferencia().getIndice() == mes 
                && pagamento.getAnoReferencia() == ano){
                return pagamento;
            }
        }
        PagamentoMensalidade pagamentoPendente = new PagamentoMensalidade(
                null, 
                null, 
                Meses.obterPorIndice(mes), 
                ano
        );
        
        return pagamentoPendente;
    }
    
    public void setPagamentosRealizados(ArrayList<PagamentoMensalidade> pagamentosRealizados){
        this.pagamentosRealizados = pagamentosRealizados;
    }
    
    public ArrayList<PagamentoMensalidade> getPagamentosRealizados(){
        return pagamentosRealizados;
    }
    
    public void realizarPagamento(BigDecimal valor, Date dataPagamento, Meses mesReferencia, int anoReferencia) throws RealizarPagamentoException{
        if((valor.compareTo(valorMensalidade) != 0))
            throw new RealizarPagamentoException(valorMensalidade);
        
        PagamentoMensalidade pagamanetoMensalidade = new PagamentoMensalidade(
                valor,
                dataPagamento, 
                mesReferencia, 
                anoReferencia
        );
        
        pagamentosRealizados.add(pagamanetoMensalidade);
    }
    
    public void canelarMensalidade(Date dataFim){
        this.dataFim = dataFim;
        ativa = false;
    }

}
