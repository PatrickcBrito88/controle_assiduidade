/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controllers.DadosEmailControl;
import domain.Modelo.DadosEmailModel;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class SendMailTLSPendenciaBuilderAlteracaoFrequencia {

    public void MandaEmail(String email,
            String assunto, StringBuilder mensagem) {
        
        DadosEmailControl dadosControl = new DadosEmailControl();
        DadosEmailModel dados = new DadosEmailModel();
        dados=dadosControl.getDados();

        final String username = dados.getLogin();
        final String password = dados.getSenha();
        
        final String serverMail = "";
        final String port="";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", serverMail);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(dados.getLogin()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse("retornoalteracao@gmail.com"));

            message.setSubject(assunto);
            String mensagemNormal = String.valueOf(mensagem);
            message.setText(mensagemNormal);

            Transport.send(message);

            //JOptionPane.showMessageDialog(null,"Mensagem enviada com sucesso!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
