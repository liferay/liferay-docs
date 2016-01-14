# Publishing Basic Web Content

Liferay's Web Content Management is a powerful and robust tool for creating and
organizing content on your web site. You'll begin by examining some basic
concepts involving sites and pages.

As you'll see, Liferay's WCM is a full-featured solution for managing your web
site. You'll start with an overview of what it has to offer and then you'll dive
down into its features. Note that web content is just one kind of asset on
Liferay. Other types of content (blog posts, wiki articles, message board posts,
etc.) are also considered assets. Liferay provides a general framework for
handling assets that includes tags, categories, comments, ratings, and more.
Please see the
[Publishing Content Dynamically](/discover/portal/-/knowledge_base/7-0/publishing-content-dynamically)
section for more information on Liferay's asset framework.

## Creating Web Content

Content is the reason web sites exist. Liferay has made it easier than ever to
get content published to your site. Because Liferay is so flexible, you can use
basic authoring tools right away or take advantage of the more advanced
features. It's adaptable to your needs.

You'll begin by creating some simple content using Liferay's WYSIWYG editor.
Then you'll publish it to the home page of the Lunar Resort's web site. This is
a fast and straightforward process that demonstrates how easy it is to create
and publish content on your Liferay instance. You'll learn about the Web Content
section in Site Administration so you can create and publish your first pieces
of content.

<!-- TODO: Review below paragraph for 7.0. Things are still unstable in UI, but
I attempted to document. -Cody -->

When you manage web content from the Site Administration menu, you can select
the location where the content resides. When selecting the Site Administration
dropdown menu from the Product Menu, you are presented with two scopes: site
scope and page scope. The site scope can be managed by clicking the Compass icon
(![Compass](../../images/icon-compass.png)) located on the Site Administration
dropdown menu, which is characterized by the name of the site. From there you
can select the site for which you want your content scoped. For instance, you
can add content that's available to a specific site or globally across your
Liferay instance. By default, the page scope must be configured before you can
access it. For instance, if you add a Web Content Display app to a site page
called *Lunar Rover*, you can navigate to the app's *Options* icon
(![Options](../../images/icon-options.png)) and select *Configuration* &rarr;
*Scope*. From the scope dropdown, you can select the current page you're on,
which will be characterized as *New* in parenthesis. Click *Save* and return to
the Site Administration &rarr; *Content* menu. You'll now observe the *Default
Scope* option. Select the *Gear* icon
(![Gear](../../images/icon-control-menu-gear.png)) and choose the page you
configured. Now the content created in this app is scoped to the *Lunar Rover*
page only.

![Figure 1: You can choose where to create content by navigating to the Site Administration menu and selecting your site and page scope.](../../images/site-page-scopes.png)

Once you have the Lunar Resort site selected, click on the *Web Content* link
under Content. You'll see a folder structure containing all of the web content
articles that exist in the currently selected scope (the Lunar Resort site). You
can click the *Add* icon (![Add Web Content](../../images/icon-add.png)) &rarr;
*Folder* to create a new folder. For sites with lots of content and web content
articles, it can be very useful to use folders to group certain kinds of web
content articles together. Click *Add* &rarr; *Basic Web Content* to create a
new web content article.

