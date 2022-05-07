package vision_menus;

import controllers.DepartamentoControl;
import controllers.DiretoriaControl;
import controllers.GerenciaControl;
import controllers.NotificacaoControl;
import controllers.PresidenciaControl;
import controllers.TelaMenuControl;
import controllers_tables.AlteracoesAvaliarControl;
import controllers_tables.TabelaAnaliseControl;
import controllers_tables.TabelaFuncionarioControl;
import tables.ImagemTabela;
import tables.TabelaAlteracaoAvaliar;
import tables.TabelaAlteracoesAvaliadores;
import tables.TabelaFuncionario;
import vision_events.VisualizaAlteracaoFrequenciaAvaliadores;
import vision_events.VisualizaAlteracaoFrequenciaMenu;
import domain.Model.ControleTabelas.AlteracoesAvaliarMenuModel;
import domain.Model.ControleTabelas.FuncionarioTabelaModel;
import domain.Model.ControleTabelas.TabelaAnaliseModel;
import domain.Modelo.GestorVisualizaAlteracaoModel;
import domain.Modelo.NotificacaoModel;
import domain.Modelo.TelaMenuModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class MenuSetores extends javax.swing.JFrame {

    private static TelaMenuModel telaModel;
    private static String local;
    private static String sigla;
    private static ArrayList<NotificacaoModel> n;
    boolean avaliadas = false;
    boolean avaliar = false;
    boolean funcionarios = false;

    public MenuSetores(TelaMenuModel telaModel, String local, String sigla, ArrayList<NotificacaoModel> n) {
        initComponents();
        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
        this.telaModel = telaModel;
        this.local = local;
        this.sigla = sigla;
        this.n = n;
        setCampos();
        preencheNotificacao(n);
        panelMenu.setVisible(false);
    }

    public void setCampos() {
        labelAlteracoesAvaliadas.setText(String.valueOf(telaModel.getAlteracoesAvaliadas()));
        labelAlteracoesAvaliar.setText(String.valueOf(telaModel.getAlteracoesAvaliar()));
        labelTotalFuncionarios.setText(String.valueOf(telaModel.getTotalFuncionarios()));
        labelSetor.setText(telaModel.getSetor());
    }

    public void preencheNotificacao(ArrayList<NotificacaoModel> n) {
        panel1.setVisible(false);
        panel2.setVisible(false);
        panel3.setVisible(false);

        int tamanho = n.size();

        if (tamanho >= 1) {
            panel1.setVisible(true);
            labelNomePanel1.setText(n.get(0).getNome());
            labelMatriculaPanel1.setText("Matrícula: " + String.valueOf(n.get(0).getMatricula()));
            labelReferenciaPanel1.setText(n.get(0).getReferencia());

        }

        if (tamanho >= 2) {
            panel1.setVisible(true);
            labelNomePanel1.setText(n.get(0).getNome());
            labelMatriculaPanel1.setText("Matrícula: " + String.valueOf(n.get(0).getMatricula()));
            labelReferenciaPanel1.setText(n.get(0).getReferencia());
            panel2.setVisible(true);
            labelNomePanel2.setText(n.get(1).getNome());
            labelMatriculaPanel2.setText("Matrícula: " + String.valueOf(n.get(1).getMatricula()));
            labelReferenciaPanel2.setText(n.get(1).getReferencia());

        }

        if (tamanho >= 3) {
            panel1.setVisible(true);
            labelNomePanel1.setText(n.get(0).getNome());
            labelMatriculaPanel1.setText("Matrícula: " + String.valueOf(n.get(0).getMatricula()));
            labelReferenciaPanel1.setText(n.get(0).getReferencia());
            panel2.setVisible(true);
            labelNomePanel2.setText(n.get(1).getNome());
            labelMatriculaPanel2.setText("Matrícula: " + String.valueOf(n.get(1).getMatricula()));
            labelReferenciaPanel2.setText(n.get(1).getReferencia());
            panel3.setVisible(true);
            labelNomePanel3.setText(n.get(2).getNome());
            labelMatriculaPanel3.setText("Matrícula: " + String.valueOf(n.get(2).getMatricula()));
            labelReferenciaPanel3.setText(n.get(2).getReferencia());

        }

    }

    public boolean preencheTabelaAlteracoesAvaliar() {
        panelMenu.setVisible(true);
        labelTitulo.setText("ALTERAÇÕES PARA AVALIAR");
        AlteracoesAvaliarControl alteracoesAvaliar = new AlteracoesAvaliarControl();
        ArrayList<AlteracoesAvaliarMenuModel> listaAlteracoesAvaliar = new ArrayList();
        listaAlteracoesAvaliar = alteracoesAvaliar.pegaListaAlteracoesAvaliar(local, sigla);
        //listaEventos=listaResumo; 

        boolean sinal = listaAlteracoesAvaliar.isEmpty();

        if (!listaAlteracoesAvaliar.isEmpty()) {

            TabelaAlteracaoAvaliar tabelaAlteracao = new TabelaAlteracaoAvaliar(listaAlteracoesAvaliar);//Montar uma lista com a tabela Resumo

            tableMenu.setDefaultRenderer(Object.class, new ImagemTabela());
            tableMenu.setRowHeight(35);
            ((DefaultTableCellRenderer) tableMenu.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
             ((DefaultTableCellRenderer) tableMenu.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableMenu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableMenu.getTableHeader().setOpaque(false);
            tableMenu.getTableHeader().setBackground(new Color(0, 0, 0));
            tableMenu.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tableMenu.setModel(tabelaAlteracao);
        } else {
            panelMenu.setVisible(false);
            JOptionPane.showMessageDialog(null, "Você não possui Alterações de Frequência para avaliar!");

        }
        return sinal;
    }

    public boolean preencheTabelaAlteracoesAvaliadas() {
        panelMenu.setVisible(true);
        labelTitulo.setText("ALTERAÇÕES AVALIADAS");
        AlteracoesAvaliarControl alteracoesAvaliar = new AlteracoesAvaliarControl();
        ArrayList<AlteracoesAvaliarMenuModel> listaAlteracoesAvaliar = new ArrayList();
        listaAlteracoesAvaliar = alteracoesAvaliar.pegaListaAlteracoesAvaliadas(local, sigla);
        //listaEventos=listaResumo; 

        boolean sinal = listaAlteracoesAvaliar.isEmpty();

        if (!listaAlteracoesAvaliar.isEmpty()) {

            TabelaAlteracaoAvaliar tabelaAlteracao = new TabelaAlteracaoAvaliar(listaAlteracoesAvaliar);//Montar uma lista com a tabela Resumo

            tableMenu.setDefaultRenderer(Object.class, new ImagemTabela());
            tableMenu.setRowHeight(35);
            ((DefaultTableCellRenderer) tableMenu.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
             ((DefaultTableCellRenderer) tableMenu.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableMenu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableMenu.getTableHeader().setOpaque(false);
            tableMenu.getTableHeader().setBackground(new Color(0, 0, 0));
            tableMenu.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tableMenu.setModel(tabelaAlteracao);
        } else {
            panelMenu.setVisible(false);
            JOptionPane.showMessageDialog(null, "Você não possui Alterações de Frequência avaliadas!");

        }
        return sinal;
    }

    public boolean preencheTabelaFuncionarios() {
        panelMenu.setVisible(true);
        labelTitulo.setText("FUNCIONÁRIOS DESTA LOTAÇÃO");
        TabelaFuncionarioControl tabelaFuncionarioControl = new TabelaFuncionarioControl();
        ArrayList<FuncionarioTabelaModel> listaFuncionarios = new ArrayList();
        listaFuncionarios = tabelaFuncionarioControl.getLista(local, sigla);
        //listaEventos=listaResumo; 

        boolean sinal = listaFuncionarios.isEmpty();

        if (!listaFuncionarios.isEmpty()) {

            TabelaFuncionario tabelaFuncionario = new TabelaFuncionario(listaFuncionarios);//Montar uma lista com a tabela Resumo

            tableMenu.setDefaultRenderer(Object.class, new ImagemTabela());
            tableMenu.setRowHeight(35);
            ((DefaultTableCellRenderer) tableMenu.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer) tableMenu.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableMenu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableMenu.getTableHeader().setOpaque(false);
            tableMenu.getTableHeader().setBackground(new Color(0, 0, 0));
            tableMenu.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tableMenu.setModel(tabelaFuncionario);
        } else {
            panelMenu.setVisible(false);
            JOptionPane.showMessageDialog(null, "Você não possui funcionários nesta lotação!!");

        }
        return sinal;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Button2 = new javax.swing.JPanel();
        Indicator2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        Button5 = new javax.swing.JPanel();
        Indicator5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        Button6 = new javax.swing.JPanel();
        Indicator6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelSetor = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelAlteracoesAvaliar = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        labelAlteracoesAvaliadas = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        labelTotalFuncionarios = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btn_close = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        labelNomePanel1 = new javax.swing.JLabel();
        labelReferenciaPanel1 = new javax.swing.JLabel();
        labelMatriculaPanel1 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        labelNomePanel2 = new javax.swing.JLabel();
        labelReferenciaPanel2 = new javax.swing.JLabel();
        labelMatriculaPanel2 = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        labelNomePanel3 = new javax.swing.JLabel();
        labelReferenciaPanel3 = new javax.swing.JLabel();
        labelMatriculaPanel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMenu = new javax.swing.JTable();
        labelTitulo = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Button2.setBackground(new java.awt.Color(255, 255, 255));
        Button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Button2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Button2MouseExited(evt);
            }
        });

        Indicator2.setBackground(new java.awt.Color(204, 0, 204));
        Indicator2.setOpaque(false);

        javax.swing.GroupLayout Indicator2Layout = new javax.swing.GroupLayout(Indicator2);
        Indicator2.setLayout(Indicator2Layout);
        Indicator2Layout.setHorizontalGroup(
            Indicator2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        Indicator2Layout.setVerticalGroup(
            Indicator2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );

        jLabel13.setBackground(new java.awt.Color(0, 60, 113));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(96, 83, 150));
        jLabel13.setText("Alterar E-mail");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Button2Layout = new javax.swing.GroupLayout(Button2);
        Button2.setLayout(Button2Layout);
        Button2Layout.setHorizontalGroup(
            Button2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Button2Layout.createSequentialGroup()
                .addComponent(Indicator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addGap(0, 138, Short.MAX_VALUE))
        );
        Button2Layout.setVerticalGroup(
            Button2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Indicator2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Button2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(Button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 232, -1));

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

        Button5.setBackground(new java.awt.Color(255, 255, 255));
        Button5.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        Button5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Button5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Button5MouseExited(evt);
            }
        });

        Indicator5.setBackground(new java.awt.Color(204, 0, 204));
        Indicator5.setOpaque(false);

        javax.swing.GroupLayout Indicator5Layout = new javax.swing.GroupLayout(Indicator5);
        Indicator5.setLayout(Indicator5Layout);
        Indicator5Layout.setHorizontalGroup(
            Indicator5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        Indicator5Layout.setVerticalGroup(
            Indicator5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );

        jLabel24.setBackground(new java.awt.Color(0, 60, 113));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(96, 83, 150));
        jLabel24.setText("Alterar Senha");
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Button5Layout = new javax.swing.GroupLayout(Button5);
        Button5.setLayout(Button5Layout);
        Button5Layout.setHorizontalGroup(
            Button5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Button5Layout.createSequentialGroup()
                .addComponent(Indicator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addGap(0, 138, Short.MAX_VALUE))
        );
        Button5Layout.setVerticalGroup(
            Button5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Indicator5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Button5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(Button5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 227, 232, -1));

        Button6.setBackground(new java.awt.Color(255, 255, 255));
        Button6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Button6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Button6MouseExited(evt);
            }
        });

        Indicator6.setBackground(new java.awt.Color(204, 0, 204));
        Indicator6.setOpaque(false);

        javax.swing.GroupLayout Indicator6Layout = new javax.swing.GroupLayout(Indicator6);
        Indicator6.setLayout(Indicator6Layout);
        Indicator6Layout.setHorizontalGroup(
            Indicator6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        Indicator6Layout.setVerticalGroup(
            Indicator6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Button6Layout = new javax.swing.GroupLayout(Button6);
        Button6.setLayout(Button6Layout);
        Button6Layout.setHorizontalGroup(
            Button6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Button6Layout.createSequentialGroup()
                .addComponent(Indicator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 176, Short.MAX_VALUE))
        );
        Button6Layout.setVerticalGroup(
            Button6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Indicator6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(Button6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 530));

        jPanel2.setBackground(new java.awt.Color(247, 247, 247));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelSetor.setBackground(new java.awt.Color(0, 60, 113));
        labelSetor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelSetor.setForeground(new java.awt.Color(96, 83, 150));
        labelSetor.setText("Setor xxxxxxxxxxxxxxxxxxxxxxxx");
        jPanel2.add(labelSetor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 23, 470, 37));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        labelAlteracoesAvaliar.setBackground(new java.awt.Color(0, 60, 113));
        labelAlteracoesAvaliar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelAlteracoesAvaliar.setForeground(new java.awt.Color(96, 83, 150));
        labelAlteracoesAvaliar.setText("500");

        jLabel6.setBackground(new java.awt.Color(0, 60, 113));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(96, 83, 150));
        jLabel6.setText("Para avaliar");

        jPanel11.setBackground(new java.awt.Color(160, 116, 0));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelAlteracoesAvaliar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(labelAlteracoesAvaliar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelAlteracoesAvaliadas.setBackground(new java.awt.Color(0, 60, 113));
        labelAlteracoesAvaliadas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelAlteracoesAvaliadas.setForeground(new java.awt.Color(96, 83, 150));
        labelAlteracoesAvaliadas.setText("50");
        jPanel4.add(labelAlteracoesAvaliadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 22, -1, -1));

        jPanel10.setBackground(new java.awt.Color(160, 116, 0));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 10));

        jLabel22.setBackground(new java.awt.Color(0, 60, 113));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(96, 83, 150));
        jLabel22.setText("Em Avaliadas");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 80, 10));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 130, 80));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTotalFuncionarios.setBackground(new java.awt.Color(0, 60, 113));
        labelTotalFuncionarios.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelTotalFuncionarios.setForeground(new java.awt.Color(96, 83, 150));
        labelTotalFuncionarios.setText("50");
        jPanel5.add(labelTotalFuncionarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 21, 75, -1));

        jPanel12.setBackground(new java.awt.Color(160, 116, 0));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 10));

        jLabel19.setBackground(new java.awt.Color(0, 60, 113));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(96, 83, 150));
        jLabel19.setText("Nesta Lotação");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 90, 10));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 140, 80));

        jLabel12.setBackground(new java.awt.Color(0, 60, 113));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(96, 83, 150));
        jLabel12.setText("Total de Funcionários");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 106, 160, 30));

        btn_close.setBackground(new java.awt.Color(96, 83, 150));
        btn_close.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_close.setForeground(new java.awt.Color(255, 255, 255));
        btn_close.setText("X");
        btn_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_closeMouseClicked(evt);
            }
        });
        jPanel2.add(btn_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 20, 30));

        jPanel6.setBackground(new java.awt.Color(247, 247, 247));

        panel1.setBackground(new java.awt.Color(255, 255, 255));

        labelNomePanel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNomePanel1.setForeground(new java.awt.Color(96, 83, 150));
        labelNomePanel1.setText("Nome Funcionário");

        labelReferenciaPanel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelReferenciaPanel1.setForeground(new java.awt.Color(96, 83, 150));
        labelReferenciaPanel1.setText("Referência: xxxxxxxxxx");

        labelMatriculaPanel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMatriculaPanel1.setForeground(new java.awt.Color(96, 83, 150));
        labelMatriculaPanel1.setText("Matrícula: xxx");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(labelMatriculaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addComponent(labelReferenciaPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNomePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(labelNomePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMatriculaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelReferenciaPanel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        panel2.setBackground(new java.awt.Color(255, 255, 255));

        labelNomePanel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNomePanel2.setForeground(new java.awt.Color(96, 83, 150));
        labelNomePanel2.setText("Nome Funcionário");

        labelReferenciaPanel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelReferenciaPanel2.setForeground(new java.awt.Color(96, 83, 150));
        labelReferenciaPanel2.setText("Referência: xxxxxxxxxx");

        labelMatriculaPanel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMatriculaPanel2.setForeground(new java.awt.Color(96, 83, 150));
        labelMatriculaPanel2.setText("Matrícula: xxx");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(labelMatriculaPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(labelReferenciaPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNomePanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addComponent(labelNomePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMatriculaPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelReferenciaPanel2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        panel3.setBackground(new java.awt.Color(255, 255, 255));

        labelNomePanel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNomePanel3.setForeground(new java.awt.Color(96, 83, 150));
        labelNomePanel3.setText("Nome Funcionário");

        labelReferenciaPanel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelReferenciaPanel3.setForeground(new java.awt.Color(96, 83, 150));
        labelReferenciaPanel3.setText("Referência: xxxxxxxxxx");

        labelMatriculaPanel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMatriculaPanel3.setForeground(new java.awt.Color(96, 83, 150));
        labelMatriculaPanel3.setText("Matrícula: xxx");

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(labelMatriculaPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(labelReferenciaPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNomePanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addComponent(labelNomePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMatriculaPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelReferenciaPanel3)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, -1, 350));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(96, 83, 150));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Últimas notificações");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 240, -1));

        jPanel8.setBackground(new java.awt.Color(0, 60, 113));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 180, -1));

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableMenu.setModel(new javax.swing.table.DefaultTableModel(
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
        tableMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMenuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableMenu);

        panelMenu.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 460, 230));

        labelTitulo.setBackground(new java.awt.Color(0, 60, 113));
        labelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(96, 83, 150));
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelMenu.add(labelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 30));

        jPanel2.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 460, 260));

        jLabel11.setBackground(new java.awt.Color(0, 60, 113));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(96, 83, 150));
        jLabel11.setText("Alterações avaliadas");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 102, 130, 41));

        jLabel8.setBackground(new java.awt.Color(0, 60, 113));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(96, 83, 150));
        jLabel8.setText("Alterações para avaliar");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 140, 41));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 780, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btn_closeMouseClicked

    private void Button2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button2MouseClicked

        onClick(Button2);
        onLeaveClick(Button5);
        onLeaveClick(Button6);

        //indicators
        Indicator2.setOpaque(true);
        Indicator5.setOpaque(false);
        Indicator6.setOpaque(false);
    }//GEN-LAST:event_Button2MouseClicked

    private void Button2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button2MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_Button2MouseEntered

    private void Button2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Button2MouseExited

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void Button5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button5MouseClicked
        onClick(Button5);
        onLeaveClick(Button2);
        onLeaveClick(Button6);

        //indicators
        Indicator5.setOpaque(true);
        Indicator2.setOpaque(false);
        Indicator6.setOpaque(false);
        
      System.out.println("Local: "+local);
    }//GEN-LAST:event_Button5MouseClicked

    private void Button5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Button5MouseEntered

    private void Button5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Button5MouseExited

    private void Button6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button6MouseClicked
        onClick(Button6);
        onLeaveClick(Button5);
        onLeaveClick(Button2);

        //indicators
        Indicator6.setOpaque(true);
        Indicator5.setOpaque(false);
        Indicator2.setOpaque(false);
    }//GEN-LAST:event_Button6MouseClicked

    private void Button6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Button6MouseEntered

    private void Button6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Button6MouseExited

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        if (local.equals("Gerência")) {
            GerenciaControl g = new GerenciaControl();
            int id=g.getIdGerencia(sigla);
            g.TrocaSenhaGerencia(id);
        }

        if (local.equals("Departamento")) {
          DepartamentoControl dE = new DepartamentoControl();
          int id=dE.getIdDepartamento(sigla);
          dE.TrocaSenhaDepartamento(id);
        }

        if (local.equals("Diretoria")) {
           DiretoriaControl dI = new DiretoriaControl();
           int id=dI.getIdDiretoria(sigla);
           dI.TrocaSenhaDiretoria(id);
        }

        if (local.equals("Presidência")) {
           PresidenciaControl p = new PresidenciaControl();
           p.TrocaSenhaPresidencia(1);
        }
    }//GEN-LAST:event_jLabel24MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        TelaMenuModel telaModel = new TelaMenuModel();
        TelaMenuControl telaControl = new TelaMenuControl();
        telaModel = this.telaModel;
        this.telaModel = telaControl.preencheMenu(local, sigla);
        setCampos();
    }//GEN-LAST:event_formWindowActivated

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        preencheTabelaAlteracoesAvaliar();
        avaliadas = false;
        avaliar = true;
        funcionarios = false;
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        preencheTabelaAlteracoesAvaliadas();
        avaliadas = true;
        avaliar = false;
        funcionarios = false;
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        preencheTabelaFuncionarios();
        avaliadas = false;
        avaliar = false;
        funcionarios = true;
    }//GEN-LAST:event_jPanel5MouseClicked

    private void tableMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMenuMouseClicked
        if (evt.getClickCount() == 2) {
            int id = 0;
            if (local.equals("Gerência")) {
                GerenciaControl g = new GerenciaControl();
                id = g.getIdGerenciaPeloNome(telaModel.getSetor());
            }

            if (local.equals("Departamento")) {
                DepartamentoControl dE = new DepartamentoControl();
                id = dE.getIdDepartamentoPeloNome(telaModel.getSetor());
            }

            if (local.equals("Diretoria")) {
                DiretoriaControl dI = new DiretoriaControl();
                id = dI.getIdDiretoriaPeloNome(telaModel.getSetor());
            }

            if (local.equals("Presidência")) {
                //Não precisa do ID pois não há outra Presidência no banco

            }

            if ((avaliadas == true) || (avaliar == true)) {
                GestorVisualizaAlteracaoModel g = new GestorVisualizaAlteracaoModel();
                g.setMat(Integer.parseInt("" + tableMenu.getValueAt(tableMenu.getSelectedRow(), 0)));
                g.setNome(("" + tableMenu.getValueAt(tableMenu.getSelectedRow(), 1)));
                g.setReferencia(("" + tableMenu.getValueAt(tableMenu.getSelectedRow(), 2)));
                VisualizaAlteracaoFrequenciaMenu v = new VisualizaAlteracaoFrequenciaMenu(local, id, g);
                v.setVisible(true);

            }
        }
    }//GEN-LAST:event_tableMenuMouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
       if (local.equals("Gerência")) {
            GerenciaControl g = new GerenciaControl();
           g.TrocaEmailGerencia(sigla);
        }
       
       if (local.equals("Departamento")) {
            DepartamentoControl dE = new DepartamentoControl();
           dE.TrocaEmailDepartamento(sigla);
        }
       
       if (local.equals("Diretoria")) {
            DiretoriaControl dI = new DiretoriaControl();
           dI.TrocaEmailDiretoria(sigla);
        }
       
       if (local.equals("Presidência")) {
            PresidenciaControl p = new PresidenciaControl();
           p.TrocaEmailPresidencia();
        }
    }//GEN-LAST:event_jLabel13MouseClicked

    int xx, xy;

    //bad idea
    private void onClick(JPanel panel) {
        panel.setBackground(new Color(205, 136, 205));
    }

    private void onLeaveClick(JPanel panel) {
        panel.setBackground(Color.white);
    }

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuSetores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuSetores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuSetores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuSetores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuSetores(telaModel, local, sigla, n).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Button2;
    private javax.swing.JPanel Button5;
    private javax.swing.JPanel Button6;
    private javax.swing.JPanel Indicator2;
    private javax.swing.JPanel Indicator5;
    private javax.swing.JPanel Indicator6;
    private javax.swing.JLabel btn_close;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAlteracoesAvaliadas;
    private javax.swing.JLabel labelAlteracoesAvaliar;
    private javax.swing.JLabel labelMatriculaPanel1;
    private javax.swing.JLabel labelMatriculaPanel2;
    private javax.swing.JLabel labelMatriculaPanel3;
    private javax.swing.JLabel labelNomePanel1;
    private javax.swing.JLabel labelNomePanel2;
    private javax.swing.JLabel labelNomePanel3;
    private javax.swing.JLabel labelReferenciaPanel1;
    private javax.swing.JLabel labelReferenciaPanel2;
    private javax.swing.JLabel labelReferenciaPanel3;
    private javax.swing.JLabel labelSetor;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelTotalFuncionarios;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JTable tableMenu;
    // End of variables declaration//GEN-END:variables
}
