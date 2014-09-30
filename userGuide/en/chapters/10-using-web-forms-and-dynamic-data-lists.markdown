
# Using Web Forms and Dynamic Data Lists [](id=using-web-forms-and-dynamic-data-lists-liferay-portal-6-2-user-guide-10-en)

As needs change in business and organizations, the technology used to fulfill
those needs must adapt as well. People use electronic means to do things that
years ago were done using manual processes. For example, you may want your team
to sign up on your web site for a holiday party. Or maybe every fall, you need
to put up a job posting board, only allowing administrators to create new job
posts. Maybe you want to allow users to manage a notebook or To-Do list on their
private pages. In all of these cases, you want to enter in custom sets of data,
allow your users to add their information, and be able to access the set of
data. 

In the past, you'd need to be a developer to accomplish any of this. Today, you
can do it without writing a single line of code. Enter Liferay's *Dynamic Data
Lists*. This is an easy way to create, aggregate, and display new data types.
Data Lists are flexible enough to handle all types of data, and you don't have
to write any code. Simply put, Liferay gives you the power to perform the
following actions:

- Define your own data definitions

- Create new lists from those definitions

- Customize the input forms for ease of use

- Customize the output format

- Integrate lists into Workflow 

All of this capability can be easily distilled into two concepts: data defining
and data displaying. These data lists are dynamic for a reason: they are
flexible and powerful. Whether you want to collect simple input from the user or
develop an entire data entry system for real estate listings, Dynamic Data Lists
have your use case covered. Combined with the flexibility provided through
templates and the power of languages like Velocity, entire applications can be
built in a short time.

## Building a List Platform in Liferay and Defining Data Types [](id=building-a-list-platform-in-liferay-and-liferay-portal-6-2-user-guide-10-en)

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

**Documents and Media:** select an existing uploaded document or image to attach
to the data record. Also has the ability to upload documents and images into the
Document Library.

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

## Creating Data Lists [](id=creating-data-lists-liferay-portal-6-2-user-guide-10-en)

