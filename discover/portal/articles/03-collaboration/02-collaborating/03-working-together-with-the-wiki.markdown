# Working Together with the Wiki

Liferay's Wiki, like the [Message Boards](/discover/portal/-/knowledge_base/7-0/creating-forums-with-message-boards),
is a full-featured [wiki](/participate/liferaypedia/-/wiki/Main/Wiki)
application which has all of the features you would expect in a state of the art
wiki. Again, though, it has the benefit of being able to take advantage of all
of the features of the Liferay platform. As such, it is completely integrated
with Liferay's [user management](/discover/portal/-/knowledge_base/7-0/user-management),
[tagging](/discover/portal/-/knowledge_base/7-0/organizing-content-with-tags-and-categories),
and [security features](/discover/deployment/-/knowledge_base/7-0/liferay-portal-security).

So, what is a wiki? Basically, a wiki is an application which allows users to
collaboratively build a repository of information. There are, of course, many
implementations of this idea, the most famous of which is Wikipedia. Wikipedia
is a full online encyclopedia developed collaboratively by users from all over
the world, using a wiki. Another example would be
[TripAdvisor](http://www.tripadvisor.com/), which tourists use to rate travel
destinations and accommodations, and help other tourists plan fantastic trips.

A wiki application allows users to create and edit online documents and link them to
each other. To accomplish this, a special form of markup is used which is
sometimes called wikitext. Unfortunately, the proliferation of many different
wiki applications resulted in slightly different syntax for wikitext in the
various products, as each new wiki tried to focus on new features that other
wikis did not have. For that reason, a project called [WikiCreole](http://www.wikicreole.org/) was started.
This project resulted in the release of WikiCreole 1.0 in 2007, which is an
attempt to define a standard wiki markup that all wikis can support.

Rather than define another wikitext syntax, Liferay's Wiki app supports
WikiCreole as its syntax. This syntax is a best-of-breed wiki syntax and should
be familiar to users of other wikis. The app provides a handy cheat sheet for
the syntax on the page editing form, with a link to the full documentation if
you wish to use some of WikiCreole's advanced features. 

An instance of the Wiki application can be created for each scope: the entire
Liferay virtual instance (global scope), a site, or a page. A page-scoped Wiki
application instance must be created from the page, via a Wiki application or
Wiki Display application's configuration screen--we'll cover this later. For
now, let's get started using your site's Wiki application instance. 

## Getting Started with Wikis

Site Administration is the best place to get started working with your wikis. To
start working with wikis for your site, page, or global scope, click on the
*Product Menu* icon (![Menu](../../../images/icon-menu.png)), navigate to the
*Content* section for your site, page, or global scope via the scope menu
(![Scope](../../../images/icon-compass.png)), and click on *Wiki*. The Wiki
administration screen appears. This screen allows you to add, modify, and delete
wikis. A Wiki application instance (wiki instance) can contain many wikis (wiki
nodes). By default, it contains one node, called *Main*.

![Figure x: The Wiki application instance has a wiki node named *Main* with a single front page. You can build on the Main node or create a new node.](../../../images/wiki-admin-empty.png)

Before we start adding to our site's wiki instance, let's configure it the way
we want it. The instance's interfaces for permissions, export and import,
configuration, and application templates are accessible from the Options menu.
To view the menu, click on the *Options* icon
(![Options](../../../images/icon-options.png)).

Here are the options screens accessible for a Wiki application instance:

**Permissions**: Allows you to specify which roles can create wiki nodes (wikis)
and which roles can access the Permissions screen. 

**Export / Import**: Enables you to import existing wiki content into your wiki
instance and export wiki content to a file.

**Configuration**: This screen has tabs for configuring email notifications and
a tab for configuring RSS feeds. The *Email From*, *Page Added Email*, and *Page
Updated Email* tabs are similar to the ones for notification email settings for
other apps, allowing you to customize who wiki emails come from and the format
and text of the email that is sent when a page is added or updated. The *RSS*
tab allows you to configure RSS feeds, as you can do for other Liferay
applications. 

**App Templates**: Lets you store your current application setup or apply an
existing archived setup. This is especially helpful when you have configurations
you like to use multiple application instances.

From the *Options* menu, select *Permissions*. If you've created a specific role
for creating wiki nodes, you can select the check box in the *Add Node* column
and then click *Save*, and that role will be able to create new wiki nodes in
this wiki instance. 

![Figure x: You can configure email messages and RSS feeds for users to stay informed with your wiki instance.](../../../images/wiki-admin-configure-email.png)

Now that we're done configuring our site's Wiki app instance, it's time to start
working with a wiki node (or wiki). Let's create a wiki node called *Space Wiki*
in which everyone at the Lunar Resort can participate in building an
encyclopedia of sorts on everything they know about space. 

Click the *Add* icon (![Add](../../../images/icon-add.png)) to start creating
the new wiki node. The New Wiki Node screen appears and
prompts you to enter a name and description for the new wiki node. You can also set up some
default permissions. Click *Save* when you're done specifying your new wiki
node. 

![Figure x: The New Wiki Node screen lets you describe your new node and set view permissions and permissions for Guest and Site Member roles.](../../../images/wiki-new-wiki-node.png)


When you create a new wiki node, it appears in a list at
the top of the main page of the Wiki app.

<!-- Decide where to mention the wiki options listed below -->

Next to each wiki in the list of wikis is an *Options* icon [Options](../../../images/icon-app-options.png).
Here are the wiki node options:

**Edit**: lets you edit the wiki's name and description.

**Permissions**: lets you specify which roles can add attachments to wiki pages,
add pages to the wiki, delete pages, import pages to the wiki, set permissions
on the wiki, subscribe to wiki modifications, update existing pages, and view
the wiki.

**Import Pages**: allows you to import data from other wikis. This lets you
migrate off of another wiki which you may be using and use the Liferay wiki
instead. You might wish to do this if you are migrating your site from a set of
disparate applications (i.e. a separate forum, a separate wiki, a separate
content management system) to Liferay, which provides all of these features.
Currently, MediaWiki is the only wiki that is supported, but others are likely
to be supported in the future.

**RSS**: opens a new page where you can subscribe to an RSS feed using Live
Bookmarks, Yahoo, Microsoft Outlook, or an application you can choose from your
machine.

**Subscribe**: allows you to subscribe to a wiki node; any time a wiki page is
added or updated Liferay will send you an email informing you what happened.

**View Removed Attachments**: displays attachments that have been removed from
the wiki node.

**Move to the Recycle Bin**: moves the wiki node to the [Recycle Bin](/discover/portal/-/knowledge_base/7-0/restoring-deleted-assets).

![Figure x: The Wiki application lists all of its wiki nodes. Each node's options icon menu lists node configuration options and actions you can perform. ](../../../images/wiki-admin-wikis-and-wiki-options.png)

Before you open up wiki nodes to contributors, you should consider whether to
associate a workflow with them. For example, you could create a workflow that
requires a wiki page modification (add, update, or delete) be approved by an
administrator before it's published. You can access your site's default *Wiki
Page* workflow from within the Product Menu, by navigating to *Configuration
&rarr; Workflow Configuration* for your site. To learn how to use workflow,
refer to [Using Workflow](/discover/portal/-/knowledge_base/7-0/using-workflow).

Next, let's create our wiki node's front page. 

<!--

To view the Wiki application's configuration options, select *Options &rarr;
Configuration*. The Wiki app's Configuration screen appears. 

The *Email From*, *Page Added Email*, and *Page Updated Email* tabs are similar
to the ones for notification email settings for other apps, allowing you to
customize who wiki emails come from and the format and text of the email that
is sent when a page is added or updated.

Finally, the Wiki portlet also supports RSS feeds as the other collaboration
apps do, and you can configure its options in the *RSS* tab.

The *Setup* tab lets you choose wikis to display and gives you several options
for displaying them. The *Enable Related Assets*, *Enable Page Ratings*, *Enable
Comments*, and *Enable Comment Ratings* check boxes let you enable or disable
those features for the Wiki app. They give you the ability to set how you want
users to interact with wiki documents: a little, a lot, or not at all. The
*Display Template* drop-down option lets you choose the application display
template for the app. Below this, you can set which wikis (wiki nodes) are
visible in the Wiki and which are hidden. You might host two wikis on a given
site, exposing one to the public and keeping the other private for site members.

-->

## Adding and Editing Wiki Pages [](id=adding-and-editing-wiki-pages)

When you initially create a wiki node, it has no pages. It's not until you
navigate into that node that a default page called *FrontPage* is created
automatically. To view the page, click on its name. The page shows a default
message explaining that it's empty and that you need to edit to to add content.
Click on the message text (which is a link) to start editing the page.

![Figure x: Each empty wiki page presents a default message link you can click to edit the page.](../../../images/wiki-empty-frontpage.png)

Wiki uses a WYSIWYG editor, similar to the editor used in the Blogs and Web
Content applications. You'll write your wiki page text in the content section under
the wiki page title. The content section entices you with its default message
*Write your content here...*. Click there and enter some text.

Next, highlight your content text. Formatting options appear. They let you
markup the text as bold or italics. You can add ordered or unordered lists and
even link the text to a URL. You're working in the editor's regular mode. Source
mode, on the other hand, lets you enter text in Creole format. 

![Figure x: When you highlight text in the wiki page editor, a toolbar of formatting options appears. You can apply common markups to the text, make it a list item or a link, or remove existing markup.](../../../images/wiki-page-highlight-content-text.png)

You can switch to source mode by clicking the *Source* icon (`</>`) or switch
back to regular mode by clicking the *Roller* icon
(![Roller](../../../images/icon-roller.png))--the editor always displays the
other mode's icon so you can switch between modes. Click on the *Source* icon to
switch to source mode.

Notice that there's a convenient *Show Syntax Help >>* link which can help with
the wiki syntax. You can use this syntax to format your wiki pages.

Consider, for example, the following wiki document written in Creole format:

    Space--it's our //final frontier//! But we've learned so much about it, from our perspective at the Lunar Resort. 

    Let's share what we know about galaxies, solar systems, planets, moons, suns, and more!

    [[Galaxies]]

    [[Solar Systems]]

    [[Planets]]

    [[Moons]]

    [[All kinds of stuff]]

Enter it as content. Now, click on the *Enlarge* icon
(![Enlarge](../../../images/icon-enlarge.png)) to edit the source in an enlarged
dual screen editor. As you enter Creole format text in one pane the preview pane
renders it. The editor lets you hide the preview pane, use a dark and
light color scheme in the source pane, and arrange the panes horizontally or
vertically. 

![Figure x: The wiki page source editor has a dual page mode for rendering content as you edit its source text.](../../../images/wiki-dual-pane-editor.png)

At the bottom of the page editing screen, you can select *Categorization* to add
tags. The tags link your wiki to categories. You can create categories from the
Site Administration page, in the *Content &rarr; Categories* section. Categories
are hierarchical lists of headings under which you can create wiki pages. This
allows you to organize your content in a more formal fashion.

In the edit screen's *Configuration* section, you can set the page to use
Creole wiki format, MediaWiki format, HTML, or plain text. We recommend
that you stick with the Creole format, as it allows for a much cleaner
separation of content and code. 

Similar to other Liferay applications, in the *Related Assets* section of the
editor, you can select other assets to associate with the wiki page. And from
the *Permissions* section, you can set general permissions for accessing and
acting on the page.

*Publish* your page when you're done editing it. The next time you edit the
page, the edit screen provides you with an *Attachments* section for attaching
files to the page, via drag and drop or file upload.

When you're done editing in source view, click *Done*. When you're done editing
the page, click *Publish*.

We've produced the wiki page shown in the figure below.

![Figure x: The source editor and syntax guide, help you format wiki page headings and text.](../../../images/wiki-page.png)

Did you notice that we added links for pages named *Galaxies*, *Solar Systems*,
etc, that we haven't yet created? This is a common practice in building
wikis--you add a link so that someone (maybe you) will then create a page for
it. The terrific thing is that you can create that new page for the link by
clicking it. When you click a link to a non-existent page, the new wiki page
screen appears for you to create the page. This editing screen looks just like
the one you used previously when you wrote the front page. Liferay displays a
notice at the top of the page stating that the page does not exist yet, and that
you are creating it right now. As you can see, it is very easy to create wiki
pages. All you have to do is create a link from an existing page.

+$$$

**Note**: when you create a page by clicking on a link to a page that does not
yet exist, the new page is **not** a child of the current page. The page is
created at the front page level of the wiki node. From Wiki in Site
Administration, you can use the page's Move action to assign it a new parent
page. Clicking on the *Move* action brings up a window that lets you select a
new parent page. 

$$$

On exiting the wiki page editor, your brought back to the node's view. It shows
all of the node's top-level pages. In our case, *FrontPage* is our only
top-level page. If you navigate to a page that has child pages, its child pages
are listed. In these page listings, each wiki page's Options icon (![Options](../../../images/icon-actions.png)
lists the following options.

**Edit**: Opens the wiki page in the wiki page editor.

**Permissions**: Allows you to determine which roles can view, update, delete,
subscribe to, or set permissions on the page, and add, update, or delete page
discussions (comments).

**Copy**: Brings up a wiki page editor window with all the content from the
source wiki page. You're prompted to specify a new title for it.

**Move**: Opens a dialog that enables you to assign the wiki page a new parent
within the wiki node.

**Subscribe (or Unsubscribe)**: Subscribes to (or unsubscribes from)
notifications of modifications to the wiki page.

**Move to the Recycle Bin**: Deletes the wiki page from the wiki and moves it to
the Recycle Bin.

<!-- This is enough on Wiki in site admin. Time to go on site pages. -->

<!-- These should go in application section? -->

The Edit, Details, and Print icons show above each page's content. Clicking the Edit icon
brings up the edit screen. Clicking the Print icon brings up the browser's page
printing window. Between these two options is the Details option, which we'll
explore next.

## Using the Wiki Applications on Site Pages

The Wiki application works just like the other Liferay apps. Open the Add menu
by clicking the *Add* icon (![Add](../../../images/icon-control-menu-add.png)).
In the menu, navigate to *Applications &rarr; Wiki* and then click on *Wiki
Add*. To configuration the Wiki app, click on its *Options* icon
(![Options](../../../images/icon-app-options.png)) and select *Configuration*.
It displays options you're likely to be familiar with such as the tab for sharing
the application with websites, Facebook, Google Gadgets, etc. You will also
notice that the communication tab has some additional options not seen in the
other portlets.

Figure x: For each of the public parameters in this app, it is possible to ignore the values coming from other portlets or to read the value from another parameter.

The communication tab of the configuration window allows you to configure
communication across portlets, using predefined public render parameters. From
here you can modify six public render parameters: categoryId, nodeId, nodeName,
resetCur, tag, and title. For each parameter you can:

-   Ignore the values for this parameter that come from other portlets. For
    example, the wiki app can be used along with the tags navigation
app. When a user clicks on a tag in the tags navigation app, the wiki
shows a list of pages with that tag. In some cases an administrator may want
the wiki app to always show the front page independently of any tag
navigation done through other portlets. This can be achieved by checking the
Ignore check box so that the values of the parameter coming from those other
portlets are ignored.

-   Read the value of a parameter from another app. This is an advanced but
    very powerful option that allows portlets to communicate without
configuring it beforehand. For example, imagine that the wiki app is used
to publish information about certain countries. Imagine further that a custom
app that allows browsing countries for administrative reasons was written
and placed on the same page. We could associate to this second app a public
render parameter called *country* to designate the name of the country. Using
this procedure, we can cause the wiki to show the information from the country
being browsed through in the other app. You can do this here for the wiki
by setting the value for the title parameter to be read from the country
parameter of the other app.

Once you set the options the way you want them, click *Save*.

## Page Details

When viewing a page, you can view its details by clicking the *Details* icon
above the page content. Several tabs appear, to give you access to several
categories of information about the page. 

### Details

The Details tab shows various statistics about the page, and also allows you to
perform some actions on the page.

**Title**: displays the page title.

**Format**: displays the format for the page -- either Creole, HTML, 
MediaWiki, or plain text.

**Latest Version**: displays the latest version of the page. The wiki portlet
automatically keeps track of page versions whenever a page has been edited.

**Created By**: identifies the user who created the page.

**Last Changed By**: identifies the user who last modified the page.

**Attachments**: displays the number of attachments to the page.

**RSS Subscription**: displays an icon that opens a new page where you can subscribe to an RSS feed using Live
Bookmarks, Yahoo, Microsoft Outlook, or an application you can choose from your
machine.

**Email Subscription**: contains links allowing you to subscribe to or
unsubscribe from modifications notifications for the page and the entire wiki node. 

**Advanced Actions**: displays icons allowing you to modify the permissions on
the page, make a copy of the page, move (rename) the page, or move the page to
the recycle bin.

### History

The History tab lets you access the page's activities and versions. Its
*Activities* tab lists actions performed on the page. Each activity has shows an
icon that represents the type of action, the name of the user, the actions
description, date, and an options icon to revert the action. For example, if
user Jane Doe attached file `sunset.jpg` to the page, 
then the activity would show an Attachment icon (![Attachment](../../../images/icon-paper-clip.png)), this text
*Jane Doe added the attachment `sunset.jpg`.*, the action date, and an options
icon to remove the attachment. 

The *Versions* tab lists all of the versions of the wiki page since it was
created. You can revert a page back to a previous state by clicking the *Revert* icon (![Revert](../../../images/icon-revert.png)). You can also
compare the differences between versions by selecting two versions and then
clicking the *Compare Versions* button.

### Incoming/Outgoing Links

The next two tabs are list incoming and outgoing links. These are wiki links to
and from the page. You can use this tab to examine how this page links to other
pages and how other pages link back to this page.

### Attachments

The *Attachments* tab lists the name and size of each file attached to the page.
You can attach any file to the wiki. Image files are the most common type of
file attached to a page. Referencing them using the proper WikiCreole syntax
renders the image inline, which is a nice way to include illustrations in your
wiki documents.

## Navigating in the Wiki

Above the wiki's content are links which allow you to navigate around
the wiki. Simply click on the wiki's name to begin browsing that wiki. After
this is a set of navigation links:

**Recent Changes**: takes you to a page which shows all of the recently updated
pages.

**All Pages**: takes you to a flat, alphabetical list of all pages currently
stored in the wiki.

**Orphan Pages**: takes you to a list of pages that have no links to them. This
can happen if you take a page link out of a wiki page in an edit without realizing
it's the only link to that page. This area allows you to review wiki pages
that are orphaned in this way so that you can re-link to them or delete them
from the wiki if they are no longer relevant.

**Draft Pages**: takes you to a list of pages which have not yet been
published. Users can edit pages and save their changes as drafts. They can come
back later to finish their page changes and publish them once they've been
approved.

**Search**: allows you to a search for pages matching a term. If no matches
are found for the term, a link will be 
displayed which allows you to create a new wiki page named after that term.

![Figure x: If a wiki doesn't have a page matching the topic you're looking for, it gives you the option to create a page named for the topic.](../../../images/wiki-search.png)

The Wiki portlet is another full-featured Liferay application with all of the
features you expect from a state of the art wiki. 


<!--
Wiki Display is for displaying a single wiki (wiki node). 

Tree Menu displays a wiki's hierarchy of pages as a tree. It lets you navigate
all of a wiki's pages. 
-->