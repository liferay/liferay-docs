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
    *Sites*). The Overview tab contains the Site report.

The Site report contains the following data: 

-   [Site Metrics](#site-metrics)
-   [Top Pages](#top-pages)
-   [Acquisitions](#acquisitions)
-   [Visitors by Day and Time](#visitors-by-day-and-time)
-   [Search Terms](#search-terms)
-   [Interests](#interests)
-   [Sessions by Location](#sessions-by-location)
-   [Session Technology](#session-technology)
-   [Cohort Analysis](#cohort-analysis)

### Site Metrics

The Site Metrics panel presents a summary of how visitors interact with your 
Site. This panel contains the following data: 

-   **Visitors:** Total unique visitors. 
-   **Sessions per Visitor:** An average of the number of sessions for each 
    unique visitor. A single user can open multiple sessions. These sessions can 
    occur on the same day or over days, weeks, or months. A session ends after 
    30 minutes of inactivity, or at midnight. 
-   **Session Duration:** The length of time an average session lasts. 
-   **Bounce Rate:** The percentage of visitors who view your Site's first page, 
    but do nothing else before the session ends. 
-   **Engagement:** A proprietary metric that Analytics Cloud calculates to 
    determine how engaged a visitor is with your Site. This is derived from 
    session duration, pages visited, scroll depth, time on page, and more. 

Clicking each metric changes the visualization in the panel to display the 
selected metric. 

### Top Pages

### Acquisitions

### Visitors by Day and Time

### Search Terms

### Interests

### Sessions by Location

### Session Technology

### Cohort Analysis

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
