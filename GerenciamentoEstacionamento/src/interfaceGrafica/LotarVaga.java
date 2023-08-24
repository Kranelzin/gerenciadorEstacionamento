package interfaceGrafica;

import Repositorio.Biblioteca;
import controladores.CtrBoxVaga;
import controladores.CtrCliente;
import controladores.CtrInterfacesGraficas;
import enums.TipoUsuario;
import exceptions.EstacionarVagaException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SpinnerListModel;
import objetos.BoxVaga;
import objetos.Cliente;
import objetos.Veiculo;
/**
 *
 * @author marce
 */
public class LotarVaga extends javax.swing.JFrame {
    
    private ArrayList<Veiculo> veiculos = new ArrayList<>();
    private ArrayList<BoxVaga> boxVagas = new ArrayList<>();
    private Cliente cliente = null;
    private Veiculo veiculo = null;
    private BoxVaga boxVaga = null;
    private boolean lotar = true;
    
    public LotarVaga(boolean lotar){
        this.lotar = lotar;
        initComponents();
        setLocationRelativeTo(null);
        configurarComponentes(false);
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
        lbVaga = new javax.swing.JLabel();
        spVaga = new javax.swing.JSpinner();
        spVeiculo = new javax.swing.JSpinner();
        lbVeiculo = new javax.swing.JLabel();

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

        tfPesquisaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPesquisaClienteActionPerformed(evt);
            }
        });

        btPesquisaCliente.setText("Pesquisar");
        btPesquisaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisaClienteActionPerformed(evt);
            }
        });

        lbVaga.setText("Vaga");

        spVaga.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spVagaStateChanged(evt);
            }
        });

        spVeiculo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spVeiculoStateChanged(evt);
            }
        });

        lbVeiculo.setText("Veiculo");

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
                            .addComponent(lbVaga)
                            .addComponent(lbVeiculo))
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
                    .addComponent(lbVaga)
                    .addComponent(spVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbVeiculo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(btProximo)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoActionPerformed
        
        if(cliente == null){
            Biblioteca.exibirAlerta("Cliente não encontrado");
            return;
        }
        
        String placa = (String) spVeiculo.getValue();
        
        for(Veiculo vec : veiculos){
            if(vec.getPlaca().contentEquals(placa)){
               veiculo = vec;
               break;
            }
            
        }
        
        if(veiculo == null){
            Biblioteca.exibirAlerta("Nenhum veículo selecionado");
            return;
        }
        
        if(lotar){
            String codigo = (String) spVaga.getValue();
        
            for(BoxVaga vaga : boxVagas){
                if(vaga.getCodigoVaga().contentEquals(codigo)){
                   boxVaga = vaga;
                   break;
                }

            }
        
            if(boxVaga == null){
                Biblioteca.exibirAlerta("Nenhuma vaga selecionada");
                return;
            }
        
            try{
                cliente.estacionarVaga(new Timestamp(new Date().getTime()), boxVaga, veiculo);

                CtrBoxVaga.lotarVaga(cliente.getUsuarioId(), boxVaga);

                Biblioteca.exibirAlerta("Lotado com sucesso");
                setVisible(false);
                Menu menu = CtrInterfacesGraficas.getMenu();
                menu.setVisible(true);
            }
            catch(Exception e){
                Biblioteca.exibirAlerta("Erro ao lotar vaga: " + e.getMessage());
            }
        }
        else{
            try {
                BoxVaga vagaLiberar = cliente.getBoxVagaPorVeiculo(veiculo);
                cliente.liberarVaga(new Timestamp(new Date().getTime()), vagaLiberar);
                
                Biblioteca.exibirAlerta("Liberado com sucesso");
                setVisible(false);
                Menu menu = CtrInterfacesGraficas.getMenu();
                menu.setVisible(true);
                
                CtrBoxVaga.liberarVaga(vagaLiberar);
                
            } catch (Exception e) {
                Biblioteca.exibirAlerta("Nenhuma vaga encontrada para o veiculo selecionado");
                return;
            }
        }
        
    
    }//GEN-LAST:event_btProximoActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        setVisible(false);
        Menu cadastro = CtrInterfacesGraficas.getMenu();
        cadastro.setVisible(true);
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btPesquisaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisaClienteActionPerformed
        String nomeUsuario = tfPesquisaCliente.getText();
        
        try {
            CtrCliente.setCliente(nomeUsuario);
        } catch (EstacionarVagaException e) {
            Biblioteca.exibirAlerta("Erro ao buscar usuario: " + e.getMessage());
        }
        cliente = CtrCliente.getCliente();

        if(cliente == null || cliente.getTipo() != TipoUsuario.CLIENTE){
            Biblioteca.exibirAlerta("Cliente não encontrado");
            return;
        }
       
        if(lotar){
            veiculos = cliente.getVeiculos();
            boxVagas = CtrBoxVaga.getBoxVagasDisponiveis();
            configurarSpBoxaVagas();
        }
        else{
            veiculos = cliente.getVeiculosLotadosVagas();
            if(veiculos.size() == 0){
                Biblioteca.exibirAlerta("Nenhuma vaga a ser liberada por esse cliente");
                return;
            }
        }
        
        configurarSpVeiculos();
        configurarComponentes(true);

    }//GEN-LAST:event_btPesquisaClienteActionPerformed

    private void spVeiculoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spVeiculoStateChanged

            
    }//GEN-LAST:event_spVeiculoStateChanged

    private void tfPesquisaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPesquisaClienteActionPerformed
        
    }//GEN-LAST:event_tfPesquisaClienteActionPerformed

    private void spVagaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spVagaStateChanged

    }//GEN-LAST:event_spVagaStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPesquisaCliente;
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel lbPesquisaCliente;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbVaga;
    private javax.swing.JLabel lbVeiculo;
    private javax.swing.JSpinner spVaga;
    private javax.swing.JSpinner spVeiculo;
    private javax.swing.JTextField tfPesquisaCliente;
    // End of variables declaration//GEN-END:variables

    private void configurarComponentes(boolean visivel) {
       
        lbVeiculo.setVisible(visivel);
        spVeiculo.setVisible(visivel);
        
        if(!lotar)
            visivel = false;
        spVaga.setVisible(visivel);
        lbVaga.setVisible(visivel);
         
        
    }

    private void configurarSpVeiculos() {
        ArrayList<String> placas = new ArrayList<>();
        
        for(Veiculo veiculo : veiculos)
            placas.add(veiculo.getPlaca());
        
        spVeiculo.setModel(new SpinnerListModel(placas));
        
    }

    private void configurarSpBoxaVagas() {
        
        if(boxVagas.size() == 0){
            Biblioteca.exibirAlerta("Todas as vagas estão reservadas ou em uso no momento");
            return;
        }
        
        ArrayList<String> codigosVagas = new ArrayList<>();
        
        for(BoxVaga boxVaga : boxVagas)
            codigosVagas.add(boxVaga.getCodigoVaga());
            
        spVaga.setModel(new SpinnerListModel(codigosVagas));
    }

}
    

