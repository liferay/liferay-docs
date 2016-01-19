# Using Document Types and Metadata Sets

When a user assigns a document type to a document, the user is required to fill
out the fields defined by the metadata set of the document type. This encourages
users not to forget to enter important information about their documents. For
example, you could create a *copyrighted* document type and require users to
enter a license for all "copyrighted" documents. More importantly, document
types and metadata sets can make it easier to find documents. The values that
users enter into the fields determined by their document type's metadata set
become searchable entities within Liferay. You can use Liferay's Search
app to search for these terms. You can manage document types and metadata sets
from Documents and Media in Site Administration. Select *Options
(![Options](../../images/icon-options.png)) &rarr; Document Types* to open the
*Document Types* window.

![Figure 1: You can create and manage document types from the Documents and Media's Document Types window.](../../images/dm-document-types-list.png)

This window lists all of the current custom document types. From Search you can
enter keywords to find document types that match them. Selecting the checkbox at
the top of the window selects all of the document types. When one or more
are selected the *Recycle Bin* icon (![Recycle Bin](../../images/icon-trash.png)),
giving you the option to move the document types from Documents and Media to the
Recycle Bin. 

The following actions are available for each document type:

- *Edit*: opens a window for modifying the document type.

- *Permissions*: lets you grant or revoke roles the ability to update document types,
configure their permissions, delete document types, and view them.

- *Delete*: moves the document type from Documents and Media to the Recycle
Bin.

A document type must be associated with one or more metadata sets. A metadata
set is a group of fields that describes a document. Documents of a particular
type can have the same set of characteristics. 

When creating a new document type, you can define *Main Metadata Fields* or
select *Additional Metadata Fields*. Main metadata fields are directly tied to
their document type. Main medata fields can only be created within a document
type's editor window and they cannot be used with other document types.
Additional metadata fields, by contrast, can be used in multiple document types.
They must be created from the Metadata Sets management window before they can be
selected for use in document types. You can differentiate the document types
that implement the same additional metadata set by defining different main
metadata fields for them. 

As an example, we could create a document type called *Syllabus* and define a
metadata set. What metadata should we associate with syllabi? Let's choose for
our syllabi to have course title, professor, location, semester, course
description, and course requirements fields. All syllabi in our Liferay instance
should maintain entries for these fields. This ensures that a syllabus shows up
in the Search app if the course's title, professor, or semester are searched
for. Since we don't want to use our metadata set for any document type other
than *Syllabus*, let's create our metadata set under the Main Metadata Fields
area. 

From the Document Types management window, click *+ Add*. This opens the New
Document Types window. Name the document type *Syllabus*. Click the *Details*
dropdown section to show a text area for entering a description. The *Main
Metadata Fields* dropdown section shows an the metadata editor in which you can
define metadata by dragging field types from the left icon area and dropping
them onto the definition area on the right. 

![Figure 2: The metadata fields editor for Main Metadata Fields and Metadata Sets helps you define custom document types.](../../images/dm-metadata-set-settings.png)

Make sure to select the *View &rarr; Fields* tab. Then, to define a metadata
set, just choose fields to use from the area on the left and drag and drop them
into the area on the right. The drag and drop interface allows for nested fields
so you need to be careful about where you drop the fields. 
Default values,
mouse-over tips, widths, and other settings can be configured for most fields.


To configure these settings, just click on a field from the area on the right.
This automatically selects the Settings tab on the left. Alternatively, you can
access the Settings tab by clicking the fields *Settings* button (wrench). Then
double-click on a setting value to edit. 

When you select a field on the right, the following icons show
![Icons](../../icon-dm-metadata-actions.png). The wrench is for showing the
field's settings on the left. The `+` icon is for adding a duplicate of the
current field. The trash can is for deleting the field. 

Liferay supports the following kinds of fields for metadata sets:

**Boolean:** is a checkbox.

**Date:** lets you enter a date. A valid date format is required for the date
field, but you don't have to enter a date manually. When you select the date
field a mini-calendar pops up which you can use to select a date.

**Decimal:** lets you enter a decimal number. The value will be persisted as a
double.

**Documents and Media:** lets you select a file from one of the portal's
Documents and Media libraries.

**Geolocation:** lets you specify a location to associate with the document.

**HTML:** an area that uses a WYSIWYG editor to enhance the content.

**Integer:** lets you enter an integer. The value will be persisted as an int.

**Link to Page:** lets you link to another page in the same site.

**Number:** lets you enter a decimal number or an integer. The value will be
persisted either as a double or an int, depending on the type of input.

**Radio:** displays several clickable options. The default number is three but
this is customizable. Only one option can be selected at a time.

**Select:** is just like the radio field except that the options are hidden and
have to be accessed from a drop-down menu.

**Text:** lets you enter a single line of text.

**Text Box:** is just like the text field except you can enter multiple lines of
text or separate paragraphs. 

Using the Additional Metadata Fields selector, you can add multiple metadata
sets to a document type.

In the document type's *Permissions* section, you can specify its permissions.
By default, the document type is viewable by anyone, including site guests. But
you can restrict the type's view, update, delete, and permissions configuration
permissions to site members or the type's owner.

Alternatively, we could've created our metadata set independently using the
Metadata Sets window and then selected it as an Additional Metadata Field. In
Documents and Media in Site Administration, select *Options
(![Options](../../images/icon-options.png)) &rarr; Metadata Sets* to open the
*Metadata Sets* window.

![Figure 3: Adding a new Metadata Set is easier than ever with Liferay's advanced metadata set editor.](../../images/dm-metadata-sets-list.png)

You can view, edit, or add metadata sets from the Metadata Sets window. You
can select the checkbox for all the sets or select checkboxes for individual
sets to move sets to the Recycle Bin. The *Order by* selector lets you order the
sets by *Modified Date* or *ID*.

The following actions can be selected for each metadata set:

- *Edit*: opens an editor for modifying the metadata set.

- *Permissions*: grant or revoke permission for roles to update, delete, view,
or configure permissions for the metadata set.

- *Copy*: Make a copy of the metadata set.

- *Delete*: Move the set from Documents and Media to the Recycle Bin.

If you click the *Add* icon
(![Add](../../images/icon-add.png)), you can use same metadata set editor for
defining a metadata set that you used in the Add Document Type window.

Remember that metadata sets created independently are reusable. Once they have
been created they can be included in any number of document types as additional
metadata sets. 
