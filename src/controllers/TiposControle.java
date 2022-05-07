
package controllers;

import utils.ConectaBanco;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class TiposControle {
    ConectaBanco conecta = new ConectaBanco();
    
    public int getIdTipo (String tipo){
        conecta.conexao();
        int idTipo=0;
        conecta.executaPesquisaSQL("Select * from tipos where Descricao_T='"+tipo+"'");
        try {
            conecta.rs.first();
            idTipo=conecta.rs.getInt("idTipos_T");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"TiposControle/getIdTipo.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return idTipo;
    }
    
    public ArrayList <String> getListaTipos (){
        ArrayList <String> lista = new ArrayList();
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from Tipos");
        try {
            conecta.rs.first();
            do{
            lista.add(conecta.rs.getString("Descricao_T"));
            }while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"TiposControle/getListaTipos");
        } finally {
            conecta.desconecta();
        }
        return lista;
    }
    
    public String getNomeTipo (int id){
        String nomeTipo="";
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from tipos where idTipos_T="+id);
        try {
            conecta.rs.first();
            nomeTipo=conecta.rs.getString("Descricao_T");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"TiposControle/getNomeTipo.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return nomeTipo;
    }
    
}
