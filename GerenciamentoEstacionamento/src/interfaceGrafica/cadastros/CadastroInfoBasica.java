package interfaceGrafica.cadastros;

import Repositorio.Biblioteca;
import controladores.CtrCliente;
import controladores.CtrUsuario;
import controladores.CtrCadastroEmpresa;
import controladores.CtrInterfacesGraficas;
import enums.TipoCadastro;
import enums.TipoTelefone;
import enums.TipoUsuario;
import interfaceGrafica.Login;
import java.util.ArrayList;
import javax.swing.SpinnerListModel;
import objetos.Cliente;
import objetos.Telefone;
import objetos.UsuarioLogin;

/**
 *
 * @author marce
 */
public class CadastroInfoBasica extends javax.swing.JFrame {

    private TipoCadastro tipoCadastro;
    private ArrayList<String> emails = new ArrayList<>();
    private ArrayList<Telefone> telefones = new ArrayList<>();
    private boolean cadastrarEmpresa = false;
    private boolean update = false;
    
    public CadastroInfoBasica(TipoCadastro tipoCadastro, boolean cadastrarEmpresa, boolean update){
        this.tipoCadastro = tipoCadastro;
        this.cadastrarEmpresa = cadastrarEmpresa;
        this.update= update;
        iniciarComponentes();
        
        if(update)
            preencherInformacoes();
        
    }
     
    public CadastroInfoBasica(TipoCadastro tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
        iniciarComponentes();
    }

    private void iniciarComponentes(){
        
        initComponents();
        rbAtivo.setVisible(false);
        rbMovel.setSelected(true);
        setLocationRelativeTo(null);
        lbTitulo.setText("Cadastro " + tipoCadastro.getDescricao());
        spEmail.setVisible(false);
        spTelefone.setVisible(false);
        configurarSpEmail();
        configurarSpTelefone();
        
        
        if(tipoCadastro == TipoCadastro.EMPRESA || tipoCadastro == TipoCadastro.CLIENTE){
            lbLogin.setVisible(false);
            lbSenha.setVisible(false);
            lbSenhaRepetida.setVisible(false);
            tfLogin.setVisible(false);
            tfSenha.setVisible(false);
            tfSenhaRepetida.setVisible(false);
            
        }
    }
    
    private void cadastroEmpresa(TipoCadastro tipoCadastro){
        
        if(!cadastrarInformacoes(false))
            return;
        
        CtrCadastroEmpresa.cadastroInfoBasica(
            tfNome.getText(), 
            tfCpfCnpj.getText().replace(".", "").replace("/", "").replace("-", ""), 
            emails, 
            telefones
        );
        
        setVisible(false);
        CadastroInfoEndereco cadastroEmpresa = new CadastroInfoEndereco(tipoCadastro, cadastrarEmpresa, update);
        cadastroEmpresa.setVisible(true);
    
    }
    
    private void cadastroCliente(TipoCadastro tipoCadastro){
        
        if(!cadastrarInformacoes(false))
            return;
        
        CtrCliente.cadastroInfoBasica(
            tfNome.getText(), 
            tfCpfCnpj.getText().replace(".", "").replace("/", "").replace("-", ""), 
            emails, 
            telefones
        );
        
        setVisible(false);
        CadastroInfoEndereco cadastroCliente = new CadastroInfoEndereco(tipoCadastro, false, update);
        CtrInterfacesGraficas.setCadastroInfoEndereco(cadastroCliente);
        cadastroCliente.setVisible(true);
    
    }
    
    private void cadastroUsuario(TipoUsuario tipoUsuario){
        
        if(!cadastrarInformacoes(true))
            return;
        
        String senha = new String(tfSenha.getPassword());
        
        CtrUsuario.cadastroInfoBasica(
            tfLogin.getText(), 
            senha, 
            tfNome.getText(), 
            tfCpfCnpj.getText().replace(".", "").replace("/", "").replace("-", ""), 
            emails, 
            telefones, 
            tipoUsuario,
            rbAtivo.isSelected()
        );
        

        setVisible(false);
        CadastroInfoEndereco cadastroEndereco = new CadastroInfoEndereco(TipoCadastro.obterPorIndice(tipoUsuario.getIndice()), cadastrarEmpresa, update);
        
        CtrInterfacesGraficas.setCadastroInfoEndereco(cadastroEndereco);
        
        cadastroEndereco.setVisible(true);
                
    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rbFixo = new javax.swing.JRadioButton();
        rbMovel = new javax.swing.JRadioButton();
        rbWhats = new javax.swing.JRadioButton();
        tfCpfCnpj = new javax.swing.JTextField();
        tfNome = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        tfTelefone = new javax.swing.JTextField();
        btProximo = new javax.swing.JButton();
        lbTitulo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btTelefone = new javax.swing.JButton();
        btEmail = new javax.swing.JButton();
        lbLogin = new javax.swing.JLabel();
        lbSenha = new javax.swing.JLabel();
        lbSenhaRepetida = new javax.swing.JLabel();
        tfLogin = new javax.swing.JTextField();
        tfSenha = new javax.swing.JPasswordField();
        tfSenhaRepetida = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        spEmail = new javax.swing.JSpinner();
        btEmailRemover = new javax.swing.JButton();
        spTelefone = new javax.swing.JSpinner();
        btTelefoneRemover = new javax.swing.JButton();
        rbAtivo = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome Razão Social:");

        jLabel2.setText("Cpf ou Cnpj");

        jLabel3.setText("Email");

        jLabel5.setText("Telefone");

        buttonGroup1.add(rbFixo);
        rbFixo.setText("Fixo");
        rbFixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFixoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbMovel);
        rbMovel.setText("Móvel");

