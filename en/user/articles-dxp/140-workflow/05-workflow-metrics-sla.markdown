---
header-id: workflow-metrics-the-service-level-agreement-sla
---

# Workflow Metrics: The Service Level Agreement (SLA)

[TOC levels=1-4]

A brand new feature in Liferay DXP 7.2, _Workflow Metrics_ gives insights into
the time certain workflow events take to complete. To use it, set up deadlines
on a workflow process's events. These deadline configurations are referred to as
SLAs (Service Level Agreements). Once defined, Workflow Reports measure compliance
with the SLAs.

| **Requires Elasticsearch:** To use Workflow Metrics, you must be using
| Elasticsearch to index your @product data. Read
| [here](/docs/7-2/deploy/-/knowledge_base/d/installing-elasticsearch) to learn
| about installing Elasticsearch.

SLAs define the deadlines on a workflow process's events. They're like a
contract between the workflow participants and Users submitting workflow items.

Workflow Reports shows data for all processes with SLAs, including each
workflow item's SLA status: on time or overdue.

| **Editing a Workflow with SLAs:** Editing a workflow (e.g., removing nodes, editing a task name) with
| SLAs defined on it may invalidate the SLA for
| items already in the workflow/SLA pipeline.
|
| **Creating or Editing SLAs for Active Processes:** Editing an SLA's duration or
| defining a new SLA while items are already in the workflow process causes a
| recalculation for all instances currently in the workflow. Completed workflow
| instances are not recalculated.

## Adding SLAs

To add an SLA,

1.  Go to Control Panel &rarr; Workflow &rarr; Metrics.

2.  Click on the title of the Process. 

    The Reports UI for the process is displayed. 

3.  If there's no SLA for the process, a warning message stating
    as much appears. Click the _Add a new SLA_ link from the warning to
    access the New SLA form directly.

    Alternatively, click the Options (![Options](../../images/icon-options.png))
    menu and select _SLA Settings_.

    ![Figure 1: Add SLAs to a workflow definition from the Metrics application.](../../images/workflow-add-sla.png)

3.  On the SLAs screen, click the *Add* button
    (![Add](../../images/icon-add.png)).

4.  In the New SLA form, Give the SLA a Name and Description.

5.  Define the time frame for the SLA, specifying three things:

    - Start: When the item makes it to the event defined here, the SLA timer
        begins counting.
    - Pause: If there's an event in the workflow when time should stop counting,
        enter it here. For the Single Approver workflow, you might choose to
        pause the SLA timer when the item is in the Update task.
    - Stop: Choose when the SLA is completed. If the item makes it to the Stop
        event before the defined SLA duration (the deadline), it's _On Time_
        according to the SLA. If it fails to make it to the Stop event in the
        specified duration, it's _Overdue_.

6.  Define the duration (i.e., the deadline) for the SLA. Fill out at least one
    of the two time boxes.

    **Days:** Enter a whole number of days.

    **Hours:** Enter hours and minutes in the format HH:MM

7.  Once you click *Save*, you'll see the SLA listed on the SLAs screen.

| **System Calendar:** By default, there's an internal calendar that assumes the
| SLA duration should continue counting all the time: in other words, 24 hours
| per day, seven days per week. If you need a different calendar format, find a
| friendly developer to create a custom calendar. Official docs will be written
| for this extension point, but the basic idea is to implement the
| `WorkflowMetricsSLACalendar` interface. New implementations of this service are
| picked up automatically by the Workflow Metrics applications, so it becomes
| available as soon as the module holding the service implementation is
| deployed. The interface has three methods to implement:
|
| `public Duration getDuration(
|   LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime);`
|
| `public LocalDateTime getOverdueLocalDateTime(
|   LocalDateTime nowLocalDateTime, Duration remainingDuration);`
|
| `public String getTitle(Locale locale);`
|
| See the `DefaultWorkflowMetricsSLACalendar` from the
| `portal-workflow-metrics-service` module for example code. If you define a new
| calendar, a new option becomes available in the Add SLA form, allowing you to
| choose from the default 24/7 calendar or any custom ones you've provided (for
| example, make the timer run for 9 hours per day, 5 days per week).

![Figure 2: Manage SLAs from the SLAs screen.](../../images/workflow-metrics-sla-list.png)

#### Valid Start and Stop Events

Any workflow task can be used as a start or end parameter for the SLA.

When defining the tasks to act as the SLA's Start Events, choose between three
events:

- The start node
- Entry into a task
- Exit from a task

When defining the tasks to act as the SLA's Stop Events, choose between three
events:

- Entry into a task
- Exit from a task
- The end node

The SLA can be paused at any task that falls between the start node and the end
node, and it's defined by setting the node(s) when the SLA should be paused.
_The SLA timer is paused the entire time a workflow item is in the specified
node_.

#### Durations

Define the SLA durations in at least one of the available boxes (Days and
Hours). Here are some examples:

Example Duration: 1 day, 24 hours
: Valid configuration --- Days: _1_
: Invalid --- Hours: _24:00_. The Hours box must not exceed _23:59_.

Example Duration: 36 hours
: Valid --- Days: _1_, Hours: _12:00_
: Invalid --- Days: _1.5_. Only whole numbers are accepted.

Example Duration: 6.5 hours
: Valid --- Hours: _06:30_

Once your SLAs are configured, activate the workflow on an asset, stretch
your fingers, and get ready for the submissions to roll in if you're one of the
workflow assignees. You're on the hook to get those workflow items through the
process within the SLA duration!
