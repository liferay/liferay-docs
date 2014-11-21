# Make it Pretty: Creating Custom Displays [](id=make-it-pretty-creating-custom-displays)

When creating custom lists and data definitions, you can control not only how
the input form appears to your users but also how the list itself displays.
Eventually you may realize you need to create another sign-up sheet but you
don't need the same level of detail provided by the Volunteer Sign-Up data
definition you created. Liferay empowers you to customize both the input and
output of your lists to unlimited levels. Dynamic data lists provide two areas
to customize: form templates and display templates. This covers the forms of
lists (*form templates*), as well as the display of the list contents (*display
templates*). 

## Form Templates [](id=form-templates)

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

+$$$

Note: Form templates were called *detail
 templates* prior to Liferay 6.2.

$$$

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

## Display Templates [](id=display-templates)

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

+$$$

Note: Display templates were called *list
 templates* prior to Liferay 6.2.

$$$

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

![Figure 11.7: This display template provides a list of users who've volunteered along with the tasks for which they volunteered.](../../images/05-ddl-list-template.png)

All the knowledge you have accrued through building out your award-winning
content can be brought to bear in display templates. With the full power of
FreeMarker or Velocity templates at your fingertips, you have easy access to all
the data in the list, as well as the full complement of helper methods and the
Alloy UI JavaScript library to make easy work of dynamic displays. 

If you're not a Listie and you happen to be deploying custom lists in an
environment that requires approval from other users, then it's not enough to
just create the list and display a form. What you need is a real integration
with workflow. Workflow integrates smoothly with Dynamic Data Lists.

## Using Workflow [](id=using-workflow)

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

![Figure 11.8: Once the workflow plugin has been installed, you can choose a workflow when creating a new dynamic data list.](../../images/05-ddl-add-workflow.png)

Choose the workflow you'd like to use. This requires that every record must pass
through the workflow process. Now if you need to preview or edit entries as
they're coming in, it's easy to integrate it into your daily workflow.

### Creating a Kaleo Form [](id=creating-a-kaleo-form)

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

### Starting a New Process [](id=starting-a-new-process)

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

![Figure 11.9: When using the Kaleo Forms portlet to create a new workflow process, you need to complete this form.](../../images/05-ddl-kaleo-forms-new-process.png)

While the form looks complicated, it's easy to complete. There are a few pieces
that make up a process and when you click on one, you can browse a list and
select the appropriate piece to insert.

#### Selecting an Entry Definition [](id=selecting-an-entry-definition)

The first part of a new Kaleo process is also the simplest one: the entry
definition. This is just another way to refer to a data definition. All of the
available data definitions can be chosen, including our awesome "Volunteer
Sign-Up List." Just as with normal data lists, you can always create a new entry
definition from the list view by clicking *Add*.

#### Selecting an Initial Form [](id=selecting-an-initial-form)

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

#### Selecting a Workflow [](id=selecting-a-workflow)

You can now select a workflow to apply to your new list-defined process. Any of
the available workflows can be chosen. You can also create new ones from the
selection screen. Simply choose *Add Workflow* and a Workflow Designer screen
appears allowing you to define a new workflow by dragging elements in a flow
chart.

![Figure 11.10: You'll see this form when editing or creating a new workflow with Kaleo Designer](../../images/05-kaleo-forms-kaleo-designer.png)

We'll keep ours simple; just choose *Single Approver Definition*. This gives us
a starting point (entry creation) and a *review* task, which we can use to add
additional information in a secondary form.

#### Assigning Workflow Task Forms [](id=assigning-workflow-task-forms)

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

![Figure 11.11: When creating or editing a Kaleo process, click *Assign* under Workflow Task Forms. Then click on a task, such as review, from the graphical view. Look for the *Forms* property in the Settings tab; you can use this property to assign a form template to a task.](../../images/05-kaleo-forms-task-form.png)

You can assign forms to as many tasks as you need until you're satisfied with
the workflow. After this stage, save the process and it's ready to be used in
Kaleo Forms.

### Using a Kaleo Form [](id=using-a-kaleo-form)

![EE Only Feature](../../images/ee-feature-web.png)

Once you have a new Kaleo Form process, you can add new entries through the
Summary tab in Kaleo Forms. Once the form is filled out and submitted, it enters
the workflow you selected for the process.

![Figure 11.12: When adding a new entry to a process, you're presented with the process's configured form template for the initial display.](../../images/05-kaleo-form-new-entry.png)

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
