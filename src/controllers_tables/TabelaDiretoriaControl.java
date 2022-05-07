
package controllers_tables;

import utils.ConectaBanco;
import domain.Model.ControleTabelas.TabelaDiretoriaModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class TabelaDiretoriaControl {
    
    ConectaBanco conecta = new ConectaBanco();
    
    public ArrayList<TabelaDiretoriaModel> getListaDiretoria (){
        conecta.conexao();
        ArrayList<TabelaDiretoriaModel> lista = new ArrayList();
        conecta.executaPesquisaSQL("Select * from diretoria where nomediretoria_di<>'Vinculação Indireta'");
        try {
            if (conecta.rs.first()==true){
                do {
                    TabelaDiretoriaModel t = new TabelaDiretoriaModel();
                    t.setEmail(conecta.rs.getString("email_di"));
                    t.setNome(conecta.rs.getString("nomediretoria_di"));
                    t.setSigla(conecta.rs.getString("sigla_di"));
                    t.setVinculacao("Presidência");
                    lista.add(t);                    
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TabelaDiretoriaControl/getListaDiretorias.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }
    
    public ArrayList<TabelaDiretoriaModel> getListaDiretoriaBuscaCampo (String sql){
        conecta.conexao();
        ArrayList<TabelaDiretoriaModel> lista = new ArrayList();
        conecta.executaPesquisaSQL(sql);
        try {
            if (conecta.rs.first()==true){
                do {
                    TabelaDiretoriaModel t = new TabelaDiretoriaModel();
                    t.setEmail(conecta.rs.getString("email_di"));
                    t.setNome(conecta.rs.getString("nomediretoria_di"));
                    t.setSigla(conecta.rs.getString("sigla_di"));
                    t.setVinculacao("Presidência");
                    lista.add(t);                    
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TabelaDiretoriaControl/getListaDiretorias.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }
    
}
