---
header-id: localization
---

# Localization

[TOC levels=1-4]

If you're writing a Liferay application, you're probably a genius who is also
really cool, which means your application will be used throughout the entire
world. At least, if its messages can be translated into their language, it will.
Thankfully, Liferay facilitates creating and using message translations and
adapting to cultural conventions for user names and initials. 

You can leverage Liferay's localization framework or use standard resource
bundles to localize your app. The localization framework uses properties files
(the same as any resource bundle) but leverages a default properties file called
`Language.properties` to propagate messages (language keys) to properties files
for all your locales. For example, when you add a new message to the
`Language.properties` file and run Language Builder, it propagates the message
to your locale files. All you must do is translate the message in each locale
file, manually or automatically using Language Builder. 

Language Builder integrates the Microsoft Text Translator API to translate each
locale file's messages from your default locale to the respective locale. A
machine's translation is no substitute for a human's, of course, but the
automatic translation gives you a base to work from. 

It's common to use the same messages in multiple apps. @product@ provides these
message sharing features:

-   @product@'s messages (and their translations) are available for all your 
    apps to use. JSP tags such as `<liferay-ui:message ... />` let you use all
    @product@ messages. 

-   Language modules are easy to use in all your apps. They're great for 
    centralizing messages in all your locales. 

Lastly, @product@ provides settings for adapting your app to cultures:

-   Naming conventions for users

-   Initial conventions for user avatars 

-   Text direction settings (left-to-right or right-to-left)

Localization is important to all site users. Liferay helps you get it right!
Start with localizing your application using Liferay's localization framework. 
