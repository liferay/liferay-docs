# Designing a Kaleo Workflow Definition [](id=designing-a-kaleo-workflow-definition)

To illustrate several different workflow strategies, here are diagrams of each
of the workflows that ship with the Kaleo Web plugin. You'll find these
definitions in the directory
`/kaleo-web/docroot/WEB-INF/classes/META-INF/definitions`.

___

![Figure 1: The Scripted Single Approver Definition is identical in structure to the Single Approver Definition that comes installed with Liferay, but includes a scripted assignment.](../../images/scripted-single-approver-def.png)

___

![Figure 2: The Legal Marketing Approval Definition has two review tasks and an update task.](../../images/legal-marketing-def.png)

___

![Figure 3: The Category Specific Approval Definition has a condition node, two review tasks, and an update task.](../../images/category-specific-def.png)

___

Think of workflow as a state machine made up of nodes. A node can be a state, a
task, a condition, a fork, a join, or a timer. Transitions are used to move
from one node to another. Each type of node has different properties. For
example, states execute actions automatically and require no user input. Tasks
block until user input completes a transition to another state. The transition
then moves the workflow to the next task or state. This cycle continues until
the end Approved state is reached. For example, you could create a workflow
which goes through two approvers. Initiating the workflow puts it in the In
Review state and then transitions to a task which requires user input. Users
approve or reject the asset as part of the task. When the first user approves
the asset in the workflow, a condition checks to see if there are two
approvals. Since there is only one, workflow transitions back to the task. When
the second user approves the asset, the condition finds there are two approvers
and it triggers a different transition to the Approved state. 

Transitions connect one node to another. On exiting the first node, processing
follows the transition to the node it points to. Selecting a *Transition* turns
your pointer into a connector; you connect the starting end of a transition to
one node and the other end to the next node in your process. 

In addition to *Start* and *End* node types, there are five node types you can
use in your workflow: 

- *Condition*: Directs workflow execution to an appropriate transition based on
  conditional logic of the node's script. 
- *Fork*: Forks the workflow execution into two parallel threads. 
- *Join*: Joins parallel workflow threads. 
- *Join XOR*: Joins parallel workflow threads, but only needs to receive workflow execution from 
  one of them.
- *State*: Represents a workflow state. 
- *Task*: Represents a task that can be assigned. 

Drag and drop any nodes you need onto the Kaleo Workflow Designer for Java
workflow diagram. Each node type supports execution of scripted actions and
sending notifications that can use templates. 

+$$$

**Note:** Unlike most tutorials on Liferay Developer Network, this set of
tutorials works toward building a specific project, the
*ticket-process-definition* workflow. We started building the project in the
tutorial on [Creating a Workflow Definition with Kaleo Designer for
Java](/develop/tutorials/-/knowledge_base/6-2/creating-a-workflow-definition-with-kaleo-designer-for-java).
However, you can use the information you find here in your own workflow project.

$$$

For the ticket-process-definition workflow diagram, there's a simple
`StartNode` *State* node, followed by the `Developer` *Task* node, followed by
the `EndNode` *State* node. There are two transitions, from `StartNode` &rarr;
`Developer` and from `Developer` &rarr; `EndNode`. 

Here's the idea behind the Ticket Process Workflow definition. A developer
should approve his fix and send it for quality assurance to *QA*, where it must
pass testing by a QA engineer. Then it'll go to *QA Management*, where it must
be approved by a QA manager. Let's use a *Fork* node to accurately depict these
parallel approval tasks. 

Drag and drop a *Fork* node onto your workflow diagram. A wizard helps you
create your node. Click the green plus symbol to select new or
existing nodes to process in parallel threads. A drop-down menu gives you
options to select tasks to be done in your fork threads. In addition, a checkbox
lets you indicate whether to automatically add a corresponding join node to your
workflow.

![Figure 4: You can select what to fork and whether to automatically create a join node.](../../images/kaleo-7.png)

On finishing your fork node in the wizard, Kaleo Designer places your new nodes
onto the workflow diagram's canvas. If you're not happy with the location of
your new nodes, drag them to place them where you want on your canvas. Our
ticket process workflow now looks something like this: 

