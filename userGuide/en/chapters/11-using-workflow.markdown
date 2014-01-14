
# Using Workflow [](id=using-workflow-liferay-portal-6-2-user-guide-11-en)

Liferay Portal includes a workflow engine called Kaleo. In Greek, Kaleo 
means "called ones," which is appropriate for a workflow engine that calls users
to participate in a process designed for them. Kaleo workflow allows a user to
define any number of simple to complex business processes/workflows, deploy
them, and manage them through a portal interface. The processes have knowledge
of users, groups and roles. You don't have to write a single line of code to
accomplish this: all you have to do is create one XML document. And if
you're a Liferay EE customer, you get a graphical workflow designer which gives
you a point and click interface to create workflows. 

To explain how to use Kaleo Workflow, this chapter covers: 

- Enabling workflow

- Creating workflow definitions

- Configuring assets to use workflow process definitions

- Using workflow with other applications

Once we're done with this chapter, you should be familiar with how to use
Liferay's Kaleo workflow to set up approval process for any kind of content
before it is published to your portal. 

## Enabling workflow [](id=enabling-workflow-liferay-portal-6-2-user-guide-11-en)

Liferay's Kaleo workflow engine can be installed for both CE and EE versions of
Liferay. The web plugin's name is `kaleo-web` and is bundled in the *Kaleo
Forms EE* and *Kaleo Workflow CE* apps on Liferay marketplace, wich you can
access through the Apps section of the Control Panel. Installing the plugin
adds a *Workflow* option under the *Configuration* section of your Control
Panel. 

The `kaleo-web` plugin comes bundled with one worfklow called the Single
Approver Workflow. This workflow requires one approval before an asset can be
published. One of the conveniences of using Liferay's workflow engine is that
any roles specified in the workflow definition are created automatically when
the definition is deployed. This provides a level of integration with the
portal that third party engines cannot match. The Single Approver Workflow
contains three roles, each with different scopes. You can deduce the scope of
each role by its name: Site Content Reviewer, Organization Content Reviewer and
Portal Content Reviewer. 

Let's jump right in and create a workflow process definition. 

## Creating new workflow definitions [](id=creating-new-workflow-definitions-liferay-portal-6-2-user-guide-11-en)

A Kaleo workflow, called a *process definition*, is defined in an XML file and
is executed by users of the portal. You can create as many different workflow
definitions as needed to manage the work done on your portal. Your workflows
can define new user roles to manage the approval process or use roles that
already exist in your portal.

The XML file has several parts which define the workflow. To get an idea of how
this works, we'll examine the default `single-approver-definition.xml` file
which is included in the Liferay Kaleo plugin.

