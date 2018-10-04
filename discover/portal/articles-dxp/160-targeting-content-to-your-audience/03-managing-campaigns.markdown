# Managing Campaigns [](id=managing-campaigns)

A campaign is an effort to expose certain user segments to a set of
assets within a specific period of time. To manage campaigns for a Site,

1.  Navigate to *Site Administration* &rarr; *Configuration* &rarr; *Audience
    Targeting* &rarr; *Campaigns*.

2.  To create a new campaign, select the (![Add Campaign](../../images-dxp/icon-add.png)) button.

3.  Set a *Name* and *Description.

4.  Select the *User Segments* to target, a *Start Date*, an *End Date*, and
    a *Priority*.

5.  Indicate if the campaign you create should be active or inactive.

6.  Click *Save*.

The user segments you select when creating a campaign represent users targeted
by the campaign. The start and end dates together specify the duration of the
campaign. There can be multiple campaigns active at the same time that target
the same user segments. In these situations, the priority attribute of the
campaigns determines which campaign takes precedence. Finally, you can activate
or deactivate a campaign via the *Active* attribute of a campaign. Deactivating
a campaign disables the effect of the campaign. A deactivated campaign can be
reactivated later. It can be useful to deactivate a campaign if a problem is
found with the way content is being displayed. Once the problem has been
corrected, the campaign can be reactivated.

![Figure 1: Navigate to Site Administration and click *Configuration* &rarr; *Audience Targeting* &rarr; *Campaigns* to manage campaigns for a Site.](../../images-dxp/audience-targeting-user-campaigns.png)

For example, suppose you wanted to display certain content (life advertisements
for your new Android app) to female baseball fans during the months leading up
to the World Series. You could use the Gender rule (configured to female), the
Device Rule (configured for Android devices), and the Score Points rule to
define a user segment called *Female Baseball Fans*. The Score points rule
assigns one point to a user each time the user visits a page or views an asset
categorized under the user segment *Female Baseball Fans*. When a user
accumulates a certain number of points (specified by the value of the Score
Points rule's *Score Points Threshold* attribute), the user matches this rule.
Once you have the user segment, you can create a new campaign targeting
this segment, select start and end dates, choose a priority, choose *Active*,
and then click *Save*. To present content to the users belonging to the *Female
Baseball Fans* user segment, you must use the Campaign Content Display
application.

Once you've created a campaign, you can open its summary view, which displays
relevant data and configurations.

![Figure 2: Select a pre-existing campaign to view its Summary page.](../../images-dxp/campaign-summary.png)

To configure permissions for creating, editing, and deleting user segments,
visit the Roles section of the Control Panel. Then click on the *Actions* &rarr;
*Define Permissions* button corresponding to the Role you want to configure
permissions for, and search for *Audience Targeting*.

## Managing Campaign Priorities [](id=managing-campaign-priorities)

The priority of your campaigns becomes important when multiple campaigns are
running at the same time on your website. The Campaign Content Display
application can be configured to display content based on the campaign your
users match. When a user matches multiple campaigns, the one with the highest
priority takes precedence. 
 
If you have several Campaign Content Display applications around your website
configured to display different content per campaign, changing the priority of
one campaign automatically affects all Campaign Content Display applications.
Similarly, if a campaign is deactivated or if a campaign's date range is
exceeded, all Campaign Content Display applications on your website are
affected.

