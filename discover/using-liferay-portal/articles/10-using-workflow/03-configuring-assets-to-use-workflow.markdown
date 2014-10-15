# Configuring Assets to Use Workflow Process Definitions [](id=configuring-assets-to-use-workflow-process-definitions)

Most of your workflow configuration is done via the control panel. Everything
you need to do in the portal can be done through simple GUI controls.

![Figure 10.3: Adding a Workflow
Definition](../../images/kaleo-workflow-new-definition-control-panel.png)

You can find the Workflow section under the Portal heading in the control panel.
There are three options under Workflow: *Definitions*, *Default Configuration*
and *Submissions*.

If you created a new workflow definition, you need to add it so it can be used
in the portal. Click *Definitions*. By default, only the Single Approver
workflow appears here. Clicking *Add* allows you to enter a title for a new
workflow definition and upload the XML file. Once you add a file here, it's
added to the portal and is immediately available for use.

Under *Default Configuration*, you can set the workflow behavior (if any) for
all workflow-enabled applications on the portal. You can choose to use no
workflow, which is the default, or select any installed workflow definition.
Setting the default configuration causes any newly created sites to default to
that configuration. An administrator can then edit the definitions for each one
individually through the *Workflow Configuration* page.

Clicking on *Submissions* will let you view any currently pending assets or any
assets which were previously approved.

## Configuring Workflow [](id=configuring-workflow)

After you have uploaded workflow definitions and set the default workflow
behavior you can go up to *Workflow Configuration* and tweak the definitions
you're using for each site individually.

![Figure 10.4: The Workflow Configuration
Page](../../images/kaleo-workflow-configuration.png)

Using the context selector drop-down menu in the control panel, you can select
any site in the portal. All the options under that heading, including Workflow
Configuration, now apply to that particular site. Using workflow is just as
easy. 

## My Workflow Tasks [](id=my-workflow-tasks)

My Workflow Tasks is a personalized version of the Workflow Tasks and it's found
in your personal section of the control panel. Here are specific tasks which
have been assigned to you or assigned to a role of which you are a member. You
can also view your completed tasks.

![Figure 10.5: My Workflow Tasks Page](../../images/kaleo-workflow-my-tasks.png)

It's here workflow users review and approve content. By clicking on the actions
next to a piece of content, a user can view the content, then choose to approve
or reject it and add comments. 

## My Submissions [](id=my-submissions)

My Submissions is found under your user's personal information in the control
panel. From this screen you can view any assets  you have submitted to review.
Those currently under review are listed under the *Pending* tab and those that
have been reviewed are listed under the *Completed* tab.

![Figure 10.6: The My Submissions
Page](../../images/kaleo-workflow-my-submissions.png)

Besides viewing your work, you can also withdraw a submission from the review
process by clicking on *Withdraw Submission* from the *Pending* tab.

## Using Kaleo Workflow Processes in Liferay Portal [](id=using-kaleo-workflow-processes-in-liferay-portal)

Before workflow can be used, you must define which types of assets on the portal
are workflow-enabled. If you have created additional definitions, you must also
choose the workflow definition to use for each asset that is workflow-enabled.

![Figure 10.7: You can select which site to work on by using the drop-down menu
in the Content section of the control
panel.](../../images/kaleo-workflow-control-panel-context-selector.png)

To demonstrate how this works, we'll create a press release. Press releases
should be posted in the *Newsroom* section of the web site, so before setting
specific workflow configuration options or creating content, create the Newsroom
site and switch to it in the control panel. In Workflow Configuration, set Web
Content to use the Single Approver workflow.

Next, create two users, a Content Creator and a Content Reviewer. The Content
Creator logs in and creates a new press release for Nose-ster and clicks *Submit
for Publication*. This triggers the workflow process and notifies the Content
Reviewer. When the Content Reviewer logs in, he or she can assign the workflow
task to him- or herself and approve the content.

![Figure 10.8: Before a Content Reviewer can approve content, he must assign it
to himself or have an administrator assign it to
him.](../../images/kaleo-workflow-assign-to-me.png)

Once the content is approved, it can be posted on the Press Releases page in a
web content display portlet. 

There's more. EE customers get extra features that enable them to create
workflows without having to deal with XML. 
