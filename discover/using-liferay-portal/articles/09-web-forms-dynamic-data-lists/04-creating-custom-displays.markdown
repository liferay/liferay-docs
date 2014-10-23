# Make it Pretty: Creating Custom Displays [](id=make-it-pretty-creating-custom-displays)

When creating custom lists and data definitions, you can control not only how
the input form appears to your users but also how the list itself displays.
Eventually you may realize you need to create another sign-up sheet but you
don't need the same level of detail provided by the Volunteer Sign-Up data
definition you created. Liferay empowers you to customize both the input and
output of your lists to unlimited levels. Dynamic data lists provide two areas
to customize: detail templates and list templates. This covers the forms of
lists (*detail templates*), as well as the display of the list contents (*list
templates*). 

## Detail Templates [](id=detail-templates)

The default data entry form is the entire data model you created in a data
definition, including required and optional fields. Listies who create new lists
using a data definition will see every item in that definition on the input
form. What if, however, you want a quick sign-up form to find out who's coming
to dinner tonight? Using a detail template you can customize the form's display
any way you want. You can limit the fields displayed for entry or change the
order of elements. To access and create new templates, go to *Control Panel
&rarr; Content &rarr; Dynamic Data Lists &rarr; Manage Data Definitions*, choose
the data model you want to modify, click the *Actions* button and choose *Manage
Templates*. When you click on *Add Detail Template*, you're presented with the
same kind of graphical, drag-and-drop interface used for creating the data
definition. Move items around, delete unwanted fields from view and save when
ready. 

Note that data definitions can have multiple templates. You can choose the
template you want to use for display in either a dynamic data list display or a
dynamic data list form portlet (see below). You should create as many templates
as you might need, and you can prototype them in the portlets to see how each
feels. 

Now your friends and enemies alike will be impressed with your `list.it` skills.
It may look to the untrained eye like you've single-handedly created three or
four different data types for your lists but you know better. You used the power
that detail templates provide, using one data model that encompasses the maximum
information you might need (like preferred activity, favorite color and ideal
schedule). Then you quickly churned out four different detail templates with a
few mouse clicks. Now that you have such a vast amount of data collection
options, how will you display them? However you want, as you're about to find
out.

## List Templates [](id=list-templates)

For every data definition, you have an unlimited number of displays you can
create. If you created a special "Thanksgiving Dinner Sign-Up" list using your
"Volunteer Sign-Up" definition, you wouldn't want to confuse fellow Listies by
displaying data fields you never asked for. "Preferred task?" a friend might
say, "I don't remember seeing *that* on the sign-up form!" To avoid such
embarrassing situations, you should create a custom display to match that list.
Taking it even further, you could provide a fancy, JavaScript-driven image
carousel preview of all the attendees of the party. This would complement your
other displays and be another bragging right on `list.it`. List templates give
you the power to do all this and more.

Just like detail templates, list templates are found in the Manage Templates
section of a data definition. With list templates you can customize the display
of a list in precisely the same way as you can customize web content. List
templates can be written in FreeMarker or Velocity, pulling data from the data
definition in the same way that web content templates pull data from their
structures. We'll look at a simple example, but for more information on using
template scripts to pull data from a backing structure, see web content
templates in chapter 3.

The first thing we need to do is create a new list template for our "Volunteer
Sign-Up" data definition. Like other features in Liferay, there are several ways
to do this, depending on your context.

From the Dynamic Data List Display portlet:

1. Navigate to where your DDL Display portlet is and make sure your list is
   selected.

2. Find the *Create List Template* icon on the bottom-left of the portlet window
   and click it to create a new template. If you don't see the icon, sign in as
   a user with rights to create templates.

From the Dockbar:

1. Go to *Manage &rarr; Site Content*.

2. When loaded, navigate to *Dynamic Data Lists &rarr; Manage Data Definitions*.

3. Find your data definition in the list, then click *Actions &rarr; Manage
   Templates*

4. Now you can click on *Add List Template* to create a new template.

From the control panel:

1. Navigate to *Dynamic Data Lists &rarr; Manage Data Definitions*.

2. Find your data definition in the list, then click *Actions &rarr; Manage
   Templates*

3. Now you can click on *Add List Template* to create a new template.

