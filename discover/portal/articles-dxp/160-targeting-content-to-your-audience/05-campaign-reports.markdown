# Campaign Reports [](id=campaign-reports)

Campaigns are useless without reports showing what happened. Select the
campaign name and click the *Reports* tab to see the list of reports available.
More reports can be created by developers and deployed as OSGi plugins. See the
[Reporting User Behavior with Audience Targeting](/develop/tutorials/-/knowledge_base/7-1/reporting-user-behavior-with-audience-targeting)
tutorial for details. To create a report,

1.  Click on a *Campaign* name.

2.  Open the *Reports* tab.

3.  Click *Add Custom Report* (![Add Custom Report](../../images-dxp/icon-add.png)).

The Content Views report shows the number of times assets were viewed via the
Campaign Content Display application by users in the context of the current
campaign. For example, if you placed three Campaign Content Display
widgets on your website to display content for a campaign, the Content
View report would show how many times users interacted with that content.

![Figure 6: You can build your own custom campaign report to fit your needs.](../../images-dxp/audience-targeting-report-builder.png)

You can track many other user actions by creating a custom report. You can drag
and drop different kinds of metrics to track for your campaign, and the report
shows how many times each metric was triggered by users. For a complete
reference of all report metrics available, see the 
[Audience Targeting Metrics](/develop/tutorials/-/knowledge_base/7-1/audience-targeting-metrics)
tutorial.

Consider the metrics example we introduced earlier: you've created a campaign
for a future event. For this event, you created a main page with a YouTube
video and a banner stating "Register Now." You also have a blog post about the
event that appears on several different pages. Finally, the Register page
contains the form to pay for the event. A custom Metrics report could show you
how many users visited the event page, how many watched the video, how many
clicked on the banner, how many viewed the blog post about the event, how many
started filling the registration form, etc. This information helps you measure
the effectiveness of your campaign. You can use this information to evaluate
if users are following the engagement path you had prepared.

![Figure 7: This campaign report displays several event types for content in
the campaign.](../../images-dxp/audience-targeting-campaign-report.png)

The metrics you apply to a report can be tracked in several different ways. For
instance, if you added the *YouTube Videos* metric to your report, there are
several different event types to track, such as how many users played the video
or who finished the video. You can track these events with the *Event type*
field. You can even track *all* events, if you want all the metrics for an
option.
