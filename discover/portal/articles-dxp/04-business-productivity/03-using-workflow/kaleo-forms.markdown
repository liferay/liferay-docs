# Workflow Forms [](id=workflow-forms)

Business processes are often form-based and workflow-driven. They start with
some data that needs to be entered and shared with another person or group, for
further data entry, approval, editing, legal review, or some other purpose.  To
write an app for each of these processes is laborious, and often not cost
effective. Businesses need a way to quickly define a process to suit their
needs. They need to define the data that gets collected, and define the process
the data will move through to reach its final state. @product@ already includes
the [Dynamic Data Lists
application](/discover/portal/-/knowledge_base/7-0/creating-data-definitions)
for defining forms, and the [Kaleo Designer
application](/discover/portal/-/knowledge_base/7-0/kaleo-designer) for designing
workflows. The Kaleo Forms solution combines the features of these applications,
letting you design an integrated process for sending forms through a workflow.
The entire process includes:

1. Creating a Kaleo Process
2. Creating the process's field set (or selecting an existing [Data
   Definition](/discover/portal/-/knowledge_base/7-0/creating-data-definitions))
3. Creating the process's workflow definition (or selecting one that's already created)
4. Creating and assigning forms for each workflow task

+$$$

**Example Use Case:** While space is unlimited at The Lunar Resort (because it's
in space--get the joke?), physical meeting spaces for the different departments
are very limited. In fact, there are only two rooms with the size and seating
appropriate for employee meetings. There used to be a paper reservation list
hanging on the door of each room, but it kept getting lost, destroyed, and
defaced. The resort is moving room reservation requests to an online sign-up
form. The form needs fields like Requested Room Number, Event Name, Event
Contact, Event Importance, a boolean Approved field, and others. Then the form
go through a workflow, where the request form is approved as is,
updated with a new date, or rejected (for example, if the Mechanical department
wants to use the meeting room for a Rover Oil Change event). Any form-based
process that must move through multiple people or roles benefits from the
Kaleo Forms Admin application.

$$$
<!-- This is not the example we show below, though it will probably be implemented in
the future because of some changes that will happen with the app (no more ability to use default field sets like the To Do list). If it's too confusing to explain this use case while we illustrate a different example in the article, we can remove it for now. -->

Access Kaleo Forms Admin in the Site Administration section of a site. Make
sure intended users of your Kaleo Processes have access to the application. See
the article on [roles and
permissions](/discover/portal/-/knowledge_base/7-0/roles-and-permissions) if
you're unsure how that works.

<!--The simple example shown below demonstrates using a field set and workflow
already installed in Liferay to create a new Kaleo Process.-->

## Using Kaleo Forms [](id=using-kaleo-forms)

Access Kaleo Forms Admin from the *Content* section of your site's administration
menu (click *[Site Name]* &rarr; *Content* &rarr; *Kaleo Forms Admin*). The
portlet appears with a listing of the defined processes. If you’re coming here
for the first time, there won’t be any, so create one. 

### Creating a Kaleo Process [](id=creating-a-kaleo-process)

Click the *Add* (![Add](../../../images-dxp/icon-add.png)) button to open the
*New Process Wizard*. In this first step, name the process *Room Request*, add a
description, and then click *Next*.

![Figure 1: Add a Kaleo Forms Process to link a form with a workflow definition.](../../../images-dxp/kaleo-forms-add.png)

### Creating the Form [](id=creating-the-form)

In the second step of the New Process Wizard, define the fields that can appear
in your forms. There are two methods:

1.  Choose a predefined field set. To use a field set that already exists, pick
    one from the list of field sets by clicking the Kebab menu
    (![Actions](../../../images-dxp/icon-actions.png)) next to its entry and
    selecting *Choose*. 
2.  Create a new field set. Use the *Add Field Set* button to add a new field
    set. If you're not sure about creating a field set, see the documentation on
    [creating data
    definitions](/discover/portal/-/knowledge_base/7-0/creating-data-definitions)
    first.
    - For this example, Add a new field set: 
        - A Text field called *Event Name* 
        - A Text Box called *Event Description*
        - A Date field called *Request Date*
        - A Date field called *Backup Date*
        - A Text field called *Requested Time*
        - A Text field called *Backup Time*
        - A Radio field called *Preferred Room* with two options: *1* and *2*
        - A Text field called *Contact Name*
        - A Boolean field called *Approved*
        - A Date field called *Approved Date*
        - A Text field called *Approved Time*
        - A Radio field called *Room Assignment* with two options: *1* and *2*
        - A Boolean field called *Availability Confirmed*
        - A Text Box called *Managerial Comments*

Click *Next* to move on to the next step in the wizard: adding a workflow for
the process.

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

- For the Room Request process, make a new workflow definition called *Room
    Request Definition*:
     ![Figure x: The Room Request process has two review processes that happen
     in parallel.](../../../images/kaleo-forms-room-request-definition.png)

The Room Request definition has a StartNode, a Fork node, two parallel review
Task nodes, a Join node, and an EndNode. Develop it in the graphical designer
built into Kaleo Forms Admin. There's a more detailed write-up of the Kaleo
Designer [here](/discover/portal/-/knowledge_base/7-0/kaleo-designer) if you're
not already familiar with it. 

![Figure 3: You can create new workflow definitions directly in the Kaleo Forms user interface.](../../../images-dxp/kaleo-forms-designer.png)

