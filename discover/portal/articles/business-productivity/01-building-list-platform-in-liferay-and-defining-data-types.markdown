# Building a List Platform in Liferay and Defining Data Types

<!-- Creating Your Own Data Definitions is my proposed title for this section -->

To expand and extend the social capabilities of your site, build a
new, radical platform on Liferay: custom-built lists that users can share and
collaborate on with their friends (or enemies, depending on their Social
Relation type). Marketing has come up with a great name for our new service:
`list.it`. Our beautiful `list.it` dashboard will give users the power to
generate their own lists, see the lists of their friends and tally the results
of certain types of lists (surveys, anyone?). Liferay makes this as simple as
throwing some Dynamic Data List Display and Form portlets on the public and
private pages of users' personal sites.

When new users log in to `list.it`, they are going to want to build a few lists
for themselves. Chances are, many of the lists they would want to create--to do
lists, shopping lists and memos come to mind--are already defined in the portal.
All the user has to do is create a new list, choose the predefined data type,
and have at it! The data definitions that ship with the portal's default site
include *To Do*, *Meeting Minutes*, *Issues Tracking*, *Inventory*, *Events*,
and *Contacts*. Use these definitions on their own to generate new data lists or
tweak them to fit your use case. 

![Figure 1: The portal ships with several predefined data definitions.](../../images/ddl-definitions.png)

If the built-in data definitions don't suit your needs, create your own data
definition. Maybe you want to allow `list.it` users (who would probably call
themselves "list-ers" or "list-ies") to create their own data types for lists
they create. In this case, they need to have complete access to the content of
their private user site where they can create a new data type.

Using data lists to outline a new data model is as simple as point and click.
You now have a `list.it` account and have been dying to bug your friends and
family to sign up for "volunteer" work: helping you move into a new apartment.
Using an intuitive visual editor, you can quickly draw up the skeleton for that
volunteer list in minutes. Since data lists exemplify a unique type of content
for your site, you can find them in the Content section Site Administration area
of the Control Panel. To manage the dynamic data lists of your site, open the
Product Menu and click *Sites* &rarr; *Content* &rarr; *Dynamic Data Lists*.

![Figure 2: You can manage dynamic data lists from the *Sites* &rarr; *Content* section of the Product Menu.](../../images/ddl-product-menu.png)

<!-- Product menu screenshot has high likelihood of needing to be updated says
Russ in October 2015 -->

## Managing Data Definitions

From a site's Dynamic Data Lists portlet (accessed through the Product Menu),
you can either add a new dynamic data list from an existing data type or you can
click *Manage Data Definitions* to add or edit data definitions.

