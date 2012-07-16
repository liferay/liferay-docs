# Using Workflow [](id=workflow-with-kal-3)

Liferay Portal includes a workflow engine called Kaleo. In Greek, this word means "called ones," which is appropriate for a workflow engine that calls users to participate in a process designed for them. Kaleo workflow allows a user to define any number of simple to complex business processes/workflows, deploy them, and manage them through a portal interface. The processes have knowledge of users, groups and roles. You don't have to write a single line of code to accomplish this: all you have to do is create a single XML document. And if you're a Liferay EE customer, you get a graphical workflow designer which gives you a point and click interface to create workflows. 

To explain how to use Kaleo Workflow, this chapter covers: 

-	Installation
-	Creating workflow definitions
-   Configuring assets to use workflow
-	How users interact with workflow

We introduced Kaleo workflow in chapter 2, where we discussed how to set up an approval process for basic web content. Once we're done with this chapter, you should be familiar with how to use Liferay's Kaleo workflow to set up approval process for any kind of content before it is published to your portal. 

## Enabling workflow [](id=installati-3)

Liferay's Kaleo workflow engine ships with CE versions of Liferay. If you have EE or if you uninstalled it, the plugin can be installed through the Liferay marketplace. The name is `kaleo-web` and you'll find it in the list of web plugins. Installing the plugin adds a number of new options to the control panel: 

-   My Workflow Tasks
-   Workflow Configuration
-   My Submissions
-   Workflow

There is one workflow that comes bundled with the `kaleo-web` plugin: Single Approver Workflow. This workflow requires one approval before an asset can be published. One of the conveniences of using Liferay's workflow engine is that any roles specified in the workflow definition are created automatically when the definition is deployed. This provides a level of integration with the portal that third party engines cannot match. The Single Approver Workflow contains three roles each with different scopes. The scope of each role can be deduced by their names: Site Content Reviewer, Organization Content Reviewer and Portal Content Reviewer. 

Let's jump right in and create a workflow process definition. 

## Creating new workflow definitions [](id=creating-process-definitions)

A Kaleo workflow, called a *process definition*, is defined in an XML file and is executed by users on the portal. You can create as many different workflow definitions as needed to manage the work done on your portal. You can define new user roles in the workflow to manage the approval process or use roles that already exist in your portal.

The XML file has several parts which define the workflow. To get an idea of how this works, we'll examine the default `single-approver-definition.xml` file which is included in the the Liferay Kaleo plugin.

The key parts of the workflow definition are the asset that's running through the workflow, the nodes of the workflow and the transitions between nodes. The asset is any kind of asset registered in Liferay: web content, wiki articles, message board threads and more. Developers can create their own assets as well to be used with workflow (see [*Liferay in Action*](http://manning.com/sezov) or *Liferay Developer's Guide* for more information). Nodes represent stages of the workflow and there are several types. Transitions occur between nodes and indicate what the next node should be. 

Think of workflow as a state machine made up of nodes. A node can be a state, a task, a condition, a fork, a join or a timer. Transitions are used to move from one node to another. Each type of node has different properties. For example, states execute actions automatically and require no user input. Tasks block until user input completes a transition to another state. The transition then moves the workflow to the next task or state. This cycle continues until the end Approved state is reached. For example, you could create a workflow which goes through two approvers. Initiating the workflow puts it in the In Review state and then transitions to a task which requires user input. Users approve or reject the asset as part of the task. When the first user approves the workflow, a condition checks to see if there are two approvals. Since there is only one, workflow transitions back to the task. When the second user approves the workflow, the condition finds there are two approvers and it triggers a different transition to the Approved state. 

Let's look in detail at how you'd create a workflow using a single approver.

### Starting a workflow definition [](id=lp-6-1-ugen06-starting-a-workflow-definition-0)

Below is a diagram of a single approver workflow definition. It has only two tasks and two states.  

![Figure 10.1: The default single approver workflow. Arrows represent transitions and boxes represent states and tasks.](../../images/kaleo-workflow-single-approver.png)

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

### Creating an initial state [](id=lp-6-1-ugen06-creating-an-initial-state-0)

