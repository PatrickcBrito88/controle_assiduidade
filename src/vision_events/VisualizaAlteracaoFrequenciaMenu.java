package vision_events;

import controllers.AlteracaoFrequenciaControl;
import controllers.FuncionarioControl;
import controllers.RepositorioControl;
import controllers.SetoresControl;
import controllers_tables.TabelaAnaliseControl;
import controllers_tables.TabelaResumoControl;
import email_.EnvioEmail;
import domain_enums.EnumSituacao;
import report_code.ImprimeRelatorio;
import tables.ImagemTabela;
import tables.TabelaAlteracoesAvaliadores;
import tables.TabelaResumo;
import tables.TabelaResumoAvaliadores;
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

public class VisualizaAlteracaoFrequenciaMenu extends javax.swing.JFrame {

    private static String tipoLocalizacao;
    private static int idLocalizacao;
    private static GestorVisualizaAlteracaoModel g;
    private static int idLocalNaoAbono = 0;

    public VisualizaAlteracaoFrequenciaMenu(String tipoLocalizacao, int idLocalizacao, GestorVisualizaAlteracaoModel g) {
        initComponents();
        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
        setaEstáticos(tipoLocalizacao, idLocalizacao, g);
        setCampos();
        System.out.println("TipoLocalização: " + tipoLocalizacao);

//        FadeEffect.transp(NovaAlteracaoFrequencia.this);
//        FadeEffect.fadeInFrame(this, 15, 0.5f);
    }

    private void setCampos() {
        labelCompetencia.setText(g.getReferencia());
        labelMatricula.setText(String.valueOf(g.getMat()));
        labelNome.setText(g.getNome());
    }

