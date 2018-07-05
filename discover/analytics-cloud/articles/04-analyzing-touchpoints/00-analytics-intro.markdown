# Finding Touchpoint Data

Combining traditional Page Analytics with Path and Asset Analytics sets Liferay
Analytics Cloud apart from other analytics tools.

Traditional Page Analytics, such as Views, Visitors, and Bounce Rate, provide
lots of information about how visitors interact with your site's pages. Liferay
Analytics offers that functionality, but also provides a novel set of metrics
for your site pages in the form of Path Analytics. In addition, several
@product@ Assets have even more fine-grained metrics to drill deeper than the
page level to determine how you can improve the performance of your site.

![Figure 1: Pages and Assets are important Touchpoints in Analytics Cloud.](../../images/pages-touchpoints-menu.png)

This tutorial shows where to find the analytics for Pages and Assets. The
remaining tutorials explain their metrics.

## Viewing Page Data [](id=viewing-page-data)

1.  Find the Touchpoints section of the menu.

2.  Click *Pages* to view the list of Pages with summary data.

![Figure 2: The Page list contains useful summary data.](../../images/pages-list.png)

Order Pages in ascending or descending order based on any one of these metrics:

- Average Engagement Score
- Total Visitors
- Total Views
- Average Bounce Rate
- Average Time on Page

<!--SCREENSHOT:currently there's no data to screenshot-->

<!-- In addition to ordering the Touchpoints, filter them by entering search terms.
There's a prominent search bar at the top of the Touchpoints table. Run a basic
search and see the matching Touchpoints returned.

Once you have some returned results, perhaps being ordered by one of the
available metrics in descending order, turn your attention to the Time Period
selector next to the search bar. It recalculates the result based on the time
period selected. If you go from a shorter time period to longer, you'll
naturally see more results. In addition, metrics are recalculated. -->

The metrics for ordering the Page list are calculated based on the time period
selected in the time period filter. The following values are supported:

- Last 24 hours
- Yesterday
- Last 7 days
- Last 28 days
- Last 30 days (default)
- Last 90 days

To see this in action, select *Order* &rarr; *Visitors*. Keep the default
descending order and select *Last 90 days* from the time period menu. The list
of Pages is recalculated, and the first result is the Page with the most 
visitors in the last 90 days.

![Figure 3: Order the pages in ascending or descending order, over a selected time period.](../../images/pages-order.png)

To view detailed metrics for a single page, click a page in the list. 

![Figure 4: Most Page metrics are available in the Overview tab.](../../images/pages-overview.png)

See [Page Analytics](https://github.com/liferay/liferay-docs/blob/master/discover/analytics-cloud/articles/04-analyzing-touchpoints/01-page-analytics.markdown) 
for more information.

## Finding Asset Data [](id=finding-asset-data)

1.  Find the Touchpoints section of the menu.

2.  Click *Assets* for a list of Assets with summary data.

3.  Choose an Asset type: Blogs, Documents and Media, Forms, or Web content.

Order Assets in ascending or descending order based on any one of the Asset's
metrics. The metrics differ between Assets. See each Asset's documentation for
more information. In addition, calculate the metrics over a selected time period
by selecting one of the following values from the period menu:

- Last 24 hours
- Yesterday
- Last 7 days
- Last 28 days
- Last 30 days (default)
- Last 90 days

To see this in action, select *Blogs* &rarr; *Order* &rarr; *Shares*. Keep the
default descending order and select *Last 7 days* from the time period menu.
The list of Blogs is recalculated, and the first result is the Blogs Entry with
the most shares in the last 7 days.

![Figure 5: Order Blogs in ascending or descending order, over a selected time period.](../../images/assets-blogs-order.png)

<!-- In addition to ordering the Assets, filter them by entering search terms.
There's a prominent search bar at the top of the Assets table. Run a basic
keyword search and see the matching Assets returned. 

SCREENSHOT

Once you have some returned Assets, being ordered by one of the available
metrics in descending order, find the Time Period selector next to the search
bar. It recalculates the result based on the time period selected. If you go
from a shorter time period to longer, you'll naturally see more results. In
addition, metrics are recalculated. Take Views as an example to see how this
works:

Take Forms as an example asset. Searching for *symposium* and ordering the
results in descending order by the Views metric would return a healthy sample of
results if the project was for Liferay's sites. However, you must also specify
the time period for which you'd like to view the metrics. The default is 30
days, but this is configurable. The following time values are supported:

- Last 24 hours
- Yesterday
- Last 7 days
- Last 28 days
- Last 30 days (default)
- Last 90 days

Take 90 days for our example search for *symposium*. Now all Forms containing
the word *symposium* are returned, in descending order of views *in the last 90
days*.-->

Click on an Asset in the list to see more granular detail. Remember that there
are separate lists for different Asset types.

![Figure 6: Once you find an Asset, click it to see its metrics.](../../images/assets-overview-blogs.png)

More details on each Asset type's metrics are covered in the subsequent
tutorials.
