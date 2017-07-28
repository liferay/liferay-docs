# Creating Data Definitions [](id=creating-data-definitions)

<!-- Renumber figures, numbers and captions. -->

Imagine this: The Lunar Resort is becoming a popular destination. The Recreation
Department's offerings (hiking, lunar golf, rover races, and more) are very
popular with the adventurous guests, and it's becoming difficult for Recreation
Department employees to keep up with demand. An unexpected number of
participants in a lunar hike makes it so that the Lunar Sherpa (hiking guide)
has to scramble to find enough space suits for all the guests involved, and the
kitchen staff needs to rummage up more food at the last minute so that the
hikers have enough to eat. The lack of efficiency could be dangerous, and cost
the resort much needed revenue if guests are unhappy with the experience.  The
resort needs a way for guests to sign up for events ahead of time. If you're
thinking that means you need to hire developers to build you an app, you're
wrong! Liferay gives you, the administrator (or anyone with the proper
permissions), the power to build a simple list app using the Dynamic Data Lists
application, with no coding involved.

The Lunar Resort has a public site for guests. On that site, there's a page
called *Activities Sign-Up*, where guests can sign up for activities led by the
Recreation Department, or for the upcoming Lunar Luau, a Hawaiian themed dinner
party on the Moon! It's important to know which guests are attending which
activities, since any lunar activity requires special equipment, considerations, and
preparation. You need to do two things:

1.  Provide guests with sign-up forms.
2.  Access the guest lists to view the entries. 

<!-- Forms portlet will either replace or be an integral part of this process,
just not yet ready to do everything that's needed (not all field types
supported, no way to display the entries on the page). They will at least be
used to create the form templates, when ready. -->

These are basically simple applications, that take user input and store the
data somewhere. While you don't need to be a developer to make this
happen, you do need to be familiar with data definitions, data lists, and
templates. You probably also need a user group<!--LINK--> of
List Creators who have permission to create lists and access the entries. 

When list creators sign in to the Lunar Resort portal, you want to make it easy
for them to build lists. To this end, you'll provide one or more data definitions
that should suit most of their list making needs. Not everyone needs to be an
expert in Dynamic Data Lists (but you do, so keep reading). 

What are these data definitions? They're just collections of different field
types, configured in a certain way, to guide users in providing data for your
lists. All lists are backed by a data definition, so this is the place to start
in building a list app. An exhaustive discussion of the field types available
and their configuration options is found at the end of this article. Next, get
started creating data definitions.

## Creating Data Definitions [](id=creating-data-definitions)

The Lunar Resort List creators can't post and manage lists unless you give them
some useful definitions. You can, of course, delegate the data definition
responsibilities to the list creators, if you want. The following directions
apply to whoever has the permissions to access the DDL application. Create some
data definitions and set your list creators to work.

1.  Make sure you're logged in as an administrator, then open the site
administration menu for the site you're working on. Click *Content* &rarr;
*Dynamic Data Lists*.

2.  To create a data definition, find the icon (![Configuration Menu](../../../images/icon-options.png)) for the Dynamic Data List
configuration menu. Click on it and select *Manage Data Definitions*. The Data
Definitions window appears, where you can access existing definitions or create
new ones.

    ![Figure 1: The portal ships with several commonly used predefined data definitions that  can be used as is or modified to build your lists. You can also add your own data definitions.](../../../images/ddl-definitions.png)

3.  Click the *Add* icon (![Add Icon](../../../images/icon-add.png)) to add a
new Data Definition. 

4.  Fill out the Title and Description fields. Since this definition will be
used for multiple lists (because there are multiple activities the resort needs
lists for), name it *Activity Entry*. Beware that the Title you give the data
definition will appear to portal users who are adding a record to your list.
There will be a button that users click to *Add a [Definition Title]*. A good description
might be *This definition should cover most use cases on the Activities Sign-Up
page. Modify the templates to suit your specific needs.*

    Below the Description field, there's a Parent Data Definition selector. The
Activity Entry definition won't use any of the pre-configured definitions as a
parent, so leave it blank and scroll down to the data definition designer. The
*View* tab is displayed by default, and that's where you'll probably do most of
your work. You can, however, click *Source* mode to manually customizing a
definition's XML file. This method is likely to be useful for more experienced
users and developers.

    ![Figure 2: You can add and configure fields to form a custom list definition.](../../../images/ddl-data-definition-designer.png)

