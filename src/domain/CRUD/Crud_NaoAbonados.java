
package domain.CRUD;

import controllers.RepositorioControl;
import utils.ConectaBanco;
import com.mysql.jdbc.PreparedStatement;
import domain.Modelo.NaoAbonadoModel;
import domain.Modelo.RepositorioModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Crud_NaoAbonados {
    ConectaBanco conecta = new ConectaBanco();
    
    public void InsereNaoAbonado (NaoAbonadoModel n){
        conecta.conexao();
        PreparedStatement pst;
        RepositorioModel r = new RepositorioModel();
        RepositorioControl rC = new RepositorioControl();
        r=rC.getRepositorio(n.getIdRepositorio());//Copia o IdRepositorio
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Insert into RepositorioNaoAbonado (AlteracaoFrequencia_matfunc_rna,"
                    + " alteracaofrequencia_referencia_rna, setornaoabonado_rna, motivonaoabonado_rna, idtipo_rna, descricaoevento_rna, dataevento_rna)"+
                    "VALUES (?,?,?,?,?,?,?)");
            pst.setInt(1, r.getMatFunc());
            pst.setString(2, r.getReferencia());
            pst.setString(3, n.getSetorNaoAbonado());
            pst.setString(4, n.getMotivoNaoAbono());
            pst.setInt(5, r.getIdTipo());
            pst.setString(6, r.getDescricao());
            pst.setDate(7, r.getDataEvento());
            //pst.setBinaryStream(8, r.getAnexo());
            pst.execute();
            rC.deletaEvento(r.getIdRepositorio());
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Crud_NaoAbonado/InsereNaoAbonado.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        
    }
    
    
}
