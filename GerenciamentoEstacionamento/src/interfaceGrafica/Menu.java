package interfaceGrafica;

import controladores.CtrInterfacesGraficas;
import interfaceGrafica.cadastros.MenuCadastros;

/**
 *
 * @author marce
 */
public class Menu extends javax.swing.JFrame {

    public Menu() {
        CtrInterfacesGraficas.setMenu(this);
        initComponents();
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btCadastros = new javax.swing.JButton();
        btLotarVaga = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        btVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btCadastros.setText("Cadastros");
        btCadastros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrosActionPerformed(evt);
            }
        });

        btLotarVaga.setText("Lotar Vaga");
        btLotarVaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLotarVagaActionPerformed(evt);
            }
        });

        jButton12.setText("Liberar Vaga");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

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
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton12)
                    .addComponent(btLotarVaga)
                    .addComponent(btCadastros))
                .addContainerGap(568, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btVoltar)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btVoltar)
                .addGap(53, 53, 53)
                .addComponent(btCadastros)
                .addGap(18, 18, 18)
                .addComponent(btLotarVaga)
                .addGap(18, 18, 18)
                .addComponent(jButton12)
                .addContainerGap(234, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrosActionPerformed
        setVisible(false);
        MenuCadastros menuCadastros = new MenuCadastros();
        CtrInterfacesGraficas.setMenuCadastros(menuCadastros);
        menuCadastros.setVisible(true);
    }//GEN-LAST:event_btCadastrosActionPerformed

    private void btLotarVagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLotarVagaActionPerformed
        setVisible(false);
        LotarVaga lotarVaga = new LotarVaga(true);
        lotarVaga.setVisible(true);
    }//GEN-LAST:event_btLotarVagaActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        setVisible(false);
        LotarVaga lotarVaga = new LotarVaga(false);
        lotarVaga.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        setVisible(false);
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_btVoltarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastros;
    private javax.swing.JButton btLotarVaga;
    private javax.swing.JButton btVoltar;
    private javax.swing.JButton jButton12;
    // End of variables declaration//GEN-END:variables
}