![Figure 5: After creating the fork and join nodes, you can reposition them to your liking.](../../images/kaleo-8.png)

Why is there a red "X" in the bottom left of multiple nodes within our workflow?
These are error markers! Errors indicate something is specified incorrectly or
is missing from your nodes. If you click on an error marker, Developer Studio 
displays hints on resolving the problem. Don't worry, we'll address these error 
markers momentarily. 

Clicking on a node brings up a floating palette; use it to make quick, 
convenient customizations to a node. 

The floating palette has several features you can use: 

- *Add* (green cross): Add an action or notification on the node. To edit the
  added action or notification, bring up Designer's *Properties* view. 
- *Edit Actions* (paper with arrow): Edit the node's existing actions. 
- *Edit Notifications* (envelope): Edit existing notifications on the node. 
- *Edit Script* (pencil): Edit the script of the condition node. This feature
  only applies to the condition node. 
- *Change Assignments* (person): Assign or reassign a task. This feature only
  applies to the *Task* node type. 
- *Delete* (red "X"): Delete the node. 
- *Show in Source* (paper with folded corner and arrow): Show the node specified
  as as a model element in the workflow definition's XML source and switches the
  main editor to *Source* mode. 
- *Start Transition* (black arrow): Change the pointer to transition mode
  letting you create a workflow transition from the current node to another
  node.

Obviously, there is still work to be done in our workflow definition. We have
multiple error markings and the fork and join nodes aren't connected to
anything. Let's change the assignments for our two new task nodes, QA and QA
Management, by clicking the *Change Assignments* icon from the floating palette
for each node. The *Choose Assignment Type* menu appears for each node, letting
you choose their assignment type. After we assign the QA and QA Management task
nodes, the error markers disappear.

For the QA tasks in our `ticket process` workflow, let's assign someone other
than the asset creator. Realistically, each of these tasks would be assigned to
different site roles. For simplicity, lets assign both the QA and QA Management
tasks to the same user. If you have a user in mind, specify that user.
Otherwise, create a user named "Joe Bloggs" with screen name "joe". For the user
to receive emails, he must be registered within Liferay Portal. If you haven't
registered Joe Bloggs ("joe") already, see the *Discover* documentation on
[Adding and Editing
Users](/discover/portal/-/knowledge_base/6-2/the-users-section-of-the-control-panel#adding-and-editing-users). To configure the user's email, login to the
user's account and visit *Control Panel* &rarr; *Server Administration* &rarr;
*Mail* for setup options. 

Select the *Change Assignments* icon from the floating palette for each QA task
node. Then, select *Assign to a specific user* from the *Choose Assignment
Type* menu and click *Next*. You have options to enter the user's *User-id*,
*Screen name*, or *Email address*. Enter the user's screen name and click
*Finish*.

![Figure 6: Designer lets you assign a task to a specific user of Liferay Portal.](../../images/kaleo-29.png)

Assigning the QA and QA Management task nodes resolved their error markings (no
more red "X"!). The join node's error marking won't disappear until you connect
the join node to another task. 

<!-- Considering we jump away from the development work we've been doing in
designer, it might be good to screenshot here, or at least to mention, and link
if not in this tutorial, to where the development continues. -->

Let's take a moment to consider the XML code of the ticket process workflow
definition in its current state.

It specifies its XML version, encoding, and its document root element called
`workflow-definition`. Nested within the `workflow-definition` element are its
name, description (optional), version, and its nodes: 1 fork, 1 join, a start
and end state. Here's the general overview of our workflow definition: 

	<?xml version="1.0" encoding="UTF-8"?>
	<workflow-definition xmlns="urn:liferay.com:liferay-workflow_6.2.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="urn:liferay.com:liferay-workflow_6.2.0 http://www.liferay.com/dtd/liferay-workflow-definition_6_2_0.xsd">
		<name>Ticket Process</name>
		<version>1</version>

		<!-- fork node -->

		<!-- join node -->

		<!-- start and end state nodes -->

		<!-- task nodes -->

    </workflow-definition>

We'll describe each of our workflow definition's nodes, starting from top to
bottom, left to right.

Our start state node, named *StartNode*, simply transitions the flow of
execution to our *Developer* task. Here is this state node (note, we left off
the optional `<metadata>...</metada>` elements to shorten the code snippets): 

    <state>
        <name>StartNode</name>
        <initial>true</initial>
        <transitions>
            <transition>
                <name>Developer</name>
                <target>Developer</target>
                <default>true</default>
            </transition>
        </transitions>
    </state>

The *Developer* task is assigned to the creator and transitions to the *EndNode*
state node upon approval. Each workflow instance is associated with an asset. By
default, a task is associated with the asset creator.

    <task>
        <name>Developer</name>
        <assignments>
            <user></user>
        </assignments>
        <transitions>
            <transition>
                <name>approved</name>
                <target>approved</target>
            </transition>
            <transition>
                <name>EndNode</name>
                <target>EndNode</target>
            </transition>
        </transitions>
    </task>

The ending state node is named *EndNode*. It specifies that an *Approve*
action is to be executed on entering this node. 

    <state>
        <name>EndNode</name>
        <actions>
            <action>
                <name>Approve</name>
                <description>Approve</description>
                <script> <![CDATA[Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus("approved"), workflowContext);]]> </script>
                <script-language>javascript</script-language>
                <execution-type>onEntry</execution-type>
            </action>
        </actions>
    </state>

