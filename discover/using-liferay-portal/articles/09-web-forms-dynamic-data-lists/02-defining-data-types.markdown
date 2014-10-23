# Defining Data Types [](id=defining-data-types)

When new users log in to `list.it`, they are going to want to build a few lists
for themselves. Chances are, many of the lists they would want to create--to do
lists, shopping lists and memos come to mind--are already defined in the portal.
All the user has to do is create a new list, choose that pre-defined data type,
and have at it! A number of data definitions ship with the portal to help you
get started: *To Do*, *Issues*, *Meeting Minutes* and *Contacts*. Use these on
their own to generate new data lists or tweak them to fit your use case. 

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
for your site, you can find them in the content section of the control panel,
selecting *Go To &rarr; Control Panel &rarr; Content &rarr; Dynamic Data Lists*
or *Manage &rarr; Site Content &rarr; Dynamic Data Lists*. Within the dynamic
data lists section, you can either create a new data type (*Manage Data
Definitions*) or a new list from an existing data type.

![Figure 9.1: Data Lists in the control
panel.](../../images/05-ddl-control-panel.png)

If you have a new data type, you need to create a definition for it first. Click
*Manage Data Definitions* and click the *Add* button. The first thing you should
enter is a name for the definition and a description. Call it *Volunteer
Sign-Up*. When creating a new data definition, you have a palette of fields to
lay out, as well as a blank canvas to construct the definition. The interface
looks similar to creating and editing web content structures covered previously.
Let's explore the different data types at our disposal:

**Boolean:** presents a checkbox to the user and stores either a `true`
(checked) or `false` (unchecked) based on state.

**Date:** a preformatted text field that displays a convenient date picker to
assist in selecting the desired date. The format for the date is governed by the
current locale.

**Number:** a text box that only accepts numbers as inputs, but puts no
constraints on the kind of number entered.

**Integer:** similar to `Number`, except that it constrains user input to
non-fractional numbers.

**Decimal:** similar to `Number`, except that it requires a decimal point (`.`)
be present.

**Documents and Media:** select an existing uploaded document to attach to the
data record.

**File Upload:** upload a document to attach to the data record. Uploads are
stored in Documents and Media.

**Radio:** presents the user with a list of options to choose from using radio
button inputs. Values are stored as `String`s. Similar to `Select`.

**Select:** a selection of options for the user to choose from using a combo
box. Can be configured to allow multiple selections, unlike `Radio`.

**Text:** a simple text field for any `String` input.

**Text Box:** a large text box for long text input.

![Figure 9.2: Data definition
fields.](../../images/05-data-definition-screen.png)

Using that reference as a nice cheat-sheet, you can now create the data type you
need for "Volunteer Work Sign-Up." Use a `Text` type for the name. For all the
tasks your friends and family can volunteer to do for you, use `Select`
(obviously set to allow multiple options). Finally, you don't want to forget a
`File Upload` so they can upload images of themselves. After all, how much more
official-feeling and fun is it if you can print out some nifty badges? To add
these fields, drag them from the palette on the left to the work area on the
right. 

When creating data definitions, you can also customize the appearance of the
input fields and provide helpful tips and hints for those entering data. Some
data types have specific configuration options but all have some in common. The
following properties can be edited in three ways: 1) by double-clicking on any
field, 2) by clicking the gear icon in the upper-right corner of the field or 3)
by clicking the *Settings* tab when the field is selected. Let's take a look at
the properties you can edit for each of these field types: 

**Type:** Lists the type of field placed in the definition. This is not editable
but is available to reference from a list template.

**Field Label:** Sets the text that can be displayed with the field. This is the
human-readable text that the user sees.

**Show Label:** When set to `Yes`, the label is shown with the form field.

**Required:** When set to `Yes`, this field must have data in it for a new entry
to be submitted.

**Name:** The name of the field internally, automatically generated. Since this
is the variable name that you can read the data from in a list template, you
should give a more memorable name here.

**Predefined value:** If you would like example data or a default value for the
user to start with, enter it here. The field's value defaults to this when
adding a new entry.

**Tip:** Each field can have a small help icon, with a tooltip attached that
displays helpful information. If you would like to provide text for the tooltip
you may enter it here.

**Multiple (Select):** When set to `Yes`, allows the user to select more than
one option. This defaults to no.

**Allowed File Extensions (File Upload):** By default, form validation accepts
any file type to be submitted. Set this value to a comma-delimited list of
extensions, including the character `.`, and Liferay checks the extension before
the file can be uploaded.

**Width (Text, Text Box, Decimal, Integer, Number):** Sets the visual width of
the form on the page. It does not affect the values that are stored. Possible
values are `Small`, `Medium` and `Large`.

![Figure 9.3: Data field
properties.](../../images/05-data-definition-field-properties.png)

In addition to dragging the fields around to create your desired forms, you can
stack inputs within inputs by dragging a field within another field. You can
organize your data into unlimited levels of hierarchy, creating the clearest,
most logical data model. There is also a duplicate button on each field (the
middle button), allowing you to easily clone any field as many times as you
need.

That really covers the basic tools that users of `list.it` need to get rolling
with an unlimited array of custom types. Plus, you can always come back and
change your form. If you find you needed to add some more information, simply
come back to the data definition and fix it. All your data lists that use it are
then instantly updated with the new or changed fields. 

All that's left to do is build a new data list and let your users play with it.
