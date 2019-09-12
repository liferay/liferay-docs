---
header-id: creating-language-keys
---

# Creating Language Keys

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Displaying Messages and Errors</p><p>Step 1 of 3</p>
</div>

Modern applications should place messages and form field labels in a language
keys files that hold multiple language translations. Here, you'll learn how to
provide a *default* set of English language keys for your application. For more
information on language keys and providing automatically translated language
keys, 
[see Generating Translations](/docs/7-2/frameworks/-/knowledge_base/f/automatically-generating-translations).

Language keys are stored in the `Language.properties` file included in your 
`guestbook-web` module. `Language.properties` is the default, but you can create 
more translations by appending the ISO-639 language code to the file name
(e.g., `Language_es.properties` for Spanish or `Language_de.properties` for
German). For now, stick to the default language keys. 

Follow these steps to create your language keys:

1.  Open `/src/main/resources/content/Language.properties` in your 
    `guestbook-web` module. Remove the default keys in this file. 

2.  Paste in the following keys: 

    ```properties
    entry-added=Entry added successfully.
    entry-deleted=Entry deleted successfully.
    guestbook-added=Guestbook added successfully.
    guestbook-updated=Guestbook updated successfully.
    guestbook-deleted=Guestbook deleted successfully.
    ```

3.  Save the file. 

Your messages are now in place, and your application can use them. Next, you'll 
add them to your action methods. 
