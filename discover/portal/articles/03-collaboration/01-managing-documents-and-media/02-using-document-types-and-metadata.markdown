# Using Document Types and Metadata Sets [](id=using-document-types-and-metadata-sets)

If you're concerned only with a file's content and are happy with its basic
built-in metadata, then add the file to Documents and Media as a Basic Document.
What do you do, however, if you want to ascribe more characteristics to a file?
For example, you might have documents to which you want users to apply
copyrights and license information. Or you might want to add searchable
attributes to documents. Is there a way to do these things in Documents and
Media? Yes! @product@ enables you to define metadata for describing files.
Liferay provides tools to create and aggregate metadata into Metadata Sets that
you can apply to Document Library files. You leverage the Metadata Sets through
Document Types. These Document Types wrap the Documents and Media files so users
can completely describe them. 

Documents and Media uses a Document Type to classify every Document Library
file. A Document Type wraps each file with a type name and metadata. The
metadata fields let authors describe the document. As a Documents and Media
administrator you create metadata fields, add them to Metadata Sets, and apply
the sets to various Document Types.

Typically you'll want to specify document metadata fields in a Metadata Set and
then apply it to a Document Type. So let's start creating a metadata set.

## Creating Metadata Sets [](id=creating-metadata-sets)

Metadata Sets aggregate metadata fields so you can apply them to Document Types.
Liferay provides Metadata fields to capture textual, boolean, date, numerical, location,
and descriptive characteristics about documents. When users create or edit
a document of a document type that has a metadata set, they specify metadata
field values to describe the document.

@product@ supports the following metadata fields:

**Boolean**: is a check box.

**Date**: lets you enter a date. A valid date format is required for the date
field, but you don't have to enter a date manually. When you select the date
field a mini-calendar pops up which you can use to select a date.

**Decimal**: lets you enter a decimal number. The value is persisted as a
double.

**Documents and Media**: lets you select a file from one of the @product@
instance's Documents and Media libraries.

**Geolocation**: lets you specify a location to associate with the document.

**HTML**: an area that uses a WYSIWYG editor to enhance the content.

**Integer**: lets you enter an integer. The value will be persisted as an int.

**Link to Page**: lets you link to another page in the same site.

**Number**: lets you enter a decimal number or an integer. The value is
persisted either as a double or an int, depending on the type of input.

**Radio**: displays several clickable options. The default number of options is
three but this is customizable. Only one option can be selected at a time.

**Select**: is just like the radio field except that the options are hidden and
have to be accessed from a drop-down menu.

**Text**: lets you enter a single line of text.

**Text Box**: is just like the text field except you can enter multiple lines of
text or separate paragraphs. 

The easiest way to create metadata fields is by creating them in Metadata Sets.
In Documents and Media in Site Administration, select *Options
(![Options](../../../images/icon-options.png)) &rarr; Metadata Sets* to open the
Metadata Sets window.

![Figure 1: The Metadata Sets management window lets you view existing sets and create new ones for applying to document types.](../../../images/dm-metadata-sets-list.png)

You can view, edit, or add metadata sets from the Metadata Sets window. If you
select the check box next to the *Order by* heading all the sets are selected.
If you select one or more sets, the Recycle Bin icon (![Recycle
Bin](../../../images/icon-trash.png)) appears. To move selected metadata sets to
the Recycle Bin, click the Recycle Bin icon. The *Order by* selector lets you
order the sets by *Modified Date* or *ID*. The up and down arrows let you
arrange the sets in ascending or descending order, respectively. You can also
click on a metadata set's name to edit it.

The following actions can be performed on each metadata set.

**Edit**: opens an editor for modifying the metadata set.

**Permissions**: grant or revoke permission for roles to update, delete, view,
or configure permissions for the metadata set.

**Copy**: Make a copy of the metadata set.

**Delete**: Move the set from Documents and Media to the Recycle Bin.

You create metadata sets independently of any document types, but you can apply
them to any number of document types.

As an example, let's create a metadata set called *Syllabus Fields* for a
document type called *Syllabus*. What metadata should we associate with syllabi?
Let's choose for our syllabi to have course title, professor, location,
semester, course description, and course requirements fields. All syllabi in our
Liferay instance should be created using the *Syllabus* document type and
authors must specify values for these fields. This ensures that a syllabus shows
up in the Search app when keywords match its metadata field values.

To start creating the Metadata Set, click the *Add* icon
(![Add](../../../images/icon-add.png)). The *New Metadata Set* window appears. Name
the metadata set *Syllabus Fields*.

Note that you can translate each of a metadata set's field values to any of the
locales your Liferay instance supports. To specify a field value for a
translation, select the flag that represents the locale and enter the field
value for the locale.

In the *Details* dropdown section you can describe the metadata set and select
an existing metadata set to extend. If there's a metadata set to which you want
to simply append more fields, then click the *Select* button and choose the
metadata set you want as your new metadata set's parent. When a user creates
adds a document of a document type that uses this metadata set, the parent
metadata fields will appear above the other fields you define in this metadata
set.

