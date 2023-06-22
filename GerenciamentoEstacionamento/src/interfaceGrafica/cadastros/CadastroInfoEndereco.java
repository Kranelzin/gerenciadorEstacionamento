package interfaceGrafica.cadastros;

import Repositorio.Biblioteca;
import banco.BuscarEndereco;
import controladores.CtrCadastroAdmin;
import controladores.CtrCadastroEmpresa;
import enums.Estados;
import enums.TipoCadastro;
import enums.TipoEndereco;
import interfaceGrafica.Login;
import java.util.ArrayList;
import javax.swing.SpinnerListModel;
import objetos.Endereco;
import objetos.CidadeEstado;

/**
 *
 * @author marce
 */
public class CadastroInfoEndereco extends javax.swing.JFrame {

    private TipoCadastro tipoCadastro;
    private ArrayList<Endereco> enderecos = new ArrayList<>();
    
    public CadastroInfoEndereco(TipoCadastro tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
        initComponents();
        configurarSpinnerUf();
        rbResidencial.setSelected(true);
        lbUltimoEndereco.setVisible(false);
        setLocationRelativeTo(null);
        lbTitulo.setText("Cadastro " + tipoCadastro.getDescricao());
    }
    
    private void adicionarEndereco(){
        
        String logradouro =  tfLogradouro.getText();
        String numero = tfNumero.getText();
        String complemento =  tfComplemento.getText();
        String bairro = tfBairro.getText();
        String cidade = tfCidade.getText();
        
        if(Biblioteca.verificarCamposVazios(
                logradouro,
                numero,
                complemento,
                bairro,
                cidade
        )){
            Biblioteca.exibirAlerta("Preencha todos os campos");
            return;
        }
            
        
        TipoEndereco tipoEndereco = rbResidencial.isSelected() ? TipoEndereco.RESIDENCIAL : TipoEndereco.COMERCIAL;
        
        Estados tipoEstado = null;
        
        if(Biblioteca.verificarValorValido(String.class, spEstado.getValue()) && Estados.existeEstado((String) spEstado.getValue()))
            tipoEstado = Estados.obterPorDescricao((String) spEstado.getValue());
        else{
            Biblioteca.exibirAlerta("O estado selecionado é inválido");
            return;
        }
        
        CidadeEstado cidadeEstado = BuscarEndereco.buscarCidadeEstado(cidade, tipoEstado.getUF());
        
        if(cidadeEstado == null){
            Biblioteca.exibirAlerta("Cidade não encontrada para o estado selecionado");
            return;
        }
        
        Endereco endereco = new Endereco(
            tipoEndereco,
            Integer.parseInt(tfCep.getText()),
            logradouro,
            Integer.parseInt(numero),
            complemento,
            bairro,
            cidadeEstado
        );
        
        enderecos.add(endereco);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfCep = new javax.swing.JTextField();
        tfNumero = new javax.swing.JTextField();
        btProximo = new javax.swing.JButton();
        lbTitulo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tfBairro = new javax.swing.JTextField();
        tfCidade = new javax.swing.JTextField();
        tfComplemento = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        spEstado = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        btEndereco = new javax.swing.JButton();
        tfLogradouro = new javax.swing.JTextField();
        rbComercial = new javax.swing.JRadioButton();
        rbResidencial = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        lbUltimoEndereco = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CEP");

        jLabel2.setText("Logradouro");

        jLabel3.setText("Número");

        jLabel5.setText("Complemento");

        tfCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCepFocusLost(evt);
            }
        });

        btProximo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btProximo.setText("Próximo");
        btProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProximoActionPerformed(evt);
            }
        });

        lbTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTitulo.setText("Cadastrar Nova Empresa");

        jLabel15.setText("Informações endereço");

        jLabel6.setText("Bairro");

        jLabel7.setText("Cidade");

        jLabel8.setText("UF");

        btEndereco.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btEndereco.setText("+");
        btEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnderecoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbComercial);
        rbComercial.setText("Comercial");
        rbComercial.setToolTipText("");
        rbComercial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbComercialActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbResidencial);
        rbResidencial.setText("Residencial");

        jLabel4.setText("Tipo");

        lbUltimoEndereco.setText("jLabel9");
        lbUltimoEndereco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbUltimoEnderecoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTitulo)
                .addGap(268, 268, 268))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbUltimoEndereco)
                        .addGap(174, 174, 174)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btProximo)
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel15)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(206, 293, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfCep, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbComercial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbResidencial))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(spEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfBairro)
                                    .addComponent(tfComplemento))
                                .addGap(4, 4, 4)
                                .addComponent(btEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btProximo)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(btEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbComercial)
                            .addComponent(rbResidencial)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbUltimoEndereco)
                        .addContainerGap(49, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnderecoActionPerformed
        
        adicionarEndereco();
        limparCampos();
        lbUltimoEndereco.setText(enderecos.get(enderecos.size() - 1).getLogradouro() + " X ");
        lbUltimoEndereco.setVisible(true);
    }//GEN-LAST:event_btEnderecoActionPerformed

    private void tfCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCepFocusLost
        String cep = tfCep.getText();
        
        Endereco endereco = BuscarEndereco.pesquisarEndereco(cep);
            
        if(!endereco.temCep()){
            Biblioteca.exibirAlerta("Cep não encontrado");
            return;
        }
        
        tfLogradouro.setText(endereco.getLogradouro());
        tfBairro.setText(endereco.getBairro());
        tfCidade.setText(endereco.getCidadeEstado().getCidade());
        spEstado.setValue(endereco.getCidadeEstado().getEstado().getUF());
    }//GEN-LAST:event_tfCepFocusLost

    private void btProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoActionPerformed
        
        adicionarEndereco();
        
        switch (tipoCadastro) {
            case EMPRESA:
                try{
                    CtrCadastroEmpresa.cadastroInfoEndereco(enderecos);
                    setVisible(false);
                    CadastroInfoBasica cadastroAdmin = new CadastroInfoBasica(TipoCadastro.ADMIN);
                    cadastroAdmin.setVisible(true);
                }
                catch(Exception e){
                    Biblioteca.exibirAlerta("Erro ao cadastrar empresa: " + e.getMessage());
                }
                break;
            case ADMIN:
                try{
                    CtrCadastroAdmin.cadastroInfoEndereco(enderecos);
                    CtrCadastroEmpresa.cadastrarNovaEmpresa();

                    setVisible(false);
                    Login login = new Login();
                    login.setVisible(true);
                }
                catch(Exception e){
                    Biblioteca.exibirAlerta("Erro ao cadastrar admin: " + e.getMessage());
                }
                break;
            case FUNCIONARIO:
                System.out.println("Cadastro de Funcionário");
                break;
            case CLIENTE:
                System.out.println("Cadastro de Cliente");
                break;
            default:
                System.out.println("Tipo de cadastro inválido");
        }
        
    }//GEN-LAST:event_btProximoActionPerformed

    private void rbComercialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbComercialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbComercialActionPerformed

    private void lbUltimoEnderecoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUltimoEnderecoMouseClicked
         int ultimaPos = enderecos.size() - 1;
        
        enderecos.remove(ultimaPos);
        
        if(enderecos.size() == 0){
            lbUltimoEndereco.setVisible(false);
            return;
        }
        
        lbUltimoEndereco.setText(enderecos.get(ultimaPos - 1).getLogradouro()+ " X ");
    }//GEN-LAST:event_lbUltimoEnderecoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEndereco;
    private javax.swing.JButton btProximo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbUltimoEndereco;
    private javax.swing.JRadioButton rbComercial;
    private javax.swing.JRadioButton rbResidencial;
    private javax.swing.JSpinner spEstado;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JTextField tfCep;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JTextField tfComplemento;
    private javax.swing.JTextField tfLogradouro;
    private javax.swing.JTextField tfNumero;
    // End of variables declaration//GEN-END:variables

    private void configurarSpinnerUf() {
        ArrayList<String> ufs = new ArrayList<>();
        
        for(int i =1; i < 28; i++)
            ufs.add(Estados.obterPorIndice(i).getUF());
        
        spEstado.setModel(new SpinnerListModel(ufs));
    }

    private void limparCampos() {
        tfCep.setText("");
        tfLogradouro.setText("");
        tfNumero.setText("");
        tfComplemento.setText("");
        tfBairro.setText("");
        tfCidade.setText("");
        spEstado.setValue(Estados.AC.getUF());
        
    }
}
