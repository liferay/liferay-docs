# Tracking User Actions with Audience Targeting [](id=tracking-user-actions-with-audience-targeting)

In the Audience Targeting (AT) application, a campaign defines a set of content
targeted to specific user segments during a time period. Campaign custom reports
allow campaign administrators to learn how users behave in the context of a
campaign by monitoring their interaction over different elements of the site.
Out of the box, Liferay provides several metrics that are based on entity types
that you can track, such as content, forms, links, pages, etc. You can use these
metrics to create custom reports. For example, if you want track how many users
watch a YouTube video that is published on your site, you might create a custom
report with the YouTube Videos metric.

The AT app ships with many metrics you can apply to custom reports, but it's
also extensible. This means that if the default metrics available do not fulfill
your needs, you can create one yourself!

A metric's development strategy comes down to four choices:

- Entity to Track
- Tracking Mechanism
- Tracking Events
- Differentiation Method

Creating a metric involves targeting what you want to track in a custom report.
Suppose you're the owner of a hardware store and you'd like to send emails to
your customers notifying them of the store's weekly newsletter. You send the
email every week, but you're in the dark about how many customers actually open
and read the newsletter. For this example, your entity to track is a newsletter.

To track how many customers view the newsletter, you'll need to create a
tracking mechanism. You can provide a custom tracking mechanism (e.g., a
servlet) or you can use the ones provided by Audience Targeting. For a 
newsletter, you could use a transparent image as the tracking mechanism, which
would have the *View* tracking event capability. Whenever the image is viewed,
the Audience Targeting app computes and stores the information.

In many cases, a metric can have multiple tracking event options. For example,
the YouTube Videos metric provides tracking event options like Buffering,
Playing, Paused, Ended, etc. This lets you track different kinds of actions on
an entity, providing a more accurate report on user interactions.

Finally, you must assign the metric to an entity. For a newsletter, you could
provide a Newsletter ID field that the user could fill in to differentiate
newsletters, if there's more than one.

To learn more about how metrics are used in the Audience Targeting application,
visit the
[Defining Metrics](https://dev.liferay.com/discover/portal/-/knowledge_base/7-1/managing-campaigns#defining-metrics)
section.

For this tutorial, you'll create a newsletter that can track who views it. This
process involves defining the view/save lifecycle, which is when a user applies
a metric to a report using the Report Editor. Then you'll define its tracking
mechanism, tracking event(s), and differentiation method, similar to what was
described above.

![Figure 1: The sample Newsletter metric requires the newsletter name, ID, and event type.](../../images-dxp/metric-template.png)

Now that you have an idea of how to plan your new metric, you'll begin
creating one next!

## Related Topics [](id=related-topics)

[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-1/creating-modules-with-blade-cli)

[Defining Metrics](/discover/portal/-/knowledge_base/7-1/managing-campaigns#defining-metrics)

[Audience Targeting Metrics](/discover/portal/-/knowledge_base/7-1/audience-targeting-metrics)