The key parts of the workflow definition are the asset or workflow-enabled
action that's running through the workflow, the nodes of the workflow, and the
transitions between nodes. Assets are any kind of asset registered in Liferay:
web content, wiki articles, message board threads, blogs entries, and even
comments, are workflow-enabled. Workflow-enabled actions in the portal include
Page Revision and  User addition or editing. Developers can also create their
own assets to use with workflows (see [*Liferay in
Action*](http://manning.com/sezov) or *Liferay Developer's Guide* for more
information). Nodes represent stages of the workflow and there are several
types. Transitions occur between nodes and indicate what the next node should
be. 

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

Let's learn about the Single Approver Workflow's components and look in detail
at how you'd create a workflow using a single approver. 

### Starting a workflow definition [](id=starting-a-workflow-definition-liferay-portal-6-2-user-guide-11-en)

Below is a diagram of a single approver workflow definition. It has only two
tasks (Update and Review) and two states (Initial State and Approved). 

![Figure 11.1: The default single approver workflow. Arrows represent transitions and boxes represent states and tasks.](../../images/kaleo-workflow-single-approver.png)

First you should define the schema. For Liferay workflows using Kaleo,
`liferay-worklow-definition-6_2_0.xsd` should be your schema. You can find this
schema in the `definitions` folder of the Liferay source or a good XML editor
can cache it from Liferay's web site. Here's how you define it in your workflow
definition's XML file:

    <workflow-definition
        xmlns="urn:liferay.com:liferay-workflow_6.2.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="urn:liferay.com:liferay-workflow_6.2.0 http://www.liferay.com/dtd/liferay-workflow-definition_6_2_0.xsd"
    >

Next you define a name and description for the workflow. This appears in the
control panel when users choose and configure workflows.

	<name>Single Approver</name>
	<description>A single approver can approve a workflow content.</description>
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

### Creating tasks [](id=creating-tasks-liferay-portal-6-2-user-guide-11-en)

The task has several parts and is the most complex part of the definition. Tasks
are linked with roles in order to choose who should complete the task. Roles are
notified that there's new content in need of review. If you define a role that
doesn't exist, it is created automatically.

The first task listed in the `single-approver-definition.xml` workflow
definition is the *update* task. Though it appears first in the file, it's
actually not the first task in the workflow. The *update* task is the task
that's assigned by the workflow if the asset is rejected by an approver. It's
listed first because it's the default task: when this task is triggered, the
workflow process is reset back to the beginning. In this task, the asset is
assigned back to the content creator, who receives an email notification and is
required to resubmit the asset. Once the task is resubmitted, it goes back to
the review stage.

You can also see the task is assigned to `<user/>`. This tag always assigns the
task back to the user who created the asset.

    <task>
        <name>update</name>
        <metadata>
            <![CDATA[{"transitions":{"resubmit":{"bendpoints":[[303,140]]}},"xy":[328,199]}]]>
        </metadata>
		<actions>
			<action>
				<name>reject</name>
				<script>
					<![CDATA[
						Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus("denied"), workflowContext);
						Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus("pending"), workflowContext);
					]]>
				</script>
				<script-language>javascript</script-language>
				<execution-type>onAssignment</execution-type>
			</action>
			<notification>
				<name>Creator Modification Notification</name>
				<template>Your submission was rejected by ${userName}, please modify and resubmit.</template>
				<template-language>freemarker</template-language>
				<notification-type>email</notification-type>
				<notification-type>user-notification</notification-type>
				<execution-type>onAssignment</execution-type>
			</notification>
		</actions>
		<assignments>
			<user />
		</assignments>
		<transitions>
			<transition>
				<name>resubmit</name>
				<target>review</target>
			</transition>
		</transitions>
	</task>

The *review* task is the first task in the workflow. This is where portal users
with the proper role review the content and decide to reject it (move it back
to the beginning) or accept it (transition it to the next step).

Once the transition has been made to this task, a notification is sent to those
who are assigned to the task. You can edit the name or content of the
notification in the XML file. Once the reviewer completes their review and
exits the workflow task, another email is sent to the original submitter
indicating that the review is completed. 

	<task>
		<name>review</name>
		<metadata>
			<![CDATA[{"xy":[168,36]}]]>
		</metadata>
		<actions>
			<notification>
				<name>Review Notification</name>
				<template>${userName} sent you a ${entryType} for review in the workflow.</template>
				<template-language>freemarker</template-language>
				<notification-type>email</notification-type>
				<notification-type>user-notification</notification-type>
				<execution-type>onAssignment</execution-type>
			</notification>
			<notification>
				<name>Review Completion Notification</name>
				<template>
					Your submission has been reviewed and the reviewer has applied the following ${taskComments}.</template>
				<template-language>freemarker</template-language>
				<notification-type>email</notification-type>
				<recipients>
					<user />
				</recipients>
				<execution-type>onExit</execution-type>
			</notification>
		</actions>

You must also assign the task to a specific role or roles. This role doesn't
have to be the role you notified. For example, you might want to notify all the
content creators any time a new item is submitted. Regardless of who you're
notifying, you definitely want to send a notification to anyone who is
responsible for approving content.

### Sending notifications [](id=sending-notifications-liferay-portal-6-2-user-guide-11-en)

Notifications need an `execution-type` which can be `onAssignment`, `onEntry` or
`onExit`.

-   `onAssignment` generates and sends the notification when the user is
    assigned the task in the workflow. **Note:** `onAssignment` notification
    will not work if you wish to notify a user that is not part of the workflow.

-   `onEntry` generates and sends the notification when entering the workflow
    task or state.

-   `onExit` generates and sends the notification when exiting the workflow task
    or state.

Notifications also need a `notification-type` which can be `email`, `im` or
`private-message`. Note that both the `private-message` and `im` types are
placeholders for now; that functionality is in Liferay's Social Office product
but has not yet been integrated into Liferay Portal. Your notification type and
execution type should complement each other. You wouldn't want to use an
`onExit` execution type with a private message, because the user won't receive
that message until he or she logs back in. Generally speaking, email
notifications work best with `onExit` or `onAssignment`, while IM or private
message work better with `onEntry`.

