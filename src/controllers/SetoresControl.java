package controllers;

import utils.ConectaBanco;
import domain.Modelo.FuncionarioModel;
import domain.Modelo.HierarquiaModel;
import domain.Modelo.NomeLocalModel;
import domain.Modelo.PerfilModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SetoresControl {

    ConectaBanco conecta = new ConectaBanco();

    public int getIdSetor(String local, String setor) {
        int id = 0;
        conecta.conexao();

        //Gerência
        if (local.equals("Gerência")) {
            conecta.executaPesquisaSQL("Select * from gerencia where nomeGerencia_g='" + setor + "'");
            try {
                conecta.rs.first();
                id = conecta.rs.getInt("idGerencia_G");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SetoresControl/getIdSetor.\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }

        //Departamento
        if (local.equals("Departamento")) {
            conecta.executaPesquisaSQL("Select * from departamento where nomedepartamento_de='" + setor + "'");
            try {
                conecta.rs.first();
                id = conecta.rs.getInt("idDepartamento_De");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SetoresControl/getIdSetor.\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }

        //Diretoria
        if (local.equals("Diretoria")) {
            conecta.executaPesquisaSQL("Select * from gerencia where nomeDiretoria_Di='" + setor + "'");
            try {
                conecta.rs.first();
                id = conecta.rs.getInt("idDiretoria_Di");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SetoresControl/getIdSetor.\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }
        return id;
    }

    public String geNomeLocal(int mat) {
        conecta.conexao();
        String nomeLocal = "";
        CalculaHierarquia c = new CalculaHierarquia();
        HierarquiaModel h = new HierarquiaModel();
        h = c.getHierarquiaFuncionario(mat);

        conecta.executaPesquisaSQL("Select * from getNomeLocal where matfunc_f=" + mat);
        try {
            conecta.rs.first();

            //Gerência
            if (h.getIdGerencia() != 999) {
                nomeLocal = conecta.rs.getString("nomegerencia_g");
            }

            //Departamento
            if ((h.getIdGerencia() == 999) && (h.getIdDepartamento() != 999)) {
                nomeLocal = conecta.rs.getString("nomeDepartamento_De");
            }

            //Diretoria
            if ((h.getIdDepartamento() == 999) && (h.getIdDiretoria() != 999)) {
                nomeLocal = conecta.rs.getString("nomeDiretoria_Di");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SetoresControl/GetNomeLocal.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        return nomeLocal;
    }

    public NomeLocalModel getNomeLocal(int mat) {
        NomeLocalModel nomeLocal = new NomeLocalModel();
        conecta.conexao();
        HierarquiaModel h = new HierarquiaModel();
        CalculaHierarquia c = new CalculaHierarquia();
        h = c.getHierarquiaFuncionario(mat);
        GerenciaControl g = new GerenciaControl();
        DepartamentoControl dE = new DepartamentoControl();
        DiretoriaControl dI = new DiretoriaControl();
        PerfilModel p = new PerfilModel();
        GetPerfil gP = new GetPerfil();

        p = gP.preencherPerfil(mat);

        if (p.isFuncionarioGerencia()) {
            nomeLocal.setNomeGerencia(g.getNome(h.getIdGerencia()));
            nomeLocal.setNomeDepartamento(dE.getNome(h.getIdDepartamento()));
            nomeLocal.setNomeDiretoria(dI.getNome(dE.getDiretoria(h.getIdDepartamento())));
        }

        if (p.isGerente()) {
            nomeLocal.setNomeDepartamento(dE.getNome(h.getIdDepartamento()));
            nomeLocal.setNomeDiretoria(dI.getNome(dE.getDiretoria(h.getIdDepartamento())));
        }

        if (p.isFuncionarioGercom()) {
            nomeLocal.setNomeGerencia(g.getNome(h.getIdGerencia()));
            nomeLocal.setNomeDepartamento(dE.getNome(h.getIdDepartamento()));
            nomeLocal.setNomeDiretoria(dI.getNome(h.getIdDiretoria()));
            nomeLocal.setNomePresidencia("Presidência");
        }

        if (p.isGerenteGercom()) {
//            nomeLocal.setNomeDiretoria(dI.getNome(h.getIdDiretoria()));
            nomeLocal.setNomeDepartamento(dE.getNome(h.getIdDepartamento()));
            nomeLocal.setNomePresidencia("Presidência");
        }

        if (p.isChefe()) {
            nomeLocal.setNomeDiretoria(dI.getNome(h.getIdDiretoria()));
        }

        if (p.isFuncionarioDpto()) {
            nomeLocal.setNomeDepartamento(dE.getNome(h.getIdDepartamento()));
            nomeLocal.setNomeDiretoria(dI.getNome(h.getIdDiretoria()));
        }

        if (p.isChefeDepci()) {
            nomeLocal.setNomePresidencia("Presidência");
        }

        if (p.isFuncionarioDpci()) {
            nomeLocal.setNomeDepartamento(dE.getNome(h.getIdDepartamento()));
            nomeLocal.setNomePresidencia("Presidência");
        }

        if (p.isFuncionarioDiretoria()) {
            nomeLocal.setNomeDiretoria(dI.getNome(h.getIdDiretoria()));
        }

        if (p.isDiretor()) {
            nomeLocal.setNomePresidencia("Presidência");
        }

//        nomeLocal.setNomeGerencia(g.getNome(h.getIdGerencia()));
//        nomeLocal.setNomeDepartamento(dE.getNome(h.getIdDepartamento()));
//        nomeLocal.setNomeDiretoria(dI.getNome(h.getIdDiretoria()));
        return nomeLocal;

    }

    public ArrayList<String> getListaGeral() {
        ArrayList<String> lista = new ArrayList();

        GerenciaControl g = new GerenciaControl();
        lista = g.getListaGerencias();

        DepartamentoControl dE = new DepartamentoControl();
        ArrayList<String> listaDepartamento = new ArrayList();
        listaDepartamento = dE.getListaDepartamento();
        for (int i = 0; i < listaDepartamento.size(); i++) {
            lista.add(listaDepartamento.get(i));
        }

        DiretoriaControl dI = new DiretoriaControl();
        ArrayList<String> listaDiretoria = new ArrayList();
        listaDiretoria = dI.getListaDiretoria();
        for (int i = 0; i < listaDiretoria.size(); i++) {
            lista.add(listaDiretoria.get(i));
        }

        lista.add("--TODOS--");

        return lista;
    }

    public boolean verificaPossibilidadePermitirEdicao(String tipoLocalizacao, int idRepositorio) {
        conecta.conexao();
        String situacaoDepartamento = "";
        String situacaoDiretoria = "";
        String situacaoPresidencia = "";
        boolean sinal = false;
        conecta.executaPesquisaSQL("Select * from repositorioAlteracao where idrepositorio_ra=" + idRepositorio);
        try {
            conecta.rs.first();

            if (tipoLocalizacao.equals("Gerência")) {
                situacaoDepartamento = conecta.rs.getString("AprovacaoDepartamento_RA");
                situacaoDiretoria = conecta.rs.getString("AprovacaoDiretoria_RA");
                if ((situacaoDepartamento.equals("NAO_RECEBIDA")) && (situacaoDiretoria.equals("NAO_RECEBIDA"))) {
                    sinal = true;
                }
            }

            if (tipoLocalizacao.equals("Departamento")) {
                situacaoDiretoria = conecta.rs.getString("AprovacaoDiretoria_RA");
                situacaoPresidencia = conecta.rs.getString("AprovacaoPresidencia_RA");
                if ((situacaoPresidencia.equals("NAO_RECEBIDA")) && (situacaoDiretoria.equals("NAO_RECEBIDA"))) {
                    sinal = true;
                }
            }
            
             if (tipoLocalizacao.equals("Diretoria")) {
                situacaoPresidencia = conecta.rs.getString("AprovacaoPresidencia_RA");
                if ((situacaoPresidencia.equals("NAO_RECEBIDA"))) {
                    sinal = true;
                }
            }
             
             if (tipoLocalizacao.equals("Presidência")){
                 sinal=true;
             }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SetoresControl/VerificaPossibilidadeEdicao");
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }

}
