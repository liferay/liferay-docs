# A/B Testing Analytics

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

You'll learn about these next.

## Summary

The Summary Panel gives you an overview of your test. It provides you
information such as % of completion, how many days it has been running, and the
total visitor sessions who has seen your test.

Finally it also gives you a quick glance on what your test metric is, and what
your current best performing variant is.

![Figure x: ](../../images/ab-test-summary.png)

## Variant Report

The Variant Panel provides your with detail breakdown of each variant and how
well each performs.

![Figure x: ](../../images/ab-test-variant-report.png)

Below are the metrics we report for each variant:

- Median:

    Definition: The median is the "number in the middle" of the sample. The
    purpose of this is to get a sense of what a typical user reports.

    Why it is helpful: This is what most people use when comparing 2 metrics.

- Confidence Interval:

    Definition: This is the range of values in which we’re expecting the actual
    <value of interest (think of better way of phrasing this)> to contain. 

    Why it is useful: A user would like to know a range of possible values that
    seem plausible for what we’re measuring. We are able to connect a likelihood
    to a possible range.

- Improvement:

    Definition: This metric may also be known as lift. It gives the relative
    improvement from the control group.

    Example: Let's pretend the control page has a 15% retention rate. The
    variant reports an 16% retention rate. The improvement is ((16 - 15) / 15) =
    ~6.67% improvement.

    Why it is Helpful: Knowing the typical impact of a change is valuable. If
    there is only a small improvement, it may not be worthwhile implementing
    that change. This metric gives a sense of the impact in comparison to the
    baseline.

- Probability to win:

    Definition: This predicts the likelihood that for the given metric, it would
    be the winner out of all the other variants. Imagine that all the variants
    are racehorses, and we're racing each one of them against each other,
    thousands of times. This returns the odds for each racehorse winning.

   Why it is helpful: This metric is especially helpful if you want to see how
   multiple metrics stack against each other.

- Unique visitors:

    Definition: This is the number of visitors who are contributing to the
    variant. We try our best to make sure that the user is unique, by tracking
    their usage via cookies and login; however a single user might be using 2
    different browsers, in which we would count that "unique user" twice.

    Why it is useful: Besides giving a sense of how much traffic is hitting that
    page, it also can be helpful in determining if there is an issue with how
    the AB test is configured. Perhaps there is too much traffic going to only 1
    variant. (This could be because of a misconfiguration of segments.)

## Test Sessions

Sessions Panel provides statistics on how many sessions are seeing your test
impressions per day over time. This helps you validate that your audiences are
being directed to your A/B test impressions. It also helps you see how your test
is affecting the traffic to your page compared to before.

![Figure x: ](../../images/ab-test-sessions.png)

## Test Status

### Test is Running

This means that your test is still running and we still need more sample size
before we could declare a winner. Liferay Analytic Cloud does provide you a way
to see which variant is your current best, but this does not mean we have
achieved the desired confidence level.

![Figure x: ](../../images/ab-test-current-best.png)

During this status, you can always terminate the running A/B test by selecting
*Terminate* from the Summary bar.

![Figure 3: Terminating an A/B test allows you to delete the test, if desired.](../../images/ab-test-terminate.png)

### Winner Declared

Once your A/B test finishes, it is possible to have two status, "Winner Declared" or "No Clear Winner".

#### Winner Declared

The summary panel will inform you that a variant is declared as a winner. At
this state, you can perform the following actions. 

- With just a click, you can publish the winning variant as your default experience for your audience in the future. 

- You can just complete the test without publishing any variant

![Figure x: Click *Publish Winner* to publish the winning Variant.](../../images/ab-test-winner-declared.png)

#### No Clear Winner

Sometimes, Analytics Cloud cannot determine a winner because no variant has
outperformed significantly over the control. In this case, you will be able to
perform the either:

- Complete the test without publishing anything. The control will resume being
  the default experience

![Figure x: ](../../images/ab-test-no-winner.png)

By viewing the generated analytics for your A/B tests, you're constantly
informed on how they're progressing. With the provided data, you can confidently
choose the best Experience for your Site's users.
