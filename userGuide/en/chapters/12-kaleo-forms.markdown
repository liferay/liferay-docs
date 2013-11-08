
# Kaleo Forms: Defining Business Processes [](id=kaleo-forms-defining-business-processes-liferay-portal-6-2-user-guide-12-en)

![EE Only Feature](../../images/ee-feature-web.png)

In the last chapter, we looked at the elements that comprise a workflow
definition and discussed how to create a workflow definition. In this chapter,
we introduce the Kaleo Workflow Designer for Liferay EE which allows you to
create workflow definitions using an intuitive UI. Using the workflow designer
saves you the time and trouble of having to deal directly with the XML.

Developers who are used to working with XML are generally able to create
workflow definitions. Other users may not be so comfortable with it. In fact,
even skilled developers can make mistakes that break a definition and require
time to troubleshoot. To help streamline the creation of workflow definitions
and empower more users to create custom workflows, Liferay provides the Kaleo
Workflow Designer in Liferay 6.2 EE.

There are two pieces to the workflow designer: *Kaleo Forms* and *Kaleo
Designer*. These two portlets are included in the *Kaleo Forms* app and can be
downloaded from Liferay Marketplace. You'll also need to install the *Kaleo
Workflow* app from Marketplace. This app provides the Kaleo Workflow engine,
which allows users to participate in a workflow process that has been designed
for them. Kaleo Forms is an extension of the Dynamic Data Lists feature (covered
in chapter 10). This enables you to create web forms and basic applications,
then apply a workflow to govern the processing of those forms. Kaleo Designer is
a drag and drop interface for creating new workflow definitions. It can be used
in conjunction with Kaleo Forms or standalone to create workflow definitions
without having to write XML.

Let's look at Kaleo Forms first. 

## Kaleo Forms [](id=kaleo-forms-liferay-portal-6-2-user-guide-12-en)

Add the Kaleo Forms portlet to a page. In the portlet's initial state, the
*Summary* tab displays similar information to what you might see in the *My
Workflow Tasks* portion of the *My Account* interface for a particular user.
Because the Kaleo Forms portlet is scoped to Kaleo Processes, tasks related to
other assets cannot be managed from Kaleo Forms. Any forms available for
processing through the workflow can be initiated through the *Submit New*
button, as the below image indicates.

![Figure 12.1: Kaleo Forms give you a convenient way to manage all available workflows in the portal. ](../../images/kaleo-forms-initial-view.png)

Click on *Processes* to view any existing workflow processes or to create new
ones. All available processes are listed here. If you're coming here for the
first time, however, there won't be any, so let's create one. Click *Add*.
You'll see the screen below. 

![Figure 12.2: The New Process page gives you several options when creating a new workflow.](../../images/kaleo-workflow-add-process.png)

Set a name and a description. Next, you'll define an *Entry Definition* and an
*Initial Form*, choose or create a *workflow*, and *Workflow Task Forms*.

**Entry Definition:** This is a Dynamic Data List Definition, you can use an
existing definition here or create a custom one through the UI.

**Initial Form:** You can customize the display of the Entry Definition with the
Initial Form. This can include things like adding Pagination or altering some
other display feature.

**Workflow:** You can choose any existing workflow definition here or define a
new one.

**Workflow Task Forms:** This is where you can define how the workflow
definition interacts with form definitions. You can trigger a workflow action to
occur along with a form action, such as a notification, or have a multi-step
process where part of the form is completed but needs to be approved before the
user can complete another part of the form. 

You probably noticed when choosing a workflow, you also have the option to
create a new one. This is where the Kaleo Workflow Designer can help you build a
workflow without having to write any XML. Let's see how this works. 

## Kaleo Designer [](id=kaleo-designer-liferay-portal-6-2-user-guide-12-en)

Kaleo Designer provides a drag and drop interface for users to create custom
workflows. It's an incredibly powerful tool for managing workflow definitions.
The Workflow Designer can only be accessed through the Kaleo Forms portlet but
definitions created can be used for other processes as well.

![Figure 12.3: Define business processes using Kaleo Workflow Designer.](../../images/kaleo-workflow-designer.png)

