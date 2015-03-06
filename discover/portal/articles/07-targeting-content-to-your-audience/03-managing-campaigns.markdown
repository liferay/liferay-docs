# Managing Campaigns [](id=managing-campaigns)

A campaign represents an effort to expose a certain user segment to a certain
set of assets within a specific period of time. To manage campaigns for a site,
navigate to *Site Administration* &rarr; *Configuration* &rarr; *Audience
Targeting* &rarr; *Campaigns*. To create a new campaign, you need to select a
user segment to target, a start date and an end date, and a priority, as well as
a name and, optionally, a description. You also have to indicate whether or not
the campaign you create should be active or inactive. When you've entered the
required information, click *Save*. The user segment you select when creating a
campaign represents the portal users targeted by the campaign. The start and end
dates together specify the duration of the campaign. There can be multiple
campaigns active at the same time that target the same user segment. In these
situations, the priority attribute of the campaigns determines which campaign
takes precedence. Finally, you can activate or deactivate a campaign via the
active attribute of a campaign. Deactivating a campaign disables the effect of
the campaign within the portal. Deactivating a campaign is like deleting the
campaign except that a deactivated campaign can be reactivated later. It can be
useful to deactivate a campaign if a problem is found with the way content is
being displayed. Once the problem has been corrected, the campaign can be
reactivated.

![Figure 7.5: Click on *Site Administration* &rarr; *Configuration* &rarr; *Audience Targeting* &rarr; *Campaigns* to manage campaigns for a site.](../../images/07-audience-targeting-user-campaigns.png)

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

To configure permissions for creating, editing, and deleting user segments,
visit the Roles section of the Control Panel. Then click on the *Actions* &rarr;
*Define Permissions* button corresponding to the role you'd like to configure
permissions for, and search for *Audience Targeting Resources*.

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

## Defining Tracking Actions [](id=defining-tracking-actions)

One of the most interesting features of campaigns is that they allow you to
measure the effectiveness of a campaign. This provides your marketing team with
real feedback from users. When creating a campaign, you can define the user
actions that you want to track. The Audience Targeting app can display reports
of how often those actions are triggered. For example, suppose you want to run a
campaign for an event that your company is hosting next month. For this event,
imagine that you have created a main page for the event which contains a Youtube
video and a banner which says *Register Now*. Imagine also that you have a blog
entry about the event displayed on several different pages of your website and a
Register page which contains the form to pay for the event. In this campaign,
your goal is to get as many people to register as possible. However, you will
probably be interested in tracking the following information to see if there is
something not working as your team expected:

 - Visits to the main page of the event
 - Clicks to view the video
 - Number of users who watched the video until the end
 - Clicks on the Register Now banner
 - Views of the blog entry about the event
 - Views of the Register form
 - Number of users who started to fill out the Register form
 - Number of users who completed the registration

![Figure 7.6: Drag and drop tracking actions to the right to configure a campaign.](../../images/07-audience-targeting-tracking-actions.png)
 
You could drag and drop *tracking actions* from the palette to track all the
actions mentioned above. More types of tracking actions can be created by
developers and deployed as OSGI plugins. See the
[Tracking User Actions with Audience Targeting](/develop/tutorials/-/knowledge_base/6-2/tracking-user-actions-with-audience-targeting)
tutorial for details.

The tracking actions use an analytics engine called *Audience Targeting
Analytics* that can be configured per site or per portal instance. To configure
the analytics engine per site, go to *Site Administration* &rarr; *Site
Settings*. To configure it per portal instance, go to *Control Panel* &rarr;
*Portal Configuration*. Tracking all the actions of all your users (even guest
users) can be a very heavy load for your server. Therefore, it's best to disable
the tracking of any actions about which you don't need information.

## Campaign Reports [](id=campaign-reports)

Reports are available for campaigns. You can click *Actions* &rarr; *Reports*
next to a campaign to see the list of reports available. More reports can be
created by developers and deployed as OSGI plugins. See the
[Reporting User Behavior with Audience Targeting](/develop/tutorials/-/knowledge_base/6-2/reporting-user-behavior-with-audience-targeting)
tutorial for details. 

The Content Views report shows the number of times that different assets have
been viewed via the Campaign Content Display application by users in the context
of the current campaign. For example, if you configured five Campaign Content
Display applications around your website to display content for a campaign, the
Content View report for the campaign would show how many times was that content
displayed to different users.

The Tracking Actions report shows the number of times that the actions tracked
by the campaign have been triggered by users. Consider the example that we
introduced earlier in the section on tracking actions: you've created a campaign
for an event that your company will host soon. For this event, you have created
a main page for the event which contains a Youtube video and a banner which says
"Register Now". You also have created a blog post about the event which is
displayed on several different pages of your website. Lastly, you have a
Register page which contains the form to pay for the event. For this example,
the Tracking Actions report would show you how many users visited the event
page, how many watched the video, how many clicked on the banner, how many
viewed the blog post about the event, how many started filling the
registration form, etc. This information helps you measure the effectiveness of
your campaign. You can use this information to evaluate whether or not the users
are following the engagement path you had prepared.
