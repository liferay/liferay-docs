# Configuring assets to use workflow process definitions [](id=configuring-assets-to-use-workflow-process-definitions-lp-6-2-use-useportal)

All your global workflow configuration can be done via the control panel.
Everything you need to do in the portal can be done through simple GUI
controls.

![Figure 11.3: Adding a Workflow Definition](../../images/11-workflow-upload-definition.png)

You can find the Workflow section under the Configuration heading in the
control panel. There are three tabs under Workflow which are used to configure
workflow in the portal: *Definitions*, *Default Configuration* and
*Submissions*. These tabs let you upload definitions, enable workflow for your
portal's resources, and The default workflow behavior you specify here will
apply throughout your Liferay Portal.

From the *Definitions* tab you can see the currently available workflow
definitions and add newly created workflow definitions you'd like to use in the
portal. Clicking *Upload Definition* allows you to enter a title for a new
workflow definition, browse to your local XML file, and upload it to your
Liferay instance. Once you add a file here, it's added to the portal and is
immediately available for use.

Under *Default Configuration*, you can set the workflow behavior for all
workflow-enabled actions in the portal; available resources include Page
Revision, User, Blogs Entries, Web Content Articles, Comments, Message Boards
Messages, and Wiki Pages. You can also add a custom potlet to this list by
using Liferay's API. You can choose from two default workflow options in
Default Configuration: *No Workflow* or *Single Approver*, which we looked at
in detail earlier in the chapter.

When you set the default workflow configuration of a resource, any new action
on that resource defaults to that configuration, throughout the portal. An
administrator can edit the workflow behavior for each site's resources
individually through the *Workflow Configuration* section of a particular
site's Site Administration section. The interface for making changes at the
site level is similar to that of the Control Panel interface; the only
difference is that you are in the Site Administration section and the changes
you specify are only applicable to the scope you have selected here.

In the *Submissions* tab you can see any currently pending assets or any assets
which were previously approved.

Let's learn more about configuring workflows for both the portal and individual
sites next.

## Configuring workflow

After you have uploaded workflow definitions and set the default workflow
behavior you can go up to *Default Configuration* and enable workflow for
specified actions in your portal. 

![Figure 11.4: The Workflow Configuration Page](../../images/11-workflow-default-config.png)

By setting default workflow configuration behavior from the Control Panel, your
specified settings are implemented at the global portal scope. If you need to
set the workflow configuration for a specific site, you can do so from the
*Site Administration* section. You can get there from the Control Panel by
clicking *Sites*, clicking on a site from the list, and opening the
*Configuration* menu. The *Workflow Configuration* option lets you modify
workflow behavior for the sleected site's resources that can be workflow
enabled.

## My Workflow Tasks

My Workflow Tasks is a personalized version of the Workflow Tasks and it's found
in the user's My Account section. Here you'll find specific tasks which were
assigned to you or assigned to a role of which you are a member. You can also
view your completed tasks by opening the Completed tab.

![Figure 11.5: My Workflow Tasks Page](../../images/11-my-workflow-tasks.png)

Workflow administrative users review and approve content from their My Worfklow
Tasks section. By clicking the link to the asset, you can view it, and the
Actions dropdown lets you approve, reject, or reassign assets assigned to you.
For assets assigned to your roles, you can assign the listed assets to be
reviewed by yourself or another user. Whether or not an asset is assigned to
you or to your role, you can update the due date as well. 

## My Submissions

My Submissions is found in your user's My Account section of the portal. From
this screen you can view any assets you have submitted for review. Those
currently under review are listed under the *Pending* tab and those that have
been reviewed are listed under the *Completed* tab.

![Figure 11.6: The My Submissions Page](../../images/11-workflow-my-submissions.png)

Besides viewing your work, you can also withdraw a submission from the review
process by clicking on *Withdraw Submission* from the *Pending* tab.

## Using Kaleo Workflow Processes in Liferay Portal

Before workflow can be used, you must define which types of assets on the portal
are workflow-enabled. If you have created additional definitions, you must also
choose the workflow definition to use for each asset that is workflow-enabled.

![Figure 11.7: You can select which site to work on by using the drop-down menu in the Site Administration section of the portal.](../../images/11-workflow-site-configuration.png)

To demonstrate how this works, we'll create a press release. Press releases
should be posted in the *Newsroom* section of the web site, so before setting
specific workflow configuration options or creating content, create the
Newsroom site. When you click Save, you'll be redirected to the Site
Administration section of the portal where you can work on the Newsroom site
further. Under Configration, in Workflow Configuration, set Web Content to use
the Single Approver workflow.

Next, create two users, a Content Creator and a Content Reviewer. The Content
Creator logs in and creates a new press release for the Lunar Resort and clicks
*Submit for Publication*. This triggers the workflow process and notifies the
Content Reviewer. When the Content Reviewer logs in, he or she can assign the
workflow task to him- or herself and approve the content.

![Figure 11.8: Before a Content Reviewer can approve content, he must assign it to himself or have an administrator assign it to him.](../../images/11-workflow-assign-to-me.png)

Once the content is approved, it can be posted on the Press Releases page in a
Web Content Display portlet. 

There's more. EE customers get extra features that enable them to create
workflows without having to deal with XML. 
