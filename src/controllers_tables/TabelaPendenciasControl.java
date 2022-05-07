/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers_tables;

import controllers.DepartamentoControl;
import controllers.DiretoriaControl;
import controllers.FuncionarioControl;
import controllers.GerenciaControl;
import controllers.RepositorioControl;
import utils.ConectaBanco;
import domain.Model.ControleTabelas.MenuAdminPendenciasModel;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TabelaPendenciasControl {

    ConectaBanco conecta = new ConectaBanco();

    public ArrayList<MenuAdminPendenciasModel> getListaPendencas(String tipoLocal, String nome,
            String setor, String competencia) {
        ArrayList<MenuAdminPendenciasModel> lista = new ArrayList();

        /*
        Incluí dois parâmetros neste método.
        1º Identifica o tipo de local
        2º Identifica o nome, caso o RH queira fazer a busca pelo nome do funcionário.
        O 2º é utilizado como vazio em todos, exceto quando o tipoLocal é RH
         */
        conecta.conexao();

        if (tipoLocal.equals("Gerência")) {
            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where situacaogerencia_af='RECEBIDA'"
                    + " order by gerenciaavaliadora_af asc");

            FuncionarioControl f = new FuncionarioControl();
            try {
                if (conecta.rs.first() == true) {
                    do {

                        MenuAdminPendenciasModel a = new MenuAdminPendenciasModel();
                        a.setCompetencia(conecta.rs.getString("referencia_af"));
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                        a.setDataElaboracao(formato.format(conecta.rs.getDate("dataelaboracao_af")));

                        int mat = (conecta.rs.getInt("funcionario_matfunc_af"));
                        a.setNome(f.getNome(conecta.rs.getInt("funcionario_matfunc_af")));
                        RepositorioControl r = new RepositorioControl();
                        a.setnEventos(r.getNumeroEventosPendentes("Gerência", mat, a.getCompetencia()));

                        GerenciaControl g = new GerenciaControl();
                        a.setSetor(g.getSigla(conecta.rs.getInt("GerenciaAvaliadora_AF")));

                        lista.add(a);
                    } while (conecta.rs.next());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "TabelaPendências/GetListaPendências.\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }//Fim Gerência

        if (tipoLocal.equals("Departamento")) {
            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where situacaodepartamento_af='RECEBIDA'"
                    + " order by departamentoavaliador_af asc");
            FuncionarioControl f = new FuncionarioControl();
            try {
                if (conecta.rs.first() == true) {
                    do {
                        MenuAdminPendenciasModel a = new MenuAdminPendenciasModel();
                        a.setCompetencia(conecta.rs.getString("referencia_af"));
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        a.setDataElaboracao(formato.format(conecta.rs.getDate("dataelaboracao_af")));
                        int mat = (conecta.rs.getInt("funcionario_matfunc_af"));
                        a.setNome(f.getNome(conecta.rs.getInt("funcionario_matfunc_af")));
                        RepositorioControl r = new RepositorioControl();
                        a.setnEventos(r.getNumeroEventosPendentes("Departamento", mat, a.getCompetencia()));
                        DepartamentoControl dE = new DepartamentoControl();
                        a.setSetor(dE.getSigla(conecta.rs.getInt("DepartamentoAvaliador_AF")));
                        lista.add(a);
                    } while (conecta.rs.next());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "TabelaPendências/GetListaPendências.\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }//Fim Departamento

        if (tipoLocal.equals("Diretoria")) {
            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where situacaodiretoria_af='RECEBIDA'"
                    + " order by diretoriaavaliadora_af asc");
            FuncionarioControl f = new FuncionarioControl();
            try {
                if (conecta.rs.first() == true) {
                    do {
                        DiretoriaControl dI = new DiretoriaControl();
                        MenuAdminPendenciasModel a = new MenuAdminPendenciasModel();
                        a.setCompetencia(conecta.rs.getString("referencia_af"));
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        a.setDataElaboracao(formato.format(conecta.rs.getDate("dataelaboracao_af")));
                        int mat = (conecta.rs.getInt("funcionario_matfunc_af"));
                        a.setNome(f.getNome(conecta.rs.getInt("funcionario_matfunc_af")));
                        RepositorioControl r = new RepositorioControl();
                        a.setnEventos(r.getNumeroEventosPendentes("Diretoria", mat, a.getCompetencia()));
                        a.setSetor(dI.getSigla(conecta.rs.getInt("DiretoriaAvaliadora_AF")));

                        lista.add(a);
                    } while (conecta.rs.next());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "TabelaPendências/GetListaPendências.\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }//Fim Diretoria

        if (tipoLocal.equals("Presidência")) {
            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where situacaopresidencia_af='RECEBIDA'");
            FuncionarioControl f = new FuncionarioControl();
            try {
                if (conecta.rs.first() == true) {
                    do {
                        MenuAdminPendenciasModel a = new MenuAdminPendenciasModel();
                        a.setCompetencia(conecta.rs.getString("referencia_af"));
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        a.setDataElaboracao(formato.format(conecta.rs.getDate("dataelaboracao_af")));
                        int mat = (conecta.rs.getInt("funcionario_matfunc_af"));
                        a.setNome(f.getNome(conecta.rs.getInt("funcionario_matfunc_af")));
                        RepositorioControl r = new RepositorioControl();
                        a.setnEventos(r.getNumeroEventosPendentes("Presidência", mat, a.getCompetencia()));
                        a.setSetor("Presidência");
                        lista.add(a);
                    } while (conecta.rs.next());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "TabelaPendências/GetListaPendências.\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }//Fim Presidência

        if (tipoLocal.equals("RH")) {

            if (competencia.equals("Todos")) {

                if (nome.equals("")) {
                    conecta.executaPesquisaSQL("Select * from alteracaofrequencia where situacaorh_af='RECEBIDA'");
                    FuncionarioControl f = new FuncionarioControl();
                    try {
                        if (conecta.rs.first() == true) {
                            do {
                                MenuAdminPendenciasModel a = new MenuAdminPendenciasModel();
                                a.setCompetencia(conecta.rs.getString("referencia_af"));
                                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                                a.setDataElaboracao(formato.format(conecta.rs.getDate("dataelaboracao_af")));
                                int mat = (conecta.rs.getInt("funcionario_matfunc_af"));
                                a.setNome(f.getNome(conecta.rs.getInt("funcionario_matfunc_af")));
                                RepositorioControl r = new RepositorioControl();
                                int n = r.getNEventosNaoAbonados(a.getCompetencia(), mat);
                                a.setnEventosNaoAbonados(n);
                                a.setSetor("Presidência");
                                lista.add(a);
                            } while (conecta.rs.next());
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "TabelaPendências/GetListaPendências.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }
                } else {
                    //se tiver um campo de busca
                    conecta.executaPesquisaSQL("Select * from buscapornome where situacaorh_af='RECEBIDA' and"
                            + " nome_f like '%" + nome + "%' order by nome_f asc");

                    FuncionarioControl f = new FuncionarioControl();
                    try {
                        if (conecta.rs.first() == true) {
                            do {
                                MenuAdminPendenciasModel a = new MenuAdminPendenciasModel();
                                a.setCompetencia(conecta.rs.getString("referencia_af"));
                                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                                a.setDataElaboracao(formato.format(conecta.rs.getDate("dataelaboracao_af")));
                                int mat = (conecta.rs.getInt("funcionario_matfunc_af"));
                                a.setNome(f.getNome(conecta.rs.getInt("funcionario_matfunc_af")));
                                RepositorioControl r = new RepositorioControl();
                                int n = r.getNEventosNaoAbonados(a.getCompetencia(), mat);
                                a.setnEventosNaoAbonados(n);
                                a.setSetor("Presidência");
                                lista.add(a);
                            } while (conecta.rs.next());
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "TabelaPendências/GetListaPendências.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }

                }
            }//Fim setor todos
            else {//else setor diferente de todos
                if (nome.equals("")) {
                    conecta.executaPesquisaSQL("Select * from alteracaofrequencia where situacaorh_af='RECEBIDA'"
                            + " and referencia_af='"+competencia+"'");
                    FuncionarioControl f = new FuncionarioControl();
                    try {
                        if (conecta.rs.first() == true) {
                            do {
                                MenuAdminPendenciasModel a = new MenuAdminPendenciasModel();
                                a.setCompetencia(conecta.rs.getString("referencia_af"));
                                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                                a.setDataElaboracao(formato.format(conecta.rs.getDate("dataelaboracao_af")));
                                int mat = (conecta.rs.getInt("funcionario_matfunc_af"));
                                a.setNome(f.getNome(conecta.rs.getInt("funcionario_matfunc_af")));
                                RepositorioControl r = new RepositorioControl();
                                int n = r.getNEventosNaoAbonados(a.getCompetencia(), mat);
                                a.setnEventosNaoAbonados(n);
                                a.setSetor("Presidência");
                                lista.add(a);
                            } while (conecta.rs.next());
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "TabelaPendências/GetListaPendências.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }
                } else {
                    //se tiver um campo de busca
                    conecta.executaPesquisaSQL("Select * from buscapornome where situacaorh_af='RECEBIDA' and"
                            + " nome_f like '%" + nome + "%' and referencia_af='"+competencia+"' order by nome_f asc");

                    FuncionarioControl f = new FuncionarioControl();
                    try {
                        if (conecta.rs.first() == true) {
                            do {
                                MenuAdminPendenciasModel a = new MenuAdminPendenciasModel();
                                a.setCompetencia(conecta.rs.getString("referencia_af"));
                                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                                a.setDataElaboracao(formato.format(conecta.rs.getDate("dataelaboracao_af")));
                                int mat = (conecta.rs.getInt("funcionario_matfunc_af"));
                                a.setNome(f.getNome(conecta.rs.getInt("funcionario_matfunc_af")));
                                RepositorioControl r = new RepositorioControl();
                                int n = r.getNEventosNaoAbonados(a.getCompetencia(), mat);
                                a.setnEventosNaoAbonados(n);
                                a.setSetor("Presidência");
                                lista.add(a);
                            } while (conecta.rs.next());
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "TabelaPendências/GetListaPendências.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }

                }
            }
        }//Fim RH

        return lista;
    }
}
