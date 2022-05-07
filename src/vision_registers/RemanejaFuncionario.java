
package vision_registers;

import domain.CRUD.Crud_Funcionario;
import controllers.DepartamentoControl;
import controllers.DiretoriaControl;
import controllers.FuncionarioControl;
import controllers.GerenciaControl;
import LIB.FadeEffect;
import domain.Modelo.FuncionarioModel;
import domain.Modelo.RemanejaModel;
import utils.ConectaBanco;
import utils.ModeloTabela;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;


public class RemanejaFuncionario extends javax.swing.JFrame {

   
    ConectaBanco conecta = new ConectaBanco();
    
  
    public RemanejaFuncionario() {
        initComponents();
        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
//        FadeEffect.transp(RemanejaFuncionario.this);
//        FadeEffect.fadeInFrame(this,15, 0.5f);
        geraComboDe();
        geraComboPara();
        chamaTabelaDe();
        chamaTabelaPara();
        
    }
    
    
    public void geraComboDe(){
        cBoxDe.removeAllItems();
        DiretoriaControl dI = new DiretoriaControl();
        DepartamentoControl dE = new DepartamentoControl();
        GerenciaControl gC = new GerenciaControl();
        
        //Adicionar as Gerências
        ArrayList <String> a = new ArrayList();
        a=gC.getListaGerencias();
        int n=a.size();
        int i;
            for (i=0;i<n;i++){
                cBoxDe.addItem(a.get(i));
                }
//        cBoxDe.removeItemAt(0);
        
        //Adicionar os Departamentos
        ArrayList <String> b = new ArrayList();
        b=dE.getListaDepartamentoCadastroFuncionario();
        int m=b.size();
        int j;
            for (j=1;j<m;j++){
                cBoxDe.addItem(b.get(j));
            }
       
        //Adicionar as Diretorias
        ArrayList <String> c = new ArrayList();
        c=dI.getListaDiretoria();
        int o=c.size();
        int k;
            for (k=1;k<o;k++){
                cBoxDe.addItem(c.get(k));
            }
        cBoxDe.removeItem("PRESIDÊNCIA");
        cBoxDe.addItem("SEM SETOR");
        
    }
    
    public void geraComboPara(){
        cBoxPara.removeAllItems();
        DiretoriaControl dI = new DiretoriaControl();
        DepartamentoControl dE = new DepartamentoControl();
        GerenciaControl gC = new GerenciaControl();
        
        //Adicionar as Gerências
        ArrayList <String> a = new ArrayList();
        a=gC.getListaGerencias();
        int n=a.size();
        int i;
            for (i=0;i<n;i++){
                cBoxPara.addItem(a.get(i));
                }
//        cBoxPara.removeItemAt(0);
        
        //Adicionar os Departamentos
        ArrayList <String> b = new ArrayList();
        b=dE.getListaDepartamentoCadastroFuncionario();
        int m=b.size();
        int j;
            for (j=1;j<m;j++){
                cBoxPara.addItem(b.get(j));
            }
       
        //Adicionar as Diretorias
        ArrayList <String> c = new ArrayList();
        c=dI.getListaDiretoria();
        int o=c.size();
        int k;
            for (k=1;k<o;k++){
                cBoxPara.addItem(c.get(k));
            }
        cBoxPara.removeItem("PRESIDÊNCIA");
        
    }
        
    public void preencherTabelaDe(String sql){
        ArrayList dados = new ArrayList();
        String [] colunas = new String[]{"Mat", "Nome"};
        
        conecta.conexao();
        conecta.executaPesquisaSQL(sql);
        try {
            conecta.rs.first();
        do{
            
            dados.add(new Object[]{conecta.rs.getInt("matFunc_F"), 
                conecta.rs.getString("nome_f")});
            
        }while(conecta.rs.next());
        }catch (SQLException ex) {
//           JOptionPane.showMessageDialog(null,"RemanejaFuncionário/Tabela DE\n"+ex);
        }finally{
            conecta.desconecta();
        }
                        
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTableDe.setModel(modelo);
        jTableDe.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTableDe.getColumnModel().getColumn(0).setResizable(true);
        jTableDe.getColumnModel().getColumn(1).setPreferredWidth(523);
        jTableDe.getColumnModel().getColumn(1).setResizable(true);
        jTableDe.getTableHeader().setReorderingAllowed(false);
        jTableDe.setAutoResizeMode(jTableDe.AUTO_RESIZE_OFF);
        jTableDe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
       }  
    
