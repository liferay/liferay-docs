# Styling Apps with Application Display Templates [](id=styling-apps-with-application-display-templates)

The application display template (ADT) framework allows Liferay administrators
to override the default display templates, removing limitations to the way your
site's content is displayed. With ADTs, you can define custom display templates
used to render asset-centric applications. For example, you may want to show
blog entries horizontally instead of vertically, or list your assets in the
asset publisher application in different sizes.

You'll go through a simple use case to illustrate how creating a custom ADT can
improve your site. Suppose you're customizing the Lunar Resort site and want to
allow users to communicate with other interested travelers. Specifically, you
want to configure the Wiki app for collaboration with Facebook or Twitter. With
ADTs, you can launch a template editor, create a custom template, and configure
your app host that template. Custom templates let you re-skin your application
and give you ultimate control over its appearance and functionality in your
Liferay instance.

Before attempting to change the ADT for your application, you'll need to select
a site for your custom template to reside in. Choosing the *Global* context
makes your template available across all sites. To choose a site to house your
ADT, navigate to the Site Administration dropdown list by selecting the
*Compass* icon (![Compass](../../images/icon-compass.png)). If you select the
Global context, the *Application Display Templates* page of Site
Administration's Configuration Menu shows you a list of sample templates
available for your apps. These sample templates differ from the default
templates already configured in the apps. If you choose a site to host your
template, you must create a custom template for that site's apps.

![Figure 1: The Site Administration dropdown menu lets you choose the context in which your application display template resides.](../../images/context-selector.png)

If you'd like to add an existing ADT, select the template from the list. The
list below specifies the apps that can be customized using ADTs.

- *Asset Publisher*
- *Blogs*
- *Breadcrumb*
- *Categories Navigation*
- *Documents and Media*
- *Language Selector*
- *Navigation Menu*
- *RSS Publisher*
- *Site Map*
- *Tags Navigation*
- *Wiki*

To create a new ADT, click *Add* and select the template you'd like to create,
based on application type. Then enter the name and, optionally, a description
and a small image to use. You can select the language type for your template
(FTL or VM). Lastly, the *Script* option lets you browse your file system for a
template on your file system, or you can use the editor and create one directly.
On the left side of the template editor, you'll notice a palette of common
variables used for making templates. This is a great reference when creating
your template. To place one of the variables into the template editor, simply
position your cursor where you want it placed, and click the variable name.

Think it can't get any better? Guess again! If the variable name doesn't give
you enough information on the variable's functionality, you can hover your
pointer over it for a more detailed description. Because there are multiple
kinds of ADTs, there are also different variables for each ADT. Thus, each
template has a different set of variables only applicable for that specific
template. 

![Figure 2: Liferay offers a versatile script editor to customize your ADT.](../../images/adt-script-editor.png)

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
create a custom template, visit the
[Implementing Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/implementing-application-display-templates)
tutorial.

<!--TODO: Update tutorial link above for 7.0, when available. -Cody -->

After you've completed the initial set up and saved your ADT, you can manage
your ADT through its *Actions* (![Actions](../../images/icon-actions.png))
button. This provides several options:

- *Edit*: lets you modify the ADT's setup properties.
- *Permissions*: lets you manage the permissions *Update*, *Permissions*,
  *Delete*, and *View* for the ADT.
- *Copy*: creates a copy of the ADT.
- *Delete*: deletes the ADT.

Additionally, your ADT generates a static URL and a WebDAV URL. These values
access the XML source of your template. You can find these URLs by clicking the
ADT from the menu and expanding the *Details* section. With the WebDAV URL, site
administrators are capable of adding, browsing, editing, and deleting ADTs on a
remote server. If you'd like to learn more about what the WebDAV URL can do,
visit the section on
[WebDAV access](https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/publishing-files#desktop-access-to-documents-and-media).

+$$$

**Note:** Embedding apps into ADTs, although possible, is not recommended
because this could cause conflicts with other apps or unexpected behavior (e.g.,
embedding an app that aggregates data to the breadcrumb). If embedding an app
into an ADT is your only option, make sure it does not interfere with other
apps.

$$$

To enable your ADT for an application, navigate to the app you want to modify
and open its *Configuration* menu. In the *Setup* tab, select your ADT from the
*Display Template* drop-down menu. Also, you can manage site-specific display
templates for your app: do this by clicking the *Manage Display Templates for
[SPECIFIC_SITE]* link next to the *Display Template* drop-down menu. A window
will display with a list of your configured templates only available for your
site with options to add new templates or edit existing templates.

![Figure 3: In the *Configuration* menu of an app, you can edit and manage available ADTs.](../../images/adt-configuration.png)

Now that you know the general functions of ADTs, you'll create your own. This
brief demonstration will show you just how easy, yet powerful, ADTs can be for
your Liferay instance.

1. Add the Media Gallery application to a page by navigating to *Add*
   (![Add](../../images/icon-control-menu-add.png)) &rarr; *Applications*
   &rarr; *Content Management* &rarr; *Media Gallery*.

2. Click the app's *Add* button (![Actions](../../images/icon-app-add.png))
   &rarr; *Multiple Media* and select two custom photos to display. Then click
   *Save*, and navigate back to the main application screen.

3. Notice the default format of the pictures. To change the display template for
   this app, navigate to *Options*
   (![Options](../../images/icon-app-options.png)) &rarr; *Configuration*.

4. From the *Display Template* drop-down menu, select *Carousel*. Then click
   *Save*.

	![Figure 4: After applying the Carousel ADT, your pictures are displayed as a carousel slideshow.](../../images/adt-carousel.png)

	The Media Gallery application is transformed into a carousel slideshow. At
	this time, it's perfectly natural to be experiencing "I can conquer the
	world" feelings, just as Liferay's mascot, Ray, exudes in the image above.
	ADTs have that kind of power to transform your site into an enjoyable and
	convenient home for users.

Customizing the user interface of @product@'s bundled apps provides the ultimate
customization experience for Liferay users.