        rbWhats.setText("Possui Whats");

        btProximo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btProximo.setText("Próximo");
        btProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProximoActionPerformed(evt);
            }
        });

        lbTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTitulo.setText("Cadastrar Nova Empresa");

        jLabel15.setText("Informações básicas");

        btTelefone.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btTelefone.setText("+");
        btTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTelefoneActionPerformed(evt);
            }
        });

        btEmail.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btEmail.setText("+");
        btEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmailActionPerformed(evt);
            }
        });

        lbLogin.setText("Login:");

        lbSenha.setText("Senha");

        lbSenhaRepetida.setText("Repita a senha:");

        tfSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSenhaActionPerformed(evt);
            }
        });

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        spEmail.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spEmailStateChanged(evt);
            }
        });

        btEmailRemover.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btEmailRemover.setText("-");
        btEmailRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmailRemoverActionPerformed(evt);
            }
        });

        spTelefone.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spTelefoneStateChanged(evt);
            }
        });

        btTelefoneRemover.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btTelefoneRemover.setText("-");
        btTelefoneRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTelefoneRemoverActionPerformed(evt);
            }
        });

        rbAtivo.setText("Ativo");
        rbAtivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAtivoActionPerformed(evt);
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
                .addComponent(jButton1)
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
                                .addComponent(jLabel5)
                                .addGap(91, 91, 91)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(spEmail)
                                    .addComponent(spTelefone))
                                .addGap(18, 18, 18)
                                .addComponent(rbFixo)
                                .addGap(18, 18, 18)
                                .addComponent(rbMovel)
                                .addGap(18, 18, 18)
                                .addComponent(rbWhats))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbLogin)
                                        .addComponent(lbSenha))
                                    .addComponent(jLabel1)
                                    .addComponent(lbSenhaRepetida)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btEmailRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tfSenhaRepetida, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfSenha, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btTelefoneRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rbAtivo))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitulo)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLogin)
                    .addComponent(tfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbAtivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSenha)
                    .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSenhaRepetida)
                            .addComponent(tfSenhaRepetida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEmailRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(rbFixo)
                    .addComponent(rbMovel)
                    .addComponent(rbWhats)
                    .addComponent(btTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTelefoneRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btProximo)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbFixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFixoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbFixoActionPerformed

    private void btTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTelefoneActionPerformed
        spTelefone.setVisible(true);
        adicionarTelefone();   
    }//GEN-LAST:event_btTelefoneActionPerformed

    private void btEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmailActionPerformed
        spEmail.setVisible(true);
        adicionarEmail();

    }//GEN-LAST:event_btEmailActionPerformed

    private void btProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoActionPerformed

        switch (tipoCadastro) {
            case EMPRESA:
                cadastroEmpresa(TipoCadastro.EMPRESA);
                break;
                
            case ADMIN:
                cadastroUsuario(TipoUsuario.ADMIN);
                break;
                
            case FUNCIONARIO:
                cadastroUsuario(TipoUsuario.FUNCIONARIO);
                break;
                
            case CLIENTE:
                cadastroCliente(TipoCadastro.CLIENTE);
                break;
                
            default:
                
                System.out.println("Tipo de cadastro inválido");
                break;
        }
         CtrInterfacesGraficas.setCadastroInfoBasica(this);
    }//GEN-LAST:event_btProximoActionPerformed

    private void tfSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSenhaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
        
        if(CtrInterfacesGraficas.getMenuCadastros() != null){
            MenuCadastros menu = CtrInterfacesGraficas.getMenuCadastros();
            menu.setVisible(true);
        }
        else{
            Login login = new Login();
            login.setVisible(true);
        }
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void spEmailStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spEmailStateChanged
        String email = (String) spEmail.getValue();
        tfEmail.setText(email.contentEquals("Novo email") ? "" : email);
    }//GEN-LAST:event_spEmailStateChanged

    private void btEmailRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmailRemoverActionPerformed
        String email = tfEmail.getText().trim();
        
        if(email.contentEquals(""))
            return;
        
        for(String e : emails){
            if(e.contentEquals(email)){
                emails.remove(e);
                break;
            }
        }
        configurarSpEmail();
        spEmail.setValue("Novo email");
       
    }//GEN-LAST:event_btEmailRemoverActionPerformed

    private void spTelefoneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spTelefoneStateChanged
        String numero = (String) spTelefone.getValue();
        
        if(numero.contentEquals("Novo número")){
            tfTelefone.setText("");
            return;
        }
        Telefone trocarTelefone = null;
                
        for(Telefone telefone : telefones){
            if(telefone.getNumero().contentEquals(numero)){
                trocarTelefone = telefone;
                break;
            }
        }
        
        if(trocarTelefone == null)
            return;
        
        tfTelefone.setText(trocarTelefone.getNumero());
        rbFixo.setSelected(trocarTelefone.getTipo() == TipoTelefone.FIXO);
        rbMovel.setSelected(trocarTelefone.getTipo() == TipoTelefone.MOVEL);
        rbWhats.setSelected(trocarTelefone.temWhats());
        
    }//GEN-LAST:event_spTelefoneStateChanged

    private void btTelefoneRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTelefoneRemoverActionPerformed
        String numero = tfTelefone.getText();
        
        if(numero.contentEquals(""))
            return;
        
        for(Telefone telefone : telefones){
            if(telefone.getNumero().contentEquals(numero)){
                telefones.remove(telefone);
                break;
            }
        }
        
        tfTelefone.setText("");
        spTelefone.setValue("Novo número");
    }//GEN-LAST:event_btTelefoneRemoverActionPerformed

    private void rbAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbAtivoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEmail;
    private javax.swing.JButton btEmailRemover;
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btTelefone;
    private javax.swing.JButton btTelefoneRemover;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel lbSenhaRepetida;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JRadioButton rbAtivo;
    private javax.swing.JRadioButton rbFixo;
    private javax.swing.JRadioButton rbMovel;
    private javax.swing.JRadioButton rbWhats;
    private javax.swing.JSpinner spEmail;
    private javax.swing.JSpinner spTelefone;
    private javax.swing.JTextField tfCpfCnpj;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfLogin;
    private javax.swing.JTextField tfNome;
    private javax.swing.JPasswordField tfSenha;
    private javax.swing.JPasswordField tfSenhaRepetida;
    private javax.swing.JTextField tfTelefone;
    // End of variables declaration//GEN-END:variables

    private boolean adicionarTelefone() {
        
        String numero = tfTelefone.getText().trim();
        
        if(numero.contentEquals(""))
            return true;
        
        if(!Biblioteca.somenteNumeros(numero)){
            Biblioteca.exibirAlerta("Valor de telefone inválido");
            return false;
        }
        
        for(Telefone tel : telefones){
            if(tel.getNumero().contentEquals(numero)){
                return true;
            }
        }
        
        TipoTelefone tipoTelefone = rbMovel.isSelected() ? TipoTelefone.MOVEL : TipoTelefone.FIXO;
        
        Telefone telefone = new Telefone(
            numero,
            tipoTelefone,
            rbWhats.isSelected()
        );
        
        telefones.add(telefone);
        
        tfTelefone.setText("");
        configurarSpTelefone();
        
        return true;
    }

    private boolean cadastrarInformacoes(boolean verificarSenha) {
        
        String nome = tfNome.getText();
        String cpfCnpj = tfCpfCnpj.getText();
        String numero = tfTelefone.getText();
        String email = tfEmail.getText().trim();
        
        if(verificarSenha){
            String login = tfLogin.getText();
            String senha = new String(tfSenha.getPassword());
            String senhaRepetida = new String(tfSenhaRepetida.getPassword());
            
            if(Biblioteca.verificarCamposVazios(
                login,
                senha,
                senhaRepetida
            )){
                Biblioteca.exibirAlerta("Preencha todos os campos");
                return false;
            }
        
            if(!senha.contentEquals(senhaRepetida)){
                Biblioteca.exibirAlerta("As senhas devem ser iguais");
                return false;
            }
        }

        if(Biblioteca.verificarCamposVazios(
                nome,
                cpfCnpj,
                emails.size() > 0 ? "não vazio" : email,
                telefones.size() > 0 ? "nao vazio" : numero
        )){
            Biblioteca.exibirAlerta("Preencha todos os campos");
            return false;
        }
        
        
        if(!validarCpfCnpj(cpfCnpj)){
            Biblioteca.exibirAlerta("CPF ou CNPJ inválido");
            return false;
        }
        
        
        if(!adicionarEmail())
            return false;

        if(!adicionarTelefone())
            return false;
        
        return true;
    }

    private boolean adicionarEmail() {
        String email = tfEmail.getText().trim();
        
        if(email.contentEquals("") || emails.contains(email))
            return true;
        
        emails.add(email);
        
        configurarSpEmail();
        tfEmail.setText("");
        return true;
    }
    
    private static boolean validarCpfCnpj(String documento) {
        // Remove caracteres especiais como pontos e traços
        documento = documento.replaceAll("[^0-9]", "");
        
        if (documento.length() == 11) {
            return validarCpf(documento);
        }
        
        if (documento.length() == 14) {
            return validarCnpj(documento);
        }
        
        return false;
    }
    
    private static boolean validarCpf(String cpf) {
        // Verifica se todos os dígitos são iguais, o que é inválido para CPF
        if (cpf.matches("(\\d)\\1{10}"))
            return false;
        
        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(cpf.substring(i, i + 1)) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }
        
        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(cpf.substring(i, i + 1)) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }
        
        // Verifica se os dígitos verificadores são iguais aos dígitos informados
        return cpf.substring(9).equals(String.valueOf(digito1) + String.valueOf(digito2));
    }
    
    private static boolean validarCnpj(String cnpj) {
        // Verifica se todos os dígitos são iguais, o que é inválido para CNPJ
        if (cnpj.matches("(\\d)\\1{13}"))
            return false;
        
        // Calcula o primeiro dígito verificador
        int soma = 0;
        int peso = 2;
        for (int i = 11; i >= 0; i--) {
            soma += Integer.parseInt(cnpj.substring(i, i + 1)) * peso;
            peso++;
            if (peso > 9) {
                peso = 2;
            }
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }
        
        // Calcula o segundo dígito verificador
        soma = 0;
        peso = 2;
        for (int i = 12; i >= 0; i--) {
            soma += Integer.parseInt(cnpj.substring(i, i + 1)) * peso;
            peso++;
            if (peso > 9) {
                peso = 2;
            }
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }
        
        // Verifica se os dígitos verificadores são iguais aos dígitos informados
        return cnpj.substring(12).equals(String.valueOf(digito1) + String.valueOf(digito2));
    }

    private void configurarSpEmail() {
        ArrayList<String> emailsSp = new ArrayList<>();
        emailsSp.add("Novo email");
        emailsSp.addAll(emails);
        spEmail.setModel(new SpinnerListModel(emailsSp));
    }
    
    private void configurarSpTelefone() {
        ArrayList<String> numeros = new ArrayList<>();
        numeros.add("Novo número");
        for(Telefone t: telefones)
            numeros.add(t.getNumero());
        
        spTelefone.setModel(new SpinnerListModel(numeros));
    }

    private void preencherInformacoes() {
        
        switch(tipoCadastro){
            case CLIENTE:
                Cliente cliente = CtrCliente.getCliente();
                tfNome.setText(cliente.getNomeUsuario());
                tfCpfCnpj.setText(cliente.getCpfCnpj());
                emails = cliente.getEmails();
                telefones = cliente.getTelefones();
                break;
                
            case ADMIN:
                rbAtivo.setVisible(true);
                UsuarioLogin admin = CtrUsuario.getUsuarioLogin();
                rbAtivo.setSelected(admin.isAtivo());
                tfLogin.setText(admin.getLogin());
                tfSenha.setText(admin.getSenha());
                tfSenhaRepetida.setText(admin.getSenha());
                tfNome.setText(admin.getNomeUsuario());
                tfCpfCnpj.setText(admin.getCpfCnpj());
                emails = admin.getEmails();
                telefones = admin.getTelefones();
                break;
                
            case FUNCIONARIO:
                rbAtivo.setVisible(true);
                UsuarioLogin funcionario = CtrUsuario.getUsuarioLogin();
                rbAtivo.setSelected(funcionario.isAtivo());
                tfLogin.setText(funcionario.getLogin());
                tfSenha.setText(funcionario.getSenha());
                tfSenhaRepetida.setText(funcionario.getSenha());
                tfNome.setText(funcionario.getNomeUsuario());
                tfCpfCnpj.setText(funcionario.getCpfCnpj());
                emails = funcionario.getEmails();
                telefones = funcionario.getTelefones();
                break;
                
            default:
                break;
        }
        
        spEmail.setVisible(true);
        spTelefone.setVisible(true);
        configurarSpEmail();
        configurarSpTelefone();
        Object proximoEmail = spEmail.getNextValue();
        spEmail.setValue(proximoEmail);
        Object proximoTelefone = spTelefone.getNextValue();
        spTelefone.setValue(proximoTelefone);
        
    }
}

