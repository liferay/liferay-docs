# Metadata Sets [](id=metadata-sets)

You can define metadata fields that users fill out when they create or edit
Document Library files. You do this by creating *metadata sets* and then
associating them with document types, which wrap Document Library files and thus
apply your metadata fields to the files. Although you apply metadata sets via
document types, metadata sets exist independently and you can apply them to any
number of document types. 

## Managing Metadata Sets [](id=managing-metadata-sets)

To see the available metadata sets, open the *Menu* 
(![Product Menu](../../../images/icon-menu.png)), expand your site's menu, and
navigate to *Content* &rarr; *Documents and Media*. Then click the *Metadata
Sets* tab. The sets appear in a table. 

To select a metadata set, select the checkbox to its left. To select all the
sets, select the checkbox in the Management Bar. With one or more sets selected,
an `X` icon appears in the Management Bar. Clicking it deletes the selected
metadata set(s). Note that metadata sets don't support the Recycle Bin. If you
delete a metadata set, it's gone forever. 

The Management Bar also contains other options for managing the metadata sets.
The selector menu to the right of the checkbox filters the sets the table
displays (it's set to *All* by default). The *Order by* selector orders the sets
by Modified Date or ID. The up and down arrows sort the sets in ascending or
descending order, respectively. You can also use the Search bar to search for
a set. 

In the table, each metadata set has an Actions button 
(![Actions Menu](../../../images/icon-actions.png)) for performing the following
actions on that set: 

-   **Edit**: Edit the set. Alternatively, click the set's name in the table.
-   **Permissions**: Configure the set's permissions. 
-   **Copy**: Copy the metadata set. 
-   **Delete**: Delete the set. 

![Figure 1: The Metadata Sets management window lets you view existing sets and create new ones for applying to document types.](../../../images/dm-metadata-sets-list.png)

## Creating Metadata Sets [](id=creating-metadata-sets)

Follow these steps to create a metadata set: 

1.  From the *Menu* (![Product Menu](../../../images/icon-menu.png)), click your
    Site's name and navigate to *Content* &rarr; *Documents and Media*. Then
    click the *Metadata Sets* tab. 

2.  Click the *Add* button 
    (![Add](../../../images/icon-add.png)). The New Metadata Set form appears. 

3.  Give your metadata set a name. 

4.  Open the *Details* section of the form to give your metadata set a 
    description or select a metadata set to extend (both are optional). To 
    select a metadata set to extend, click the *Select* button for *Parent 
    Metadata Set* and then select the metadata set. When a user creates a 
    document of a document type that uses an extended metadata set, the parent
    metadata set's fields appear above the extended metadata set's. 

5.  Add the metadata fields that should be part of this metadata set. To do 
    this, first select the editor's *View* tab and select the *Fields* tab 
    within it. Icons representing the field types are listed on one side and the 
    metadata set's canvas is on the other side. To add a field type to the
    metadata set, select its icon, drag, and drop it onto the canvas. The field
    shows on the canvas as for document type users. By dragging a field onto
    a field that's already on the canvas, you can nest the new field in the
    existing field. When you mouse over a field on the canvas, the field action
    icons (![Icons](../../../images/icon-dm-metadata-actions.png))
    appear. Clicking the *+* icon creates a duplicate of the current field and
    adds it below the current field. Clicking the trash can deletes the field. 

    The following metadata fields are available:

    -   **Boolean:** A check box. 
    -   **Color:** Specifies a color. 
    -   **Date:** Enter a date. A valid date format is required for the 
        date field, but you don't have to enter a date manually. When you select 
        the date field a mini-calendar pops up which you can use to select a 
        date. 
    -   **Decimal:** Enter a decimal number. The value is persisted as 
        a `double`. 
    -   **Documents and Media:** Select a file from a Documents and Media library. 
    -   **Geolocation:** Specify a location to associate with the 
        document.
    -   **HTML:** An area that uses a WYSIWYG editor to enhance the content. 
    -   **Integer:** Enter an integer. The value is persisted as an 
        `int`. 
    -   **Link to Page:** Link to another page in the same site.
    -   **Number:** Enter a decimal number or an integer. The value is
        persisted either as a `double` or an `int`, depending on the input's type.
    -   **Radio:** Displays several clickable options. The default number of 
        options is three but this is customizable. Only one option can be 
        selected at a time.
    -   **Select:** This is just like the radio field except that the options 
        are hidden and must be accessed from a drop-down menu. 
    -   **Text:** Enter a single line of text.
    -   **Text Box:** This is just like the text field except you can enter 
        multiple lines of text or separate paragraphs. 
    -   **Web Content:** Select web content.

    ![Figure 2: Add your metadata set's fields to the canvas.](../../../images/dm-metadata-set-fields.png)

6.  Edit your fields to reflect their intended metadata. For example, a text 
    field's default label is *Text*. If you want to use the text field as a 
    title, for instance, then you should change the field's label to *Title*. To 
    do this, first select the field on the canvas. This automatically selects 
    the *Settings* tab on the left. Alternatively, you can access the Settings 
    tab by clicking the field's wrench icon. To edit a setting value, 
    double-click it in the Settings table and enter the new value. 

    Labels, default values, variable names, mouse-over tips, widths, and other
    settings can be configured for most fields. Some fields have a *Required*
    setting for specifying whether users must populate the field. If a field's
    *Repeatable* setting is set to *Yes*, users can add multiple consecutive
    instances of the field to the document's metadata. 

    Also note that you can translate each of a metadata set's field values to
    any supported locales. To specify a field value for a translation, select
    the flag that represents the locale and enter the field value for the
    locale. 

    ![Figure 3: Edit your metadata set's fields to match the metadata that you want each field to hold.](../../../images/dm-metadata-set-settings.png)

7.  Click *Save* when you're done specifying your new metadata set.
