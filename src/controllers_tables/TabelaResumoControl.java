package controllers_tables;

import controllers.TiposControle;
import domain_enums.EnumSituacao;
import domain.Model.ControleTabelas.TabelaResumoAlteracaoModel;
import utils.ConectaBanco;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TabelaResumoControl {

    ConectaBanco conecta = new ConectaBanco();
    TabelaResumoAlteracaoModel resumo = new TabelaResumoAlteracaoModel();

    public ArrayList<TabelaResumoAlteracaoModel> listaResumo(int mat, String referencia) {
        ArrayList<TabelaResumoAlteracaoModel> lista = new ArrayList();

        TiposControle tiposControle = new TiposControle();

        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from RepositorioAlteracao where "
                + "alteracaofrequencia_matfunc_Ra=" + mat + " and alteracaofrequencia_referencia_ra='"
                + referencia + "' group by idRepositorio_RA order by dataevento_ra asc");
        try {
            boolean sinal = conecta.rs.first();
            if (sinal) {
                do {
                    TabelaResumoAlteracaoModel resumo = new TabelaResumoAlteracaoModel();
                    resumo.setAnexo(conecta.rs.getBoolean("anexo_RA")); //Anexo
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                    resumo.setDataEvento(formato.format(conecta.rs.getDate("DataEvento_RA"))); //Data
                    String descricao = conecta.rs.getString("Descricao_RA"); //Descricao
                    resumo.setDescricao(descricao);
                    resumo.setId(conecta.rs.getInt("idRepositorio_RA"));
                    if (descricao != null) {
                        resumo.setDescricao(true);
                    } else {
                        resumo.setDescricao(false);
                    }
                    resumo.setTrava(conecta.rs.getBoolean("trava_ra"));
                    resumo.setTipo(tiposControle.getNomeTipo(conecta.rs.getInt("idTipo_RA")));//tipo 
                    resumo.setReferencia(conecta.rs.getString("AlteracaoFrequencia_Referencia_RA"));

                    lista.add(resumo);
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ResumoControleDTO/listaResumo.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        return lista;
    }

    public ArrayList<TabelaResumoAlteracaoModel> listaResumoAnalise(int mat, String referencia, String tipoLocal) {
        ArrayList<TabelaResumoAlteracaoModel> lista = new ArrayList();

        TiposControle tiposControle = new TiposControle();

        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from RepositorioAlteracao where "
                + "alteracaofrequencia_matfunc_Ra=" + mat + " and alteracaofrequencia_referencia_ra='"
                + referencia + "' group by idRepositorio_RA order by dataevento_ra asc");
        System.out.println("Local: "+tipoLocal);
        try {
            conecta.rs.first();
            do {
                TabelaResumoAlteracaoModel resumo = new TabelaResumoAlteracaoModel();
                resumo.setAnexo(conecta.rs.getBoolean("anexo_RA")); //Anexo
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                resumo.setDataEvento(formato.format(conecta.rs.getDate("DataEvento_RA"))); //Data
                String descricao = conecta.rs.getString("Descricao_RA"); //Descricao

                if (descricao != null) {
                    resumo.setDescricao(true);
                } else {
                    resumo.setDescricao(false);
                }
                resumo.setTipo(tiposControle.getNomeTipo(conecta.rs.getInt("idTipo_RA")));//tipo 

                String aprovacao = "";
                if (tipoLocal.equals("Gerência")) {
                    aprovacao = conecta.rs.getString("aprovacaogerencia_Ra");
                    if (aprovacao.equals(EnumSituacao.RECEBIDA.toString())) {//Foi recebida mas não foi avaliada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoAvaliada.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.ABONADA.toString())) {//Foi recebida e abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.NAO_ABONADA.toString())) {//Foi recebida e não foi abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
                    }

                }
                if (tipoLocal.equals("Departamento")) {
                    aprovacao = conecta.rs.getString("aprovacaodepartamento_Ra");
                    if (aprovacao.equals(EnumSituacao.RECEBIDA.toString())) {//Foi recebida mas não foi avaliada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoAvaliada.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.ABONADA.toString())) {//Foi recebida e abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.NAO_ABONADA.toString())) {//Foi recebida e não foi abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
                    }
                }
                if (tipoLocal.equals("Diretoria")) {
                    aprovacao = conecta.rs.getString("aprovacaodiretoria_RA");
                    if (aprovacao.equals(EnumSituacao.RECEBIDA.toString())) {//Foi recebida mas não foi avaliada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoAvaliada.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.ABONADA.toString())) {//Foi recebida e abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.NAO_ABONADA.toString())) {//Foi recebida e não foi abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
                    }
                }
               
                if (tipoLocal.equals("Presidência")) {
                    aprovacao = conecta.rs.getString("aprovacaopresidencia_RA");
                    if (aprovacao.equals(EnumSituacao.RECEBIDA.toString())) {//Foi recebida mas não foi avaliada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoAvaliada.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.ABONADA.toString())) {//Foi recebida e abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.NAO_ABONADA.toString())) {//Foi recebida e não foi abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
                    }
                }

                lista.add(resumo);
            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ResumoControleDTO/listaResumoAnalise.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        return lista;
    }

    public ArrayList<TabelaResumoAlteracaoModel> listaResumoAnaliseAvaliadores(int mat, String referencia, String tipoLocal) {
        ArrayList<TabelaResumoAlteracaoModel> lista = new ArrayList();

        TiposControle tiposControle = new TiposControle();

        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from RepositorioAlteracao where "
                + "alteracaofrequencia_matfunc_Ra=" + mat + " and alteracaofrequencia_referencia_ra='"
                + referencia + "' and naoabonado_ra=0 group by idRepositorio_RA order by dataevento_ra asc");
        try {
            conecta.rs.first();
            do {
                TabelaResumoAlteracaoModel resumo = new TabelaResumoAlteracaoModel();
                resumo.setAnexo(conecta.rs.getBoolean("anexo_RA")); //Anexo
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                resumo.setDataEvento(formato.format(conecta.rs.getDate("DataEvento_RA"))); //Data
                String descricao = conecta.rs.getString("Descricao_RA"); //Descricao
                resumo.setDescricao(descricao);
                resumo.setReferencia(referencia);
                resumo.setId(conecta.rs.getInt("IdRepositorio_RA"));

                if (descricao != null) {
                    resumo.setDescricao(true);
                } else {
                    resumo.setDescricao(false);
                }
                resumo.setTipo(tiposControle.getNomeTipo(conecta.rs.getInt("idTipo_RA")));//tipo 

                String aprovacao = "";
//                 System.out.println("O local é "+tipoLocal);
                if (tipoLocal.equals("Gerência")) {
//                    System.out.println("O");
                    aprovacao = conecta.rs.getString("aprovacaogerencia_Ra");
                    if (aprovacao.equals(EnumSituacao.RECEBIDA.toString())) {//Foi recebida mas não foi avaliada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoAvaliada.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.ABONADA.toString())) {//Foi recebida e abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.NAO_ABONADA.toString())) {//Foi recebida e não foi abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
                    }

                }
                if (tipoLocal.equals("Departamento")) {
//                     System.out.println("1");
                    aprovacao = conecta.rs.getString("aprovacaodepartamento_Ra");
//                   System.out.println(aprovacao);
                    if (aprovacao.equals(EnumSituacao.RECEBIDA.toString())) {//Foi recebida mas não foi avaliada

                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoAvaliada.png"))));

                    }
                    if (aprovacao.equals(EnumSituacao.ABONADA.toString())) {//Foi recebida e abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.NAO_ABONADA.toString())) {//Foi recebida e não foi abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
                    }
                }
                if (tipoLocal.equals("Diretoria")) {
                    System.out.println("2");
                    aprovacao = conecta.rs.getString("aprovacaodiretoria_RA");
                    if (aprovacao.equals(EnumSituacao.RECEBIDA.toString())) {//Foi recebida mas não foi avaliada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoAvaliada.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.ABONADA.toString())) {//Foi recebida e abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.NAO_ABONADA.toString())) {//Foi recebida e não foi abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
                    }
                }

                lista.add(resumo);
            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ResumoControleDTO/listaResumoAnalise.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        return lista;
    }

    public ArrayList<TabelaResumoAlteracaoModel> listaResumoAnaliseAvaliadoresNaoAtualiza(int mat, String referencia, String tipoLocal, int idNaoAbono) {
        //Este método é idêntico ao de cima, com excessão da SQL que não altera o idnaoabonado
        //Com isso eu consigo manter os eventos nao abonados no nível que não abonou
        ArrayList<TabelaResumoAlteracaoModel> lista = new ArrayList();

        TiposControle tiposControle = new TiposControle();

        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from RepositorioAlteracao where "
                + "alteracaofrequencia_matfunc_Ra=" + mat + " and alteracaofrequencia_referencia_ra='"
                + referencia + "' and (naoabono_idnaoabono_ra=1 or naoabono_idnaoabono_ra=" + idNaoAbono + ") "
                        + " and trava_ra=true group by idRepositorio_RA order by dataevento_ra asc");
        try {
            conecta.rs.first();
            do {
                TabelaResumoAlteracaoModel resumo = new TabelaResumoAlteracaoModel();
                resumo.setAnexo(conecta.rs.getBoolean("anexo_RA")); //Anexo
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                resumo.setDataEvento(formato.format(conecta.rs.getDate("DataEvento_RA"))); //Data
                String descricao = conecta.rs.getString("Descricao_RA"); //Descricao
                resumo.setTrava(conecta.rs.getBoolean("trava_ra"));
                resumo.setDescricao(descricao);
                resumo.setReferencia(referencia);
                resumo.setId(conecta.rs.getInt("IdRepositorio_RA"));

                if (descricao != null) {
                    resumo.setDescricao(true);
                } else {
                    resumo.setDescricao(false);
                }
                resumo.setTipo(tiposControle.getNomeTipo(conecta.rs.getInt("idTipo_RA")));//tipo 

                String aprovacao = "";
//                 System.out.println("O local é "+tipoLocal);
                if (tipoLocal.equals("Gerência")) {
//                    System.out.println("O");
                    aprovacao = conecta.rs.getString("aprovacaogerencia_Ra");
                    if (aprovacao.equals(EnumSituacao.RECEBIDA.toString())) {//Foi recebida mas não foi avaliada
                        
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoAvaliada.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.ABONADA.toString())) {//Foi recebida e abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.NAO_ABONADA.toString())) {//Foi recebida e não foi abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
                    }

                }
                if (tipoLocal.equals("Departamento")) {
//                     System.out.println("1");
                    aprovacao = conecta.rs.getString("aprovacaodepartamento_Ra");
//                   System.out.println(aprovacao);
                    if (aprovacao.equals(EnumSituacao.RECEBIDA.toString())) {//Foi recebida mas não foi avaliada

                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoAvaliada.png"))));

                    }
                    if (aprovacao.equals(EnumSituacao.ABONADA.toString())) {//Foi recebida e abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.NAO_ABONADA.toString())) {//Foi recebida e não foi abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
                    }
                }
                if (tipoLocal.equals("Diretoria")) {
                   
                    aprovacao = conecta.rs.getString("aprovacaodiretoria_RA");
                    if (aprovacao.equals(EnumSituacao.RECEBIDA.toString())) {//Foi recebida mas não foi avaliada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoAvaliada.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.ABONADA.toString())) {//Foi recebida e abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.NAO_ABONADA.toString())) {//Foi recebida e não foi abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
                    }
                }
                
                if (tipoLocal.equals("Presidência")) {
        
                    aprovacao = conecta.rs.getString("aprovacaopresidencia_RA");
                    if (aprovacao.equals(EnumSituacao.RECEBIDA.toString())) {//Foi recebida mas não foi avaliada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoAvaliada.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.ABONADA.toString())) {//Foi recebida e abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
                    }
                    if (aprovacao.equals(EnumSituacao.NAO_ABONADA.toString())) {//Foi recebida e não foi abonada
                        resumo.setAprovacao(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
                    }
                }

                lista.add(resumo);
            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ResumoControleDTO/listaResumoAnalise.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        return lista;
    }
    
    public ArrayList<TabelaResumoAlteracaoModel> listaResumoFuncionario(int mat, String referencia) {
        ArrayList<TabelaResumoAlteracaoModel> lista = new ArrayList();

        TiposControle tiposControle = new TiposControle();

        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from RepositorioAlteracao where "
                + "alteracaofrequencia_matfunc_Ra=" + mat + " and alteracaofrequencia_referencia_ra='"
                + referencia + "' group by idRepositorio_RA order by dataevento_ra asc");
        try {
            boolean sinal = conecta.rs.first();
            if (sinal) {
                do {
                    TabelaResumoAlteracaoModel resumo = new TabelaResumoAlteracaoModel();
                    resumo.setAnexo(conecta.rs.getBoolean("anexo_RA")); //Anexo
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                    resumo.setDataEvento(formato.format(conecta.rs.getDate("DataEvento_RA"))); //Data
                    String descricao = conecta.rs.getString("Descricao_RA"); //Descricao
                    resumo.setDescricao(descricao);
                    resumo.setId(conecta.rs.getInt("idRepositorio_RA"));
                    if (descricao != null) {
                        resumo.setDescricao(true);
                    } else {
                        resumo.setDescricao(false);
                    }
                    resumo.setTrava(conecta.rs.getBoolean("trava_ra"));
                    resumo.setTipo(tiposControle.getNomeTipo(conecta.rs.getInt("idTipo_RA")));//tipo 
                    resumo.setReferencia(conecta.rs.getString("AlteracaoFrequencia_Referencia_RA"));

                    lista.add(resumo);
                } while (conecta.rs.next());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ResumoControleDTO/listaResumo.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        return lista;
    }


}
