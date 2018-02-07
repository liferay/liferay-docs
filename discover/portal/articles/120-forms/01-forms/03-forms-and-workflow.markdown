# Sending Form Entries through a Workflow [](id=sending-form-entries-through-a-workflow)

@product@ has a [workflow
feature](/discover/portal/-/knowledge_base/7-0/using-workflow) that directs a
submitted asset through a workflow process before it's published. Most assets
are configured to use workflow at the instance or site level.

![Figure 1: Workflow is enabled in the Control Panel or in Site Administration
for most @product@ assets.](../../../images/workflow-configuration.png)

Forms is different, and that's why its assets don't appear in the above image.
There are so many use cases for forms, and there could be so many per site, that
a site- or instance-scoped workflow configuration won't serve your needs well.
Instead, the Forms application lets you configure the workflow that's used for
*each form*. That means that each submitted form record will go through
a workflow process before being marked as *Approved*. Configure workflow for
a form in its Settings window.

## Enabling Workflow in a Form [](id=enabling-workflow-in-a-form)

To enable workflow in a form: 

- Open the form's editor by navigating to
*Site Administration &rarr; Content &rarr; Forms*, and clicking on the form
you're interested in.

    ![Figure 2: Navigate directly to a form to enable workflow.](../../../images/forms-list.png)

- Click the Options button (![Options](../../../images/icon-options.png)) and
    choose *Settings*.

    ![Figure 3: Enable workflow for each form in its Settings window.](../../../images/form-settings.png)

- The Settings window has a *Select a Workflow* dropdown. Find the workflow
    you're interested in, select it, and then click *Done*.

    ![Figure 4: Select the form's workflow using the Select a Workflow dropdown
menu.](../../../images/forms-workflow.png)

## Testing the Workflow [](id=testing-the-workflow)

Test the workflow process by submitting a form entry. If you're using the Single
Approver workflow that ships with @product@ and you're the administrative user,
you'll see a notification come in after submitting the form entry, alerting you
that there's a form record to review. 

Next go find the form entry in the Forms application:

- Go back to the Forms application in Site Administration.
- Click the Form's Actions button (![Actions](../../../images/icon-actions.png))
    and select *View Entries*.

The entry is currently marked *Pending*. Now approve the form record:

- Navigate to *My Account &rarr; My Workflow tasks*.
- Click the *Assigned to My Roles* tab.
- Click on the form entry.
- Click the Actions button (![Actions](../../../images/icon-actions.png)) and
    choose *Assign to Me*.
- Click *Done*.
- Click the Actions button (![Actions](../../../images/icon-actions.png)) again,
    then click *Approve*.
- Click *Done* again.

Navigate back to the View Entries screen for the form, and now the entry is
marked as *Approved*. 

![Figure 5: Each entry's status is visible in the Forms application's View
Entries screen.](../../../images/forms-view-entries-status.png)

## Example: Win a Free, All-Inclusive Trip to the Lunar Resort [](id=example-win-a-free-all-inclusive-trip-to-the-lunar-resort)

The Lunar Resort is giving away a free, all-inclusive trip to several lucky
families. All a user must do is click the *I Want a Free Trip* link from the
site homepage, and they're directed to a form they can fill out and submit in
under three minutes:

![Figure 6: The Lunar Resort Giveaway form is ready to be filled out.](../../../images/lunar-resort-giveaway.png)

The Lunar Resort wants to exclude folks who don't meet certain qualifications:

- Exclude families with more than five persons in the household.
- Prioritize families who've never visited The Lunar resort before.
- Exclude anyone who's on the resort's naughty list, appropriately titled "The
    Dark Side of the Moon".

To compile a list of finalists, each form entry should go through a workflow
with one review task. The Single Approver workflow that ships with @product@
works nicely for this.

After creating the form in the above image, select the Single Approver workflow
from the form's Settings window, as described in the [above
section](#enabling-workflow-in-a-form). Publish the form, then fill out and
submit an entry. The administrative user receives a notification that a form
record was submitted. Go to the View Entries screen and see that the form record
is currently marked as Pending. Once the review is completed, it's marked as
Approved.

![Figure 7: Assign a workflow to a form in several steps.](../../../images/lunar-resort-giveaway-workflow-new.gif)

