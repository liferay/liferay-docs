# Workflow Forms [](id=workflow-forms)

Using the handy Kaleo Designer application, you can [create workflow definitions
for any asset in
@product@](/discover/portal/-/knowledge_base/7-0/kaleo-designer). Meanwhile, the
Dynamic Data Lists application lets you [create field
sets](/discover/portal/-/knowledge_base/7-0/creating-data-definitions) to back
your forms. The Kaleo Forms Admin application combines the features of these
applications, letting you design an integrated process for sending forms through
a workflow. The entire process includes:

1. Creating a Kaleo Process
2. Creating the process's field set (or selecting an existing [Data
   Definition](/discover/portal/-/knowledge_base/7-0/creating-data-definitions))
3. Creating the process's workflow definition (or selecting one that's already created)
4. Creating and assigning forms for each workflow task

+$$$

**Example Use Case:** While space is unlimited at The Lunar resort (because it's
in space--get the joke?), physical meeting spaces for the different departments
are very limited. In fact, there are only two rooms with the size and seating
appropriate for employee meetings. There used to be a paper reservation list
hanging on the door of each room, but it kept getting lost, destroyed, and
defaced. The resort is moving room reservation requests to an online sign-up
form. The form needs fields like Requested Room Number, Event Name, Event
Contact, Event Importance, a boolean Approved field, and others. Then the form
needs to go through a workflow, where the request form is approved as is,
updated with a new date, or rejected (for example, if the Mechanical department
wants to use the room for a Rover Oil Change event). Any internal site process
that is form-based and needs to move through multiple people or roles benefits
from the Kaleo Forms Admin application.

$$$

The Kaleo Forms Admin application tool accessible in the Site Administration
section of each site. Make sure intended users of your Kaleo Processes have
access to the application. See the article on [roles and
permissions](/discover/portal/-/knowledge_base/7-0/roles-and-permissions) if
you're unsure how that works.

The simple example shown below demonstrates using a field set and workflow
already installed in Liferay to create a new Kaleo Process.

## Using Kaleo Forms [](id=using-kaleo-forms)

Access Kaleo Forms Admin in the *Content* section of your site's administration
menu (click *[Site Name]* &rarr; *Content* &rarr; *Kaleo Forms Admin*). The
portlet appears with a listing of the defined processes. If you’re coming here
for the first time, there won’t be any, so create one. 

### Creating a Kaleo Process [](id=creating-a-kaleo-process)

Click the *Add* (![Add](../../../images-dxp/icon-add.png)) button to open the
*New Process Wizard*. In this first step, give your process the name *Lunar
Resort News Content*, add a description, and then click *Next*.

![Figure 1: Add a Kaleo Forms Process to link a form with a workflow definition.](../../../images-dxp/kaleo-forms-add.png)

### Creating the Form [](id=creating-the-form)

In the second step of the New Process Wizard, define the fields that can appear
in your forms. There are two methods:

1. Choose a predefined field set. To use a field set that already exists, pick
   one from the list of field sets.
2. Create a new field set. Use the *Add Field Set* button to add a new field
   set.  If you're not sure about creating a field set, see the documentation on
   [creating data
   definitions](/discover/portal/-/knowledge_base/7-0/creating-data-definitions)
   first.

For this example, choose the *To Do* field set by clicking the Kebab menu
(![Actions](../../../images-dxp/icon-actions.png)) next to its entry and
selecting *Choose*. Click *Next* to move on to the next step in the wizard:
adding a workflow for the process.

![Figure 2: In the second step of the New Process Wizard, you define and choose the fields for your form.](../../../images-dxp/kaleo-forms-fields.png)

### Choosing a Workflow [](id=choosing-a-workflow)

In the third step of the New Process Wizard you select a workflow to use for
your forms. The Single Approver workflow is included by default. See how it's
defined by opening it in the Kaleo Designer. Click the kebab menu
(![Actions](../../../images-dxp/icon-actions.png)) and then *Edit*. 

The graphical interface for editing or defining a workflow appears below the
Details section of the Single Approver edit screen. 

If you don't want to base your workflow on the Single Approver definition (or
any definition already available in your system), add a new workflow from
scratch using the Kaleo Designer. Click the *Add Workflow* button to get
started.

In the graphical designer you can build a workflow without having to write any
XML. There's a more detailed write-up of the Kaleo designer
[here](/discover/portal/-/knowledge_base/7-0/kaleo-designer) if you're not
already familiar with it. 

![Figure 3: You can create new workflow definitions directly in the Kaleo Forms user interface.](../../../images-dxp/kaleo-forms-designer.png)

The flexibility and power of Kaleo Forms is compounded at this step. The ability
to create a field set from scratch, then design a workflow process from scratch,
all in one process, is powerful.

