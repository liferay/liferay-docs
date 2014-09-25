## Targeting Content To Your Audience

Liferay's Audience Targeting app allows you to divide your audience into user
segments, target specific content to different user segments, and create
campaigns for different user segments. It also allows you to quickly measure the
effectiveness of your campaigns. User segments allow you to configure your
website to display different assets to different users. Campaigns allow you to
display specific content to different user segments for fixed periods of time.
They also allow you to measure the interaction of the targeted user segments
with the chosen content.

Suppose you'd like to display advertisements to users of your website. You'd
like to display one set of advertisements to one user segment and another set of
advertisements to another user segment. Maybe this should dynamically change
every week based on your marketing goals. Or maybe you have created several sets
of news articles for your website that would be interesting to different user
segments. You'd like to display news articles to users based on all the user
segments to which they belong. 
 
Liferay's Audience Targeting app, available from [Liferay Marketplace](http://www.liferay.com/marketplace),
allows you to create multiple user segments which are defined by multiple rules
based on session attributes, profile attributes, behavior, and information from
social networks. Developers can easily create additional rules and rule types
with minimal coding efforts. The Audience Targeting App also allows you to
create campaigns that target a specific user segment. Campaigns last for fixed
periods of time and each campaign has a priority. If you are running several
campaigns on your website at the same time, the priority field determines which
campaign takes precedence. Campaigns allow you to configure different assets to
be displayed at different periods of time to the targeted user segment.
 
The Audience Targeting App adds an *Audience Targeting* section to the
Configuration section of the Site Administration area of the Control Panel and
an Audience Targeting Simulator to the Dockbar. The following three applications
are also included with the Audience Targeting app:

- User Segment Content Display
- User Segment Content List
- Campaign Content Display

You can add these applications to any portal page. In the next sections, we'll
explain how to use the *Audience Targeting* section of the Configuration section
of the Site Administration area of the Control Panel to manage user segments and
campaigns. Then we'll explain how to use each of Audience Targeting
applications and the Audience Targeting Simulator.

### Managing User Segments

The *Audience Targeting* section of the Configuration section of the Site
Administration area of the Control Panel allows you to manage user segments and
campaigns.

![Figure 6.x: Click on *Site Administration* &rarr; *Configuration* &rarr; *Audience Targeting* to manage user segments and campaigns for a site.](../../images/07-audience-targeting-admin.png)

A user segment represents a subset of the total group of portal users (logged in
or not). A user segment is defined by one or more rules that users have to match
in order to belong to that user segment. Click on *Site Administration* &rarr;
*Configuration* &rarr; *Audience Targeting* &rarr; *New User Segment* to add a
new user segment. All the rules that have been deployed appear under the Rules
heading. Drag a rule to the right to apply the rule to the user segment. Once a
rule has been applied, you can adjust the rule's parameters. E.g., once the
Gender rule has been applied, you can select *Male* or *Female*. Once the Age
rule has been applied, you can select an *Older than* value and a *Younger than*
value. For example, you could define a *Women over 30* user segment by applying
the Gender rule and selecting *Female* and applying the Age rule and setting the
*Older than* attribute to *30*. Once you've customized the rules for the new
user segment, entered a name and, optionally, a description, click *Save* to
actually create the user segment.

![Figure 6.x: Drag a rule to the right to create a user segment.](../../images/07-audience-targeting-rules.png)

To configure permissions for creating, editing, and deleting user segments,
visit the Roles section of the Control Panel and search for *Audience Targeting
Resources*.

Both user segments and campaigns are inherited in your site hierarchy.
Therefore, user segments and campaigns defined in the Global scope will be
available to any site. User segments and campaigns created in a site will be
available to all of its child sites.

#### User Segment Rules

