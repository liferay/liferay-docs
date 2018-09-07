# Defining Metrics [](id=defining-metrics)

One of the most interesting features of campaigns is that they allow you to
measure the effectiveness of a campaign. This provides your marketing team with
real feedback from users. When creating a campaign, you can define the user
actions that you want to track. This can be done by defining *Metrics*.

Metrics enable Audience Targeting administrators to build custom reports and
measure the effectiveness of a campaign by tracking certain user actions.

Metrics filter the analytics data gathered by the Audience Targeting Analytics
engine to obtain the number of times that a certain action was performed on a
given element or content by users and the user segments they belong to.

A custom report may contain multiple metrics of the same type; for instance,
metrics for different links. The name field in the metric identifies each value
in the report results (e.g., *Link to Marketplace*, *Link to More Info*, etc.).

Metrics can be created by developers and deployed as extensions. Out of the box,
Audience Targeting includes metrics to track the most common user actions. These
metrics are described below.

## Content [](id=content)

Tracks the number of times certain content has been viewed. Use the content
selector to set the content to be tracked.

## Page [](id=page)

Tracks the number of times a selected page has been viewed. You can track views
on both both public or private pages.

## Form [](id=form)

Tracks how many users view a form, interact with it (i.e., type or select values
in the inputs) or submit it. If you select the *All* option from the *Event
type* field, the custom report will show the figures for the three events
simultaneously. You must also provide the form you want to track, which is
selectable from the *Form* metric.

As a use case, suppose a company wants to organize a webinar to promote one of
their products. The marketing team creates a landing page with a subscription
form and promotes it on social media. They want to generate a report listing the
number of visits to the landing page, form interactions, and form submissions to
measure the success of their campaign. This Form metric would allow campaign
administrators the ability to track forms in their site.

## Link [](id=link)

Tracks how often links are clicked. This helps campaign administrators determine
if they're sufficiently visible or helpful.

Similar to forms, you must provide the ID of the link you want to track. If you
don't know it, you can inspect the HTML of the page where the link is and
extract this information.

## YouTube Videos [](id=youtube-videos)

Tracks how users interact with embedded YouTube videos. You must enter the ID of
the YouTube video. You can extract this ID from the video URL as the value for
the `v` parameter. For instance, in the URL
`https://www.youtube.com/watch?v=H9xtSO-6aXY` the YouTube video ID is
`H9xtSO-6aXY`. Then select one of the available events, or *All* to track all of
them. For further reference on the meaning of these events, read the official
YouTube API documentation.

Notice that this option only works if the YouTube video is embedded as an
iframe. The iframe code is available from the YouTube video's *Share* &rarr;
*Embed* menu.

## Using Metrics

Suppose you want to run a campaign for an event that your company is hosting 
next month. For this event, you have created a main page for the event which 
contains a Youtube video and a banner which says *Register Now*. You also have 
a blog entry about the event displayed on several different pages of your 
website and a Register page which contains the form to pay for the event. In 
this campaign, your goal is to get as many people to register as possible. 
However, you will probably be interested in tracking the following information 
to ensure that everything is working as expected:

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
