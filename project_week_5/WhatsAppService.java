/**
 * Concrete implementation of NotificationMedium for WhatsApp delivery.
 * 
 * This class simulates sending notifications via WhatsApp. In a production
 * environment, this would integrate with the WhatsApp Business API.
 * 
 * @author Phil
 * @version 1.0
 * @since 2026-06-14
 */
public class WhatsAppService implements NotificationMedium {
    
    private final String businessAccountId;
    private final String phoneNumberId;
    
    /**
     * Constructs a new WhatsAppService with default configuration.
     */
    public WhatsAppService() {
        this.businessAccountId = "default-business-account";
        this.phoneNumberId = "+1234567890";
    }
    
    /**
     * Constructs a new WhatsAppService with custom configuration.
     * 
     * @param businessAccountId The WhatsApp Business Account ID
     * @param phoneNumberId The phone number ID for sending messages
     */
    public WhatsAppService(String businessAccountId, String phoneNumberId) {
        this.businessAccountId = businessAccountId;
        this.phoneNumberId = phoneNumberId;
    }
    
    /**
     * Sends a message via WhatsApp.
     * 
     * @param message The message content to be delivered
     * @throws IllegalArgumentException if the message is null or empty
     */
    @Override
    public void send(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        
        System.out.println("[WhatsApp] Sending message: " + message);
    }
    
    /**
     * Returns the WhatsApp Business Account ID.
     * 
     * @return The Business Account ID
     */
    public String getBusinessAccountId() {
        return businessAccountId;
    }
    
    /**
     * Returns the phone number ID.
     * 
     * @return The phone number ID for sending messages
     */
    public String getPhoneNumberId() {
        return phoneNumberId;
    }
}
