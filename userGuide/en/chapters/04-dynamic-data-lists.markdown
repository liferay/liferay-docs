# Dynamic Data Lists in Liferay

## The Evolving Business Model

As needs change in business and organizations, the technology they use must adapt as well.  Say one day, you'd like your team to sign up for a holiday party.  You want to enter in custom sets of data, allow your users to add their information, and be able to access the set of data.  And you don't want to write a line of code.  Enter Liferay's Dynamic Data Lists: an easy way to create, aggregate and display new data types.  Data Lists are flexible enough to handle all types of data, both simple and complex.  Every fall, you may need to put up a job posting board, only allowing administrators to create new job posts.  Or you could simply want to allow users to manage a notebook or To-Do list on their private pages.

## Defining Data Types

Using data lists to outline a new data model is as simple as point and click.  Using an intuitive visual editor, you can quickly draw up the skeleton for a job posting in minutes. Since data lists exemplify a unique type of content for your site, you can find them in the content section of the control panel, going to *Go To > Control Panel > Content > Dynamic Data Lists* or *Manage > Site Content > Dynamic Data Lists*.  Within the dynamic data lists section, you can either create a new data type (**Manage Data Definitions**) or a new list from an existing data type.

![Figure X.x: Data Lists in the control panel.](../../XX-ddl-control-panel.png)

When creating a new data definition, you have a palette of fields to lay out, as well as a blank canvas to construct the definition.  The interface will look very similar to creating and editing web content structures covered previously.  Let's explore the different data types at our disposal:

**Boolean:**  Presents a checkbox to the user and stores either a `true` (checked) or `false` (unchecked) based on state.

**Date:**  A preformatted text field that displays a convenient date picker to assist in selecting the desired date.  The format for the date is governed by the current locale.

**Number:** A text box that only accepts numbers as inputs, but puts no constraints on the kind of number entered.

**Integer:** Similar to `Number`, except that it constrains user input to non-fractional numbers.

**Decimal:**  Similar to `Number`, except that it requires a decimal point (`.`) be present.

**Document Library:**  Allows the user to select an existing document from the **document library** to attach to the data record.

**File Upload:**  Allows the user to upload a document from their computer to the document library to attach to the data record.  The upload can be constrained to a containing folder inside the document library, or the user's default upload location.

**Radio:** Presents the user with a list of options to choose from, using the radio button inputs.  Values are stored as `string`s.  Similar to `Select`.

**Select:** Lists a selection of options for the user to choose from, using a combo box.  Can be configured to allow multiple selections, unlike `Radio`.

**Text:** A simple text field for any `string` input.

**Text Box:** A large text box for long text input.

![Figure X.x: Data definition fields.](../../XX-data-definition-screen.png)

When creating data definitions, you can also customize the appearance of the input fields, and provide helpful tips and hints for those entering data.  Some data types have specific configuration options, but all have some in common.  The following properties can be edited by double-clicking on any field, by clicking the gear icon in the upper-right corner of the field, or by clicking the *Settings* tab when the field is selected (highlighted with a green background).  Most of the properties you will find there are fairly self-explanatory, but there are a few that need a closer look --

**Multiple (Select):**  When set to `Yes`, allows the user to select more than one option.  This defaults to no.

**Allowed File Extensions (File Upload):**  By default, form validation will accept any file type to be submitted.  Set this value to a comma-delimited list of extensions, including the character `.`, and Liferay will check the extension before the file can be uploaded.

**Folder (File Upload):**  Set the location the document will be uploaded to in the document library.  You can choose from an existing folder, create one, or default to the document library's home location.

![Figure X.x: Data field properties.](../../XX-data-definition-field-properties.png)

In addition to dragging the fields around to create your desired forms, you can stack inputs within inputs, by dragging a field within another field.  Organize your data into unlimited levels of hierarchy, creating the clearest, most logical data model easily.  There is also a duplicate button on each field (the middle button), allowing you to easily clone any field as many times as you need.

A number of data definitions ship with the portal to help you get started: *To Do*, *Issues*, *Meeting Minutes*, and *Contacts*.  Use these on their own to generate new data lists, or tweak them to fit your use case.  All that's left to do is build a new data list, and let your users play with it.

## Creating Data Lists

