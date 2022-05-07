
package controllers;

import utils.ConectaBanco;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class PegaLocalizacaoControl {
    
    ConectaBanco conecta = new ConectaBanco();
    
    public String getSiglaGerencia (int idGerencia){
        conecta.conexao();
        String nomeGerencia="";
        conecta.executaPesquisaSQL("Select * from Gerencia where idGerencia_g="+idGerencia);
        try {
            conecta.rs.first();
            nomeGerencia=conecta.rs.getString("Sigla_G");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"PegaLocalizacao/getNomeGerencia.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return nomeGerencia;
    }
    
     public String getSiglaDepartamento (int idDepartamento){
        conecta.conexao();
        String nomeDepartamento="";
        conecta.executaPesquisaSQL("Select * from Departamento where idDepartamento_de="+idDepartamento);
        try {
            conecta.rs.first();
            nomeDepartamento=conecta.rs.getString("Sigla_De");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"PegaLocalizacao/getNomeDepartamento.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return nomeDepartamento;
    }
     
      public String getSiglaDiretoria (int idDiretoria){
        conecta.conexao();
        String nomeDiretoria="";
        conecta.executaPesquisaSQL("Select * from Diretoria where idDiretoria_Di="+idDiretoria);
        try {
            conecta.rs.first();
            nomeDiretoria=conecta.rs.getString("Sigla_Di");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"PegaLocalizacao/getNomeDiretoria.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return nomeDiretoria;
    }
      
      
}
