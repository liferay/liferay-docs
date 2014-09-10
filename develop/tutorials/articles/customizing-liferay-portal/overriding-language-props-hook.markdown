# Overriding Language Properties Using a Hook

Hooks let you change Liferay Portal's messages to suit your
needs. For example, you may want to change a Liferay portlet's title, the message 
Liferay displays on successfully completing a request, or some other bit of 
text in the UI. You can override Liferay Portal's messages using a language
properties Liferay hook plugin. 

![Figure 1: You can override portlet titles, like the Sign In portlet's default title "Welcome", with a new title from a language properties Liferay hook plugin.](../../images/override-sign-in-en.png)

This tutorial shows you how to you how to override Liferay's messages with
messages that you want to use. 

To do so, you need to create a *Language* file for the locale of 
the messages you want to customize, then refer to that file from your 
`liferay-hook.xml` file. 

This tutorial illustrates this process by showing you how to use a hook to 
override the title of Liferay's Sign In portlet. First, it's useful to know 
where you can find the default Language files and the messages they contain. Go 
ahead and get started!

1. Determine the language keys that you want to change.
    - locate Liferay's language properties files.
    - find the key you want to change.
    - how to determine a portlet's ID
2. 

## Locating the Default Language Keys

Liferay's default Language files are in the `WEB-INF/lib/portal-impl.jar/content` 
folder of the Liferay Portal deployment on your app server. You can also view 
these on [Github](https://github.com/liferay/liferay-portal). The Language files 
contain Liferay's default language keys. Each file corresponds to a language 
that Liferay supports. For example, `Language_en.properties` contains the English 
language keys, `Language_es.properties` contains the Spanish language keys, 
`Language_fr.properties` contains the French language keys, and so on.

A common language key to override is `javax.portlet.title.58`. The value of this 
language key is the title of the Sign In portlet. The next steps show you how to 
use a hook to override it.

## Creating Language Files

Once you have created a hook project, you need to create the Language files and 
their contents. The steps here show you how to do this in Liferay IDE. If you're 
developing from the command line, you can use the information in steps 2 and 3 
to manually create the Language files, the file path they reside in, and their 
contents.

1.  If you don't yet have a hook project, create one following the steps in the 
	[Creating a Hook](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-a-hook-lp-6-2-develop-tutorial) tutorial.

2.  In Liferay IDE, right click your hook project and select 
	*New* &rarr; *Liferay Hook Configuration*. In the window that appears, check 
	the *Language properties* check box and click *Next*.
	
	![Figure 3: Language properties is checked in the Liferay Hook Configuration wizard.](../../images/new-hook-configuration-language.png)

3.	Accept the default path of `docroot/WEB-INF/src/content` for the content 
	folder. Click *Add* and add the files `Language.properties`, 
	`Language_en.properties`, and `Language_es.properties`. Then click *Finish*.
	
	![Figure 4: Add the Language files in the Liferay Hook Configuration wizard.](../../images/new-hook-configuration-language-files.png)

4.  Add `javax.portlet.title.58=Welcome` to the files `Language.properties` and 
	`Language_en.properties`. Add `javax.portlet.title.58=Bienvenidos` to 
	the file `Language_es.properties`.
	
5.  Deploy your hook and reload a page on your portal that has the Sign In 
	portlet. To see the Spanish translation, add the Language portlet and click 
	on the flag of Spain. To switch back to the English translation, click on 
	the U.S. flag.
	
	![Figure 5: The Spanish translation.](../../images/override-sign-in-es.png)
	
If you followed these steps using Liferay IDE, then you're finished and can skip 
the next section! The Liferay Hook Configuration wizard in Liferay IDE 
automatically takes care of inserting the proper references to your Language 
files in `liferay-hook.xml`. If you need to add more Language files, simply run 
the wizard again.

However, what if you created your Language files manually? What if you have an 
inherent distrust of wizards? What if you're a veteran commander of the command 
line? Don't fret! The next section shows you how to manually reference your 
Language files in `liferay-hook.xml`.

## Referring to Language Files

Each reference to a Language file in `liferay-hook.xml` is defined by a set of 
`language-properties` tags. For example, the three Language files you created 
earlier are referenced in `liferay-hook.xml` as:

    <hook>
        ...
        <language-properties>content/Language.properties</language-properties>
        <language-properties>content/Language_en.properties</language-properties>
        <language-properties>content/Language_es.properties</language-properties>
        ...
    </hook>

You can create these references manually in the source `xml`, or by using the 
*Overview* mode in Liferay IDE. Overview mode provides an easy to use graphical 
interface where you can add to and edit the `xml` without actually needing to 
enter any raw `xml`.

Just follow these steps to use Overview mode in Liferay IDE to reference your 
Language files.

1.  Open `liferay-hook.xml` and click the *Overview* tab to access Overview 
	mode.
	
2.  Click *Language Properties* in the *Outline* column on the left. The 
	*Language Properties* field appears to the right.

3.  Click the plus icon and type in the Language file that you want to 
	reference. For example, to add a reference to `Language_en.properties`, 
	enter `content/Language_en.properties`. You can repeat this step for as many 
	Language files as you want to reference.

![Figure 6: Overview mode of `liferay-hook.xml`, with added Language files.](../../images/overview-mode-language-props.png)
	
If you want to view the `xml` references that Liferay IDE has entered for you, 
click on the *Source* tab of `liferay-hook.xml`.

---

 ![Tip](../../images/tip-pen-paper.png) **Tip:** Check the DTD of each Liferay
 XML file, you modify for the elements and attributes that can be included in the
 XML and the specified order for those elements. You can find the Liferay DTDs
 online here: [http://docs.liferay.com/portal/6.2/definitions](http://docs.liferay.com/portal/6.2/definitions).

---

## Next Steps

 [Extending your Indexer Post Processor Using a Hook](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/extend-indexer-post-processor-hook)
