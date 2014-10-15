# Understanding Liferay's Common Configuration Options [](id=understanding-liferays-common-configuration-options)

Just like siblings have common features inherited from their parents,
applications that ship with Liferay also share common features. These include
look and feel, communication, scoping, sharing, permissions, archive
configurations, and exporting/importing portlet data. So before we get into the
nitty gritty of the applications themselves, it's best to cover these common
features first, starting with the look and feel configuration options.

## Look and Feel [](id=look-and-feel)

An administrator can access the look and feel configuration menu of any Liferay
portlet by clicking on the wrench icon at the top right corner of the portlet
and selecting *Look and Feel*. The location of the wrench icon and other
portlet icons (minimixe, maximize, and remove) may vary, depending on your
theme. Liferay portlets' look and feel dialog boxes contain seven tabs:

- Portlet Configuration
- Text Styles
- Background Styles
- Border Styles
- Margin and Padding
- Advanced Styling
- WAP Styling

After making customizations on any tab, remember to click the *Save* button to
apply your changes. To see the effect of your changes, you may have to refresh
the page. If you don't like the effect of your changes, click the *Reset*
button to discard them.

On the Portlet Configuration tab, you can check the *Use Custom Title* box to
rename your portlet's title. The value you enter in the Portlet Title box will
be displayed at the top of the portlet window on the page. You can also select
a language from the Portlet Title dropdown menu. If you've provided a language
key translation for the language you select, the your portlet's title will be
displayed in the selected language.

![Figure 7.1: The Porlet Configuration tab of the Look and Feel Box allows you
to define a custom portlet title, link porlet URLs to a specific page, and
select whether or not portlet borders should be
displayed.](../../images/look-and-feel-portlet-configuration.png)

If you select a page in the *Link Portlet URLs to Page* dropdown menu, all
portlet URLs will point to the page you selected. The current page is the
default. Note that you can use the Asset Publisher's View in a Specific Portlet
feature and web content articles' Display Page attribute to achieve a more
elegant solution for displaying the full view of web content articles on
specific pages. Please see the Display Page section of chapter 5 for details.

You can also choose whether or not to display borders around your portlet. By
default, borders are displayed. Be careful about turning portlet borders off;
some themes assume that portlet borders are turned on and may not display
correctly with them turned off.

The Text Styles tab allows you to configure format of the text that appears in
the portlet. The fonts you can choose from include Arial, Georgia, Times New
Roman, Tahoma, Trebuchet MS, and Verdana. Arial is the default. You can set the
text to bold, italics, or both. You can set the font size anywhere from 0.1 em
to 12 em, with 0.1 em increments. 1 em is the default. You can set the text
color to any six digit hex color code. If you'd like help choosing a color,
click on the pencil icon to open the color palette. You can set the text
alignment to left, center, right, or justified. (Justified text is both left
and right aligned.) You can set an underline, overline, or strikethrough as the
text decoration. The default text decoration is none.

![Figure 7.2: The Text Styles tab lets you configure the format of the text
that appears in the portlet.](../../images/look-and-feel-text-styles.png)

You can set the word spacing anywhere from -1 em to 0.95 em, with 0.5 em
increments. 0 em is the default. You can set the line height anywhere from 0 em
to 12 em, with 0.1 em increments. 0 em is the default. Finally, you can set the
letter spacing anywhere from -10 px to 50 px, with 1 px increments. 0 px is the
default.

The Background Styles tab allows you to specify the portlet's background color.
You can enter any six digit hex color code or you click on the pencil icon to
use the color palette.

![Figure 7.3: The Background Styles tab lets you specify the portlet's
background color.](../../images/look-and-feel-background-styles.png)

On the Border Styles tab, you can configure your portlet's border width, border
style, and border color. For each of these attributes, leave the *Same for All*
box checked to apply the same settings to top, right, bottom, and left borders. 

![Figure 7.4: The Border Styles tab lets you specify a border width, style, and
color for each side of the
portlet.](../../images/look-and-feel-border-styles.png)

For border width, you can specify any % value, em value, or px value. For
border style, you can select dashed, double, dotted, groove, hidden, inset,
outset, ridge, or solid. For border color, you can enter any six digit hex
color code, just like for the text color and background color. You can also use
the color palette.

The Margin and Padding tab allows you to specify margin and padding lengths for
the edges of your portlet. Just like for border styles, leave the *Same for
All* box checked to apply the same settings to each side (top, right, bottom,
and left) of the portlet.

