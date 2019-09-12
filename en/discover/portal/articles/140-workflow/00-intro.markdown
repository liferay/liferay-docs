---
header-id: workflow
---

# Workflow

[TOC levels=1-4]

Liferay's workflow engine is named *Kaleo*. In Greek, Kaleo means "called ones,"
which is appropriate for a workflow engine that calls users to participate in
a review process designed for them. Kaleo workflow makes it possible to define
any number of simple to complex business processes/workflows, deploy them, and
manage them through a portal interface. The processes have knowledge of Users,
Groups and Roles. You don't have to write a single line of code to accomplish
this: it can be defined in an XML document, or if you're a Liferay Digital
Experience Platform (DXP) customer, you have access to a nifty visual designer
for workflows, called Kaleo Designer. 

There are several steps to effective workflowing: 

- [Designing review processes in XML](/docs/7-1/tutorials/-/knowledge_base/t/crafting-xml-workflow-definitions)

- (DXP only) [Designing review processes in Kaleo Designer](https://customer.liferay.com/documentation/7.1/admin/-/official_documentation/portal/kaleo-designer)

- [Uploading workflow definitions](/docs/7-1/user/-/knowledge_base/u/managing-workflows#uploading-workflow-definitions)

- [Activating workflow for enabled assets](/docs/7-1/user/-/knowledge_base/u/activating-workflow)

- [Managing Workflow definitions](/docs/7-1/user/-/knowledge_base/u/managing-workflows)

- [Sending assets through review](/docs/7-1/user/-/knowledge_base/u/reviewing-assets)

After all that, you'll be familiar with using Liferay's Kaleo workflow to set up
approval processes for any 
[workflow-enabled content](/docs/7-1/user/-/knowledge_base/u/activating-workflow).

## What's New with Workflow

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

## Embedded Workflows

In addition to the Single Approver definition, there are some workflow
definitions that ship with @product@ but are not pre-installed, since they're
primarily included for test cases. They can be found in the Liferay source code
in
`/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions`,
or in your Liferay installation itself. Open your Liferay installation's
`osgi/portal/com.liferay.portal.workflow.kaleo.runtime.impl.jar`, and then find and open
the `com.liferay.workflow.kaleo.runtime.impl-[version].jar`. The definitions are
in the `META-INF/definitions` folder (see the diagrams
[here](/docs/6-2/tutorials/-/knowledge_base/t/designing-a-kaleo-workflow-definition)).

<!-- Do you need a real life example to convince you that workflow is
important? Grab a cup of coffee and settle in. Story about ancient Greek
philosophers (web sites) competing rhetorically for followers and financial
supporters (users, customers, advertisers). One of them runs his arguments by
another philosopher for review, and finds his way to fame (single approver
workflow). The other does not, and is forgotten to history. Names: Phlegmaticus
and Sanguineus -->
