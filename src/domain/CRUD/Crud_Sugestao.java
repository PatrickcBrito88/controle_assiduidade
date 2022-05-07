
package domain.CRUD;

import utils.ConectaBanco;
import com.mysql.jdbc.PreparedStatement;
import domain.Modelo.ErroModel;
import domain.Modelo.LogModel;
import domain.Modelo.SugestaoModel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import static java.time.LocalDate.now;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Crud_Sugestao {
 
    ConectaBanco conecta = new ConectaBanco();
    
    public void GravaSugestao (SugestaoModel sugestao){
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Insert into sugestoes (sugestoes_s, status_s, "
                    + "funcionario_matfunc_s, titulo_s, data_s) VALUES (?,?,?,?,now())");
            pst.setString(1, sugestao.getSugestao());
            pst.setBoolean(2, false);
            pst.setInt(3, sugestao.getMat());
            pst.setString(4, sugestao.getTitulo());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Sugestão registrada com sucesso!");
            
            LogModel log = new LogModel();
            log.setDescricao("Usuário reportou uma sugestão");
             try {
                log.setIp(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException ex) {
                Logger.getLogger(Crud_Alteracao.class.getName()).log(Level.SEVERE, null, ex);
            }
             log.setMat(sugestao.getMat());
             log.setTipo("Report de sugestão");
            Crud_Log c = new Crud_Log();
            c.InsereLog(log);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudSugestao/GravaSugestao.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        
    }
    
    public void alteraErro (boolean sinal, String sugestoes){
        //POR ENQUANTO VAI SER NO NOME DO ERRO, DEPOIS EU ALTERO PARA ID
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Update sugestoes set status_s=? where sugestoes_s=?");
            pst.setBoolean(1, sinal);
            pst.setString(2, sugestoes);
            pst.executeUpdate();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Crud_Erro/AlteraStatus.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        
    }
    
    public void DeletaSugestao (int id){
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Delete from sugestoes where idsugestoes_s=?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Crud_Sugestao/DeletaSugestao.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        
    }
    
}
