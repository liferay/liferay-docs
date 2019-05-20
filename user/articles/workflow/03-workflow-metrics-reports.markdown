# Workflow Metrics: Reports

As soon as you enter the Metrics screen (Control Panel &rarr; Workflow &rarr;
Metrics) you're seeing metrics on each workflow installed in the system.

![Figure x: ](../../images/workflow-metrics-reports1.png)

A table view of all installed workflow processes shows you how many items are
Overdue, how many are On Time, and how many are Untracked in the SLA system.

There's more to Metrics than the overview report though. Get more detailed
reports by clicking on one of the workflow processes.

## Understanding Reports 

When you click into the metrics for a specific process, you're presented with
two valuable reports: Pending Items and Workload by Step.

![Figure x: ](../../images/workflow-metrics-reports2.png)

### Pending Items

Pending Items shows you the overview of items by their SLA status. Drill down by
clicking on any of the statuses, to see the specific items that are enumerated
in Pending Items.

### Workload by Step

Workload by Step shows a breakdown of the items that are in each step of the
workflow process, by their SLA status (Overdue or On Time). Items untracked by
the SLA are not shown in Workload by Step/

## Items View

Once you click into the All Items screen from the overview report, you'll seea
mroe detailed table including the following columns:

**ID**
: This is the ?????? that identifies the particular wokflow item to the system.
WHEN MIGHT THIS BE USEFUL TO ADMINS? 

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

![Figure x: ](../../images/workflow-reports-sla-status.png)
 
### Filtering by Process Status and Completion Period

Filter items based on whether they're Pending or Completed.

If you choose to filter items by selecting the Completed status, you'll get an
additional option, which is to filter items by the Completion Period. This
dropdown lets you select from these time periods:

Today
Yesterday
Last 7 Days
Last 30 Days (default)
Last 90 Days
Last 180 Days
Last Year
All Time

![Figure x: ](../../images/workflow-reports-process-status.png)
 
### Filtering by Process Step

Filter items based on where they are in the workflow definition. For example, in
the Single Approver workflow process, you can choose to see a report including
all items in the Review task.

![Figure x: ](../../images/workflow-reports-process-step.png)

### Combining Filters

Use a combination of filters to find just the items you need to see. For
example, 

![Figure x: ](../../images/workflow-reports-complex-filter.png)



![Figure x: ](../../images/workflow-metrics-reports3.png)

![Figure x: ](../../images/workflow-metrics-reports4.png)

![Figure x: ](../../images/workflow-metrics-reports5.png)


