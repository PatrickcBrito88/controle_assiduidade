
package vision_registers;

import controllers.DepartamentoControl;
import controllers.DiretoriaControl;
import controllers_tables.TabelaDepartamentoControl;
import controllers_tables.TabelaDiretoriaControl;
import domain.CRUD.Crud_Diretoria;
import LIB.FadeEffect;
import tables.ImagemTabela;
import tables.TabelaDepartamentos;
import tables.TabelaDiretoria;
import domain.CRUD.Crud_Departamento;
import domain.Model.ControleTabelas.TabelaDepartamentoModel;
import domain.Model.ControleTabelas.TabelaDiretoriaModel;
import domain.Modelo.DepartamentoModel;
import domain.Modelo.DiretoriaModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;


public class CadastroDiretoria extends javax.swing.JFrame {

   
    public CadastroDiretoria() {
        initComponents();
        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
//        FadeEffect.transp(CadastroDiretoria.this);
//        FadeEffect.fadeInFrame(this, 15, 0.5f);
        geraComboVinculacao();
        preencheTabelaDiretoria();
    }
    
    int idDiretoria=0;
    
     public boolean preencheTabelaDiretoria() {

        TabelaDiretoriaControl tabelaDiretoria = new TabelaDiretoriaControl();//Controle da Tabela

        ArrayList<TabelaDiretoriaModel> listaTabela = new ArrayList();//Tipo de Lista

        listaTabela = tabelaDiretoria.getListaDiretoria();
        //listaEventos=listaResumo; 

        boolean sinal = listaTabela.isEmpty();

        if (!listaTabela.isEmpty()) {

            TabelaDiretoria tabela = new TabelaDiretoria(listaTabela);//Montar uma lista com a tabela Resumo

            tableSetor.setDefaultRenderer(Object.class, new ImagemTabela());
            tableSetor.setRowHeight(35);
            ((DefaultTableCellRenderer) tableSetor.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer) tableSetor.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableSetor.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableSetor.getTableHeader().setOpaque(false);
            tableSetor.getTableHeader().setBackground(new Color(0, 0, 0));
            tableSetor.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tableSetor.setModel(tabela);
        } else {

            JOptionPane.showMessageDialog(null, "Não há Diretorias Cadastradas!");

        }
        return sinal;
    }
     
     public boolean preencheTabelaDiretoriaBusca(String sql) {

        TabelaDiretoriaControl tabelaDiretoria = new TabelaDiretoriaControl();//Controle da Tabela

        ArrayList<TabelaDiretoriaModel> listaTabela = new ArrayList();//Tipo de Lista

        listaTabela = tabelaDiretoria.getListaDiretoriaBuscaCampo(sql);
        //listaEventos=listaResumo; 

        boolean sinal = listaTabela.isEmpty();

        if (!listaTabela.isEmpty()) {

            TabelaDiretoria tabela = new TabelaDiretoria(listaTabela);//Montar uma lista com a tabela Resumo

            tableSetor.setDefaultRenderer(Object.class, new ImagemTabela());
            tableSetor.setRowHeight(35);
            ((DefaultTableCellRenderer) tableSetor.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer) tableSetor.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableSetor.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableSetor.getTableHeader().setOpaque(false);
            tableSetor.getTableHeader().setBackground(new Color(0, 0, 0));
            tableSetor.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tableSetor.setModel(tabela);
        } else {

            JOptionPane.showMessageDialog(null, "Não há Diretorias Cadastradas!");

        }
        return sinal;
    }

   
    public void geraComboVinculacao(){
        cBoxVinculacao.removeAllItems();
        cBoxVinculacao.addItem("Presidência");
    }
    
