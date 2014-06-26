# Working together with the Wiki [](id=working-together-with-the-wiki-lp-6-2-use-useportal)

Liferay's Wiki portlet, like the Message Boards portlet, is a full-featured
wiki application which has all of the features you would expect in a state of
the art wiki. Again, though, it has the benefit of being able to take advantage
of all of the features of the Liferay platform. As such, it is completely
integrated with Liferay's user management, tagging, and security features.

So, what is a wiki? Basically, a wiki is an application which allows users to
collaboratively build a repository of information. There are, of course, many
implementations of this idea, the most famous of which is Wikipedia. Wikipedia
is a full online encyclopedia developed collaboratively by users from all over
the world, using a wiki. Another example would be Liferay's wiki, which is used
for collaborative documentation of the Community Edition of Liferay Portal.

A wiki application allows users to create and edit documents and link them to
each other. To accomplish this, a special form of markup is used which is
sometimes called wikitext. Unfortunately, the proliferation of many different
wiki applications resulted in slightly different syntax for wikitext in the
various products, as each new wiki tried to focus on new features that other
wikis did not have. For that reason, a project called WikiCreole was started.
This project resulted in the release of WikiCreole 1.0 in 2007, which is an
attempt to define a standard wiki markup that all wikis can support.

Rather than define another wikitext syntax, Liferay's Wiki portlet supports
WikiCreole as its syntax. This syntax is a best-of-breed wiki syntax and should
be familiar to users of other wikis. The portlet provides a handy cheat sheet
for the syntax on the page editing form, with a link to the full documentation
if you wish to use some of WikiCreole's advanced features.

## Getting Started with the Liferay Wiki 

The Wiki portlet works just like the other portlets developed by Liferay. Add
the portlet to a page using the *Add &rarr; Applications* menu and then click
*Configuration* in the portlet's *Options* menu in the Wiki portlet's title bar.
You'll see some options are likely to be familiar to you by now such as sharing
the application with websites, Facebook, Google Gadgets, etc. You will also
notice that the communication tab has some additional options not seen in the
other portlets.

![Figure 8.23: For each of the public parameters in this portlet, it is possible to ignore the values coming from other portlets or to read the value from another parameter.](../../images/05-wiki-configuration.png)

The communication tab of the configuration window allows you to configure
communication across portlets, using predefined public render parameters. From
here you can modify six public render parameters: categoryId, nodeId, nodeName,
resetCur, tag, and title. For each parameter you can:

-   Ignore the values for this parameter that come from other portlets. For
    example, the wiki portlet can be used along with the tags navigation
portlet. When a user clicks on a tag in the tags navigation portlet, the wiki
shows a list of pages with that tag. In some cases an administrator may want
the wiki portlet to always show the front page independently of any tag
navigation done through other portlets. This can be achieved by checking the
Ignore check box so that the values of the parameter coming from those other
portlets are ignored.

-   Read the value of a parameter from another portlet. This is an advanced but
    very powerful option that allows portlets to communicate without
configuring it beforehand. For example, imagine that the wiki portlet is used
to publish information about certain countries. Imagine further that a custom
portlet that allows browsing countries for administrative reasons was written
and placed on the same page. We could associate to this second portlet a public
render parameter called *country* to designate the name of the country. Using
this procedure, we can cause the wiki to show the information from the country
being browsed through in the other portlet. You can do this here for the wiki
by setting the value for the title parameter to be read from the country
parameter of the other portlet.

Once you have set the options the way you want them, click *Save*.

## Managing Wikis 

The Wiki portlet can contain many wikis. By default, it contains only one,
called *Main*. To manage Wikis, navigate to your site's *Site Administration
&rarr; Content* page and select *Wiki*. This page allows you to add, modify, and
delete wikis. The Main wiki has already been added for you.

At the top of this screen is a *Permissions* button. Clicking this allows you
to define which roles have access to create wikis. If you have created a
specific role for creating wikis, you can click the box in the *Add Node*
column and then click *Submit*, and that role will have access to create new
wikis in this portlet.

Clicking the *Add Wiki* button prompts you to enter a name and description for
the new wiki. You can also set up some default permissions. When you create a
new wiki, it appears in a list at the top of the main page of the Wiki portlet.

Next to each wiki in the list of wiki nodes is an *Actions* button. This button
contains several options:

**Edit:** lets you edit the name and description of the wiki.

**Permissions:** lets you define what roles can add attachments to wiki pages,
add pages to the wiki, delete pages, import pages to the wiki, set permissions
on the wiki, subscribe to the wiki, update existing pages, and view the wiki.

**Import Pages:** allows you to import data from other wikis. This lets you
migrate off of another wiki which you may be using and use the Liferay wiki
instead. You might wish to do this if you are migrating your site from a set of
disparate applications (i.e. a separate forum, a separate wiki, a separate
content management system) to Liferay, which provides all of these features.
Currently, MediaWiki is the only wiki that is supported, but others are likely
to be supported in the future.

**RSS:** opens a new page where you can subscribe to an RSS feed using Live
Bookmarks, Yahoo, or a chosen application from your machine.

**Subscribe:** allows you to subscribe to a wiki node, and any time a page is
added or updated Liferay will send you an email informing you what happened.

**Move to the Recycle Bin:** moves the wiki node to the recycle bin.

**View Removed Attachments:** displays attachments that have been removed from
the wiki node.

To go back to your wiki, navigate back to the Wiki portlet you added to your
page. Then click the *Options &rarr; Configuration* button, which contains
several other options which you may have seen on other portlets.

