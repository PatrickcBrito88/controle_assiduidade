package vision_events;

import controllers.AlteracaoFrequenciaControl;
import controllers.FuncionarioControl;
import controllers.RepositorioControl;
import controllers.SetoresControl;
import controllers_tables.TabelaAnaliseControl;
import controllers_tables.TabelaResumoControl;
import email_.EnvioEmail;
import domain_enums.EnumSituacao;
import tables.ImagemTabela;
import tables.TabelaAlteracoesAvaliadores;
import tables.TabelaResumo;
import tables.TabelaResumoAvaliadores;
import tables.TabelaResumoFuncionariosEventos;
import domain.Model.ControleTabelas.TabelaAnaliseModel;
import domain.Model.ControleTabelas.TabelaResumoAlteracaoModel;
import domain.Modelo.AnalisaSituacaoModel;
import domain.Modelo.BackupModel;
import domain.Modelo.GestorVisualizaAlteracaoModel;
import domain.Modelo.SetCampos.VisualizaItemAlteracaoModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.image.BufferedImage;

import java.io.FileInputStream;
import java.net.URL;

import java.util.ArrayList;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class FuncionarioVisualizaAlteracao extends javax.swing.JFrame {

   private static String nome;
   private static String referencia;
   private static int mat;

    public FuncionarioVisualizaAlteracao(String nome, String referencia, int mat) {
        initComponents();
        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
        setaEstáticos(referencia, nome, mat);
        setCampos();
       preencheTabelaResumo();
       

//        FadeEffect.transp(NovaAlteracaoFrequencia.this);
//        FadeEffect.fadeInFrame(this, 15, 0.5f);
    }

    private void setCampos() {
        labelCompetencia.setText(referencia);
        labelMatricula.setText(String.valueOf(mat));
        labelNome.setText(nome);
    }

    public void setaEstáticos(String referencia, String nome, int mat) {
      this.referencia=referencia;
      this.nome=nome;
      this.mat=mat;
    }

    List<TabelaResumoAlteracaoModel> listaEventos = new ArrayList();
    FileInputStream fis = null;

    BufferedImage image;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableResumo = new javax.swing.JTable();
        labelMatricula = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        labelCompetencia = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 630, 10));

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
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 620, 10));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Competência:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 140, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Alteração de Frequência");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 430, 40));

        tableResumo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableResumo.setModel(new javax.swing.table.DefaultTableModel(
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
        tableResumo.setAlignmentX(0.0F);
        tableResumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableResumoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableResumo);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 600, 290));

        labelMatricula.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelMatricula.setForeground(new java.awt.Color(102, 102, 102));
        labelMatricula.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelMatricula.setText("xxx");
        jPanel1.add(labelMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 60, 30));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 620, 10));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Mat.:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 40, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Nome do(a) funcionário(a):");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 190, 30));

        labelCompetencia.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelCompetencia.setForeground(new java.awt.Color(102, 102, 102));
        labelCompetencia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCompetencia.setText("Competência");
        jPanel1.add(labelCompetencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 160, 30));

        labelNome.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        labelNome.setForeground(new java.awt.Color(102, 102, 102));
        labelNome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNome.setText("Nome do funcionário:");
        jPanel1.add(labelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 250, 30));

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
                .addGap(0, 44, Short.MAX_VALUE)
                .addComponent(btn_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_close1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 70, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        
    }//GEN-LAST:event_formFocusGained

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    public void preencheTabelaResumo() {
        TabelaResumoControl resumo = new TabelaResumoControl();
        ArrayList<TabelaResumoAlteracaoModel> listaResumoAlteracaoModel = new ArrayList();//Lista de objetos que será exibido na tabela
        FuncionarioControl f = new FuncionarioControl();

        listaResumoAlteracaoModel = resumo.listaResumoFuncionario(mat, referencia);//cria a lista
        listaEventos = listaResumoAlteracaoModel;
      
        
        if (listaResumoAlteracaoModel != null) {
            TabelaResumoFuncionariosEventos tabelaResumoAvaliadores = new TabelaResumoFuncionariosEventos(listaResumoAlteracaoModel);//Montar uma lista com a tabela Resumo
            tableResumo.setDefaultRenderer(Object.class, new ImagemTabela());
            tableResumo.setRowHeight(35);
            ((DefaultTableCellRenderer) tableResumo.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer) tableResumo.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableResumo.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableResumo.getTableHeader().setOpaque(false);
            tableResumo.getTableHeader().setBackground(new Color(0, 0, 0));
            tableResumo.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();
            tableResumo.setModel(tabelaResumoAvaliadores);
        } else {
            JOptionPane.showMessageDialog(null, "Você não possui eventos para esta referência!");
        }

    }

    private void tableResumoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableResumoMouseClicked
        if (evt.getClickCount() == 2) {
            int linha = (tableResumo.getSelectedRow());

            VisualizaItemAlteracaoModel item = new VisualizaItemAlteracaoModel();
            item.setDataEvento(listaEventos.get(linha).getDataEvento());
            item.setDescricao(listaEventos.get(linha).getDescricao());
            item.setReferencia(listaEventos.get(linha).getReferencia());
            item.setTipo(listaEventos.get(linha).getTipo());
            item.setIdRepositorio(listaEventos.get(linha).getId());
            item.setTrava(listaEventos.get(linha).isTrava());
            item.setMat(mat);
           
            FuncionarioEventos funcionario = new FuncionarioEventos(item);
            funcionario.setVisible(true);
        }
        //FAZER ALGO PARA PEGAR A LINHA, PREENCHER O OBJETO E JOGAR PARA A TELA PARA O FUNCIONÁRIO ANALISAR 

    }//GEN-LAST:event_tableResumoMouseClicked

    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
              dispose();
    }//GEN-LAST:event_btn_close1MouseClicked

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
            java.util.logging.Logger.getLogger(FuncionarioVisualizaAlteracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FuncionarioVisualizaAlteracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FuncionarioVisualizaAlteracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FuncionarioVisualizaAlteracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FuncionarioVisualizaAlteracao(nome, referencia, mat).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_close1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel labelCompetencia;
    private javax.swing.JLabel labelMatricula;
    private javax.swing.JLabel labelNome;
    private javax.swing.JTable tableResumo;
    // End of variables declaration//GEN-END:variables
}