![Figure 7.5: The Margin and Padding tab allows you to specify margin and
paddings lengths for the sides of your
portlet.](../../images/look-and-feel-margin-and-padding.png)

For both padding and margin, you can specify any % value, em value, or px
value.

The Advanced Styling tab displays current information about your portlet,
including your portlet's Liferay ID and CSS classes.

![Figure 7.6: The Advanced Styling tab displays your portlet's Liferay ID and
allows you to enter CSS code to customize the look and feel of your
portlet.](../../images/look-and-feel-advanced-styling.png)

On this tab, you can also enter custom CSS class names for your portlet and
custom CSS code. Clicking the *Add a CSS rule for just this portlet* or *Add a
CSS rule for all portlets like this one* links adds the CSS code shells into
your custom CSS text box. If you check the *Update my styles as I type* box,
your CSS code will be dynamically applied to your portlet so you can see the
effects of your edits.

The WAP Styling tab allows you to specify a custom portlet title that will be
displayed when mobile devices using the Wireless Application Protocol make page
requests. You can also set the initial window state to normal or minimized.
Normal is the default.

![Figure 7.7: The WAP Styling tab lets you enter a custom portlet title to be
displayed to devices making page requests via WAP; it also allows you to
specify an initial window state.](../../images/look-and-feel-wap-styling.png)

Next, let's discuss exporting and importing portlet data.

## Export/Import [](id=export-import)

Some Liferay portlets allow you to export or import portlet data. These include
many of Liferay's collaborative applications, such as the Blogs, Wiki, and
Message Boards portlets. To export or import portlet data, right-click on the
wrench icon of your portlet and select *Export/Import*. Exporting portlet data
produces a `.lar` file that you can save and import into another portlet
applicaton of the same type. To import portlet data, you must select a `.lar`
file. Be careful not to confuse portlet-specific `.lar` files with
site-specific `.lar` files. See the Backing up and Restoring Pages section of
chapter 2 for a discussion of exporting and importing data across an entire
site.

![Figure 7.8: When exporting portlet data, you can choose which categories of
information to include.](../../images/portlet-export.png)

Each portlet has different configuration options. Checking the *Setup* box
selects the portlet's saved configuration for export. Checking the *User
Preferences* box selects saved portlet configurations of specific users. The
*Data* box is the most important one--check this to select your portlet's data
(like blog entries, message board posts, or wiki articles, for example) for
export. When you check the *Data* box, more options appear, allowing you to
choose specific kinds of metadata to include and to select a data range. Check
the *Permissions* box if you'd like to export your the permissions defined for
your portlet. When you check this box, a subbox called *Permissions Assigned to
Roles* appears. If you wish, you can export your portlet's permissions but not
the permissions assigned to roles. Finally, you can check the *Categories* box
to include categories for export. When selected, all categories referenced by
portlet data will be exported or imported, keeping their hierarchy.

![Figure 7.9: When importing portlet data, you can choose which categories of
information to use.](../../images/portlet-import.png)

When you import portlet data, only the data types you select will be
overwriten. If you'd like to import portlet data, you have to select a `.lar`
file. You can import any items that were included when your `.lar` file was
created. Note that user preferences can only be successfully imported when the
user UUIDs match. Additionally, you can import any archived setups into your
portlet, if any. Archived setups provide a means to save multiple portlet
configurations and to switch between them. We discuss archived setups below. If
you check the *Delete portlet data before importing* box, *all* data created by
the portlets will be deleted just before the import process. Be careful, some
portlets on others pages may be referencing this data.

Next, let's discuss the concept of a portlet's scope.

## Scope [](id=scope)

As we learned earlier, roles can be scoped by the portal, by a site, or by an
organization. A role only takes effect within its scope. For example, a Message
Boards Administrator role with complete access to the Message Boards portlet
has different permissions based on the role's scope. If it's a portal role,
members have permission to administer message boards across the entire portal.
If it's a site role, members only have permission to administer message boards
within the site where they've been assigned the role. For organizations with
sites, site roles are automatically assigned to organization members based on
the organization roles they have. So for an organization-scoped Message Boards
administrator role, members only have permission to administer message boards
within the site of the organization that assigned the role to them. 

We also use the word *scope* to refer to the data set of a portlet. By default,
when a portlet is added to a page in a site, it is *scoped* for that site. This
means that its data belongs to that site. If the portlet is added to a page in
a different site, it employs a completely different data set. This enables you
to place a Message Boards portlet in one site with one set of categories and
threads, and place another Message Boards portlet in different site with a
different set of categories and threads.

