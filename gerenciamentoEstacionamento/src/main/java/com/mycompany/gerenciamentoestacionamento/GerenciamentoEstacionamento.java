package com.mycompany.gerenciamentoestacionamento;

import banco.Conexao;
import banco.Consulta;
import banco.Insert;
import interfaceGrafica.Login;


public class GerenciamentoEstacionamento {

    public static void main(String[] args) {
         Conexao con = new Conexao();
         insertDados(con);
         verDados(con);
    }
    
    private static void insertDados(Conexao con){
        Insert insert = con.novoInsert();
        String sql = "INSERT INTO USUARIO(NOME, CPF_CNPJ) VALUES(?, ?) ";
        insert.setSql(sql);
        insert.executarComando(new Object[]{"marcelo", "12345678910111"});
    }
    
    private static void verDados(Conexao con){
        Consulta consulta = con.novaConsulta();
        String sql = "SELECT * FROM USUARIO ";
        consulta.setSql(sql);
        consulta.executarComando();
        
        while(!consulta.fimConsulta()){
            System.out.println(consulta.getString("NOME"));
            System.out.println(consulta.getString("CPF_CNPJ"));
        }
    }
}
/*

java.awt.EventQueue.invokeLater(() -> {
             new Login().setVisible(true);
         });
*/