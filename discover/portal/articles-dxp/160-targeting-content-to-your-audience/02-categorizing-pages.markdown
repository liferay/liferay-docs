# Categorizing Pages and Content for User Segments [](id=categorizing-pages-and-content-for-user-segments)

Each new user segment that's created can be used to categorize pages or content.
The Audience Targeting app adds a new *User Segment* select button to the SEO
section of pages and Metadata section for assets. These buttons can assign one
or more Site-scoped or global user segments to the content. This categorization
has mainly two purposes:

- Assigning points to users using the Score Points rule
- Showing dynamic lists of content in the User Segment Content List application

![Figure 1: Pages and content can be categorized for user segments.](../../images-dxp/audience-targeting-categorization.png)

You don't have to create categories for each of your user segments. User
segments are distinct from regular vocabularies. The editing screen for both
pages and assets contain distinct select buttons for user segments and regular
vocabularies.

Another way to display user segments is through the Asset Publisher app. You can
enable the Asset Publisher to retrieve assets that have matching categorization
with the user segments of the current user. This enhances the Asset Publisher to
only display relevant content to the user. To enable *User Segments Filter*,

1.  Navigate to the Asset Publisher's *Options* 
    (![Options](../../images-dxp/icon-app-options.png)) &rarr; *Configuration* 
    menu.

2.  Under the *Asset Selection* tab, Open the *User Segments Filter* option.

3.  Enable the *User Segments Filter*.

![Figure 2: Enabling the User Segments Filter retrieves assets that match the current user's user segments.](../../images-dxp/audience-targeting-asset-publisher-filtering.png)

Next, you'll learn about managing user segment reports.

## Managing User Segment Reports [](id=managing-user-segment-reports)

When managing user segments, you can select the user segment name and then
select the *Reports* tab to see the list of reports available for each user
segment. Click the report name to view the report or *Actions*
(![Actions](../../images-dxp/icon-actions.png)) &rarr; *Update Report* to
generate a new report. Reports display a summary of interesting information
related to each user segment. For example, the Content Views report shows the
asset viewed the most by users that belong to the user segment.

![Figure 3: This report displays what pages the user segment has visited.](../../images-dxp/audience-targeting-user-segment-report.png)

Reports also display which users belonged to a user segment. This lets an
administrator know which users of the Site joined the particular user segment.
You can export this list by clicking the user report you're interested in and
selecting the *Options* (![Options](../../images-dxp/icon-options.png)) &rarr;
*Export* button. This downloads a CSV file with the list of users.

Additional reports can be created by developers and deployed as OSGi plugins.
See the [Reporting User Behavior with Audience Targeting](/develop/tutorials/-/knowledge_base/7-1/reporting-user-behavior-with-audience-targeting)
tutorial for details. Reports are generated daily by default, but you can
generate a report at any time. To generate a new report when currently viewing
a report, click the *Update* button from the *Options* icon
(![Options](../../images-dxp/icon-options.png)) in the top right corner.

Next, you'll discover how to use your user segments in a campaign.
