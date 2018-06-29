# Viewing and Understanding Analytics

Combining traditional Page Analytics with Path Analytics and Asset Analytics is
what makes Liferay Analytics Cloud special.

A Liferay DXP site often features pages and content. Understanding traditional
Page Analytics like Views, Visitors, and Bounce Rate is great. You can glean
lots of good information about how site visitors are interacting with your site
pages. Liferay Analytics offers that functionality, but provides a novel set of
metrics for your site pages, Path Analytics, in additioon to traditionaly Page
Analytics. In addition, several Liferay DXP Assets have even more fine-grained
metrics to let you drill down deeper than the page level to determine how you
can improve the performance of your site.

## Viewing Page Data

To view page data directly:

1.  Find the Engagement section of the menu.

2. Click *Pages*.

3. From the list of pages, click any one to see a detailed view and
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

- Average Engagement Score
- Total Visitors
- Total Views
- Average Bounce Rate
- Average Time on Page

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

# Where is Asset Data?

To view Asset data directly:

1.  Find the Engagement section of the menu.

2. Click *Assets*.

To view the Asset data for a particular Individual or Segment:

1.  Navigate to the Individuals or Segments screen in the People section of the
    menu.

2. The Overview tab contains analytics on the Individual's (or Segment's)
   interaction with Assets. <!-- CHECK THIS -->

SCREENSHOT

The table of Assets provides two ways to visualize Assets: 

Order Assets by ascending or descending order based on any one of the Asset's
metrics. The specific metrics differ between Assets. See each Asset's
documentation for more information.

SCREENSHOT

In addition to ordering the Assets, filter them by entering search terms.
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
days*.

SCREENSHOT

Viewing and searching the list of Asset, with overview data displayed, is quite
helpful. But what if you want to dive into a single Asset's metrics?

Each Asset's metrics are covered in a separate article.

