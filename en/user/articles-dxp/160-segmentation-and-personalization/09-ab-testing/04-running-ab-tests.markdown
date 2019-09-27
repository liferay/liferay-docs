---
header-id: running-a-b-tests
---

# Running A/B Tests

[TOC levels=1-4]

Once you've created and configured your A/B test, you'll want to run it to begin
gathering data on your Control Page and Variants. To run an A/B test,

1.  Click the *Review and Run Test* button from the A/B Test menu.

2.  Configure how your test should run. There are two configurations:

    *Traffic Split*: the percentage of visitors that are randomly split between
    the Variants when visiting the Page. A visitor randomly assigned a Variant
    always sees the same Variant until the test is finished.

    *Confidence Level Required*: the accuracy of the test results (i.e., when
    the winning Variant truly outperforms the other Variants). Typically you
    want to have the highest confidence level possible, but this impacts test
    duration. The higher the required confidence level, the longer it takes to
    declare a winning Variant. Choose the percentage based on your expectations.

    The *Estimated Time to Declare Winner* field is also displayed. This is the
    estimated duration the test runs. This is calculated based on the
    selected traffic split, confidence level, and projected page traffic. Your
    page's past traffic history is provided by Analytics Cloud.

    ![Figure 1: Configure the final parameters of your A/B test before running it.](../../../images-dxp/run-ab-test.png)

3.  Select *Run*.

Your A/B test is now running!

While an A/B test is running, you have two buttons available to help manage the
test:

*Terminate Test*: terminates the test. To delete a test, you must terminate it
first.

*View Data in Analytics Cloud*: redirects you to your A/B Testing dashboard
hosted on Analytics Cloud. Here you can view your test's traffic, reports,
statistics, etc. related to your test. See the
[Monitoring A/B Test Results](/docs/7-2/user/-/knowledge_base/u/monitoring-a-b-test-results)
article for more information.

Awesome! You now have a running A/B test accumulating data based on user
interactions with your Page. Next, you'll learn how to monitor your A/B test's
results.
