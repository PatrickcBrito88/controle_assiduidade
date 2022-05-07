package vision_events;

import controllers.FuncionarioControl;
import controllers_tables.TabelaAnaliseControl;
import controllers_tables.TabelaResumoControl;
import domain_enums.EnumSituacao;
import tables.ImagemTabela;
import tables.TabelaAlteracoesAvaliadores;
import tables.TabelaResumo;
import tables.TabelaResumoAvaliadores;
import domain.Model.ControleTabelas.TabelaAnaliseModel;
import domain.Model.ControleTabelas.TabelaResumoAlteracaoModel;
import domain.Modelo.AnalisaSituacaoModel;
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

public class VisualizaAlteracaoFrequenciaAvaliadores extends javax.swing.JFrame {

    private static String tipoLocalizacao;
    private static int idLocalizacao;

    public VisualizaAlteracaoFrequenciaAvaliadores(String tipoLocalizacao, int idLocalizacao) {
        initComponents();
        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
        setaEstáticos(tipoLocalizacao, idLocalizacao);
        boolean sinal=preencheTabelaAlteracoes();
        if (sinal==true){
           System.exit(1);
        }
        
        preencheTabelaResumoInicial();
//        FadeEffect.transp(NovaAlteracaoFrequencia.this);
//        FadeEffect.fadeInFrame(this, 15, 0.5f);

    }