In this case, the state is simply that the asset has been created. States can contain actions and transitions. Actions can contain scripts. You can specify the language of the script with the `<script-language>` tag. Scripts can be written in Groovy, JavaScript, Ruby or Python (see chapter 18 for more information on leveraging scipts in workflow). For a state, the action is triggered automatically and then executes a transition. Transitions move you to a new state or task.

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

### Creating tasks [](id=lp-6-1-ugen06-creating-tasks-0)

The task has several parts and is the most complex part of the definition. Tasks are linked with roles in order to choose who should complete the task. Roles are notified that there's new content in need of review. If you define a role that doesn't exist, it is created automatically.

The first task listed in the `single-approver-definition.xml` workflow definition is the *update* task. Though it appears first in the file, it's actually not the first task in the workflow. The *update* task is the task that's assigned by the workflow if the asset is rejected by an approver. It's listed first because it's the default task: when this task is triggered, the workflow process is reset back to the beginning. In this task, the asset is assigned back to the content creator, who receives an email notification and is required to resubmit the asset. Once the task is resubmitted, it goes back to the review stage.

You can also see the task is assigned to `<user/>`. This tag always assigns the task back to the user who created the asset.

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

### Sending notifications [](id=lp-6-1-ugen06-sending-notifications-0)

Notifications need an `execution-type` which can be `onAssignment`, `onEntry` or `onExit`.

-   `onAssignment` generates and sends the notification when the user is assigned the task in the workflow. **Note:** `onAssignment` notification will not work if you wish to notify a user that is not part of the workflow.
-   `onEntry` generates and sends the notification when entering the workflow task or state.
-   `onExit` generates and sends the notification when exiting the workflow task or state.

Notifications also need a `notification-type` which can be `email`, `im` or `private-message`. Note the `private-message` type is a placeholder for now; that functionality is in Liferay's Social Office product but has not yet been integrated into Liferay Portal. Your notification type and execution type should complement each other. You wouldn't want to use an `onExit` execution type with a private message, because the user won't receive that message until he or she logs back in. Generally speaking, email notifications work best with `onExit` or `onAssignment`, while IM or private message work better with `onEntry`.

Email and private message notifications can also be created as plain text or you can create formatted content using Freemarker or Velocity templating languages. When creating the notification, you need to specify the `template-language` as `text`, `freemarker` or `velocity`.

In this workflow, anyone who is capable of approving the content is notified `onAssignment`. This includes administrators and site and organization owners. The `role-type` tag helps the system sort out who should receive the notification based on the scope and can be set as *community*, *organization* or *portal*. 

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

Once the content is approved you'll want to transition to a new state. 

### Using transitions [](id=lp-6-1-ugen06-using-transitions-0)

In this case, you only need a single approver, then the transition goes to the final approved state. In more complex workflows, you might transition to a second tier approver.

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

Finally, we define our end state. Remember states automatically run all actions that are assigned to them, so a script executes and sets the state of the content to *approved*. Workflow scripts are completely contained within XML workflow definitions.

You could also write a customized script if there were actions outside the standard one that you need to perform on your asset. The script below, written in JavaScript, sets the status of the asset to *approved*. Of course, there's much more you can do with scripts. You don't even have to use JavaScript: if you want, you can change the `<script-language>` to another supported language (Ruby, Groovy or Python) and rewrite the action with additional details to meet your needs.

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

To create longer workflows, you'd create additional states, tasks and transitions according to your requirements. For instance, if you wanted to have a second level of review before an item is approved, you'd create a new task in between the *review* task and the *approved* state. The task itself might have similar content to *review* but you would assign it to a different role. The *review* task would transition to your new task and the new task would transition to the *approved* state. 

You can also use *forks* and *joins* to create more complex workflows.

### Using forks and joins [](id=lp-6-1-ugen06-using-forks-and-joins-0)

Forks and joins are used for parallel processing. For example, say you have a new offer you'd like to put up on your site but it needs to go through both the sales manager and the marketing manager first. You can set up a workflow that notifies both managers at the same time so they can approve them individually. This way, you're not waiting for one manager's approval before you can send the notification to the other manager. The below illustration shows how a workflow with a fork and a join might be designed. 

<!-- | TODO - I deleted the original one... -->
![Figure 10.2: Parallel Approval Design](../../images/kaleo-workflow-parallel-approval.png)

