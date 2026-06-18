# Flexible Notification System - Week 5

Extension of the Week 4 notification system adding **WhatsApp support** while demonstrating the **Open/Closed Principle**: software entities should be open for extension but closed for modification.

## What's New in Week 5

Added `WhatsAppService` - a new notification channel - **without modifying any existing code**.

### New Files

| File | Purpose |
|------|---------|
| `WhatsAppService.java` | WhatsApp notification implementation |
| `WhatsAppTest.java` | Test harness for WhatsApp integration |
| `WHATSAPP_IMPLEMENTATION.md` | Detailed implementation documentation |

### Unchanged Files (Proof of Open/Closed Principle)

- `AlertSystem.java` - Core engine unchanged
- `NotificationMedium.java` - Interface unchanged
- `EmailService.java` - Existing implementation preserved
- `SMSService.java` - Existing implementation preserved

## Quick Start

```bash
cd project_week_5
javac *.java
java AlertSystem        # Run main demo
java WhatsAppTest       # Run WhatsApp-specific tests
```

## Architecture Review

Week 4 established composition-based design. Week 5 proves its value:

```
                                    Week 4
┌─────────────────┐         ┌──────────────────────┐
│  AlertSystem    │────────▶│  NotificationMedium  │
│  (Container)    │         └──────────┬───────────┘
└─────────────────┘                    │
                          ┌────────────┼────────────┐
                          ▼            ▼            ▼
                   ┌──────────┐  ┌──────────┐
                   │   Email  │  │   SMS    │
                   │ Service  │  │ Service  │
                   └──────────┘  └──────────┘

                                    Week 5 (Extension)
                                                       ▼
                                                ┌──────────┐
                                                │ WhatsApp │
                                                │ Service  │
                                                └──────────┘
```

**Key Achievement**: WhatsApp support added by creating ONE new class. Zero modifications to existing code.

## WhatsAppService

New concrete implementation of `NotificationMedium`.

**Constructors:**
- `WhatsAppService()` - Default configuration
- `WhatsAppService(String businessAccountId, String phoneNumberId)` - Custom configuration

**Getters:**
- `getBusinessAccountId()` - Returns WhatsApp Business Account ID
- `getPhoneNumberId()` - Returns phone number ID for sending

**Usage:**
```java
AlertSystem alertSystem = new AlertSystem();

// Default configuration
alertSystem.setMedium(new WhatsAppService());
alertSystem.notifyUser("Hello via WhatsApp!");

// Custom configuration
alertSystem.setMedium(new WhatsAppService("WA-BIZ-001", "+123****7890"));
alertSystem.notifyUser("Custom WhatsApp message");
```

## Full Project Structure

```
project_week_5/
├── AlertSystem.java            # Core container (unchanged from Week 4)
├── NotificationMedium.java     # Interface (unchanged from Week 4)
├── EmailService.java           # Email implementation (unchanged)
├── SMSService.java             # SMS implementation (unchanged)
├── WhatsAppService.java        # NEW: WhatsApp implementation
├── WhatsAppTest.java           # NEW: WhatsApp test harness
├── DEVELOPMENT_NOTES.md        # Development process notes
├── WHATSAPP_IMPLEMENTATION.md  # NEW: Detailed WhatsApp docs
├── javadoc/                    # Generated JavaDoc HTML
└── README.md                   # This file
```

## Adding Future Notification Types

The pattern is now proven. To add another channel (e.g., Slack, Push, Telegram):

1. Create a new class implementing `NotificationMedium`:

```java
public class SlackService implements NotificationMedium {
    private final String webhookUrl;
    private final String channel;
    
    public SlackService(String webhookUrl, String channel) {
        this.webhookUrl = webhookUrl;
        this.channel = channel;
    }
    
    @Override
    public void send(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        // Integrate with Slack Incoming Webhooks API
        System.out.println("[Slack] Posting to " + channel + ": " + message);
    }
}
```

2. Use immediately with existing AlertSystem:

```java
alertSystem.setMedium(new SlackService("https://hooks.slack.com/...", "#alerts"));
alertSystem.notifyUser("Deployment complete!");
```

## Running Tests

### Main Demo (All Channels)

```bash
java AlertSystem
```

Shows Email → SMS → Email switching with session logging.

### WhatsApp-Specific Tests

```bash
java WhatsAppTest
```

Verifies:
- WhatsApp message delivery
- Message format: `[WhatsApp] Sending message: <text>`
- Integration with AlertSystem logging
- Runtime medium switching

## Building Documentation

Generate JavaDoc:

```bash
javadoc -d javadoc -author -version -windowtitle "Notification System - Week 5" *.java
```

Open `javadoc/index.html` in a browser.

## Design Principles Demonstrated

| Principle | How It's Demonstrated |
|-----------|----------------------|
| **Composition over Inheritance** | AlertSystem uses NotificationMedium as a field, not inheritance |
| **Open/Closed Principle** | Extended with WhatsApp without modifying existing code |
| **Dependency Inversion** | AlertSystem depends on abstraction (interface), not concrete classes |
| **Single Responsibility** | Each service class handles one notification type only |
| **Liskov Substitution** | Any NotificationMedium implementation works interchangeably |

## Production Integration Notes

Current implementations simulate delivery via console output. For production:

| Service | Integration Target |
|---------|-------------------|
| EmailService | JavaMail API, SendGrid, Amazon SES |
| SMSService | Twilio, Vonage, Plivo |
| WhatsAppService | WhatsApp Business API (Meta) |

The interface-based design ensures integrations can be added without changing `AlertSystem`.

## Comparison: Week 4 vs Week 5

| Aspect | Week 4 | Week 5 |
|--------|--------|--------|
| Notification Types | Email, SMS | Email, SMS, WhatsApp |
| AlertSystem Changes | N/A (baseline) | None |
| New Classes Created | 4 (core) | 2 (WhatsApp + test) |
| Design Principle | Composition introduced | Open/Closed proven |

## Error Handling

| Exception | When |
|-----------|------|
| `IllegalArgumentException` | Message is null or empty |
| `IllegalArgumentException` | NotificationMedium is null in `setMedium()` |
| `IllegalStateException` | `notifyUser()` called without setting a medium |

## Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0 | 2026-06-09 | Initial release (Email, SMS) |
| 1.1 | 2026-06-14 | Added WhatsApp support |

## See Also

- **Week 4 README**: `../week_4/project_week_4/README.md` - Original implementation docs
- **WhatsApp Implementation**: `WHATSAPP_IMPLEMENTATION.md` - Detailed extension docs
- **Development Notes**: `DEVELOPMENT_NOTES.md` - AI-assisted development process

## Author

Phil - Class Design & Documentation Course

## License

MIT
