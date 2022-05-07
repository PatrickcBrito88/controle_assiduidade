package controllers;

import domain_enums.EnumSituacao;
import utils.ConectaBanco;
import com.mysql.jdbc.PreparedStatement;
import domain.CRUD.Crud_NaoAbonados;
import domain.Modelo.AnalisaSituacaoModel;
import domain.Modelo.NaoAbonadoModel;
import domain.Modelo.PerfilModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AnalisaEvento {

    ConectaBanco conecta = new ConectaBanco();

    public void tratamentoEvento(PerfilModel p, AnalisaSituacaoModel a) {
        //Perfil é o perfil do avaliado
        //SituacaoModel tem o tipoLocal. TipoLocal é quem está avaliando

        conecta.conexao();
        PreparedStatement pst;
        Crud_NaoAbonados c = new Crud_NaoAbonados();

        if (a.getLocal().equals("Gerência")) {//Casos em que a gerência está avaliando
            System.out.println("Situacao Funcionario Gercom: " + p.isFuncionarioGercom());
            //##Verificação de Perfil
            //Funcionários de Gerência
            if ((p.isFuncionarioGerencia() == true) || (p.isFuncionarioGercom() == true)) {
                if (a.getSituacao().toString().equals("ABONADA")) {
                    try {
                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set aprovacaogerencia_ra=?, aprovacaodepartamento_ra=?,"
                                + " naoabonado_ra=?,  naoabono_idnaoabono_RA=? where idRepositorio_ra=?");
                        pst.setString(1, a.getSituacao().toString());
                        pst.setString(2, EnumSituacao.RECEBIDA.toString());
                        pst.setBoolean(3, false);
                        pst.setInt(4, 1);
                        pst.setInt(5, a.getIdRepositorio());
                        pst.executeUpdate();

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/FuncionárioGerência.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }
                }
                if (a.getSituacao().toString().equals("NAO_ABONADA")) {//Ou seja, se não for abonado
                    try {
                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set aprovacaogerencia_ra=?, aprovacaodepartamento_ra=?, naoabonado_ra=?,"
                                + " naoabono_idnaoabono_RA=?, motivonaoabono_RA=? where idRepositorio_ra=?");
                        pst.setString(1, a.getSituacao().toString());
                        pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());
                        pst.setBoolean(3, true);
                        pst.setInt(4, 2);
                        pst.setString(5, a.getMotivoNaoAbonado());
                        pst.setInt(6, a.getIdRepositorio());
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/FuncionárioGerência.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }
                }
            } //Fim de Funcionário Gerência e Funcionário Gercom

            if ((p.isFuncionarioGerjur() == true)) {
                if (a.getSituacao().toString().equals("ABONADA")) {
                    try {
                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set aprovacaogerencia_ra=?, aprovacaodiretoria_ra=?,"
                                + " naoabonado_ra=?,  naoabono_idnaoabono_RA=? where idRepositorio_ra=?");
                        pst.setString(1, a.getSituacao().toString());
                        pst.setString(2, EnumSituacao.RECEBIDA.toString());
                        pst.setBoolean(3, false);
                        pst.setInt(4, 1);
                        pst.setInt(5, a.getIdRepositorio());
                        pst.executeUpdate();

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/FuncionárioGerência.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }
                }
                if (a.getSituacao().toString().equals("NAO_ABONADA")) {//Ou seja, se não for abonado
                    try {
                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set aprovacaogerencia_ra=?, aprovacaodiretoria_ra=?, naoabonado_ra=?,"
                                + " naoabono_idnaoabono_RA=?, motivonaoabono_RA=? where idRepositorio_ra=?");
                        pst.setString(1, a.getSituacao().toString());
                        pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());
                        pst.setBoolean(3, true);
                        pst.setInt(4, 2);
                        pst.setString(5, a.getMotivoNaoAbonado());
                        pst.setInt(6, a.getIdRepositorio());
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/FuncionárioGerência.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }
                }
            }//Fim de funcionário Gerjur

