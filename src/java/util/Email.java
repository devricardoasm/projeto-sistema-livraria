/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelos.ItemDeCompra;
import modelos.Login;
import modelos.Pedido;
import modelos.Usuario;

/**
 *
 * @author 11131103404
 */
public class Email {

    public void EnviarEmail(Usuario usuario) {
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Hotmail
         */
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("virtual.book@hotmail.com", "virtual123456789");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("virtual.book@hotmail.com")); //Remetente

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(usuario.getLogin().getLogin())); //Destinatário(s)
            message.setSubject("Cadastro realizado com sucesso");//Assunto
            message.setText("Olá " + usuario.getNome() + " " + usuario.getSobrenome() + " , Agradeçemos por relizar o cadastro agora você já pode acessar o Virtual Book, Lembrando que sua senha cadastrada foi: '" + usuario.getLogin().getSenha() + "', não esqueça dela!");

            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    
    public void EnviarNotificacaoPedidoCancelado(Pedido pedido) {
        
    Properties props = new Properties();
       
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("virtual.book@hotmail.com", "virtual123456789");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("virtual.book@hotmail.com")); //Remetente

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(pedido.getUsuario().getLogin().getLogin())); //Destinatário(s)
            message.setSubject("Seu pedido foi Cancelado");//Assunto
            message.setText("Olá " + pedido.getUsuario().getNome() + " " + pedido.getUsuario().getSobrenome()+ " , Infelizmente seu pedido de Nº'" + pedido.getId() + "', foi cancelado por falta de pagamento. Peçamos que faça novamente a compra");
            
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    
    public void EnviarNotificacaoPedidoRealizado(Pedido pedido) {
          
        
    Properties props = new Properties();
       
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("virtual.book@hotmail.com", "virtual123456789");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("virtual.book@hotmail.com")); //Remetente

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(pedido.getUsuario().getLogin().getLogin())); //Destinatário(s)
            message.setSubject("Seu pedido foi criado com sucesso!! :)");//Assunto
            message.setText("Olá " + pedido.getUsuario().getNome() + " " + pedido.getUsuario().getSobrenome()+ " , seu pedido de Nº'" + pedido.getId() + "', foi realizado com sucesso. estamos aguardando o pagamento.");
            
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    
    
    public void EnviarNotificacaoPedidoPago(Pedido pedido) {
        
    Properties props = new Properties();
       
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("virtual.book@hotmail.com", "virtual123456789");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("virtual.book@hotmail.com")); //Remetente

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(pedido.getUsuario().getLogin().getLogin())); //Destinatário(s)
            message.setSubject("Compra liberada!");//Assunto
            message.setText("Olá " + pedido.getUsuario().getNome() + " " + pedido.getUsuario().getSobrenome()+ " , seu pedido de Nº'" + pedido.getId() + "', já esta liberado. BOA LEITURA!");
            
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
