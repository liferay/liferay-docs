# Touchpoints: Engaging People

Touchpoints are a key concept in Analytics Cloud. If you don't know what
Touchpoints are, read the Analytics Cloud 
[introductory documentation](LINK). 
In summary, Touchpoints are:

- Pages on a website.
- Imported during the initial Analytics Cloud setup.
- Queried on a schedule for up-to-date data.
- Reported on in Analytics Cloud with the metrics and reports shown in this
    article.

Readers of this article want to understand their Touchpoints' performance. They
already have an understanding of what Analytics Cloud is and want to understand
where Touchpoint data exists, what metrics it includes, and how to interpret the
data and configure its views for easy consumption.

## Viewing Touchpoint Data

To view Touchpoint data directly:

1.  Find the Engagement section of the menu.

2. Click *Touchpoints*.

3. From the list of Touchpoints, click any Touchpoint to see a detailed view and
   metrics.

4. The default screen is called *Overview*, and gives you 

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
- Bounce Rate
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

## Visitor Behavior

The Overview tab of a Touchpoint's detail view has several data presentations.
The first chart, called Visitor Behavior, is paramount. It contains four
important metrics:

- Engagement
- Visitors
- Views
- Bounce Rate

SCREENSHOT

### Data Time Periods

At the top right of the Visitor Behavior chart is a time period selector, which
defaults to *Last 30 Days*. Select the time period of the data displayed. There
are always two trend lines displayed: Selected Period and Previous Period. This
facilitates comparisons. For example, take Views. If the Views are dropping over
the selected time period, you might be concerned about the health of the
Touchpoint. However, if you see that they're still higher than they were at any
point during the previous time period, perhaps it puts the current data in
context.

SCREENSHOT

So what are these metrics in the Visitor Behavior chart?

### Metric: Touchpoint Engagement

Touchpoint Engagement is an aggregation of metrics into one overall score. It
incorporates these factors:

- Depth of scroll on the page
- Number of clicks
- Time spent on the page
- More!

SCREENSHOT

Use the Touchpoint Engagement score as a high level view of the Touchpoint's
overall value. It might not tell you specifically what's so effective (or weak)
about a Touchpoint, but it can tell you if the Touchpoint is performing as
desired over the selected time period.

+$$$

**Baseball Analogy:** Are you a baseball fan? If so you're probably familiar
with the classic individual metrics used to describe a non-pitcher's
proficiency: Batting Average, Slugging Percentage, Fielding Percentage, and a
few more. Now there are higher level aggregation metrics used to evaluate
baseball players, such as BABIP (Batting Average on Balls In Play) and WAR (Wins
Above Replacement). Discussion of those metrics is not the point here. Think of
the simpler metrics offered by Analytics Cloud (Views, Visitors, Time on Page)
as single snapshots into how useful a Touchpoint is, like looking at Batting
Average and Fielding Percentage to evaluate baseball players. Think of
Touchpoint Engagement as an advanced aggregation metric that captures the
overall usefulness of a Touchpoint, similar to BABIP or WAR in baseball. Maybe
the Analytics team can come up with a WAR-like metric next, to compare how
valuable a Touchpoint is as compared with the average Touchpoint. It could be
called TEAR (Touchpoint Engagement Above Replacement).

$$$

Touchpoint Engagement is useful to combine with time period filtering and
comparative time period features. Comparing the engagement score from different
periods is the best way to determine how your Touchpoint's performance changes
over time.

### Metric: Touchpoint Visitors

Useful with the time period filter, Touchpoint Visitors is the number of
visitors that accessed a Touchpoint page in a given period of time.

A unique visitor has a unique IP address in Analytics cloud. Therefore, if the
same human being hits the Touchpoint page from a different device, it will be
logged as a unique visitor to the Touchpoint.

SCREENSHOT

### Metric: Touchpoint Views

Useful with the time period filter, Touchpoint View is the number of views for a
Touchpoint page in a given period of time. It's not the same as the number of
visitors, because it doesn't try to count only unique IP addresses. So over the
last 30 days, one visitor (IP address) could come back to the Touchpoint page
100 times. That means there are 100 Touchpoint Views, but only one visitor
(assuming they used the same machine to access the Touchpoint page each time.
However, a unique view won't be logged for a single user unless at least 30
minutes of inactivity on the Touchpoint page passes before the user interacts
with the Touchpoint again.

SCREENSHOT

### Metric: Touchpoint Bounce Rate

Bounce Rate is the percentage of visitors to the Touchpoint page that navigated
away from the site without doing anything after viewing the Touchpoint. If a
Touchpoint has a high bounce rate, there might be something wrong with it.

### Metric: Time on Touchpoint Page

Time on Touchpoint Page calculates the average time on page for all the Views in
the selected time period.

SCREENSHOT

## Views by Segment

The Views by Segment bar graph shows how many times the Touchpoint was viewed by
the top six segments over the selected time period. In addition, the views by
the remaining segments are calculated (the seventh bar on the graph, labeled _X
More Segments_). 

SCREENSHOT

## Views by Location

View a pie chart to compare the views by location over the selected period. 

SCREENSHOT

## Views by Technology

View a bar graph of the Touchpoint's views by operating system and web browser
over the selected time period.

SCREENSHOT

## Assets

View a list of the Assets on the Touchpoint page by their number of Interactions
over the selected time period.

SCREENSHOT

## Touchpoint Path Analytics

Beside the Overview tab, there's the Path tab in a Touchpoint's screen. This
diagram represents the most common entry points to viewing a Touchpoint page.

After the top X paths to the Touchpoint, the remaining paths are aggregated to
show how many views came from _Other_ entry points.

Clicking one of the entry point URLs brings you to its page.

By default, all of the views of the Touchpoint are represented in the Paths
diagram. The Paths can be filtered by location. Click the *Filter* menu and
select one or more locations whose Path diagram you'd like to see. Click *Apply
Filter* when finished, and the Path diagram is updated to represent the top
Paths for only the selected locations.

As you select filters, they're made visible at the top center of the Path
screen.

To remove a filter, click the X next to the location name.

SCREENSHOT


