package controllers_tables;

import controllers.FuncionarioControl;
import utils.ConectaBanco;
import domain.Model.ControleTabelas.TabelaAnaliseModel;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TabelaAnaliseControl {

    ConectaBanco conecta = new ConectaBanco();

    public ArrayList<TabelaAnaliseModel> listaTabelaAnaliseLocalizacao(int idLocal, String analisador) {
        ArrayList<TabelaAnaliseModel> lista = new ArrayList();
        FuncionarioControl f = new FuncionarioControl();

        FuncionarioControl funcionarioControle = new FuncionarioControl();
        conecta.conexao();

        try {
            //O QUE VAI APARECER NA TABELA ???? -> As alterações recebidas
            if (analisador.equals("Gerência")) {
                conecta.executaPesquisaSQL("Select * from alteracaofrequencia where "
                        + "GerenciaAvaliadora_AF=" + idLocal + " and situacaogerencia_af='RECEBIDA'");
                conecta.rs.first();
            }

            if (analisador.equals("Departamento")) {
                conecta.executaPesquisaSQL("Select * from alteracaofrequencia where "
                        + "DepartamentoAvaliador_AF=" + idLocal + " and situacaodepartamento_af='RECEBIDA'");
                conecta.rs.first();
            }

            if (analisador.equals("Diretoria")) {
                conecta.executaPesquisaSQL("Select * from alteracaofrequencia where "
                        + "DiretoriaAvaliadora_AF=" + idLocal + " and situacaodiretoria_af='RECEBIDA'");
                conecta.rs.first();
            }

            if (analisador.equals("Presidência")) {
                conecta.executaPesquisaSQL("Select * from alteracaofrequencia where "
                        + "situacaopresidencia_af='RECEBIDA'");
                conecta.rs.first();
            }

            do {
                TabelaAnaliseModel objetoAnalise = new TabelaAnaliseModel();
                objetoAnalise.setLocalizacao(funcionarioControle.getLocalizacao(conecta.rs.getInt("funcionario_matfunc_af")));
               int matv=(conecta.rs.getInt("funcionario_matfunc_af"));
               objetoAnalise.setNome(f.getNome(matv));
                objetoAnalise.setReferencia(conecta.rs.getString("Referencia_AF"));
                lista.add(objetoAnalise);
            } while (conecta.rs.next());

        } catch (SQLException ex) {
            //COmentei para não ficar aparecendo na tela a chamada que o resultset esta vazio.
            //JOptionPane.showMessageDialog(null,"TabelaAnaliseControl/ListaTabelaAnalise.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return lista;
    }
}
