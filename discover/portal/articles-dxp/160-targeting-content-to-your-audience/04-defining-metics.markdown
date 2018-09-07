# Defining Metrics [](id=defining-metrics)

One of the most interesting features of campaigns is that they allow you to
measure the effectiveness of a campaign. This provides your marketing team with
real feedback from users. When creating a campaign, you can define the user
actions that you want to track. This can be done by defining *Metrics*.

For example, suppose you want to run a campaign for an event that your company is hosting next month. For this event, you have created a main page for the event which contains a Youtube video and a banner which says *Register Now*. You also have a blog entry about the event displayed on several different pages of your website and a Register page which contains the form to pay for the event. In this campaign, your goal is to get as many people to register as possible. However, you will probably be interested in tracking the following information to ensure that everything is working as expected:

 - Visits to the main page of the event
 - Clicks to view the video
 - Number of users who watched the video until the end
 - Clicks on the Register Now banner
 - Views of the blog entry about the event
 - Views of the Register form
 - Number of users who started to fill out the Register form
 - Number of users who completed the registration

![Figure 3: Drag and drop metrics to the right to configure a campaign.](../../images-dxp/audience-targeting-metrics.png)

You can assign metrics to a campaign report, which is elaborated on in the next
section. To access the Metrics palette, select a pre-existing campaign, select
the *Reports* tab, and then add a custom report. The Metrics palette is
accessible at the bottom of the *New Report* wizard.

You could drag and drop *metrics* from the palette to track all the actions
mentioned above. More types of metrics can be created by developers and deployed
as OSGi plugins. See the
[Tracking User Actions with Audience Targeting](/develop/tutorials/-/knowledge_base/6-2/tracking-user-actions-with-audience-targeting)
tutorial for details.

The metrics use an analytics engine called *Audience Targeting Analytics* that
can be configured per site or per @product@ installation. You'll learn about
this next.

### Audience Targeting Analytics [](id=audience-targeting-analytics)

To configure the analytics engine per site,

1.  Go to Site Administration and click *Configuration* &rarr; *Site Settings* &rarr; *Advanced* &rarr; *Audience Targeting Analytics*.

To configure it per portal instance,

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *Instance Settings* &rarr; *Audience Targeting Analytics*.

The following analytics options are available:

- Anonymous Users (not available per site)
- Pages
- Content
- Forms
    - Form Views
    - Form Interactions
    - Form Submits
- Links
- YouTube Videos

Tracking all the actions of all your users (even guest users) can be a heavy
load for your server. Therefore, it's best to disable tracking any actions about
which you don't need information. For example, Audience Targeting, by default,
stores anonymous users' behavior analytics. This stores a large amount of data
to the database. If you're not interested in tracking anonymous users, you can
turn that functionality off for your @product@ installation by disabling the
*Anonymous Users* selector.

![Figure 4: There's no need to track anonymous users if you're not interested in their behavior.](../../images-dxp/anonymous-users-analytics.png)

Disabling analytics for certain entities means you won't track them using
Audience Targeting. Carefully manage analytics to optimize your Audience
Targeting experience.

You can also store your analytics data in a separate database schema, which
allows for independent scalability. To separate the storage of analytics data
from Liferay's database schema,

1.  Navigate to the Control Panel &rarr; *Configuration* &rarr; *System Settings* &rarr; *Web Experience*

2.  Select *Audience Targeting Analytics Storage*.

3.  Fill out the external storage fields to point to your alternative database 
    schema.

![Figure 5: By filling out the external storage requirements, you configure your Audience Targeting analytics data to be stored in an alternative database schema.](../../images-dxp/alternative-analytics-db.png)

Once you've saved your external datasource configuration, you must restart the
Audience Targeting Analytics component.

1.  Navigate to the Control Panel &rarr; *Apps* &rarr; *App Manager* and select
    the *Liferay Audience Targeting* app suite.

2.  Select the *Options* (![Options](../../images-dxp/icon-app-options.png))
    button for the Analytics component and click *Deactivate*.

3.  Select the *Options* (![Options](../../images-dxp/icon-app-options.png))
    button for the Analytics component again and click *Activate*.

Now your analytics data is stored in an alternative database schema!

Next you'll learn about Campaign Reports.

## Campaign Reports [](id=campaign-reports)

Reports are available for campaigns. You can select the campaign name and click
the *Reports* tab to see the list of reports available. More reports can be
created by developers and deployed as OSGi plugins. See the
[Reporting User Behavior with Audience Targeting](/develop/tutorials/-/knowledge_base/7-1/reporting-user-behavior-with-audience-targeting)
tutorial for details. You can create a custom report by selecting the a campaign
and clicking the *Reports* tab &rarr; *Add Custom Report*
(![Add Custom Report](../../images-dxp/icon-add.png)).

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
