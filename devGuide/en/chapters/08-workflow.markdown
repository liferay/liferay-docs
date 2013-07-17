# Designing workflows with Kaleo Designer for Java 

![EE Only Feature](../../images/ee-feature-web.png)

Liferay Portal includes a workflow engine called *Kaleo Designer for Java*.
Kaleo allows portal administrators to set up workflows for their organization's
needs; the workflow calls users to participate in processes designed for them.
Kaleo workflows, called *process definitions*, are essentially XML documents.
Kaleo supports a host of XML element types to trigger decisive actions in your
business process instances. You can fine tune the logic of your process
definition by incorporating scripts and templates. The *Kaleo Forms EE* app from
Marketplace includes Liferay's *Kaleo Workflow Designer* that lets you create
and modify portal workflows in your browser. With Kaleo Designer for Java, you
can design and publish Kaleo workflows from Liferay Developer Studio! 

![Figure 8.14: Kaleo Designer for Java gives you a powerful environment for designing, modifying, and publishing Kaleo workflows.](../../images/kaleo-designer-for-java.png)

Kaleo Designer for Java lets you incorporate back end Java development and
scripting in your workflows. Its graphical interface lets you drag and drop
nodes into your workflow. A shortcut on each node gives you easy access to the
node's XML, letting you edit its current implementation to make subtle
modifications or inject new business logic. In addition, Liferay Developer
Studio comes bundled with a Java/Groovy editor (made available by Spring
Source), giving you the same rich editing experience you're accustomed in
editing Kaleo [Groovy](http://groovy.codehaus.org/Documentation) scripts. From
the editor you can delegate workflow decisions to your custom business logic
APIs or access any of the Liferay Portal APIs. In Developer Studio, you can
leverage editors for Beanshell, Drl, JavaScript, Python, and Ruby scripting
languages. You can leverage editors for
[FreeMarker](http://freemarker.sourceforge.net/) and
[Velocity](http://velocity.apache.org/engine/releases/velocity-1.5/user-guide.html)
template languages too. Kaleo Designer for Java gives you a rich tool set for
creating/editing workflows, manipulating workflow nodes, and implementing
business logic. But there's more!

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

<!--I only lightly edited the introduction to Kaleo. I don't think I understand
enough about how it works from a development perspective; The above might be
perfectly clear to a developer, but it's not to me. -Russ -->

---

![note](../../images/tip-pen-paper.png) **Note:** The *Kaleo Forms EE* app comes
with an existing workflow designer that's used *within* Liferay Portal. It's
used to design workflow configuration and is described in the [Kaleo Forms:
Defining Business
Processes](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen11-kaleo-forms-defining-business-processes-0)
chapter of *Using Liferay Portal*. Refer to the [Using
Workflow](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/workflow-with-kal-4)
chapter of *Using Liferay Portal* if you're unfamiliar with basic Kaleo
workflow concepts or want to know how to design your workflow within Liferay
Portal. 

---

To use Kaleo Designer for Java, install the *Kaleo Forms EE* app from Liferay
Marketplace. The app includes three plugins--`kaleo-forms-portlet`,
`kaleo-designer-portlet`, and `kaleo-web` plugins.

Here's how to download and install *Kaleo Forms EE*:

1.  Go to [Liferay Marketplace](https://www.liferay.com/marketplace).

2.  Click on [EE
    Marketplace](http://www.liferay.com/marketplace/-/mp/category/12729032) in
    the left navigation area.

3.  Download and install the *Kaleo Forms EE* app.

After downloading and installing the Kaleo Forms EE application, restart the
Liferay Server and a *Kaleo Workflows* folder automatically appears underneath
the server instance in the Servers view of Developer Studio.

![Figure 8.15: In Studio's *Servers* view, your server's Kaleo Workflows folder shows workflows published on your portal.](../../images/kaleo-1.png)

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

## Creating a workflow 

Developer Studio makes it easy for you to write custom business logic that
enhances Kaleo workflows. Let's create our own workflow in Developer Studio and
highlight features from Kaleo Designer for Java (Designer) along the way.

To demonstrate Designer's features, let's create a workflow definition for a
software ticketing process. Are you ready to roll? Let's get started! 

![Figure 8.16: Check out the adventures of Liferay's mascots, Ray and Alloy, found at http://www.liferay.com/ray.](../../images/kaleo-15.png)

1.  Create your new workflow definition by going to *File* &rarr; *New* &rarr;
    *Liferay Kaleo Workflow*. Alternatively, you can select *Liferay Kaleo
    Workflow* from the toolbar button shown in the figure below. 

    ![Figure 8.17: Create a new workflow definition locally by selecting *Liferay Kaleo Workflow* from the toolbar button.](../../images/kaleo-3.png)

2.  The *Create New Kaleo Workflow File* wizard will guide you through the steps
    necessary to complete the initial setup of your new workflow definition. 

    The first window you'll see is the *Create Kaleo Workflow* form. There are
    several fields here:  

    *Project*: Specify an existing Liferay project to house your workflow definition. 

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

    You'll be directed to the *Choose Assignment Type* window next. Here you'll
    provide an *Initial task name*, then choose an assignment type from the list
    of options.  
    
    For our workflow example, select *Assign to asset creator* and name the task
    `Developer`. When our workflow's `Developer` task is invoked, the creator of
    the workflow's asset is assigned to it. In Liferay Portal, each Kaleo
    workflow is associated with an asset type. Later in our exercise, we'll
    associate our workflow with a Dynamic Data List (DDL). 

    ![Figure 8.18: When choosing an assignment type for a task node, you are given multiple options.](../../images/kaleo-4.png)

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

### Palette and Floating Palette 

The *Palette* lets you graphically customize your workflow with nodes and
transitions. In addition, you can choose different behaviors for your mouse
pointer. 

![Figure 8.19: The palette toolbar lets you customize your workflow with additional nodes and transitions.](../../images/kaleo-6.png)

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
- *State*: Represents a workflow state. 
- *Task*: Represents a task that can be assigned. 

Drag and drop any nodes you need onto your workflow diagram. Each node type
supports execution of scripted actions and sending notifications that can use
templates. For additional information on the node types, refer to the [Kaleo
Designer](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen06-kaleo-designer-0)
section of *Using Liferay Portal*. 

For our `ticket-process-definition` workflow diagram, we have a simple
`StartNode` *State* node, followed by the `Developer` *Task* node, followed by
the `EndNode` *State* node. There are two transitions, from `StartNode` &rarr;
`Developer` and from `Developer` &rarr; `EndNode`. 

We want a developer to approve his fix and send it for quality assurance to
*QA*, where it must pass testing by a QA engineer. Then it'll go to *QA
Management*, where it must be approved by a QA manager. Let's use a *Fork* node
to accurately depict these parallel approval tasks. 

Drag and drop a *Fork* node onto your workflow diagram. A wizard helps you create
your node. Click the green plus symbol to select new or
existing nodes to process in parallel threads. A drop-down menu gives you
options to select tasks to be done in your fork threads. In addition, a checkbox
lets you indicate whether to automatically add a corresponding join node to your
workflow.

![Figure 8.20: You can select what to fork and whether to automatically create a join node.](../../images/kaleo-7.png) 

On finishing your fork node in the wizard, Kaleo Designer places your new nodes
onto the workflow diagram's canvas. If you're not happy with the location of
your new nodes, drag them to place them where you want on your canvas. Our
ticket process workflow now looks something like this: 

![Figure 8.21: After creating the fork and join nodes, you can reposition them to your liking.](../../images/kaleo-8.png)

Why is there a red "X" in the bottom left of multiple nodes within our workflow?
These are error markers! Errors indicate something is specified incorrectly or is
missing from your nodes. Hover your pointer over each error marker; Developer
Studio displays hints on resolving each problem. Don't worry, we'll address
these error markers momentarily. 

Hovering your pointer over a node brings up a floating palette; use it to make
quick, convenient customizations to a node. 

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
registered Joe Bloggs ("joe") already, see the [Adding
users](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen12-adding-users-0)
section of *Using Liferay Portal* for instructions. To configure the user's
email, login to the user's account and visit *Control Panel* &rarr; *Server
Administration* &rarr; *Mail* for setup options. 

Select the *Change Assignments* icon from the floating palette for each QA task
node. Then, select *Assign to a specific user* from the *Choose Assignment
Type* menu and click *Next*. You have options to enter the user's *User-id*,
*Screen name*, or *Email address*. Enter the user's screen name and click
*Finish*.

![Figure 8.22: Designer lets you assign a task to a specific user of Liferay Portal.](../../images/kaleo-29.png)

Assigning the QA and QA Management task nodes resolved their error markings (no
more red "X"!). The join node's error marking won't disappear until you connect
the join node to another task. 

While we're using Designer's Workflow Diagram, let's go over some of its
features. 

### Workflow Diagram Features 

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

### Properties View and Outline View 

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

![Figure 8.23: The Properties view gives you multiple sub-tabs to help customize your workflow nodes.](../../images/kaleo-11.png)

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

## Using Workflow Scripts 

You can use Developer Studio to edit workflow scripts; it recognizes multiple
script languages, so you can choose one you're comfortable with. Developer
Studio provides you many script editing features so you can quickly implement
business logic in your workflows. 

Developer Studio supports several script languages: 

- Beanshell 
- Drl 
- Groovy 
- JavaScript 
- Python 
- Ruby 

Let's dive back into our `software ticket` workflow definition and create a
script. It's not guaranteed that every ticket submitted has a resolution. If the
issue was due to a silly user error, there's no reason to change the product. In
such cases the developer will resolve the ticket and indicate there is no
resolution in the product (i.e., no modifications are were made). Regardless,
we'll have the developer fill out an online Dynamic Data List (DDL) form to
initiate a workflow for each of her tickets. Once the workflow is invoked, its
associated DDL record is accessible from our workflow's context. Let's use a
condition node to handle the ticket based on the DDL record. 

To set up the workflow process we described above, we'll need to add a
*Condition* node and two transitions. 

1.  Drag and drop a *Condition* node onto your workflow diagram. A *Create New
    Condition Node* menu should appear.

2.  Name the node *Resolution*.

3.  Choose a script language for the condition node. Select *Groovy* and you'll
    see how easy it is to embed Java code. In our Groovy script, we'll access
    the DDL record to determine whether the ticket warrants a modification to
    the product. If it does, we'll assign it to a developer via the Developer
    task node. Otherwise we'll end the workflow by transitioning to the
    workflow's EndNode.  

4.  From the *Create New Condition Node* menu, add two transitions--one to the
    *Developer* node and the other to the *EndNode* state. We'll add the
    transition to the *Developer* node first. 

    Click the green plus sign and select the transition icon from its menu. An
    entry for the transition appears in the named list of *Condition
    transitions*. 

    Click the browse icon in the entry and select the *Developer* node.

5.  Add a transition to the *EndNode* state in the same manner that added the
    transition to the *Developer* node in the previous step.

6.  Click *Finish*. 

<!--When I select the green plus sign I get a list to choose from; Existing
Node, New Condition, New Fork, New Join, New State, New Task. I do not see a
transition icon to select. -->

Here's a snapshot of the *Create New Condition Node* menu configured for the
ticket process workflow. 

![Figure 8.24: When creating a condition node, you can set your preferred script language, name, and condition transitions.](../../images/kaleo-12.png) 

Before adding a script to our condition node, let's make some changes to our
workflow transitions: 

- Add a transition from the *Developer* task node to the *Pass To QA* fork node.
- Add a transition from the *StartNode* state node to the *Resolution* condition
  node.
- Delete the transition that currently connects the *StartNode* state node to
  the *Developer* task node.

To add a transition from one node to another, do the following:

1.  Click the transition icon from the palette. Your pointer's icon shows as a
    plug indicating you are in *connector* mode.

2.  Select a node on your workflow diagram from which the transition will start.
    A dotted line appears with one end connected to the selected node and the
    other end following your pointer.

3.  Select a node to which the transition will end. The dotted line changes into
    a fixed ray with the arrow pointing to the transition's end node.

4.  To exit connector mode, hit *Escape* on your keyboard and click your pointer
    at empty space in your workflow diagram.

You may notice the error marking on the condition node. When you hover over
the marking, a hint indicates a script must be specified for the node.

Open the script editor for your *Resolution* condition node by doing one of the
following: 

- Select the node and click *Edit Script* from the *Script* tab of the
  Properties view. 
- Click the *Edit Script* tool from the node's floating palette. 
- Right-click the node and select *Edit Script*. 

We set our default script language to Groovy, so the Java/Groovy editor appears.
To learn more about the Groovy editor, see the [Groovy User
Guide](http://groovy.codehaus.org/User+Guide). If you set the script language to
another language, the editor for that specific language appears. The editor runs
in the context of editing the specific node you selected. Anything you type in
the script editor for this condition node is written inside the
`<script></script>` tags for the `<condition/>` element that represents our node
in our workflow definition's XML file (in our case,
`ticket-process-definition.xml`). 

---

![note](../../images/tip-pen-paper.png) **Note:** Developer Studio lets you use
multiple script editors even while modifying the same workflow definition XML
file. 

---

The *Palette* view is much different from when you were working in the workflow
diagram; it's associated with your Java/Groovy script editor now and includes
folders containing the following entities for your script: 

- Context Variables 
- Dynamic Data Lists 
- Roles 
- Scripts 
- Status Updates 

You can expand and collapse a folder by clicking its name bar. 

Here's a snapshot of the palette with the *Context Variables* folder open:

![Figure 8.25: Each script editor is associated with a palette that contains helpful snippets of code you can insert.](../../images/kaleo-14.png) 

Drag and drop an entity from your palette onto your Java/Groovy editor and code
representing that entity appears in the editor. The inserted code is free of
compile errors and warnings because the editor is running in the context of
Liferay Portal. All of the Liferay Portal APIs are available to you. In the
editor you can invoke code-assist and access built in Kaleo workflow variables. 

Let's get the DDL record that's being worked on in our workflow process. We'll
need the `serviceContext` entity, under `Context Variables` in the palette. To
learn more about Service Context and its parameters, see the [Service
Context](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/service-conte-1)
section of this guide.

Let's use Designer's palette features in conjunction with our Java/Groovy editor
to implement our condition: 

1.  Drag and drop the *serviceContext* entity from the *Context Variables*
    folder in your palette onto the script editor. This grabs the Service
    Context. 

2.  Drag and drop the *ddlRecord* entity from the *Dynamic Data Lists* folder in
    your palette onto the script editor. We get the `ddlRecordId` from the
    Service Context and use that ID to look up the DDL record via Liferay
    service utility `DDLRecordLocalServiceUtil`.

Append the following Java code to the `DDLRecordLocalServiceUtil` script: 

	Field field = ddlRecord.getField("status");

	String status = GetterUtil.getString(field.getValue());
	if (status.contains("not")) {
		returnValue = "No"
	}
	else {
		returnValue = "Yes"
	}


We're pulling out the status from the DDL record and returning a value
indicating "Yes" to continue fixing the ticket issue or "No" to transition to
the workflow's end state. 

Add the following to the script's imports to finsih things up: 

	import com.liferay.portlet.dynamicdatamapping.storage.Field;

Now the script accurately implements the condition logic we want. As a reminder,
all of the code was injected into our workflow's XML file within the
`<condition/>` element that represents our condition node. 

---

 ![note](../../images/tip-pen-paper.png) **Note:** Make sure you correctly name
 the transitions stemming from the condition node. The "No" transition should
 point to the EndNode, while the "Yes" transition should point to the Developer.
 If the condition script's return values don't match the transition names, the
 workflow engine won't know which transition to use. 

---

Here's a snapshot of our current ticket process workflow after inserting the
condition node: 

![Figure 8.26: The ticket process workflow after inserting the condition node.](../../images/kaleo-30.png)

We need to create a valid DDL record to invoke this workflow properly. If you're
thinking "How do we set up a DDL record?" or "How does this DDL record thingy
work?", you're on the right track. If you're jumping up and down screaming
"Liferay is da bomb!" We welcome your reaction, too. Regardless, we'll address
DDLs soon in the *Configuring a DDL record* section of this chapter. 

Next let's create a custom notification and write a template for it using a
template editor. 

## Leveraging Template Editors for Notifications 

Designer lets you leverage FreeMarker and Velocity editors to customize
templates for your workflow notifications. A FreeMarker editor comes bundled
with Developer Studio. To edit Velocity templates, you'll need to install an
editor. Don't worry, Developer Studio makes it easy, and we'll show you how. 

### Add-on: Install Velocity Editor Support 

Liferay Developer Studio comes with additional add-on support for editing
Velocity template notifications in the Kaleo Designer for Java. To use this
feature go to the Developer Studio 1.6 customer page
[http://www.liferay.com/group/customer/products/developer-studio/1.6](http://www.liferay.com/group/customer/products/developer-studio/1.6),
navigate to the *Add-on: Install Velocity Editor Support* section, and download
the Liferay Developer Studio Velocity update site zip file and install it. You
can follow these steps: 

1.  From Liferay Developer Studio, Click *Help* &rarr; *Install New Software...*

2.  Click *Add...*

3.  Click *Archive*.

4.  Browse to the downloaded update site zip file and select it.

5.  Click *OK* to close the *Add Repository* dialog.

6.  Expand the added repository and check the *Liferay Developer Studio
    Velocity* feature.

7.  Uncheck the option *Contact all update sites...*

8.  Click *Next* to progress through the wizard.

9.  After reading and accepting the license agreement, click *Finish*.

10. Restart Liferay Developer Studio to complete the install.

When you edit a notification template set to the Velocity type, the template
opens in the Velocity editor (a small "v" icon is in the left side of the
editor's tab). 

### Creating Notifications 

To access the template editors, click on the node of your choice and select the
*Notifications* sub-tab in the *Properties* view. Create a new notification by
clicking the green "plus" symbol. 

![Figure 8.27: When creating a notification, Developer Studio offers several options, like choosing  a template language.](../../images/kaleo-16.png)

There are several fields to fill in for your notification: 

- **Name**
- **Template Language**
- **Execution Type**
- **Notification Type**
- **Notification Transports**
- **Addresses**

<!--This is a field list, so bold, right? -->

Click the pencil icon to open the editor associated with your notification's
template language. Like the script editor, the template editor's *Palette* view
lists entities that you can drag and drop onto your workflow diagram. 

Because Developer Studio lets you leverage full featured template editors like
FreeMarker or Velocity, content-assist is available for you to use right away.
For example, if you are using the FreeMarker editor, content-assist suggests
FreeMarker functions when you are editing your notification template. In
addition, when you're doing a FreeMarker variable insertion, the editor gives
you all the available variables that are a part of the Kaleo workflow. You can
visit the documentation pages for [FreeMarker](http://freemarker.org/docs/) and
[Velocity](http://velocity.apache.org/engine/releases/velocity-1.5/user-guide.html)
for more information on the variables and functions available in these template
languages.

---

![note](../../images/tip-pen-paper.png) **Note:** Similar to the bundled script
editors, Developer Studio lets you use FreeMarker and Velocity template editors
to customize notifications in your workflow definition.

---

Let's continue editing our ticket process workflow. After completion of the QA
task assignments, our Project Management team should be notified. To set up a
notifcation email, we'll add a new task node that transitions from our join
node. This new task node will hold our email notification. Typically, we'd
assign this task to a project management role and email it to the project
management team's email alias. For demonstration purposes, we'll use "Joe Blogs"
for both purposes. As mentioned previously, you can specify an existing user
that has an email or create a user with screen name "joe" having your email
address. This process is similar to how we assigned our previous task nodes. 

1.  Drag a new *Task* node onto your workflow diagram. 

2.  Name the new node *Project Management* and select *Assign to a specific
    user*. 

3.  Click *Next*. 

4.  Enter *Screen name* "joe". 

5.  Click *Finish*. 

Now we just need to incorporate the Project Management node into our
workflow process. Add a transition named *Passed QA* from our join node to the
Project Management node. Lastly, add a transition named *Completed* from our
Project Management node to our *EndNode*. Here's an updated screenshot of what
your workflow diagram should look like: 

![Figure 8.28: Our workflow diagram is busy now; but we're not finished yet!](../../images/kaleo-31.png) 

Let's create our email notification for our Project Management task node next.
Click on the Project Management node and select *Notifications* in the
Properties window. 

To create the email notification, follow these steps: 

1.  Click the green "plus" symbol to create a new notification. 

2.  In the **Name** text field, enter "ticket process email".

3.  Select *FreeMarker* from the **Template Language** drop-down menu.

4.  Select *On entry* from the **Execution type** drop-down menu.

5.  Select *email* under **Notification transports**.

<!--Bolded field items. -->

Now open the FreeMarker template editor by clicking the pencil icon beneath the
green "plus" symbol. 

Insert FreeMarker code into the FreeMarker editor to specify your email
notification template. We've provided an example email notification code snippet
written in FreeMarker. 

    <#assign comments = taskComments!"">
    <#assign portalURL = serviceContext.portalURL!"">
    <#assign wTasksURL = portalURL+"/group/control_panel/manage?p_p_id=153&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&doAsGroupId="+groupId+"&refererPlid=">

    <!-- email body -->
    <p> There is a new submission of ${entryType} waiting for review in the workflow. </p>

    <!-- personal message to assignee -->
    <p> Please review the code waiting for you in your workflow tasks.
    <#if comments != "" > <br/> Assignment comment says: <strong>${comments}</strong> </#if>
    </p>
    <p> <a href="${wTasksURL}">Click here</a> to see workflow tasks assigned to you. </p>

    <!-- signature -->
    <p>Sincerely,<br /><strong>Liferay Portal Workflow</strong></p>

The snapshot below illustrates what the snippet sends to the configured email
recipient. 

![Figure 8.29: This is how the email notification will appear when it's received.](../../images/kaleo-43.png) 

Your email notification is set up! Now, when the Project Management task node is
activated in the workflow, the specified user (i.e. *joe*) will receive the
notification email, all dressed up with your FreeMarker template (you might say
it's all dressed up with somewhere to go). 

With template editors, customizing your notification templates is easier than
ever. FreeMarker comes bundled with Developer Studio so it's obviously the
simplest solution, but you can create Velocity templates just as easy by using
the Velocity editor you installed. 

In the next section you'll see a list of workflow and service context content
you can use when creating a customized script or template. 

### Workflow Context and Service Context Variables 

A context variable provides a uniform variable to insert into your templates and
scripts. When executed, a context variable is automatically deleted and replaced
with the value pertaining to that key. When you create notifications for a
workflow, assign Liferay Portal context variables for a cleaner and more
efficient process. With context variables, your notifications become more
customizable, rather than following the same format for every recipient. The
context variables you declare in your notifications refer to your Liferay
instance and the values it holds for your declarations. 

Below you'll see tables listing numerous context variables and service context
content. The context variables are the first table, followed by the service
context content for web content, blog entries, and message board messages. We've
separated service context content from the workflow context variables because
service context keys depend on asset type, while context variables don't. Also,
note the asterisks (`*`); they're used to flag context variables that depend on
workflow activity. 


***Workflow* Context Variables**

 Key | Type | Description |
---- | ---- | ----------- |
 `companyId` | &nbsp;&nbsp;java.lang.String&nbsp;&nbsp;&nbsp; | Primary key of the company |          
 `entryClassName` | &nbsp;&nbsp;java.lang.String | Class name for entry used by the task (e.g. com.liferay.portlet.journal.model.JournalArticle) |
 `entryClassPK` | &nbsp;&nbsp;java.lang.String | Primary key of the entry class |
 `entryType` | &nbsp;&nbsp;java.lang.String | Type of entry used by the task (e.g. Web Content, Blog Entry, MB Message) |
 `groupId` | &nbsp;&nbsp;java.lang.string | Primary key of the assigned group |
 `taskComments*` | &nbsp;&nbsp;java.lang.String | Workflow comments assigned to the task |
 `taskName*` | &nbsp;&nbsp;java.lang.String | Workflow task that activates the notification (e.g. review) |
 `transitionName*` | &nbsp;&nbsp;java.lang.String | Name of transition pointing to the task (e.g. approve) |
 `userId` | &nbsp;&nbsp;java.lang.String | Primary key of the assigned user |
---

***Web Content* Service Context Variables - obtain via key serviceContext**

 Key | Type | Description |
---- | ---- | ----------- |
 `articleId` | &nbsp;&nbsp;java.lang.String&nbsp;&nbsp;&nbsp; | Primary key of the web content |
 `articleURL` | &nbsp;&nbsp;java.lang.String | Link to the web content in maximized mode |
 `assetLinkEntryIds` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset entries linked to the web content |
 `assetLinksSearchContainerPrimaryKeys` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset link search container |
 `assetTagNames` | &nbsp;&nbsp;java.lang.String | Tag names applied the asset |
 `autoArticleId` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether an article ID is generated (e.g. false) |
 `classNameId` | &nbsp;&nbsp;java.lang.String | Primary key of the class name used by the task |
 `classPK` | &nbsp;&nbsp;java.lang.String | Primary key of the model entity |
 `content` | &nbsp;&nbsp;java.lang.String | Content of the web content |
 `defaultLanguageId` | &nbsp;&nbsp;java.lang.String | Primary key of the default language (e.g. en_US) |
 `description_en_US` | &nbsp;&nbsp;java.lang.String | Description of the web content (in English) |
 `displayDateDay` | &nbsp;&nbsp;java.lang.String | Calendar day the web content is set to display (e.g. 12) |
 `displayDateHour` | &nbsp;&nbsp;java.lang.String | Hour the web content is set to display (e.g. 4) |
 `displayDateMinute` | &nbsp;&nbsp;java.lang.String | Minute the web content is set to display (e.g. 26) |
 `displayDateMonth` | &nbsp;&nbsp;java.lang.String | Month the web content is set to display (e.g. 5) |
 `displayDateYear` | &nbsp;&nbsp;java.lang.String | Year the web content is set to display (e.g. 2012) |
 `doAsGroupId` | &nbsp;&nbsp;java.lang.String | Primary key of the organization associated with the web content |
 `folderId` | &nbsp;&nbsp;java.lang.String | Primary key of the web content's folder |
 `indexable` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the web content is searchable (e.g. true) |
 `indexableCheckbox` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the *Searchable* checkbox is checked (e.g. false) |
 `inputPermissionsShowOptions` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether permission options are viewable (e.g. true) |
 `inputPermissionsViewRole` | &nbsp;&nbsp;java.lang.String | Role type that has permission to view web content (e.g. Site Member) |
 `languageId` | &nbsp;&nbsp;java.lang.String | Primary key of the selected language (e.g. en_US) |
 `localized` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the *Localizable* checkbox is selected (e.g. false) |
 `neverExpire` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the web content is set to expire (e.g. true) |
 `neverExpireCheckbox` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the *Never Auto Expire* checkbox is checked (e.g. false) |
 `neverReview` | &nbsp;&nbsp;java.lang.String |  Boolean variable indicating whether the web content is set to review (e.g. true) |
 `neverReviewCheckbox` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the *Never Review* checkbox is checked (e.g. false) |
 `refererPlid` | &nbsp;&nbsp;java.lang.String | Primary key of the page hosting the web content |
 `smallImage` | &nbsp;&nbsp;java.lang.String | Indicates whether a small image is being used (e.g. on) |
 `smallImageURL` | &nbsp;&nbsp;java.lang.String | URL for the web content's attached image |
 `structureDescription` | &nbsp;&nbsp;java.lang.String | Description of the configured structure |
 `structureId` | &nbsp;&nbsp;java.lang.String | Primary key of the configured structure |
 `structureName` | &nbsp;&nbsp;java.lang.String | Name of the configured structure |
 `structureXSD` | &nbsp;&nbsp;java.lang.String | The XML schema definition used for the configured structure |
 `templateId` | &nbsp;&nbsp;java.lang.String | Primary key of the configured template |
 `title_en_US` | &nbsp;&nbsp;java.lang.String | Title of the web content (in English) |
 `type` | &nbsp;&nbsp;java.lang.String | Categorization type associated with the web content  (e.g. Press Release) |
 `variableName` | &nbsp;&nbsp;java.lang.String | Custom variable name set for the web content's configured structure (e.g. Content) |
 `version` | &nbsp;&nbsp;java.lang.String | Current version of the web content (e.g. 1.0) |
 `workflowAction` | &nbsp;&nbsp;java.lang.String | Numerical value for the workflow action in progress (e.g. 1) |
---

***Blog Entry* Service Context Variables - obtain via key serviceContext**

 Key | Type | Description |
---- | ---- | ----------- |
 `assetLinkEntryIds` | &nbsp;&nbsp;java.lang.String&nbsp;&nbsp;&nbsp; | Primary keys of the asset entries linked to the blog entry |
 `assetLinksSearchContainerPrimaryKeys` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset entries linked to the blog entry |
 `assetTagNames` | &nbsp;&nbsp;java.lang.String | Tag names applied the asset (e.g. history, news, programming) |
 `attachments` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating if blog entry has any attachments (e.g. true) |
 `content` | &nbsp;&nbsp;java.lang.String | Content of the blog entry |
 `description` | &nbsp;&nbsp;java.lang.String | Description of the blog entry (e.g. The comparison between two Fortune 500 companies) |
 `displayDateAmPm` | &nbsp;&nbsp;java.lang.String | Time "period" (based on the 12-hour clock) the blog entry is set to display (AM=0, PM=1) |
 `displayDateDay` | &nbsp;&nbsp;java.lang.String | Calendar day the blog entry is set to display (e.g. 3) |
 `displayDateHour` | &nbsp;&nbsp;java.lang.String | Hour the blog entry is set to display (e.g. 26) |
 `displayDateMinute` | &nbsp;&nbsp;java.lang.String | Minute the blog entry is set to display (e.g. 32) |
 `displayDateMonth` | &nbsp;&nbsp;java.lang.String | Month the blog entry is set to display (e.g. 8) |
 `displayDateYear` | &nbsp;&nbsp;java.lang.String | Year the blog entry is set to display (e.g 2012) |
 `doAsGroupId` | &nbsp;&nbsp;java.lang.String | Primary key of the organization associated with the blog entry |
 `editor` | &nbsp;&nbsp;java.lang.String | Content of the blog entry (equivalent to the `content` value) |
 `entryId` | &nbsp;&nbsp;java.lang.String | Primary key of the blog entry |
 `refererPlid` | &nbsp;&nbsp;java.lang.String | Primary key of the page hosting the blog entry |
 `smallImage` | &nbsp;&nbsp;java.lang.String | Indicates whether a small image is being used (e.g. on) |
 `smallImageURL` | &nbsp;&nbsp;java.lang.String | URL for the blog entry's attached image |
 `title` | &nbsp;&nbsp;java.lang.String | Title of the blog entry (e.g. My Blog Entry) |
 `workflowAction` | &nbsp;&nbsp;java.lang.String | Numerical value for the workflow action in progress (e.g. 2) |
---

***Message Board Message* Service Context Variables - obtain via key serviceContext**

 Key | Type | Description |
---- | ---- | ----------- |
 `anonymous` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating if message is anonymous (e.g. true) |
 `assetLinkEntryIds` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset entries linked to the message |
 `assetLinksSearchContainerPrimaryKeys` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset link search container |
 `assetTagNames` | &nbsp;&nbsp;java.lang.String | Tag names applied the asset (e.g. sea, sailing, swimming) |
 `attachments` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the message has any attachments (e.g. false) |
 `body` | &nbsp;&nbsp;java.lang.String | Content of the message |
 `editor` | &nbsp;&nbsp;java.lang.String | Content of the message (equivalent to the `body` value) |
 `mbCategoryId` | &nbsp;&nbsp;java.lang.String | Primary key of the message's category |
 `messageId` | &nbsp;&nbsp;java.lang.String | Primary key of the message |
 `parentMessageId` | &nbsp;&nbsp;java.lang.String | Primary key of the message's parent message |
 `preview` | &nbsp;&nbsp;java.lang.Boolean&nbsp;&nbsp; | Boolean variable indicating if message is available to preview (e.g. true) |
 `question` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating if message is marked as a question (e.g. false) |
 `subject` | &nbsp;&nbsp;java.lang.String | Subject line of the message (e.g. My MB Subject) |
 `threadId` | &nbsp;&nbsp;java.lang.String | Primary key of the message board thread hosting the message |
 `workflowAction` | &nbsp;&nbsp;java.lang.String | Numerical value for the workflow action in progress (e.g. 3) |
---

<!--Rich says change these to HTML. -Russ -->

Next you'll learn how to view your workflow definition XML file in Developer
Studio. 

## Viewing workflow definition XML source 

The workflow diagram view of your workflow definition is convenient; sometimes
you'll also want to edit and review your workflow definition's XML source code.
Selecting the *Source* tab next to the *Diagram* tab in the main editor view
takes you to the XML, and you can easily switch contexts as you need. 

![Figure 8.30: Feel free to switch between *Diagram* and *Source* modes of your workflow editor in Kaleo Designer for Java.](../../images/kaleo-20.png)

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

---

![note](../../images/tip-pen-paper.png) **Note:** *Using Liferay Portal* section
[Creating new workflow
definitions](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/creating-process-definitio-1)
explains how to define workflows via XML.

---

With the *Source* view, you can keep track of your edits while using Developer
Studio's powerful graphical features. 

Let's save your workflow definition and publish it to your Liferay server. 

## Publishing workflows to the server 

After you create a new workflow or modify an existing one, you'll have to
publish it onto your Liferay server before your site's members can use it. Let's
publish the `ticket process` workflow definition onto your Liferay server. 

To publish your `ticket process` workflow definition: 

1.  Right-click the *Kaleo Workflows* folder listed under your Liferay server in
    the *Servers* view. 

2.  Select *Upload new workflow...* to bring up the *workspace files* browser. 

3.  Browse for your workflow definition file and select it for publishing. 

Alternatively, you can publish your new workflow XML file by dragging it from
your *Package Explorer* view onto your Liferay server in your *Servers* view. 

---

![note](../../images/tip-pen-paper.png) **Note:** To update your Kaleo Workflows
folder with the latest workflow versions created or modified in Liferay Portal
using Kaleo Workflow Designer from the *Kaleo Forms* portlet, right click *Kaleo
Workflows* under your server and select *Refresh*. 

---

You probably understand why it's necessary to publish new workflow definitions
onto the Liferay server; it might be less clear why you need to republish
existing workflow definitions that you've modified. When you save changes to a
workflow, they're not immediately available in your portal; it's still using the
previous version of the workflow. Developer Studio saves the workflow as a
draft, so you can work on multiple iterations of the same version until you're
ready to publish your changes. Once you publish, you now have a new version to
make changes on top of. For example, you might be working on *Version 1* of your
workflow definition; as you make changes, you save them in multiple drafts. When
you are finished with all of your changes, you publish the workflow triggering
creation of a new version (*Version 2*) of the workflow. The new version is made
available on the server immediately for your workflow administrators to
associate with asset publications, DDLs, and with Kaleo Forms. 

Unlike other Java editors, Developer Studio lets you test your workflow
definition as a draft. You can also publish your workflow definition straight to
Liferay Portal for quick and easy configuration. 

Are you ready for our *Kaleo Designer for Java* finale? You just have to
activate the workflow in your Liferay Portal, then we'll set up the DDL record
and try out our new workflow. Let's activate! 

## Using workflows in Liferay Portal 

Let's put some finishing touches on your workflow and test drive it in Liferay
Portal. Before you can use a workflow definition, it must be activated in your
Liferay Portal. Navigate to the *Control Panel* and select *Workflow*. Then, in
the *Definitions* tab, click on the *Actions* button and select *Activate*. 

A workflow definition can be associated with publication of an asset or DDL
record. Let's associate our ticket process workflow definition with a DDL record
that lets a developer indicate whether she'll fix a ticket's issue. You can
find detailed instructions for creating a DDL by visiting the section [Defining
data
types](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/defining-data-typ-1)
in *Using Liferay Portal*. We'll demonstrate how easy it is. 

## Using DDLs with workflows 

Let's associate our workflow with a Dynamic Data List (DDL) record. To learn
more about DDLs, visit [Using Web Forms and Dynamic Data
Lists](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/dynamic-data-lists-in-lifer-1)
in *Using Liferay Portal*. 

First we'll create a data definition that lets the user select a status value. 

1.  In Liferay Portal, go to *Control Panel* &rarr; *Dynamic Data Lists*.

2.  Click the *Manage Data Definitions* link, then *Add* a new data definition.

3.  In the *Fields* tab, drag and drop the *Select* field onto the canvas. 

    ![Figure 8.31: Creating data definitions for your DDL is a snap with Liferay's graphical drag and drop interface.](../../images/kaleo-35.png)

4.  In the *Settings* tab, double click the *Name* property to open the property
    editor--enter *status* as the value. 

5.  Edit the *Options* setting; give your *status* field option values of `fix`
    with label "Fix" and `not` with label "Do not fix". 

6.  Click *Save*. 

Recall the code we inserted for our condition node:

    Field field = ddlRecord.getField("status");

    String status = GetterUtil.getString(field.getValue());
    if (status.contains("not")) {
        returnValue = "No"
    }
    else {
        returnValue = "Yes"
    }

In our code, the `getField()` method ingests the value of our DDL field named
"status". When the script is invoked, if the value for the status field is
`not`, the value *No* is returned and our workflow transitions to our EndNode
state. Otherwise, the workflow transitions to our Developer task node. 

After you create the data definition, make sure you select the ticket process
workflow for our new Kaleo Forms process to use. *Save* the DDL. 

Now our DDL is set for use inside our ticket process workflow! Let's use the
Kaleo Forms portlet to test our new workflow definition! 

### Using Kaleo Forms to run workflows 

Let's use the Kaleo Forms portlet to invoke our workflow from Liferay Portal.
Deploy the Kaleo Forms portlet to your portal and add it to a page on your site.
You can learn how to use Kaleo Forms in the [Kaleo
Forms](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen06-kaleo-forms-0)
section of *Using Liferay Portal*; we'll demonstrate its use here by using it
with our ticket process workflow. 

1.  Create a new *Process* in Kaleo Forms; name it *Ticket Process*. 

2.  Select the *status* data definition we created earlier. 

3.  Add an *Initial Form* based on our *status* data definition. 

4.  Select our ticket process workflow. 

5.  Leave *Workflow Task Forms* unassigned for our demonstration. 

![Figure 8.32: To test the ticket process workflow, create a new Kaleo Forms *Process*--provide a name, entry definition, and workflow.](../../images/kaleo-41.png)

After saving, select the *Summary* tab in Kaleo Forms, click the *Submit New*
button, and select *Ticket Process*.

Now you can interact with the DDL and progress throughout the ticket process
using Kaleo Forms. Remember to sign in as Joe Bloggs to access the tasks
assigned to him. Joe Bloggs should also receive an email when the Project
Management node is activated. 

You successfully created a workflow definition and created a workflow process
within Liferay Portal! You're officially a workflow master (you can get your
framed certification at the front desk on your way out--tell them we sent you)! 

Have you noticed that there's a lot of depth to Liferay IDE? You can easily come
across difficult questions and run into very specific problems, but someone else
might have already solved your issue or answered your question. So where would
you go to find out? Don't reinvent the wheel, visit the [Liferay IDE Community
page](http://www.liferay.com/community/liferay-projects/liferay-ide/overview)!
On the *Forums* page, you can look up resolutions to specific errors and ask
questions. Be sure to fully describe any problems you have to ensure you get a
working answer. You can even track known issues from the *Issue Tracker* page. 

## Summary 

In this chapter we looked at managing workflows using Kaleo Designer for Java in
Liferay Developer Studio. With Developer Studio's dynamic workflow diagram, you
can visualize your workflow and create steps to ensure a successful business
process. The script and template editors give you a convenient workspace to
further enhance your workflow development environment. 

Because Developer Studio has access to your custom business logic APIs and
Liferay Portal APIs, it's simple to customize your workflow definition. To get
your workflows running in Liferay, you can publish directly to your existing
Liferay server; the configuration process is quick and easy. With all of its
features, the Kaleo Designer for Java in Developer Studio is a powerful yet
simple tool. 



