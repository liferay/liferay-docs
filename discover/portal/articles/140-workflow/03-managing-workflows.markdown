# Managing Workflows

Like other resources, workflow definitions can be added, edited, and deleted.
But that's just the beginning of workflow management.

- Because they can be complex works in progress, workflow definition versioning
is supported.

- Unpublished drafts can be saved.

- Because workflow definitions are XML files, they're portable. Thus, they can also be uploaded. 

Start by learning about basic workflow management.

## Adding, Editing, and Deleting

To add a workflow definition,

1.  Go to Control Panel &rarr; Configuration &rarr; Workflow.

2.  In the default view (Workflows), click the Add button
    (![Add](../../../images/icon-add.png)).

3.  From here you're either 
    [writing an XML definition](/develop/tutorials/-/knowledge_base/7-1/crafting-xml-workflow-definitions), 
    [designing a definition in Kaleo Designer (DXP only)](/discover/portal/-/knowledge_base/7-1/kaleo-designer), 
    or uploading an existing definition ([see
    below](#uploading-workflow-definitions)).

To edit a definition, 

1.  Go to Control Panel &rarr; Configuration &rarr; Workflow.

2.  Click the Actions button (![Actions](../../../images/icon-actions.png)) for
    the workflow to edit, and click *Edit*.

To delete a definition,

1.  Go to Control Panel &rarr; Configuration &rarr; Workflow.

2.  Click the Actions button (![Actions](../../../images/icon-actions.png)) for
    the workflow to edit, and click *Unpublish*.

    Published workflows cannot be deleted, so you must unpublish the workflow
    definition first. 

    You can't unpublish a definition if it's activated for an asset. First
    dissociate the workflow definition from any assets that use it. See
    [here](/discover/portal/-/knowledge_base/7-1/activating-workflow) for more
    information.

## Uploading Workflow Definitions [](id=uploading-workflow-definitions)

If you have an XML definition file locally (perhaps one of the embedded
workflows), upload it to Liferay Portal:

1.  Navigate to Control Panel &rarr; Configuration &rarr; Workflow.

2.  Click the Add button (![Add](../../images/icon-add.png)).

3.  Give the workflow a descriptive title; then click the link to _import a
    file_. 

4.  Find the XML file and upload it. Once uploaded, its XML is displayed in the
    workflow editor and it can be further edited if necessary.

5.  If it's ready to publish, click *Publish*. Otherwise, *Save* it and it stays
    Unpublished.

DXP customers use the 
[Kaleo Designer](https://customer.liferay.com/documentation/7.1/admin/-/official_documentation/portal/kaleo-designer)
to create workflows by default. To upload existing XML files in the Designer,

1.  Navigate to Control Panel &rarr; Configuration &rarr; Workflow.

2.  Click the Add button (![Add](../../images/icon-add.png)).

    The Kaleo Designer palette appears.

3.  Click the *Source* tab.

4.  Click the hyperlink *import a file* in the sentence

        Write your definition or import a file

5.  Find the XML file and upload it. Once uploaded, its XML is displayed in the
    workflow editor and it can be further edited if necessary. 

6.  To edit using the Kaleo Designer drag-and-drop interface, click the
    *Diagram* tab.

7.  If it's ready to publish, click *Publish*. Otherwise, *Save* it and it stays
    Unpublished.

What's the difference between saving and publishing?

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

Alternatively, you can look at the 
[embedded definitions](/discover/portal/-/knowledge_base/7-1/workflow#embedded-workflows)
to get ideas while designing and building your own definition. 

