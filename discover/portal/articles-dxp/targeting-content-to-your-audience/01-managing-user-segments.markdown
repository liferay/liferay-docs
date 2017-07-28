# Managing User Segments [](id=managing-user-segments)

The *Audience Targeting* section of the Configuration section of the Site
Administration area of the Menu allows you to manage user segments and
campaigns.

![Figure 1: Click on *Configuration* &rarr; *Audience Targeting* in Site Administration to manage user segments and campaigns for a site.](../../images-dxp/audience-targeting-admin.png)

A user segment represents a subset of the total group of portal users (logged in
or not). A user segment is defined by one or more rules that users have to match
in order to belong to that user segment. Open the Site Administration menu and
click *Configuration* &rarr; *Audience Targeting* &rarr; *Add User Segment*
(![Add User Segment](../../images-dxp/icon-add.png)) to add a new user segment.
All the rules that have been deployed appear under the Rules heading. Drag a
rule to the right to apply the rule to the user segment. Once a rule has been
applied, you can adjust the rule's parameters. For example, once the Gender rule
has been applied, you can select *Male* or *Female*. Once the Age rule has been
applied, you can select an *Older than* value and a *Younger than* value. For
example, you could define a *Women over 30* user segment by applying the Gender
rule and selecting *Female*, and applying the Age rule and setting the *Older
than* attribute to *30*.

Some rules are also instantiable, meaning you can apply more than one of the
same type of rule to a user segment. This allows you to create scenarios where
you need to segment your audience based on actions that might occur multiple
times (e.g., visiting multiple pages, viewing multiple banners, having several
roles, etc.).

Once you've customized the rules for the new user segment, entered a name and,
optionally, a description, click *Save* to actually create the user segment.

![Figure 2: After applying the rule, click the rule to expland/collapse it.](../../images-dxp/audience-targeting-rules.png)

Once you've created a user segment, you can open its summary view, which
displays relevant data and configurations.

![Figure 3: Select a pre-existing user segment to view its Summary page.](../../images-dxp/user-segment-summary.png)

To configure permissions for creating, editing, and deleting user segments,
visit the *Users* &rarr; *Roles* section of the Control Panel. Then click on the
*Actions* &rarr; *Define Permissions* button corresponding to the role you'd
like to configure permissions for, and search for *Audience Targeting*.

Both user segments and campaigns are inherited in your site hierarchy.
Therefore, user segments and campaigns defined in the Global scope will be
available to any site. User segments and campaigns created in a site will be
available to all of its child sites.

## User Segment Rules [](id=user-segment-rules)

Custom rules can be created by developers and deployed as OSGi plugins. See the
[Creating New Audience Targeting Rule Types](/develop/tutorials/-/knowledge_base/6-2/creating-new-audience-targeting-rule-types)
tutorial for details.

<!-- Update tutorial above, when available. -Cody -->
 
These are some of the rules that are included with the app by default:

- User Attributes
    - Age (from the user profile)
    - Gender (from the user profile)
    - Role (regular role, organization role or site role)
    - Membership (site member, organization member, user group member)
- Session Attributes
    - Location (obtained from the IP address)
    - Browser, Device, Operating system
    - Time
- Social
    - Like of a specific Facebook page
    - Number of Facebook friends
    - City, Age, Gender, Education, etc. from your Facebook profile
- Behavior
    - Viewed page or content
    - Score Points rule.

For a complete reference of all rules available, see the
[Liferay Audience Targeting Rules](/develop/tutorials/-/knowledge_base/7-0/liferay-audience-targeting-rules)
tutorial.

The Score Points rule assigns 1 point to a user each time the user views a page
or content that's been categorized under the user segment to which the rule has
been applied. Once a user exceeds the configured threshold, the user matches
this rule. For example, suppose that your website is about sports and you have
pages and content about basketball, tennis, and soccer. You would like to divide
your audience into three user segments (Basketball fans, Tennis fans, and Soccer
fans) in order to display the most relevant content to them on your site's front
page. After creating these three user segments using the Score Points rule with
a threshold of, say, 20, you should appropriately categorize the content which
would be most relevant to each user segment. For example, apply the *Basketball
fans* user segment to content about basketball, apply the *Tennis fans* user
segment to content about tennis, etc. Now, every time a user (even a guest user)
visits a page or views a piece of content categorized for a user segment to
which the Score Points rule has been applied, the user will start accumulating
points. Once the user has accumulated enough points, the user will belong to the
user segment. After a user has visited more than 20 pages or pieces of content
related to basketball, the user will belong to the Basketball fans user segment.
Once the user belongs to a user segment, you can use that information to direct
more relevant information to the user in your website using the User Segment
Content Display application.

## Categorizing Pages and Content for User Segments [](id=categorizing-pages-and-content-for-user-segments)

Each new user segment that's created can be used to categorize pages or content.
The Audience Targeting app adds a new *User Segment* select button to the SEO
section of pages and Metadata section for assets. These buttons allow you to
assign one or more site-scoped or global user segments to the content. This
categorization has mainly two purposes:

- Assigning points to users using the Score Points rule
- Showing dynamic lists of content in the User Segment Content List application

![Figure 4: Pages and content can be categorized for user segments.](../../images-dxp/audience-targeting-categorization.png)

You don't have to create categories for each of your user segments. User
segments are distinct from regular vocabularies. The editing screen for both
pages and assets contain distinct select buttons for user segments and regular
vocabularies.

Another way to display user segments is through the Asset Publisher app. You can
enable the Asset Publisher to retrieve assets that have matching categorization
with the user segments of the current user. This enhances the Asset Publisher to
only display relevant content to the user. To enable *User Segments Filter*,
navigate to the Asset Publisher's *Options*
(![Options](../../images-dxp/icon-app-options.png)) &rarr; *Configuration* menu
and select the *Audience Targeting* option. Then enable the *User Segments
Filter*.

![Figure 5: Enabling the User Segments Filter retrieves assets that match the current user's user segments.](../../images-dxp/audience-targeting-asset-publisher-filtering.png)

Next, you'll learn about managing user segment reports.

## Managing User Segment Reports [](id=managing-user-segment-reports)

When managing user segments, you can select the user segment name and then
select the *Reports* tab to see the list of reports available for each user
segment. Click the report name to view the report or *Actions*
(![Actions](../../images-dxp/icon-actions.png)) &rarr; *Update Report* to
generate a new report. Reports display a summary of interesting information
related to each user segment. For example, the Content Views report shows the
asset that's been most viewed by users that belong to the user segment.

![Figure 6: This report displays what pages the user segment has visited.](../../images-dxp/audience-targeting-user-segment-report.png)

Reports also display which users belonged to a user segment. This lets an
administrator know which users of the site qualified to the particular user
segment. @product@ provides a convenient way to export a list of user segment
users. Simply click the user report you're interested in and select the
*Options* (![Options](../../images-dxp/icon-options.png)) &rarr; *Export*
button. This downloads a CSV file with the list of users.

Additional reports can be created by developers and deployed as OSGi plugins.
See the [Reporting User Behavior with Audience Targeting](/develop/tutorials/-/knowledge_base/6-2/reporting-user-behavior-with-audience-targeting)
tutorial for details. Reports are generated daily by default. However, you can
generate a report at any time. To generate a new report when currently viewing a
report, click on the *Update* button from the *Options* icon 
(![Options](../../images-dxp/icon-options.png)) in the top right corner.

Next, you'll discover how to use your user segments in a campaign.
