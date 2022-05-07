package vision_menus;


import controllers.DepartamentoControl;
import controllers.DiretoriaControl;
import controllers.FuncionarioControl;
import controllers.GerenciaControl;
import controllers.NotificacaoControl;
import controllers.PresidenciaControl;
import controllers.ResetaSenhaControl;
import controllers.TelaMenuControl;
import LIB.FadeEffect;
import vision_events.MenuAlteracaoFrequencia;
import vision_events.VisualizaAlteracaoFrequenciaAvaliadores;
import domain.Modelo.NotificacaoModel;
import domain.Modelo.TelaMenuModel;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class LoginAvaliadores extends javax.swing.JFrame {

    public LoginAvaliadores() {
        initComponents();
        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
        geraCombo();
//        FadeEffect.transp(LoginSistema.this);
//        FadeEffect.fadeInFrame(this, 15, 0.5f);

    }

    public void geraCombo() {
        cBoxVinculacao.removeAllItems();

        if (jRadioDiretoria.isSelected()) {
            ArrayList<String> a = new ArrayList();
            DiretoriaControl d = new DiretoriaControl();
            a = d.getListaDiretoria();
            int n = a.size();
            int i;
            for (i = 0; i < n; i++) {
                cBoxVinculacao.addItem(a.get(i));
            }
//            cBoxVinculacao.removeItemAt(0);
            cBoxVinculacao.removeItem("PRESIDÊNCIA");
        }

        if (jRadioPresidencia.isSelected()) {
            cBoxVinculacao.addItem("PRESIDÊNCIA");
        }

        if (jRadioDepartamento.isSelected()) {
            ArrayList<String> a = new ArrayList();
            DepartamentoControl d = new DepartamentoControl();
            a = d.getListaDepartamento();
            int n = a.size();
            int i;
            for (i = 0; i < n; i++) {
                cBoxVinculacao.addItem(a.get(i));
            }
//            cBoxVinculacao.removeItemAt(0);
        }

        if (jRadioGerencia1.isSelected()) {
            ArrayList<String> a = new ArrayList();
            GerenciaControl g = new GerenciaControl();
            a = g.getListaGerencias();
            int n = a.size();
            int i;
            for (i = 0; i < n; i++) {
                cBoxVinculacao.addItem(a.get(i));
            }
//            cBoxVinculacao.removeItemAt(0);
        }

        if (jRadioGabinete.isSelected()) {
            ArrayList<String> a = new ArrayList();
            DepartamentoControl d = new DepartamentoControl();
            a = d.getListaGabinete();
            int n = a.size();
            int i;
            for (i = 0; i < n; i++) {
                cBoxVinculacao.addItem(a.get(i));
            }
//            cBoxVinculacao.removeItemAt(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cBoxVinculacao = new javax.swing.JComboBox<>();
        btn_cancelar = new java.awt.Button();
        btn_login = new java.awt.Button();
        jRadioGabinete = new javax.swing.JRadioButton();
        jRadioPresidencia = new javax.swing.JRadioButton();
        jRadioDepartamento = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        txt_pwd = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jRadioDiretoria = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jRadioGerencia1 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 710, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 630, 10));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Selecione sua opção");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 40, 650, 40));

        jPanel14.setBackground(new java.awt.Color(160, 116, 0));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 91, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, -1));

        jPanel13.setBackground(new java.awt.Color(0, 60, 113));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 100, 40));

        jPanel9.setBackground(new java.awt.Color(0, 60, 113));

        btn_close1.setBackground(new java.awt.Color(96, 83, 150));
        btn_close1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_close1.setForeground(new java.awt.Color(255, 255, 255));
        btn_close1.setText("X");
        btn_close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_close1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 54, Short.MAX_VALUE)
                .addComponent(btn_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_close1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, 80, -1));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cBoxVinculacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cBoxVinculacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cBoxVinculacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 330, 30));

        btn_cancelar.setBackground(new java.awt.Color(160, 116, 0));
        btn_cancelar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_cancelar.setForeground(new java.awt.Color(255, 255, 255));
        btn_cancelar.setLabel("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 160, 40));

        btn_login.setBackground(new java.awt.Color(0, 60, 113));
        btn_login.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_login.setForeground(new java.awt.Color(255, 255, 255));
        btn_login.setLabel("Login");
        btn_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_loginMouseClicked(evt);
            }
        });
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        jPanel2.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 160, 40));

        jRadioGabinete.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jRadioGabinete.setText("Gabinete");
        jRadioGabinete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioGabineteActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioGabinete, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jRadioPresidencia.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jRadioPresidencia.setText("Presidência");
        jRadioPresidencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioPresidenciaActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioPresidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, -1, -1));

        jRadioDepartamento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jRadioDepartamento.setText("Departamento / Setor ");
        jRadioDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioDepartamentoActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home17/images/unlock_18px.png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, -1, -1));

        txt_pwd.setForeground(new java.awt.Color(102, 102, 102));
        txt_pwd.setBorder(null);
        txt_pwd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_pwdFocusGained(evt);
            }
        });
        txt_pwd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_pwdKeyPressed(evt);
            }
        });
        jPanel2.add(txt_pwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 290, 20));

        jSeparator2.setBackground(new java.awt.Color(0, 60, 113));
        jSeparator2.setForeground(new java.awt.Color(0, 60, 113));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 290, 10));

        jRadioDiretoria.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jRadioDiretoria.setText("Diretoria");
        jRadioDiretoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioDiretoriaActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioDiretoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Esqueceu a senha?");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        jRadioGerencia1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jRadioGerencia1.setText("Gerência");
        jRadioGerencia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioGerencia1ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioGerencia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 640, 240));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
        dispose();
    }//GEN-LAST:event_btn_close1MouseClicked

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        dispose();

    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed

    }//GEN-LAST:event_btn_loginActionPerformed

    private void jRadioGabineteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioGabineteActionPerformed
        jRadioPresidencia.setSelected(false);
        jRadioDepartamento.setSelected(false);
        jRadioDiretoria.setSelected(false);
        jRadioGerencia1.setSelected(false);
        geraCombo();
    }//GEN-LAST:event_jRadioGabineteActionPerformed

    private void jRadioPresidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioPresidenciaActionPerformed
        jRadioGabinete.setSelected(false);
        jRadioDepartamento.setSelected(false);
        jRadioDiretoria.setSelected(false);
        jRadioGerencia1.setSelected(false);
        geraCombo();
    }//GEN-LAST:event_jRadioPresidenciaActionPerformed

    private void jRadioDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioDepartamentoActionPerformed
        jRadioGabinete.setSelected(false);
        jRadioPresidencia.setSelected(false);
        jRadioDiretoria.setSelected(false);
        jRadioGerencia1.setSelected(false);
        geraCombo();
    }//GEN-LAST:event_jRadioDepartamentoActionPerformed

    private void txt_pwdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pwdFocusGained
        txt_pwd.setText("");
    }//GEN-LAST:event_txt_pwdFocusGained

    private void jRadioDiretoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioDiretoriaActionPerformed
        jRadioGabinete.setSelected(false);
        jRadioPresidencia.setSelected(false);
        jRadioDepartamento.setSelected(false);
        jRadioGerencia1.setSelected(false);
        geraCombo();
    }//GEN-LAST:event_jRadioDiretoriaActionPerformed

    private void btn_loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseClicked
        String opcao = "";
        String senha = "";

        TelaMenuModel telaModel = new TelaMenuModel();
        TelaMenuControl telaControl = new TelaMenuControl();

        if (jRadioGerencia1.isSelected()) {
            String local = "Gerência";
            String sigla = String.valueOf(cBoxVinculacao.getSelectedItem());
            ArrayList<NotificacaoModel> n = new ArrayList();
            NotificacaoControl nC = new NotificacaoControl();
            n = nC.getNotificacao(local, sigla);
            telaModel = telaControl.preencheMenu(local, sigla);
            opcao = String.valueOf(cBoxVinculacao.getSelectedItem());
            senha = txt_pwd.getText();
            GerenciaControl g = new GerenciaControl();
            int id = g.getIdGerencia(opcao);
            char primeiraLetra = senha.charAt(0);
            char segundaLetra = senha.charAt(1);
            char terceiraLetra = senha.charAt(2);

            if ((primeiraLetra == '@') && (segundaLetra == '@') && (terceiraLetra == '#')) {//Identifica senha provisória

                if (g.logarProvisoria(id, senha) == true) {
                    boolean login = g.AcessoProvisorio(id);
                    if (login == true) {

                        MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                        menu.setVisible(true);
                        dispose();
                    }
                } else {

                    JOptionPane.showMessageDialog(null, "Senha provisória incorreta.");
                }
            } else {

                if (g.validaLoginGerenciaDefinitivo(opcao, senha) == true) {
                    MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                    menu.setVisible(true);
                    dispose();
                } else {

                    JOptionPane.showMessageDialog(null, "Senha incorreta.");
                }
            }
        }

        if (jRadioDepartamento.isSelected()) {
            String local = "Departamento";
            String sigla = String.valueOf(cBoxVinculacao.getSelectedItem());
            ArrayList<NotificacaoModel> n = new ArrayList();
            NotificacaoControl nC = new NotificacaoControl();
            n = nC.getNotificacao(local, sigla);
            telaModel = telaControl.preencheMenu(local, sigla);
            opcao = String.valueOf(cBoxVinculacao.getSelectedItem());
            senha = txt_pwd.getText();
            DepartamentoControl dE = new DepartamentoControl();
            int id = dE.getIdDepartamento(opcao);
            char primeiraLetra = senha.charAt(0);
            char segundaLetra = senha.charAt(1);
            char terceiraLetra = senha.charAt(2);

            if ((primeiraLetra == '@') && (segundaLetra == '@') && (terceiraLetra == '#')) {//Identifica senha provisória
            
                if (dE.logarProvisoria(id, senha) == true) {
                    boolean login = dE.AcessoProvisorio(id);
                    if (login == true) {

                        MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                        menu.setVisible(true);
                        dispose();
                    }
                } else {

                    JOptionPane.showMessageDialog(null, "Senha provisória incorreta.");
                }
            } else {

                if (dE.validaLoginDepartamentoDefinitivo(opcao, senha) == true) {
                    MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                    menu.setVisible(true);
                    dispose();
                } else {

                    JOptionPane.showMessageDialog(null, "Senha incorreta.");
                }
            }
        }

        if (jRadioGabinete.isSelected()) {
            String local = "Departamento";
            String sigla = String.valueOf(cBoxVinculacao.getSelectedItem());
            ArrayList<NotificacaoModel> n = new ArrayList();
            NotificacaoControl nC = new NotificacaoControl();
            n = nC.getNotificacao(local, sigla);
            telaModel = telaControl.preencheMenu(local, sigla);
            opcao = String.valueOf(cBoxVinculacao.getSelectedItem());
            senha = txt_pwd.getText();
            DepartamentoControl dE = new DepartamentoControl();
            int id = dE.getIdDepartamento(opcao);
            char primeiraLetra = senha.charAt(0);
            char segundaLetra = senha.charAt(1);
            char terceiraLetra = senha.charAt(2);
            
           
            if ((primeiraLetra == '@') && (segundaLetra == '@') && (terceiraLetra == '#')) {//Identifica senha provisória
            
           
                if (dE.logarProvisoria(id, senha) == true) {
                    boolean login = dE.AcessoProvisorio(id);
                    if (login == true) {

                        MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                        menu.setVisible(true);
                        dispose();
                    }
                } else {

                    JOptionPane.showMessageDialog(null, "Senha provisória incorreta.");
                }
            } else {

                if (dE.validaLoginDepartamentoDefinitivo(opcao, senha) == true) {
                    MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                    menu.setVisible(true);
                    dispose();
                } else {

                    JOptionPane.showMessageDialog(null, "Senha incorreta.");
                }
            }
        }

        if (jRadioDiretoria.isSelected()) {

            String local = "Diretoria";
            String sigla = String.valueOf(cBoxVinculacao.getSelectedItem());
            ArrayList<NotificacaoModel> n = new ArrayList();
            NotificacaoControl nC = new NotificacaoControl();
            n = nC.getNotificacao(local, sigla);
            telaModel = telaControl.preencheMenu(local, sigla);

            opcao = String.valueOf(cBoxVinculacao.getSelectedItem());
            senha = txt_pwd.getText();
            DiretoriaControl dI = new DiretoriaControl();
            int id = dI.getIdDiretoria(opcao);

            char primeiraLetra = senha.charAt(0);
            char segundaLetra = senha.charAt(1);
            char terceiraLetra = senha.charAt(2);

            if ((primeiraLetra == '@') && (segundaLetra == '@') && (terceiraLetra == '#')) {//Identifica senha provisória
            
                if (dI.logarProvisoria(id, senha) == true) {
                    boolean login = dI.AcessoProvisorio(id);
                    if (login == true) {

                        MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                        menu.setVisible(true);
                        dispose();
                    }
                } else {

                    JOptionPane.showMessageDialog(null, "Senha provisória incorreta.");
                }
            } else {

                if (dI.validaLoginDiretoriaDefinitiva(opcao, senha) == true) {
                    MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                    menu.setVisible(true);
                    dispose();
                } else {

                    JOptionPane.showMessageDialog(null, "Senha incorreta.");
                }
            }
        }

        if (jRadioPresidencia.isSelected()) {
            String local = "Presidência";
            ArrayList<NotificacaoModel> n = new ArrayList();
            NotificacaoControl nC = new NotificacaoControl();
            n = nC.getNotificacao(local, "Sem sigla");
            telaModel = telaControl.preencheMenu(local, "Sem sigla");//Coloquei sem sigla apenas para passar na função e não precisar fazer outra função para a Presidência
            opcao = String.valueOf(cBoxVinculacao.getSelectedItem());
            senha = txt_pwd.getText();
            PresidenciaControl p = new PresidenciaControl();
            int id = 1;
            char primeiraLetra = senha.charAt(0);
            char segundaLetra = senha.charAt(1);
            char terceiraLetra = senha.charAt(2);

            if ((primeiraLetra == '@') && (segundaLetra == '@') && (terceiraLetra == '#')) {//Identifica senha provisória
            
                if (p.logarProvisoria(id, senha) == true) {
                    boolean login = p.AcessoProvisorio(id);
                    if (login == true) {

                        MenuSetores menu = new MenuSetores(telaModel, local, "Presidência", n);
                        menu.setVisible(true);
                        dispose();
                    }
                } else {

                    JOptionPane.showMessageDialog(null, "Senha provisória incorreta.");
                }
            } else {

                if (p.validaLoginPresidencia(senha) == true) {
                    MenuSetores menu = new MenuSetores(telaModel, local, "Presidência", n);
                    menu.setVisible(true);
                    dispose();
                } else {

                    JOptionPane.showMessageDialog(null, "Senha incorreta.");
                }
            }
        }
    }//GEN-LAST:event_btn_loginMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        String setor = String.valueOf(cBoxVinculacao.getSelectedItem());
        System.out.println(setor);
        if (setor.isBlank() == true) {
            JOptionPane.showMessageDialog(null, "Escolha uma lotação");
        } else {

            Object[] options = {"Sim", "Não"};
            int i = JOptionPane.showOptionDialog(null, "Deseja resetar sua senha e criar uma senha provisória?", "Reset de senha",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            ResetaSenhaControl r = new ResetaSenhaControl();

            if (i == JOptionPane.YES_OPTION) {

                if (jRadioGabinete.isSelected() == true) {
                    GerenciaControl g = new GerenciaControl();
                    String opcao = (String.valueOf(cBoxVinculacao.getSelectedItem()));
                    g.ResetaSenha(opcao, r.geraSenha());
                }

                if (jRadioDepartamento.isSelected() == true) {
                    DepartamentoControl dE = new DepartamentoControl();
                    String opcao = (String.valueOf(cBoxVinculacao.getSelectedItem()));
                    dE.ResetaSenha(opcao, r.geraSenha());
                }

                if (jRadioDiretoria.isSelected() == true) {
                    DiretoriaControl dI = new DiretoriaControl();
                    String opcao = (String.valueOf(cBoxVinculacao.getSelectedItem()));
                    dI.ResetaSenha(opcao, r.geraSenha());
                }

                if (jRadioPresidencia.isSelected() == true) {
                    PresidenciaControl p = new PresidenciaControl();
                    String opcao = (String.valueOf(cBoxVinculacao.getSelectedItem()));
                    p.ResetaSenha(1, r.geraSenha());
                }

            }
        }

    }//GEN-LAST:event_jLabel6MouseClicked

    private void jRadioGerencia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioGerencia1ActionPerformed
        jRadioPresidencia.setSelected(false);
        jRadioDepartamento.setSelected(false);
        jRadioDiretoria.setSelected(false);
        jRadioGabinete.setSelected(false);
        geraCombo();
    }//GEN-LAST:event_jRadioGerencia1ActionPerformed

    private void txt_pwdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pwdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String opcao = "";
            String senha = "";

            TelaMenuModel telaModel = new TelaMenuModel();
            TelaMenuControl telaControl = new TelaMenuControl();

            if (jRadioGerencia1.isSelected()) {
                String local = "Gerência";
                String sigla = String.valueOf(cBoxVinculacao.getSelectedItem());
                ArrayList<NotificacaoModel> n = new ArrayList();
                NotificacaoControl nC = new NotificacaoControl();
                n = nC.getNotificacao(local, sigla);
                telaModel = telaControl.preencheMenu(local, sigla);
                opcao = String.valueOf(cBoxVinculacao.getSelectedItem());
                senha = txt_pwd.getText();
                GerenciaControl g = new GerenciaControl();
                int id = g.getIdGerencia(opcao);
                char primeiraLetra = senha.charAt(0);
                char segundaLetra = senha.charAt(1);
                char terceiraLetra = senha.charAt(2);

                if ((primeiraLetra == '@') && (segundaLetra == '@') && (terceiraLetra == '#')) {//Identifica senha provisóri
                
                    if (g.logarProvisoria(id, senha) == true) {
                        boolean login = g.AcessoProvisorio(id);
                        if (login == true) {

                            MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                            menu.setVisible(true);
                            dispose();
                        }
                    } else {

                        JOptionPane.showMessageDialog(null, "Senha provisória incorreta.");
                    }
                } else {

                    if (g.validaLoginGerenciaDefinitivo(opcao, senha) == true) {
                        MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                        menu.setVisible(true);
                        dispose();
                    } else {

                        JOptionPane.showMessageDialog(null, "Senha incorreta.");
                    }
                }
            }

            if (jRadioDepartamento.isSelected()) {
                String local = "Departamento";
                String sigla = String.valueOf(cBoxVinculacao.getSelectedItem());
                ArrayList<NotificacaoModel> n = new ArrayList();
                NotificacaoControl nC = new NotificacaoControl();
                n = nC.getNotificacao(local, sigla);
                telaModel = telaControl.preencheMenu(local, sigla);
                opcao = String.valueOf(cBoxVinculacao.getSelectedItem());
                senha = txt_pwd.getText();
                DepartamentoControl dE = new DepartamentoControl();
                int id = dE.getIdDepartamento(opcao);
                char primeiraLetra = senha.charAt(0);
                char segundaLetra = senha.charAt(1);
                char terceiraLetra = senha.charAt(2);

                if ((primeiraLetra == '@') && (segundaLetra == '@') && (terceiraLetra == '#')) {//Identifica senha provisóri
               

                    if (dE.logarProvisoria(id, senha) == true) {
                        boolean login = dE.AcessoProvisorio(id);
                        if (login == true) {

                            MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                            menu.setVisible(true);
                            dispose();
                        }
                    } else {

                        JOptionPane.showMessageDialog(null, "Senha provisória incorreta.");
                    }
                } else {

                    if (dE.validaLoginDepartamentoDefinitivo(opcao, senha) == true) {
                        MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                        menu.setVisible(true);
                        dispose();
                    } else {

                        JOptionPane.showMessageDialog(null, "Senha incorreta.");
                    }
                }
            }

            if (jRadioGabinete.isSelected()) {
                String local = "Departamento";
                String sigla = String.valueOf(cBoxVinculacao.getSelectedItem());
                ArrayList<NotificacaoModel> n = new ArrayList();
                NotificacaoControl nC = new NotificacaoControl();
                n = nC.getNotificacao(local, sigla);
                telaModel = telaControl.preencheMenu(local, sigla);
                opcao = String.valueOf(cBoxVinculacao.getSelectedItem());
                senha = txt_pwd.getText();
                DepartamentoControl dE = new DepartamentoControl();
                int id = dE.getIdDepartamento(opcao);
                char primeiraLetra = senha.charAt(0);
                char segundaLetra = senha.charAt(1);
                char terceiraLetra = senha.charAt(2);

                if ((primeiraLetra == '@') && (segundaLetra == '@') && (terceiraLetra == '#')) {//Identifica senha provisóri

                    if (dE.logarProvisoria(id, senha) == true) {
                        boolean login = dE.AcessoProvisorio(id);
                        if (login == true) {

                            MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                            menu.setVisible(true);
                            dispose();
                        }
                    } else {

                        JOptionPane.showMessageDialog(null, "Senha provisória incorreta.");
                    }
                } else {

                    if (dE.validaLoginDepartamentoDefinitivo(opcao, senha) == true) {
                        MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                        menu.setVisible(true);
                        dispose();
                    } else {

                        JOptionPane.showMessageDialog(null, "Senha incorreta.");
                    }
                }
            }

            if (jRadioDiretoria.isSelected()) {

                String local = "Diretoria";
                String sigla = String.valueOf(cBoxVinculacao.getSelectedItem());
                ArrayList<NotificacaoModel> n = new ArrayList();
                NotificacaoControl nC = new NotificacaoControl();
                n = nC.getNotificacao(local, sigla);
                telaModel = telaControl.preencheMenu(local, sigla);

                opcao = String.valueOf(cBoxVinculacao.getSelectedItem());
                senha = txt_pwd.getText();
                DiretoriaControl dI = new DiretoriaControl();
                int id = dI.getIdDiretoria(opcao);

                char primeiraLetra = senha.charAt(0);
                char segundaLetra = senha.charAt(1);
                char terceiraLetra = senha.charAt(2);

                if ((primeiraLetra == '@') && (segundaLetra == '@') && (terceiraLetra == '#')) {//Identifica senha provisória

                    if (dI.logarProvisoria(id, senha) == true) {
                        boolean login = dI.AcessoProvisorio(id);
                        if (login == true) {

                            MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                            menu.setVisible(true);
                            dispose();
                        }
                    } else {

                        JOptionPane.showMessageDialog(null, "Senha provisória incorreta.");
                    }
                } else {

                    if (dI.validaLoginDiretoriaDefinitiva(opcao, senha) == true) {
                        MenuSetores menu = new MenuSetores(telaModel, local, sigla, n);
                        menu.setVisible(true);
                        dispose();
                    } else {

                        JOptionPane.showMessageDialog(null, "Senha incorreta.");
                    }
                }
            }

            if (jRadioPresidencia.isSelected()) {
                String local = "Presidência";
                ArrayList<NotificacaoModel> n = new ArrayList();
                NotificacaoControl nC = new NotificacaoControl();
                n = nC.getNotificacao(local, "Sem sigla");
                telaModel = telaControl.preencheMenu(local, "Sem sigla");//Coloquei sem sigla apenas para passar na função e não precisar fazer outra função para a Presidência
                opcao = String.valueOf(cBoxVinculacao.getSelectedItem());
                senha = txt_pwd.getText();
                PresidenciaControl p = new PresidenciaControl();
                int id = 1;
                char primeiraLetra = senha.charAt(0);
                char segundaLetra = senha.charAt(1);
                char terceiraLetra = senha.charAt(2);

                if ((primeiraLetra == '@') && (segundaLetra == '@') && (terceiraLetra == '#')) {//Identifica senha provisóri
               

                    if (p.logarProvisoria(id, senha) == true) {
                        boolean login = p.AcessoProvisorio(id);
                        if (login == true) {

                            MenuSetores menu = new MenuSetores(telaModel, local, "Presidência", n);
                            menu.setVisible(true);
                            dispose();
                        }
                    } else {

                        JOptionPane.showMessageDialog(null, "Senha provisória incorreta.");
                    }
                } else {

                    if (p.validaLoginPresidencia(senha) == true) {
                        MenuSetores menu = new MenuSetores(telaModel, local, "Presidência", n);
                        menu.setVisible(true);
                        dispose();
                    } else {

                        JOptionPane.showMessageDialog(null, "Senha incorreta.");
                    }
                }
            }
        }


    }//GEN-LAST:event_txt_pwdKeyPressed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginAvaliadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginAvaliadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginAvaliadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginAvaliadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginAvaliadores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btn_cancelar;
    private javax.swing.JLabel btn_close1;
    private java.awt.Button btn_login;
    private javax.swing.JComboBox<String> cBoxVinculacao;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioDepartamento;
    private javax.swing.JRadioButton jRadioDiretoria;
    private javax.swing.JRadioButton jRadioGabinete;
    private javax.swing.JRadioButton jRadioGerencia1;
    private javax.swing.JRadioButton jRadioPresidencia;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPasswordField txt_pwd;
    // End of variables declaration//GEN-END:variables
}
