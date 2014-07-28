# Creating new workflow definitions [](id=creating-new-workflow-definitions-lp-6-2-use-useportal)

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

## Starting a workflow definition

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

## Creating an initial state

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

## Creating tasks

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

## Sending notifications

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

## Using transitions

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

## Using forks and joins

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
## Timers

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

## Putting it all together

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
