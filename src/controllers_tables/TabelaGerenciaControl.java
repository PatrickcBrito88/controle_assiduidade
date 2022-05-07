
package controllers_tables;

import controllers.DepartamentoControl;
import controllers.DiretoriaControl;
import controllers.GerenciaControl;
import utils.ConectaBanco;
import domain.Model.ControleTabelas.TabelaGerenciaModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TabelaGerenciaControl {
    
    ConectaBanco conecta = new ConectaBanco();
    
    public ArrayList<TabelaGerenciaModel> getListaGerencias(){
        conecta.conexao();
        ArrayList<TabelaGerenciaModel> lista = new ArrayList();
       conecta.executaPesquisaSQL("Select * from gerencia where nomegerencia_g<>'Vinculação Indireta'");
        try {
            if (conecta.rs.first()==true){
                do{
                   TabelaGerenciaModel t = new TabelaGerenciaModel();
                   t.setNome(conecta.rs.getString("nomeGerencia_G"));
                   t.setSigla(conecta.rs.getString("sigla_g"));
                   t.setEmail(conecta.rs.getString("email_g"));
                   int idDepartamento=conecta.rs.getInt("Departamento_idDepartamento_G");
                   int idDiretoria=conecta.rs.getInt("Diretoria_iddiretoria_G");
                   if (idDepartamento!=999){
                       GerenciaControl g = new GerenciaControl();
                       DepartamentoControl dE = new DepartamentoControl();
                       String vinculacao=dE.getSigla(g.getDepartamento(conecta.rs.getInt("idGerencia_G")));
                       t.setVinculacao(vinculacao);
                   } else {
                       DiretoriaControl dI = new DiretoriaControl();
                       GerenciaControl g = new GerenciaControl();
                       String vinculacao=dI.getNome(g.getDiretoria(conecta.rs.getInt("Diretoria_iddiretoria_g")));
                       t.setVinculacao(vinculacao);
                   }
                  
                   lista.add(t);             
                }while (conecta.rs.next());
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TabelaGerenciaControl/getListaGerencias.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }
    
    public ArrayList<TabelaGerenciaModel> getListaGerenciasPorCampo(String sql){
        conecta.conexao();
        ArrayList<TabelaGerenciaModel> lista = new ArrayList();
       conecta.executaPesquisaSQL(sql);
        try {
            if (conecta.rs.first()==true){
                do{
                   TabelaGerenciaModel t = new TabelaGerenciaModel();
                   t.setNome(conecta.rs.getString("nomeGerencia_G"));
                   t.setSigla(conecta.rs.getString("sigla_g"));
                   t.setEmail(conecta.rs.getString("email_g"));
                   int idDepartamento=conecta.rs.getInt("Departamento_idDepartamento_G");
                   int idDiretoria=conecta.rs.getInt("Diretoria_iddiretoria_G");
                   if (idDepartamento!=999){
                       GerenciaControl g = new GerenciaControl();
                       DepartamentoControl dE = new DepartamentoControl();
                       String vinculacao=dE.getSigla(g.getDepartamento(conecta.rs.getInt("idGerencia_G")));
                       t.setVinculacao(vinculacao);
                   } else {
                       DiretoriaControl dI = new DiretoriaControl();
                       GerenciaControl g = new GerenciaControl();
                       String vinculacao=dI.getNome(g.getDiretoria(conecta.rs.getInt("Diretoria_iddiretoria_g")));
                       t.setVinculacao(vinculacao);
                   }
                  
                   lista.add(t);             
                }while (conecta.rs.next());
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TabelaGerenciaControl/getListaGerencias.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }
    
}
