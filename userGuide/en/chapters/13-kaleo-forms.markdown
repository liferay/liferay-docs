
# Kaleo Forms: Defining Business Processes [](id=kaleo-forms-defining-business-processes-liferay-portal-6-2-user-guide-12-en)

![EE Only Feature](../../images/ee-feature-web.png)

In the last chapter, you saw the elements that comprise a workflow
definition and learned how to create a workflow definition. In this chapter,
you'll learn about the Kaleo Forms Admin application for Liferay EE. This
application lets you create workflow definitions using an intuitive UI. Using
the workflow designer saves you the time and trouble of having to deal directly
with the XML.

Developers who are used to working with XML can create workflow definitions with
little struggle. Other users may not be so comfortable with it. In fact, even
skilled developers can make mistakes that break a definition and require time to
troubleshoot. To help streamline the creation of workflow definitions and
empower more users to create custom workflows, Liferay provides the Kaleo Forms
Admin application in Liferay 6.2 EE.

There are two pieces to the workflow designer: *Kaleo Forms Admin* and *Kaleo
Forms Display*. These two portlets are included in the Kaleo Forms app and can 
be downloaded from Liferay Marketplace. Kaleo Forms Admin contains a wizard that
guides you through each step in the creation of a workflow process. This
includes a drag and drop interface for creating new workflow definitions without
having to write XML. Kaleo Forms Display allows you to work with a process in
its associated workflow. In other words, Kaleo Forms Admin is for *creating*
workflow processes, while Kaleo Forms Display is for *using* them.

Let's look at Kaleo Forms Admin first. 

## Kaleo Forms Admin [](id=kaleo-forms-liferay-portal-6-2-user-guide-12-en)

You can access Kaleo Forms Admin from the Dockbar by clicking
*Admin* &rarr; *Site Administration* &rarr; *Content* and then clicking *Kaleo 
Forms Admin*. The portlet then appears with a listing of the processes that 
you've defined. If you're coming here for the first time, however, there won't 
be any, so create one. Click *Add*. You'll see the screen below. This is 
the first step in the *New Process Wizard*. For this example, give your process 
the name *Lunar Resort News Content*, add a description, and then click *Next*.

![Figure 12.1: The first step of the New Process Wizard.](../../images/kaleo-forms-admin-wizard-1.png)

The second step in the New Process Wizard lets you define the fields that 
can appear in your forms. To the right of the To Do field, click the *Actions* 
button and select *Choose*. Now that you've chosen a field definition for your 
forms, you can move on to the next step in the wizard to add or define a 
workflow for those forms. Click *Next*.

![Figure 12.2: The second step of the New Process Wizard. This is where you define and choose the fields for your forms.](../../images/kaleo-forms-admin-wizard-2.png)

The third step in the New Process Wizard is really where the meat and potatoes 
of Kaleo Forms Admin is. This is where you choose or define a workflow to use 
for your forms. The Single Approver workflow is included by default. To get a 
look at how it's defined, click *Actions* and then *Edit*. The graphical 
interface for editing or defining a workflow appears below the Details section 
of the Single Approver edit screen. 

![Figure 12.3: The graphical view for editing or defining a workflow.](../../images/kaleo-forms-graphical-editor.png)

This graphical view is where Kaleo Forms Admin can help you build a workflow 
without having to write any XML. There are eight types of nodes you can add to a 
definition. The node types are **Condition**, **End**, **Fork**, **Join**, 
**Join XOR**, **Start**, **State** and **Task**. Notice that Start and End 
aren't node types we've previously discussed; that's because they're actually 
just State nodes, with certain fields pre-filled to help streamline the creation 
process. Since every workflow has a start and end state, you'd have to do this 
anyway. 

Each node you add has a pop-up menu letting you edit or delete the node. As
you hover your mouse over the edges of a node, notice your mouse pointer changes
to a cross. The cross indicates you can connect the current node to another
node. Hold down your mouse button and drag the mouse to start drawing your
transition to another node. If you stop before reaching the edge of the next
node, a pop-up displays node types you can create and connect to on-the-fly. To
connect with an existing node, continue dragging the connector to that node. 

