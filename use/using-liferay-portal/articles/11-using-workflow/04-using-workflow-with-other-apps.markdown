# Using workflow with other applications [](id=using-workflow-with-other-applications)

Above we saw an example of using workflow with Liferay web content. The process
is the same for all resources that are workflow-enabled. However, while
configuring your resources to use workflow in the Default Configuration tab of
the Workflow section, you may have noticed a note that "The workflows for the
following resources can be configured within their respective portlets".
resources listed are *Documents* and *Dynamic Data Lists Records*. Workflow
configuration is more specifically enabled for Documents and Media folders and
Dynamic Data Lists (i.e., each individaul list). 

To see how this works for a Documents and Media folder, create a new page in
the default site called *Documents and Media* and add the Documents and Media
portlet to this page. Then click *Add* &rarr; *Folder*, enter the name *My
Documents*, and click *Save*. Mouse over your new My Documents folder, click on
the black arrow that appears at the top right corner of the folder, and select
*Edit*.

![Figure 11.9: Workflow for Documents and Media must be enabled at the folder level. Edit a folder to select a workflow.](../../images/11-workflow-edit-folder.png)

By default, the *Use document type restrictions and workflow of the parent
folder* button is selected. To enable workflow for this folder, select the
*Define specific document type restrictions and workflow for this folder*
button. After you've selected this button, a *Default Workflow for all Document
Types* drop-down menu appears. By default, you can select *No workflow* or
*Single Approver*. Any custom workflows that you added also appear in this
drop-down menu. You can add custom workflows through the Workflow page in the
Portal section of the Control Panel.

![Figure 11.10: You can use the document type restrictions and workflow of the parent folder or you can define specific document type restrictions and workflow for this folder.](../../images/11-folder-workflow.png)

After you've selected the *Single Approver* workflow and clicked *Save*,
workflow takes effect for the My Documents folder. Try adding a new document to
your My Documents folder--notice that the *Publish* button now says *Submit for
Publication* since workflow is enabled. Any users assigned to the (Portal,
Organization, or Site) Content Reviewer roles can see that your document has
been submitted for publication by navigating to My Account and clicking on *My
Workflow Tasks*. Ordinarily, the same user who submitted a document for
publication wouldn't also approve it but we'll do this to demonstrate how the
process works.

Like with Documents and Media folders, workflow is configured for a Dynamic
Data List at the list level. That is, when you add a New List for users, you
can enable workflow for that particular list. No Workflow is the default
setting, but you can select the Single Approver definition or any definition
you uploaded to use in the portal. When a user submits an item to a list that
was configured to use a workflow, they'll see the item's status as Pending.
Once the list item has been approved by an administrative user, the status
changes to Approved. 

<!--When I try to add a Document to a workflow-enabled folder, I cannot.
There is no option under Add to add a folder. It works for folders not
workflow-enabled. An already created document cannot be moved to a workflow
enabled folder either. So I couldn't test this step properly.-->

Navigate to the My Account and click on *My Workflow Tasks*. Documents and Data
Lists that you submitted for publication appear under *Assigned to My Roles*
since you're an administrator. Click *Actions* &rarr; *Assign to Me* next to
your document. Then click *Actions* &rarr; *Approve* next to the document or
list when it appears in the *Assigned to Me* category. That's it--your
resources have passed through the workflow!
