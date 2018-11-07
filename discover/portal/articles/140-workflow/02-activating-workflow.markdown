# Activating Workflow [](id=activating-workflow)

Assets are integrated with the 
[workflow framework](/develop/tutorials/-/knowledge_base/7-1/liferays-workflow-framework)
if their content is meant to be sent through review processes. Since this is
most often the case, there are many out of the box assets that integrate with
workflow. In this article, you'll learn how to select a workflow for use with
each of these workflow-enabled assets.

## Workflow Assets [](id=workflow-assets)

Activate a workflow for these assets in Control Panel &rarr; Configuration
&rarr; Workflow, in the Schemes tab:

- Blogs Entry
- Calendar Event
- Comment
- Message Boards Message
- Page Revision
- User
- Web Content Article
- Wiki Page
- Knowledge Base

Activate workflow for these assets at the Site level in Site &rarr;
Configuration &rarr; Workflow:

- Blogs Entry
- Calendar Event
- Comment
- Message Boards Message
- Page Revision
- Web Content Article
- Wiki Page
- Knowledge Base

What's the difference between the Site workflow configuration and the Control
Panel Workflow configuration? As with most scoped configurations, the higher
level setting (in the Control Panel) sets the default behavior. It's overridden
at the more granular level (in the Site menu).

User doesn't appear on the Site list because adding users is strictly a
portal-wide administrator activity. Only assets that can be added and configured
at the Site level (for example, those that are accessed from the Site menu) have
workflow configuration controls at the Site level.

Some assets that are workflow-enabled are activated in their respective
application: 

Activate workflow for Web Content Folders from the folder settings menu:

![Figure 1: Activate workflow on Web Content folders from the folder's edit screen.](../../images/workflow-web-content-folder.png)

Activate workflow for Documents and Media Folders from the folder settings:

![Figure 2: Activate workflow on Documents and Media folders from the folder's
edit screen.](../../images/workflow-dm-folder.png)

Enable workflow on Dynamic Data List entries in each list's Add form:

![Figure 3: Activate workflow for each individual Dynamic Data List.](../../images/workflow-ddl.png)

Activate workflow for each individual form's entries from the Form Settings screen:

![Figure 4: Activate workflow on each form's entries from the Form Settings window.](../../images/workflow-form.png)

## Workflow Behavior [](id=workflow-behavior)

Most of the resources listed above behave just as you might expect with workflow
activated: The Publish button for the resource's *Add* form is replaced by a
*Submit for Publication* button, and instead of instant publication, the asset
has its status set as *Pending* and must proceed through the workflow before
publication. 

![Figure 5: Instead of a Publish button, a Submit for Publication button appears
for workflow-enabled resources.](../../images/submit-for-publication.png)

Page revisions are slightly different. Page revisions only occur in 
[staging environments](/discover/portal/-/knowledge_base/7-1/staging-content-for-publication) 
that have Page Versioning enabled. When a Page Variation or Site Page Variation
is created, its creator must click *Submit for Publication* at the top of the
page, and the variation must be approved in the workflow before it can be
published to the live Site.

![Figure 6: With workflow enabled on Page Revisions, the Site administrator must submit their page variation for publication before it can go live.](../../images/page-revision-submission.png)
