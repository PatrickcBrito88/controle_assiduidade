package controllers;

import email_.ComposicaoEmail;
import email_.EnvioEmail;
import domain_enums.EnumSituacao;
import utils.ConectaBanco;
import com.mysql.jdbc.PreparedStatement;
import domain.Modelo.AlteraStatusModel;
import domain.Modelo.AnalisaSituacaoModel;
import domain.Modelo.ControleStatusModel;
import domain.Modelo.EmailModelo;
import domain.Modelo.HierarquiaModel;
import domain.Modelo.NomeLocalModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AnalisarAlteracao {

    ConectaBanco conecta = new ConectaBanco();

    public void AnalisarEvento(AnalisaSituacaoModel analisaSituacao) {
        conecta.conexao();
        PreparedStatement pst;

        if (analisaSituacao.getLocal().equals("Gerência")) {
            try {
                pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioalteracao set AprovacaoGerencia_RA=?, "
                        + "AprovacaoDepartamento_RA=? where idRepositorio_RA=?");
                pst.setString(1, analisaSituacao.getSituacao().toString());
                pst.setString(2, EnumSituacao.RECEBIDA.toString());
                pst.setInt(3, analisaSituacao.getIdRepositorio());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Situação alterada com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "AnalisaAlteracao/AnalisaEvento.\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }

        if (analisaSituacao.getLocal().equals("Departamento")) {// FALTA FAZER UMA CONDIÇÃO PARA QUANDO FOR A GERCOM
            //sugestão colocar uma condição dentro do model de analisasituação para dizer se o departamento é igual 
            //ou diferente de 999
            try {

                pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioalteracao set AprovacaoDepartamento_RA=?, "
                        + "AprovacaoDiretoria_RA=? where idRepositorio_RA=?");
                pst.setString(1, analisaSituacao.getSituacao().toString());
                pst.setString(2, EnumSituacao.RECEBIDA.toString());
                pst.setInt(3, analisaSituacao.getIdRepositorio());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Situação alterada com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "AnalisaAlteracao/AnalisaEvento.\n" + ex);
            } finally {
                conecta.desconecta();
            }

        }

        if (analisaSituacao.getLocal().equals("Diretoria")) {// FALTA FAZER UMA CONDIÇÃO PARA QUANDO FOR A GERCOM
            //sugestão colocar uma condição dentro do model de analisasituação para dizer se o departamento é igual 
            //ou diferente de 999
            try {

                pst = (PreparedStatement) conecta.conn.prepareStatement("Update repositorioalteracao set AprovacaoDiretoria_RA=?"
                        + " where idRepositorio_RA=?");
                pst.setString(1, analisaSituacao.getSituacao().toString());
                pst.setInt(2, analisaSituacao.getIdRepositorio());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Situação alterada com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "AnalisaAlteracao/AnalisaEvento.\n" + ex);
            } finally {
                conecta.desconecta();
            }

        }

    }

    public ControleStatusModel alteraSituacaoAlteracaoFrequencia(AlteraStatusModel statusModel) {
        conecta.conexao();
        ControleStatusModel controle = new ControleStatusModel();
        controle.setTodosNaoAbonados(false);
        int contNaoAbonada = 0;
        int tamanhoLista = 0;
        boolean sinalExterno = true;
        conecta.executaPesquisaSQL("SELECT * FROM `repositorioalteracao` where AlteracaoFrequencia_MatFunc_RA=" + statusModel.getMat()
                + " and Alteracaofrequencia_Referencia_RA='" + statusModel.getReferencia() + "' and naoabono_idnaoabono_RA=1");

        //Se for Gerência
        if (statusModel.getLocalizacao().equals("Gerência")) {

            try {

                conecta.rs.first();
                do {
                    tamanhoLista++;
                    boolean sinal;
                    String conteudo = "";

                    conteudo = conecta.rs.getString("AprovacaoGerencia_RA");//RECEBIDA

                    if (!((conteudo.equals("ABONADA") || (conteudo.equals("NAO_ABONADA"))))) {
                        sinal = false;
                        sinalExterno = sinal;

//            System.out.println(String.valueOf(conecta.rs.getInt("idRepositorio_RA"))+sinalExterno);
                    }
                    if (conteudo.equals("NAO_ABONADA")) {
                        contNaoAbonada++;

                    }

                } while (conecta.rs.next() || sinalExterno == false);
            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, "AnalisarAlteração/AlteraSituacaoAlteracaoFrequencia.1\n"+ex);
                //Foi comentada. A função está funcionando  mas esta dando mensagem de erro de end of resultset
            } finally {

                controle.setSinal(sinalExterno);
                controle.setContNaoAbonada(contNaoAbonada);
                if (tamanhoLista == contNaoAbonada) {
                    controle.setTodosNaoAbonados(true);
                    controle.setContNaoAbonada(0);// Se for = ele recebe 0 para não cair no IF do contNao Abonada  recebe Abonada Parcialmente
                }
                conecta.desconecta();
            }
        }

        //se for departamento
        if (statusModel.getLocalizacao().equals("Departamento")) {

            try {

                conecta.rs.first();
                do {
                    tamanhoLista++;
                    boolean sinal;
                    String conteudo = "";

                    conteudo = conecta.rs.getString("AprovacaoDepartamento_RA");//RECEBIDA

                    if (!((conteudo.equals("ABONADA") || (conteudo.equals("NAO_ABONADA"))))) {
                        sinal = false;
                        sinalExterno = sinal;

//            System.out.println(String.valueOf(conecta.rs.getInt("idRepositorio_RA"))+sinalExterno);
                    }
                    if (conteudo.equals("NAO_ABONADA")) {
                        contNaoAbonada++;

                    }

                } while ((conecta.rs.next() || sinalExterno == false));
            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, "AnalisarAlteração/AlteraSituacaoAlteracaoFrequencia2.\n"+ex);
                //Foi comentada. A função está funcionando  mas esta dando mensagem de erro de end of resultset
            } finally {
                controle.setSinal(sinalExterno);
                controle.setContNaoAbonada(contNaoAbonada);
                if (tamanhoLista == contNaoAbonada) {
                    controle.setTodosNaoAbonados(true);
                    controle.setContNaoAbonada(0);
                }
                conecta.desconecta();
            }
        }
        //se for diretoria
        if (statusModel.getLocalizacao().equals("Diretoria")) {

            try {

                conecta.rs.first();
                do {
                    tamanhoLista++;
                    boolean sinal;
                    String conteudo = "";

                    conteudo = conecta.rs.getString("AprovacaoDiretoria_RA");//RECEBIDA

                    if (!((conteudo.equals("ABONADA") || (conteudo.equals("NAO_ABONADA"))))) {
                        sinal = false;
                        sinalExterno = sinal;

//            System.out.println(String.valueOf(conecta.rs.getInt("idRepositorio_RA"))+sinalExterno);
                    }
                    if (conteudo.equals("NAO_ABONADA")) {
                        contNaoAbonada++;

                    }

                } while ((conecta.rs.next() || sinalExterno == false));
            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, "AnalisarAlteração/AlteraSituacaoAlteracaoFrequencia3.\n"+ex);
                //Foi comentada. A função está funcionando  mas esta dando mensagem de erro de end of resultset
            } finally {
                controle.setSinal(sinalExterno);
                controle.setContNaoAbonada(contNaoAbonada);
                if (tamanhoLista == contNaoAbonada) {
                    controle.setTodosNaoAbonados(true);
                    controle.setContNaoAbonada(0);
                }
                conecta.desconecta();
            }
        }

        if (statusModel.getLocalizacao().equals("Presidência")) {

            try {
                conecta.rs.first();
                do {
                    tamanhoLista++;
                    boolean sinal;
                    String conteudo = "";
                    conteudo = conecta.rs.getString("AprovacaoPresidencia_RA");//RECEBIDA
                    if (!((conteudo.equals("ABONADA") || (conteudo.equals("NAO_ABONADA"))))) {
                        sinal = false;
                        sinalExterno = sinal;

//            System.out.println(String.valueOf(conecta.rs.getInt("idRepositorio_RA"))+sinalExterno);
                    }
                    if (conteudo.equals("NAO_ABONADA")) {
                        contNaoAbonada++;

                    }

                } while ((conecta.rs.next() || sinalExterno == false));
            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, "AnalisarAlteração/AlteraSituacaoAlteracaoFrequencia3.\n"+ex);
                //Foi comentada. A função está funcionando  mas esta dando mensagem de erro de end of resultset
            } finally {
                controle.setSinal(sinalExterno);
                controle.setContNaoAbonada(contNaoAbonada);
                if (tamanhoLista == contNaoAbonada) {
                    controle.setTodosNaoAbonados(true);
                    controle.setContNaoAbonada(0);
                }
                conecta.desconecta();
            }
        }//Fim Presidência
