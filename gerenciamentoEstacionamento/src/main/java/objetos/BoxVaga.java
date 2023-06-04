package objetos;

/**
 *
 * @author marce
 */

import java.sql.Timestamp;

public class BoxVaga {
    private final int boxVagaId;
    private final String codigo;
    private final int vaga;
    private Timestamp dataHoraUltEntrada;
    private Timestamp dataHoraUltSaida;
    private boolean emUso;
    
    public BoxVaga(
        int boxVagaId,
        String codigo,
        int vaga,
        Timestamp dataHoraUltEntrada
    ){
        this.boxVagaId = boxVagaId;
        this.codigo = codigo;
        this.vaga = vaga;
        this.dataHoraUltEntrada = dataHoraUltEntrada;
        emUso = true;
    }
    
    public int getBoxVagaId(){
        return boxVagaId;
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
    
    public boolean getEmUso(){
        return emUso;
    }
    
    public void setDataHoraUltEntrada(Timestamp dataHoraUtlEntrada){
        this.dataHoraUltEntrada = dataHoraUtlEntrada;
    }
    
    public void setDataHoraUltSaida (Timestamp dataHoraUltSaida){
        this.dataHoraUltSaida = dataHoraUltSaida;
    }
    
    public void liberarVaga(Timestamp dataHoraUltSaida){
        this.dataHoraUltEntrada = dataHoraUltSaida;
        emUso = false;
    }
}
