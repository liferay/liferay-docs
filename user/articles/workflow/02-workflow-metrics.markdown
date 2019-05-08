# Workflow Metrics

A brand new feature in Liferay DXP 7.2, _Workflow Metrics_ gives insights into
the time certain workflow events take to complete. Perhaps more importantly,
they let you set deadlines in the workflow, called SLAs (Service LEvel
Agreements). Once set, the Workflow Reports and they measure the compliance
with the deadlines.

Workflows can be varied, and the important timelines in the workflow are
different for every use case, so there's a user interface where the events

For DXP subscribers, the _Workflow Metrics_ feature was introduced. Configure
one or more Service Level Agreements (SLAs; think of these as deadlines) on a
workflow definition's events, and workflow submissions are accordingly tracked
and reported as on time or overdue. There are two new concepts around this
feature:


SLAs define the deadlines on a workflow process's events. They're like a
contract between the workflow participants and Users submitting workflow items.

Workflow Reports shows data for all processes with SLAs, including each
workflow item's SLA status: on time or overdue.

## Adding SLAs

1.  Go to Control Panel &rarr; Workflow &rarr; Metrics.

2.  Click the actions (![Actions](../../images/icon-actions.png)) menu and
    select _Set Up SLAs_.

    ![Figure x: Add SLAs to a workflow definition from the Metrics application.](../../images/workflow-add-sla.png)

3.  On the SLAs screen, click the Add button
    (![Add](../../images/icon-add.png)).

4.  Give the SLA a Name and Description.

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

    - Days: Enter a whole number of days. 
    - Hours: Enter hours and minutes in the format HH:MM

#### Valid workflow events

#### Durations

## Understanding Workflow Reports

