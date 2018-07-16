# Creating Data Definitions [](id=creating-data-definitions)

Follow these steps to create a data definition: 

1.  Open the Menu 
    (![Menu](../../../images/icon-menu.png)) 
    and expand your site's menu (the Site Administration menu). Then select 
    *Content* &rarr; *Dynamic Data Lists*. This opens the Dynamic Data Lists 
    screen. A table lists any existing dynamic data lists. 

2.  Click the *Options* button at the top-right 
    (![Options](../../../images/icon-options.png)) 
    and select *Manage Data Definitions*. The Data Definitions screen appears. A
    table lists any existing data definitions. Several definitions are embedded
    for common use cases like contacts, events, inventory, and more. 

    ![Figure 1: The Data Definitions screen.](../../../images/ddl-definitions.png)

3.  Click the *Add* button 
    (![Add](../../../images/icon-add.png)) 
    to begin creating a new data definition. This opens the 
    *New Data Definition* form. 

4.  Give your data definition a name. Note that the definition's name will 
    appear to any users filling out a dynamic data list that uses the 
    definition. Then expand the *Details* section of the form and give it a 
    description. 

5.  The Details section of the form also contains the field *Parent Data
    Definition*. This optional field lets you select an existing data definition
    (the parent) to form the basis of the new one (the child). The child
    definition inherits the parent's fields and settings, which you can then add
    to. When you create a dynamic data list from a child definition, it includes
    the fields of the parent and child definitions. This lets you use a common
    definition (the parent) as the basis of a specialized definition (the
    child). For example, if you were planning a rock climbing trip, you could
    use the default Events definition as the parent of a Rock Climbing Trip
    definition that contains fields unique to rock climbing (e.g., climbing
    equipment availability, altitude, etc.). 

    To choose a parent definition, click the *Select* button below the *Parent
    Data Definition* field and then select an existing definition in the dialog
    that appears. 

    ![Figure 2: After naming your data definition, expand the Details section of the form and give your definition a description and parent definition, if desired.](../../../images/ddl-definition-form-01.png)

6.  Add the data definition's fields in the data definition designer, which is
    below the form's Details section. The designer's *View* tab, which is
    selected by default, lets you create the definition in a WYSIWYG editor. You
    can click the *Source* tab to work with the definition's underlying XML, but
    it's much easier to stick with the WYSIWYG editor. 

    In the *View* tab select the *Fields* tab. Icons representing the field
    types are listed on one side and the data definition's canvas is on the
    other side. To add a field type to the definition, select its icon, drag,
    and drop it onto the canvas. By dragging a field onto a field that's already
    on the canvas, you can nest the new field in the existing field. When you
    mouse over a field on the canvas, the field action icons 
    (![Icons](../../../images/icon-ddl-actions.png)) 
    appear. Clicking the *+* icon creates a duplicate of the current field and
    adds it below the current field. Clicking the trash can deletes the field. 

    The following fields are available:

    -   **Boolean:** A check box. 
    -   **Color:** Specifies a color. 
    -   **Date:** Enter a date. A valid date format is required for the date 
        field, but you don't have to enter a date manually. When you select the 
        date field a mini-calendar pops up which you can use to select a date. 
    -   **Decimal:** Enter a decimal number. The value is persisted as a 
        `double`. 
    -   **Documents and Media:** Select a file from a Documents and Media 
        library. 
    -   **Geolocation:** Specify a location to associate with the document.
    -   **HTML:** An area that uses a WYSIWYG editor to enhance the content. 
    -   **Integer:** Enter an integer. The value is persisted as an `int`. 
    -   **Link to Page:** Link to another page in the same site.
    -   **Number:** Enter a decimal number or an integer. The value is
        persisted either as a `double` or an `int`, depending on the input's 
        type.
    -   **Radio:** Displays several clickable options. The default number of 
        options is three but this is customizable. Only one option can be 
        selected at a time.
    -   **Select:** This is just like the radio field except that the options 
        are hidden and must be accessed from a drop-down menu. 
    -   **Text:** Enter a single line of text.
    -   **Text Box:** This is just like the text field except you can enter 
        multiple lines of text or separate paragraphs. 
    -   **Web Content:** Select web content.

    ![Figure 3: Use the data definition designer to add fields to the data definition.](../../../images/ddl-data-definition-designer.png)

7.  Edit field labels to reflect their intended data. A text field's default
    label is *Text*. To use the text field as a title, then you should change
    the field's label to *Title*. First select the field on the canvas. This
    automatically selects the *Settings* tab on the left. Alternatively, you
    can access the Settings tab by clicking the field's wrench icon. To edit a
    setting value, double-click it in the Settings table and enter the new
    value. The available settings are listed below. 

    You can translate each of a data definition's field values to any supported
    locales. To specify a field value for a translation, select the flag that
    represents the locale and enter the field value for the locale. 

    The following field settings are available. Note that some of these settings 
    are only available for specific field types:

    -   **Type:** The field's type (e.g., text, radio, etc.). This setting can't 
        be edited, but a display template can reference it. 
    -   **Field Label:** The field's display name. 
    -   **Show Label:** Whether the field label is shown. 
    -   **Required:** Whether users must fill out the field (not available for 
        Boolean fields). 
    -   **Name:** The field's internal identifier. You can use this value in a 
        display template to read the field's data. This value is automatically 
        generated, but you can change it if you wish. 
    -   **Predefined Value:** The field's default value. 
    -   **Tip:** Text to display in a tooltip. 
    -   **Indexable:** Whether the field is indexed for search. 
    -   **Localizable:** Whether the field can be translated. 
    -   **Repeatable:** Whether users can make copies of the field. 
    -   **Width:** The field's width on the page. Possible width values are 
        *Small*, *Medium* and *Large* (not available for these field types: 
        Boolean, Documents and Media, Geolocation, Radio, and Select). 
    -   **Multiple:** Whether the user can select more than one option. This is 
        only available for Select fields. 
    -   **Options:** The options available for selection in Radio and Select 
        fields. You can add and remove options, and edit each option's display 
        name and value. 

    ![Figure 4: Configure the settings for each field in your data definition.](../../../images/ddl-data-definition-settings.png)

8.  Click *Save* when you're done. Your new data definition then appears in the
    table with the pre-defined ones and any you've already added. 