Custom rules can be created by developers and deployed as OSGi plugins. See the
[Creating Custom Audience Targeting Rules](https://www.liferay.com) tutorial for
details.
 
These are some of the rules that are included with the app by default:

- User Attributes
    - Age (from the user profile)
    - Gender (from the user profile)
    - Role (regular role, organization role or site role)
    - Membership (site member, organization member, user group member)
- Social
    - Like of a specific Facebook page
    - Number of Facebook friends
    - City, Age, Gender, Education, etc. from your Facebook profile
- Session Attributes
    - Location (obtained from the IP address)
    - Browser, Device, Operating system
    - Time
- Behavior
    - Viewed page or content
    - Score Points rule.
    
The Score Points rule assigns 1 point to a user each time the user views a page
or content that's been categorized under the user segment to which the rule has
been applied. Once a user exceeds the configured threshold, the user matches
this rule. For example, suppose that your website is about sports and you have
pages and content about basketball, tennis, and soccer. You would like to divide
your audience into three user segments (Basketball fans, Tennis fans, and Soccer
fans) in order to display the most relevant content to them on your site's front
page. After creating these three user segments using the Score Points rule with
a threshold of, say, 20, you should appropriately categorize the content which
would be most relevant to each user segment. E.g., apply the *Basketball fans*
user segment to content about basketball, apply the *Tennis fans* user segment
to content about tennis, etc. Now, every time a user (even a guest user) visits
a page or views a piece of content categorized for a user segment to which the
Score Points rule has been applied, the user will start accumulating points.
Once the user has accumulated enough points, the user will belong to the user
segment. After a user has visited more than 20 pages or pieces of content
related to basketball, the user will belong to the Basketball fans user segment.
Once the user belongs to a user segment, you can use that information to direct
more relevant information to the user in your website using the User Segment
Content Display application.

#### Categorizing Pages and Content for User Segments

Each new user segment that's created can be used to categorize pages or content.

+$$$

**Note:** Page categorization is a feature added by the Audience Targeting app.
In a regular Liferay Portal 6.2 instance without the Audience Targeting app
installed, pages cannot be categorized.

$$$

The Audience Targeting app adds two select buttons to the Categorization section
of pages and assets: *User Segment* and *User Segment (Global)*. These buttons
allow you to assign one or more site-scoped or global user segments to the
content. This categorization has mainly two purposes:

- Assigning points to users using the Score Points rule
- Showing dynamic lists of content in the User Segment Content List application

![Figure 6.x: Pages and content can be categorized for user segments.](../../images/07-audience-targeting-categorization.png)

You don't have to create categories for each of your user segments. User
segments are distinct from regular vocabularies. The Categorization section of
pages and assets contains distinct select buttons for user segments and regular
vocabularies.

#### Managing User Segment Reports

When managing user segments, you can click *Actions* &rarr; *Reports* next to a
user segment to see the list of reports available for each user segment. Click
*Actions* &rarr; *View Report* to view the report or *Actions* &rarr; *Update
Report* to generate a new report. Reports display a summary of interesting
information related to each user segment. For example, the Content Views report
shows the asset that's been most viewed by users that belong to the user segment.

![Figure 6.x: the Content Views report shows the asset that's been most viewed by users that belong to a user segment.](../../images/07-audience-targeting-user-segment-report.png)

Additional reports can be created by developers and deployed as OSGI plugins.
See the [Creating Custom Audience Targeting Reports](http://www.liferay.com)
tutorial for details. Reports are generated daily by default. However, you can
generate a report at any time. To generate a new report when currently viewing a
report, click on the *Update Report* button in the top right corner.

### Managing Campaigns

A campaign represents an effort to expose a certain user segment to a certain
set of assets within a specific period of time. To manage campaigns for a site,
navigate to *Site Administration* &rarr; *Configuration* &rarr; *Audience
Targeting* &rarr; *Campaigns*. To create a new campaign, you need to select a
user segment to target, a start date and an end date, and a priority, as well as
a name and, optionally, a description. You also have to indicate whether or not
the campaign you create should be active or inactive. When you've entered the
required information, click *Save*. The user segment you select when creating a
campaign represents the portal users targeted by the campaign. The start and end
dates together specify the duration of the campaign. There can be multiple
campaigns active at the same time that target the same user segment. In these
situations, the priority attribute of the campaigns determines which campaign
takes precedence. Finally, you can activate or deactivate a campaign via the
active attribute of a campaign. Deactivating a campaign disables the effect of
the campaign within the portal. Deactivating a campaign is like deleting the
campaign except that a deactivated campaign can be reactivated later. It can be
useful to deactivate a campaign if a problem is found with the way content is
being displayed. Once the problem has been corrected, the campaign can be
reactivated.

![Figure 6.x: Click on *Site Administration* &rarr; *Configuration* &rarr; *Audience Targeting* &rarr; *Campaigns* to manage campaigns for a site.](../../images/07-audience-targeting-user-campaigns.png)

For example, suppose you wanted the ability to display certain content (for
example, advertisements about your new Android app) to female baseball fans
during the months leading up to the World Series. To achieve this, you could use
the Gender rule (configured to female), the Device Rule (configured for Android
devices) and the Score Points rule to define a user segment called *Female
Baseball Fans*. The Score points rule assigns 1 point to a user each time the
user visits a page or views an asset categorized under the user segment *Female
Baseball Fans*. When a user accumulates a certain number of points (specified by
the value of the Score Points rule's *Score Points Threshold* attribute), the
user matches this rule. After creating this user segment, you would create a new
campaign targeting this segment, select start and end dates, choose a priority,
choose *Active*, and then click *Save*. To actually present content to the users
belonging to the *Female Baseball Fans* user segment, you need to use the
Campaign Content Display application.

To configure permissions for creating, editing, and deleting campaigns, visit
the Roles section of the Control Panel and search for *Audience Targeting
Resources*.

#### Managing Campaign Priority

The priority of your campaigns becomes important when multiple campaigns are
running at the same time on your website. The Campaign Content Display
application can be configured to display content based on the campaign your
users match. When a user matches multiple campaigns, the one with the highest
priority takes precedence. 
 
If you have several Campaign Content Display applications around your website
configured to display different content per campaign, changing the priority of
one campaign automatically affects all the Campaign Content Display
applications. Similarly, if a campaign is deactivated or if a campaign's date
range is exceeded, all of the Campaign Content Display applications on your
website are affected.

#### Defining Tracking Actions

One of the most interesting features of campaigns is that they allow you to
measure the effectiveness of a campaign. This provides your marketing team with
real feedback from users. When creating a campaign, you can define the user
actions that you want to track. The Audience Targeting app can display reports
of how often those actions are triggered. For example, suppose you want to run a
campaign for an event that your company is hosting next month. For this event,
imagine that you have created a main page for the event which contains a Youtube
video and a banner which says "Register Now". Imagine also that you have a blog
entry about the event displayed on several different pages of your website and a
Register page which contains the form to pay for the event. In this campaign,
your goal is to get as many people to register as possible. However, you will
probably be interested in tracking the following information to see if there is
something not working as your team expected:

 - Visits to the main page of the event
 - Clicks to view the video
 - Number of users who watched the video until the end
 - Clicks on the Register Now banner
 - Views of the blog entry about the event
 - Views of the Register form
 - Number of users who started to fill out the Register form
 - Number of users who completed the registration

![Figure 6.x: Drag and drop tracking actions to the right to configure a campaign.](../../images/07-audience-targeting-tracking-actions.png)
 
You could drag and drop *tracking actions* from the palette to track all the
actions mentioned above. More types of tracking actions can be created by
developers and deployed as OSGI plugins. See the [Creating Custom Audience Targeting Tracking Actions](https://www.liferay.com)
tutorial for details.

The tracking actions use an analytics engine called *Audience Targeting
Analytics* that can be configured per site or per portal instance. To configure
the analytics engine per site, go to *Site Administration* &rarr; *Site
Settings*. To configure it per portal instance, go to *Control Panel* &rarr;
*Portal Configuration*. Tracking all the actions of all your users (even guest
users) can be a very heavy load for your server. Therefore, it's best to disable
the tracking of any actions about which you don't need information.

#### Campaign Reports

Reports are available for campaigns. You can click *Actions* &rarr; *Reports*
next to a campaign to see the list of reports available. More reports can be
created by developers and deployed as OSGI plugins. See the
[Creating Custom Audience Targeting Reports](https://www.liferay.com)
tutorial for details. 

The Content Views report shows the number of times that different assets have
been viewed via the Campaign Content Display application by users in the context
of the current campaign. For example, if you configured five Campaign Content
Display applications around your website to display content for a campaign, the
Content View report for the campaign would show how many times was that content
displayed to different users.

The Tracking Actions report shows the number of times that the actions tracked
by the campaign have been triggered by users. Consider the example that we
introduced earlier in the section on tracking actions: you've created a campaign
for an event that your company will host soon. For this event, you have created
a main page for the event which contains a Youtube video and a banner which says
"Register Now". You also have created a blog post about the event which is
displayed on several different pages of your website. Lastly, you have a
Register page which contains the form to pay for the event. For this example,
the Tracking Actions report would show you how many users visited the event
page, how many watched the video, how many clicked on the banner, how many
viewed the blog post about the event, how many started filling the
registration form, etc. This information helps you measure the effectiveness of
your campaign. You can use this information to evaluate whether or not the users
are following the engagement path you had prepared.

### Using the Audience Targeting Applications

The Audience Targeting app not only adds the Audience Targeting application to
the Site Administration area of the Control Panel, it also includes the
following instanceable applications which can be added to any portal page:

- User Segment Content Display
- User Segment Content List
- Campaign Content Display

All of these applications support Application Display Templates (ADTs) so that
site administrators can customize the look and feel of the application. Any of
Liferay Portal's out-of-the-box Asset Publisher ADTs can actually be re-used for
these Audience Targeting applications.

#### User Segment Content Display

The User Segment Content Display application allows administrators to specify
exactly which content to display to a user based on the user segments that the
user belongs to. You can specify multiple rules according to the following
format:

- If the user \[belongs|does not belong\] to \[any|all\] of the following user
  segments [specify a list of user segments], then display this content:
  \[specify a specific asset\].
  
You can specify any number of *if* clauses when configuring the User Segment
Content Display application's rules. However, an *otherwise* clause always follows
the last *if* clause so that the application knows what to display if the user
doesn't match any user segments. *Don't display anything* is an option for the
*otherwise* clause.

![Figure 6.x: You can configure the User Segment Content Display application to display content according to rules that you define in the application's configuration window.](../../images/user-segment-content-display-config.png)

For example, you can add a User Segment Content Display application to a page and
configure the following rules for it:

- If the user *belongs* to *any* of the following user segments: *Tennis fans*,
  then display this content: *tennis_picture.jpg*.
- If the user *belongs* to *any* of the following user segments: 
  *Basketball fans*, then display this content: *basketball_picture.jpg*.
- If the user *belongs* to *any* of the following user segments: *Soccer*, then
  display this content: *soccer_picture.jpg*.
- Otherwise, *Don't display anything*.

Once a User Segment Content Display application has been added to a page and been
configured this way, users (even guest users) will see a different image based
on the user segment to which they belong. The application won't even be visible
to a user if the user doesn't belong to any of the configured user segments.

![Figure 6.x: In the User Segment Content Display application, site administrators can preview the various assets that have been configured to be displayed to different user segments.](../../images/07-audience-targeting-uscd.png)

The User Segment Content Display application allows site administrators to
preview the various assets that have been configured to be displayed to
different user segments.

#### User Segment Content List

The User Segment Content List application displays content that has been
categorized for the user segments that match the user segments to which the
current user belongs. For example, suppose that your website has several assets
categorized under the following user segments: *Tennis fans*, *Soccer fans*, and
*Basketball fans*. When a user that belongs to the Tennis fans user segment
views this application, the application displays a list of assets categorized
for the Tennis fans user segment. If the user belongs to multiple user segments,
then a list of articles that have been categorized for any of the matching user
segments will be shown.

![Figure 6.x: The User Segment Content List application displays content that matches the user segments which fit the current user. You can configure the User Segment Content List application to display assets of any kind or only specific kinds of assets.](../../images/user-segment-content-list.png)

By default, the User Segment Content List application is configured to display
assets of any type that have been categorized for any user segment that matches
the current user. However, you can configure the User Segment Content List
application to display only assets of specific types. For example, you can
configure the User Segment Content List application to only display web content
articles and documents.

#### Campaign Content Display

The Campaign Content Display application is similar to the User Segment Content
Display application except that instead of displaying an asset based on the user
segments to which a user belongs, it displays an asset based on the campaigns
that a user matches. However, the Campaign Content Display application's display
rules are simpler than those of the User Segment Content Display application.
You can specify multiple rules for the Campaign Content Display application
according to the following format:

- If the user belongs to this campaign: [select a campaign], then display this
  content: \[specify a specific asset\].

As with the User Segment Content Display application, you can specify any number
of *if* clauses when configuring the Campaign Content Display application. An
*otherwise* clause always follows the last *if* clause so the Campaign Content
Display application knows what to display if the user doesn't match any
campaigns. These rules cannot be ordered manually as they can with the User
Segment Content Display application. The order of the rules is based on the
priority of the campaigns.

![Figure 6.x: The rules for configuring the Campaign Content Display application to display content are similar to the rules of the User Segment Content Display application, but simpler.](../../images/campaign-content-display-config.png)

For example, suppose that you've created two campaigns called *World Cup* and
*Stanley Cup*, designed to target users who are interested in the World Cup of
soccer and the Stanley Cup of hockey. You could add a Campaign Content Display
application to a page and configure it with the following rules:

- If the user belongs to this campaign: *World Cup*, then display this content:
  *soccer-jersey.jpg*.
- If the user belongs to this campaign: *Stanley Cup*, then display this
  content: *hockey-jersey.jpg*.
- Otherwise, display this content: *generic-sports-jersey.jpg*

Once a Campaign Content Display application has been added to a page and been
configured this way, portal users who match the World Cup campaign will see a
certain image in the application. Users who match the Stanley cup campaign will
see a different image in the application. Users who don't match either campaign
will see the default image. Of course, once a campaigns has ended, no user will
match that campaign and once all campaigns have ended, all users will see the
default image.

The Campaign Content Display application, like the User Segment Content Display
application, allows site administrators to preview the different assets that
will be displayed for different campaigns.

### Simulating User Segments and Campaigns

The Audience Targeting app includes a simulator feature. The Audience Targeting
Simulator allows administrators to preview the way portal pages appear to
different users. It does so by allowing site administrators to modify the
matched user segments or campaigns. The Audience Targeting Simulator is
accessible via the Dockbar. The Simulator icon appears below the Add, Preview,
and Edit icons but above the Edit Controls icon on the left side of the Dockbar.
Clicking on the Simulator icon opens a panel which has two tabs: *User Segments*
and *Campaigns*. By default, the Audience Targeting Simulator is only visible to
site administrators and users with the View permission for the Audience Targeting
Simulator.

+$$$

Permissions pertaining to the Audience Targeting Simulator and other features of
the Audience Targeting app can be granted via the Roles section of the Control
Panel.

$$$

The User Segments tab of the Audience Targeting simulator displays a list of all
the user segments matched by the current user as well as all the user segments
not matched by the current user. Any matched user segment can be unselected and
any unmatched user segment can be selected. This allows site administrators to
simulate how the website would appear to different users. Clicking on the
*Simulate* button makes your website appear to you the way it would appear to
the selected user segments. The simulation continues until you click the *Stop
Simulation* button. This allows site administrators to navigate around their
site as if they were a user that matched different user segments or campaigns.

![Figure 6.x: Audience Targeting Simulator](../../images/07-audience-targeting-simulator.png)

The Campaigns tab of the Content Targeting Simulator displays all matched
campaigns and all unmatched campaigns. It does not offer a *Simulate* option
yet. However, since campaigns target specific user segments, it's easy to
simulate how your site would look if certain campaigns were matched.

