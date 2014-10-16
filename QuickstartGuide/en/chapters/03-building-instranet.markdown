# Building Your Intranet

The remainder of this document will walk you through creating pages in
Liferay and loading them with content. We will see a lot of the built-in
features of Liferay. Because Liferay is a portal server, you can add new
features to it yourself by downloading or purchasing third party
portlets—or by developing some portlets yourself.

## Content Management

Go up to the Dock and scroll to *My Places -\> Guest* and click *Public
Pages*. This the home page of your portal, and it's pretty spartan at
this point. So let's jazz it up.

Go up to the Dock and click *Layout Template*. Pick *2 Columns 70/30*
and click *Save.*Now drag the Sign In portlet over to the column on the
right. Remove the Hello World portlet. Go up to the Dock and click *Add
Application*. From the CMS category, drag a Journal Content portlet to
the left column and drop it there. Click the *Add Article* icon which
appears in the portlet. You will see a content management window appear.
This is part of Liferay's Content Management system, and it allows you
to create static content for your web pages.

We're going to put just a title at the top of the page. Give the article
the name *Welcome* and type the word *Welcome!* into the rich text
editor. Highlight the text you typed and choose *XX-Large* from the
*Size* drop down. Click the *Text Color* icon which is right next to it
and choose a color you like. Finally, click the *Bold* icon and click
the *Save and Approve* button at the bottom of the page.

You'll be back to your home page, but this time it will be displaying
the Journal article you created in a portlet window. You can add as many
Journal Content portlets as you like to populate your page with content.
Go ahead and use the Dock to add three more Journal Content portlets to
your page: two underneath your welcome message and one in the right hand
column underneath the Sign In portlet.

You can add whatever content you like to the two portlets in the column
on the left: you'd generally want to put news announcements here; things
you'd want everyone to see when they come to your corporate home page.
Put a Café Menu in the portlet on the right, so your visitors can see
what your cafeteria is serving this week. If you don't have real
content, have fun making it up!

When you're finished, you should have a page that looks something like
this:

![image](../../images/03-building-instranet_html_m18267269.png)\
*Illustration 1: Home page after adding some content.*

Now we want to add a page for community and collaboration. Click the
*Add Page*link at the top of the page. Give the page the name
*Community*. Go up to the Dock, click *Layout Template*, and select the
*3 Column* layout.

We will be making this page the central focus of global collaboration
for your Intranet. Later, you can actually create organizations for the
various groups within your company to have their own collaboration
spaces, but this will be a shared area where everyone can go.

Go up to the Dock and click *Add Application*. From the CMS category,
add the Navigation portlet to the left column. From the Collaboration
category, add the Recent Bloggers portlet to the left column under the
Navigation portlet.

In the middle column, add a Journal Content portlet from the CMS
category and the Communities portlet from the Community category.

In the right column, add a Polls Display portlet from the Polls category
and the Page Comments portlet from the *Community* category. Close the
*Add Application* window. Add an article explaining what this page is
for to the Journal Content portlet you added to the middle column.

## Creating an Administrative Page

Notice that several of the portlets have no content in them? These
portlets require content to be created in other areas of the portal
before any content will show up. Let's take the simplest one first and
fill it with content. To do this, we will create a special
administrative private page in your Intranet.

Go up to the Dock, navigate to *My Places -\> My Community -\> Private
Pages*. Click on the *Admin*page you created earlier. In the Communities
portlet, click the *All Communities* tab. Click the *Actions*button and
select *Manage Pages*. You should see the following screen:

![image](../../images/03-building-instranet_html_m340808c0.png)\
*Illustration 2: Liferay's Manage Pages interface.*

You can see on the left a hierarchy of pages that you have been
creating. So far, you have a flat list of pages, but you can nest them
as deeply as you want. Keep in mind if you do this that most of
Liferay's themes only provide navigation for the top level, so you will
have to put a Navigation or Breadcrumb portlet on these pages in order
to enable users to get to them. We have already done this on the
Community page you have created, because we will be adding a nested page
under the Community page.

Across the top you have tabs for Public Pages, Private Pages, and
Settings. Settings affect the pages for the whole community. You can set
virtual hosts, enable Google Analytics on your pages, and a whole host
of other things. We won't go into that here, but feel free to explore
the interface yourself.

