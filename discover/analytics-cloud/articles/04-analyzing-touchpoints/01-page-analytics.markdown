---
header-id: understanding-page-analytics
---

# Understanding Page Analytics

How are your Site Pages performing? Are visitors abandoning your site when they
hit a certain Page? Is one Page constantly getting views, engaging users, and
drawing visitors back to your site? Liferay Analytics Cloud answers these 
questions and more. 

Analytics Cloud does the following for pages: 

-   Registers and tracks pages for analysis the first time the Analytics Cloud 
    client detects a Page interaction.
-   Queries pages on a schedule for up-to-date data.
-   Reports on pages with the metrics described here.

## Data Time Periods

All Page data in Analytics Cloud appears for a specified time period. The time 
period selector recalculates the metrics for the selected time period. You must 
specify a time period to view the metrics. The default is 30 days, but this is 
configurable. The following values are supported: 

**Last 24 hours:** Displays data generated over the last 24 hours.

**Yesterday:** Displays all data generated yesterday, beginning at 12:00 AM and 
ending at 11:59 PM.

**Last 7 days:** Displays data generated for the previous seven full days (the
current day is excluded), in daily increments.

**Last 28 days:** Displays data generated for the previous 28 full days (the
current day is excluded), in weekly increments.

**Last 30 days (default):** Displays data generated for the previous 30 full
days (the current day is excluded), in weekly increments.

**Last 90 days:** Displays data generated over a 13 week period beginning 90
days prior to the end of the current week. The time period always begins on
a Sunday, and ends with the current, incomplete week (unless viewed on
a Saturday approaching midnight). 

Note that the first Sunday of the 90 day time period is not included.

## Visitor Behavior

The Overview tab of a Page's metrics has several data presentations. The first
chart, called Visitor Behavior, contains these metrics: 

- Average Engagement Score
- Unique Visitors
- Total Views
- Bounce Rate
- Average Time on Page

Select the time period for the data displayed. There are always two trend lines
displayed: Selected Period and Previous Period. This facilitates comparisons
between time periods. 

![Figure 1: The Visitors Behavior chart contains interesting trend lines.](../../images/pages-visitors-behavior.png)

So what are these metrics in the Visitor Behavior chart?

### Metric: Engagement

Engagement, or average Page Engagement is an aggregation of metrics into one
overall score. It incorporates these factors:

-   Depth of scroll on the Page
-   Number of clicks
-   Time spent on the Page
-   More!

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

### Metric: Unique Visitors

Useful with the time period filter, Page Visitors is the number of visitors that 
accessed a Page in a given period of time. 

A unique visitor has a unique IP address in Analytics Cloud. Therefore, if the 
same person views the Page from a different device, it's logged as two unique 
visitors. 

### Metric: Page Views

Useful with the time period filter, Page View is the number of views for a Page
Page in a given period of time. It's not the same as the number of visitors,
because it doesn't try to count only unique IP addresses. Over the last 30
days, one visitor (IP address) could come back to the Page 100 times. That means
there are 100 Page Views, but only one visitor (assuming she used the same
machine to access the Page each time. However, a unique view isn't logged for
a single user unless at least 30 minutes of inactivity <!-- need info on whether
30 minutes is accurate and that this is how views are calculated--> on the Page
passes before the user interacts with the Page again.

### Metric: Page Bounce Rate

Bounce Rate is the percentage of visitors to the Page that navigated away from
the site without any page interaction (including scrolling on the page) after
the initial page load. It's calculated as a daily rate (percentage per day), and
the daily rate trend line is displayed over the selected time period. 

### Metric: Time on Page

Time on Page calculates the average time spent on a Page for all the Views each
day. It's displayed for the selected time period.

This metric is calculated like this for each 24 hour period:

    (view-1-time + view-2-time + ...) / total-number-views

That concludes the Visitors Behavior chart, but see below for more Page
data.

## Audience

The Audience report uses charts to present information about the audience 
interaction with the Page. It answers these questions: 

-   How many users are interacting with my content?
-   Of these users, how many are known or anonymous?
-   Of the known users who interacted with my content, how many belong to 
    Analytics Cloud Segments?
-   Of the users in Segments, what are the top 15 segments? 

Here are the charts in the Audience report: 

**Unique Visitors:** A donut chart that presents the percentage of total unique 
visitors who are known or anonymous. The center of the chart shows the total 
number of unique visitors. Tooltips on each chart section show the number of 
users for that section (e.g, the number of known or anonymous users). 

**Known Individuals:** A donut chart that presents the percentage of known 
individuals who belong or do not belong to one or more Analytics Cloud Segment. 
The center of the chart shows the total number of known individuals. Tooltips on 
each chart section show the number of users for that section (e.g, the number of 
users belonging to one or more Segments). Click the chart title (Known 
Individuals) to view a list of all the known individuals who comprise the 
chart's data. 

**Known Individuals Segments:** A bar chart that shows the percentage of known 
individuals that comprise each Segment. The chart shows a bar for each of the 
top five Segments, and then aggregates the remaining Segments into the last bar. 
A tooltip on the last bar shows the values for each of the remaining Segments. 

![Figure 2: The Audience report visualizes how individuals interact with the Page.](../../images/audience-report.png)

## Views by Location

The map in the Views by Location panel shows the number of views by country in 
the selected time period. 

![Figure 3: See where the Page is most popular.](../../images/pages-views-location.png)

## Views by Technology

View a stacked bar graph of the Page's views by operating system (grouped by
device type) in the default tab. Hover over each bar to see the detailed
breakdown of data.

![Figure 4: Which OS is most commonly being used to access the Page?](../../images/pages-views-os.png)

Click *Web Browser* to see a donut chart displaying up to the top eight web
browsers over the selected time period. If applicable, remaining web browsers
are aggregated in the ninth donut segment.

![Figure 5: Which browser should your page be optimized for?](../../images/pages-views-browser.png)

## Assets

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