Scoping by site means that you can only have one Message Boards portlet per
site. If you add one Message Boards portlet to a page in a site and add another
Message Boards portlet to a different page in the same site, the second Message
Boards portlet contains exactly the same data as the first. This is because, by
default, the Message Boards portlet is scoped by site. Most of Liferay's other
portlets also default to being scoped by site.

To avoid this limitation, many Liferay portlets can be scoped by page. In this
case, the data sets of page-scoped portlets serve a single page, not an entire
site. If you set the scope of a portlet to *page* instead of *site*, you can
add any number of these portlets to different pages, and then they have
different sets of data. This allows you to have more than one message board per
site if you wish. Most portlets, however, default to the "native"
configuration, and have their scopes set to the site where they are placed.

Unless otherwise noted, all the portlets in this chapter support scoping by
portal (global), site (default), or page (select layout &rarr; current page).
This grants you some flexibility in how you want to set up your portal. You can
configure the scope of a portlet with just a few simple steps.

1.  Click the *Menu* icon in the portlet window (the wrench).

2.  Select *Configuration*.

3.  Select the *Scope* tab.

4.  Use the drop-down menu to set the scope.

5.  Click *Save*.

![Figure 7.10: Changing the scope of a
portlet](../../images/05-changing-portlet-scope.png)

That's all it takes to change the scope for a particular portlet instance. By
setting the scope to *Current Page*, you can add as many of these portlets to a
site as you want, provided they are all added to separate pages. 

Another useful feature of Liferay's portlets is Archived Setups.  

## Archived Setups [](id=archived-setups)

Once you've configured a portlet, Archived Setups enables you to save those
settings in an "archive". If someone goes in and changes the settings of a
particular portlet, it then becomes easy to revert those changes back to the
original, archived configuration.

To create an archived setup, click the *Configuration* option from the menu in
the portlet's title bar. If the current settings of the portlet you're
configuring are the ones you want to archive, click the *Archive/Restore Setup*
link. If not, change and save the settings until you have the portlet
configured the way you want it, and then click the *Archive/Restore Setup*
link.

There is only one field to fill out. Enter a name for your archive and click
*Save*. You should now see your archive in the list. If you ever need to revert
the portlet to these archived settings, you can click *Actions &rarr; Restore*
next to the archived setup you want to restore.

Unless otherwise noted, all of the portlets in this chapter support this
feature. This is particularly useful for portlets that have a lot of
configuration options, such as the Message Boards portlet. 

Next, we'll see how permissions apply to Liferay portlets in general. 

## Permissions [](id=permissions)

All of Liferay's portlets support Liferay's robust, fine-grained permissions
system. Some higher level permissions can be configured in the permissions tab
of the portlet configuration dialog box. You can grant roles permission to add
the portlet to a page, configure the portlet, or view the portlet. To set these
permissions, go to the *Configuration* menu and click on *Permissions*. This
shows you a table of roles defined in the portal. Use the check boxes to grant
certain permissions to different roles. Click *Submit* after you have made your
selections. 

Beyond this, specific permissions are generally defined for specific
applications. For example, the message boards portlet contains a *Ban User*
permission. This makes no sense in the context of another portlet, say, the
blogs portlet. We'll go over permissions for specific applications in the
sections for those applications. For now, let's move on to sharing
applications.

## Communication [](id=communication)

Liferay implements several communication mechanisms across portlets including
those specified by the JSR-286 standard: public render parameters and events.
Public render parameters are easy to use and can be quite powerful. Some
Liferay portlets provide a configuration UI to help you get the most out of
this communication mechanism. To access this UI, open your portlet's
configuration window by clicking on the wrench icon and selecting
*Configuration*. Then click on the *Communication* tab. 

![Figure 7.11: You can configure portlets to communicate with each other using
public render parameters.](../../images/portlet-communication-tab.png)

The screenshot above is for the Wiki portlet, which has six public render
parameters: categoryId, nodeId, nodeName, resetCur, tag, title. For each of
these parameters, you can configure the portlet to ignore the values coming
from other portlets to read the value from another parameter.

Why might it be useful to ignore the values for certain parameters that come
from other portlets? Consider a common use case for the Wiki portlet. The Wiki
portlet is often used along with the Tags Navigation portlet so that when a
user clicks on a tag of the latter, the Wiki portlet shows a list of pages with
that tag. In some cases, an administrator may want the Wiki portlet to always
show the front page independently of any tag navigation done through other
portlets. This can be achieved by checking the *Ignore* checkbox so that the
values of the parameter coming from those other portlets are ignored.

