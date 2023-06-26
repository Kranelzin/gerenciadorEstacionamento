package objetos;

/**
 *
 * @author marce
 */

import exceptions.EstacionarVagaException;
import java.sql.Timestamp;

public class BoxVaga {
    private int usuarioId;
    private final String codigo;
    private final int vaga;
    private Timestamp dataHoraUltEntrada;
    private Timestamp dataHoraUltSaida;
    private boolean emUso;
    private boolean reservada;
    private Veiculo veiculo;
    
    public BoxVaga(
        String codigo,
        int vaga,
        Timestamp dataHoraUltEntrada,
        Timestamp dataHoraUltSaida,
        boolean reservada,
        Veiculo veiculo
    ){
        this.codigo = codigo;
        this.vaga = vaga;
        this.dataHoraUltEntrada = dataHoraUltEntrada;
        this.dataHoraUltSaida = dataHoraUltSaida;
        this.reservada = reservada;
        this.veiculo = veiculo;
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
        if(emUso)
            throw new EstacionarVagaException(emUso, veiculo);
        
        this.dataHoraUltEntrada = dataHoraUltEntrada;
        emUso = true;
    }
    
    public void liberarVaga(Timestamp dataHoraUltSaida) throws EstacionarVagaException{
        if(!emUso)
            throw new EstacionarVagaException(!emUso, veiculo);
        
        this.dataHoraUltSaida = dataHoraUltSaida;
        emUso = false;
        veiculo = null;
    }
}
