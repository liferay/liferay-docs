# Using Web Forms and Dynamic Data Lists [](id=dynamic-data-lists-in-liferay)

As needs change in business and organizations, the technology used to fulfill those needs must adapt as well. People use electronic means to do things that years ago were done using manual processes. For example, you may want your team to sign up on your web site for a holiday party. Or maybe every fall, you need to put up a job posting board, only allowing administrators to create new job posts. Maybe you want to allow users to manage a notebook or To-Do list on their private pages. In all of these cases, you want to enter in custom sets of data, allow your users to add their information and be able to access the set of data. 

In the past, you'd need to be a developer to accomplish any of this. Today, you can do it without writing a single line of code. Enter Liferay's *Dynamic Data Lists*. This is an easy way to create, aggregate and display new data types. Data Lists are flexible enough to handle all types of data, and you don't have to write any code. Simply put, Liferay gives you the power to:

- Define your own data definitions
- Create new lists from those definitions
- Customize the input forms for ease of use
- Customize the output format
- Integrate lists into Workflow 

All of this capability is easily distilled into two concepts: data defining and data displaying. These data lists are dynamic for a reason: they are flexible and powerful. Whether you want to collect simple input from the user or develop an entire data entry system for real estate listings, Dynamic Data Lists have your use case covered. Combined with the flexibility provided through templates and the power of languages like Velocity, entire applications can be built in a  short time.

## Building a list platform in Liferay [](id=building-a-list-platform-in-liferay)

To expand and extend the social capabilities of our site, we want to build a new, radical platform on Liferay: custom-built lists that users can share and collaborate on with their friends (or enemies, depending on their Social Relation type). Marketing has come up with a great name for our new service: `list.it`. Our beautiful `list.it` dashboard will give users the power to generate their own lists, see the lists of their friends and tally the results of certain types of lists (surveys, anyone?). Liferay makes this as simple as throwing some Dynamic Data List Display and Form portlets on a user's private and public user pages. 

## Defining data types [](id=defining-data-types)

When new users log in to `list.it`, they are going to want to build a few lists for themselves. Chances are, many of the lists they would want to create--to do lists, shopping lists and memos come to mind--are already defined in the portal. All the user has to do is create a new list, choose that pre-defined data type, and have at it! A number of data definitions ship with the portal to help you get started: *To Do*, *Issues*, *Meeting Minutes* and *Contacts*. Use these on their own to generate new data lists or tweak them to fit your use case. 

If none of the built-in data definitions suits your needs, you can define your own. Perhaps we want to allow our `list.it` users (who would probably call themselves "list-ers" or "list-ies") to create their own data types for lists they create. In this case, they would need to have unfettered access to the content of their private user site where they can create a new data type.

Using data lists to outline a new data model is as simple as point and click. You now have a `list.it` account and have been dying to bug your friends and family to sign up for "volunteer" work: helping you move into a new apartment. Using an intuitive visual editor, you can quickly draw up the skeleton for that volunteer list in minutes. Since data lists exemplify a unique type of content for your site, you can find them in the content section of the control panel, selecting *Go To &rarr; Control Panel &rarr; Content &rarr; Dynamic Data Lists* or *Manage &rarr; Site Content &rarr; Dynamic Data Lists*. Within the dynamic data lists section, you can either create a new data type (*Manage Data Definitions*) or a new list from an existing data type.

![Figure 9.1: Data Lists in the control panel.](../../images/05-ddl-control-panel.png)

If you have a new data type, you need to create a definition for it first. Click *Manage Data Definitions* and click the *Add* button. The first thing you should enter is a name for the definition and a description. Call it *Volunteer Sign-Up*. When creating a new data definition, you have a palette of fields to lay out, as well as a blank canvas to construct the definition. The interface looks similar to creating and editing web content structures covered previously. Let's explore the different data types at our disposal:

**Boolean:** presents a checkbox to the user and stores either a `true` (checked) or `false` (unchecked) based on state.

