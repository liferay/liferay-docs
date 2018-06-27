# Other Content Options [](id=other-content-options)

Publishing content won't always be a neat and simply process. Here are some 
important options and tools that you can use to enhance your content and user
experience.

## Localizing Content [](id=localizing-content)

When you create a new piece of web content, you have the ability to choose a
default language. First, you'll need to change the system configuration to 
enable the option to change the default language.

1.  Go to the *Control Panel* &rarr; *System Settings*. 

2.  Locate *Web Content* &rarr; *Administration* by scrolling or using the 
    search bar.

3.  Check the box labeled *Changeable Default Language*.

4.  *Save* your configuration.

You now need to add translations for any languages you need. Adding 
translations works like this:

1.  Open a web content article.

2.  Click the flag icon with a country code on it next to any localizable web 
    content field.

3.  Select a language from the list.

When you select a language, all of the fields in the article will switch to the 
new language. To create the new translation, fill in the fields in the selected 
language and publish the article. 

![Figure 1: Adding a translation to an 
article.](../../../../images/web-content-translation.png)

You can modify the language translation list by inserting `locales.enabled=`
followed by your preferred languages in your `portal-ext.properties` file. For
example, `locales.enabled=ar_SA,nl_NL,hi_IN` offers *Arabic (Saudi Arabia)*,
*Dutch (Netherlands)*, and *Hindi (India)*.

+$$$

**Warning:** If you switch your site's default language (e.g., via friendly
URL), but do not have the necessary translations for localizable fields, your
site's language values will be used from the old default language. Therefore,
you should change the default language of your site *only* when you have
translated values for all localizable entities. Otherwise, you may not be in
control of what language is displayed in your Liferay instance.

$$$

When you create a new web content structure, each field you create has a
*Localizable* checkbox displayed next to it. This enables you to control what
can and can't be changed in the translation process. For example, if you don't
want images or content titles to be changed when the content is translated, you
can make sure those fields aren't listed as localizable. When you follow the
steps above to localize content, only fields within the structure that had the
*Localizable* box checked appear within the translation window.

## Xuggler for Embedding Video [](id=xuggler-for-embedding-video)

Xuggler is a tool which generates video previews and makes it possible to embed
videos from your Documents and Media library in web content and elsewhere on the
site. To enable Xuggler:

1.  Navigate to the *Control Panel*.

2.  click on *Configuration* &rarr; *Server Administration* &rarr; *External Services*.

3.  Scroll to the bottom and click *Install* in the *Xuggler* section.

    This will download the necessary libraries and prompt you to restart the
    server to enable Xuggler.

4.  After the server restarts, you can enable Xuggler from the same page.

Once Xuggler has been installed and enabled, you can embed a video or audio 
file in a web content article the same way you added images previously. 

![Figure 2: If you've installed and enabled Xuggler from the *Server Administration* &rarr; *External Tools* section of the Control Panel, you can add audio and video to your web content!](../../../../images/web-content-audio-video.png)

## XML Format Downloads [](id=xml-format-downloads)

@product@ provides tools like the [Resource Importer](/develop/tutorials/-/knowledge_base/7-1/importing-resources-with-your-themes) and Site Initiators which can be deployed to build a site almost 
instantly. To import Web Content like this, you need to have it individually
exported in XML format. To export the content:

1.  Go to *Site Administration* &rarr; *Content* &rarr; *Web Content*

2.  Start editing the article you need an XML version of.

3.  Click the *Options* icon (![Options](../../../../images/icon-options.png)) in 
    the top right of the page and select *View Source*.

This displays the raw XML source of the article. You can copy this content to 
save into an XML file locally.

![Figure 3: The *View Source* button is available from the *Options* button.](../../../../images/web-content-download.png)

## Subscribing to Content [](id=subscribing-to-content)

An administrator or web content writer can subscribe to an article or folder to follow changes being made to it. To do this:

1.  Go to *Content* &rarr; *Web Content* for your Site.

2.  Click *Options* 
    (![Options](../../../../images/icon-app-options.png)) &rarr; *Subscribe* next 
    to the article or folder you want to follow.

Anytime an asset that you follow is modified, you'll receive an email notifying 
you of the change.

![Figure 4: Click the Subscribe icon in the web content entity's *Information* menu to begin receiving web content notifications.](../../../../images/web-content-subscribe.png)

That's pretty much all there is to basic content creation. Whole sites have
been created this way. But if you want to take advantage of the full power of
@product@'s WCM, you'll want to use structures and templates. You'll cover these
topics next.