//            if (p.isFuncionarioGercom() == true) {
//                if (a.getSituacao().toString().equals("ABONADA")) {
//                    try {
//                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set aprovacaogerencia_ra=?, aprovacaodepartamento_ra=?,"
//                                + " naoabonado_ra=?,  naoabono_idnaoabono_RA=? where idRepositorio_ra=?");
//                        pst.setString(1, a.getSituacao().toString());
//                        pst.setString(2, EnumSituacao.RECEBIDA.toString());
//                        pst.setBoolean(3, false);
//                        pst.setInt(4, 1);
//                        pst.setInt(5, a.getIdRepositorio());
//                        pst.executeUpdate();
//
//                    } catch (SQLException ex) {
//                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/FuncionárioGercom.\n" + ex);
//                    } finally {
//                        conecta.desconecta();
//                    }
//                }//fim do abonado
//                if (a.getSituacao().toString().equals("NAO_ABONADA")) {//Ou seja, se não for abonado
//                    try {
//                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set aprovacaogerencia_ra=?, aprovacaodiretoria_ra=?, naoabonado_ra=?,"
//                                + " naoabono_idnaoabono_RA=?, motivonaoabono_RA=? where idRepositorio_ra=?");
//                        pst.setString(1, a.getSituacao().toString());
//                        pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());
//                        pst.setBoolean(3, true);
//                        pst.setInt(4, 2);
//                        pst.setString(5, a.getMotivoNaoAbonado());
//                        pst.setInt(6, a.getIdRepositorio());
//                        pst.executeUpdate();
//                    } catch (SQLException ex) {
//                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/FuncionárioGercom.\n" + ex);
//                    } finally {
//                        conecta.desconecta();
//                    }
//                }//Fim do não abonado
//            }
        }//Fim da gerência

        if (a.getLocal().equals("Departamento")) {//se quem estiver avaliando for de departamento

            //pode ser de um departamento normal ou do depci
            if ((p.isFuncionarioDpto() == true) || (p.isGerente()) == true || (p.isFuncionarioGerencia() == true)) {//Quem está sendo avaliado ?
                if (a.getSituacao().toString().equals("ABONADA")) {

                    try {
                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set aprovacaodepartamento_ra=?, aprovacaodiretoria_ra=?,"
                                + " naoabonado_ra=?,  naoabono_idnaoabono_RA=? where idRepositorio_ra=?");
                        pst.setString(1, a.getSituacao().toString());
                        pst.setString(2, EnumSituacao.RECEBIDA.toString());
                        pst.setBoolean(3, false);
                        pst.setInt(4, 1);
                        pst.setInt(5, a.getIdRepositorio());
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/Abonada/Chefe.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }
                }

                //Aqui entra se não for abonada 
                if (a.getSituacao().toString().equals("NAO_ABONADA")) {
                    System.out.println("Entrou no não abonada abonada");
                    try {
                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set aprovacaodepartamento_ra=?,"
                                + " aprovacaodiretoria_ra=?, naoabonado_ra=?,"
                                + " naoabono_idnaoabono_RA=?, motivonaoabono_RA=? where idRepositorio_ra=?");
                        pst.setString(1, a.getSituacao().toString());
                        pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());
                        pst.setBoolean(3, true);
                        pst.setInt(4, 3);
                        pst.setString(5, a.getMotivoNaoAbonado());
                        pst.setInt(6, a.getIdRepositorio());
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/NaoAbonado/Chefe.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }
                }

            }//Fim do if (Gerente, funcionario Gerencia e funcionário departamento)
            //funcionário do DEPCI
            if ((p.isFuncionarioDpci()) || (p.isFuncionarioGercom() || (p.isGerenteGercom()))) {
                if (a.getSituacao().toString().equals("ABONADA")) {

                    try {
                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set"
                                + " aprovacaodepartamento_ra=?, aprovacaopresidencia_ra=?,"
                                + " naoabonado_ra=?,  naoabono_idnaoabono_RA=? where idRepositorio_ra=?");
                        pst.setString(1, a.getSituacao().toString());
                        pst.setString(2, EnumSituacao.RECEBIDA.toString());
                        pst.setBoolean(3, false);
                        pst.setInt(4, 1);
                        pst.setInt(5, a.getIdRepositorio());
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/Abonada/FuncionárioDepci.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }
                }

                if (a.getSituacao().toString().equals("NAO_ABONADA")) {
                    System.out.println("Entrou no não abonada abonada");
                    try {
                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set aprovacaodepartamento_ra=?,"
                                + " aprovacaopresidencia_ra=?, naoabonado_ra=?,"
                                + " naoabono_idnaoabono_RA=?, motivonaoabono_RA=? where idRepositorio_ra=?");
                        pst.setString(1, a.getSituacao().toString());
                        pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());
                        pst.setBoolean(3, true);
                        pst.setInt(4, 3);
                        pst.setString(5, a.getMotivoNaoAbonado());
                        pst.setInt(6, a.getIdRepositorio());
                        pst.executeUpdate();

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/NaoAbonado/Chefe.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }

                }
            }// Fim do DEPCI

