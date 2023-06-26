package controladores;

import banco.CadastrarBuscarBoxVaga;
import banco.comandos.Conexao;
import java.util.ArrayList;
import objetos.BoxVaga;

/**
 *
 * @author marce
 */
public class CtrBoxVaga {
    
    private static ArrayList<BoxVaga> boxVagas;
    
    public static ArrayList<BoxVaga> getBoxVagasDisponiveis(){
        ArrayList<BoxVaga> vagasDisponiveis = new ArrayList<>();
        
        for(BoxVaga boxVaga : boxVagas){
            if(!boxVaga.isEmUso() && !boxVaga.isReservada())
                vagasDisponiveis.add(boxVaga);
        }
        
        return vagasDisponiveis;
    }
    
    public static void setBoxVagas(Conexao con, int empresaId){
        boxVagas = CadastrarBuscarBoxVaga.getBoxVagas(con, empresaId);
    }

    public static BoxVaga buscarVagaPorCodigo(String vaga) {
        for(BoxVaga boxVaga : boxVagas){
            if(boxVaga.getCodigoVaga().contentEquals(vaga) && !boxVaga.isEmUso() && !boxVaga.isReservada())
                return boxVaga;
        }
        
        return null;
    }
        
}
