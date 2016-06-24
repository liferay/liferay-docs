# Kaleo Designer

With the proper permissions, your @product@ users can publish assets as they
please, by default. Even if your enterprise has the greatest employees in the
world, they'll still need reviews, for a variety of reasons. The Kaleo Designer
in @product@ lets you design workflow definitions so your assets go through a
review process before publication.

<!-- An asset can have a whole bunch of different workflow statuses:

-  Draft: Just created.
-  Pending: Waiting for review.
-  Approved: Successfully navigated the workflow.
-  Denied: Did not successfully navigate the workflow.
-  Expired: The asset had an expiration date which has now passed.
-  In Trash: Deleted.
-  Inactive: 
-  Incomplete
-  Scheduled
-->

The Kaleo Designer lets you develop workflow definitions using a convenient drag
and drop user interface, so you don't need to be familiar with any scripting
languages or with writing XML by hand. However, some of the features can be
enhanced if you're familiar with one of the supported scripting languages. All
that to say, don't be scared off when you come to a block of code in this
article. Just decide if you need the feature, and find someone familiar with one
of the supported scripting languages to help you out.

So what scripting languages are supported for use in your workflow definitions?
Beanshell, DRL, Groovy, JavaScript, Python, and Ruby. 

+$$$

**Note:** By default, there's only one workflow definition that's installed in
@product@; the Single Approver Workflow definition. What you might not know is
that you have access to several others, as well, you just have to do a little
digging. If you have a local @product@ installation, look in the
`[Liferay_Home]/osgi/marketplace/Liferay Forms and Workflow.lpkg`, and find the
`com.liferay.portal.workflow.kaleo.runtime.impl-[version].jar`. Open it up and
then look in `META-INF/definitions`. You'll see the following workflow
definitions:

    category-specific-definition.xml
    legal-marketing-definition.xml
    single-approver-definition.xml
    single-approver-definition-scripted-assignment.xml

To work with any of these definitions in Kaleo Designer, extract them from the
JAR file first. Once you have the XML files locally, add a new workflow (see
the below instructions) in the designer, then open the *Source (Kaleo XML)* tab.
Replace the entire contents with the contents of the XML definition file. Name
the definition appropriately, and click either *Save as Draft* or Publish* (see
below for more information on saving and publishing). Now you can begin
exploring or modifying the definition as you'd like.

$$$

It's time to start exploring the Kaleo Designer and its features.

## Adding New Workflow Definitions with Kaleo Designer

Access the Kaleo Designer by going to the Control Panel, then clicking
Configuration &rarr; Kaleo Designer. Click the Add icon
(![Add](../../../images-dxp/icon-add.png)).

Give the workflow definition a title and you're ready to start designing your
workflow.

![Figure x: The Kaleo Designer's graphical interface makes designing workflows intuitive.](../../../images-dxp/kaleo-designer-canvas.png)

## Saving and Publishing Workflow Definitions

You're eager to design a workflow definition, but first look below the canvas to
see the buttons that let you *Save as Draft*, *Publish*, or *Cancel*. Saving the
definition as a draft lets you save your work, so it's not lost (due to a
timeout, for example). It won't be published (and usable in @product@), and it
won't be considered a version, until the Publish button is clicked. Each time
you save the workflow as a draft, the *Draft Version* field is incremented, and
each time you publish the workflow, a new *Version* is added.

![Figure x: View a list of the current workflows that can be edited in the Kaleo Designer.](../../../images-dxp/kaleo-designer-definitions.png)

## Adding Nodes

Since it's unlikely that you'll  be satisfied with a start node, an end node,
and a transition between them, you need to add nodes to the workflow. You can
add whatever nodes you'd like, but here's how you do it:

1. *Drag* a node from the *Nodes* palette on the right of the designer, and
*drop* it on the canvas.

2. You'll see it's not connected to other nodes by a transition, so it will
never be used in the workflow. Delete the existing transition and then you can
make new transitions to direct the *flow* of your workflow (see more about
transitions below if you're not sure what they're for or how to use them in
Kaleo Designer).

Alternatively, start by deleting the default transition, then click the edge of
the start node, drag a new transition from the start node to a blank spot on the
canvas, and release it. You'll be prompted to create a node at that spot,
because you can't have a transition without a starting point and an
ending point on a node.

![Figure x: You can add a node by creating a transition that ends at a blank spot on your Designer canvas.](../../../images-dxp/kaleo-designer-add-node.png)

That's it. Of course, if you drag, say, a *Task* node onto the canvas, you're
going to want to configure it.

## Node Settings

