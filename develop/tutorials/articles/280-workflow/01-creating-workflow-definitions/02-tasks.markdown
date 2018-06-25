# Workflow Task Nodes [](id=workflow-task-nodes)

Task nodes are fundamental parts of a workflow definition. When you define your
organization's business processes and planning workflows, you likely first
envision the tasks. As the name implies, tasks are the part of the workflow
where *work* is done. A user enters the picture and must interact with the
submitted asset. Users often take the role of reviewer, deciding if an asset
from the workflow is acceptable for publication or needs more work.

Unlike other workflow nodes, task nodes have Assignments, because a user is
expected to *do something* (often approve or reject the submitted asset) when a
workflow process enters the task node. 

Commonly, task nodes contain task timers, assignments, actions (which can
include notifications and scripts), and transitions. Notifications and actions
aren't limited to task nodes, but task nodes and their assignments deserve their
own article (this one).

Check out the Review task in the Single Approver definition, noting that several
`<role>` tags are excluded from this snippet for brevity:

	<task>
		<name>review</name>
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
				<template><![CDATA[Your submission was reviewed<#if taskComments?has_content> and the reviewer applied the following ${taskComments}</#if>.]]></template>
				<template-language>freemarker</template-language>
				<notification-type>email</notification-type>
				<recipients>
					<user />
				</recipients>
				<execution-type>onExit</execution-type>
			</notification>
		</actions>
		<assignments>
			<roles>
				<role>
					<role-type>organization</role-type>
					<name>Organization Administrator</name>
				</role>
                ...
			</roles>
		</assignments>
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

There are two `actions` in the review task, both `<notification>`s. Each
notification may contain a name, template, notification-type, execution-type,
and recipients. Besides notifications, You can also use the `<action>` tag.
These have a name and a 
[script](/discover/portal/-/knowledge_base/7-1/leveraging-the-script-engine-in-workflow) 
and are more often used in state nodes than tasks.

## Assignments [](id=assignments)

Workflow tasks are completed by a user. Assignments make sure the right users
can access the tasks. You can choose how you want to configure your assignments. 

You can choose to add assignments to specific roles, to multiple roles of a role
type (organization, site, or regular role types), to the asset creator, to
resource actions, or to specific users. Additionally, you can write a script to
define the assignment. For an example, see the
`single-approver-definition-scripted-assignment.xml`.

    <assignments>
        <roles>
            <role>
                <role-type>organization</role-type>
                <name>Organization Administrator</name>
            </role>
        </roles>
    </assignments>

The above assignment specifies that an Organization Administrator must complete
the task.

    <assignments>
        <user>
            <user-id>20156</user-id>
        </user>
    </assignments>

The above assignment specifies that only the user with the user ID of 20156 may
complete the task. Alternatively, specify the `<screen-name>` or
`<email-address>` of the user.

		<assignments>
			<scripted-assignment>
				<script>
					<![CDATA[
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
						]]>
					</script>
				<script-language>groovy</script-language>
			</scripted-assignment>
		</assignments>

The above assignment assigns the task to the *Administrator* role, then checks
whether the *group* of the asset is an Organization. If it is, the *Organization
Content Reviewer* role is assigned to it. If it's not, the task is assigned to
the *Site Content Reviewer* role.

Note the `roles = new ArrayList<Role>();` line above. In a scripted assignment,
the `roles` variable is where you specify any roles the task is assigned to. For
example, when `roles.add(adminRole);` is called, the Administrator role is added
to the assignment.

Assigning tasks to Roles, Organizations, or Asset Creators is a straightforward
concept, but what does it mean to assign a workflow task to a Resource Action?
Imagine an *UPDATE* resource action. If your workflow definition specifies the
UPDATE action in an assignment, then anyone who has permission to update the
type of asset being processed in the workflow is assigned to the task. You can
configure multiple assignments for a task.

## Resource Action Assignments [](id=resource-action-assignments)

*Resource actions* are operations performed by users on an application or
entity. For example, a user might have permission to update Message Boards
Messages. This is called an UPDATE resource action, because the user can update
the resource. If you're uncertain about what resource actions are, refer to the
developer tutorial on the 
[permission system](/develop/tutorials/-/knowledge_base/7-1/adding-permissions-to-resources)
for a more detailed explanation.

To find all the resource actions that have been created, you need access to the
Roles Admin application in the Control Panel (in other words, you need
permission for the VIEW action on the roles resource).

- Navigate to Control Panel &rarr; Users &rarr; Roles.
- Add a new Regular Role. See the 
  [article on managing roles](/discover/portal/-/knowledge_base/7-1/roles-and-permissions)
  for more information.
- Once the role is added, navigate to the Define Permissions interface for the
  role.
- Find the resource whose action should define your workflow assignment.

Here's what the assignment's XML looks like:

    <assignments>
        <resource-actions>
            <resource-action>UPDATE</resource-action>
        </resource-actions>
    </assignments>

Now when the workflow proceeds to the task with the resource action assignment,
users with `UPDATE` permission on the resource (for example, Message Boards
Messages) are notified of the task and can assign it to themselves (if the
notification is set to Task Assignees). Specifically, users see the tasks in
their *My Workflow Tasks* application under the tab *Assigned to My Roles*.

Use all upper case letters for resource action names. Here are some common
resource actions:

    UPDATE
    ADD
    DELETE
    VIEW
    PERMISSIONS
    SUBSCRIBE
    ADD_DISCUSSION

Determine the probable resource action name from the permissions screen for a
resource. For example, in Message Boards, one of the permissions displayed on
that screen is *Add Discussion*. Convert that to all uppercase and replace the
space with an underscore, and you have the action name. 

## Task Timers [](id=task-timers)

Task timers trigger an action after a specified time period passes. Timers are
useful for ensuring a task does not go unattended for a long time. Available
timer actions include sending an additional notification, reassigning the asset,
or creating a timer action.

    <task-timers>
        <task-timer>
            <name></name>
            <delay>
                <duration>1</duration>
                <scale>hour</scale>
            </delay>
            <blocking>false</blocking>
            <recurrence>
                <duration>10</duration>
                <scale>minute</scale>
            </recurrence>
            <timer-actions>
                <timer-notification>
                    <name></name>
                    <template></template>
                    <template-language>text</template-language>
                    <notification-type>im</notification-type>
                </timer-notification>
            </timer-actions>
        </task-timer>
    </task-timers>

The above task timer creates a notification. Specify a time period in the
`<delay>` tag, and specify what action to take when the time expires in the
`<timer-actions>` block. The `<blocking>` element specifies whether the timer
actions may recur. If blocking is set to `false`, timer actions may recur. In
a `recurrence` element, specify the recurrence interval using a `duration` and
a `scale`, as demonstrated above. The above recurrence element specifies that
the timer actions run again every ten minutes after the initial occurrence.
Setting blocking to true prevents timer actions from recurring.

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

The above snippet demonstrates how to set up a reassignment action.

Tasks are at the core of the workflow definition. Once you understand how to
create tasks and the other 
[workflow
nodes](/discover/portal/-/knowledge_base/7-1/workflow-definition-nodes) and add
transitions between the nodes, you're on the cusp of workflow wizard-hood.
