package controllers;

import email_.EnvioEmail;
import utils.ConectaBanco;
import utils.ValidaEmail;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DiretoriaControl {

    ConectaBanco conecta = new ConectaBanco();

    public int getIdDiretoria(String sigla) {
        int idDiretoria = 0;
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from diretoria where sigla_di='" + sigla + "'");
        try {
            conecta.rs.first();
            idDiretoria = conecta.rs.getInt("idDiretoria_di");
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null,"DiretoriaControl/getIdDiretoria.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return idDiretoria;
    }

    public int getIdDiretoriaPeloNome(String nome) {
        conecta.conexao();
        int id = 0;
        conecta.executaPesquisaSQL("Select * from diretoria where nomediretoria_di="
                + "'" + nome + "'");
        try {
            conecta.rs.first();
            id = conecta.rs.getInt("iddiretoria_di");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DiretoriaControl/getIdDiretoriaPeloNome.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return id;
    }

    public ArrayList<String> getListaDiretoria() {
        ArrayList<String> a = new ArrayList();
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from diretoria where sigla_di<>'Vinculação Indireta' order by sigla_di asc");
        try {
            conecta.rs.first();
            do {

                a.add(conecta.rs.getString("sigla_di"));

            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DiretoriaControl/getComboBoxDiretoria.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        a.add("PRESIDÊNCIA");
        return a;
    }

    public int getPresidencia(int id) {
        int presidencia = 0;
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from diretoria where idDiretoria_Di=" + id);
        try {
            conecta.rs.first();
            presidencia = conecta.rs.getInt("Presidencia_idPresidencia_Di");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DiretoriaControl/getPresidencia.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return presidencia;
    }

    public boolean validaLoginDiretoriaDefinitiva(String sigla, String senha) {
        boolean sinal = false;
        conecta.conexao();
        String senhaBanco = "";
        conecta.executaPesquisaSQL("Select * from diretoria where sigla_di='" + sigla + "'");
        try {
            conecta.rs.first();
            senhaBanco = conecta.rs.getString("SenhaDiretoria_Di");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DiretoriaControl/validaLoginDiretoria.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        if (senha.equals(senhaBanco)) {
            sinal = true;
        }
        return sinal;
    }

    public boolean logarProvisoria(int id, String senha) {
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from diretoria where idDiretoria_DI=" + id);
        String senhaBanco = "";
        boolean sinal = false;
        try {
            conecta.rs.first();
            senhaBanco = conecta.rs.getString("senhadiretoria_di");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ValidaLogin/LogarProvisoriaDiretoria.\n" + ex);
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

    public String getNome(int id) {
        conecta.conexao();
        String nome = "";
        conecta.executaPesquisaSQL("Select * from diretoria where idDiretoria_Di=" + id);
        try {
            conecta.rs.first();
            nome = conecta.rs.getString("nomeDiretoria_Di");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DiretoriaControl/getNome.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return nome;

    }

    public int getNumeroFuncionariosDiretoria(int id) {
        conecta.conexao();
        int numero = 0;
        conecta.executaPesquisaSQL("Select * from funcionario where diretoria_iddiretoria_f=" + id
                + " and departamento_iddepartamento_f=999");
        try {
            if (conecta.rs.first()) {
                do {
                    numero++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DiretoriaControl/NumeroFuncionarios.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return numero;
    }

    public String getSigla(int id) {
        conecta.conexao();
        String nome = "";
        conecta.executaPesquisaSQL("Select * from Diretoria where idDiretoria_Di=" + id);
        try {
            conecta.rs.first();
            nome = conecta.rs.getString("Sigla_DI");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DiretoriaControl/getSigla.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return nome;
    }

    public String BuscaPorCampoNome(String sigla) {
        conecta.conexao();
        String sql = "";
        FuncionarioControl f = new FuncionarioControl();

        sql = "Select * from diretoria where nomediretoria_di like '%"
                + sigla + "%' and nomediretoria_di<>'Vinculação Indireta'";

        return sql;
    }

    public boolean AlteraEmail(String sigla, String email) {
        conecta.conexao();
        boolean sinal=false;
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update Diretoria set email_di=? "
                    + "where sigla_di=?");
            pst.setString(1, email);
            pst.setString(2, sigla);
            pst.executeUpdate();
            sinal=true;
            JOptionPane.showMessageDialog(null,"E-mail alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DiretoriaControl/AlteraSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }

    public String getEmail(String sigla) {
        conecta.conexao();
        String email = "";
        conecta.executaPesquisaSQL("Select * from diretoria where sigla_di='" + sigla + "'");
        try {
            if (conecta.rs.first()) {
                email = conecta.rs.getString("email_di");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DiretoriaControl/getEmail.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return email;
    }

    public String getEmailNomeDiretoria(String nome) {
        conecta.conexao();
        String email = "";
        conecta.executaPesquisaSQL("Select * from diretoria where nomediretoria_di='" + nome + "'");
        try {
            if (conecta.rs.first()) {
                email = conecta.rs.getString("email_di");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DiretoriaControl/getEmailNomeDiretoria.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return email;
    }
    
    public void ResetaSenha(String sigla, String senha) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update Diretoria set senhadiretoria_di=? "
                    + "where sigla_di=?");
            pst.setString(1, senha);
            pst.setString(2, sigla);
            pst.executeUpdate();
            String email = getEmail(sigla);
            EnvioEmail envioEmail = new EnvioEmail();
            envioEmail.ResetaSenhaSetores(sigla, senha, email);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DiretoriaControl/AlteraSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public boolean AlteraSenha(int id, String senha) {
        conecta.conexao();
        boolean sinal = false;
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update Diretoria set senhadiretoria_di=? "
                    + "where iddiretoria_di=?");
            pst.setString(1, senha);
            pst.setInt(2, id);
            pst.executeUpdate();
            sinal = true;
            JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DiretoriaControl/AlteraSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }
    
    public boolean TrocaSenhaDiretoria(int id) {
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
    
    public boolean TrocaEmailDiretoria(String sigla) {
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

       
        coincide = AlteraEmail(sigla, mail1);

        return coincide;
    }

}
