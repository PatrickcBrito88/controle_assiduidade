
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
import domain.Model.ControleTabelas.TabelaResumoAlteracaoModel;
import LIB.FadeEffect;
import utils.CalculoMesesAnteriores;
import utils.ConversaoMeses;
import utils.EscolherArquivo;
import domain.Modelo.AFModel;
import domain.Modelo.RepositorioModel;
import utils.ManipularImagem;
import domain.Modelo.HierarquiaModel;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;



public class VisualizaAlteracaoFrequencia extends javax.swing.JFrame {

   private static int mat;
   private static String referencia;
   
    public VisualizaAlteracaoFrequencia(int matricula, String referencia) {
        initComponents();
        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
//        FadeEffect.transp(NovaAlteracaoFrequencia.this);
//        FadeEffect.fadeInFrame(this, 15, 0.5f);
        setMat(matricula);
        setReferencia(referencia);
        geraCombo(referencia);
        formataData(); 
        geraComboTipos();
        
       
        preencheTabelas(); //VERIFICAR SE A TABELA ESTARÁ VAZIA, SE ESTIVER NAO FAZ NADA
    }
    
    List<TabelaResumoAlteracaoModel> listaEventos = new ArrayList();
   FileInputStream fis=null;
    
    public void setMat (int matricula){
        this.mat=matricula;
    }
    
    public void setReferencia (String referencia){
        this.referencia=referencia;
    }
    
    public void formataData(){
        MaskFormatter formData;
        try {
            formData = new MaskFormatter("##/##/####");
            txtData.setFormatterFactory (new DefaultFormatterFactory(formData));
        } catch (ParseException ex) {
             JOptionPane.showMessageDialog(null,"Erro ao formatar data.\n"+ex);
        }
        
    }
        
    public void geraCombo(String referencia){
        cbBoxRef.removeAllItems();
       cbBoxRef.addItem(referencia);
    }
    
      
    public void geraComboTipos(){
        cBoxTipo.removeAllItems();
        TiposControle t = new TiposControle();
        ArrayList<String>lista = new ArrayList();
        lista=t.getListaTipos();
        
        for (int i=0;i<lista.size();i++){
            cBoxTipo.addItem(lista.get(i));
        }
    }
    
    public void preencheTabelas(){
        TabelaResumoControl resumo = new TabelaResumoControl();
        ArrayList<TabelaResumoAlteracaoModel> listaResumo = new ArrayList();
        listaResumo=resumo.listaResumo(mat, String.valueOf(cbBoxRef.getSelectedItem()));
        listaEventos=listaResumo; 
               
        if (listaResumo!=null){
        
        TabelaResumo tabelaResumo = new TabelaResumo(listaResumo);//Montar uma lista com a tabela Resumo
        
        tableResumo.setDefaultRenderer(Object.class, new ImagemTabela());
        tableResumo.setRowHeight(35);
        ((DefaultTableCellRenderer)tableResumo.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
         ((DefaultTableCellRenderer)tableResumo.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
        tableResumo.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tableResumo.getTableHeader().setOpaque(false);
        tableResumo.getTableHeader().setBackground(new Color (0,0,0));
        tableResumo.getTableHeader().setForeground(new Color (255,255,255));
        ImagemTabela img = new ImagemTabela();
                          
        tableResumo.setModel(tabelaResumo);
        } else {
           
            JOptionPane.showMessageDialog(null,"Você não possui eventos para esta referência!");
        }
        
        
    }

    BufferedImage image;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnnewuser2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        cBoxTipo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tAreaDescricao = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        cbBoxRef = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtData = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        btnnewuser3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableResumo = new javax.swing.JTable();
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
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 110, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 610, 10));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Referência:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Descrição:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 40));

        btnnewuser2.setBackground(new java.awt.Color(0, 60, 113));
        btnnewuser2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser2.setForeground(new java.awt.Color(255, 255, 255));
        btnnewuser2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser2.setText("Salvar");
        btnnewuser2.setOpaque(true);
        btnnewuser2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser2MouseClicked(evt);
            }
        });
        jPanel1.add(btnnewuser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, 130, 40));

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
        jScrollPane1.setViewportView(tAreaDescricao);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 490, 270));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Tipo:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, 40));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 660, 10));

        cbBoxRef.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbBoxRef.setLightWeightPopupEnabled(false);
        cbBoxRef.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbBoxRefItemStateChanged(evt);
            }
        });
        cbBoxRef.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbBoxRefFocusLost(evt);
            }
        });
        jPanel1.add(cbBoxRef, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 200, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Data do evento:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, 30));

        txtData.setBorder(null);
        txtData.setText("      /      /  ");
        txtData.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtData, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 110, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Inclusão - Alteração de Frequência");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, 30));

        btnnewuser3.setBackground(new java.awt.Color(160, 116, 0));
        btnnewuser3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnnewuser3.setForeground(new java.awt.Color(255, 255, 255));
        btnnewuser3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnnewuser3.setText("Anexar Arquivo");
        btnnewuser3.setOpaque(true);
        btnnewuser3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnnewuser3MouseClicked(evt);
            }
        });
        jPanel1.add(btnnewuser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, 130, 40));

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

        tableResumo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
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
        jScrollPane2.setViewportView(tableResumo);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 97, 360, 370));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Quadro Resumo");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, 30));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 380, 10));
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnnewuser2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser2MouseClicked
       //Objetos Model
       AFModel afModel = new AFModel();//preenche a tabela AlteraçãoFrequencia
       HierarquiaModel hierarquiaModel = new HierarquiaModel();// Ajuda a preencher a tabela AlteracaoFrequencia
       RepositorioModel repositorioModel = new RepositorioModel();//Preenche a tabela de RepositorioModel
       
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
       hierarquiaModel=calcularHierarquia.getHierarquiaFuncionario(mat);
       afModel.setDepartamentoAvaliador(hierarquiaModel.getIdDepartamento());
       afModel.setDiretoriaAvaliadora(hierarquiaModel.getIdDiretoria());
       afModel.setGerenciaAvaliadora(hierarquiaModel.getIdGerencia());
              
       //inserindo no banco
       if (aF.verificaAF(afModel.getReferencia(), mat)==false){//Verifica se existe registro no banco. Se existir, não salva
       crudAlteracao.CadastraAlteracao(afModel);
       }
       
       //Cadastrar o Repositório de Alterações
       SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
       java.util.Date date;
        String dataSql = txtData.getText();
        try {
            date = formatador.parse(dataSql);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            repositorioModel.setDataEvento(sqlDate);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null,"Botão 2 - Salvar - Conversor de datas.\n"+ex);
        }

       repositorioModel.setDescricao(tAreaDescricao.getText());
       repositorioModel.setIdTipo(tipoControle.getIdTipo(String.valueOf(cBoxTipo.getSelectedItem())));
       repositorioModel.setMatFunc(mat);
       repositorioModel.setAnexo(fis);
       repositorioModel.setReferencia(String.valueOf(cbBoxRef.getSelectedItem()));
       
       //inserindo no banco
       crudRepositorio.InsereRepositorio(repositorioModel);
       tAreaDescricao.setText("");
       txtData.setText("");
       preencheTabelas();
       
    }//GEN-LAST:event_btnnewuser2MouseClicked

     
    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
        dispose();
    }//GEN-LAST:event_btn_close1MouseClicked

    private void tableResumoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableResumoMouseClicked
          if(evt.getClickCount() == 2){
         int linha=(tableResumo.getSelectedRow());
         
         VisualizaItemAlteracaoModel item = new VisualizaItemAlteracaoModel();
         item.setDataEvento(listaEventos.get(linha).getDataEvento());
         item.setDescricao(listaEventos.get(linha).getDescricao());
         item.setReferencia(listaEventos.get(linha).getReferencia());
         item.setTipo(listaEventos.get(linha).getTipo());
         item.setIdRepositorio(listaEventos.get(linha).getId());
         item.setMat(mat);
         
         VisualizaEventosAlteracaoFuncionario f = new VisualizaEventosAlteracaoFuncionario(item);
         f.setVisible(true);
          
        }      
        //FAZER ALGO PARA PEGAR A LINHA, PREENCHER O OBJETO E JOGAR PARA A TELA PARA O FUNCIONÁRIO ANALISAR 
                
    }//GEN-LAST:event_tableResumoMouseClicked

    private void btnnewuser3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnewuser3MouseClicked