So far, we have been creating public pages. This time, we are going to
create an administrative private page. A public page is viewable by
everyone, even those who are not logged in to your portal. A private
page is viewable only by members of the community. By creating a private
page in the Guest community, we are ensuring that only people who are
logged in to the portal can view it, because we earlier made everyone
who is registered in the portal a member of the Guest community by
default. For this reason, making the administrative page a private page
will only protect it from people who are not logged in to the
portal—hardly a good security measure. So after we create the page, we
will place custom security restrictions on it so that only the people we
specify may view the page.

Click the *Private Pages* tab. You will see that there are no pages in
the hierarchy on the left, because there are no private pages yet. On
the right side of the screen, give the page the name *Admin* and click
the *Add Page* button. Now you have a page in the tree on the left.
Click on your new page, and then click the *Page* tab on the right side
of the screen. Click the *Permissions* button at the bottom. You will
see Liferay Portal's permissions screen. Click the *Community Roles*
tab, and then click the *Available* tab. You should see the following:

![image](../../images/03-building-instranet_html_4ebdb7ec.png)\
*Illustration 3: Liferay's permissions interface.*

Select Community Administrator and Community Owner by clicking the check
boxes to the left of their names and then click *Update Permissions*.

You will now set the permissions for the first role you selected. Select
the *View* permission and click the arrow to move it over to the left.
By doing this, you are saying that users with the Community
Administrator Role can View this page. Click the *Next* button.

You will then be able to set the permissions for the Community Owner
role. Select all the permissions and move them to the left. Then click
the *Finished* button.

![image](../../images/03-building-instranet_html_m2bcee04b.png)\
*Illustration 4: Setting permissions on a role.*

Are we done yet? No, not yet. Why? Because, though we have granted
administrators access to the page, we have not yet taken away access
from the rest of the community. So click the *Community* tab.

By default, the community can add discussion (if you've added the Page
Comments portlet to a page) or view any page in the community. We're
going to take these permissions away. Move the *Add Discussion* and
*View* permissions to the right (Available) column and click *Save*. Now
click the *Back* tab.

## 

![image](../../images/03-building-instranet_html_46f27bc4.png)\
*Illustration 5: Polls portlets*

Polls Portlets

Let's put some portlets on this page now. Click the *View Pages* button
in the top left corner. You will presented with a now-familiar blank
portal page. Go up to the Dock and click *Add Application*. From the
Polls category, add the Polls portlet. Notice that the Polls portlet has
a purple icon and the Polls Display portlet has a green icon. This is
telling you that the Polls portlet is a *non-instanceable* portlet and
the Polls Display portlet is an *instanceable* portlet.

This means that the Polls portlet can be added to a community once, and
it holds one set of data. In this case, you will be adding poll
questions with multiple choice answers. The set of questions belongs to
the community as a whole. So if you add the Polls portlet to another
page in the community, the same set of questions will be displayed. The
Polls Display portlet, however, is different. It can be added many times
to pages in the community, and each instance of the portlet can hold a
different poll question. So you use the Polls portlet to create
questions and the Polls Display portlet to display them and allow users
to answer them.

If you were to add the Polls portlet to another community, that
community would get its own set of questions that is different from the
ones in the Guest community. But each instance of that portlet has the
same set of data within that community. That is what makes it
non-instanceable.

Once you have added the Polls portlet to the page, close the *Add
Application* window. You have one button in a default Polls portlet:
*Add Question*. Go ahead and click it. You will see a form which allows
you to add a question and the multiple choice answers. You can even add
choices beyond the default two by clicking the *Add Choice* button. Have
fun. Make up a question. When you're finished, you should have something
which looks like the following:

![image](../../images/03-building-instranet_html_m4b1ce060.png)\
*Illustration 6: The Polls portlet.*

Click *Save*. You have now added a Poll question on the administrative
page. This makes it so that only administrators can create poll
questions. You are now ready to display this question on the *Community*
page, where you added the Polls Display portlet.

