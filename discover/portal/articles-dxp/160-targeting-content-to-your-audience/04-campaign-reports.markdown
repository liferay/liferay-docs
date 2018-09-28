# Campaign Reports [](id=campaign-reports)

Reports are available for campaigns. You can select the campaign name and click
the *Reports* tab to see the list of reports available. More reports can be
created by developers and deployed as OSGi plugins. See the
[Reporting User Behavior with Audience Targeting](/develop/tutorials/-/knowledge_base/7-1/reporting-user-behavior-with-audience-targeting)
tutorial for details. To create a custom,

1.  Click on a *Campaign* name

2.  Open the *Reports* ta

3.  Click *Add Custom Report* (![Add Custom Report](../../images-dxp/icon-add.png)).

The Content Views report shows the number of times that different assets have
been viewed via the Campaign Content Display application by users in the context
of the current campaign. For example, if you configured three Campaign Content
Display applications around your website to display content for a campaign, the
Content View report for the campaign would show how many times that content was
interacted with by different users.

![Figure 6: You can build your own custom campaign report to fit your needs.](../../images-dxp/audience-targeting-report-builder.png)

You can track many other user actions by creating a custom report. You can drag
and drop different kinds of metrics to track for your campaign, which shows the
number of times each metric has been triggered by users. For a complete
reference of all report metrics available, see the
[Audience Targeting Metrics](/develop/tutorials/-/knowledge_base/7-1/audience-targeting-metrics)
tutorial.

Consider the example that we introduced earlier in the section on metrics:
you've created a campaign for an event that your company will host soon. For
this event, you have created a main page for the event which contains a Youtube
video and a banner which says "Register Now". You also have created a blog post
about the event which is displayed on several different pages of your website.
Lastly, you have a Register page which contains the form to pay for the event.
For this example, a custom Metrics report could show you how many users visited
the event page, how many watched the video, how many clicked on the banner, how
many viewed the blog post about the event, how many started filling the
registration form, etc. This information helps you measure the effectiveness of
your campaign. You can use this information to evaluate whether or not the users
are following the engagement path you had prepared.

![Figure 7: This campaign report displays several event types for content in the campaign.](../../images-dxp/audience-targeting-campaign-report.png)

The metrics you apply to a report can be tracked in several different ways. For
instance, if you added the *YouTube Videos* metric to your report, you have
several different event types to track. Some administrators may be interested in
how many users played the video, while others are only interested in those that
finished the video. You can track these events with the *Event type* field. You
even have the option to track *all* events, if your interested in all the
metrics for an option.