Email and private message notifications can also be created as plain text or you
can create formatted content using FreeMarker or Velocity templating languages.
When creating the notification, you need to specify the `template-language` as
`text`, `freemarker` or `velocity`.

In this workflow, anyone who is capable of approving the content is notified
`onAssignment`. This includes administrators and site and organization owners.
The `role-type` tag helps the system sort out who should receive the
notification based on the scope and can be set as *site*, *organization* or
*portal*. When you specify a `role-type` to define the scope of the workflow
notification, please note that the  portal role is equvalent to the *regular*
role-type declaration in our XML snippet below. 

<!--So can they use portal in place of regular or must they specify regular to
define a role-type scoped at the portal level?-->

    <assignments>
        <roles>
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
                <name>Administrator</name>
            </role>
            <role>
                <role-type>regular</role-type>
                <name>Portal Content Reviewer</name>
            </role>
            <role>
                <role-type>site</role-type>
                <name>Site Administrator</name>
            </role>
            <role>
                <role-type>site</role-type>
                <name>Site Content Reviewer</name>
            </role>
            <role>
                <role-type>site</role-type>
                <name>Site Owner</name>
            </role>
        </roles>
    </assignments>

Once the content is approved you'll want to transition to a new state. 

### Using transitions [](id=using-transitions-liferay-portal-6-2-user-guide-11-en)

In this case, you only need a single approver, then the transition goes to the
final approved state. In more complex workflows, you might transition to a
second tier approver.

		<transitions>
			<transition>
				<name>approve</name>
				<target>approved</target>
			</transition>
			<transition>
				<name>reject</name>
				<target>update</target>
				<default>false</default>
			</transition>
		</transitions>
    </task>

Finally, we define our end state. Remember states automatically run all actions
that are assigned to them, so a script executes and sets the state of the
content to *approved*. Workflow scripts are completely contained within XML
workflow definitions.

You could also write a customized script if there were actions outside the
standard one that you need to perform on your asset. The script below, written
in JavaScript, sets the status of the asset to *approved*. Of course, there's
much more you can do with scripts. You don't even have to use JavaScript: if you
want, you can change the `<script-language>` to another supported language
(Ruby, Groovy or Python) and rewrite the action with additional details to meet
your needs.

	<state>
		<name>approved</name>
		<metadata>
			<![CDATA[
				{"xy":[380,51]}
			]]>
		</metadata>
		<actions>
			<action>
				<name>approve</name>
				<script>
					<![CDATA[
						import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
						import com.liferay.portal.kernel.workflow.WorkflowConstants;

						WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.toStatus("approved"), workflowContext);
					]]>
				</script>
				<script-language>groovy</script-language>
				<execution-type>onEntry</execution-type>
			</action>
		</actions>
	</state>

To create longer workflows, you'd create additional states, tasks and
transitions according to your requirements. For instance, if you wanted to have
a second level of review before an item is approved, you'd create a new task in
between the *review* task and the *approved* state. The task itself might have
similar content to *review* but you would assign it to a different role. The
*review* task would transition to your new task and the new task would
transition to the *approved* state. 

You can also use *forks* and *joins* to create more complex workflows.

### Using forks and joins [](id=using-forks-and-joins-liferay-portal-6-2-user-guide-11-en)

Forks and joins are used for parallel processing. For example, say you have a
new offer you'd like to put up on your site but it needs to go through both the
sales manager and the marketing manager first. You can set up a workflow that
notifies both managers at the same time so they can approve them individually.
This way, you're not waiting for one manager's approval before you can send the
notification to the other manager. The below illustration shows how a workflow
with a fork and a join might be designed. 

<!-- | TODO - I deleted the original one... --> 
![Figure 11.2: Parallel Approval Design](../../images/kaleo-workflow-parallel-approval.png)

You can transition to a fork from a task or state. From the fork, you can
transition to multiple tasks or states which occur in parallel. In the previous
example, when we have multiple transitions from one task, they're mutually
exclusive: you either trigger one or the other. The transitions are also serial,
meaning one must occur before the next one can occur. With a parallel workflow,
you can have different approvals going through different users at the same time.
For example, you could use this to separate two different departments' approval
chains on a single asset. A fork should be formatted like this:

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
	
To bring a fork back together, transition both nodes of the fork back to a
single join. A join is formatted similarly to a fork, except that any
transitions are serial, not parallel, as in the example below.

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

