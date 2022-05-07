
package domain.CRUD;

import utils.ConectaBanco;
import com.mysql.jdbc.PreparedStatement;
import domain.Modelo.ErroModel;
import domain.Modelo.LogModel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import static java.time.LocalDate.now;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Crud_Erro {
 
    ConectaBanco conecta = new ConectaBanco();
    
    public void GravaErro (ErroModel erro){
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Insert into erros (erros_e, statuserro_e, "
                    + "funcionario_matfunc_e, titulo_e, data_e) VALUES (?,?,?,?,now())");
            pst.setString(1, erro.getErro());
            pst.setBoolean(2, false);
            pst.setInt(3, erro.getMat());
            pst.setString(4, erro.getTitulo());
            pst.execute();
            
             LogModel log = new LogModel();
            log.setDescricao("Usuário reportou um erro");
             try {
                log.setIp(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException ex) {
                Logger.getLogger(Crud_Alteracao.class.getName()).log(Level.SEVERE, null, ex);
            }
             log.setMat(erro.getMat());
             log.setTipo("Report de erro");
            Crud_Log c = new Crud_Log();
            c.InsereLog(log);
            
            JOptionPane.showMessageDialog(null,"Erro notificado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudErro/GravaErro.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        
    }
    
    public void alteraErro (boolean sinal, String erro){
        //POR ENQUANTO VAI SER NO NOME DO ERRO, DEPOIS EU ALTERO PARA ID
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Update erros set statuserro_e=? where erros_e=?");
            pst.setBoolean(1, sinal);
            pst.setString(2, erro);
            pst.executeUpdate();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Crud_Erro/AlteraStatus.\n"+ex);
        } finally {
            conecta.desconecta();
        }
    }
    
    public void deletaErro (int id){
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Delete from erros where iderros_e=?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Crud_Erro/DeletaErro.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        
    }
}
