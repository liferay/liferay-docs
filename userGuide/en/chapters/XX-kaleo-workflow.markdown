## Workflow with Kaleo

Liferay Portal includes a workflow engine called Kaleo. In Greek, this word means “called ones,” which is appropriate for a workflow engine that will be calling users to participate in a process that has been designed for them.

Kaleo workflow allows a user to define any number of simple to complex business processes/workflows, deploy them, and manage them through a portal interface. Those processes have knowledge of users, groups, and roles without writing a single line of code—it only requires the creation of a single XML document.

### Installation

Liferay's Kaleo workflow engine ships with CE versions of Liferay. If you have EE or if you have uninstalled it, the plugin can be installed through the built-in plugin repository. The name is `kaleo-web`{.western}, and you'll find it in the list of web plugins. Installing the plugin will add a number of new options to the Control Panel.

-   My Workflow Tasks

-   Workflow Configuration

-   My Submissions

-   Workflow

There is one default workflow that is bundled with the `kaleo-web`{.western} plugin: Single Approver Workflow. This workflow requires one approval before an asset can be published. One of the conveniences of using Liferay's workflow engine is that any roles that are specified in the workflow definition are created automatically when the definition is deployed. This provides a level of integration with the portal that third party engines cannot match. The Single Approver Workflow contains three roles each with different scopes. The scope of each role can be easily deduced by their names - Community Content Reviewer, Organization Content Reviewer, and Portal Content Reviewer.

### Kaleo Workflow in a Nutshell

Liferay's Kaleo workflows are defined in an XML file and executed by users on the portal. Administrators can create as many different workflow definitions as they need to manage the work done on their portal. You can define new user roles in the workflow to manage the approval process or use roles that already exist in your portal.

#### Process Definitions

Each workflow definition is defined by a single XML file. The XML file has several parts which define the workflow. To get an idea of how this works, we'll be examining the default `single-approver-definition.xml`{.western} file which is included in the the Liferay Kaleo plugin.

The key parts of the workflow definition are the asset, states, transitions, and tasks. The asset is whatever piece of content is being reviewed and approved in the workflow. States represent stages of the workflow; for example: created, rejected, or approved. Transitions occur between states, and indicate what the next state should be. Tasks are steps in the workflow that require user action.

Generally speaking a state will contain a task and the user input from the task will determine which transition will occur. The transition will then move the workflow to the next task. This cycle will continue until the end “approved” state is reached.

![image](../../images/portal-admin-ch7_html_m4f6bc60e.png)\
*Illustration 2: The default single approver workflow. Arrows represent transitions, and boxes represent states and tasks.*

First we define the schema. For Liferay workflows using Kaleo, `liferay-worklow-definition-6_0_0.xsd`{.western} should be your schema. You can find this schema in the `definitions`{.western} folder of the Liferay source or a good XML editor should be able to cache it from Liferay's website.

    <workflow-definition

    xmlns="urn:liferay.com:liferay-workflow\_6.0.0"

    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

    xsi:schemaLocation="urn:liferay.com:liferay-workflow\_6.0.0
    http://www.liferay.com/dtd/liferay-workflow-definition\_6\_0\_0.xsd"
    \>

Next we define a name and description for the workflow. This will appearcin the Control Panel when you are choosing and configuring workflows.

    <name\>Single Approver</name\>

    <description\>A single approver can approve a workflow
    content.</description\>

    <version\>1</version\>

After that, we need to define our initial state. In this case, in is simply that the asset has been created. States can contain actions and transitions. Actions can contain scripts. You can specify the language of the script with the <script-language\> tag. Scripts can be written in Groovy, JavaScript, Ruby, or Python. Transitions will move you to a new state or task.

    <state\>

    <name\>created</name\>

    <initial\>true</initial\>

From the initial state, we transition to a new state where the asset will need to be reviewed.

    <transitions\>

    <transition\>

    <name\>review</name\>

    <target\>review</target\>

    <default\>true</default\>

    </transition\>

    </transitions\>

    </state\>

After that, we create a task. The task has several parts, and is the most complex part of the definition. In the task, we first need to choose a role to notify that there's new content which is in need of review. If the role doesn't exist, defining it here causes it to be created automatically.

