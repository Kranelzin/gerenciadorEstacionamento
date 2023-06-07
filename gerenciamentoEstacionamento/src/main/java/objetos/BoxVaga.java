package objetos;

/**
 *
 * @author marce
 */

import exceptions.EstacionarVagaException;
import java.sql.Timestamp;

public class BoxVaga {
    private final char codigo;
    private final int vaga;
    private Timestamp dataHoraUltEntrada;
    private Timestamp dataHoraUltSaida;
    private boolean emUso;
    private Veiculo veiculo;
    
    public BoxVaga(
        char codigo,
        int vaga,
        Timestamp dataHoraUltEntrada
    ){
        this.codigo = codigo;
        this.vaga = vaga;
    }
    
    public String getCodigoVaga(){
        return codigo+Integer.toString(vaga);
    }
    
    public char getCodigo(){
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
    
    public boolean getEmUso(){
        return emUso;
    }
    
    public void setEmUso(boolean emUso){
        this.emUso = emUso;
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
    }
}
