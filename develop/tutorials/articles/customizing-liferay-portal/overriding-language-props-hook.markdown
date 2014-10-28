# Overriding Language Properties Using a Hook [](id=overriding-language-properties-using-a-hook)

A language properties hook lets you change Liferay Portal's messages and labels
to suit your needs. For example, you may want to change the title of a Liferay
core portlet, a message Liferay displays on successfully completing a request,
or some other bit of text in the UI. You can override all these strings using
a language properties Liferay hook plugin. 

![Figure 1: You can override portlet titles, like the Sign In portlet's default title *Sign In*, with new titles from a language properties hook plugin.](../../images/override-sign-in-en.png)

Customizing Liferay's default textual content involves the following two steps:

1. Locating Liferay's default language keys that correspond to the text that you
want to replace.
2. Specifying new custom text values using a Liferay language properties hook.

This tutorial illustrates this process, and it shows you how to use a hook to
override the title of Liferay's Sign In portlet. You can apply the same process
to customizing any of Liferay's language properties. 

First, you'll learn how to find the language key properties that specify
Liferay's default text values. It's time to get started and locate the default
language files. 

## Locating the Default Language Keys [](id=locating-the-default-language-keys)

Liferay's default language files are in the
`WEB-INF/lib/portal-impl.jar/content` folder of the Liferay Portal deployment on
your app server. You can view Liferay CE versions of these files on
[GitHub](https://github.com/liferay/liferay-portal). The language files contain
Liferay's default language keys. Each file corresponds to a language locale that
Liferay supports. For example, `Language_en.properties` contains the English
language keys, `Language_es.properties` contains the Spanish language keys,
`Language_fr.properties` contains the French language keys, and so on. 

All of Liferay's default text values are specified as properties in these
language properties files. The title of Liferay's Sign In portlet, for example,
is specified by language key `javax.portlet.title.58` in each of the files. Are
you wondering why the key includes the number `58`? That's an ID number assigned
to the portlet. Per convention, the names of the language properties of
Liferay's core portlets end with a portlet ID number. 

After you've noted the names of all of the properties that you want to
customize, you can create new language properties files to specify the text
values that you prefer. You'll create these properties files in a Liferay hook
plugin. 

## Creating Language Files [](id=creating-language-files)

The steps in this section show you how to use Liferay IDE to create a language
property hook plugin for overriding Liferay's default text values with your own
custom values. 

1.  If you don't yet have a hook project, create one following the steps in the 
    [Creating a Hook](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/6-2/creating-a-hook-lp-6-2-develop-tutorial) tutorial.

2.  In Liferay IDE, right click your hook project and select *New* &rarr;
*Liferay Hook Configuration*. In the window that appears, select the *Language
properties* check box and click *Next*. 

    ![Figure 2: In the Liferay Hook Configuration wizard, you can select the *Language properties* check box to create a language properties hook.](../../images/new-hook-configuration-language.png)

3.  Accept the default path `docroot/WEB-INF/src/content` for the content
    folder. Click on the *Add...* button and add the names of any language
    properties files that contain text values that you want to customize. Then
    click *Finish*. 

    ![Figure 3: The Liferay Hook Configuration wizard lets you specify any language properties files to customize.](../../images/new-hook-configuration-language-files.png)

4.  In the properties files, specify each of the properties that you are
    customizing and assign your custom values to them. 

    For example, to customize the Sign In portlet's title for English and
    Spanish languages, you'd specify the `javax.portlet.title.58` property in
    each language file and assign it your custom value in that language. Here's
    what you might specify in the following language files for the Sign In
    portlet's title: 

    - **Language.properties:** `javax.portlet.title.58=Welcome` 
    - **Language_en.properties:** `javax.portlet.title.58=Welcome`
    - **Language_es.properties:** `javax.portlet.title.58=Bienvenidos` 

5.  Deploy your hook and reload any page that uses the language key you've
    customized. To see the customization for the particular language that you
    customized, you can add the Language portlet to a page and click on a
    nation's flag that uses that language. 

    For example, to switch to see a Spanish translation customization, add the
    Language portlet and click on Spain's flag. To switch back to an English
    translation, click on the U.S. flag or Great Britain's flag.

    ![Figure 4: Here's a Spanish translation of the Sign In portlet's title.](../../images/override-sign-in-es.png)

If you created the language properties hook using Liferay IDE, you're finished
and can skip the next section. The Liferay Hook Configuration wizard in Liferay
IDE automatically takes care of inserting the proper references in the
`liferay-hook.xml` file to your language files. If you need to add more language
files, simply run the wizard again. 

If you created your language files manually, however, the next section shows you
how to manually reference your language files in your hook plugin's
`liferay-hook.xml` file.

## Referencing Custom Language Files in the liferay-hook.xml File [](id=referencing-custom-language-files-in-the-liferay-hook-xml-file)

Each reference to a language file in `liferay-hook.xml` is defined in a
`language-properties` tag. For example, you could reference custom language
files for English, Spanish, and the default language properties file by
specifying them in the `liferay-hook.xml` file like this:

    <hook>
        ...
        <language-properties>content/Language.properties</language-properties>
        <language-properties>content/Language_en.properties</language-properties>
        <language-properties>content/Language_es.properties</language-properties>
        ...
    </hook>

You can create these references manually in the raw XML, or by using *Overview*
mode in Liferay IDE's XML editor. Overview mode provides an easy-to-use
graphical interface for adding and editing XML without requiring you to enter
any raw XML tags. 

Just follow these steps in Liferay IDE to use Overview mode to reference your 
language files.

1.  Open `liferay-hook.xml` and click the *Overview* tab to access Overview 
    mode.

2.  Click *Language Properties* in the *Outline* column on the left. The 
    *Language Properties* field appears to the right.

3.  Click the plus icon and type in the language file that you want to 
    reference. For example, to add a reference to `Language_en.properties`, 
    enter `content/Language_en.properties`. You can repeat this step for as many 
    language files as you want to reference.

![Figure 5: Liferay IDE's Overview mode for editing `liferay-hook.xml` facilitates specifying custom language files.](../../images/overview-mode-language-props.png)
 
If you want to view the XML references that Liferay IDE generated for you, click
on the *Source* tab of `liferay-hook.xml` editor.

+$$$

**Tip:** You can check the DTD of any Liferay
 XML file, such as `liferay-hook.xml`.  You can find the Liferay DTDs
 online at [http://docs.liferay.com/portal/6.2/definitions](http://docs.liferay.com/portal/6.2/definitions).

$$$

Now that you've customized the Liferay language key values, you can enjoy seeing
your new titles, labels, and message strings in your portal. 

## Related Topics [](id=related-topics)

[Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)

<!-- TODO Activate topic link when the tutorial is available.
[Extending your Indexer Post Processor Using a Hook](/develop/tutorials/-/knowledge_base/6-2/extend-indexer-post-processor-hook)
-->

