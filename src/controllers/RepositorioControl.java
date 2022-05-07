package controllers;

import domain_enums.EnumSituacao;
import utils.ConectaBanco;
import com.mysql.jdbc.PreparedStatement;
import domain.CRUD.Crud_Repositorio;
import domain.Modelo.RepositorioModel;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RepositorioControl {

    ConectaBanco conecta = new ConectaBanco();

    public RepositorioModel getRepositorio(int id) {
        conecta.conexao();
        RepositorioModel r = new RepositorioModel();
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where idrepositorio_ra=" + id);
        try {
            conecta.rs.first();
            r.setAnexo((FileInputStream) conecta.rs.getBinaryStream("Anexo_RA"));
            r.setDataEvento(conecta.rs.getDate("DataEvento_RA"));
            r.setDescricao(conecta.rs.getString("Descricao_RA"));
            r.setIdTipo(conecta.rs.getInt("idTipo_RA"));
            r.setMatFunc(conecta.rs.getInt("AlteracaoFrequencia_Matfunc_RA"));
            r.setReferencia(conecta.rs.getString("AlteracaoFrequencia_Referencia_RA"));
            r.setIdRepositorio(conecta.rs.getInt("idRepositorio_RA"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "RepositorioControl/getRepositorio.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return r;
    }

    public void deletaEvento(int id) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Delete from repositorioAlteracao where idRepositorio_RA=?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "RepositorioControl/DeletaEvento.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public void EnviaEventos(String referencia, int mat) {
        conecta.conexao();

        conecta.executaPesquisaSQL("Select * from repositorioalteracao where alteracaofrequencia_matfunc_ra=" + mat
                + " and alteracaofrequencia_referencia_ra='" + referencia + "'");
        try {
            conecta.rs.first();
            do {
                int id = conecta.rs.getInt("idRepositorio_RA");
                TravaEvento(id);
            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "RepositorioControl/EnviaEventos.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public void TravaEvento(int id) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("update repositorioalteracao set trava_ra=true where "
                    + "idrepositorio_ra=?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "RepositorioAlteracao/TravaEvento.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

    public void DestravaEvento(int id) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("update repositorioalteracao set trava_ra=false where "
                    + "idrepositorio_ra=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            //Quando destrava, volta tudo a ser como antes de avaliar
//            Crud_Repositorio repositorio = new Crud_Repositorio();
//            repositorio.UpdateRepositorio(id, getMatIdRepositorio(id));            
//Eu ia editar a situação dos eventos mas preferi não fazer porque quando o funcionário realiza novo envio do evento
//a alteração atualiza para recebida, o evento continua com a última avaliação
//Caso queira reatualizar o evento, basta descomentar as duas linhas de cima

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "RepositorioAlteracao/DestravaEvento.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }
    
    public boolean verificaTrava (int id){
        conecta.conexao();
        boolean sinal=false;
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where idrepositorio_ra="+id);
        try {
            conecta.rs.first();
            sinal=conecta.rs.getBoolean("trava_ra");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "RepositorioControl/VerificaTrava.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }

    public int getNumeroEventosPendentes(String tipoLocal, int mat, String referencia) {
        conecta.conexao();
        int n = 0;
        if (tipoLocal.equals("Gerência")) {
            System.out.println("Referenica: " + referencia);
            System.out.println("Mat: " + mat);
            conecta.executaPesquisaSQL("Select * from repositorioAlteracao where alteracaofrequencia_matfunc_ra=" + mat
                    + " and alteracaofrequencia_referencia_ra='" + referencia + "' and aprovacaogerencia_ra='RECEBIDA' "
                    + "and trava_ra=true");

            try {
                if (conecta.rs.first() == true) {
                    do {
                        n++;

                    } while (conecta.rs.next());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/GetNumeroPendentes");
            } finally {
                conecta.desconecta();
            }
        }//Fim Gerência

        if (tipoLocal.equals("Departamento")) {
            conecta.executaPesquisaSQL("Select * from repositorioAlteracao where alteracaofrequencia_matfunc_ra=" + mat
                    + " and alteracaofrequencia_referencia_ra='" + referencia + "' and aprovacaodepartamento_ra='RECEBIDA'");

            try {
                if (conecta.rs.first() == true) {
                    do {
                        n++;

                    } while (conecta.rs.next());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/GetNumeroPendentes");
            } finally {
                conecta.desconecta();
            }
        }//Fim Departamento

        if (tipoLocal.equals("Diretoria")) {
            conecta.executaPesquisaSQL("Select * from repositorioAlteracao where alteracaofrequencia_matfunc_ra=" + mat
                    + " and alteracaofrequencia_referencia_ra='" + referencia + "' and aprovacaodiretoria_ra='RECEBIDA'");

            try {
                if (conecta.rs.first() == true) {
                    do {
                        n++;

                    } while (conecta.rs.next());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/GetNumeroPendentes");
            } finally {
                conecta.desconecta();
            }
        }//Fim Diretoria

        if (tipoLocal.equals("Presidência")) {
            conecta.executaPesquisaSQL("Select * from repositorioAlteracao where alteracaofrequencia_matfunc_ra=" + mat
                    + " and alteracaofrequencia_referencia_ra='" + referencia + "' and aprovacaopresidencia_ra='RECEBIDA'");

            try {
                if (conecta.rs.first() == true) {
                    do {
                        n++;

                    } while (conecta.rs.next());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "AlteracaoFrequenciaControl/GetNumeroPendentes");
            } finally {
                conecta.desconecta();
            }
        }//Fim Presidência
        return n;

    }

    public int getMatIdRepositorio(int idRepositorio) {
        int mat = 0;
        conecta.conexao();
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where idrepositorio_ra=" + idRepositorio);
        try {
            conecta.rs.first();
            mat = conecta.rs.getInt("alteracaofrequencia_matfunc_ra");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "RepositorioControl/GetIdRepositorio.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return mat;
    }

    public int getNEventosNaoAbonados(String referencia, int mat) {
        conecta.conexao();
        int n = 0;
        conecta.executaPesquisaSQL("Select * from repositorioAlteracao where alteracaofrequencia_matfunc_ra=" + mat
                + " and alteracaofrequencia_referencia_ra='" + referencia + "' and naoabonado_ra=true");
        try {
            if (conecta.rs.first() == true) {
                do {
                    n++;
                } while (conecta.rs.next());

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "RepositorioControl/getNEventosNaoAbonados.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return n;
    }
    
    public void apagaAnexo (int id){
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Update repositorioalteracao set anexo_ra=null"
                    + " where idrepositorio_ra=?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"RepositorioControl/ApagaAnexo\n"+ex);
        } finally {
            conecta.desconecta();
        }
        
    }
    
    public boolean verificaAnexo(int id){
        conecta.conexao();
        boolean sinal=false;
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where idrepositorio_ra="+id);
        try {
            conecta.rs.first();
            if (conecta.rs.getBinaryStream("Anexo_RA")==null){
                sinal=false;
            } else {
                sinal=true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "RepositorioAlteracao/VerificaAnexo");
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }
    
    public void ResetaEvento (int idRepositorio){
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("Update repositorioalteracao set naoabonado_ra=?,"
                    + " naoabono_idnaoabono_ra=?, motivonaoabono_ra=?, aprovacaogerencia_ra=?,  "
                    + "aprovacaodepartamento_ra=?, aprovacaodiretoria_ra=?, recebimentorh_ra=?, finalizado_ra=? where idrepositorio_ra=?");
            pst.setBoolean(1, false);
            pst.setInt(2, 1);
            pst.setString(3, "");
            pst.setString(4, EnumSituacao.NAO_RECEBIDA.toString());
            pst.setString(5, EnumSituacao.NAO_RECEBIDA.toString());
            pst.setString(6, EnumSituacao.NAO_RECEBIDA.toString());
            pst.setString(7, EnumSituacao.NAO_RECEBIDA.toString());
            pst.setBoolean(8, false);
            pst.setInt(9, idRepositorio);
            pst.executeUpdate();
           
            JOptionPane.showMessageDialog(null,"O evento retornou para a fase de edição do funcionário.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"RepositorioControl/ResetaAvaliações.\n"+ex);
        } finally {
            conecta.desconecta();
        }
    }
    
    public String getMotivo (int id){
        conecta.conexao();
        String texto="";
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where idrepositorio_ra="+id);
        try {
            conecta.rs.first();
            texto=conecta.rs.getString("MotivoNaoAbono_RA");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"RepositorioControl/getDescricao.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return texto;
    }
    
    public boolean VerificaTrava (int id){
        conecta.conexao();
        boolean sinal=false;
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where idrepositorio_ra="+id);
        try {
            conecta.rs.first();
            sinal=conecta.rs.getBoolean("trava_ra");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"RepositorioControl/VerificaTrava.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return sinal;
    }
    
    public String getReferencia (int id){
        conecta.conexao();
        String referencia="";
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where idrepositorio_ra="+id);
        try {
            conecta.rs.first();
            referencia=conecta.rs.getString("alteracaofrequencia_referencia_ra");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ReferenciaControl/getReferência.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return referencia;
    }
    
     public int getMat (int id){
        conecta.conexao();
        int mat=0;
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where idrepositorio_ra="+id);
        try {
            conecta.rs.first();
            mat=conecta.rs.getInt("alteracaofrequencia_matfunc_ra");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ReferenciaControl/getMat.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return mat;
    }
     
     public int getTipo(int id){
         conecta.conexao();
        int tipo=0;
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where idrepositorio_ra="+id);
        try {
            conecta.rs.first();
            tipo=conecta.rs.getInt("idtipo_ra");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ReferenciaControl/getTipo.\n"+ex);
        } finally {
            conecta.desconecta();
        }
        return tipo;
     }
     
     public boolean verificaEventosNaAlteracao(int mat, String referencia) {
        conecta.conexao();
        boolean existe = true;
        conecta.executaPesquisaSQL("Select * from repositorioAlteracao where alteracaofrequencia_matfunc_ra=" + mat
                + " and alteracaofrequencia_referencia_ra='" + referencia + "'");
        try {
            if (conecta.rs.first() == true) {
                existe = true;
            } else {
                existe = false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "RepositorioControl/verificaEventosNaAlteracao.\n" + ex);
        } finally {
            conecta.desconecta();
        }
        return existe;
    }
    
   

}
