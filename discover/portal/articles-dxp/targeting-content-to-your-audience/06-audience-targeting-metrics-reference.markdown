# Audience Targeting Metrics [](id=audience-targeting-metrics)

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