The first task listed in the `single-approver-definition.xml`{.western} workflow definition is actually not the first task in the workflow. It is the *update* task. This is the task that will be assigned by the workflow if the asset is rejected by an approver. It is listed first because it is the default task: when this task is triggered, the workflow process has been reset back to the beginning. In this task, the asset is assigned back to the content creator, who will receive an email notification and be required to resubmit the asset. Once the task is resubmitted, it goes back to the review stage.

You can also see that the task is assigned to `<user/>`{.western}*. *This tag will always assign the task back to the user who created the asset.

    <task\>

    <name\>update</name\>

    <actions\>

    <notification\>

    <name\>Creator Modification Notification</name\>

    <execution-type\>onAssignment</execution-type\>

    <template\>Your submission was rejected by a reviewer, please modify and resubmit.</template\>

    <template-language\>text</template-language\>

    <notification-type\>email</notification-type\>

    </notification\>

    </actions\>

    <assignments\>

    <user /\>

    </assignments\>

    <transitions\>

    <transition\>

    <name\>resubmit</name\>

    <target\>review</target\>

    <default\>true</default\>

    </transition\>

    </transitions\>

    </task\>

The review task is the first task in the workflow . This is where users on the portal need to review the content to move to the next step.

Once the transition has been made to this task, a notification is sent out to those who are assigned to the task. You can edit the name or content of the notification in the XML file.

    <task\>

    <name\>review</name\>

    <actions\>

    <notification\>

    <name\>Review Notification</name\>

    <execution-type\>onAssignment</execution-type\>

    <template\>You have a new submission waiting for your review in the workflow.</template\>

    <template-language\>text</template-language\>

    <notification-type\>email</notification-type\>

    </notification\>

    </actions\>

You must also assign the task to a specific role or roles. This role does not have to be the role which you notified if, for example, you wanted to notify all of the content creators any time a new item was submitted. Regardless of who else you are notifying, you will definitely want to notify anyone who will be responsible for approving content.

Notifications need an `exectution-type`{.western}* *which can be `onAssignment`{.western}*, *`onEntry`{.western}*, *or*
*`onExit`{.western}*.*

-   `onEntry`{.western} generates and sends the notification when the user logs in to the portal.

-   `onExit`{.western} generates and sends the notification when the user logs out.

-   `onAssignment`{.western} generates and sends the notification as soon as the user is assigned the task in the workflow. `onAssignment`{.western} notification will not work if you wish to notify a user that is not part of the workflow.

Notifications also need a `notification-type`{.western}* *which can be `email`{.western}, `im`{.western}, or `private-message`{.western}. Your notification type and execution type should complement each other. You wouldn't generally want to use an `onExit`{.western} execution type with a private message, because the user won't receive that message until he or she logs back in. Generally speaking, email notifications work best with `onExit`{.western} or `onAssignment`{.western}, while IM or private message work better with `onEntry`{.western}.

Email and private message notifications can also be created as plain text, or you can create formatted content using Freemarker or Velocity templating languages. When creating the notification, you need to specify the `template-language`{.western} as `text`{.western}*, *`freemarker`{.western}*, *or `velocity`{.western}*.*

In this workflow, anyone who would be capable of approving the content is notified `onAssignment`{.western}. This includes administrators, and community and organization owners. The `role-type`{.western} tag helps the system sort out who should be receiving the notification based on the scope, and can be set as *community, organization, *or *portal.*

    <assignments\>

    <roles\>

    <role\>

    <role-type\>community</role-type\>

    <name\>Community Administrator</name\>

    </role\>

    <role\>

    <role-type\>community</role-type\>

    <name\>Community Content Reviewer</name\>

    </role\>

    <role\>

    <role-type\>community</role-type\>

    <name\>Community Owner</name\>

    </role\>

    <role\>

    <role-type\>organization</role-type\>

    <name\>Organization Administrator</name\>

    </role\>

    <role\>

    <role-type\>organization</role-type\>

    <name\>Organization Content Reviewer</name\>

    </role\>

    <role\>

    <role-type\>organization</role-type\>

    <name\>Organization Owner</name\>

    </role\>

    <role\>

    <role-type\>regular</role-type\>

    <name\>Portal Content Reviewer</name\>

    </role\>

    <role\>

    <role-type\>regular</role-type\>

    <name\>Administrator</name\>

    </role\>

    </roles\>

    </assignments\>

