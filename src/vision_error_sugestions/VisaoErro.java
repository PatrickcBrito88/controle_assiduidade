
package vision_error_sugestions;

import controllers.FuncionarioControl;
import controllers_tables.TabelaErroControl;
import controllers_tables.TabelaResumoControl;
import tables.ImagemTabela;
import tables.TabelaErros;
import tables.TabelaResumoAvaliadores;
import domain.CRUD.Crud_Erro;
import domain.Model.ControleTabelas.ErroModelTabela;
import domain.Model.ControleTabelas.TabelaResumoAlteracaoModel;
import domain.Modelo.ErroModel;
import static groovy.xml.dom.DOMCategory.parent;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;


public class VisaoErro extends javax.swing.JFrame {
    

    private static int mat;
    
    public VisaoErro(int mat) {
        initComponents();
        setMat(mat);
        setCampos();
    }
    
    ArrayList<ErroModelTabela> listaDeErros = new ArrayList();
    
    public void setMat(int mat){
        this.mat=mat;
    }
    
    public void setCampos(){
        FuncionarioControl f = new FuncionarioControl();
        labelNome.setText(f.getNome(mat));
        labelMatricula.setText(String.valueOf(mat));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableErro = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        labelMatricula = new javax.swing.JLabel();
        btnnewuser6 = new javax.swing.JLabel();
        btnnewuser4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableErro.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableErro);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 540, 230));

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

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, -1, -1));

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

        jPanel1.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Reportar Erro");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 10, 350, 30));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 540, 10));

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

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Nome do(a) funcionário(a):");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 200, 30));

        labelNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelNome.setForeground(new java.awt.Color(102, 102, 102));
        labelNome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNome.setText("Nome do funcionário:");
        jPanel1.add(labelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 290, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Mat.:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 40, 30));

        labelMatricula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelMatricula.setForeground(new java.awt.Color(102, 102, 102));
        labelMatricula.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelMatricula.setText("xxx");
        jPanel1.add(labelMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 70, 30));

        btnnewuser6.setBackground(new java.awt.Color(0, 60, 113));
        btnnewuser6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser6.setForeground(new java.awt.Color(255, 255, 255));
        btnnewuser6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser6.setText("Novo");
        btnnewuser6.setOpaque(true);
        btnnewuser6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser6MouseClicked(evt);
            }
        });
        jPanel1.add(btnnewuser6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, 130, 40));

        btnnewuser4.setBackground(new java.awt.Color(160, 116, 0));
        btnnewuser4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser4.setForeground(new java.awt.Color(255, 255, 255));
        btnnewuser4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser4.setText("Apagar");
        btnnewuser4.setOpaque(true);
        btnnewuser4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnnewuser4MouseEntered(evt);
            }
        });
        jPanel1.add(btnnewuser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 130, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
        dispose();
    }//GEN-LAST:event_btn_close1MouseClicked

    private void btnnewuser6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser6MouseClicked
        String texto="";
        Motivo motivo = new Motivo(null, true, texto, mat);
        motivo.setVisible(true);
        String descricao=motivo.getMotivo();
         int nCarac = motivo.getN();
        
         String descricaoErro="";
        if (nCarac<=500){
        descricaoErro=motivo.getMotivo();
        } else {
            JOptionPane.showMessageDialog(null,"Digite no máximo 500 caracteres!");
        }
        
    }//GEN-LAST:event_btnnewuser6MouseClicked

    public void preencheTabelaResumo() {
        TabelaErroControl resumo = new TabelaErroControl();
        ArrayList<ErroModelTabela> lista = new ArrayList();
        
        lista=resumo.ListaErros(mat);
        listaDeErros=lista;
        
        
        if (lista != null) {
            
            TabelaErros tabelaDeErros = new TabelaErros(lista);
            
            tableErro.setDefaultRenderer(Object.class, new ImagemTabela());
            tableErro.setRowHeight(35);
            ((DefaultTableCellRenderer) tableErro.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer) tableErro.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableErro.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableErro.getTableHeader().setOpaque(false);
            tableErro.getTableHeader().setBackground(new Color(0, 0, 0));
            tableErro.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();
            tableErro.setModel(tabelaDeErros);
        } else {
            JOptionPane.showMessageDialog(null, "Você ainda não enviou nenhum erro!");
        }
        
    }
    
    private void btnnewuser4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser4MouseClicked
          int linha = (tableErro.getSelectedRow());
           int id=(listaDeErros.get(linha).getId());
           Crud_Erro c = new Crud_Erro();
           c.deletaErro(id);
           preencheTabelaResumo();
    }//GEN-LAST:event_btnnewuser4MouseClicked

    private void btnnewuser4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnewuser4MouseEntered

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        preencheTabelaResumo();
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(VisaoErro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisaoErro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisaoErro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisaoErro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisaoErro(mat).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_close1;
    private javax.swing.JLabel btnnewuser4;
    private javax.swing.JLabel btnnewuser6;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelMatricula;
    private javax.swing.JLabel labelNome;
    private javax.swing.JTable tableErro;
    // End of variables declaration//GEN-END:variables
}
