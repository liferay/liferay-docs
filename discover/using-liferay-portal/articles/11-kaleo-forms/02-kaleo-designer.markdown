# Kaleo Designer [](id=kaleo-designer)

Kaleo Designer provides a drag and drop interface for users to create custom
workflows. It's an incredibly powerful tool for managing workflow definitions.
The Workflow Designer can only be accessed through the Kaleo Forms portlet but
definitions created can be used for other processes as well.

![Figure 11.3: Define business processes using Kaleo Workflow Designer.](../../images/kaleo-workflow-designer.png)

There are seven types of nodes you can add to a definition. The node types are
**Condition**, **End**, **Fork**, **Join**, **Start**, **State** and **Task**.
If you've read the entire chapter, you'll notice Start and End aren't node types
we've previously discussed; that's because they're actually just State nodes,
with certain fields prefilled to help streamline the creation process. Since
every workflow has a start and end state, you'd have to do this anyway. 

![Figure 11.4: The Node Configuration Menu shows options to edit or delete your node.](../../images/kaleo-designer-submenu.png)

Each node you add has a pop-up menu letting you edit or delete the node. As
you hover your mouse over the edges of a node, notice your mouse pointer changes
to a cross. The cross indicates you can connect the current node to another
node. Hold down your mouse button and drag the mouse to start drawing your
transition to another node. If you stop before reaching the edge of the next
node, a pop-up displays node types you can create and connect to on-the-fly. To
connect with an existing node, continue dragging the connector to that node. 

![Figure 11.5: Create transitions to existing or new nodes. The connector pop-up let's you create and connect to new nodes on-the-fly.](../../images/kaleo-connector.png)

To get a feel for how the designer works, let's use the workflow designer to
duplicate the default workflow definition. When we choose the option to *Add
Definition* from the Kaleo Forms portlet, it creates a blank workflow definition
with start and end nodes. To make this work, we'll add two tasks, fill in the
relevant information, assign the tasks properly, and create the transitions.

First add two tasks, then use the edit icon to name them *Review* and *Update*. 

![Figure 11.6: Edit a node by clicking on its edit icon and modifying its settings.](../../images/kaleo-rename-node.png)

Next, connect the nodes so Review has four nodes, as follows: one receiving the
transition from **StartNode**, one sending a transition to **Update**, one
receiving a transition from **Update**, and one sending a transition to
**EndNode**.

![Figure 11.7: Your workflow should look something like this.](../../images/kaleo-designer-basic-workflow.png)

Next, we want to add the correct assignments and notifications. Click on
*Review*. The box on the left shows all the properties of the Review node. In
the *assignments* category, assign the task to the *Portal Content Reviewer*
role. Click on *Notifications* and create a notification with the type *On
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

![Figure 11.8: Your completed workflow should look like this.](../../images/kaleo-designer-basic-workflow-complete.png)

Now let's take a look at the generated XML. It should look a lot like our
default workflow, only a tiny bit messier, as the nodes display in the order
they were created, not in the logical order that happens when a human writes the
code. Save your definition and it's ready to use.
