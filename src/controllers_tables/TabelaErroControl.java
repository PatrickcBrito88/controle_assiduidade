
package controllers_tables;

import utils.ConectaBanco;
import domain.Model.ControleTabelas.ErroModelTabela;
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

public class TabelaErroControl {
    ConectaBanco conecta = new ConectaBanco();
    
    public ArrayList<ErroModelTabela> ListaErros (int mat){
        conecta.conexao();
        ArrayList<ErroModelTabela> lista = new ArrayList();
        conecta.executaPesquisaSQL("Select * from erros where funcionario_matfunc_e="+mat);
        try {
            if (conecta.rs.first()==true){
                do{
                    ErroModelTabela e = new ErroModelTabela();
                    e.setId(conecta.rs.getInt("idErros_e"));
                    e.setErro(conecta.rs.getString("Erros_e"));
                    e.setMat(mat);
                    boolean status=(conecta.rs.getBoolean("statuserro_e"));
                    if (status==true){
                        e.setStatus(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
                    } else {
                         e.setStatus(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
                    }
                    e.setTitulo(conecta.rs.getString("Titulo_e"));
                  
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    e.setData(formato.format(conecta.rs.getDate("Data_e")));
                    lista.add(e);                    
                }while (conecta.rs.next());
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"TabelaErroControl/ListaErros.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }
}
