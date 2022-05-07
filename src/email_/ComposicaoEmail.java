
package email_;

import utils.ConectaBanco;
import domain.Modelo.EmailModelo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ComposicaoEmail {
    ConectaBanco conecta = new ConectaBanco();
    
    public EmailModelo ComporEmail (int mat){
        conecta.conexao();
        EmailModelo emailModelo = new EmailModelo();
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f="+mat);
        try {
            conecta.rs.first();
            emailModelo.setEmailDestinatario(conecta.rs.getString("email_f"));
            emailModelo.setNome(conecta.rs.getString("nome_f"));
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ComposicaoEmail/ComporEmail.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return emailModelo;
    }
}
