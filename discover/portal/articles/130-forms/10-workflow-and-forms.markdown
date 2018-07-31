# Workflow and Forms [](id=sending-form-entries-through-a-workflow)

[Kaleo](/discover/portal/-/knowledge_base/7-1/workflow)
is a workflow engine for sending a submitted asset through a workflow process
before it's published. Most assets are configured to use workflow at the
instance or site level.

![Figure 1: Workflow is enabled in the Control Panel or in Site Administration for most @product@ assets.](../../images/workflow-configuration.png)

Forms is different, and that's why its assets don't appear in the above image.
There are so many use cases for forms, and there could be so many per site, that
a site---or instance---scoped workflow configuration won't serve your needs 
well. Instead, configure workflow for *each form* separately.

## Enabling Workflow in a Form [](id=enabling-workflow-in-a-form)

To enable workflow in a form: 

1.  Open the form's editor by opening the Menu, selecting your Site, navigating 
    to *Content  &rarr; Forms*, and clicking on the form you're interested in.

    ![Figure 2: Navigate directly to a form to enable workflow.](../../images/forms-list.png)

2.  Click the Options button (![Options](../../images/icon-options.png)) and
    choose *Settings*.

3.  The Settings window has a *Select a Workflow* drop-down. Find the workflow
    you're interested in, select it, and then click *Done*.

    ![Figure 3: Enable workflow for each form in its Settings window.](../../images/form-settings.png)

## Testing the Workflow [](id=testing-the-workflow)

Test the workflow process:

1.  Add the form to a page.

2.  Click *Submit for Publication* to submit the form entry.

3.  Approve the entry.

    If you're using the Single Approver workflow and you're the administrative
    user, you'll see a notification come in after submitting the form entry,
    alerting you that there's a form record to review. 

Next go find the form entry in the Forms widget:

1.  Go back to the Forms application in the Menu in your site's *Content* 
    section.

2.  Click the Form's *Actions* button 
    (![Actions](../../images/icon-actions.png)) and select *View Entries*.

    The entry is currently marked *Pending*.
    
Now approve the form record:

1.  Navigate to *My Account &rarr; My Workflow tasks*.

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

![Figure 4: Each entry's status is visible in the Forms application's Form Entries screen.](../../images/forms-view-entries-status.png)

## Example Use Case: Win a Free, All-Inclusive Trip to the Lunar Resort [](id=example-win-a-free-all-inclusive-trip-to-the-lunar-resort)

The Lunar Resort is giving away a free, all-inclusive trip to several lucky
families. All a user must do is click the *I Want a Free Trip* link from the
site homepage, and they're directed to a form they can fill out and submit in
under three minutes:

![Figure 5: The Lunar Resort Giveaway form is ready to be filled out.](../../images/lunar-resort-giveaway.png)

The Lunar Resort wants to exclude folks who don't meet certain qualifications:

-   Exclude families with more than five persons in the household.
-   Prioritize families who've never visited The Lunar resort.
-   Exclude anyone who's on the resort's naughty list, appropriately titled "The
    Dark Side of the Moon".

To compile a list of finalists, each form entry should go through a workflow
with one review task. The Single Approver workflow works nicely for this.

After creating the form in the above image, select the Single Approver workflow
from the form's Settings window, as described in the
[above section](#enabling-workflow-in-a-form).
Publish the form, then fill out and submit an entry. The administrative user
receives a notification that a form record was submitted. Go to the View Entries
screen and see that the form record is currently marked as Pending. Once the
review is completed, it's marked as Approved.

![Figure 6: Assign a workflow to a form in several steps.](../../images/lunar-resort-giveaway-workflow-new.gif)
