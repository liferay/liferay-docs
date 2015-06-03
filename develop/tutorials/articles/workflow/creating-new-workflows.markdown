# Creating Workflows with Kaleo

# Designing Workflow with Kaleo [](id=designing-workflows-with-kaleo-designer-liferay-portal-6-2-dev-guide-07-en)

Liferay Portal includes the *Kaleo* workflow engine. With Kaleo, administrators
can set up workflows for their organization's needs; the workflow calls users to
participate in processes designed for them. Kaleo workflows, called *process
definitions*, are XML documents. Kaleo supports a [host of XML element
types](http://www.liferay.com/dtd/liferay-workflow-definition_6_2_0.xsd) to
trigger decisive actions in your business process instances.

The *Kaleo Forms EE* app from Marketplace includes Liferay's *Kaleo Workflow
Designer* that lets you create and modify portal workflows in your browser. With
Kaleo Designer for Java, you can design and publish Kaleo workflows from Liferay
Developer Studio! 

![Figure 8.1: Kaleo Designer for Java gives you a powerful environment for designing, modifying, and publishing Kaleo workflows.](../../images/kaleo-designer-for-java.png)

Kaleo Workflow does a lot. This tutorial focuses on designing a workflow process
definition, using solely XML. If you're an EE customer, you have access to Kaleo
Designer for Java, a plugin for Liferay Developer Studio that makes workflow
development much more simple. Additionally, there's a Kaleo Designer in the
Control Panel of Liferay, for subscription customers.

In other tutorials, you can explore the Kaleo Designer for Java plugin in
Liferay Developer Studio. An additional tutorial on [scripting and notification
templates]() is available.

Kaleo Designer lets you incorporate [back-end Java development](PROVIDE URL WHEN TUTORIAL COMPLETED) and [scripting](PROVIDE URL WHEN TUTORIAL COMPLETED) in your 
workflows. In this tutorial, installation and basic workflow design will be
covered. 

## Designing a Workflow

To illustrate several different workflow strategies, here are diagrams of each
of the workflows that ships with the Kaleo Web plugin.

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



## Getting Started RENAME, THIS HEADING STINKS

To illustrate how to develop workflows in XML, a definition called Ticket
Process Definition (LINK TO DOWNLOAD THE FILE) is useful to illustrate the
process.

The Ticket Process Worklfow will have several different nodes:

- State nodes to indicate the beginning and end of the workflow process

- A conditional node to move the asset along one or another transitions

- Task nodes when the workflowing asset requires user action

- Transitions to direct the asset to the next node in the workflow

- Fork and join nodes so parallel processing can happen on the asset



![Figure x.x: A visual representation of the Ticket Process Definition's nodes.](../../images/ticket-process-workflow.png)

![EE Only Feature](../../images/ee-feature-web.png)

Start by defining the schema. For Liferay workflows using Kaleo, it's
`liferay-worklow-definition-6_2_0.xsd`. Find it in the `definitions` folder of
the Liferay source or a good XML editor can cache it from Liferay's web site.
Here's the XML:

    <workflow-definition
        xmlns="urn:liferay.com:liferay-workflow_6.2.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="urn:liferay.com:liferay-workflow_6.2.0 http://www.liferay.com/dtd/liferay-workflow-definition_6_2_0.xsd"
    >

Next you define a name and description for the workflow. This appears in the
control panel when users choose and configure workflows.

	<name>Ticket Process Definition</name>
	<description>WIRTE DESCRIPTIVE SENTENCE.</description>
	<version>1</version>

After that, you define your initial state. 

### Creating an initial state [](id=creating-an-initial-state-liferay-portal-6-2-user-guide-11-en)

In this case, the state is simply that the asset has been created. States can
contain actions and transitions. Actions can contain scripts. You can specify
the language of the script with the `<script-language>` tag. Scripts can be
written in Groovy, JavaScript, Ruby or Python (see chapter 18 for more
information on leveraging scripts in workflow). For a state, the action is
triggered automatically and then executes a transition. Transitions move you to
a new state or task.

    <state>
            <name>StartNode</name>
            <initial>true</initial>
            <transitions>
                <transition>
                    <name>Assign</name>
                    <target>Developer</target>
                    <default>true</default>
                </transition>
            </transitions>
    </state>

The initial state in the Ticket Process Definition is named StartNode. While
unimaginative, it gets the point across; this is the beginning of the workflow
process. Other good names are *Created*, *Initialized*, or *ItsPartyTime*. from
the intiial state, there's a transition named Assign to a new task called
Developer. This one gets more complicatedso here's the first taste:

    <task>
		<name>Developer</name>
		<actions>
			<action>
				<name>Reject</name>

Pretty basic so far, with an opening `task` tag, follwed by a `name`. After
that, there's an `actions` tag that will hold at least one `action` tag for the
node. This actions is called Reject, and it contains a JavaScript script that
malicious code. WHAT DOEsTHIS SCRRIPT DO?

                    <script><![CDATA[Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus("denied"), workflowContext);
        Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus("pending"), workflowContext);]]></script>
                    <script-language>javascript</script-language>
                    <execution-type>onAssignment</execution-type>
                </action>
                <notification>
                    <name>Reject</name>
                    <template>&lt;#assign refererPlid = serviceContext.getAttribute("refererPlid")!""&gt; &lt;#assign doAsGroupId = serviceContext.getAttribute("doAsGroupId")!""&gt; &lt;#assign comments = taskComments!""&gt; &lt;#assign comments = taskComments!""&gt; &lt;#assign portalURL = serviceContext.portalURL!""&gt; &lt;#assign pathCtx = portalUtil.pathContext!"NO_PATH_CTX"&gt; &lt;#assign wTasksURL = ""&gt; &lt;#if (portalURL?last_index_of("/") &gt; 6)&gt; &lt;#assign portalURL = portalURL?substring(0,portalURL?index_of("/", 7))&gt; &lt;/#if&gt; &lt;#if (portalURL?length &gt; 0) &amp;&amp; (refererPlid != "") &amp;&amp; (doAsGroupId != "")&gt; &lt;#if (pathCtx?length &gt; 0)&gt; &lt;#assign portalURL = portalURL+pathCtx&gt; &lt;/#if&gt; &lt;#assign wTasksURL = portalURL+"/group/control_panel/manage?p_p_id=153&amp;p_p_lifecycle=0&amp;p_p_state=maximized&amp;p_p_mode=view&amp;doAsGroupId="+doAsGroupId+"&amp;refererPlid="+refererPlid&gt; &lt;/#if&gt; &lt;!-- email body --&gt; &lt;p&gt; Your ${entryType} submission was rejected by a reviewer. &lt;#if comments != "" &gt; Rejection comment says: &lt;strong&gt;"${comments}"&lt;/strong&gt; &lt;/#if&gt; &lt;br /&gt;Please do necessary modifications to your ${entryType} and &lt;strong&gt;resubmit&lt;/strong&gt; your work. &lt;/p&gt; &lt;#if (wTasksURL?length &gt; 0)&gt; &lt;p&gt; &lt;a href="${wTasksURL}"&gt;Click here&lt;/a&gt; to see workflow tasks assigned to you. &lt;/p&gt; &lt;/#if&gt; &lt;!-- Signature --&gt; &lt;p&gt;Sincerely,&lt;br /&gt;&lt;strong&gt;Liferay Portal Workflow&lt;/strong&gt;&lt;/p&gt;</template>
				<template-language>freemarker</template-language>
				<notification-type>email</notification-type>
				<execution-type>onEntry</execution-type>
			</notification>
		</actions>
		<assignments>
			<user/>
		</assignments>
		<transitions>
			<transition>
				<name>Submit</name>
				<target>Code Review</target>
				<default>true</default>
			</transition>
		</transitions>
	</task>



    <state>
        <name>created</name>
		<metadata>
			<![CDATA[{"xy":[36,51]}]]>
		</metadata>
        <initial>true</initial>