Now you know how to add nodes to the workflow definition. By default you have
three things added to your canvas: a start node, a transition, and an end node.
Think of the *EndNode* as the point in the workflow where an asset reaches the
*Approved* status. The *StartNode* is the point at which the asset goes from the
*Draft* status to *Pending*. You might decide to name your nodes to reflect
what's going on in each one. To name a node, just double click it and its
*Settings* will be displayed. Then double click the value of the *Name* property
and you'll be able to edit the name. Click *Save* when you're done. 

![Figure x: You can edit a node's settings.](../../../images-dxp/kaleo-designer-node-settings.png)

Of course, there's more you can do besides changing node names. Actions,
Notifications, and Assignments can be used to make your workflow definition
really useful and interactive.

### Actions

Actions are used to do some additional processing after exiting a node, before
entering the node, or once a task node is assigned. They're configured by
accessing a node's Settings tab, then double clicking *Actions*.

![Figure x: You can add an Action to a Task node.](../../../images-dxp/kaleo-designer-action.png)

The Single Approver workflow contains an Update task with an action written in
Groovy that sets the status of the asset as *denied*, then sets it to *pending*. 

    import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
                            import com.liferay.portal.kernel.workflow.WorkflowConstants;

                            WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("denied"), workflowContext);
                            WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("pending"), workflowContext);

Why would the action script first set the status to one thing and then to
another like that?  Because for some assets, the *denied* status is used to send
the asset creator an email notification that the item has been denied.

The end node in your workflow definition has an action configured on it by
default, on entry to the end node:

    import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
                            import com.liferay.portal.kernel.workflow.WorkflowConstants;

                            WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("approved"), workflowContext);

This is a Groovy script that just updates the status to *approved*, since that's
usually the goal of a workflow process.

You can do something simple like the actions above, or you can be as creative as
you'd like.

### Assignments

Workflow tasks need to be completed by a @product@ user. You can choose how you
want to configure your assignments. 

![Figure x: You can add a Notification to a Task node.](../../../images-dxp/kaleo-designer-assignment.png)

You can choose to add assignments to specific Roles, multiple roles of a Role
Type (organization, site, or regular role types), to the Asset Creator, to
Resource Actions, or to specific Users. Additionally, you can write a script to
define the assignment.

You can configure multiple assignments for a task.

+$$$

**Note:** What does it mean to assign a workflow task by Resource Action? Think
of an *UPDATE* resource action. If your workflow definition specifies the UPDATE
action in an assignment, then anyone who has permission to update the type of
asset being processed in the workflow is assigned to the task.

$$$

+$$$

**Note:** One of the options you have is to use a script to manage the assignment. Here's
the script for the Review task assignment in the Scripted Single Approver
workflow definition:

    import com.liferay.portal.kernel.model.Group;
    import com.liferay.portal.kernel.model.Role;
    import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
    import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
    import com.liferay.portal.kernel.util.GetterUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;

    long companyId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));

    long groupId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_GROUP_ID));

    Group group = GroupLocalServiceUtil.getGroup(groupId);

    roles = new ArrayList<Role>();

    Role adminRole = RoleLocalServiceUtil.getRole(companyId, "Administrator");

    roles.add(adminRole);

    if (group.isOrganization()) {
        Role role = RoleLocalServiceUtil.getRole(companyId, "Organization Content Reviewer");

        roles.add(role);
    }
    else {
        Role role = RoleLocalServiceUtil.getRole(companyId, "Site Content Reviewer");

        roles.add(role);
    }

    user = null;
						
Don't let all that code intimidate you. It's just assigning the task to the
*Administrator* role, then checking whether the *group* of the asset is an
Organization, and assigning it to the *Organization Content Reviewer* role if it
is. If it's not, it's assigning the task to the *Site Content Reviewer* role.

$$$

It's good to assign a task to a user, and it's even more useful if they can get
a notification that something is required of them in the workflow.

### Notifications

Notifications are often sent to tell task assignees that something is required
of them in the workflow, or to update asset creators on the status of the
process. They can be sent for tasks or any other type of node in the workflow.
To set up notifications, double click on Notifications in a node's Settings tab
and create a notification.

![Figure x: You can send notification from a Task node.](../../../images-dxp/kaleo-designer-notification.png)

You'll need to specify the Notification Type, and you can choose from a list of
User Notification, Email, Instant Messenger, or Private Message. You can use
Freemarker or Velocity if you need a template, or you can choose to write a
plain text message.

Here's a basic Freemarker template that reports the name of the asset creator and
the type of asset in the notification:

    ${userName} sent you a ${entryType} for review in the workflow.

You can also choose to link the sending of the notification to entry into the
node (On Entry), when a task is assigned (On Assignment), or when the workflow
processing is leaving a node (On Exit).