    public void preencherTabelaPara(String sql){
        ArrayList dados = new ArrayList();
        String [] colunas = new String[]{"Mat", "Nome"};
        
        conecta.conexao();
        conecta.executaPesquisaSQL(sql);
        try {
            conecta.rs.first();
        do{
            
            dados.add(new Object[]{conecta.rs.getInt("matFunc_F"), 
                conecta.rs.getString("nome_f")});
            
        }while(conecta.rs.next());
        }catch (SQLException ex) {
//           JOptionPane.showMessageDialog(null,"RemanejaFuncionário/Tabela Para\n"+ex);
        }finally{
            conecta.desconecta();
        }
                        
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTablePara.setModel(modelo);
        jTablePara.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTablePara.getColumnModel().getColumn(0).setResizable(true);
        jTablePara.getColumnModel().getColumn(1).setPreferredWidth(523);
        jTablePara.getColumnModel().getColumn(1).setResizable(true);
        jTablePara.getTableHeader().setReorderingAllowed(false);
        jTablePara.setAutoResizeMode(jTablePara.AUTO_RESIZE_OFF);
        jTablePara.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
       }  
    
    public void chamaTabelaDe(){
        FuncionarioControl f = new FuncionarioControl();
        preencherTabelaDe(f.chamaTabela(String.valueOf(cBoxDe.getSelectedItem())));
        
    }
    
