
package controllers_tables;

import utils.ConectaBanco;
import domain.Model.ControleTabelas.ErroModelTabela;
import domain.Model.ControleTabelas.SugestaoModelTabela;
import domain.Modelo.ErroModel;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TabelaSugestaoControl {
    ConectaBanco conecta = new ConectaBanco();
    
    public ArrayList<SugestaoModelTabela> ListaSugestao (int mat){
        conecta.conexao();
        ArrayList<SugestaoModelTabela> lista = new ArrayList();
        conecta.executaPesquisaSQL("Select * from sugestoes where funcionario_matfunc_s="+mat);
        try {
            if (conecta.rs.first()==true){
                do{
                    SugestaoModelTabela e = new SugestaoModelTabela();
                    e.setId(conecta.rs.getInt("idsugestoes_s"));
                    e.setSugestao(conecta.rs.getString("Sugestoes_s"));
                    e.setMat(mat);
                    boolean status=(conecta.rs.getBoolean("status_s"));
                    if (status==true){
                        e.setStatus(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
                    } else {
                         e.setStatus(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
                    }
                    e.setTitulo(conecta.rs.getString("Titulo_s"));
                  
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    e.setData(formato.format(conecta.rs.getDate("Data_s")));
                    lista.add(e);                    
                }while (conecta.rs.next());
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"TabelaSugestaoControl/ListaSugestao.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }
}
