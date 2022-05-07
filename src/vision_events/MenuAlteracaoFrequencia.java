package vision_events;

import report_code.ImprimeRelatorio;
import infrastructure_dto.VisaoTabelaDto;
import controllers.AlteracaoFrequenciaControl;
import tables.ImagemTabela;

import utils.ConectaBanco;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import tables.TabelaAlteracoes;
import controllers.SituacaoAFControl;
import control_tables_dto.ConverteVisaoTabelaDto;
import controllers.FuncionarioControl;
import controllers.GetPerfil;
import utils.BarraProgressoLenta;
import vision_error_sugestions.VisaoErro;
import vision_error_sugestions.VisaoSugestao;
import domain.Modelo.PerfilModel;
import domain.Modelo.SituacaoAFModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuAlteracaoFrequencia extends javax.swing.JFrame {

    private static int mat;

    public MenuAlteracaoFrequencia(int id) {

        initComponents();
        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
        mat = id;

        preencheTabela();

        setaCampos();

    }

    ConectaBanco conecta = new ConectaBanco();

    public void setaCampos() {
        FuncionarioControl funcionarioControle = new FuncionarioControl();
        txtNome.setText(funcionarioControle.getNome(mat));
        txtMat.setText(String.valueOf(mat));
        txtLocal.setText(funcionarioControle.getLocalizacao(mat));
    }

    public void preencheTabela() {

        SituacaoAFModel situacao = new SituacaoAFModel();
        SituacaoAFControl situacaoAFControl = new SituacaoAFControl();
        ArrayList<SituacaoAFModel> listaAFModel = new ArrayList();
//         JOptionPane.showMessageDialog(null,"a.1");
        listaAFModel = situacaoAFControl.getSituacao(mat);
//         JOptionPane.showMessageDialog(null,"b.1");
        ArrayList<VisaoTabelaDto> listaDTO = new ArrayList();
//         JOptionPane.showMessageDialog(null,"c.1");
        ConverteVisaoTabelaDto conversor = new ConverteVisaoTabelaDto();
//         JOptionPane.showMessageDialog(null,"d.1");
        VisaoTabelaDto objeto = new VisaoTabelaDto();
//         JOptionPane.showMessageDialog(null,"e.1");
        for (int i = 0; i < listaAFModel.size(); i++) {
//             JOptionPane.showMessageDialog(null,"f.1");
            objeto = conversor.getVisaoTabelaDto(listaAFModel.get(i));
//             JOptionPane.showMessageDialog(null,"g.1");
            listaDTO.add(objeto);
//             JOptionPane.showMessageDialog(null,"h.1");
        }

        //criar uma lista de visaoModel e converter para VisaoDTO
        if (listaDTO != null) {
            PerfilModel p = new PerfilModel();
            GetPerfil g = new GetPerfil();
            p=g.preencherPerfil(mat);
//             JOptionPane.showMessageDialog(null,"i");
            TabelaAlteracoes tabelaRepositorio = new TabelaAlteracoes(listaDTO,p);//lista de visaomodel

            tabelaAlteracao.setDefaultRenderer(Object.class, new ImagemTabela());
            tabelaAlteracao.setRowHeight(35);
            ((DefaultTableCellRenderer) tabelaAlteracao.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            tabelaAlteracao.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
            tabelaAlteracao.getTableHeader().setOpaque(false);
            tabelaAlteracao.getTableHeader().setBackground(new Color(0, 0, 0));
            tabelaAlteracao.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();
//             JOptionPane.showMessageDialog(null,"j");
            tabelaAlteracao.setModel(tabelaRepositorio);
        } else {
//             JOptionPane.showMessageDialog(null,"l");
            JOptionPane.showMessageDialog(null, "Você não possui alterações de frequência cadastradas!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel77 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNome = new LIB.JTexfieldPH_FielTex();
        jLabel6 = new javax.swing.JLabel();
        txtLocal = new LIB.JTexfieldPH_FielTex();
        txtMat = new LIB.JTexfieldPH_FielTex();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAlteracao = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        barraProgresso = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(160, 116, 0));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 80));

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

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, 40));

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
                .addGap(0, 134, Short.MAX_VALUE)
                .addComponent(btn_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_close1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 160, -1));

        jPanel2.setBackground(new java.awt.Color(160, 116, 0));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Erro.png"))); // NOI18N
        jLabel15.setText("Reportar Erros");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 30));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 140, 30));

        jPanel10.setBackground(new java.awt.Color(160, 116, 22));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Gerenciar Alteração de Frequência");
        jLabel9.setPreferredSize(new java.awt.Dimension(195, 18));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jLabel9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel9KeyPressed(evt);
            }
        });
        jPanel10.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 60));

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 260, 57));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 96, 226, 34));

        jPanel8.setBackground(new java.awt.Color(160, 116, 22));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Emitir Relatório Final");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 60));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 260, 57));

        jPanel77.setBackground(new java.awt.Color(255, 255, 255));
        jPanel77.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Sugestao.png"))); // NOI18N
        jLabel16.setText("Sugestões");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel77.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 30));

        jPanel2.add(jPanel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 140, 30));

        jPanel11.setBackground(new java.awt.Color(160, 116, 22));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Apagar Alteração de Frequência");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel11.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 60));

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 260, 57));

        jPanel16.setBackground(new java.awt.Color(160, 116, 22));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Emitir Espelho da Alteração de Frequência");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jPanel16.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 60));

        jPanel2.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 260, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 280, 550));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(0, 60, 113));
        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nome:");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        txtNome.setEditable(false);
        txtNome.setBackground(new java.awt.Color(0, 60, 113));
        txtNome.setBorder(null);
        txtNome.setForeground(new java.awt.Color(204, 204, 204));
        txtNome.setText("Nome do Funcionário");
        txtNome.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNome.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNome.setPhColor(new java.awt.Color(255, 255, 255));
        txtNome.setPlaceholder("Nome do funcionário");
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        jPanel7.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 380, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Lotação:");
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 40));

        txtLocal.setEditable(false);
        txtLocal.setBackground(new java.awt.Color(0, 60, 113));
        txtLocal.setBorder(null);
        txtLocal.setForeground(new java.awt.Color(204, 204, 204));
        txtLocal.setText("Local");
        txtLocal.setCaretColor(new java.awt.Color(0, 60, 113));
        txtLocal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtLocal.setPhColor(new java.awt.Color(255, 255, 255));
        txtLocal.setPlaceholder("Local");
        txtLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocalActionPerformed(evt);
            }
        });
        jPanel7.add(txtLocal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 220, 40));

        txtMat.setEditable(false);
        txtMat.setBackground(new java.awt.Color(0, 60, 113));
        txtMat.setBorder(null);
        txtMat.setForeground(new java.awt.Color(204, 204, 204));
        txtMat.setText("000");
        txtMat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMat.setPhColor(new java.awt.Color(255, 255, 255));
        txtMat.setPlaceholder("000");
        txtMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatActionPerformed(evt);
            }
        });
        jPanel7.add(txtMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 40, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Mat.:");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, -1, 40));

        tabelaAlteracao.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaAlteracao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaAlteracaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaAlteracao);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 710, 530));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Alteração de Frequência");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 10, 680, -1));
        jPanel1.add(barraProgresso, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 630, 690, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatActionPerformed

    private void txtLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void jLabel9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel9KeyPressed

    }//GEN-LAST:event_jLabel9KeyPressed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        int matricula = (Integer.parseInt(txtMat.getText()));
        String lotacao = txtLocal.getText();
        NovaAlteracaoFrequencia n = new NovaAlteracaoFrequencia(matricula, lotacao);
        n.setVisible(true);
        
        

    }//GEN-LAST:event_jLabel9MouseClicked

    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_btn_close1MouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        preencheTabela();
    }//GEN-LAST:event_formWindowGainedFocus

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        AlteracaoFrequenciaControl a = new AlteracaoFrequenciaControl();
        String referencia = "";
        boolean sinal = true;
        try {
            referencia = "" + tabelaAlteracao.getValueAt(tabelaAlteracao.getSelectedRow(), 0);
            System.out.println(referencia);
        } catch (Exception ex) {
            sinal = false;
            JOptionPane.showMessageDialog(null, "Você deve selecionar uma alteração de frequência no"
                    + " quadro ao lado para que exibido o relatório!.");
        }

        if (sinal == true) {
            int mat = Integer.parseInt(txtMat.getText());
            String lotacao = txtLocal.getText();

            if (a.verificaConclusaoAlteracao(referencia, mat) == true) {
//        barraProgresso.setVisible(true);
                BarraProgressoLenta barra = new BarraProgressoLenta(barraProgresso);
                barra.load();

                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MenuAlteracaoFrequencia.class.getName()).log(Level.SEVERE, null, ex);
                }

                ImprimeRelatorio imprime = new ImprimeRelatorio();
                imprime.ImprimeRelatorioFinal(mat, referencia, lotacao);
            } else {
                JOptionPane.showMessageDialog(null, "Esta alteração de frequência ainda não encontra-se disponível "
                        + "para emissão de relatório!");
            }
        }
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        AlteracaoFrequenciaControl a = new AlteracaoFrequenciaControl();
        boolean sinal = true;
        String referencia = "";
        try {
            referencia = "" + tabelaAlteracao.getValueAt(tabelaAlteracao.getSelectedRow(), 0);
            System.out.println(referencia);
        } catch (Exception ex) {
            sinal = false;
            JOptionPane.showMessageDialog(null, "Você deve selecionar uma alteração de frequência no"
                    + " quadro ao lado para que seja deletada.");
        }

        if (sinal == true) {
            int mat = Integer.parseInt(txtMat.getText());
            Object[] options = {"Sim", "Não"};
            int i = JOptionPane.showOptionDialog(null, "Tem certeza que deseja deletar a alteração de "
                    + "frequência com referencia em " + referencia + "?",
                    "Deletar Alteração de Frequência", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (i == JOptionPane.YES_OPTION) {
                a.DeletaAlteracao(mat, referencia);
            }
        }

    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        AlteracaoFrequenciaControl a = new AlteracaoFrequenciaControl();
        String referencia = "";
        boolean sinal = true;
        try {
            referencia = "" + tabelaAlteracao.getValueAt(tabelaAlteracao.getSelectedRow(), 0);
            System.out.println(referencia);
        } catch (Exception ex) {
            sinal = false;
            JOptionPane.showMessageDialog(null, "Você deve selecionar uma alteração de frequência no"
                    + " quadro ao lado para que exibido o relatório!.");
        }

        if (sinal == true) {
            int mat = Integer.parseInt(txtMat.getText());
            String lotacao = txtLocal.getText();

            if (a.VerificaEventoEnviado(referencia, mat) == true) {
//        barraProgresso.setVisible(true);
                BarraProgressoLenta barra = new BarraProgressoLenta(barraProgresso);
                barra.load();

                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MenuAlteracaoFrequencia.class.getName()).log(Level.SEVERE, null, ex);
                }

                ImprimeRelatorio imprime = new ImprimeRelatorio();
                imprime.ImprimeEspelho(mat, referencia, lotacao);
            } else {
                JOptionPane.showMessageDialog(null, "Você ainda não enviou nenhum evento desta "
                        + "Alteração de Frequência para avaliação.");
            }
        }
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        VisaoErro v = new VisaoErro(Integer.parseInt(txtMat.getText()));
        v.setVisible(true);

    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        VisaoSugestao s = new VisaoSugestao(mat);
        s.setVisible(true);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void tabelaAlteracaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaAlteracaoMouseClicked
        //        if (evt.getClickCount() == 2) {
