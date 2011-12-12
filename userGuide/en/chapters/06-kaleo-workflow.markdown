# Workflow with Kaleo

Liferay Portal includes a workflow engine called Kaleo. In Greek, this word means "called ones," which is appropriate for a workflow engine that calls users to participate in a process that has been designed for them.

Kaleo workflow allows a user to define any number of simple to complex business processes/workflows, deploy them, and manage them through a portal interface. Those processes have knowledge of users, groups, and roles. You don't have to write a single line of code to accomplish this: all you have to do is create a single XML document. And if you're a Liferay EE customer, you get a graphical workflow designer which gives you a point and click interface to create workflows. 

To better understand how to use Kaleo Workflow, this chapter covers: 

-	Installation
-	Workflow basics
-	Creating workflow definitions
-	How users interact with workflow
-	Kaleo workflow designer

Once we're done, you should be familiar with how Liferay's Kaleo workflow is used make sure all content has been approved before it is published to your portal. 

## Installation

Liferay's Kaleo workflow engine ships with CE versions of Liferay. If you have EE or if you uninstalled it, the plugin can be installed through the Liferay marketplace. The name is `kaleo-web`, and you'll find it in the list of web plugins. Installing the plugin adds a number of new options to the control panel: 

-   My Workflow Tasks
-   Workflow Configuration
-   My Submissions
-   Workflow

There is one workflow that comes bundled with the `kaleo-web` plugin: Single Approver Workflow. This workflow requires one approval before an asset can be published. One of the conveniences of using Liferay's workflow engine is that any roles that are specified in the workflow definition are created automatically when the definition is deployed. This provides a level of integration with the portal that third party engines cannot match. The Single Approver Workflow contains three roles each with different scopes. The scope of each role can be deduced by their names: Site Content Reviewer, Organization Content Reviewer, and Portal Content Reviewer. 

Let's jump right in and create a workflow process definition. 

## Creating process definitions

A Kaleo workflow, called a *process definition*, is defined in an XML file and is executed by users on the portal. You can create as many different workflow definitions as need to manage the work done on your portal. You can define new user roles in the workflow to manage the approval process or use roles that already exist in your portal.

The XML file has several parts which define the workflow. To get an idea of how this works, we'll examine the default `single-approver-definition.xml` file which is included in the the Liferay Kaleo plugin.

The key parts of the workflow definition are the asset that's running through the workflow, the nodes of the workflow, and the transitions between nodes. The asset is any kind of asset registered in Liferay: web content, wiki articles, message board threads, and more. Developers can create their own assets as well to be used with workflow (see *Liferay in Action* or *Liferay Developer's Guide* for more information). Nodes represent stages of the workflow, and there are several types. Transitions occur between nodes, and indicate what the next node should be. 

Think of workflow as a state machine made up of nodes. A node can be a state, a task, a condition, a fork, a join, or a timer. Transitions are used to move from one node to another. Each type of node has different properties. For example, states execute actions automatically and require no user input. Tasks block until user input completes a transition to another state. The transition then moves the workflow to the next task or state. This cycle continues until the end Approved state is reached. For example, you could create a workflow which goes through two approvers. Initiating the workflow puts it in the In Review state, and then transitions to a task which requires user input. Users approve or reject the asset as part of the task. When the first user approves the workflow, a condition checks to see if there are two approvals. Since there is only one, workflow transitions back to the task. When the second user approves the workflow, the condition finds there are two approvers, and it triggers a different transition to the Approved state. 

Let's look in detail at how you'd create a workflow using a single approver.

### Starting a workflow definition

Below is a diagram of a single approver workflow definition. It has only two tasks and two states.  

![Figure 6.x: The default single approver workflow. Arrows represent transitions, and boxes represent states and tasks.](../../images/kaleo-workflow-single-approver.png)

