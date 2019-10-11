---
header-id: finding-analytics-data
---

# Finding Analytics Data

Combining traditional page analytics with site, path, and asset analytics sets 
Liferay Analytics Cloud apart from other analytics tools. Analytics Cloud's 
site-wide report feature provides a comprehensive report of your entire site. 
This helps you understand how many unique visitors your site has over time, 
which pages these visitors access, what their interests are, and more. 

Analytics cloud also provides analytics for individual pages. This includes 
views, visitors, and bounce rate. Path analytics let you see how visitors arrive 
at your pages. This includes information on which pages they visit prior to 
yours, and their location and device type. In addition, several Liferay DXP 
Assets have even more fine-grained metrics to drill deeper than the page level 
to determine how you can improve your site's performance. 

Here, you'll learn how to find the site, page, and asset analytics. The rest of 
the articles in this section explain their metrics in detail. 

![Figure 1: Analytics Cloud provides important data for Sites and Assets.](../../images/pages-touchpoints-menu.png)

## Site Report

Liferay Analytics Cloud provides a single Site report for each connected data 
source. For example, if you connect Analytics Cloud to one DXP instance and 
configure analytics for two subsites, one Site report aggregating both subsites 
is created. 

Follow these steps to view the Site report: 

1.  In the *Touchpoints* section of the menu, click *Sites*. 

2.  Click the *Overview* tab (this tab is selected by default when you click 
    *Sites*). This tab contains the Site report.

The Site report contains the following data: 

