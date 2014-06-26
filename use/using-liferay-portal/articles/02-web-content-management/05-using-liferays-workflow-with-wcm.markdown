# Using Liferay's Workflow with WCM [](id=using-liferays-workflow-with-wcm-lp-6-2-use-useportal)

Workflow is essentially a predetermined sequence of connected steps. In Liferay
WCM, workflow is designed to manage the creation, modification and publication
of web content. You can set up a workflow so content can't be published without
going through an approval process you design. In this way, content is published
to the site only after it has been reviewed and approved.

Liferay's workflow engine is called Kaleo workflow and it ships with Liferay CE.
If you have uninstalled it or are using EE, it needs to be installed and
configured separately. This is covered in chapter 6. Since we have somewhat of a
"What came first--the chicken or the egg?" problem, for now, we'll assume it's
installed and look at how you can take advantage of workflow in getting your
content through any approval steps between creation and publication.

You may have noticed something appears to be missing from the staging process
discussed above. In particular, you might be asking the question, "How do I
reject changes?" Staging is integrated with Liferay's Workflow engine. To have a
review process for staged pages, you need to make sure you have a workflow
engine configured and you have staging set up in the workflow. To do this,
select the workflow definition desired for page revisions in the Workflow
Configuration.

When using a workflow, clicking *Submit for Publication* submits the staged
pages into the workflow. Once all necessary approvals have been completed, the
page status is marked as ready for publication. The *Publish to Live Now* and
*Schedule for Publication* options publish the last version of the selected
pages marked as ready for publication.

To enable workflow for Web Content, navigate to the Control Panel and select
*Workflow Configuration*. From there, select a workflow that has been deployed
to Liferay.

![Figure 2.23: You can select the pre-made *Single Approver* workflow to experiment with workflow management.](../../images/04-web-content-workflow-config.png)

As you'll discover in chapter 10, you can design workflows to suit your
organization's approval process. For the Lunar Resort's implementation we'll use
the *Single Approver* workflow which ships with the product.

## Defining Workflows for Web Content 

Let's set up Liferay's Workflow for the Lunar Resort web site. You must have the
Kaleo workflow plugin installed in order for the workflow categories to appear
in the Control Panel. Liferay's Kaleo workflow engine ships with CE versions of
Liferay. For installation instructions for Liferay EE, please see chapter 10.

1. Go to the Site Administration page and select *Workflow Configuration* from
the *Configuration* section.

2. From the select box, choose *Single Approver* for Web Content. Click *Save*.
Note that you can add workflow to many of Liferay's portlets.

That's all it takes to set up workflow for web content. Now that workflow is
enabled, publishing content works a little bit differently. Let's go through the
process of publishing details for new class offerings at the Lunar Resort.
Return to the home page and click the *Add Web Content* icon on the Web Content
Display portlet. Call the new content *Course Offerings* and enter some content.
Notice that the Publish button is now gone. In its place is a *Submit for
Publication* button. Go ahead and click it.

Next, go to the *Workflow Tasks* in Site Administration and select *My Workflow
Tasks*. You will see the option to Review Content for Sales Goals. It shows
because you are logged in as an Administrator. There is also a Content Approvers
role which is defined by this workflow and anyone in this role can approve
content as well.

To approve the content, you must first take ownership of it. Click on the task.
You should see the screen below.

Taking ownership of, reviewing and approving content is very easy:

1.  Click the *Assign to Me* button. Alternatively, you could assign it to
    someone else in the Content Approvers role or create / update a due date for
    the content's approval.
2.  Once you've assigned it to yourself, buttons allowing you to approve or
    reject the content appear. Click *Approve*.
3.  You're asked to submit a comment. You'd have to do this for either *Approve*
    or *Reject*. Add a comment and click *Save*.
4.  The content is now approved.

In a real world situation, you obviously wouldn't want the person who created
the content to be the one who approves it. Instead, you would have one or more
roles designed for users who will be creating content and you would have
specific users assigned to one or more roles for approving content. Our example
was of a very straightforward workflow, as it has only a single approver. Kaleo
workflow allows you to design workflows that go through as many steps as you
need to conform to your business processes. We look at Kaleo workflow in more
detail in chapter 6.
