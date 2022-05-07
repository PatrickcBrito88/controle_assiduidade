package controllers;

import email_.EnvioEmail;
import utils.ConectaBanco;
import utils.ValidaEmail;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DepartamentoControl {

    ConectaBanco conecta = new ConectaBanco();

    public int getIdDepartamento(String sigla) {
        conecta.conexao();
        int id = 0;
        conecta.executaPesquisaSQL("Select * from departamento where sigla_de="
                + "'" + sigla + "'");
        try {
            conecta.rs.first();
            id = conecta.rs.getInt("idDepartamento_De");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/getIdDepartamento.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return id;
    }

    public int getIdDepartamentoPeloNome(String nome) {
        conecta.conexao();
        int id = 0;
        conecta.executaPesquisaSQL("Select * from departamento where nomedepartamento_de="
                + "'" + nome + "'");
        try {
            conecta.rs.first();
            id = conecta.rs.getInt("idDepartamento_de");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/getIdDepartamentoPeloNome.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return id;
    }

    public ArrayList<String> getListaDepartamento() {
        ArrayList<String> a = new ArrayList();
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from departamento where sigla_de<>'Vinculação Indireta' "
                + "and sigla_de<>'GABIN' order by sigla_de asc");
        try {
            conecta.rs.first();
            do {

                a.add(conecta.rs.getString("sigla_de"));

            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/getComboBoxDepartamento.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        return a;
    }
    
    public ArrayList<String> getListaDepartamentoCadastroFuncionario() {
        //Função de cima estava limitando o Gabin e não sei o motivo.
        //Como quero corrigir apenas o combo do cadastro funcionário
        //preferi criar outra função apenas para o cadastro de func.
        ArrayList<String> a = new ArrayList();
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from departamento where sigla_de<>'Vinculação Indireta' "
                + "order by sigla_de asc");
        try {
            conecta.rs.first();
            do {

                a.add(conecta.rs.getString("sigla_de"));

            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/getComboBoxDepartamento.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        return a;
    }
    
     public ArrayList<String> getListaGabinete() {
        ArrayList<String> a = new ArrayList();
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from departamento where nomedepartamento_de='Gabinete da Presidência'");
        try {
            conecta.rs.first();
            do {

                a.add(conecta.rs.getString("sigla_de"));

            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/getComboBoxDepartamento.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        return a;
    }

    public String getSigla(int id) {
        String sigla = "";
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from departamento where idDepartamento_De=" + id);
        try {
            conecta.rs.first();
            sigla = conecta.rs.getString("Sigla_De");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/getSigla.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return sigla;
    }

    public int getDiretoria(int id) {
        int diretoria = 0;
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from departamento where idDepartamento_De=" + id);
        try {
            conecta.rs.first();
            diretoria = conecta.rs.getInt("Diretoria_idDiretoria_De");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/getDiretoria.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return diretoria;
    }

    public int getPresidencia(int id) {
        int presidencia = 0;
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from departamento where idDepartamento_De=" + id);
        try {
            conecta.rs.first();
            presidencia = conecta.rs.getInt("Presidencia_idPresidencia_De");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/getPresidencia.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return presidencia;
    }

    public boolean validaLoginDepartamentoDefinitivo(String sigla, String senha) {
        boolean sinal = false;
        conecta.conexao();
        String senhaBanco = "";
        conecta.executaPesquisaSQL("Select * from departamento where sigla_de='" + sigla + "'");
        try {
            conecta.rs.first();
            senhaBanco = conecta.rs.getString("SenhaDepartamento_De");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/validaLoginDepartamento.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        if (senha.equals(senhaBanco)) {
            sinal = true;
        }
        return sinal;
    }

    public String getNome(int id) {
        conecta.conexao();
        String nome = "";
        conecta.executaPesquisaSQL("Select * from departamento where idDepartamento_de=" + id);
        try {
            conecta.rs.first();
            nome = conecta.rs.getString("nomeDepartamento_De");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/getNome.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return nome;

    }

    public int getNumeroFuncionariosDepartamento(int id) {
        conecta.conexao();
        int numero = 0;
        conecta.executaPesquisaSQL("Select * from funcionario where departamento_idDepartamento_f=" + id
                + " and gerencia_idgerencia_f=999");
        try {
            if (conecta.rs.first()) {
                do {
                    numero++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/NumeroFuncionarios.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return numero;
    }

    public String BuscaPorCampoNome(String sigla) {
        conecta.conexao();
        String sql = "";
        FuncionarioControl f = new FuncionarioControl();

        sql = "Select * from departamento where nomedepartamento_de like '%"
                + sigla + "%' and nomedepartamento_de<>'Vinculação Indireta'";

        return sql;
    }

    public boolean AlteraEmail(String sigla, String email) {
        conecta.conexao();
        boolean sinal=false;
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update Departamento set email_de=? "
                    + "where sigla_de=?");
            pst.setString(1, email);
            pst.setString(2, sigla);
            pst.executeUpdate();
            sinal=true;
            JOptionPane.showMessageDialog(null,"E-mail alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/AlteraSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }

    public String getEmail(String sigla) {
        conecta.conexao();
        String email = "";
        conecta.executaPesquisaSQL("Select * from departamento where sigla_de='" + sigla + "'");
        try {
            if (conecta.rs.first()) {
                email = conecta.rs.getString("email_de");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/getEmail.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return email;
    }
    
     public String getEmailNomeDpto(String nome) {
        conecta.conexao();
        System.out.println(nome);
        String email = "";
        conecta.executaPesquisaSQL("Select * from departamento where nomedepartamento_de='" + nome + "'");
        try {
            if (conecta.rs.first()) {
                email = conecta.rs.getString("email_de");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/getEmailNomeDpto.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return email;
    }

    public void ResetaSenha(String sigla, String senha) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update Departamento set senhadepartamento_de=? "
                    + "where sigla_de=?");
            pst.setString(1, senha);
            pst.setString(2, sigla);
            pst.executeUpdate();
            String email = getEmail(sigla);
            EnvioEmail envioEmail = new EnvioEmail();
            envioEmail.ResetaSenhaSetores(sigla, senha, email);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/ResetaSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public boolean AlteraSenha(int id, String senha) {
        conecta.conexao();
        boolean sinal = false;
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update Departamento set senhadepartamento_de=? "
                    + "where iddepartamento_de=?");
            pst.setString(1, senha);
            pst.setInt(2, id);
            pst.executeUpdate();
            sinal = true;
            JOptionPane.showMessageDialog(null, "Senha alterada com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DepartamentoControl/AlteraSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }

    public boolean logarProvisoria(int id, String senha) {
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from departamento where iddepartamento_de=" + id);
        String senhaBanco = "";
        boolean sinal = false;
        try {
            conecta.rs.first();
            senhaBanco = conecta.rs.getString("senhadepartamento_de");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ValidaLogin/LogarProvisoriaDepartamento.\n" + ex);
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
                JOptionPane.showMessageDialog(null, "As senhas não coincidem");
            }
        } while (continua2 == false); //se continua2 for true, significa que alterou a senha

        coincide = AlteraSenha(id, senha1);

        return coincide;
    }
    
    public int getIdPresidencia (int id){
        conecta.conexao();
        int idPresidencia=0;
        conecta.executaPesquisaSQL("Select * from departamento where idDepartamento_de="+id);
        try {
            conecta.rs.first();
             idPresidencia=conecta.rs.getInt("Presidencia_idPresidencia_De");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"DepartamentoControl/GetIdPresidencia");
        } finally{
            conecta.desconecta();
        }
        return idPresidencia;
    }
    
     public boolean TrocaSenhaDepartamento(int id) {
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
     
     public boolean TrocaEmailDepartamento(String sigla) {
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