    public void setaEstáticos(String tipoLocalizacao, int idLocalizacao, GestorVisualizaAlteracaoModel g) {
        this.tipoLocalizacao = tipoLocalizacao;
        this.idLocalizacao = idLocalizacao;
        this.g = g;
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
        botaoReavaliar = new javax.swing.JLabel();
        botaoEdicao = new javax.swing.JLabel();

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
        jLabel11.setText("Análise de Eventos - Alteração de Frequência");
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

        botaoReavaliar.setBackground(new java.awt.Color(255, 255, 255));
        botaoReavaliar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botaoReavaliar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botaoReavaliar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/impressora.png"))); // NOI18N
        botaoReavaliar.setText("Imprimir Eventos");
        botaoReavaliar.setOpaque(true);
        botaoReavaliar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoReavaliarMouseClicked(evt);
            }
        });
        jPanel1.add(botaoReavaliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, 190, 40));

        botaoEdicao.setBackground(new java.awt.Color(255, 255, 255));
        botaoEdicao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botaoEdicao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botaoEdicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Editar.png"))); // NOI18N
        botaoEdicao.setText("Autorizar edição");
        botaoEdicao.setOpaque(true);
        botaoEdicao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoEdicaoMouseClicked(evt);
            }
        });
        jPanel1.add(botaoEdicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 200, 40));

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
//preencheTabelaResumoTempoReal();// Este ,método atualiza a tabela em tempo real. Com isso, os eventos não abonados por aquele
        //nível somem. Eu quero que suma apenas para o próximo nível. Vou alterar a SQL
        int idNaoAbono = 0;
        if (tipoLocalizacao.equals("Gerência")) {
            idNaoAbono = 2;
        }
        if (tipoLocalizacao.equals("Departamento")) {
            idNaoAbono = 3;
        }
        if (tipoLocalizacao.equals("Diretoria")) {
            idNaoAbono = 4;
        }
        if (tipoLocalizacao.equals("Presidência")) {
            idNaoAbono = 5;
        }

        System.out.println("IdNaoAbonoaqui: " + idNaoAbono);
        idLocalNaoAbono = idNaoAbono;
        preencheTabelaResumo(idNaoAbono);
    }//GEN-LAST:event_formWindowActivated

    public void preencheTabelaResumo(int idNaoAbono) {
        TabelaResumoControl resumo = new TabelaResumoControl();
        ArrayList<TabelaResumoAlteracaoModel> listaResumoAlteracaoModel = new ArrayList();//Lista de objetos que será exibido na tabela
        FuncionarioControl f = new FuncionarioControl();

        String nome = g.getNome();
        int mat = f.getMat(nome);
        String referencia = g.getReferencia();

        listaResumoAlteracaoModel = resumo.listaResumoAnaliseAvaliadoresNaoAtualiza(mat, referencia, tipoLocalizacao, idNaoAbono);//cria a lista
        listaEventos = listaResumoAlteracaoModel;
        System.out.println(listaEventos.get(0).getId());

        if (listaResumoAlteracaoModel != null) {
            TabelaResumoAvaliadores tabelaResumoAvaliadores = new TabelaResumoAvaliadores(listaResumoAlteracaoModel);//Montar uma lista com a tabela Resumo
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
            FuncionarioControl f = new FuncionarioControl();

            if ((tipoLocalizacao.equals("Diretoria") || (tipoLocalizacao.equals("Presidência")))) {
                int linha = (tableResumo.getSelectedRow());

                VisualizaItemAlteracaoModel item = new VisualizaItemAlteracaoModel();
                item.setDataEvento(listaEventos.get(linha).getDataEvento());
                item.setDescricao(listaEventos.get(linha).getDescricao());
                item.setReferencia(g.getReferencia());
                item.setTipo(listaEventos.get(linha).getTipo());
                item.setIdRepositorio(listaEventos.get(linha).getId());
                String nome = g.getNome();
                item.setMat(f.getMat(nome));
                item.setLocal(tipoLocalizacao);
                AnalisaSituacaoModel situacao = new AnalisaSituacaoModel();
                situacao.setIdRepositorio(listaEventos.get(linha).getId());
                situacao.setLocal(tipoLocalizacao);

                SetoresControl s = new SetoresControl();
                if ((s.verificaPossibilidadePermitirEdicao(tipoLocalizacao, listaEventos.get(linha).getId())) == true) {

                    VisualizaEventosAlteracaoAvaliadoresFinal v = new VisualizaEventosAlteracaoAvaliadoresFinal(item, situacao);
                    v.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Você já avaliou este evento, que agora encontra-se em fase de "
                            + "avaliação de sua chefia imediata!");
                }

            } else {
                int linha = (tableResumo.getSelectedRow());

                VisualizaItemAlteracaoModel item = new VisualizaItemAlteracaoModel();
                item.setDataEvento(listaEventos.get(linha).getDataEvento());
                item.setDescricao(listaEventos.get(linha).getDescricao());
                item.setReferencia(g.getReferencia());
                item.setTipo(listaEventos.get(linha).getTipo());
                item.setIdRepositorio(listaEventos.get(linha).getId());
                String nome = g.getNome();
                item.setMat(f.getMat(nome));
                item.setLocal(tipoLocalizacao);
                AnalisaSituacaoModel situacao = new AnalisaSituacaoModel();
                situacao.setIdRepositorio(listaEventos.get(linha).getId());
                situacao.setLocal(tipoLocalizacao);

                SetoresControl s = new SetoresControl();
                if ((s.verificaPossibilidadePermitirEdicao(tipoLocalizacao, listaEventos.get(linha).getId())) == true) {

                    VisualizaEventosAlteracaoAvaliadores v = new VisualizaEventosAlteracaoAvaliadores(item, situacao);
                    v.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Você já avaliou este evento, que agora encontra-se em fase de "
                            + "avaliação de sua chefia imediata!");
                }
            }
        }
    }//GEN-LAST:event_tableResumoMouseClicked

    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
        dispose();
    }//GEN-LAST:event_btn_close1MouseClicked

    private void botaoReavaliarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoReavaliarMouseClicked

        // O CODIGO COMENTADO REFERE-SE À FUNÇÃO ANTERIOR DESTE BOTÃO QUE ERA AVALIAR O EVENTO
        //COMO ESSA FUNÇÃO ESTÁ NO DUPLO CLIQUE DA TABELA, OPTEI POR TRANSFORMAR NA FUNÇÃO DE IMPRIMIR RELATÓRIO
