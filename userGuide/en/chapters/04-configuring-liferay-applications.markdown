
# Configuring Liferay Applications [](id=configuring-liferay-applications-liferay-portal-6-2-user-guide-04-en)

Just like siblings have common features inherited from their parents,
applications that ship with Liferay also share common features. These include
look and feel, communication, scoping, sharing, permissions, archive
configurations, exporting/importing portlet data, and the Recycle Bin. We'll
discuss how these features work together to facilitate information flow within
your portal and provide an enhanced experience for your users. So before we get
into the nitty gritty of the applications themselves, it's best to cover these
common features first, starting with the look and feel configuration options.

## Look and Feel [](id=look-and-feel-liferay-portal-6-2-user-guide-04-en)

An administrator can access the look and feel configuration menu of any Liferay
portlet by clicking on the gear icon at the top right corner of the portlet and
selecting *Look and Feel*. The location of the gear icon may vary, depending on
your theme. Liferay portlets' look and feel dialog boxes contain six tabs:

- Portlet Configuration
- Text Styles
- Background Styles
- Border Styles
- Margin and Padding
- Advanced Styling

After making customizations on any tab, remember to click the *Save* button to
apply your changes. To see the effect of your changes, you may have to refresh
the page. If you don't like the effect of your changes, click the *Reset*
button to discard them.

On the Portlet Configuration tab, you can check the *Use Custom Title* box to
rename your portlet's title. The value you enter in the Portlet Title box will
be displayed at the top of the portlet window on the page. You can also select
a language from the Portlet Title drop-down menu. If you've provided a language
key translation for the language you select, the your portlet's title will be
displayed in the selected language.

![Figure 4.1: The Portlet Configuration tab of the Look and Feel Box allows you to define a custom portlet title, link portlet URLs to a specific page, and select whether or not portlet borders should be displayed.](../../images/look-and-feel-portlet-configuration.png)

If you select a page in the *Link Portlet URLs to Page* drop-down menu, all
portlet URLs will point to the page you selected. The current page is the
default. Note that you can use the Asset Publisher's View in a Specific Portlet
feature and web content articles' Display Page attribute to achieve a more
elegant solution for displaying the full view of web content articles on
specific pages. Please see the Display Page section of chapter 5 for details.

---

 ![Note](../../images/01-tip.png) **Note:** The *Link Portlet URLs to Page* menu
 is deprecated for Liferay 6.2 and will be removed in Liferay 7.0. The
 functionality of this menu can also be found in the Asset Publisher. 

---

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
click on the text box to open the color palette. You can set the text
alignment to left, center, right, or justified. (Justified text is both left
and right aligned.) You can set an underline, overline, or strikethrough as the
text decoration. The default text decoration is none.

![Figure 4.2: The Text Styles tab lets you configure the format of the text that appears in the portlet.](../../images/look-and-feel-text-styles.png)

You can set the word spacing anywhere from -1 em to 0.95 em, with 0.5 em
increments. 0 em is the default. You can set the line height anywhere from 0 em
to 12 em, with 0.1 em increments. 0 em is the default. Finally, you can set the
letter spacing anywhere from -10 px to 50 px, with 1 px increments. 0 px is the
default.

The Background Styles tab allows you to specify the portlet's background color.
When you select the text space, you're given a color palette to choose your
background color or you can manually enter any six digit hex color code.