Dynamic data lists are simply sets of new data types presented in a custom way, just as web content is sets of information presented in a custom way.  Similarly, data definitions drive the content of a data list much like web content structures drive the content of a web content piece.  With a data definition in place, all that remains is to create a new data list to capture the information we're going after.  This is the easiest step in creating the list, with only a few clicks between a data definition and robust data entry and delivery.

If we wanted to create a new jobs listing with the jobs post data definition:

1.  From the *content* section of the Control Panel, select Dynamic Data Lists.
2.  Click on *Add* to create a new list based on a data definition, which in our case will be the job post.
3.  Give the data list a name, like *Open Jobs*, and a description to assist administrative users in the future.
4.  Last, and most importantly, click *Selet* under the **Data Defition** section -- this is where you set the data model that drives this list.
5.  Choose the *Job Post* data definition you created, then click *Save*.

Your new list of available jobs is now ready to be populated!

![Figure X.x: Entering a new data record.](../../XX-ddl-add-record.png)


### Using Default Displays

![Figure X.x: The default data list display.](../../XX-ddl-list-display.png)

While you can interact with the data list within the control panel, and ask everyone to contribute through the content section of their control panel, it's more likely you want to give them a simple way to access the list.  Liferay provides the **Dynamic Data List Display** portlet to ease the integration of your new list onto your site.  With your jobs posting list in hand, head over to the page you want, and add the portlet.  Like the web content display, you just configure the data list display to show your list and you're done!  The default display will spill out the contents of the list, but can be configured to use a different display template, which will be explored later.  The two important configuration options to consider are:

-  **Editable:** Allows users that have permission to add new entries to the list.  By default, this is disabled and when enabled, administrators are the only ones with add permission.  To easily grant access to other users, edit the permissions on the list you'd like to grant access to, and grant the `Add Record` permission.

-  **Spreadsheet View:**  Displays the list in a dynamic spreadsheet view, allows users with permission to interact with the list in the same way as in a standard spreadsheet program.

If you need more customizability with the display of the list or the data entry form, you may want to consider a custom **list template** or **detail template**.

![Figure X.x: The spreadsheet view](../../XX-ddl-spreadsheet-view.png)

### Using Data List Forms

In addition to the the standard Dynamic Data List Display, Liferay provides the Dynamic Data List Forms portlet.  Unlike the data list display, the forms portlet is tailored to entering new records.  When you deploy that data list for your users to sign up for a retreat, using the data list form allows you to simplify the sign-up process and hide the contents of the list.

### Using Workflow

Liferay integrates the powerful features of workflow and the data capabilities of dynamic data lists in **Kaleo Forms**.  Workflow is not directly enabled in the dynamic data list portlets, instead allowing you to build custom forms backed by a data list.  You can then deploy custom workflows to the form and its data.  See the section on Workflow for more information.

## Make It Pretty: Creating Custom Displays

If the default display and data entry forms don't suit your purposes, or your manager requests a change to the interface that you swear can only be done with development, never fear!  Dynamic data lists provides two areas to customize: detail templates and list templates.

### Detail Templates

The default data entry form is simply the entire data model you created in a data definition, both required and optional fields.  Using a detail template, you can limit the fields displayed for entry, or change the order of elements.  Data definitions can have multiple templates, that you choose when displaying in either a dynamic data list display, or a dynamic data list form.  To access and create new templates, go to **Control Panel > Content > Dynamic Data Lists > Manage Data Definitions**, choose the data model you want to modify, click the *Actions* button, and choose *Manage templates*.  Once you click on *Add Detail Template*, you'll be presented with the same kind of graphical, drag-and-drop interface used for creating the data definition.  Move items around, and delete unwanted fields from view and save when ready.  Create as many as you might need, and change them around in the portlets to see how each feels.

### List Templates

Just like detail templates, list templates are found in the Manage Templates section of a data definition.  With list templates, you can customize the display of a list in precisely the same way as　you can customize web content.  List templates allow either Freemarker or Velocity templates, pulling data from the data definition in the same way that web content templates pull data from their structures.  For more information on using Velocity and other templates to pull data from a backing structure, see web content templates.

## Take It Further

With the ability to create dynamic sets of data, customize both the data display as well as the entry, the possible combinations are limitless.  In　some ways, it can be viewed as a way to deliver small-scale applications that display desired information quickly.