You can configure multiple notifications on a node.

Commonly, the assignment and notification settings are teamed up so a user
receives a notification when assigned a task in the workflow. To do this you
just choose *Task Assignees* under *Recipient Type* when configuring the
notification.

## Transitions

What are transitions? Workflow transitions connect one node to another. On
exiting the first node, processing continues to the node pointed to by the
transition. Every time you create an arrow from one node to another, Kaleo
Designer creates a transition. 

![Figure x: You connect nodes and direct workflow processing with transitions. The Single Approver workflow has transitions named Submit, Resubmit, Reject, and Approve.](../../../images-dxp/kaleo-designer-single-approver.png)

Each node you add has a pop-up menu letting you delete the node. As you
hover your mouse over the edges of a node, notice your mouse pointer changes to
a cross. The cross indicates you can connect the current node to another node.
Hold down your mouse button and drag the mouse to start drawing your transition
to another node. If you stop before reaching the edge of the next node, a pop-up
displays node types you can create and connect to on-the-fly. To connect with an
existing node, continue dragging the connector to that node.

When developing workflows in Kaleo Designer, make sure you go through all the
transitions and name them appropriately. By default, these transitions get system
generated names, so rename them all to something more human-readable, as they're
displayed to workflow users as links that will send the workflown item to the
next step in the workflow.

![Figure x: In the Single Approver workflow, a user in the Review task can choose to Approve or Reject the asset, which sends the asset either to the EndNode or to the Update task.](../../../images-dxp/kaleo-designer-transition-link.png)

To rename transitions, click on the arrow representing the transition
and use the Setting tab to set the name just like you do for a node.

## Task Nodes

Task nodes have several parts and are the most complex parts of a workflow
definition. Notifications, Assignments, and Actions defined in scripts can all
be part of a task node. 

Drag and drop a task node on your workflow canvas if you have not already. Open
its settings and give it a name as described above. Then double click *Actions*
in the task's Settings pane.

You can write a script defining an action that's triggered for your task in
Beanshell, DRL, Groovy, JavaScript, Python, or Ruby.

As described above, you can configure notifications and assignments on task
nodes.

## Start and End Nodes

Start and end nodes are used to kick off the workflow processing, and to bring
the asset in the workflow to its final, approved state. Often times, you can
use the default start node and end node that are added to a new workflow
definition by the Kaleo Designer without modification. If you want to do some
more processing (in the case of a start node) add an action to the node using
the Settings tab, as described in the section on Actions above.

End nodes have an action by default, that simply sets the workflow status to
Approved using the Groovy scripting language:

    import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
                            import com.liferay.portal.kernel.workflow.WorkflowConstants;

                            WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("approved"), workflowContext);

Feel free to add more to the action script if you need to do additional
processing.

By default, there's a transition connecting the start node and end node, but
you'll probably want to delete it, since most workflows won't proceed straight
form the initial state to approved.

## State Nodes

State nodes can have Notifications and Actions. The default end node added by
Kaleo Designer is simply a pre-configured state node that sets the workflow
status to Approved. Perhaps you want to create a node that sets the status to
*Expired*. You could create a state node for it by dragging one onto your Kaleo
Designer canvas, then configuring an action in it that sets the status to
Expired. Here's what it would look like in Groovy:

    import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
                            import com.liferay.portal.kernel.workflow.WorkflowConstants;

                            WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("expired"), workflowContext);

Next learn to do parallel processing using fork and join nodes.

## Forks and Joins

Sometimes you don't need to wait for one task to be completed before moving on
to another one. Instead, you want to do some parallel processing in the workflow
process. For this purpose, you'll transition to a fork node, make two
transitions from the fork to your parallel tasks, and then come back together
using a join  node.

![Figure x: Forks and Joins are used to enable parallel processing in the
workflow.](../../../images-dxp/kaleo-designer-fork-join.png)

With a regular Join node, in order for the workflow to proceed beyond the join,
the transition from both parallel executions must be invoked. However, if you
use a Join XOR node instead, the workflow will proceed as long as the transition
from one of the parallel executions is invoked.

Keep in mind that you must balance your fork nd join nodes. In other words, for
every fork, there must be a join that bring the parallel workflow threads back
together.

## Conditions

Sometimes you need to check for something on the asset before continuing in the
workflow. You basically need a node for scripting.

