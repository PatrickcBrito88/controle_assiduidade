
package controllers;

import email_.EnvioEmail;
import utils.ConectaBanco;
import utils.ValidaEmail;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class PresidenciaControl {
    ConectaBanco conecta = new ConectaBanco();
    
    public boolean validaLoginPresidencia (String senha){
        boolean sinal=false;
        conecta.conexao();
        String senhaBanco="";
        conecta.executaPesquisaSQL("Select * from presidencia");
        try {
            conecta.rs.first();
            senhaBanco=conecta.rs.getString("senha_p");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"PresidenciaControl/validaLoginPresidencia.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        
        if (senha.equals(senhaBanco)){
            sinal=true;
        }
        return sinal;
    }
    
    public int getNumeroFuncionariosPresidencia() {
        conecta.conexao();
        int numero = 0;
        //So existe 1 presidência, por isso o valor de 1 direto
        conecta.executaPesquisaSQL("Select * from funcionario where presidencia_idpresidencia_f=1");
        try {
            if (conecta.rs.first()) {
                do {
                    numero++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "PresidenciaControl/NumeroFuncionarios.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return numero;
    }
    
    public boolean AlteraEmail(int id, String email) {
        conecta.conexao();
        boolean sinal=false;
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update Presidencia set email_p=? "
                    + "where idpresidencia_p=?");
            pst.setString(1, email);
            pst.setInt(2, id);
            pst.executeUpdate();
            sinal=true;
            JOptionPane.showMessageDialog(null,"E-mail alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "PresidenciaControl/AlteraSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }

    public String getEmail(int id) {
        conecta.conexao();
        String email = "";
        conecta.executaPesquisaSQL("Select * from presidencia where idpresidencia_p="+id);
        try {
            if (conecta.rs.first()) {
                email = conecta.rs.getString("email_p");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "PresidenciaControl/getEmail.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return email;
    }

    public void ResetaSenha(int id, String senha) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update Presidencia set senha_p=? "
                    + "where idpresidencia_p=?");
            pst.setString(1, senha);
            pst.setInt(2, id);
            pst.executeUpdate();
            String email = getEmail(id);
            
            EnvioEmail envioEmail = new EnvioEmail();
            envioEmail.ResetaSenhaSetores("Presidência", senha, email);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "PresidênciaControl/ResetaSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public boolean AlteraSenha(int id, String senha) {
        conecta.conexao();
        boolean sinal = false;
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update presidencia set senha_p=? "
                    + "where idpresidencia_p=?");
            pst.setString(1, senha);
            pst.setInt(2, id);
            pst.executeUpdate();
            sinal = true;
            JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "PresidênciaControl/AlteraSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }

    public boolean logarProvisoria(int id, String senha) {
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from presidencia where idpresidencia_p=" + id);
        String senhaBanco = "";
        boolean sinal = false;
        try {
            conecta.rs.first();
            senhaBanco = conecta.rs.getString("senha_p");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ValidaLogin/LogarProvisoriaPresidencia.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        if (senha.equals(senhaBanco)) {
            sinal = true;
        }
        return sinal;
    }

    public boolean AcessoProvisorio(int id) {
//        conecta.conexao();
        boolean coincide = false;
        boolean continua = false;
        boolean continua2 = false;
        //String senha = "";
        char[] password1 = null;
        char[] password2 = null;
        String senha1 = "";
        String senha2 = "";

        java.sql.PreparedStatement pst = null;

        do {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Por favor digite uma senha definitiva:");

            JPasswordField pass = new JPasswordField(10);
            panel.add(label);
            panel.add(pass);
            String[] options = new String[]{"Confirma", "Cancela"};
            int option = JOptionPane.showOptionDialog(null, panel, "Cadastro de nova senha",
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

            JPanel panel2 = new JPanel();
            JLabel label2 = new JLabel("Por favor repita a senha");
            JPasswordField pass2 = new JPasswordField(10);
            panel2.add(label2);
            panel2.add(pass2);
            String[] options2 = new String[]{"Confirma", "Cancela"};
            int option2 = JOptionPane.showOptionDialog(null, panel2, "Cadastro de nova senha",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[1]);
            if (option2 == 0) // Botão Confirma
            {
                password2 = pass2.getPassword();

            }
            senha1 = new String(password1);
            senha2 = new String(password2);

            if (senha1.equals(senha2)) {
                continua2 = true;
            } else {
                JOptionPane.showMessageDialog(null, "As senhas não coincidem!");
            }
        } while (continua2 == false); //se continua2 for true, significa que alterou a senha

        coincide = AlteraSenha(id, senha1);

        return coincide;
    }
    
     public boolean TrocaSenhaPresidencia(int id) {
//        conecta.conexao();
        boolean coincide = false;
        boolean continua = false;
        boolean continua2 = false;
        //String senha = "";
        char[] password1 = null;
        char[] password2 = null;
        String senha1 = "";
        String senha2 = "";

        java.sql.PreparedStatement pst = null;

        do {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Por favor digite uma nova senha");

            JPasswordField pass = new JPasswordField(10);
            panel.add(label);
            panel.add(pass);
            String[] options = new String[]{"Confirma", "Cancela"};
            int option = JOptionPane.showOptionDialog(null, panel, "Cadastro de nova senha",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[1]);
            if (option == 0) // Botão Confirma
            {
                password1 = pass.getPassword();

            }
            if (option == 1) // Botão Cancela
            {
               return false;

            }

            JPanel panel2 = new JPanel();
            JLabel label2 = new JLabel("Por favor repita a senha");
            JPasswordField pass2 = new JPasswordField(10);
            panel2.add(label2);
            panel2.add(pass2);
            String[] options2 = new String[]{"Confirma", "Cancela"};
            int option2 = JOptionPane.showOptionDialog(null, panel2, "Cadastro de nova senha",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[1]);
            if (option2 == 0) // Botão Confirma
            {
                password2 = pass2.getPassword();

            }
            senha1 = new String(password1);
            senha2 = new String(password2);

            if (senha1.equals(senha2)) {
                continua2 = true;
            } else {
                JOptionPane.showMessageDialog(null, "As senhas não coincidem!");
            }
        } while (continua2 == false); //se continua2 for true, significa que alterou a senha

       
        coincide = AlteraSenha(id, senha1);

        return coincide;
    }
     
     public boolean TrocaEmailPresidencia() {
//        conecta.conexao();
        boolean coincide = false;
        boolean continua = false;
        boolean continua2 = false;
        //String senha = "";
       
        String mail1 = "";
        String mail2 = "";

        java.sql.PreparedStatement pst = null;

        do {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Por favor digite um novo email");

            JTextField pass = new JTextField(10);
            panel.add(label);
            panel.add(pass);
            String[] options = new String[]{"Confirma", "Cancela"};
            int option = JOptionPane.showOptionDialog(null, panel, "Cadastro de novo e-mail",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[1]);
            if (option == 0) // Botão Confirma
            {
                mail1 = pass.getText();

            }
            if (option == 1) // Botão Cancela
            {
               return false;

            }

            JPanel panel2 = new JPanel();
            JLabel label2 = new JLabel("Por favor repita o email");
            JTextField pass2 = new JTextField(10);
            panel2.add(label2);
            panel2.add(pass2);
            String[] options2 = new String[]{"Confirma", "Cancela"};
            int option2 = JOptionPane.showOptionDialog(null, panel2, "Cadastro de novo e-mail",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[1]);
            if (option2 == 0) // Botão Confirma
            {
                mail2 = pass2.getText();

            }
            

            if (mail1.equals(mail2)) {
                ValidaEmail validaEmail = new ValidaEmail();
                if (validaEmail.validaEmail(mail1)==true){
                continua2 = true;
                } else {
                    continua2=false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Os e-mails não coincidem!");
            }
        } while (continua2 == false); //se continua2 for true, significa que alterou a senha

       
        coincide = AlteraEmail(1, mail1);

        return coincide;
    }
    
}