Go up to the Dock and go to *My Places -\> Guest -\> Public Pages*.
Click the link to the *Community* page you created. In the Polls Display
portlet—which you added to the column on the right—click the
Configuration icon. It looks like a gear. You will get a simple drop
down selection box which allows you to choose a question from the Polls
portlet to be displayed in the Polls Display portlet. Choose your
question and click *Save*. Then click the *Return to Full Page*link.

You will now see that your poll question is being displayed in the Polls
Display portlet. This allows your users to vote on the question, and it
keeps track of all the votes. Once a user has voted, it does not allow
that user to vote again.

You should now have a page that looks something like this:

## 

![image](../../images/03-building-instranet_html_m7ce5b43b.png)\
*Illustration 7: Community page with your poll question displayed.*

Blogs and Blogs Aggregation

Notice that there is also nothing in the Recent Bloggers portlet. That
is because you have no users who are bloggers yet. Since you are the
only user in the system, if you create a blog for yourself on your
personal public page, it can show up in the Recent Bloggers portlet.

Go up to the Dock and select *My Places -\> My Community -\> Private
Pages.*Click on your *Admin* page you created earlier. Click the *Users*
tab in the Enterprise Admin portlet. Click the *Actions* button next to
your user name and select the *Manage Pages* link. You will be brought
to the Manage Pages screen you used earlier to create pages in the Guest
community, but this time you are looking at your own, personal public
pages. And as you can see from the hierarchy on the left, you have no
pages. So go ahead and create a page called *Home*. Click the *View
Pages* button.

You will see that you now have a blank page. Go up to the Dock and click
*Add Application*. From the Collaboration category, add the Blogs
portlet to the right column on your page and close the *Add Application*
window.

When there are no blog entries in the Blogs portlet, it contains only
one button: *Add Blog Entry*. Click this button and add a blog entry.
You will see that it uses the same WYSIWYG editor that the Journal
portlets use. You can use this to add text and images to your blog
entries. When you are finished with your blog entry, click *Save*.

If you wish, you can add other portlets to the left hand column or
change the layout to a one column layout to make the page look a bit
better. In any case, you should have a blogs portlet that looks
something like this:

![image](../../images/03-building-instranet_html_36a376fd.png)\
*Illustration 8: The Liferay Blogs portlet with a single blog entry.*

Now use the Dock to navigate back to the Guest community, where your
Intranet pages are. To do this, select *My Places -\> Guest -\> Public
Pages*. Then click the Community link. You will now see that you have a
blog entry listed in the Recent Bloggers portlet. People can click the
link to go directly to your blog and view the entries. By default, the
Recent Bloggers portlet will show every blog in the portal. You can use
the Configuration icon to modify it so it displays only blogs from users
of a particular organization. For now, we will leave it alone.

We have designed this page so that as users begin using the portal,
creating blog entries, and commenting on the page, the portlets will
grow down toward the bottom of the page as the content within them
changes. This is generally a good practice, as you want to make sure
that all of the content types available to your users are above what is
called the *fold* of the page. You don't want to hide any content at the
bottom, because users might miss it entirely. If, however, your users
can see that “Recent Bloggers” or “Page Comments” continue down the
page, they may decide that they want to scroll down to view that
content, since it is obvious that it is there. This is a good page
design.

At this point, you should have a Community page that looks something
like this:

## 

![image](../../images/03-building-instranet_html_m3de92ff7.png)\
*Illustration 9: Continuing with the Community page.*

Breadcrumbs and Message Boards

Let's now give your users an area where they can discuss particular
topics of interest to them. Go up to the Dock and click *Manage Pages*.
Click the *Children* tab. You are going to create a child page under the
Community page called simply, Forums. So name the page *Forums* and
click the *Add Page* button. You will see that there is now an arrow
next to the Community page in the hierarchy on the left. This indicates
that there is at least one other page underneath Community. Click the
arrow, and the hierarchy will expand.

Click the *Return to Full Page* link in the top right corner of the
portlet window. Notice that the Navigation portlet now has a link to
your newly created Forums page, and that there is no link to that page
in the navigation that runs across the top of the screen. Most Liferay
themes don't have drop-down menus that can display the full page
hierarchy, so you need to provide your own navigation to them by using a
Navigation or a Breadcrumb portlet. Of course, you can always build your
own theme which includes this functionality if you need it. We will look
at themes a little later in this document.