First you define the schema. For Liferay workflows using Kaleo, `liferay-worklow-definition-6_1_0.xsd` should be your schema. You can find this schema in the `definitions` folder of the Liferay source or a good XML editor can cache it from Liferay's web site.

    <workflow-definition
	xmlns="urn:liferay.com:liferay-workflow_6.1.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="urn:liferay.com:liferay-workflow_6.1.0 
	http://www.liferay.com/dtd/liferay-workflow-definition_6_1_0.xsd"
    >

Next you define a name and description for the workflow. This appears in the control panel when users choose and configure workflows.

    <name>Single Approver</name>
    <description>A single approver can approve a workflow
    content.</description>
    <version>1</version>

After that, you define your initial state. 

### Creating an initial state

In this case, the state is simply that the asset has been created. States can contain actions and transitions. Actions can contain scripts. You can specify the language of the script with the `<script-language>` tag. Scripts can be written in Groovy, JavaScript, Ruby, or Python (See chapter 13: Liferay's Script Engine for more information on leveraging scipts in workflow). For a state, the action is triggered automatically, and then executes a transition. Transitions move you to a new state or task.

    <state>
        <name>created</name>
        <initial>true</initial>

From the initial state, you transition to a new task, where further processing is blocked so the asset can be reviewed.

        <transitions>
            <transition>
            <name>review</name>
            <target>review</target>
            <default>true</default>
            </transition>
        </transitions>
    </state>

The next step is to create a task. 

### Creating tasks

The task has several parts, and is the most complex part of the definition. Tasks are linked with roles in order to choose who should complete the task. Roles are notified that there's new content that's in need of review. If you define a role that doesn't exist, it is created automatically.

The first task listed in the `single-approver-definition.xml` workflow definition is the *update* task. Though it appears first in the file, it's actually not the first task in the workflow. The *update* task is the task that's assigned by the workflow if the asset is rejected by an approver. It's listed first because it's the default task: when this task is triggered, the workflow process is reset back to the beginning. In this task, the asset is assigned back to the content creator, who receives an email notification and is required to resubmit the asset. Once the task is resubmitted, it goes back to the review stage.

You can also see that the task is assigned to `<user/>`. This tag always assigns the task back to the user who created the asset.

    <task>
	<name>update</name>
	<actions>
        <notification>
		<name>Creator Modification Notification</name>
		<execution-type>onAssignment</execution-type>
		<template>Your submission was rejected by a reviewer, please modify and resubmit.</template>
		<template-language>text</template-language>
		<notification-type>email</notification-type>
        </notification>
	</actions>
	<assignments>
        <user />
	</assignments>
	<transitions>
        <transition>
		<name>resubmit</name>
		<target>review</target>
		<default>true</default>
        </transition>
	</transitions>
    </task>
 
The *review* task is the first task in the workflow. This is where portal users with the proper role review the content and decide to reject it (move it back to the beginning) or accept it (transition it to the next step).

Once the transition has been made to this task, a notification is sent to those who are assigned to the task. You can edit the name or content of the notification in the XML file.

    <task>
	<name>review</name>
	<actions>
        <notification>
		<name>Review Notification</name>
		<execution-type>onAssignment</execution-type>
		<template>You have a new submission waiting for your review in the workflow.</template>
		<template-language>text</template-language>
		<notification-type>email</notification-type>
        </notification>
	</actions>
    
You must also assign the task to a specific role or roles. This role doesn't have to be the role you notified. For example, you might want to notify all the content creators any time a new item is submitted. Regardless of who you're notifying, you definitely want to send a notification to anyone who is responsible for approving content.

### Sending notifications

Notifications need an `execution-type` which can be `onAssignment`, `onEntry`, or `onExit`.

-   `onEntry` generates and sends the notification when entering the workflow task or state.
-   `onExit` generates and sends the notification when exiting the workflow task or state.
-   `onAssignment` generates and sends the notification when the user is assigned the task in the workflow. **Note:** `onAssignment` notification will not work if you wish to notify a user that is not part of the workflow.

Notifications also need a `notification-type` which can be `email`, `im`, or `private-message`. Note that the `private-message` type is a placeholder for now; that functionality is in Liferay's Social Office product but has not yet been integrated into Liferay Portal. Your notification type and execution type should complement each other. You wouldn't want to use an `onExit` execution type with a private message, because the user won't receive that message until he or she logs back in. Generally speaking, email notifications work best with `onExit` or `onAssignment`, while IM or private message work better with `onEntry`.

Email and private message notifications can also be created as plain text, or you can create formatted content using Freemarker or Velocity templating languages. When creating the notification, you need to specify the `template-language` as `text`, `freemarker`, or `velocity`.

In this workflow, anyone who is capable of approving the content is notified `onAssignment`. This includes administrators and site and organization owners. The `role-type` tag helps the system sort out who should receive the notification based on the scope, and can be set as *community*, *organization*, or *portal*. 

	<assignments>
        <roles>
		<role>
            <role-type>community</role-type>
            <name>Community Administrator</name>
		</role>
		<role>
            <role-type>community</role-type>
            <name>Community Content Reviewer</name>
		</role>
		<role>
            <role-type>community</role-type>
            <name>Community Owner</name>
		</role>
		<role>
            <role-type>organization</role-type>
            <name>Organization Administrator</name>
		</role>
		<role>
              <role-type>organization</role-type>
              <name>Organization Content Reviewer</name>
		</role>
		<role>
            <role-type>organization</role-type>
            <name>Organization Owner</name>
		</role>
		<role>
            <role-type>regular</role-type>
            <name>Portal Content Reviewer</name>
		</role>
		<role>
            <role-type>regular</role-type>
            <name>Administrator</name>
		</role>
        </roles>
	</assignments>

Once the content is approved, you'll want to transition to a new state. 

### Using transitions

In this case, you only need a single approver, the transition goes to the final approved state. In more complex workflows, you might transition to a second tier approver.

	<transitions>
        <transition>
		<name>approve</name>
		<target>approved</target>
		<default>true</default>
        </transition>
        <transition>
		<name>reject</name>
		<target>update</target>
		<default>false</default>
        </transition>
	</transitions>
    </task>

Finally, we define our end state. Remember that states automatically run all actions that are assigned to them, so a script executes and sets the state of the content to *approved*. Workflow scripts are completely contained within XML workflow definitions.

You could also write a customized script if there were actions outside the standard one that you need to perform on your asset. The script below, written in JavaScript, sets the status of the asset to *approved*. Of course, there's much more you can do with scripts. You don't even have to use JavaScript: if you want, you can change the `<script-language>` to another supported language (Ruby, Groovy, or Python) and rewrite the action with additional details to meet your needs.

    <state>
	<name>approved</name>
	<actions>
        <action>
		<name>approve</name>
		<execution-type>onEntry</execution-type>
		<script>
        <![CDATA[Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus("approved"), workflowContext);]]>
		</script>
		<script-language>javascript</script-language>
        </action>
	</actions>
    </state>

