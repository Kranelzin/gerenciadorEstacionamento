package com.mycompany.gerenciamentoestacionamento;

import banco.Conexao;
import banco.Consulta;
import interfaceGrafica.Login;


public class GerenciamentoEstacionamento {

    public static void main(String[] args) {
         Conexao con = new Conexao();
         Consulta consulta = con.novaConsulta();
         String sql = "SELECT * FROM USUARIO WHERE USUARIO_ID = ? ";
         consulta.setSql(sql);
         consulta.pesquisar(new Object[]{0});
         System.out.println(consulta.getDados());
    }
}
/*

java.awt.EventQueue.invokeLater(() -> {
             new Login().setVisible(true);
         });
*/