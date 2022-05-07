package vision_menus;

import report_code.ImprimeRelatorio;
import controllers.AlteracaoFrequenciaControl;
import controllers.CompetenciaControl;
import controllers.DepartamentoControl;
import controllers.DiretoriaControl;
import controllers.FuncionarioControl;
import controllers.GerenciaControl;
import controllers.NotificacaoControl;
import controllers.PresidenciaControl;
import controllers.ResetaSenhaControl;
import controllers.SetoresControl;
import controllers.TelaMenuControl;
import controllers_tables.AlteracoesAvaliarControl;
import controllers_tables.TabelaAnaliseControl;
import controllers_tables.TabelaFuncionarioControl;
import controllers_tables.TabelaPendenciasControl;
import email_.EnvioEmail;
import tables.ImagemTabela;
import tables.TabelaAlteracaoAvaliar;
import tables.TabelaAlteracoesAvaliadores;
import tables.TabelaFuncionario;
import tables.TabelaMenuAdminConcluidas;
import tables.TabelaMenuAdminPendencia;
import utils.BarraProgresso;
import vision_events.FuncionarioVisualizaAlteracao;
import vision_events.VisualizaAlteracaoFrequenciaAvaliadores;
import vision_events.VisualizaAlteracaoFrequenciaMenu;
import vision_registers.CadastroDepartamento;
import vision_registers.CadastroDiretoria;
import vision_registers.CadastroFuncionario;
import vision_registers.CadastroGerencia;
import vision_registers.RemanejaFuncionario;
import domain.Model.ControleTabelas.AlteracoesAvaliarMenuModel;
import domain.Model.ControleTabelas.FuncionarioTabelaModel;
import domain.Model.ControleTabelas.MenuAdminPendenciasModel;
import domain.Model.ControleTabelas.TabelaAnaliseModel;
import domain.Modelo.GestorVisualizaAlteracaoModel;
import domain.Modelo.NotificacaoModel;
import domain.Modelo.TelaMenuModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class MenuAdmin extends javax.swing.JFrame {

    private static TelaMenuModel telaModel;
    private static String local;
    private static String sigla;
    private static ArrayList<NotificacaoModel> n;
    boolean avaliadas = false;
    boolean avaliar = false;
    boolean funcionarios = false;

    public MenuAdmin() {
        initComponents();

        URL caminhoImagem = this.getClass().getClassLoader().getResource("futuro.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
        setQuadros();

        panelMenu.setVisible(false);
        panelBarraProgresso.setVisible(false);
        System.out.println("1");
        geraComboSetores();
        System.out.println("2");
        geraComboCompetencia();
        System.out.println("3");
        cbBoxSetor.setEnabled(false);
        jLabelBuscar.setEnabled(false);
        jLabelSetor.setEnabled(false);
        cbBoxCompetencia.setEnabled(false);
        txtBuscar.setEnabled(false);
        cbBoxCompetencia.setEnabled(false);
    }

    public void geraComboSetores() {
        cbBoxSetor.removeAllItems();
        cbBoxSetor.addItem("Todos");
        DiretoriaControl dI = new DiretoriaControl();
        DepartamentoControl dE = new DepartamentoControl();
        GerenciaControl gC = new GerenciaControl();

        //Adicionar as Gerências
        ArrayList<String> a = new ArrayList();
        a = gC.getListaGerencias();
        int n = a.size();
        int i;
        for (i = 0; i < n; i++) {
            cbBoxSetor.addItem(a.get(i));
        }
//        cBoxVinculacao.removeItemAt(0);

        //Adicionar os Departamentos
        ArrayList<String> b = new ArrayList();
        b = dE.getListaDepartamentoCadastroFuncionario();
        int m = b.size();
        int j;
        for (j = 0; j < m; j++) {
            cbBoxSetor.addItem(b.get(j));
        }

        //Adicionar as Diretorias
        ArrayList<String> c = new ArrayList();
        c = dI.getListaDiretoria();
        int o = c.size();
        int k;
        for (k = 0; k < o; k++) {
            cbBoxSetor.addItem(c.get(k));
        }
        //cBoxVinculacao.removeItem("PRESIDÊNCIA");

    }

    public void geraComboCompetencia() {
        cbBoxCompetencia.removeAllItems();
        cbBoxCompetencia.addItem("Todos");
        CompetenciaControl c = new CompetenciaControl();
        ArrayList<String> a = new ArrayList();
        a = c.listaCompetencias();

        for (int i = 0; i < a.size(); i++) {
            cbBoxCompetencia.addItem(a.get(i));
        }
    }

    public void setQuadros() {
        AlteracaoFrequenciaControl a = new AlteracaoFrequenciaControl();
        int nGerencia = a.getPendênciasGerencia();
        int nDepartamento = a.getPendênciasDepartamento();
        int nDiretoria = a.getPendênciasDiretoria();
        int nPresidencia = a.getPendênciasPresidencia();
        int nRecebidasRH = a.getNumeroRecebidasRH();

        labelGerencia.setText(String.valueOf(nGerencia));
        labelDepartamento.setText(String.valueOf(nDepartamento));
        labelDiretoria.setText(String.valueOf(nDiretoria));
        labelPresidencia.setText(String.valueOf(nPresidencia));
        labelRecebidas.setText(String.valueOf(nRecebidasRH));
    }

    public boolean preencheTabelaPendenciasGerencia() {
        panelMenu.setVisible(true);
        labelTitulo.setText("Pendências - Gerência");
        TabelaPendenciasControl tabelaPendencias = new TabelaPendenciasControl();

        ArrayList<MenuAdminPendenciasModel> listaTabela = new ArrayList();

        listaTabela = tabelaPendencias.getListaPendencas("Gerência", "", "", "");
        //listaEventos=listaResumo; 

        boolean sinal = listaTabela.isEmpty();

        if (!listaTabela.isEmpty()) {

            TabelaMenuAdminPendencia tabelaMenuAdmin = new TabelaMenuAdminPendencia(listaTabela);//Montar uma lista com a tabela Resumo

            tableMenu.setDefaultRenderer(Object.class, new ImagemTabela());
            tableMenu.setRowHeight(35);
            ((DefaultTableCellRenderer) tableMenu.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer) tableMenu.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableMenu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableMenu.getTableHeader().setOpaque(false);
            tableMenu.getTableHeader().setBackground(new Color(0, 0, 0));
            tableMenu.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tableMenu.setModel(tabelaMenuAdmin);
        } else {
            panelMenu.setVisible(false);
            JOptionPane.showMessageDialog(null, "Não há pendências relacionadas às Gerências!");

        }
        return sinal;
    }

    public boolean preencheTabelaPendenciasDepartamento() {
        panelMenu.setVisible(true);
        labelTitulo.setText("Pendências - Departamento");
        TabelaPendenciasControl tabelaPendencias = new TabelaPendenciasControl();
        ArrayList<MenuAdminPendenciasModel> listaTabela = new ArrayList();
        listaTabela = tabelaPendencias.getListaPendencas("Departamento", "", "", "");
        //listaEventos=listaResumo; 

        boolean sinal = listaTabela.isEmpty();

        if (!listaTabela.isEmpty()) {

            TabelaMenuAdminPendencia tabelaMenuAdmin = new TabelaMenuAdminPendencia(listaTabela);//Montar uma lista com a tabela Resumo

            tableMenu.setDefaultRenderer(Object.class, new ImagemTabela());
            tableMenu.setRowHeight(35);
            ((DefaultTableCellRenderer) tableMenu.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer) tableMenu.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableMenu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableMenu.getTableHeader().setOpaque(false);
            tableMenu.getTableHeader().setBackground(new Color(0, 0, 0));
            tableMenu.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tableMenu.setModel(tabelaMenuAdmin);
        } else {
            panelMenu.setVisible(false);
            JOptionPane.showMessageDialog(null, "Não há pendências relacionadas aos Departamentos!");

        }
        return sinal;
    }

    public boolean preencheTabelaPendenciasDiretoria() {
        panelMenu.setVisible(true);
        labelTitulo.setText("Pendências - Diretoria");
        TabelaPendenciasControl tabelaPendencias = new TabelaPendenciasControl();
        ArrayList<MenuAdminPendenciasModel> listaTabela = new ArrayList();
        listaTabela = tabelaPendencias.getListaPendencas("Diretoria", "", "", "");
        //listaEventos=listaResumo; 

        boolean sinal = listaTabela.isEmpty();

        if (!listaTabela.isEmpty()) {

            TabelaMenuAdminPendencia tabelaMenuAdmin = new TabelaMenuAdminPendencia(listaTabela);//Montar uma lista com a tabela Resumo

            tableMenu.setDefaultRenderer(Object.class, new ImagemTabela());
            tableMenu.setRowHeight(35);
            ((DefaultTableCellRenderer) tableMenu.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer) tableMenu.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableMenu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableMenu.getTableHeader().setOpaque(false);
            tableMenu.getTableHeader().setBackground(new Color(0, 0, 0));
            tableMenu.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tableMenu.setModel(tabelaMenuAdmin);
        } else {
            panelMenu.setVisible(false);
            JOptionPane.showMessageDialog(null, "Não há pendências relacionadas às Diretorias!");

        }
        return sinal;
    }

    public boolean preencheTabelaConcluidas() {
        panelMenu.setVisible(true);
        labelTitulo.setText("Avaliações Concluídas e Recebidas");
        TabelaPendenciasControl tabelaPendencias = new TabelaPendenciasControl();
        ArrayList<MenuAdminPendenciasModel> listaTabela = new ArrayList();
        String nome = txtBuscar.getText();
        String setor = String.valueOf(cbBoxSetor.getSelectedItem());
        String competencia = String.valueOf(cbBoxCompetencia.getSelectedItem());
        listaTabela = tabelaPendencias.getListaPendencas("RH", nome, setor, competencia);
        //listaEventos=listaResumo; 

        boolean sinal = listaTabela.isEmpty();

        if (!listaTabela.isEmpty()) {

            TabelaMenuAdminConcluidas tabelaMenuAdmin = new TabelaMenuAdminConcluidas(listaTabela);//Montar uma lista com a tabela Resumo

            tableMenu.setDefaultRenderer(Object.class, new ImagemTabela());
            tableMenu.setRowHeight(35);
            ((DefaultTableCellRenderer) tableMenu.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer) tableMenu.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableMenu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableMenu.getTableHeader().setOpaque(false);
            tableMenu.getTableHeader().setBackground(new Color(0, 0, 0));
            tableMenu.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tableMenu.setModel(tabelaMenuAdmin);
        } else {
            panelMenu.setVisible(false);
            JOptionPane.showMessageDialog(null, "Não há avaliações concluídas!");

        }
        return sinal;
    }

    public boolean preencheTabelaPendenciaPresidencia() {
        panelMenu.setVisible(true);
        labelTitulo.setText("Pendências - Presidência");
        TabelaPendenciasControl tabelaPendencias = new TabelaPendenciasControl();
        ArrayList<MenuAdminPendenciasModel> listaTabela = new ArrayList();
        listaTabela = tabelaPendencias.getListaPendencas("Presidência", "", "", "");
        //listaEventos=listaResumo; 

        boolean sinal = listaTabela.isEmpty();

        if (!listaTabela.isEmpty()) {

            TabelaMenuAdminPendencia tabelaMenuAdmin = new TabelaMenuAdminPendencia(listaTabela);//Montar uma lista com a tabela Resumo

            tableMenu.setDefaultRenderer(Object.class, new ImagemTabela());
            tableMenu.setRowHeight(35);
            ((DefaultTableCellRenderer) tableMenu.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            ((DefaultTableCellRenderer) tableMenu.getDefaultRenderer(getClass())).setHorizontalAlignment(SwingConstants.CENTER);
            tableMenu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            tableMenu.getTableHeader().setOpaque(false);
            tableMenu.getTableHeader().setBackground(new Color(0, 0, 0));
            tableMenu.getTableHeader().setForeground(new Color(255, 255, 255));
            ImagemTabela img = new ImagemTabela();

            tableMenu.setModel(tabelaMenuAdmin);
        } else {
            panelMenu.setVisible(false);
            JOptionPane.showMessageDialog(null, "Não há pendências relacionadas à Presidência!");

        }
        return sinal;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        labelGerencia = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        labelDepartamento = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        labelRecebidas = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btn_close = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMenu = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabelSetor = new javax.swing.JLabel();
        jLabelBuscar = new javax.swing.JLabel();
        cbBoxSetor = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbBoxCompetencia = new javax.swing.JComboBox<>();
        btn_relatorios = new java.awt.Button();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        labelDiretoria = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        labelPresidencia = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        panelBarraProgresso = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        barraProgresso = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        labelGerencia.setBackground(new java.awt.Color(0, 60, 113));
        labelGerencia.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelGerencia.setForeground(new java.awt.Color(96, 83, 150));
        labelGerencia.setText("500");

        jLabel6.setBackground(new java.awt.Color(0, 60, 113));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(96, 83, 150));
        jLabel6.setText("Pendência(s)");

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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelGerencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(53, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelGerencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 140, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelDepartamento.setBackground(new java.awt.Color(0, 60, 113));
        labelDepartamento.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelDepartamento.setForeground(new java.awt.Color(96, 83, 150));
        labelDepartamento.setText("50");
        jPanel4.add(labelDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, -1, 40));

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
        jLabel22.setText("Pendência(s)");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 130, 80));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelRecebidas.setBackground(new java.awt.Color(0, 60, 113));
        labelRecebidas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelRecebidas.setForeground(new java.awt.Color(96, 83, 150));
        labelRecebidas.setText("50");
        jPanel5.add(labelRecebidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 13, 75, 40));

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
        jLabel19.setText("Concluídas");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, 20));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, 140, 80));

        jLabel12.setBackground(new java.awt.Color(0, 60, 113));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(96, 83, 150));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Alterações concluídas");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 140, 40));

        jPanel8.setBackground(new java.awt.Color(0, 60, 113));

        btn_close.setBackground(new java.awt.Color(96, 83, 150));
        btn_close.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_close.setForeground(new java.awt.Color(255, 255, 255));
        btn_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_close.setText("X");
        btn_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_closeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 57, Short.MAX_VALUE)
                .addComponent(btn_close, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_close, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 0, 90, -1));

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTitulo.setBackground(new java.awt.Color(0, 60, 113));
        labelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(96, 83, 150));
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelMenu.add(labelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 30));

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
        jScrollPane2.setViewportView(tableMenu);

        panelMenu.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 970, 290));

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        panelMenu.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 200, 30));

        jLabelSetor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSetor.setText("Setor:");
        panelMenu.add(jLabelSetor, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 50, 30));

        jLabelBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelBuscar.setText("Buscar:");
        panelMenu.add(jLabelBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 50, 30));

        cbBoxSetor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelMenu.add(cbBoxSetor, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 200, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Competência:");
        panelMenu.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 100, 30));

        cbBoxCompetencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbBoxCompetencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbBoxCompetenciaFocusLost(evt);
            }
        });
        cbBoxCompetencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBoxCompetenciaActionPerformed(evt);
            }
        });
        panelMenu.add(cbBoxCompetencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 200, 30));

        btn_relatorios.setBackground(new java.awt.Color(160, 116, 0));
        btn_relatorios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_relatorios.setForeground(new java.awt.Color(255, 255, 255));
        btn_relatorios.setLabel("Relatórios");
        btn_relatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_relatoriosMouseClicked(evt);
            }
        });
        btn_relatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_relatoriosActionPerformed(evt);
            }
        });
        panelMenu.add(btn_relatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 130, -1));

        jPanel2.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 980, 370));

        jLabel11.setBackground(new java.awt.Color(0, 60, 113));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(96, 83, 150));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("ADMINISTRADOR DO SISTEMA");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 810, 41));

        jLabel8.setBackground(new java.awt.Color(0, 60, 113));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(96, 83, 150));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Pendências Gerências");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 140, 41));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelDiretoria.setBackground(new java.awt.Color(0, 60, 113));
        labelDiretoria.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelDiretoria.setForeground(new java.awt.Color(96, 83, 150));
        labelDiretoria.setText("50");
        jPanel7.add(labelDiretoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, 80, 40));

        jPanel15.setBackground(new java.awt.Color(160, 116, 0));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 10));

        jLabel20.setBackground(new java.awt.Color(0, 60, 113));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(96, 83, 150));
        jLabel20.setText("Pendência(s)");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, 20));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 140, 80));

        jLabel14.setBackground(new java.awt.Color(0, 60, 113));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(96, 83, 150));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Pendências Diretorias");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 140, 40));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelPresidencia.setBackground(new java.awt.Color(0, 60, 113));
        labelPresidencia.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelPresidencia.setForeground(new java.awt.Color(96, 83, 150));
        labelPresidencia.setText("50");
        jPanel6.add(labelPresidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 13, 75, 40));

        jPanel16.setBackground(new java.awt.Color(160, 116, 0));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 10));

        jLabel27.setBackground(new java.awt.Color(0, 60, 113));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(96, 83, 150));
        jLabel27.setText("Pendência(s)");
        jPanel6.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, 20));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, 140, 80));

        jLabel13.setBackground(new java.awt.Color(0, 60, 113));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(96, 83, 150));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Pendências Presidência");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 140, 40));

        panelBarraProgresso.setBackground(new java.awt.Color(255, 255, 255));
        panelBarraProgresso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(panelBarraProgresso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 970, 40));

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

        jPanel2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

        jPanel2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Versão 1.04");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 80, 20));

        jLabel15.setBackground(new java.awt.Color(0, 60, 113));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(96, 83, 150));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Pendências Dptos");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 130, 41));
        jPanel2.add(barraProgresso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 970, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 620));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Funcionarios.png"))); // NOI18N
        jMenu1.setText("Funcionários");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        jMenuItem1.setText("Cadastrar Funcionários");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Remanejar Funcionários");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Resetar Senha");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Setores.png"))); // NOI18N
        jMenu2.setText("Setores");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        jMenuItem4.setText("Cadastrar/Editar Gerência");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem6.setText("Cadastrar/Editar Departamento");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Cadastrar/Editar Diretoria");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem5.setText("Resetar Senha");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btn_closeMouseClicked

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

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        preencheTabelaPendenciasGerencia();
        txtBuscar.setEnabled(false);
        cbBoxSetor.setEnabled(false);
        cbBoxCompetencia.setEnabled(false);
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        preencheTabelaPendenciasDepartamento();
        txtBuscar.setEnabled(false);
        cbBoxSetor.setEnabled(false);
        cbBoxCompetencia.setEnabled(false);
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        jLabelBuscar.setEnabled(true);
        jLabelSetor.setEnabled(true);
        cbBoxCompetencia.setEnabled(true);
        txtBuscar.setEnabled(true);
        cbBoxCompetencia.setEnabled(true);
        preencheTabelaConcluidas();
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        preencheTabelaPendenciasDiretoria();
        txtBuscar.setEnabled(false);
        cbBoxSetor.setEnabled(false);
        cbBoxCompetencia.setEnabled(false);
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        preencheTabelaPendenciaPresidencia();
        txtBuscar.setEnabled(false);
        cbBoxSetor.setEnabled(false);
        cbBoxCompetencia.setEnabled(false);
    }//GEN-LAST:event_jPanel6MouseClicked

    private void tableMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMenuMouseClicked
        if (evt.getClickCount() == 2) {

            String nome = "" + tableMenu.getValueAt(tableMenu.getSelectedRow(), 0);
            String referencia = "" + tableMenu.getValueAt(tableMenu.getSelectedRow(), 1);
            FuncionarioControl func = new FuncionarioControl();
            int mat = func.getMat(nome);
            FuncionarioVisualizaAlteracao f = new FuncionarioVisualizaAlteracao(nome, referencia, mat);
            f.setVisible(true);
        }
    }//GEN-LAST:event_tableMenuMouseClicked

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped

    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            preencheTabelaConcluidas();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        CadastroFuncionario cadastro = new CadastroFuncionario();
        cadastro.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        RemanejaFuncionario r = new RemanejaFuncionario();
        r.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        FuncionarioControl f = new FuncionarioControl();
        ArrayList<String> lista = new ArrayList();
        lista = f.getListaFuncionários();
        JComboBox jcb = new JComboBox();

        for (int i = 0; i < lista.size(); i++) {
            jcb.addItem(lista.get(i));
        }

        Object[] options = {"Confirmar", "Sair"};
        int i = JOptionPane.showOptionDialog(null, jcb,
                "Selecione o Funcionário", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

//        JOptionPane.showMessageDialog(null, jcb, "Selecione o Funcionário", JOptionPane.QUESTION_MESSAGE);
        if (i == 0) {
            String nome = String.valueOf(jcb.getSelectedItem());

            f.ResetSenha(nome);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        CadastroDepartamento c = new CadastroDepartamento();
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        CadastroGerencia c = new CadastroGerencia();
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        CadastroDiretoria c = new CadastroDiretoria();
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        SetoresControl setores = new SetoresControl();
        ArrayList<String> lista = new ArrayList();
        lista = setores.getListaGeral();

        JComboBox jcb = new JComboBox();

        for (int i = 0; i < lista.size(); i++) {
            jcb.addItem(lista.get(i));
        }

        Object[] options = {"Confirmar", "Sair"};
        int i = JOptionPane.showOptionDialog(null, jcb,
                "Selecione o setor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (i == 0) {
            String setor = String.valueOf(jcb.getSelectedItem());
            System.out.println(setor);
            char letra1 = setor.charAt(0);
            char letra2 = setor.charAt(1);
            ResetaSenhaControl r = new ResetaSenhaControl();

            if ((letra1 == 'G') && (letra2 == 'E')) {
                GerenciaControl g = new GerenciaControl();
                g.ResetaSenha(setor, r.geraSenha());
            }

            if (((letra1 == 'D') && (letra2 == 'E')) || ((letra1 == 'G') && (letra2 == 'A')) || (letra1 == 'S') && (letra2 == 'E')) {
                DepartamentoControl dE = new DepartamentoControl();
                dE.ResetaSenha(setor, r.geraSenha());
            }

            if ((letra1 == 'D') && (letra2 == 'I') || ((letra1 == 'A') && (letra2 == 'S'))) {
                DiretoriaControl dI = new DiretoriaControl();
                dI.ResetaSenha(setor, r.geraSenha());
            }

            if (setor.equals("PRESIDÊNCIA")) {
                PresidenciaControl p = new PresidenciaControl();
                p.ResetaSenha(1, r.geraSenha());//Id do Presidente é sempre 1
            }

        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void btn_relatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_relatoriosMouseClicked

    }//GEN-LAST:event_btn_relatoriosMouseClicked

    private void btn_relatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_relatoriosActionPerformed
        panelBarraProgresso.setVisible(true);
        BarraProgresso barra = new BarraProgresso(barraProgresso);
        barra.load();
        try {
            sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        FuncionarioControl f = new FuncionarioControl();
        String titulo = labelTitulo.getText();
        if (titulo.equals("Avaliações Concluídas e Recebidas")) {

            String nome = ("" + tableMenu.getValueAt(tableMenu.getSelectedRow(), 0));
            String referencia = (("" + tableMenu.getValueAt(tableMenu.getSelectedRow(), 1)));
            int mat = f.getMat(nome);
            ImprimeRelatorio imprime = new ImprimeRelatorio();

            String lotacao = f.getLotacao(mat);

            imprime.ImprimeRelatorioFinal(mat, referencia, lotacao);
        }

    }//GEN-LAST:event_btn_relatoriosActionPerformed

    private void cbBoxCompetenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBoxCompetenciaActionPerformed

    }//GEN-LAST:event_cbBoxCompetenciaActionPerformed

    private void cbBoxCompetenciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbBoxCompetenciaFocusLost
        preencheTabelaConcluidas();
        geraComboCompetencia();
    }//GEN-LAST:event_cbBoxCompetenciaFocusLost

    int xx, xy;

    //bad idea
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
            java.util.logging.Logger.getLogger(MenuAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgresso;
    private javax.swing.JLabel btn_close;
    private java.awt.Button btn_relatorios;
    private javax.swing.JComboBox<String> cbBoxCompetencia;
    private javax.swing.JComboBox<String> cbBoxSetor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelBuscar;
    private javax.swing.JLabel jLabelSetor;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDepartamento;
    private javax.swing.JLabel labelDiretoria;
    private javax.swing.JLabel labelGerencia;
    private javax.swing.JLabel labelPresidencia;
    private javax.swing.JLabel labelRecebidas;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelBarraProgresso;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JTable tableMenu;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
