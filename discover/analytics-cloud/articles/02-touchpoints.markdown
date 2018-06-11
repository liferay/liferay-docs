# Touchpoints: Engaging People

Touchpoints are a key concept in Analytics Cloud. If you don't know what Touchpoints are,
read the Analytics Cloud [introductory documentation](LINK). In summary, Touchpoints are:

- Pages on a website.
- Imported during the initial Analytics Cloud setup.
- Queried on a schedule.
- Reported on in Analytics Cloud with the data reports shown in this article.

Readers of this article have an understanding of what Analytics Cloud is and want to
understand where Touchpoint data exists, what metrics it includes, and how to
interpret the data and configure its views for easiest consumption by end users.

## Finding Touchpoint Data

To view Touchpoint data directly:

1.  Find the Engagement section of the menu.

2. Click *Touchpoints*.

SCREENSHOT

To view the Touchpoint data for a particular Individual or Segment:

1.  Navigate to the Individuals or Segments screen in the People section of the
    menu.

2. The Overview tab contains analytics on the Individual's (or Segment's)
   interaction with Touchpoints.

SCREENSHOT

The table of Touchpoints provides two ways to visualize Touchpoints: 

Order Touchpoints by ascending or descending order based on any one of these
metrics:

- Engagement score
- Visitors
- Views
- Time on Page

SCREENSHOT

In addition to ordering the Touchpoints, filter them by entering search terms.
There's a prominent search bar at the top of the Touchpoints table. Run a basic
search and see the matching Touchpoints returned.

SCREENSHOT

Once you have some returned results, perhaps being ordered by one of the
available metrics in descending order, turn your attention to the Time Period
selector next to the search bar. It recalculates the result based on the time
period selected. If you go from a shorter time period to longer, you'll
naturally see more results. In addition, metrics are recalculated. Take
Views as an example to see how this works:

If the example project was for Liferay's own sites, searching for *symposium*
and ordering the results in descending order would give us a healthy sample.
However, you must specify the time period for which you'd like to view the
metrics. The default is 30 days, but this is configurable. The following values
are supported:

- Last 24 hours
- Yesterday
- Last 7 days
- Last 28 days
- Last 30 days (default)
- Last 90 days

Take 90 days for our example search for *symposium*. Now all Touchpoints
containing the word *symposium* are returned, in descending order of views *in
the last 90 days*.

SCREENSHOT

Viewing the list of Touchpoints with their overview data is great, but what if
you want to dive into each metric?

## Metric: Touchpoint Engagement

Touchpoint Engagement is an aggregation of metrics into one overall score. It
incorporates these factors:

- Depth of scroll on the page
- Number of clicks
- Time spent on the page
- More!

SCREENSHOT

Use the Touchpoint Engagement score as a high level view of the Touchpoint's
overall value. It might not tell you specifically what's so effective about a
good Touchpoint or what can be improved, but it can tell you whether the Touchpoint
needs improvement.

+$$$

**Baseball Analogy:** Are you a baseball fan? If so you're probably familiar
with the classic individual metrics used to describe a non-pitcher's
proficiency: Batting Average, Slugging Percentage, Fielding Percentage, and a
few more. Now there are higher level aggregation metrics used to evaluate
baseball players, such as BABIP (Batting Average on Balls in Play) and WAR (Wins
above Replacement). Discussion of those metrics is not the point of this
article. Think of  the simpler metrics offered by Analytics Cloud (Views,
Visitors, Time on Page) as single snapshots into how useful a Touchpoint is, and
think of Touchpoint Engagement as an advanced aggregation metric that captures
the overall usefulness of a Touchpoint. Maybe the Analytics team can come up
with a WAR-like metric next, to compare how valuable a Touchpoint is as compared
with the average Touchpoint. It could be called TEAR (Touchpoint Engagement
above Replacement).

$$$

Touchpoint Engagement is useful to combine with the time period filtering
ability of Analytics Cloud. Compare the engagement score from different periods
to determine how your Touchpoint's performance changes over time.

## Metric: Touchpoint Visitors

Useful with the time period filter, Touchpoint Visitors is the number of
visitors that accessed a Touchpoint page in a given period of time.

How are visitors differentiated? IP Address? This could be the same person on a
different machine?

SCREENSHOT

## Metric: Touchpoint Views

Useful with the time period filter, Touchpoint View is the number of views for a
Touchpoint page in a given period of time. It's not the same as the number of
visitors, because it doesn't try to count only unique IP addresses. So over the
last 30 days, one visitor (IP address) could come back to the Touchpoint page
100 times. That means there are 100 Touchpoint Views, but only one visitor
(assuming they used the same machine to access the Touchpoint page each time.

SCREENSHOT

## Metric: Time on Touchpoint Page

Time on Touchpoint Page calculates the average time on page for all the Views in
the selected time period.

SCREENSHOT