You can transition to a fork from a task or state. From the fork, you can transition to multiple tasks or states which occur in parallel. In the previous example, when we have multiple transitions from one task, they're mutually exclusive: you either trigger one or the other. The transitions are also serial, meaning one must occur before the next one can occur. With a parallel workflow, you can have different approvals going through different users at the same time. For example, you could use this to separate two different departments' approval chains on a single asset. A fork should be formatted like this:

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

Another important consideration when creating parallel approvals is each node needs its own "rejected" state for cases where content is approved in one node but rejected in another. Another feature you can use in custom workflows along with forks and joins is Timers. While using parallel workflow enables you to speed up your process by getting content in front more people at once, instead of making them wait in line, timers allow you to add some urgency to the process.
	
<!-- | TODO I accidentally removed this one too -->
### Timers [](id=lp-6-1-ugen06-timers-0)

**Timers** are a new workflow feature in 6.1, which help make sure important tasks in a workflow aren't forgotten or left undone because of an oversight or the absence of someone on the critical path. The basic concept of the timer is that after a period of time specified, a specific action occurs. There are two main elements for a Timer, the **Task Timer** and the **Timer Action**.

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
		
The outer element is <task-timers> because you can have multiple timers with multiple actions. The specific <task-timer> then contains the element <delay> which has a <duration> and <scale>. The duration can be any number, whole or fractional, and it's significance is defined by the scale. The scale tells you what unit of time the duration is talking about - seconds, minutes, hours, days, weeks, months or years. Once you've determined the time, you'll want to pick an action - either a notification, reassignment or a custom script.

Notifications are pretty simple - if a certain amount of time passes and an action isn't completed yet, the user assigned to the task will receive a fresh notification. With the timer, you have all of the standard notification types available and you can choose a different notification type than was used for the original notifcation. For example, you could create a definition such that when a new item is submitted to the workflow, all members of the *Content Reviewer* role receive a notification. You could then use a timer to say if the content hasn't been reviewed within two hours each member of the *Content Reviewer* role will receive a second notification via instant messenger.

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
    
Obviously we can't think of eveything, so if you have an idea for using timers in your workflow that doesn't fit into our design, you could access Liferay's scripting engine to create a custom action to happen after a specified amount of time. For example, if you had means of sending electric shocks through employees chairs if they weren't doing their work, and had created a Liferay portlet to access the shock mechanism, you could use a custom script to zap any users who were at their desk that hadn't reviewed content assigned to them.

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

For more information on using scripting in Liferay, please refer to chapter 18.

Using workflows and approvals is necessary for virtually any organization and timers are an excellent way to help mitigate the potential headaches caused by having multiple bottlenecks through the process. Using timers in conjunction with other workflow features can help you create powerful workflows for your organization.

### Putting it all together [](id=lp-6-1-ugen06-putting-it-all-together-0)

The Kaleo workflow engine is deeply integrated with Liferay Portal. It can generate roles scoped for organizations, sites and for the whole portal based on workflow definitions. You can also customize workflow options for individual sites.

Users are the most important part of the workflow, since they're the ones who do all the work. To make a user a part of the workflow process, you assign them a role which you defined in your workflow . When you're creating your workflow definition, you can create new roles by defining them in the XML file or by using roles which you have already created in your portal. Roles created automatically are always portal scoped, so if you want to use site or organization scoped roles, create the roles before deploying your workflow to the portal.

A portal administrator can create a default workflow definition scheme for each application which applies for the entire portal and site and organization administrators can customize the settings for their sites and organizations. Now that we've seen how to create workflow definitions, let's discuss how to use them. 

## Configuring assets to use workflow process definitions [](id=using-workflow-in-the-control-panel)

Most of your workflow configuration is done via the control panel. Everything you need to do in the portal can be done through simple GUI controls.

![Figure 10.3: Adding a Workflow Definition](../../images/kaleo-workflow-new-definition-control-panel.png)

You can find the Workflow section under the Portal heading in the control panel. There are three options under Workflow: *Definitions*, *Default Configuration* and *Submissions*.

If you created a new workflow definition, you need to add it so it can be used in the portal. Click *Definitions*. By default, only the Single Approver workflow appears here. Clicking *Add* allows you to enter a title for a new workflow definition and upload the XML file. Once you add a file here, it's added to the portal and is immediately available for use.

