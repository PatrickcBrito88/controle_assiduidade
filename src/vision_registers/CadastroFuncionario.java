//GERENCIA, DEPCI, GERCOM E DIREX TESTADOS
package vision_registers;

import domain.CRUD.Crud_Funcionario;
import controllers.DepartamentoControl;
import controllers.DiretoriaControl;
import controllers.FuncionarioControl;
import controllers.GerenciaControl;
import controllers_tables.TabelaFuncionariosEdicaoControl;
import controllers_tables.TabelaPendenciasControl;
import LIB.FadeEffect;
import tables.ImagemTabela;
import tables.TabelaFuncionarioEdicao;
import tables.TabelaMenuAdminPendencia;
import domain.Model.ControleTabelas.MenuAdminPendenciasModel;
import domain.Modelo.FuncionarioModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CadastroFuncionario extends javax.swing.JFrame {

    public CadastroFuncionario() {
        initComponents();
        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
//        FadeEffect.transp(CadastroFuncionario.this);
//        FadeEffect.fadeInFrame(this, 15, 0.5f);
        geraCombo();
        geraCargo();
        preencheTabelaFuncionarios();
    }

    public boolean preencheTabelaFuncionarios() {

        TabelaFuncionariosEdicaoControl tabelaFuncionarios = new TabelaFuncionariosEdicaoControl();

        ArrayList<FuncionarioModel> listaTabela = new ArrayList();

        listaTabela = tabelaFuncionarios.getListaFuncionario();
        //listaEventos=listaResumo; 

        boolean sinal = listaTabela.isEmpty();

        if (!listaTabela.isEmpty()) {

            TabelaFuncionarioEdicao tabela = new TabelaFuncionarioEdicao(listaTabela);//Montar uma lista com a tabela Resumo

            tableFuncionário.setDefaultRenderer(Object.class, new ImagemTabela());
            tableFuncionário.setRowHeight(35);
            ((DefaultTableCellRenderer) tableFuncionário.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer) tableFuncionário.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableFuncionário.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableFuncionário.getTableHeader().setOpaque(false);
            tableFuncionário.getTableHeader().setBackground(new Color(0, 0, 0));
            tableFuncionário.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tableFuncionário.setModel(tabela);
        } else {

            JOptionPane.showMessageDialog(null, "Não há Funcionários Cadastrados!");

        }
        return sinal;
    }

    public boolean preencheTabelaFuncionariosBusca(String sql) {

        TabelaFuncionariosEdicaoControl tabelaFuncionarios = new TabelaFuncionariosEdicaoControl();

        ArrayList<FuncionarioModel> listaTabela = new ArrayList();

        listaTabela = tabelaFuncionarios.getListaFuncionarioBusca(sql);
        //listaEventos=listaResumo; 

        boolean sinal = listaTabela.isEmpty();

        if (!listaTabela.isEmpty()) {

            TabelaFuncionarioEdicao tabela = new TabelaFuncionarioEdicao(listaTabela);//Montar uma lista com a tabela Resumo

            tableFuncionário.setDefaultRenderer(Object.class, new ImagemTabela());
            tableFuncionário.setRowHeight(35);
            ((DefaultTableCellRenderer) tableFuncionário.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer) tableFuncionário.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableFuncionário.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableFuncionário.getTableHeader().setOpaque(false);
            tableFuncionário.getTableHeader().setBackground(new Color(0, 0, 0));
            tableFuncionário.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tableFuncionário.setModel(tabela);
        } else {

            JOptionPane.showMessageDialog(null, "Não há Funcionários Cadastrados!");

        }
        return sinal;
    }

    public void geraCombo() {
        cBoxVinculacao.removeAllItems();
        DiretoriaControl dI = new DiretoriaControl();
        DepartamentoControl dE = new DepartamentoControl();
        GerenciaControl gC = new GerenciaControl();

        //Adicionar as Gerências
        ArrayList<String> a = new ArrayList();
        a = gC.getListaGerencias();
        int n = a.size();
        int i;
        for (i = 0; i < n; i++) {
            cBoxVinculacao.addItem(a.get(i));
        }
//        cBoxVinculacao.removeItemAt(0);

        //Adicionar os Departamentos
        ArrayList<String> b = new ArrayList();
        b = dE.getListaDepartamentoCadastroFuncionario();
        int m = b.size();
        int j;
        for (j = 0; j < m; j++) {
            cBoxVinculacao.addItem(b.get(j));
        }

        //Adicionar as Diretorias
        ArrayList<String> c = new ArrayList();
        c = dI.getListaDiretoria();
        int o = c.size();
        int k;
        for (k = 0; k < o; k++) {
            cBoxVinculacao.addItem(c.get(k));
        }
        cBoxVinculacao.removeItem("PRESIDÊNCIA");

    }

    public void geraCargo() {
        String vinculacao = String.valueOf(cBoxVinculacao.getSelectedItem());
        char letra1 = vinculacao.charAt(0);
        char letra2 = vinculacao.charAt(1);

        if ((letra1 == 'G') && (letra2 == 'E')) {
            cBoxCargo.removeAllItems();
            cBoxCargo.addItem("FUNCIONÁRIO");
            cBoxCargo.addItem("GERENTE");
        }

        if ((letra1 == 'D') && (letra2 == 'E')) {
            cBoxCargo.removeAllItems();
            cBoxCargo.addItem("FUNCIONÁRIO");
            cBoxCargo.addItem("CHEFE");
        }
        
        if ((letra1 == 'S') && (letra2 == 'E')) {
            cBoxCargo.removeAllItems();
            cBoxCargo.addItem("FUNCIONÁRIO");
            cBoxCargo.addItem("ENCARREGADO");
        }

        if ((letra1 == 'D') && (letra2 == 'I')) {
            cBoxCargo.removeAllItems();
            cBoxCargo.addItem("FUNCIONÁRIO");
            cBoxCargo.addItem("DIRETOR");
        }

        if ((letra1 == 'G') && (letra2 == 'A')) {
            cBoxCargo.removeAllItems();
            cBoxCargo.addItem("FUNCIONÁRIO");
            cBoxCargo.addItem("CHEFE DE GABINETE");
        }

        if ((letra1 == 'A') && (letra2 == 'S')) {
            cBoxCargo.removeAllItems();
            cBoxCargo.addItem("FUNCIONÁRIO");
            cBoxCargo.addItem("ASSESSOR");
        }
    }

    public void LimpaCampos() {
        txtMail.setText("");
        txtNome.setText("");
        txtMat1.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new LIB.JTexfieldPH_FielTex();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtMail = new LIB.JTexfieldPH_FielTex();
        jLabel5 = new javax.swing.JLabel();
        btnnewuser1 = new javax.swing.JLabel();
        btnnewuser2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        cBoxVinculacao = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cBoxCargo = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableFuncionário = new javax.swing.JTable();
        txtNomeBusca = new LIB.JTexfieldPH_FielTex();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMat1 = new LIB.JTexfieldPH_FielTex();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSeparator1KeyPressed(evt);
            }
        });
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 560, 10));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 400, 10));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Nome:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, 40));

        txtNome.setBorder(null);
        txtNome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNome.setPhColor(new java.awt.Color(51, 51, 51));
        txtNome.setPlaceholder("Nome");
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        jPanel1.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 400, 40));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 560, 10));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 220, 10));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("E-mail:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, -1, 40));

        txtMail.setBorder(null);
        txtMail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMail.setPhColor(new java.awt.Color(51, 51, 51));
        txtMail.setPlaceholder("E-mail");
        txtMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailActionPerformed(evt);
            }
        });
        jPanel1.add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 220, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Vinculação:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, 40));

        btnnewuser1.setBackground(new java.awt.Color(160, 116, 0));
        btnnewuser1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser1.setForeground(new java.awt.Color(255, 255, 255));
        btnnewuser1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser1.setText("Novo");
        btnnewuser1.setOpaque(true);
        btnnewuser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser1MouseClicked(evt);
            }
        });
        jPanel1.add(btnnewuser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 130, 40));

        btnnewuser2.setBackground(new java.awt.Color(0, 60, 113));
        btnnewuser2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser2.setForeground(new java.awt.Color(255, 255, 255));
        btnnewuser2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser2.setText("Salvar");
        btnnewuser2.setOpaque(true);
        btnnewuser2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser2MouseClicked(evt);
            }
        });
        jPanel1.add(btnnewuser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 130, 40));

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

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 80, -1));

        cBoxVinculacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cBoxVinculacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cBoxVinculacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cBoxVinculacaoFocusLost(evt);
            }
        });
        jPanel1.add(cBoxVinculacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 290, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Cargo:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, 40));

        cBoxCargo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cBoxCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cBoxCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 290, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableFuncionário.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableFuncionário.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableFuncionárioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableFuncionário);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 540, 110));

        txtNomeBusca.setBorder(null);
        txtNomeBusca.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNomeBusca.setPhColor(new java.awt.Color(51, 51, 51));
        txtNomeBusca.setPlaceholder("Nome");
        txtNomeBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeBuscaActionPerformed(evt);
            }
        });
        txtNomeBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeBuscaKeyPressed(evt);
            }
        });
        jPanel2.add(txtNomeBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 8, 140, -1));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 140, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Funcionários Cadastrados");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 420, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 560, 170));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Matrícula:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, 40));

        txtMat1.setBorder(null);
        txtMat1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMat1.setPhColor(new java.awt.Color(51, 51, 51));
        txtMat1.setPlaceholder("Matrícula");
        txtMat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMat1ActionPerformed(evt);
            }
        });
        jPanel1.add(txtMat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 100, 40));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 100, 10));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Funcionário");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMailActionPerformed

    private void btnnewuser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser1MouseClicked
        LimpaCampos();
        cBoxVinculacao.setEnabled(true);
        txtMat1.setEnabled(true);
        preencheTabelaFuncionarios();
    }//GEN-LAST:event_btnnewuser1MouseClicked

    private void btnnewuser2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser2MouseClicked
        if (cBoxVinculacao.isEnabled() == true) {
            FuncionarioModel f = new FuncionarioModel();
            GerenciaControl g = new GerenciaControl();
            DepartamentoControl dE = new DepartamentoControl();
            DiretoriaControl dI = new DiretoriaControl();
            Crud_Funcionario fC = new Crud_Funcionario();
            
            
            f.setNome(txtNome.getText());
            f.setMat(Integer.parseInt(txtMat1.getText()));
            f.setCargo(String.valueOf(cBoxCargo.getSelectedItem()));
            f.setEmail(txtMail.getText());
            f.setSenha("");
            int idDiretoria = 0;
            int idDepartamento = 0;
            int idGerencia = 0;
           
            
            String vinculacao = String.valueOf(cBoxVinculacao.getSelectedItem());
            char letra1 = vinculacao.charAt(0);
            char letra2 = vinculacao.charAt(1);
            String setor = String.valueOf(cBoxVinculacao.getSelectedItem());

            //Quando for Gerência
            if ((letra1 == 'G') && (letra2 == 'E')) {
                idGerencia = g.getIdGerencia(String.valueOf(cBoxVinculacao.getSelectedItem()));
                f.setGerencia(idGerencia);
                f.setDepartamento(g.getDepartamento(idGerencia));
                f.setDiretoria(g.getDiretoria(idGerencia));
                f.setPresidencia(g.getPresidencia(idGerencia));
            }  //Quando for Departamento
                if (((letra1 == 'D') && (letra2 == 'E')) || (letra1 == 'S') && (letra2 == 'E')) {
                    idDepartamento = dE.getIdDepartamento(String.valueOf(cBoxVinculacao.getSelectedItem()));
                    f.setDepartamento(idDepartamento);
                    f.setDiretoria(dE.getDiretoria(idDepartamento));
                    f.setPresidencia(dE.getPresidencia(idDepartamento));
                    f.setGerencia(999);
                } 
                    if (((letra1 == 'G') && (letra2 == 'A'))) {
                    idDepartamento = dE.getIdDepartamento(String.valueOf(cBoxVinculacao.getSelectedItem()));
                    f.setDepartamento(idDepartamento);
                    f.setDiretoria(999);
                    f.setPresidencia(dE.getPresidencia(idDepartamento));
                    f.setGerencia(999);
                    
                } if (((letra1 == 'D') && (letra2 == 'I'))||((letra1 == 'A') && (letra2 == 'S'))) { // Quando for Diretoria, Assessoria, etc
                    idDiretoria = dI.getIdDiretoria(String.valueOf(cBoxVinculacao.getSelectedItem()));
                    f.setDiretoria(idDiretoria);
                    f.setPresidencia(1);
                    f.setDepartamento(999);
                    f.setGerencia(999);
                }

            
               
            fC.CadastraFuncionario(f);
            LimpaCampos();
            preencheTabelaFuncionarios();
        } else {
            FuncionarioModel f = new FuncionarioModel();
            f.setCargo(String.valueOf(cBoxCargo.getSelectedItem()));
            f.setMat(Integer.parseInt(txtMat1.getText()));
            f.setNome(txtNome.getText());
            f.setEmail(txtMail.getText());
            Crud_Funcionario funcionario = new Crud_Funcionario();
            funcionario.EditaFuncionario(f);
            preencheTabelaFuncionarios();
            LimpaCampos();
        }
    }//GEN-LAST:event_btnnewuser2MouseClicked

    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
       dispose();
    }//GEN-LAST:event_btn_close1MouseClicked

    private void cBoxVinculacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cBoxVinculacaoFocusLost
        geraCargo();
    }//GEN-LAST:event_cBoxVinculacaoFocusLost

    private void tableFuncionárioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFuncionárioMouseClicked
        String matString = ("" + tableFuncionário.getValueAt(tableFuncionário.getSelectedRow(), 0));
        FuncionarioControl f = new FuncionarioControl();
        FuncionarioModel funcionario = new FuncionarioModel();
        funcionario = f.getFuncionario(Integer.parseInt(matString));
        txtMat1.setText(String.valueOf(funcionario.getMat()));
        txtNome.setText(funcionario.getNome());
        txtMail.setText(funcionario.getEmail());
        cBoxCargo.setSelectedItem(funcionario.getCargo());
        cBoxVinculacao.setEnabled(false);
        txtMat1.setEnabled(false);

    }//GEN-LAST:event_tableFuncionárioMouseClicked

    private void txtMat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMat1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMat1ActionPerformed

    private void txtNomeBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeBuscaActionPerformed

    private void txtNomeBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeBuscaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String nome = txtNomeBusca.getText();

            if (nome.equals("")) {
                preencheTabelaFuncionarios();
            } else {
                FuncionarioControl f = new FuncionarioControl();
                String sql = f.BuscaPorCampoNome(nome);
                preencheTabelaFuncionariosBusca(sql);
            }
        }
    }//GEN-LAST:event_txtNomeBuscaKeyPressed

    private void jSeparator1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSeparator1KeyPressed

    }//GEN-LAST:event_jSeparator1KeyPressed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_close1;
    private javax.swing.JLabel btnnewuser1;
    private javax.swing.JLabel btnnewuser2;
    private javax.swing.JComboBox<String> cBoxCargo;
    private javax.swing.JComboBox<String> cBoxVinculacao;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable tableFuncionário;
    private LIB.JTexfieldPH_FielTex txtMail;
    private LIB.JTexfieldPH_FielTex txtMat1;
    private LIB.JTexfieldPH_FielTex txtNome;
    private LIB.JTexfieldPH_FielTex txtNomeBusca;
    // End of variables declaration//GEN-END:variables
}
