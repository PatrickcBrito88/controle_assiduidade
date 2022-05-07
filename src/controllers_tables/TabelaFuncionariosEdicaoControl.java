package controllers_tables;

import utils.ConectaBanco;
import domain.Modelo.FuncionarioModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TabelaFuncionariosEdicaoControl {

    ConectaBanco conecta = new ConectaBanco();

    public ArrayList<FuncionarioModel> getListaFuncionario() {
        ArrayList<FuncionarioModel> lista = new ArrayList();
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from funcionario");
        try {
            if (conecta.rs.first() == true) {

                do {
                    FuncionarioModel f = new FuncionarioModel();
                    f.setCargo(conecta.rs.getString("cargo_f"));
                    f.setMat(conecta.rs.getInt("MatFunc_F"));
                    f.setNome(conecta.rs.getString("nome_f"));
                    f.setEmail(conecta.rs.getString("email_f"));
                    lista.add(f);
                } while (conecta.rs.next());

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TabelaFuncionariosControl/getListaFuncionarios.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }
    
    public ArrayList<FuncionarioModel> getListaFuncionarioBusca(String sql) {
        ArrayList<FuncionarioModel> lista = new ArrayList();
        conecta.conexao();
        conecta.executaPesquisaSQL(sql);
        try {
            if (conecta.rs.first() == true) {

                do {
                    FuncionarioModel f = new FuncionarioModel();
                    f.setCargo(conecta.rs.getString("cargo_f"));
                    f.setMat(conecta.rs.getInt("MatFunc_F"));
                    f.setNome(conecta.rs.getString("nome_f"));
                    f.setEmail(conecta.rs.getString("email_f"));
                    lista.add(f);
                } while (conecta.rs.next());

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TabelaFuncionariosControl/getListaFuncionarios.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }

}
