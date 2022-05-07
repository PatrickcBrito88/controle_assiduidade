package domain.CRUD;

import controllers.AlteracaoFrequenciaControl;
import controllers.FuncionarioControl;
import controllers.GetPerfil;
import controllers.RepositorioControl;
import controllers.TiposControle;
import domain_enums.EnumSituacao;
import domain.Modelo.RepositorioModel;
import utils.ConectaBanco;
import com.mysql.jdbc.PreparedStatement;
import domain.Modelo.LogModel;
import domain.Modelo.PerfilModel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Crud_Repositorio {

    ConectaBanco conecta = new ConectaBanco();
    ConectaBanco conecta2 = new ConectaBanco();

    public void InsereRepositorio(RepositorioModel r) {
        conecta.conexao();
        PerfilModel p = new PerfilModel();
        GetPerfil g = new GetPerfil();
        p = g.preencherPerfil(r.getMatFunc());
        PreparedStatement pst;
        //9 - diretoria, 8 departamento, 7 gerencia, 10 - presidencia
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Insert into repositorioalteracao (idtipo_ra,"
                    + " alteracaofrequencia_matfunc_ra, alteracaofrequencia_referencia_ra, Anexo_RA, Descricao_RA,"
                    + " dataevento_ra, aprovacaoGerencia_RA, aprovacaoDepartamento_RA, aprovacaoDiretoria_RA, "
                    + " AprovacaoPresidencia_RA, RecebimentoRH_RA) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, r.getIdTipo());
            pst.setInt(2, r.getMatFunc());
            pst.setString(3, r.getReferencia());
            pst.setBinaryStream(4, r.getAnexo());
            pst.setString(5, r.getDescricao());
            pst.setDate(6, r.getDataEvento());
            FuncionarioControl f = new FuncionarioControl();
            String cargo = f.getCargo(r.getMatFunc());

            if ((p.isFuncionarioGerencia()) || (p.isFuncionarioGercom() || (p.isFuncionarioGerjur()))) {//Funcionário de Gerência ou da Gercom ou do gerjur
                pst.setString(7, EnumSituacao.RECEBIDA.toString());//Gerência
                pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
                pst.setString(9, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
                pst.setString(10, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
            }

            if ((p.isGerente()) || (p.isFuncionarioDpto()) || (p.isFuncionarioDpci() || (p.isGerenteGercom()))) {//Gerente, Funcionário Dpto e Funcionário Depci
                pst.setString(7, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
                pst.setString(8, EnumSituacao.RECEBIDA.toString());//Departamento
                pst.setString(9, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
                pst.setString(10, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
            }

//            if (p.isGerenteGercom()){//Gerente da Gercom
//                 pst.setString(7, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
//                 pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
//                 pst.setString(9, EnumSituacao.RECEBIDA.toString());//Diretoria
//                 pst.setString(10, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
//            }
            if ((p.isChefe()) || (p.isFuncionarioDiretoria() || (p.isGerenteGerjur()))) {//Chefe ou Funcionário de DIretoria ou Gerente Jurídico
                System.out.println("Chefe");
                pst.setString(7, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
                pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
                pst.setString(9, EnumSituacao.RECEBIDA.toString());//Diretoria
                pst.setString(10, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
            }

            if ((p.isChefeDepci()) || (p.isDiretor())) {//Chefe do DEPCI ou diretores
                pst.setString(7, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
                pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
                pst.setString(9, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
                pst.setString(10, EnumSituacao.RECEBIDA.toString());//Presidência
            }

            pst.setString(11, EnumSituacao.NAO_RECEBIDA.toString());//RH

            pst.execute();
            TiposControle t = new TiposControle();
            String tipo = t.getNomeTipo(r.getIdTipo());

            LogModel log = new LogModel();
            log.setDescricao("Usuário registrou um evento. Matrícula: " + r.getMatFunc() + ". Referência: " + r.getReferencia()
                    + " Tipo:" + tipo);
            try {
                log.setIp(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException ex) {
                Logger.getLogger(Crud_Alteracao.class.getName()).log(Level.SEVERE, null, ex);
            }
            log.setMat(r.getMatFunc());
            log.setTipo("Criação de evento");
            Crud_Log c = new Crud_Log();
            c.InsereLog(log);

            //JOptionPane.showMessageDialog(null,"Incluído no repositório");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Crud_Repositorio/InsereRepositorio.\n" + ex);
        } finally {
            conecta.desconecta();
        }
    }

//    public void UpdateRepositorio(int idRepositorio, int mat) {
//        conecta.conexao();
//        PerfilModel p = new PerfilModel();
//        GetPerfil g = new GetPerfil();
//        p=g.preencherPerfil(mat);        
//        PreparedStatement pst;
//        //9 - diretoria, 8 departamento, 7 gerencia, 10 - presidencia
//        try {
//            pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioalteracao "
//                    + "set aprovacaoGerencia_RA=?, aprovacaoDepartamento_RA=?, aprovacaoDiretoria_RA=?, "
//                    + " AprovacaoPresidencia_RA=?, RecebimentoRH_RA=? where idrepositorio_ra=? ) VALUES (?,?,?,?,?,?)");
//            
//            FuncionarioControl f = new FuncionarioControl();
//            String cargo = f.getCargo(mat);
//            
//            if ((p.isFuncionarioGerencia())||(p.isFuncionarioGercom())){//Funcionário de Gerência ou da Gercom
//                 pst.setString(1, EnumSituacao.RECEBIDA.toString());//Gerência
//                 pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
//                 pst.setString(3, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
//                 pst.setString(4, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
//            }
//            
//           if ((p.isGerente())||(p.isFuncionarioDpto())||(p.isFuncionarioDpci())){//Gerente, Funcionário Dpto e Funcionário Depci
//                 pst.setString(1, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
//                 pst.setString(2, EnumSituacao.RECEBIDA.toString());//Departamento
//                 pst.setString(3, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
//                 pst.setString(4, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
//            }
//            
//            if (p.isGerenteGercom()){//Gerente da Gercom
//                 pst.setString(1, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
//                 pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
//                 pst.setString(3, EnumSituacao.RECEBIDA.toString());//Diretoria
//                 pst.setString(4, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
//            }
//            
//             if ((p.isChefe())||(p.isFuncionarioDiretoria())){//Chefe ou Funcionário de DIretoria
//                 pst.setString(1, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
//                 pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
//                 pst.setString(3, EnumSituacao.RECEBIDA.toString());//Diretoria
//                 pst.setString(4, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
//            }
//             
//              if ((p.isChefeDepci())||(p.isDiretor())){//Chefe do DEPCI ou diretores
//                 pst.setString(1, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
//                 pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
//                 pst.setString(3, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
//                 pst.setString(4, EnumSituacao.RECEBIDA.toString());//Presidência
//            }   
//              
//
//            pst.setString(5, EnumSituacao.NAO_RECEBIDA.toString());//RH
//
//            pst.execute();
//            //JOptionPane.showMessageDialog(null,"Incluído no repositório");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Crud_Repositorio/InsereRepositorio.\n" + ex);
//        } finally {
//            conecta.desconecta();
//        }
//    }
    public void ReenviaEvento(int mat, String referencia) {
        conecta.conexao();
        conecta2.conexao();

        conecta.executaPesquisaSQL("Select * from repositorioalteracao where alteracaofrequencia_matfunc_ra=" + mat + " and "
                + "alteracaofrequencia_referencia_ra='" + referencia + "'");
        try {
            if (conecta.rs.first() == true) {
                do {
                    int id = conecta.rs.getInt("idRepositorio_RA");
                    RepositorioControl r = new RepositorioControl();
                    if (r.verificaTrava(id) == false) {//Corre todos os eventos se tiver 1 nao finalizado, retorna false. Se for false, reseta alteração

                        PerfilModel p = new PerfilModel();
                        GetPerfil g = new GetPerfil();
                        p = g.preencherPerfil(mat);
                        PreparedStatement pst;
                        conecta2.conexao();
                        pst = (PreparedStatement) conecta2.conn.prepareStatement("Update repositorioalteracao set aprovacaogerencia_ra=?, "
                                + "aprovacaodepartamento_ra=?, aprovacaodiretoria_ra=?, aprovacaopresidencia_ra=? where idrepositorio_ra=?");

                         //##Verificação de Perfil
                        if ((p.isFuncionarioGerencia()) || (p.isFuncionarioGercom() || (p.isFuncionarioGerjur()))) {//Funcionário de Gerência ou da Gercom ou do Jurídico
                            pst.setString(1, EnumSituacao.RECEBIDA.toString());//Gerência
                            pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
                            pst.setString(3, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
                            pst.setString(4, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
                        }

                        if ((p.isGerente()) || (p.isFuncionarioDpto()) || (p.isFuncionarioDpci() || (p.isGerenteGercom()))) {//Gerente, Funcionário Dpto e Funcionário Depci
                            pst.setString(1, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
                            pst.setString(2, EnumSituacao.RECEBIDA.toString());//Departamento
                            pst.setString(3, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
                            pst.setString(4, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
                        }

//            if (p.isGerenteGercom()){//Gerente da Gercom
//                 pst.setString(7, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
//                 pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
//                 pst.setString(9, EnumSituacao.RECEBIDA.toString());//Diretoria
//                 pst.setString(10, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
//            }
                        if ((p.isChefe()) || (p.isFuncionarioDiretoria() || (p.isGerenteGerjur()))) {//Chefe ou Funcionário de DIretoria
                            pst.setString(1, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
                            pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
                            pst.setString(3, EnumSituacao.RECEBIDA.toString());//Diretoria
                            pst.setString(4, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
                        }

                        if ((p.isChefeDepci()) || (p.isDiretor())) {//Chefe do DEPCI ou diretores
                            pst.setString(1, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
                            pst.setString(2, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
                            pst.setString(3, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
                            pst.setString(4, EnumSituacao.RECEBIDA.toString());//Presidência
                        }
                        pst.setInt(5, id);
                        pst.executeUpdate();

                        LogModel log = new LogModel();
                        log.setDescricao("Usuário enviou evento. Referência: " + referencia + " Matrícula: " + mat
                                + " IdEvento: " + id);
                        try {
                            log.setIp(InetAddress.getLocalHost().getHostAddress());
                        } catch (UnknownHostException ex) {
                            Logger.getLogger(Crud_Alteracao.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        log.setMat(mat);
                        log.setTipo("Envio de Evento");
                        Crud_Log c = new Crud_Log();
                        c.InsereLog(log);
                    }

                } while (conecta.rs.next());
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "CrudRepositorio/ReenviaEvento.\n" + ex);
        } finally {
            conecta.desconecta();
        }

    }
    
     public void AlteraCompetencia (int idRepositorio, String referencia){
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst=(PreparedStatement) conecta.conn.prepareStatement("update repositorioalteracao set "
                    + "Alteracaofrequencia_Referencia_RA=? where idRepositorio_RA=?");
            pst.setString(1, referencia);
            pst.setInt(2, idRepositorio);
            pst.executeUpdate();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"CrudRepositorio/AlteraCompetencia.\n"+ex);
        } finally {
            conecta.desconecta();
        }
    }

    public void atualizaItemRepositorio(RepositorioModel r) {//Esse aqui altera os niveis dos eventos, mas precisa ?
        conecta.conexao();
        PerfilModel p = new PerfilModel();
        GetPerfil g = new GetPerfil();
        p = g.preencherPerfil(r.getMatFunc());
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set idTipo_RA=?,"
                    + " Anexo_RA=?, Descricao_RA=?, dataEvento_RA=?, aprovacaoGerencia_RA=?, aprovacaoDepartamento_RA=?, aprovacaoDiretoria_RA=?, "
                    + " AprovacaoPresidencia_RA=?, RecebimentoRH_RA=? where idRepositorio_RA=?");
            pst.setInt(1, r.getIdTipo());
            pst.setBinaryStream(2, r.getAnexo());
            pst.setString(3, r.getDescricao());
            pst.setDate(4, r.getDataEvento());
            //##Verificação de Perfil
            //Gerencia 5, Departamento 6, Diretoria 7, Presidencia 8, RH 9
            if ((p.isFuncionarioGerencia()) || (p.isFuncionarioGercom() || (p.isFuncionarioGerjur()))) {//Funcionário de Gerência ou da Gercom
                pst.setString(5, EnumSituacao.RECEBIDA.toString());//Gerência
                pst.setString(6, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
                pst.setString(7, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
                pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
                pst.setString(9, EnumSituacao.NAO_RECEBIDA.toString());//RH
            }

            if ((p.isGerente()) || (p.isFuncionarioDpto()) || (p.isFuncionarioDpci() || (p.isGerenteGercom()))) {//Gerente, Funcionário Dpto e Funcionário Depci
                pst.setString(5, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
                pst.setString(6, EnumSituacao.RECEBIDA.toString());//Departamento
                pst.setString(7, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
                pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
                pst.setString(9, EnumSituacao.NAO_RECEBIDA.toString());//RH
            }

//            if (p.isGerenteGercom()){//Gerente da Gercom
//                 pst.setString(5, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
//                 pst.setString(6, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
//                 pst.setString(7, EnumSituacao.RECEBIDA.toString());//Diretoria
//                 pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
//                  pst.setString(9, EnumSituacao.NAO_RECEBIDA.toString());//RH
//            }
            if ((p.isChefe()) || (p.isFuncionarioDiretoria() || (p.isGerenteGerjur()))) {//Chefe ou Funcionário de DIretoria
                pst.setString(5, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
                pst.setString(6, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
                pst.setString(7, EnumSituacao.RECEBIDA.toString());//Diretoria
                pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
                pst.setString(9, EnumSituacao.NAO_RECEBIDA.toString());//RH
            }

            if ((p.isChefeDepci()) || (p.isDiretor())) {//Chefe do DEPCI ou diretores
                pst.setString(5, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
                pst.setString(6, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
                pst.setString(7, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
                pst.setString(8, EnumSituacao.RECEBIDA.toString());//Presidência
                pst.setString(9, EnumSituacao.NAO_RECEBIDA.toString());//RH
            }

            pst.setInt(10, r.getIdRepositorio());
//            pst.setInt(11, r.getMatFunc());
//            pst.setString(12, r.getReferencia());
//            System.out.println("Referência: "+r.getReferencia());
//Antes eu fazia o where com referencia e matricula, mas não precisa pq o idrepositorio nao altera

            pst.executeUpdate();
            
            //Verifica a nova competência do evento
            AlteracaoFrequenciaControl aFC = new AlteracaoFrequenciaControl();
            Crud_Alteracao crudAlt = new Crud_Alteracao();
            
            if (aFC.verificaExistenciaAlteracao(r.getMatFunc(), r.getReferencia())==true){
                AlteraCompetencia(r.getIdRepositorio(),r.getReferencia());
            } else {
                crudAlt.CriaIdPorAlteracaoEvento(r.getMatFunc(), r.getReferencia());
                 AlteraCompetencia(r.getIdRepositorio(),r.getReferencia());
            }

            LogModel log = new LogModel();
            log.setDescricao("Usuário atualizou evento. Referência: " + r.getReferencia() + " Matrícula: " + r.getMatFunc()
                    + " IdEvento: " + r.getIdRepositorio());
            try {
                log.setIp(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException ex) {
                Logger.getLogger(Crud_Alteracao.class.getName()).log(Level.SEVERE, null, ex);
            }
            log.setMat(r.getMatFunc());
            log.setTipo("Atualização de evento");
            Crud_Log c = new Crud_Log();
            c.InsereLog(log);

            JOptionPane.showMessageDialog(null, "O evento foi alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Crud_Repositorio/InsereRepositorio.\n" + ex);
        } finally {
            conecta.desconecta();
        }

    }

    public void atualizaItemRepositorioSemAnexo(RepositorioModel r) {//Esse aqui altera os niveis dos eventos, mas precisa ?
        conecta.conexao();
        PerfilModel p = new PerfilModel();
        GetPerfil g = new GetPerfil();
        p = g.preencherPerfil(r.getMatFunc());
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set idTipo_RA=?,"
                    + " Descricao_RA=?, dataEvento_RA=?, aprovacaoGerencia_RA=?, aprovacaoDepartamento_RA=?, aprovacaoDiretoria_RA=?, "
                    + " AprovacaoPresidencia_RA=?, RecebimentoRH_RA=? where idRepositorio_RA=?");
            pst.setInt(1, r.getIdTipo());

            pst.setString(2, r.getDescricao());
            pst.setDate(3, r.getDataEvento());
            //Gerencia 5, Departamento 6, Diretoria 7, Presidencia 8, RH 9
           //##Verificação de Perfil
            if ((p.isFuncionarioGerencia()) || (p.isFuncionarioGercom()) || (p.isFuncionarioGerjur())) {//Funcionário de Gerência ou da Gercom ou do Gerjur
                pst.setString(4, EnumSituacao.RECEBIDA.toString());//Gerência
                pst.setString(5, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
                pst.setString(6, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
                pst.setString(7, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
                pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());//RH
            }

            if ((p.isGerente()) || (p.isFuncionarioDpto()) || (p.isFuncionarioDpci() || (p.isGerenteGercom()))) {//Gerente, Funcionário Dpto e Funcionário Depci
                pst.setString(4, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
                pst.setString(5, EnumSituacao.RECEBIDA.toString());//Departamento
                pst.setString(6, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
                pst.setString(7, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
                pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());//RH
            }


            if ((p.isChefe()) || (p.isFuncionarioDiretoria() || (p.isGerenteGerjur()))) {//Chefe ou Funcionário de DIretoria ou Gerente Gerjur
                pst.setString(4, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
                pst.setString(5, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
                pst.setString(6, EnumSituacao.RECEBIDA.toString());//Diretoria
                pst.setString(7, EnumSituacao.NAO_RECEBIDA.toString());//Presidência
                pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());//RH
            }

            if ((p.isChefeDepci()) || (p.isDiretor())) {//Chefe do DEPCI ou diretores
                pst.setString(4, EnumSituacao.NAO_RECEBIDA.toString());//Gerência
                pst.setString(5, EnumSituacao.NAO_RECEBIDA.toString());//Departamento
                pst.setString(6, EnumSituacao.NAO_RECEBIDA.toString());//Diretoria
                pst.setString(7, EnumSituacao.RECEBIDA.toString());//Presidência
                pst.setString(8, EnumSituacao.NAO_RECEBIDA.toString());//RH
            }

            pst.setInt(9, r.getIdRepositorio());
//            pst.setInt(10, r.getMatFunc());
//            pst.setString(11, r.getReferencia());
//            System.out.println("Referência: "+r.getReferencia());
//Antes eu fazia o where com referencia e matricula, mas não precisa pq o idrepositorio nao altera

            pst.executeUpdate();
            
              //Verifica a nova competência do evento
            AlteracaoFrequenciaControl aFC = new AlteracaoFrequenciaControl();
            Crud_Alteracao crudAlt = new Crud_Alteracao();
            
            if (aFC.verificaExistenciaAlteracao(r.getMatFunc(), r.getReferencia())==true){
                AlteraCompetencia(r.getIdRepositorio(),r.getReferencia());
            } else {
                crudAlt.CriaIdPorAlteracaoEvento(r.getMatFunc(), r.getReferencia());
                 AlteraCompetencia(r.getIdRepositorio(),r.getReferencia());
            }
            
//            //Verifica se existe eventos na alteração. Se não existir deleta a 
//            if (aFC.verificaEventosNaAlteracao(r.getMatFunc(), r.getReferencia())==false){
//                aFC.DeletaAlteracao(r.getMatFunc(), r.getReferencia());
//            }

            LogModel log = new LogModel();
            log.setDescricao("Usuário atualizou evento. Referência: " + r.getReferencia() + " Matrícula: " + r.getMatFunc()
                    + " IdEvento: " + r.getIdRepositorio());
            try {
                log.setIp(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException ex) {
                Logger.getLogger(Crud_Alteracao.class.getName()).log(Level.SEVERE, null, ex);
            }
            log.setMat(r.getMatFunc());
            log.setTipo("Atualização de evento");
            Crud_Log c = new Crud_Log();
            c.InsereLog(log);

            JOptionPane.showMessageDialog(null, "O evento foi alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Crud_Repositorio/InsereRepositorio.\n" + ex);
        } finally {
            conecta.desconecta();
        }

    }

    public void atualizaItemRepositorio2(RepositorioModel r) {
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioAlteracao set idTipo_RA=?,"
                    + " Anexo_RA=?, Descricao_RA=?, dataEvento_RA=? where idRepositorio_RA=?");
            pst.setInt(1, r.getIdTipo());
            pst.setBinaryStream(2, r.getAnexo());
            pst.setString(3, r.getDescricao());
            pst.setDate(4, r.getDataEvento());
            pst.setInt(5, r.getIdRepositorio());
//            pst.setInt(6, r.getMatFunc());
//            pst.setString(7, r.getReferencia());
//Antes eu fazia o where com referencia e matricula, mas não precisa pq o idrepositorio nao altera
//                    System.out.println("Tipo: "+r.getIdTipo());
//                    System.out.println("Descricao: "+r.getDescricao());
//                    System.out.println("DataEvento: "+r.getDataEvento());
//                    System.out.println("idRepositorio: "+r.getIdRepositorio());
//                    System.out.println("Mat: "+r.getMatFunc());
//                    System.out.println("Referencia: "+r.getReferencia());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "O evento foi alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Crud_Repositorio/InsereRepositorio.\n" + ex);
        } finally {
            conecta.desconecta();
        }

    }

    public void apagaEvento(int id) {
        RepositorioControl r = new RepositorioControl();
        TiposControle t = new TiposControle();
        LogModel log = new LogModel();
        int mat=r.getMat(id);
        log.setDescricao("Usuário deletou evento. Referência: " + r.getReferencia(id) + " Matrícula: " + r.getMat(id)
                + " IdEvento: " + id + " Tipo de Evento: " + t.getNomeTipo(r.getTipo(id)));
        conecta.conexao();
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conecta.conn.prepareStatement("Delete from repositorioalteracao where idrepositorio_ra=?");
            pst.setInt(1, id);
            pst.executeUpdate();

            try {
                log.setIp(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException ex) {
                Logger.getLogger(Crud_Alteracao.class.getName()).log(Level.SEVERE, null, ex);
            }
            log.setMat(mat);
            log.setTipo("Evento deletado");
            Crud_Log c = new Crud_Log();
            c.InsereLog(log);

            JOptionPane.showMessageDialog(null, "Evento deletado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "CrudRepositorio/ApagaEvento.\n" + ex);
        } finally {
            conecta.desconecta();
        }

    }
    
   

}
