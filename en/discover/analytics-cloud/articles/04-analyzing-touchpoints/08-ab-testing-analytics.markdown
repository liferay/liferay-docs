# A/B Testing Analytics

| This is beta documentation and describes functionality that is not complete.

A/B Testing evaluates the effectiveness of Content Pages by testing multiple
versions/layouts of the Page at once. This is done by creating Page Variants off
the original Page, testing the Page with a goal (e.g., clicks), and publishing
the most effective Variant. You can learn more about creating an A/B test and
configuring it for a Content Page in Liferay DXP's
[A/B Testing](/docs/7-2/user/-/knowledge_base/u/a-b-testing) documentation.

All results from an A/B test running in Liferay DXP are tracked by Analytics
Cloud. An A/B test is synced with Analytics Cloud once it's created. From there,
you can manage the A/B test from Analytics Cloud. To view all drafted, running,
terminated, and completed A/B tests, go to the *Tests* menu from the left
column.

![Figure 1: A complete history of your A/B tests are available in Analytics Cloud.](../../images/ab-test-view.png)

For a drafted A/B test, you can manage its

- *Target*: the Experience and User Segment.
- *Metric*: the goal to track (e.g., Bounce Rate or Click).
- *Variants*: the Page Variants for users to interact with.
- *Traffic Split*: the percentage of visitors that are randomly split between
  the Variants when visiting the Page.
- *Confidence Level*: the accuracy of the test results.

![Figure 2: Analytics Cloud offers a visual checklist for an A/B test's setup.](../../images/ab-test-draft-setup.png)

See Liferay DXP's [A/B Testing](/docs/7-2/user/-/knowledge_base/u/a-b-testing)
documentation for more information on an A/B test's setup.

Once your A/B test is running, Analytics Cloud offers several reports to keep
you up-to-date on your A/B test's progress:

- *Summary*
- *Variant Report*
- *Test Sessions*

<!-- Go through these options when development stabilizes. -->

You can always terminate the running A/B test by selecting *Terminate* from the
Summary bar.

![Figure 3: Terminating an A/B test allows you to delete the test, if desired.](../../images/ab-test-terminate.png)

Once your A/B test finishes, you can publish the winner from the Summary bar.

![Figure 4: Click *Publish Winner* to publish the winning Variant.](../../images/ab-test-winner-declared.png)

By viewing the generated analytics for your A/B tests, you're constantly
informed on how they're progressing. With the provided data, you can confidently
choose the best Experience for your Site's users.
