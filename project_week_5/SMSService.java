/**
 * Concrete implementation of NotificationMedium for SMS delivery.
 * 
 * This class simulates sending notifications via SMS. In a production
 * environment, this would integrate with an SMS gateway API like Twilio.
 * 
 * @author Phil
 * @version 1.0
 * @since 2026-06-09
 */
public class SMSService implements NotificationMedium {
    
    private final String gatewayUrl;
    private final String apiKey;
    private final String senderPhone;
    
    /**
     * Constructs a new SMSService with default configuration.
     */
    public SMSService() {
        this.gatewayUrl = "https://api.sms-gateway.com";
        this.apiKey = "default-api-key";
        this.senderPhone = "+1234567890";
    }
    
    /**
     * Constructs a new SMSService with custom gateway configuration.
     * 
     * @param gatewayUrl The SMS gateway API endpoint
     * @param apiKey The API key for authentication
     * @param senderPhone The sender's phone number
     */
    public SMSService(String gatewayUrl, String apiKey, String senderPhone) {
        this.gatewayUrl = gatewayUrl;
        this.apiKey = apiKey;
        this.senderPhone = senderPhone;
    }
    
    /**
     * Sends a message via SMS.
     * 
     * @param message The message content to be delivered (max 160 characters)
     * @throws IllegalArgumentException if the message is null or empty
     */
    @Override
    public void send(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        
        int charCount = message.length();
        String status = charCount <= 160 ? "Sent successfully" : "Sent (split into " + ((charCount + 159) / 160) + " parts)";
        
        System.out.println("=================================================");
        System.out.println("SMS NOTIFICATION");
        System.out.println("=================================================");
        System.out.println("From:     " + senderPhone);
        System.out.println("Gateway:  " + gatewayUrl);
        System.out.println("Length:   " + charCount + " characters");
        System.out.println("Message:  " + message);
        System.out.println("Status:   " + status);
        System.out.println("=================================================");
    }
    
    /**
     * Returns the SMS gateway URL.
     * 
     * @return The gateway API endpoint
     */
    public String getGatewayUrl() {
        return gatewayUrl;
    }
    
    /**
     * Returns the API key (masked for security).
     * 
     * @return The masked API key
     */
    public String getApiKey() {
        return apiKey.substring(0, Math.min(4, apiKey.length())) + "****";
    }
    
    /**
     * Returns the sender phone number.
     * 
     * @return The sender's phone number
     */
    public String getSenderPhone() {
        return senderPhone;
    }
}
