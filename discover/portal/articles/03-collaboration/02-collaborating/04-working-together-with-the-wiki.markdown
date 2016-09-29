# Working Together with the Wiki [](id=working-together-with-the-wiki)

@product@'s Wiki is a full-featured [wiki](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Wiki)
application which has all the features you would expect in a state of the art
wiki. Again, though, it has the benefit of being able to take advantage of all
the features of @product@. As such, it is completely integrated
with @product@'s [user management](/discover/portal/-/knowledge_base/7-0/user-management),
[tagging](/discover/portal/-/knowledge_base/7-0/organizing-content-with-tags-and-categories),
and [security features](/discover/deployment/-/knowledge_base/7-0/liferay-portal-security).

So what is a wiki? A wiki is an application that allows users to
collaboratively build a repository of information. There are, of course, many
implementations of this idea, the most famous of which is Wikipedia. Wikipedia
is a full online encyclopedia developed collaboratively by users from all over
the world, using a wiki. 

A wiki application allows users to create documents and link them to each other.
To accomplish this, a special form of markup called wikitext is used.
Unfortunately, the proliferation of many different wiki applications resulted in
slightly different syntax for wikitext, as each new wiki tried to focus on new
features that other wikis did not have. For that reason, a project called
[WikiCreole](http://www.wikicreole.org/) was started, which is an attempt to
define a standard wiki markup that all wikis can support.

Rather than define another wikitext syntax, Liferay's Wiki app supports
WikiCreole as its syntax. This syntax is a best-of-breed wiki syntax and should
be familiar to users of other wikis. The app provides a handy cheat sheet for
the syntax on the page editing form with a link to the full documentation if
you wish to use some of WikiCreole's advanced features.

An instance of the Wiki application can be created for each scope: the entire
@product@ virtual instance (global scope), a site, or a page. It's time to see how
to use your site's Wiki application instance. 

## Getting Started with Wikis [](id=getting-started-with-wikis)

The menu provides the best place to start working with your wikis. To start
working with wikis for your site, click on the *Menu* icon
(![Menu](../../../images/icon-menu.png)), navigate to your site, and select the
*Content* section. If you are updating an existing page-scoped wiki application
instance you can select that page scope from the scope menu that the Gear icon
(![Gear](../../../images/icon-control-menu-gear.png)) makes available. The
site's wiki application instance is available in the Default Scope. Once you're
in the proper content scope click on *Wiki*. The Wiki administration screen
appears. This screen allows you to add, modify, and delete wiki nodes. A Wiki
application instance can contain many wiki nodes. By default, it contains one
node, called *Main*.

![Figure 1: The Wiki application instance has a wiki node named *Main* with a single front page. You can build on the Main node or click the Add icon to create a new node.](../../../images/wiki-admin-empty.png)

Before you start adding to your wiki instance, you should configure it. The
instance's interfaces for permissions, export and import, configuration, and
application templates are accessible from the Options menu. Click the
*Options* icon (![Options](../../../images/icon-options.png)) to open the menu.

Here are the wiki application instance options screens:

**Wikis Permissions**: allows you to specify which roles [roles](/discover/portal/-/knowledge_base/7-0/roles-and-permissions)
can create wiki nodes and which roles can access the Wikis Permissions screen.

**Export / Import**: enables you to import existing wiki content into your wiki
application instance or export wiki content to a file. Refer to
[Importing/Exporting Pages and Content](/discover/portal/-/knowledge_base/7-0/importing-exporting-pages-and-content)
for details.

**Configuration**: has tabs for configuring email notifications and a tab for
configuring RSS feeds. The *Email From*, *Page Added Email*, and *Page Updated
Email* tabs are similar to other app's notification email settings tabs; they
let you customize who wiki emails come from and the format and text of the email
sent when a page is added or updated. The *RSS* tab allows you to configure RSS
feeds.

**Permissions**: allows you to specify which roles can view the wiki application
instance's options menu, access the menu's Configuration and Permissions
options, and access any custom preference options added to the wiki application.

Select the *Wikis Permissions* option to open the Permissions screen. If you've
created a specific role for creating wiki nodes and want to enable that role to
create new wiki nodes in this wiki application instance, select the role's check
box in the *Add Node* column and then click *Save*. 

Now that we're done configuring our site's Wiki app instance, it's time to start
working with a wiki node. Let's create a wiki node that everyone at the Lunar
Resort can use to help build an encyclopedia on everything they know about
space. We'll call it *Space Wiki*.

Click the *Add* icon (![Add](../../../images/icon-add.png)) to start creating
the new wiki node. The New Wiki Node screen appears and prompts you to name and
describe the wiki node. From this screen, you can also set up some default
permissions. Click *Save* when you're done creating the wiki node. 

![Figure 2: The New Wiki Node screen lets you describe your new node, set view permissions, and set permissions for Guest and Site Member roles.](../../../images/wiki-new-wiki-node.png)

On creating the new wiki node, you're brought back to the Wiki instance view.
Your new wiki node accompanies the Main node in the list of this wiki instance's
nodes. Next to each listed wiki node is an *Options* icon
(![Options](../../../images/icon-app-options.png)).

Here are the wiki node options:

**Edit**: lets you edit the wiki's name and description.

**Permissions**: lets you specify which roles can add attachments to wiki pages,
add pages, delete pages, import pages, set permissions on the wiki node,
subscribe to modifications, update existing pages, and view the wiki node.

**Import Pages**: allows you to import data from other wikis. This lets you
migrate off of another wiki application which you may be using and use the
@product@ wiki instead. You might wish to do this if you are migrating your
site from a set of disparate applications (i.e., a separate forum, a separate
wiki, a separate content management system) to @product@, which provides
all of these features. Currently, MediaWiki is the only wiki that is supported,
but others are likely to be supported in the future.

**RSS**: opens a new page where you can subscribe to an RSS feed using Live
Bookmarks, Yahoo, Microsoft Outlook, or an application you can choose from your
machine.

**Subscribe**: allows you to subscribe to a wiki node; any time a wiki page is
added or updated, your @product@ instance sends you an email informing you
what happened. 

**View Removed Attachments**: displays attachments that have been removed from
the wiki node.

**Move to the Recycle Bin**: moves the wiki node to the [Recycle Bin](/discover/portal/-/knowledge_base/7-0/restoring-deleted-assets).

The figure below shows the wiki node options menu.

![Figure 3: The Wiki application lists all of its wiki nodes. Each node's options icon menu lists node configuration screens and node actions you can perform. ](../../../images/wiki-admin-wikis-and-wiki-options.png)

Before you open up wiki nodes to contributors, you should consider whether to
associate a workflow with them. For example, you could create a workflow that
requires an administrator's approval to publish a wiki page modification (add,
update, or delete). You can access your site's default *Wiki Page* workflow from
within the Product Menu, by navigating to *Configuration &rarr; Workflow
Configuration* for your site. To learn how to use workflow, refer to [Using Workflow](/discover/portal/-/knowledge_base/7-0/using-workflow).

Next, let's create our wiki node's front page. 

## Adding and Editing Wiki Pages [](id=adding-and-editing-wiki-pages)

When you create a wiki node, it has no pages. It's not until you navigate into
that node that a default page called *FrontPage* is created automatically. To
view the page, click on the wiki node's name. The FrontPage appears and shows a
default message that explains the page is empty and needs you to add content.
The message is a link; cick on it to start editing the page.

![Figure 4: Each empty wiki page presents a default message link you can click to edit the page.](../../../images/wiki-empty-frontpage.png)

Wiki uses a WYSIWYG editor, similar to the one used in [Blogs](/discover/portal/-/knowledge_base/7-0/publishing-blogs).
The content section's default message *Write your content here...* entices you
to write. Click there and start entering text. 

Next, highlight your pare of your text. Text formatting options appear. They let
you markup the text as bold or italics, add it to an ordered or unordered list,
or make it a link. You can also use keyboard shortcuts Ctrl+b for bold, Ctrl+i
for italics, and Ctrl+u for underline. To remove all formatting, click the
*Remove Format* icon (![Remove 
Format](../../../images/icon-remove-formatting.png)). To insert an image, table,
or horizontal line, you can click the `+` icon to bring up the insert content
menu (![Insert](../../../images/icon-content-insert-controls.png)).

![Figure 5: When you highlight text in the wiki page editor, a formatting toolbar appears. You can apply common markups to the text, make it a list item or a link, or remove existing markup.](../../../images/wiki-page-highlight-content-text.png)

If you need to modify an image for your wiki, use the [Image Editor](/discover/portal/-/knowledge_base/7-0/publishing-files#editing-images).
Click the `+` icon and select the mountain silhouette to add an image. Select an
existing image from the Documents and Media repository, and click the pencil
icon (![Pencil](../../../images/icon-edit-pencil.png)) in the bottom right
corner of the preview window, to open the Image Editor. 

![Figure 6: You can use the Image Editor to edit photos for your wiki.](../../../images/image-editor-preview-window.png)

Any edits you make are automatically applied to a copy of the image, which you
can then use in your wiki.

You're working in the editor's regular mode. Source mode, on the other hand,
lets you enter text in Creole format. You can switch to source mode by clicking
the *Source* icon (`</>`) or switch back to regular mode by clicking the
*Roller* icon (![Roller](../../../images/icon-roller.png))--the editor always
displays the other mode's icon so you can switch between modes. Click on the
*Source* icon to switch to source mode.

Notice that there's a convenient *Show Syntax Help >>* link to a cheat sheet for
helping you with the wiki syntax. You can use this syntax to format your wiki
page content.

Consider, for example, the following wiki content written in Creole syntax:

    Space--it's our //final frontier//! But we've learned so much about it, from our perspective at the Lunar Resort. 

    Let's share what we know about galaxies, solar systems, planets, moons, suns, and more!

    [[Galaxies]]

    [[Solar Systems]]

    [[Planets]]

    [[Moons]]

    [[All kinds of stuff]]

Enter it as your page content. Then click on the *Enlarge* icon
(![Enlarge](../../../images/icon-enlarge.png)) to edit the source in an enlarged
dual screen editor. As you enter Creole format text in one pane the preview pane
renders it. The editor lets you hide the preview pane, use a dark or light color
source pane scheme, and arrange the panes horizontally or vertically. 

![Figure 7: The wiki page source editor has a dual page mode for rendering content as you edit the source text.](../../../images/wiki-dual-pane-editor.png)

At the bottom of the page editing screen, you can select *Categorization* to add
tags. The tags link your wiki to categories. You can create categories from the
Site Administration page, in the *Content &rarr; Categories* section. Categories
are hierarchical lists of headings under which you can create wiki pages. This
allows you to organize your content in a more formal fashion.

In the edit screen's *Configuration* section, you can set the page to use
Creole wiki format, HTML, or plain text. We recommend that you stick with the 
Creole format, as it allows for a much cleaner separation of content and code.

+$$$

**Note:** The MediaWiki and JSPWiki engines and formats are also available in
respective deprecated apps on the Marketplace.

To install the JSPWiki engine, you must first remove the Creole engine by 
uninstalling the `com.liferay.wiki.engine.creole` bundle from your Liferay 
instance. The JSPWiki format will display in the format selector as *Creole*
since it is an extension of the Creole engine. 

$$$

Similar to other @product@ applications, in the *Related Assets* section of
the editor, you can select other assets to associate with the wiki page. And
from the *Permissions* section, you can set general permissions for accessing
and acting on the page.

*Publish* your page when you're done editing it. The next time you edit the
page, the edit screen provides you with an *Attachments* section for attaching
files to the page, via drag and drop or file upload.

When you're done editing in source view, click *Done*. When you're done editing
the page, click *Publish*. The figure below shows the updated wiki page.

![Figure 8: The Wiki's WYSIWYG editor helps you create attractive wiki pages.](../../../images/wiki-page.png)

Did you notice that we added links for pages named *Galaxies*, *Solar Systems*,
etc, that we haven't yet created? This is a common practice in building
wikis--you add a link so that someone (maybe you) will create a page for it. The
terrific thing is that you can create that new page for the link by clicking it.
When you click a link to a non-existent page, the new wiki page screen appears
for you to create the page. @product@ displays a notice at the top of the
page stating that the page does not exist yet, and that you are creating it
right now. As you can see, it is very easy to create wiki pages. All you have to
do is create a link, click on the link, and create the wiki page.

+$$$

**Note**: when you create a page by clicking on a link to a page that does not
yet exist, the new page is **not** a child of the current page. The page is
created at the wiki node's root. From Wiki in Site Administration, you can use
the page's Move action to assign it a new parent page. Clicking on the *Move*
action brings up a window that lets you select a new parent for the wiki page. 

$$$

On exiting the wiki page editor, you're brought back to the wiki node's view. It shows
all of the node's top-level wiki pages. In our case, *FrontPage* is our only
top-level page. If you navigate to a page that has child pages, its child pages
are listed. In these page listings, each wiki page's Options icon
(![Options](../../../images/icon-actions.png)) lists the following options.

**Edit**: opens the wiki page in the wiki page editor.

**Permissions**: allows you to determine which roles can view, update, delete,
subscribe to, or set permissions on the page, and add, update, or delete page
discussions (comments).

**Copy**: brings up a wiki page editor window with all the content from the
source wiki page. You're prompted to specify a new title for it.

**Move**: opens a dialog that allows you to rename the wiki page or assign the
wiki page a new parent page within the wiki node.

**Subscribe (or Unsubscribe)**: subscribes you to (or unsubscribes you from)
notifications for the wiki page's modifications.

**Move to the Recycle Bin**: deletes the wiki page from the wiki node and moves
it to the Recycle Bin.

Each wiki page has a check box next to it. When you select a page's check
box, an Info icon (![Info](../../../images/icon-information.png)) and Recycle
Bin icon (![Recycle Bin](../../../images/icon-trash.png)) appear in the top
corner above the page list. To move the selected page to the Recycle Bin, click
the Recycle Bin icon. To get additional information about the page, click the
Info icon. The information menu provides a Star icon that you can select to
subscribe to the page's modifications. The menu's Details section displays the
page's Create Date and Last Modified date. 

When you select multiple wiki pages, by selecting individual page check boxes or
the Select All check box in the tool bar, the Info icon and Recycle Bin icon
appear. They give you the wiki node's page count and let you move multiple pages
to the Recycle Bin.

There are several more features in the wiki node view's toolbar. The *Order
by:...* menu allows you to order the wiki pages by title or modification date.
Next to that, the arrows let you arrange the pages in ascending or descending
order. The display style options are next to the Info icon. To list the pages
using a descriptive display style, click the *Descriptive* icon
(![Descriptive](../../../images/icon-descriptive-style.png)). To list the pages
using a list display style, click the *List* icon
(![List](../../../images/icon-list-style.png)). 

![Figure 9: The wiki node's view in site administration has features that help you access and learn information about a wiki node's pages.](../../../images/wiki-node-view-in-admin.png)

As far as the other aspects of the Wiki application interface, we'll cover them
as we use the Wiki application on site pages. Let's go to your site by clicking
*Go to Site* and then close the product menu by clicking its icon
(![Menu](../../../images/icon-menu.png)). We're ready to add Wiki applications
to the site's pages. 

## Using the Wiki Applications on Site Pages [](id=using-the-wiki-applications-on-site-pages)

The Wiki application works just like the other Liferay applications. Open the
Add menu by clicking the *Add* icon
(![Add](../../../images/icon-control-menu-add.png)). In the menu, navigate to
*Applications &rarr; Wiki* and then click on *Wiki Add*.

Your site's wiki nodes display in the application. Click on the *Space Wiki* tab
to view it in the app. 

![Figure 10: Users can interact with your Wiki nodes when you add the Wiki application to a page.](../../../images/wiki-page-full.png)

To view the Wiki application's configuration options, click on its *Options*
icon (![Options](../../../images/icon-app-options.png)) and select
*Configuration*. The Wiki app's Configuration screen appears and has several 
tabs.

**Setup**: lets you choose wikis to display and gives you several options for
displaying them. The *Enable Related Assets*, *Enable Page Ratings*, *Enable
Comments*, and *Enable Comment Ratings* check boxes let you enable or disable
those features for the Wiki app. They give you the ability to set how you want
users to interact with wiki documents: a little, a lot, or not at all. The
*Display Template* drop-down option lets you choose the [Application Display Template](/discover/portal/-/knowledge_base/7-0/styling-apps-with-application-display-templates)
for the app. Below this, you can set which wikis (wiki nodes) are
visible in the Wiki and which are hidden. You might host two wikis on a given
site, exposing one to the public and keeping the other private for site members.

**Permissions**: enables you to grant or revoke privileges for roles to edit the
Wiki application's preferences, configure it, access its permissions, view the
Wiki application's contents, add display templates for it, or add the
application to a page.

**Communication**: allows you to configure communication across portlets, using
predefined public render parameters. From here you can modify six public render
parameters: categoryId, nodeId, nodeName, resetCur, tag, and title. For each
parameter you can:

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

**Sharing**: displays options you're likely to be familiar with such as the tab
for sharing the application with websites, Facebook, and NetVibes.

**Scope**: allows you to select the Wiki application of a particular scope to
display. You can select the site scoped or global scoped instance, or select or
create an instance for the page. If the page doesn't already have an instance
scoped to it, you can click on the *[page name] (Create New)* menu option to
create a page-scoped Wiki application instance.

![Figure 11: Here the user has selected to create a new Wiki application instance scoped to the current page named *My Wiki*](../../../images/wiki-app-configuration-scope.png)

Once you set the application's configuration options the way you want them,
click *Save*.

Along with the Configuration option, the Wiki application's options menu lists
all of the standard application options. The options enable you to specify the
app's [look and feel](/discover/portal/-/knowledge_base/7-0/look-and-feel-configuration),
[export or import app data](/discover/portal/-/knowledge_base/7-0/exporting-importing-app-data),
minimize or maximize the app in the browser, use [Configuration Templates](/discover/portal/-/knowledge_base/7-0/configuration-templates)
to store your current application setup or apply an existing archived setup to
this application instance, or remove the app from the page.

The Wiki application displays links to all of the Wiki application instance's
wiki nodes and provides links for navigating around the wiki. Simply click on a
wiki node's name to begin browsing that node's wiki pages. The following
navigation links are listed after links to the wiki nodes.

**Recent Changes**: takes you to a page which shows all of the recently updated
pages.

**All Pages**: takes you to a flat, alphabetical list of all pages currently
stored in the wiki.

**Orphan Pages**: takes you to a list of pages that have no links to them. This
can happen if you take a page link out of a wiki page in an edit without
realizing it's the only link to that page. This area allows you to review wiki
pages that are orphaned in this way so that you can re-link to them or delete
them from the wiki if they are no longer relevant.

**Draft Pages**: takes you to a list of pages which have not yet been published.
Users can edit pages and save their changes as drafts. They can come back later
to finish their page changes and publish them once they've been approved.

**Search**: allows you to a search for pages matching a term. If no matches are
found for the term, a link displays to enable you to create a new wiki page
named after that term.

![Figure 12: If the wiki node doesn't have a wiki page matching the topic you're looking for, it gives you the option to create a wiki page named for the topic.](../../../images/wiki-search.png)

The current wiki page's content shows in the application's main viewing area.
Several features display below the wiki page content, depending on which
features are enabled for the application. The *+ Add Child Page* link lets you
add a wiki page as a child of the current wiki page. A view counter displays the
wiki page's view count. Ratings and Comments controls display if they're
enabled.

The Edit, Details, and Print icons show above each wiki page's content. Clicking the
Edit icon brings up the edit screen. Clicking the Print icon brings up the
browser's page printing window. Between these two options is the *Details* option,
which we'll explore next. 

## Page Details [](id=page-details)

When viewing a page, you can view its details by clicking the *Details* icon
above the page content. Several tabs appear, to give you access to several
categories of information about the page.

### Details [](id=details)

The Details tab shows various statistics about the page, and also allows you to
perform some actions on the page.

**Title**: displays the page title.

**Format**: displays the format for the page -- either Creole, HTML, MediaWiki,
or plain text.

**Latest Version**: displays the latest version of the page. The wiki portlet
automatically keeps track of page versions whenever a page has been edited.

**Created By**: identifies the user who created the page.

**Last Changed By**: identifies the user who last modified the page.

**Attachments**: displays the number of attachments to the page.

**RSS Subscription**: displays an icon that opens a new page where you can
subscribe to an RSS feed using Live Bookmarks, Yahoo, Microsoft Outlook, or an
application you can choose from your machine.

**Email Subscription**: contains links allowing you to subscribe to or
unsubscribe from modifications notifications for the page and the entire wiki
node.

**Advanced Actions**: displays icons allowing you to modify the permissions on
the page, make a copy of the page, move (rename) the page, or move the page to
the recycle bin.

### History [](id=history)

The History tab lets you access the page's activities and versions. Its
*Activities* tab lists actions performed on the page. Each activity has an
icon that represents the type of action, the name of the user, the actions
description, date, and an options icon to revert the action. For example, if
user Jane Doe attached file `sunset.jpg` to the page, then the activity would
show an Attachment icon (![Attachment](../../../images/icon-paper-clip.png)),
this text *Jane Doe added the attachment `sunset.jpg`.*, the action date, and an
options icon to remove the attachment. 

The *Versions* tab lists all of the versions of the wiki page since it was
created. You can revert a page back to a previous state by clicking the *Revert*
icon (![Revert](../../../images/icon-revert.png)). You can also compare the
differences between versions by selecting two versions and then clicking the
*Compare Versions* button.

### Incoming/Outgoing Links [](id=incoming-outgoing-links)

The next two tabs are list incoming and outgoing links. These are wiki links to
and from the page. You can use this tab to examine how this page links to other
pages and how other pages link back to this page.

### Attachments [](id=attachments)

The *Attachments* tab lists the name and size of each file attached to the page.
You can attach any file to the wiki. Image files are the most common type of
file attached to a page. Referencing them using the proper WikiCreole syntax
renders the image inline, which is a nice way to include illustrations in your
wiki documents.

As you've seen, Liferay's Wiki application is chock-full of features that help
you create, maintain, and consume wiki pages. In addition to the Wiki
application there are several other applications that supplement it. You'll
learn about them next.

## Supplemental Wiki Applications [](id=supplemental-wiki-applications)

The applications that accompany the Wiki application help you display particular
wiki nodes and navigate them. To use them on a page, click the *Add* icon
(![Add](../../../images/icon-control-menu-add.png)), navigate to *Applications
&rarr; Wiki*, and either click *Add* next to the application you want or drag it
onto the page. 

Let's first explore the Wiki Display.

### Wiki Display [](id=wiki-display)

The Wiki Display application enables you to focus user attention on one wiki
node. Click on the app's Options icon
![Options](../../../images/icon-app-options.png) and select *Configuration*. In
the Configuration screen, select the *Setup* tab. Set the *Node* you want to
display and click *Save*; then select a *Page* in that node that you want to
display. This page provides the gateway into the wiki node. 

![Figure 13: The Wiki Display lets users explore a wiki node, starting from one of its pages--the front page, typically.](../../../images/wiki-display-configuration-setup.png)

The configuration options and user interface for the Wiki Display are almost
identical to that of the Wiki application. 

To help users navigate the wikis in a site page's Wiki Display or Wiki
applications, you can use the Tree Menu app. 

### Tree Menu [](id=tree-menu)

The Tree Menu application displays a wiki's page hierarchy as a tree. It lets
you navigate all of a wiki's pages. Much like Wiki Display setup, you configure
the Tree Menu app to focus on a wiki node. In addition, you can configure how
deep to allow users to navigate into the page hierarchy. You can set the *Depth*
to a value from 1 to 5, or select *All* to allow navigation to all of the wiki
node's pages.

In the Tree Menu, folder icons represent parent wiki pages and document icons
represent child wiki pages at the end of the nodes. When you click on a
parent wiki page or child wiki page icon, the Wiki application or Wiki Display
on the site page displays the respective wiki page.

![Figure 14: When you select a element in the Tree Menu application, the respective wiki page shows in your site page's Wiki application or Wiki Display.](../../../images/wiki-tree-menu.png)

There's even a Page Menu application that presents a wiki page's outgoing links. 

### Page Menu [](id=page-menu)

The Page Menu app lets you display a wiki page's outgoing links. It answers the
question, "What wiki pages can I access from this page?" As with the Tree Menu
and Wiki Display applications, you specify a wiki node and wiki page on which
the Page Menu application operates. 

![Figure 15: The Page Menu application displays all of a wiki page's outgoing links to other wiki pages.](../../../images/wiki-page-menu.png)

When you click on a Page Menu link, the site page's Wiki or Wiki Display application
displays the wiki page associated with the link. 

## Summary [](id=summary)

@product@'s Wiki application is another full-featured Liferay application
with all of the features and sub-applications you expect from a state of the art
wiki. It allows you to create pages of information quickly and collaboratively.
The Wiki gives administrators flexibility to reposition wiki pages to their
proper place in a wiki hierarchy. And it allows users to author rich content to
share with others.

Next we'll learn how to inform and alert users with the Alerts and Notifications
applications. 