//            if (p.isFuncionarioGercom()){
//                  if (a.getSituacao().toString().equals("ABONADA")) {
//                   
//                    try {
//                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao "
//                                + "set aprovacaodepartamento_ra=?, aprovacaopresidencia_ra=?,"
//                                + " naoabonado_ra=?,  naoabono_idnaoabono_RA=? where idRepositorio_ra=?");
//                        pst.setString(1, a.getSituacao().toString());
//                        pst.setString(2, EnumSituacao.RECEBIDA.toString());
//                        pst.setBoolean(3, false);
//                        pst.setInt(4, 1);
//                        pst.setInt(5, a.getIdRepositorio());
//                        pst.executeUpdate();
//                    } catch (SQLException ex) {
//                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/Abonada/FuncionárioGercom.\n" + ex);
//                    } finally {
//                        conecta.desconecta();
//                    }
//                }
//
//                if (a.getSituacao().toString().equals("NAO_ABONADA")) {
//                    System.out.println("Entrou no não abonada abonada");
//                    try {
//                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set "
//                                + "aprovacaodepartamento_ra=?,"
//                                + " aprovacaopresidencia_ra=?, naoabonado_ra=?,"
//                                + " naoabono_idnaoabono_RA=?, motivonaoabono_RA=? where idRepositorio_ra=?");
//                        pst.setString(1, a.getSituacao().toString());
//                        pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());
//                        pst.setBoolean(3, true);
//                        pst.setInt(4, 3);
//                        pst.setString(5, a.getMotivoNaoAbonado());
//                        pst.setInt(6, a.getIdRepositorio());
//                        pst.executeUpdate();
//                    } catch (SQLException ex) {
//                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/NaoAbonado/Chefe.\n" + ex);
//                    } finally {
//                        conecta.desconecta();
//                    }
//
//                }
//            }
        }//Fim do Departamento

        if (a.getLocal().equals("Diretoria")) {//Se quem estiver avaliando for de diretoria
            //funcionário gerencia, gerente, funcionário gercom, gerente 
            //, funcionário dpto, chefe dpto --> RH
            if ((p.isFuncionarioGerencia()) || (p.isGerente()) || (p.isChefe()) || (p.isFuncionarioDpto()) || (p.isFuncionarioDiretoria()
                    || (p.isFuncionarioGerjur()) || (p.isGerenteGerjur()))) {
                if (a.getSituacao().toString().equals("ABONADA")) {
                    System.out.println("Entrou no abonada");
                    try {
                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set aprovacaodiretoria_ra=?, "
                                + "recebimentorh_ra=?,"
                                + " naoabonado_ra=?,  naoabono_idnaoabono_RA=?, finalizado_ra=? where idRepositorio_ra=?");
                        pst.setString(1, a.getSituacao().toString());
                        pst.setString(2, EnumSituacao.RECEBIDA.toString());
                        pst.setBoolean(3, false);
                        pst.setInt(4, 1);
                        pst.setBoolean(5, true);
                        pst.setInt(6, a.getIdRepositorio());
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/Abonada/Diretoria.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }
                }

                if (a.getSituacao().toString().equals("NAO_ABONADA")) {

                    try {
                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set aprovacaodiretoria_ra=?,"
                                + " recebimentorh_ra=?, naoabonado_ra=?,"
                                + " naoabono_idnaoabono_RA=?, motivonaoabono_RA=?, finalizado_ra=? where idRepositorio_ra=?");
                        pst.setString(1, a.getSituacao().toString());
                        pst.setString(2, EnumSituacao.RECEBIDA.toString());
                        pst.setBoolean(3, true);
                        pst.setInt(4, 4);
                        pst.setString(5, a.getMotivoNaoAbonado());
                        pst.setBoolean(6, true);
                        pst.setInt(7, a.getIdRepositorio());
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/NaoAbonado/Diretoria.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }
                }
            } // fim if funcionário gerencia, gerente, chefe, funcionario dpto, func gercom, etc
        } //fim if diretoria

        if (a.getLocal().equals("Presidência")) {//Se quem estiver avaliando for o Presidente
            //Diretor, funcionário Depci, Chefe Depci

            if ((p.isDiretor()) || (p.isFuncionarioDpci()) || (p.isChefeDepci()) || (p.isFuncionarioGercom() || (p.isGerenteGercom()))) {
                if (a.getSituacao().toString().equals("ABONADA")) {

                    try {
                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set aprovacaopresidencia_ra=?, recebimentorh_ra=?,"
                                + " naoabonado_ra=?,  naoabono_idnaoabono_RA=?, finalizado_ra=? where idRepositorio_ra=?");
                        pst.setString(1, a.getSituacao().toString());
                        pst.setString(2, EnumSituacao.RECEBIDA.toString());
                        pst.setBoolean(3, false);
                        pst.setInt(4, 1);
                        pst.setBoolean(5, true);
                        pst.setInt(6, a.getIdRepositorio());
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/Abonada/Presidencia.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }
                }

                if (a.getSituacao().toString().equals("NAO_ABONADA")) {

                    try {
                        pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set aprovacaopresidencia_ra=?,"
                                + " recebimentorh_ra=?, naoabonado_ra=?,"
                                + " naoabono_idnaoabono_RA=?, motivonaoabono_RA=?, finalizado_ra=? where idRepositorio_ra=?");
                        pst.setString(1, a.getSituacao().toString());
                        pst.setString(2, EnumSituacao.RECEBIDA.toString());
                        pst.setBoolean(3, true);
                        pst.setInt(4, 5);
                        pst.setString(5, a.getMotivoNaoAbonado());
                        pst.setBoolean(6, true);
                        pst.setInt(7, a.getIdRepositorio());
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "AnalisaEvento/TratamentoEvento/NaoAbonado/Presidência.\n" + ex);
                    } finally {
                        conecta.desconecta();
                    }
                }
            } //
        } //fim if presidência

    }
}
