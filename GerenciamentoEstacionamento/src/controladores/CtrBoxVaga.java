package controladores;

import banco.BancoBoxVaga;
import banco.comandos.Conexao;
import exceptions.EstacionarVagaException;
import java.util.ArrayList;
import objetos.BoxVaga;
import objetos.Veiculo;

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
    
    public static void setBoxVagas(Conexao con, int empresaId) throws EstacionarVagaException{
        boxVagas = BancoBoxVaga.getBoxVagas(con, empresaId);
    }

    public static BoxVaga buscarVagaPorCodigo(String vaga) {
        for(BoxVaga boxVaga : boxVagas){
            if(boxVaga.getCodigoVaga().contentEquals(vaga) && !boxVaga.isEmUso() && !boxVaga.isReservada())
                return boxVaga;
        }
        
        return null;
    }

    public static void lotarVaga(int usuarioId, BoxVaga boxVaga) {
        Conexao con = new Conexao();
        con.abrirConexao();
        
        BancoBoxVaga.lotarVaga(con, usuarioId, boxVaga);
        
        con.fecharConexao();
    }

    public static void liberarVaga(BoxVaga vagaLiberar) {
        Conexao con = new Conexao();
        con.abrirConexao();
        
        BancoBoxVaga.liberarVaga(con, vagaLiberar);
        
        con.fecharConexao();
    }
        
}
