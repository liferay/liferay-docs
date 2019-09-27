---
header-id: creating-a-b-tests
---

# Creating A/B Tests

[TOC levels=1-4]

To begin leveraging A/B Testing, you must first create an A/B test. You cannot
create a test on an Experience that already has an active test running.

To create an A/B test,

1.  Navigate to the Content Page you want to test.

2.  Click the A/B Testing (![AB Testing](../../../images-dxp/icon-ab-testing.png))
    button from the Control Menu.

3.  Choose the Experience you want to test. This option is only available when
    you have a custom Experience (an Experience other than the default).

    A test can be performed on the Default Experience as well as a personalized
    Experience mapped to a Segment. When an Experience is being used in an A/B
    test, it cannot be edited. Deleting a Page/Experience being used in an A/B
    test also deletes the test for that Page.

4.  Click *Create Test*.

5.  Assign the test a name and description (optional).

6.  Assign the goal you want the test to track. There are two:

    *Bounce Rate*: the percentage of users who don't exhibit any activities on
    the page (click, scroll, etc.) and then navigate away from the site
    without visiting another page.

    *Click*: the percentage of users who clicked on the page (per session).

<!--
    *Scroll Depth*: the average depth users scrolled down on the page.

    *Time On Page*: the average duration users spent on the page.
-->

![Figure 1: Fill out the form to create your A/B test.](../../../images-dxp/create-ab-test.png)

7.  Click *Save*.

You now have an A/B test! Notice that the test's status is *Draft*. This means
it's not yet visible to visitors.

| **Note:** You can only create an A/B test for one page/experience at a time.

| **Note:** The *Control* entity represents the currently published Content
| Page.

![Figure 2: You now have an A/B test, but there are additional configurations you can apply.](../../../images-dxp/new-ab-test.png)

You can always edit or delete the new A/B test by clicking the *Actions* button
(![Actions](../../../images-dxp/icon-actions.png)) in the top right of the A/B
Test menu. Deleted tests are not recoverable (i.e., not sent to the Recycle
Bin). These options are not available for an active test. You can also, at any
time, view your A/B Testing history by selecting the *History* tab. This
displays all completed and terminated A/B tests.

Now it's time to create your test Variant(s). A test Variant is a customization
of the Experience you want to optimize. An A/B test must contain at least one
Variant before it can run.

To create a Variant,

1.  In the A/B Test menu, click *Create Variant*.

2.  Give the Variant a name and click *Save*.

3.  Select the new Variant's Edit button
    ![Edit](../../../images-dxp/icon-edit.png). The current Control page's
    content/formatting is copied and displayed as the baseline for the Variant.

4.  Edit the Variant as desired. Then click *Save Variant*.

You now have a Variant of the Control Page. You can create as many Variants as
you want.

If you selected the Click goal, you must select the clickable element you want
to target on the Control and Variant pages. If you selected a different goal,
you can skip the steps below.

To configure the Click goal target,

1.  Click *Set Target* under the Click Goal heading of your A/B Test. Any
    clickable element on the page is highlighted.

    | **Note:** Only links and buttons with an ID attribute can be selected as a
    | target for the Click goal.

2.  Select the element you want to set as the click target for your Control and
    Variant pages.

    ![Figure 3: Set the click target to be tracked.](../../../images-dxp/set-click-target.png)

Your Click goal is now set! You can edit the target at any time before starting
the test.

![Figure 4: Once the click target is set, you can run the A/B test.](../../../images-dxp/click-goal-set.png)

Once you're finished creating Variants and configuring goals for your A/B test,
you're ready to run the A/B test. You'll learn how to do this next.
