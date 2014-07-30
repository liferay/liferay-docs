# Building a List Platform in Liferay and Defining Data Types [](id=building-a-list-platform-in-liferay-and-defining-data-)

To expand and extend the social capabilities of our site, we want to build a
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
All the user has to do is create a new list, choose that pre-defined data type,
and have at it! A number of data definitions ship with the portal's default site
to help you get started. These include *To Do*, *Issues Tracking*, *Meeting
Minutes*, and *Contacts*. Use these on their own to generate new data lists or
tweak them to fit your use case. 

If none of the built-in data definitions suits your needs, you can define your
own. Perhaps we want to allow our `list.it` users (who would probably call
themselves "list-ers" or "list-ies") to create their own data types for lists
they create. In this case, they would need to have unfettered access to the
content of their private user site where they can create a new data type.

Using data lists to outline a new data model is as simple as point and click.
You now have a `list.it` account and have been dying to bug your friends and
family to sign up for "volunteer" work: helping you move into a new apartment.
Using an intuitive visual editor, you can quickly draw up the skeleton for that
volunteer list in minutes. Since data lists exemplify a unique type of content
for your site, you can find them in the Content section Site Administration area
of the Control Panel. To manage the dynamic data lists of your site, click
*Admin* from the Dockbar and select *Content*. Then click on *Dynamic Data
Lists*.

![Figure 10.1: You can manage dynamic data lists from the Content section of the Site Administration area of the Control Panel.](../../images/05-ddl-control-panel.png)

From the Dynamic Data Lists portlet in the Control Panel, you can either click
*Add* to create a new dynamic data list from an existing data type or you can
click *Manage Data Definitions* to add or edit data definitions. Liferay 6.2
introduced the *Copy* action which copies the DDM templates associated with an
existing data definition. You can access the Copy button by navigating to
*Manage Data Definitions* and clicking *Actions* &rarr; *Copy* next to a data
definition. The Copy menu includes options for copying the form and displaying
templates associated with the data definition. We'll discuss how to manage and
create form and display templates later in the chapter. When you're finished,
the copied data definition can be accessed in the *Manage Data Definitions*
menu. The Copy feature lets you create new data definitions based on existing
ones. You can use the copied version as a checkpoint and work off of it.

If you want to use a new data type, you need to create a definition for it.
From the Dynamic Data Lists portlet in the Control Panel, click *Manage Data
Definitions* and click the *Add* button. The first thing you should enter is a
name for the definition and a description. Create a new data definition called
*Volunteer Sign-Up*. When creating a new data definition, you have a palette of
fields to lay out, as well as a blank canvas to construct the definition. The
interface looks similar to creating and editing web content structures covered
previously. Let's explore the different data types at our disposal:

**Boolean:** presents a checkbox to the user and stores either a `true`
(checked) or `false` (unchecked) based on state.

**Date:** a preformatted text field that displays a convenient date picker to
assist in selecting the desired date. The format for the date is governed by the
current locale.

**Decimal:** similar to *Number*, except that it requires a decimal point (`.`)
be present.

**Documents and Media:** select an existing uploaded document to attach to the
data record. Also has the ability to upload documents into the Document Library.

**HTML:** An area that uses a WYSIWYG editor to enhance the content.

**Integer:** similar to *Number*, except that it constrains user input to
non-fractional numbers.

**Link to Page:** Inserts a link to another page in the same site.

**Number:** a text box that only accepts numbers as inputs, but puts no
constraints on the kind of number entered.

**Radio:** presents the user with a list of options to choose from using radio
button inputs. Values are stored as strings. Similar to *Select*.

**Select:** a selection of options for the user to choose from using a combo
box. Can be configured to allow multiple selections, unlike *Radio*.

**Text:** a simple text field for any string input.

**Text Box:** a large text box for long text input.

![Figure 10.2: You can combine many different kinds of fields to form a list definition and you can configure various settings and properties for each field.](../../images/05-data-definition-screen.png)

Using that reference as a nice cheat-sheet, you can now create the data type you
need for "Volunteer Work Sign-Up." Use a *Text* type for the name. For all the
tasks your friends and family can volunteer to do for you, use *Select* to allow
users to choose from a list of tasks. Finally, don't forget a *Documents and
Media* field users can upload images of themselves. After all, how much more
official-feeling and fun is it if you can print out some nifty badges? To add
these fields, drag them from the palette on the left to the work area on the
right.

When creating data definitions, you can also customize the appearance of the
input fields and provide helpful tips and hints for those entering data. Some
data types have specific configuration options but all have some in common. The
following properties can be edited in three ways: 1) by double-clicking on any
field, 2) by clicking the wrench icon in the upper-right corner of the field or
3) by clicking the *Settings* tab when the field is selected. Let's take a look
at the properties you can edit for each of these field types: 

**Type:** Lists the type of field placed in the definition. This is not editable
but is available to reference from a display template.

**Field Label:** Sets the text that can be displayed with the field. This is the
human-readable text that the user sees.

**Show Label:** When set to *Yes*, the label is shown with the form field.

**Required:** When set to *Yes*, this field must have data in it for a new entry
to be submitted (not available for Boolean).

**Name:** The name of the field internally, automatically generated. Since this
is the variable name that you can read the data from in a display template, you
should give a more memorable name here.

**Predefined Value:** If you would like example data or a default value for the
user to start with, enter it here. The field's value defaults to this when
adding a new entry.

**Tip:** Each field can have a small help icon, with a tooltip attached that
displays helpful information. If you would like to provide text for the tooltip
you may enter it here.

**Indexable:** When set to *Yes*, Liferay is able to index your field for
search.

**Repeatable:** When set to *Yes*, the field is repeatable. Your users can then
add as many copies of this field as they like.

**Width:** Sets the visual width of the form on the page. It does not affect the
values that are stored. Possible values are *Small*, *Medium* and *Large* (not
available for Boolean, Documents and Media, Radio, and Select).

**Multiple:** When set to *Yes*, allows the user to select more than one option.
This defaults to no (only available for Select).

**Options:** Changes the options available for selection. You're able to add and
remove options as well as edit each individual option's display name and VALUE
(ONLY AVAILABLE FOR RADIO AND SELECT).

![Figure 10.3: You can edit the properties of data fields. This allows you to, for example, add and edit selectable options for the *Task* drop-down menu on the Spring Move-In Sign Up form.](../../images/05-data-definition-field-properties.png)

In addition to dragging the fields around to create your desired forms, you can
stack inputs within inputs by dragging a field within another field. You can
organize your data into unlimited levels of hierarchy, creating the clearest,
most logical data model. There is also a duplicate button on each field (the
middle button), allowing you to easily clone any field as many times as you
need.

Another method to edit your data definition is switching to *Source* mode and
manually customizing your structure by editing its XML file. You'll notice by
default the *View* mode is selected. Click the *Source* tab to switch to Source
mode. This method is for the more experienced developers.

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
learn more about WebDAV or if you'd like to see WebDAV in action, visit the
*Document Management* chapter's *WebDAV access* chapter.

That really covers the basic tools that users of `list.it` need to get rolling
with an unlimited array of custom types. Plus, you can always come back and
change your form. If you find you needed to add some more information, simply
come back to the data definition and fix it. All your data lists that use it are
then instantly updated with the new or changed fields. 

All that's left to do is build a new data list and let your users play with it.
