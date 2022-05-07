
package domain.CRUD;

import domain.Modelo.DiretoriaModel;
import utils.ConectaBanco;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Crud_Diretoria {
   ConectaBanco conecta = new ConectaBanco();
  
   public void CadastraDiretoria (DiretoriaModel diretoria){
      PreparedStatement pst;
      conecta.conexao();
      
       try {
           pst=(PreparedStatement) conecta.conn.prepareStatement("Insert into diretoria (NomeDiretoria_DI,"
                   + " sigla_di, presidencia_idpresidencia_di, email_di) VALUES (?,?,?,?)");
           pst.setString(1, diretoria.getNome());
           pst.setString(2, diretoria.getSigla());
           pst.setInt(3, diretoria.getPresidencia());
           pst.setString(4, diretoria.getEmail());
           pst.execute();
           JOptionPane.showMessageDialog(null, "Diretoria Cadastrada!");
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "CrudDepartamento/CadastraDiretoria.\n"+ex);
       } finally {
           conecta.desconecta();
       }
   }
   
   public void AlteraDiretoria (DiretoriaModel diretoria){
       conecta.conexao();
       PreparedStatement pst;
       
       try {
           pst=(PreparedStatement) conecta.conn.prepareStatement("Update diretoria set NomeDiretoria_DI=?, Sigla_Di=?, "
                   + "presidencia_idpresidencia_di=?, email_di=? where idDiretoria_Di=?");
           pst.setString(1, diretoria.getNome());
           pst.setString(2, diretoria.getSigla());
           pst.setInt(3, diretoria.getPresidencia());
            pst.setString(4, diretoria.getEmail());
           pst.setInt(5, diretoria.getIdDir());
            pst.executeUpdate();
           JOptionPane.showMessageDialog(null,"Diretoria alterada!");
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"CrudDepartamento/CadastraDiretoria.\n"+ex);
       } finally {
           conecta.desconecta();
       }
   }
   
   public void ExcluiDiretoria (int idDiretoria){
        conecta.conexao();
        java.sql.PreparedStatement pst;
        try {
            pst=conecta.conn.prepareStatement("Delete from diretoria where idDiretoria_Di=?");
            pst.setInt(1, idDiretoria);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Diretoria deletada! ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudDiretoria/ExcluiDiretoria.\n"+ ex);
        } finally {
            conecta.desconecta();
        }
   }
   
   public void EditaDiretoria (DiretoriaModel d){
       conecta.conexao();
       PreparedStatement pst;
       try {
           pst=(PreparedStatement) conecta.conn.prepareStatement("Update diretoria set nomediretoria_di=?, sigla_di=?, "
                   + "email_di=? where iddiretoria_di=?");
           pst.setString(1, d.getNome());
           pst.setString(2, d.getSigla());
           pst.setString(3, d.getEmail());
           pst.setInt(4, d.getIdDir());
           pst.executeUpdate();
       } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "CrudDiretoria/EditaDiretoria.\n"+ex);
       } finally {
           conecta.desconecta();
       }
       
   }

}
