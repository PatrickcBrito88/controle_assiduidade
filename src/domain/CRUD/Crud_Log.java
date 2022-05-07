
package domain.CRUD;

import utils.ConectaBanco;
import com.mysql.jdbc.PreparedStatement;
import domain.Modelo.LogModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Crud_Log {
    
    ConectaBanco conecta = new ConectaBanco();
    
    public void InsereLog (LogModel log){
        conecta.conexao();
        PreparedStatement pst;
        
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Insert into log (funcionario_matfunc_l, log_descricao_l, "
                    + "log_tipo_l, log_ip_l, "
                    + "log_horario_l) VALUES (?,?,?,?,now())");
            pst.setInt(1, log.getMat());
            pst.setString(2, log.getDescricao());
            pst.setString(3, log.getTipo());
            pst.setString(4, log.getIp());
           pst.execute();
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Crud_Log/InsereLog.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        
    }
}
