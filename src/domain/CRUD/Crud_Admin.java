
package domain.CRUD;

import utils.ConectaBanco;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author patrick
 */
public class Crud_Admin {
    
    ConectaBanco conecta = new ConectaBanco();
    
    public void AlteraSenhaGerhum (String senha){
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Update administradores set senha_a=? where idadmin_a=?");
            pst.setString(1, senha);
            pst.setInt(2, 1);
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudAdmin/AlteraSenhaGerhum");
        } finally {
            conecta.desconecta();
        }
    }
    
    public void AlteraSenhaAdmin (String senha){
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Update administradores set senha_a=? where idadmin_a=?");
            pst.setString(1, senha);
            pst.setInt(2, 2);
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudAdmin/AlteraSenhaAdmin");
        } finally {
            conecta.desconecta();
        }
    }
    
   
    
}
