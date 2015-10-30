# Publishing Blogs [](id=publishing-blogs)

Since blogs are a mainstay on the web, bloggers and blog administrators expect
feature-rich environments for publishing blogs. Bloggers expect not only a
powerful editor that allows them to create terrific content, but also a
pleasurable authoring experience—like sipping a hot personalized beverage while
touching up your final draft. Blog admins demand an intuitive environment
that facilitates configuring blog instances and managing blog entries
efficiently. Liferay's Blogs meets these demands. 

Its editor delivers features you'd expect, in an elegant in-context manner. It
has a complete set of WYSIWYG controls that appear where and when you need them.
You can stay in this easy-to-use mode or switch to source mode to edit your
content's HTML code. In source mode, you can work with light text on a dark
background or dark text on a light background. To cap things off, you can do
fullscreen editing to minimize distractions as your create your posts. You'll
have a satisfying experience creating awesome blog posts. 

Lastly, Liferay Blogs empowers you to show off your blogs using powerful display
portlets. You can leverage Liferay's built-in display templates or create your
own, to present blogs the way you like them. And you can now add a beautiful
cover image to each of your blog entries. Their like album covers for your
posts. Let's face it--you might not be able to judge a book by its cover, but
you're more likely to draw readers to your blog entry by decorating it with an
enticing cover image! 

By now you're probably chomping at the bit to start blogging, right?! Let's get
started. 

## Adding Blog Entries [](id=adding-blog-entries)

Each site comes with a built-in blog instance. So let's explore adding an entry
to your site's blog. The Blogs Administration portlet provides the best place to
draft blogs. You access it in the Product Menu at *Sites* &rarr; *\[Site Name\]*
&rarr; *Content* &rarr; *Blogs*. The scope label *Default Scope* shows in
darkened font below the *Content* header; the scope label indicates you're
working with the site's blog instance. The *Entries* screen appears, listing the
site's blog entries. 

+$$$

**Note**: To switch to a particular page-scoped blog instance within the site,
you can click the gear icon and select the page's name. Creating a page-scoped
blog instance is explained later in the *Scope* portion of the *Displaying
Blogs* section. 

$$$

Click on the *Add Blog Entry* button to bring up the blog entry editor. 

