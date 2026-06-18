/**
 * Interface defining the contract for notification delivery mechanisms.
 * 
 * This interface enables the AlertSystem to work with any notification medium
 * through composition, allowing easy extension without modifying existing code.
 * 
 * @author Phil
 * @version 1.0
 * @since 2026-06-09
 */
public interface NotificationMedium {
    
    /**
     * Sends a notification message through the implementing medium.
     * 
     * @param message The message content to be delivered to the recipient
     * @throws IllegalArgumentException if the message is null or empty
     */
    void send(String message);
}
