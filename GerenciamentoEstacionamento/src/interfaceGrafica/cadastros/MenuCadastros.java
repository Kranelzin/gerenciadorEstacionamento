package interfaceGrafica.cadastros;

import controladores.CtrInterfacesGraficas;
import enums.TipoCadastro;
import interfaceGrafica.Menu;

/**
 *
 * @author marce
 */
public class MenuCadastros extends javax.swing.JFrame {

    public MenuCadastros() {
        initComponents();
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btCadastrarCliente = new javax.swing.JButton();
        btCadastrarFuncionario = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btCadastrarVaga = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btCadastrarCliente.setText("Cadastrar Cliente");
        btCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarClienteActionPerformed(evt);
            }
        });

        btCadastrarFuncionario.setText("Cadastrar Funcionário");
        btCadastrarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarFuncionarioActionPerformed(evt);
            }
        });

        jButton3.setText("Cadastrar Admin");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Editar Cliente");

        jButton5.setText("Editar Funcionario");

        jButton6.setText("Editar Admin");

        btCadastrarVaga.setText("Cadastrar Vaga");
        btCadastrarVaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarVagaActionPerformed(evt);
            }
        });

        jButton8.setText("Editar Vaga");

        btVoltar.setText("Voltar");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCadastrarVaga)
                    .addComponent(jButton8)
                    .addComponent(btCadastrarFuncionario)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(btCadastrarCliente)
                    .addComponent(jButton3)
                    .addComponent(jButton6))
                .addGap(116, 116, 116))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btVoltar)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btCadastrarVaga))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btVoltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCadastrarCliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton4))
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCadastrarFuncionario)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarFuncionarioActionPerformed
        setVisible(false);
        CadastroInfoBasica cadastroFuncionario = new CadastroInfoBasica(TipoCadastro.FUNCIONARIO);
        CtrInterfacesGraficas.setCadastroInfoBasica(cadastroFuncionario);
        cadastroFuncionario.setVisible(true);
    }//GEN-LAST:event_btCadastrarFuncionarioActionPerformed

    private void btCadastrarVagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarVagaActionPerformed
        setVisible(false);
        CadastroBoxVaga cadastroBoxVaga = new CadastroBoxVaga();
        cadastroBoxVaga.setVisible(true);
    }//GEN-LAST:event_btCadastrarVagaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setVisible(false);
        CadastroInfoBasica cadastroAdmin = new CadastroInfoBasica(TipoCadastro.ADMIN);
        CtrInterfacesGraficas.setCadastroInfoBasica(cadastroAdmin);
        cadastroAdmin.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        setVisible(false);
        Menu menu = CtrInterfacesGraficas.getMenu();
        menu.setVisible(true);
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarClienteActionPerformed
        setVisible(false);
        CadastroInfoBasica cadastroCliente = new CadastroInfoBasica(TipoCadastro.CLIENTE);
        CtrInterfacesGraficas.setCadastroInfoBasica(cadastroCliente);
        cadastroCliente.setVisible(true);
    }//GEN-LAST:event_btCadastrarClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastrarCliente;
    private javax.swing.JButton btCadastrarFuncionario;
    private javax.swing.JButton btCadastrarVaga;
    private javax.swing.JButton btVoltar;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    // End of variables declaration//GEN-END:variables
}
