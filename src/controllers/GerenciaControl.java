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

public class GerenciaControl {

    ConectaBanco conecta = new ConectaBanco();

    public int getIdGerencia(String sigla) {
        conecta.conexao();
        int id = 0;
        conecta.executaPesquisaSQL("Select * from gerencia where sigla_g='" + sigla + "'");

        try {
            conecta.rs.first();
            id = conecta.rs.getInt("idGerencia_g");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/getIdGerencia.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return id;
    }

    public int getIdGerenciaPeloNome(String nome) {
        conecta.conexao();
        int id = 0;
        conecta.executaPesquisaSQL("Select * from gerencia where nomegerencia_g="
                + "'" + nome + "'");
        try {
            conecta.rs.first();
            id = conecta.rs.getInt("idGerencia_g");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/getIdGerenciaPeloNome.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return id;
    }

    public ArrayList<String> getListaGerencias() {
        ArrayList<String> a = new ArrayList();
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from gerencia where sigla_g<>'Vinculação Indireta' order by sigla_g asc ");
        try {
            conecta.rs.first();
            do {

                a.add(conecta.rs.getString("sigla_g"));

            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/getComboBoxGerencia.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        return a;
    }

    public String getSigla(int id) {
        String sigla = "";
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from gerencia where idGerencia_g=" + id);
        try {
            conecta.rs.first();
            sigla = conecta.rs.getString("Sigla_G");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/getSigla.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return sigla;
    }

    public int getDepartamento(int id) {
        int departamento = 0;
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from gerencia where idGerencia_G=" + id);
        try {
            conecta.rs.first();
            departamento = conecta.rs.getInt("Departamento_idDepartamento_G");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/getDepartamento.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return departamento;
    }

    public int getDiretoria(int id) {
        int diretoria = 0;
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from gerencia where idGerencia_G=" + id);
        try {
            conecta.rs.first();
            diretoria = conecta.rs.getInt("Diretoria_idDiretoria_G");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/getDiretoria.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        System.out.println("Iddiretoria: "+diretoria);
        return diretoria;
    }

    public int getPresidencia(int id) {
        int presidencia = 0;
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from gerencia where idGerencia_G=" + id);
        try {
            conecta.rs.first();
            presidencia = conecta.rs.getInt("Presidencia_IdPresidencia_G");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/getPresidencia.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return presidencia;
    }

    public boolean validaLoginGerenciaDefinitivo(String sigla, String senha) {
        boolean sinal = false;
        conecta.conexao();
        String senhaBanco = "";
        conecta.executaPesquisaSQL("Select * from gerencia where sigla_g='" + sigla + "'");
        try {
            conecta.rs.first();
            senhaBanco = conecta.rs.getString("SenhaGerencia_G");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaCotrol/validaLoginGerencia.\n" + ex);
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
        conecta.executaPesquisaSQL("Select * from gerencia where idGerencia_G=" + id);
        try {
            conecta.rs.first();
            nome = conecta.rs.getString("nomeGerencia_G");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/getNome.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return nome;

    }

    public int getNumeroFuncionariosGerencia(int id) {
        conecta.conexao();
        int numero = 0;
        conecta.executaPesquisaSQL("Select * from funcionario where gerencia_idgerencia_f=" + id);
        try {
            if (conecta.rs.first()) {
                do {
                    numero++;
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/NumeroFuncionarios.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return numero;
    }

    public String BuscaPorCampoNome(String sigla) {
        conecta.conexao();
        String sql = "";
        FuncionarioControl f = new FuncionarioControl();

        sql = "Select * from gerencia where nomegerencia_g like '%"
                + sigla + "%' and nomegerencia_g<>'Vinculação Indireta'";

        return sql;
    }

    public boolean AlteraEmail(String sigla, String email) {
        conecta.conexao();
        boolean sinal=false;
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update gerencia set email_g=? "
                    + "where sigla_g=?");
            pst.setString(1, email);
            pst.setString(2, sigla);
            pst.executeUpdate();
            sinal=true;
            JOptionPane.showMessageDialog(null,"E-mail alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/AlteraSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }
    
     public String getEmail(String sigla) {
        conecta.conexao();
        String email = "";
        conecta.executaPesquisaSQL("Select * from gerencia where sigla_g='" + sigla + "'");
        try {
            if (conecta.rs.first()) {
                email = conecta.rs.getString("email_g");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/getEmail.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return email;
    }
     
     public String getEmailPorNome(String nome) {
        conecta.conexao();
        String email = "";
        conecta.executaPesquisaSQL("Select * from gerencia where nomegerencia_g='" + nome + "'");
        try {
            if (conecta.rs.first()) {
                email = conecta.rs.getString("email_g");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/getEmailPorNome.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return email;
    }
     
     public void ResetaSenha(String sigla, String senha) {
        conecta.conexao();
        
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update Gerencia set senhagerencia_g=? "
                    + "where sigla_g=?");
            pst.setString(1, senha);
            pst.setString(2, sigla);
            pst.executeUpdate();
            
            String email = getEmail(sigla);
            System.out.println(email);
            EnvioEmail envioEmail = new EnvioEmail();
            envioEmail.ResetaSenhaSetores(sigla, senha, email);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/ResetaSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }
     
     public boolean AlteraSenha(int id, String senha) {
        conecta.conexao();
        boolean sinal=false;
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update gerencia set senhagerencia_g=? "
                    + "where idgerencia_g=?");
            pst.setString(1, senha);
            pst.setInt(2, id);
            pst.executeUpdate();
            sinal=true;
            JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
           } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "GerenciaControl/AlteraSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }
     
      public boolean logarProvisoria (int id, String senha){
       conecta.conexao();
       conecta.executaPesquisaSQL("Select * from gerencia where idgerencia_g="+id);
       String senhaBanco="";
       boolean sinal=false;
        try {
            conecta.rs.first();
            senhaBanco=conecta.rs.getString("senhagerencia_g");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ValidaLogin/LogarProvisoriaGerencia.\n"+ex);       
        } finally {
            conecta.desconecta();
        }
        
        if (senha.equals(senhaBanco)){
            sinal= true;
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

      public boolean TrocaSenhaGerencia(int id) {
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
      
      public boolean TrocaEmailGerencia(String sigla) {
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
