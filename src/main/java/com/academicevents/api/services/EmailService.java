package com.academicevents.api.services;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text, BufferedImage certificate) throws IOException, MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // Criando o corpo do e-mail como multipart
        MimeMultipart multipart = new MimeMultipart("related");

        // Parte 1: Texto
        MimeBodyPart textPart = new MimeBodyPart();
        String htmlText = "<html><body>" +
                "<p>" + text + "</p>" +
                "<img src='cid:certificateImage'>" +
                "</body></html>";
        textPart.setContent(htmlText, "text/html");
        multipart.addBodyPart(textPart);

        // Parte 2: Imagem
        MimeBodyPart imagePart = new MimeBodyPart();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(certificate, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        DataSource dataSource = new ByteArrayDataSource(imageBytes, "image/png");
        imagePart.setDataHandler(new DataHandler(dataSource));
        imagePart.setHeader("Content-ID", "<certificateImage>");
        imagePart.setFileName("certificado.png");
        multipart.addBodyPart(imagePart);

        // Configurar destinatários e assunto
        mimeMessage.setFrom("projetobdcat@gmail.com");
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO, to);
        mimeMessage.setSubject(subject);
        mimeMessage.setContent(multipart);

        // Enviar o e-mail
        mailSender.send(mimeMessage);
    }
}