Once the content is approved, you will want to transition to a new state. In this case, we have only need of a single approver, so we will transition to the final approved state. In more complex workflows, this might transition to a second tier approver.

    <transitions\>

    <transition\>

    <name\>approve</name\>

    <target\>approved</target\>

    <default\>true</default\>

    </transition\>

    <transition\>

    <name\>reject</name\>

    <target\>update</target\>

    <default\>false</default\>

    </transition\>

    </transitions\>

    </task\>

Finally, we define our end state, which runs a script to set the state of the content to approved, in the portal. The script is part of the Kaleo workflow engine. Any workflow customizations can be completely contained within XML workflow definitions.

You could also write a customized script if there were actions outside of the standard one that need to be performed on your asset. The default script, written in Javascript, sets the status of the asset to *approved.* You can add additional information into the script through Javascript, or you can change the <script-language\> to another supported language (Ruby, Groovy, or Python) and rewrite the action with additional details to meet your needs.

    <state\>

    <name\>approved</name\>

    <actions\>

    <action\>

    <name\>approve</name\>

    <execution-type\>onEntry</execution-type\>

    <script\>

    <![CDATA[

    Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus("approved"), workflowContext);

    ]]\>

    </script\>

    <script-language\>javascript</script-language\>

    </action\>

    </actions\>

    </state\>

To create longer workflows, you would simply create additional states, tasks and transitions similar to the ones in the single approver definition, and create additional reviewer roles. For instance, if you wanted to have a second level of review before an item is approved, you could create a new task in between the *review* task and the *approved* state. The task itself would have similar content to *review*, but you would assigned to a different role – either one that you have already created, or a new one generated by Kaleo. You would set the *review* task to transition to your new task, and set the new task to transition to the *approved* state, once it is completed. You can also use *forks *and *joins * to create more complex workflows.

![image](../../images/portal-admin-ch7_html_6d2265ce.png)\
*Illustration 3: Parallel approval design*

You can transition to a fork from a task or state, and from a fork, you can transition to multiple tasks or states which will occur in parallel. In the above example, when we have multiple transitions from one task, they are mutually exclusive –- you either trigger one or the other. The transitions are also serial, meaning that one must occur, and then the next one can occur. With a parallel workflow, you can have different approvals going through different users at the same time. For example, you could use this to separate two different departments' approval
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
	
To bring a fork back together, you would transition both nodes of the fork back to a single join. A join is formatted similarly to a fork, except that any transitions are serial, not parallel, as in the example below.

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

Another important consideration when creating a parallel approval is that each node will need it's own "rejected" states for cases where content is approved in one node, but rejected in another.
	
	
##### Due Dates

When you're creating a task for a workflow, you can configure due date options. The due date for an item isn't set as a specific day, but as a period of time after the task is assigned. For example, you could set the due date for a specific task to be two days after the task is assigned. This is all configured in the XML definition file, and there is currently no GUI option to configure this setting.

The due date options are formatted in the definitions file like this:

    <task\>

    <name\></name\>

    <description/\></description\>

    <due-date-duration\>{any whole number}</due-date-duration\>

    <due-date-scale\>{second, minute, hour, day, week, month, year}<due-date-scale\>

    ...

    </task\>

The due date is set inside the task with the two elements: a duration and a scale. The duration can be any whole number, and is completely meaningless without the scale. The scale tells you what units the duration is measured in, valid values for this are *second, minute, hour, day, week, month, *and *year*.

Here's an example of how this can work practically: you could set the duration to *10*, and then set the scale to be *hour*. This would mean that the task would be due 10 hours after it was assigned. If you edited the definition file, and changed *hour* to *day*, that would mean that the task would need to be completed within 10 days after in was assigned.

#### Workflow in the Control Panel

![image](../../images/portal-admin-ch7_html_m506bb3c6.png)\
*Illustration 4: Adding a workflow definition*

Most of your workflow configuration is done via the Control Panel. Everything you need to do in the portal can be done through simple GUI controls.

##### Workflow

Workflow is found under the Portal heading in the Control Panel. There are three options under Workflow. *Definitions, Default Configuration, *and *Submissions.*

Before you do anything else, you'll need to add workflow definitions through the Definitions to make them available. By default only the Single Approver workflow appears here. Clicking *Add* allows you to enter a title for a new workflow definition and upload the XML file. Once you add a file here, it is added to the previous page.