Our workflow definition has one fork node named *Pass to QA*. It forks the
process to the *QA* and *QA Manager* task nodes:

    <fork>
        <name>Pass to QA</name>
        <transitions>
            <transition>
                <name>QA</name>
                <target>QA</target>
            </transition>
            <transition>
                <name>QA Management</name>
                <target>QA Management</target>
            </transition>
        </transitions>
    </fork>

Both the QA-related task nodes are assigned to the user with screen name
*joe*.

    <task>
        <name>QA</name>
        <assignments>
            <user>
                <screen-name>joe</screen-name>
            </user>
        </assignments>
        <transitions>
            <transition>
                <name>Pass to QA Join</name>
                <target>Pass to QA Join</target>
            </transition>
        </transitions>
    </task>

    <task>
        <name>QA Management</name>
        <assignments>
            <user>
                <screen-name>joe</screen-name>
            </user>
        </assignments>
        <transitions>
            <transition>
                <name>Pass to QA Join</name>
                <target>Pass to QA Join</target>
            </transition>
        </transitions>
    </task>

Lastly, the *QA* and *QA Manager* task nodes transition into the *Pass to QA
Join* join node:

    <join>
        <name>Pass to QA Join</name>
    </join>

Now you know what the resulting XML is like for your workflow definition. You
can check your definition's source code anytime from within Liferay Studio or
your favorite XML editor. To learn more on the different workflow nodes
available to use in Liferay workflow definitions, see
the *Discover* documentation on [Creating New Workflow Definitions](/discover/portal/-/knowledge_base/6-2/creating-new-workflow-definitions). 

Since we've been using Designer's Workflow Diagram mode, let's go over some of
its features. 

## Workflow Diagram Features [](id=workflow-diagram-features)

Developer Studio provides you with additional features within the workflow
diagram. Below we list some of these features; they can greatly enhance your
workflow designing experience. 

- *Workflow Diagram Actions* are available via the toolbar in the upper right
  corner of the Workflow Diagram: 
    - *Zoom In*
    - *Zoom Out*
    - *Zoom Actual*
    - *Save as Image*
    - *Print*

	These toolbar icons are shown in the figure below.
	
	![Figure 7: The Workflow Diagram Actions are in the toolbar in the upper right corner of the Workflow Diagram.](../../images/kaleo-toolbar-icons-01.png)

- *More Workflow Diagram Actions* are accessible by right clicking on the
  Workflow Diagram's canvas:
    - The *Layout* feature lets you arrange nodes vertically or horizontally, so
      it's easy to organize your workflow quickly without having to touch a node. 
    - The *Rename* feature lets you rename a node or transition; select by
      double clicking its current name and typing in a new name.
    - The *Surveyor's Level* feature helps you center a node, vertically or
      horizontally, with respect to another node.