Reading the value of a parameter from another portlet is an advanced but very
powerful option that allows portlets to communicate with each other even if
their developers didn't intend them to. For example, imagine that the Wiki
portlet is being used to publish information about certain countries. Imagine
further that there's another portlet that allows browsing countries for
administrative reasons. The second portlet has a public render parameter called
*country* with the name of the country. We'd like the Wiki to show the
information from the country that's selected in the administration portlet.
This can be achieved by setting the value of the title parameter of the Wiki
portlet to be read from the country parameter of the administration portlet.
Cool, isn't it?

## Sharing [](id=sharing)

The web was once thought of as a number of islands of applications in a vast
universe of "cyberspace." Many web sites attempted to make their island the
biggest. Some succeeded to a large extent and some failed. More recently, the
concept of the web as an application itself has taken hold, and so widgets have
become very popular nowadays. This concept is part of the "Web 2.0" concept and
is very much enabled by widgets. What is a widget? A widget is a small piece of
code which provides a piece of functionality, can be included on any web site,
but does not necessarily have to be hosted by that web site. If you have ever
embedded a YouTube video on your own web site so that users could watch a video
without actually having to visit [http://youtube.com](http://youtube.com/),
then you've already used a widget.

Liferay supports serving its portlets as widgets. You can embed a particular
instance of a portlet running on your site into another site, such as Facebook.
This opens up a whole new avenue of exposure to your web site that you would
not have had otherwise. In fact, this is how all those Facebook games work.

![Figure 7.12: Sharing Tab of the Portlet Configuration Dialog
Box](../../images/liferay-collaboration-portlet-configuration-sharing.png)

To share one of your portlets as a widget, open the *Configuration* dialog box
from the portlet's title bar and select the *Sharing* tab. There are five
subtabs under sharing: Any Web Site, Facebook, Google Gadget, Netvibes, and
Friends.

### Any Web Site [](id=any-web-site)

Copy and paste the provided snippet of JavaScript code into the web site to
which you want to add the portlet as a widget. That's all you need to do. When
a user loads the page on the other web site, the code will pull the relevant
portlet from your site and display it.

### Facebook [](id=facebook)

You can add any Liferay portlet as an application on Facebook. To do this, you
must first get a developer key. A link for doing this is provided to you in the
Facebook tab. You'll have to create the application on Facebook and get the key
and canvas page URL from Facebook. Once you've done this, you can copy and
paste their values into the Facebook tab. Your portlet will now be available on
Facebook as a Facebook application.

![Figure 7.13: Liferay's Forums on
Facebook](../../images/05-liferay-forum-facebook.png)

Incidentally, this makes Liferay a fantastic platform upon which to build
applications for Facebook. See the *Liferay Developer's Guide* or [*Liferay in
Action*](http://manning.com/sezov) for more details. 

### OpenSocial Gadget [](id=opensocial-gadget)

OpenSocial comprises a container and a set of APIs for social networking and
other web applications. iGoogle is a service provided by Google that lets users
create a customizable page and add *Gadgets* to that page. Liferay can serve up
portlets to be used as Open Social Gadgets on iGoogle or other
OpenSocial-compatible pages.

To serve a Liferay portlet on iGoogle, check the box labeled *Allow users to
add [portlet-name] to iGoogle*. Then copy and paste the URL provided into
Google's *Add a feed or gadget* feature on the iGoogle configuration page. Your
Liferay portal instance will serve that portlet directly onto your iGoogle
page. The URL provided is unique to the specific instance of the portlet, so
you could serve multiple instances of the same portlet as different Google
Gadgets.

You could use this feature to allow users to view what's happening on your
portal at a glance, using asset publishers or custom RSS feeds. You could also
use Liferay's API to build your own portlet and provide the URL for users to
place on their iGoogle pages.

### Netvibes [](id=netvibes)

Netvibes offers a similar service to iGoogle--users can log in, create their
own personal portal, called a *dashboard*, and add customizable widgets to the
dashboard that they create. To set up Netvibes support for a particular
portlet, check the *Allow users to add [portlet-name] to Netvibes pages* box.
You can then use the provided URL to create a custom Netvibes widget based on
the instance of the portlet that you're using.

### Friends [](id=friends)

The final sub-tab of the *Sharing* tab is called *Friends*. This tab has a
single check box that allows you to give your friends permission to add the
application as a widget to another web site. This could be particularly useful
for your blog or calendar if you wish to share them.

Now that we've seen all the common options available in Liferay's portlet
applications, we can move on to specific applications, starting with blogs.
