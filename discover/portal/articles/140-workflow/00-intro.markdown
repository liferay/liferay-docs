# Workflow [](id=workflow)

Liferay's workflow engine is named *Kaleo*. In Greek, Kaleo means "called ones,"
which is appropriate for a workflow engine that calls users to participate in
a review process designed for them. Kaleo workflow makes it possible to define
any number of simple to complex business processes/workflows, deploy them, and
manage them through a portal interface. The processes have knowledge of Users,
Groups and Roles. You don't have to write a single line of code to accomplish
this: it can be defined in an XML document, or if you're a Liferay Digital
Experience Platform (DXP) customer, you have access to a nifty visual designer
for workflows, called the Workflow Designer. 

There are several steps to effective workflowing: 

- [Designing review processes in XML](/develop/tutorials/-/knowledge_base/7-1/crafting-xml-workflow-definitions)

- [DXP only] [Designing review process in Kaleo Designer](/discover/portal/knowledge_base/7-1/kaleo-designer)

- [Uploading workflow definitions](#uploading-workflow-definitions)

- [Activating workflow for enabled assets](/discover/portal/-/knowledge_base/7-1/activating-workflow)

- Managing Workflow definitions (not yet written)

- Sending assets through review (not yet written)

After all that, you'll be familiar with using Liferay's Kaleo workflow to set up
approval processes for any 
[workflow-enabled content](/discover/portal/-/knowledge_base/7-1/activating-workflow).

## What's New with Workflow [](id=whats-new-with-workflow)

There are some noteworthy enhancements to the workflow functionality:

- Duplicate a workflow.

- Preview and restore a prior version.

- Save draft workflows and retain them in an unpublished state (this is a new
  feature for Liferay Portal CE workflow users).

- All Control Panel workflow functionality is unified under one entry, found at
  Control Panel &rarr; Configuration &rarr; Workflow.

- Create workflows (using a text editor for Liferay Portal CE users, and the Workflow
  Designer for Liferay DXP customers) at Control Panel &rarr; Configuration
  &rarr; Workflow. Just click the Add button.

Create your own workflows from scratch or leverage existing workflows.

## Embedded Workflows [](id=embedded-workflows)

In addition to the Single Approver definition, there are some workflow
definitions that ship with @product@ but are not pre-installed, since they're
primarily included for test cases. They can be found in the Liferay source code
in
`/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions`,
or in your Liferay installation itself. Open your Liferay installation's
`osgi/portal/com.liferay.portal.workflow.kaleo.runtime.impl.jar`, and then find and open
the `com.liferay.workflow.kaleo.runtime.impl-[version].jar`. The definitions are
in the `META-INF/definitions` folder (see the diagrams
[here](/develop/tutorials/-/knowledge_base/6-2/designing-a-kaleo-workflow-definition)).

## Uploading Workflow Definitions [](id=uploading-workflow-definitions)

If you have an XML definition file locally (perhaps one of the embedded
workflows, upload it:

1.  Navigate to Control Panel &rarr; Configuration &rarr; Workflow.

2.  Click the Add button (![Add](../../images/icon-add.png)).

3.  Give the workflow a descriptive title; then click the link to _import a
    file_. 

4.  Find the XML file and upload it. Once uploaded, its XML is displayed in the
    workflow editor and it can be further edited if necessary.

5.  If it's ready to publish, click *Publish*. Otherwise, *Save* it and it stays
    Unpublished.

## Published Versus Unpublished [](id=published-versus-unpublished)

The difference between a published and unpublished workflow is important:

Published
: Validation is complete, and the workflow can be assigned to assets.

Unpublished
: Validation is not performed on unpublished workflows, and they cannot be
assigned to assets until they're published.

## Workflow Versions [](id=workflow-versions)

You're making a simple edit to a workflow, when suddenly you remember you were
supposed to have a meeting with your boss. Quickly you save the workflow and
hurry off to your meeting. Congratulations! You were promoted to Director of
Business Productivity! You have no time to edit workflows now, so your colleague
must finish your edits and publish the workflow. Unfortunately, in all the
excitement of your promotion, you forgot what you did to the workflow. It's best
to revert to the prior version and start editing it from scratch.

1.  Open the workflow editor. Go to *Control Panel* &rarr; *Configuration*
    &rarr; *Workflow*, and click the workflow from the list.

2.  Open the information panel. Click the Information button
    (![Information](../../images/icon-information.png))

3.  There are two sections to the information panel: Details and Revision
    History.

    The Details screen shows information about the creation of the workflow,
    information about its last modification, and a summary of the total
    modifications.

    The Revision History screen shows the current and prior, restorable versions
    of the workflow. To view an old workflow or to restore it if you're sure
    it's the right version, click the Actions button
    (![Actions](../../images/icon-actions.png)) and select either *Preview* or
    *Restore*.

4.  When you click *Restore* and see the success message, the prior version
    becomes the current version of the workflow. You can now edit the restored
    version of the workflow. 

5.  If edits are necessary, edit and click *Update*. This creates another
    version of the workflow.

![Figure 1: View and restore prior versions of a workflow.](../../images/workflow-revisions.png)

Alternatively, you can look at the pre-existing definitions to get ideas while
designing and building your own definition. 

<!-- Do you need a real life example to convince you that workflow is
important? Grab a cup of coffee and settle in. Story about ancient Greek
philosophers (web sites) competing rhetorically for followers and financial
supporters (users, customers, advertisers). One of them runs his arguments by
another philosopher for review, and finds his way to fame (single approver
workflow). The other does not, and is forgotten to history. Names: Phlegmaticus
and Sanguineus -->
