<!--copied from 03-creaing-custom-displays. That article was too long. -->

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
