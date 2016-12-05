# Workflow Forms [](id=workflow-forms)

Business processes are often form-based and workflow-driven. They start with
some data that needs to be entered and progress by sending the data to other
people or groups. The initial data is processed in some way (for example,
further data is entered or approval is granted), and the process moves on until
completion, when each interested party has seen and manipulated the data. To
write an app for each of these fluid processes is laborious and not cost
effective. Instead, a tool for quickly defining a process to suit each use case
is needed. The process architect must define the data that gets collected and
define the process the data must move through to reach its final state. To
accomplish this, @product@ already includes the 
[Dynamic Data Lists application](/discover/portal/-/knowledge_base/7-0/creating-data-definitions)
for defining forms, and the [Kaleo Designer
application](/discover/portal/-/knowledge_base/7-0/kaleo-designer) for designing
workflows. The Kaleo Forms solution combines the features of these applications,
letting you design an integrated process for sending forms through a workflow in
one intuitive UI.

The entire process includes:

1. Creating a Kaleo Process

2. Creating the process's field set (or selecting an existing 
   [Data Definition](/discover/portal/-/knowledge_base/7-0/creating-data-definitions))

3. Creating the process's workflow definition (or selecting one that's already
   created)

4. Creating and assigning forms for each workflow task

+$$$

**Example Use Case:** 

The Lunar Resort spa is a popular place. With all the adventure-driven
activities (lunar hiking, rover races, etc.) available to guests, it's important
to offer some relaxing and rejuvenating experiences. As the spa expands and new
Spa Technicians are hired, the Spa Manager decides to implement a new process to
handle orders. It involves several steps and several people:

- The customer service representative for the spa interacts with a customer and
  fills out an order form.

- The customer service representative gives the order details to the spa
  technicians so they can divide up the requests, with a single technician
  assigned to each order. 

- The spa technician sends the order to the spa manager for final approval and
  comment. 

- The spa manager sends the final order back to the customer service
  representative, who contacts the customer with the final details of his spa
  service order.

A process like this is a nightmare to manage manually, but perfectly suited for
Kaleo Forms.

$$$

Since Kaleo Forms is accessed using a site's administration menu, make sure
the intended users of your Kaleo Processes have access to the application. See the
article on [roles and permissions](/discover/portal/-/knowledge_base/7-0/roles-and-permissions) 
if you're unsure how that works.

## Using Kaleo Forms [](id=using-kaleo-forms)

Access Kaleo Forms from the *Content* section of your site's
administration menu (click *[Site Name]* &rarr; *Content* &rarr; *Kaleo Forms
Admin*). Kaleo Forms appears with a listing of the defined processes. If you're
coming here for the first time, there won't be any, so create one. 

### Creating a Kaleo Process [](id=creating-a-kaleo-process)

Click the *Add* (![Add](../../../images-dxp/icon-add.png)) button to open the
New Process Wizard and begin the first step in creating a Kaleo Process. Name
the process *Spa Order Process*, add a description, and then click *Next*.

![Figure 1: Add a Kaleo Forms Process to link a form with a workflow definition.](../../../images-dxp/kaleo-forms-add.png)

### Creating the Form [](id=creating-the-form)

In the second step of the New Process Wizard, define the fields that can appear
in your process's forms. There are two methods:

1.  Choose a predefined field set. To use a field set that already exists, pick
    one from the list of field sets by clicking the Kebab menu
    (![Actions](../../../images-dxp/icon-actions.png)) next to its entry and
    selecting *Choose*. 
2.  Create a new field set. Use the *Add Field Set* button to add a new field
    set. If you're not sure about creating a field set, see the documentation on
    [creating data definitions](/discover/portal/-/knowledge_base/7-0/creating-data-definitions)
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

Click *Next* to move to the next step in the wizard: adding a workflow for
the process.

![Figure 2: In the second step of the New Process Wizard, define and choose the fields for your form.](../../../images-dxp/kaleo-forms-fields.png)

### Choosing a Workflow [](id=choosing-a-workflow)

In the third step of the New Process Wizard you select a workflow to use for
your forms. The Single Approver workflow is included by default. See how it's
defined by opening it in the Kaleo Designer. Click the kebab menu
(![Actions](../../../images-dxp/icon-actions.png)) and then *Edit*. 

The graphical interface for editing or defining a workflow appears below the
Single Approver edit screen's Details section. 

If you don't want to base your workflow on the Single Approver definition (or
any definition already available in your system), add a new workflow from
scratch using the Kaleo Designer. Click the *Add Workflow* button to get
started.

- For the Spa Order Process, make a new workflow definition called *Spa Order
    Workflow*:
     ![Figure 3: The Spa Order Process has three tasks that happen sequentially.](../../../images-dxp/kaleo-forms-spa-order-definition.png)

The definition has a StartNode and three task nodes. Develop it in the graphical
designer built into Kaleo Forms. There's a more detailed write-up of the
Kaleo Designer [here](/discover/portal/-/knowledge_base/7-0/kaleo-designer) if
you're not already familiar with it. 

This workflow exhibits simple linear processing, so that the initial form is
filled out (in the StartNode) and then the workflow moves to the first task
(Technician Claim), then to the second task (Managerial Approval), and finally,
to the final order task.

The task assignments of this workflow are as follows:

- Technician Claim: Assigned to the Spa Technician Role.
- Managerial Approval: Assigned to the Spa Manager Role.
- Final Order: Assigned to the Asset Creator.

+$$$

