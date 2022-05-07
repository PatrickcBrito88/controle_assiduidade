package vision_events;

import tables.ImagemTabela;
import tables.TabelaAlteracoes;
import tables.TabelaResumo;
import domain.CRUD.Crud_Alteracao;
import domain.CRUD.Crud_Repositorio;
import controllers.AlteracaoFrequenciaControl;
import controllers_tables.TabelaResumoControl;
import controllers.TiposControle;
import controllers.CalculaHierarquia;
import controllers.GetPDF;
import controllers.GetPerfil;
import controllers.RepositorioControl;
import email_.EnvioEmail;
import domain.Model.ControleTabelas.TabelaResumoAlteracaoModel;
import LIB.FadeEffect;
import report_code.ImprimeRelatorio;
import utils.CalculoMesesAnteriores;
import utils.ConversaoMeses;
import utils.EscolherArquivo;
import domain.Modelo.AFModel;
import domain.Modelo.RepositorioModel;
import utils.ManipularImagem;
import domain.Modelo.BackupModel;
import domain.Modelo.HierarquiaModel;
import domain.Modelo.PerfilModel;
import domain.Modelo.SetCampos.VisualizaItemAlteracaoModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class NovaAlteracaoFrequencia extends javax.swing.JFrame {

    private static int mat;
    private static String lotacao;

    String motivo = "";
    int n = 0;

    public NovaAlteracaoFrequencia(int matricula, String lotacao) {
        initComponents();
        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
//        FadeEffect.transp(NovaAlteracaoFrequencia.this);
//        FadeEffect.fadeInFrame(this, 15, 0.5f);
        setMat(matricula);
        setLotacao(lotacao);
        geraCombo();
//        formataData();
        geraComboTipos();
        geraComboDia();
        labelCaracteres.setVisible(false);
        labelNumero.setVisible(false);
        labelNumero.setText("0");
        preencheTabelas(); //VERIFICAR SE A TABELA ESTARÁ VAZIA, SE ESTIVER NAO FAZ NADA
    }

    List<TabelaResumoAlteracaoModel> listaEventos = new ArrayList();
    FileInputStream fis = null;
    boolean arquivoAnexo = false;

    public void monitoraArquivoAnexo() {
        if (arquivoAnexo == true) {
            labelArquivoAnexo.setText("Arquivo anexado com sucesso!");
        }
    }

    public void setLotacao (String lotacao){
        this.lotacao=lotacao;
    }
    
    public void setMat(int matricula) {
        this.mat = matricula;
    }

//    public void formataData() {
//        MaskFormatter formData;
//        try {
//            formData = new MaskFormatter("##/##/####");
//            txtData.setFormatterFactory(new DefaultFormatterFactory(formData));
//        } catch (ParseException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao formatar data.\n" + ex);
//        }
//    }
    public void geraCombo() {
        cbBoxRef.removeAllItems();
        CalculoMesesAnteriores calculo = new CalculoMesesAnteriores();
        cbBoxRef.addItem(calculo.getMes());
        cbBoxRef.addItem(calculo.getMes_1());
        cbBoxRef.addItem(calculo.getMes_2());
    }

    public void geraComboDia() {
        cBoxDia.removeAllItems();
        int dia = 0;
        String mes = String.valueOf(cbBoxRef.getSelectedItem());

        if ((mes.contains("JANEIRO")) || (mes.contains("MARÇO")) || (mes.contains("MAIO")) || (mes.contains("JULHO"))
                || (mes.contains("AGOSTO")) || (mes.contains("OUTUBRO")) || (mes.contains("DEZEMBRO"))) {
            for (dia = 1; dia <= 31; dia++) {
                cBoxDia.addItem(String.valueOf(dia));
            }
        }

        if ((mes.contains("ABRIL")) || (mes.contains("JUNHO")) || (mes.contains("SETEMBRO")) || (mes.contains("NOVEMBRO"))) {
            for (dia = 1; dia <= 30; dia++) {
                cBoxDia.addItem(String.valueOf(dia));
            }
        }

        if ((mes.contains("FEVEREIRO"))) {
            for (dia = 1; dia <= 29; dia++) {
                cBoxDia.addItem(String.valueOf(dia));
            }
        }
        BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();
        UIResource.setHorizontalAlignment(SwingConstants.CENTER);
        cBoxDia.setRenderer(UIResource);
    }

    public void geraComboTipos() {
        cBoxTipo.removeAllItems();
        TiposControle t = new TiposControle();
        ArrayList<String> lista = new ArrayList();
        lista = t.getListaTipos();

        for (int i = 0; i < lista.size(); i++) {
            cBoxTipo.addItem(lista.get(i));
        }
    }

    public void preencheTabelas() {
        TabelaResumoControl resumo = new TabelaResumoControl();
        ArrayList<TabelaResumoAlteracaoModel> listaResumo = new ArrayList();
        listaResumo = resumo.listaResumo(mat, String.valueOf(cbBoxRef.getSelectedItem()));
        listaEventos = listaResumo;

        if (listaResumo != null) {

            TabelaResumo tabelaResumo = new TabelaResumo(listaResumo);//Montar uma lista com a tabela Resumo

            tabelaResumoEventos.setDefaultRenderer(Object.class, new ImagemTabela());
            tabelaResumoEventos.setRowHeight(35);
            ((DefaultTableCellRenderer) tabelaResumoEventos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer) tabelaResumoEventos.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tabelaResumoEventos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tabelaResumoEventos.getTableHeader().setOpaque(false);
            tabelaResumoEventos.getTableHeader().setBackground(new Color(0, 0, 0));
            tabelaResumoEventos.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tabelaResumoEventos.setModel(tabelaResumo);
        } else {

            JOptionPane.showMessageDialog(null, "Você não possui eventos para esta referência!");
        }

    }

    BufferedImage image;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        cBoxTipo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tAreaDescricao = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        cbBoxRef = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnnewuser3 = new javax.swing.JLabel();
        labelArquivoAnexo = new javax.swing.JLabel();
        btnnewuser4 = new javax.swing.JLabel();
        cBoxDia = new javax.swing.JComboBox<>();
        btnnewuser5 = new javax.swing.JLabel();
        labelNumero = new javax.swing.JLabel();
        labelCaracteres = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnnewuser6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaResumoEventos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();

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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Referência:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Descrição:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 40));

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

        cBoxTipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(cBoxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 250, 30));

        tAreaDescricao.setColumns(20);
        tAreaDescricao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tAreaDescricao.setLineWrap(true);
        tAreaDescricao.setRows(5);
        tAreaDescricao.setBorder(null);
        tAreaDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tAreaDescricaoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tAreaDescricao);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 490, 230));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Tipo:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, 40));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 660, 10));

        cbBoxRef.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbBoxRef.setLightWeightPopupEnabled(false);
        cbBoxRef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBoxRefActionPerformed(evt);
            }
        });
        jPanel1.add(cbBoxRef, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 200, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Dia do evento:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Inclusão - Alteração de Frequência");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, 30));

        btnnewuser3.setBackground(new java.awt.Color(255, 255, 255));
        btnnewuser3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons8_trash_32px.png"))); // NOI18N
        btnnewuser3.setText("Apagar Anexo");
        btnnewuser3.setOpaque(true);
        btnnewuser3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser3MouseClicked(evt);
            }
        });
        jPanel1.add(btnnewuser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 170, 40));

        labelArquivoAnexo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel1.add(labelArquivoAnexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 390, 180, 40));

        btnnewuser4.setBackground(new java.awt.Color(160, 116, 0));
        btnnewuser4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser4.setForeground(new java.awt.Color(255, 255, 255));
        btnnewuser4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser4.setText("Apagar Evento");
        btnnewuser4.setOpaque(true);
        btnnewuser4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnnewuser4MouseEntered(evt);
            }
        });
        jPanel1.add(btnnewuser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 130, 40));

        cBoxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cBoxDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 110, 30));

        btnnewuser5.setBackground(new java.awt.Color(255, 255, 255));
        btnnewuser5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Anexo.png"))); // NOI18N
        btnnewuser5.setText("Anexar Arquivo");
        btnnewuser5.setOpaque(true);
        btnnewuser5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser5MouseClicked(evt);
            }
        });
        jPanel1.add(btnnewuser5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, 170, 40));

        labelNumero.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        labelNumero.setText("n");
        jPanel1.add(labelNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 30, -1));

        labelCaracteres.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        labelCaracteres.setText("Caracteres digitados");
        jPanel1.add(labelCaracteres, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 110, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Limite de 500 caracteres");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 120, 20));

        btnnewuser6.setBackground(new java.awt.Color(0, 60, 113));
        btnnewuser6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser6.setForeground(new java.awt.Color(255, 255, 255));
        btnnewuser6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser6.setText("Salvar");
        btnnewuser6.setOpaque(true);
        btnnewuser6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser6MouseClicked(evt);
            }
        });
        jPanel1.add(btnnewuser6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, 130, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/enviarAviao.png"))); // NOI18N
        jLabel1.setText("ENVIAR");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 430, 140, 40));

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

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 80, -1));

        tabelaResumoEventos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabelaResumoEventos.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaResumoEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaResumoEventosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaResumoEventos);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 97, 360, 370));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Quadro Resumo");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, 30));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 380, 10));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 380, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
        dispose();
    }//GEN-LAST:event_btn_close1MouseClicked

    private void tabelaResumoEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaResumoEventosMouseClicked
        if (evt.getClickCount() == 2) {
            int linha = (tabelaResumoEventos.getSelectedRow());

            VisualizaItemAlteracaoModel item = new VisualizaItemAlteracaoModel();
            item.setDataEvento(listaEventos.get(linha).getDataEvento());
            item.setDescricao(listaEventos.get(linha).getDescricao());
            item.setReferencia(listaEventos.get(linha).getReferencia());
            item.setTipo(listaEventos.get(linha).getTipo());
            item.setIdRepositorio(listaEventos.get(linha).getId());
            item.setTrava(listaEventos.get(linha).isTrava());
            item.setMat(mat);

            if (item.isTrava() == false) {
                VisualizaEventosAlteracaoFuncionario f = new VisualizaEventosAlteracaoFuncionario(item);
                f.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Este item já foi enviado para avaliação e apenas"
                        + " pode ser alterado mediante autorização do seu gestor.", "Impossibilidade de edição", HEIGHT);

            }

        }
        //FAZER ALGO PARA PEGAR A LINHA, PREENCHER O OBJETO E JOGAR PARA A TELA PARA O FUNCIONÁRIO ANALISAR 

    }//GEN-LAST:event_tabelaResumoEventosMouseClicked

    private void btnnewuser3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser3MouseClicked

        int linha = (tabelaResumoEventos.getSelectedRow());
        int id = (listaEventos.get(linha).getId());
        RepositorioControl r = new RepositorioControl();
        boolean sinal = r.verificaTrava(id);
        if (sinal == false) {
            r.apagaAnexo(id);
            preencheTabelas();
        } else {
            JOptionPane.showMessageDialog(null, "Você não pode deletar o anexo de um evento que "
                    + "encontra-se sob análise de sua chefia.");

        }

    }//GEN-LAST:event_btnnewuser3MouseClicked

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

    }//GEN-LAST:event_formFocusGained

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        preencheTabelas();
        monitoraArquivoAnexo();
    }//GEN-LAST:event_formWindowActivated

    private void btnnewuser4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser4MouseClicked
           Object[] options = {"Sim", "Não"};
        int i = JOptionPane.showOptionDialog(null, "Tem certeza que deseja deletar este evento ?",
                "Envio para aprovação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (i == JOptionPane.YES_OPTION) {
        
        int linha = (tabelaResumoEventos.getSelectedRow());
        boolean travado = listaEventos.get(linha).isTrava();
        
        if (travado == false) {
             int id=(listaEventos.get(linha).getId());
            VisualizaItemAlteracaoModel item = new VisualizaItemAlteracaoModel();
           
            Crud_Repositorio cR = new Crud_Repositorio();
            cR.apagaEvento(id);
            preencheTabelas();
            } else {
                JOptionPane.showMessageDialog(null, "Este item já foi enviado para avaliação e apenas"
                        + " pode ser deletado mediante autorização do seu gestor.", "Impossibilidade de edição", HEIGHT);

            }
        }
         
    }//GEN-LAST:event_btnnewuser4MouseClicked

    private void cbBoxRefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBoxRefActionPerformed
        preencheTabelas();
        geraComboDia();
    }//GEN-LAST:event_cbBoxRefActionPerformed

    private void btnnewuser5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser5MouseClicked
        JFileChooser fc = new JFileChooser();

        fc.setFileFilter(new FileNameExtensionFilter("Arquivos PDF", "pdf"));
        fc.setAcceptAllFileFilterUsed(false);

        int len;
        fc.setDialogTitle("Escolhendo arquivo");

        int resposta = fc.showOpenDialog(null);

        if (resposta == JFileChooser.APPROVE_OPTION) {

            File file = new File(fc.getSelectedFile().getAbsolutePath());

            try {

                fis = new FileInputStream(file);
                arquivoAnexo = true;
                len = (int) file.length();
               
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NovaAlteracaoFrequencia.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }//GEN-LAST:event_btnnewuser5MouseClicked

    private void tAreaDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tAreaDescricaoKeyPressed
        labelCaracteres.setVisible(true);
        labelNumero.setVisible(true);
        int n = getNCarac(tAreaDescricao.getText());
        motivo = tAreaDescricao.getText();
        if (n > 500) {
            labelNumero.setForeground(Color.RED);
        }
        if (n <= 500) {
            labelNumero.setForeground(Color.BLACK);
        }

        labelNumero.setText(String.valueOf(n));
    }//GEN-LAST:event_tAreaDescricaoKeyPressed

    private void btnnewuser6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser6MouseClicked
        String campoDescricao=tAreaDescricao.getText();
        
        if (!campoDescricao.equals("")){
            
        //Objetos Model
        AFModel afModel = new AFModel();//preenche a tabela AlteraçãoFrequencia
        HierarquiaModel hierarquiaModel = new HierarquiaModel();// Ajuda a preencher a tabela AlteracaoFrequencia
        RepositorioModel repositorioModel = new RepositorioModel();//Preenche a tabela de RepositorioModel

        boolean sinal = true;
        //Calcular Hierarquia
        CalculaHierarquia calcularHierarquia = new CalculaHierarquia();

        //Cruds
        Crud_Alteracao crudAlteracao = new Crud_Alteracao();//Inclusão no banco (tabela de alteração)
        Crud_Repositorio crudRepositorio = new Crud_Repositorio();//Inclusão no banco (tabela de repositorio)

        //Verificar se existe algo gravado
        AlteracaoFrequenciaControl aF = new AlteracaoFrequenciaControl();

        TiposControle tipoControle = new TiposControle();

        //Popular os objetos
        Date dataAtual = new Date(System.currentTimeMillis());
        afModel.setDataelaboracao(dataAtual);
        afModel.setMat(mat);//Variável Global. Instanciar antes do setvisible=true
        afModel.setReferencia(String.valueOf(cbBoxRef.getSelectedItem()));
        hierarquiaModel = calcularHierarquia.getHierarquiaFuncionarioAvaliacao(mat);
        afModel.setDepartamentoAvaliador(hierarquiaModel.getIdDepartamento());
        afModel.setDiretoriaAvaliadora(hierarquiaModel.getIdDiretoria());
        afModel.setGerenciaAvaliadora(hierarquiaModel.getIdGerencia());
        afModel.setPresidenciaAvaliadora(hierarquiaModel.getIdPresidencia());

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date;
        CalculoMesesAnteriores c = new CalculoMesesAnteriores();
        int dia = Integer.parseInt(String.valueOf(cBoxDia.getSelectedItem()));
        String mesEAno = String.valueOf(cbBoxRef.getSelectedItem());
        String data = c.ConverteData(dia, mesEAno);
        String dataSql = data;
        System.out.println(data);
        boolean dataValida = true;
        try {
            date = formatador.parse(dataSql);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            repositorioModel.setDataEvento(sqlDate);
        } catch (ParseException ex) {
            dataValida = false;
            JOptionPane.showMessageDialog(null, "Antes de salvar, informe a data do evento!");
        }

        //inserindo no banco
        if (dataValida == true) {
            if (aF.verificaAF(afModel.getReferencia(), mat) == false) {//Verifica se existe registro no banco. Se existir, não salva
                crudAlteracao.CadastraAlteracao(afModel);
            }

            //Cadastrar o Repositório de Alterações
            int numero = getNCarac(motivo);
            System.out.println(numero);
            if (numero <= 499) {
                motivo = tAreaDescricao.getText();
                } else {
                sinal = false;
                
            }

            repositorioModel.setDescricao(tAreaDescricao.getText());
            repositorioModel.setIdTipo(tipoControle.getIdTipo(String.valueOf(cBoxTipo.getSelectedItem())));
            repositorioModel.setMatFunc(mat);
            repositorioModel.setAnexo(fis);
            repositorioModel.setReferencia(String.valueOf(cbBoxRef.getSelectedItem()));

            //Calculando situação dos itens
            repositorioModel.setIdGerencia(afModel.getGerenciaAvaliadora());
            repositorioModel.setIdDepartamento(afModel.getDepartamentoAvaliador());
            repositorioModel.setIdDiretoria(afModel.getDiretoriaAvaliadora());

            if (sinal == true) {
                //inserindo no banco
                crudRepositorio.InsereRepositorio(repositorioModel);
                tAreaDescricao.setText("");
                cBoxDia.setSelectedItem("1");
                labelArquivoAnexo.setText("");
                 labelNumero.setText("0");
                 labelArquivoAnexo.setText("");
                preencheTabelas();
                arquivoAnexo=false;
            } else {
                JOptionPane.showMessageDialog(null,"Limite sua Descrição a 500 caracteres!");
            }
        }
        } else {
            JOptionPane.showMessageDialog(null,"O campo de descrição está vazio");
        }
    }//GEN-LAST:event_btnnewuser6MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       AlteracaoFrequenciaControl a = new AlteracaoFrequenciaControl();
        String referencia=String.valueOf(cbBoxRef.getSelectedItem());
       if (a.TodosItensEnviados(referencia, mat)==false){
        preencheTabelas();
        RepositorioControl r = new RepositorioControl();
        String referencia2 = String.valueOf(cbBoxRef.getSelectedItem());
        Object[] options = {"Sim", "Não"};
        int i = JOptionPane.showOptionDialog(null, "Após enviar os itens para avaliação, apenas será possível editar com a autorização dos seus gestores.\n"
                + "Confirma o envio?",
                "Envio para aprovação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (i == JOptionPane.YES_OPTION) {
            Crud_Repositorio c = new Crud_Repositorio();
            c.ReenviaEvento(mat, referencia2);//Reenvia os evetos para o caso em que eles são abertos para edição
            r.EnviaEventos(referencia2, mat);//Altera a trava
            AlteracaoFrequenciaControl aF = new AlteracaoFrequenciaControl();
            //Dentro do método alteraStatus tem o método resetaalteração. Toda vez que um evento é criado ou alterado, a alteração é resetada
            aF.alteraStatus(referencia2, mat);
            EnvioEmail e = new EnvioEmail();
            e.CriacaoAlteracaoFrequencia(referencia2, mat);
            aF.NotificaSetor(mat);
            //Enviar email para o setor que fará a análise inicial
            int j = JOptionPane.showOptionDialog(null, "Deseja imprimir o Espelho da Alteração de Frequência ?",
                "Impressão de Relatório", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (j == JOptionPane.YES_OPTION) {
             ImprimeRelatorio imprime = new ImprimeRelatorio();
                imprime.ImprimeEspelho(mat, referencia2, lotacao);
        }
            
            //Painel de Teste
            PerfilModel p = new PerfilModel();
            GetPerfil g = new GetPerfil();
            p = g.preencherPerfil(mat);
            System.out.println("Func Gerencia: " + p.isFuncionarioGerencia());
            System.out.println("Gerente: " + p.isGerente());
            System.out.println("Chefe: " + p.isChefe());
            System.out.println("Func Depto: " + p.isFuncionarioDpto());
            System.out.println("Func Gercom " + p.isFuncionarioGercom());
            System.out.println("Gerente Gercom: " + p.isGerenteGercom());
            System.out.println("Func Diretoria: " + p.isFuncionarioDiretoria());
            System.out.println("Diretor: " + p.isDiretor());
            System.out.println("Func. DEPCI: " + p.isFuncionarioDpci());
            System.out.println("Chefe DEPCI: " + p.isChefeDepci());

        }
       } else {
           //Fiz esses metodos de backup pq achei que ao permitir edição do evento, o sistema zerasse a alteração, mas não, 
           //ele altera quando o evento é reenviado
//           AlteracaoFrequenciaControl aFc = new AlteracaoFrequenciaControl();
//           BackupModel b =  new BackupModel();
//           b= aFc.BuscaBackup(referencia, mat);
//           aFc.RestauraBackup(b);
           JOptionPane.showMessageDialog(null,"Todos os eventos já foram enviados para avaliação.");
       }

    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnnewuser4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnewuser4MouseEntered

    public int getNCarac(String texto) {
        return texto.length();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NovaAlteracaoFrequencia(mat, lotacao).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_close1;
    private javax.swing.JLabel btnnewuser3;
    private javax.swing.JLabel btnnewuser4;
    private javax.swing.JLabel btnnewuser5;
    private javax.swing.JLabel btnnewuser6;
    private javax.swing.JComboBox<String> cBoxDia;
    private javax.swing.JComboBox<String> cBoxTipo;
    private javax.swing.JComboBox<String> cbBoxRef;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel labelArquivoAnexo;
    private javax.swing.JLabel labelCaracteres;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JTextArea tAreaDescricao;
    private javax.swing.JTable tabelaResumoEventos;
    // End of variables declaration//GEN-END:variables
}
