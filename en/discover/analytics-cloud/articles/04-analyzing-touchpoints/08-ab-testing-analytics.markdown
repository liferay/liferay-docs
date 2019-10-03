---
header-id: a-b-testing-analytics
---

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

The Summary panel gives you an overview of your test. It provides you with
information like

- percent completion
- running time (in days)
- total visitor sessions

It also gives you a quick glance at your test metric and the best current
performing Variant.

![Figure 3: The Summary panel gives you a quick way to assess the status of your A/B test.](../../images/ab-test-summary.png)

## Variant Report

The Variant Report panel provides a detailed breakdown of each Variant and how
well they're performing.

![Figure 4: Variants are tracked using multiple metrics.](../../images/ab-test-variant-report.png)

Below are the metrics reported for each variant:

**Median:** the middle number in the set of sample values. This estimates a
typical user's behavior.

**Confidence Interval:** the range of values expected to contain the true mean
of the population. For example, a 95% confidence interval is a range of values
that the system is 95% sure contains the true mean. This gives the range of
possible values that seem plausible for the measured goal.

**Improvement:** the relative improvement from the control group. This metric
may also be known as *Lift*. For example, assume the Control Page has a 15%
retention rate. The improvement calculation would be `((16 - 15) / 15) = ~6.67%`
improvement.

The lets you know the impact of a change. If there is only a small improvement,
it may not be worth implementing that change.

**Probability to Win:** predicts the likelihood that the Variant will beat out
all other participating Variants. This lets you see how multiple metrics compare
to each other. For example, consider a horse racing event; each horse has a
generated chance to win that is posted before a race (i.e., odds of winning),
which is calculated by simulating them racing each other thousands of times.
This same method is used for your Variants to calculate their probability of
winning the A/B test.

**Unique Visitors:** the number of visitors who are contributing to the
Variant. A visitor randomly assigned a Variant always sees the same Variant
until the test is finished.

Besides knowing how much traffic is hitting a page, this metric also helps
determine if there is an issue with how the A/B test is configured. For example,
there could be too much traffic going to one Variant (typically caused by a
Segment misconfiguration).

## Test Sessions

The Test Sessions panel provides statistics on how many sessions are viewing
your test impressions per day over time. This helps you validate that your
audiences are being directed to your A/B test impressions. It also portrays how
your test is affecting the traffic to your page compared to before.

![Figure 5: This graph gives you an accurate depiction of your page traffic.](../../images/ab-test-sessions.png)

Next, you'll learn about an A/B test's statuses.

## Test Status

An A/B test is always characterized with a status after it starts. These include

- *Test is Running*
- *Winner Declared*
- *No Clear Winner*

You'll explore each status next.

### Test is Running

This means that your test is still running and needs a larger sample size before
declaring a winner. Analytics Cloud provides a way to see which Variant is your
current best; however, the desired confidence level has not been met.

![Figure 6: The leading Variant is clearly labeled as being the current best.](../../images/ab-test-current-best.png)

When a test is running, you can always terminate it by selecting *Terminate*
from the Summary bar.

![Figure 7: Terminating an A/B test allows you to delete the test, if desired.](../../images/ab-test-terminate.png)

### Winner Declared

Once your A/B test successfully finishes, a Variant is declared a winner. At
this state, you can perform the following actions:

- publish the winning Variant as your default experience. 
- complete the test without publishing any Variants.

![Figure 8: Click *Publish Winner* to publish the winning Variant.](../../images/ab-test-winner-declared.png)

### No Clear Winner

Sometimes, Analytics Cloud cannot determine a winner because no Variant has
outperformed significantly over the Control Page. In this case, you can complete
the test without publishing anything. The control will resume being the default
experience.

![Figure 9: When the required confidence level is not met during the time duration, there is no winning Variant.](../../images/ab-test-no-winner.png)

By viewing the generated analytics for your A/B tests, you're constantly
informed on how they're progressing. With the provided data, you can confidently
choose the best Experience for your Site's users.