**Date:** a preformatted text field that displays a convenient date picker to assist in selecting the desired date. The format for the date is governed by the current locale.

**Number:** a text box that only accepts numbers as inputs, but puts no constraints on the kind of number entered.

**Integer:** similar to `Number`, except that it constrains user input to non-fractional numbers.

**Decimal:** similar to `Number`, except that it requires a decimal point (`.`) be present.

**Documents and Media:** select an existing uploaded document to attach to the data record.

**File Upload:** upload a document to attach to the data record. Uploads are stored in Documents and Media, in an existing folder or in the user's default upload location.

**Radio:** presents the user with a list of options to choose from using radio button inputs. Values are stored as `String`s. Similar to `Select`.

**Select:** a selection of options for the user to choose from using a combo box. Can be configured to allow multiple selections, unlike `Radio`.

**Text:** a simple text field for any `String` input.

**Text Box:** a large text box for long text input.

![Figure 9.2: Data definition fields.](../../images/05-data-definition-screen.png)

Using that reference as a nice cheat-sheet, you can now create the data type you need for "Volunteer Work Sign-Up." Use a `Text` type for the name. For all the tasks your friends and family can volunteer to do for you, use `Select` (obviously set to allow multiple options). Finally, you don't want to forget a `File Upload` so they can upload images of themselves. After all, how much more official-feeling and fun is it if you can print out some nifty badges? To add these fields, drag them from the palette on the left to the work area on the right. 

When creating data definitions, you can also customize the appearance of the input fields and provide helpful tips and hints for those entering data. Some data types have specific configuration options but all have some in common. The following properties can be edited in three ways: 1) by double-clicking on any field, 2) by clicking the gear icon in the upper-right corner of the field or 3) by clicking the *Settings* tab when the field is selected. Let's take a look at the properties you can edit for each of these field types: 


**Type:** Lists the type of field placed in the definition. This is not editable but is available to reference from a list template.

**Field Label:** Sets the text that can be displayed with the field. This is the human-readable text that the user sees.

**Show Label:** When set to `Yes`, the label is shown with the form field.

**Required:** When set to `Yes`, this field must have data in it for a new entry to be submitted.

**Name:** The name of the field internally, automatically generated. Since this is the variable name that you can read the data from in a list template, you should give a more memorable name here.

**Predefined value:** If you would like example data or a default value for the user to start with, enter it here. The field's value defaults to this when adding a new entry.

**Tip:** Each field can have a small help icon, with a tooltip attached that displays helpful information. If you would like to provide text for the tooltip you may enter it here.

**Multiple (Select):** When set to `Yes`, allows the user to select more than one option. This defaults to no.

**Allowed File Extensions (File Upload):** By default, form validation accepts any file type to be submitted. Set this value to a comma-delimited list of extensions, including the character `.`, and Liferay checks the extension before the file can be uploaded.

**Folder (File Upload):** Set the location the document is uploaded to in Documents and Media. You can choose from an existing folder, create one or default to Documents and Media's home location.

**Width (Text, Text Box, Decimal, Integer, Number):** Sets the visual width of the form on the page. It does not affect the values that are stored. Possible values are `Small`, `Medium` and `Large`.


![Figure 9.3: Data field properties.](../../images/05-data-definition-field-properties.png)

In addition to dragging the fields around to create your desired forms, you can stack inputs within inputs by dragging a field within another field. You can organize your data into unlimited levels of hierarchy, creating the clearest, most logical data model. There is also a duplicate button on each field (the middle button), allowing you to easily clone any field as many times as you need.

That really covers the basic tools that users of `list.it` need to get rolling with an unlimited array of custom types. Plus, you can always come back and change your form. If you find you needed to add some more information, simply come back to the data definition and fix it. All your data lists that use it are then instantly updated with the new or changed fields. 

All that's left to do is build a new data list and let your users play with it.

## Creating data lists [](id=creating-data-lists)

