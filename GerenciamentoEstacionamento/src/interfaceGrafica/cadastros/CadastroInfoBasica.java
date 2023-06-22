package interfaceGrafica.cadastros;

import Repositorio.Biblioteca;
import controladores.CtrCadastroAdmin;
import controladores.CtrCadastroEmpresa;
import enums.TipoCadastro;
import enums.TipoTelefone;
import enums.TipoUsuario;
import java.util.ArrayList;
import java.util.Arrays;
import objetos.Telefone;

/**
 *
 * @author marce
 */
public class CadastroInfoBasica extends javax.swing.JFrame {

    private TipoCadastro tipoCadastro;
    private ArrayList<String> emails = new ArrayList<>();
    private ArrayList<Telefone> telefones = new ArrayList<>();
    
    public CadastroInfoBasica(TipoCadastro tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
        initComponents();
        rbMovel.setSelected(true);
        setLocationRelativeTo(null);
        lbTitulo.setText("Cadastro " + tipoCadastro.getDescricao());
        lbUltimoEmail.setVisible(false);
        lbUltimoTelefone.setVisible(false);
        
        if(tipoCadastro == TipoCadastro.EMPRESA){
            lbLogin.setVisible(false);
            lbSenha.setVisible(false);
            lbSenhaRepetida.setVisible(false);
            tfLogin.setVisible(false);
            tfSenha.setVisible(false);
            tfSenhaRepetida.setVisible(false);
            
        }
    }
    
    private void cadastroEmpresa(){
        
        if(!cadastrarInformacoes(false))
            return;
        
        CtrCadastroEmpresa.cadastroInfoBasica(
            tfNome.getText(), 
            tfCpfCnpj.getText(), 
            emails, 
            telefones
        );
        
        setVisible(false);
        CadastroInfoEndereco cadastroEmpresa = new CadastroInfoEndereco(TipoCadastro.EMPRESA);
        cadastroEmpresa.setVisible(true);
    
    }
    
    private void cadastroAdmin(){
        
        if(!cadastrarInformacoes(true))
            return;
        
        CtrCadastroAdmin.cadastroInfoBasica(
            tfLogin.getText(), 
            tfSenha.getPassword().toString(), 
            tfNome.getText(), 
            tfCpfCnpj.getText(), 
            emails, telefones, 
            TipoUsuario.ADMIN
        );
        
        setVisible(false);
        CadastroInfoEndereco cadastroAdmin = new CadastroInfoEndereco(TipoCadastro.ADMIN);
        cadastroAdmin.setVisible(true);
                
    
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
        lbUltimoEmail = new javax.swing.JLabel();
        lbUltimoTelefone = new javax.swing.JLabel();

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

        lbUltimoEmail.setText("jLabel4");
        lbUltimoEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbUltimoEmailMouseClicked(evt);
            }
        });

        lbUltimoTelefone.setText("jLabel4");
        lbUltimoTelefone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbUltimoTelefoneMouseClicked(evt);
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
                .addGap(0, 0, Short.MAX_VALUE))
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
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbLogin)
                                        .addComponent(lbSenha))
                                    .addComponent(jLabel1)
                                    .addComponent(lbSenhaRepetida)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tfSenhaRepetida, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfSenha, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(91, 91, 91)
                                .addComponent(tfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rbFixo)
                                .addGap(18, 18, 18)
                                .addComponent(rbMovel)
                                .addGap(18, 18, 18)
                                .addComponent(rbWhats)
                                .addGap(18, 18, 18)
                                .addComponent(btTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbUltimoEmail)
                            .addComponent(lbUltimoTelefone))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLogin)
                    .addComponent(tfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbUltimoEmail)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(rbFixo)
                    .addComponent(rbMovel)
                    .addComponent(rbWhats)
                    .addComponent(btTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbUltimoTelefone)
                .addGap(66, 66, 66)
                .addComponent(btProximo)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbFixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFixoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbFixoActionPerformed

    private void btTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTelefoneActionPerformed

        if(!adicionarTelefone())
            return;
        
        tfTelefone.setText("");
        lbUltimoTelefone.setText("QTD Telefones: " + telefones.size() + "; " + telefones.get(telefones.size() -1).getNumero() + " X ");
        lbUltimoTelefone.setVisible(true);
    }//GEN-LAST:event_btTelefoneActionPerformed

    private void btEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmailActionPerformed
        String email = tfEmail.getText().trim();
        
        if(email.contentEquals(""))
            return;
        
        if(!adicionarEmail())
            return;
        
        tfEmail.setText("");
        lbUltimoEmail.setText("QTD Emails: " + emails.size() + "; " + email + " X ");
        lbUltimoEmail.setVisible(true);
    }//GEN-LAST:event_btEmailActionPerformed

    private void btProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoActionPerformed

        switch (tipoCadastro) {
            case EMPRESA:
                cadastroEmpresa();
                break;
                
            case ADMIN:
                cadastroAdmin();
                break;
                
            case FUNCIONARIO:
                
                System.out.println("Cadastro de Funcionário");
                break;
                
            case CLIENTE:
                
                System.out.println("Cadastro de Cliente");
                break;
                
            default:
                
                System.out.println("Tipo de cadastro inválido");
                break;
        }
        
    }//GEN-LAST:event_btProximoActionPerformed

    private void tfSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSenhaActionPerformed

    private void lbUltimoTelefoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUltimoTelefoneMouseClicked
        int ultimaPos = telefones.size() - 1;
        
        telefones.remove(ultimaPos);
        
        if(telefones.size() == 0){
            lbUltimoTelefone.setVisible(false);
            return;
        }
        
        lbUltimoTelefone.setText(telefones.get(ultimaPos - 1).getNumero() + " X ");
    }//GEN-LAST:event_lbUltimoTelefoneMouseClicked

    private void lbUltimoEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUltimoEmailMouseClicked
        int ultimaPos = emails.size() - 1;
        
        emails.remove(ultimaPos);
        
        if(emails.size() == 0){
            lbUltimoEmail.setVisible(false);
            return;
        }
        
        lbUltimoEmail.setText(emails.get(ultimaPos - 1) + " X ");
    }//GEN-LAST:event_lbUltimoEmailMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEmail;
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btTelefone;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel lbSenhaRepetida;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbUltimoEmail;
    private javax.swing.JLabel lbUltimoTelefone;
    private javax.swing.JRadioButton rbFixo;
    private javax.swing.JRadioButton rbMovel;
    private javax.swing.JRadioButton rbWhats;
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
        
        TipoTelefone tipoTelefone = rbMovel.isSelected() ? TipoTelefone.MOVEL : TipoTelefone.FIXO;
        
        Telefone telefone = new Telefone(
            numero,
            tipoTelefone,
            rbWhats.isSelected()
        );
        
        telefones.add(telefone);
        
        return true;
    }

    private boolean cadastrarInformacoes(boolean verificarSenha) {
        
        String nome = tfNome.getText();
        String cpfCnpj = tfCpfCnpj.getText();
        String numero = tfTelefone.getText();
        String email = tfEmail.getText();
        
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
        String email = tfEmail.getText();
        
        if(email.contentEquals(""))
            return true;
        
        if(emails.contains(email)){
            Biblioteca.exibirAlerta("Email já inserido");
            return false;
        }
        
        emails.add(email);
        
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
}

