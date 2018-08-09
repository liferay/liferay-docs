# Managing Workflows [](id=managing-workflows)

Like other resources, workflow definitions can be added, edited, and deleted.
But that's just the beginning of workflow management.

- Because workflow definitions can be complex works in progress, they can be versioned. 

- Unpublished drafts can be saved.

- Because workflow definitions are XML files, they're portable. Thus, they can also be uploaded. 

Start by learning basic workflow management.

## Adding, Editing, and Deleting [](id=adding-editing-and-deleting)

To add a workflow definition,

1.  Go to Control Panel &rarr; Configuration &rarr; Workflow.

2.  In the default view (Workflows), click the Add button
    (![Add](../../../images/icon-add.png)).

3.  From here you're either 
    [writing an XML definition](/develop/tutorials/-/knowledge_base/7-1/crafting-xml-workflow-definitions), 
    [designing a definition in Kaleo Designer (DXP only)](https://customer.liferay.com/documentation/7.1/admin/-/official_documentation/portal/kaleo-designer), 
    or
    [uploading an existing definition](#uploading-workflow-definitions).

To edit a definition, 

1.  Go to Control Panel &rarr; Configuration &rarr; Workflow.

2.  Click the *Actions* button (![Actions](../../../images/icon-actions.png)) 
    for the workflow, and click *Edit*.

To delete a definition,

1.  Go to Control Panel &rarr; Configuration &rarr; Workflow.

2.  Click the *Actions* button (![Actions](../../../images/icon-actions.png)) 
    for the workflow, and click *Unpublish*.

    A published workflow cannot be deleted, so you must unpublish its workflow
    definition first. 

    You can't unpublish a definition if it's activated for an asset. First
    dissociate the workflow definition from any assets that use it. See
    [here](/discover/portal/-/knowledge_base/7-1/activating-workflow) for more
    information.

## Uploading Workflow Definitions [](id=uploading-workflow-definitions)

If you have a local XML definition file (perhaps you want to create a new
workflow based on one of the
[embedded workflows](/discover/portal/-/knowledge_base/7-1/workflow#embedded-workflows)),
upload it to @product@:

1.  Navigate to Control Panel &rarr; Configuration &rarr; Workflow.

2.  Click the *Add* button (![Add](../../images/icon-add.png)).

3.  Name the workflow; then click the link to *import a file*. 

4.  Find the XML file and upload it. Once uploaded, the definition's XML 
    appears in the workflow editor.

5.  If it's ready to publish, click *Publish*. Otherwise, *Save* it and it stays
    Unpublished.

Liferay DXP customers use the 
[Kaleo Designer](https://customer.liferay.com/documentation/7.1/admin/-/official_documentation/portal/kaleo-designer)
to create workflows by default. To upload existing XML files in Kaleo Designer,

1.  Navigate to Control Panel &rarr; Configuration &rarr; Workflow.

2.  Click the *Add* button (![Add](../../images/icon-add.png)).

    The Kaleo Designer palette appears.

3.  Click the *Source* tab.

4.  Click the hyperlink *import a file* in the sentence

    `Write your definition or import a file`

5.  Find the XML file and upload it. Once uploaded, the definition's XML 
    appears in the workflow editor. 

6.  To edit using the Kaleo Designer drag-and-drop interface, click the
    *Diagram* tab.

7.  If it's ready to publish, click *Publish*. Otherwise, *Save* it and it stays
    Unpublished.

What's the difference between saving and publishing?

## Published Versus Unpublished [](id=published-versus-unpublished)

The difference between a published and unpublished workflow is important:

**Published:** Validation is complete, and the workflow can be assigned to 
assets.

**Unpublished:** Validation is not performed on the unpublished workflow, and it
cannot be assigned to assets until it's published.

## Workflow Versions [](id=workflow-versions)

You're making a simple edit to a workflow, when suddenly you remember you have a
meeting with your boss. Quickly you save the workflow and hurry off to your
meeting. Congratulations! You were promoted to Director of Business
Productivity! You have no time to edit workflows now, so your colleague must
finish editing and publishing the workflow. Unfortunately, in all the excitement
of your promotion, you forgot what you changed in the workflow. It's best to
revert to the prior version and start editing it from scratch.

1.  Open the workflow editor. Go to *Control Panel* &rarr; *Configuration*
    &rarr; *Workflow*, and select the workflow from the list.

2.  Open the information panel. Click the *Information* button
    (![Information](../../images/icon-information.png))

3.  There are two information panel sections: Details and Revision
    History.

    The Details screen shows information about the creation of the workflow and
    its last modification, and a summary of the total modifications.

    The Revision History screen shows the workflow's current and prior,
    restorable versions. To view an old workflow or to restore it if you're sure
    it's the right version, click the *Actions* button
    (![Actions](../../images/icon-actions.png)) and select either *Preview* or
    *Restore*.

4.  When you click *Restore* and see the success message, the prior version
    is the current version of the workflow. You can now edit the restored
    version of the workflow. 

5.  If edits are necessary, edit and click *Update*. This creates another
    version of the workflow.

![Figure 1: View and restore prior versions of a workflow.](../../images/workflow-revisions.png)

Alternatively, you can refer to the 
[embedded definitions](/discover/portal/-/knowledge_base/7-1/workflow#embedded-workflows)
to get workflow definition ideas. 