Building out new lists really isn't all that different from creating new pieces of web content. Just as you can create new Web Content Structures to control the input of a particular type of web content, you can use Data List Definitions to control the input of new list types. Similarly, just as you create a new piece of web content, selecting the Structure you would like to use with it, `list.it` users (we'll call them *Listies*) choose the Definition they want to use when creating a new list. Now that a data definition is in place, all that remains is to create a new data list to capture the information we're going after. This is the easiest step in creating the list, with only a few clicks between a data definition and robust data entry and delivery.

To create a new volunteer list with the "Volunteer Sign-Up" definition:

1. From the *Content* section of the Control Panel, select Dynamic Data Lists.
2. Click on *Add* to create a new list based on a data definition, which in our case is the volunteer sign-up.
3. Give the data list a name, like *Spring Move-In* and a description to assist administrative users in the future.
4. Last and most importantly, click *Select* under the *Data Defition* section--this is where you set the data model that drives this list.
5. Choose the *Volunteer Sign-Up* data definition you created, then click *Save*.

Now that you've created your brand new volunteer list, you can pester everyone you know to sign up. But what would it look like for them to add an entry to this list? The data definition you've previously created (or selected) defines the layout as well, which means the form looks just the way you laid it out. 

![Figure 9.4: Entering a new data record.](../../images/05-ddl-add-record.png)

But how will this data appear? How will my awesome, new Volunteer Sign-Up sheet or that boring Jobs Listing look? The answers to these pressing, burning questions bring us to the mecca that is the display side of this equation.

### Using data list forms [](id=lp-6-1-ugen05-using-data-list-forms-0)

A nice way to enable people to use your forms is the Dynamic Data List Forms portlet. This portlet is tailored to entering new records. When you deploy that data list for your users to sign up for a retreat, or your family members to volunteer to help you move, using the data list form allows you to simplify the sign-up process and hide the contents of the list.

Using the Dynamic Data List Form is exactly the same as using the Web Content Display portlet: just set it up, point it to a list (either existing or new) and let it go. This is very easy to do. 

To display a list inside the portlet, add the Dynamic Data List Form portlet to a page from the Dockbar: *Add &rarr; More &rarr; Dynamic Data List Form*. With the portlet on the page, click on the small gear icon in the lower left corner. This takes you to the configuration page, where you can select a list to use for the form entries. Each time a user visits your page with the volunteer sign-up, they are presented with a form to fill out. If they have already filled out an entry, a message is displayed instead.

Unlike the Web Content Display portlet, however, the Dynamic Data List Forms portlet may not be installed already in your portal. If not, just head over to Liferay Marketplace, grab it and install it. 

You can publish your lists anywhere in your portal too. Read on to find out more about that. 

### Using default displays [](id=lp-6-1-ugen05-using-default-displays-0)

Lists are published in the portal through the Dynamic Data List Display portlet. If Listies don't customize the display, their lists look something like this:

![Figure 9.5: The default data list display.](../../images/05-ddl-list-display.png)

This isn't all that exciting, but it allows users to see the list's contents, and if they have permission, to add and/or edit list items. Within a site like `list.it`, this type of interaction is used for display-only lists that the user chooses to expose to others, or for the user's own private lists. But you can improve the display. You can show the data in a spreadsheet, so you can view the responses to your Volunteer Sign-Up in a comfortable, easy-to-read format. The Dynamic Data List Display portlet provides an easy way for a user (such as a member of a site) to interact with whatever list is active and available.

While it's possible to ask everyone to contribute to the data list within the control panel, it's much better to give them a simple way to access the list. Liferay provides the Dynamic Data List Display portlet to ease the integration of your new list onto your site. With your list in hand, head over to the page you want and add the portlet. It works much like the Web Content Display portlet: use the gear icon to select a list for display or use the pen/paper icon to add a new list. The default display spills out the contents of the list, but can be configured to use a different display template, which is explored later in this chapter. The two important configuration options to consider are:

**Editable:** allows users that have permission to add new entries to the list. By default, this is disabled and when enabled, administrators are the only ones with add permission. To easily grant access to other users, edit the permissions on the list you'd like to grant access to, and grant the `Add Record` permission.

