package objetos;

/**
 *
 * @author marce
 */

import exceptions.EstacionarVagaException;
import java.sql.Timestamp;

public class BoxVaga {
    
    private int usuarioId;
    private int empresaId;
    private final String codigo;
    private final int vaga;
    private Timestamp dataHoraUltEntrada;
    private Timestamp dataHoraUltSaida;
    private boolean emUso;
    private boolean reservada;
    private Veiculo veiculo;
    
    public BoxVaga(
        int empresaId,
        String codigo,
        int vaga,
        Timestamp dataHoraUltEntrada,
        Timestamp dataHoraUltSaida,
        boolean reservada,
        Veiculo veiculo
    ) throws EstacionarVagaException{
        this.empresaId = empresaId;
        this.codigo = codigo;
        this.vaga = vaga;
        this.dataHoraUltEntrada = dataHoraUltEntrada;
        this.dataHoraUltSaida = dataHoraUltSaida;
        this.reservada = reservada;
        this.veiculo = veiculo;
        
        if(dataHoraUltEntrada != null && dataHoraUltSaida == null && veiculo != null){
            estacionarVaga(dataHoraUltEntrada, veiculo, true);
        }
        else if(dataHoraUltSaida != null && veiculo != null){
            liberarVaga(dataHoraUltSaida, true);
        }
    }
    
    public int getEmpresaId(){
        return empresaId;
    }
    
    public int getUsuarioId(){
        return usuarioId;
    }
    
    public String getCodigoVaga(){
        return codigo + Integer.toString(vaga);
    }
    
    public String getCodigo(){
        return codigo;
    }
    
    public int getVaga(){
        return vaga;
    }
    
     public Timestamp getDataHoraUltEntrada(){
        return dataHoraUltEntrada;
    }
    
    public Timestamp getDataHoraUltSaida(){
        return dataHoraUltSaida;
    }
    
    public boolean isReservada(){
        return reservada;
    }
    
    public Veiculo getVeiculo(){
        return veiculo;
    }
    
    public boolean isEmUso(){
        return emUso;
    }
    
    public void setUsuarioId(int usuarioId){
        this.usuarioId = usuarioId;
    }
    
    public void setEmUso(boolean emUso){
        this.emUso = emUso;
    }
    
    public void setReservada(boolean reservada){
        this.reservada = reservada;
    }
    
    public void setDataHoraUltEntrada(Timestamp dataHoraUtlEntrada){
        this.dataHoraUltEntrada = dataHoraUtlEntrada;
    }
    
    public void setDataHoraUltSaida (Timestamp dataHoraUltSaida){
        this.dataHoraUltSaida = dataHoraUltSaida;
    }
    
    public void estacionarVaga(Timestamp dataHoraUltEntrada, Veiculo veiculo) throws EstacionarVagaException{
        estacionarVaga(dataHoraUltEntrada, veiculo, false);
    }
    
    private void estacionarVaga(Timestamp dataHoraUltEntrada, Veiculo veiculo, boolean construtor) throws EstacionarVagaException{
        if(emUso && ! construtor)
            throw new EstacionarVagaException(emUso, veiculo);
        
        this.dataHoraUltEntrada = dataHoraUltEntrada;
        this.dataHoraUltSaida = null;
        this.veiculo = veiculo;
        emUso = true;
    }
    
    public void liberarVaga(Timestamp dataHoraUltSaida) throws EstacionarVagaException{
        liberarVaga(dataHoraUltSaida, false);
    }
    private void liberarVaga(Timestamp dataHoraUltSaida, boolean construtor) throws EstacionarVagaException{
        if(!emUso && ! construtor)
            throw new EstacionarVagaException(!emUso, veiculo);
        
        this.dataHoraUltSaida = dataHoraUltSaida;
        this.dataHoraUltEntrada = null;
        emUso = false;
        veiculo = null;
    }
}
