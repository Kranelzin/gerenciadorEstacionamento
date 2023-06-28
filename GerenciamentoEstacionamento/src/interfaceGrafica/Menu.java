package interfaceGrafica;

import controladores.CtrInterfacesGraficas;
import interfaceGrafica.cadastros.MenuCadastros;

/**
 *
 * @author marce
 */
public class Menu extends javax.swing.JFrame {

    public Menu() {
        initComponents();
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btCadastros = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        btLotarVaga = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btCadastros.setText("Cadastros");
        btCadastros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrosActionPerformed(evt);
            }
        });

        jButton9.setText("Receber Mensalidade");

        jButton10.setText("Receber Pagamento");

        btLotarVaga.setText("Lotar Vaga");
        btLotarVaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLotarVagaActionPerformed(evt);
            }
        });

        jButton12.setText("Liberar Vaga");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton12)
                    .addComponent(btLotarVaga)
                    .addComponent(jButton10)
                    .addComponent(jButton9)
                    .addComponent(btCadastros))
                .addContainerGap(518, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(btCadastros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9)
                .addGap(18, 18, 18)
                .addComponent(jButton10)
                .addGap(18, 18, 18)
                .addComponent(btLotarVaga)
                .addGap(18, 18, 18)
                .addComponent(jButton12)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrosActionPerformed
        setVisible(false);
        MenuCadastros menuCadastros = new MenuCadastros();
        CtrInterfacesGraficas.setmMenuCadastros(menuCadastros);
        menuCadastros.setVisible(true);
    }//GEN-LAST:event_btCadastrosActionPerformed

    private void btLotarVagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLotarVagaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btLotarVagaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastros;
    private javax.swing.JButton btLotarVaga;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton9;
    // End of variables declaration//GEN-END:variables
}
