package interfaceGrafica;

import Repositorio.Biblioteca;
import controladores.CtrCliente;
import controladores.CtrInterfacesGraficas;
import controladores.CtrUsuario;
import enums.TipoCadastro;
import enums.TipoUsuario;
import interfaceGrafica.cadastros.CadastroInfoBasica;
import interfaceGrafica.cadastros.MenuCadastros;
import objetos.Cliente;
import objetos.UsuarioLogin;
/**
 *
 * @author marce
 */
public class LotarVaga extends javax.swing.JFrame {
    
    private TipoUsuario tipoUsuario;
    
    public LotarVaga(TipoUsuario tipoUsuario){
        this.tipoUsuario = tipoUsuario;
        initComponents();
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btProximo = new javax.swing.JButton();
        lbTitulo = new javax.swing.JLabel();
        btVoltar = new javax.swing.JButton();
        lbPesquisaCliente = new javax.swing.JLabel();
        tfPesquisaCliente = new javax.swing.JTextField();
        btPesquisaCliente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        spVaga = new javax.swing.JSpinner();
        spVeiculo = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btProximo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btProximo.setText("Próximo");
        btProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProximoActionPerformed(evt);
            }
        });

        lbTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTitulo.setText("Lotar Vaga");

        btVoltar.setText("Voltar");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        lbPesquisaCliente.setText("Digite o nome do cliente: ");

        btPesquisaCliente.setText("Pesquisar");
        btPesquisaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisaClienteActionPerformed(evt);
            }
        });

        jLabel2.setText("Vaga");

        spVeiculo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spVeiculoStateChanged(evt);
            }
        });

        jLabel3.setText("Veiculo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbPesquisaCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(124, 124, 124)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spVeiculo)
                            .addComponent(spVaga))))
                .addGap(356, 356, 356))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btPesquisaCliente)
                                .addGap(176, 176, 176))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTitulo)
                                .addGap(258, 258, 258)))
                        .addComponent(btVoltar)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btProximo)
                        .addGap(316, 316, 316))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitulo)
                    .addComponent(btVoltar))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPesquisaCliente)
                    .addComponent(tfPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisaCliente))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(spVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(btProximo)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoActionPerformed
        
        
    }//GEN-LAST:event_btProximoActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        setVisible(false);
        MenuCadastros cadastro = CtrInterfacesGraficas.getMenuCadastros();
        cadastro.setVisible(true);
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btPesquisaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisaClienteActionPerformed
        String nomeUsuario = tfPesquisaCliente.getText();

        switch(tipoUsuario){
            case CLIENTE:
            CtrCliente.setCliente(nomeUsuario);
            Cliente cliente = CtrCliente.getCliente();

            if(cliente == null || cliente.getTipo() != TipoUsuario.CLIENTE){
                Biblioteca.exibirAlerta("Cliente não encontrado");
                return;
            }
            setVisible(false);
            CadastroInfoBasica cadastroCliente = new CadastroInfoBasica(TipoCadastro.CLIENTE, false, true);
            cadastroCliente.setVisible(true);

            break;

            case FUNCIONARIO:
            CtrUsuario.setUsuario(nomeUsuario);
            UsuarioLogin funcionario = CtrUsuario.getUsuarioLogin();

            if(funcionario == null || funcionario.getTipo() != TipoUsuario.FUNCIONARIO){
                Biblioteca.exibirAlerta("Funcionário não encontrado");
                return;
            }
            setVisible(false);
            CadastroInfoBasica cadastroFuncionario = new CadastroInfoBasica(TipoCadastro.FUNCIONARIO, false, true);
            cadastroFuncionario.setVisible(true);
            break;

            case ADMIN:
            CtrUsuario.setUsuario(nomeUsuario);
            UsuarioLogin admin = CtrUsuario.getUsuarioLogin();

            if(admin == null || admin.getTipo() != TipoUsuario.ADMIN){
                Biblioteca.exibirAlerta("Funcionário não encontrado");
                return;
            }
            setVisible(false);
            CadastroInfoBasica cadastroAdmin = new CadastroInfoBasica(TipoCadastro.ADMIN, false, true);
            cadastroAdmin.setVisible(true);
            break;

            default:
            break;
        }

    }//GEN-LAST:event_btPesquisaClienteActionPerformed

    private void spVeiculoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spVeiculoStateChanged
        String placa = (String) spVeiculo.getValue();

        if(placa.contentEquals("Novo veículo")){
            limparCampos();
            return;
        }

        Veiculo veiculo = null;

        for(Veiculo vec : veiculos){
            if(vec.getPlaca().contentEquals(placa))
            veiculo = vec;
        }

        if(veiculo == null)
        return;

        tfPlaca.setText(veiculo.getPlaca());
        tfModelo.setText(veiculo.getModelo());
        tfMarca.setText(veiculo.getMarca());
    }//GEN-LAST:event_spVeiculoStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPesquisaCliente;
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbPesquisaCliente;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JSpinner spVaga;
    private javax.swing.JSpinner spVeiculo;
    private javax.swing.JTextField tfPesquisaCliente;
    // End of variables declaration//GEN-END:variables

}
    