To create longer workflows, you'd create additional states, tasks and transitions according to your requirements. For instance, if you wanted to have a second level of review before an item is approved, you'd create a new task in between the *review* task and the *approved* state. The task itself might have similar content to *review*, but you would assign it to a different role. The *review* task would transition to your new task, and the new task would transition to the *approved* state. 

You can also use *forks* and *joins* to create more complex workflows.

### Using forks and joins

Forks and joins are used for parallel processing. For example, say you have a new offer you'd like to put up on your site, but it needs to go through both the sales manager and the marketing manager first. You can set up a workflow that notifies both managers at the same time so that they can approve them individually. This way, you're not waiting for one manager's approval before you can send the notification to the other manager. The below illustration shows how a workflow with a fork and a join might be designed. 

<!-- | TODO - I deleted the original one... -->

![Figure 6.x: Parallel Approval Design](../../images/kaleo-workflow-parallel-approval.png)

You can transition to a fork from a task or state. From the fork, you can transition to multiple tasks or states which occur in parallel. In the previous example, when we have multiple transitions from one task, they're mutually exclusive: you either trigger one or the other. The transitions are also serial, meaning that one must occur, and then the next one can occur. With a parallel workflow, you can have different approvals going through different users at the same time. For example, you could use this to separate two different departments' approval chains on a single asset. A fork should be formatted like this:

    <fork>
		<name>review_fork</name>
		<transitions>
			<transition>
				<name>node_1</name>
				<target>review_one</target>
			</transition>
			<transition>
				<name>node_2</name>
				<target>review_two</target>
			</transition>
		</transitions>
	</fork>
	
