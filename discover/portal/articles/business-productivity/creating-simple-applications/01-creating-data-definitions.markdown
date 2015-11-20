# Creating Data Definitions

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
templates. You probably also need a [user group](LINK TO USER GROUPS) of
List Creators (that's boring; maybe they should be called "listers", or
"listies") who have permission to create lists and access the entries. 

When list creators sign in to the Lunar Resort portal, you want to make it easy
for them to build lists. To this end, you'll provide one or more data definitions
that should suit most of their list making needs. Not everyone needs to be an
expert in Dynamic Data Lists (but you do, so keep reading). 

What are these data definitions you've been hearing about? They're just
collections of different field types, configured in a certain way, to guide
users in providing data for your lists. All lists are backed by a data
definition, so this is the place to start in building a list app. An exhaustive
discussion of the field types available and their configuration options is found
at the end of this article. Next, get started creating data definitions.

## Creating Data Definitions

Create some data definitions and set your list creators to
work.

1.  Make sure you're logged in as an administrator, then open the product menu
and click *Sites*. Select the site where you want to create data definitions
(Lunar Resort, if you're following along with the example site). You're now in
the Site Administration section of the Product Menu, and your site is selected.
Click *Content* &rarr; *Dynamic Data Lists*.

    ![Figure 1: You can manage dynamic data lists from the *Sites* &rarr;
*Content* section of the Product Menu.](../../../images/ddl-product-menu.png)

2.  To create a data definition, find the icon (![Configuration
Menu](../../../images/options-icon.png)) for the Dynamic Data List
configuration menu. Click on it and select *Manage Data Definitions*. The Data
Definitions window appears, where you can access existing definitions or create
a new one.

    ![Figure 1: The portal ships with several commonly used predefined data
definitions that your lists can be built onto. You can also add your own data
definitions, or modify the existing ones.](../../../images/ddl-definitions.png)

3.  Click the *Add* icon (![Add Icon](../../../images/add-icon.png)) to add a
new Data Definition. 

4.  Fill out the title and description fields. Since this definition will be
used for multiple lists (because there are multiple activities the resort needs
lists for), name it *Activity Entry*. A good description might be *This
definition should cover most use cases on the Activities Sign-Up page. Modify
the templates to suit your specific needs.*

    Below the Description field, there's a Parent Data Definition selector. This
definition won't use any of the pre-defined ones as a parent, so leave it blank
and check out the data definition designer. The View tab is displayed by
default, and that's where you'll probably do most of your work.

    ![Figure 5: You can add and configure fields to form a custom list
definition.](../../../images/ddl-data-definition-designer.png)

5.  To start drag a Text field from the Fields tab onto the canvas. This will eventually
become a required field called Full Name. Below the Text field, drop a Radio field.
Drop four Boolean fields, and finish this section with a Select field. While
doing this you might have noticed you can nest fields below each other. Nest all
the fields below the Text field.

    ![Figure 5: Fields can be nested below other fields.
definition.](../../../images/ddl-fields-added.png)

6.  Before adding more fields, configure the current ones. Click on the Text
field and then click on the wrench icon
(![Configure DDL Field Icon](../../../images/configure-ddl-field-icon.png)).
Alternatively, click on the field and then click the Settings tab.

    ![Figure 5: Fields can be configured in a variety of ways.](../../../images/ddl-configure-field.png)

7.  Double click the Field Label and change it to *Full Name*. Click Save.

    ![Figure 5: You can add and configure fields to form a custom list
definition.](../../../images/ddl-field-label-edit.png)

8.  Double click the Required setting and change it to *Yes*, then click Save.

    ![Figure 5: You can add and configure fields to form a custom list
definition.](../../../images/ddl-field-required.png)

9. 






<!-- To expand and extend the social capabilities of your site, you've come up with a
great idea: give your users custom-built lists that they can share with their
friends (or enemies, depending on their Social Relation type). Marketing has
come up with a great name for your new service: `list.it`. Your beautiful
`list.it` dashboard will give users the power to generate their own lists, see
their friends' lists, and tally the results of certain types of lists
(surveys, anyone?). Liferay makes this as simple as throwing some Dynamic Data
List Display and Form portlets on the public and private pages of users'
personal sites.

When new users log in to `list.it`, you want to make it easy for them to build
lists. To this end, you've pre-defined some common ones-to do lists, shopping
lists, and memos. To create one of these, all the user has to do is create a new
list, choose the predefined data type, and have at it! The data definitions
that ship with the portal's default site include *To Do*, *Meeting Minutes*,
*Issues Tracking*, *Inventory*, *Events*, and *Contacts*. Users can use these
definitions on their own to generate new data lists or tweak them to fit their
needs. 

-->

<!--
If none of the built-in data definitions are suitable, users can create their
own definitions. If you want to allow `list.it` users (who would probably call
themselves "list-ers" or "list-ies") to create their own data types for lists
they create, they must have complete access to the content of their private user
sites where they can create a new data type.

Using data lists to outline a new data model is as simple as point and click.
Say you now have a `list.it` account. You've been dying to bug your friends and
family to sign up for "volunteer" work: helping you move into a new apartment.
Using an intuitive visual editor, you can quickly draw up the skeleton for that
volunteer list in minutes. Since data lists exemplify a unique type of content
for your site, you'll find the Dynamic Data Lists application in the *Sites*
section of the Product Menu, under *Content*. 
-->

<!-- Product menu screenshot has high likelihood of needing to be updated says
Russ in October 2015 -->

<!--Liferay ships with
several deafult definitions that can be used to create lists. To create a list
from one of these, all the user has to do is create a new list, choose the
predefined data type, and have at it! The data definitions that ship with the
portal's default site include *To Do*, *Meeting Minutes*, *Issues Tracking*,
*Inventory*, *Events*, and *Contacts*. Users can use these definitions on their
own to generate new data lists or tweak them to fit their needs. 
-->
<!-- The above paragraph contains too much info for our use case. We're not using
a pre-defined list so move to the end? -->

## Managing Data Definitions

From a site's Dynamic Data Lists application (accessed through the Product Menu),
you can either add a new dynamic data list from an existing data type or you can
click *Manage Data Definitions* from the action button to add or edit data
definitions.

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
created or edited web content structures, the interface will look
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
helpful when you want to make a similar data definition to one that's already
created. For instance, if you'd like to create an advanced sign-up sheet in
addition to a regular sign-up sheet, you can simply inherit the characteristics
of the regular sheet and only add the additional fields necessary for the
advanced sheet. When the advanced sheet is configured, it will display its
parent's fields in addition to its own fields.

After you've saved your data definition, Liferay provides a WebDAV URL and a
static URL. These values access the XML source of your data definition. To
obtain these values, return to your data definition after it has been saved. To
learn more about WebDAV or if you'd like to see WebDAV in action, see 
the article on WebDAV access.

![Figure 7: New data definitions are assigned a WebDAV URL and a static URL. After saving the definition, open the definition again (select *Manage Data Definitions* form the DDL portlet's configuration menu) to view the URLs. ](../../images/ddl-def-urls.png)

Data definitions are easy to create with the DDL portlet's graphical interface.
Using this information, users of `list.it` can get rolling
with an unlimited array of custom types. Plus, you can always come back and
change your form. If you find you needed to add some more information, simply
come back to the data definition and fix it. All your data lists that use it are
then instantly updated with the new or changed fields. 

<!-- list.it? Should we stick with that? Are we mixing it up too much with the
Volunteer Sign-Up stuff? -->

All that's left to do is build a new data list and cajole your users into
filling it out.
