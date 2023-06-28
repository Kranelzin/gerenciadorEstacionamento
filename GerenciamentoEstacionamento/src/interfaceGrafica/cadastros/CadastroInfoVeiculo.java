package interfaceGrafica.cadastros;

import Repositorio.Biblioteca;
import controladores.CtrCliente;
import controladores.CtrInterfacesGraficas;
import enums.TipoCadastro;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SpinnerListModel;
import objetos.Cliente;
import objetos.Endereco;
import objetos.Veiculo;

/**
 *
 * @author marce
 */
public class CadastroInfoVeiculo extends javax.swing.JFrame {
    
    private ArrayList<Veiculo> veiculos = new ArrayList<>();
    private boolean update = false;
    
    public CadastroInfoVeiculo(TipoCadastro tipoCadastro, boolean update){
        initComponents();
        setLocationRelativeTo(null);
        lbTitulo.setText("Cadastro " + tipoCadastro.getDescricao());
        configurarSpVeiculo();
        
        if(update)
            preencherInformacoes();
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btProximo = new javax.swing.JButton();
        lbTitulo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbLogin = new javax.swing.JLabel();
        lbSenha = new javax.swing.JLabel();
        lbSenhaRepetida = new javax.swing.JLabel();
        tfPlaca = new javax.swing.JTextField();
        btVoltar = new javax.swing.JButton();
        tfMarca = new javax.swing.JTextField();
        tfModelo = new javax.swing.JTextField();
        btVeiculo = new javax.swing.JButton();
        spVeiculo = new javax.swing.JSpinner();
        btVeiculoRemover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btProximo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btProximo.setText("Próximo");
        btProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProximoActionPerformed(evt);
            }
        });

        lbTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTitulo.setText("Cadastrar Nova Empresa");

        jLabel15.setText("Informações veículo");

        lbLogin.setText("Placa");

        lbSenha.setText("Modelo");

        lbSenhaRepetida.setText("Marca");

        btVoltar.setText("Voltar");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        btVeiculo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btVeiculo.setText("+");
        btVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVeiculoActionPerformed(evt);
            }
        });

        spVeiculo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spVeiculoStateChanged(evt);
            }
        });

        btVeiculoRemover.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btVeiculoRemover.setText("-");
        btVeiculoRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVeiculoRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(311, Short.MAX_VALUE)
                .addComponent(btProximo)
                .addGap(333, 333, 333))
            .addGroup(layout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btVoltar)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(jLabel15))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbSenha)
                                    .addComponent(lbSenhaRepetida)
                                    .addComponent(lbLogin))
                                .addGap(99, 99, 99)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addComponent(btVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btVeiculoRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(spVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(307, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLogin)
                    .addComponent(tfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSenha)
                    .addComponent(tfModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSenhaRepetida)
                    .addComponent(tfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btVeiculoRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(spVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(212, 212, 212)
                .addComponent(btProximo)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoActionPerformed
        if(!adicionarVeiculo())
            return;
        CtrCliente.cadastroInfoVeiculo(veiculos);
        
        if(update){
            CtrCliente.updateCliente();
            Biblioteca.exibirAlerta("Dados salvos com sucesso ");
            return;
        }
        
        int resposta = Biblioteca.exibirCaixaDialogo("Cadastrar Mensalidade", "Deseja cadastrar o cliente como mensalista? ");
        
        switch(resposta){
            case JOptionPane.YES_OPTION:
                try{
                    setVisible(false);
                    CadastroMensalidade cadstroMensalidade = new CadastroMensalidade(update);
                    cadstroMensalidade.setVisible(true);
                }
                catch(Exception e){
                    Biblioteca.exibirAlerta("Falha ao iniciar cadastro de mensalidade: " + e.getMessage());
                }
                
                break;
            
            case JOptionPane.NO_OPTION:
                try{

                    CtrCliente.cadastrarCliente();
                    setVisible(false);
                    MenuCadastros menuCadastros = new MenuCadastros();
                    menuCadastros.setVisible(true);
                    
                }
                catch(Exception e){
                    Biblioteca.exibirAlerta("Falha ao cadastrar cliente" + e.getMessage());
                }
                break;
                
            default:
                break;
        }
    }//GEN-LAST:event_btProximoActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        setVisible(false);
        CadastroInfoEndereco cadastroEndereco = CtrInterfacesGraficas.getCadastroInfoEndereco();
        cadastroEndereco.setVisible(true);
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVeiculoActionPerformed
        adicionarVeiculo();
    }//GEN-LAST:event_btVeiculoActionPerformed

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

    private void btVeiculoRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVeiculoRemoverActionPerformed
        String placa = tfPlaca.getText();

        if(placa.contentEquals(""))
            return;

        for(Veiculo veiculo : veiculos){
            if(veiculo.getPlaca().contentEquals(placa)){
                veiculos.remove(veiculo);
                break;
            }
        }

        configurarSpVeiculo();
        limparCampos();
        spVeiculo.setValue("Novo veículo");
    }//GEN-LAST:event_btVeiculoRemoverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btVeiculo;
    private javax.swing.JButton btVeiculoRemover;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel lbSenhaRepetida;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JSpinner spVeiculo;
    private javax.swing.JTextField tfMarca;
    private javax.swing.JTextField tfModelo;
    private javax.swing.JTextField tfPlaca;
    // End of variables declaration//GEN-END:variables

    private boolean adicionarVeiculo() {
        
        String placa = tfPlaca.getText();
        String modelo = tfModelo.getText();
        String marca = tfMarca.getText();
        
        if(veiculos.size() == 0 && Biblioteca.verificarCamposVazios(
            placa,
            modelo,
            marca
            )
        ){
            Biblioteca.exibirAlerta("Preencha todos os campos");
            return false;
        }
        
        Veiculo veiculo = new Veiculo(placa,modelo, marca);
        
        veiculos.add(veiculo);
        configurarSpVeiculo();
        spVeiculo.setVisible(true);
        limparCampos();
        return true;
    }

    private void limparCampos() {
        tfPlaca.setText("");
        tfModelo.setText("");
        tfMarca.setText("");
    }

    private void preencherInformacoes() {
        Cliente cliente = CtrCliente.getCliente();
        veiculos = cliente.getVeiculos();
        configurarSpVeiculo();
        spVeiculo.setVisible(true);
 
    }

    private void configurarSpVeiculo() {
        ArrayList<String> placas = new ArrayList<>();
        placas.add("Novo veículo");
        
        for(Veiculo veiculo : veiculos)
            placas.add(veiculo.getPlaca());
        
        spVeiculo.setModel(new SpinnerListModel(placas));
    }
}