//            String referencia = "" + tabelaAlteracao.getValueAt(tabelaAlteracao.getSelectedRow(), 0);
//            int mat = Integer.parseInt(txtMat.getText());
//            int matricula = (Integer.parseInt(txtMat.getText()));
//            String lotacao = txtLocal.getText();
//            NovaAlteracaoFrequencia n = new NovaAlteracaoFrequencia(matricula, lotacao);
//            n.setVisible(true);
        if (evt.getClickCount() == 2) {
            String nome = txtNome.getText();
            String referencia = "" + tabelaAlteracao.getValueAt(tabelaAlteracao.getSelectedRow(), 0);
            FuncionarioVisualizaAlteracao f = new FuncionarioVisualizaAlteracao(nome, referencia, mat);
            f.setVisible(true);

            //COMENTADO ABAIXO PORQUE O NOVA ALTERAÇÃO FAZ A MESMA COISA
//            VisualizaAlteracaoFrequencia v = new VisualizaAlteracaoFrequencia(mat, referencia);
//            v.setVisible(true);
        }
    }//GEN-LAST:event_tabelaAlteracaoMouseClicked

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
            java.util.logging.Logger.getLogger(MenuAlteracaoFrequencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuAlteracaoFrequencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuAlteracaoFrequencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuAlteracaoFrequencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuAlteracaoFrequencia(mat).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgresso;
    private javax.swing.JLabel btn_close1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tabelaAlteracao;
    private LIB.JTexfieldPH_FielTex txtLocal;
    private LIB.JTexfieldPH_FielTex txtMat;
    private LIB.JTexfieldPH_FielTex txtNome;
    // End of variables declaration//GEN-END:variables
}