![Figure 1: This screenshot highlights the blog entry editor's controls for formatting text, justifying images, and editing tables.](../../images/blogs-blogs-entry-mars.png)

The editor's initial screen presents fields to set a cover image, title, and
subtitle, and an area for creating the entry's content. You may be asking
yourself, "Where are the editor's controls?" The editor gives you a seamless
writing experience, displaying controls when you need them, and hiding them from
view when you don't need them. This keeps your canvas unclutterd, so you can
focus on writing. As you create content, context-specific controls appear. 

Go ahead and enter text in the *Content* area. If you highlight text, controls
appear. These controls let you style the text or convert it to a link or a
tweet--this shares your blog post on Twitter. 

Whenever you park your cursor in the content area, the *Add* icon (`+`)
appears. If you click on it, it shows controls for inserting an image,
table, and horizontal line. To insert an image, click the icon that depicts the
mountain silhouette. The image file selector screen appears. It let's you choose
an existing image from in the blog or Documents and Media, or an image outside
the portal. After you've added the image to the blog entry, clicking on the
image brings up controls for justifying to the right or left side of the
article. 

You can also insert a table with as many rows and columns as you like. If you
click inside the table, table editing controls appear. They let you designate
the first row and/or column as table headers. The controls also enable you to
add rows, columns, and cells. 

Now you're familiar with the editor's regular mode. If you'd rather work with
the content's HTML code, you can. To switch the editor to source view, select
the *Source* icon (`</>`). Note that an icon (`abc`) for regular mode appears,
giving you the option to switch back to regular view. Source view offers slick
features allow you to make the editor comfortable and practical. To satisfty
your eyes, source view's moon icon and sun icon let you switch between night
mode, a dark background with white type, and day mode, a white background with
black type. And the built-in syntax coloring helps you identify HTML elements,
no matter if you're using day or night mode. 

Have you ever cleared off your workspace to continue your work in a clean, fresh
environment? The editor's fullscreen mode allows you to do the same thing as you
blog. You can switch to fullscreen mode by selecting the *Fullscreen* icon
(bidirectional diagonally-crossed arrows ). The *Edit Content* window appears,
taking up the entire browser screen. The first icons above the editor allow you
to arrange the HTML editor and rendered content area, horizontally or
vertically. These dual views allow you to edit the HTML source and render it in
real time. The last icon at the top of the content editor lets you toggle hiding
the content rendering area, so you can use the entire browser space to work on
the HTML code. Fullscreen view can really help you stay "in the zone" as you
create awesome blog entries. 
 
![Figure 2: Fullscreen view helps you minimize screen clutter and render HTML changes in real time.](../../images/blogs-full-screen-editor.png)

Every 25 seconds, the entry you're editing is automatically saved as a draft; so
a browser crash or network interruption won't cause you to lose your entry. You
can exit the fullscreen view, by clicking on *Done* (which saves your content)
or clicking on *Cancel* to abandon any changes since the last auto-save. From
the normal sized source view, you can click the `abc` icon to switch back to
regular view to use the WYSIWYG editor. 

You've learned all the ins and outs of the content editor. Next, you'll learn
how to specify your blog entry's other characteristics. 

The edit screen's first input field--*Drag \& Drop to Upload*--lets you add a
cover image (optional) for your entry. This might be an image that represents
your entry's purpose and attract readers. You can drag and drop onto this field
any image you like. As an alternative to dragging and dropping an image, you
click on the *Select File* button to use the image selector to choose an
existing image in the blog, an image file from Documents and Media, or an image
outside the portal. You can browse to a file and upload it, or you can specify
an image's URL for the image selector to take in the image automatically. After
you set the entry's cover image, you can add a caption to it. And if you want to
select a different image, you can click the *Change* icon (circling arrows) to
bring up the image selector again.

Clicking on the *Configuration* icon (gear) at the top of the entry editor
displays lets you configure the blog entry. It organizes the configuration
options under several headings. Its Display Date heading reports the date and
time the entry was first published. The Abstract section lets you choose between
using 400 character long text-only abstracts or a Custom Abstract. Custom
Abstract enables you to represent your entry using a thumbnail image and
description. The Email Notification section let's you enable sending emails on
entry updates. 

The Categorization heading is next in the entry configuration screen. Its
options allow you to attribute tags and/or categories to your blog entry. You
should definitely consider doing this: it improves search results for blog
entries, and it gives you more navigation options that you can pass on to your
users. For example, you can add the Tags Navigation portlet to another column on
your blogs page, allowing users to browse blog entries by tag. 

Below this is the Related Assets heading. If there's some other content in the
portal that's related to your entry, you can choose it here. For example, you
might want to write a blog entry talking about a particular discussion that
happened on the forums. To link those two assets together, select the forum
thread under Related Assets. 

Blog entries also support trackbacks and pingbacks. Trackbacks are
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

Once you've finished your blog entry, click *Publish*. Your blog entry appears
with the site's other blog entries. 

![Figure 3: Blogs Administration is the perfect place to create and manage blog entries. It has several options for modifying, displaying, filtering, and finding entries.](../../images/blogs-admin-entries.png)

Congratulations on creating your blog entry! Before displaying it on your site's
pages, let's look at how you can manage blog entries from Blogs
Administration. 

## Managing Blog Entries [](id=managing-blog-entries)

Blogs Adminstration helps bloggers and blog administrators manage blog entries.
*Search* finds entries that match the keywords you enter. The *Order by*
selector enables you to organize entries by *Title* or *Display Date*, in
ascending or descending order. And the icons above the listing of entries allow
you to display the entries in different ways: showing large cover images,
listing single cell rows that show the authors image and entry information, or
displaying the entries in a table. Working with existing blog entries has never
been easier! 

You can manage entries individually or in a batch. Each entry has an *Actions*
icon to edit the entry, configure its permissions, or move it to the Recycle
Bin. You can select the *All* checkbox to select all entries or select an
entry's individual check box, and click the Recycle Bin icon to move them into
the Recycle Bin. Under the *Images* tab you view individual entries or delete
them individually or in a batch. 

The *Options* icon at the top of Blogs Administration allows you to open up
screens for configuring permissions and notifications, and importing/exporting
the blog. Here are the blog instance Configuration options:

**Email From:** defines the *From* field in the email messages that users
receive from Blogs.

**Entry Added Email:** defines a subject and body for the emails sent out when
a new blog entry has been added.

**Entry Updated Email:** defines a subject and body for the emails sent out
when a new blog entry has been updated.

**RSS:** choose how blogs are displayed to RSS readers. Here, you can choose
how you want your blog entries to be published as feeds to readers and outside
web sites.

- *Maximum Items to Display:* choose the total number of RSS feeds to display on
the initial page. You can choose up to one hundred to be displayed.

- *Display Style:* choose between *Full Content*, *Abstract*, and *Title*. These
options work just like the ones above for blog entries.

- *Format:* choose which format you want to deliver your blogs: Atom 1.0, RSS
1.0, or RSS 2.0.

You've learned how to create blog entries and manage them. It's time to consider
actions to authorize users to perform in Blogs. For example, who you allow to
view the different blog instances and who should you allow to add blog entries?

If you have a personal blog, the default permissions should work well for you.
If you have a shared blog, you may want to modify the permissions on the blog.
The default settings make it so only the owner of the site to which the portlet
has been added is able to add entries. This, of course, is great if the Blogs
portlet has been added to a user's personal pages, but doesn't work so well for
a shared multi-author blog. But don't worry: it's easy to grant blogging
permissions to users. 

First, create a role for your bloggers and add them to the role--roles are
covered in the [*Roles and
Permissions*](/discover/portal/-/knowledge_base/6-2/roles-and-permissions)
section. Next, click *Options* &rarr; *Permissions* in Blogs Administration. A
list of both portal and site roles is displayed, and currently only the owner is
checked for the *Add Entry* permission. Check on any other role or team that
should have the ability to add blog entries, and then click *Save*. Once this is
done, users in the roles or teams that you selected are able to post to the
shared blog. Also note you can grant roles and teams the ability to subscribe to
blog updates. 

From within the Control Panel you can configure all the permissions and in the
individual portlets for Blogs: Blogs Administration, Blogs, and Blogs
Aggregator, you can configure portlet specific permissions. Permissions for the
Blogs and Blogs Aggregator portlets is covered in the next section. 

You've now created a blog entry and learned how to manage blog entries and
permissions. Next, you'll learn how to use the Blogs and Blogs Aggregator
portlets to display blog entries the way you want them. 

## Displaying Blogs [](id=displaying-blogs)

The Blogs portlet is available from the *Collaboration* section of the *Add
&rarr; Applications* menu. Since it supports scopes, you can use it to create a
multi-author blog to build a site like
[http://slashdot.org/](http://slashdot.org/) or to create multiple personal
blogs to build a site like [http://blogger.com](http://blogger.com). What's the
difference? Adding the Blogs portlet to a site page creates a shared blog for
members of the site that the page belongs to. Adding the Blogs portlet to a
user's personal site (dashboard) creates a blog just for that user. The Blogs
portlet works the same way in both cases. And of course, you can change a Blog
portlet's scope to be page-scoped, to produce a blog instance within the site,
but different from the site's blog instance. 

Add the Blogs portlet to one of your site's pages. It shows a lists abstracts of
the site's recently published blog entries. Notice how each entry's cover image
shows prominently in the listing. The figure below shows a blog entry's
abstract in a Blogs portlet. 

![Figure 4: Here's a blog entry in abstract format in a Blogs portlet.](../../images/blogs-blog-entry-abstract-2.png)

You can see that in the summary view, you don't see the trackback/pingback link,
and you only see the number of comments that have been added. If you click the
entry's title, you'll see the entirety of the article, all the comments in a
threaded view, and the trackback/pingback link which others can use to link back
to your blog entry. 

The full view of the blog entry also contains links to share blog entries on
social networks, such as Twitter, Facebook, and Google+. This gives your
readers an easy way to share blog entries with friends, potentially driving
further traffic to your site. As you can see, the Blogs portlet is a
full-featured blogging application that gives you and your users the ability to
enter the blogosphere with an application that supports anything a blogger
needs. 

![Figure 5: Users can view your blog entry in all its glory. They can rate it, comment on it, and share it with other people.](../../images/blogs-blog-entry-full-content.png)

By default, the Blogs portlet displays the abstract and image of the latest
entries. There are several display options that let you configure the listing to
look the way you want. To configure the Blogs portlet, click on the *Options*
icon in the portlet's title bar and select *Configuration*. Beneath the Setup
tab, there is another row of options. 

**Display Settings:** changes various display options for the Blogs portlet. To
choose the right settings, you should think about the best way to display your
entries as well as how you want users to interact with bloggers.

- *Maximum Items to Display:* choose the total number of blog entries to display
on the initial page. You can select up to seventy-five to display at once.

- *Display Template:* choose between *Abstract*, *Full Content*, *Title*, or
*Basic*. Setting this to Abstract shows the entry's cover image and first four
hundred characters of text. Users can click on the entry's title to see its full
content. You can click on *Manage Display Templates for Liferay* to select an
existing Blogs application display template (ADT) or to create your own. To
learn how to customize your own display templates with
[Application Display Templates](/participate/liferaypedia/-/wiki/Main/Application+Display+Templates)
visit the *Using Application Display Templates* section of this guide.

- *Enable Flags:* flag content as inappropriate and send an email to the
administrators.

- *Enable Related Assets:* select related content from other portlets to pull
into their blog entry for readers to view.

- *Enable Ratings:* lets readers rate your blog entries from one to five stars.

- *Enable Comments:* lets readers comment on your blog entries.

- *Enable Comment Ratings:* lets readers rate the comments which are posted to
your blog entries.

- *Enable Social Bookmarks:* lets users tweet, Facebook like, or +1 (Google+)
about blog posts. You can edit which social bookmarks are available in the
*Social Bookmarks* section of the Configuration menu.

- *Display Style:* select a menu, simple, vertical, or horizontal display style for
your blog posts.

- *Display Position:* choose a top or bottom position for your blog posts.

- *Social Bookmarks:* choose social bookmarks to enable for blog posts, which
includes Twitter, Facebook, and Google+.

Here are descriptions for the other Blogs Configuration tabs:

*Permissions*: let's you grant permissions to manage using preferences,
configure the portlet, set the portlet's permissions, view the portlet, add
display templates, and add the portlet to a page. 

*Communication*: presents the portlet's public render parameters that you
can manage for communication across portlets. 

*Sharing*: gives you options for embedding the portlet instance as a
widget on on any website , Facebook, or Netvibes, or as an OpenSocial Gadget on
an OpenSocial site. 

*Scope*: lets you specify the blog instance the portlet displays: the current
site's blog (default), global blog, or page blog. If the page doesn't already
have a blog instance, you can select scope option *+ \[Page Name\] \(Create
New\)* to create a page-scoped blog instance for the Blogs portlet
to display. 

Of course, Liferay is a portal, and as a portal, it excels at aggregating
information from multiple places. For that reason, it also includes the Blogs
Aggregator portlet so that you can "bubble up" blog entries from multiple users
and highlight them on your site. Let's look next at how that works. 

## Aggregating Blogs [](id=aggregating-blogs)

You can set up a whole web site devoted just to blogging if you wish. The Blogs
Aggregator portlet allows you to publish entries from multiple bloggers on one
page, giving further visibility to blog entries. This portlet is also very
straightforward to set up. You can add it to a page from the *Collaboration*
category in the *Add &rarr; Applications* menu. 

If you click *Configuration* from the options button in the portlet's title bar,
the Blogs Aggregator's configuration page appears. From here, you can set
several configuration options.

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

At the top of the Configuration screen, there's an option called
*Archive/Restore Setup*. It lets you store your current Setup configuration or
apply an existing archived Setup. This is especially helpful when you have
multiple configurations you like to use in Blogs Aggregator portlets. 

Here are descriptions for the other Blogs Aggregator's Configuration screens:

*Permissions*: let's you grant permissions to view the portlet, add the portlet
to a page, configure the portlet, set the portlet's permissions, and manage its
preferences. 

<!-- TODO - Revisit the permission Access in Control Panel. It's currently grayed out. - Jim --> 

*Communication*: presents the portlet's public render parameters that you
can manage for communication accross portlets. 

*Sharing*: gives you options for embedding the portlet instance as a
widget on on any website , Facebook, or Netvibes, or as an OpenSocial Gadget on
a site that supports OpenSocial. 

*Scope*: lets you specify the blog instance the portlet displays: the current
site's blog (default), global blog, or the page's. If the page doesn't already
have a blog instance, you can select scope option *+ \[Page Name\] \(Create
New\)* to create a page-scoped blog instance and set the Blogs portlet
to display it. 

When you've finished setting the options in the portlet, click *Save*. Then
close the dialog box. You'll notice the Blogs Aggregator looks very much like
the Blogs portlet, except that it shows entries from multiple blogs. 

![Figure 6: The Blogs Aggregator lets you display blog entries authored by multiple authors from different sites.](../../images/blogs-blog-aggregator.png)

Throughout this section, you've learned how to create blog posts in a powerful
no-nonsense editor, manage blog instances and blog entries in Blogs
Administration, and display blogs the way you like them, using the the Blogs
portlet and Blogs Aggregator portlet. Now, you can enjoy the benefits of
participating in the world of blogging. 

Blogs are terrific for sharing information on a topic, posting important
announcements, and expressing yourself. And if comments are enabled, users can
have short exchanges about your blog entry. The best place to discuss things or
ask questions, however, is in a forum. Next, you'll learn how to create forums
using Message Boards. 
