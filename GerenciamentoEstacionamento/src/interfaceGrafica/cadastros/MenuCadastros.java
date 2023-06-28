package interfaceGrafica.cadastros;

import controladores.CtrInterfacesGraficas;
import enums.TipoCadastro;
import enums.TipoUsuario;
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
        btEditarCliente = new javax.swing.JButton();
        btEditarFuncionario = new javax.swing.JButton();
        btEditarAdmin = new javax.swing.JButton();
        btCadastrarVaga = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btVoltar = new javax.swing.JButton();
        btCadastroMensalidade = new javax.swing.JButton();
        btEditarMensalidade = new javax.swing.JButton();

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

        btEditarCliente.setText("Editar Cliente");
        btEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarClienteActionPerformed(evt);
            }
        });

        btEditarFuncionario.setText("Editar Funcionário");
        btEditarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarFuncionarioActionPerformed(evt);
            }
        });

        btEditarAdmin.setText("Editar Admin");
        btEditarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarAdminActionPerformed(evt);
            }
        });

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

        btCadastroMensalidade.setText("Cadastrar Mensalidade");
        btCadastroMensalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastroMensalidadeActionPerformed(evt);
            }
        });

        btEditarMensalidade.setText("Editar Mensalidade");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btVoltar)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCadastrarVaga)
                    .addComponent(jButton8)
                    .addComponent(btEditarMensalidade)
                    .addComponent(btCadastroMensalidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 279, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(btEditarAdmin)
                    .addComponent(btEditarCliente)
                    .addComponent(btCadastrarCliente)
                    .addComponent(btCadastrarFuncionario)
                    .addComponent(btEditarFuncionario))
                .addGap(93, 93, 93))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btVoltar)
                        .addGap(42, 42, 42)
                        .addComponent(btCadastrarVaga)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(btCadastroMensalidade)
                        .addGap(18, 18, 18)
                        .addComponent(btEditarMensalidade))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(btCadastrarCliente)
                        .addGap(18, 18, 18)
                        .addComponent(btEditarCliente)
                        .addGap(18, 18, 18)
                        .addComponent(btCadastrarFuncionario)
                        .addGap(18, 18, 18)
                        .addComponent(btEditarFuncionario)))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(17, 17, 17)
                .addComponent(btEditarAdmin)
                .addContainerGap(95, Short.MAX_VALUE))
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

    private void btEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarClienteActionPerformed
        setVisible(false);
        PesquisarUsuario pesquisa = new PesquisarUsuario(TipoUsuario.CLIENTE);
        pesquisa.setVisible(true);
    }//GEN-LAST:event_btEditarClienteActionPerformed

    private void btCadastroMensalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastroMensalidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCadastroMensalidadeActionPerformed

    private void btEditarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarFuncionarioActionPerformed
        setVisible(false);
        PesquisarUsuario pesquisa = new PesquisarUsuario(TipoUsuario.FUNCIONARIO);
        pesquisa.setVisible(true);
    }//GEN-LAST:event_btEditarFuncionarioActionPerformed

    private void btEditarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarAdminActionPerformed
        setVisible(false);
        PesquisarUsuario pesquisa = new PesquisarUsuario(TipoUsuario.ADMIN);
        pesquisa.setVisible(true);
    }//GEN-LAST:event_btEditarAdminActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastrarCliente;
    private javax.swing.JButton btCadastrarFuncionario;
    private javax.swing.JButton btCadastrarVaga;
    private javax.swing.JButton btCadastroMensalidade;
    private javax.swing.JButton btEditarAdmin;
    private javax.swing.JButton btEditarCliente;
    private javax.swing.JButton btEditarFuncionario;
    private javax.swing.JButton btEditarMensalidade;
    private javax.swing.JButton btVoltar;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    // End of variables declaration//GEN-END:variables
}