Building out new lists really isn't all that different from creating new pieces
of web content. Just as you can create new Web Content Structures to control the
input of a particular type of web content, you can use Data List Definitions to
control the input of new list types. Similarly, just as you create a new piece
of web content, selecting the Structure you would like to use with it, `list.it`
users (we'll call them *Listies*) choose the Definition they want to use when
creating a new list. Now that a data definition is in place, all that remains is
to create a new data list to capture the information we're going after. This is
the easiest step in creating the list, with only a few clicks between a data
definition and robust data entry and delivery.

To create a new volunteer list with the "Volunteer Sign-Up" definition:

1. From the Dockbar, click on *Admin* &rrar; *Content* and then on *Dynamic Data
   Lists*.

2. Click on *Add* to create a new list based on a data definition, which in our
   case is the volunteer sign-up.

3. Give the data list a name, like *Spring Move-In Sign Up* and a description to
   assist administrative users in the future.

4. Last and most importantly, click *Select* under the *Data Definition*
   section--this is where you set the data model that drives this list.

5. Choose the *Volunteer Sign-Up* data definition you created, then click
   *Save*.

Now that you've created your brand new volunteer list, you can pester everyone
you know to sign up. But what would it look like for them to add an entry to
this list? The data definition you've previously created (or selected) defines
the layout as well, which means the form looks just the way you laid it out. 

![Figure 10.4: You can enter a new data record by clicking on *Admin* &rarr; *Content* from the Dockbar, clicking on *Dynamic Data Lists*, clicking on an existing list, and then clicking on the *Add* button next to the list's name. Alternatively, you can install the Dynamic Data List Form portlet, add it to a page, and configure it to allow users to submit new data records for a lists.](../../images/05-ddl-add-record.png)

But how will this data appear? How will my awesome, new Volunteer Sign-Up sheet
or that boring Jobs Listing look? The answers to these pressing, burning
questions bring us to the mecca that is the display side of this equation.

### Using Data List Forms [](id=using-data-list-forms-liferay-portal-6-2-user-guide-10-en)

A nice way to enable people to use your forms is the Dynamic Data List Form
portlet. This portlet is tailored to entering new records. When you deploy that
data list for your users to sign up for a retreat, or your family members to
volunteer to help you move, using the data list form allows you to simplify the
sign-up process and hide the contents of the list.

The Dynamic Data List Form portlet is not a core Liferay portlet like the Web
Content Display portlet. However, it's freely available on [Liferay Marketplace](http://www.liferay.com/marketplace). Just search for and install
the Dynamic Data List Form (or Dynamic Data List Form EE) portlet. Using the
Dynamic Data List Form is similar to using the Web Content Display portlet: just
set it up, point it to a list (either existing or new) and let it go. This is
very easy to do. 

To display a list inside the portlet, add the Dynamic Data List Form portlet to
a page: Click the *Add* button on the left side of the screen, click on
*Applications*, search for *Dynamic Data List Form*, then click *Add* next to
its name. With the portlet on the page, click on the *Select List* gear icon.
This opens a configuration popup, where you can select a list to use for the
form entries. When configuring the portlet, make sure to check the *Allow
Multiple Submissions* box if you'd like users to be able to add multiple records
for a list. If this box is not checked, once a user fills out an entry, the
portlet displays a message stating that a record has already been submitted. If
the *Allow Multiple Submissions* box is checked, each time a user visits your
page with the Sign-Up form, the Dynamic Data List Form portlet presents them
with a form to fill out.

---

 ![Note](../../images/tip.png) **Note:** If you want to allow guests to submit
 DDL forms, you'll need to enable the *Allow Multiple Submissions* feature.
 Liferay treats the Guest user as one user. Therefore, if a guest submits a DDL
 form, Liferay assumes the Guest user filled out their one form and it won't
 allow any other guests to submit a form. Thus, allowing the Guest user to
 submit multiple submissions allows all your guests to submit DDL forms.

---

Once records have been submitted for lists, you can publish the lists anywhere
in your portal. Read on to find out more about that. 

### Using Default Displays [](id=using-default-displays-liferay-portal-6-2-user-guide-10-en)

Lists are published in the portal through the Dynamic Data List Display portlet.
If Listies don't customize the display, their lists look something like this:

![Figure 10.5: The default data list display in the Control Panel shows the list's records and allows record to added, edited, or removed.](../../images/05-ddl-list-display.png)

This isn't all that exciting, but it allows users to see the list's contents,
and if they have permission, to add and/or edit list items. Within a site like
`list.it`, this type of interaction is used for display-only lists that the user
chooses to expose to others, or for the user's own private lists. But you can
improve the display. You can show the data in a spreadsheet, so you can view the
responses to your Volunteer Sign-Up in a comfortable, easy-to-read format. The
Dynamic Data List Display portlet provides an easy way for a user (such as a
member of a site) to interact with whatever list is active and available.

While it's possible to ask everyone to contribute to the data list within the
control panel, it's much better to give them a simple way to access the list.
Liferay provides the Dynamic Data List Display portlet to ease the integration
of your new list onto your site. With your list in hand, head over to the page
you want and add the portlet. It works much like the Dynamic Data List Form
portlet and the Web Content Display portlet: use the gear icon to select a list
for display or use the pen/paper icon to add a new list. The Dynamic Data List
Display portlet is a Liferay core portlet, unlike the Dynamic Data List Form
portlet. The default display spills out the contents of the list, but can be
configured to use a different display template, which is explored later in this
chapter. The two important configuration options to consider are:

**Editable:** allows users that have permission to add new entries to the list.
By default, this is disabled and when enabled, administrators are the only ones
with add permission. To easily grant access to other users, edit the permissions
on the list you'd like to grant access to, and grant the `Add Record`
permission.

**Spreadsheet View:** displays the list in a dynamic spreadsheet view. This
allows users with permission to interact with the list in the same way as in a
standard spreadsheet program.

![Figure 10.6: The Dynamic Data List Display portlet allows users to view the records belong to a list in either a standard or a spreadsheet view. This figure shows the spreadsheet view.](../../images/05-ddl-spreadsheet-view.png)

Now, as useful as this default display is, and it's certainly useful for my to
do list and my memo notes, it can be an awkward way to ask my volunteers to sign
up. In fact, any time I want other Listies to interact with my lists and
contribute responses, I really just want a simple form to show them. They don't
need to see the full range of responses. And in some cases, it can be hazardous
to your health for everyone to see the responses. Then you don't have to explain
why your sister-in-law won't work with your brother on the same task because of
his B.O. problem. For reasons like that, you'll need to customize the data entry
form or the display of the list. Liferay lets you do exactly that using a custom
*form template* or *display template*. 

## Make it Pretty: Creating Custom Displays [](id=make-it-pretty-creating-custom-displays-liferay-portal-6-2-user-guide-10-en)

When creating custom lists and data definitions, you can control not only how
the input form appears to your users but also how the list itself displays.
Eventually you may realize you need to create another sign-up sheet but you
don't need the same level of detail provided by the Volunteer Sign-Up data
definition you created. Liferay empowers you to customize both the input and
output of your lists to unlimited levels. Dynamic data lists provide two areas
to customize: form templates and display templates. This covers the forms of
lists (*form templates*), as well as the display of the list contents (*display
templates*). 

### Form Templates [](id=form-templates-liferay-portal-6-2-user-guide-10-en)

The default data entry form is the entire data model you created in a data
definition, including required and optional fields. Listies who create new lists
using a data definition will see every item in that definition on the input
form. What if, however, you want a quick sign-up form to find out who's coming
to dinner tonight? Using a form template you can customize the form's display
any way you want. You can limit the fields displayed for entry or change the
order of elements. To access and create new templates, go to the Dockbar and
click *Admin* &rarr; *Content*, click on Dynamic Data Lists, click on *Manage
Data Definitions*, then click on the *Actions* button next to your data
definition of choice and select *Manage Templates*. When you click on *Add Form
Template*, you're presented with the same kind of graphical, drag-and-drop
interface used for creating the data definition. Move items around, delete
unwanted fields from view and save when ready.

---

 ![Tip](../../images/tip.png) Note: Form templates were called *detail
 templates* prior to Liferay 6.2.

---

Note that data definitions can have multiple templates. You can choose the
template you want to use for display in either a dynamic data list display or a
dynamic data list form portlet (see below). You should create as many templates
as you might need, and you can prototype them in the portlets to see how each
feels. 

Now your friends and enemies alike will be impressed with your `list.it` skills.
It may look to the untrained eye like you've single-handedly created three or
four different data types for your lists but you know better. You used the power
that form templates provide, using one data model that encompasses the maximum
information you might need (like preferred activity, favorite color and ideal
schedule). Then you quickly churned out four different form templates with a
few mouse clicks. Now that you have such a vast amount of data collection
options, how will you display them? However you want, as you're about to find
out.

### Display Templates [](id=display-templates-liferay-portal-6-2-user-guide-10-en)

For every data definition, you have an unlimited number of displays you can
create. If you created a special "Thanksgiving Dinner Sign-Up" list using your
"Volunteer Sign-Up" definition, you wouldn't want to confuse fellow Listies by
displaying data fields you never asked for. "Preferred task?" a friend might
say, "I don't remember seeing *that* on the sign-up form!" To avoid such
embarrassing situations, you should create a custom display to match that list.
Taking it even further, you could provide a fancy, JavaScript-driven image
carousel preview of all the attendees of the party. This would complement your
other displays and be another bragging right on `list.it`. Display templates
give you the power to do all this and more.

---

 ![Tip](../../images/tip.png) Note: Display templates were called *list
 templates* prior to Liferay 6.2.

---

Just like form templates, display templates are found in the Manage Templates
section of a data definition. With display templates you can customize the
display of a list in precisely the same way as you can customize web content.
Display templates can be written in FreeMarker or Velocity, pulling data from
the data definition in the same way that web content templates pull data from
their structures. Also similar to web content templates, display templates can
be embedded in other display templates. This allows for reusable code, JS
library imports, or macros which will be imported by Velocity or FreeMarker
templates in the system. Embedding display templates provides a more efficient
process when you have a multitude of similar data definitions. Just import an
embedded display template and work off of it for your new display template.
We'll look at a simple example, but for more information on using template
scripts to pull data from a backing structure, see web content templates in
chapter 3.

The first thing we need to do is create a new display template for our
"Volunteer Sign-Up" data definition. As with many other features in Liferay,
there are multiple ways to do this, depending on your context.

From the Dynamic Data List Display portlet:

1. Navigate to the page with your DDL Display portlet and make sure your list is
   selected in the portlet's configuration.

2. Find the *Add Display Template* icon on the bottom-left corner of the portlet
   window and click it to create a new template. If you don't see the icon, sign
   in as a user with permission to create templates.

From the Dockbar/Control Panel:

1. Click on *Admin* &rarr; *Content*.

2. Navigate to *Dynamic Data Lists* &rarr; *Manage Data Definitions*.

3. Find your data definition in the list, then click *Actions* &rarr; *Manage
   Templates*.

4. Now you can click on *Add Display Template* to create a new template.

Fill out the form with a name and a description. Next, choose a templating
language. Just like web content templates, you can choose between FreeMarker or
Velocity. There is no functional difference between the two. Once you choose the
script language, you can upload a template file or use the display template
editor to type in a script manually. Inside the editor, you have access to a
palette featuring common variables related to your selected template language.
Additionally, you can hover your pointer over a variable in the palette for a
more detailed description. To place a variable into the display template code,
position your cursor where you want it placed, and click the variable name in
the palette. Another useful tool in the display template editor is the
autocomplete feature. In a FreeMarker template, it can be invoked by typing *${*
which opens a drop-down menu of common variables. Upon selecting one of the
variables, the editor inserts the variable into your display template code.

We want to write a FreeMarker template to give us a summary of who is helping on
the tasks in our move. To do that, we need to access the records for the list
and pull out the name and task for each volunteer. Within the template, we have
access to a number of helper variables to find out what records we have access
to:

    reserved_ddm_structure_id
    
    reserved_record_set_description
    
    reserved_record_set_id
    
    reserved_record_set_name
    
Inside a template, these variables give us the ID for the record set (that
contains all of the volunteers in our list), as well as the name, description
and data definition. We can easily retrieve all the records through a service
call to `DDLRecordLocalService`. To gain access to this service, we need to use
a helper utility called `serviceLocator` that retrieves an instance of the
service for us. Once we have the service, we can retrieve the list of records
(our list of volunteers). Accessing the service with the `serviceLocator` can be
done with the following line of code:

    <#assign DDLRecordLocalService =
        serviceLocator.findService(
        "com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalService")>

We store a handle to our service in `DDLRecordLocalService` so we can use the
service to retrieve our list of volunteers:

    <#assign records = DDLRecordLocalService.getRecords(reserved_record_set_id)>
    
Now that we have our records, we can iterate through the list and display the
data from each record that we want to show. To access a field from a record
entry (such as the volunteer's name), we call the `getFieldValue` method and
pass in the field's name. Each dynamic data list record has a number of other
similar methods (see the `com.liferay.portlet.dynamicdatalists.model.DDLRecord`
interface at
[http://docs.liferay.com/portal/6.2/javadocs](http://docs.liferay.com/portal/6.2/javadocs)
but you'll probably use `getFieldValue` most often. This method returns the
content of the field.

    ${cur_record.getFieldValue("name")}
       
Now all we have to do is set the results in some appealing way. In this example,
we've made it very simple by using an unordered list for the results (`<ul>`).
Here is the complete source for the template:

    <h1>Task Summary</h1>

    Here are the tasks that people have signed up for on "${reserved_record_set_name}".

    <#assign DDLRecordLocalService = serviceLocator.findService("com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalService")>

    <#assign records = DDLRecordLocalService.getRecords(reserved_record_set_id)>

    <ul>
    <#if records?has_content>
            <#list records as cur_record>
                    <li><em>${cur_record.getFieldValue("name")}</em> will help with ${cur_record.getFieldValue("task")}</li>
            </#list>
    </#if>
    </ul>

Once you've typed the template's source into the editor window, click *Save* to
save the display template. With the display template selected, your list display
can now be a summary of tasks as shown below. 

![Figure 10.7: This display template provides a list of users who've volunteered along with the tasks for which they volunteered.](../../images/05-ddl-list-template.png)

All the knowledge you have accrued through building out your award-winning
content can be brought to bear in display templates. With the full power of
FreeMarker or Velocity templates at your fingertips, you have easy access to all
the data in the list, as well as the full complement of helper methods and the
Alloy UI JavaScript library to make easy work of dynamic displays. 

If you're not a Listie and you happen to be deploying custom lists in an
environment that requires approval from other users, then it's not enough to
just create the list and display a form. What you need is a real integration
with workflow. Workflow integrates smoothly with Dynamic Data Lists.

### Using Workflow [](id=using-workflow-liferay-portal-6-2-user-guide-10-en)

Liferay integrates the powerful features of workflow and the data capabilities
of dynamic data lists in *Kaleo Forms*. Workflow is not enabled in the dynamic
data list portlets by default, so you can focus on the core task of building
custom forms backed by a data list. After this is done, you can deploy custom
workflows to the form and its data. Though Kaleo Forms is only available in
Liferay EE, you can still apply a workflow to a list when creating it in Liferay
CE.

If you don't have a workflow engine installed, you need install the Kaleo Web
plugin. This plugin is included in the *Kaleo Workflow CE* and *Kaleo Workflow
EE* apps which are available from Liferay Marketplace. To manually deploy the
Kaleo workflow plugin, just copy the plugin `.war` file to the deploy folder of
you application server. Once workflow is installed, you have a new option when
creating a list:

![Figure 10.8: Once the workflow plugin has been installed, you can choose a workflow when creating a new dynamic data list.](../../images/05-ddl-add-workflow.png)

Choose the workflow you'd like to use. This requires that every record must pass
through the workflow process. Now if you need to preview or edit entries as
they're coming in, it's easy to integrate it into your daily workflow.

#### Creating a Kaleo Form [](id=creating-a-kaleo-form-liferay-portal-6-2-user-guide-10-en)

![EE Only Feature](../../images/ee-feature-web.png)

Kaleo Forms EE is an app that provides you with greater control over the list
creation and entry process. The Kaleo Forms EE app is available from Liferay
Marketplace. It includes both the Kaleo Designer portlet and the Kaleo Forms
portlet. The Kaleo Designer portlet provides an easy-to-use UI that helps
streamline the creation of workflow definitions. The Kaleo Forms portlet lets
you create web forms and basic applications. The Kaleo Forms EE app bundles
these applications together, you can create workflows that govern the processing
of web forms and applications.

For lists to appeal to companies all over the world (and make your new site not
just a resounding success but attract profitable businesses), business users
must be able to control the workflow of list entry and review those entries when
made. There should also be a cool dashboard you make all of your changes. Using
Kaleo Forms, users can create lists that follow a workflow, called a *process*,
or create new *entries* in a process. Creating a new process is easy,
straightforward, and effective.

#### Starting a New Process [](id=starting-a-new-process-liferay-portal-6-2-user-guide-10-en)

Defining processes that must be followed in data collection and entry is a
fundamental part of business. Historically, this hasn't been fun or easy but
Kaleo forms makes it as easy as possible. A process is just another way to
describe a workflow that's imposed on a list. When you place a Kaleo Forms
portlet on a page, you are presented with a dashboard with two tabs: *Summary*
and *Processes*. The summary view shows an inbox view which shows tasks that are
assigned to you and tasks that are assigned to your roles. The summary view also
lets you view your pending requests and your completed requests. If any process
definitions have been defined, you can submit records to a list from the summary
view. The processes view allows you to add process definitions and manage ones
that have already been created.

To build a list in Kaleo Forms with a workflow:

1. Add the Kaleo Forms portlet to a page.

2. Within the Kaleo Forms portlet, click on the *Processes* tab.

3. Click on *Add Process* and a form appears.

4. Enter a name and, optionally, a description. Entering a description helps
   your users understand the purpose of this process.

5. Select the appropriate list, workflow and forms you want to use in this
   process.

6. Click *Save* to save your process.

![Figure 10.9: When using the Kaleo Forms portlet to create a new workflow process, you need to complete this form.](../../images/05-ddl-kaleo-forms-new-process.png)

While the form looks complicated, it's easy to complete. There are a few pieces
that make up a process and when you click on one, you can browse a list and
select the appropriate piece to insert.

##### Selecting an Entry Definition [](id=selecting-an-entry-definition-liferay-portal-6-2-user-guide-10-en)

The first part of a new Kaleo process is also the simplest one: the entry
definition. This is just another way to refer to a data definition. All of the
available data definitions can be chosen, including our awesome "Volunteer
Sign-Up List." Just as with normal data lists, you can always create a new entry
definition from the list view by clicking *Add*.

##### Selecting an Initial Form [](id=selecting-an-initial-form-liferay-portal-6-2-user-guide-10-en)

One of the advantages of using Kaleo forms to present your list as a process is
that it grants you complete control over the form template. You can always use a
default template, which displays all the fields from your entry definition.
You can also, however, create multiple form templates for use in different
stages of the process. When you create a form template, you can specify what
*mode* to put it in:

**Create:** *Create* mode gives a display for creating the initial entry. The
first stage of any workflow requires you to create a new entry, so the initial
form template must be a create mode form. All fields marked `required` must be
included on create mode forms.

**Edit:** *Edit* mode is used for any stage of the workflow process. For
instance, you might want to separate information that need not be saved from
information that must. Other stages in the workflow could be a great place to
store additional, non-required, information. Required fields can be absent from
an edit mode form.

Once you have chosen an initial display template (it must be a create mode
template, not an edit template), all that's left to do is configure the workflow
for your process.

##### Selecting a Workflow [](id=selecting-a-workflow-liferay-portal-6-2-user-guide-10-en)

You can now select a workflow to apply to your new list-defined process. Any of
the available workflows can be chosen. You can also create new ones from the
selection screen. Simply choose *Add Workflow* and a Workflow Designer screen
appears allowing you to define a new workflow by dragging elements in a flow
chart.

![Figure 10.10: You'll see this form when editing or creating a new workflow with Kaleo Designer](../../images/05-kaleo-forms-kaleo-designer.png)

We'll keep ours simple; just choose *Single Approver Definition*. This gives us
a starting point (entry creation) and a *review* task, which we can use to add
additional information in a secondary form.

##### Assigning Workflow Task Forms [](id=assigning-workflow-task-forms-liferay-portal-6-2-user-guide-10-en)

Many workflows offer the option of having multiple editorial and review stages.
During these stages, you might want to offer different forms that allow the user
to add more information to the entry. Kaleo forms offers you the opportunity to
fine-tune the stages of workflow to use different forms.

From the view to assign forms to tasks:

1. Choose the workflow task by clicking on it. This selects the task in the
   chart.

2. In the details pane on the left-hand side there is a property called `Forms`.
   Double click to edit the *value*.

3. Start typing the name of a form template and it appears.

4. Click *Save* to save the form assignment.

![Figure 10.11: When creating or editing a Kaleo process, click *Assign* under Workflow Task Forms. Then click on a task, such as review, from the graphical view. Look for the *Forms* property in the Settings tab; you can use this property to assign a form template to a task.](../../images/05-kaleo-forms-task-form.png)

You can assign forms to as many tasks as you need until you're satisfied with
the workflow. After this stage, save the process and it's ready to be used in
Kaleo Forms.

#### Using a Kaleo Form [](id=using-a-kaleo-form-liferay-portal-6-2-user-guide-10-en)

![EE Only Feature](../../images/ee-feature-web.png)

Once you have a new Kaleo Form process, you can add new entries through the
Summary tab in Kaleo Forms. Once the form is filled out and submitted, it enters
the workflow you selected for the process.

![Figure 10.12: When adding a new entry to a process, you're presented with the process's configured form template for the initial display.](../../images/05-kaleo-form-new-entry.png)

After you have created an entry, (depending on the configured workflow) the next
task in the workflow may have an additional form to complete. If so, there will
be an option to enter it:

1. Next to the entry in progress, click the *Actions* button.

2. Click *Complete form*.

After the new entry has worked its way through the entire workflow, it is added
to the data set collected. The owner of that data set (who created the Kaleo
process) can view and edit the entries collected.

![To view an entry submitted to a list via workflow, click on *My Completed Requests* from the Kaleo Forms portlet's Summary tab, then click on the name of the entry you'd like to view.](../../images/05-kaleo-forms-view.png)

If you are a Listie, or a `list.it` developer, you're now prepared to show your
lists to the world. That is, in fact, the reason you created `list.it` in the
first place, right? 

## Summary [](id=summary-liferay-portal-6-2-user-guide-10-en)

Our `list.it` experience is now much more enjoyable than when we first signed
up. As new Listies, we had no idea how to define our own data types for our
lists, let alone how to create a list. You can now be the envy of your
co-workers as you breeze through list and data definition setup. Once you have
your new lists set up, you can work through building new, custom input forms for
the data. Your friends on `list.it` will thank you and wonder how you were able
to accomplish it all. That's nothing next to the masterpiece of design that you
can show off in your custom displays through display templates. Once the lists
have the precise look and feel you envisioned, then living among the Listies
will not only be easy, but fun and exciting.

With the ability to create dynamic sets of data and customize both the data
display as well as the entry, the possible combinations are limitless. Dynamic
Data Lists can be viewed as a way to deliver small-scale applications that
display desired information quickly. Whether you're building a site like
`list.it` or a real estate listing service, you'll find the limitless power of
dynamic data lists enticing, easy to use, and above all, empowering.

