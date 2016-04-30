package Email;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bakamedi
 */
public class Email {
    private String usuarioCorreo;
    private String destinatario;
    private String nombreArchivo;
    private String rutaArchivo;
    private String asunto;
    private String mensaje;
    private static String clave = "kayjqlvwdkhxqltb";

    public Email(String usuarioCorreo, String destinatario, String asunto, String mensaje) {
        this.usuarioCorreo = usuarioCorreo;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }
    
    public Email(String usuarioCorreo, String destinatario,
            String nombreArchivo,String rutaArchivo, String asunto, String mensaje) {
        this.usuarioCorreo = usuarioCorreo;
        this.destinatario = destinatario;
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }
    
    public void sendMailVacio(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuarioCorreo,clave);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuarioCorreo));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destinatario));
            message.setSubject(this.asunto);
            message.setText("No responda a este mensaje");
            System.out.println("Enviando.... ");
            Transport.send(message);
            System.out.println("EMAIL ENVIADO");

        } catch (MessagingException e) {
            System.out.println("EMAIL NO ENVIADO");
            throw new RuntimeException(e);
        }
    }
    
    public void sendMail(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuarioCorreo,clave);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuarioCorreo));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destinatario));
            message.setSubject(this.asunto);
            message.setText("No responda a este mensaje");
            
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(rutaArchivo);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(nombreArchivo);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            System.out.println("Enviando.... ");
            Transport.send(message);
            System.out.println("EMAIL ENVIADO");

        } catch (MessagingException e) {
            System.out.println("EMAIL NO ENVIADO");
            throw new RuntimeException(e);
        }
    }
}