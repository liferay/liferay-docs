# Understanding Page Analytics

How are your website Pages performing? Do you know if visitors are abandoning
your site when they hit a certain Page, or if one Page is constantly getting
views, engaging users, and drawing visitors back to your site? 

<!-- In the intro should we give some distinguishing information on how our page
analytics are different, or alternatively, say that users of other analytics
products will quickly understand how to use our Analytics product for pages? -->

What are Pages in Liferay Analytics Cloud? Pages are:

- Pages on a website.
- Registered and tracked by the Analytics Cloud server for analysis the first
    time a Page interaction is detected by the Analytics Cloud client.
- Queried on a schedule for up-to-date data.
- Reported on in Analytics Cloud with the metrics and reports shown in this
    article.

Readers of this article want to understand their Pages' performance. They
already have an understanding of what Analytics Cloud is and want to understand
its Page metrics and how to interpret the data. They're ready to understand and
act on their Pages' data.

BETTER FOR INTRO SINCE It DESCRIBES FUNCTIONALITY THAT'S FOR ASSETS TOO

## Time Periods

Pages are displayed in Analytics Cloud in ascending or descending order by one
of the available metrics. The Time Period selector next to the search bar
recalculates the result based on the time period selected. If you go from a
shorter time period to longer, you'll naturally see more results. In addition,
metrics are recalculated. You must specify a time period for which you'd like to
view the metrics. The default is 30 days, but this is configurable. The
following values are supported:

- Last 24 hours
- Yesterday
- Last 7 days
- Last 28 days
- Last 30 days (default)
- Last 90 days

If you select 90 days, all Pages (filtered by your search terms, if any) are
returned, in descending order of the Views (by default) *in the last 90 days*.

SCREENSHOT

## Visitor Behavior

The Overview tab of a Page's metrics has several data presentations.  The first
chart, called Visitor Behavior, is paramount. It contains four important
metrics:

- Average Engagement Score
- Total Visitors
- Total Views
- Average Bounce Rate <!-- Is this really an average? what's being averaged? or just a rate? -->
- Average Time on Page

SCREENSHOT

### Data Time Periods

At the top right of the Visitor Behavior chart is a time period selector, which
defaults to *Last 30 Days*. Select the time period of the data displayed. There
are always two trend lines displayed: Selected Period and Previous Period. This
facilitates comparisons between time periods. For example, take Views ove rthe
last 30 days. 

If the Views are dropping over the selected time period, you might be concerned
about the health of the Page. However, if you see that they're still
higher than they were at any point during the previous time period, perhaps it
puts the current data in context.

SCREENSHOT

So what are these metrics in the Visitor Behavior chart?

### Metric: Page Engagement

Average Page Engagement is an aggregation of metrics into one overall score. It
incorporates these factors:

- Depth of scroll on the Page
- Number of clicks
- Time spent on the Page
- More!

SCREENSHOT

Use the engagement score as a high level view of the Page's overall performance, as
compared with other Pages. It might not tell you specifically what's so
effective (or weak) about a Page, but it can tell you if the Page is
performing as desired over the selected time period.

<!-- Remove since most likely not sensible to non-US readers. Keeping for now in
case there's some other analogy that might be more universal. -->
+$$$

**Baseball Analogy:** Are you a baseball fan? If so you're probably familiar
with the classic individual metrics used to describe a non-pitcher's
proficiency: Batting Average, Slugging Percentage, Fielding Percentage, and a
few more. Now there are higher level aggregation metrics used to evaluate
baseball players, such as BABIP (Batting Average on Balls In Play) and WAR (Wins
Above Replacement). Discussion of those metrics is not the point here. Think of
the simpler metrics offered by Analytics Cloud (Views, Visitors, Time on Page)
as single snapshots into how useful a Page is, like looking at Batting
Average and Fielding Percentage to evaluate baseball players. Think of
Page Engagement as an advanced aggregation metric that captures the
overall usefulness of a Page, similar to BABIP or WAR in baseball. Maybe
the Analytics team can come up with a WAR-like metric next, to compare how
valuable a Page is as compared with the average Page. It could be
called TEAR (Page Engagement Above Replacement).