    public void limpaCampos(){
        txtNome.setText("");
        txtEmail.setText("");
        txtSigla.setText("");
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
        txtEmail = new LIB.JTexfieldPH_FielTex();
        jLabel5 = new javax.swing.JLabel();
        btnnewuser1 = new javax.swing.JLabel();
        btnnewuser2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        cBoxVinculacao = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtSigla = new LIB.JTexfieldPH_FielTex();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        txtNomeBusca = new LIB.JTexfieldPH_FielTex();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSetor = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 590, 10));
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
        jPanel1.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 410, 40));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 590, 10));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 200, 10));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("E-mail:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, -1, 40));

        txtEmail.setBorder(null);
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtEmail.setPhColor(new java.awt.Color(51, 51, 51));
        txtEmail.setPlaceholder("E-mail");
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 200, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Vinculação:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, 40));

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
        jPanel1.add(btnnewuser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 130, 40));

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
        jPanel1.add(btnnewuser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 130, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Diretoria");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, 30));

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

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 80, -1));

        cBoxVinculacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cBoxVinculacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cBoxVinculacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 290, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Sigla:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, 40));

        txtSigla.setBorder(null);
        txtSigla.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSigla.setPhColor(new java.awt.Color(51, 51, 51));
        txtSigla.setPlaceholder("Sigla");
        txtSigla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSiglaActionPerformed(evt);
            }
        });
        jPanel1.add(txtSigla, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 120, 40));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 120, 10));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel2.add(txtNomeBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 140, 30));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 140, 10));

        tableSetor.setModel(new javax.swing.table.DefaultTableModel(
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
        tableSetor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSetorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSetor);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 570, 170));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Diretorias Cadastradas");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 590, 220));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnnewuser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnewuser1MouseClicked

    private void btnnewuser2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser2MouseClicked
        if (cBoxVinculacao.isEnabled()==true){
        DiretoriaModel d = new DiretoriaModel();
        d.setNome(txtNome.getText());
        d.setSigla(txtSigla.getText());
        d.setEmail(txtEmail.getText());
        d.setPresidencia(1); //Presidencia 1 é igual ao registro do Presidente
        
        
        Crud_Diretoria c = new Crud_Diretoria();
        c.CadastraDiretoria(d);
        limpaCampos();
        preencheTabelaDiretoria();
        } else {
            DiretoriaModel dI = new DiretoriaModel();
            dI.setEmail(txtEmail.getText());
            dI.setNome(txtNome.getText());
            dI.setSigla(txtSigla.getText());
            dI.setIdDir(idDiretoria);
            Crud_Diretoria c = new Crud_Diretoria();
           c.EditaDiretoria(dI);
            limpaCampos();
            cBoxVinculacao.setEnabled(true);
            preencheTabelaDiretoria();
        }
        
    }//GEN-LAST:event_btnnewuser2MouseClicked

    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btn_close1MouseClicked

    private void txtSiglaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSiglaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSiglaActionPerformed

    private void txtNomeBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeBuscaActionPerformed

    private void txtNomeBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeBuscaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String nome = txtNomeBusca.getText();

            if (nome.equals("")) {
                preencheTabelaDiretoria();
            } else {
                DiretoriaControl dI = new DiretoriaControl();
                String sql = dI.BuscaPorCampoNome(nome);
                preencheTabelaDiretoriaBusca(sql);
            }
        }
    }//GEN-LAST:event_txtNomeBuscaKeyPressed

    private void tableSetorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSetorMouseClicked
        DiretoriaModel dI = new DiretoriaModel();
        dI.setNome("" + tableSetor.getValueAt(tableSetor.getSelectedRow(), 0));
        dI.setSigla("" + tableSetor.getValueAt(tableSetor.getSelectedRow(), 1));
        dI.setEmail("" + tableSetor.getValueAt(tableSetor.getSelectedRow(), 2));
        DiretoriaControl dC = new DiretoriaControl();
        idDiretoria = dC.getIdDiretoria(dI.getSigla());

        txtNome.setText(dI.getNome());
        txtEmail.setText(dI.getEmail());
        txtSigla.setText(dI.getSigla());
        cBoxVinculacao.setEnabled(false);
    }//GEN-LAST:event_tableSetorMouseClicked

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
            java.util.logging.Logger.getLogger(CadastroDiretoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroDiretoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroDiretoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroDiretoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new CadastroDiretoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_close1;
    private javax.swing.JLabel btnnewuser1;
    private javax.swing.JLabel btnnewuser2;
    private javax.swing.JComboBox<String> cBoxVinculacao;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JTable tableSetor;
    private LIB.JTexfieldPH_FielTex txtEmail;
    private LIB.JTexfieldPH_FielTex txtNome;
    private LIB.JTexfieldPH_FielTex txtNomeBusca;
    private LIB.JTexfieldPH_FielTex txtSigla;
    // End of variables declaration//GEN-END:variables
}
