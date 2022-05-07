
package domain.CRUD;

import controllers.GerenciaControl;
import domain.Modelo.GerenciaModel;
import utils.ConectaBanco;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Crud_Gerencia {
    ConectaBanco conecta = new ConectaBanco();
        
    public void CadastraGerencia (GerenciaModel gerencia){
        conecta.conexao();
        PreparedStatement pst;
        
        try {
            pst= (PreparedStatement) conecta.conn.prepareStatement ("Insert into gerencia (nomeGerencia_G,"
                    + " Departamento_idDepartamento_G, Diretoria_idDiretoria_G, sigla_G, "
                    + "Presidencia_IdPresidencia_G, email_g) VALUES (?,?,?,?,?,?)");
            pst.setString(1, gerencia.getNome());
            pst.setInt(2, gerencia.getDepartamento());
            pst.setInt(3, gerencia.getDiretoria());
            pst.setString(4, gerencia.getSigla());
            pst.setInt(5, gerencia.getPresidencia());
            pst.setString(6, gerencia.getEmail());
            pst.execute();
            JOptionPane.showMessageDialog (null,"Gerência Cadastrada!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudGerencia/CadastraGerencia.\n"+ ex);
        } finally {
            conecta.desconecta();
        }
    }
    
    public void AlteraGerencia (GerenciaModel gerencia){
        conecta.conexao();
        PreparedStatement pst;
        
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement ("Update Gerencia set NomeGerencia_G=?,"
                    + " Departamento_idDepartamento_G=?, Diretoria_idDiretoria_G=?, sigla_G=?, Presidencia_idPresidencia_G=? "
                    + "email_g=? where idGerencia_G?) VALUES (?,?,?,?,?)");
            pst.setString (1,gerencia.getNome());
            pst.setInt(2, gerencia.getDepartamento());
            pst.setInt(3, gerencia.getDiretoria());
            pst.setString(4, gerencia.getSigla());
            pst.setInt(5, gerencia.getIdGerencia());
            pst.setInt(6, gerencia.getPresidencia());
            pst.setString(7, gerencia.getEmail());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Gerência alterada!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudGerencia/AlteraGerencia.\n"+ ex);
        } finally {
            conecta.desconecta();
        }
    }
    
    public void ExcluiGerencia (int idGerencia){
         conecta.conexao();
        java.sql.PreparedStatement pst;
        try {
            pst=conecta.conn.prepareStatement("Delete from gerencia where idGerencia=?");
            pst.setInt(1, idGerencia);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Gerência deletada! ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudGerencia/ExcluiGerencia.\n"+ ex);
        } finally {
            conecta.desconecta();
        }
     }
    
    public void EditaGerencia (GerenciaModel g){
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Update gerencia set nomegerencia_g=?, sigla_g=?, "
                    + "email_g=? where idGerencia_g=?");
            pst.setString(1, g.getNome());
            pst.setString(2, g.getSigla());
            pst.setString(3, g.getEmail());
            pst.setInt(4, g.getIdGerencia());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudGerencia/EditaGerencia.\n"+ex);
        } finally{
            conecta.desconecta();
        }
    }
    
}
