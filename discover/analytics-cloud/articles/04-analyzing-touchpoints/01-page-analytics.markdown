# Understanding Page Analytics [](id=understanding-page-analytics)

How are your Site Pages performing? Are visitors abandoning your site when they
hit a certain Page? Is one Page constantly getting views, engaging users, and
drawing visitors back to your site? 

Liferay Analytics Cloud answers these questions.

- Its server registers and tracks pages for analysis the first time a Page
  interaction is detected by the Analytics Cloud client.
- It queries pages on a schedule for up-to-date data.
- It reports on pages with the metrics described in this article.

Continue reading for details on how to interpret Analytics Cloud data to better
understand your pages' performance.

## Data Time Periods [](id=data-time-periods)

All Pages data in Analytics Cloud appears for a specified time period. The time
period selector recalculates the metrics based on the time period selected. You
must specify a time period to view the metrics. The default is 30 days, but this
is configurable. The following values are supported:

- Last 24 hours
- Yesterday
- Last 7 days
- Last 28 days
- Last 30 days (default)
- Last 90 days

## Visitor Behavior [](id=visitor-behavior)

The Overview tab of a Page's metrics has several data presentations.  The first
chart, called Visitor Behavior, contains four important metrics:

- Average Engagement Score
- Total Visitors
- Total Views
- Bounce Rate
- Average Time on Page

![Figure 1: The Visitors Behavior chart contains interesting rend lines.](../../images/pages-visitors-behavior.png)

Select the time period for the data displayed. There are always two trend lines
displayed: Selected Period and Previous Period. This facilitates comparisons
between time periods. 

<!-- REMOVING INTERPRETIVE ANALYSIS FOR NOW: If the Views are dropping over the selected time period, you might be concerned
about the health of the Page. However, if you see that they're still
higher than they were at any point during the previous time period, perhaps it
puts the current data in context. -->

So what are these metrics in the Visitor Behavior chart?

### Metric: Engagement [](id=metric-engagement)

Engagement, or average Page Engagement is an aggregation of metrics into one
overall score. It incorporates these factors:

- Depth of scroll on the Page
- Number of clicks
- Time spent on the Page
- More!

Use the engagement score as a high level view of the Page's overall performance,
as compared with other Pages. It might not tell you specifically what's so
effective (or weak) about a Page, but it can tell you if the Page is performing
as desired over the selected time period.

**Baseball Analogy:** Are you a baseball fan? If so you might be familiar with
the classic individual metrics used to describe a non-pitcher's proficiency:
Batting Average, Slugging Percentage, Fielding Percentage, and a few more. Now
there are higher level aggregation metrics used to evaluate baseball players,
such as BABIP (Batting Average on Balls In Play) and WAR (Wins Above
Replacement). Discussion of those metrics is not the point here. Think of the
simpler metrics offered by Analytics Cloud (Views, Visitors, Time on Page) as
single snapshots into how useful a Page is, like looking at Batting Average and
Fielding Percentage to evaluate baseball players. Think of Page Engagement as an
advanced aggregation metric that captures the overall usefulness of a Page,
similar to BABIP or WAR in baseball. Maybe the Analytics team can come up with
a WAR-like metric next, to compare how valuable a Page is as compared with the
average Page. It could be called TEAR (Page Engagement Above Replacement).


Page Engagement is useful to combine with time period filtering and comparative
time period features. Comparing the engagement score from different periods is
the best way to determine how your Page's performance changes over time.

### Metric: Page Visitors [](id=metric-page-visitors)

Useful with the time period filter, Page Visitors is the number of
visitors that accessed a Page in a given period of time.

A unique visitor has a unique IP address <!--need more info on how unique
visitors are calculated--> in Analytics cloud. Therefore, if the same human
being hits the Page from a different device, it's logged as two unique visitors
to the Page.

### Metric: Page Views [](id=metric-page-views)

Useful with the time period filter, Page View is the number of views for a Page
Page in a given period of time. It's not the same as the number of visitors,
because it doesn't try to count only unique IP addresses. Over the last 30
days, one visitor (IP address) could come back to the Page 100 times. That means
there are 100 Page Views, but only one visitor (assuming she used the same
machine to access the Page each time. However, a unique view isn't logged for
a single user unless at least 30 minutes of inactivity <!-- need info on whether
30 minutes is accurate and that this is how views are calculated--> on the Page
passes before the user interacts with the Page again.

### Metric: Page Bounce Rate [](id=metric-page-bounce-rate)

Bounce Rate is the percentage of visitors to the Page that navigated away from
the site without any page interaction (including scrolling on the page) after
the initial page load. It's calculated as a daily rate (percentage per day), and
the daily rate trend line is displayed over the selected time period. 

### Metric: Average Time on Page [](id=metric-average-time-on-page)

Time on Page calculates the average time spent on a Page for all the Views each
day. It's displayed for the selected time period.

This metric is calculated like this for each 24 hour period:

    (view-1-time + view-2-time + ...) / total-number-views

That concludes the Visitors Behavior chart, but see below for more Page
data.

## Views by Segment [](id=views-by-segment)

Segments are created by the Analytics Cloud administrator in the People &rarr;
Segments section. Segments are groups of Individuals with common
characteristics.

The Views by Segment bar graph shows how many times the Page was viewed by up to
the top seven Segments over the selected time period. If there are more than
seven Segments, the top six are displayed, and views by the remaining Segments
are aggregated in the seventh bar on the graph, labeled _X More Segments_).

![Figure 2: See which Segments are most commonly viewing the Page.](../../images/pages-views-segments.png)

## Views by Location [](id=views-by-location)

View a bubble chart to compare the views by country over the selected period. Up
to the top five countries are displayed, and the sixth bubble is an aggregation
of the remaining countries.

![Figure 3: See where the Page is most popular.](../../images/pages-views-location.png)

## Views by Technology [](id=views-by-technology)

View a stacked bar graph of the Page's views by operating system (grouped by
device type) in the default tab. Hover over each bar to see the detailed
breakdown of data.

![Figure 4: Which OS is most commonly being used to access the Page?](../../images/pages-views-os.png)

Click *Web Browser* to see a donut chart displaying up to the top eight web
browsers over the selected time period. If applicable, remaining web browsers
are aggregated in the ninth donut segment.

![Figure 5: Which browser should your page be optimized for?](../../images/pages-views-browser.png)

## Assets [](id=assets)

View a list of the Assets on the Page by their number of Interactions
over the selected time period.

Depending on the Asset being viewed, a different Interaction metric is reported:

- Blogs reports Views.
- Documents and Media reports Downloads.
- Forms reports Submissions.
- Web Content reports Views.

![Figure 6: Which Assets on the Page are getting the most interactions?](../../images/pages-assets.png)

After all those fundamental metrics, you're really getting to know your Pages.
But there's some interesting Page data you haven't seen. Discover how people
came to the Page in the first place. Learn about Path Analytics next.
