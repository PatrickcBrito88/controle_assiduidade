package vision_events;

import controllers.AlteracaoFrequenciaControl;
import controllers.CalculaHierarquia;
import controllers.GetPDF;
import controllers.RepositorioControl;
import controllers.TiposControle;
import utils.CalculoMesesAnteriores;
import domain.CRUD.Crud_Alteracao;
import domain.CRUD.Crud_Repositorio;
import domain.Modelo.AFModel;
import domain.Modelo.HierarquiaModel;
import domain.Modelo.RepositorioModel;
import domain.Modelo.SetCampos.VisualizaItemAlteracaoModel;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class VisualizaEventosAlteracaoFuncionario extends javax.swing.JFrame {

    private static VisualizaItemAlteracaoModel item;

    public VisualizaEventosAlteracaoFuncionario(VisualizaItemAlteracaoModel item) {
        initComponents();
        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
        setVisualizaItem(item);
        geraCombo();
        formataData();

//        FadeEffect.transp(NovaAlteracaoFrequencia.this);
//        FadeEffect.fadeInFrame(this, 15, 0.5f);
        geraComboTipos();
        panelBarra.setVisible(false);
        geraComboDia();
        setCampos(item);
        motivo=tAreaDescricao.getText();
         labelCaracteres.setVisible(false);
        labelNumero.setVisible(false);
        labelNumero.setText("0");
        setNumeroCaracteres();
    }

    String motivo = "";
    int n = 0;
    
    boolean existe = false;

    FileInputStream fis = null;
    boolean arquivoAnexo = false;

    public void monitoraArquivoAnexo() {
        if (arquivoAnexo == true) {
            labelArquivoAnexo.setText("Arquivo anexado com sucesso!");
        }
    }

    public void setVisualizaItem(VisualizaItemAlteracaoModel visualizaItem) {
        this.item = visualizaItem;
    }
    
    public void geraComboReferencia() {
        cbBoxRef.removeAllItems();
        CalculoMesesAnteriores calculo = new CalculoMesesAnteriores();
        cbBoxRef.addItem(calculo.getMes());
        cbBoxRef.addItem(calculo.getMes_1());
        cbBoxRef.addItem(calculo.getMes_2());
    }

    public void geraCombo() {
        cbBoxRef.removeAllItems();
        geraComboReferencia();
        cbBoxRef.setSelectedItem(item.getReferencia());
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

    public void formataData() {
        MaskFormatter formData;
        try {
            formData = new MaskFormatter("##/##/####");

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar data.\n" + ex);
        }

    }

    public void setCampos(VisualizaItemAlteracaoModel item) {
        cbBoxRef.setSelectedItem(item.getReferencia());
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = formatador.parse(item.getDataEvento());
        } catch (ParseException ex) {
            Logger.getLogger(VisualizaEventosAlteracaoFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dia=String.valueOf(data.getDate());
      
        cBoxDia.setSelectedItem(dia);
        cBoxTipo.setSelectedItem(item.getTipo());
        tAreaDescricao.setText(item.getDescricao());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnnewuser2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tAreaDescricao = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnnewuser3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        cbBoxRef = new javax.swing.JComboBox<>();
        cBoxTipo = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        labelArquivoAnexo = new javax.swing.JLabel();
        panelBarra = new javax.swing.JPanel();
        barraProgresso = new javax.swing.JProgressBar();
        btnnewuser4 = new javax.swing.JLabel();
        cBoxDia = new javax.swing.JComboBox<>();
        labelNumero = new javax.swing.JLabel();
        labelCaracteres = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 610, 10));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Referência:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Descrição:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 40));

        btnnewuser2.setBackground(new java.awt.Color(0, 60, 113));
        btnnewuser2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser2.setForeground(new java.awt.Color(255, 255, 255));
        btnnewuser2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser2.setText("Confirmar e Sair");
        btnnewuser2.setOpaque(true);
        btnnewuser2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser2MouseClicked(evt);
            }
        });
        jPanel1.add(btnnewuser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, 170, 40));

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

        tAreaDescricao.setColumns(20);
        tAreaDescricao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tAreaDescricao.setLineWrap(true);
        tAreaDescricao.setRows(5);
        tAreaDescricao.setBorder(null);
        tAreaDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tAreaDescricaoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tAreaDescricao);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 490, 170));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Anexo.png"))); // NOI18N
        jLabel7.setText("Substituir Anexo");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, 170, 40));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 610, 10));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Dia do evento:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Análise - Alteração de Frequência");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, 30));

        btnnewuser3.setBackground(new java.awt.Color(160, 116, 0));
        btnnewuser3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser3.setForeground(new java.awt.Color(255, 255, 255));
        btnnewuser3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser3.setText("Deletar");
        btnnewuser3.setOpaque(true);
        btnnewuser3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser3MouseClicked(evt);
            }
        });
        jPanel1.add(btnnewuser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 130, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Tipo:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 50, 40));

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

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 80, -1));

        cbBoxRef.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbBoxRef.setLightWeightPopupEnabled(false);
        jPanel1.add(cbBoxRef, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 180, 30));

        cBoxTipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(cBoxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 180, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lupa.png"))); // NOI18N
        jLabel11.setText("Verificar Anexo");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, 170, 40));

        labelArquivoAnexo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel1.add(labelArquivoAnexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 170, 30));

        javax.swing.GroupLayout panelBarraLayout = new javax.swing.GroupLayout(panelBarra);
        panelBarra.setLayout(panelBarraLayout);
        panelBarraLayout.setHorizontalGroup(
            panelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraProgresso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );
        panelBarraLayout.setVerticalGroup(
            panelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraProgresso, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(panelBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 610, 40));

        btnnewuser4.setBackground(new java.awt.Color(255, 255, 255));
        btnnewuser4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser4.setForeground(new java.awt.Color(102, 102, 102));
        btnnewuser4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons8_trash_32px.png"))); // NOI18N
        btnnewuser4.setText("Apagar Anexo");
        btnnewuser4.setOpaque(true);
        btnnewuser4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser4MouseClicked(evt);
            }
        });
        jPanel1.add(btnnewuser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 170, 40));

        cBoxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cBoxDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 110, 30));

        labelNumero.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        labelNumero.setText("n");
        jPanel1.add(labelNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 20, -1));

        labelCaracteres.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        labelCaracteres.setText("Caracteres digitados");
        jPanel1.add(labelCaracteres, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 110, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Limite de 500 caracteres");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 120, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnnewuser2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser2MouseClicked
        //Objetos Model
        AFModel afModel = new AFModel();//preenche a tabela AlteraçãoFrequencia
        HierarquiaModel hierarquiaModel = new HierarquiaModel();// Ajuda a preencher a tabela AlteracaoFrequencia
        RepositorioModel repositorioModel = new RepositorioModel();//Preenche a tabela de RepositorioModel

        //Cruds
        Crud_Alteracao crudAlteracao = new Crud_Alteracao();//Inclusão no banco (tabela de alteração)
        Crud_Repositorio crudRepositorio = new Crud_Repositorio();//Inclusão no banco (tabela de repositorio)

        TiposControle tipoControle = new TiposControle();

        //Popular os objetos
        Date dataAtual = new Date(System.currentTimeMillis());
        afModel.setDataelaboracao(dataAtual);
        afModel.setMat(item.getMat());//Variável Global. Instanciar antes do setvisible=true
        afModel.setReferencia(String.valueOf(cbBoxRef.getSelectedItem()));

        afModel.setDepartamentoAvaliador(hierarquiaModel.getIdDepartamento());
        afModel.setDiretoriaAvaliadora(hierarquiaModel.getIdDiretoria());
        afModel.setGerenciaAvaliadora(hierarquiaModel.getIdGerencia());

        //Cadastrar o Repositório de Alterações
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

        if (dataValida == true) {
            repositorioModel.setDescricao(tAreaDescricao.getText());
            repositorioModel.setIdTipo(tipoControle.getIdTipo(String.valueOf(cBoxTipo.getSelectedItem())));
            repositorioModel.setMatFunc(item.getMat());
            repositorioModel.setAnexo(fis);
            repositorioModel.setReferencia(String.valueOf(cbBoxRef.getSelectedItem()));
            repositorioModel.setIdRepositorio(item.getIdRepositorio());

            //inserindo no banco
            if (repositorioModel.getAnexo() == null) {
                String referencia=String.valueOf(cbBoxRef.getSelectedItem());
                crudRepositorio.atualizaItemRepositorioSemAnexo(repositorioModel);
                
            } else {
               String referencia=String.valueOf(cbBoxRef.getSelectedItem());
                crudRepositorio.atualizaItemRepositorio(repositorioModel);
            }
            tAreaDescricao.setText("");

            dispose();
        }
    }//GEN-LAST:event_btnnewuser2MouseClicked


    private void btnnewuser3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser3MouseClicked

    }//GEN-LAST:event_btnnewuser3MouseClicked

    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
        dispose();
    }//GEN-LAST:event_btn_close1MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        GetPDF g = new GetPDF();

        panelBarra.setVisible(g.getArquivoPDF(item.getIdRepositorio(), barraProgresso));
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
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

                len = (int) file.length();
                arquivoAnexo = true;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NovaAlteracaoFrequencia.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        monitoraArquivoAnexo();
    }//GEN-LAST:event_formWindowActivated

    private void btnnewuser4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser4MouseClicked
        RepositorioControl r = new RepositorioControl();
        boolean sinal = r.verificaTrava(item.getIdRepositorio());
        if (sinal == false) {
            if (r.verificaAnexo(item.getIdRepositorio()) == false) {
                JOptionPane.showMessageDialog(null, "Este evento não possui anexo!");
            } else {
                r.apagaAnexo(item.getIdRepositorio());
                JOptionPane.showMessageDialog(null, "Anexo deletado com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você não pode deletar o anexo de um evento que "
                    + "encontra-se sob análise de sua chefia.");

        }

    }//GEN-LAST:event_btnnewuser4MouseClicked

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

     public int getNCarac(String texto) {
        return texto.length();
        
    }
     
     public void setNumeroCaracteres(){
         labelCaracteres.setVisible(true);
         labelNumero.setVisible(true);
         labelNumero.setText(String.valueOf(getNCarac(motivo)));
         System.out.println(getNCarac(motivo));
     }

     
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizaEventosAlteracaoFuncionario(item).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgresso;
    private javax.swing.JLabel btn_close1;
    private javax.swing.JLabel btnnewuser2;
    private javax.swing.JLabel btnnewuser3;
    private javax.swing.JLabel btnnewuser4;
    private javax.swing.JComboBox<String> cBoxDia;
    private javax.swing.JComboBox<String> cBoxTipo;
    private javax.swing.JComboBox<String> cbBoxRef;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelArquivoAnexo;
    private javax.swing.JLabel labelCaracteres;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JPanel panelBarra;
    private javax.swing.JTextArea tAreaDescricao;
    // End of variables declaration//GEN-END:variables
}
