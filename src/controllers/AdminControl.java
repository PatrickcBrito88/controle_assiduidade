package controllers;

import utils.ConectaBanco;
import domain.Modelo.AdminModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class AdminControl {

    ConectaBanco conecta = new ConectaBanco();

    public boolean LoginGerhum(String senha) {
        boolean sinal = false;
        String senhaBanco = "";

        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from administradores where idadmin_a=1");
        try {
            conecta.rs.first();
            senhaBanco = conecta.rs.getString("senha_a");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AdminControl/LoginGerhum.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        if (senhaBanco.equals(senha)) {
            sinal = true;
        }
        return sinal;
    }

    public boolean LoginAdmin(String senha) {
        boolean sinal = false;
        String senhaBanco = "";

        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from administradores where idadmin_a=2");
        try {
            conecta.rs.first();
            senhaBanco = conecta.rs.getString("senha_a");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AdminControl/LoginAdmin.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        if (senhaBanco.equals(senha)) {
            sinal = true;
        }
        return sinal;
    }

    public boolean LogarGerhum() {
        boolean sinal=false;
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Digite a senha de Administrador");
        JPasswordField pass = new JPasswordField(10);
        char[] password1 = null;
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"Confirma", "Cancela"};
        int option = JOptionPane.showOptionDialog(null, panel, "Login Administrador",
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[1]);
        if (option == 0) // Botão Confirma
        {
            password1 = pass.getPassword();

        }
        if (option == 1) // Botão Cancela
        {
            System.exit(1);

        }
        
        String senha = new String(password1);
        
        return LoginGerhum(senha);
    }

}
