
package domain.CRUD;

import domain.Modelo.DepartamentoModel;
import domain.Modelo.GerenciaModel;
import utils.ConectaBanco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Crud_Departamento {
    ConectaBanco conecta = new ConectaBanco();
    
    
    public void CadastraDepartamento (DepartamentoModel departamento){
       conecta.conexao();
       PreparedStatement pst;
       
        try {
            pst=conecta.conn.prepareStatement("Insert into departamento (nomeDepartamento_De, "
                    + "diretoria_idDiretoria_de, sigla_de, presidencia_idpresidencia_de, email_de) VALUES (?,?,?,?,?)");
            pst.setString(1, departamento.getNome());
            pst.setInt(2, departamento.getIdDir());
            pst.setString(3, departamento.getSigla());
            pst.setInt(4, departamento.getPresidencia());
            pst.setString(5, departamento.getEmail());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Departamento gravado!");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"CrudDepartamento/CadastraDepartamento.\n"+ex);
        } finally{
            conecta.desconecta();
        }
    }
    
    public void AlteraDepartamento (DepartamentoModel departamento){
        conecta.conexao();
        com.mysql.jdbc.PreparedStatement pst;
        
        try {
            pst=(com.mysql.jdbc.PreparedStatement) conecta.conn.prepareStatement 
        ("Update departamento set nomeDepartamento_De=?, Diretoria_iddiretoria_De=?,"
                + " sigla_de=?, presidencia_idpresidencia_de=?, email_de=? where idDepartamento_De?) VALUES (?,?,?,?,?,?)");
            pst.setString (1,departamento.getNome());
            pst.setInt(2, departamento.getIdDir());
            pst.setString(3, departamento.getSigla());
            pst.setInt(4, departamento.getPresidencia());
            pst.setString(5, departamento.getEmail());
            pst.setInt(6, departamento.getIdDep());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Departamento alterado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudDepartamento/ALteraDepartamento.\n"+ ex);
        } finally {
            conecta.desconecta();
        }
    }
     
    public void ExcluiDepartamento (int idDpto){
        conecta.conexao();
        java.sql.PreparedStatement pst;
        try {
            pst=conecta.conn.prepareStatement("Delete from departamento where idDepartamento_De=?");
            pst.setInt(1, idDpto);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Departamento deletado! ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudDepartamento/ExcluiDepartamento.\n"+ ex);
        } finally {
            conecta.desconecta();
        }
     }
    
    public void EditaDepartamento (DepartamentoModel d){
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=conecta.conn.prepareStatement("Update departamento set nomeDepartamento_De=?, sigla_de=?, email_de=?"
                    + " where iddepartamento_de=?");
            pst.setString(1, d.getNome());
            pst.setString(2, d.getSigla());
            pst.setString(3, d.getEmail());
            pst.setInt(4, d.getIdDep());
            pst.executeUpdate();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"CrudDepartamento/EditaDepartamento.\n"+ex);
        } finally {
            conecta.desconecta();
        }
    }
    
}