5.  To start, drag a Text field from the Fields tab (in View mode) onto the
canvas. This will eventually become a required field called Full Name. Below the
Text field, drop a Radio field. Drop four Boolean fields, and finish this
section with a Select field. While doing this you might have noticed you can
nest fields below each other. Nest all the fields you just added below the Text field.

    ![Figure 3: Fields can be nested below other fields.](../../../images/ddl-fields-added.png)

    +$$$

    **NOTE:** When you nest fields, they will not appear when the list entries
    are displayed with the default display template. For more information about
    templates, see the article on 
    [Using Templates to Display Forms and Lists](/discover/portal/-/knowledge_base/7-0/using-templates-to-display-forms-and-lists).

    $$$

6.  Before adding more fields, configure the current ones. Click on the Text
field and then click on the wrench icon (![Configure DDL Field Icon](../../../images/configure-ddl-field-icon.png)). Alternatively, click on the field and then click the 
Settings tab.

    ![Figure 4: Fields can be configured in a variety of ways.](../../../images/ddl-configure-field.png)

    -  Double click the Field Label and change it to *Full Name*. Click Save.

    ![Figure 5: You can give your fields appropriate field labels.](../../../images/ddl-field-label-edit.png)

    -  Double click the Required setting and change it to *Yes*, then click Save.

    ![Figure 6: You can make your fields required.](../../../images/ddl-field-required.png)

9.  Next, configure the Radio field.

    -  Change the Field Label to *Sex*. 

    -  Configure the field Options to be *Male* and *Female*, and feel free to
       set the values for the options to *male* and *female*. Only the Options
       will be displayed to users. Remove the unused Option.

    ![Figure 7: You can add and configure fields to form a custom list definition.](../../../images/ddl-options-edit.png)

12.  Now create and configure DDL data definition fields to make the definition
look like this image: 

    ![Figure 8: You can add and configure fields to form a custom list definition.](../../../images/ddl-name-and-more.png)

    - The options for the Select field  wtih the label *How fit are you?* should be *Fit as a
      fiddle!*, *Moderately fit, I think.*, and *Bring extra oxygen.*

13.  That's a good start to the form. To make sure all Activity Sign-Up use
cases are covered, add the following fields to the data definition:

    -  Documents and Media field called *Profile Picture*.
    -  A Radio field called *Preferred Rover Style* with these options:
        -  *Uranus (light blue)*
        -  *Jupiter (orange and white)*
        -  *Mars (red)*
    -  A Geolocation field with the label *Tell other guests where you're from.
       Optional.*
    -  A Text Box field with the label *Tell us something about yourself.*

14.  Once you finish adding and configuring fields, click Save.

Your new data definition will now appear in a list with all of the
pre-configured definitions. 

![Figure 9: You can add and configure fields to form a custom list definition.](../../../images/ddl-data-definitions.png)