**Spreadsheet View:** displays the list in a dynamic spreadsheet view. This allows users with permission to interact with the list in the same way as in a standard spreadsheet program.

![Figure 9.6: The spreadsheet view](../../images/05-ddl-spreadsheet-view.png)

Now, as useful as this default display is, and it's certainly useful for my to do list and my memo notes, it can be an awkward way to ask my volunteers to sign up. In fact, any time I want other Listies to interact with my lists and contribute responses, I really just want a simple form to show them. They don't need to see the full range of responses. And in some cases, it can be hazardous to your health for everyone to see the responses. Then you don't have to explain why your sister-in-law won't work with your brother on the same task because of his B.O. problem. For reasons like that, you'll need to customize the data entry form or the display of the list. Liferay lets you do exactly that using a custom *detail template* or *list template*. 

## Make it pretty: creating custom displays [](id=make-it-pretty-creating-custom-displays)

When creating custom lists and data definitions, you can control not only how the input form appears to your users but also how the list itself displays. Eventually you may realize you need to create another sign-up sheet but you don't need the asme level of detail provided by the Volunteer Sign-Up data definition you created. Liferay empowers you to customize both the input and output of your lists to unlimited levels. Dynamic data lists provide two areas to customize: detail templates and list templates. This covers the forms of lists (*detail templates*), as well as the display of the list contents (*list templates*). 

### Detail templates [](id=lp-6-1-ugen05-detail-templates-0)

The default data entry form is the entire data model you created in a data definition, including required and optional fields. Listies who create new lists using a data definition will see every item in that definition on the input form. What if, however, you want a quick sign-up form to find out who's coming to dinner tonight? Using a detail template you can customize the form's display any way you want. You can limit the fields displayed for entry or change the order of elements. To access and create new templates, go to *Control Panel &rarr; Content &rarr; Dynamic Data Lists &rarr; Manage Data Definitions*, choose the data model you want to modify, click the *Actions* button and choose *Manage Templates*. When you click on *Add Detail Template*, you're presented with the same kind of graphical, drag-and-drop interface used for creating the data definition. Move items around, delete unwanted fields from view and save when ready. 

Note that data definitions can have multiple templates. You can choose the template you want to use for display in either a dynamic data list display or a dynamic data list form portlet (see below). You should create as many templates as you might need, and you can prototype them in the portlets to see how each feels. 

Now your friends and enemies alike will be impressed with your `list.it` skills. It may look to the untrained eye like you've singlehandedly created three or four different data types for your lists but you know better. You used the power that detail templates provide, using one data model that encompasses the maximum information you might need (like preferred activity, favorite color and ideal schedule). Then you quickly churned out four different detail templates with a few mouse clicks. Now that you have such a vast amount of data collection options, how will you display them? However you want, as you're about to find out.

### List templates [](id=lp-6-1-ugen05-list-templates-0)

For every data definition, you have an unlimited number of displays you can create. If you created a special "Thanksgiving Dinner Sign-Up" list using your "Volunteer Sign-Up" definition, you wouldn't want to confuse fellow Listies by displaying data fields you never asked for. "Preferred task?" a friend might say, "I don't remember seeing *that* on the sign-up form!" To avoid such embarassing situations, you should create a custom display to match that list. Taking it even further, you could provide a fancy, JavaScript-driven image carousel preview of all the attendees of the party. This would complement your other displays and be another bragging right on `list.it`. List templates give you the power to do all this and more.

Just like detail templates, list templates are found in the Manage Templates section of a data definition. With list templates you can customize the display of a list in precisely the same way as you can customize web content. List templates can be written in Freemarker or Velocity, pulling data from the data definition in the same way that web content templates pull data from their structures. We'll look at a simple example, but for more information on using template scripts to pull data from a backing structure, see web content templates in chapter 3.

The first thing we need to do is create a new list template for our "Volunteer Sign-Up" data definition. Like other features in Liferay, there are several ways to do this, depending on your context.