//     //        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter(
////       ".pdf", ".jpeg", ".png", ".docx");
//        
//        JFileChooser fc = new JFileChooser();
//        
////        fc.setFileFilter(fileNameExtensionFilter);
//        
//        fc.setDialogTitle("Escolhendo arquivo");
//        
//        int resposta=fc.showOpenDialog(null);
//         
//        if (resposta == JFileChooser.APPROVE_OPTION){
//            
//            File file = new File (fc.getSelectedFile().getAbsolutePath());
//            try {
//               fileInput=new FileInputStream(file);
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(EscolherArquivo.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } 

JFileChooser fc = new JFileChooser();
        
//        fc.setFileFilter(fileNameExtensionFilter);
        int len;
        fc.setDialogTitle("Escolhendo arquivo");
        
        int resposta=fc.showOpenDialog(null);
         
        if (resposta == JFileChooser.APPROVE_OPTION){
            
            File file = new File (fc.getSelectedFile().getAbsolutePath());

         try {
        
             fis = new FileInputStream(file);
        
             len = (int)file.length();
         } catch (FileNotFoundException ex) {
              Logger.getLogger(VisualizaAlteracaoFrequencia.class.getName()).log(Level.SEVERE, null, ex);
        
         }
        
        }  
         
             
    }//GEN-LAST:event_btnnewuser3MouseClicked

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
      
    }//GEN-LAST:event_formFocusGained

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
      preencheTabelas();
    }//GEN-LAST:event_formWindowActivated

    private void cbBoxRefFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbBoxRefFocusLost
       preencheTabelas();
    }//GEN-LAST:event_cbBoxRefFocusLost

    private void cbBoxRefItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbBoxRefItemStateChanged
        preencheTabelas();
    }//GEN-LAST:event_cbBoxRefItemStateChanged

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
            java.util.logging.Logger.getLogger(VisualizaAlteracaoFrequencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizaAlteracaoFrequencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizaAlteracaoFrequencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizaAlteracaoFrequencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizaAlteracaoFrequencia(mat, referencia).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_close1;
    private javax.swing.JLabel btnnewuser2;
    private javax.swing.JLabel btnnewuser3;
    private javax.swing.JComboBox<String> cBoxTipo;
    private javax.swing.JComboBox<String> cbBoxRef;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextArea tAreaDescricao;
    private javax.swing.JTable tableResumo;
    private javax.swing.JFormattedTextField txtData;
    // End of variables declaration//GEN-END:variables
}
