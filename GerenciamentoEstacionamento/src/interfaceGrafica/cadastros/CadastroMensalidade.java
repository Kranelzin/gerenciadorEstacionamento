package interfaceGrafica.cadastros;

import Repositorio.Biblioteca;
import controladores.CtrBoxVaga;
import controladores.CtrCliente;
import controladores.CtrInterfacesGraficas;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.SpinnerListModel;
import javax.swing.text.MaskFormatter;
import objetos.BoxVaga;

/**
 *
 * @author marce
 */
public class CadastroMensalidade extends javax.swing.JFrame {
    
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    
    private ArrayList<BoxVaga> boxVagas = new ArrayList<>();
    private boolean update = false;
    
    public CadastroMensalidade(boolean update) throws ParseException {
        this.update = update;
        initComponents();
        setLocationRelativeTo(null);
        MaskFormatter mascara = new MaskFormatter("##/##/####");
        lbUltimaVaga.setVisible(false);
        mascara.install(tfDataInicio);
        configurarSpinnerBoxVaga();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btProximo = new javax.swing.JButton();
        lbTitulo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btVoltar = new javax.swing.JButton();
        tfDataInicio = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        tfValor = new javax.swing.JTextField();
        spVaga = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        tfDataVencimento = new javax.swing.JTextField();
        btBoxVaga = new javax.swing.JButton();
        lbUltimaVaga = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Data Início");

        jLabel2.setText("Vaga");

        btProximo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btProximo.setText("Próximo");
        btProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProximoActionPerformed(evt);
            }
        });

        lbTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTitulo.setText("Cadastrar Nova Mensalidade");

        jLabel15.setText("Informações Mensalidade");

        btVoltar.setText("Voltar");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        tfDataInicio.setText("jFormattedTextField1");

        jLabel3.setText("Data de vencimento");

        jLabel4.setText("Valor");

        btBoxVaga.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btBoxVaga.setText("+");
        btBoxVaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBoxVagaActionPerformed(evt);
            }
        });

        lbUltimaVaga.setText("jLabel1");
        lbUltimaVaga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbUltimaVagaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbTitulo)
                        .addGap(176, 176, 176)
                        .addComponent(btVoltar)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(294, 294, 294))))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                        .addComponent(btProximo)
                        .addGap(237, 306, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(181, 181, 181))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(180, 180, 180)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbUltimaVaga)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tfDataInicio)
                                .addComponent(spVaga)
                                .addComponent(tfDataVencimento, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(btBoxVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(spVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBoxVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(lbUltimaVaga)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(btProximo)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoActionPerformed
        
        String dataInicio = tfDataInicio.getText();
        String dataVencimentoTexto = tfDataVencimento.getText();
        String valor = tfValor.getText();
        
        if(Biblioteca.verificarCamposVazios(
            dataInicio,
            dataVencimentoTexto,
            valor
            )
        ){
            Biblioteca.exibirAlerta("Preencha todos os campos");
            return;
        }
        
        if(!Biblioteca.somenteNumeros(dataVencimentoTexto) 
            || Integer.parseInt(dataVencimentoTexto) > 28 
            || Integer.parseInt(dataVencimentoTexto) < 1
        ){
            Biblioteca.exibirAlerta("Valor inválido para data de vencimento do pagamento");
            return;
        }
        
        if(!Biblioteca.somenteNumeros(valor)){
            Biblioteca.exibirAlerta("O valor digitado é inválido");
            return;
        }
        
        if(!adicionarBoxVaga())
            return;

        try{
            Date dataInicioMensalidade = formato.parse(dataInicio);
            
            CtrCliente.cadastroInfoMensalidade(dataInicioMensalidade, Integer.parseInt(dataVencimentoTexto), boxVagas, new BigDecimal(valor));
            CtrCliente.cadastrarCliente();
        }
        catch(Exception e){
            Biblioteca.exibirAlerta("Erro ao cadastrar Mensalidade: " + e.getMessage() );
            return;
        }
        
        Biblioteca.exibirAlerta("Mensalidade cadastrada com sussesso ");
        limparCampos();
        setVisible(false);
        MenuCadastros menuCadastros = new MenuCadastros();
        menuCadastros.setVisible(true);
    }//GEN-LAST:event_btProximoActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        setVisible(false);
        CadastroInfoVeiculo cadastro = CtrInterfacesGraficas.getCadastroInfoVeiculo();
        cadastro.setVisible(true);
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btBoxVagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBoxVagaActionPerformed
        adicionarBoxVaga();
    }//GEN-LAST:event_btBoxVagaActionPerformed

    private void lbUltimaVagaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUltimaVagaMouseClicked
        int ultimaPos = boxVagas.size() - 1;

        boxVagas.remove(ultimaPos);

        if(boxVagas.size() == 0){
            lbUltimaVaga.setVisible(false);
            return;
        }

        lbUltimaVaga.setText("QTD Veúculos: " + boxVagas.size() + "; " + boxVagas.get(ultimaPos - 1).getCodigoVaga() + " X ");
    }//GEN-LAST:event_lbUltimaVagaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBoxVaga;
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbUltimaVaga;
    private javax.swing.JSpinner spVaga;
    private javax.swing.JFormattedTextField tfDataInicio;
    private javax.swing.JTextField tfDataVencimento;
    private javax.swing.JTextField tfValor;
    // End of variables declaration//GEN-END:variables

    private void limparCampos() {
        tfDataInicio.setText("");
    }

    private void configurarSpinnerBoxVaga() {
        ArrayList<BoxVaga> boxVagas = CtrBoxVaga.getBoxVagasDisponiveis();
        
        if(boxVagas.size() == 0){
            Biblioteca.exibirAlerta("Todas as vagas estão reservadas ou em uso no momento");
            setVisible(false);
            CadastroInfoVeiculo cadastroVeiculo = CtrInterfacesGraficas.getCadastroInfoVeiculo();
            cadastroVeiculo.setVisible(true);
            return;
        }
        
        ArrayList<String> codigosVagas = new ArrayList<>();
        
        for(BoxVaga boxVaga : boxVagas)
            codigosVagas.add(boxVaga.getCodigoVaga());
            
        spVaga.setModel(new SpinnerListModel(codigosVagas));
    }
    
    private boolean adicionarBoxVaga() {
        String vaga = (String) spVaga.getValue();
        
        if(vaga.contentEquals(""))
            return true;
        
        BoxVaga boxVaga = CtrBoxVaga.buscarVagaPorCodigo(vaga);
        
        if(boxVaga == null){
            Biblioteca.exibirAlerta("Vaga não encontrada ou já está em uso");
            return false;
        }
        
        if(boxVagas.contains(boxVaga)){
            Biblioteca.exibirAlerta("Esta vaga já foi adicionada");
            return false;
        }
        
        boxVagas.add(boxVaga);
        
        lbUltimaVaga.setText("QTD Vagas: " + boxVagas.size() + "; " + boxVaga.getCodigoVaga() + " X ");
        lbUltimaVaga.setVisible(true);
        return true;
    }
}
    

