# Creating Language Keys

The final step to getting this all working is to creating language keys to match
all of the potential messages that you're providing. Language keys are stored in
the `Language.properties` file included in your module. `Language.properties` 
is the default, but you can create a number of translations by appending the 
ISO-639 language code like `Language_en.properties` for English or 
`Language_de.properties` for German. For now, stick to the default language 
keys.

1. Open `/src/main/resources/content/Language.properties` in your 
    `guestbook-web` module.

2. Paste in the following keys for all of your messages:

        entry-added=Entry added successfully.
        entry-deleted=Entry deleted successfully.
        guestbook-added=Guestbook added successfully.
        guestbook-updated=Guestbook updated successfully.
        guestbook-deleted=Guestbook deleted successfully.

3. Save the file, and test your updated messages.

![Figure X: Now the message will display the value you specified in Language.properties.](../../../images/message-complete.png)

Congratulations! You've added useful feedback for operations in your 
application. Next, you'll add permission checking for your Guestbooks and 
Entries.