$$$

Page Engagement is useful to combine with time period filtering and
comparative time period features. Comparing the engagement score from different
periods is the best way to determine how your Page's performance changes
over time.

### Metric: Page Visitors

Useful with the time period filter, Page Visitors is the number of
visitors that accessed a Page in a given period of time.

A unique visitor has a unique IP address <!--need more info on how unique
visitors are calculated--> in Analytics cloud. Therefore, if the same human
being hits the Page from a different device, it's logged as a unique
visitor to the Page.

SCREENSHOT

### Metric: Page Views

Useful with the time period filter, Page View is the number of views for a Page
Page in a given period of time. It's not the same as the number of visitors,
because it doesn't try to count only unique IP addresses. So over the last 30
days, one visitor (IP address) could come back to the Page 100 times. That means
there are 100 Page Views, but only one visitor (assuming they used the same
machine to access the Page each time.  However, a unique view won't be logged
for a single user unless at least 30 minutes of inactivity <!-- need info on
whether 30 minutes is accurate and that this is how views are calculated--> on
the Page passes before the user interacts with the Page again.

SCREENSHOT

### Metric: Page Bounce Rate

Bounce Rate is the percentage of visitors (to the Page that navigated away from
the site without any page interaction (including scrolling on the page) after
the initial page load. It's calculated for a daily rate, and the daily rate is
displayed over the selected time period. 

<!-- I don't understand how this can be an average. It's the total number of
visitors who bounced divided by the total number of page loads over the period
being calculated (daily), right? So its a daily rate, not an average. -->

### Metric: Average Time on Page

Time on Page calculates the average time spent on a Page for all the Views each
day. It's displayed for the selected time period.

This metric is calculated like this for each 24 hour period:

    (view-1-time + view-2-time + ...) / total-number-views

<!-- is some sort of formula presentation for each metric desirable? or are the
actual formulas more complicated and this type of notation won't be helpful?  -->

SCREENSHOT

## Views by Segment

Segments are created by the Analytics Cloud administrator in the People &rarr;
Segments section. Segments are groups of Individuals with common
characteristics.

The Views by Segment bar graph shows how many times the Page was viewed by up to
the top seven Segments over the selected time period. If there are more than
seven Segments, the top six are displayed, and views by the remaining Segments
are aggregated in the seventh bar on the graph, labeled _X More Segments_).

SCREENSHOT

## Views by Location

View a bubble chart to compare the views by country over the selected period. Up
to the top five countries are displayed, and the sixth bubble is an aggregation
of the remaining countries.

SCREENSHOT

## Views by Technology

View a stacked bar graph of the Page's views by operating system (grouped by
device type) in the default tab.

SCREENSHOT

Click *Web Browser* to see a donut chart displaying up to the top eight web
browsers over the selected time period. If applicable, remaining web borwsers
are aggregated in the ninth <!-- (ninth what? can't view the test server right
now)-->.

SCREENSHOT

## Assets

View a list of the Assets on the Page by their number of Interactions
over the selected time period.

Depending on the Asset being viewed, a different Interaction metric is reported
on:

- Blogs reports Views.
- Documents and Media reports Downloads.
- Forms reports Submissions.
- Web Content reports Views.

SCREENSHOT


<!-- Move to new article or section of articles, needs much more discussion
## Page Path Analytics

Beside the Overview tab, there's the Path tab in a Page's screen. This
diagram represents the most common ways people used to access a Page.

After the top three paths to the Page, the remaining paths are aggregated to
show how many views came from _Other_ points.

Clicking one of the path URLs brings you to its Overview screen.
What if it's not a page that's loaded form the data source? For example, what if
it's a google search?

By default, all of the views of the Page are represented in the Paths
diagram. The Paths can be filtered by location and technology (OS and web
browser). Click the *Filter* menu and
select one location whose Path diagram you'd like to see. Click *Apply
Filter* when finished, and the Path diagram is updated to represent the top
Paths for only the selected locations.

As you select filters, they're made visible at the top center of the Path
screen.

To remove a filter, click the X next to the location name.

SCREENSHOT
-->