Another important consideration when creating parallel approvals is each node
needs its own "rejected" state for cases where content is approved in one node
but rejected in another. Another feature you can use in custom workflows along
with forks and joins is Timers. While using parallel workflow enables you to
speed up your process by getting content in front more people at once, instead
of making them wait in line, timers allow you to add some urgency to the
process.
	
<!-- | TODO I accidentally removed this one too -->
### Timers [](id=timers-liferay-portal-6-2-user-guide-11-en)

*Timers* help make sure important tasks in a workflow aren't forgotten or left
undone because of an oversight or the absence of someone on the critical path.
The basic concept of the timer is that after a period of time specified, a
specific action occurs. There are two main elements for a Timer, the *Task
Timer* and the *Timer Action*.

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
		
The outer element is `<task-timers>` because you can have multiple timers with
multiple actions. The specific `<task-timer>` then contains the element
`<delay>` which has a `<duration>` and `<scale>`. The duration can be any
number, whole or fractional, and it's significance is defined by the scale. The
scale tells you what unit of time the duration is talking about - seconds,
minutes, hours, days, weeks, months or years. Once you've determined the time,
you'll want to pick an action - either a notification, reassignment or a custom
script.

Notifications are pretty simple - if a certain amount of time passes and an
action isn't completed yet, the user assigned to the task will receive a fresh
notification. With the timer, you have all of the standard notification types
available and you can choose a different notification type than was used for the
original notification. For example, you could create a definition such that when
a new item is submitted to the workflow, all members of the *Content Reviewer*
role receive a notification. You could then use a timer to say if the content
hasn't been reviewed within two hours each member of the *Content Reviewer* role
will receive a second notification via instant messenger.

A Notification would be formatted like this:

    <timer-actions>
		<timer-notification>
			<name></name>
			<template></template>
			<template-language>text</template-language>
			<notification-type>im</notification-type>
		</timer-notification>
    </timer-actions>


Reassignments are designed to keep the workflow moving, even if a key person is
out of the office. With a timer set to reassign, after the specified amount of
time has passed, the task can be assigned to a new role. Building off of our
example above, if the Content Reviewers all received the IM notification after
two hours, but the content still wasn't approved after four hours, the workflow
could be set to automatically reassign to the task to the *Administrator* role.

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

Obviously we can't think of everything, so if you have an idea for using timers
in your workflow that doesn't fit into our design, you could access Liferay's
scripting engine to create a custom action to happen after a specified amount of
time. For example, if you had means of sending electric shocks through employees
chairs if they weren't doing their work, and had created a Liferay portlet to
access the shock mechanism, you could use a custom script to zap any users who
were at their desk that hadn't reviewed content assigned to them.

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

Using workflows and approvals is necessary for virtually any organization and
timers are an excellent way to help mitigate the potential headaches caused by
having multiple bottlenecks through the process. Using timers in conjunction
with other workflow features can help you create powerful workflows for your
organization.

### Putting it all together [](id=putting-it-all-together-liferay-portal-6-2-user-guide-11-en)

The Kaleo workflow engine is deeply integrated with Liferay Portal. It can
generate roles scoped for organizations, sites and for the whole portal based on
workflow definitions. You can also customize workflow options for individual
sites.

Users are the most important part of the workflow, since they're the ones who
do all the work. To make a user a part of the workflow process, you assign them
a role which you defined in your workflow. When you're creating your workflow
definition, you can create new roles by defining them in the XML file or by
using roles which you have already created in your portal. Roles created
automatically are always portal scoped, so if you want to use site or
organization scoped roles, create the roles before deploying your workflow to
the portal.

A portal administrator can create a default workflow definition scheme for each
application which applies for the entire portal; site and organization
administrators can customize the settings for their sites and organizations.
Now that we've seen how to create workflow definitions, let's discuss how to
use them. 

## Configuring assets to use workflow process definitions [](id=configuring-assets-to-use-workflow-proc-liferay-portal-6-2-user-guide-11-en)

All your global workflow configuration can be done via the control panel.
Everything you need to do in the portal can be done through simple GUI
controls.

![Figure 11.3: Adding a Workflow Definition](../../images/11-workflow-upload-definition.png)

You can find the Workflow section under the Configuration heading in the
control panel. There are three tabs under Workflow which are used to configure
workflow in the portal: *Definitions*, *Default Configuration* and
*Submissions*. These tabs let you upload definitions, enable workflow for your
portal's resources, and The default workflow behavior you specify here will
apply throughout your Liferay Portal.

