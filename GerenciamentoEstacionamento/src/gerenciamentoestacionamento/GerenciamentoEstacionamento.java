package gerenciamentoestacionamento;

import banco.comandos.Conexao;
import banco.comandos.Consulta;
import banco.comandos.Delete;
import banco.comandos.Insert;
import banco.comandos.Update;
import interfaceGrafica.Login;
import java.util.ArrayList;

/**
 *
 * @author marce
 */
public class GerenciamentoEstacionamento {
    
    

    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
       
    }
    
}