The *Display Settings* tab gives you several options for how the wiki should be
displayed. *Enable Related Assets*, *Enable Page Ratings*, *Enable Comments*,
and *Enable Comment Ratings* are similar to the same options in other portlets.
They give you the ability to set how you want users to interact with wiki
documents: a little, a lot, or not at all. The *Display Template* drop-down
option lets you choose the application display template for your portlet. Below
this, you can set which wikis are visible in the Wiki portlet by default and
which are hidden. You might host two wikis on a given site, exposing one to the
public and keeping the other private for site members.

The *Email From*, *Page Added Email*, and *Page Updated Email* tabs are similar
to the ones for notification email settings for other portlets, allowing you to
customize who wiki emails come from and the format and text of the email that
is sent when a page is added or updated.

Finally, the Wiki portlet also supports RSS feeds as the other collaboration
portlets do, and you can configure its options in the *RSS* tab.

## Adding and Editing Wiki Pages 

By default, there is one page added to your wiki, called *FrontPage*. To get
started adding data to your wiki, click the *Edit* link. You will be brought to
a blank editing page.

![Figure 8.24: By clicking *Edit*, you can create/modify your wiki content.](../../images/05-editing-wiki-page.png)

You can now begin to add content to the page. Notice that there is a very
convenient *Show Syntax Help* link which can help with the wiki syntax. You can
use this syntax to format your wiki pages. Consider, for example, the following
wiki document:

== Welcome to Our Wiki! ==

This is our new wiki, which should allow us to collaborate on documentation.
Feel free to add pages showing people how to do stuff.  Below are links to some
sections that have already been added.

[[Introduction]]

[[Getting Started]]

[[Configuration]]

[[Development]]

[[Support]]

[[Community]]

This would produce the following wiki page:

![Figure 8.25: By using the syntax help guide, you can format your wiki headings and text.](../../images/05-wiki-front-page.png)

This adds a simple heading, a paragraph of text, and several links to the page.
Since the pages behind these links have not been created yet, clicking one of
those links takes you to an editing screen to create the page. This editing
screen looks just like the one you used previously when you wrote the front
page. Liferay displays a notice at the top of the page stating that the page
does not exist yet, and that you are creating it right now. As you can see, it
is very easy to create wiki pages. All you have to do is create a link from an
existing page. Note that at the top of the screen you can select from the
Creole wiki format and the HTML editor that comes with Liferay. We recommend
that you stick with the Creole format, as it allows for a much cleaner
separation of content and code. If you want all of your users to use the Creole
format, you can disable the HTML format using the `portal-ext.properties` file.
See chapter 14 for details about how to configure this.

At the bottom of the page editing screen, you can select *Categorization* to add
tags. The tags link your wiki to categories. You can create categories using the
Site Administration page, in the *Content &rarr; Categories* section. Categories
are hierarchical lists of headings under which you can create wiki pages. This
allows you to organize your content in a more formal fashion.

## Page Details 

When viewing a page, you can view its details by clicking the *Details* link
which appears in the top right of the page. This allows you to view many
properties of the page. There are several tabs which organize all of the
details into convenient categories.

### Details 

The Details tab shows various statistics about the page, and also allows you to
perform some actions on the page.

**Title:** displays the title of the page.

**Format:** displays the format for the page -- either Creole, HTML, or
MediaWiki.

**Latest Version:** displays the latest version of the page. The wiki portlet
automatically keeps track of page versions whenever a page has been edited.

**Created By:** displays the user who created the page.

**Last Changed By:** displays the user who last modified the page.

**Attachments:** displays the number of attachments to the page.

**Convert To:** offers different conversion formats for the wiki page: DOC, ODT,
PDF, RTF, SXW, and TXT.

**RSS Subscription:** displays links which allow you to subscribe to the page
as an RSS feed in three formats: RSS 1.0, RSS 2.0, and Atom 1.0.

**Email Subscription:** contains links allowing you to subscribe to the entire
wiki or just to this page.

**Advanced Actions:** contains links allowing you to modify the permissions on
the page, make a copy of the page, move (rename) the page, or move the page to
the recycle bin.

### History 

This tab shows a list of all of the versions of the wiki page since it was
created. You can revert a page back to a previous state and you can also
compare the differences between versions by selecting the versions and then
clicking the *Compare Versions* button.

### Incoming/Outgoing Links 

The next two tabs are for incoming and outgoing links. These are wiki links to
and from the page. You can use this tab to examine how this page links to other
pages and how other pages link back to this page.

### Attachments 

The last tab is for attachments. You can attach any file to the wiki. This is
mostly used to attach images to wiki articles which can then be referenced in
the text. Referencing them using the proper WikiCreole syntax renders the image
inline, which is a nice way to include illustrations in your wiki documents.

## Navigating in the Wiki Portlet 

At the top of the portlet is a list of links which allow you to navigate around
the wiki. Simply click on the wiki's name to begin browsing that wiki. After
this is a set of navigation links:

**Recent Changes:** takes you to a page which shows all of the recently updated
pages.

**All Pages:** takes you to a flat, alphabetical list of all pages currently
stored in the wiki.

**Orphan Pages:** takes you to a list of pages that have no links to them. This
can happen if you take a link out of a wiki page in an edit without realizing
it's the only link to a certain page. This area allows you to review wiki pages
that are orphaned in this way so that you can re-link to them or delete them
from the wiki if they are no longer relevant.

**Draft Pages:** takes you to a list of pages which have not yet been
published. Users can edit pages and save their changes as drafts. They can come
back later to finish their page changes and publish them once they have been
approved.

**Search:** allows you to a term here and click the *Search* button to search
for items in the wiki. If the search term is not found, a link will be
displayed which allows you to create a new wiki page on the topic for which you
searched. 

The Wiki portlet is another full-featured Liferay application with all of the
features you expect from a state of the art wiki. Next, we'll look at how
Liferay handles live chat.
