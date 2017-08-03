# Creating Language Keys

Any modern application should place its messages and form field labels in a
language keys file that can be duplicated and then translated into multiple
languages. Here, you'll learn how to provide a *default* set of language keys in
English for your application. For further information about language keys and on
providing automatically translated language keys, 
[see our tutorial on the subject](/develop/tutorials/-/knowledge_base/7-0/automatically-generating-language-files).

Language keys are stored in the `Language.properties` file included in your
module. `Language.properties` is the default, but you can create a number of
translations by appending the ISO-639 language code to the file name (e.g., 
`Language_en.properties` for English or `Language_de.properties` for German).
For now, stick to the default language keys.

1.  Open `/src/main/resources/content/Language.properties` in your 
    `guestbook-web` module. There are default keys in there; remove them. 

2.  Paste in the following keys:

        entry-added=Entry added successfully.
        entry-deleted=Entry deleted successfully.
        guestbook-added=Guestbook added successfully.
        guestbook-updated=Guestbook updated successfully.
        guestbook-deleted=Guestbook deleted successfully.

3.  Save the file. 

You've now put your messages in place, and they can now be used in your
application. Next, you'll add the messages to your action methods. 

