package vision_events;

import controllers.AlteracaoFrequenciaControl;
import controllers.AnalisaEvento;
import controllers.AnalisarAlteracao;
import controllers.CalculaHierarquia;
import controllers.GetPDF;
import controllers.GetPerfil;
import controllers.RepositorioControl;
import controllers.SetoresControl;
import controllers.TiposControle;
import domain_enums.EnumSituacao;
import utils.CalculoMesesAnteriores;
import domain.CRUD.Crud_Alteracao;
import domain.CRUD.Crud_Repositorio;
import domain.Modelo.AFModel;
import domain.Modelo.AlteraStatusModel;
import domain.Modelo.AnalisaSituacaoModel;
import domain.Modelo.ControleStatusModel;
import domain.Modelo.HierarquiaModel;
import domain.Modelo.LocalIdModel;
import domain.Modelo.PerfilModel;
import domain.Modelo.RepositorioModel;
import domain.Modelo.SetCampos.VisualizaItemAlteracaoModel;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class VisualizaEventosAlteracaoAvaliadoresFinal extends javax.swing.JFrame {

    private static VisualizaItemAlteracaoModel item;
    private static AnalisaSituacaoModel situacao;

    public VisualizaEventosAlteracaoAvaliadoresFinal(VisualizaItemAlteracaoModel item, AnalisaSituacaoModel situacao) {
        initComponents();
        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
        setVisualizaItem(item);
        setVisualizaAnalise(situacao);
        geraCombo();
        formataData();
        panelBarra.setVisible(false);
//        FadeEffect.transp(NovaAlteracaoFrequencia.this);
//        FadeEffect.fadeInFrame(this, 15, 0.5f);
        setCampos(item);
        geraComboTipos();
    }

    FileInputStream fis = null;

    public void setVisualizaItem(VisualizaItemAlteracaoModel visualizaItem) {
        this.item = visualizaItem;
    }

    public void setVisualizaAnalise(AnalisaSituacaoModel situacao) {
        this.situacao = situacao;
    }

    public void geraCombo() {
        cbBoxRef.removeAllItems();
        cbBoxRef.addItem(item.getReferencia());
    }

    public void geraComboTipos() {
        cBoxTipo.removeAllItems();
        cBoxTipo.addItem(item.getTipo());
    }

    public void formataData() {
        MaskFormatter formData;
        try {
            formData = new MaskFormatter("##/##/####");
            txtData.setFormatterFactory(new DefaultFormatterFactory(formData));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar data.\n" + ex);
        }

    }

    public void setCampos(VisualizaItemAlteracaoModel item) {
        cbBoxRef.setSelectedItem(item.getReferencia());
        txtData.setText(String.valueOf(item.getDataEvento()));
        cBoxTipo.setSelectedItem(item.getTipo());
        tAreaDescricao.setText(item.getDescricao());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        botaoAceitar = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tAreaDescricao = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        botaoNaoAceitar = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        cbBoxRef = new javax.swing.JComboBox<>();
        cBoxTipo = new javax.swing.JComboBox<>();
        txtData = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        panelBarra = new javax.swing.JPanel();
        barraProgresso = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 110, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 610, 10));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Referência:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Descrição:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 40));

        botaoAceitar.setBackground(new java.awt.Color(0, 60, 113));
        botaoAceitar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botaoAceitar.setForeground(new java.awt.Color(255, 255, 255));
        botaoAceitar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botaoAceitar.setText("Abonar");
        botaoAceitar.setOpaque(true);
        botaoAceitar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoAceitarMouseClicked(evt);
            }
        });
        jPanel1.add(botaoAceitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, 170, 40));

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
        tAreaDescricao.setEnabled(false);
        jScrollPane1.setViewportView(tAreaDescricao);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 490, 200));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 620, 10));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Data do evento:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Análise - Alteração de Frequência");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, 30));

        botaoNaoAceitar.setBackground(new java.awt.Color(160, 116, 0));
        botaoNaoAceitar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botaoNaoAceitar.setForeground(new java.awt.Color(255, 255, 255));
        botaoNaoAceitar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botaoNaoAceitar.setText("Não Abonar");
        botaoNaoAceitar.setOpaque(true);
        botaoNaoAceitar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoNaoAceitarMouseClicked(evt);
            }
        });
        jPanel1.add(botaoNaoAceitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, 170, 40));

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

        txtData.setBorder(null);
        txtData.setText("     /       /");
        txtData.setEnabled(false);
        txtData.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtData, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 110, 30));

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
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, 170, 40));

        javax.swing.GroupLayout panelBarraLayout = new javax.swing.GroupLayout(panelBarra);
        panelBarra.setLayout(panelBarraLayout);
        panelBarraLayout.setHorizontalGroup(
            panelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraProgresso, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );
        panelBarraLayout.setVerticalGroup(
            panelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraProgresso, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(panelBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 620, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoAceitarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoAceitarMouseClicked

        AnalisarAlteracao analisar = new AnalisarAlteracao();
        AlteraStatusModel statusModel = new AlteraStatusModel();
        statusModel.setLocalizacao(item.getLocal());
        statusModel.setMat(item.getMat());
        statusModel.setReferencia(item.getReferencia());
        situacao.setSituacao(EnumSituacao.ABONADA);
        SetoresControl s = new SetoresControl();
        String nomeLocal = s.geNomeLocal(item.getMat());
//            PEGAR O TIPO DE LOCAL E O NOME DO LOCAL E PASSAR NO PARAMETRO METODO NO FINAL

        CalculaHierarquia c = new CalculaHierarquia();
        LocalIdModel l = new LocalIdModel();
        l = c.getIdLocalOriginal(item.getMat());
        String local = l.getLocal();
        AnalisaEvento analisa = new AnalisaEvento();
        PerfilModel p = new PerfilModel();
        GetPerfil g = new GetPerfil();

        p = g.preencherPerfil(item.getMat());
        analisa.tratamentoEvento(p, situacao);
        ControleStatusModel controle = new ControleStatusModel();
        controle = analisar.alteraSituacaoAlteracaoFrequencia(statusModel); //VERIFICA SE ESTÁ TUDO AVALIADO. SE ESTIVER ALTERA PARA ABONADO, NAO ABONADO OU PARCIALMENTE ABONADO

        //Aqui é em relação à alteração de frequência
        if (controle.isSinal() == true) {
            analisar.atualizaAlteracao(item.getMat(), item.getReferencia(), item.getLocal(), controle); //SE ESTIVER, ATUALIZA QUE NAQUELE NÍVEL ESTÁ TUDO ANALISADO

            analisar.PassaProximoNivel(local, nomeLocal, item.getMat(), item.getReferencia());
        }

        dispose();
    }//GEN-LAST:event_botaoAceitarMouseClicked


    private void botaoNaoAceitarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoNaoAceitarMouseClicked

        AnalisarAlteracao analisar = new AnalisarAlteracao();
        AlteraStatusModel statusModel = new AlteraStatusModel();

          RepositorioControl repositorio= new RepositorioControl();
        String texto=repositorio.getMotivo(item.getIdRepositorio());  
        System.out.println("Texto 2:"+texto);
        MotivoNaoAbono m = new MotivoNaoAbono(null, true, texto);
        m.setVisible(true);
        int nCarac = m.getN();

        if (nCarac <= 500) {
            String motivo = m.getMotivo();
            situacao.setMotivoNaoAbonado(motivo);

            statusModel.setLocalizacao(item.getLocal());
            statusModel.setMat(item.getMat());
            statusModel.setReferencia(item.getReferencia());
            situacao.setSituacao(EnumSituacao.NAO_ABONADA);
            SetoresControl s = new SetoresControl();
            String nomeLocal = s.geNomeLocal(item.getMat());
//            PEGAR O TIPO DE LOCAL E O NOME DO LOCAL E PASSAR NO PARAMETRO METODO NO FINAL

            CalculaHierarquia c = new CalculaHierarquia();
            LocalIdModel l = new LocalIdModel();
            l = c.getIdLocalOriginal(item.getMat());
            String local = l.getLocal();
            AnalisaEvento analisa = new AnalisaEvento();
            PerfilModel p = new PerfilModel();
            GetPerfil g = new GetPerfil();

            p = g.preencherPerfil(item.getMat());
            Object[] options = {"Sim", "Não"};
        int i = JOptionPane.showOptionDialog(null, "Confirma que o evento não será aceito ?",
               
                "Avaliação de evento", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (i == JOptionPane.YES_OPTION) {
            analisa.tratamentoEvento(p, situacao);
            ControleStatusModel controle = new ControleStatusModel();
            controle = analisar.alteraSituacaoAlteracaoFrequencia(statusModel); //VERIFICA SE ESTÁ TUDO AVALIADO. SE ESTIVER ALTERA PARA ABONADO, NAO ABONADO OU PARCIALMENTE ABONADO

            //Aqui é em relação à alteração de frequência
            if (controle.isSinal() == true) {
                analisar.atualizaAlteracao(item.getMat(), item.getReferencia(), item.getLocal(), controle); //SE ESTIVER, ATUALIZA QUE NAQUELE NÍVEL ESTÁ TUDO ANALISADO

                analisar.PassaProximoNivel(local, nomeLocal, item.getMat(), item.getReferencia());
            }

            dispose();
        }
        } else {
            JOptionPane.showMessageDialog(null, "Digite no máximo 500 caracteres.");
        }
    }//GEN-LAST:event_botaoNaoAceitarMouseClicked

    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
        dispose();
    }//GEN-LAST:event_btn_close1MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
         GetPDF g = new GetPDF();
        panelBarra.setVisible(g.getArquivoPDF(item.getIdRepositorio(), barraProgresso));
    }//GEN-LAST:event_jLabel11MouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizaEventosAlteracaoAvaliadoresFinal(item, situacao).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgresso;
    private javax.swing.JLabel botaoAceitar;
    private javax.swing.JLabel botaoNaoAceitar;
    private javax.swing.JLabel btn_close1;
    private javax.swing.JComboBox<String> cBoxTipo;
    private javax.swing.JComboBox<String> cbBoxRef;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel panelBarra;
    private javax.swing.JTextArea tAreaDescricao;
    private javax.swing.JFormattedTextField txtData;
    // End of variables declaration//GEN-END:variables
}