From the Category Specific Approval, this is the script in the condition node
that starts the workflow (coming directly from the start node):

    import com.liferay.asset.kernel.model.AssetCategory;
    import com.liferay.asset.kernel.model.AssetEntry;
    import com.liferay.asset.kernel.model.AssetRenderer;
    import com.liferay.asset.kernel.model.AssetRendererFactory;
    import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
    import com.liferay.portal.kernel.util.GetterUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    import com.liferay.portal.kernel.workflow.WorkflowHandler;
    import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

    import java.util.List;

    String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);

    WorkflowHandler workflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

    AssetRendererFactory assetRendererFactory = workflowHandler.getAssetRendererFactory();

    long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

    AssetRenderer assetRenderer = workflowHandler.getAssetRenderer(classPK);

    AssetEntry assetEntry = assetRendererFactory.getAssetEntry(assetRendererFactory.getClassName(), assetRenderer.getClassPK());

    List<AssetCategory> assetCategories = assetEntry.getCategories();

    returnValue = "Content Review";

    for (AssetCategory assetCategory : assetCategories) {
        String categoryName = assetCategory.getName();

        if (categoryName.equals("legal")) {
            returnValue = "Legal Review";

            return;
        }
    }

This script looks up the asset in question, retrieves its [asset category](/discover/portal/-/knowledge_base/7-0/organizing-content-with-tags-and-categories#defining-categories-for-content), and
sets an initial `returnValue`. Then checks to see if it has been marked with the
*legal* category. If not it goes through *Content Review* (the content-review
task in the workflow), and if it does it goes through *Legal Review* (the
legal-review task in the workflow).

Now you're equipped with the basic knowledge to design beautiful, effective
workflows so taht your assets can be properly reviewed before they're published
in your sites..

<!-- The Single Approver workflow definition is included in @product@ by default. To
get a look at how it’s defined, click Kebab menu and then Edit. The graphical
interface for editing or defining a workflow appears below the Details section
of the Single Approver edit screen.

This graphical view is where Kaleo Forms Admin can help you build a workflow
without having to write any XML. There are eight types of nodes you can add to a
definition. The node types are Condition, End, Fork, Join, Join XOR, Start,
State and Task. Notice that Start and End aren’t node types we’ve previously
discussed; that’s because they’re actually just State nodes, with certain fields
pre-filled to help streamline the creation process. Since every workflow has a
start and end state, you’d have to do this anyway.

Each node you add has a pop-up menu letting you edit or delete the node. As you
hover your mouse over the edges of a node, notice your mouse pointer changes to
a cross. The cross indicates you can connect the current node to another node.
Hold down your mouse button and drag the mouse to start drawing your transition
to another node. If you stop before reaching the edge of the next node, a pop-up
displays node types you can create and connect to on-the-fly. To connect with an
existing node, continue dragging the connector to that node.

To get a feel for how the designer works, go ahead and use the workflow designer
to duplicate the default workflow definition. Go back to the previous step and
click Add Workflow. When you choose this option, it creates a blank workflow
definition with start and end nodes. To make this work, you’ll add two tasks,
fill in the relevant information, assign the tasks properly, and create the
transitions.

First add two tasks, then use the edit icon to name them Review and Update.

Next, connect the nodes so Review has four nodes, as follows: one receiving the
transition from StartNode, one sending a transition to Update, one receiving a
transition from Update, and one sending a transition to EndNode.

Next, you want to add the correct assignments and notifications. Click on
Review. The box on the left shows all the properties of the Review node. In the
Assignments category, set the Assignment Type to Role Type, the Role Type to
Regular, and the Role Name to Portal Content Reviewer.

Now set the notifications. Double-click on Notifications and create a
notification with the Notification Type set to User Notification and the
Execution Type set to On Assignment.

Together, the assignment and notification settings specify that a user receives
a notification in their Dockbar when assigned a form in this workflow. Now move
to the Update node and assign it to the Content Creator role with its own user
notification.

Next, go through all the transitions and make sure they’re named correctly. What
are the transitions? Workflow transitions connect one node to another. On
exiting the first node, processing continues to the node pointed to by the
transition. Every time you created an arrow from one node to another, Kaleo
Designer created a transition. By default, these transitions get system
generated names, so we’ll rename them all to something more human-readable.
First, click on the arrow going from the Start node to the Review node and set
the name to Submit and set Default to true–you’ll leave all the others as false.
Set the name of the Review to Update transition to Reject and the Update to
Review transition to Resubmit. Lastly, set the name of the Review to Endnode
transition to Approve.

Now look at the generated XML. It should look a lot like the default workflow,
only a tiny bit messier, as the nodes are written in the order they were
created, not in the logical order that happens when a human writes the code.
Once you’re finished, click Publish. Your workflow is now ready to use!

Back on the third step of the New Process wizard, click Actions → Choose next to
the workflow you just created. Then click Next.

-->
