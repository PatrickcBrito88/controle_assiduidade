package controllers_tables;

import controllers.DepartamentoControl;
import controllers.DiretoriaControl;
import controllers.FuncionarioControl;
import controllers.GerenciaControl;
import utils.ConectaBanco;
import domain.Model.ControleTabelas.AlteracoesAvaliarMenuModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AlteracoesAvaliarControl {

    ConectaBanco conecta = new ConectaBanco();

    public ArrayList<AlteracoesAvaliarMenuModel> pegaListaAlteracoesAvaliar(String local, String sigla) {
        conecta.conexao();
        ArrayList<AlteracoesAvaliarMenuModel> listaExterna = new ArrayList();

        if (local.equals("Gerência")) {
            ArrayList<AlteracoesAvaliarMenuModel> lista = new ArrayList();
            GerenciaControl g = new GerenciaControl();
            int id = g.getIdGerencia(sigla);

            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where gerenciaavaliadora_af=" + id
                    + " and situacaogerencia_af='RECEBIDA'");
            try {
                conecta.rs.first();
                do {
                    AlteracoesAvaliarMenuModel item = new AlteracoesAvaliarMenuModel();
                    item.setMat(conecta.rs.getInt("funcionario_matfunc_af"));
                    FuncionarioControl f = new FuncionarioControl();
                    item.setNome(f.getNome(item.getMat()));
                    item.setReferencia(conecta.rs.getString("Referencia_af"));
                    lista.add(item);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "AlteracoesAvaliarControl/GetLista.\n" + ex);
            } finally {
                conecta.desconecta();
            }
            listaExterna = lista;
          
        } //fim gerência

        if (local.equals("Departamento")) {
            ArrayList<AlteracoesAvaliarMenuModel> lista = new ArrayList();
            DepartamentoControl dE = new DepartamentoControl();
            int id = dE.getIdDepartamento(sigla);

            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where departamentoavaliador_af=" + id
                    + " and situacaodepartamento_af='RECEBIDA'");
            try {
                conecta.rs.first();
                do {
                    AlteracoesAvaliarMenuModel item = new AlteracoesAvaliarMenuModel();
                    item.setMat(conecta.rs.getInt("funcionario_matfunc_af"));
                    FuncionarioControl f = new FuncionarioControl();
                    item.setNome(f.getNome(item.getMat()));
                    item.setReferencia(conecta.rs.getString("Referencia_af"));
                    lista.add(item);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "AlteracoesAvaliarControl/GetLista.\n" + ex);
            } finally {
                conecta.desconecta();
            }
            listaExterna = lista;
        } //fim departamento

        if (local.equals("Diretoria")) {
            ArrayList<AlteracoesAvaliarMenuModel> lista = new ArrayList();
            DiretoriaControl dI = new DiretoriaControl();
            int id = dI.getIdDiretoria(sigla);

            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where diretoriaavaliadora_af=" + id
                    + " and situacaodiretoria_af='RECEBIDA'");
            try {
                conecta.rs.first();
                do {
                    AlteracoesAvaliarMenuModel item = new AlteracoesAvaliarMenuModel();
                    item.setMat(conecta.rs.getInt("funcionario_matfunc_af"));
                    FuncionarioControl f = new FuncionarioControl();
                    item.setNome(f.getNome(item.getMat()));
                    item.setReferencia(conecta.rs.getString("Referencia_af"));
                    lista.add(item);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "AlteracoesAvaliarControl/GetLista.\n" + ex);
            } finally {
                conecta.desconecta();
            }
            listaExterna = lista;
        } //fim diretoria

       
        if (local.equals("Presidência")) {
            ArrayList<AlteracoesAvaliarMenuModel> lista = new ArrayList();
             conecta.executaPesquisaSQL("Select * from alteracaofrequencia where presidenciaavaliadora_af=1"
                    + " and situacaopresidencia_af='RECEBIDA'");
            try {
                conecta.rs.first();
                do {
                     AlteracoesAvaliarMenuModel item = new AlteracoesAvaliarMenuModel();
                    item.setMat(conecta.rs.getInt("funcionario_matfunc_af"));
                    FuncionarioControl f = new FuncionarioControl();
                    item.setNome(f.getNome(item.getMat()));
                    item.setReferencia(conecta.rs.getString("Referencia_af"));
                    lista.add(item);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "AlteracoesAvaliarControl/GetLista.\n" + ex);
            } finally {
                conecta.desconecta();
            }
            listaExterna = lista;
        } //fim presidencia

        return listaExterna;
    }
    
     public ArrayList<AlteracoesAvaliarMenuModel> pegaListaAlteracoesAvaliadas(String local, String sigla) {
        conecta.conexao();
        ArrayList<AlteracoesAvaliarMenuModel> listaExterna = new ArrayList();

        if (local.equals("Gerência")) {
            ArrayList<AlteracoesAvaliarMenuModel> lista = new ArrayList();
            GerenciaControl g = new GerenciaControl();
            int id = g.getIdGerencia(sigla);

            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where gerenciaavaliadora_af=" + id
                    + " and situacaogerencia_af='ABONADA' or situacaogerencia_af='NAO_ABONADA'");
            try {
                conecta.rs.first();
                do {
                    AlteracoesAvaliarMenuModel item = new AlteracoesAvaliarMenuModel();
                    item.setMat(conecta.rs.getInt("funcionario_matfunc_af"));
                    FuncionarioControl f = new FuncionarioControl();
                    item.setNome(f.getNome(item.getMat()));
                    item.setReferencia(conecta.rs.getString("Referencia_af"));
                    lista.add(item);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "AlteracoesAvaliarControl/GetLista.\n" + ex);
            } finally {
                conecta.desconecta();
            }
            listaExterna = lista;
          
        } //fim gerência

        if (local.equals("Departamento")) {
            ArrayList<AlteracoesAvaliarMenuModel> lista = new ArrayList();
            DepartamentoControl dE = new DepartamentoControl();
            int id = dE.getIdDepartamento(sigla);

            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where departamentoavaliador_af=" + id
                    + " and situacaodepartamento_af='ABONADA' or situacaodepartamento_af='NAO_ABONADA'");
            try {
                conecta.rs.first();
                do {
                    AlteracoesAvaliarMenuModel item = new AlteracoesAvaliarMenuModel();
                    item.setMat(conecta.rs.getInt("funcionario_matfunc_af"));
                    FuncionarioControl f = new FuncionarioControl();
                    item.setNome(f.getNome(item.getMat()));
                    item.setReferencia(conecta.rs.getString("Referencia_af"));
                    lista.add(item);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "AlteracoesAvaliarControl/GetLista.\n" + ex);
            } finally {
                conecta.desconecta();
            }
            listaExterna = lista;
        } //fim departamento

        if (local.equals("Diretoria")) {
            ArrayList<AlteracoesAvaliarMenuModel> lista = new ArrayList();
            DiretoriaControl dI = new DiretoriaControl();
            int id = dI.getIdDiretoria(sigla);

            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where diretoriaavaliadora_af=" + id
                    + " and situacaodiretoria_af='ABONADA' or situacaodiretoria_af='NAO_ABONADA'");
            try {
                conecta.rs.first();
                do {
                    AlteracoesAvaliarMenuModel item = new AlteracoesAvaliarMenuModel();
                    item.setMat(conecta.rs.getInt("funcionario_matfunc_af"));
                    FuncionarioControl f = new FuncionarioControl();
                    item.setNome(f.getNome(item.getMat()));
                    item.setReferencia(conecta.rs.getString("Referencia_af"));
                    lista.add(item);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "AlteracoesAvaliarControl/GetLista.\n" + ex);
            } finally {
                conecta.desconecta();
            }
            listaExterna = lista;
        } //fim diretoria

        if (local.equals("Presidência")) {
            ArrayList<AlteracoesAvaliarMenuModel> lista = new ArrayList();
            
            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where presidenciaavaliadora_af=1"
                    + " and situacaopresidencia_af='ABONADA' or situacaopresidencia_af='NAO_ABONADA'");
            try {
                conecta.rs.first();
                do {
                    AlteracoesAvaliarMenuModel item = new AlteracoesAvaliarMenuModel();
                    item.setMat(conecta.rs.getInt("funcionario_matfunc_af"));
                    FuncionarioControl f = new FuncionarioControl();
                    item.setNome(f.getNome(item.getMat()));
                    item.setReferencia(conecta.rs.getString("Referencia_af"));
                    lista.add(item);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "AlteracoesAvaliarControl/GetLista.\n" + ex);
            } finally {
                conecta.desconecta();
            }
            listaExterna = lista;
        } //fim presidencia

        return listaExterna;
    }
     
}
