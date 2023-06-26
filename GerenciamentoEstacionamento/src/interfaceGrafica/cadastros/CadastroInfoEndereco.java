package interfaceGrafica.cadastros;

import Repositorio.Biblioteca;
import banco.BancoEndereco;
import controladores.CtrCliente;
import controladores.CtrUsuario;
import controladores.CtrCadastroEmpresa;
import controladores.CtrInterfacesGraficas;
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
    private boolean cadastrarEmpresa = false;
    private boolean update = false;
    
    public CadastroInfoEndereco(TipoCadastro tipoCadastro, boolean cadastrarEmpresa, boolean update) {
        this.tipoCadastro = tipoCadastro;
        this.cadastrarEmpresa = cadastrarEmpresa;
        this.update = update;
        initComponents();
        configurarSpinnerUf();
        rbResidencial.setSelected(true);
        spEndereco.setVisible(false);
        setLocationRelativeTo(null);
        lbTitulo.setText("Cadastro " + tipoCadastro.getDescricao());
        configurarSpEndereco();
    }
    
    private boolean adicionarEndereco(){
        
        String logradouro =  tfLogradouro.getText();
        String numero = tfNumero.getText();
        String complemento =  tfComplemento.getText();
        String bairro = tfBairro.getText();
        String cidade = tfCidade.getText();
        
        String enderecoSp = (String) spEndereco.getValue();
        
        if(enderecoSp.contentEquals("Novo endereço") && enderecos.size() > 0)
            return true;
        
        if(Biblioteca.verificarCamposVazios(
                logradouro,
                numero,
                complemento,
                bairro,
                cidade
        )){
            Biblioteca.exibirAlerta("Preencha todos os campos");
            return false;
        }
            
        
        TipoEndereco tipoEndereco = rbResidencial.isSelected() ? TipoEndereco.RESIDENCIAL : TipoEndereco.COMERCIAL;
        
        Estados tipoEstado = null;
        
        if(Biblioteca.verificarValorValido(String.class, spEstado.getValue()) && Estados.existeEstado((String) spEstado.getValue()))
            tipoEstado = Estados.obterPorDescricao((String) spEstado.getValue());
        else{
            Biblioteca.exibirAlerta("O estado selecionado é inválido");
            return false;
        }
        
        CidadeEstado cidadeEstado = BancoEndereco.buscarCidadeEstado(cidade, tipoEstado.getUF());
        
        if(cidadeEstado == null){
            Biblioteca.exibirAlerta("Cidade não encontrada para o estado selecionado");
            return false;
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
        
        configurarSpEndereco();
        limparCampos();
        
        return true;
        
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
        btVoltar = new javax.swing.JButton();
        spEndereco = new javax.swing.JSpinner();
        btEnderecoRemover = new javax.swing.JButton();

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

        btVoltar.setText("Voltar");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        spEndereco.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spEnderecoStateChanged(evt);
            }
        });

        btEnderecoRemover.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btEnderecoRemover.setText("-");
        btEnderecoRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnderecoRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(spEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(154, 154, 154)
                                .addComponent(btProximo))
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
                                        .addComponent(rbResidencial)
                                        .addGap(18, 18, 18)
                                        .addComponent(btEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btEnderecoRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tfCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                        .addComponent(spEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfBairro)
                                        .addComponent(tfComplemento)))))
                        .addGap(206, 264, Short.MAX_VALUE))))
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
                        .addGap(286, 286, 286))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitulo)
                    .addComponent(btVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btEnderecoRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btProximo)
                            .addComponent(spEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbComercial)
                            .addComponent(rbResidencial)
                            .addComponent(jLabel4))
                        .addContainerGap(72, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnderecoActionPerformed
        spEndereco.setVisible(true);
        adicionarEndereco();
       
    }//GEN-LAST:event_btEnderecoActionPerformed

    private void tfCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCepFocusLost
        String cep = tfCep.getText();
        
        Endereco endereco = BancoEndereco.pesquisarEndereco(cep);
            
        if(!endereco.temCep()){
            Biblioteca.exibirAlerta("Cep não encontrado");
            return;
        }
        
        ArrayList<String> componentesVerificar = new ArrayList<>();
        componentesVerificar.add(tfCep.getText());
        componentesVerificar.add(tfLogradouro.getText());
        componentesVerificar.add(tfNumero.getText());
        componentesVerificar.add(tfComplemento.getText());
        componentesVerificar.add(tfBairro.getText());
        componentesVerificar.add(tfCidade.getText());
        componentesVerificar.add((String) spEstado.getValue());
        
        for(Endereco end : enderecos){
            ArrayList<String> componentes = new ArrayList<>();
            componentes.add(Integer.toString(end.getCep()));
            componentes.add(end.getLogradouro());
            componentes.add(Integer.toString(end.getNumero()));
            componentes.add(end.getComplemento());
            componentes.add(end.getBairro());
            componentes.add(end.getCidadeEstado().getCidade());
            componentes.add(end.getCidadeEstado().getEstado().getUF());
            
            try {
                if(Biblioteca.verificarContemNosComponentes(componentes, componentesVerificar)){
                    Biblioteca.exibirAlerta("Endereço já adicionado");
                    return;
                }
            } 
            catch (Exception e) {
                Biblioteca.exibirAlerta("Erro ao adicionar endereço");
            }
        }
        
        tfLogradouro.setText(endereco.getLogradouro());
        tfBairro.setText(endereco.getBairro());
        tfCidade.setText(endereco.getCidadeEstado().getCidade());
        spEstado.setValue(endereco.getCidadeEstado().getEstado().getUF());
    }//GEN-LAST:event_tfCepFocusLost

    private void btProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoActionPerformed
        
        if(!adicionarEndereco())
            return;
        
        switch (tipoCadastro) {
            case EMPRESA:
                try{
                    CtrCadastroEmpresa.cadastroInfoEndereco(enderecos);
                    setVisible(false);
                    CadastroInfoBasica cadastro = new CadastroInfoBasica(TipoCadastro.ADMIN, true, false);
                    CtrInterfacesGraficas.setCadastroInfoBasica(cadastro);
                    cadastro.setVisible(true);

                }
                catch(Exception e){
                    Biblioteca.exibirAlerta("Erro ao cadastrar empresa: " + e.getMessage());
                }
                break;
            case ADMIN:
                try{
                    CtrUsuario.cadastroInfoEndereco(enderecos);
                    
                    if(cadastrarEmpresa)
                        CtrCadastroEmpresa.cadastrarNovaEmpresa();
                    else
                        CtrUsuario.cadastrarNovoUsuario();
                    
                    Biblioteca.exibirAlerta("Salvo com sucesso!");
                    setVisible(false);
                    Login login = new Login();
                    login.setVisible(true);
                    
                }
                catch(Exception e){
                    Biblioteca.exibirAlerta("Erro ao cadastrar admin: " + e.getMessage());
                }
                break;
            case FUNCIONARIO:
                try{
                    CtrUsuario.cadastroInfoEndereco(enderecos);
                    CtrUsuario.cadastrarNovoUsuario();
                    Biblioteca.exibirAlerta("Salvo com sucesso!");
                    setVisible(false);
                    MenuCadastros menu = new MenuCadastros();
                    CtrInterfacesGraficas.setmMenuCadastros(menu);
                    menu.setVisible(true);
                    
                }
                catch(Exception e){
                    Biblioteca.exibirAlerta("Erro ao cadastrar Funcionario: " + e.getMessage());
                }
                break;
            case CLIENTE:

                CtrCliente.cadastroInfoEndereco(enderecos);
                setVisible(false);
                CadastroInfoVeiculo cadastroVeiculo = new CadastroInfoVeiculo(tipoCadastro);
                cadastroVeiculo.setVisible(true);
                CtrInterfacesGraficas.setCadastroVeiculo(cadastroVeiculo);
                break;
            default:
                System.out.println("Tipo de cadastro inválido");
        }
        
    }//GEN-LAST:event_btProximoActionPerformed

    private void rbComercialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbComercialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbComercialActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        setVisible(false);
        CadastroInfoBasica cadastro = CtrInterfacesGraficas.getCadastroInfoBasica();
        cadastro.setVisible(true);
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btEnderecoRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnderecoRemoverActionPerformed
        String rua = tfLogradouro.getText();

        if(rua.contentEquals(""))
            return;

        ArrayList<String> componentesVerificar = new ArrayList<>();
        componentesVerificar.add(tfCep.getText());
        componentesVerificar.add(tfLogradouro.getText());
        componentesVerificar.add(tfNumero.getText());
        componentesVerificar.add(tfComplemento.getText());
        componentesVerificar.add(tfBairro.getText());
        componentesVerificar.add(tfCidade.getText());
        componentesVerificar.add((String) spEstado.getValue());
        
        for(Endereco endereco : enderecos){
            ArrayList<String> componentes = new ArrayList<>();
            componentes.add(Integer.toString(endereco.getCep()));
            componentes.add(endereco.getLogradouro());
            componentes.add(Integer.toString(endereco.getNumero()));
            componentes.add(endereco.getComplemento());
            componentes.add(endereco.getBairro());
            componentes.add(endereco.getCidadeEstado().getCidade());
            componentes.add(endereco.getCidadeEstado().getEstado().getUF());
            
            try {
                if(Biblioteca.verificarContemNosComponentes(componentes, componentesVerificar))
                    enderecos.remove(endereco);
            } 
            catch (Exception e) {
                Biblioteca.exibirAlerta("Erro ao remover endereço");
            }
            
        }

        limparCampos();
        spEndereco.setValue("Novo endereço");
    }//GEN-LAST:event_btEnderecoRemoverActionPerformed

    private void spEnderecoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spEnderecoStateChanged
        String rua = (String) spEndereco.getValue();
        
        if(rua.contentEquals("Novo endereço")){
            limparCampos();
            return;
        }
        
        Endereco endereco = null;
        int contador = 1;
        for(Endereco end : enderecos){
            if(rua.contentEquals(contador + " - " + end.getLogradouro())){
                endereco = end;
                break;
            }
            
            contador ++;
        } 
        
        if(endereco == null)
            return;
        
        tfCep.setText(Integer.toString(endereco.getCep()));
        tfLogradouro.setText(endereco.getLogradouro());
        tfNumero.setText(Integer.toString(endereco.getNumero()));
        tfComplemento.setText(endereco.getComplemento());
        tfBairro.setText(endereco.getBairro());
        tfCidade.setText(endereco.getCidadeEstado().getCidade());
        spEstado.setValue(endereco.getCidadeEstado().getEstado().getUF());
        rbComercial.setSelected(endereco.getTipo() == TipoEndereco.COMERCIAL);
        rbResidencial.setSelected(endereco.getTipo() == TipoEndereco.RESIDENCIAL);
        
    }//GEN-LAST:event_spEnderecoStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEndereco;
    private javax.swing.JButton btEnderecoRemover;
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btVoltar;
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
    private javax.swing.JRadioButton rbComercial;
    private javax.swing.JRadioButton rbResidencial;
    private javax.swing.JSpinner spEndereco;
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

    private void configurarSpEndereco() {
        ArrayList<String> ruas = new ArrayList<>();
        ruas.add("Novo endereço");
        
        int contador = 1;
        for(Endereco endereco : enderecos){
            ruas.add(contador+ " - " + endereco.getLogradouro());
            contador ++;
        }
        
        spEndereco.setModel(new SpinnerListModel(ruas));
    }
}
