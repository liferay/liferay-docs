# Creating A/B Tests

[TOC levels=1-4]

To begin leveraging A/B Testing, you must first create an A/B test. You cannot
create a test on an Experience that already has an active test running.

To create an AB test,

1.  Navigate to the Content Page you want to test.

2.  Click the AB Testing (![AB Testing](../../../images-dxp/icon-ab-testing.png))
    button from the Control Menu.

3.  Choose the Experience for which you want to test. A test can be performed on
    the Default Experience as well as a personalized Experience mapped to a
    Segment.

4.  Click *Create Test*.

5.  Assign the test a name and description (optional).

6.  Assign the goal you want the test to track. There are four to choose from:

    - *Bounce Rate*: the percentage of users who visit the page and then navigate
      away from the site without visiting another page.
    - *Click*: the average number of clicks initiated on the page's assets.
    - *Scroll Depth*: the average depth users scrolled down on the page.
    - *Time On Page*: the average duration users spent on the page.

    ![Figure 1: Fill out the form to create your AB test.](../../../images-dxp/create-ab-test.png)

7.  Click *Save*.

You now have an AB test! Notice that the test's status in in *Draft*. This means
it's not yet visible to visitors.

| **Note:** You can only create an AB test for one page/experience at a time.

| **Note:** The *Control* entity represents the currently published Content
| Page.

![Figure 2: You now have an AB test, but there are additional configurations you can apply.](../../../images-dxp/new-ab-test.png)

Now it's time to create your test variant(s). A test variant is a customization
of the Experience you want to optimize. An AB test must contain at least one
variant before it can run.

To create a variant,

1.  In the A/B Test menu, click *Create Variant*.

2.  Give the variant a name and click *Save*.

3.  Select the new variant's Edit button
    ![Edit](../../../images-dxp/icon-edit.png). The current Control page's
    content/formatting is copied and displayed as the baseline for the variant.

4.  Edit the variant as desired. Then click *Save Variant*.

You now have a variant of the control page. You can create as many variants as
you want.

Once you're finished creating variants for your AB test, you're ready to run the
AB test. You'll learn how to do this next.
