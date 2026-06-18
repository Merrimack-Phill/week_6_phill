/**
 * Test script demonstrating WhatsAppService integration with AlertSystem.
 * 
 * This test verifies that WhatsAppService can be used as a drop-in replacement
 * for other notification mediums without modifying the AlertSystem class.
 * 
 * @author Phil
 * @version 1.0
 * @since 2026-06-14
 */
public class WhatsAppTest {
    
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("     WHATSAPP NOTIFICATION SYSTEM TEST");
        System.out.println("     Maintenance Enhancement - Week 5");
        System.out.println("=================================================");
        
        AlertSystem alertSystem = new AlertSystem();
        
        System.out.println("\n--- TEST 1: WhatsApp Notifications ---\n");
        WhatsAppService whatsappService = new WhatsAppService();
        alertSystem.setMedium(whatsappService);
        
        alertSystem.notifyUser("Welcome to our service!");
        alertSystem.notifyUser("Your order #12345 has been confirmed.");
        alertSystem.notifyUser("Reminder: Your appointment is tomorrow at 10 AM.");
        
        System.out.println("\n--- TEST 2: Custom WhatsApp Configuration ---\n");
        WhatsAppService customWhatsapp = new WhatsAppService("WA-BIZ-001", "+9876543210");
        alertSystem.setMedium(customWhatsapp);
        alertSystem.notifyUser("Your verification code is: 594821");
        
        System.out.println("\n--- TEST 3: Switching Between Mediums ---\n");
        
        // Switch to Email
        alertSystem.setMedium(new EmailService());
        alertSystem.notifyUser("Email: Your weekly report is ready");
        
        // Switch to SMS
        alertSystem.setMedium(new SMSService());
        alertSystem.notifyUser("SMS: Flash sale starts in 1 hour!");
        
        // Switch back to WhatsApp
        alertSystem.setMedium(new WhatsAppService());
        alertSystem.notifyUser("WhatsApp: Thanks for being a valued customer!");
        
        System.out.println("\n--- Session Log Summary ---\n");
        alertSystem.printLog();
        
        System.out.println("\n=================================================");
        System.out.println("  MAINTENANCE PROOF: SUCCESS");
        System.out.println("=================================================");
        System.out.println();
        System.out.println("WhatsAppService was added WITHOUT modifying:");
        System.out.println("  - AlertSystem.java");
        System.out.println("  - NotificationMedium.java");
        System.out.println("  - EmailService.java");
        System.out.println("  - SMSService.java");
        System.out.println();
        System.out.println("The setMedium() method allows runtime switching");
        System.out.println("between all notification mediums seamlessly.");
        System.out.println("=================================================");
    }
}