To bring a fork back together, transition both nodes of the fork back to a single join. A join is formatted similarly to a fork, except that any transitions are serial, not parallel, as in the example below.

	<join>
		<name>approved</name>
		<transitions>
			<transition>
				<name>result</name>
				<target>done</target>
				<default>true</default>
			</transition>
		</transitions>
	</join>

Another important consideration when creating parallel approvals is that each node needs its own "rejected" state for cases where content is approved in one node, but rejected in another. Another feature that you can use in custom workflows along with forks and joins is Timers. While using parallel workflow enables you to speed up your process by getting content in front more people at once, instead of making them wait in line, timers along you to add some urgency to the process.
	
<!-- | TODO I accidentally removed this one too -->

### Timers

**Timers** are a new workflow feature in 6.1, which help make sure that important tasks in a workflow aren't forgotten or left undone because an oversight or the absence of someone on the critical path. The basic concept of the timer is that after a period of time specified, a specific action occurs. There are two main elements for a Timer, the **Task Timer** and the **Timer Action**.

Timers occur within a Task element and are formatted like:
    <task>
    	...
        <task-timers>
			<task-timer>
				<name></name>
				<delay>
					<duration></duration>
					<scale></scale>
				</delay>
				<timer-actions>
					...
				</timer-actions>
			</task-timer>
		</task-timers>
		...
	</task>
		
The outer element is <task-timers> because you can have multiple timers with multiple actions. The specific <task-timer> then contains the element <delay> which has a <duration> and <scale>. The duration can be any number, whole or fractional and it's significance is defined by the scale. The scale tells you what unit of time the duration is talking about - seconds, minutes, hours, days, weeks, months, or years. Once you've determined the time, you'll want to pick an action - either a notification, reassignment, or a custom script.

Notifications are pretty simple - if a certain amount of time passes and an action isn't completed yet, the user assigned to the task will receive a fresh notification. With the timer, you have all of the standard notification types available, and you can choose a different notification type than was used for the original notifcation. For example, you could create a definition such that when a new item is submitted to the workflow, all members of the *Content Reviewer* role receive a notification. You could then use a timer to say that if the content hasn't been reviewed within two hours each member of the *Content Reviewer* role will receive a second notification via instant messenger.

A Notification would be formatted like this:

    <timer-actions>
		<timer-notification>
			<name></name>
			<template></template>
			<template-language>text</template-language>
			<notification-type>im</notification-type>
		</timer-notification>
    </timer-actions>


Reassignments are designed to keep the workflow moving, even if a key person is out of the office. With a timer set to reassign, after the specified amount of time has passed, the task can be assigned to a new role. Building off of our example above, if the Content Reviewers all received the IM notification after two hours, but the content still wasn't approved after four hours, the workflow could be set to automatically reassign to the task to the *Adminstrator* role.

A Reassignment would be formatted like this:

    <timer-actions>
		<reassignments>
			<assignments>
				<roles>
					<role>
						<role-type></role-type>
						<name></name>
					</role>
					...
				</roles>
			</assignments>
		</reassignments>
    </timer-actions>
    