You've probably noticed the *Properties* and *Outline* views below your workflow
diagram. Let's explore them next. 

## Properties View and Outline View [](id=properties-view-and-outline-view)

The *Properties* and *Outline* views contain more cool features you can use to
customize your workflow; they're located on the bottom and bottom right of
Developer Studio, respectively. 

The *Properties* view lets you edit the current node's properties. If no node is
selected or you select the workflow canvas, the Properties view displays your
workflow's general properties; you can edit these, too. Once you click on an
individual node, its properties appear. 

Node properties are grouped as follows:

- *Actions*: Execute scripts with respect to your node. Each action has a
  *Name*, *Script Language*, *Execution Type*, and a *Priority* (optional).
  Clicking *Edit Script* brings up the script in the default editor for the
  *Script Language* you specified. 
- *Notifications*: Notify users with respect to your node. Each notification has
  a *Name*, *Template Language*, *Execution Type*, and one or more *Notification
  Types*. 
- *Assignments*: Assign tasks to users or roles. Click *Change Task Assignments*
  to specify or change the assignment. 
- *Task Timers*: Name timers for a task and whether the timers are blocking.
  Each task timer has a *Name* and *Blocking* indicator. 
- *Script*: Edit a script for your condition node. Clicking *Edit Script* brings
  up the script in the default editor for the *Script Language* you specified. 
- *Other*: Edit miscellaneous properties like *Name* and *Description*,
  applicable to your node's type. 

Here's what the Properties view looks like in Developer Studio: 

![Figure 8: The Properties view gives you multiple sub-tabs to help customize your workflow nodes.](../../images/kaleo-11.png)

Workflows frequently become too large to view in entirety on the workflow
diagram screen; the *Outline* view is a huge asset when this happens. It's a top
level view that displays your entire workflow definition, no matter how large it
becomes. In addition, it highlights what you're currently viewing on your
Workflow diagram, giving you a picture of where you're located in the broader
picture of your workflow. You can use the *Outline* view to change your position
in the Workflow Diagram by dragging the highlighted box where you'd like to
focus. 

Developer Studio's *Properties* and *Outline* views make customizing your
workflow easier than ever! 

## Viewing Workflow Definition XML Source [](id=viewing-workflow-definition-xml-source)

The workflow diagram view of your workflow definition is convenient; sometimes
you'll also want to edit and review your workflow definition's XML source code.
Selecting the *Source* tab next to the *Diagram* tab in the main editor view
takes you to the XML, and you can easily switch contexts as you need. 

![Figure 9: Feel free to switch between *Diagram* and *Source* modes of your workflow editor in Kaleo Designer for Java.](../../images/kaleo-20.png)

*Source* mode offers you its own cool features: 

- *Section Highlighting*: Shows XML source code for the node or transition
  currently selected in the workflow diagram. Click the document icon in an
  entity's floating palette or right click the entity and select *Show in
  source*. 
- *Editor Validation*: Displays an error marking in the editor's gutter if your
  code is invalid. Click the error marking to see hint for resolving the error. 
- *Content Assist*: Suggests language specific functions and variables as you
  edit code. When you're inserting variables, it also lists all available
  variables that are a part of the Kaleo workflow. 

+$$$

**Note:** The *Discover* &rarr; *Portal* section
[Creating New Workflow Definitions](/discover/portal/-/knowledge_base/6-2/creating-new-workflow-definitions)
explains how to define workflows via XML.

$$$

With the *Source* view, you can keep track of your edits while using Developer
Studio's powerful graphical features. 

Designing a workflow is good. Creating nodes and transitions is good, too. But
there's more you can do with it; keep reading to learn about [creating workflow scripts](/develop/tutorials/-/knowledge_base/6-2/using-workflow-scripts), or
[making your notifications more interesting using Freemarker templates](/develop/tutorials/-/knowledge_base/6-2/leveraging-template-editors-for-notifications).

<!-- ## Related Topics -->