Click on the Forums link in the Navigation portlet, and you will be
taken to your new, blank Forums page. Go up to the Dock, click *Layout
Template*, choose the *1 Column* layout, and click *Save*. Go back up to
the Dock and click *Add Application*. Open the *CMS* category and click
the *Add* button next to the Breadcrumb portlet. Open the
*Collaboration* category and click the *Add* link next to the Message
Boards portlet. Close the *Add Application* window.

You can see that the Breadcrumb portlet shows you a trail of the
locations to which you have navigated in the page hierarchy. The first
thing we will do is remove the portlet borders from this portlet so that
it looks more natural on the page.

Click the *Look and Feel* icon in the top right corner of the Breadcrumb
portlet window. It looks like a painter's palette. A window will pop up
allowing you to change all kinds of look and feel settings for this
portlet. All we want to do is remove the borders, so uncheck the *Show
Borders* check box and click *Save*. Close the window by clicking on the
red X in the upper right corner. Now use your browser's *Refresh* button
to refresh the page. The portlet box is now gone! Users who are not
administrators will not see the configuration links above the portlet;
for all intents and purposes, the portlet content is now embedded in the
page.

Now let's configure the message boards. You have probably seen message
boards all over the Internet, and these are really no different. Click
the *Add Category* button to add a discussion category. A good one to
start with is *General Discussion*. Once you've added the name and
description, click *Save*.

Liferay's message boards allow users to subscribe to message topics. If
they do this, they will receive an email whenever somebody replies to a
topic to which they are subscribed. These emails by default come from
“Joe Bloggs” who has the email address *test@liferay.com*. This is
probably not what you want.

Click the *Configuration* icon (looks like a gear) in the top right
corner of the portlet window. Set the name and email address to
something that's appropriate, such as

**Name:**Portal Admin

**Address:** admin@mycompany.com

Click *Save*. As you can see, you can customize the messages on other
tabs of this screen. We won't be doing this here, but feel free to
explore this section later. You can also customize the user “ranks,”
which are whimsical labels placed on users who post certain amounts of
messages to the boards. The default ranks go from Youngling (zero to 24
posts) all the way up to Yoda (1000 or more posts). You can modify the
rank names and the post amounts to anything you want.

There are other settings, but we're not going to change any other
settings right now. Click the *Return to Full Page* link to get back to
the forums. If you wish, you can create more message categories. When
finished, go into the General Discussion category we created earlier.
Notice that you can add both subcategories and threads. You can nest
categories as deep as you like. For now, we're just going to post a
thread to the boards. Click the *Post New Thread* button.

You will see a form that allows you to post a message to the message
board. There is an editor which has a syntax similar to those you'd find
on other message boards. Go ahead and post a message. When you are
finished writing your message, click *Save*.

You should now see your message. It should look something like this:

## 

![image](../../images/03-building-instranet_html_28bfa894.png)\
*Illustration 10: Message boards post.*

News and RSS Feeds

What Intranet would be complete without industry news feeds collecting
news about all of your competitors from all over the Internet? Let's
create that page next.

Go up to the Dock and click *Manage Pages*. We're going to create a
top-level page, so click the *Guest* link at the top of the hierarchy on
the left. Give your new page the name *News* and click the *Add Page*
button. Click the *Return to Full Page* link and then click the link to
your new page in the top level navigation. Using the Dock, click *Layout
Template* and pick the *2 Columns 50/50* layout.

Go back up to the Dock and select *Add Application*. From the *News*
category, add the News portlet to the left column and the RSS Portlet to
the right column. Notice that both of these portlets are *instanceable*
portlets. This means you can add as many of them as you like to any page
and can configure each instance to hold different data.

When the portlets appear, you can see that they already have news in
them. They are configured by default to connect to certain feeds. Of
course, you can completely customize this.

In the News portlet, click the *Preferences* icon, which looks like a
pencil and paper. Here you can select from multiple categories of news
feeds to display in the portlet. By default, Society -\> International
Relations and Top Stories -\> Top Stories are selected. We're looking
for some specific news links, so let's make some assumptions.

