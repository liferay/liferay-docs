---
header-id: workflow-metrics-reports
---

# Workflow Metrics: Reports

[TOC levels=1-4]

As soon as you enter the Metrics screen (Control Panel &rarr; Workflow &rarr;
Metrics) you see metrics on each workflow installed in the system.

![Figure 1: In this view, the only process with pending items is the Single Approver.](../../images/workflow-metrics-reports1.png)

A table view of all installed workflow processes shows you how many items are
Overdue, how many are On Time, and how many are Pending in the workflow process.

There's more to Metrics than the overview report though. Get more detailed
reports by clicking on one of the workflow processes.

| **Requires Elasticsearch:** To use Workflow Metrics, you must be using
| Elasticsearch to index your @product data. Read
| [here](/docs/7-2/deploy/-/knowledge_base/d/installing-elasticsearch) to learn
| about installing Elasticsearch.

## Understanding Reports

The Reports UI has two main views, represented as tabs: _Pending_ (changed to
_Dashboard_ as of @product-ver@ Service Pack 1---SP1) and
_Completed_ (changed to _Performance_ in SP1).

_Pending/Dashboard_ items are those currently in the workflow process, and include items
untracked by the SLA. This might include items in the paused step of the
workflow, or items that are outside the scope of the SLA duration.

_Completed/Performance_ items show any item that has completed processing in the
workflow. SP1 includes a new chart on this screen: _Completion Velocity_.

When you first click into the metrics for a specific process, you're presented
with two valuable reports on pending items: the Pending Items overview and
Workload by Step.

![Figure 2: See data on the Pending Items and the Workload by Step for a process.](../../images/workflow-metrics-reports2.png)

### Pending Items

Pending Items shows you the overview of items by their SLA status. Drill down by
clicking on any of the statuses to see the specific items that are enumerated
in Pending Items.

### Workload by Step

Workload by Step shows a breakdown of the items that are in each step of the
workflow process, by their SLA status (Overdue or On Time).

### Completed Items

Click the *Completed* tab (*Performance* on SP1) to see the items that have
completed the workflow process. Workload by Step data doesn't make sense in this
case, because by definition, these items are no longer in any workflow process
step. Instead, there's a _Completion Velocity_ chart that shows the performance
over time.

## Completion Velocity

A line chart shows you the completion rate for the workflow process. The default
display shows the number of completed workflow instances per day, for the last
30 days.

![Figure 3: View the completion rate of items in a workflow process over time.](../../images-dxp/workflow-reports-completion-velocity.png)

The overall completion rate for the time period is displayed in the top right
corner of the chart (as _Inst/timeUnit_), while the trend-line is presented in
the chart body. The overall performance metric and the chart body are updated
when you select a new time period; the time unit changes depending on the total
time period you're measuring. For some of the longer durations, the time unit is
configurable:

**Today**
: Calculate _Inst/Hour_ from _00:00_, or _12:00 AM_, of the current day until the
current time (rounded to the nearest whole hour).

**Yesterday**
: Calculate _Inst/Hour_  From _00:00-23:59_, or _12:00 AM to 11:59 PM_, of the
previous day.

**Last 7 Days**
: Calculate _Inst/Day_. The current day counts as 1 day, so this is from 6 days
ago to the current day.

**Last 30 Days**
: Calculate the _Inst/Week_ or the _Inst/Day_. The current day counts as 1 day,
so this is from 29 days ago to the current day.

**Last 90 Days**
: Calculate the _Inst/Month_, _Inst/Week_, or _Inst/Day_. The current day counts
as 1 day, so this is from 89 days ago to the current day.

**Last 180 Days**
: Calculate the _Inst/Month_ or _Inst/Week_. The current day counts as 1 day, so
this is from 179 days ago to the current day.

**Last Year**
: Calculate the _Inst/Month_ or _Inst/Week_. The current day counts as 1 day, so
this is from 364 days (365 for a leap year) ago to the current day.

## Items View

Hover over the status you're interested in, from either the _Pending_ or the
_Completed_ tabs (on SP1, these tabs were renamed to _Dashboard_ and
_Completion_). Click into the All Items screen from the overview report and
a more detailed table appears, including the following columns:

**ID**
: This is the workflow item's numeric identifier to the system. Importantly, you
can click this to enter the Item Detail view.

**Item Subject**
: This shows a human readable summary of the item, to help administrators
identify the item.

**Process Step**
: This identifies where the item is in the workflow.

**Created By**
: This shows the user name of the submitting User.

**Creation Date**
: This shows the date and time the item was submitted to the workflow.

The All Items view can be filtered so you can find the subset of items you want
to analyze.

### Filtering by SLA Status

Filter items based on whether they're Overdue, On Time, or Untracked.

![Figure 4: Filter by SLA status: Overdue, On Time, or Untracked.](../../images/workflow-metrics-reports4.png)

**Overdue**
: Overdue items have breached at least one SLAs defined deadline.

**On Time**
: On Time items have not breached _any_ SLA deadline.

**Untracked**
: Untracked items are items in the workflow process that aren't currently under
the purview of an SLA. The can be in a task identified as a _Pause_ in the SLA,
or perhaps outside the scope of the SLA entirely, if the SLA isn't defined for
the entire process (Process Begins to Process Ends in the SLA Definition
screen).

### Filtering by Process Status and Completion Period

Filter items based on whether they're Pending or Completed in the workflow
process.

If you filter by the Completed status, you'll get an additional filtering
option: filter items by the Completion Period. Select from these time periods:

- Today
- Yesterday
- Last 7 Days
- Last 30 Days (default)
- Last 90 Days
- Last 180 Days
- Last Year
- All Time

![Figure 5: Filter by Process Status and Completion Period.](../../images/workflow-reports-process-status-period.png)

### Filtering by Process Step

Filter items based on where they are in the workflow definition. For example, in
the Single Approver workflow process, you can choose to see a report including
all items in the Review task. This is different for each workflow definition.

### Combining Filters

Use a combination of filters to find just the items you need to see. For
example, below are all items in the Single Approver process's Review task that
have the status Completed or Pending, whether On time or Overdue. Untracked
items aren't shown.

![Figure 6: Combine filters to see just the items you want.](../../images/workflow-metrics-reports13.png)

## Item Details

To see the metrics for a single workflow process item, click the ID field while
in the All Items view. A pop-up shows you more detailed information on the item.

![Figure 7: Item Details include SLA status information and whether the item is Resolved or Open.](../../images/workflow-reports-item-detail.png)

From here you can view detailed information about the asset and even click *Go
to Submission Page*, which redirects you to the item's view in the Submissions
section of the Control Panel.

The top of the Item Detail view is important. It shows you the information about
the due date for the item in the SLA, and its SLA completion status: _Open_ or
_Resolved_.

**Open**
: The defined SLA goals are not yet met. Open items can be of status Overdue or
On Time.

**Resolved**
: The defined SLA goals are completed. Resolved items can be of status Overdue
or On Time.

From the overall metrics of a workflow process down to the details on a single
item in the workflow, the new Workflow Metrics functionality gives you insights
into the time it takes to _get things done_ in @product@.
