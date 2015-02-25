# Features of Liferay's WCM [](id=features-of-liferays-wcm)

Liferay's WCM offers a host of features that makes managing the content of your
site easier:

- **WYSIWYG Editor:** A complete HTML editor that allow you to modify fonts, add
  color, insert images and much more.

- **Structure Editor:** Easily add and remove fields you want available to
  content creators and then dynamically move them around. This editor includes
  an entire suite of form controls you can drag and drop onto your structure.

- **Template Editor:** Import template script files that inform the system how
  to display the content within the fields determined by the structure.

- **Web Content Display:** A portlet that allows you place web content on a page
  in your portal.

- **Asset Publisher:** A portlet which can aggregate different types of content
  together in one view.

- **Scheduler:** Lets you schedule when content is reviewed, displayed and
  removed.

- **Workflow Integration:** Run your content through an approval or review
  process.

- **Staging:** Use a separate staging server or stage your content locally so
  you can keep your changes separate from the live site. 

Liferay's Web Content Management is a powerful and robust tool for creating and
organizing content on your web site. Let's begin by examining some basic
concepts involving sites and pages.

As you'll see, Liferay's WCM is a full-featured solution for managing your web
site. We'll start with an overview of what it has to offer and then we'll dive
down into its features. Note that web content is just one kind of asset on
Liferay. Other types of content (blog posts, wiki articles, message board posts,
etc.) are also considered assets. Liferay provides a general framework for
handling assets that includes tags, categories, comments, ratings, and more.
Please see the
[Displaying Content Dynamically](/discover/portal/-/knowledge_base/6-2/displaying-content-dynamically)
section for more information on Liferay's asset framework.

## Creating (Basic) Content [](id=creating-basic-content)

As we've already discussed, content is the reason web sites exist. Liferay
Portal has made it easier than ever to get content published to your site.
Because Liferay Portal is so flexible, you can use basic authoring tools right
away or take advantage of the more advanced features. It's adaptable to your
needs.

We'll begin by creating some simple content using Liferay's WYSIWYG editor. Then
we'll publish it to the home page of the Lunar Resort's web site. This is a fast
and straightforward process that demonstrates how easy it is to create and
publish content on your Liferay Portal instance. Let's learn about the Web
Content section in Site Administration so we can create and publish our first
pieces of content.

When you manage web content from the Site Administration page, you can select
the location where the content resides. For instance, you can add content that's
available to a specific site or globally across the portal. If you're on the
site you wish to add content to, simply navigate to the *Admin* tab on the
Dockbar and select *Site Administration*. Conversely, if you need to switch
sites or would like to add content globally, navigate to the Control Panel and
select *Sites*. From this window, you can change the scope of where you'd like
to view, edit, or create content.

![Figure 2.14: You can choose where to create content by navigating to the Control Panel and selecting *Sites*.](../../images/sites-selector.png)

Once you have the Lunar Resort site selected, click on the *Web Content* link in
Site Administration. You'll see a folder structure containing all of the web
content articles that exist in the currently selected scope (the Lunar Resort
site). You can click *Add* &rarr; *Folder* to create a new folder. For sites
with lots of content and web content articles, it can be very useful to use
folders to group certain kinds of web content articles together. Click *Add*
&rarr; *Basic Web Content* to create a new web content article.