Your business does some outsourcing, so you are interested in news on
outsourcing. Click *Business: general*, and then select Outsourcing
News. Click *Save* and then click *Back*. Because of your industry, you
compete with several companies. Click *Companies*, and then check off
some companies and click *Save* and then click *Back*.

You get the idea. We could select more news feeds from more categories,
and you can certainly do that later if you want. For now, click the
*Return to Full Page* link. You will see that news articles from all the
categories you selected are now being displayed.

The RSS portlet is a bit more free-form. It allows you to add any feed
you can find on the Internet to your portal. Is there a feed you like?
If so, go find it and copy the URL to your clipboard. If not, here's a
handy feed for you to use:

[http://www.liferay.com/web/guest/home/journal/rss/14/news](http://www.liferay.com/web/guest/home/journal/rss/14/news)

Click the *Configuration* icon (looks like a gear). You can see that
three feeds are already configured in the portlet. If you like them, you
can leave them there. If not, you can click the *Remove* icon next to
the feed to remove it. In either case, you will need to click the *Add
Feed* icon to add another feed to the list. Paste your feed into the URL
field, and optionally give it a title. Select the number of entries you
want to display and click *Save*.

You should now have a page that looks something like this:

## 

![image](../../images/03-building-instranet_html_m58e9baff.png)\
*Illustration 11: Your news feeds.*

Projects and Wikis

Every Intranet should have a collaboration area where users can go to
work on their documents and share data pertaining to them. Many portals
use online Document Libraries and discussion forums for these, and
Liferay has this functionality as well. But probably the most ideal tool
for this sort of thing is a Wiki. So let's set up a Projects page for
users to collaborate on their projects.

Click the *Add Page* link in your top level navigation and give the page
the name *Projects*. Click on your new page. Go to the Dock and pick
*Layout Template*. Choose the *1 Column* layout and click *Save*. Now go
to the Dock again and click on *Add Application*. Add the Wiki portlet
from the *Wiki* category.

Liferay's wiki is a full-fledged, powerful wiki application that
supports every wiki feature you could want:

-   Flexible Wiki syntax (Creole format)

-   Images from the Internet or attached to the page

-   Multiple wikis in the same community

-   History

-   Attachments of files

-   Print Preview

-   Page moving (without breaking existing links to the page)

-   Diff of page versions

-   Email subscriptions to changes

-   Table of Contents

-   Optimistic Locking (notification that somebody else is editing the
    same page)

-   Child pages

-   Tag based navigation

And yes, there is more. We won't have time to explore many of these
features here, but suffice it to say that Liferay's wiki will be
sufficient to the task (and likely will exceed the number of features
necessary) for managing your company's wiki.

Let's edit the main page first and add some introductory text. There's a
link that says “This page is empty. Edit to add some text.” Go ahead and
click that link.

You are brought to a page which allows you to enter text, either in Wiki
syntax or via the WYSIWYG HTML editor you've used previously. Let's use
the Wiki editor.

Entering text into a Wiki is done via wiki syntax. Liferay's wiki uses
the Creole syntax, which is a common wiki syntax that has been designed
to be a standard for wikis going forward. Because many wiki markup
engines have in the past had slightly different syntax, this is an
effort to standardize them. There is a cheat sheet for the syntax to the
right of the wiki editor.

Enter some text into the editor. Here's some example text:

== Projects ==

\
\

Welcome to the projects page! Here you can get an overview of the
projects being worked on internally and optionally gain access to
contribute to them. Ask your project manager for further details.

When you are finished, click the *Save* button. You will notice that
your text has now been formatted properly via the wiki syntax. There is
also a link below the page stating there are 0 attachments. Let's add an
attachment to the article by clicking that link.

You will be brought to another page which holds meta data about the
page. One of the tabs on that page is labeled Attachments. Click the
*Add Attachments* button and attach a document from your machine to the
page. Use the multiple file uploader (requires Flash) or the classic
uploader to upload a document.

Before we leave this section of the page, click on the *General* tab.
Notice that you can provide RSS feeds to your Wiki content from here.
This allows you to publish wiki content to other areas of your portal or
to other web sites. There is also a permissions link here which allows
you to permission this wiki page just like any other asset in the
portal.

The *History* tab will let you see all the revisions of the page,
compare between those revisions, and even revert to a previous revision
of the page if you wish to.

There's more to explore here, but for now, click the back icon next to
the title of the page (FrontPage). You'll be taken back to the front
page of the wiki, and now it will be saying there's one attachment.

There's a small icon at the top right corner. If you roll your mouse
over it, a label pops up: *Manage Wikis*. Go ahead and click it. This is
where you can create various wikis for various projects. Right now,
there is only one, labeled *Main*. This is fine and can serve as an
introduction to the page or as a “roll up” wiki that a project manager
might use to summarize the status of many projects. You have already
added a page to this wiki.

Let's create a new wiki for another project. Click the *Add Wiki*
button. Give your project a cool name, like *Project Idioglossia*. Click
*Save*. Now you will see that there are two wikis: the main one and one
for your new project. You can add as many as you want. Notice the
*Actions* button next to the wikis. You can use this button to
permission the wikis by user group, organization, community, or role so
that only the project team working on that particular project can update
that wiki. As you did before, you can go in and examine the permissions
that are there by default to determine whether they are appropriate. For
now, click the *Back* tab.

Now you can see that Project Idioglossia has a link up at the top of the
portlet window along with the main wiki. To get to that wiki, simply
click on the link and begin adding pages and attachments.

We have one more thing to do to this page. Since there's only one
portlet on this page, and it takes up the whole page, don't you think it
would look better if we removed the portlet window borders? Click the
*Look and Feel* icon (the painter's palette), uncheck *Show Borders* and
click *Save*. Close the window and refresh the page. You should now have
a page that looks something like this:

## 

![image](../../images/03-building-instranet_html_2a5f2303.png)\
*Illustration 12: The project page with the wiki portlet.*

Organization Pages

Your portal does not have to be built solely in the Guest community.
There will be times when certain organizations within your company need
their own space. In this case, you can allow them to create pages for
their own use.

Every company has a marketing organization in order to spread the word
about the company's products so they can be sold. So let's create a
marketing organization for My Company and allow them to create their own
pages.

Go back to your personal community via the Dock: *My Places -\> My
Community -\> Private Pages*. Go to your Admin page. Click the
*Organizations* tab. Click the *Actions* button next to My Company and
then click *Add Suborganization*. Call it *My Company Marketing* and
give it a country / region, and then click *Save*.

Because you created the organization, you automatically become its
owner. However, you're not in marketing, you're a portal administrator.
So you need to create and permission a marketing user who will
administer the marketing organization. Click the *Actions* button next
to My Company Marketing and click *Add User* (if the user already
existed in the portal, you could have clicked *Assign Members*). Add a
user. Make up his or her name if you want to. When finished, click
*Save*. As before, change the password so you can log in as this user.
Click the *Return to Full Page* link in the top right of the portlet
window.

Now we want to make our marketing user an administrator of the marketing
organization. Click the *Organizations* tab again and click the
*Actions* button next to My Company Marketing. Select *Assign User
Roles*. Click the *Organization Owner* role. You will see that you are
in this role, because you created the organization. Click the
*Available* tab, and you should see your new user in the list. Check off
the user's name and click the *Update Associations* button. The user you
created is now an owner of the marketing organization in the portal,
with the ability to create users, pages, add portlets, and manage
permissions on resources in the organization. Click the *Return to Full
Page* link.

The marketing organization can now have their own completely separate
web site in the portal. The interface for creating it is exactly the
same as what you have been using to create the site in the Guest
community. In the Dock, you now have access to the marketing
organization's pages in *My Places*. There are no pages in there yet,
but we can create one.

Click the *Organizations* tab in the Enterprise Admin portlet. Click the
*Actions* button next to the My Company Marketing organization and then
click *Manage Pages*. You can add both public pages which everyone can
see (even Guests) or private pages that only members of the My Company
Marketing organization can see. Since we created this organization
because the marketing people wanted their own space, that probably means
they want private pages. So we'll create a home page for them there and
then send the user you just created an email telling her that she can
now begin creating pages.

Click the *Private Pages* tab. You will be at the new page interface
automatically. Give the page the name *Home* and click *Add Page*. Click
the *View Pages* link in the top left corner to go to the page. Go up to
the Dock, click *Add Application*, and from the *CMS* category, add a
Journal Content portlet to the page. Add an article called *Marketing
Home* and put some introductory text into it. When finished, click *Save
and Approve*.

As you can see, you can build portals for your entire organizational
hierarchy in Liferay. You can also create communities which cut across
organizational hierarchies to provide services that anyone can access.
And for those who want a private section of the site to do their work,
you can create that too.

You can go and send that email now. Your marketing person probably wants
to get started.

## Themes

Building out your intranet has been pretty easy, hasn't it? But there's
one glaring omission in what we've been building: all the pages look the
same, and they have the Liferay logo sitting in the top left corner. You
probably want the My Company logo up there, and maybe a whole different
look and feel.

Developers can create new themes for Liferay that completely change the
way it looks. With a good theme, it can be hard to tell that your site
is even running Liferay. For now, though, you don't have to wait for a
developer to create your theme: you can grab one of the ones from
Liferay's repository on the Internet.

Go up to the Dock, and select *My Places -\> My Community -\> Private
Pages*. Click on the Admin page. Go back to the Dock and click *Add
Application*. From the *Admin* category, add the Plugin Installer to the
page under the Communities portlet in the left column. This portlet
allows you to download and install new portlets and themes from
Liferay's internet repository. In addition to that, you can add other
repositories that you create internally or others create.

Click the *Theme Plugins* tab. Browse the themes and when you find one
you like, click on it. You can see a screen shot of it and if you click
the screen shot, you can see a larger version. To install the theme,
simply click the *Install* button: the theme will be automatically
downloaded and installed.

You can apply themes to whole communities, individual pages, and
everything in between. Before you apply a theme to the Guest community,
you may want to try it on your personal community first to make sure
that it looks like what you want. Go up to the Dock and click *Manage
Pages*. Click the *Look and Feel* tab. You should see the theme or
themes you installed in the list. Choose it and the page will
automatically refresh with the new theme.

Look to the left, where the page hierarchy is. You can see that the
*Admin* page is selected. You have just applied the theme to the Admin
page in your personal community. If you were to navigate to the *Home*
page, it would still have the old theme. To apply the theme to the whole
community, click the community name at the top of the hierarchy—which in
this case is your name. Click the *Look and Feel* tab again, and select
the theme. Now it is applied to every page in your community.

Now we've taken care of the look and feel, but what about that pesky
Liferay logo? We want to replace it with the logo of our company. This
can be done in several places.

If you want to replace it for the entire portal, click the arrow tab in
the Enterprise Admin portlet. Click the *Settings* tab. You can see now
where the Liferay logo is set. Click the *Change* link underneath it.
Click the *Browse* button and find the logo you want to use on your
machine. Click *OK* and then click *Save*. The logo will change to your
company logo across the portal.

If you want to replace the logo just for a particular community or
organization, you can do that too. Click the *Return to Full Page* link
and then click the *All Communities* tab in the Communities portlet.
Click the *Actions* button next to the Guest community and then click
*Manage Pages*. Click the *Settings* tab and then click the *Logo* tab.
You can browse for a logo that will be displayed just for this
community.

Go ahead and change the theme for the Guest community to one of the
themes that you downloaded. You can do this by accessing the *Manage
Pages* interface through the Communities portlet. If you're already
there, click the *Back* tab from the logo page. Click the name of the
community (Guest) which is at the top of the hierarchy. Then click the
*Look and Feel* tab and select your theme. To see your new Intranet page
with the theme applied, access it via the Dock: *My Places -\> Guest -\>
Public Pages*.

You may want to customize the titles of your portlets now (which you can
do by clicking on them), or remove the portlet windows altogether.
Modify the page as you see fit. When you are finished, you should have
something that looks like this:

![image](../../images/03-building-instranet_html_6b8bf097.png)\
*Illustration 13: The final home page of your Intranet. This theme is
called Dreamy.*

You are done!

We hope you have enjoyed creating an Intranet from scratch using
Liferay!
