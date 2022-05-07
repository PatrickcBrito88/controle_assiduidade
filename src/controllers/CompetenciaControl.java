
package controllers;

import utils.ConectaBanco;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class CompetenciaControl {
    
    ConectaBanco conecta = new ConectaBanco();
    
    public ArrayList<String> listaCompetencias (){
        ArrayList<String> lista = new ArrayList();
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from competencia");
        try {
            if (conecta.rs.first()){
                do {
                    lista.add(conecta.rs.getString("descricaocompetencia"));                    
                }while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CompetenciaControl/ListaCompetências.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }
}