![Figure 4.3: The Background Styles tab lets you specify the portlet's background color.](../../images/look-and-feel-background-styles.png)

On the Border Styles tab, you can configure your portlet's border width, border
style, and border color. For each of these attributes, leave the *Same for All*
box checked to apply the same settings to top, right, bottom, and left borders. 

![Figure 4.4: The Border Styles tab lets you specify a border width, style, and color for each side of the portlet.](../../images/look-and-feel-border-styles.png)

For border width, you can specify any % value, em value, or px value. For
border style, you can select dashed, double, dotted, groove, hidden, inset,
outset, ridge, or solid. For border color, you can enter any six digit hex
color code, just like for the text color and background color. You can also use
the color palette.

The Margin and Padding tab allows you to specify margin and padding lengths for
the edges of your portlet. Just like for border styles, leave the *Same for
All* box checked to apply the same settings to each side (top, right, bottom,
and left) of the portlet.

![Figure 4.5: The Margin and Padding tab allows you to specify margin and padding lengths for the sides of your portlet.](../../images/look-and-feel-margin-and-padding.png)

For both padding and margin, you can specify any % value, em value, or px
value.

The Advanced Styling tab displays current information about your portlet,
including your portlet's Liferay ID and CSS classes.

![Figure 4.6: The Advanced Styling tab displays your portlet's Liferay ID and allows you to enter CSS code to customize the look and feel of your portlet.](../../images/look-and-feel-advanced-styling.png)

On this tab, you can also enter custom CSS class names for your portlet and
custom CSS code. Clicking the *Add a CSS rule for just this portlet* or *Add a
CSS rule for all portlets like this one* links adds the CSS code shells into
your custom CSS text box. If you check the *Update my styles as I type* box,
your CSS code will be dynamically applied to your portlet so you can see the
effects of your edits.

For Liferay 6.2, the WAP Styling functionality has been deprecated. Liferay
Portal uses a new responsive design making WAP Styling configuration unnecessary
for your portlet's look and feel. You can enable the WAP functionality for your
portal's Look and Feel section by opening/creating your `portal-ext.properties`
file in your `${LIFERAY_HOME}` directory and setting
`mobile.device.styling.wap.enabled=true`. WAP functionality will be completely
removed from Liferay in the next release.

Next, let's discuss exporting and importing portlet data.

## Export/Import [](id=export-import-liferay-portal-6-2-user-guide-04-en)

<!-- This section needs to be tested against the new AlloyUI -->

Some Liferay portlets allow you to export or import portlet data. These include
many of Liferay's collaborative applications, such as the Blogs, Wiki, and
Message Boards portlets. To export or import portlet data, click on the gear
icon of your portlet and select *Export/Import*. Exporting portlet data produces
a `.lar` file that you can save and import into another portlet application of
the same type. To import portlet data, you must select a `.lar` file. Be careful
not to confuse portlet-specific `.lar` files with site-specific `.lar` files.
See the Backing up and Restoring Pages section of chapter 2 for a discussion of
exporting and importing data across an entire site. Let's explore the export
process first.

![Figure 4.7: When exporting portlet data, you can choose what content to include.](../../images/portlet-export.png)

First, you can select a *Date Range* of content that you'd like to export.
Content that has been added to your portlet within your specified date range is
included in the `.lar` file. You also have options of choosing *All* dates or
just the most recently added content, or *Last*.

Next, by checking the *Content* box, you can choose specific content you'd like
to export. When you check the *Content* box, more options appear, allowing you
to choose specific kinds of metadata to include. For example, if you have a wiki
page with referenced content that you don't wish to include, you can simply
check the *Wiki Pages* checkbox and uncheck the *Referenced Content* checkbox.
Another option in the Content section of the Export/Import window is the
selection of content types. Two familiar content types in your portal is
*Comments* and *Ratings*. If you wish to include these entities in your `.lar`
file, select *Change* and select them from the checklist.

Lastly, you can choose whether to include permissions for your exported content.
The permissions assigned for the exported portlet window will be included if the
*Permissions* option is checked. When you check this box, a sub-box called
*Permissions Assigned to Roles* appears. If you wish, you can export your
portlet's permissions but not the permissions assigned to roles. After you've
exported your portlet's data, switch to the *Current and Previous* tab to view
ongoing export processes and the history of past exports.

![Figure 4.8: When importing portlet data, you can choose a LAR file using the file explorer or drag and drop the file between the dotted lines.](../../images/portlet-import.png)

To import portlet data, you can select the LAR using your file explorer or by
dragging and dropping the file between the dotted lines. After selecting the LAR
file, you're given a similar screen to what you'd be offered during export.
Select the appropriate content and permissions, and click *Continue*.

The next screen offers options split into two sections -- *Update Data* and
*Authorship of the Content*. Here's options and descriptions for each section:

**Update Data**

* *Mirror*: All data and content inside the imported LAR will be created as new
the first time while maintaining a reference to the source. Subsequent imports
from the same source will update entries instead of creating new entries.
* *Mirror with overwriting*: Same behavior as the mirror strategy, but if a
document or an image with the same name is found, it is overwritten.
* *Copy as New*: All data and content inside the imported LAR will be created as
new entries within the current site every time the LAR is imported.

**Authorship of the Content**

* *Use the Original Author*: Keep authorship of imported content whenever
possible. Use the current user as author if the original one is not found.
* *Use the Current User as Author*: Assign the current user as the author of all
imported content.

Next, let's discuss the concept of a portlet's scope.

## Scope [](id=scope-liferay-portal-6-2-user-guide-04-en)

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
portal (global), site (default), or page . This grants you some flexibility in
how you want to set up your portal. You can configure the scope of a portlet
with just a few simple steps.

1.  Click the *Options* icon in the portlet window (the gear icon).

2.  Select *Configuration*.

3.  Select the *Scope* tab.

4.  Use the drop-down menu to set the scope.

![Figure 4.9: You can change the scope of your portlet by navigating to its Configuration menu.](../../images/05-changing-portlet-scope.png)

That's all it takes to change the scope for a particular portlet instance. By
setting the scope to the current page, you can add as many of these portlets to a
site as you want, provided they are all added to separate pages. 

Another useful feature of Liferay's portlets is Archived Setups.  

## Archived Setups [](id=archived-setups-liferay-portal-6-2-user-guide-04-en)

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

## Permissions [](id=permissions-liferay-portal-6-2-user-guide-04-en)

All of Liferay's portlets support Liferay's robust, fine-grained permissions
system. Some higher level permissions can be configured in the permissions tab
of the portlet configuration dialog box. You can grant roles permission to add
the portlet to a page, configure the portlet, or view the portlet. To set these
permissions, go to the *Configuration* menu and click on *Permissions*. This
shows you a table of roles defined in the portal. Use the check boxes to grant
certain permissions to different roles. Click *Save* after you've made your
selections. 

Beyond this, specific permissions are generally defined for specific
applications. For example, the message boards portlet contains a *Ban User*
permission. This makes no sense in the context of another portlet, say, the
blogs portlet. We'll go over permissions for specific applications in the
sections for those applications. For now, let's move on to sharing
applications.

## Communication [](id=communication-liferay-portal-6-2-user-guide-04-en)

Liferay implements several communication mechanisms across portlets including
those specified by the JSR-286 standard: public render parameters and events.
Public render parameters are easy to use and can be quite powerful. Some
Liferay portlets provide a configuration UI to help you get the most out of
this communication mechanism. To access this UI, open your portlet's
configuration window by clicking on the gear icon and selecting
*Configuration*. Then click on the *Communication* tab. 

![Figure 4.10: You can configure portlets to communicate with each other using public render parameters.](../../images/portlet-communication-tab.png)

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

## Sharing [](id=sharing-liferay-portal-6-2-user-guide-04-en)

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

![Figure 4.11: The Sharing tab of the Portlet Configuration Dialog Box allows you to share your portlet in a variety of ways.](../../images/liferay-collaboration-portlet-configuration-sharing.png)

To share one of your portlets as a widget, open the *Configuration* dialog box
from the portlet's title bar and select the *Sharing* tab. There are five
sub-tabs under sharing: Any Web Site, Facebook, OpenSocial Gadget, Netvibes, and
Friends.

### Any Web Site [](id=any-web-site-liferay-portal-6-2-user-guide-04-en)

Copy and paste the provided snippet of JavaScript code into the web site to
which you want to add the portlet as a widget. That's all you need to do. When
a user loads the page on the other web site, the code will pull the relevant
portlet from your site and display it.

### Facebook [](id=facebook-liferay-portal-6-2-user-guide-04-en)

You can add any Liferay portlet as an application on Facebook. To do this, you
must first get a developer key. A link for doing this is provided to you in the
Facebook tab. You'll have to create the application on Facebook and get the key
and canvas page URL from Facebook. Once you've done this, you can copy and
paste their values into the Facebook tab. Your portlet will now be available on
Facebook as a Facebook application.

![Figure 4.12: Liferay's Forums on Facebook is an example of sharing the Message Boards portlet.](../../images/05-liferay-forum-facebook.png)

Incidentally, this makes Liferay a fantastic platform upon which to build
applications for Facebook. See the *Liferay Developer's Guide* or [*Liferay in
Action*](http://manning.com/sezov) for more details. 

### OpenSocial Gadget [](id=opensocial-gadget-liferay-portal-6-2-user-guide-04-en)

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

#### Netvibes [](id=netvibes-liferay-portal-6-2-user-guide-04-en)

Netvibes offers a similar service to iGoogle--users can log in, create their
own personal portal, called a *dashboard*, and add customizable widgets to the
dashboard that they create. To set up Netvibes support for a particular
portlet, check the *Allow users to add [portlet-name] to Netvibes pages* box.
You can then use the provided URL to create a custom Netvibes widget based on
the instance of the portlet that you're using.

#### Friends [](id=friends-liferay-portal-6-2-user-guide-04-en)

The final sub-tab of the *Sharing* tab is called *Friends*. This tab has a
single check box that allows you to give your friends permission to add the
application as a widget to another web site. This could be particularly useful
for your blog or calendar if you wish to share them.

Next, let's explore what the Recycle Bin does for your Liferay applications.

## Recycling Assets with the Recycle Bin [](id=recycling-assets-with-the-recycle-bin-liferay-portal-6-2-user-guide-04-en)

Have you ever had that life-altering experience where you deleted an important
file and immediately regretted deleting it? The deed is usually followed by a
palm to the forehead or a sick feeling. Good news! Liferay is here to turn that
frown upside down with the *Recycle Bin* feature. With the Recycle Bin, the
*Move to the Recycle Bin* action replaces *Delete* for certain asset types.
Content is now temporarily stored in the Recycle Bin. This allows the content to
be restored back to its original state. Recycled items can expire after a
certain period of time, resulting in their permanent deletion. Before diving
into how the Recycle Bin works, let's look at how to configure it.

### Configuring the Recycle Bin [](id=configuring-the-recycle-bin-liferay-portal-6-2-user-guide-04-en)

To begin using the Recycle Bin, you must enable it where you plan to use it. The
Recycle Bin supports portal-wide scope or site-specific scope. The portal-wide
scope of the Recycle Bin is set by adding the `trash.enabled` property to your
`portal-ext.properties` file. We'll go into more detail for adding this property
and several others to your properties file later in the section. First, let's
explore the UI and see what the Recycle Bin can do.

First, let's configure the Recycle Bin for site-specific scoping. Choose the
site you'd like configure for the Recycle Bin from *My Sites* in the Dockbar.
Then click *Admin* &rarr; *Configuration* to navigate to the *Site Settings*
page. Next, click *Recycle Bin* on the right-side menu under the Advanced
heading. You'll notice a few configurable options:

**Enable Recycle Bin:** enable and disable settings for the Recycle Bin's
site-specific scope.

**Trash Entries Max Age:** customize the number of days a file is kept in the
Recycle Bin until its permanent deletion (default is 30 days).

![Figure 4.13: The Recycle Bin offers several configurable options for your site.](../../images/recycle-bin-site-settings.png)

When you've finished configuring your Recycle Bin settings, click *Save*.

---

 ![note](../../images/tip.png) **Note:** If you disable the Recycle Bin while
 it's still holding recycled items, the recycled items reappear in the Recycle
 Bin if it is re-enabled.

---

You can also configure the Recycle Bin via properties in the `portal.properties`
file. Remember that it's a best practice not to edit the `portal.properties`
directly, but to create a separate `portal-ext.properties` file containing the
properties to override. There are some additional options not available in the
GUI that you can set:

`trash.search.limit=500`: set the limit for results used when performing
searches in the Recycle Bin (default is 500).

`trash.entry.check.interval=60`: set the interval in minutes for how often the
trash handler runs to delete trash entries that have been in the Recycle Bin
longer than the maximum age (default is 60).

Also, as we mentioned earlier, there is a property to enable the Recycle bin
portal-wide.

`trash.enabled=true`: set this property to true to enable the Recycle Bin for
all sites in the portal while allowing site administrators to disable it per
site.

<!-- Visit the `portal.properties` file at <link> to view all of the
configurable properties for the Recycle Bin. -->

Next, you should make sure permissions are set properly for users who can
handle/view the assets in the Recycle Bin. Users who had *View* permissions on
a document when it was recycled can also view that document in the Recycle
Bin. Users who had *Update* or *Delete* permissions on a document when it was
recycled can restore the document. 

Now that you've successfully configured the Recycle Bin, let's look at how to
use it.

### Using the Recycle Bin [](id=using-the-recycle-bin-liferay-portal-6-2-user-guide-04-en)

The Recycle Bin is temporary storage configured for multiple asset types across
the portal. Instead of offering a specific Recycle Bin for each asset type,
Liferay provides a central master Recycle Bin where different asset types can be
stored. This provides an easy search and recovery process.

![Figure 4.14: The Recycle Bin provides a seamless administrative experience for deleting and removing content.](../../images/recycle-bin-overview.png)

You can recycle several different types of assets, including:

- Blogs
- Bookmarks
- Documents and Media
- Message Boards (and attachments)
- Web Content
- Wiki (and attachments)

<!-- TODO: Planned for RB compatibility - Calendar (LPS-35264), Attachments (for
wiki and MB) (LPS-28165 -> still some kinks), Pages (LPS-29964), Structures
(LPS-29970), Templates (LPS-29970). -->

<!-- NOTE: The wiki/MB attachments can currently be recycled, but the recycle
button is currently listed as *Remove*. The attachment does not get sent to the
Recycle Bin, but can be restored from within the Wiki/MB portlet. I assume this
gets changed to the consistent *Move to the Recycle Bin* expression and sent to
the Recycle Bin when the attachments feature is fully supported. -->

For a quick example to show how easy the Recycle Bin is to use, let's send a web
content article to the Recycle Bin and then restore it. We'll run through two
different methods of restoring the file.

1. Navigate to *Site Administration* &rarr; *Content* &rarr; *Web Content*.

2. Select the *Add* button and click *Basic Web Content*.

3. Enter some text for the Title and Content and click *Publish*.

4. In the top right corner of the web content, select the arrow and click *Move
to the Recycle Bin*.

	Note that the *Delete* button is not listed. Liferay avoids the risk of
	accidental deletion of your files by funneling the content through the
	Recycle Bin.

5. After deleting the file, a success message appears, offering an *Undo*
option. Click *Undo*. The web content is retrieved from the Recycle Bin and
stored in its original place.

6. Select the *Move to the Recycle Bin* button again.

7. Navigate back to Site Administration and click the Recycle Bin button from
the menu.

8. Find your sample web content and click its *Actions* tab.

9. You can restore or delete the content. Select *Restore*.

	![Figure 4.15: In the Recycle Bin, you have the option of restoring or permanently deleting the content.](../../images/recycle-bin-restore.png)

10. Navigate back to the Web Content screen and notice that your sample web
content was restored back to its original place.

Congratulations! You now know the two general processes of sending and restoring
content to/from the Recycle Bin. For other asset types, the Recycle Bin works
similarly.

Some Liferay applications, such as Web Content and Documents and Media, support
folders into which their content can be organized. You can also send folders and
sub-folders to the Recycle Bin. Keep in mind that this sends the entire
folder/sub-folder structure and all files to the Recycle Bin. Folders and
sub-folders are restored and deleted the same way as a single file. Also,
expired web content can be moved to the Recycle Bin and restored, just like a
regular file.

In step 9 of the example, you probably noticed the *Delete* button from within
the Recycle Bin. This is the permanent delete button. Once you select this, your
file cannot be retrieved and is gone forever. There is also an *Empty the
Recycle Bin* button located at the top of the Recycle Bin screen. This
permanently deletes all the files from the Recycle Bin. Next, let's discuss how
to use the drag and drop feature.

#### Drag and Drop [](id=drag-and-drop-liferay-portal-6-2-user-guide-04-en)

A quick and easy way to dispose of your unwanted assets is to drag and drop them
into the Recycle Bin. While you're in the Control Panel, you can simply select
an asset and drag it to the Recycle Bin portlet located on the Control Panel
menu. When you click and begin dragging the asset, a message appears near your
cursor notifying you of the amount of files ready to be moved, and the
Recycle Bin is highlighted, showing you where the files can be dropped. After
you drop the asset onto the Recycle Bin portlet, the asset is removed from its
original location and transferred to the Recycle Bin.

![Figure 4.16: A quick and easy way of disposing your items is the drag and drop method.](../../images/recycle-bin-drag.png)

Next, let's explore the Recycle Bin's intelligence and behind the scenes support
that aids in a seamless recycling experience.

#### Recycle Bin intelligence and support [](id=recycle-bin-intelligence-and-support-liferay-portal-6-2-user-guide-04-en)

Have you ever wondered if it's possible to check the IQ of a software feature?
Unfortunately, there is no tangible way to do this; however, if there were, the
Liferay Recycle Bin would be at the top of its class. As we've mentioned
already, it supports multiple asset types, a drag and drop feature, an Undo
option, and many more. Have you ever wondered what happens to file shortcuts if
their linked assets are recycled? What if you restore a file that has the same
name as another file currently stored in your site/portal? The Recycle Bin
already knows how to handle these types of issues for a seamless user
experience.

When documents with shortcuts are moved to the Recycle Bin, the shortcuts are
invalidated. This ensures your portal has active links and shortcuts at all
times. This cuts down on maintenance time and backtracking after recycling an
important asset.

Another important trait is the duplicate name recognition feature. When a file
is restored, the Recycle Bin scans the corresponding asset type files currently
in the site/portal to check for duplicate file names. If a duplicate file name
is found, the Recycle Bin prompts you to overwrite the existing file or rename
the file name you're trying to restore. 

For example, suppose you have the document `file1` stored in the Recycle Bin and
you have a separate document you created later with the same name in the
document library. If you try to restore the `file1` document, the Recycle Bin
recognizes duplicate names and prompts you to overwrite the existing document
in the document library or rename the document you're trying to restore.

![Figure 4.17: The Recycle Bin always scans your site/portal for duplicate file names during the restoration process.](../../images/recycle-bin-duplicate-name.png)

Although the Recycle Bin prohibits the restoration of files that match
pre-existing file names in your site/portal, it will store files with matching
names.

Have you thought about how the Recycle Bin works during the staging process?
Although we stated earlier that there is only one master Recycle Bin, the
staging process requires a bit more flexibility with the Recycle Bin to maximize
its productivity. Therefore, when staging is enabled, there is a new and
separate Recycle Bin: the *Staging* Recycle Bin. The original Recycle Bin, or
*Live* Recycle Bin, holding unstaged material is still viewable while in
staging; however, it is never used.

![Figure 4.18: During staging, a separate Recycle Bin appears to keep staging and live content separate.](../../images/recycle-bin-staging.png)

During staging, everything you recycle is sent to the Staging Recycle Bin. This
prevents staged and unstaged recycled content from mixing. Do you see why this
would be a problem? Consider you have an unstaged document currently on your
live site. Next, you enable staging and delete that document. If you were to
turn staging off and return to the live site, without separate Recycle Bins, the
live document would be located on your site and in the Recycle Bin! Because of
this, the separate Staging Recycle Bin is necessary and only used during the
staging process. Finally, when you publish your staged material to live, the
Staging Recycle Bin content is transferred to the Live Recycle Bin.

<!-- TODO: The Staging RB is still being produced and an accurate snapshot of
options provided by the RB when publishing to Live aren't final. Need to
check-up on progress periodically. (Bug in LP build, need to test when snapshot
appears the same as in LPS-26266.) -->

---

 ![note](../../images/tip.png)**Note:** The Staging Recycle Bin saves its
contents until the staged material has been published to live. This means that
you can turn the staging mode on and off without losing your recycled material.

---

The Recycle Bin saves you time by letting you restore content that's been
recycled. Instead of recreating or re-uploading content, you'll be tailoring
your Liferay portal to fully leverage its capabilities.

## Summary [](id=summary-liferay-portal-6-2-user-guide-04-en)

In this chapter, we explored the configuration options available for Liferay
applications. We showed how to customize your applications, export/import
portlet data, take advantage of different scopes, and communicate between
portlets. We also examined the different uses of social applications like
Facebook and Netvibes for your Liferay applications. Besides the general
configuration options, we also explored the Recycle Bin. This feature is a
special file directory allowing users to browse, retrieve, or permanently delete
recycled content. In all, Liferay gives you an abundance of options to leverage
the full capability of your applications.

