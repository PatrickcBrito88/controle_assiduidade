package domain.CRUD;

import controllers.CalculaHierarquia;
import controllers.FuncionarioControl;
import domain.Modelo.AFModel;
import utils.ConectaBanco;
import com.mysql.jdbc.PreparedStatement;
import domain_enums.EnumSituacao;
import domain.Modelo.HierarquiaModel;
import domain.Modelo.LogModel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Crud_Alteracao {

    ConectaBanco conecta = new ConectaBanco();

    public void CadastraAlteracao(AFModel aF) {
        conecta.conexao();
        boolean cargoComissionadoGerencia = false;
        boolean cargoComissionadoDepartamento = false;
        boolean cargoComissionadoDiretoria = false;

        PreparedStatement pst;
        try {

            pst = (PreparedStatement) conecta.conn.prepareStatement("Insert into alteracaofrequencia (funcionario_matfunc_af, "
                    + "dataelaboracao_af, referencia_af, gerenciaavaliadora_af, departamentoavaliador_af,"
                    + "diretoriaavaliadora_af, presidenciaavaliadora_af, situacaogerencia_af, situacaodepartamento_af, situacaodiretoria_af, "
                    + "situacaoPresidencia_AF, situacaorh_af) VALUES (?,now(),?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, aF.getMat());
            pst.setString(2, aF.getReferencia());
            pst.setInt(3, aF.getGerenciaAvaliadora());
            pst.setInt(4, aF.getDepartamentoAvaliador());
            pst.setInt(5, aF.getDiretoriaAvaliadora());
            pst.setInt(6, aF.getPresidenciaAvaliadora());

            FuncionarioControl f = new FuncionarioControl();
            String cargo = f.getCargo(aF.getMat());
            pst.setString(7, EnumSituacao.NAO_RECEBIDA.toString());
            pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());
            pst.setString(9, EnumSituacao.NAO_RECEBIDA.toString());
            pst.setString(10, EnumSituacao.NAO_RECEBIDA.toString());
            pst.setString(11, EnumSituacao.NAO_RECEBIDA.toString());

            pst.execute();

            LogModel log = new LogModel();
            log.setDescricao("Criação de Alteração de Frequência. Referência: " + aF.getReferencia() + ", Matricula: " + aF.getMat());
            try {
                log.setIp(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException ex) {
                Logger.getLogger(Crud_Alteracao.class.getName()).log(Level.SEVERE, null, ex);
            }
            log.setMat(aF.getMat());
            log.setTipo("Criação de Alteração de Frequência");
            Crud_Log c = new Crud_Log();
            c.InsereLog(log);

            JOptionPane.showMessageDialog(null, "Alteraçao de frequência cadastrada com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Crud_Alteracao/CadastraAlteracao.\n" + ex);
        } finally {
            conecta.desconecta();
        }

    }

    public String DeletaAlteracao(int mat, String referencia) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Delete from alteracaofrequencia where funcionario_matfunc_af=?"
                    + " and referencia_af=?");
            pst.setInt(1, mat);
            pst.setString(2, referencia);
            pst.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro AlteracaoFrequencia/DeletarAlteracao, erro:\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return "Alteração de Frequência deletada com sucesso!";
    }

    public void CriaIdPorAlteracaoEvento(int mat, String referencia) {
        //Este método cria uma alteração a partir da alteração de um evento cuja alteração nao exista no banco
        AFModel afModel = new AFModel();//preenche a tabela AlteraçãoFrequencia
        HierarquiaModel hierarquiaModel = new HierarquiaModel();// Ajuda a preencher a tabela AlteracaoFrequencia

        boolean sinal = true;
        //Calcular Hierarquia
        CalculaHierarquia calcularHierarquia = new CalculaHierarquia();

        Date dataAtual = new Date(System.currentTimeMillis());
        afModel.setDataelaboracao(dataAtual);
        afModel.setMat(mat);//Variável Global. Instanciar antes do setvisible=true
        afModel.setReferencia(referencia);
        hierarquiaModel = calcularHierarquia.getHierarquiaFuncionarioAvaliacao(mat);
        afModel.setDepartamentoAvaliador(hierarquiaModel.getIdDepartamento());
        afModel.setDiretoriaAvaliadora(hierarquiaModel.getIdDiretoria());
        afModel.setGerenciaAvaliadora(hierarquiaModel.getIdGerencia());
        afModel.setPresidenciaAvaliadora(hierarquiaModel.getIdPresidencia());

        CadastraAlteracao(afModel);
    }

    public void ApagaAlteracaoSemEvento(String referencia, int mat) {
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where alteracaofrequencia_matfunc_ra=" + mat
                + " and alteracaofrequencia_referencia_ra='" + referencia + "'");
        try {
            if (conecta.rs.first()) {
                System.out.println("Sim possui evento.");
            } else {
                System.out.println("Não possui evento");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Crud_Alteracao/ApagaAlteracaoSemEvento.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

}