There are seven types of nodes you can add to a definition. The node types are
**Condition**, **End**, **Fork**, **Join**, **Start**, **State** and **Task**.
If you've read the entire chapter, you'll notice Start and End aren't node types
we've previously discussed; that's because they're actually just State nodes,
with certain fields pre-filled to help streamline the creation process. Since
every workflow has a start and end state, you'd have to do this anyway. 

Each node you add has a pop-up menu letting you edit or delete the node. As
you hover your mouse over the edges of a node, notice your mouse pointer changes
to a cross. The cross indicates you can connect the current node to another
node. Hold down your mouse button and drag the mouse to start drawing your
transition to another node. If you stop before reaching the edge of the next
node, a pop-up displays node types you can create and connect to on-the-fly. To
connect with an existing node, continue dragging the connector to that node. 

![Figure 12.4: Create transitions to existing or new nodes. The connector pop-up let's you create and connect to new nodes on-the-fly.](../../images/kaleo-connector.png)

To get a feel for how the designer works, let's use the workflow designer to
duplicate the default workflow definition. When we choose the option to *Add
Definition* from the Kaleo Forms portlet, it creates a blank workflow definition
with start and end nodes. To make this work, we'll add two tasks, fill in the
relevant information, assign the tasks properly, and create the transitions.

First add two tasks, then use the edit icon to name them *Review* and *Update*. 

![Figure 12.5: Edit a node by clicking on its node icon and modifying its settings.](../../images/kaleo-rename-node.png)

Next, connect the nodes so Review has four nodes, as follows: one receiving the
transition from **StartNode**, one sending a transition to **Update**, one
receiving a transition from **Update**, and one sending a transition to
**EndNode**.

![Figure 12.6: Your workflow should look something like this.](../../images/kaleo-designer-basic-workflow.png)

Next, we want to add the correct assignments and notifications. Click on
*Review*. The box on the left shows all the properties of the Review node. In
the *Assignments* category, assign the task to the *Portal Content Reviewer*
role. Double-click on *Notifications* and create a notification with the type *On
Assignment*. Now move to the Update node and assign it to the *Content Creator*
with its own notification.

Next, let's go through all of the transitions and make sure they're named
correctly. What are the transitions? Workflow transitions connect one node to
another. On exiting the first node, processing continues to the node pointed to
by the transition. Every time you created an arrow from one node to another it
created a transition. By default, these transitions get system generated names
so we'll rename them all to something more human readable. First, click on the
arrow going from the Start node to the Review node and set the name as *Submit*
and set *Default* to true--we'll leave all the others as false. Set the name of
the transition from Review to Update to *Reject* and the one from Update to
Review to *Resubmit*. Lastly, set the name of the transition from Review to the
EndNode to *Approve*.

![Figure 12.7: Your completed workflow should look like this.](../../images/kaleo-designer-basic-workflow-complete.png)

Now let's take a look at the generated XML. It should look a lot like our
default workflow, only a tiny bit messier, as the nodes display in the order
they were created, not in the logical order that happens when a human writes the
code. Save your definition and it's ready to use.

## Summary [](id=summary-liferay-portal-6-2-user-guide-12-en)

As you can see, Liferay Portal and the Kaleo Workflow engine combine to create a
very robust environment for web content management. The Kaleo Forms portlet
allows you to manage all available workflows in the portal. You can create your
portal's workflows by using the Kaleo Designer's unique drag and drop interface.
Simple workflows can be managed using the default configuration and GUI tools,
while more complex workflows can be created to meet the workflow management
needs of almost any portal. Through this chapter and the previous one, we've
taken a look at the various elements of a workflow and shown how to use those
elements to create your own custom workflows. We've also seen how to properly
use the various elements of a workflow like Assignments and Notifications, as
well as newer and more advanced features like Parallel Workflows, Timers, and
Custom Scripts.

It's not enough to understand each individual step of the workflow process; one
of the keys to using Kaleo workflow is to understand how each step interacts
with the other elements. If you understand these relationships, you can figure
out which features will work best for your organization. We hope you'll use the
information we've covered on workflows to craft suitable processes for your
portal.