Next up is the metadata fields editor. Select the editor's *View* tab and select
the *Fields* tab within it. Here you have all of the field types available for
you to use in creating your metadata set. Icons representing the field types are
listed on one side and the metadata set's canvas is on the other side. To add a
field type to the metadata set, select its icon and then drag and drop it onto
the canvas. The field shows on the canvas as it will for document type users.
Note, by dragging a field onto a field that's already on the canvas, you can
nest the new field in the existing field.

When you move your mouse over a field on the canvas, the field action icons
(![Icons](../../../images/icon-dm-metadata-actions.png)) appear. Clicking the
wrench shows the field's settings table opposite the canvas. Clicking the *+*
icon creates a duplicate of the current field and adds it below the current
field. Clicking the trash can deletes the field. 

![Figure 2: The metadata fields editor for Metadata Sets and a Document Type's Main Metadata Fields helps you define custom document types.](../../../images/dm-metadata-set-settings.png)

To configure a field's settings, just click on the field in the canvas. This
automatically selects the Settings tab to show the settings table opposite the
canvas. Alternatively, you can access the Settings tab by clicking the field's
*Settings* icon (wrench). To edit the setting value, double-click on the setting
value in the settings table and specify your new value.

Labels, default values, variable names, mouse-over tips, widths, and other
settings can be configured for most fields. Some fields have a *Required*
setting that lets you specify whether users must specify a value for the field.
If a field's *Repeatable* setting is set to *Yes*, users can add multiple
consecutive instances of the field to the document's metadata.

Click *Save* when you're done specifying your new metadata set. Now you can
apply the metadata set to a new Document Type.

## Creating Document Types [](id=creating-document-types)

When apply to Document Types enrich your document library files with information
and integrate them further with @product@'s features. Not only do Document
Types allow you to encourage, and even demand, that users specify important
document metadata, but they also help you integrate the document with more of
@product@'s features, such as Search and Workflow. Liferay's Search picks
up on Documents and Media file metadata, so users can find files faster. You can
apply workflows to specific document types. And you can more cleanly organize
document libraries by designating folders to exclusively hold particular
document types. 

Let's open up the Document Types manager to start creating a document type. In
Documents and Media in Site Administration, select *Options
(![Options](../../../images/icon-options.png)) &rarr; Document Types* to open the
*Document Types* window. It lists all of the current custom document types. From
Search you can enter keywords to find matching document types. Selecting the
check box next to *Order by* selects all of the document types. When one or more
are selected the *Recycle Bin* icon (![Recycle
Bin](../../../images/icon-trash.png)) appears. You can click the *Recycle Bin* icon
to move the selected document types from Documents and Media to the Recycle Bin.

The following actions are available for each document type.

**Edit**: opens a window for modifying the document type.

**Permissions**: lets you grant or revoke roles the ability to update, delete,
and view the document type, and configure its permissions.

**Delete**: moves the document type from Documents and Media to the Recycle Bin.

A document type must be associated with one or more metadata sets. When creating
a new document type, you can define *Main Metadata Fields* or select *Additional
Metadata Fields*. Main metadata fields are directly tied to their document type.
Main metadata fields can only be created within a document type's editor window
and they cannot be used with other document types. You can apply existing
metadata sets to a document type in the document type's *Additional Metadata
Fields* section. You can differentiate the document types that implement the
same additional metadata sets by defining different main metadata fields for
them.

Let's create a *Syllabus* document type to which we can apply the *Syllabus
Fields* metadata set we created earlier. From the Document Types management
window, click *Add*. This opens the New Document Types window. Name the document
type *Syllabus*. Click the *Details* dropdown section to show a text area for
entering a description.

The *Main Metadata Fields* section shows the same metadata editor that you used
for defining your metadata set. The editor lets you define metadata by dragging
field types from the icon area and dropping them onto the canvas. You can define
a document type's metadata in the Main Metadata Fields section and/or define it
using existing metadata sets.

In the *Additional Metadata Fields* section, you can select existing metadata
sets to apply to the document type in addition to the document type's main
metadata set. Click *Select* and choose the *Syllabus Fields* metadata set you
created earlier. You've now added the metadata set to your *Syllabus* document
type.

The document type's *Permissions* section lets you specify its permissions. By
default, the document type is viewable by anyone, including site guests. But you
can restrict the type's view, update, delete, and permissions configuration
permissions to site members or the document type's owner.

When you're done creating your new document type, click *Save*. The document
type is now accessible from Documents and Media's *Add* menu. When users create
new instances of the document type, they're presented with metadata fields to
describe the document. These fields look the same as they do in the metadata
editor canvas.

Document Types and their Metadata Sets let users ascribe important information
to documents. The metadata is available for users to inspect in the document's
file entry view. The metadata is also searchable using @product@'s Search
app. As you've witnessed, Document Types and Metadata Sets make documents rich
with information and integrate them more with @product@'s features. 
