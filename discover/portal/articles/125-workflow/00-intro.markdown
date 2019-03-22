# Using Workflow [](id=using-workflow)

The ancient Greeks were pretty important, so we decided to name Liferay's
workflow engine *Kaleo*. In Greek, Kaleo means "called ones," which is
appropriate for a workflow engine that calls users to participate in a review
process designed for them. Kaleo workflow allows a user to define any number of
simple to complex business processes/workflows, deploy them, and manage them
through a portal interface. The processes have knowledge of users, groups and
roles. You don't have to write a single line of code to accomplish this: all
you have to do is create one XML document. 

<!--And if you're a Liferay EE customer,
you get a graphical workflow designer which gives you a point and click
interface to create workflows. 
Just commenting out for now.
-->

There are several aspects to workflow that you need to understand: 

- Enabling workflow

- Designing review processes

- Uploading workflow definitions

- Configuring assets to use workflows

- Using workflow with other applications

After all that, you should be familiar with how to use Liferay's Kaleo workflow
to set up approval process for any kind of content before it is published to
your portal. 

## Embedded Workflows [](id=embedded-workflows)

In addition to the Single Approver definition, there are some workflow
definitions that ship with Liferay but are not pre-installed, since they're
primarily included for test cases. They can be found in the Liferay source code
in
`modules/aps/forms-and-workflow/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions`,
or in your Liferay installation itself. Open your Liferay installation's
`osgi/marketplace/Liferay CE Forms and Workflow.lpkg`, and then find and open
the `com.liferay.workflow.kaleo.runtime.impl-[version].jar`. The definitions are
in the `META-INF/definitions` folder (see the diagrams
[here](/develop/tutorials/-/knowledge_base/6-2/designing-a-kaleo-workflow-definition)).
Once you have the XML definition files locally, upload them into Liferay:

1.  Navigate to Control Panel &rarr; Configuration &rarr; Workflow Definition.

2.  Click the Add button (![Add](../../images/icon-add.png)).

3.  Give the workflow a descriptive title, then click *Select File* and find
    the XML file. Once uploaded it appears in the list of definitions.

Alternatively, you can look at the pre-existing definitions to get ideas while
designing and building your own definition. 

## Workflow Assignees Have Implicit Asset Permissions [](id=workflow-assignees-have-implicit-asset-permissions)

Users with permission to execute a workflow task (e.g., Users with the Portal
Content Reviewer Role) have full resource action permissions over the assets
they can review. These permissions apply in the My Workflow Tasks widget in the
User's personal page and anywhere else actions on the Asset can be performed.

For example, consider a User with two permissions:

- The Portal Content Reviewer Role enables Users to review workflow
    submissions and grants edit and delete permissions on the content they're
    reviewing. 
- Users also have permission to view Web Content Articles in the Site's
    _Content_ section. 

Neither permission explicitly grants the User management permissions on Web
Content Articles. Users cannot normally edit or delete a Web Content Article,
for example. However, if a Web Content Article is sent to the workflow, Users
can access the Web Content Article for review (in their _Assigned to Me_ or
_Assigned to my Roles_ section of My Workflow Tasks), and they can edit or
delete the content while reviewing it in the workflow. While it's in the status
_Pending_, they can also edit or delete the article from Site Administration
&rarr; Content &rarr; Web Content because of their implicit permissions granted
by the workflow system. This additional permission is temporary, and the normal
resource permissions are activated once the Web Content Article exits the
workflow process (for example, it's rejected or approved).

![Figure 1: A User with VIEW permission on Web Content cannot manage Approved Articles.](../../images/workflow-approved-permissions.png)

![Figure 2: A User with access to Web Content in the Workflow can manage Pending Articles.](../../images/workflow-pending-permissions.png)

<!-- Do you need a real life example to convince you that workflow is
important? Grab a cup of coffee and settle in. Story about ancient Greek
philosophers (web sites) competing rhetorically for followers and financial
supporters (users, customers, advertisers). One of them runs his arguments by
another philosopher for review, and finds his way to fame (single approver
workflow). The other does not, and is forgotten to history. Names: Phlegmaticus
and Sanguineus -->
