package email_;

import controllers.FuncionarioControl;
import utils.ConectaBanco;
import utils.SendMailTLSPendenciaBuilderAlteracaoFrequencia;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class EnvioEmail {

    ConectaBanco conecta = new ConectaBanco();

    public void enviaAndamentoAvaliacao(String nome, String periodo, String local, String pendente,
            String emailDestinatario) {
        conecta.conexao();

        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Prezado(a) " + nome + ",\n\n"
                + "Informamos que os eventos de sua alteração de frequência, referentes ao período de " + periodo
                + ", foram avaliados pelo(a) " + local + ", estando desta forma pendente da avaliacao do(a) " + pendente + ".\n\n"
                + "Atenciosamente, \n"
                );

        SendMailTLSPendenciaBuilderAlteracaoFrequencia mail = new SendMailTLSPendenciaBuilderAlteracaoFrequencia();

        if ((emailDestinatario == null) || (emailDestinatario.equals(""))) {
            JOptionPane.showMessageDialog(null, "O(A) funcionário(a) " + nome + " não receberá notificação por e-mail, "
                    + "pois não há e-mail cadastrado.");
        } else {
            mail.MandaEmail(emailDestinatario, "Andamento da avaliação da Alteração de Frequência: " + periodo, mensagem);
            JOptionPane.showMessageDialog(null, "Notificação encaminhada por e-mail para \n" + nome + "!");
        }
    }

    public void enviaRH(String nome, String periodo, String local,
            String emailDestinatario) {
        conecta.conexao();

        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Prezado(a) " + nome + ",\n\n"
                + "Informamos que os eventos de sua alteração de frequência, referentes ao período de " + periodo
                + ", foram avaliados pelo(a) " + local + ", tendo sido recebidos pela Gerência de Recursos Humanos.\n"
                + "O resultado da alteração de frequência poderá ser conferido no Painel de Controle do Sistema.\n\n"
                );

        SendMailTLSPendenciaBuilderAlteracaoFrequencia mail = new SendMailTLSPendenciaBuilderAlteracaoFrequencia();
        if ((emailDestinatario == null) || (emailDestinatario.equals(""))) {
            JOptionPane.showMessageDialog(null, "O(A) funcionário(a) " + nome + " não receberá notificação por e-mail, "
                    + "pois não há e-mail cadastrado.");
        } else {

            mail.MandaEmail(emailDestinatario, "Conclusão da avaliação da Alteração de Frequência: " + periodo, mensagem);
            JOptionPane.showMessageDialog(null, "Notificação encaminhada por e-mail para \n" + nome + "!");
        }
    }

    public void CriacaoAlteracaoFrequencia(String referencia, int mat) {
        StringBuilder mensagem = new StringBuilder();
        FuncionarioControl f = new FuncionarioControl();
        String nome = f.getNome(mat);
        String emailDestinatario = f.getEmail(mat);
        mensagem.append("Prezado(a) " + nome + ",\n\n"
                + "Os eventos de sua alteração de frequência foram enviados à sua Chefia Imediata.\n "
                + "Solicitamos que aguarde a conclusão das avaliações.\n"
                + "O acompanhamento das avaliações poderá ser feito via painel do sistema ou pelas notificações por  e-mail.\n\n"
                );

        SendMailTLSPendenciaBuilderAlteracaoFrequencia mail = new SendMailTLSPendenciaBuilderAlteracaoFrequencia();
        if ((emailDestinatario == null) || (emailDestinatario.equals(""))) {
            JOptionPane.showMessageDialog(null, "O(A) funcionário(a) " + nome + " não receberá notificação por e-mail, "
                    + "pois não há e-mail cadastrado.");
        } else {
            mail.MandaEmail(emailDestinatario, "Criação de Alteração de Frequência. Referência: " + referencia, mensagem);
        }
    }

    public void ResetaSenha(String nome, String novaSenha) {
        StringBuilder mensagem = new StringBuilder();
        FuncionarioControl f = new FuncionarioControl();
        int mat = f.getMat(nome);
        String emailDestinatario = f.getEmail(mat);
        if ((emailDestinatario == null) || (emailDestinatario.equals(""))) {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar senha provisória, pois não há e-mail cadastrado"
                    + " para esta matrícula.");
        } else {
            mensagem.append("Prezado(a) " + nome + ",\n\n"
                    + "Sua senha foi resetada.\n"
                    + "Sua senha provisória é " + novaSenha + ".\n"
                    + "Esta senha servirá apenas para o seu próximo acesso, ocasião em que será necessário cadastrar uma nova senha definitiva.\n\n"
                   );

            SendMailTLSPendenciaBuilderAlteracaoFrequencia mail = new SendMailTLSPendenciaBuilderAlteracaoFrequencia();

            mail.MandaEmail(emailDestinatario, "Criação de senha provisória", mensagem);
            JOptionPane.showMessageDialog(null, "Senha provisória encaminhada por e-mail para " + nome + "!");
        }
    }

    public void ResetaSenhaSetores(String nome, String novaSenha, String email) {
        StringBuilder mensagem = new StringBuilder();
        FuncionarioControl f = new FuncionarioControl();
        if ((email == null) || (email.equals(""))) {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar senha provisória, pois não há e-mail cadastrado"
                    + " para esta localização.");
        } else {

            mensagem.append("Prezado(a) responsável pelo(a) " + nome + ",\n\n"
                    + "Sua senha foi resetada.\n"
                    + "Sua senha provisória é " + novaSenha + ".\n"
                    + "Esta senha servirá apenas para o seu próximo acesso, ocasião em que será necessário cadastrar uma nova senha definitiva.\n\n"
                    );

            SendMailTLSPendenciaBuilderAlteracaoFrequencia mail = new SendMailTLSPendenciaBuilderAlteracaoFrequencia();

            mail.MandaEmail(email, "Criação de senha provisória", mensagem);
            JOptionPane.showMessageDialog(null, "Senha provisória encaminhada por e-mail para " + nome + "!");
        }
    }
    
    public void NotificaSetores(String email, String nome) {
        StringBuilder mensagem = new StringBuilder();
        FuncionarioControl f = new FuncionarioControl();
        if ((email == null) || (email.equals(""))) {
            JOptionPane.showMessageDialog(null, "O "+nome+" não possui e-mail cadastrado. Não foi possível notificá-lo.");
        } else {
            mensagem.append("Prezado(a) responsável pelo(a) " + nome + ",\n\n"
                    + "Existem Alterações de Frequência pendentes de sua avaliação. \n\n"
                    );

            SendMailTLSPendenciaBuilderAlteracaoFrequencia mail = new SendMailTLSPendenciaBuilderAlteracaoFrequencia();

            mail.MandaEmail(email, "Notificação - Alteração de Frequência Pendente", mensagem);
            JOptionPane.showMessageDialog(null,"O(A) "+nome+" foi notificado(a) por e-mail sobre esta Alteração de Frequência!");        
        }
    }
    
    public void NotificaEventoLiberado(int mat, String referencia){
        FuncionarioControl f = new FuncionarioControl();
        String email=f.getEmail(mat);
        String nome=f.getNome(mat);
        StringBuilder mensagem = new StringBuilder();
        
        if ((email == null) || (email.equals(""))) {
            JOptionPane.showMessageDialog(null, "O "+nome+" não possui e-mail cadastrado. Não foi possível notificá-lo.");
        } else {
            mensagem.append("Prezado(a) "+ nome + ",\n\n"
                    + "Um dos seus eventos de alteração de frequencia, referente ao mês de "+referencia+
                    " foi liberado para edição.\n"
                    );

            SendMailTLSPendenciaBuilderAlteracaoFrequencia mail = new SendMailTLSPendenciaBuilderAlteracaoFrequencia();

            mail.MandaEmail(email, "Notificação - Evento liberado para edição. Referência: "+referencia, mensagem);
            JOptionPane.showMessageDialog(null,"O(A) "+nome+" foi notificado(a) por e-mail sobre a possibilidade de edição deste evento!");        
        }
    }
    
    

}