-   [Site Metrics](#site-metrics)
-   [Top Pages](#top-pages)
-   [Acquisitions](#acquisitions)
-   [Visitors by Day and Time](#visitors-by-day-and-time)
-   [Search Terms and Interests](#search-terms-and-interests)
-   [Sessions by Location](#sessions-by-location)
-   [Session Technology](#session-technology)
-   [Cohort Analysis](#cohort-analysis)

### Site Metrics

The Site Metrics panel presents a summary of how visitors interact with your 
Site. This panel contains the following metrics: 

-   **Visitors:** Total unique visitors. 
-   **Sessions per Visitor:** An average of the number of sessions for each 
    unique visitor. A single user can open multiple sessions. A session ends 
    after 30 minutes of inactivity, or at midnight. 
-   **Session Duration:** The length of time an average session lasts. 
-   **Bounce Rate:** The percentage of visitors who view your Site's first page, 
    but do nothing else before the session ends. 
-   **Engagement:** A proprietary metric that Analytics Cloud calculates to 
    determine how engaged a visitor is with your Site. This is derived from 
    session duration, pages visited, scroll depth, time on page, and more. 

Clicking each metric changes the visualization in the panel to display the 
selected metric. 

![Figure 2: The Site Metrics panel shows how visitors are using your site.](../../images/site-metrics.png)

### Top Pages

The Top Pages panel lists the following: 

-   **Visited Pages:** Your Site's most visited pages and the number of visitors 
    for each. 
-   **Entrance Pages:** The most common pages that visitors view first upon 
    entering your Site, and the number of entrances for each. 
-   **Exit Pages:** The most common pages that visitors view when leaving your 
    Site, and the exit percentage for each. The exit percentage is the 
    percentage of visitors for which the page is the last page in their session. 

To view more detailed page analytics, click *View Pages* in the panel. 

![Figure 3: The Top Pages panel lists your Site's most relevant pages.](../../images/top-pages.png)

### Acquisitions

The Acquisitions panel shows how visitors arrive to your Site. It shows data for 
the channels, source/medium, and referrers from which visitors arrive to your 
Site. 

This report works best if your marketing campaign utilizes UTM parameters. UTM 
parameters allow Analytics Cloud to determine where visitors arrive from (e.g., 
the specific referrer or ad campaign). 

![Figure 4: The Acquisitions panel shows how visitors get to your Site.](../../images/acquisitions.png)

### Visitors by Day and Time

The Visitors by Day and Time panel visualizes the days and times when visitors 
come to your Site. This helps you understand when your Site is most active. You 
can use this information, for example, to know when to release important 
information or launch an advertising campaign. 

The panel contains a grid with the days of the week on one axis, and the time of 
day on the other axis. Darker cells in the grid indicate heavier Site traffic at 
the corresponding day and time. Tooltips for each cell show the number of 
visitors for that day and time. 

![Figure 5: The Visitors by Day and Time panel shows your Site's traffic by day and time.](../../images/day-and-time.png)

### Search Terms and Interests

The Search Terms and Interests panels show your visitors' most common search 
terms and the topics they're interested in, respectively. Search terms are 
collected from the search query parameter in your Site's URL. Interest topics 
are derived from the keyword metadata of the pages that visitors view. 

![Figure 6: The Search Terms and Interests panels show your Site's most common search terms and interest topics.](../../images/search-terms-interests.png)

### Sessions by Location

The Sessions by Location panel shows the countries from which visitors access 
your Site. Countries with more visitors are shaded darker on the map. The 
countries are also listed below the map along with the number and percentage of 
visitors for each. 

![Figure 7: The Sessions by Location panel shows where your Site's visitors are.](../../images/sessions-by-location.png)

### Session Technology

The Session Technology panel shows the devices, operating systems, and browsers 
that visitors access your Site with. Tooltips for each graph element display 
more detailed data for that element. On the *Devices* tab, for example, mouse 
over each bar on the bar graph to see the operating system data for that device. 

![Figure 8: The Session Technology panel shows what visitors use to access your Site.](../../images/session-technology.png)

### Cohort Analysis

The Cohort Analysis panel shows a 
[cohort analysis](https://en.wikipedia.org/wiki/Cohort_analysis) 
based on visitors from a specific acquisition date (the cohort) and whether they 
return to your Site over a given time period. Use the selector menus at the top 
left of the panel to select the visitor type (All, Anonymous, or Known) and time 
period (Day, Week, or Month). 

For example, if you select *All Visitors* and *Day*, the percentage of visitors 
from a given acquisition date that return to your Site are listed in the chart 
for each following day. 

![Figure 9: The cohort analysis is based on visitors from a specific date and the percentage of those visitors that return over the time period that follows.](../../images/cohort-analysis.png)

## Viewing Page Data

1.  Find the Touchpoints section of the menu.

2.  Click *Pages* to view the list of Pages with summary data.

![Figure 2: The Page list contains useful summary data.](../../images/pages-list.png)

Order Pages in ascending or descending order based on any one of these metrics. 
Click the metric's heading in the table to perform the sort: 

-   Average Engagement Score
-   Total Visitors
-   Total Views
-   Average Bounce Rate
-   Average Time on Page

The metrics for ordering the Page list are calculated based on the time period
selected in the time period menu (at the top-right of the table). The following 
values are supported: 

-   Last 24 hours
-   Yesterday
-   Last 7 days
-   Last 28 days
-   Last 30 days (default)
-   Last 90 days

To view detailed metrics for a single page, click that page in the table. See 
[Page Analytics](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/04-analyzing-touchpoints/01-page-analytics.markdown) 
for more information. 

![Figure 3: Most Page metrics are available in the Overview tab.](../../images/pages-overview.png)

## Finding Asset Data

1.  Find the Touchpoints section of the menu. 

2.  Click *Assets* for a list of Assets with summary data. 

3.  Choose an Asset type: Blogs, Documents and Media, Forms, Web Content, or 
    Custom. 

![Figure 4: The Assets appear in a table.](../../images/assets-list.png)

To order Assets in ascending or descending order based on any one of the Asset's 
metrics, click the metric's heading in the table. The metrics differ between 
Assets. See each Asset's documentation for more information. In addition, you 
can calculate the metrics over a selected time period by selecting one of the 
following values from the time period menu (at the top-right of the table): 

-   Last 24 hours
-   Yesterday
-   Last 7 days
-   Last 28 days
-   Last 30 days (default)
-   Last 90 days

Click an Asset in the list to see more granular detail. Remember that there are 
separate lists for different Asset types. More details on each Asset type's 
metrics are covered in the subsequent articles. 

![Figure 5: Once you find an Asset, click it to see its metrics.](../../images/assets-overview.png)