Under *Default Configuration*, you can set the workflow behavior (if any) for all workflow-enabled applications on the portal. You can choose to use no workflow, which is the default, or select any installed workflow definition. Setting the default configuration causes any newly created sites to default to that configuration. An administrator can then edit the definitions for each one individually through the *Workflow Configuration* page.

Clicking on *Submissions* will let you view any currently pending assets or any assets which were previously approved.

### Configuring workflow [](id=lp-6-1-ugen06-configuring-workflow-0)

After you have uploaded workflow definitions and set the default workflow behavior you can go up to *Workflow Configuration* and tweak the definitions you're using for each site individually.

![Figure 10.4: The Workflow Configuration Page](../../images/kaleo-workflow-configuration.png)

Using the context selector drop-down menu in the control canel, you can select any site in the portal. All the options under that heading, including Workflow Configuration, now apply to that particular site. Using workflow is just as easy. 

### My Workflow Tasks [](id=lp-6-1-ugen06-my-workflow-tasks-0)

My Workflow Tasks is a personalized version of the Workflow Tasks and it's found in your personal section of the control panel. Here are specific tasks which have been assigned to you or assigned to a role of which you are a member. You can also view your completed tasks.

![Figure 10.5: My Workflow Tasks Page](../../images/kaleo-workflow-my-tasks.png)

It's here workflow users review and approve content. By clicking on the actions next to a piece of content, a user can view the content, then choose to approve or reject it and add comments. 

### My Submissions [](id=lp-6-1-ugen06-my-submissions-0)

My Submissions is found under your user's personal information in the control panel. From this screen you can view any assets  you have submitted to review. Those currently under review are listed under the *Pending* tab and those that have been reviewed are listed under the *Completed* tab.

![Figure 10.6: The My Submissions Page](../../images/kaleo-workflow-my-submissions.png)

Besides viewing your work, you can also withdraw a submission from the review process by clicking on *Withdraw Submission* from the *Pending* tab.

### Using Kaleo Workflow Processes in Liferay Portal [](id=using-kaleo-workflow-processes-in-liferay-portal)

Before workflow can be used, you must define which types of assets on the portal are workflow-enabled. If you have created additional definitions, you must also choose the workflow definition to use for each asset that is workflow-enabled.

![Figure 10.7: You can select which site to work on by using the drop-down menu in the Content section of the control panel.](../../images/kaleo-workflow-control-panel-context-selector.png)

To demonstrate how this works, we'll create a press release. Press releases should be posted in the *Newsroom* section of the web site, so before setting specific workflow configuration options or creating content, create the Newsroom site and switch to it in the control panel. In Workflow Configuration, set Web Content to use the Single Approver workflow.

Next, create two users, a Content Creator and a Content Reviewer. The Content Creator logs in and creates a new press release for Nose-ster and clicks *Submit for Publication*. This triggers the workflow process and notifies the Content Reviewer. When the Content Reviewer logs in, he or she can assign the workflow task to him- or herself and approve the content.

![Figure 10.8: Before a Content Reviewer can approve content, he must assign it to himself or have an administrator assign it to him.](../../images/kaleo-workflow-assign-to-me.png)

Once the content is approved, it can be posted on the Press Releases page in a web content display portlet. 

There's more. EE customers get extra features that enable them to create workflows without having to deal with XML. 

<!--  These sections need content.
## Using workflow with other applications (head ref) [](id=lp-6-1-ugen10-using-workflow-with-other-applications-head-ref-0)
	
### WCM [](id=lp-6-1-ugen10-wcm-0)

### Documents & media [](id=lp-6-1-ugen10-documents--media-0)

### Collaboration [](id=lp-6-1-ugen10-collaboration-0)
-->
## Summary [](id=lp-6-1-ugen10-summary-0)

In this chapter, we explained how to install the Kaleo workflow plugin for Liferay EE. Liferay's Kaleo workflow engine is included with Liferay CE. We discussed how to create new workflow definitions and examined the XML schema for them. We also looked at how to choose different workflow processes for different asset types. Finally, we explained how end-users can interact with the approval process. In the next chapter, we'll look at Kaleo forms and the Kaleo workflow designer.