From the *Definitions* tab you can see the currently available workflow
definitions and add newly created workflow definitions you'd like to use in the
portal. Clicking *Upload Definition* allows you to enter a title for a new
workflow definition, browse to your local XML file, and upload it to your
Liferay instance. Once you add a file here, it's added to the portal and is
immediately available for use.

Under *Default Configuration*, you can set the workflow behavior for all
workflow-enabled actions in the portal; available resources include Page
Revision, User, Blogs Entries, Web Content Articles, Comments, Message Boards
Messages, and Wiki Pages. You can also add a custom potlet to this list by
using Liferay's API. You can choose from two default workflow options in
Default Configuration: *No Workflow* or *Single Approver*, which we looked at
in detail earlier in the chapter.

When you set the default workflow configuration of a resource, any new action
on that resource defaults to that configuration, throughout the portal. An
administrator can edit the workflow behavior for each site's resources
individually through the *Workflow Configuration* section of a particular
site's Site Administration section. The interface for making changes at the
site level is similar to that of the Control Panel interface; the only
difference is that you are in the Site Administration section and the changes
you specify are only applicable to the scope you have selected here.

In the *Submissions* tab you can see any currently pending assets or any assets
which were previously approved.

Let's learn more about configuring workflows for both the portal and individual
sites next.

### Configuring workflow [](id=configuring-workflow-liferay-portal-6-2-user-guide-11-en)

After you have uploaded workflow definitions and set the default workflow
behavior you can go up to *Default Configuration* and enable workflow for
specified actions in your portal. 

![Figure 11.4: The Workflow Configuration Page](../../images/11-workflow-default-config.png)

By setting default workflow configuration behavior from the Control Panel, your
specified settings are implemented at the global portal scope. If you need to
set the workflow configuration for a specific site, you can do so from the
*Site Administration* section. You can get there from the Control Panel by
clicking *Sites*, clicking on a site from the list, and opening the
*Configuration* menu. The *Workflow Configuration* option lets you modify
workflow behavior for the sleected site's resources that can be workflow
enabled.

### My Workflow Tasks [](id=my-workflow-tasks-liferay-portal-6-2-user-guide-11-en)

My Workflow Tasks is a personalized version of the Workflow Tasks and it's found
in the user's My Account section. Here you'll find specific tasks which were
assigned to you or assigned to a role of which you are a member. You can also
view your completed tasks by opening the Completed tab.

![Figure 11.5: My Workflow Tasks Page](../../images/11-my-workflow-tasks.png)

Workflow administrative users review and approve content from their My Worfklow
Tasks section. By clicking the link to the asset, you can view it, and the
Actions dropdown lets you approve, reject, or reassign assets assigned to you.
For assets assigned to your roles, you can assign the listed assets to be
reviewed by yourself or another user. Whether or not an asset is assigned to
you or to your role, you can update the due date as well. 

### My Submissions [](id=my-submissions-liferay-portal-6-2-user-guide-11-en)

My Submissions is found in your user's My Account section of the portal. From
this screen you can view any assets you have submitted for review. Those
currently under review are listed under the *Pending* tab and those that have
been reviewed are listed under the *Completed* tab.

![Figure 11.6: The My Submissions Page](../../images/11-workflow-my-submissions.png)

Besides viewing your work, you can also withdraw a submission from the review
process by clicking on *Withdraw Submission* from the *Pending* tab.

### Using Kaleo Workflow Processes in Liferay Portal [](id=using-kaleo-workflow-processes-in-lifer-liferay-portal-6-2-user-guide-11-en)

Before workflow can be used, you must define which types of assets on the portal
are workflow-enabled. If you have created additional definitions, you must also
choose the workflow definition to use for each asset that is workflow-enabled.

![Figure 11.7: You can select which site to work on by using the drop-down menu in the Site Administration section of the portal.](../../images/11-workflow-site-configuration.png)

To demonstrate how this works, we'll create a press release. Press releases
should be posted in the *Newsroom* section of the web site, so before setting
specific workflow configuration options or creating content, create the
Newsroom site. When you click Save, you'll be redirected to the Site
Administration section of the portal where you can work on the Newsroom site
further. Under Configration, in Workflow Configuration, set Web Content to use
the Single Approver workflow.

