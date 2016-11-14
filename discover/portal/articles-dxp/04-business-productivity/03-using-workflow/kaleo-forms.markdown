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
3. Creating the process's workflow definition (or selecting one that's already
   created)
4. Creating and assigning forms for each workflow task

+$$$

**Example Use Case:** 




I guess we should explore Marcellus idea on the "SPA registration app" and combine with your idea. You highlighted an excellent pain point: manual control for room management. We should consider this as a pain point for the "SPA registration".

As an operational manager of a hotel SPA you would like to know the number of customers that would like to attend to your SPA and their needs/interests, so that you could organize: material, facilities, schedule professionals (massagists, cleaning services) and so on. Imagine performing all those activities using spreadsheets, printed lists and physical notes? Add to this, the need of sharing these information with your assistants and operational people to get things done. A mess, right?

How could you do it better? Integrating from the point of view of your customer, since the booking of a SPA session to the execution of the high value service?

How could Kaleo Forms help us accomplish that?

I can see the following journey (just examples, you can simplify): booking a SPA session, revising booking session, email confirmation to the customer with the booking details. Internally, for the operation: check list of material to operational team, book massagist, book the room in the SPA calendar. After the session: survey sent to customer email.

While space is unlimited at The Lunar Resort (because it's in space--get the
joke?), physical meeting spaces for the different departments are very limited.
In fact, there are only two rooms with the size and seating appropriate for
employee meetings. There used to be a paper reservation list hanging on the door
of each room, but it kept getting lost, destroyed, and defaced. The resort is
moving room reservation requests to an online sign-up form. The form needs
fields like Requested Room Number, Event Name, Event Contact, Event Importance,
a boolean Approved field, and others. Then the form go through a workflow, where
the request form is approved as is, updated with a new date, or rejected (for
example, if the Mechanical department wants to use the meeting room for a Rover
Oil Change event). Any form-based process that must move through multiple people
or roles benefits from the Kaleo Forms Admin application.

$$$

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
*New Process Wizard*. In this first step, name the process *Spa rder Process*, add a
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
        - A Text field called *Customer Name* 
        - A Text Box called *Requested Spa Technician*
        - A Select field called *Services Requested* with these options: 
            - Massage
            - Fish Pedicure
            - Sauna
            - Acupuncture
            - Moon Mud Facial Mask
        - A Boolean field called *Returning Customer?*
        - A Date field called *Preferred Date*
        - A Text field called *Preferred Time*
        - A Date field called *Available Date*
        - A Text field called *Available Time*
        - A Boolean field called *Approved*
        - A Text Box field called *Managerial Comments*

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

- For the Spa Order Process, make a new workflow definition called *Spa Order
    Workflow*:
     ![Figure x: The Room Request process has two review processes that happen
     in parallel.](../../../images/kaleo-forms-room-request-definition.png)

The definition has a StartNode and three task nodes. Develop it in the graphical
designer built into Kaleo Forms Admin. There's a more detailed write-up of the
Kaleo Designer [here](/discover/portal/-/knowledge_base/7-0/kaleo-designer) if
you're not already familiar with it. 

![Figure 3: You can create new workflow definitions directly in the Kaleo Forms user interface.](../../../images-dxp/kaleo-forms-designer.png)

This workflow exhibits simple linear processing, so that the initial form is
filled out (in the StartNode) and then the workflow moves to the first task
(Technician Claim), then to the second task (Managerial Approval), and finally,
to the final order task.

The task assignments of this workflow are as follows:

Technician Claim: Assigned to the Spa Technician Role.
Managerial Approval: Assigned to the Spa Manager Role.
Final Order: Assigned to the Asset Creator.

+$$$

**Note:** Create the Spa Technician Role and the Spa Manager Role as [Site
Roles](/discover/portal/-/knowledge_base/7-0/roles-and-permissions), and give
them
[permission](/discover/portal/-/knowledge_base/7-0/roles-and-permissions#defining-role-permissions)
to access the Kaleo Forms Admin application. In the role's page for defining
permissions, they're found under Site Administration &rarr; Content &rarr; Kaleo
Forms Admin. Both roles need all of the permissions available for Kaleo Forms
Admin.
<!-- Is that true? -->

$$$

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

For review, the Spa Order Process has these fields:

- Customer Name
- Requested Spa Technician
- Services Requested 
- Returning Customer?
- Preferred Date
- Preferred Time
- Available Date
- Available Time
- Approved
- Managerial Comments

Not all of the available fields should appear on each form in this example. Some
of the fields, like Approved, should only appear on the reviewer's form.

Each applicable workflow node (the initial state and each task) appears in a row
with its associated form and a button that lets you assign a form. In the Spa
Order Workflow definition there are four nodes that need forms: StartNode,
Technician Claim, Managerial Approval, and Final Order.

![Figure 5: You can assign a form to each task in the workflow, and for the initial state (*created* in this case.)](../../../images-dxp/kaleo-forms-task-forms.png)

Click on the kebab menu (![Action](../../../images-dxp/icon-actions.png)) next
to the Request Entered task and then click *Assign Form*. The screen that
appears lets you create a new form if you click the *Add* button
(![Add](../../../images-dxp/icon-add.png)). 

-  First, name the initial form (call it *Spa Order Process--Order Form* if you like). 
-  Next look at the available fields. In this stage of the workflow, you don't 
    need all the approval fields. Delete these fields from the form (mouse over
    the field and click the delete
    icon--![Delete](../../../images/icon-trash.png):
    - Available Date
    - Available Time
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

Next create a form named *Spa Order Process--Technician Claim Form* for the
Technician Claim task. Follow the same procedure used above, this time deleting
these fields from the full field set:

- Approved
- Managerial Comments

+$$$

**Example Use Case Note:** In the Spa Order Process, the Managerial Approval
workflow task and it's associated form are filled out by the spa manager. The
manager can oversee the order, making sure that customers requests are being met
and that all technicians are fulfilling their share of the spa's duties.

$$$

Create a form to be used in the Managerial Approval task, called *Spa Order
Process--Managerial Approval Form*. The manager can change anything they want
about the order, so include the entire field set in the form.

<!-- Available Fields: Should we use the full set of fields?
- Customer Name
- Requested Spa Technician
- Services Requested 
- Returning Customer?
- Preferred Date
- Preferred Time
- Available Date
- Available Time
- Approved
- Managerial Comments
-->

+$$$

**Example Use Case note:** The customer service representative finalizes the
order and gets back to the customer on the final details (available time, name
of technician, and confirms the services they want).

$$$

There's one more form to create: the *Spa Order Process--Final Order Form*. Like
the last form, it should have all the fields of the form.


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
workflow engine's management. Submitting a process backed by the Spa Order
Workflow definition, the workflow moves first to the Technician Claim task, then
to the Managerial Approval task, and last, to the Final Order task. Whatever
users or roles you assigned to the tasks receive a notification and the task
appears in the Assigned to My Roles section of the My Workflow Tasks portlet. A
notification is also triggered in the Notifications application. Once in the
task, the user views and approve the form, or clicks the edit
(![Edit](../../../images-dxp/icon-edit.png)) button. At this point the workflow
task forms you created come into play. Each assigned user fills out the form,
saves it, and sends it along in the workflow.

The Kaleo Forms Admin application unites two powerful features of Liferay by
integrating a workflow and a form into a new entity, the Kaleo Process.
