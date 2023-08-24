package controladores;

import banco.BancoBoxVaga;
import banco.comandos.Conexao;
/**
 *
 * @author marce
 */
public class CtrCadastroBoxVaga {
    
    public static void cadastrarBoxVaga(String codigo, String vaga){
        Conexao con = new Conexao();
        con.abrirConexao();

        BancoBoxVaga.inerirNovoBoxVaga(con, CtrLogin.getEmpresa().getEmpresaId(), codigo, vaga);
       
        con.fecharConexao();

    }
    
   
    
}
