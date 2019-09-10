---
header-id: sending-form-entries-through-a-workflow
---

# Workflow and Forms

[TOC levels=1-4]

[The workflow engine](/docs/7-2/user/-/knowledge_base/u/workflow) is for
sending a submitted asset through a workflow process before it's published.
Most assets are configured to use workflow at the instance or Site level.

![Figure 1: Workflow is enabled in the Control Panel or in Site Administration for most @product@ assets.](../../images/forms-workflow-configuration.png)

Forms are different, so they don't appear in the above image.  There are so
many use cases for forms, and there could be so many per site, that a site- or
instance-scoped workflow configuration won't serve your needs well. Instead,
configure workflow for *each form* separately.

## Enabling Workflow in a Form

To enable workflow in a form, 

1.  Open the form's editor by opening the Menu, selecting your Site, navigating 
    to *Content &Data* &rarr; *Forms*, and clicking on the form you want.

2.  Click the Options button (![Options](../../images/icon-options.png)) and
    choose *Settings*.

3.  The Settings window has a *Select a Workflow* drop-down. Find the workflow
    you want, select it, and then click *Done*.

    ![Figure 2: Enable workflow for each form in its Settings window.](../../images/form-settings.png)

## Testing the Workflow

Test the workflow process:

1.  Add the form to a page.

2.  Click *Submit for Publication* to submit the form entry.

Next go find the form entry in the Forms application:

1.  Go back to the Forms application in the Menu in your Site's *Content &
    Data* section.

2.  Click the Form's *Actions* button 
    (![Actions](../../images/icon-actions.png)) and select *View Entries*.

    The entry is currently marked *Pending*.
 
Now [approve the form
record](/docs/7-2/user/-/knowledge_base/u/reviewing-assets):

1.  Navigate to *My Workflow Tasks* in the User Personal Menu.

2.  Click the *Assigned to My Roles* tab.

3.  Click on the form entry.

4.  Click the Actions button (![Actions](../../images/icon-actions.png)) and
    choose *Assign to Me*.

5.  Click *Done*.

6.  Click the Actions button (![Actions](../../images/icon-actions.png)) again, 
    then click *Approve*.

7.  Click *Done* again.

8.  Navigate back to the View Entries screen for the form, and now the entry is
    marked as *Approved*. 

![Figure 3: Each entry's status is visible in the Forms application's Form Entries screen.](../../images/forms-view-entries-status.png)

