# Document Types and Metadata Sets [](id=document-types-and-metadata-sets-lp-6-2-use-useportal)

When a user assigns a document type to a document, the user is required to fill
out the fields defined by the metadata set of the document type. This encourages
users not to forget to enter important information about their documents. For
example, you could create a *copyrighted* document type and require users to
enter a license for all "copyrighted" documents. More importantly, document
types and metadata sets can make it easier to find documents. The values that
users enter into the fields determined by their document type's metadata set
become searchable entities within the portal. You can use Liferay's search
portlet to search for these terms. Document types and metadata sets are
accessible from the *Manage* button at the top of the Documents and Media
portlet window.

![Figure 5.3: Navigate to the Document Types Dialog Box by clicking *Manage* &rarr; *Document Types*.](../../images/05-document-types.png)

You can add a new document type using the Add button at the top of the dialog
box. To do so, you need to define one or more metadata sets to associate with
your document type. When creating a new document type, you can define *Main
Metadata Fields* or select *Additional Metadata Fields*. Main metadata fields
are directly tied to their document type and cannot be made available to other
document types. Additional metadata fields, by contrast, can be defined
independently and can be used in many different document types. You can
differentiate the document types that implement the same additional metadata set
by defining different main metadata fields for them. However, additional
metadata fields need to be defined and saved before creating a document type
that will implement them. 

As an example, we could create a document type called *Syllabus* and define a
metadata set. What metadata should we associate with syllabi? Let's choose for
our syllabi to have course title, professor, semester, course description, and
course requirements fields. All syllabi in our portal should maintain entries
for these fields. This ensures that a syllabus shows up in a portal search
if its course title, professor or semester is searched for. Since we don't want
to use our metadata set for any document type other than *Syllabus*, let's
create our metadata set under the Main Metadata Fields area. Alternatively, we
could create our metadata set independently using *Manage &rarr; Metadata Sets
&rarr; Add* and then select it as an Additional Metadata Field. 

You can view, edit, or add metadata sets from the *Manage &rarr; Metadata*
window. A metadata set consists of a group of fields. If you click the *Add*
button, you can use same UI for defining a metadata set that you used in the Add
Document Type window.

![Figure 5.4: Adding a New Metadata Set is easier than ever with Liferay's advanced editor.](../../images/05-new-metadata-set.png)

<!-- Needs to be replaced for 6.2 -->
<!-- Editor had icons missing during image updating cycle -->

Make sure the Fields tab is selected on the left. Then, to define a metadata
set, just choose fields to use from the area on the left and drag and drop them
into the area on the right. The drag and drop interface allows for nested fields
so you need to be careful about where you drop the fields. Default values,
mouse-over tips, widths, and other settings can be configured for most fields.
To configure these settings, just click on a field from the area on the right.
This automatically selects the Settings tab on the left. Alternatively, you can
access the Settings tab by clicking the fields *Settings* button (wrench). Then
double-click on a value to edit. Liferay supports the following kinds of fields
for metadata sets:

**Boolean:** is a checkbox.

**Date:** lets you enter a date. A valid date format is required for the date
field, but you don't have to enter a date manually. When you select the date
field a mini-calendar pops up which you can use to select a date.

**Decimal:** lets you enter a decimal number. The value will be persisted as a
double.

**Documents and Media:** lets you select a file from one of the portal's
Documents and Media libraries.

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

Remember that metadata sets created independently are reusable. Once they have
been created they can be included in any number of document types as additional
metadata sets. Next, let's take a look at tags. Tags can be attached to most
forms of web content that can be created in Liferay, including documents.