//          System.out.println("Sinal externo: "+sinalExterno);
        return controle;
    }

    public void atualizaAlteracao(int mat, String referencia, String local, ControleStatusModel controle) {
        conecta.conexao();
        PreparedStatement pst;
        //Local é o local que está fazendo as avaliações

        //gerencia
        if (local.equals("Gerência")) {
            System.out.println("Entrou a");
            try {
                pst = (PreparedStatement) conecta.conn.prepareStatement("Update AlteracaoFrequencia set situacaoGerencia_AF=? where Funcionario_matfunc_af=?"
                        + " and referencia_af=? ");
                if (controle.getContNaoAbonada() != 0) {
                    pst.setString(1, EnumSituacao.PARCIALMENTE_ABONADA.toString());
                }
                if (controle.isTodosNaoAbonados()) {
                    pst.setString(1, EnumSituacao.NAO_ABONADA.toString());
                }
                if ((controle.isTodosNaoAbonados() == false) && (controle.getContNaoAbonada() == 0)) {
                    pst.setString(1, EnumSituacao.ABONADA.toString());
                }
                pst.setInt(2, mat);
                pst.setString(3, referencia);
                pst.executeUpdate();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " AnalisarAlteracao/atualizaAlteracao.\n" + ex);
            } finally {
                conecta.desconecta();
            }

        }

        //departamento
        if (local.equals("Departamento")) {
//            System.out.println("Entrou b");
            try {
                pst = (PreparedStatement) conecta.conn.prepareStatement("Update AlteracaoFrequencia set situacaoDepartamento_AF=? where Funcionario_matfunc_af=?"
                        + " and referencia_af=? ");
                if (controle.getContNaoAbonada() != 0) {
                    pst.setString(1, EnumSituacao.PARCIALMENTE_ABONADA.toString());
                }
                if (controle.isTodosNaoAbonados()) {
                    pst.setString(1, EnumSituacao.NAO_ABONADA.toString());
                }
                if ((controle.isTodosNaoAbonados() == false) && (controle.getContNaoAbonada() == 0)) {
                    pst.setString(1, EnumSituacao.ABONADA.toString());
                }
                pst.setInt(2, mat);
                pst.setString(3, referencia);
                pst.executeUpdate();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, " AnalisarAlteracao/atualizaAlteracao.\n" + ex);
            } finally {
                conecta.desconecta();
            }

        }

        //Diretoria
        if (local.equals("Diretoria")) {

            System.out.println(local + " Entrou c");
            try {
                pst = (PreparedStatement) conecta.conn.prepareStatement("Update AlteracaoFrequencia set situacaoDiretoria_AF=? where Funcionario_matfunc_af=?"
                        + " and referencia_af=? ");
                if (controle.getContNaoAbonada() != 0) {
                    pst.setString(1, EnumSituacao.PARCIALMENTE_ABONADA.toString());
                }
                if (controle.isTodosNaoAbonados()) {
                    pst.setString(1, EnumSituacao.NAO_ABONADA.toString());
                }
                if ((controle.isTodosNaoAbonados() == false) && (controle.getContNaoAbonada() == 0)) {
                    pst.setString(1, EnumSituacao.ABONADA.toString());
                }
                pst.setInt(2, mat);
                pst.setString(3, referencia);
                pst.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " AnalisarAlteracao/atualizaAlteracao.\n" + ex);
            } finally {
                conecta.desconecta();
            }

        }

        //Diretoria
        if (local.equals("Presidência")) {

            System.out.println(local + " Entrou c");
            try {
                pst = (PreparedStatement) conecta.conn.prepareStatement("Update AlteracaoFrequencia set situacaoPresidencia_AF=? where Funcionario_matfunc_af=?"
                        + " and referencia_af=? ");
                if (controle.getContNaoAbonada() != 0) {
                    pst.setString(1, EnumSituacao.PARCIALMENTE_ABONADA.toString());
                }
                if (controle.isTodosNaoAbonados()) {
                    pst.setString(1, EnumSituacao.NAO_ABONADA.toString());
                }
                if ((controle.isTodosNaoAbonados() == false) && (controle.getContNaoAbonada() == 0)) {
                    pst.setString(1, EnumSituacao.ABONADA.toString());
                }
                pst.setInt(2, mat);
                pst.setString(3, referencia);
                pst.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " AnalisarAlteracao/atualizaAlteracao.\n" + ex);
            } finally {
                conecta.desconecta();
            }

        }

    }

    public void PassaProximoNivel(String local, String nomeLocal, int mat, String referencia) {
        conecta.conexao();

        //Pegar um hierarquiaModel da matrícula
        HierarquiaModel h = new HierarquiaModel();
        CalculaHierarquia c = new CalculaHierarquia();
        h = c.getHierarquiaFuncionario(mat); //CONTEM TODO O CORPO DE RELACIONAMENTO DO LOCAL
        AlteracaoFrequenciaControl a = new AlteracaoFrequenciaControl();
        SetoresControl s = new SetoresControl();
        NomeLocalModel nome = new NomeLocalModel();
        nome = s.getNomeLocal(mat);

        //--------------E-MAIL-------------
        EmailModelo emailModelo = new EmailModelo();
        EnvioEmail eMail = new EnvioEmail();
        ComposicaoEmail comporEmail = new ComposicaoEmail();

        emailModelo = comporEmail.ComporEmail(mat);
        emailModelo.setPeriodo(referencia);

        //LOCAL É O NÍVEL DE QUEM CRIOU A ALTERAÇÃO DE FREQUENCIA
        //Gerência
        if (local.equals("Gerência")) {
            System.out.println("Entrou na gerência");
            int idDepartamento = h.getIdDepartamento();
            int idGerencia = h.getIdGerencia();
            int idDiretoria = h.getIdDiretoria();
            int idPresidencia = h.getIdPresidencia();
            GerenciaControl g = new GerenciaControl();
            DepartamentoControl dE = new DepartamentoControl();
            DiretoriaControl dI = new DiretoriaControl();

            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where funcionario_matfunc_af=" + mat + " "
                    + "and referencia_AF='" + referencia + "'");
            try {
                conecta.rs.first();
                String situacaoGerencia = conecta.rs.getString("SituacaoGerencia_AF");
                String situacaoDepartamento = conecta.rs.getString("SituacaoDepartamento_AF");
                String situacaoDiretoria = conecta.rs.getString("SituacaoDiretoria_AF");
                String situacaoPresidencia = conecta.rs.getString("SituacaoPresidencia_AF");

                boolean GerenciaOk = false;
                boolean DepartamentoOk = false;
                boolean DiretoriaOk = false;
                boolean PresidenciaOk = false;

                if ((situacaoGerencia.equals("ABONADA") || situacaoGerencia.equals("NAO_ABONADA") || situacaoGerencia.equals("PARCIALMENTE_ABONADA"))) {
                    GerenciaOk = true;
                }

                if ((situacaoDepartamento.equals("ABONADA") || situacaoDepartamento.equals("NAO_ABONADA") || situacaoDepartamento.equals("PARCIALMENTE_ABONADA"))) {
                    DepartamentoOk = true;
                }

                if ((situacaoDiretoria.equals("ABONADA") || situacaoDiretoria.equals("NAO_ABONADA") || situacaoDiretoria.equals("PARCIALMENTE_ABONADA"))) {
                    DiretoriaOk = true;

                }

                if ((situacaoPresidencia.equals("ABONADA") || situacaoPresidencia.equals("NAO_ABONADA") || situacaoPresidencia.equals("PARCIALMENTE_ABONADA"))) {
                    PresidenciaOk = true;

                }

                //Funcionário de Gerência  e Gercom para Departamento 
                if ((GerenciaOk == true) && (DepartamentoOk == false) && (h.getIdDiretoria()==999)) {
                    idDiretoria = dE.getDiretoria(idDepartamento);//Se entrar aqui significa que o IDDiretoria é igual a 999
                    a.AlteraAvaliacaoDepartamento(mat, referencia);//Altera a SItuacaoDepartamento para recebida

                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeGerencia());//Local que está (Verificou que está na gerência e pegou o nome da gerência)
                    emailModelo.setPendente(nome.getNomeDepartamento());//Local para onde vai (Identificou que vai para o dpto e pegou o nome)

                    eMail.enviaAndamentoAvaliacao(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getPendente(), emailModelo.getEmailDestinatario());//Envia o Email
                    DepartamentoControl d = new DepartamentoControl();
                    String emailDestinatario = d.getEmailNomeDpto(emailModelo.getPendente());
                    eMail.NotificaSetores(emailDestinatario, emailModelo.getPendente());

                }
                
                //Ou seja se sair da Gerência Direto para a Diretoria...
                if ((GerenciaOk == true) && (DiretoriaOk == false) && (h.getIdDepartamento()==999)) {
                    idDiretoria = dE.getDiretoria(idDepartamento);//Se entrar aqui significa que o IDDiretoria é igual a 999
                    a.AlteraAvaliacaoDiretoria(mat, referencia);//Altera a SItuacaoDepartamento para recebida

                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeGerencia());//Local que está (Verificou que está na gerência e pegou o nome da gerência)
                    emailModelo.setPendente(nome.getNomeDiretoria());//Local para onde vai (Identificou que vai para o dpto e pegou o nome)

                    eMail.enviaAndamentoAvaliacao(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getPendente(), emailModelo.getEmailDestinatario());//Envia o Email
                    DepartamentoControl d = new DepartamentoControl();
                    String emailDestinatario = dI.getEmailNomeDiretoria(emailModelo.getPendente());
                    eMail.NotificaSetores(emailDestinatario, emailModelo.getPendente());

                }
                
                //Ou seja se sair da Gerência Direto para a Diretoria...
                if ((GerenciaOk == false) && (DiretoriaOk == true) && (h.getIdDepartamento()==999)) {
                    //Este método confundi-se com o funcionário de diretoria, mas fica restruto ao tipoLocal que criou a avaliação
                    idDiretoria = dE.getDiretoria(idDepartamento);//Se entrar aqui significa que o IDDiretoria é igual a 999
                    a.AlteraAvaliacaoRH(mat, referencia);//Altera a SItuacaoDepartamento para recebida

                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeGerencia());//Local que está (Verificou que está na gerência e pegou o nome da gerência)
                    emailModelo.setPendente(nome.getNomeDiretoria());//Local para onde vai (Identificou que vai para o dpto e pegou o nome)

                    eMail.enviaAndamentoAvaliacao(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getPendente(), emailModelo.getEmailDestinatario());//Envia o Email
                    DepartamentoControl d = new DepartamentoControl();
                    String emailDestinatario = dI.getEmailNomeDiretoria(emailModelo.getPendente());
                    eMail.NotificaSetores(emailDestinatario, emailModelo.getPendente());

                }
                
                  //Ou seja saiu da Gerência para a Diretoria e da Diretoria para o RH
                if ((GerenciaOk == true) && (DiretoriaOk == true) && (h.getIdDepartamento()==999)) {
                    a.AlteraAvaliacaoRH(mat, referencia);//Altera a situacaoRH para recebida
                    System.out.println("Entrou 2");
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeDiretoria());//Local que está (Verificou que está na gerência e no departamento esta ok e pegou o nome da diretoria)

                    eMail.enviaRH(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getEmailDestinatario());//Envia o Email
                }
                
                
                //Funcionário Gerência para Diretoria, após o Departamento estar ok
                if ((GerenciaOk == true) && (DepartamentoOk == true) && (DiretoriaOk == false)
                        && (h.getIdDiretoria() == 999) && (h.getIdPresidencia() == 999)) {
                    idDiretoria = h.getIdDiretoria();
                    a.AlteraAvaliacaoDiretoria(mat, referencia);
                    System.out.println("Entrou 1");
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeDepartamento());//Local que está (Verificou que está na gerência e pegou o nome da gerência)
                    emailModelo.setPendente(nome.getNomeDiretoria());//Local para onde vai (Identificou que vai para a diretoria e pegou o nome)

                    eMail.enviaAndamentoAvaliacao(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getPendente(), emailModelo.getEmailDestinatario());//Envia o Email

                    DiretoriaControl d = new DiretoriaControl();
                    String emailDestinatario = d.getEmailNomeDiretoria(emailModelo.getPendente());
                    eMail.NotificaSetores(emailDestinatario, emailModelo.getPendente());
                }
                //Funcionário de Gerência para o RH
                if ((GerenciaOk == true) && (DepartamentoOk == true) && (DiretoriaOk == true)) {
                    a.AlteraAvaliacaoRH(mat, referencia);//Altera a situacaoRH para recebida
                    System.out.println("Entrou 2");
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeDiretoria());//Local que está (Verificou que está na gerência e no departamento esta ok e pegou o nome da diretoria)

                    eMail.enviaRH(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getEmailDestinatario());//Envia o Email
                    //Vai notificar o RH??
                }

                //Gerente do Departamento para a Diretoria
                if ((GerenciaOk == false) && (DepartamentoOk == true) && (DiretoriaOk == false) && (h.getIdPresidencia() == 999)) {
                    idDiretoria = h.getIdDiretoria();
                    a.AlteraAvaliacaoDiretoria(mat, referencia);
                    System.out.println("Entrou 3");
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeDepartamento());//Local que está (Verificou que está na gerência e pegou o nome da gerência)
                    emailModelo.setPendente(nome.getNomeDiretoria());//Local para onde vai (Identificou que vai para a diretoria e pegou o nome)

                    eMail.enviaAndamentoAvaliacao(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getPendente(), emailModelo.getEmailDestinatario());//Envia o Email

                    DiretoriaControl d = new DiretoriaControl();
                    String emailDestinatario = d.getEmailNomeDiretoria(emailModelo.getPendente());
                    eMail.NotificaSetores(emailDestinatario, emailModelo.getPendente());

                }
                //Gerente para o RH
                if ((GerenciaOk == false) && (DepartamentoOk == true) && (DiretoriaOk == true)) {
                    a.AlteraAvaliacaoRH(mat, referencia);//Altera a situacaoRH para recebida
                    System.out.println("Entrou 4");
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeDiretoria());//Local que está (Verificou que está na gerência e no departamento esta ok e pegou o nome da diretoria)

                    eMail.enviaRH(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getEmailDestinatario());//Envia o Email
                    //Vai notificar o RH??
                }

                //FuncionárioGercom para a Presidência
                if ((GerenciaOk == true) && (DepartamentoOk == true) && (PresidenciaOk == false) && (h.getIdDiretoria() == 999)
                        && (h.getIdPresidencia() != 999)) {
                    a.AlteraAvaliacaoPresidencia(mat, referencia);
                    System.out.println("Entrou 5");
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeDepartamento());//Local que está (Verificou que está na gerência e no departamento esta ok e pegou o nome da diretoria)
                    emailModelo.setPendente(nome.getNomePresidencia());//Local para onde vai (Identificou que vai para a diretoria e pegou o nome)

                    eMail.enviaAndamentoAvaliacao(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getPendente(), emailModelo.getEmailDestinatario());//Envia o Email

                    PresidenciaControl p = new PresidenciaControl();

                    String emailDestinatario = p.getEmail(1);
                    eMail.NotificaSetores(emailDestinatario, emailModelo.getPendente());
                }

                //FuncionarioGercom para RH
                if ((GerenciaOk == true) && (DepartamentoOk == true) && (PresidenciaOk == true)) {
                    a.AlteraAvaliacaoRH(mat, referencia);//Altera a situacaoRH para recebida
                    System.out.println("Entrou 6");

                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal("Presidencia");//Local que está (Verificou que está na gerência e no departamento esta ok e pegou o nome da diretoria)

                    eMail.enviaRH(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getEmailDestinatario());//Envia o Email
                    //Vai notificar o RH??
                }

                if ((GerenciaOk == false) && (DepartamentoOk == true) && (PresidenciaOk == false)
                        && (h.getIdDiretoria() == 999) && (h.getIdPresidencia() != 999)) {
                    a.AlteraAvaliacaoPresidencia(mat, referencia);
                    System.out.println("Entrou 7");
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeDepartamento());//Local que está (Verificou que está na gerência e no departamento esta ok e pegou o nome da diretoria)
                    emailModelo.setPendente(nome.getNomePresidencia());//Local para onde vai (Identificou que vai para a diretoria e pegou o nome)

                    eMail.enviaAndamentoAvaliacao(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getPendente(), emailModelo.getEmailDestinatario());//Envia o Email

                    PresidenciaControl p = new PresidenciaControl();

                    String emailDestinatario = p.getEmail(1);
                    eMail.NotificaSetores(emailDestinatario, emailModelo.getPendente());
                }

                //Gerente Gercom para RH
                if ((GerenciaOk == false) && (DepartamentoOk == true) && (PresidenciaOk == true)) {
                    a.AlteraAvaliacaoRH(mat, referencia);//Altera a situacaoRH para recebida
                    System.out.println("Entrou 8");

                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal("Presidência");//Local que está (Verificou que está na gerência e no departamento esta ok e pegou o nome da diretoria)

                    eMail.enviaRH(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getEmailDestinatario());//Envia o Email
                    //Vai notificar o RH??
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "AnalisarAlteracao/PassaProximoNivel.\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }

        if (local.equals("Departamento")) {
            int idDepartamento = h.getIdDepartamento();
            int idGerencia = h.getIdGerencia();
            int idDiretoria = h.getIdDiretoria();
            GerenciaControl g = new GerenciaControl();
            DepartamentoControl dE = new DepartamentoControl();
            DiretoriaControl dI = new DiretoriaControl();

            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where funcionario_matfunc_af=" + mat + " "
                    + "and referencia_AF='" + referencia + "'");
            try {
                conecta.rs.first();

                String situacaoDepartamento = conecta.rs.getString("SituacaoDepartamento_AF");
                String situacaoDiretoria = conecta.rs.getString("SituacaoDiretoria_AF");
                String situacaoRH = conecta.rs.getString("SituacaoRH_AF");
                String situacaoPresidencia = conecta.rs.getString("SituacaoPresidencia_AF");

                boolean DepartamentoOk = false;
                boolean DiretoriaOk = false;
                boolean SituacaoRH = false;
                boolean PresidenciaOk = false;

                if ((situacaoDepartamento.equals("ABONADA") || situacaoDepartamento.equals("NAO_ABONADA") || situacaoDepartamento.equals("PARCIALMENTE_ABONADA"))) {
                    DepartamentoOk = true;
                }

                if ((situacaoDiretoria.equals("ABONADA") || situacaoDiretoria.equals("NAO_ABONADA") || situacaoDiretoria.equals("PARCIALMENTE_ABONADA"))) {
                    DiretoriaOk = true;
                }

                if ((situacaoPresidencia.equals("ABONADA") || situacaoPresidencia.equals("NAO_ABONADA") || situacaoPresidencia.equals("PARCIALMENTE_ABONADA"))) {
                    PresidenciaOk = true;
                }

                //Se a departamento está ok mas a diretoria não e a diretoria existir, coloca a diretoria como recebido
                if ((DepartamentoOk == true) && (DiretoriaOk == false) && (h.getIdDiretoria() != 999)) {
                    a.AlteraAvaliacaoDiretoria(mat, referencia);//Altera a SItuacaoDepartamento para recebida
                    System.out.println("Entrou 6");
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeDepartamento());//Local que está (Verificou que departamento esta ok e pegou o nome da diretoria)
                    emailModelo.setPendente(nome.getNomeDiretoria());//Local para onde vai (Identificou que vai para a diretoria e pegou o nome)

                    eMail.enviaAndamentoAvaliacao(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getPendente(), emailModelo.getEmailDestinatario());//Envia o Email
                    DiretoriaControl d = new DiretoriaControl();

                    String emailDestinatario = d.getEmailNomeDiretoria(emailModelo.getPendente());
                    eMail.NotificaSetores(emailDestinatario, emailModelo.getPendente());
                }
                //se o departamento está ok, a diretoria também, lança para o RH 
                if ((DepartamentoOk == true) && (DiretoriaOk == true) && (SituacaoRH == false)) {
                    a.AlteraAvaliacaoRH(mat, referencia);//Altera a SItuacaoDiretoria para recebida
                    System.out.println("Entrou 7");
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeDiretoria());//Local que está (Verificou que está na gerência e pegou o nome da gerência)
                    eMail.enviaRH(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getEmailDestinatario());//Envia o Email
                    //Vai enviar para o RH ?
                }

                if ((DiretoriaOk == true) && (h.getIdDepartamento() != 999)) {
                    a.AlteraAvaliacaoRH(mat, referencia);
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeDiretoria());//Local que está (Verificou que está na gerência e pegou o nome da gerência)
                    eMail.enviaRH(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getEmailDestinatario());//Envia o Email
                    System.out.println("Entrou 7.1");
                    //Vai enviar ao RH ?
                }

                //caso do DEPCI. Se o Departamento esta ok e não tem Diretoria, segue para o RH
                if ((DepartamentoOk == true) && (h.getIdDiretoria() == 999) && (PresidenciaOk == false)) {
                    a.AlteraAvaliacaoPresidencia(mat, referencia);
                    System.out.println("Entrou 8");
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeDepartamento());//Local que está (Verificou que está na gerência e no departamento esta ok e pegou o nome da diretoria)
                    emailModelo.setPendente(nome.getNomePresidencia());//Local para onde vai (Identificou que vai para a diretoria e pegou o nome)

                    eMail.enviaAndamentoAvaliacao(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getPendente(), emailModelo.getEmailDestinatario());//Envia o Email

                    PresidenciaControl p = new PresidenciaControl();

                    String emailDestinatario = p.getEmail(1);
                    eMail.NotificaSetores(emailDestinatario, emailModelo.getPendente());

                }

                if ((PresidenciaOk == true)) {
                    a.AlteraAvaliacaoRH(mat, referencia);//Altera a RecebimentoRH para recebida
                    System.out.println("Entrou 9");
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomePresidencia());//Local que está (Verificou que está na gerência e pegou o nome da gerência)
                    eMail.enviaRH(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getEmailDestinatario());//Envia o Email
                    System.out.println("Entrou 7.1");
                    //Vai enviar para o RH??
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "AnalisarAlteracao/PassaProximoNivel.\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }

        if (local.equals("Diretoria")) {
            int idDepartamento = h.getIdDepartamento();
            int idGerencia = h.getIdGerencia();
            int idDiretoria = h.getIdDiretoria();
            GerenciaControl g = new GerenciaControl();
            DepartamentoControl dE = new DepartamentoControl();
            DiretoriaControl dI = new DiretoriaControl();

            conecta.executaPesquisaSQL("Select * from alteracaofrequencia where funcionario_matfunc_af=" + mat + " "
                    + "and referencia_AF='" + referencia + "'");
            try {
                conecta.rs.first();

                String situacaoDepartamento = conecta.rs.getString("SituacaoDepartamento_AF");
                String situacaoDiretoria = conecta.rs.getString("SituacaoDiretoria_AF");
                String situacaoRH = conecta.rs.getString("SituacaoRH_AF");
                String situacaoPresidencia = conecta.rs.getString("SituacaoPresidencia_af");

                boolean DepartamentoOk = false;
                boolean DiretoriaOk = false;
                boolean SituacaoRH = false;
                boolean PresidenciaOk = false;

                if ((situacaoDiretoria.equals("ABONADA") || situacaoDiretoria.equals("NAO_ABONADA") || situacaoDiretoria.equals("PARCIALMENTE_ABONADA"))) {
                    DiretoriaOk = true;
                }

                if ((situacaoPresidencia.equals("ABONADA") || situacaoPresidencia.equals("NAO_ABONADA") || situacaoPresidencia.equals("PARCIALMENTE_ABONADA"))) {
                    PresidenciaOk = true;
                }

                //Se a diretoria está ok, altera a situação do RH para recebida
                if ((DiretoriaOk == true)) {
                    a.AlteraAvaliacaoRH(mat, referencia);//Altera a SItuacaoDepartamento para recebida
                    System.out.println("Entrou 9");
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomeDiretoria());//Local que está (Verificou que está na gerência e pegou o nome da gerência)
                    eMail.enviaRH(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getEmailDestinatario());//Envia o Email
                    System.out.println("Entrou 7.1");
                    //Vai enviar para o RH ??
                }

                if ((PresidenciaOk == true)) {
                    a.AlteraAvaliacaoRH(mat, referencia);//Altera a SItuacaoDepartamento para recebida
                    System.out.println("Entrou 9");
                    //-------- ENVIANDO EMAIL PARA O FUNCIONÁRIO -------
                    emailModelo.setLocal(nome.getNomePresidencia());//Local que está (Verificou que está na gerência e pegou o nome da gerência)
                    eMail.enviaRH(emailModelo.getNome(), emailModelo.getPeriodo(), emailModelo.getLocal(),
                            emailModelo.getEmailDestinatario());//Envia o Email
                    //Vai enviar para o RH ?
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "AnalisarAlteracao/PassaProximoNivel.\n" + ex);
            } finally {
                conecta.desconecta();
            }
        }

    }

    public String EmQualNivelEsta(String referencia, int mat) {
        conecta.conexao();
        String gerencia = "";
        String departamento = "";
        String diretoria = "";
        String presidencia = "";
        conecta.executaPesquisaSQL("Select * from alteracaofrequencia where funcionario_matfunc_af=" + mat + " and"
                + " referencia_af='" + referencia + "'");
        try {
            if (conecta.rs.first()) {
                gerencia = conecta.rs.getString("situacaogerencia_af");
                departamento = conecta.rs.getString("situacaodepartamento_af");
                diretoria = conecta.rs.getString("situacaodiretoria_af");
                presidencia = conecta.rs.getString("situacaopresidencia_af");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnalisarAlteracao.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (gerencia.equals("RECEBIDA")) {
            return "Gerência";
        }
        if (departamento.equals("RECEBIDA")) {
            return "Departamento";
        }
        if (diretoria.equals("RECEBIDA")) {
            return "Diretoria";
        }
        if (presidencia.equals("RECEBIDA")) {
            return "Presidência";
        } else {
            return "N/A";
        }

    }

    public void verificaAlteracaoAposExclusao(String referencia, int mat, String local) {
        conecta.conexao();
        boolean sinal = true;
        System.out.println(local);
        conecta.executaPesquisaSQL("Select * from repositorioalteracao where alteracaofrequencia_referencia_ra='" + referencia
                + "' and alteracaofrequencia_matfunc_ra=" + mat);
        
        try {
            if (conecta.rs.first()) {
                
                do {
//                    System.out.println("Entrou aqui pelo menos ?");
                    if (local.equals("Gerência")) {
//                        System.out.println("Entramos em gerência");
                        String situacao = conecta.rs.getString("aprovacaogerencia_ra");
                        if (situacao.equals("ABONADA") || (situacao.equals("NAO_ABONADA"))) {
                            sinal = true;
                            System.out.println("Qtde eventos");
                        } else {
                            sinal = false;
                        }
                    }

                    if (local.equals("Departamento")) {
                        String situacao = conecta.rs.getString("aprovacaodepartamento_ra");
                        if (situacao.equals("ABONADA") || (situacao.equals("NAO_ABONADA"))) {
                            sinal = true;
                        } else {
                            sinal = false;
                        }
                    }

                    if (local.equals("Diretoria")) {
                        String situacao = conecta.rs.getString("aprovacaodiretoria_ra");
                        if (situacao.equals("ABONADA") || (situacao.equals("NAO_ABONADA"))) {
                            sinal = true;
                        } else {
                            sinal = false;
                        }
                    }

                    if (local.equals("Presidência")) {
                        String situacao = conecta.rs.getString("aprovacaopresidencia_ra");
                        if (situacao.equals("RECEBIDA")) {
                            sinal = true;
                        } else {
                            sinal = false;
                        }
                    }

                } while ((conecta.rs.next() || (sinal == false)));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AnalisarAlteracao/VerificaAlteracao.\n" + ex);
        } finally {
            conecta.desconecta();
        }

        if (sinal == true) {
            System.out.println("Pode avancçar");
        } else {
            System.out.println("Não pode avançar");
        }
    }

}
