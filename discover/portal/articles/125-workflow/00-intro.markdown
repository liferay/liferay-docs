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

<!-- Do you need a real life example to convince you that workflow is
important? Grab a cup of coffee and settle in. Story about ancient Greek
philosophers (web sites) competing rhetorically for followers and financial
supporters (users, customers, advertisers). One of them runs his arguments by
another philosopher for review, and finds his way to fame (single approver
workflow). The other does not, and is forgotten to history. Names: Phlegmaticus
and Sanguineus -->
