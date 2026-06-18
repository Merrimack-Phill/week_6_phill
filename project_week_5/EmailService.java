/**
 * Concrete implementation of NotificationMedium for email delivery.
 * 
 * This class simulates sending notifications via email. In a production
 * environment, this would integrate with an SMTP server or email API.
 * 
 * @author Phil
 * @version 1.0
 * @since 2026-06-09
 */
public class EmailService implements NotificationMedium {
    
    private final String smtpServer;
    private final String senderEmail;
    
    /**
     * Constructs a new EmailService with default configuration.
     */
    public EmailService() {
        this.smtpServer = "smtp.example.com";
        this.senderEmail = "noreply@example.com";
    }
    
    /**
     * Constructs a new EmailService with custom SMTP configuration.
     * 
     * @param smtpServer The SMTP server hostname
     * @param senderEmail The sender's email address
     */
    public EmailService(String smtpServer, String senderEmail) {
        this.smtpServer = smtpServer;
        this.senderEmail = senderEmail;
    }
    
    /**
     * Sends a message via email.
     * 
     * @param message The message content to be delivered
     * @throws IllegalArgumentException if the message is null or empty
     */
    @Override
    public void send(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        
        System.out.println("=================================================");
        System.out.println("EMAIL NOTIFICATION");
        System.out.println("=================================================");
        System.out.println("From:    " + senderEmail);
        System.out.println("Server:  " + smtpServer);
        System.out.println("Message: " + message);
        System.out.println("Status:  Sent successfully");
        System.out.println("=================================================");
    }
    
    /**
     * Returns the SMTP server configuration.
     * 
     * @return The SMTP server hostname
     */
    public String getSmtpServer() {
        return smtpServer;
    }
    
    /**
     * Returns the sender email address.
     * 
     * @return The sender's email address
     */
    public String getSenderEmail() {
        return senderEmail;
    }
}
