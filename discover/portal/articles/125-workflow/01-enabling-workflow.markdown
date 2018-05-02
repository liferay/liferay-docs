# Enabling Workflow [](id=enabling-workflow)

The workflow functionality bundled with the Liferay Forms and Workflow suite comes
with one pre-installed workflow definition called the Single Approver Workflow. This
workflow requires one approval before an asset can be published. One of the
conveniences of using Liferay's workflow engine is that any roles specified in
the workflow definition are created automatically when the definition is
deployed. This provides a level of integration with the portal that third party
engines cannot match. The Single Approver Workflow contains three roles, each
with different scopes. You can deduce the scope of each role by its name: Site
Content Reviewer, Organization Content Reviewer and Portal Content Reviewer.

![Figure 1: The Single Approver Definition comes included with the Liferay Forms and Workflow suite.](../../images/single-approver.png)

A word about designing workflows. If you are a CE <!--Change if necessary-->
customer, you'll have to get comfortable working with XML to create workflow
definitions. There's a tutorial in the documentation for developers that will
help you learn how to do that<!--LINK-->. 

<!-- If you're a DXP customer, you have access to the Kaleo Designer
workflow builder, which gives you a nice user interface where you drag and drop
workflow nodes onto a canvas to create your workflow definitions. If you want to
start with one of the definitions mentioned above, editing it to suit your needs
would be a simple task with the Kaleo Designer. -->

<!--Picture of Workflow Designer-->

## Workflow Assets [](id=workflow-assets)

Many of the assets in @product@ are enabled for workflow. Configure workflow or
these assets in Control Panel &rarr; Configuration &rarr; Workflow Configuration:

- Blogs Entry
- Calendar Event
- Comments
- Message Boards Message
- Page Revision
- User
- Wiki Page

Configure workflow for these assets at the site level in Site &rarr;
Configuration &rarr; Workflow Configuration:

- Blogs Entry
- Calendar Event
- Comments
- Message Boards Message
- Page Revision
- User
- Wiki Page

What's the difference? As with most scoped activities, the higher level setting
(in the Control Panel) sets the default behavior. It's overridden at the more
granular level (in the site menu).

User doesn't appear on the site list because adding users is strictly a
portal-wide administrator activity. Only assets that can be added and configured
at the site level (for example, those that are accessed from the site menu) have
workflow configuration controls at the site level.

Some assets that are workflow enabled are only configured in their respective
application. Web Content articles are workflow enabled from the folder settings
menu:

![Figure 2: Enable workflow on Web Content at the folder level.](../../images/web-content-workflow.png)

Enable workflow on Dynamic Data List entries in each list's Add form:

![Figure 3: Workflow is configured for each individual Dynamic Data List.](../../images/ddl-workflow.png)

Enable workflow for each individual form's entries from the Form Settings screen:

![Figure 4: Enable workflow on each form's entries in the Form Settings window.](../../images/forms-workflow2.png)

## Workflow Behavior [](id=workflow-behavior)

Most of the resources listed above behave just as you might expect with workflow
enabled: The Publish button for the resource's *Add* form is replaced by a
*Submit for Publication* button, and instead of instant publication, the asset
has its status set as *Pending* and must proceed through the workflow
before publication. 

![Figure 5: Instead of a Publish button, a Submit for Publication button appears
for workflow-enabled resources.](../../images/submit-for-publication.png)

Page revisions are slightly different. Page revisions only occur in 
[staging environments](/discover/deployment/-/knowledge_base/7-0/staging-content-for-publication) 
that have Page Versioning enabled. When a Page Variation or Site Page Variation
is created, its creator must click *Submit for Publication* at the top of the
page, and the variation must be approved in the workflow, before it can be
published to the live site.

![Figure 6: With workflow enabled on Page Revisions, the site administrator must submit their page variation for publication before it can go live.](../../images/page-revision-submission.png)



