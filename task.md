Assignment: The WhatsApp Maintenance Enhancement
Scenario:

The marketing department has requested a new feature for our "Flexible Notification System." In addition to Email and SMS, users must now be able to receive alerts via WhatsApp.

The Constraint: You are strictly forbidden from rewriting the AlertSystem class or changing the existing NotificationMedium interface. You must treat this as a Maintenance Project where you extend the system's functionality by adding new components, not by modifying the core engine.
Part 1: Implementation (45 Minutes)

    Create the New Service: Implement a new class called WhatsAppService that implements the existing NotificationMedium interface.

    Logic: The send(String message) method should output: [WhatsApp] Sending message: <message text>.

    Collection Usage: Ensure that when this new medium is used, the message is still correctly stored in the ArrayList log within the AlertSystem (inherited behavior through composition).

Part 2: Generative AI & Refinement (30 Minutes)

    The Prompt: Use a GenAI tool to generate the WhatsAppService class and a test script.

    Documentation: Please document:

        The prompt you used.

        How you ensured the AI output matched the existing interface signature perfectly to avoid a rewrite.

        Any specific adjustments made to the JavaDocs to maintain professional standards.

Part 3: Verification (15 Minutes)

    CLI Test: Compile all files (javac *.java) and run the AlertSystem.

    Maintenance Proof: Please make sure you can switch between EmailService, SMSService, and WhatsAppService at runtime using the setMedium() method without ever having to recompile the AlertSystem.java file itself.

Deliverable

Save your files within a single folder;

Save your folder in a zip file