Obviously we can't think of eveything, so if you have an idea for using timers in your workflow that doesn't fit into our design, you could access Liferay's scripting engine to create a custom action to happen after a specified amount of time. For example, if you had means of sending electric shocks through employees chairs if they weren't doing their work, and had created a Liferay portlet to access the shock mechanism, you could use a custom script to zap any users who were at their desk, but hadn't reviewed content assigned to them.

    <timer-actions>
		<action>
				<name></name>
				<script>
					<![CDATA[
					]]>
				</script>
				<script-language></script-language>
				<execution-type></execution-type>
		</action>
    </timer-actions>

For more information on using scripting in Liferay see Chapter 13: Script Engine.

Using workflows and approvals is necessary for virtually any organization, and timers are an excellent way to help mitigate the potential headaches caused by having multiple bottlenecks through the process. Using timers in conjunction with other workflow features can help you create powerful workflows for your organization.

### Putting it all together

The Kaleo workflow engine is deeply integrated with Liferay Portal. It can generate roles scoped for organizations, sites, and for the whole portal based on workflow definitions. You can also customize workflow options for individual sites.

Users are the most important part of the workflow, since they're the ones who do all the work. To make a user a part of the workflow process, you assign them a role which you defined in your workflow . When you're creating your workflow definition, you can create new roles by defining them in the XML file, or by using roles which you have already created in your portal. Roles created automatically are always portal scoped, so if you want to use site or organization scoped roles, create the roles before deploying your workflow to the portal.

A portal administrator can create a default workflow definition scheme for each application which applies for the entire portal, and site and organization administrators can customize the settings for their sites and organizations. 

Let's turn from creating definitions to how you'd use them. 

## Using workflow in the control panel

Most of your workflow configuration is done via the control panel. Everything you need to do in the portal can be done through simple GUI controls.

![Figure 6.x: Adding a Workflow Definition](../../images/kaleo-workflow-new-definition-control-panel.png)

You can find the Workflow section under the Portal heading in the control panel. There are three options under Workflow: *Definitions*, *Default Configuration*, and *Submissions*.

If you created a new workflow definition, you need to add it so it can be used in the portal. Click *Definitions*. By default, only the Single Approver workflow appears here. Clicking *Add* allows you to enter a title for a new workflow definition and upload the XML file. Once you add a file here, it's added to the portal and is immediately available for use.

Under *Default Configuration*, you can set the workflow behavior (if any) for all workflow-enabled applications on the portal. You can choose to use no workflow, which is the default, or select any installed workflow definition. Setting the default configuration causes any newly created sites to default to that configuration. An administrator can then edit the definitions for each one individually through the *Workflow Configuration* page.

Clicking on *Submissions* will let you view any currently pending assets, or any assets which were previously approved.

### Configuring workflow

After you have uploaded workflow definitions and set the default workflow behavior you can go up to *Workflow Configuration* and tweak the definitions that you're using for each site individually.

![Figure 6.x: The Workflow Configuration Page](../../images/kaleo-workflow-configuration.png)

Using the context selector drop-down menu in the control canel, you can select any site in the portal. All the options under that heading, including Workflow Configuration, now apply to that particular site. 

Using workflow is just as easy. 

### My Workflow Tasks

My Workflow Tasks is a personalized version of the Workflow Tasks, and it's found in your personal section of the control panel. Here are specific tasks which have been assigned to you or assigned to a role of which you are a member. You can also view your completed tasks.

![Figure 6.x: My Workflow Tasks Page](../../images/kaleo-workflow-my-tasks.png)

It's here that workflow users review and approve content. By clicking on the actions next to a piece of content, a user can view that content and then choose to approve or reject it and add comments. 

### My Submissions

My Submissions is found under your user's personal information in the control panel. From this screen you can view any assets that you have submitted to review. Those that are currently under review are listed under the *Pending* tab, and those that have gone through the review process are listed under the *Completed* tab.

![Figure 6.x: The My Submissions Page](../../images/kaleo-workflow-my-submissions.png)

