---
header-id: workflow
---

# Workflow

[TOC levels=1-4]

Liferay's workflow engine is named *Kaleo*. In Greek, Kaleo means "called
ones," which is appropriate for a workflow engine that calls users to
participate in a review process designed for them. Kaleo workflow makes it
possible to define any number of simple to complex business
processes/workflows, deploy them, and manage them through a portal interface.
The processes have knowledge of Users, Groups and Roles. You don't have to
write a single line of code to accomplish this: it's defined in an XML
document. If you're a Liferay Digital Experience Platform (DXP) customer, you
have access to a nifty visual designer for workflows, called Kaleo Designer. 

There are several steps to effective workflowing: 

- [Designing review processes in XML](/docs/7-2/reference/-/knowledge_base/r/crafting-xml-workflow-definitions)

- (DXP only) [Designing review processes in Kaleo Designer](/docs/7-2/user/-/knowledge_base/u/managing-workflows#uploading-workflow-definitions)

- [Uploading workflow definitions](/docs/7-2/user/-/knowledge_base/u/managing-workflows#uploading-workflow-definitions)

- [Activating workflow for enabled assets](/docs/7-2/user/-/knowledge_base/u/activating-workflow)

- [Managing Workflow definitions](/docs/7-2/user/-/knowledge_base/u/managing-workflows)

- [Sending assets through review](/docs/7-2/user/-/knowledge_base/u/reviewing-assets)

After all that, you'll be familiar with using Liferay's Kaleo workflow to set up
approval processes for any 
[workflow-enabled content](/docs/7-2/user/-/knowledge_base/u/activating-workflow).

## What's New with Workflow?

There are some noteworthy enhancements to the workflow functionality:

### DXP Feature: Workflow Metrics

For DXP subscribers, the concept of _Workflow Metrics_ was introduced. Set
definition-specific deadlines and use a stopwatch-like timer to track and report
workflow events. There are two new concepts around this feature: Service Level
Agreements (SLAs), and Workflow Reports.

#### Service Level Agreements (SLAs)

SLAs can be formally agreed-upon deadlines between you and your customers,
tracking the response and resolution times for a submitted workflow instance.
But you can also set them up informally to track how long any workflow event
generally takes.

![Figure 3: Use Service Level Agreements (SLAs) to define how workflow metrics are reported.](../../images/workflow-add-sla.png)

#### Workflow Reports

Once an SLA is set, workflow submissions that trigger the SLA timer are
automatically reported on by the workflow engine.

![Figure x: See Workflow Reports generated based on your SLAs.](../../images/workflow-report.png)

### Control Panel Reorganization

The Workflow section of the Control Panel is now a top-level section with its
own subcategories: Process Builder and Submissions (Metrics, too, if you're a
DXP subscriber). In @product@ 7.1 Workflow was nested under Control Panel
&rarr; Configuration.

![Figure 1: Workflow has a top-level entry in the Control Panel.](../../images/workflow-menu.png)

### Workflow Definition Permissions: System Settings

The Workflow System Settings category (Control Panel &rarr; Configuration &rarr;
System Settings) has a new system scoped configuration entry: _Workflow
Definitions_. There's just one configuration option, but it's important:
Enabling it gives administrators permission to publish workflows and scripts.

![Figure 2: Explicit permission must be granted before administrators are allowed to publish and edit workflow definitions.](../../images/workflow-publication-permission.png)

Create your own workflows from scratch or leverage existing workflows.

## Embedded Workflows

In addition to the Single Approver definition, there are some workflow
definitions that ship with @product@ but are not pre-installed, since they're
primarily included for test cases. They can be found in the Liferay source code
in

    /modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions

They're also in your Liferay installation. Open your Liferay installation's
`osgi/portal/com.liferay.portal.workflow.kaleo.runtime.impl.jar`, and then find
and open the `com.liferay.workflow.kaleo.runtime.impl-[version].jar`. The
definitions are in the `META-INF/definitions` folder. 
