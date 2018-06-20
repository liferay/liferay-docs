# Assets: Analyzing Content

Assets are individual pieces of content that exist in your Liferay Analytics
Cloud data sources. In summary, Assets are:

- Content residing on a Touchpoint page.
- Imported during the initial Analytics Cloud setup.
- Queried and reported on at regular intervals.
- Reported on in Analytics Cloud with the data reports shown in this article.

Metrics for these Assets are currently reported in Analytcis Cloud:

- Forms
- Blogs
- Documents and Media
- Web Content Articles

<!-- Should we link to portal documentation on these assets?-->

Readers of this article have an understanding of what Analytics Cloud is and
what they want to get out of their Asset data. They are here to understand where
Asset data exists, what metrics it includes, and how to interpret the data and
configure its views for easiest consumption.

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

SEPARATE ARTICLE
# Forms Metrics

## 



SEPARATE ARTICLE
# Blogs Metrics



SEPARATE ARTICLE
# Documents and Media Metrics


SEPARATE ARTICLE
# Web Content Metrics


