import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Container class that manages notification delivery through composition.
 * 
 * AlertSystem uses a NotificationMedium object as a field, allowing the
 * notification mechanism to be swapped at runtime without modifying the
 * core alert logic. This design follows the Composition over Inheritance
 * principle, making the system highly maintainable and extensible.
 * 
 * Features:
 * - Dynamic medium switching via setMedium()
 * - Session logging via ArrayList
 * - Timestamp tracking for each notification
 * 
 * @author Phil
 * @version 1.0
 * @since 2026-06-09
 * @see NotificationMedium
 * @see EmailService
 * @see SMSService
 */
public class AlertSystem {
    
    private NotificationMedium medium;
    private final ArrayList<String> messageLog;
    private final ArrayList<String> timestampLog;
    
    public AlertSystem() {
        this.medium = null;
        this.messageLog = new ArrayList<>();
        this.timestampLog = new ArrayList<>();
    }
    
    public AlertSystem(NotificationMedium medium) {
        this.medium = medium;
        this.messageLog = new ArrayList<>();
        this.timestampLog = new ArrayList<>();
    }
    
    public void setMedium(NotificationMedium medium) {
        if (medium == null) {
            throw new IllegalArgumentException("NotificationMedium cannot be null");
        }
        this.medium = medium;
        System.out.println("Notification medium changed to: " + medium.getClass().getSimpleName());
    }
    
    public void notifyUser(String message) {
        if (medium == null) {
            throw new IllegalStateException("No notification medium set. Call setMedium() first.");
        }
        
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        messageLog.add(message);
        timestampLog.add(timestamp);
        
        System.out.println("\n[" + timestamp + "] Sending notification...");
        medium.send(message);
    }
    
    public ArrayList<String> getMessageLog() {
        return new ArrayList<>(messageLog);
    }
    
    public ArrayList<String> getTimestampLog() {
        return new ArrayList<>(timestampLog);
    }
    
    public int getMessageCount() {
        return messageLog.size();
    }
    
    public void clearLog() {
        messageLog.clear();
        timestampLog.clear();
        System.out.println("Message log cleared");
    }
    
    public void printLog() {
        System.out.println("\n=================================================");
        System.out.println("SESSION MESSAGE LOG");
        System.out.println("=================================================");
        System.out.println("Total messages sent: " + messageLog.size());
        System.out.println("-------------------------------------------------");
        
        if (messageLog.isEmpty()) {
            System.out.println("(No messages sent in this session)");
        } else {
            for (int i = 0; i < messageLog.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + timestampLog.get(i));
                System.out.println("    " + messageLog.get(i));
            }
        }
        System.out.println("=================================================");
    }
    
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("     FLEXIBLE NOTIFICATION SYSTEM DEMO");
        System.out.println("     Composition over Inheritance");
        System.out.println("=================================================");
        
        AlertSystem alertSystem = new AlertSystem();
        
        System.out.println("\n--- DEMO 1: Email Notifications ---\n");
        EmailService emailService = new EmailService();
        alertSystem.setMedium(emailService);
        
        alertSystem.notifyUser("Welcome to the notification system!");
        alertSystem.notifyUser("Your account has been created successfully.");
        
        System.out.println("\n--- DEMO 2: Switching to SMS ---\n");
        SMSService smsService = new SMSService();
        alertSystem.setMedium(smsService);
        
        alertSystem.notifyUser("Your verification code is: 847293");
        alertSystem.notifyUser("Alert: Unusual login attempt detected!");
        
        System.out.println("\n--- DEMO 3: Switching back to Email ---\n");
        alertSystem.setMedium(new EmailService("smtp.company.com", "alerts@company.com"));
        alertSystem.notifyUser("Daily summary: 5 new notifications pending");
        
        alertSystem.printLog();
        
        System.out.println("\n=================================================");
        System.out.println("  COMPOSITION BENEFIT: Easy Extension");
        System.out.println("=================================================");
        System.out.println();
        System.out.println("To add WhatsApp support next week:");
        System.out.println("  1. Create WhatsAppService implements NotificationMedium");
        System.out.println("  2. Call alertSystem.setMedium(new WhatsAppService())");
        System.out.println("  3. Done! No changes to AlertSystem required.");
        System.out.println();
    }
}
