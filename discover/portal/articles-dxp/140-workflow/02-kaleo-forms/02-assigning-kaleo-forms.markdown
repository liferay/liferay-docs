# Assigning Forms for Workflow Tasks [](id=adding-task-forms)

In the fourth (and final) step of the New Process wizard, assign a form to each
task in the workflow definition (and to the StartNode to get the process kicked
off). All forms you work with here will be either the entire field set you
defined in step two, or a subset of it. 

+$$$

**Note:** In this step, you can't add any proper form fields (fields that accept
user input). If you forgot to add a Select field to the field set in step two,
for example, you cannot add one here. Instead go back and add it to the field
set.

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
Technician Claim, Managerial Approval, and Final Order. Next you'll assign a 
form for each of the four nodes.


## Assigning Kaleo Forms

![Figure 5: You can assign a form to each task in the workflow, and for the initial state (*Created* in this case.)](../../../images-dxp/kaleo-forms-task-forms.png)

1.  Click on the actions menu (![Action](../../../images-dxp/icon-actions.png)) next to the Request Entered task

2.  Click *Assign Form*.

3.  Click the *Add* button (![Add](../../../images-dxp/icon-add.png)) on the 
    next screen.

4.  Name the initial form (call it *Spa Order Process--Order Form* if 
    you like). 

5.  Delete these fields from the form (mouse over
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


## Test the Process [](id=test-the-process)

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
task, the user views and approve the form or clicks the edit button. At this 
point the workflow task forms you created come into play. Each assigned user fills out the form, saves it, and sends it along in the workflow.

The Kaleo Forms application unites two powerful features of Liferay by
integrating a workflow and a form into a new entity, the Kaleo Process.

## Related Topics [](id=related-topics)

[Kaleo Designer](/discover/portal/-/knowledge_base/7-0/kaleo-designer)

[Using Workflow](/discover/portal/-/knowledge_base/7-0/enabling-workflow)

[Liferay's Workflow Framework](/develop/tutorials/-/knowledge_base/7-0/liferays-workflow-framework)

[Creating Simple Applications](/discover/portal/-/knowledge_base/7-0/creating-simple-applications)