Besides viewing your work, you can also withdraw a submission from the review process by clicking on *Withdraw Submission* from the *Pending* tab.n.

## Using Kaleo Workflow Processes in Liferay Portal

Before workflow can be used, you must define which types of assets on the portal are workflow-enabled. If you have created additional definitions, you must also choose the workflow definition to use for each asset that is workflow-enabled.

![Figure 6.x: You can select which site to work on by using the drop-down menu in the Content section of the control panel.](../../images/kaleo-workflow-control-panel-context-selector.png)

To demonstrate how this works, we'll create a press release. Press releases should be posted in the *Newsroom* section of the web site, so before setting specific workflow configuration options or creating content, create the Newsroom site and switch to it in the control panel. In Workflow Configuration, set Web Content to use the Single Approver workflow.

Next, create two users, a Content Creator and a Content Reviewer. The Content Creator logs in and creates a new press release for Nose-ster and clicks *Submit for Publication*. This triggers the workflow process and notifies the Content Reviewer. When the Content Reviewer logs in, he or she can assign the workflow task to him- or herself and approve the content.

![Figure 6.x: Before a Content Reviewer can approve content, he must assign it to himself, or have an administrator assign it to him.](../../images/kaleo-workflow-assign-to-me.png)

Once the content is approved, it can be posted on the Press Releases page in a web content display portlet. 

There's more. EE customers get extra features that enable them to create workflows without having to deal with XML. 

### Kaleo forms and workflow designer

![EE Only Feature](../../images/ee-only-image/ee-feature-web.png)

Developers who are used to working with XML are generally able to create workflow definitions. Other users may not be so comfortable with it. In fact, even skilled developers can make mistakes that break a definition and require time to troubleshoot. To help streamline the creation of workflow definitions and empower more users to create custom workflows, Liferay provides the Kaleo Workflow Designer in Liferay 6.1 EE.

There are two pieces to the workflow designer: *Kaleo Forms* and *Kaleo Designer*. Kaleo Forms is an extension of the Dynamic Data Lists feature (covered in chapter 5). This enables you to create web forms and basic applications, and then apply a workflow to govern the processing of those forms. Kaleo Designer is a drag and drop interface for creating new workflow definitions. It can be used in conjunction with Kaleo Forms or standalone to create workflow definitions without having to write XML.

Let's look at Kaleo Forms first. 

#### Kaleo Forms

Add the Workflow Forms portlet to a page. The initial state, the *Summary* tab displays the same information that you might have seen in *My Workflow Tasks* in the control panel. Any forms that are available for processing through the workflow can be initiated through the *Submit New* button, as the below image indicates. 

![Figure 6.x: Kaleo Forms give you a convenient way to manage all available workflows in the portal. ](../../images/kaleo-forms-initial-view.png)

Click on *Processes* to view any existing workflow processes or to create new ones. Any processes that are already available are listed here. If you're coming here for the first time, however, there won't be any, so let's create one. Click *Add*. You'll see the screen below. 

![Figure 6.x: The Workflow Process Creation Page](../../images/kaleo-workflow-add-process.png)

Set a name and a description. Next, you'll define an *Entry Definition* and an *Initial Form*, choose or create a *workflow* and *Workflow Task Forms*.

**Entry Definition:** This is a Dynamic Data List Definition, you can use an existing definition here, or create a custom one through the UI.

**Initial Form:** You can customize the display of the Entry Definition with the Inital Form. This can include things like adding Pagination or altering some other display feature.

**Workflow:** You can choose any existing workflow definition here, or define a new one.

**Workflow Task Forms:** This is where you can define how the workflow definition interacts with form definitions. You can trigger a workflow action to occur along with a form action, such as a notification, or have a multistep process where part of the form is completed but needs to be approved before the user can complete another part of the form. 

You probably noticed that when choosing a workflow, you also have the option to create a new one. This is where the Kaleo Workflow Designer can help you build a workflow without having to write any XML. Let's see how this works. 

