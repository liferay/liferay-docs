# Using Application Display Templates [](id=using-application-display-templates-lp-6-2-use-useportal)

The application display template (ADT) framework allows portal administrators to
override the default display templates, removing limitations to the way your
site's content is displayed. With ADTs, you can define custom display templates
used to render asset-centric applications. For example, you may want to show
blog entries horizontally instead of vertically, or list your assets in the
asset publisher portlet in different sizes.

Let's go through a simple use case to illustrate how creating a custom ADT can
improve your site. Suppose you're customizing the Lunar Resort site and want to
allow users to communicate with other interested travelers. Specifically, you
want to configure the Wiki portlet for collaboration with Facebook or Twitter.
With ADTs, you can launch a template editor, create a custom template, and
configure your portlet host that template. Custom templates let you re-skin your
portlet and give you ultimate control over its appearance and functionality in
your portal.

Before attempting to change the ADT for your application, you'll need to select
a site for your custom template to reside in. Choosing the *Global* context
makes your template available across all sites. To choose a site to house your
ADT, navigate to the Control Panel &rarr; *Sites* and click on a site from the
list. If you select the Global context, the *Application Display Templates* page
of the Control Panel's Configuration Menu shows you a list of sample templates
available for your portlets. These sample templates differ from the default
templates already configured in the portlets. If you choose a site to host your
template, you must create a custom template for that site's portlets.

![Figure 7.5: In the Control Panel, you can choose the context in which your application display template resides.](../../images/context-selector.png)

If you'd like to add an ADT, select the portlet you'd like to customize. The
list below specifies the portlets that can be customized using ADTs. It also
provides sample template descriptions:

- *Asset Publisher*: displays abstracts, icons, related assets, and print/edit
  actions for assets. Optionally includes asset bookmarks and ratings.
- *Blogs*: displays titles, authors, and abstracts compactly for blog entries.
- *Categories Navigation*: displays a column for each vocabulary. Each column
  includes the name of a vocabulary with the vocabulary's top level categories
  listed underneath.
- *Media Gallery*: displays images in a carousel.
- *Site Map*: displays a column for each top level page. Each column includes
  the name of a top level page with the page's immediate children listed
  underneath.
- *Tags Navigation*: displays asset tags colored by popularity: red (high),
  yellow (medium), and green (low).
- *Wiki*: displays social bookmarks and ratings for wiki pages and their child
  pages.

To create a new ADT, click *Add* and select the template you'd like to create,
based on portlet type. Then enter the name and, optionally, a description and a
small image to use. You can select the language type for your template (FTL or
VM). Lastly, the *Script* option lets you browse your file system for a template
on your file system, or you can launch the editor and create one directly. On
the left side of the template editor, you'll notice a palette of common
variables used for making templates. This is a great reference when creating
your template. To place one of the variables into the template editor, simply
position your cursor where you want it placed, and click the variable name.

Think it can't get any better? Guess again! If the variable name doesn't give
you enough information on the variable's functionality, you can hover your
pointer over it for a more detailed description. Because there are multiple
kinds of ADTs, there are also different variables for each ADT. Thus, each
template has a different set of variables only applicable for that specific
template. 

![Figure 7.6: Liferay offers a versatile script editor to customize your ADT.](../../images/adt-script-editor.png)

You can also use the autocomplete feature to add variables to your template. It
can be invoked by typing *${* which opens a drop-down menu of available
variables. By clicking one of the variables, the editor inserts the variable
into the editor.

You also have the ability to embed same-type templates into other templates. For
example, suppose you have an existing Wiki ADT and would like to create another
similar Wiki ADT. Instead of starting from scratch, you can import the existing
Wiki ADT into your new one and build off of it. In other words, you can utilize
ADTs as generic templates which allow for reusable code to be imported by
Velocity or FreeMarker templates in the system. For more information on how to
create a custom template, visit the [Liferay Developer's Guide](https://www.liferay.com/documentation/liferay-portal/6.2/development).

Another cool feature is the *Export/Import* functionality. You can take
advantage of this feature by clicking the gear icon at the top right of the
screen and selecting *Export/Import*. For more information on using this
feature, visit the
[Export/Import](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/export-import-liferay-portal-6-2-user-guide-04-en)
section of this guide.

After you've completed the initial set up and saved your ADT, you can manage
your ADT through its *Actions* button. This provides several options:

- *Edit*: lets you modify the ADT's setup properties.
- *Permissions*: lets you manage the permissions *Delete*, *Permissions*,
  *Update*, and *View* for the ADT.
- *Copy*: creates a copy of the ADT.
- *Delete*: deletes the ADT.

Additionally, your ADT generates a static URL and a WebDAV URL. These values
access the XML source of your template. You can find these URLs by clicking the
ADT from the menu and expanding the *Details* section. With the WebDAV URL, site
administrators are capable of adding, browsing, editing, and deleting ADTs on a
remote server. If you'd like to learn more about what the WebDAV URL can do,
visit the *Document Management* chapter's *WebDAV access* chapter.

To enable your ADT for a portlet, navigate to the portlet you want to modify and
open its *Configuration* menu. In the *Display Settings* sub-tab located within
the *Setup* tab, select your ADT from the *Display Template* drop-down menu.
You'll notice they're separated by context type. Also, you can manage
site-specific display templates for your portlet: do this by clicking the
*Manage Display Templates for [SPECIFIC_SITE]* link next to the *Display
Template* drop-down menu. A window will display with a list of your configured
templates only available for your site with options to add new templates or edit
existing templates.

![Figure 7.7: In the *Configuration* menu of a portlet, you can edit and manage available ADTs.](../../images/adt-configuration.png)

Now that you know the general functions of ADTs, let's create our own. This
brief demonstration will show you just how easy, yet powerful, ADTs can be for
your portal.

1. Add the Media Gallery portlet to a page by navigating to *Add* &rarr;
   *Content and Applications* &rarr; *Applications* &rarr; *Content Management*
   &rarr; *Media Gallery*.

2. Select the *Options* gear from the top right corner, then click
   *Configuration*.

3. Enable the *Show Actions* and *Show Folder Menu* display settings. Then click
   *Save* and close the popup.

4. Click the *Multiple Media* link and select two custom photos to display. Then
   click *Save*, and navigate back to the main portlet screen.

5. Notice the default format of the pictures. To change the display template for
   this portlet, navigate back to the *Options* gear and click *Configuration*.

6. From the *Display Template* drop-down menu, select *Carousel*. Then click
   *Save*.

	![Figure 7.8: After applying the Carousel ADT, your pictures are displayed as a carousel slideshow.](../../images/adt-carousel.png)

	The Media Gallery portlet is transformed into a carousel slideshow. At this
	time, it's perfectly natural to be experiencing "I can conquer the world"
	feelings, just as Liferay's mascot, Ray, exudes in the image above. ADTs
	have that kind of power to transform your site into an enjoyable and
	convenient home for users.

Customizing the user interface of Liferay's bundled portlets provides the
ultimate customization experience for Liferay users. Next, let's look at how to
use Liferay's rules engine.
