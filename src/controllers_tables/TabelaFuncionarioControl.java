
package controllers_tables;

import controllers.DepartamentoControl;
import controllers.DiretoriaControl;
import controllers.GerenciaControl;
import utils.ConectaBanco;
import domain.Model.ControleTabelas.FuncionarioTabelaModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TabelaFuncionarioControl {
    ConectaBanco conecta = new ConectaBanco();
    
    public ArrayList<FuncionarioTabelaModel> getLista (String local, String sigla){
        conecta.conexao();
        ArrayList<FuncionarioTabelaModel> listaExterna = new ArrayList();

        if (local.equals("Gerência")){
            
            GerenciaControl g = new GerenciaControl();
            int id=g.getIdGerencia(sigla);
            ArrayList<FuncionarioTabelaModel> lista = new ArrayList();
            conecta.executaPesquisaSQL("Select * from funcionario where gerencia_idgerencia_f="+id);
            try {
                conecta.rs.first();
                do{
                    FuncionarioTabelaModel item = new FuncionarioTabelaModel();
                    item.setMat(conecta.rs.getInt("matfunc_f"));
                    item.setEmail(conecta.rs.getString("email_f"));
                    item.setNome(conecta.rs.getString("nome_f"));
                    lista.add(item);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null,"TabelaFuncionarioControl/getLista.\n"+ex);
            } finally {
                conecta.desconecta();
            }
            listaExterna=lista;
        } //fim gerencia
        
        if (local.equals("Departamento")){
            
            DepartamentoControl dE = new DepartamentoControl();
            int id=dE.getIdDepartamento(sigla);
            ArrayList<FuncionarioTabelaModel> lista = new ArrayList();
            conecta.executaPesquisaSQL("Select * from funcionario where departamento_iddepartamento_f="+id
            +" and gerencia_idgerencia_f=999");
            try {
                conecta.rs.first();
                do{
                    FuncionarioTabelaModel item = new FuncionarioTabelaModel();
                    item.setMat(conecta.rs.getInt("matfunc_f"));
                    item.setEmail(conecta.rs.getString("email_f"));
                    item.setNome(conecta.rs.getString("nome_f"));
                    lista.add(item);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null,"TabelaFuncionarioControl/getLista.\n"+ex);
            } finally {
                conecta.desconecta();
            }
            listaExterna=lista;
        } //fim departamento
        
        if (local.equals("Diretoria")){
            
            DiretoriaControl dI = new DiretoriaControl();
            int id=dI.getIdDiretoria(sigla);
            ArrayList<FuncionarioTabelaModel> lista = new ArrayList();
            conecta.executaPesquisaSQL("Select * from funcionario where diretoria_iddiretoria_f="+id
            +" and departamento_iddepartamento_f=999");
            try {
                conecta.rs.first();
                do{
                    FuncionarioTabelaModel item = new FuncionarioTabelaModel();
                    item.setMat(conecta.rs.getInt("matfunc_f"));
                    item.setEmail(conecta.rs.getString("email_f"));
                    item.setNome(conecta.rs.getString("nome_f"));
                    lista.add(item);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null,"TabelaFuncionarioControl/getLista.\n"+ex);
            } finally {
                conecta.desconecta();
            }
            listaExterna=lista;
        } //fim diretoria
        
        if (local.equals("Presidência")){
            
            DiretoriaControl dI = new DiretoriaControl();
            int id=dI.getIdDiretoria(sigla);
            ArrayList<FuncionarioTabelaModel> lista = new ArrayList();
            conecta.executaPesquisaSQL("Select * from funcionario where presidencia_idpresidencia_f=1");
            try {
                conecta.rs.first();
                do{
                    FuncionarioTabelaModel item = new FuncionarioTabelaModel();
                    item.setMat(conecta.rs.getInt("matfunc_f"));
                    item.setEmail(conecta.rs.getString("email_f"));
                    item.setNome(conecta.rs.getString("nome_f"));
                    lista.add(item);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null,"TabelaFuncionarioControl/getLista.\n"+ex);
            } finally {
                conecta.desconecta();
            }
            listaExterna=lista;
        } //fim Presidência
        
          return listaExterna;
    }
  
}