#### Kaleo Designer

Kaleo Designer provides a drag and drop interface for users to create custom workflows. It's an incredibly powerful tool for managing workflow defintions. The Workflow Designer can only be accessed through the Kaleo Forms portlet, but definitions created can be used for other processes as well.

![Figure 6.x: The Workflow Designer](../../images/kaleo-workflow-designer.png)

There are seven types of nodes that you can add to a defintion. The node types are **Condition**, **End**, **Fork**, **Join**, **Start**, **State**, and **Task**. If you've read the entire chapter, you'll notice that Start and End aren't node types that we've previously discussed; that's because they're actually just State nodes, with certain fields prefilled to help streamline the creation process. Since every workflow has a start and end state, you'd have to do this anyway. 

![Figure 6.x: The Node Configuration Menu](../../images/kaleo-designer-submenu.png)

Each node that you add has a pop-up edit menu which lets you add anchors, attach tasks, or delete the node. Anchors connect the node to other nodes, so for each connection, you need an anchor. To transition to the next node, you'd add an anchor, create a new node, and then click the first anchor and drag it to the anchor on the new node. This creates a transition.

To get a feel for how the designer works, let's use the workflow designer to duplicate the default workflow definition. When we choose the option to *Add Defintion* from the Kaleo Forms portlet, it creates a blank workflow defintion with start and end nodes. To make this work, we'll add two tasks, fill in the relevant information, assign the tasks properly, and create the transitions.

First add two tasks, and use the edit icon to name them *Review* and *Update*. Next, connect the nodes so that Review has four nodes, as follows: one receiving the transition from **StartNode**, one sending a transition to **Update**, one receiving a transition from **Update**, and one sending a transition to **EndNode**.

<!-- | TODO: I fixed this screenshot, but not before I deleted this todo -->


![Figure 6.x: Your workflow should look something like this.](../../images/kaleo-designer-basic-workflow.png)

Next, we want to add the correct assignments and notifications. Click on *Review*. The box on the left shows all the proprerties of the Review node. In the *assignments* category, assign the task to the *Portal Content Reviewer* role. Click on *Notifications* and create a notification with the type *On Assignment*. Now move to the Update node, and assign it to the *Content Creator* with its own notification.

<!-- | TODO: The below paragraph is confusing; please fix. -->

Next let's go through all of the transitions, and make sure that they're named correctly. What are the transitions? Every time you created an arrow from one node to another it created a transtion. By default, these transitions get system generated names, so we'll rename them to all to something more human readable. First click on the arrow going from the Start node to the Review node and set the name as *Submit* and set *Default* to true--we'll leave all the others as false. Set the name of the transition from Review to Update to *Reject*, and the one from Update to Review to *Resubmit*. Lastly, set the name of the transition from Review to the EndNode to *Approve*.

Now let's take a look at the generated XML. It should look a lot like our default workflow, only a tiny bit messier, as the nodes display in the order they were created, not in the logical order that happens when a human writes the code. Save your definition and it's ready to use.

## Summary

<!-- | TODO: This summary is too thin. It should summarize what you covered in the chapter. Going back and looking at the chapter headings can help you write a good summary. | --> 

As you can see, Liferay Portal and the Kaleo Workflow engine combine to create a very robust environment for web content management. Simple workflows can be managed using the default configuration and GUI tools, while more complex workflows can be created to meet the workflow management needs of almost any portal. Through this chapter, we've taken a look at the various elements of a workflow, and shown how to use those elements to create your own custom workflows. We've also seen how to properly use the various elements of a workflow like Assignments and Notifications, as well as newer and more advanced features like Parallel Workflows, Timers, and Custom Scripts.

One of the keys to using Kaleo workflow isn't just understanding each individual element, but seeing how each one interacts with the other elements and figuring out which features are going to work best for your organization. Hopefully this chapter has helped you understand how to best craft a workflwo for you portal.