Next, create two users, a Content Creator and a Content Reviewer. The Content
Creator logs in and creates a new press release for the Lunar Resort and clicks
*Submit for Publication*. This triggers the workflow process and notifies the
Content Reviewer. When the Content Reviewer logs in, he or she can assign the
workflow task to him- or herself and approve the content.

![Figure 11.8: Before a Content Reviewer can approve content, he must assign it to himself or have an administrator assign it to him.](../../images/11-workflow-assign-to-me.png)

Once the content is approved, it can be posted on the Press Releases page in a
Web Content Display portlet. 

There's more. EE customers get extra features that enable them to create
workflows without having to deal with XML. 

## Using workflow with other applications [](id=using-workflow-with-other-applications-liferay-portal-6-2-user-guide-11-en)

Above we saw an example of using workflow with Liferay web content. The process
is the same for all resources that are workflow-enabled. However, while
configuring your resources to use workflow in the Default Configuration tab of
the Workflow section, you may have noticed a note that "The workflows for the
following resources can be configured within their respective portlets".
resources listed are *Documents* and *Dynamic Data Lists Records*. Workflow
configuration is more specifically enabled for Documents and Media folders and
Dynamic Data Lists (i.e., each individaul list). 

To see how this works for a Documents and Media folder, create a new page in
the default site called *Documents and Media* and add the Documents and Media
portlet to this page. Then click *Add* &rarr; *Folder*, enter the name *My
Documents*, and click *Save*. Mouse over your new My Documents folder, click on
the black arrow that appears at the top right corner of the folder, and select
*Edit*.

![Figure 11.9: Workflow for Documents and Media must be enabled at the folder level. Edit a folder to select a workflow.](../../images/11-workflow-edit-folder.png)

By default, the *Use document type restrictions and workflow of the parent
folder* button is selected. To enable workflow for this folder, select the
*Define specific document type restrictions and workflow for this folder*
button. After you've selected this button, a *Default Workflow for all Document
Types* drop-down menu appears. By default, you can select *No workflow* or
*Single Approver*. Any custom workflows that you added also appear in this
drop-down menu. You can add custom workflows through the Workflow page in the
Portal section of the Control Panel.

![Figure 11.10: You can use the document type restrictions and workflow of the parent folder or you can define specific document type restrictions and workflow for this folder.](../../images/11-folder-workflow.png)

After you've selected the *Single Approver* workflow and clicked *Save*,
workflow takes effect for the My Documents folder. Try adding a new document to
your My Documents folder--notice that the *Publish* button now says *Submit for
Publication* since workflow is enabled. Any users assigned to the (Portal,
Organization, or Site) Content Reviewer roles can see that your document has
been submitted for publication by navigating to My Account and clicking on *My
Workflow Tasks*. Ordinarily, the same user who submitted a document for
publication wouldn't also approve it but we'll do this to demonstrate how the
process works.

Like with Documents and Media folders, workflow is configured for a Dynamic
Data List at the list level. That is, when you add a New List for users, you
can enable workflow for that particular list. No Workflow is the default
setting, but you can select the Single Approver definition or any definition
you uploaded to use in the portal. When a user submits an item to a list that
was configured to use a workflow, they'll see the item's status as Pending.
Once the list item has been approved by an administrative user, the status
changes to Approved. 

<!--When I try to add a Document to a workflow-enabled folder, I cannot.
There is no option under Add to add a folder. It works for folders not
workflow-enabled. An already created document cannot be moved to a workflow
enabled folder either. So I couldn't test this step properly.-->

Navigate to the My Account and click on *My Workflow Tasks*. Documents and Data
Lists that you submitted for publication appear under *Assigned to My Roles*
since you're an administrator. Click *Actions* &rarr; *Assign to Me* next to
your document. Then click *Actions* &rarr; *Approve* next to the document or
list when it appears in the *Assigned to Me* category. That's it--your
resources have passed through the workflow!

## Summary [](id=summary-liferay-portal-6-2-user-guide-11-en)

In this chapter, we explained how to install the Kaleo workflow plugin for
Liferay EE. Liferay's Kaleo workflow engine is included with Liferay CE. We
discussed how to create new workflow definitions and examined the XML schema
for them. We looked at how to choose different workflow processes for different
asset types. We also explained how end-users can interact with the approval
process. Finally, we discussed how to use workflow with different types of
applications such as Documents and Media and Dynamic Data Lists. In the next
chapter, we'll look at Kaleo forms and the Kaleo workflow designer.
