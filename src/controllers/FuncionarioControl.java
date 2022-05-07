package controllers;

import email_.EnvioEmail;
import utils.ConectaBanco;
import com.mysql.jdbc.PreparedStatement;
import domain.Modelo.FuncionarioModel;
import domain.Modelo.PerfilModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class FuncionarioControl {

    ConectaBanco conecta = new ConectaBanco();

    public String chamaTabela(String setor) {
        String sql = "";
        GerenciaControl g = new GerenciaControl();
        DepartamentoControl dE = new DepartamentoControl();
        DiretoriaControl dI = new DiretoriaControl();
        char letra1 = setor.charAt(0);
        char letra2 = setor.charAt(1);

        if (setor.equals("SEM SETOR")) {
            sql = "Select * from funcionario where gerencia_idgerencia_f is null and "
                    + "departamento_iddepartamento_f is null and diretoria_iddiretoria_f is null"
                    + " and presidencia_idpresidencia_f is null";
        } else {

            if ((letra1 == 'G') && (letra2 == 'E')) {
                int idGerencia = g.getIdGerencia(setor);
                sql = "Select * from funcionario where Gerencia_idGerencia_F=" + idGerencia;
            } else {
                if (((letra1 == 'D') && (letra2 == 'E'))||((letra1 == 'G') && (letra2 == 'A')) || (letra1 == 'S') && (letra2 == 'E')) {
                    int idDepartamento = dE.getIdDepartamento(setor);
                    sql = "Select * from funcionario where departamento_iddepartamento_f=" + idDepartamento
                            + " and gerencia_idgerencia_f=999;";
                } else {
                    int idDiretoria = dI.getIdDiretoria(setor);
                    sql = "Select * from funcionario where diretoria_iddiretoria_f=" + idDiretoria + " and"
                            + " departamento_iddepartamento_f=999 and gerencia_idgerencia_f=999;";
                }

            }
        }
        return sql;
    }

    public String getCargo(int mat) {
        conecta.conexao();
        String cargo = "";
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f=" + mat);
        try {
            conecta.rs.first();
            cargo = conecta.rs.getString("cargo_f");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FuncionárioControl/getCargo.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return cargo;
    }

    public int getMat(String nome) {
        conecta.conexao();
        int id = 0;
        conecta.executaPesquisaSQL("Select * from funcionario where nome_F='" + nome + "'");
        try {
            conecta.rs.first();
            id = conecta.rs.getInt("matfunc_f");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FuncionárioControl/getMat.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return id;
    }

    public String BuscaPorCampoVinculacao(String nome, String vinculacao) {
        conecta.conexao();
        String sql = "";
        FuncionarioControl f = new FuncionarioControl();

        sql = "Select * from funcionario where nome_f like '%"
                + nome + "%' and gerencia_idgerencia_f is null "
                + " and departamento_iddepartamento_f is null and diretoria_iddiretoria_f"
                + " is null and presidencia_idpresidencia_f is null";

        return sql;
    }

    public String BuscaPorCampoNome(String nome) {
        conecta.conexao();
        String sql = "";
        FuncionarioControl f = new FuncionarioControl();

        sql = "Select * from funcionario where nome_f like '%"
                + nome + "%'";

        return sql;
    }

    public String getLocalizacao(int mat) {
        String localizacao = "";
        int idGerencia = 0;
        int idDepartamento = 0;
        int idDiretoria = 0;
        int idPresidencia = 0;

        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from funcionario where matFunc_F=" + mat);
        try {
            conecta.rs.first();
            idGerencia = conecta.rs.getInt("Gerencia_idGerencia_F");
            idDepartamento = conecta.rs.getInt("Departamento_idDepartamento_F");
            idDiretoria = conecta.rs.getInt("Diretoria_idDiretoria_F");
            idPresidencia = conecta.rs.getInt("Presidencia_idPresidencia_F");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FuncionarioControl/getLocalizacao.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        //departamento + presidencia = departamento
        //departamento + diretoria= departamento
        //diretoria = diretoria
        //gerencia + departamento= gerencia
        //gerencia + diretoria = gerencia
        PegaLocalizacaoControl pegaLocal = new PegaLocalizacaoControl();

        if ((idDepartamento != 999) && (idPresidencia != 999)) {
            localizacao = pegaLocal.getSiglaDepartamento(idDepartamento);
        }

        if ((idDepartamento != 999) && (idDiretoria != 999)) {
            localizacao = pegaLocal.getSiglaDepartamento(idDepartamento);
        }

        if ((idDiretoria != 999) && (idGerencia == 999) && (idDepartamento == 999)) {
            localizacao = pegaLocal.getSiglaDiretoria(idDiretoria);

        }

        if ((idGerencia != 999) && (idDepartamento != 999)) {
            localizacao = pegaLocal.getSiglaGerencia(idGerencia);

        }

        if ((idGerencia != 999) && (idDiretoria != 999)) {
            localizacao = pegaLocal.getSiglaGerencia(idGerencia);

        }
        return localizacao;
    }

    public String getNome(int mat) {
        conecta.conexao();
        String nome = "";
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f=" + mat);
        try {
            conecta.rs.first();
            nome = conecta.rs.getString("nome_f");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FuncionarioControl/getNome.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return nome;
    }

    public String getEmail(int mat) {
        conecta.conexao();
        String email = "";
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f=" + mat);
        try {
            conecta.rs.first();
            email = conecta.rs.getString("email_f");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FuncionarioControl/getEmail.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return email;
    }

    public FuncionarioModel getFuncionario(int mat) {
        FuncionarioModel f = new FuncionarioModel();
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f=" + mat);
        try {
            conecta.rs.first();
            f.setCargo(conecta.rs.getString("cargo_f"));
            f.setMat(mat);
            f.setNome(conecta.rs.getString("Nome_f"));
            f.setEmail(conecta.rs.getString("Email_f"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FuncionarioControl/GetFuncionario.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return f;
    }

    public ArrayList<String> getListaFuncionários() {
        conecta.conexao();
        ArrayList<String> lista = new ArrayList();
        conecta.executaPesquisaSQL("Select * from funcionario");
        try {
            conecta.rs.first();
            do {
                lista.add(conecta.rs.getString("nome_f"));

            } while (conecta.rs.next());

        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "FuncionarioControl/getListaFuncionario.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }

    public void ResetSenha(String nome) {
        conecta.conexao();
        ResetaSenhaControl resetSenha = new ResetaSenhaControl();
        String novaSenha = resetSenha.geraSenha();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update funcionario set senha_f=? where nome_f=?");
            pst.setString(1, novaSenha);
            pst.setString(2, nome);
            pst.executeUpdate();
            TrocaSenha(getMat(nome), true);//ALtera o troca senha para true
            EnvioEmail email = new EnvioEmail();
            email.ResetaSenha(nome, novaSenha);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FuncionárioControl/ResetSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }

    }

    public boolean AlteraSenha(int mat, String senha) {
        conecta.conexao();
        boolean sinal = false;
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update funcionario set senha_f=?, "
                    + "trocasenha_f=false where matfunc_f=?");
            pst.setString(1, senha);
            pst.setInt(2, mat);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Senha definitiva alterada com sucesso!");
            sinal = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FuncionárioControl/AlteraSenha.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }

    public boolean AcessoProvisorio(int mat) {
        conecta.conexao();
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

        FuncionarioControl f = new FuncionarioControl();
        coincide = f.AlteraSenha(mat, String.valueOf(password1));

        return coincide;
    }

    public boolean existeMatricula(int mat) {
        boolean existe = false;
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f=" + mat);
        try {
            if (conecta.rs.first()) {
                existe = true;
            }
        } catch (SQLException ex) {
//           JOptionPane.showMessageDialog(null,"FuncionarioControl/ExisteMatricula.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return existe;
    }

    public int getIdGerencia(int mat) {
        conecta.conexao();
        int id = 0;
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f=" + mat);
        try {
            conecta.rs.first();
            id = conecta.rs.getInt("Gerencia_idGerencia_F");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FuncionarioControl/GetIdGerencia.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return id;
    }

    public int getIdDepartamento(int mat) {
        conecta.conexao();
        int id = 0;
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f=" + mat);
        try {
            conecta.rs.first();
            id = conecta.rs.getInt("Departamento_idDepartamento_F");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FuncionarioControl/GetIdDepartamento.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return id;
    }

    public int getIdDiretoria(int mat) {
        conecta.conexao();
        int id = 0;
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f=" + mat);
        try {
            conecta.rs.first();
            id = conecta.rs.getInt("Diretoria_idDiretoria_F");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FuncionarioControl/GetIdDiretorian" + ex);
        } finally {
            conecta.desconecta();
        }
        return id;
    }

    public String getLotacao(int mat) {
        conecta.conexao();
        String lotacao = "";
        PerfilModel p = new PerfilModel();
        GetPerfil g = new GetPerfil();
        p = g.preencherPerfil(mat);
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f=" + mat);
        try {
            conecta.rs.first();

            if ((p.isGerente()) || (p.isFuncionarioGerencia()) || (p.isGerenteGercom()) || (p.isFuncionarioGercom())) {
               int idGerencia=conecta.rs.getInt("gerencia_idgerencia_f");
               GerenciaControl gerencia = new GerenciaControl();
               lotacao=gerencia.getNome(idGerencia);
            }
            
            if ((p.isChefe())||(p.isChefeDepci())||(p.isFuncionarioDpci())||(p.isFuncionarioDpto())){
                int idDepartamento=conecta.rs.getInt("Departamento_iddepartamento_f");
                DepartamentoControl departamento = new DepartamentoControl();
                lotacao=departamento.getNome(idDepartamento);
            }
            
            if(p.isDiretor()||(p.isFuncionarioDiretoria())){
                int idDiretoria=conecta.rs.getInt("diretoria_iddiretoria_f");
                DiretoriaControl diretoria = new DiretoriaControl();
                lotacao=diretoria.getNome(idDiretoria);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"FuncionarioControl/GetLotacao.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return lotacao;
    }
    
    public void TrocaSenha (int mat, boolean sinal){
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Update funcionario set trocasenha_f=? where matfunc_f=?");
            pst.setBoolean(1, sinal);
            pst.setInt(2, mat);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"FuncionarioControl/TrocaSenha.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        
    }
    
    public boolean verificaTrocaSenha(int mat){
        boolean sinal=false;
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from funcionario where matfunc_f="+mat);
        try {
            conecta.rs.first();
            sinal=conecta.rs.getBoolean("trocasenha_f");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"FuncionarioControl/VerificaTrocaSenha.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }

}