From the Dynamic Data List Display portlet:

1. Navigate to where your DDL Display portlet is and make sure your list is selected.
2. Find the *Create List Template* icon on the bottom-left of the portlet window and click it to create a new template. If you don't see the icon, sign in as a user with rights to create templates.

From the Dockbar:

1. Go to *Manage &rarr; Site Content*.
2. When loaded, navigate to *Dynamic Data Lists &rarr; Manage Data Definitions*.
3. Find your data definition in the list, then click *Actions &rarr; Manage Templates*
4. Now you can click on *Add List Template* to create a new template.

From the control panel:

1. Navigate to *Dynamic Data Lists &rarr; Manage Data Definitions*.
2. Find your data definition in the list, then click *Actions &rarr; Manage Templates*
3. Now you can click on *Add List Template* to create a new template.

Fill out the form with a title and a description. Next, choose a templating language. Just like web content templates, you can choose between Freemarker or Velocity. There is no functional difference between the two. Once you choose the script language, you can upload a template file or choose *Launch Editor* to type in a script manually. Inside the editor you can also choose to use plain text editing or a rich editor that features line numbers and syntax highlighting.

We want to use our template to give us a summary of who is helping on the tasks in our move. To do that, we need to access the records for the list and pull out the name and task for each volunteer. Within the template, we have access to a few helper variables to find out what records we have access to:

    reserved_ddm_structure_id
    
    reserved_record_set_description
    
    reserved_record_set_id
    
    reserved_record_set_name
    
Inside a template, these variables give us the ID for the record set (that contains all of the volunteers in our list), as well as the name, description and data definition. We can easily retrieve all the records through a service call to `DDLRecordLocalService`. To gain access to this service, we need to use a helper utility called `serviceLocator` that retrieves an instance of the service for us. Once we have the service, we can retrieve the list of records (our list of volunteers). Accessing the service with the `serviceLocator` is a single line of code:

    #set ($ddlRecordsUtil = 
    		$serviceLocator.findService(
    		"com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalService"))

We store a handle to our service in `ddlRecordsUtil` so we can then use the service to retrieve our list of volunteers:

    #set ($records = ${ddlRecordsUtil.getRecords($recordSetId)})
    
