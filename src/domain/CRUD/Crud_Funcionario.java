
package domain.CRUD;

import domain.Modelo.FuncionarioModel;
import domain.Modelo.RemanejaModel;
import utils.ConectaBanco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Crud_Funcionario {
    ConectaBanco conecta = new ConectaBanco();
        
    public void CadastraFuncionario (FuncionarioModel funcionario){
        conecta.conexao();
        PreparedStatement pst;
        
        try {
            pst=conecta.conn.prepareStatement ("Insert into Funcionario (matFunc_F, Nome_F,"
                    + " Gerencia_idGerencia_F, Diretoria_idDiretoria_F, Departamento_"
                    + "idDepartamento_F, Presidencia_idPresidencia_F, Cargo_F, Senha_F, email_f) VALUES (?,?,?,?,?,?,?,?,?)");
            pst.setInt(1,funcionario.getMat());
            pst.setString(2, funcionario.getNome());
            pst.setInt(3, funcionario.getGerencia());
            pst.setInt(4, funcionario.getDiretoria());
            pst.setInt(5, funcionario.getDepartamento());
            pst.setInt(6, funcionario.getPresidencia());
            pst.setString(7, funcionario.getCargo());
            pst.setString(8, funcionario.getSenha());
            pst.setString(9, funcionario.getEmail());
            pst.execute();
//            JOptionPane.showMessageDialog(null,"Funcionário cadastrado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudFuncionário/CadastraFuncionario.\n"+ ex);
        } finally {
            conecta.desconecta();
        }
        
    }
    
     public void AlteraFuncionario (FuncionarioModel funcionario){
        conecta.conexao();
        PreparedStatement pst;
        
        try {
            pst=conecta.conn.prepareStatement ("Update Funcionario set Nome_F=?,"
                    + " Gerencia_idGerencia_F=?, Diretoria_idDiretoria_F=?,"
                    + " Departamento_idDepartamento_F=?, Presidencia_idPresidencia_F=?"
                    + " cargo_f=?, senha_f=? where matFunc_F=?) VALUES"
                    + " (?,?,?,?,?,?,?,?)");
            pst.setString (1,funcionario.getNome());
            pst.setInt(2, funcionario.getGerencia());
            pst.setInt(3, funcionario.getDiretoria());
            pst.setInt(4, funcionario.getDepartamento());
            pst.setInt(5, funcionario.getPresidencia());
            pst.setString(6, funcionario.getCargo());
            pst.setString(7, funcionario.getSenha());
            pst.setInt(8, funcionario.getMat());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Funcionário alterado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudFuncionário/AlteraFuncionário.\n"+ ex);
        } finally {
            conecta.desconecta();
        }
     }
     
     public void ExcluiFuncionario (int mat){
         conecta.conexao();
        PreparedStatement pst;
        try {
            pst=conecta.conn.prepareStatement("Delete from funcionario where matFunc_F=?");
            pst.setInt(1, mat);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário deletado! ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"CrudFuncionário/ExcluiFuncionario.\n"+ ex);
        } finally {
            conecta.desconecta();
        }
     }
     
     public void RemanejaFuncionario (RemanejaModel r){
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=conecta.conn.prepareStatement("Update funcionario set Gerencia_idGerencia_F=?"
                    + ", Departamento_idDepartamento_F=?, Diretoria_idDiretoria_F=?, Presidencia_idPresidencia_F=?"
                    + " where matFunc_F=?");
            pst.setInt(1, r.getIdGerencia());
            pst.setInt(2, r.getIdDepartamento());
            pst.setInt(3, r.getIdDiretoria());
            pst.setInt(4, r.getIdPresidencia());
            pst.setInt(5, r.getMat());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Remanejado com sucesso");
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null,"Crud_Funcionário/RemanejaFuncionário.\n"+ex);
        } finally {
            conecta.desconecta();
        }
     }
     
       public void EditaFuncionario (FuncionarioModel f){
          conecta.conexao();
          com.mysql.jdbc.PreparedStatement pst;
        try {
            pst=(com.mysql.jdbc.PreparedStatement) conecta.conn.prepareStatement("Update funcionario set nome_f=?, cargo_f=?, email_f=? where matfunc_f=?");
            pst.setString(1, f.getNome());
            pst.setString(2, f.getCargo());
            pst.setString(3, f.getEmail());
            pst.setInt(4, f.getMat());
            pst.executeUpdate();
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"FuncionarioControl/EditaFuncionario.\n"+ex);
        } finally {
            conecta.desconecta();
        }
      }

}