    public void chamaTabelaPara(){
        FuncionarioControl f = new FuncionarioControl();
        preencherTabelaPara(f.chamaTabela(String.valueOf(cBoxPara.getSelectedItem())));
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDe = new javax.swing.JTable();
        cBoxDe = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cBoxPara = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePara = new javax.swing.JTable();
        btnnewuser2 = new javax.swing.JLabel();
        btnnewuser1 = new javax.swing.JLabel();
        txtNome = new LIB.JTexfieldPH_FielTex();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 680, 10));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("De:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, 40));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 680, 10));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Remanejamento de Funcionários");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, 30));

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

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 80, -1));

        jTableDe.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableDe);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 600, 120));

        cBoxDe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cBoxDe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cBoxDeFocusLost(evt);
            }
        });
        cBoxDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBoxDeActionPerformed(evt);
            }
        });
        jPanel1.add(cBoxDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 400, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Para:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, 40));

        cBoxPara.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cBoxPara.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cBoxParaFocusLost(evt);
            }
        });
        cBoxPara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBoxParaActionPerformed(evt);
            }
        });
        jPanel1.add(cBoxPara, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 470, 30));

        jTablePara.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTablePara);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 600, 120));

        btnnewuser2.setBackground(new java.awt.Color(0, 60, 113));
        btnnewuser2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser2.setForeground(new java.awt.Color(255, 255, 255));
        btnnewuser2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser2.setText("Remanejar");
        btnnewuser2.setOpaque(true);
        btnnewuser2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser2MouseClicked(evt);
            }
        });
        jPanel1.add(btnnewuser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, 130, 40));

        btnnewuser1.setBackground(new java.awt.Color(160, 116, 0));
        btnnewuser1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser1.setForeground(new java.awt.Color(255, 255, 255));
        btnnewuser1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser1.setText("Cancel");
        btnnewuser1.setOpaque(true);
        btnnewuser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser1MouseClicked(evt);
            }
        });
        jPanel1.add(btnnewuser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 430, 130, 40));

        txtNome.setBorder(null);
        txtNome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNome.setPhColor(new java.awt.Color(51, 51, 51));
        txtNome.setPlaceholder("Nome");
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
        });
        jPanel1.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 150, 40));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 150, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
       dispose();
    }//GEN-LAST:event_btn_close1MouseClicked

    private void btnnewuser2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser2MouseClicked
       RemanejaModel f = new RemanejaModel();
       GerenciaControl g = new GerenciaControl();
       DepartamentoControl dE = new DepartamentoControl();
       DiretoriaControl dI = new DiretoriaControl();
       Crud_Funcionario fC = new Crud_Funcionario();
       FuncionarioControl fCo = new FuncionarioControl();
       
       int mat = Integer.parseInt((""+jTableDe.getValueAt(jTableDe.getSelectedRow(), 0)));
       
       
       f.setMat(mat);
       
     
       int idDiretoria=0;
       int idDepartamento=0;
       int idGerencia=0;
       
        String vinculacao = String.valueOf(cBoxPara.getSelectedItem());
        char letra1=vinculacao.charAt(0);
        char letra2=vinculacao.charAt(1);
        String setor=String.valueOf(cBoxPara.getSelectedItem());
        
       
        //Quando for Gerência
        if ((letra1=='G')&&(letra2=='E')){
          idGerencia=g.getIdGerencia(String.valueOf(cBoxPara.getSelectedItem()));
          f.setIdGerencia(idGerencia);
          f.setIdDepartamento(g.getDepartamento(idGerencia));
          f.setIdDiretoria(g.getDiretoria(idGerencia));
          f.setIdPresidencia(g.getPresidencia(idGerencia));
        } else { //Quando for Departamento
        if ((letra1=='D')&&(letra2=='E') || (letra1=='S') && (letra2=='E')){
            idDepartamento=dE.getIdDepartamento(String.valueOf(cBoxPara.getSelectedItem()));
            f.setIdDepartamento(idDepartamento);
            f.setIdDiretoria(dE.getDiretoria(idDepartamento));
            f.setIdPresidencia(dE.getPresidencia(idDepartamento));
            f.setIdGerencia(999);
        } else { // Quando for Diretoria, Assessoria, etc
            idDiretoria=dI.getIdDiretoria(String.valueOf(cBoxPara.getSelectedItem()));
            f.setIdDiretoria(idDiretoria);
            f.setIdPresidencia(1);
            f.setIdDepartamento(999);
            f.setIdGerencia(999);
        }
               
        }
               
        fC.RemanejaFuncionario(f);
        chamaTabelaDe();
        chamaTabelaPara();
        
    }//GEN-LAST:event_btnnewuser2MouseClicked

    private void btnnewuser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnewuser1MouseClicked

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void cBoxDeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cBoxDeFocusLost
       
    }//GEN-LAST:event_cBoxDeFocusLost

    private void cBoxParaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cBoxParaFocusLost
      
    }//GEN-LAST:event_cBoxParaFocusLost

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) { 
             String nome = txtNome.getText();
             String vinculacao = String.valueOf(cBoxDe.getSelectedItem());
             FuncionarioControl f = new FuncionarioControl();
             preencherTabelaDe(f.BuscaPorCampoVinculacao(nome, vinculacao));
         }
    }//GEN-LAST:event_txtNomeKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
      
        
    }//GEN-LAST:event_formWindowActivated

    private void cBoxDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBoxDeActionPerformed
       chamaTabelaDe();
         String vinculacao=(String.valueOf(cBoxDe.getSelectedItem()));
        if (vinculacao.equals("SEM SETOR")){
            txtNome.setEnabled(true);
        } else {
            txtNome.setEnabled(false);
        }
    }//GEN-LAST:event_cBoxDeActionPerformed

    private void cBoxParaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBoxParaActionPerformed
         chamaTabelaPara();
    }//GEN-LAST:event_cBoxParaActionPerformed

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
            java.util.logging.Logger.getLogger(RemanejaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RemanejaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RemanejaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RemanejaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RemanejaFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_close1;
    private javax.swing.JLabel btnnewuser1;
    private javax.swing.JLabel btnnewuser2;
    private javax.swing.JComboBox<String> cBoxDe;
    private javax.swing.JComboBox<String> cBoxPara;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTableDe;
    private javax.swing.JTable jTablePara;
    private LIB.JTexfieldPH_FielTex txtNome;
    // End of variables declaration//GEN-END:variables
}