If you click on the newly created definition (or any definition, for that
matter), you'll notice that you have access to both a WebDAV URL and a
static URL, which are used to access the XML source of the data definition. To
learn more about WebDAV or if you'd like to see WebDAV in action, see 
the article on [WebDAV access](/discover/portal/-/knowledge_base/6-2/automatic-previews-and-metadata#webdav-access)
<!--Make link to 7-0 when ready-->.

### Using a Parent Definition [](id=using-a-parent-definition)

Though it wasn't necessary for the use case presented above, data definitions
also have the capability of inheriting characteristics from other definitions.
When a parent data definition is configured, the child definition inherits the
parent's fields and settings. Using this feature is helpful when you want to
make a similar data definition to one that's already created. For instance, if
you'd like to create an advanced sign-up sheet in addition to a regular sign-up
sheet, you can simply inherit the characteristics of the regular sheet and only
add the additional fields necessary for the advanced sheet. When the advanced
sheet is configured, it will display its parent's fields in addition to its own
fields.

If you wanted the Sales Department to fill out a form to help keep inventory of
the Lunar Resort store's items, you could build a list based on the Inventory
Definition that comes shipped with Liferay. It has some useful fields already
configured, but the existing Inventory data definition isn't enough. You want to
give Lunar Resort employees a chance to rate the popularity of each item they're
filling out the Inventory form for. Just create a new definition, define the
Inventory definition as a Parent, and add the fields you need.

![Figure 10: You can create a child definition to add new fields to a pre-existing one.](../../../images/ddl-inventory-record-def.png)

When you create a list (you can call it Inventory List) based on the new
definition (probably called something like Inventory Record), it will include
the fields you added and the fields of the existing definition. This way,
whoever is in charge of maintaining the Inventory definition gets the
information they want, and you get the information you want.

![Figure 11: You can create a child definition to add new fields to a pre-existing one.](../../../images/ddl-parent-def-list.png)
Your definition now lives with all the pre-configured ones. There are
several things you can do with these existing definitions, including deleting
them, editing them, and copying them.

## Managing Data Definitions [](id=managing-data-definitions)

From a site's Dynamic Data Lists application (accessed through the Product Menu),
you can either add a new dynamic data list from an existing data type or you can
click *Manage Data Definitions* from the Actions button to add or edit data
definitions.

![Figure 12: You can manage the portal's data definitions.](../../../images/ddl-config-menu.png)

There are several data definition management actions you can perform. Of course,
you can edit a data definition, but you can also configure its permissions,
manage its templates, copy it, or delete it.

![Figure 13: You can copy an existing data definition, manage its templates, and more.](../../../images/ddl-copy-definition.png)

If you edit a data definition that has references (from your lists or
templates), you'll need to account for that, by editing the template, for
instance.

![Figure 14: You'll be warned if you try to edit a data definition that's referenced in a template.](../../../images/ddl-edit-definition-warning.png)

### Copying Data Definitions [](id=copying-data-definitions)

Liferay Portal 6.2 introduced the ability to *Copy* the DDM structures and templates
associated with an existing data definition. You can access the Copy button by
navigating to *Manage Data Definitions* and clicking the *Actions* button (the
icon is a vertical ellipsis). Select *Copy* from the actions menu. The Copy menu
opens, and includes options for copying the form and display templates
associated with the data definition. You'll learn how to manage and create new
form and display templates later. When you're finished, the copied data
definition can be accessed in the *Manage Data Definitions* menu. The Copy
feature lets you create new data definitions based on existing ones. In one
action you can copy a data definition with its templates, and then you can
modify it to suit your needs. You can, of course, edit any definition in
the portal, but if you copy a definition instead, you'll still have
access to the original.

![Figure 15: You can copy an existing data definition's DDM structures and templates.](../../../images/ddl-copy-definition.png)

From the actions menu for a data definition, you can also configure its permissions.

### Data Definition Permissions [](id=data-definition-permissions)

From a data definition's Actions menu, you can select Permissions to configure
the permissions for a particular data definition. Note that these permissions
are for an individual definition accessed through the DDL application in Site
Administration (from the Product Menu, go to *Sites* &rarr; *Content* &rarr;
*Dynamic Data Lists*). For example, by default a Site Member can View the
Activity Entry Data Definition. Any Site Member who was also assigned a role
that can access the DDL application and its data definitions would see this
definition listed in the Manage Data Definitions list. If you don't want this,
just deselect View for Site Member, and these users won't see your data
definition listed with the others.

![Figure 16: You can configure the permissions on an existing data definition.](../../../images/ddl-definition-permissions.png)

You can manage a data definition's templates as well. 

### Managing a Data Definition's Templates [](id=managing-a-data-definitions-templates)

Manage a data definition's templates by clicking Manage Templates from a data
definition's actions menu (click the ![Configuration Menu](../../../images/icon-options.png) icon). 

![Figure 17: You can manage the templates associated with a data definition.](../../../images/ddl-definition-manage-templates.png)

You can copy templates if you want to create a new template that's similar to an
existing one, or you can edit them directly. You can also configure permissions
for a template, and delete templates. All of these are accomplished by clicking
the actions button for a template. For more information see the article on
[Using Templates to Display Forms and Lists](/discover/portal/-/knowledge_base/7-0/using-templates-to-display-forms-and-lists).

## Configuring Data Definition Fields [](id=configuring-data-definition-fields)

You already know how to create data definitions, and if you created the example
definition, you've added and configured some of the field types that can be used
in your definitions. But there's more to know about the field types and
their configuration options. 

There are many different types of fields to choose from when creating data definitions:

*Boolean* fields present a checkbox to the user and stores either a `true`
(checked) or `false` (unchecked) based on state.

*Date* fields are preformatted text fields that display a convenient date picker to
assist in selecting the desired date. The format for the date is governed by the
current locale.

*Decimal* fields are similar to Number fields, but require that a decimal point
be present.

*Documents and Media* fields allow users to select an existing uploaded document
to attach to the data record. Users can also upload documents into the
Document Library.

*Geolocation* fields are integrated with Google Maps, and allow users to enter
an address, which is then displayed on a map.

*HTML* fields present an area that uses a WYSIWYG editor to enhance the content.

*Integer* fields are similar to Number fields, but constrain user input to
non-fractional numbers.

*Link to Page* fields allow insertion of a link to another page in the same
site.

*Number* fields provide a text box that only allows numeric input, but put no
constraints on the kind of number entered.

*Radio* fields presents are similar to Select fields, presenting the user with a
list of options to choose from using radio button inputs. The values are stored
as strings, and only one option can be chosen.

*Select* fields provide the user options for the user to choose from using a
combo box. Unlike Radio fields, Select fields can be configured to allow
multiple selections.

*Text* fields are areas where any string input can be entered.

*Text Box* fields are larger text areas for longer text input.

If you're in the Dynamic Data List's Add Data Definition section, you add these
fields to a definition by dragging them from the palette on the left to
the work area on the right.

You can also customize the appearance of input fields and provide helpful
tips and hints for those entering data. Some data types have specific
configuration options but all have some in common. Field properties can
be edited in several ways: by double-clicking on the field, by clicking the
wrench icon in the upper-right corner of the field,or by clicking the *Settings*
tab when the field is selected:

The *Type* setting can't be edited, but can be referenced from a 
display template. It lists the type of field placed in the definition,
and is available to reference from a display template.

Edit the *Field Label* to set the text that's displayed with the field.

The *Show Label* setting is used to configure whether the field label is shown
with the form field.

*Required* can be set to *Yes* (it defaults to *No*), which means the field must
have data in it for a new entry to be submitted (not available for Boolean).

The *Name* property sets the name of the field internally, and is automatically
generated. This is the variable name that you can read the data from in a
display template, so you should specify a memorable name here.

Set the *Predefined Value* to provide example data or a default value for the
user to start with. The value you enter becomes the field's default value when
adding a new entry.

Use a *Tip* for the field to provide helpful text that's displayed in a tooltip,
which pops up from a small help icon next to the field.

Set *Indexable* to *Yes* if you want Liferay to index your field for
search.

Setting *Localizable* to *No* turns off localization for a particular
field, so that [translations](/discover/portal/-/knowledge_base/6-2/localization) 
cannot be made for that field.

<!-- Add link to 7.0 docs on localization when they're created -->

Setting the *Repeatable* property to *Yes* lets users add as many copies of this
field as they like.

Set the visual *Width* of the form on the page. It doesn't affect the field
values that will be stored. Possible width values are *Small*, *Medium* and
*Large* (not available for Boolean, Documents and Media, Geolocation, Radio, and Select).

Setting *Multiple* to *Yes* allows the user to select more than one option.
This defaults to *No* (only available for Select).

Setting the field's *Options* changes the options available for selection.
You're able to add and remove options as well as edit each individual option's
display name and value (only available for Radio and Select fields).

![Figure 18: The properties of a definition's data fields are configurable. For example, add and edit options for the *How fit are you?* Select field drop-down menu on the Activity Entry form.](../../../images/ddl-data-def-properties.png)

In addition to dragging the fields around on the canvas, you can stack inputs
within inputs by dragging a field within another field. You can organize your
data into unlimited levels of hierarchy, creating a clear, logical data model.
There is also a duplicate button on each field (the button with the plus symbol
icon), allowing you to easily clone any field as many times as you need.

![Figure 19: Configure data fields by clicking the wrench icon, duplicate fields with the add button (the plus symbol icon), or delete them using the delete button (the trash can icon).](../../../images/ddl-def-duplicate-field.png)

Once you have a data definition, you and your list creators can use it to start
creating lists for users to fill out.

