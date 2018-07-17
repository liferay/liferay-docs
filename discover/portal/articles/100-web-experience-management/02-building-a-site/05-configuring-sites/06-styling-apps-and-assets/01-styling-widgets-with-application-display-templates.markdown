# Styling Widgets with Application Display Templates [](id=styling-widgets-with-application-display-templates)

Suppose you're customizing the Lunar Resort Site and want to allow users to use 
Facebook or Twitter to communicate with other interested travelers. You can add
this functionality to an existing widget using ADTs: launch a template editor,
create a custom template, and configure your app to host that template. ADTs
let you re-skin your application and give you ultimate control over its
appearance and functionality.

## Creating an ADT [](id=creating-an-adt)

Here's the process of creating an ADT:

1.  From Site Administration, click the *Site Selector* button 
    (![Compass](../../../../../images/icon-compass.png)) to choose the Site where you
    want to create the ADT. 
 
2.  Open *Build* &rarr; *Application Display Templates*. 
 
If you selected the Global context, this page shows a list of sample templates
available for your apps. These sample templates differ from the default
templates already configured in the apps. If you choose a Site to host your
template, you must create a custom template for that Site's apps.

![Figure 1: The Site Administration dropdown menu lets you choose the context in which your application display template resides.](../../../../../images/context-selector.png)

3.  Click the *Add*
    (![Add](../../../../../images/icon-add-app.png)) button, and you're 
    prompted to select the type of template to create.

- *Asset Publisher*
- *Blogs*
- *Breadcrumb*
- *Categories Navigation*
- *Language Selector*
- *Media Gallery*
- *Navigation Menu*
- *RSS Publisher*
- *Site Map*
- *Tags Navigation*
- *Wiki*

4.  Enter the name and, optionally, open *Details* to provide a description and
    a small image to use. You can select the language type for your template.
 
5.  Within *Details* select a scripting language to use. You can use Freemarker
    or Velocity. Freemarker is recommended. 
 
6.  Use the *Script* section to create the ADT.

7.  Click *Save* when done.

### The Template Editor [](id=the-template-editor)

On the left side of the template editor is a palette of common variables used
for making templates. This is a great reference when creating your template. To
place one of the variables into the template editor, position your text cursor
where you want it placed, and click the variable name.

Each variable also has a tooltip which displays a detailed description. Because
there are multiple kinds of ADTs, there are also different variables for each
ADT. Thus, each template has a different set of variables only applicable for
that specific template. 

![Figure 2: Liferay offers a versatile script editor to customize your ADT.](../../../../../images/adt-script-editor.png)

You can also use the autocomplete feature to add variables to your template. It
can be invoked by typing *${* which opens a drop-down menu of available
variables. By clicking one of the variables, the editor inserts the variable
into the editor.

You can also embed same-type templates into other templates. For example,
suppose you have an existing Wiki ADT and would like to create another similar
Wiki ADT. Instead of starting from scratch, you can import the existing Wiki ADT
into your new one and build off of it. In other words, you can utilize ADTs as
generic templates which allow for reusable code to be imported by Velocity or
FreeMarker templates in the system. For more information on how to create
a custom template, visit the 
[Implementing Application Display Templates](/develop/tutorials/-/knowledge_base/7-1/implementing-application-display-templates)
tutorial.

## Configuring ADTs [](id=configuring-adts)

After you've saved your ADT, you can manage it through its *Actions*
(![Actions](../../../../../images/icon-actions.png)) button. This provides
several options:

- *Edit*: lets you modify the ADT's setup properties.
- *Permissions*: lets you manage the permissions *Update*, *Permissions*,
  *Delete*, and *View* for the ADT.
- *Copy*: creates a copy of the ADT.
- *Delete*: deletes the ADT.

Additionally, your ADT generates a static URL and a WebDAV URL. These values
access the XML source of your template. You can find these URLs by clicking the
ADT from the menu and expanding the *Details* section. With the WebDAV URL, Site
administrators can add, browse, edit, and delete ADTs on a remote server. If you
want to learn more about what the WebDAV URL can do, visit the tutorial on
[WebDAV access](/discover/portal/-/knowledge_base/7-1/desktop-access-to-documents-and-media). 

+$$$

**Note:** Embedding widgets into ADTs, although possible, is not recommended
because this could cause conflicts with other widgets or unexpected behavior 
(e.g., embedding a widget that aggregates data to the breadcrumb). If embedding 
a widget into an ADT is your only option, make sure it does not interfere with 
other widgets.

$$$

Next you must configure the widget to use the new ADT:

1.  Go to the *Configuration* page for the application you want to modify and
    open its *Display Settings*.

2.  Under *Display Template*, select your ADT from the drop-down menu.

Also, you can manage Site-specific display templates for your app: do this by 
clicking the *Manage Display Templates for [SPECIFIC_SITE]* link next to the
*Display Template* drop-down menu. A window appears with a list of your
configured templates only available for your Site with options to add new
templates or edit existing templates.

![Figure 3: In the *Configuration* menu of an app, you can edit and manage available ADTs.](../../../../../images/adt-configuration.png)
