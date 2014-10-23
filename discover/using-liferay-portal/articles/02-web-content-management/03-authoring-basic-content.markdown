# Authoring Basic Content [](id=authoring-basic-content)

You've been assigned the task to build a web site for an innovative new social
networking site called Nose-ster. You've decided to take advantage of Liferay
Portal and its rapid deployment features as well as its ability to get a fully
functional, content-rich web site with integrated social features up and running
in little time. Together, we can get you started.

We'll walk through the creation of Nose-ster's web site, starting by creating
and publishing some simple content using Liferay's built-in WYSIWYG editor.
We'll then take advantage of Liferay's robust structure editor. We'll use
templates to display the content and then explore some of the advanced
publishing features such as the built-in workflow and Asset Publisher.

## Creating Content the Simple Way [](id=creating-content-the-simple-way)

As we've stated above, content is the reason web sites exist. Liferay Portal has
made it easier than ever to get content published to your site. Because Liferay
Portal is so flexible, you can use basic authoring tools right away or take
advantage of the more advanced features. It's adaptable to your needs.

We'll begin by creating simple content using Liferay's WYSIWYG Editor and then
we'll publish it to the home page of Nose-ster's web site. This is a fast and
straightforward process that demonstrates how easy it is to create and publish
content on your Liferay Portal instance. Let's learn about the Web Content
section of the control panel so we can create and publish our first pieces of
content.

![Figure 2.12: Choosing a Site in the Content
Section](../../images/04-web-content-context-dropdown.png)

When you manage web content from the Control Panel you can select the location
where the content resides. For instance, you can add content that's available to
a specific site or globally across the portal. The Content section of the
Control Panel displays as its heading the name of the site you're currently
working on. This heading is called the *context menu selector*: you can change
the scope of where you'd like to view, edit or create content by using the
drop-down selector attached to the heading. 

## Rich, WYSIWYG Editing [](id=rich-wysiwyg-editing)

Once you have the Nose-ster site selected, click on the *Web Content* link in
the Control Panel. Next, click the *Add* button under the *Web Content* tab.
This is a highly customizable form that by default has two fields: a title and a
powerful WYSIWYG editor. We could customize this form to contain whatever fields
our content needs but let's keep things simple for now. We'll cover more
advanced features such as structures, templates and content scheduling later in
this chapter.

For now, type the words *Welcome to Nose-ster* in the *Name* field. Notice that
content can be localized in whatever language you want. If you click on the
*localize* checkbox, two select boxes appear which allow you to pick the
language you're working in and the default language. You can enter translations
of your content for any language in the list. The screenshot below shows this
interface but for now, we won't be using it, so you can leave it unchecked. In
the content field, add a short sentence announcing the web site is up and
running.

![Figure 2.13: The Web Content Editor provides many options for
customization.](../../images/04-web-content-wysiwyg.png)

Getting a new web site up and running is an exciting step for anyone, whether it
is a large corporation or a small non-profit charity. To celebrate this
momentous achievement at Nose-ster, let's give our announcement some of the pomp
and circumstance we think it deserves!

Using the editor, select all the text and then change the style to *Heading 1*
and the color to *Dark Green*.

You could insert an image here or even more text with a different style, as
demonstrated in the screenshot below. You can also add bullets, numbering, links
to another site or custom images. You can even add an emoticon. Let's add a
smiley face at the end of our announcement.

![Figure 2.14: View your content changes directly in the
editor.](../../images/04-web-content-example2.png)

The WYSIWYG editor is a flexible tool that gives you the ability to add text,
images, tables, links and more. Additionally, you can modify the display to
match the purpose of the content. Plus it's integrated with the rest of Liferay
Portal: for example, when you upload an image to be added to a page, that image
can be viewed and manipulated in the Documents and Media portlet.

If you're HTML savvy, Liferay WCM doesn't leave you out in the cold. You can
click the *Source* button and write your own HTML if you wish.

On the right of the New Web Content form are options that allow you to
customize your web content. 

![Figure 2.15: New web content can be customized in various ways using the menu
on the right.](../../images/wcm-abstract.png)

**Abstract:** lets you to create a brief summary of the web content. You can
also pair the text with a small image.

**Categorization:** specifies the content type from a list of options. They are
*Announcements*, *Blogs*, *General*, *News*, *Press Release*, and *Test*. You
can also create tags to make the content easier to find in a search. Note that
these categories are defined by a property in the properties file; see the
`journal.article.types` property in chapter 20 for further information. 

**Schedule:** customizes the date and time your content publishes and/or
expires.

**Display Page:** lets you determine where the web contents are displayed when
linked from other pages. The concept of the Canonical URL is new to Liferay 6.1.
The Canonical URL is unique for articles that redirect the visitor to the
article's default display page. 

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
Templates* in the Portal section of the Control Panel.

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

www.nosester.com/nose-article

This is an example of a canonical URL, and it's a nice enhancement for Search
Engine Optimization (SEO) because the article's URL becomes the page URL. To a
search engine that's crawling your site, this means that the location of your
article never changes. And if you decide to use the content on another page in
the future, the article is still available at this URL. This feature is used in
search results, in related assets and in Asset Publishers. For more information
on Liferay's Display Pages, see chapter 5.

**Related Assets:** enables you to connect any number of assets within a site or
across the portal, even if they don't share any tags and aren't in the same
category. You can connect your content to a Blogs Entry, Message Boards Message,
Web Content, Calendar Event, Bookmarks Entry, Documents and Media Document, and
a Wiki Page.

![Figure 2.16: This blog entry has links to three Related Assets: one web
content display and two blog entries.](../../images/related-assets-link.png)

You'll learn how to publish links to related assets using the Related Assets
portlet in the *Defining content relationships* section of chapter 5.

**Permissions:** customize who has access to the content. By default, content is
viewable by Anyone (Guest Role). You can limit viewable permissions by selecting
any Role from the drop-down or in the list. Additionally, Liferay Portal
provides the ability to customize permissions in more detail. Select the *More
Options* link next to the drop down button and you'll find the different
activities you can grant or deny to your web content.

![Figure 2.17: Permissions for Web Content allow you to fine-tune how your
content is accessed.](../../images/04-web-content-content-permissions.png)

**Custom fields:** customize metadata about the web content. The fields can
represent anything you like, such as the web content's author or creation date.
If custom fields have been defined for web content (which can be done from the
*Custom Fields* page of the Control Panel), they appear here.

For more information on Custom Fields see the Custom Fields section in chapter
16.

For this piece of web content, we don't need to change anything. After you're
finished with permissions, click *Save as Draft*. This saves the content in
draft form. Once you're satisfied with your changes, select *Publish*. This
makes the content available for display, but we still have some work to do to
enable users to see it. In Liferay WCM, all content resides in a container,
which is one of two portlets: Web Content Display or Web Content List. By far
the most frequently used is the *Web Content Display* portlet. Let's look at how
it works.
