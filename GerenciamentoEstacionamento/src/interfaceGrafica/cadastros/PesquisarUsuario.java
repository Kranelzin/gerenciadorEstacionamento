package interfaceGrafica.cadastros;

import Repositorio.Biblioteca;
import controladores.CtrCliente;
import controladores.CtrInterfacesGraficas;
import controladores.CtrUsuario;
import enums.TipoCadastro;
import enums.TipoUsuario;
import exceptions.EstacionarVagaException;
import interfaceGrafica.cadastros.CadastroInfoBasica;
import interfaceGrafica.cadastros.MenuCadastros;
import objetos.Cliente;
import objetos.UsuarioLogin;
/**
 *
 * @author marce
 */
public class PesquisarUsuario extends javax.swing.JFrame {
    
    private TipoUsuario tipoUsuario;
    
    public PesquisarUsuario(TipoUsuario tipoUsuario){
        this.tipoUsuario = tipoUsuario;
        initComponents();
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btProximo = new javax.swing.JButton();
        lbTitulo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btVoltar = new javax.swing.JButton();
        lbPesquisaCliente = new javax.swing.JLabel();
        tfPesquisaCliente = new javax.swing.JTextField();
        btPesquisaCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btProximo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btProximo.setText("Próximo");
        btProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProximoActionPerformed(evt);
            }
        });

        lbTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTitulo.setText("Cadastrar Nova Mensalidade");

        jLabel15.setText("Pesquisar Cliente");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(258, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(294, 294, 294))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btPesquisaCliente)
                            .addComponent(lbTitulo))
                        .addGap(176, 176, 176)
                        .addComponent(btVoltar)
                        .addGap(15, 15, 15))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addComponent(btProximo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(lbPesquisaCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitulo)
                    .addComponent(btVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPesquisaCliente)
                    .addComponent(tfPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisaCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                .addComponent(btProximo)
                .addGap(17, 17, 17))
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
        
                try {
                    CtrCliente.setCliente(nomeUsuario);
                } catch (EstacionarVagaException e) {
                    Biblioteca.exibirAlerta("Erro ao buscar usuario: " + e.getMessage());
                }

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
                    Biblioteca.exibirAlerta("Admin não encontrado");
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPesquisaCliente;
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel lbPesquisaCliente;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JTextField tfPesquisaCliente;
    // End of variables declaration//GEN-END:variables

}
    

