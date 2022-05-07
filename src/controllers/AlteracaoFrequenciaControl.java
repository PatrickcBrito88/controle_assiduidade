package controllers;

import email_.EnvioEmail;
import utils.ConectaBanco;
import domain_enums.EnumSituacao;
import com.mysql.jdbc.PreparedStatement;
import domain.CRUD.Crud_Alteracao;
import domain.CRUD.Crud_Log;
import domain.Model.ControleTabelas.TabelaResumoAlteracaoModel;
import domain.Modelo.SituacaoAFModel;
import domain.Model.ControleTabelas.TabelaVisaoAlteracaoModel;
import domain.Modelo.BackupModel;
import domain.Modelo.LogModel;
import domain.Modelo.PerfilModel;
import domain.Modelo.SetCampos.AlteracaoFrequenciaModel;
import domain.Modelo.SetCampos.VisualizaItemAlteracaoModel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AlteracaoFrequenciaControl {

    ConectaBanco conecta = new ConectaBanco();

    public boolean verificaAnexo(int idRA) {
        conecta.conexao();
        byte[] anexo = null;
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where idAlteracaoFrequencia_RA=" + idRA);
        try {
            conecta.rs.first();
            anexo = conecta.rs.getBytes("anexo_ra");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/verificaAnexo.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        if (anexo == null) {
            return false;
        } else {
            return true;
        }

    }

    public int getLastAF(int mat) {
        conecta.conexao();
        int id = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where funcionario_matfunc_af=" + mat);
        try {
            conecta.rs.last();
            id = conecta.rs.getInt("idAlteracaofrequencia_AF");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/getLastAF");
        } finally {
            conecta.desconecta();
        }
        return id;
    }

    public boolean verificaAF(String referencia, int mat) {
        boolean sinal = false;
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from AlteracaoFrequencia where funcionario_matFunc_AF="
                + mat + " and referencia_af='" + referencia + "'");
        try {
            sinal = conecta.rs.first();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/verificaAF");
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }

    private ArrayList<SituacaoAFModel> listaSituacao(int mat) {

        ArrayList<SituacaoAFModel> listaSituacao = new ArrayList();
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from Alteracaofrequencia where funcionario_matfunc_af=" + mat);
        try {
            conecta.rs.first();
            do {
                SituacaoAFModel situacaoAFModel = new SituacaoAFModel();
                situacaoAFModel.setSituacaoGerencia(EnumSituacao.valueOf(conecta.rs.getString("SituacaoGerencia_AF")));
                situacaoAFModel.setSituacaoDepartamento(EnumSituacao.valueOf(conecta.rs.getString("SituacaoDepartamento_AF")));
                situacaoAFModel.setSituacaoDiretoria(EnumSituacao.valueOf(conecta.rs.getString("SituacaoDiretoria_AF")));
                situacaoAFModel.setSituacaoRH(EnumSituacao.valueOf(conecta.rs.getString("SituacaoRH_AF")));
                listaSituacao.add(situacaoAFModel);
            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaModel/listaSituacao.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return listaSituacao;
    }

    public AlteracaoFrequenciaModel getObjeto(String tipoLocal, int idLocal) {
        AlteracaoFrequenciaModel a = new AlteracaoFrequenciaModel();

        if (tipoLocal.equals("Gerência")) {
            conecta.conexao();
            conecta.executaPesquisaSQL("Select * from gerencia where idGerencia_G=" + idLocal);
            try {
                conecta.rs.first();
                a.setNomeLocal(conecta.rs.getString("nomeGerencia_G"));
                a.setSiglaLocal(conecta.rs.getString("sigla_G"));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/GetObjeto .\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }

        if (tipoLocal.equals("Departamento")) {
            conecta.conexao();
            conecta.executaPesquisaSQL("Select * from departamento where iddepartamento_de=" + idLocal);
            try {
                conecta.rs.first();
                a.setNomeLocal(conecta.rs.getString("nomedepartamento_de"));
                a.setSiglaLocal(conecta.rs.getString("Sigla_De"));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/GetObjeto .\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }

        if (tipoLocal.equals("Diretoria")) {
            conecta.conexao();
            conecta.executaPesquisaSQL("Select * from diretoria where idDiretoria_Di=" + idLocal);
            try {
                conecta.rs.first();
                a.setNomeLocal(conecta.rs.getString("nomeDiretoria_Di"));
                a.setSiglaLocal(conecta.rs.getString("sigla_Di"));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/GetObjeto .\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }

        return a;
    }

    public void AlteraAvaliacaoGerencia(int mat, String referencia) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update alteracaofrequencia set situacaogerencia_af=? "
                    + "where funcionario_matfunc_af=? and referencia_af=?");
            pst.setString(1, EnumSituacao.RECEBIDA.toString());
            pst.setInt(2, mat);
            pst.setString(3, referencia);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/AlteraAvaliacaoGerencia.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public void AlteraAvaliacaoDepartamento(int mat, String referencia) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update alteracaofrequencia set situacaodepartamento_af=? "
                    + "where funcionario_matfunc_af=? and referencia_af=?");
            pst.setString(1, EnumSituacao.RECEBIDA.toString());
            pst.setInt(2, mat);
            pst.setString(3, referencia);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/AlteraAvaliacaoGerencia.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public void AlteraAvaliacaoDiretoria(int mat, String referencia) {
        conecta.conexao();
        PreparedStatement pst;

        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update alteracaofrequencia set situacaodiretoria_af=? "
                    + "where funcionario_matfunc_af=? and referencia_af=?");
            pst.setString(1, EnumSituacao.RECEBIDA.toString());
            pst.setInt(2, mat);
            pst.setString(3, referencia);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/AlteraAvaliacaoGerencia.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public void AlteraAvaliacaoPresidencia(int mat, String referencia) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update alteracaofrequencia set situacaopresidencia_af=? "
                    + "where funcionario_matfunc_af=? and referencia_af=?");
            pst.setString(1, EnumSituacao.RECEBIDA.toString());
            pst.setInt(2, mat);
            pst.setString(3, referencia);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/AlteraAvaliacaoGerencia.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public void AlteraAvaliacaoRH(int mat, String referencia) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update alteracaofrequencia set situacaorh_af=? "
                    + "where funcionario_matfunc_af=? and referencia_af=?");
            pst.setString(1, EnumSituacao.RECEBIDA.toString());
            pst.setInt(2, mat);
            pst.setString(3, referencia);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/AlteraAvaliacaoGerencia.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public int QtdeAvaliarGerencia(int idGerencia) {
        conecta.conexao();
        int numero = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where gerenciaavaliadora_af=" + idGerencia
                + " and situacaogerencia_af='RECEBIDA'");
        try {
            if (conecta.rs.first()) {
                do {
                    numero++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequencia/QtdeAvaliarGerencia.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return numero;
    }

    public int QtdeAvaliadosGerencia(int idGerencia) {
        conecta.conexao();
        int numero = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where gerenciaavaliadora_af=" + idGerencia
                + " and (situacaogerencia_af='ABONADA' or situacaogerencia_af='NAO_ABONADA')");
        try {
            if (conecta.rs.first()) {
                do {
                    numero++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/QtdeAvaliadosGerencia.\n");
        } finally {
            conecta.desconecta();
        }
        return numero;
    }

    public int QtdeAvaliarDepartamento(int idDepartamento) {
        conecta.conexao();
        int numero = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where departamentoavaliador_af=" + idDepartamento
                + " and situacaodepartamento_af='RECEBIDA'");
        try {
            if (conecta.rs.first()) {
                do {
                    numero++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequencia/QtdeAvaliarDepartamento.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return numero;
    }

    public int QtdeAvaliadosDepartamento(int idDepartamento) {
        conecta.conexao();
        int numero = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where departamentoavaliador_af=" + idDepartamento
                + " and (situacaodepartamento_af='ABONADA' or situacaodepartamento_af='NAO_ABONADA')");
        try {
            if (conecta.rs.first()) {
                do {
                    numero++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/QtdeAvaliadosDepartamento.\n");
        } finally {
            conecta.desconecta();
        }
        return numero;
    }

    public int QtdeAvaliarDiretoria(int idDiretoria) {
        conecta.conexao();
        int numero = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where diretoriaavaliadora_af=" + idDiretoria
                + " and situacaodiretoria_af='RECEBIDA'");
        try {
            if (conecta.rs.first()) {
                do {
                    numero++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequencia/QtdeAvaliarDiretoria.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return numero;
    }

    public int QtdeAvaliadosDiretoria(int idDiretoria) {
        conecta.conexao();
        int numero = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where diretoriaavaliadora_af=" + idDiretoria
                + " and (situacaodiretoria_af='ABONADA' or situacaodiretoria_af='NAO_ABONADA')");
        try {
            if (conecta.rs.first()) {
                do {
                    numero++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/QtdeAvaliadosDiretoria.\n");
        } finally {
            conecta.desconecta();
        }
        return numero;
    }

    public int QtdeAvaliarPresidencia() {
        conecta.conexao();
        int numero = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where situacaopresidencia_af='RECEBIDA'");
        //So existe 1 presidência e o Id é 1, então não precisa pegar o Id da Presidência
        try {
            if (conecta.rs.first()) {
                do {
                    numero++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequencia/QtdeAvaliarPresidência.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return numero;
    }

    public int QtdeAvaliadosPresidencia() {
        conecta.conexao();
        int numero = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where"
                + " situacaopresidencia_af='ABONADA' or situacaopresidencia_af='NAO_ABONADA'");
        try {
            if (conecta.rs.first()) {
                do {
                    numero++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/QtdeAvaliadosPresidência.\n");
        } finally {
            conecta.desconecta();
        }
        return numero;
    }

    public String DeletaAlteracao(int mat, String referencia) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Delete from alteracaofrequencia where funcionario_matfunc_af=?"
                    + " and referencia_af=?");
            pst.setInt(1, mat);
            pst.setString(2, referencia);
            pst.executeUpdate();

            LogModel log = new LogModel();
            log.setDescricao("Usuário Deletou Alteração de Frequencia. Referência: " + referencia + ", Matricula: " + mat);
            try {
                log.setIp(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException ex) {
                Logger.getLogger(Crud_Alteracao.class.getName()).log(Level.SEVERE, null, ex);
            }
            log.setMat(mat);
            log.setTipo("Alteração de frequência deletada");
            Crud_Log c = new Crud_Log();
            c.InsereLog(log);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro AlteracaoFrequencia/DeletarAlteracao, erro:\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return "Alteração de Frequência deletada com sucesso!";
    }

    public boolean verificaEventoEnviado(String referencia, int mat) {
        conecta.conexao();
        boolean existe = false;
        conecta.executaPesquisaSQL("Select * from repositorioAlteracao where alteracaofrequencia_matfunc_ra=" + mat
                + " and alteracaofrequencia_referencia_ra='" + referencia + "' and trava_ra=true");
        try {
            if (conecta.rs.first() == true) {
                existe = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/VerificaEvento.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return existe;
    }

    public void alteraStatus(String referencia, int mat) {
        if (verificaEventoEnviado(referencia, mat) == true) {
            ResetarAlteracao(mat, referencia);//Ou seja quando um evento é criado reseta todo o histórico da alteracaofrequencia. Eventos atualizados continuam normal
            conecta.conexao();
            PerfilModel p = new PerfilModel();
            GetPerfil g = new GetPerfil();
            p = g.preencherPerfil(mat);
            PreparedStatement pst;

            //##Verificação de Perfil
            //FuncionárioGerência e FuncionárioGercom e FuncionárioJurídico
            if ((p.isFuncionarioGerencia()) || (p.isFuncionarioGercom() || (p.isFuncionarioGerjur()))) {
                try {
                    pst = (PreparedStatement) conecta.conn.prepareStatement("Update alteracaofrequencia set situacaogerencia_af=?"
                            + " where funcionario_matfunc_af=? and referencia_af=?");
                    pst.setString(1, EnumSituacao.RECEBIDA.toString());
                    pst.setInt(2, mat);
                    pst.setString(3, referencia);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/AlteraStatus.\n" + ex);
                } finally {
                    conecta.desconecta();
                }
            } // fim funcionariogerencia e funcionariogercom

            //Funcionário Departamento, Gerente e Funcionário DPCI
            if ((p.isFuncionarioDpto()) || (p.isGerente()) || (p.isFuncionarioDpci()) || (p.isGerenteGercom())) {
                System.out.println(p.isFuncionarioDpto());
                System.out.println(p.isGerente());
                System.out.println(p.isFuncionarioDpci());
                try {
                    pst = (PreparedStatement) conecta.conn.prepareStatement("Update alteracaofrequencia set situacaodepartamento_af=?"
                            + " where funcionario_matfunc_af=? and referencia_af=?");
                    pst.setString(1, EnumSituacao.RECEBIDA.toString());
                    pst.setInt(2, mat);
                    pst.setString(3, referencia);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/AlteraStatus.\n" + ex);
                } finally {
                    conecta.desconecta();
                }

            }//Fim funcionário Departamento, gerente e funcionário DEPCI

            //Chefe, Funcionário Diretoria e Gerente Gercom e Gerente Gerjur
            if ((p.isChefe()) || (p.isFuncionarioDiretoria() || (p.isGerenteGerjur()))) {
                try {
                    pst = (PreparedStatement) conecta.conn.prepareStatement("Update alteracaofrequencia set situacaodiretoria_af=?"
                            + " where funcionario_matfunc_af=? and referencia_af=?");
                    pst.setString(1, EnumSituacao.RECEBIDA.toString());
                    pst.setInt(2, mat);
                    pst.setString(3, referencia);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/AlteraStatus.\n" + ex);
                } finally {
                    conecta.desconecta();
                }

            }//Fim Chefe, FuncionárioDiretoria e Gerente Gercom

            //ChefeDPCI e Diretor
            if ((p.isChefeDepci()) || (p.isDiretor())) {
                try {
                    pst = (PreparedStatement) conecta.conn.prepareStatement("Update alteracaofrequencia set situacaopresidencia_af=?"
                            + " where funcionario_matfunc_af=? and referencia_af=?");
                    pst.setString(1, EnumSituacao.RECEBIDA.toString());
                    pst.setInt(2, mat);
                    pst.setString(3, referencia);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/AlteraStatus.\n" + ex);
                } finally {
                    conecta.desconecta();
                }

            }//Fim ChefeDPCI e Diretor

        }
    }

    public int getPendênciasGerencia() {
        conecta.conexao();
        int n = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where situacaogerencia_af='RECEBIDA'");
        try {
            if (conecta.rs.first() == true) {
                do {
                    n++;
                } while (conecta.rs.next());
            }

        } catch (SQLException ex) {
//           JOptionPane.showMessageDialog(nul,"AlteracaoFrequenciaControl/GetPendenciasGerencias");
        } finally {
            conecta.desconecta();
        }
        return n;
    }

    public int getPendênciasDepartamento() {
        conecta.conexao();
        int n = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where situacaodepartamento_af='RECEBIDA'");
        try {
            if (conecta.rs.first() == true) {
                do {
                    n++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
//           JOptionPane.showMessageDialog(nul,"AlteracaoFrequenciaControl/GetPendenciasGerencias");
        } finally {
            conecta.desconecta();
        }
        return n;
    }

    public int getPendênciasDiretoria() {
        conecta.conexao();
        int n = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where situacaodiretoria_af='RECEBIDA'");
        try {
            if (conecta.rs.first() == true) {
                do {
                    n++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/GetPendenciasDiretoria.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        return n;
    }

    public int getPendênciasPresidencia() {
        conecta.conexao();
        int n = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where situacaopresidencia_af='RECEBIDA'");
        try {
            if (conecta.rs.first() == true) {
                do {
                    n++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
//           JOptionPane.showMessageDialog(nul,"AlteracaoFrequenciaControl/GetPendenciasGerencias");
        } finally {
            conecta.desconecta();
        }
        return n;
    }

    public int getNumeroRecebidasRH() {
        conecta.conexao();
        int n = 0;
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where situacaorh_af='RECEBIDA'");
        try {
            if (conecta.rs.first()) {
                do {
                    n++;
                } while (conecta.rs.next());
            }

        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "AdminControl/getNumeroRecebidas.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return n;
    }

    public void NotificaSetor(int mat) {
        PerfilModel p = new PerfilModel();
        GetPerfil g = new GetPerfil();
        p = g.preencherPerfil(mat);

        if ((p.isFuncionarioGerencia()) || (p.isFuncionarioGercom())) {//Funcionário de Gerência ou da Gercom
            GerenciaControl gerencia = new GerenciaControl();
            FuncionarioControl f = new FuncionarioControl();
            int id = f.getIdGerencia(mat);
            String nome = gerencia.getNome(id);
            String email = gerencia.getEmailPorNome(nome);
            EnvioEmail e = new EnvioEmail();
            System.out.println(nome);
            e.NotificaSetores(email, nome);
        }

        if ((p.isGerente()) || (p.isFuncionarioDpto()) || (p.isFuncionarioDpci()) || (p.isGerenteGercom())) {//Gerente, Funcionário Dpto e Funcionário Depci

            DepartamentoControl dpto = new DepartamentoControl();
            FuncionarioControl f = new FuncionarioControl();
            int id = f.getIdDepartamento(mat);
            String nome = dpto.getNome(id);
            String email = dpto.getEmailNomeDpto(nome);
            EnvioEmail e = new EnvioEmail();
            System.out.println(nome);
            e.NotificaSetores(email, nome);

        }

//        if (p.isGerenteGercom()) {//Gerente da Gercom
//            DiretoriaControl d = new DiretoriaControl();
//            FuncionarioControl f = new FuncionarioControl();
//            int id = f.getIdDiretoria(mat);
//            String nome = d.getNome(id);
//            String email = d.getEmailNomeDiretoria(nome);
//            EnvioEmail e = new EnvioEmail();
//            System.out.println(nome);
//            e.NotificaSetores(email, nome);
//
//        }
        if ((p.isChefe()) || (p.isFuncionarioDiretoria())) {//Chefe ou Funcionário de DIretoria
            DiretoriaControl d = new DiretoriaControl();
            FuncionarioControl f = new FuncionarioControl();
            int id = f.getIdDiretoria(mat);
            String nome = d.getNome(id);
            String email = d.getEmailNomeDiretoria(nome);
            EnvioEmail e = new EnvioEmail();
            System.out.println(nome);
            e.NotificaSetores(email, nome);
        }

        if ((p.isChefeDepci()) || (p.isDiretor())) {//Chefe do DEPCI ou diretores
            PresidenciaControl presidencia = new PresidenciaControl();
            FuncionarioControl f = new FuncionarioControl();
            int id = 1;
            String nome = "Presidência";
            String email = presidencia.getEmail(id);
            EnvioEmail e = new EnvioEmail();
            System.out.println(nome);
            e.NotificaSetores(email, nome);
        }

    }

    public boolean verificaConclusaoAlteracao(String referencia, int mat) {
        conecta.conexao();
        String situacaoRH = "";
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where funcionario_matfunc_af=" + mat + ""
                + " and referencia_af='" + referencia + "'");
        try {
            conecta.rs.first();
            situacaoRH = conecta.rs.getString("situacaoRH_AF");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/VerificaConclusaoAlteracao.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        if ("RECEBIDA".equals(situacaoRH)) {
            return true;
        } else {
            return false;
        }

    }

    public void ResetarAlteracao(int mat, String referencia) {
        conecta.conexao();

        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update alteracaofrequencia set SituacaoGerencia_AF=?, "
                    + "SituacaoDepartamento_AF=?, SituacaoDiretoria_AF=?, SituacaoRH_AF=?, SituacaoPresidencia_AF=? "
                    + "where funcionario_matfunc_af=? and referencia_af=?");
            pst.setString(1, EnumSituacao.NAO_RECEBIDA.toString());
            pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());
            pst.setString(3, EnumSituacao.NAO_RECEBIDA.toString());
            pst.setString(4, EnumSituacao.NAO_RECEBIDA.toString());
            pst.setString(5, EnumSituacao.NAO_RECEBIDA.toString());
            pst.setInt(6, mat);
            pst.setString(7, referencia);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/RetiraAlteracaoRH.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public boolean VerificaEventoEnviado(String referencia, int mat) {
        conecta.conexao();
        boolean sinal = false;
        int cont = 0;
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where alteracaofrequencia_matfunc_ra=" + mat + " and "
                + "alteracaofrequencia_referencia_ra='" + referencia + "'");
        try {
            if (conecta.rs.first() == true) {

                do {
                    boolean enviado = conecta.rs.getBoolean("trava_ra");
                    if (enviado == true) {
                        cont++;
                    }

                } while (conecta.rs.next());

            }

        } catch (SQLException ex) {
            Logger.getLogger(AlteracaoFrequenciaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cont > 0) {
            sinal = true;
        }
        return sinal;

    }

    public boolean TodosItensEnviados(String referencia, int mat) {
        conecta.conexao();
        boolean sinal = false;
        int cont = 0;
        int qtdeEventos = 0;
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where alteracaofrequencia_matfunc_ra=" + mat + " and "
                + "alteracaofrequencia_referencia_ra='" + referencia + "'");
        try {
            if (conecta.rs.first() == true) {

                do {
                    qtdeEventos++;
                    boolean enviado = conecta.rs.getBoolean("trava_ra");
                    if (enviado == true) {
                        cont++;

                    }

                } while (conecta.rs.next());

            }

        } catch (SQLException ex) {
            Logger.getLogger(AlteracaoFrequenciaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cont == qtdeEventos) {
            sinal = true;
        }
        return sinal;

    }

    public BackupModel CriaBackup(String referencia, int mat) {
        conecta.conexao();
        BackupModel b = new BackupModel();
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where funcionario_matfunc_af=" + mat
                + " and referencia_af='" + referencia + "'");
        try {
            conecta.rs.first();
            b.setReferencia(referencia);
            b.setMat(mat);
            b.setSituacaoGerencia(conecta.rs.getString("SituacaoGerencia_af"));
            b.setSituacaoDepartamento(conecta.rs.getString("SituacaoDepartamento_AF"));
            b.setSituacaoDiretoria(conecta.rs.getString("SituacaoDiretoria_AF"));
            b.setSituacaoPresidencia(conecta.rs.getString("SituacaoPresidencia_AF"));
            b.setSituacaoRH(conecta.rs.getString("SituacaoRH_AF"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/CriaBackup.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return b;
    }

    public void GravaBackupAlteracao(BackupModel b) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Insert into backupalteracao (alteracaofrequencia_funcionario_matfunc_bk, "
                    + "alteracaofrequencia_referencia_bk, situacaogerencia_bk, situacaodepartamento_bk, situacaodiretoria_bk, "
                    + "situacaoRH_BK, situacaopresidencia_bk) VALUES (?,?,?,?,?,?,?)");
            pst.setInt(1, b.getMat());
            pst.setString(2, b.getReferencia());
            System.out.println(b.getSituacaoGerencia());
            pst.setString(3, b.getSituacaoGerencia());
            pst.setString(4, b.getSituacaoDepartamento());
            pst.setString(5, b.getSituacaoDiretoria());
            pst.setString(6, b.getSituacaoRH());
            pst.setString(7, b.getSituacaoPresidencia());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/GravaBackupAlteracao.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public BackupModel BuscaBackup(String referencia, int mat) {
        conecta.conexao();
        BackupModel b = new BackupModel();
        conecta.executaPesquisaSQL("Select * from backupalteracao where alteracaofrequencia_funcionario_matfunc_bk=" + mat
                + " and alteracaofrequencia_referencia_bk='" + referencia + "'");
        try {
            conecta.rs.first();
            b.setMat(mat);
            b.setReferencia(referencia);
            b.setSituacaoGerencia(conecta.rs.getString("SituacaoGerencia_BK"));
            b.setSituacaoDepartamento(conecta.rs.getString("SituacaoDepartamento_BK"));
            b.setSituacaoDiretoria(conecta.rs.getString("SituacaoDiretoria_BK"));
            b.setSituacaoPresidencia(conecta.rs.getString("SituacaoPresidencia_BK"));
            b.setSituacaoRH(conecta.rs.getString("SituacaoRH_BK"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/RestauraBackup.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return b;
    }

    public void RestauraBackup(BackupModel b) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update AlteracaoFrequencia set SituacaoGerencia_AF=?, "
                    + "SituacaoDepartamento_AF=?, SituacaoDiretoria_AF=?, SituacaoPresidencia_AF=?, SituacaoRH_AF=?"
                    + " where funcionario_matfunc_af=? and referencia_af=?");
            pst.setString(1, b.getSituacaoGerencia());
            pst.setString(2, b.getSituacaoDepartamento());
            pst.setString(3, b.getSituacaoDiretoria());
            pst.setString(4, b.getSituacaoPresidencia());
            pst.setString(5, b.getSituacaoRH());
            pst.setInt(6, b.getMat());
            pst.setString(7, b.getReferencia());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/RestauraBackup.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public boolean verificaExistenciaAlteracao(int mat, String referencia) {
        boolean existe = false;
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where funcionario_matfunc_af=" + mat
                + " and referencia_af='" + referencia + "'");
        try {
            if (conecta.rs.first()) {
                existe = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/VerificaExistenciaAlteracao");
        } finally {
            conecta.desconecta();
        }
        return existe;
    }

    public boolean verificaEventosNaAlteracao(int mat, String referencia) {
        conecta.conexao();
        boolean existe = true;
        System.out.println("A referencia é "+referencia);
        conecta.executaPesquisaSQL("Select * from repositorioAlteracao where alteracaofrequencia_matfunc_ra=" + mat
                + " and alteracaofrequencia_referencia_ra='" + referencia + "'");
        try {
            if (conecta.rs.first() == true) {
                existe = true;
                System.out.println("Existe");
            } else {
                existe = false;
                 System.out.println("Não Existe");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "RepositorioControl/verificaEventosNaAlteracao.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return existe;
    }

}
