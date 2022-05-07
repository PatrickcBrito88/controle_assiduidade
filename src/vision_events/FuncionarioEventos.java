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

public class FuncionarioEventos extends javax.swing.JFrame {

    private static VisualizaItemAlteracaoModel item;

    public FuncionarioEventos(VisualizaItemAlteracaoModel item) {
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
        cBoxDia.setEnabled(false);
        cBoxTipo.setEnabled(false);
        cbBoxRef.setEnabled(false);
        tAreaDescricao.setEnabled(false);
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

    public void geraCombo() {
        cbBoxRef.removeAllItems();
        cbBoxRef.addItem(item.getReferencia());
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
            Logger.getLogger(FuncionarioEventos.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tAreaDescricao = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        cbBoxRef = new javax.swing.JComboBox<>();
        cBoxTipo = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        labelArquivoAnexo = new javax.swing.JLabel();
        panelBarra = new javax.swing.JPanel();
        barraProgresso = new javax.swing.JProgressBar();
        cBoxDia = new javax.swing.JComboBox<>();

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
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 610, 10));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Dia do evento:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Análise - Alteração de Frequência");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, 30));

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
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, 170, 40));

        labelArquivoAnexo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel1.add(labelArquivoAnexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 170, 30));

        javax.swing.GroupLayout panelBarraLayout = new javax.swing.GroupLayout(panelBarra);
        panelBarra.setLayout(panelBarraLayout);
        panelBarraLayout.setHorizontalGroup(
            panelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBarraLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(barraProgresso, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBarraLayout.setVerticalGroup(
            panelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBarraLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(barraProgresso, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(panelBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 610, 40));

        cBoxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cBoxDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 110, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
        dispose();
    }//GEN-LAST:event_btn_close1MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        GetPDF g = new GetPDF();

        panelBarra.setVisible(g.getArquivoPDF(item.getIdRepositorio(), barraProgresso));
    }//GEN-LAST:event_jLabel11MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        monitoraArquivoAnexo();
    }//GEN-LAST:event_formWindowActivated

    private void tAreaDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tAreaDescricaoKeyPressed
         
       
    }//GEN-LAST:event_tAreaDescricaoKeyPressed

     public int getNCarac(String texto) {
        return texto.length();
        
    }
     
   
     
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FuncionarioEventos(item).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgresso;
    private javax.swing.JLabel btn_close1;
    private javax.swing.JComboBox<String> cBoxDia;
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelArquivoAnexo;
    private javax.swing.JPanel panelBarra;
    private javax.swing.JTextArea tAreaDescricao;
    // End of variables declaration//GEN-END:variables
}