Once the workflow is done, select it: back on the third step of the New Process
wizard, click *Actions* &rarr; *Choose* on the workflow you just created.  Then
click *Next*.

### Adding Task Forms [](id=adding-task-forms)

In the fourth (and final) step of the New Process wizard, assign a form to each
task in the workflow definition (and to the StartNode to get the process kicked
off)). All forms you work with here will be either the entire field set you
defined in step two, or a subset of it. You can't add any fields that accept
user input in this step, but there are three elements you can still add to the
existing form to enhance it's usability:

1. Add a a *Fieldset* to provide headings in a form. For example, name it
   after the workflow task to provide clarity to the task assignee.
2. Add a *Paragraph* field to add a block of text to the form. For example,
   provide instructions to the assignee about what's expected of them to complete
   the task.
3. Use the *Separator* to visually separate sections of the form. For example,
   combine a Paragraph and a Separator to instruct the assignee to focus on a
   certain section of the form.

![Figure 4: Use Fieldsets, Paragraphs, and Separators to enhance the appearance of your workflow task forms.](../../../images-dxp/kaleo-forms-field-types.png)

For review, the Room Request process has these fields:

- Event Name
- Event Description
- Request Date
- Backup Date
- Requested Time
- Backup Time
- Preferred Room
- Contact Name
- Approved
- Approved Date
- Approved Time
- Room Assignment
- Availability Confirmed
- Managerial Comments

Not all of the available fields should appear on each form in this example. Some
of the fields, like Approved, should only appear on the reviewer's form.

Each applicable workflow node (the initial state and each task) appears in a row
with its associated form and a button that lets you assign a form. In the Room
Request Definition there are three nodes that need forms: Request Entered,
Managerial Review, and Availability Review.

![Figure 5: You can assign a form to each task in the workflow, and for the initial state (*created* in this case.)](../../../images-dxp/kaleo-forms-task-forms.png)

Click on the kebab menu (![Action](../../../images-dxp/icon-actions.png)) next
to the Request Entered task and then click *Assign Form*. The screen that appears lets
you create a new form if you click the *Add* button
(![Add](../../../images-dxp/icon-add.png)). 

-  First, name the initial form (call it *Room Request Form* if you like). 
-  Next look at the available fields. In this stage of the workflow, you don't 
    need all the approval fields. Delete these fields from the form ( mouse over
    the field and click the delete
    icon--![Delete](../../../images/icon-trash.png):
    - Availability Confirmed
    - Approved Date
    - Approved Time
    - Room Assignment
    - Approved
    - Managerial Comments

![Figure 6: Workflow task forms are a subset of the original form.](../../../images-dxp/kaleo-forms-form-builder.png)

+$$$

**Note:** If you delete a field by accident or want to add another field to your
form, drag and drop its icon from the *Fields* tab on the left to the form
canvas on the right. Fields already on the form that can appear on it only once
are grayed out. You can also edit the settings of a field in the form by mousing
over it and clicking the wrench icon.

$$$

![Figure 7: You can configure the form fields added to your task forms.](../../../images-dxp/kaleo-forms-field-settings.png)

If you don’t need to change any field settings in the form, click *Save*. On the
next screen choose your new form from the *Actions* button next to it. 

Next create a form named *Approval Form* for the Managerial Review task. Follow
the same procedure used above, this time deleting these fields form the full
field set:

- Availability Confirmed
- Approved Date
- Approved Time
- Room Assignment

+$$$

**Example Use Case Note:** In the Room request Process, the Managerial Review
workflow task and it's associated form are filled out by a manager who wishes to
review what kinds of meetings resort staff are engaging in. They can reject
inappropriate uses of the meeting room as they see fit and make comments in the
form. For example, if the Mechanical Department wants to use a meeting room for
their Annual Rover tune-up event, they'll be rejected.

$$$

Next create a form to be used in the Availability Review task. Delete these
fields:

- Approved
- Managerial Comments

+$$$

**Example Use Case note:** The employee tasked with managing the meeting rooms
keeps track of the meeting bookings at the resort, and makes the finald decision
on the date, time, and room number where a meeting will take place.


$$$

![Figure 8: Once created, your process is listed in Kaleo Forms Admin.](../../../images-dxp/kaleo-forms-processes.png)

Click Save when finished. The process is ready to test.

### Test the Process [](id=test-the-process)

Once your new process is developed, add records to it by clicking on the process
name, and then the Plus button (![Add](../../../images-dxp/icon-add.png)).

![Figure 9: Add a record to your new process.](../../../images-dxp/kaleo-forms-admin.png)

The application displays the form you assigned to the workflow's initial state.
Fill out the form and click Save.

![Figure 10: Fill out the form and send it through the process.](../../../images-dxp/kaleo-forms-new-process-form.png)

Once submitted in the Kaleo Forms Admin application, the process moves under the
workflow engine's management. Submitting a process backed by the Room Request
Definition, the workflow moves simultaneously to the Managerial Review and
Availability Review tasks. Whatever users or roles you assigned to the tasks
receive a notification and the task appears in the Assigned to My Roles section
of the My Workflow Tasks portlet. A notification is also triggered in the
Notifications application. Once in the task, the user views and approve the
form, or clicks the edit (![Edit](../../../images-dxp/icon-edit.png)) button. At
this point the workflow task forms you created come into play. Each assigned
user fills out the form, saves it, and sends it along in the workflow.

The Kaleo Forms Admin application unites two powerful features of Liferay by
integrating a workflow and a form into a new entity, the Kaleo Process.
