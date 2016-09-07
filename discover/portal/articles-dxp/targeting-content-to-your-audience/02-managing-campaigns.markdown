# Managing Campaigns [](id=managing-campaigns)

A campaign represents an effort to expose certain user segments to a set of
assets within a specific period of time. To manage campaigns for a site,
navigate to *Site Administration* &rarr; *Configuration* &rarr; *Audience
Targeting* &rarr; *Campaigns*. To create a new campaign, select the
(![Add Campaign](../../images-dxp/icon-add.png)) button. You need to select the
user segments to target, a start date and an end date, and a priority, as well as
a name and, optionally, a description. You also have to indicate whether or not
the campaign you create should be active or inactive. When you've entered the
required information, click *Save*. The user segments you select when creating a
campaign represents the portal users targeted by the campaign. The start and end
dates together specify the duration of the campaign. There can be multiple
campaigns active at the same time that target the same user segments. In these
situations, the priority attribute of the campaigns determines which campaign
takes precedence. Finally, you can activate or deactivate a campaign via the
*Active* attribute of a campaign. Deactivating a campaign disables the effect of
the campaign within the portal. Deactivating a campaign is like deleting the
campaign except that a deactivated campaign can be reactivated later. It can be
useful to deactivate a campaign if a problem is found with the way content is
being displayed. Once the problem has been corrected, the campaign can be
reactivated.

![Figure 1: Navigate to Site Administration and click *Configuration* &rarr; *Audience Targeting* &rarr; *Campaigns* to manage campaigns for a site.](../../images-dxp/audience-targeting-user-campaigns.png)

For example, suppose you wanted the ability to display certain content (for
example, advertisements about your new Android app) to female baseball fans
during the months leading up to the World Series. To achieve this, you could use
the Gender rule (configured to female), the Device Rule (configured for Android
devices), and the Score Points rule to define a user segment called *Female
Baseball Fans*. The Score points rule assigns 1 point to a user each time the
user visits a page or views an asset categorized under the user segment *Female
Baseball Fans*. When a user accumulates a certain number of points (specified by
the value of the Score Points rule's *Score Points Threshold* attribute), the
user matches this rule. After creating this user segment, you would create a new
campaign targeting this segment, select start and end dates, choose a priority,
choose *Active*, and then click *Save*. To actually present content to the users
belonging to the *Female Baseball Fans* user segment, you need to use the
Campaign Content Display application.

Once you've created a campaign, you can open its summary view, which displays
relevant data and configurations.

![Figure 2: Select a pre-existing campaign to view its Summary page.](../../images-dxp/campaign-summary.png)

To configure permissions for creating, editing, and deleting user segments,
visit the Roles section of the Control Panel. Then click on the *Actions* &rarr;
*Define Permissions* button corresponding to the role you'd like to configure
permissions for, and search for *Audience Targeting*.

## Managing Campaign Priorities [](id=managing-campaign-priorities)

The priority of your campaigns becomes important when multiple campaigns are
running at the same time on your website. The Campaign Content Display
application can be configured to display content based on the campaign your
users match. When a user matches multiple campaigns, the one with the highest
priority takes precedence. 
 
If you have several Campaign Content Display applications around your website
configured to display different content per campaign, changing the priority of
one campaign automatically affects all the Campaign Content Display
applications. Similarly, if a campaign is deactivated or if a campaign's date
range is exceeded, all of the Campaign Content Display applications on your
website are affected.

## Defining Metrics [](id=defining-metrics)

One of the most interesting features of campaigns is that they allow you to
measure the effectiveness of a campaign. This provides your marketing team with
real feedback from users. When creating a campaign, you can define the user
actions that you want to track. This can be done by defining *Metrics*.

+$$$

**Note:** Metrics were previously known as Tracking Actions and were aggregated
as part of the campaign editing options. Since Liferay 7, Tracking Actions have
been renamed to Metrics and are aggregated in custom reports. As part of the
upgrade process to Liferay 7, for each campaign containing Tracking Actions, a
custom report with the equivalent Metrics is automatically added.

$$$

The Audience Targeting app can display reports of how often those actions are
triggered. For example, suppose you want to run a campaign for an event that
your company is hosting next month. For this event, imagine that you have
created a main page for the event which contains a Youtube video and a banner
which says *Register Now*. Imagine also that you have a blog entry about the
event displayed on several different pages of your website and a Register page
which contains the form to pay for the event. In this campaign, your goal is to
get as many people to register as possible. However, you will probably be
interested in tracking the following information to see if there is something
not working as your team expected:

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
as OSGI plugins. See the
[Tracking User Actions with Audience Targeting](/develop/tutorials/-/knowledge_base/6-2/tracking-user-actions-with-audience-targeting)
tutorial for details.

The metrics use an analytics engine called *Audience Targeting
Analytics* that can be configured per site or per portal instance. To configure
the analytics engine per site, go to Site Administration and click
*Configuration* &rarr; *Site Settings* &rarr; *Advanced* &rarr; *Audience
Targeting Analytics*. To configure it per portal instance, go to *Control Panel*
&rarr; *Configuration* &rarr; *Instance Settings* &rarr; *Audience Targeting
Analytics*. Tracking all the actions of all your users (even guest users) can be
a very heavy load for your server. Therefore, it's best to disable the tracking
of any actions about which you don't need information.

## Campaign Reports [](id=campaign-reports)

Reports are available for campaigns. You can select the campaign name and click
the *Reports* tab to see the list of reports available. More reports can be
created by developers and deployed as OSGI plugins. See the
[Reporting User Behavior with Audience Targeting](/develop/tutorials/-/knowledge_base/6-2/reporting-user-behavior-with-audience-targeting)
tutorial for details. You can create a custom report by selecting the a campaign
and clicking the *Reports* tab &rarr *Add Custom Report*
(![Add Custom Report](../../images-dxp/icon-add.png)).

The Content Views report shows the number of times that different assets have
been viewed via the Campaign Content Display application by users in the context
of the current campaign. For example, if you configured three Campaign Content
Display applications around your website to display content for a campaign, the
Content View report for the campaign would show how many times that content was
interacted with by different users.

![Figure 4: You can build your own custom campaign report to fit your needs.](../../images-dxp/audience-targeting-report-builder.png)

You can track many other user actions by creating a custom report. You can drag
and drop different kinds of metrics to track for your campaign, which shows the
number of times each metric has been triggered by users. For a complete
reference of all report metrics available, see the
[Audience Targeting Metrics](/develop/tutorials/-/knowledge_base/7-0/audience-targeting-metrics)
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

![Figure 5: This campaign report displays several event types for content in the campaign.](../../images-dxp/audience-targeting-campaign-report.png)

The metrics you apply to a report can be tracked in several different ways. For
instance, if you added the *YouTube Videos* metric to your report, you have
several different event types to track. Some adminstrators may be interested in
how many users played the video, while others are only interested in those that
finished the video. You can track these events with the *Event type* field. You
even have the option to track *all* events, if your interested in all the
metrics for an option.