Once the workflow is done, select it: back on the third step of the New Process
wizard, click *Actions* &rarr; *Choose* on the workflow you just created.  Then
click *Next*.

### Adding Task Forms [](id=adding-task-forms)

In the fourth (and final) step of the New Process wizard, assign a form to each
task in the workflow definition. All forms you work with here will be either the
entire field set you defined in step two, or a subset of it. You can't add any
fields that accept user input in this step. However, there are three elements
you can still add to the existing form to make it form easier to understand:

1. A *Fieldset* can be added to the form. Use it to provide a heading for your
   form (for example, name it after the workflow task to provide clarity to the
   task assignee.
2. Add a *Paragraph* field to write text giving some explanation to the workflow
   task assignee directly in the form. For example, direct the assignee in
   what's expected of them to complete the task.
3. Use the *Separator* to visually separate sections of the form. For example,
   use a Paragraph and Separator to instruct the assignee to focus on a certain
   section of the form.

   ![Figure 4: Use Fieldsets, Paragraphs, and Separators to enhance the appearance of your workflow task forms.](../../../images-dxp/kaleo-forms-field-types.png)

Each workflow task appears in a row with its associated form and a button that
lets you assign a form. If you use the Single Approver workflow, there are
three tasks: create, update, and review.

![Figure 5: You can assign a form to each task in the workflow, and for the initial state (*created* in this case.)](../../../images-dxp/kaleo-forms-task-forms.png)

Click on the kebab menu (![Action](../../../images-dxp/icon-actions.png)) next
to the create task and then click *Assign Form*. The screen that appears lets
you create a new form if you click the *Add* button
(![Add](../../../images-dxp/icon-add.png)). 

-  First name the initial form (call it *Initial Form* if you like). 
-  Next look at the available fields. In our example, this is a simple review
task so you won’t need all the fields that are in the form by default. To delete
a field, mouse over it and click the *Trash* icon that appears. Delete all but
the *Assigned To*, *Severity*, *Start Date*, *Status*, and *Title* fields.

![Figure 6: You can assign a form to each task in the workflow, and for the initial state (*created* in this case.)](../../../images-dxp/kaleo-forms-form-builder.png)

+$$$

**Note:** If you delete a field by accident or want to add another field to your
form, drag and drop its icon from the *Fields* tab on the left to the form
canvas on the right. Fields already on the form that can appear on it only once
are grayed out. You can also edit the settings of a field in the form by mousing
over it and clicking the wrench icon.

$$$

![Figure 7: You can assign a form to each task in the workflow, and for the initial state (*created* in this case.)](../../../images-dxp/kaleo-forms-field-settings.png)

If you don’t need to change any field settings in the form, click *Save*. On the
next screen choose your new form from the *Actions* button next to it. Now that
you’re familiar with the basics of form creation and assignment, create a form
(if you're not sure what to call it, be super clever and use *Second Form*).
This form can be used for the update and review tasks. Give it the following
fields:

-  Assigned To
-  Attachment
-  Comments
-  Description
-  End Date
-  Percent Complete
-  Severity
-  Start Date
-  Status
-  Title

For the Lunar Resort News Content process, choose the new form for the update
and review tasks, then click *Save*. Your new process is complete, and is listed
in the Kaleo Forms Admin portlet. Celebrate!

![Figure 8: Once created, your process is listed in Kaleo Forms Admin.](../../../images-dxp/kaleo-forms-processes.png)

### Test the Process [](id=test-the-process)

Once your new process is developed, add records to it by clicking on the process
name, and then the Plus button (![Add](../../../images-dxp/icon-add.png)).

![Figure 9: Add a record to your new process.](../../../images-dxp/kaleo-forms-admin.png)

The application displays the form you assigned to the workflow's initial state.
Fill out the form and click Save.

![Figure 10: Add a record to your new process.](../../../images-dxp/kaleo-forms-new-process-form.png)

Once submitted in the Kaleo Forms Admin application, the process moves under the
workflow engine's management. Submitting a process backed by the Single Approver
workflow, the workflow moves to the Review task, assigned automatically to the
Portal Content Reviewer role. Users with the Portal Content Reviewer role
receive a notification and the task appears in the Assigned to My Roles section
of the My Workflow Tasks portlet. A notification is also triggered in the
Notifications application. Once in the Review task, the user can view and
approve the form, or click the edit (![Edit](../../../images-dxp/icon-edit.png))
button. At this point the workflow task forms you created come into play. Each
assigned user fills out the form, saves it, and sends it along in the workflow.

The Kaleo Forms Admin application unites two powerful features of Liferray,
integrating a workflow and a form into a new entity, the Kaleo Process.
