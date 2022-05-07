package controllers;

import utils.ConectaBanco;
import domain.Modelo.NotificacaoModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class NotificacaoControl {

    ConectaBanco conecta = new ConectaBanco();

    public ArrayList<NotificacaoModel> getNotificacao(String tipoLocal, String siglaLocal) {
        conecta.conexao();
        ArrayList<NotificacaoModel> listaNotificacao = new ArrayList();
       
        
        if (tipoLocal.equals("Gerência")){
            GerenciaControl g = new GerenciaControl();
            int id = g.getIdGerencia(siglaLocal);
           
            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where gerenciaavaliadora_af="+id+" "
                    + " and situacaogerencia_af='RECEBIDA' order by dataelaboracao_af desc");
                    
            try {
                conecta.rs.first();
                do{
                    NotificacaoModel notificacaoModel = new NotificacaoModel();
                    notificacaoModel.setMatricula(conecta.rs.getInt("funcionario_matfunc_af"));
                    FuncionarioControl f = new FuncionarioControl();                   
                    notificacaoModel.setNome(f.getNome(notificacaoModel.getMatricula()));
                    notificacaoModel.setReferencia(conecta.rs.getString("referencia_af"));
                    listaNotificacao.add(notificacaoModel);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null,"NotificacaoControl/getNotificacao.\n"+ex);
            } finally {
                conecta.desconecta();
            }
        }
        
        if (tipoLocal.equals("Departamento")){
            DepartamentoControl dE = new DepartamentoControl();
            int id = dE.getIdDepartamento(siglaLocal);
           
            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where departamentoavaliador_af="+id+" "
                    + " and situacaodepartamento_af='RECEBIDA' order by dataelaboracao_af desc ");
                 
            try {
                conecta.rs.first();
                do{
                    NotificacaoModel notificacaoModel = new NotificacaoModel();
                    notificacaoModel.setMatricula(conecta.rs.getInt("funcionario_matfunc_af"));
                    FuncionarioControl f = new FuncionarioControl();                   
                    notificacaoModel.setNome(f.getNome(notificacaoModel.getMatricula()));
                    notificacaoModel.setReferencia(conecta.rs.getString("referencia_af"));
                    listaNotificacao.add(notificacaoModel);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null,"NotificacaoControl/getNotificacao.\n"+ex);
            } finally {
                conecta.desconecta();
            }
        }
        
        if (tipoLocal.equals("Diretoria")){
            DiretoriaControl dI = new DiretoriaControl();
            int id = dI.getIdDiretoria(siglaLocal);
           
            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where diretoriaavaliadora_af="+id+" "
                    + " and situacaodiretoria_af='RECEBIDA' order by dataelaboracao_af desc ");
                   
            try {
                conecta.rs.first();
                do{
                    NotificacaoModel notificacaoModel = new NotificacaoModel();
                    notificacaoModel.setMatricula(conecta.rs.getInt("funcionario_matfunc_af"));
                    FuncionarioControl f = new FuncionarioControl();                   
                    notificacaoModel.setNome(f.getNome(notificacaoModel.getMatricula()));
                    notificacaoModel.setReferencia(conecta.rs.getString("referencia_af"));
                    listaNotificacao.add(notificacaoModel);
                } while (conecta.rs.next());
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null,"NotificacaoControl/getNotificacao.\n"+ex);
            } finally {
                conecta.desconecta();
            }
        }
        
        return listaNotificacao;
    }
}