//        FuncionarioControl f = new FuncionarioControl();
//
//        if ((tipoLocalizacao.equals("Diretoria") || (tipoLocalizacao.equals("Presidência")))) {
//            int linha = (tableResumo.getSelectedRow());
//
//            VisualizaItemAlteracaoModel item = new VisualizaItemAlteracaoModel();
//            item.setDataEvento(listaEventos.get(linha).getDataEvento());
//            item.setDescricao(listaEventos.get(linha).getDescricao());
//            item.setReferencia(g.getReferencia());
//            item.setTipo(listaEventos.get(linha).getTipo());
//            item.setIdRepositorio(listaEventos.get(linha).getId());
//            String nome = g.getNome();
//            item.setMat(f.getMat(nome));
//            item.setLocal(tipoLocalizacao);
//            AnalisaSituacaoModel situacao = new AnalisaSituacaoModel();
//            situacao.setIdRepositorio(listaEventos.get(linha).getId());
//            situacao.setLocal(tipoLocalizacao);
//
//           SetoresControl s = new SetoresControl();
//                if ((s.verificaPossibilidadePermitirEdicao(tipoLocalizacao, listaEventos.get(linha).getId())) == true) {
//
//                    VisualizaEventosAlteracaoAvaliadoresFinal v = new VisualizaEventosAlteracaoAvaliadoresFinal(item, situacao);
//                    v.setVisible(true);
//
//                } else {
//                    JOptionPane.showMessageDialog(null, "Você já avaliou este evento, que agora encontra-se em fase de "
//                            + "avaliação de sua chefia imediata!");
//                }
//
//        } else {
//            int linha = (tableResumo.getSelectedRow());
//
//            VisualizaItemAlteracaoModel item = new VisualizaItemAlteracaoModel();
//            item.setDataEvento(listaEventos.get(linha).getDataEvento());
//            item.setDescricao(listaEventos.get(linha).getDescricao());
//            item.setReferencia(g.getReferencia());
//            item.setTipo(listaEventos.get(linha).getTipo());
//            item.setIdRepositorio(listaEventos.get(linha).getId());
//            String nome = g.getNome();
//            item.setMat(f.getMat(nome));
//            item.setLocal(tipoLocalizacao);
//            AnalisaSituacaoModel situacao = new AnalisaSituacaoModel();
//            situacao.setIdRepositorio(listaEventos.get(linha).getId());
//            situacao.setLocal(tipoLocalizacao);
//
//            SetoresControl s = new SetoresControl();
//                if ((s.verificaPossibilidadePermitirEdicao(tipoLocalizacao, listaEventos.get(linha).getId())) == true) {
//
//                    VisualizaEventosAlteracaoAvaliadores v = new VisualizaEventosAlteracaoAvaliadores(item, situacao);
//                    v.setVisible(true);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Você já avaliou este evento, que agora encontra-se em fase de "
//                            + "avaliação de sua chefia imediata!");
//                }
//
//        }
        ImprimeRelatorio imprime = new ImprimeRelatorio();
        int mat=Integer.parseInt(labelMatricula.getText());
        String referencia=labelCompetencia.getText();
        FuncionarioControl f = new FuncionarioControl();
      
        String lotacao=f.getLotacao(mat);
        imprime.ImprimeEventosAlteracao(mat, referencia, lotacao, idLocalNaoAbono);


    }//GEN-LAST:event_botaoReavaliarMouseClicked

    private void botaoEdicaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoEdicaoMouseClicked
        RepositorioControl r = new RepositorioControl();
        int linha = (tableResumo.getSelectedRow());

        Object[] options = {"Sim", "Não"};
        int i = JOptionPane.showOptionDialog(null, "Deseja permitir que o funcionário edite o evento desta alteração de frequência ?",
                "Permitir edição", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (i == JOptionPane.YES_OPTION) {
            String referencia = labelCompetencia.getText();
            int mat = Integer.parseInt(labelMatricula.getText());
            AlteracaoFrequenciaControl a = new AlteracaoFrequenciaControl();
//            BackupModel b =a.CriaBackup(referencia, mat);
//            a.GravaBackupAlteracao(b);
            SetoresControl s = new SetoresControl();
            if ((s.verificaPossibilidadePermitirEdicao(tipoLocalizacao, listaEventos.get(linha).getId())) == true) {
                r.DestravaEvento(listaEventos.get(linha).getId());//Apenas tira a trava
                r.ResetaEvento(listaEventos.get(linha).getId());
                EnvioEmail email = new EnvioEmail();
                email.NotificaEventoLiberado(mat, referencia);
                AlteracaoFrequenciaControl ab = new AlteracaoFrequenciaControl();
                //ab.ResetarAlteracao(Integer.parseInt(labelMatricula.getText()), listaEventos.get(linha).getReferencia());
            } else {
                JOptionPane.showMessageDialog(null, "Você perdeu a condiçao de permitir a edição deste evento!");
            }
        }

    }//GEN-LAST:event_botaoEdicaoMouseClicked

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
            java.util.logging.Logger.getLogger(VisualizaAlteracaoFrequenciaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizaAlteracaoFrequenciaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizaAlteracaoFrequenciaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizaAlteracaoFrequenciaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizaAlteracaoFrequenciaMenu(tipoLocalizacao, idLocalizacao, g).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel botaoEdicao;
    private javax.swing.JLabel botaoReavaliar;
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
