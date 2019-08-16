---
header-id: tracking-user-actions-with-audience-targeting
---

# Tracking User Actions with Audience Targeting

[TOC levels=1-4]

In Audience Targeting, a campaign defines a set of content targeted to specific 
user segments during a time period. Reports allow campaign administrators to 
learn how users behave in the context of a campaign by monitoring their 
interaction over different elements of the site. Out of the box, Liferay 
provides several metrics based on entity types that you can track, such as 
content, forms, links, pages, etc. For example, if you want track how many users
watch a YouTube video that is published on your site, you might create a custom
report with the YouTube Videos metric.

Audience Targeting ships with many metrics, but it's also extensible. This 
means that if the default metrics available do not fulfill your needs, you can 
create one yourself.

First you need to define some criteria about your metric:

- Entity to Track
- Tracking Mechanism
- Tracking Events
- Differentiation Method

Creating a metric involves targeting what you want to track in a report. Suppose
you're the owner of a hardware store and you want to send emails to your
customers with the store's weekly newsletter. You send the email every week, but
you're in the dark about how many customers actually open and read the
newsletter. For this example, your entity to track is the newsletter.

To track how many customers view the newsletter, you must create a tracking
mechanism. You can provide a custom tracking mechanism (e.g., a servlet) or you
can use the ones provided by Audience Targeting. For a newsletter, you could
use a transparent image as the tracking mechanism, which would have the *View*
tracking event capability. Whenever the image is viewed, the Audience Targeting
app computes and stores the information.

Finally, you must assign the metric to an entity. For a newsletter, you could
provide a Newsletter ID field that the user could fill in to differentiate
newsletters, if there's more than one.

To learn more about how metrics are used in the Audience Targeting application,
visit the
[Defining Metrics](https://dev.liferay.com/discover/portal/-/knowledge_base/7-1/managing-campaigns#defining-metrics)
section.

For this tutorial, you'll create a newsletter that can track who views it. To do
this you will,

1.  Create a module with the necessary Content Targeting API dependencies.

2.  Define the metric's View/Save lifecycle.

3.  Implement a tracking mechanism and differentiation method.

4.  Define the UI.

![Figure 1: The sample Newsletter metric requires the newsletter name, ID, and event type.](../../../images-dxp/metric-template.png)

Now that you have an idea of how to plan your new metric, you'll begin
creating one next!

## Related Topics

[Creating Projects with Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/creating-projects-with-blade-cli)

[Defining a Metric's View/Save Lifecycle](/docs/7-1/user/-/knowledge_base/u/defining-a-metrics-view-save-lifecycle)

[Defining the Metric's UI](/docs/7-1/user/-/knowledge_base/u/defining-the-metrics-ui)