**Note:** Create the Spa Technician Role and the Spa Manager Role as 
[Site Roles](/discover/portal/-/knowledge_base/7-0/roles-and-permissions), 
and give them
[permission](/discover/portal/-/knowledge_base/7-0/roles-and-permissions#defining-role-permissions)
to access the Kaleo Forms application. In the role's page for defining
permissions, they're under Site Administration &rarr; Content &rarr; Kaleo Forms
Admin. Give both roles all of the permissions available for Kaleo Forms Admin.

$$$

Once the workflow is done, select it: back on the third step of the New Process
wizard, click *Actions* &rarr; *Choose* on the workflow you just created.  Then
click *Next*.

### Adding Task Forms [](id=adding-task-forms)

In the fourth (and final) step of the New Process wizard, assign a form to each
task in the workflow definition (and to the StartNode to get the process kicked
off). All forms you work with here will be either the entire field set you
defined in step two, or a subset of it. 

+$$$

**Note:** In this step, you can't add any proper form fields (fields that accept
user input). If you forgot to add a Select field to the field set in step two,
for example, you cannot add one here. Instead go back and add it to the field
set.

While you can't add form fields, you can configure the existing fields
differently for each form, and you can add these elements to the form that
enhance its usability:

1. Add a *Fieldset* to provide headings in a form. For example, name it
   after the workflow task to provide clarity to the task assignee.
2. Use a *Paragraph* field to add a block of text to the form. For example,
   provide instructions to assignees about what's expected of them to complete
   the task.
3. Use the *Separator* to separate sections of the form visually. For example,
   combine a Paragraph and a Separator to instruct the assignee to focus on a
   certain section of the form.

![Figure 4: Use Fieldsets, Paragraphs, and Separators to enhance the appearance of your workflow task forms.](../../../images-dxp/kaleo-forms-field-types.png)

$$$

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

Not all the available fields should appear on each form. For example, the
Approved field should only appear on the form for the managerial review task.

Each applicable workflow node (the initial state and each task) appears in a row
with its associated form and a button that lets you assign a form. In the Spa
Order Workflow definition, there are four nodes that need forms: StartNode,
Technician Claim, Managerial Approval, and Final Order.

![Figure 5: You can assign a form to each task in the workflow, and for the initial state (*created* in this case.)](../../../images-dxp/kaleo-forms-task-forms.png)

Click on the kebab menu (![Action](../../../images-dxp/icon-actions.png)) next
to the Request Entered task and then click *Assign Form*. The screen that
appears lets you create a new form if you click the *Add* button
(![Add](../../../images-dxp/icon-add.png)). 

-  First, name the initial form (call it *Spa Order Process--Order Form* if you like). 
-  Next look at the available fields. In this stage of the workflow, you don't 
   need all the fields. Delete these fields from the form (mouse over
   the field and click the delete
   icon--![Delete](../../../images/icon-trash.png)):
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

If you don't need to change any field settings in the form, click *Save*. On the
next screen choose your new form by clicking on it. Now the workflow
definition's StartNode has a form associated with it.

Next create a form named *Spa Order Process--Technician Claim Form* for the
Technician Claim workflow task. Follow the same procedure used above, this time
deleting these fields from the full field set:

- Approved
- Managerial Comments

+$$$

**Example Use Case Note:** In the Spa Order Process, the Managerial Approval
workflow task and its associated form are filled out by the spa manager. The
manager can oversee the order, making sure that customers requests are being met
and that all technicians are fulfilling their share of the spa's duties.

$$$

Create a form to be used in the Managerial Approval task, called *Spa Order
Process--Managerial Approval Form*. The manager can change anything she wants
about the order, so include the entire field set in the form.

+$$$

**Example Use Case note:** The customer service representative finalizes the
order and gets back to the customer on the final details (available time, name
of technician, and confirms the services they want).

$$$

There's one more form to create: the *Spa Order Process--Final Order Form*. Like
the last form, it should have all the form's fields so that the customer service
representative can review the order with the customer.

![Figure 7: Once created, your process is listed in Kaleo Forms.](../../../images-dxp/kaleo-forms-processes.png)

Click *Save* when finished. The process is ready to test.

### Test the Process [](id=test-the-process)

Once your new process is developed, add records to it by clicking on the process
name and then the Plus button (![Add](../../../images-dxp/icon-add.png)).

The application displays the form you assigned to the workflow's initial state.
Fill out the form and click Save.

![Figure 8: Fill out the form and send it through the process.](../../../images-dxp/kaleo-forms-new-process-form.png)

Once the initial form is submitted in the Kaleo Forms application, the process
is managed by the workflow engine. In a process backed by the Spa Order
Workflow definition, the workflow moves first to the Technician Claim task, then
to the Managerial Approval task, and last, to the Final Order task. Whatever
users or roles you assigned to the tasks receive notifications, and the task
appears in the Assigned to My Roles section of the My Workflow Tasks portlet. A
notification is also triggered in the Notifications application. Once in the
task, the user views and approve the form or clicks the edit
(![Edit](../../../images-dxp/icon-edit.png)) button. At this point the workflow
task forms you created come into play. Each assigned user fills out the form,
saves it, and sends it along in the workflow.

The Kaleo Forms application unites two powerful features of Liferay by
integrating a workflow and a form into a new entity, the Kaleo Process.

## Related Topics [](id=related-topics)

[Kaleo Designer](/discover/portal/-/knowledge_base/7-0/kaleo-designer)

[Using Workflow](/discover/portal/-/knowledge_base/7-0/enabling-workflow)

[Liferay's Workflow Framework](/develop/tutorials/-/knowledge_base/7-0/liferays-workflow-framework)

[Creating Simple Applications](/discover/portal/-/knowledge_base/7-0/creating-simple-applications)
