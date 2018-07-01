# Editing Structures [](id=editing-structures)

To start, go to the *Structures* page.

1.  From *Site Administration* go to *Content* &rarr; *Web Content*.

2.  Open the *Structures* tab.

This page shows you all the web content structures in this Site. You can add
new web content structures, edit existing ones, manage the templates associated
with a structure, edit the permissions of a structure, and copy or delete
structures.

![Figure 1: Structures are not pre-installed. You have to make your own.](../../../../../images/manage-structures.png)
+$$$

Note: When you copy a structure, @product@ generates a unique ID for the copied
structure, but every other attribute of the copied structure, including the
name, is the same as that of the original. When you copy web content structure,
enter a new name for it to avoid confusing it with the original. During the
copy process, you're prompted to choose whether to copy any detail templates or
list templates associated with the structure. For information on detail
templates and list templates, please refer to 
[Using Web Forms and Dynamic Data Lists](/discover/portal/-/knowledge_base/7-1/using-web-forms-and-dynamic-data-lists)
.

$$$

*Basic Web Content* which you used in previous exercises lives at the *Global* 
scope so that it is available to all Sites. This structure and template are 
used automatically if a custom structure and template are not added.

## Structure Fields [](id=structure-fields)

Now, create a new Structure:

1.  Click *Add* (![Add Structure](../../../../../images/icon-add.png)).

2.  Give your Structure a name.

Structures are essentially a set of fields organized in a certain way. The
interface on this page provides an easy way to add and organize whatever fields
you need. Each element that you add has three icon options that you can click:

**Settings:** (![Settings](../../../../../images/icon-wrench.png)) Changes the
name and label and set other information about the field, like whether or not
it is required. 

**Delete:** (![Delete](../../../../../images/icon-trash.png)) Removes the field 
from the structure.

**Duplicate:** (![Duplicate](../../../../../images/icon-add-2.png)) Duplicates the 
field and all its settings and iterates the *Name* to avoid conflicts.

Web content structures can inherit characteristics from other structures. A 
child structure inherits all the parent's fields and settings. You can use this 
to make a similar structure to one that already exists. For example, if you 
have *Sports Article* and you want to create *In-depth Sports Article*, set 
*Sports Article* as the parent and the *In-dept Sports Article* inherits
all its fields, letting you add new ones for more in-depth information.

+$$$

**Note:** Due to import/export operations it's possible to have both a global
and a Site-scoped structure with the same `structureKey`. If this happens, the 
Site-scoped structure takes precedence, and you can't access the global
structure from that Site.

$$$

You can also manually customize a structure's XML in *Source* mode. By default 
the *View* mode is selected, but you can click the *Source* tab to switch. This 
method is for more experienced developers.

Take a moment to add, delete, and rearrange different elements.

![Figure 2: The structure editor gives you many options to customize your Web Content.](../../../../../images/web-content-structure-editor.png)

The following fields can be in structures:

**Boolean:** Adds a checkbox onto your structure, which stores either `true`
(checked) or `false` (unchecked). Template developers can use this as a display
rule.

**Date:** Adds a pre-formatted text field that displays a date picker to assist
in selecting the desired data. The format for the date is governed by the
current locale.

**Decimal:** Similar to *Number*, except that it requires a decimal point (.) be
present.

**Documents and Media:** Adds an existing uploaded document to attach to the
structure. Can also upload documents into the Document Library.

**Geolocation:** Adds a map that displays a configured location. The geolocation
system can work in two ways: letting the system know your current location
(especially useful on mobile devices) and giving the user directions to a
another place.

**HTML:** An area that uses a WYSIWYG editor to enhance the content.

**Image:** Adds the browse image application into your structure. You can
select an image from the Documents and Media library or upload an image from
your computer's storage. If uploading an image from your personal computer to
the web content article, it is only available for that article.

**Integer:** Similar to *Number*, except that it constrains user input to
whole numbers.

**Link to Page:** Inserts a link to another page in the same site.

**Number:** Presents a text box that only accepts numbers as inputs, but puts no
constraints on the kind of number entered.

**Radio:** Presents the user with a list of options to choose from using radio
button inputs.

**Select:** Presents a selection of options for the user to choose from using a
combo box. Can be configured to allow multiple selections, unlike *Radio*.

**Separator:** Adds a horizontal line between fields.

**Text:** Used for items such as titles and headings.

**Text Box:** Used for the body of your content or long descriptions.

These fields provide all you need to model any information type you would
want to use as web content. Liferay customers have used structures to model
everything from articles, to video metadata, to wildlife databases.
