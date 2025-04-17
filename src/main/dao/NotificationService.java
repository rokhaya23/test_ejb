package dao;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class NotificationService {

    private static final Logger logger = LogManager.getLogger(NotificationService.class.getName());

    @Resource(name = "mail/Session")
    private Session mailSession;

    public void sendUserCreationNotification(String email, String username, String password) {
        try {
            // Création du message email
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("noreply@yourdomain.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Votre compte a été créé");

            String htmlContent = "<html><body>"
                    + "<h2>Bienvenue " + username + "!</h2>"
                    + "<p>Votre compte a été créé avec succès.</p>"
                    + "<p>Vos identifiants de connexion :</p>"
                    + "<ul>"
                    + "<li><strong>Nom d'utilisateur:</strong> " + username + "</li>"
                    + "<li><strong>Mot de passe:</strong> " + password + "</li>"
                    + "</ul>"
                    + "<p>Merci de vous connecter pour utiliser votre compte.</p>"
                    + "</body></html>";

            message.setContent(htmlContent, "text/html; charset=utf-8");

            // Envoi du message
            Transport.send(message);
            logger.info("Email notification sent to " + email);

        } catch (MessagingException e) {
            logger.error("Error sending email notification", e);
        }
    }
}