Under *Default Configuration* you can set the default workflow behavior for each content related application on the portal. You can choose to use no workflow, which is the default, or select any installed workflow definition. Setting the default configuration will cause any newly created Communities or Organizations to default to that configuration. An Administrator can then edit the definitions for each one individually through the *Workflow Configuration *page.

Clicking on *Submissions* will let you view any currently pending assets, or any assets which were previously approved.

##### Workflow Configuration

After you have uploaded workflow definitions and set the default workflow behavior you can go up to *Workflow Configuration* and tweak the definitions that you are using for each Community and Organization individually.

![image](../../images/portal-admin-ch7_html_191c17d.png)\
*Illustration 5: The Workflow Configuration page*

Using the drop down feature on the Control Panel section, you can select any community or organization in the portal. All of the options under that heading, including Workflow Configuration, now apply to that particular group.

##### My Workflow Tasks

My Workflow Tasks is a personalized version of the Workflow Tasks, and it is found in your personal section of the Control Panel. Here are specific tasks which have been assigned to you or assigned to a role of which you are a member. You can also view your completed tasks.

![image](../../images/portal-admin-ch7_html_677770ed.png)\
*Illustration 6: My Workflow Tasks page*

It is here that workflow users review and approve content. By clicking on the actions next to a piece of content, a user can view that content and then choose to approve or reject it and add comments.

##### My Submissions

My Submissions is found under your user's personal information in the Control Panel. From this screen you can view any assets that you have submitted to review. Those that are currently under review are listed under the *Pending* tab, and those that have gone through the review process are listed under the *Completed* tab.

![image](../../images/portal-admin-ch7_html_m320e803c.png)\
*Illustration 7: The My Submissions page*

Besides viewing your work, you can also withdraw a submission from the review process by clicking on *Withdraw Submission* from the *Pending* tab.

#### Integrating with Users, Communities, Organizations and Roles

The Kaleo workflow engine is deeply integrated with Liferay Portal. It can generate roles scoped for Organizations, Communities, and for the whole Portal based on workflow definitions. You can also customize workflow options based on individual communities and organizations,

##### Users and Roles

Users are the most important part of the workflow, since they're the ones who do all the work. To make a user a part of the workflow process, you assign them a role which you defined in your workflow . When you are creating your workflow definition, you can create new roles by defining them in the XML file, or by using roles which you have already created in your portal. Roles created automatically are always portal scoped, so if you want to use Community or Organization scoped roles, create the roles before deploying your workflow to the portal.

##### Communities and Organizations

A Portal Administrator can create a default workflow definition scheme for each application which will apply for the entire portal, and a Community or Organization Administrator can customize the settings for their community or organization.

### Using Kaleo Workflow Processes in Liferay Portal

Before workflow can be used, you must define which types of assets on the portal are workflow-enabled. If you have created additional definitions, you must also define the workflow definition to use for each asset that is workflow-enabled.

![image](../../images/portal-admin-ch7_html_m4d3dfbf0.png)\
*Illustration 8: You can select which community or organization you are currently working in by using the drop down menu over the Content section.*

To demonstrate how this works when you configure it. we will create a press release. Press releases should be posted in the *Newsroom *section of the website, so before changing setting specific workflow configuration options or creating content, create the Newsroom community and switch to it in the Control Panel. After going to Workflow Configuration, set Web Content to use the Single Approver workflow.

To demonstrate the workflow process, create two users – a Content Creator and a Content Reviewer. The Content Creator logs in and creates a new Press Release for Spartan Software and clicks *Submit for Publication*. This triggers the workflow process and notifies the Content Reviewer. When the Content Reviewer logs in, he can assign the workflow task to himself and approve the content.

![image](../../images/portal-admin-ch7_html_79402c48.png)\
*Illustration 9: Before a Content Reviewer can approve content, he must assign it to himself, or have an administrator assign it to them.*

Once the content is approved, it can be posted on the Press Releases page in a web content display portlet.

As you can see, Liferay Portal and the Kaleo Workflow engine combine to create a very robust environment for web content management. Simple workflows can be managed using the default configuration and GUI tools, while more complex workflows can be created to meet the workflow management needs of almost any portal.