Fill out the form with a title and a description. Next, choose a templating
language. Just like web content templates, you can choose between FreeMarker or
Velocity. There is no functional difference between the two. Once you choose the
script language, you can upload a template file or choose *Launch Editor* to
type in a script manually. Inside the editor you can also choose to use plain
text editing or a rich editor that features line numbers and syntax
highlighting.

We want to use our template to give us a summary of who is helping on the tasks
in our move. To do that, we need to access the records for the list and pull out
the name and task for each volunteer. Within the template, we have access to a
few helper variables to find out what records we have access to:

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
(our list of volunteers). Accessing the service with the `serviceLocator` is a
single line of code:

    #set ($ddlRecordsUtil = 
    		$serviceLocator.findService(
    		"com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalService"))

We store a handle to our service in `ddlRecordsUtil` so we can then use the
service to retrieve our list of volunteers:

    #set ($records = ${ddlRecordsUtil.getRecords($recordSetId)})
    
Now that we have our records, we can iterate through the list and display the
data from each record that we want to show. To access a field from a record
entry (such as the volunteer's name), we call the `getField` method and pass in
the field's name. Each field has a number of methods on it as well, but the one
you will use most often is `getValue`, which returns the content of the field.
Each field has the set of properties discussed above and can be accessed in the
same way (`get + FieldName`):

       #set ($name = $record.getField("name").getValue())
       
Now all we have to do is set the results in some appealing way. In this example,
we've made it very simple by using an unordered list for the results (`<ul>`).
Here is the complete source for the template:

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

Once you've typed your source into the editor window, click *Update* and then
save the list template. With the list template selected, your list display can
now be a summary of tasks as shown below. 

![Figure 9.7: A list template in action](../../images/05-ddl-list-template.png)

All the knowledge you have accrued through building out your award-winning
content can be brought to bear in list templates. With the full power of
Velocity templates at your fingertips, you have easy access to all the data in
the list, as well as the full complement of helper methods and the Alloy UI
JavaScript library to make easy work of dynamic displays. 

If you're not a Listie, and you happen to be deploying custom lists in an
environment that requires approval from other users, then it's not enough to
just create the list and display a form. What you need is a real integration
with Workflow. Workflow integrates smoothly with Dynamic Data Lists.

## Using Workflow [](id=using-workflow)

Liferay integrates the powerful features of workflow and the data capabilities
of dynamic data lists in *Kaleo Forms*. Workflow is not enabled in the dynamic
data list portlets by default, so you can focus on the core task of building
custom forms backed by a data list. After this is done, you can deploy custom
workflows to the form and its data. Though Kaleo Forms is only available in
Enterprise Edition, you can still apply a workflow to a list when creating it in
the Community Edition.

If you don't have a workflow engine installed, install the Kaleo Web plugin by
going to *Control Panel &rarr; Server &rarr; Plugins Installation &rarr; Install
More Portlets &rarr; Web Plugins* and finding Kaleo Web in the list. You can
also copy the Kaleo `.war` file to the deploy folder of you application server.
Once workflow is installed, you have a new option when creating a list:

![Figure 9.8: Enabling workflow on a list](../../images/05-ddl-add-workflow.png)

Choose the workflow you would like to use, then every record has to go through
the workflow process. Now if you need to preview or edit entries as they're
coming in, it's easy to work in to your daily workflow.

### Creating a Kaleo Form [](id=creating-a-kaleo-form)

![EE Only Feature](../../images/ee-feature-web.png)

Kaleo Forms is a plugin that enables you to have greater control over the list
creation and entry process. For lists to appeal to companies all over the world
(and make your new site not just a resounding success but attract profitable
businesses), business users must be able to control the workflow of list entry
and review those entries when made. There should also be a cool dashboard you
can use to make all of your changes.

Inside Kaleo Forms, users can create lists that follow a workflow, called a
*process*, or create new *entries* in a process. Creating a new process is easy,
straightforward, and effective.

### Starting a New Process [](id=starting-a-new-process)

Defining processes that must be followed in data collection and entry is a
fundamental part of business. Historically, this hasn't been fun or easy but
Kaleo forms makes it as easy as possible. A process is just another way to
describe a workflow you want on a list. When you place a Kaleo Forms portlet on
a page, you are presented with a dashboard with two tabs: *Summary* and
*Processes*. The summary view shows entries you have added to established
processes, while also allowing you to add new entries. The processes view allows
you to manage process definitions you have created.

To build a list in Kaleo Forms with a workflow:

1. Within the Kaleo Forms portlet click on the *Processes* tab.

2. Click on *Add Process* and a form appears.

3. Enter a name and description, helping your users understand the purpose of
   this process.

4. Select the appropriate list, workflow and forms you want to use in this
   process.

5. Click *Save* to save your process.

![Figure 9.9: New Kaleo Forms
process](../../images/05-ddl-kaleo-forms-new-process.png)

While the form looks complicated, it can be straightforward. There are a few
pieces that make up a process and clicking on each one takes you to the relevant
list of options to insert.

#### Selecting an Entry Definition [](id=selecting-an-entry-definition)

The first part of a new Kaleo process is also the simplest: the entry
definition. This is just another way to refer to a data definition. All of the
available data definitions can be chosen, including our awesome "Volunteer
Sign-Up List." Just like with normal data lists, you can always create a new
entry definition from the list view by clicking on *Add new definition*.

#### Selecting an Initial Form [](id=selecting-an-initial-form)

One of the great advantages to using Kaleo forms to present your list as a
process is having total control over the detail template. You can always use a
default template, which displays all the fields from your entry definition.
Greater flexibility comes, however, from creating multiple detail templates for
use in different stages of the process. When you create a detail template you
have the option of what *mode* to put it in:

**Create:** *Create* mode gives a display for creating the initial entry. The
first stage of any workflow requires you to create a new entry, so this should
be the mode chosen for the initial form. All fields marked `required` must be
included on create mode forms.

**Edit:** *Edit* mode is used for any stage of the workflow process. For
instance, you may want to separate information that shouldn't be saved from
information that should. Other stages in the workflow may be a great place to
store that additional information. No required fields have to be present on an
edit mode form.

![Figure 9.10: Selecting a detail template as the initial
form](../../images/05-kaleo-forms-detail-templates.png)

Once you have chosen the initial display you want, all that's left to do is
configure the workflow for your process.

#### Selecting a Workflow [](id=selecting-a-workflow)

You can now select a workflow to apply to your new list-defined process. All the
available workflows can be chosen and you can create new ones from the selection
screen. Simply choose *Add Workflow* and a Workflow Designer screen appears
allowing you to define a new workflow by dragging elements in a flow chart.

![Figure 9.11: Creating a new workflow with Kaleo
Designer](../../images/05-kaleo-forms-kaleo-designer.png)

We'll keep ours simple and just choose "Single Approver Definition." This gives
us a starting point (entry creation) and a 'review' task, which we can use to
add additional information in a secondary form.

#### Assigning Workflow Task Forms [](id=assigning-workflow-task-forms)

Many workflows offer you the option of having multiple editorial and review
stages. During these stages, you might want to offer different forms that allow
the user to add more information to the entry. Kaleo forms offers you the
opportunity to fine-tune the stages of workflow to use different forms.

When inside the view to assign forms to tasks:

1. Choose the workflow task by clicking on it.  This selects the task in the
   chart.

2. In the details pane on the left-hand side there is a property called `Forms`.
   Double click to edit the *value*.

3. Start typing the name of a detail template and it appears.

4. Click *Save* to save the form assignment.

![Figure 9.12: Assigning forms to workflow
tasks](../../images/05-kaleo-forms-task-form.png)

You can assign forms to as many tasks as you need until you're satisfied with
the workflow. After this stage, save the process and it's ready to be used in
Kaleo Forms.

### Using a Kaleo Form [](id=using-a-kaleo-form)

![EE Only Feature](../../images/ee-feature-web.png)

Once you have a new Kaleo Form process, you can add new entries through the
Summary tab in Kaleo Forms. Once the form is filled out and submitted, it enters
the workflow you selected for the process.

![Figure 9.13: Adding a new entry to a
process](../../images/05-kaleo-form-new-entry.png)

After you have created an entry, the next task in the workflow may have an
additional form to complete. If so, there is also an option to enter it:

1. Next to the entry in progress click the *Actions* button.

2. Click *Complete form*.

![Figure 9.14: Completing a form in the next workflow
task](../../images/05-kaleo-forms-complete-form.png)

After the new entry has worked its way through the entire workflow, it is added
to the data set collected. The owner of that data set (who created the Kaleo
process) can view and edit the entries collected.

![Viewing a completed list](../../images/05-kaleo-forms-view.png)

If you are a Listie, or a `list.it` developer, you're now prepared to show your
lists to the world. That is, in fact, the reason you created `list.it` in the
first place, right? 
