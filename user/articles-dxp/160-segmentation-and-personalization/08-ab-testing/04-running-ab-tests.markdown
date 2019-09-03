# Running A/B Tests

[TOC levels=1-4]

Once you've created and configured your A/B test, you'll want to run it to begin
gathering data on your Control page and Variants. To run an A/B test,

1.  Click the *Review and Run Test* button from the A/B Test menu.

2.  Configure how your test should run. There are two configurations:

    *Traffic Split*: the percentage of visitors that are randomly split between
    the Variants when visiting the Page. Once a visitor is randomly assigned a
    Variant, they will always view it until the test is finished.

    *Confidence Level Required*: the accuracy of the test results (i.e., when
    the winning Variant truly outperforms the other Variants). Typically you
    want to have the highest confidence level possible, but this impacts test
    duration. The higher the required confidence level, the longer it takes to
    declare a winning Variant. Choose the percentage based on your expectations.

    ![Figure 1: Configure the final parameters of your A/B test before running it.](../../../images-dxp/run-ab-test.png)

3.  Select *Run*.























- There needs to be sufficient traffic on the page (ideally 1000 views/day).
  Having low traffic on the page will extend the duration of the test run.

- The duration of a test run is estimated based on page traffic. To change the
    test duration, the marketing analyst can change the confidence level.





## Managing

- Segments cannot be edited or deleted when it is being used in an experience
- An Experience that is being used in an AB test cannot be edited. 
- Deleting a Page/Experience being used in an AB test will also delete the
  test/s for that page.
  


DXP will only show active and recently completed tests; AB tests in DXP will be
auto-removed after some time.

A history of tests will be available on Analytics Cloud.

The system provides options similar, but not limited to the following:

- Edit (not available when a test is actively running): Editing can include, but
  is not limited to:
    - Rename Test
    - Edit Target
    - Edit Goal
- Start
    - A test in draft status can be started only if the marketing analyst has
      completed all necessary steps.
    - The system should provide an option for the marketing analyst to review
      the test before starting.
- Terminate: Terminate test before a statistically significant result is reached.
    - If a test is terminated, the Analytics Cloud results page for that test
      should indicate that the test was terminated.
    - If a test is terminated, the DXP test status displayed in DXP should
      indicate that the test was terminated.
- Delete: Deleted AB Tests will not be sent to the recycle bin.
- Pause: A paused test may be resumed or terminated.
- Reset: A marketing analyst can reset a test if a test is already running but
  decides that a parameter or variant needs to be changed. Resetting a test
  stops the test until the marketing analyst republishes the test. What happens
  to the results?
- Schedule test for future run.

- DXP will not have an admin page for AB Tests, at least for the initial version
  of AB Testing. To manage tests in DXP:
    - The marketing analysts navigates to an experience that has an active or
      completed AB Tests
    - The system displays options to manage AB Tests, such as 
- A marketing analyst can run/terminate a test from AC.
