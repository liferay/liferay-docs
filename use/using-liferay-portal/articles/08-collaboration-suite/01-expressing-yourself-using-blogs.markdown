# Expressing Yourself Using Blogs [](id=expressing-yourself-using-blogs)

The word *Blog* is an apostrophe-less contraction of the two words *web* and
*log*. Blogs were first popularized by web sites such as Slashdot
([http://slashdot.org](http://slashdot.org)) which have the format of a running
list of entries to which users could attach comments. Over time, more and more
sites such as Digg, del.icio.us, and Newsvine adopted the format, empowering
users to share their opinions and generating lively discussions.

Over the course of time, blogging sites and applications began to appear, such
as blogger.com, blogspot.com. TypePad, WordPress, and Web Roller. These
applications allow *individuals* to run their own web sites in the same format:
a running list of short articles to which readers who are registered with the
site can attach threaded comments. People who run a blog are called *bloggers*,
and sometimes they build a whole community of readers who are interested in
their blog posts. Anyone can have a blog, in fact, there are several famous
people who run their own blogs. It gives people an outlet for self-expression
that they would not otherwise have, and the ubiquity and wide reach of the
Internet ensures that if you have something important and interesting to say,
somebody will read it.

![Figure 8.1: Slashdot was one of the first blogs on the Internet.](../../images/05-slashdot.jpg)

Liferay Portal has a Blogs portlet which allows you to provide a blogging
service to users of your web site. In fact, Liferay extensively uses the Blogs
portlet on
[http://www.liferay.com/community/blogs](http://www.liferay.com/community/blogs)
to provide community members and employees with blogs of their own. In addition
to the Blogs portlet, there's also a Blogs Aggregator portlet which can take
entries from multiple users' blogs and put them all in one larger list. We will
go over how to use both of these portlets to create a blogging site for your
users.

## The Blogs Portlet 

The Blogs portlet is available from the *Collaboration* section of the *Add
&rarr; Applications* menu. Notice that it is an instanceable portlet, meaning
that it supports scopes. This allows you to use the Blogs portlet to create a
shared blog to build a site like Slashdot or to create multiple personal blogs
to build a site like [http://blogger.com](http://blogger.com). What's the
difference? Adding the Blogs portlet to a site page creates a shared blog for
members of the site that the page belongs to. Adding the Blogs portlet to a
user's personal site creates a blog just for that user. The Blogs portlet works
the same way in both cases. And of course, you can change the Blog portlet's
scope to have different blogs on different pages in the same site.

![Figure 8.2: The initial view of the Blogs portlet.](../../images/05-initial-view-blogs-portlet.png)

By default, the Blogs portlet displays the latest entry in its entirety. When
you first add the portlet to a page, it has no entries, so the portlet is
empty. There are several display options to let you configure it to look the
way you want it to look. Before we start adding entries, let's configure the
portlet so that it displays entries the way you want them.

### Configuring the Blogs Portlet 

The Blogs portlet is easy to configure. Click on the *Options* icon in the
portlet's title bar and select *Configuration*. Beneath the Setup tab, there is
another row of options.

**Display Settings:** changes various display options for the Blogs portlet. To
choose the right settings, you should think about the best way to display your
entries as well as how you want users to interact with bloggers.

- *Maximum Items to Display:* choose the total number of blog entries to display
on the initial page. You can select up to one hundred to be displayed.

- *Display Template:* choose between *Full Content*, *Abstract*, or *Title*.
Setting this to Abstract shows the abstract, or if there isn't one, only the
first 30 words of your blog entries, with a Read More link at the bottom of
each that expands to the whole entry. To learn how to customize your own display
templates visit the *Using application display templates* section of the
*Personalization and Customization* chapter.

- *Enable Flags:* flag content as inappropriate and send an email to the
administrators.

- *Enable Related Assets:* select related content from other portlets to pull
into their blog entry for readers to view.

- *Enable Ratings:* lets readers rate your blog entries from one to five stars.

- *Enable Comments:* lets readers comment on your blog entries.

- *Enable Comment Ratings:* lets readers rate the comments which are posted to
your blog entries.

- *Enable Social Bookmarks:* lets users tweet, Facebook like, or +1 (Google
Plus) about blog posts. You can edit which social bookmarks are available in the
*Social Bookmarks* section of the Configuration menu.

- *Display Style:* select a simple, vertical, or horizontal display style for
your blog posts.

- *Display Position:* choose a top or bottom position for your blog posts.

- *Social Bookmarks:* choose social bookmarks to enable for blog posts, which
includes Twitter, Facebook, and plusone (Google Plus).

![Figure 8.3: The Blogs portlet's *Configuration* menu offers a plethora of display settings.](../../images/05-blogs-configuration.png)

**Email From:** defines the *From* field in the email messages that users
receive from the Blogs portlet.

**Entry Added Email:** defines a subject and body for the emails sent out when
a new Blog entry has been added.

**Entry Updated Email:** defines a subject and body for the emails sent out
when a new Blog entry has been updated.

**RSS:** choose how blogs are displayed to RSS readers. Here, you can choose
how you want your blog entries to be published as feeds to readers and outside
web sites.

- *Maximum Items to Display:* choose the total number of RSS feeds to display on
the initial page. You can choose up to one hundred to be displayed.

- *Display Style:* choose between *Full Content*, *Abstract*, and *Title*. These
options work just like the ones above for blog entries.

- *Format:* choose which format you want to deliver your blogs: Atom 1.0, RSS
1.0, or RSS 2.0.

Now that you have the Blogs portlet looking the way you want it, you'll want to
review permissions for it--especially if you're working on a shared blog. 

### Permissions 

If you have a personal blog, the default permissions should work well for you.
If you have a shared blog, you may want to modify the permissions on the blog.
The default settings make it so only the owner of the site to which the portlet
has been added is able to add entries. This, of course, is great if the Blogs
portlet has been added to a user's personal pages, but doesn't work so well for
a shared blog. But don't worry: it's easy to share a blog with multiple users.

First, create a role for your bloggers and add them to the role (roles are
covered in chapter 13). Next, click the *Permissions* button on the
Blogs portlet. A list of both portal and site roles is displayed, and currently
only the owner is checked. Check off any other role or team that should have
the ability to add blog entries, and then click *Save*. Once this is done,
users in the roles or teams that you selected are able to post to the shared
blog. 

Now that everyone's able to post, let's look at how posts work. 

### Adding Blog Entries 

Now you're ready to begin adding blog entries. Click the *Add Blog Entry*
button. The following data entry screen appears: 

![Figure 8.4: After clicking *Add Blog Entry*, a new window appears with configuration options for your new blog entry.](../../images/05-new-blog-entry.png)

There isn't much difference between this screen and any other data entry screen
within Liferay Portal. You get a title, a way of scheduling when the entry is
to appear, and a rich editor that allows you to format your entry the way you
want, complete with embedded images, videos, and the like. Note also that as
you type, the entry is automatically saved as a draft at periodic intervals.
This gives you peace of mind in using the portlet from within your browser,
since you won't lose your entry in the event of a browser crash or network
interruption. You can also tag your entries using the same tagging mechanism
found everywhere else in the portal.

The Blogs portlet also supports trackbacks and pingbacks. Trackbacks are
special links that let you notify another site that you explicitly linked to 
them in the *Content* field. For example, if you wanted to write an entry in 
your blog and reference some other site's entry, you might put the URL to the
other entry in the *Content* field and the trackback URL in the *Trackbacks 
Sent* field. If you have multiple URLs you want to send trackbacks to, 
separate them with spaces. 

If you want others who link to your blog to let you know about the link via
trackbacks, leave the *Allow Trackbacks* box checked. This generates a URL that
is displayed with your blog entry. Others who want to link to your entry can
use this URL for the link, to send trackbacks to your blog.

Note that trackbacks only work when the protocol is supported by both the
linker and the linkee. A newer way to support similar link notification
functionality is *pingbacks*. Pingbacks are XML-RPC requests that are similar
to trackbacks except they're automatically sent when you link to another site.
They're easier to use because you don't have to do anything extra: if you link
to another site in your blog entry, Liferay sends a pingback to the other site
to notify that site that you linked to it. Similarly, if someone links to your
blog entry, Liferay can receive a pingback from that person's site and record
the link.

You can enter a description of your post beneath the Abstract heading, and this
can be used by the Abstract display style. Below this is the Categorization
heading, where you can attach tags and/or categories to your blog entry. You
should definitely consider doing this: it improves search results for blog
entries, and it gives you more navigation options that you can pass on to your
users. For example, you can add the Tags Navigation portlet to another column
on your blogs page, allowing users to browse blog entries by tag. 

Below this is the Related Assets heading. If there's some other content in the
portal that's related to your blog, you can choose it here. For example, you
might want to write a blog entry talking about a particular discussion that
happened on the forums. To link those two assets together, select the forum
thread under Related Assets. 

Once you've finished your blog entry, click *Publish*. You'll go back to the
list of entries, and now your entry is displayed. Here is what it looks like
when the display template is set to *Full Content* and the maximum items to
display is set to ten:

![Figure 8.5: This sample blog entry gives you an idea of what new content and features are displayed on your page.](../../images/05-first-blog-entry-added.png)

You can see that in the summary view, you don't see the trackback/pingback
link, and you only see the number of comments that have been added. If you were
to click the *Read More* link, you would see the entirety of the article, all
the comments in a threaded view, and the trackback/pingback link which others
can use to link back to your blog entry.

The full view of the blog entry also contains links to share blog entries on
social networks, such as Twitter, Facebook, and Google Plus. This gives your
readers an easy way to share blog entries with friends, potentially driving
further traffic to your site. As you can see, the Blogs portlet is a
full-featured blogging application that gives you and your users the ability to
enter the blogosphere with an application that supports anything a blogger
needs.

Of course, Liferay is a portal, and as a portal, it excels at aggregating
information from multiple places. For that reason, it also includes the Blogs
Aggregator portlet so that you can "bubble up" blog entries from multiple users
and highlight them on your site. Let's look next at how that works. 

## Aggregating Blog Entries 

You can set up a whole web site devoted just to blogging if you wish. The Blogs
Aggregator portlet allows you to publish entries from multiple bloggers on one
page, giving further visibility to blog entries. This portlet is also very
straightforward to set up. You can add it to a page from the *Collaboration*
category in the *Add &rarr; Applications* menu.

If you click *Configuration* from the options button in the title bar of the
portlet, the Blogs Aggregator's configuration page appears. From here, you can
set several configuration options.

![Figure 8.6: You can navigate to the Blogs Aggregator Configuration menu by selecting the *Options* gear from the portlet's title bar and selecting *Configuration*.](../../images/05-blogs-aggregator-configuration.png)

**Selection Method:** select Users or Scope here. If you select Users, the
Blogs Aggregator aggregates the entries of every blogger on your system. If you
want to refine the aggregation, you can select an organization by which to
filter the users. If you select Scope, the Blogs Aggregator contains only
entries of users who are in the current scope. This limits the entries to
members of the site where the Blogs Aggregator portlet resides.

**Organization:** select which organization's blogs you want to aggregate.

**Display Style:** select from several different styles for displaying blog
entries: *Body and Image*, *Body*, *Abstract*, *Abstract without Title*,
*Quote*, *Quote without Title*, and *Title*.

**Maximum Items to Display:** select maximum number of entries the portlet
displays.

**Enable RSS Subscription:** creates an RSS feed out of the aggregated entries.
This lets users subscribe to an aggregate feed of all your bloggers. Below this
checkbox, you can configure how you want the RSS Feed displayed:

- **Maximum Items to Display:** select maximum number of RSS items to display.
- **Display Style:** select from several different styles for displaying RSS
feeds: *Abstract*, *Full Content*, and *Title*.
- **Format:** select which web feed language to use for your feed, which
includes *Atom 1.0*, *RSS 1.0*, or *RSS 2.0*.

**Show Tags:** for each entry, displays all the tags associated with the blogs.

When you've finished setting the options in the portlet, click *Save*. Then
close the dialog box. You'll notice the Blogs Aggregator looks very much like
the Blogs portlet, except that the entries come from more than one author. This
makes it nice and familiar for your users to navigate. 

## The Blogs Administrator Portlet 

In the Control Panel there's a portlet for managing your site's blog entries.
Most of the time, the Blogs portlet is the only tool you'll need to manage your
blog entries. If, however, you need to massively delete blog entries, the blogs
administrator portlet is the perfect tool for you. 

![Figure 8.7: The Blogs Administrator portlet lets you delete large sets of blog entries.](../../images/04-blogs-administrator.png)

Note that it's only for batch processing of blog entries; for the full set of
tools for managing blog entries, your best bet is to use the Blogs portlet.
Next, let's look at Liferay's improved Calendar portlet available from Liferay
Marketplace.
