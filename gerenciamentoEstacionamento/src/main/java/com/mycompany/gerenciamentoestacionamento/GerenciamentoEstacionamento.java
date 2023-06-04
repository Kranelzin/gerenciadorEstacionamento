package com.mycompany.gerenciamentoestacionamento;

import interfaceGrafica.Login;


public class GerenciamentoEstacionamento {

    public static void main(String[] args) {
         java.awt.EventQueue.invokeLater(() -> {
             new Login().setVisible(true);
         });
    }
}
/*

java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroCliente().setVisible(true);
            }
        });
*/