![Figure 2.15: Click *Add* &rarr; *Basic Web Content* to create a new simple web content article. To create a new web content article based on an existing web content structure, click *Add* and then click on the name of the structure you'd like to use.](../../images/web-content-add-menu.png)

Existing web content structures also appear in the *Add* menu. This provides
users with shortcuts for creating specific kinds of web content articles. For
example, if a web content structure called *FAQ* has been created for Frequently
Asked Questions articles in your currently selected scope, you can create a new
FAQ article by clicking *Add* &rarr; *FAQ*.

Click *Manage* &rarr; *Structures* to view a list of web content structures that
have already been created in your chosen scope. You can add new web content
structures here. Web Content templates are always associated with a particular
web content structure, so you can click *Actions* &rarr; *Manage Templates* to
view the web content templates associated with a structure or add a new
template to a structure. In the next chapter, we'll cover advanced features such
as structures, templates, and content scheduling in detail. 

### Rich, WYSIWYG Editing [](id=rich-wysiwyg-editing)

Once you've clicked *Add* &rarr; *Basic Web Content*, you'll find a highly
customizable form that by default has two fields: a title and a powerful WYSIWYG
editor. We could customize this form to contain whatever fields our content
needs but we'll keep things simple for now. If web content structures have
already been created in your currently selected scope, you can select one for
your new web content article by clicking *Select* next to the *Structure*
heading. We discuss web content structures and templates in detail in the next
chapter.

Type the words *Welcome to the Lunar Resort* in the *Name* field. Notice that
content can be localized in whatever language you want. If you click on the
*localize* checkbox, two select boxes appear which allow you to pick the
language you're working in and the default language. You can enter translations
of your content for any language in the list. The figure below shows this
interface but for now, we won't be using it, so you can leave it unchecked. In
the content field, add a short sentence announcing the web site is up and
running.

Getting a new web site up and running is an exciting step for anyone, whether it
is a large corporation or a small non-profit charity. To celebrate this
momentous achievement at the Lunar Resort, let's give our announcement some of
the pomp and circumstance we think it deserves!

Using the editor, select all the text and then change the style to *Heading 1*
and the color to *Navy*. You could insert an image here or even more text with a
different style, as demonstrated in the screenshot below. You can also add
bullets, numbering, links to another site or custom images. You can even add an
emoticon. Go ahead and add a smiley face to the end of your announcement.

![Figure 2.16: View your content changes directly in the editor.](../../images/04-web-content-example2.png)

The WYSIWYG editor is a flexible tool that gives you the ability to add text,
images, tables, links and more. Additionally, you can modify the display to
match the purpose of the content. Plus it's integrated with the rest of Liferay
Portal: for example, when you upload an image to be added to a page, that image
can be viewed and manipulated in the Documents and Media portlet.

If you're HTML savvy, Liferay WCM doesn't leave you out in the cold. You can
switch from the WYSIWYG view by clicking the *Source* button. From the Source
view, you can view the HTML content of your web content. If you wish, can edit
the HTML directly.

![Figure 2.17: If you've installed and enabled Xuggler from the *Server Administration* &rarr; *External Tools* section of the Control Panel, you can add audio and video to your web content!](../../images/web-content-video.png)

You can integrate Liferay with external services to enable additional
functionality. For example, if you navigate to the Control Panel, click on
*Server Administration* and then on *External Services*, you can install and
enable Xuggler. Enabling Xuggler allows you to embed audio and video files in
web content. Installing and enabling Xuggler is easy; you can do it right from
the Control Panel. Please refer to [Server Administration](/discover/portal/-/knowledge_base/6-2/server-administration)
article of this guide for more details.

Once Xuggler has been installed and enabled, embedding audio or video files in a
web content is easy. From the Dockbar, navigate to *Site Content* &rarr; *Web
Content* and click *Add* &rarr; *Basic Web Content*. Look for the buttons on the
CKEditor toolbar with audio and video icons. Click on either the audio or video
button and then click *Browse Server* to browse to the audio or video file's
location on your portal's documents and media repository. When you find the
appropriate file, click *Choose*. If you haven't already uploaded the audio or
video file to the portal, you can do so by clicking on the *Upload* button.
Select the file and then check that the audio or video component appears in the
web content. Excellent! When your web content is published, users can view or
listen the embedded multimedia!

The right side of the New Web Content form provides options for customizing your
web content.

![Figure 2.18: New web content can be customized in various ways using the menu on the right.](../../images/wcm-menu.png)

**Abstract:** lets you to create a brief summary of the web content. You can
also pair the text with a small image.

**Categorization:** specifies the content type from a list of options. They are
*Announcements*, *Blogs*, *General*, *News*, *Press Release*, and *Test*. You
can also create tags to make the content easier to find in a search. Note that
these categories are defined by a property in the properties file; see the
`journal.article.types` property in the
[Properties Document](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Journal%20Portlet)
for further information. 

+$$$

**Note:** The Web Content Type portlet, located within the Categorization menu,
is deprecated for Liferay 6.2 and will be removed in Liferay 7.0. The portlet
will be migrated to a vocabulary with categories. 

$$$

**Schedule:** customizes the date and time your content publishes and/or
expires.

**Display Page:** lets you determine where the web contents are displayed when
linked from other pages. The Canonical URL can be used here. The Canonical URL
is unique for articles that redirect the visitor to the article's default
display page.

Imagine you have a newspaper with a sports section and a technology section. You
add a Sports page and a Tech page to your site, each one with a specific banner
and look and feel. You want the articles to appear in the appropriate pages, but
you know in Liferay articles are not related to pages. You can add an article as
often as you like in different web content display portlets or in configured
Asset Publishers. But if you have a *View in context* link, where will you show
your article? This is where you'd use a default display page. Articles that have
a default display page defined are shown with other related articles in
the same display page.

Imagine you have 100 sports articles and 100 tech articles. In previous versions
of Liferay you'd need to create a page for each article to show it. Now with
only one sports page and one tech page, you can show all articles in one place
in a consistent fashion.

### Creating a Display Page [](id=creating-a-display-page)

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

**Related Assets:** enables you to connect any number of assets within a site or
across the portal, even if they don't share any tags and aren't in the same
category. You can connect your content to a Blogs Entry, Message Boards Message,
Web Content, Calendar Event, Bookmarks Entry, Documents and Media Document, and
a Wiki Page.

![Figure 2.19: This blog entry has links to three Related Assets: one web content and two message board entries.](../../images/related-assets-link.png)

You'll learn how to publish links to related assets using the Related Assets
portlet in the
[Defining Content Relationships](/discover/portal/-/knowledge_base/6-2/defining-content-relationships)
section.

**Permissions:** customize who has access to the content. By default, content is
viewable by Anyone (Guest Role). You can limit viewable permissions by selecting
any Role from the drop-down or in the list. Additionally, Liferay Portal
provides the ability to customize permissions in more detail. Select the *More
Options* link next to the drop down button and you'll find the different
activities you can grant or deny to your web content.

**Custom fields:** customize metadata about the web content. The fields can
represent anything you like, such as the web content's author or creation date.
If custom fields have been defined for web content (which can be done from the
*Custom Fields* page of the Control Panel), they appear here.

For more information on Custom Fields see the
[Custom Fields](/discover/portal/-/knowledge_base/6-2/custom-fields) section.

For this piece of web content, we don't need to change anything. After you're
finished with permissions, click *Save as Draft*. This saves the content in
draft form. Once you're satisfied with your changes, select *Publish*. This
makes the content available for display, but we still have some work to do to
enable users to see it. In Liferay WCM, all content resides in a container,
which is one of two portlets: Web Content Display or Web Content List. By far
the most frequently used is the *Web Content Display* portlet. Let's look at how
it works.

## Publishing (Basic) Content [](id=publishing-basic-content)

Now that we've created and published our first piece of web content for
the Lunar Resort, it's time to display it. First, add the *Web Content Display*
portlet to our Welcome page by selecting the *Add* button from the left palette
and selecting the *Applications* tab.

![Figure 2.20: Adding the Web Content Display Portlet](../../images/add-web-content-display.png)

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
translation of your content that goes with your locale.

![Figure 2.21: Publishing web content is a snap. At a minimum, you only have to select the content you wish to publish. You can also enable lots of optional features to let your users interact with your content.](../../images/04-web-content-choosing-web-content.png)

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
content is. You'll use the same exact tools. 

## Editing Content [](id=editing-content)

Once the content is displayed--whether you've selected content or created it in
the Web Content Display portlet--you can edit the content directly from the Web
Content Display portlet or from the Control Panel. To edit it from the Web
Content Display portlet, select the *Edit* button to the lower left of the
portlet. This launches the WYSIWYG editor and from there you can make any
necessary changes.

![Figure 2.22: The *Edit*, *Select Web Content*, and *Add* buttons appear when hovering over their icons.](../../images/web-content-display-icons.png)

When you publish updates to a web content that's already being displayed
somewhere on your portal (e.g., in a Web Content Display portlet or an Asset
Publisher portlet), the content is immediately updated (unless, of course, you
have a workflow enabled, which we'll discuss below). It makes no difference
whether you edit it from a Web Content Display portlet, from the Asset
Publisher, or from the Site Administration interface.

Note: if you want to view your page the way your users will see it (i.e.,
without all those portlet controls and icons), go up to the left palette and
select the *Edit Controls* icon. This makes all those extra controls you see as
a portal administrator disappear. You'll also notice the green "eye" transforms
into an orange "eye". If you need to use those controls again, just select *Edit
Controls* to return to the original format.

That's pretty much all there is to simple content creation. Whole sites have
been created this way. But if you want to take advantage of the full power of
Liferay's WCM, you'll want to use structures and templates found in the
[Advanced Web Content Management](/discover/portal/-/knowledge_base/6-2/advanced-web-content-management)
chapter. Next, let's see how you can manage your content with an approval
process called workflow.