![Figure 3: You can manage the portal's data definitions.](../../images/ddl-config-menu.png)

<!-- Is it even worth mentioning DDM structures to portal users? -->

Liferay 6.2 introduced the ability to *Copy* the DDM structures associated with
an existing data definition. You can access the Copy button by navigating to
*Manage Data Definitions* and clicking the *Actions* button (the icon is a
vertical ellipsis). Select *Copy* from the actions menu. The Copy menu opens,
and includes options for copying the form and display templates associated with
the data definition. You'll learn how to manage and create new form and display
templates later. When you're finished, the copied data definition can be
accessed in the *Manage Data Definitions* menu. The Copy feature lets you create
new data definitions based on existing ones.

<!-- I only see DDM structures (in the database) that appear to be linked to
these data definitions. DDM templates are the actual data types, like text area,
select, etc. -->

![Figure 4: You can copy an existing data definition's DDM structures and templates.](../../images/ddl-copy-definition.png)

## Creating New Data Definitions

If you want to use a new data type, you need to create a definition for it.
Open the Dynamic Data Lists portlet from the Product Menu's *Sites* &rarr;
*Content* section. Open the portlet's configuration menu by clicking the
vertical ellipsis icon at the top right-hand corner of the page. Select *Manage
Data Definitions*, then click the *Add* button (the plus symbol). The first
thing you should enter is a name for the definition, and a description, which is
in the *Details* section. Call the new data definition *Volunteer
Sign-Up*. When creating a new data definition, you have a palette of fields to
choose from and a blank canvas where you'll drop the fields you want. If you've
created or edited [web content structures](LINK), the interface will look
familiar. You have several different data types that can be used in your
data definition:

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

![Figure 5: You can add and configure fields to form a custom list definition.](../../images/ddl-data-definition-create.png)

Using the screenshot field descriptions above, configure the data types you need
for the Volunteer Sign-Up data definition. Use a *Text* type for the name.
Require your friends and family to choose the tasks they'd like to help with by
configuring a *Select* field to list the available tasks. Adding a required
*Date* field is smart, because then you'll know that each volunteer has the
correct date. Don't forget a *Documents and Media* field so users can upload
their image. After all, how much more official-feeling and fun is it if you can
print out some nifty badges? Make sure you add a *Geolocation* field so you know
where your helpers are coming from (that way you can check with the municipal
authorities on the day of your move to ensure there won't be any travel delays
for your volunteers). To add these fields, drag them from the palette on the
left to the work area on the right.

You can also customize the appearance of the input fields and provide helpful
tips and hints for those entering data. Some data types have specific
configuration options but all have some in common. The following properties can
be edited in several ways: by double-clicking on the field, by clicking the
wrench icon in the upper-right corner of the field,or by clicking the *Settings*
tab when the field is selected:

The *Type* setting can't be edited, but can be referenced from a 
[display template](LINK). It lists the type of field placed in the definition,
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
field, so that [translations](discover/portal/-/knowledge_base/6-2/localization) 
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

![Figure 6: The properties of a definition's data fields are configurable. For example, add and edit options for the *Task* Select field drop-down menu on the Volunteer Sign-Up form.](../../images/ddl-data-def-properties.png)

In addition to dragging the fields around on the canvas, you can stack inputs
within inputs by dragging a field within another field. You can organize your
data into unlimited levels of hierarchy, creating a clear, logical data model.
There is also a duplicate button on each field (the button with the plus symbol
icon), allowing you to easily clone any field as many times as you need.

![Figure 7: Configure data fields by clicking the wrench icon, duplicate fields with the add button (the plus symbol icon), or delete them using the delete button (the trash can icon).](../../images/ddl-def-duplicate-field.png)

The graphical interface is intuitive and easy to use, but you can also edit your
data definition by switching to *Source* mode and manually customizing its XML
file. You'll notice by default the *View* mode is selected. Click the *Source*
tab to switch to Source mode. This method is likely to be useful for more
experienced users and developers.

Data definitions also have the capability of inheriting characteristics from
other definitions. When a parent data definition is configured, the child
definition inherits the parent's fields and settings. Using this feature is
helpful when you want to make a similar data definition to one you've already
created. For instance, if you'd like to create an advanced sign-up sheet in
addition to a regular sign-up sheet, you can simply inherit the characteristics
of the regular sheet and only add the additional fields necessary for the
advanced sheet. When the advanced sheet is configured, it will display its
parent's fields in addition to its own fields.

After you've saved your data definition, Liferay provides a WebDAV URL and a
static URL. These values access the XML source of your data definition. To
obtain these values, return to your data definition after it has been saved. To
learn more about WebDAV or if you'd like to see WebDAV in action, see 
[the article on WebDAV access](LINK).

![Figure 7: New data definitions are assigned a WebDAV URL and a static URL. After saving the definition, open the definition again (select *Manage Data Definitions* form the DDL portlet's configuration menu) to view the URLs. ](../../images/ddl-def-urls.png)

Data definitions are easy to create with the DDL portlet's graphical interface.
Using this information, users of `list.it` can get rolling
with an unlimited array of custom types. Plus, you can always come back and
change your form. If you find you needed to add some more information, simply
come back to the data definition and fix it. All your data lists that use it are
then instantly updated with the new or changed fields. 

<!-- list.it? Should we stick with that? Are we mixing it up too much with the
Volunteer Sign-Up stuff? -->

All that's left to do is build a new data list and let your users play with it.
