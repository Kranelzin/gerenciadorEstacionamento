package gerenciamentoestacionamento;

import interfaceGrafica.Login;

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