    public void setaEstáticos(String tipoLocalizacao, int idLocalizacao) {
        this.tipoLocalizacao = tipoLocalizacao;
        this.idLocalizacao = idLocalizacao;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAlteracoes = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableResumo = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();

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
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 610, 10));

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
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 520, 10));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Alterações de Frequência pendentes de avaliação");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 470, 30));

        tableAlteracoes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableAlteracoes.setModel(new javax.swing.table.DefaultTableModel(
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
        tableAlteracoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAlteracoesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableAlteracoes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 490, 360));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Alterações de Frequência");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 0, 400, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setAlignmentX(0.0F);
        jPanel2.setAlignmentY(0.0F);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 80, -1));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 480, 10));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 470, 10));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Tabela Resumo");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 460, 30));

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
        tableResumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableResumoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableResumo);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 460, 360));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Eventos da Avaliação de Frequência selecionada");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 460, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public boolean preencheTabelaAlteracoes() {
        TabelaAnaliseControl analiseControle = new TabelaAnaliseControl();
        ArrayList<TabelaAnaliseModel> listaResumoAnaliseModel = new ArrayList();
        listaResumoAnaliseModel = analiseControle.listaTabelaAnaliseLocalizacao(idLocalizacao, tipoLocalizacao);
        //listaEventos=listaResumo; 

        boolean sinal = listaResumoAnaliseModel.isEmpty();

        if (!listaResumoAnaliseModel.isEmpty()) {

            TabelaAlteracoesAvaliadores tabelaAlteracoes = new TabelaAlteracoesAvaliadores(listaResumoAnaliseModel);//Montar uma lista com a tabela Resumo

            tableAlteracoes.setDefaultRenderer(Object.class, new ImagemTabela());
            tableAlteracoes.setRowHeight(35);
            ((DefaultTableCellRenderer) tableAlteracoes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer)tableAlteracoes.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableAlteracoes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableAlteracoes.getTableHeader().setOpaque(false);
            tableAlteracoes.getTableHeader().setBackground(new Color(0, 0, 0));
            tableAlteracoes.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tableAlteracoes.setModel(tabelaAlteracoes);
        } else {
            JOptionPane.showMessageDialog(null, "Você não possui Alterações de Frequência para analisar!");
           
        }
        return sinal;
    }

    public void preencheTabelaResumoTempoReal() {
        TabelaResumoControl resumo = new TabelaResumoControl();
        ArrayList<TabelaResumoAlteracaoModel> listaResumoAlteracaoModel = new ArrayList();//Lista de objetos que será exibido na tabela

        int mat = Integer.parseInt("" + tableAlteracoes.getValueAt(tableAlteracoes.getSelectedRow(), 1));
        String referencia = ("" + tableAlteracoes.getValueAt(tableAlteracoes.getSelectedRow(), 0));

        listaResumoAlteracaoModel = resumo.listaResumoAnaliseAvaliadores(mat, referencia, tipoLocalizacao);//cria a lista
        listaEventos = listaResumoAlteracaoModel;

        if (listaResumoAlteracaoModel != null) {
            TabelaResumoAvaliadores tabelaResumoAvaliadores = new TabelaResumoAvaliadores(listaResumoAlteracaoModel);//Montar uma lista com a tabela Resumo
            tableResumo.setDefaultRenderer(Object.class, new ImagemTabela());
            tableResumo.setRowHeight(35);
            ((DefaultTableCellRenderer) tableResumo.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
             ((DefaultTableCellRenderer)tableResumo.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
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
    
    public void preencheTabelaResumo(int idNaoAbono) {
        TabelaResumoControl resumo = new TabelaResumoControl();
        ArrayList<TabelaResumoAlteracaoModel> listaResumoAlteracaoModel = new ArrayList();//Lista de objetos que será exibido na tabela
        FuncionarioControl f = new FuncionarioControl();
        
        String nome = ("" + tableAlteracoes.getValueAt(tableAlteracoes.getSelectedRow(), 1));
        int mat=f.getMat(nome);
        String referencia = ("" + tableAlteracoes.getValueAt(tableAlteracoes.getSelectedRow(), 0));

        listaResumoAlteracaoModel = resumo.listaResumoAnaliseAvaliadoresNaoAtualiza(mat, referencia, tipoLocalizacao, idNaoAbono);//cria a lista
        listaEventos = listaResumoAlteracaoModel;

        if (listaResumoAlteracaoModel != null) {
            TabelaResumoAvaliadores tabelaResumoAvaliadores = new TabelaResumoAvaliadores(listaResumoAlteracaoModel);//Montar uma lista com a tabela Resumo
            tableResumo.setDefaultRenderer(Object.class, new ImagemTabela());
            tableResumo.setRowHeight(35);
            ((DefaultTableCellRenderer) tableResumo.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
             ((DefaultTableCellRenderer)tableResumo.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
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

    public void preencheTabelaResumoInicial() {
        //ESSE MÉTODO SERVE APENAS PARA INICIAR A TABELA DE RESUMO COM O NOME DAS COLUNAS

        TabelaResumoControl resumo = new TabelaResumoControl();
        ArrayList<TabelaResumoAlteracaoModel> listaResumoAlteracaoModel = new ArrayList();//Lista de objetos que será exibido na tabela

        listaEventos = listaResumoAlteracaoModel;

        if (listaResumoAlteracaoModel != null) {
            TabelaResumoAvaliadores tabelaResumoAvaliadores = new TabelaResumoAvaliadores(listaResumoAlteracaoModel);//Montar uma lista com a tabela Resumo
            tableResumo.setDefaultRenderer(Object.class, new ImagemTabela());
            tableResumo.setRowHeight(35);
            //((DefaultTableCellRenderer)tabelaResumo.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
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

    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
        dispose();
    }//GEN-LAST:event_btn_close1MouseClicked

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

    }//GEN-LAST:event_formFocusGained

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        //preencheTabelaResumoTempoReal();// Este ,método atualiza a tabela em tempo real. Com isso, os eventos não abonados por aquele
        //nível somem. Eu quero que suma apenas para o próximo nível. Vou alterar a SQL
       int idNaoAbono=0;
       if (tipoLocalizacao.equals("Gerência")){
           idNaoAbono=2;
       }
       if (tipoLocalizacao.equals("Departamento")){
           idNaoAbono=3;
       }
       if (tipoLocalizacao.equals("Diretoria")){
           idNaoAbono=4;
       }
       if (tipoLocalizacao.equals("Presidência")){
           idNaoAbono=5;
       }
        
       System.out.println("IdNaoAbono: "+idNaoAbono);
        preencheTabelaResumo(idNaoAbono);
    }//GEN-LAST:event_formWindowActivated

    private void tableResumoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableResumoMouseClicked
        if (evt.getClickCount() == 2) {
            FuncionarioControl f = new FuncionarioControl();

            if ((tipoLocalizacao.equals("Diretoria")||(tipoLocalizacao.equals("Presidência")))) {
                int linha = (tableResumo.getSelectedRow());

                VisualizaItemAlteracaoModel item = new VisualizaItemAlteracaoModel();
                item.setDataEvento(listaEventos.get(linha).getDataEvento());
                item.setDescricao(listaEventos.get(linha).getDescricao());
                item.setReferencia(listaEventos.get(linha).getReferencia());
                item.setTipo(listaEventos.get(linha).getTipo());
                item.setIdRepositorio(listaEventos.get(linha).getId());
                String nome=("" + tableAlteracoes.getValueAt(tableAlteracoes.getSelectedRow(), 1));
                item.setMat(f.getMat(nome));
                item.setLocal(tipoLocalizacao);
                AnalisaSituacaoModel situacao = new AnalisaSituacaoModel();
                situacao.setIdRepositorio(listaEventos.get(linha).getId());
                situacao.setLocal(tipoLocalizacao);

                VisualizaEventosAlteracaoAvaliadoresFinal v = new VisualizaEventosAlteracaoAvaliadoresFinal(item, situacao);
                v.setVisible(true);

            } else {
                int linha = (tableResumo.getSelectedRow());

                VisualizaItemAlteracaoModel item = new VisualizaItemAlteracaoModel();
                item.setDataEvento(listaEventos.get(linha).getDataEvento());
                item.setDescricao(listaEventos.get(linha).getDescricao());
                item.setReferencia(listaEventos.get(linha).getReferencia());
                item.setTipo(listaEventos.get(linha).getTipo());
                item.setIdRepositorio(listaEventos.get(linha).getId());
                 String nome=("" + tableAlteracoes.getValueAt(tableAlteracoes.getSelectedRow(), 1));
                item.setMat(f.getMat(nome));                
                item.setLocal(tipoLocalizacao);
                AnalisaSituacaoModel situacao = new AnalisaSituacaoModel();
                situacao.setIdRepositorio(listaEventos.get(linha).getId());
                situacao.setLocal(tipoLocalizacao);

                VisualizaEventosAlteracaoAvaliadores v = new VisualizaEventosAlteracaoAvaliadores(item, situacao);
                v.setVisible(true);

            }

        }
    }//GEN-LAST:event_tableResumoMouseClicked

    private void tableAlteracoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAlteracoesMouseClicked
        if (evt.getClickCount() == 2) {
            //preencheTabelaResumoTempoReal();
             int idNaoAbono=0;
       if (tipoLocalizacao.equals("Gerência")){
           idNaoAbono=2;
       }
       if (tipoLocalizacao.equals("Departamento")){
           idNaoAbono=3;
       }
       if (tipoLocalizacao.equals("Diretoria")){
           idNaoAbono=4;
       }
       if (tipoLocalizacao.equals("Presidência")){
           idNaoAbono=5;
       }
            preencheTabelaResumo(idNaoAbono);
        }
    }//GEN-LAST:event_tableAlteracoesMouseClicked

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
            java.util.logging.Logger.getLogger(VisualizaAlteracaoFrequenciaAvaliadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizaAlteracaoFrequenciaAvaliadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizaAlteracaoFrequenciaAvaliadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizaAlteracaoFrequenciaAvaliadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizaAlteracaoFrequenciaAvaliadores(tipoLocalizacao, idLocalizacao).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_close1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable tableAlteracoes;
    private javax.swing.JTable tableResumo;
    // End of variables declaration//GEN-END:variables
}