![Figure 12.4: Create transitions to existing or new nodes. The connector pop-up let's you create and connect to new nodes on-the-fly.](../../images/kaleo-connector.png)

To get a feel for how the designer works, go ahead and use the workflow designer 
to duplicate the default workflow definition. Go back to the previous step and 
click *Add Workflow*. When you choose this option, it creates a blank workflow 
definition with start and end nodes. To make this work, you'll add two tasks, 
fill in the relevant information, assign the tasks properly, and create the 
transitions. 

First add two tasks, then use the edit icon to name them *Review* and *Update*. 

![Figure 12.5: Edit a node by clicking on its node icon and modifying its settings.](../../images/kaleo-rename-node.png)

Next, connect the nodes so Review has four nodes, as follows: one receiving the
transition from **StartNode**, one sending a transition to **Update**, one
receiving a transition from **Update**, and one sending a transition to
**EndNode**. 

![Figure 12.6: Your workflow should look something like this.](../../images/kaleo-designer-basic-workflow.png)

Next, you want to add the correct assignments and notifications. Click on
*Review*. The box on the left shows all the properties of the Review node. In
the *Assignments* category, set the *Assignment Type* to *Role Type*, the 
*Role Type* to *Regular*, and the *Role Name* to *Portal Content Reviewer*

![Figure 12.7: Assignment settings for the Review node.](../../images/kaleo-forms-review-node-assignments.png)

Now set the notifications. Double-click on *Notifications* and create a 
notification with the Notification Type set to *User Notification* and the 
Execution Type set to *On Assignment*.

![Figure 12.8: Notification settings for the Review node.](../../images/kaleo-forms-review-node-notification.png)

Together, the assignment and notification settings specify that a user receives 
a notification in their Dockbar when assigned a form in this workflow. Now move 
to the Update node and assign it to the *Content Creator* role with its own user 
notification.

Next, go through all the transitions and make sure they're named correctly. 
What are the transitions? Workflow transitions connect one node to another. On 
exiting the first node, processing continues to the node pointed to by the 
transition. Every time you created an arrow from one node to another, Kaleo
Designer created a transition. By default, these transitions get system
generated names, so we'll rename them all to something more human-readable.
First, click on the arrow going from the Start node to the Review node and set
the name to *Submit* and set *Default* to true--you'll leave all the others as
false. Set the name of the Review to Update transition to *Reject* and the
Update to Review transition to *Resubmit*. Lastly, set the name of the Review to
Endnode transition to *Approve*.

![Figure 12.9: Your completed workflow should look like this.](../../images/kaleo-designer-basic-workflow-complete.png)

Now look at the generated XML. It should look a lot like the default workflow,
only a tiny bit messier, as the nodes are written in the order they were
created, not in the logical order that happens when a human writes the code.
Once you're finished, click *Publish*. Your workflow is now ready to use!

Back on the third step of the New Process wizard, click *Actions* &rarr; *Choose* 
next to the workflow you just created. Then click *Next*.

The fourth and final step of the New Process wizard is for creating or assigning 
a form to each task you've defined in the workflow. Each task appears in a row 
with its form (if it has been assigned one) and a button that lets you assign a 
form. Therefore, you have *create*, *update*, and *review* listed as your 
tasks.

![Figure 12.10: The fourth step in the New Process wizard lets you assign a form to each task.](../../images/kaleo-forms-admin-wizard-4.png)

Go ahead and click on the *Assign Form* button next to the created task. On the 
next page, click the *Add Form* button. The screen that appears lets you create 
a new form. First, give the form the name *Initial Form*, since it's the first 
form in the process. Next, take a look at the available fields. This is a simple 
review task so you won't need all the fields that are in the form by default. To 
delete a field, mouse over it and click the *Trash* icon that appears. Go ahead 
and delete all but the *Assigned To*, *Severity*, *Start Date*, *Status*, and 
*Title* fields. Your form should now look like the one in the screenshot here.

![Figure 12.11: Basic form creation.](../../images/kaleo-forms-admin-form-creation-1.png)

If you delete a field by accident or simply want to add another field to your 
form, you can do so by dragging and dropping its icon from the *Fields* tab on 
the left to the form itself on the right. Fields already on the form that can 
appear on it only once are grayed out. You can also edit the settings of a field 
in the form by mousing over it and clicking the wrench icon. For example, the 
settings for the Assigned To field are shown in this screenshot. 

![Figure 12.12: Settings available for a field in a form.](../../images/kaleo-forms-admin-form-creation-2.png)

Since you don't need to change any of the field settings in this form, go ahead 
and click *Save*. On the next screen, choose your new form from the *Actions* 
button next to it. Now that you're familiar with the basics of form creation and 
assignment, create a form titled *Second Form* to use for the update and review
tasks. Give it the following fields: 

- Assigned To
- Attachment
- Comments
- Description
- End Date
- % Complete
- Severity
- Start Date
- Status
- Title

Make sure you choose the new form for your update and review tasks. When you're 
done assigning forms to tasks, click *Save*. Your new process is complete, and 
is listed in the Kaleo Forms Admin portlet. Congratulations! 

![Figure 12.13: Once created, your process is listed in Kaleo Forms Admin.](../../images/kaleo-forms-admin-process-complete.png)

Now it's time to look at how to use workflow processes in the Kaleo Forms 
Display portlet. 

## Kaleo Forms Display [](id=kaleo-forms-display-liferay-portal-6-2-user-guide-12-en)

The Kaleo Forms Display portlet makes use of the workflow processes that you
create in the Kaleo Forms Admin portlet. Add the Kaleo Forms Display portlet to
a page if you haven't done so yet. Any forms available for processing through
the workflow can be initiated through the *Submit New* button, as the below
image indicates. 

![Figure 12.14: The Kaleo Forms Display portlet.](../../images/kaleo-forms-display.png)

To initiate a process in the workflow, click *Submit New* &rarr; *Lunar Resort 
News Content*. The portlet now displays the form you assigned to the first task 
in your workflow. Fill out the form and click *Save*. 

<!-- Above, you told the user to give the process a name, but you didn't specify
the name. Here, you refer to the name as *New Process*. This should have a
better, more imaginative name. Consider integrating this with the Lunar Resort
site mentioned in other chapters. In fact, this might be the way you can expand
coverage of the forms application. -Rich -->

![Figure 12.15: Submitting a new process.](../../images/kaleo-forms-display-submit-new.png)

The workflow now automatically guides the form on to the next step. For example, 
if you submit a new process using the Single Approver process created in the 
section above, it is assigned automatically to the Portal Content Reviewer role. 
If you are a Portal Content Reviewer, the task then appears in the 
*Assigned to My Roles* section of the Kaleo Forms Display portlet, and you 
receive a notification in your Dockbar. You can then assign it to yourself using 
the portlet and complete the task.

![Figure 12.16: The workflow automatically assigns the task to a specific role.](../../images/kaleo-forms-display-assigned-role.png)

## Summary [](id=summary-liferay-portal-6-2-user-guide-12-en)

As you can see, Liferay Portal and the Kaleo Workflow engine combine to create a
robust environment for web content management. The Kaleo Forms portlets
allow you to manage all available workflows in the portal. You can create your
portal's workflows by using Kaleo Forms Admin's drag and drop interface. Simple
workflows can be managed using the default configuration and GUI tools, while
more complex workflows can be created to meet the workflow management needs of
almost any portal. Through this chapter and the previous one, you've taken a
look at the various elements of a workflow and been shown how to use those
elements to create your own custom workflows. You've also seen how properly to 
use the various elements of a workflow like Assignments and Notifications, as
well as newer and more advanced features like Parallel Workflows, Timers, and
Custom Scripts. 

It's not enough to understand each individual step of the workflow process: one
of the keys to using Kaleo workflow is to understand how each step interacts
with the other elements. If you understand these relationships, you can figure
out which features will work best for your organization. We hope you'll use the
information we've covered on workflows to craft suitable processes for your
portal.