Now that we have our records, we can iterate through the list and display the data from each record that we want to show. To access a field from a record entry (such as the volunteer's name), we call the `getField` method and pass in the field's name. Each field has a number of methods on it as well, but the one you will use most often is `getValue`, which returns the content of the field. Each field has the set of properties discussed above and can be accessed in the same way (`get + FieldName`):

       #set ($name = $record.getField("name").getValue())
       
Now all we have to do is set the results in some appealing way. In this example, we've made it very simple by using an unordered list for the results (`<ul>`). Here is the complete source for the template:

    <h1>Task Summary</h1>
    
    Here are the tasks that people have signed up for on "$reserved_record_set_name.data".
    #set ($ddlRecordsUtil = $serviceLocator.findService("com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalService"))
    
    #set ($recordSetId = $getterUtil.getLong($reserved_record_set_id.data))
    #set ($records = ${ddlRecordsUtil.getRecords($recordSetId)})
    
    <ul>
    
    #foreach ($record in $records)
    
       #set ($name = $record.getField("name").getValue())
       #set ($tasks = $record.getField("task").getValue())
       
        <li><em>${name}</em> will help with <strong>$tasks</strong> </li>
    #end
    </ul>

Once you've typed your source into the editor window, click *Update* and then save the list template. With the list template selected, your list display can now be a summary of tasks as shown below. 

![Figure 9.7: A list template in action](../../images/05-ddl-list-template.png)

All the knowledge you have accrued through building out your award-winning content can be brought to bear in list templates. With the full power of Velocity templates at your fingertips, you have easy access to all the data in the list, as well as the full complement of helper methods and the Alloy UI Javascript library to make easy work of dynamic displays. 

If you're not a Listie, and you happen to be deploying custom lists in an environment that requires approval from other users, then it's not enough to just create the list and display a form. What you need is a real integration with Workflow. Workflow integrates smoothly with Dynamic Data Lists.

### Using workflow [](id=lp-6-1-ugen05-using-workflow-0)

Liferay integrates the powerful features of workflow and the data capabilities of dynamic data lists in *Kaleo Forms*. Workflow is not enabled in the dynamic data list portlets by default, so you can focus on the core task of building custom forms backed by a data list. After this is done, you can deploy custom workflows to the form and its data. Though Kaleo Forms is only available in Enterprise Edition, you can still apply a workflow to a list when creating it in the Community Edition.

If you don't have a workflow engine installed, install the Kaleo Web plugin by going to *Control Panel &rarr; Server &rarr; Plugins Installation &rarr; Install More Portlets &rarr; Web Plugins* and finding Kaleo Web in the list. You can also copy the Kaleo `.war` file to the deploy folder of you application server. Once workflow is installed, you have a new option when creating a list:

![Figure 9.8: Enabling workflow on a list](../../images/05-ddl-add-workflow.png)

Choose the workflow you would like to use, then every record has to go through the workflow process. Now if you need to preview or edit entries as they're coming in, it's easy to work in to your daily workflow.

#### Creating a Kaleo Form [](id=lp-6-1-ugen05-creating-a-kaleo-form-0)

![EE Only Feature](../../images/ee-only-image/ee-feature-web.png)

Kaleo Forms is a plugin that enables you to have greater control over the list creation and entry process. For lists to appeal to companies all over the world (and make your new site not just a resounding success but attract profitable businesses), business users must be able to control the workflow of list entry and review those entries when made. There should also be a cool dashboard you can use to make all of your changes.

Inside Kaleo Forms, users can create lists that follow a workflow, called a *process*, or create new *entries* in a process. Creating a new process is easy, straightforward, and effective.

#### Starting a new Process [](id=lp-6-1-ugen05-starting-a-new-process-0)

Defining processes that must be followed in data collection and entry is a fundamental part of business. Historically, this hasn't been fun or easy but Kaleo forms makes it as easy as possible. A process is just another way to describe a workflow you want on a list. When you place a Kaleo Forms portlet on a page, you are presented with a dashboard with two tabs: *Summary* and *Processes*. The summary view shows entries you have added to established processes, while also allowing you to add new entries. The processes view allows you to manage process definitions you have created.

To build a list in Kaleo Forms with a workflow:

1. Within the Kaleo Forms portlet click on the *Processes* tab.
2. Click on *Add Process* and a form appears.
3. Enter a name and description, helping your users understand the purpose of this process.
4. Select the appropriate list, workflow and forms you want to use in this process.
5. Click *Save* to save your process.

![Figure 9.9: New Kaleo Forms process](../../images/05-ddl-kaleo-forms-new-process.png)

While the form looks complicated, it can be straightforward. There are a few pieces that make up a process and clicking on each one takes you to the relevant list of options to insert.

##### Selecting an Entry Definition [](id=lp-6-1-ugen05-selecting-an-entry-definition-0)

The first part of a new Kaleo process is also the simplest: the entry definition. This is just another way to refer to a data definition. All of the avaliable data definitions can be chosen, including our awesome "Volunteer Sign-Up List." Just like with normal data lists, you can always create a new entry definition from the list view by clicking on *Add new definition*.

##### Selecting an Initial Form [](id=lp-6-1-ugen05-selecting-an-initial-form-0)

One of the great advantages to using Kaleo forms to present your list as a process is having total control over the detail template. You can always use a default template, which displays all the fields from your entry definition. Greater flexibility comes, however, from creating multiple detail templates for use in different stages of the process. When you create a detail template you have the option of what *mode* to put it in:

**Create:** *Create* mode gives a display for creating the initial entry. The first stage of any workflow requires you to create a new entry, so this should be the mode chosen for the initial form. All fields marked `required` must be included on create mode forms.

**Edit:** *Edit* mode is used for any stage of the workflow process. For instance, you may want to separate information that shouldn't be saved from information that should. Other stages in the workflow may be a great place to store that additional information. No required fields have to be present on an edit mode form.

![Figure 9.10: Selecting a detail template as the initial form](../../images/05-kaleo-forms-detail-templates.png)

Once you have chosen the initial display you want, all that's left to do is configure the workflow for your process.

##### Selecting a Workflow [](id=lp-6-1-ugen05-selecting-a-workflow-0)

You can now select a workflow to apply to your new list-defined process. All the avaliable workflows can be chosen and you can create new ones from the selection screen. Simply choose *Add Workflow* and a Workflow Designer screen appears allowing you to define a new workflow by dragging elements in a flow chart.

![Figure 9.11: Creating a new workflow with Kaleo Designer](../../images/05-kaleo-forms-kaleo-designer.png)

We'll keep ours simple and just choose "Single Approver Definition." This gives us a starting point (entry creation) and a 'review' task, which we can use to add additional information in a secondary form.

##### Assigning Workflow Task Forms [](id=lp-6-1-ugen05-assigning-workflow-task-forms-0)

Many workflows offer you the option of having multiple editorial and review stages. During these stages, you might want to offer different forms that allow the user to add more information to the entry. Kaleo forms offers you the opportunity to fine-tune the stages of workflow to use different forms.

When inside the view to assign forms to tasks:

1. Choose the workflow task by clicking on it.  This selects the task in the chart.
2. In the details pane on the left-hand side there is a property called `Forms`. Double click to edit the *value*.
3. Start typing the name of a detail template and it appears.
4. Click *Save* to save the form assignment.

![Figure 9.12: Assigning forms to workflow tasks](../../images/05-kaleo-forms-task-form.png)

You can assign forms to as many tasks as you need until you're satisfied with the workflow. After this stage, save the process and it's ready to be used in Kaleo Forms.

#### Using a Kaleo Form [](id=lp-6-1-ugen05-using-a-kaleo-form-0)

![EE Only Feature](../../images/ee-only-image/ee-feature-web.png)

Once you have a new Kaleo Form process, you can add new entries through the Summary tab in Kaleo Forms. Once the form is filled out and submitted, it enters the workflow you selected for the process.

![Figure 9.13: Adding a new entry to a process](../../images/05-kaleo-form-new-entry.png)

After you have created an entry, the next task in the workflow may have an additional form to complete. If so, there is also an option to enter it:

1. Next to the entry in progress click the *Actions* button.
2. Click *Complete form*.

![Figure 9.14: Completing a form in the next workflow task](../../images/05-kaleo-forms-complete-form.png)

After the new entry has worked its way through the entire workflow, it is added to the data set collected. The owner of that data set (who created the Kaleo process) can view and edit the entries collected.

![Viewing a completed list](../../images/05-kaleo-forms-view.png)

If you are a Listie, or a `list.it` developer, you're now prepared to show your lists to the world. That is, in fact, the reason you created `list.it` in the first place, right? 

## Summary [](id=summ-27)

Our `list.it` experience is now much more enjoyable than when we first signed up. As new Listies, we had no idea how to define our own data types for our lists, let alone how to create a list. You can now be the envy of your co-workers as you breeze through list setup and data definitions. Once you have your new lists set up, you can work through building new, custom input forms for the data. Your friends on `list.it` will thank you and wonder how you were able to accomplish it all. That's nothing next to the masterpiece of design that you can show off in your custom displays through list templates. Once the lists have the precise look and feel you envisioned, then living among the Listies will not only be easy, but fun and exciting.

With the ability to create dynamic sets of data and customize both the data display as well as the entry, the possible combinations are limitless. Dynamic Data Lists can be viewed as a way to deliver small-scale applications that display desired information quickly. Whether you're building a site like `list.it` or a real estate listing service, you'll find the limitless power of dynamic data lists enticing, easy to use, and above all, empowering.

