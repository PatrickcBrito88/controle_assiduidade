package controllers_tables;

import controllers.DepartamentoControl;
import controllers.DiretoriaControl;
import controllers.GerenciaControl;
import utils.ConectaBanco;
import domain.Model.ControleTabelas.TabelaDepartamentoModel;
import domain.Model.ControleTabelas.TabelaGerenciaModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TabelaDepartamentoControl {

    ConectaBanco conecta = new ConectaBanco();

    public ArrayList<TabelaDepartamentoModel> getListaDepartamento() {
        ArrayList<TabelaDepartamentoModel> lista = new ArrayList();
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from departamento where nomedepartamento_de<>'Vinculação Indireta'");
        try {
            if (conecta.rs.first() == true) {
                do {
                    TabelaDepartamentoModel t = new TabelaDepartamentoModel();
                    t.setNome(conecta.rs.getString("nomedepartamento_de"));
                    t.setEmail(conecta.rs.getString("email_de"));
                    t.setSigla(conecta.rs.getString("sigla_de"));
                    int idDiretoria = conecta.rs.getInt("Diretoria_idDiretoria_De");

                    if (idDiretoria != 999) {
                        DiretoriaControl dI = new DiretoriaControl();
                        DepartamentoControl dE = new DepartamentoControl();
                        String vinculacao = dI.getSigla(idDiretoria);
                        t.setVinculacao(vinculacao);
                    } else {
                        t.setVinculacao("Presidência");
                    }
                    lista.add(t);
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TabelaDepartamentoControl/getListaDepartamento.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }

    public ArrayList<TabelaDepartamentoModel> getListaDepartamentoPorCampo(String sql) {
        ArrayList<TabelaDepartamentoModel> lista = new ArrayList();
        conecta.conexao();
        conecta.executaPesquisaSQL(sql);
        try {
            if (conecta.rs.first() == true) {
                do {
                    TabelaDepartamentoModel t = new TabelaDepartamentoModel();
                    t.setNome(conecta.rs.getString("nomedepartamento_de"));
                    t.setEmail(conecta.rs.getString("email_de"));
                    t.setSigla(conecta.rs.getString("sigla_de"));
                    int idDiretoria = conecta.rs.getInt("Diretoria_idDiretoria_De");

                    if (idDiretoria != 999) {
                        DiretoriaControl dI = new DiretoriaControl();
                        DepartamentoControl dE = new DepartamentoControl();
                        String vinculacao = dI.getSigla(idDiretoria);
                        t.setVinculacao(vinculacao);
                    } else {
                        t.setVinculacao("Presidência");
                    }
                    lista.add(t);
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TabelaDepartamentoControl/getListaDepartamento.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }

}