From the initial state, you transition to a new task, where further processing
is blocked so the asset can be reviewed.

        <transitions>
            <transition>
                <name>review</name>
                <target>review</target>
            </transition>
        </transitions>
    </state>

The next step is to create a task. 
    <?xml version="1.0"?>

    <workflow-definition 
        xmlns="urn:liferay.com:liferay-workflow_6.2.0" 
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
        xsi:schemaLocation="urn:liferay.com:liferay-workflow_6.2.0 http://www.liferay.com/dtd/liferay-workflow-definition_6_2_0.xsd"
    >



        <name>Ticket Process 2</name>
        <version>1</version>





Liferay Portal ships with the Kaleo Web plugin already deployed. The Single
Approver Definition is already installed in your portal, and can be managed in
Control Panel &rarr; Workflow section. There are a few more definitions that
ship with Kaleo Web, but aren't installed by default. You can find them by
navigating to your portal's `webapps` folder, then to
`kaleo-web/src/META-INF/definitions`, where you'll see

`category-specific-definition.xml`

`legal-marketing-definition.xml`

`single-approver-definition.xml`

`single-approver-definition-scripted-assignment.xml`

Inspecting these existing definitions can help you determine whether you are best served by creating
something from scratch or modifying the source XML from one of these.

Kaleo Designer's graphical interface lets you drag and drop nodes into your
workflow. A shortcut on each node gives you easy access to the node's XML,
letting you edit its current implementation to make subtle modifications or
inject new business logic. In addition, Liferay Developer Studio comes bundled
with a Java/Groovy editor (made available by Spring Source), giving you the
same rich editing experience you're accustomed in editing Kaleo
[Groovy](http://groovy.codehaus.org/Documentation) scripts.  From the editor
you can delegate workflow decisions to your custom business logic APIs or
access any of the Liferay Portal APIs. In Developer Studio, you can leverage
editors for Beanshell, Drl, JavaScript, Python, and Ruby scripting languages.
You can also leverage the editor for the
[FreeMarker](http://freemarker.sourceforge.net/) template language. Kaleo
Designer for Java gives you a rich tool set for creating/editing workflows,
manipulating workflow nodes, and implementing business logic. But there's more!

With Kaleo Designer for Java, you can remotely add and update workflow
definitions directly to and from your Liferay server. You can publish your
workflow drafts to your portal by simply dragging the workflow file onto your
portal server in Developer Studio's *Servers* tab. The *Servers* tab shows
workflows you've published from studio and gives you access to workflows already
published on the portal server. You can edit existing workflows and create
custom business logic in Developer Studio locally, then republish them on your
portal; you don't have to navigate back and forth from your portal and Developer
Studio to complete these tasks. As you can see, Kaleo Designer for Java is a
powerful application for creating, modifying, and publishing workflows in
Liferay Developer Studio. 

Although Kaleo Designer for Java is the tool of choice for EE workflow
designers, CE workflow designers can write Kaleo workflows too. But they are
limited to writing them in their favorite XML editor. All Kaleo process
definitions must follow the
[schema](http://www.liferay.com/dtd/liferay-workflow-definition_6_2_0.xsd). As
we show you how to design workflows, we'll include their resulting XML code for
your convenience. 

We'll cover the following topics as we design workflows:

- Installing Kaleo Designer for Java
- Creating a Workflow
- Using Workflow Scripts
- Leveraging Template Editors for Notifications
- Viewing Workflow Definition XML Source
- Publishing Workflows to the Server
- Using Workflows in Liferay Portal
- Using Dynamic Data Lists (DDLs) with Workflows

## Installing Kaleo Designer for Java [](id=installing-kaleo-designer-for-java-liferay-portal-6-2-dev-guide-07-en)

![EE Only Feature](../../images/ee-feature-web.png)

To use Kaleo Designer for Java, install the *Kaleo Forms EE* app from Liferay
Marketplace. The app includes three plugins--`kaleo-forms-portlet`,
`kaleo-designer-portlet`, and `kaleo-web` plugins.

Here's how to download and install *Kaleo Forms EE*:

1.  Go to [Liferay Marketplace](https://www.liferay.com/marketplace).

2.  Click on Categories &rarr; [Subscription Apps](http://www.liferay.com/marketplace/-/mp/category/12729032) 

3.  Download and install the [*Kaleo Forms EE*](https://www.liferay.com/marketplace/-/mp/application/15194452) app.

---

![note](../../images/tip-pen-paper.png) **Note:** The *Kaleo Forms EE* app comes
with an existing workflow designer that's used *within* Liferay Portal. It's
used to design workflow configuration and is described in the [Kaleo Forms:
Defining Business
Processes](discover/portal/-/knowledge_base/6-2/kaleo-forms-defining-business-processes)
chapter of *Using Liferay Portal*. Refer to the [Using
Workflow](discover/portal/-/knowledge_base/6-2/creating-new-workflow-definitions)
chapter of *Discover &rarr; Portal* if you're unfamiliar with basic Kaleo workflow
concepts or want to know how to design your workflow within Liferay Portal. 

---

After downloading and installing the Kaleo Forms EE application, you must
restart the Liferay Server. For Developer Studio to connect to the Kaleo APIs to
open workflow definitions in Portal 6.2, specify a username
and password for a portal administrator in your server's settings. 

1. Stop your 6.2 server.

2. Open the server's configuration editor by double-clicking the server from
within the Servers view in Studio.

3. In the configuration editor, under Liferay Settings, add the portal
administrator username and password. Save the configuration changes. 

4. Start the server.

A *Kaleo Workflows* folder automatically appears underneath the server instance
in the Servers view of Developer Studio. 

![Figure 8.2: In Studio's *Servers* view, your server's Kaleo Workflows folder shows workflows published on your portal.](../../images/kaleo-1.png)

Developer Studio retrieves all workflow definitions published on your portal
server. As mentioned previously, the Kaleo Designer for Java lets you remotely
add and update Kaleo workflow definitions directly to and from the portal
server. The *Single Approver* workflow comes already installed with the Kaleo
app. 

To open a workflow, double click it in the Kaleo Workflows folder. This
retrieves the workflow definition from the Liferay server so you can edit it in
Developer Studio. 

---

![note](../../images/tip-pen-paper.png) **Note:** When you open a workflow,
you'll be prompted to choose whether to switch to the Kaleo designer
perspective. Clicking *Yes* lets you use the perspective's helpful features,
including the palette toolbar, properties view, and outline view. 

---

Next, let's get into the flow (pun intended) by creating our own workflow using
the Kaleo Workflow Designer for Java. 

## Creating a Workflow [](id=creating-a-workflow-liferay-portal-6-2-dev-guide-07-en)

Developer Studio makes it easy for you to write custom business logic that
enhances Kaleo workflows. Let's create our own workflow in Developer Studio and
highlight features from Kaleo Designer for Java (Designer) along the way.

To demonstrate Designer's features, let's create a workflow definition for a
software ticketing process. Are you ready to roll? Let's get started! 

![Figure 8.3: Check out the adventures of Liferay's mascots, Ray and Alloy, found at http://www.liferay.com/ray.](../../images/kaleo-15.png)

1.  Create your new workflow definition by going to *File* &rarr; *New* &rarr;
    *Liferay Kaleo Workflow*. Alternatively, you can select *Liferay Kaleo
    Workflow* from the toolbar button shown in the figure below. 

    ![Figure 8.4: Create a new workflow definition locally by selecting *Liferay Kaleo Workflow* from the toolbar button.](../../images/kaleo-3.png)

2.  The *Create New Kaleo Workflow File* wizard will guide you through the steps
    necessary to complete the initial setup of your new workflow definition. 

    The first window you'll see is the *Create Kaleo Workflow* form. There are
    several fields here:  

    *Project*: Specify an existing Liferay project to house your workflow
    definition.  

    *Folder*: Specify where in the project the workflow definition XML file will
    be stored.

    *Name*: Give your workflow definition a descriptive name. We've chosen
    *Ticket Process* for our example. 

    *Initial state name* and *Final state name*: Name your workflow's initial
    and final state nodes.

    *Default script type*: Choose a default script type; Designer will bring
    its editor up when you're done creating the workflow.

    *Default template type*: Choose a default template editor; Designer will
    open it when you're done creating the workflow. 

    The snapshot below displays the setup menu for our
    ticket process workflow definition.

    When you're finished in this window click *Next*. 
    
    ![Figure 8.5: The Create Kaleo Workflow form in Liferay Developer Studio lets you select a script type and template type for your workflow.](../../images/kaleo-new-workflow-01.png)

    You'll be directed to the *Choose Assignment Type* window next. Here you'll
    provide an *Initial task name*, then choose an assignment type from the list
    of options.  
    
    For our workflow example, select *Assign to asset creator* and name the task
    `Developer`. When our workflow's `Developer` task is invoked, the creator of
    the workflow's asset is assigned to it. In Liferay Portal, each Kaleo
    workflow is associated with an asset type. Later in our exercise, we'll
    associate our workflow with a Dynamic Data List (DDL). 

    ![Figure 8.6: When choosing an assignment type for a task node, you are given multiple options.](../../images/kaleo-4.png)

    ---

    ![note](../../images/tip-pen-paper.png) **Note:** You must specify a Liferay
    project as a home for the workflow definitions you create. 

    ---

3.  Click *Finish* to complete the initial setup of our workflow definition. 

<!--This type of instruction, in step 2, with heirarchical steps actually looks
bad in my Markdown plugin for Jedit; very crammed -->

Your workflow appears as a workflow diagram, letting you interact with your
workflow graphically. Graphical features and toolbars allow you to customize
your workflow definition. The *Palette* view is one of Designer's most commonly
used tools. Let's explore it next. 

### Palette and Floating Palette [](id=kaleo-palette-and-floating-palette-liferay-portal-6-2-dev-guide-07-en)

The *Palette* lets you graphically customize your workflow with nodes and
transitions. In addition, you can choose different behaviors for your mouse
pointer. 

![Figure 8.7: The palette toolbar lets you customize your workflow with additional nodes and transitions.](../../images/kaleo-6.png)

Here are your pointer options: 

- *Select*: The default pointer setting used for selecting options on the
  workflow diagram by clicking the icon. 
- *Marquee*: Used for drawing an invisible selection box around multiple icons.
  This is useful when you want to manipulate multiple nodes and/or transitions
  on the workflow diagram. 

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

Drag and drop any nodes you need onto your workflow diagram. Each node type
supports execution of scripted actions and sending notifications that can use
templates. For additional information on the node types, refer to the [Creating
New Workflow
Definitions](discover/portal/-/knowledge_base/6-2/creating-new-workflow-definitions)
chapter of *Using Liferay Portal*. 

For our ticket-process-definition workflow diagram, we have a simple
`StartNode` *State* node, followed by the `Developer` *Task* node, followed by
the `EndNode` *State* node. There are two transitions, from `StartNode` &rarr;
`Developer` and from `Developer` &rarr; `EndNode`. 

A developer should approve his fix and send it for quality assurance to *QA*,
where it must pass testing by a QA engineer. Simultaneously, it'll go to *QA
Management*, where it must be approved by a QA manager. Use a *Fork* node to
accurately depict these parallel approval tasks. 

Drag and drop a *Fork* node onto your workflow diagram. A wizard helps you
create your node. Click the green plus symbol to select new or
existing nodes to process in parallel threads. A drop-down menu gives you
options to select tasks to be done in your fork threads. In addition, a checkbox
lets you indicate whether to automatically add a corresponding join node to your
workflow.

![Figure 8.8: You can select what to fork and whether to automatically create a join node.](../../images/kaleo-7.png) 

On finishing your fork node in the wizard, Kaleo Designer places your new nodes
onto the workflow diagram's canvas. If you're not happy with the location of
your new nodes, drag them to place them where you want on your canvas. Our
ticket process workflow now looks something like this: 

![Figure 8.9: After creating the fork and join nodes, you can reposition them to your liking.](../../images/kaleo-8.png)

Why is there a red "X" in the bottom left of multiple nodes within our workflow?
These are error markers! Errors indicate something is specified incorrectly or
is missing from your nodes. If you click on an error marker, Developer Studio 
displays hints on resolving the problem. Don't worry, we'll address these error 
markers momentarily. 

Clicking on a node brings up a floating palette; use it to make quick, 
convenient customizations to a node. 

![Figure 8.10: The floating pallet makes it convenient to edit workflow nodes.](../../images/task-floating-pallet.png)

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

For our `ticket process` workflow's QA tasks, let's assign someone other than
the asset creator. Realistically, each of these tasks would be assigned to
different site roles. For simplicity, lets assign both the QA and QA Management
tasks to the same user. If you have a user in mind, specify that user.
Otherwise, create a user named "Joe Bloggs" with screen name "joe". For the user
to receive emails, he must be registered within Liferay Portal. If you haven't
registered Joe Bloggs ("joe") already, see the [Adding and Editing
Users](discover/portal/-/knowledge_base/6-2/the-users-section-of-the-control-panel)
section of *Using Liferay Portal* for instructions. To configure the user's
email, login to the user's account and visit *Control Panel* &rarr; *Server
Administration* &rarr; *Mail* for setup options. 

Select the *Change Assignments* icon from the floating palette for each QA task
node. Then, select *Assign to a specific user* from the *Choose Assignment
Type* menu and click *Next*. You have options to enter the user's *User-id*,
*Screen name*, or *Email address*. Enter the user's screen name and click
*Finish*.

![Figure 8.10: Designer lets you assign a task to a specific user of Liferay Portal.](../../images/kaleo-29.png)

Assigning the QA and QA Management task nodes resolved their error markings (no
more red "X"!). The join node's error marking won't disappear until you connect
the join node to another task. 

The XML of the ticket process workflow definition currently specifies its XML
version, encoding, and its document root element called `workflow-definition`.
Nested within the `workflow-definition` element are its name, description
(optional), version, and its nodes: 1 fork, 1 join, a start and end state.
Here's the general overview of our workflow definition: 

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

Both the the QA-related task nodes are assigned to the user with screen name
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

Lastly, the *QA* and *QA Manager* task nodes transition into the join node named
*Pass to QA Join*:

    <join>
        <name>Pass to QA Join</name>
    </join>

Now you know what the resulting XML is like for your workflow definition. You
can check your definition's source code anytime from within Liferay Studio or
your favorite XML editor. To learn more on the different workflow nodes
available to use in Liferay workflow definitions, see [Creating New Workflow
Definitions](discover/portal/-/knowledge_base/6-2/creating-new-workflow-definitions). 

Since we've been using Designer's Workflow Diagram mode, let's go over some of
its features. 

### Workflow Diagram Features [](id=kaleo-workflow-diagram-features-liferay-portal-6-2-dev-guide-07-en)

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

	![Figure 8.11: The Workflow Diagram Actions are in the toolbar in the upper right corner of the Workflow Diagram.](../../images/kaleo-toolbar-icons-01.png)

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

### Properties View and Outline View [](id=workflow-properties-view-and-outline-view-liferay-portal-6-2-dev-guide-en)

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

![Figure 8.12: The Properties view gives you multiple sub-tabs to help customize your workflow nodes.](../../images/kaleo-11.png)

Workflows frequently become too large to view in entirety on the workflow
diagram screen; the *Outline* view is a huge asset when this happens. It's a top
level view that displays your entire workflow definition, no matter how large it
becomes. In addition, it highlights what you're currently viewing on your
Workflow diagram, giving you a picture of where you're located in the broader
picture of your workflow. You can use the *Outline* view to change your position
in the Workflow Diagram by dragging the highlighted box where you'd like to
focus. 

Developer Studio's *Properties* and *Outline* views make customizing your
workflow easier than ever! Developer Studio also offers a convenient way to edit
your workflow scripts, which is our next topic. 