![Figure 2: Click *Add* &rarr; *Basic Web Content* to create a new simple web content article. To create a new web content article based on an existing web content structure, click *Add* and then click on the name of the structure you'd like to use.](../../images/web-content-add-menu.png)

Existing web content structures also appear in the *Add* menu. This provides
users with shortcuts for creating specific kinds of web content articles. For
example, if a web content structure called *FAQ* has been created for Frequently
Asked Questions articles in your currently selected scope, you can create a new
FAQ article by clicking *Add* &rarr; *FAQ*.

You have the ability to provide a structure and template to your web content
articles. You'll learn more about the power of web content structures and
templates later. For now, you'll cover the basics of creating a piece of web
content by first exploring the editor.

### Using the WYSIWYG Editor

Once you've clicked *Add* &rarr; *Basic Web Content*, you'll find a highly
customizable form that, by default, has three fields: title, summary, and a
powerful WYSIWYG editor. You could customize this form to contain whatever
fields our content needs but you'll keep things simple for now. If web content
structures have already been created in your currently selected scope, you can
select one for your new web content article by clicking the *Structure and
Template* dropdown. You'll discuss web content structures and templates in
detail in the next chapter.

Getting a new web site up and running is an exciting step for anyone, whether it
is a large corporation or a small non-profit charity. To celebrate this
momentous achievement at the Lunar Resort, you'll give our announcement some of
the pomp and circumstance it deserves!

Type the words *Welcome to the Lunar Resort* in the *Title* field. In the
*Summary* field, give a short description of the Lunar Resort's facilities. In
the content field, you'll add the body of your web content article.

The kneejerk reaction to the simplistic looking WYSIWYG editor is "Where are the
editor's controls?" Don't let the simplistic look of the editor fool you; the
editor gives you a seamless writing experience, displaying controls when you
need them and hiding them from view when they're unnecessary. This keeps the
editor space uncluttered so you can focus on your main objective: writing. As
you create content, the context-specific controls appear.

First, add some text that will serve as the heading in your article. If you
highlight the text, controls appear. These controls let you style the text,
provide a link, or share the article on Twitter. For your heading text, select
the *Styles* dropdown and give your heading a *Heading 1* style.

Whenever you place your cursor in the content area, the *Add* icon (`+`)
appears. If you click on it, controls for inserting an image, table, or
horizontal line (![Controls](../../images/icon-content-insert-controls.png))
appear. To insert an image, select the icon the depicts a mountain silhouette.
The image file selector screen appears, allowing you to choose an existing image
to upload. After adding a photo to the web content article, clicking it brings
up controls for justifying it to the left or right side of the article. Go ahead
an add an image to the Lunar Resort article, to spice it up a bit. Note that
when adding an image via the web content editor, you can either select the file
from the Documents and Media app or provide it via URL.

You can also insert a table with as many rows and columns as you see fit. When
clicking inside the table, table editing controls appear. They let you designate
the first row and/or column as table headers, and also enable you to add rows,
columns, and cells. You also have the option to insert a horizontal line, which
is a good seperator between sub-articles or an article and its title. Now you're
familiar with the editor's regular mode.

For those content creators that would rather write in HTML code, the editor also
caters to those individuals. To switch the editor to source view, select the
*Source* icon (`</>`). Note that the regular mode icon
(![Regular Mode](../../images/icon-paint-roller.png)) appears, which you can
select to return to regular mode. You also have the option to switch between a
dark and light theme by choosing the moon and sun icons. The built-in syntax
coloring helps you identify HTML elements, regardless of the mode you're using.

You can even work in a dual pane view that shows your HTML code on the left and
a preview pane on the right. To open this view, click on the *Enlarge* icon
(![Enlarge](../../images/icon-enlarge.png)). You can arrange the HTML and
preview panes horizonally or vertically. You can also hide the preview pane, if
preferred.

![Figure 3: You can view how your HTML would render by using the preview pane.](../../images/web-content-editor-html.png)

Every 25 seconds, the entry you're creating is automatically saved as a draft.
Because of this, you never have to worry about losing your work if something
unforeseen happens in your browser or on your computer. You can exit the
enlarged editor by clicking the *Done* button at the bottom of the screen.

Add a few short sentences announcing the grand opening of the Lunar Resort. The
content can be localized in whatever language you want. You'll learn more about
localizing your content later on.

You can integrate Liferay with external services to enable additional
functionality. For example, if you navigate to the Control Panel, click on
*Configuration* &rarr; *Server Administration* &rarr; *External Services*. From
this menu, you can install and enable Xuggler. Enabling Xuggler allows you to
embed audio and video files in web content. Installing and enabling Xuggler is
easy; you can do it right from the Control Panel. Please refer to the
[Server Administration](/discover/portal/-/knowledge_base/6-2/server-administration)
article of this guide for more details.

<!-- TODO: Replace link above with 7.0 link, when available. -Cody -->

<!-- TODO: The below two pagagraphs assume the AlloyEditor was configured with
audio/video button. This feature was supposed to go into the final released
version of 7.0, but was not guaranteed (time restraints). If the feature was
never finished, use the commented out section below, which provides the
alternative way of configuring the CKEditor, since the AlloyEditor was never
configured to support multimedia. -Cody -->

Once Xuggler has been installed and enabled, embedding audio or video files in a
web content article is easy. Place your cursor in the editor and select the
audio/video button and then choose the file you'd like to insert. If you haven't
already uploaded the audio or video file to your Liferay instance, you'll need
to navigate to *Documents and Media* in the Content section and upload the file
to Liferay. Select the file and then check that the audio or video component
appears in the web content. Excellent! When your web content is published, users
can view or listen to the embedded multimedia!

Are there other things you'd like to accomplish with your WYSIWYG editor that
are not provided by default? Visit the [Editor Configurations](/develop/tutorials/-/knowledge_base/7-0/editor-configurations)
tutorials to learn about extending the default editor to provide advanced
functionality that will suit your needs.

<!-- ALTERNATIVE METHOD FOR PROVIDING MULTIMEDIA: CKEDITOR CONFIGURATION

Once Xuggler has been installed and enabled, embedding audio or video files in a
web content article is easy. By default the current WYSIWYG editor (AlloyEditor)
does not support the use of audio/video files. You can extend the default
AlloyEditor by adding an audio/video button. You can learn about doing this in
the [Editor Configurations](/develop/tutorials/-/knowledge_base/7-0/editor-configurations)
tutorials section. Another option you have is changing the WYSIWYG editor to one
that supports embedding audio/video files in web content. The CKEditor, for
example, is an editor that supports this functionality. To use the CKEditor,
create a `portal-ext.properties` file in your Liferay root folder and add the
following property:

    editor.wysiwyg.portal-impl.portlet.ddm.text_html.ftl=ckeditor

Once you restart your Liferay instance, the AlloyEditor is replaced with the
CKEditor and you have the ability to add audio/video files!

Place your cursor in the editor and select the audio/video button and then
choose the file you'd like to insert. If you haven't already uploaded the audio
or video file to your Liferay instance, you'll need to navigate to *Documents
and Media* in the Content section and upload the file to Liferay. Select the
file and then check that the audio or video component appears in the web
content. Excellent! When your web content is published, users can view or listen
to the embedded multimedia!

-->

<!--
You can also download the web content article in XML format by clicking the
*Download* button. This button is available on the Edit Web Content screen,
after you've created your web content article.

![Figure 2.18: The *Download* button is only available for an article that has a structure and template.](../../images/web-content-download.png)

An XML version of an article is essential when creating content for themes using
the [Resources Importer](/participate/liferaypedia/-/wiki/Main/Resources+Importer).
If you'd like to learn more about importing web content with a theme, visit its
dedicated [tutorial](/develop/tutorials/-/knowledge_base/6-2/importing-resources-with-your-themes).

+$$$

**Note:** The *Download* button for web content articles comes standard for
Liferay 6.2 versions CE 6.2 GA4 and EE 6.2 SP10 and later. Prior Liferay 6.2
versions require the [Resources Importer CE](https://www.liferay.com/marketplace/-/mp/application/15502239)
or [EE](https://www.liferay.com/marketplace/-/mp/application/15502140) app
installed, depending on your Liferay Portal edition. The Resources Importer app
can be downloaded from [Liferay Marketplace](https://www.liferay.com/marketplace).

$$$

------
In 6.2, there had to be a WC structure, WC template, and the Resources Importer
portlet had to be installed for the *Download* button to be visible. This is not
currently the case in Liferay 7.0.
-------

-->

The bottom menu of the New Web Content form provides options for customizing
your web content.

![Figure 4: New web content can be customized in various ways using the menu located below the WYSIWYG editor.](../../images/wcm-menu.png)

**Structure and Template:** lets you customize the web content article's
structure and template. To learn more about web content structures and
templates, visit the [Designing Uniform Content](/discover/portal/-/knowledge_base/7-0/designing-unifrom-content)
section.

**Small Image:** sets the image that is used for the web content article's
previews. For example, when viewing an article in the Web Content library, the
small image is displayed as the article's icon.

**Metadata:** let you set the organizational hierarchy of the web content
article by selecting tags, categories, and priority. To learn more about tags
and categories, visit the [Organizing Content with Tags and Categories](/discover/portal/-/knowledge_base/7-0/organizing-content-with-tags-and-categories)
section.

**Schedule:** customizes the date and time your content publishes and/or
expires. To learn more about scheduling content, visit the
[Scheduling Web Content Publication](/discover/portal/-/knowledge_base/7-0/scheduling-web-content-publication)
section.

**Display Page:** lets you determine where the web contents are displayed when
linked from other pages. The Canonical URL can be used here. The Canonical URL
is unique for articles that redirect the visitor to the article's default
display page.

Imagine you have a newspaper with a sports section and a technology section. You
add a Sports page and a Tech page to your site, each one with a specific banner
and look and feel. You want the articles to appear in the appropriate pages, but
you know in Liferay, articles are not related to pages. You can add an article
as often as you like in different web content display apps or in configured
Asset Publishers. But if you have a *View in context* link, where will you show
your article? This is where you'd use a default display page. Articles that have
a default display page defined are shown with other related articles in the same
display page.

Imagine you have 100 sports articles and 100 tech articles. Instead of needing
to create a page for each article to show it, you can have only one sports page
and one tech page, and can show all articles in one place in a consistent
fashion. You'll work through an example of creating a display page in the
[Creating a Display Page](/discover/portal/-/knowledge_base/7-0/publishing-basic-web-content#creating-a-display-page)
sub-section.

**Related Assets:** lets you determine content relationships between the web
content article and other assets in your Liferay instance, even if they don't
share any tags and aren't in the same category. You can connect your content to
a Blogs Entry, Message Boards Message, Web Content, Calendar Event, Bookmarks
Entry, Documents and Media Document, Wiki Page, etc. To learn more about
defining content relationships and publishing links to those related assets,
visit the [Defining Content Relationships](/discover/portal/-/knowledge_base/7-0/defining-content-relationships)
section.

![Figure 5: This blog entry has links to three Related Assets: one web content and two message board entries.](../../images/related-assets-link.png)

**Permissions:** customizes who has access to the content. By default, content
is viewable by Anyone (Guest Role). You can limit viewable permissions by
selecting any Role from the drop-down or in the list. Additionally, Liferay
provides the ability to customize permissions in more detail. Select the *More
Options* link below the drop down button and you'll find the different
activities you can grant or deny to your web content article.

Before you display your web content, you'll learn how to localize it to cater to
different language speaking users.

<!-- Left off here. -->

### Localizing Web Content

When you create a new piece of web content, you have the ability to choose a
default language. If you click *Change*, you can select your default language
from a large number of languages Liferay supports.

![Figure 3.17: You have many translation languages to choose from for your web content.](../../images/04-web-content-translation.png)

After you click *Add Translation*, you can select a language by scrolling
through the list or by entering the language you want to use in the search box.
When you select a language, a new window opens within your browser enabling you
to translate the original web content into the selected language. Once you are
done with the translation, click *Save* and the translation is added to the list
of *Available Translations*.

![Figure 3.18: After typing your translated text, you can summarize the translation in the *Abstract* section.](../../images/04-web-content-translation-2.png)

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
control of what language is displayed in the portal.

$$$

The ability to completely delete a translation in one step has also been added.
Instead of simply disabling a translation or having to go through a multistep
process to remove it, you can now simply open the translation you don't want and
click *Remove Translation*.

When you create a new web content structure, each field you create has a
*Localizable* checkbox displayed next to it. This enables you to control what
can and can't be changed in the translation process. For example, if you don't
want images or content titles to be changed when the content is translated, you
can make sure those fields aren't listed as localizable. When you follow the
steps above to localize content, only fields within the structure that had the
*Localizable* box checked appear within the translation window.

+$$$

**Note:** The Localizable checkbox was removed for 6.2 CE GA1 and GA2, and 6.2
EE GA1, SP1, and SP2. However, the Localizable checkbox is now available for
Liferay following these versions for web content structure fields. You can view
[LPS-33161](https://issues.liferay.com/browse/LPS-33161) for more details.

$$$

Next, you'll begin creating a display page to show your web content.

### Creating a Display Page

There are two ways of creating a display page. You can use a *Content Display
Page* template, which automatically creates everything you need, or you can
create one manually. The Content Display Page template is found under *Page
Templates* in the Sites section of the Control Panel.

To create a display page manually, add an Asset Publisher to a page. Then make
it the Default Asset Publisher for the page. This defines this Asset Publisher
as the one that displays the content if several Asset Publishers are on the same
page. Set this up by clicking *Configuration* on your Asset Publisher. Under the
*Setup* tab, navigate to *Display Settings* and check the checkbox labeled *Set
as the Default Asset Publisher for This Page*.

Once you've given an article its default display page, links to the article 
redirect the user to its default display page. To see how this works, add an
Asset Publisher to another page, like the Home page of the newspaper, and
configure it to *View in a Specific Portlet*. This setting is found in the
*Asset Link Behavior* menu under Display Settings. If you click on the link,
you'll be redirected to the Default Display Page of the article.

You now see that the link looks something like this:

    www.lunar-resort.com/lunar-article

This is an example of a canonical URL, and it's a nice enhancement for Search
Engine Optimization (SEO) because the article's URL becomes the page URL. To a
search engine that's crawling your site, this means that the location of your
article never changes. And if you decide to use the content on another page in
the future, the article is still available at this URL. This feature is used in
search results, in related assets and in Asset Publishers. For more information
on Liferay's Display Pages, see the [Content Display Pages](/discover/portal/-/knowledge_base/6-2/using-the-asset-publisher#content-display-pages)
article.

For this piece of web content, we don't need to change anything. After you're
finished with permissions, click *Save as Draft*. This saves the content in
draft form. Once you're satisfied with your changes, select *Publish*. This
makes the content available for display, but we still have some work to do to
enable users to see it. In Liferay WCM, all content resides in a container,
which is the Web Content Display app. You'll look at how it works next.

## Displaying Web Content

Now that we've created and published our first piece of web content for
the Lunar Resort, it's time to display it. First, add the *Web Content Display*
portlet to our Welcome page by selecting the *Add* button from the left palette
and selecting the *Applications* tab.

![Figure 2.21: Adding the Web Content Display Portlet](../../images/add-web-content-display.png)

Once the portlet appears, drag it to the position on the page where you want
your content to appear. You can have as many Web Content Display portlets on a
page as you need, which gives you the power to lay out your content exactly the
way you want it. 

To add existing web content, click the *Select Web Content* button on the lower
left of the portlet. You will see the message *Please select a web content from
the list below*. You have several options here.

Naturally, if your content appears in the list, you can simply select it. If
there is lots of published content available, you could search for the content
by name, ID, type, version, content and site (click the *Advanced* gear to see
all the options). You can also show the available locales for your content. If
you're working on the page for a particular language, you can select the
translation of your content that goes with your locale. To learn more about
translating your content, visit the
[Localization](/discover/portal/-/knowledge_base/6-2/localization) section.

![Figure 2.22: Publishing web content is a snap. At a minimum, you only have to select the content you wish to publish. You can also enable lots of optional features to let your users interact with your content.](../../images/04-web-content-choosing-web-content.png)

If you have enabled OpenOffice.org integration with your portal, you can also
enable document conversion for your content. This gives your users the ability
to download your content in their format of choice. This is especially handy if
you are running a research or academically oriented site; users can very quickly
download PDFs of your content for their research projects.

Note that you also have other options, such as enabling a Print button, enabling
ratings so users can rate the content, enabling comments and enabling ratings on
comments.

The Print button pops the content up in a separate browser window that contains
just the content, without any of the web site navigation. This is handy for
printing the content. Enabling ratings shows one of two ratings interfaces
Liferay has: five stars or thumbs up and thumbs down. This can be set globally
in the `portal-ext.properties` file. See the
[Properties Document](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Ratings%20Tag%20Library) for further information
for more details about this.

Enabling comments creates a discussion forum attached to your content which
users can use to discuss your content. Enabling ratings on comments gives your
users the ability to rate the comments. You may decide you want one, some or
none of these features, which is why they're all implemented as simple check
boxes to be enabled or disabled at need.

If you click the *Supported Clients* tab, you'll see you can choose the type of
client to which you want to expose content. This lets you target the large
screens of users' computers for expansive graphics and lots of special effects
or target the small screens of mobile devices with pertinent information and a
lightweight page. For now, leave both checked and click the *Save* button. You
can now close the configuration window.

To publish new content, select the *Add* button on the lower left of the
portlet. This launches the same full-featured editor you've already seen in the
Control Panel, which lets you add and edit content in place as you are working
on your page.

This is another example of the flexibility that Liferay Portal offers. At times,
you may want to add content directly into the Web Content Display portlet of the
page you're managing, especially if you are in the process of building the page.
At other times, you may want to navigate to Site Administration to create
content, because at that moment you're more concerned with the creation of the
content and not where the content will later be displayed. Liferay WCM supports
both processes. 

Editing content that's already been published is just as easy as creating new
content is. Once the content is displayed--whether you've selected content or
created it in the Web Content Display portlet--you can edit the content directly
from the Web Content Display portlet or from the Control Panel. To edit it from
the Web Content Display portlet, select the *Edit* button to the lower left of
the portlet. This launches the WYSIWYG editor and from there you can make any
necessary changes.

![Figure 2.23: The *Edit*, *Select Web Content*, and *Add* buttons appear when hovering over their icons.](../../images/web-content-display-icons.png)

When you publish updates to a web content that's already being displayed
somewhere on your portal (e.g., in a Web Content Display portlet or an Asset
Publisher portlet), the content is immediately updated (unless, of course, you
have a workflow enabled, which is discussed in greater detail in the
[Using Workflow]()
section). It makes no difference whether you edit it from a Web Content Display
portlet, from the Asset Publisher, or from the Site Administration interface.

<!-- TODO: Update link above for 7.0. -Cody -->

+$$$ 

**Note:** If you want to view your page the way your users will see it (i.e.,
without all those portlet controls and icons), go up to the left palette and
select the *Edit Controls* icon. This makes all those extra controls you see as
a portal administrator disappear. You'll also notice the green "eye" transforms
into an orange "eye". If you need to use those controls again, just select *Edit
Controls* to return to the original format.

$$$

That's pretty much all there is to simple content creation. Whole sites have
been created this way. But if you want to take advantage of the full power of
Liferay's WCM, you'll want to use structures and templates found in the
[Advanced Web Content Management](/discover/portal/-/knowledge_base/6-2/advanced-web-content-management)
chapter. Next, let's see how you can manage your content with an approval
process called workflow.

<!-- TODO: Update link above for 7.0. -Cody -->
