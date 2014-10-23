# Using Workflow with Other Applications [](id=using-workflow-with-other-applications)

We saw an example of how to use workflow with Liferay web content in chapter 2.
In this section, we'll discuss how to use workflow with other applications.
First, we'll look at using workflow with Documents and Media. After that, we'll
look at using workflow with Blogs, Wikis, and Message Boards.

## Documents and Media [](id=documents-and-media)

You can enable workflow for most portal resources, including page revisions for
staging, web content, and collaborative applications, from the Control Panel.
However, workflow for Documents and Media can only be enabled within a Documents
and Media portlet since it must be defined at the folder level. To see how this
works, create a new page in the default site called *Documents and Media* and
add the Documents and Media portlet to this page. Then click *Add* &rarr;
*Folder*, enter the name *My Documents*, and click *Save*. Mouse over your new
My Documents folder, click on the gray icon that appears at the top right corner
of the folder, and select *Edit*.

![Figure 10.9: Workflow for Documents and Media must be enabled at the folder
level. Edit a folder to select a workflow.](../../images/folder-edit.png)

By default, the *Use document type restrictions and workflow of the parent
folder* button is selected. To enable workflow for this folder, select the
*Define specific document type restrictions and workflow for this folder*
button. After you've selected this button, a *Default Workflow for all Document
Types* drop-down menu appears. By default, you can select *No workflow* or
*Single Approver*. Any custom workflows that you added also appear in this
drop-down menu. You can add custom workflows through the Workflow page in the
Portal section of the Control Panel.

![Figure 10.10: You can use the document type restrictions and workflow of the
parent folder or you can define specific document type restrictions and workflow
for this folder.](../../images/folder-workflow.png)

After you've selected the *Single Approver* workflow and clicked *Save*,
workflow takes effect for the My Documents folder. Try adding a new document to
your My Documents folder--notice that the *Publish* button now says *Submit for
Publication* since workflow is enabled. Any users assigned to the (Portal,
Organization, or Site) Content Reviewer roles can see that your document has
been submitted for publication by navigating to the Control Panel and clicking
on *My Workflow Tasks*. Ordinarily, the same user who submitted a document for
publication wouldn't also approve it but we'll do this to demonstrate how the
process works.

Navigate to the Control Panel and click on *My Workflow Tasks*. The document
that you submitted for publication appears under *Assigned to My Roles* since
you're an administrator. Click *Actions* &rarr; *Assign to Me* next to your
document. Then click *Actions* &rarr; *Approve* next to the document when it
appears in the *Assigned to Me* category. That's it--your document has passed
through the workflow!

## Collaboration [](id=collaboration)

To enable workflow for collaborative applications, first navigate to the Control
Panel and select a scope in the context menu selector. You can enable workflow
globally, or for a specific site. Once you've chosen a scope, click on *Workflow
Configuration* in the content section of the Control Panel. This page lists the
portal resources for which you can select a workflow.

![Figure 10.11: The Workflow Configuration page of the Control Panel lists the
resources for which can select a workflow for your chosen
scope.](../../images/workflow-configuration.png)

To select a workflow, click on the Workflow drop-down menu and choose a workflow.
By default, you can only select *No workflow* or *Single Approver*. Custom
workflows that you added also appear in this drop-down menu. You can add custom
workflows through the Workflow page in the Portal section of the Control Panel.
To enable workflow for the collaborative applications, select the *Single
Approver* for the Blogs Entry, Message Boards Message and Wiki Page resources,
then click *Save.*

To test the workflow for collaborative applications, add a page called
*Collaboration* to the site whose scope you selected when you enabled workflow.
Then add the Blogs, Message Boards, and Wiki portlets to this page. Using each
portlet, add a blog entry, post a new message board thread, and write some
content for the Wiki frontpage. For each application, notice that the *Publish*
button is replaced by a *Submit for Publication* button since workflow is
enabled.

The workflow process for collaborative applications works the same way as in the
examples you've already seen. Any users assigned to the (Portal, Organization,
or Site) Content Reviewer roles can see that your blog post, message board
thread, and wiki article have been submitted for publication by navigating to
the Control Panel and clicking on *My Workflow Tasks*. Of course, since you
submitted these items for publication, it wouldn't make sense in a real-world
use case for you to approve them. However, to see how the workflow process
works, go ahead and approve these items yourself; you can do this since you're
an administrator.

Navigate to the Control Panel and click on *My Workflow Tasks*. The items you've
submitted for publication appear under *Assigned to My Roles*. Click *Actions*
&rarr; *Assign to Me* next to the items you've submitted. Then click *Actions*
&rarr; *Approve* next to each item when it appears in the *Assigned to Me*
category. That's it--your blog post, message board thread, and wiki article have
passed through the workflow!
