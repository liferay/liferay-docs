## Using the Audience Targeting Application

Liferay's Audience Targeting application allows your organization to configure
different assets to be displayed to different categories of users. Maybe you'd
like to display advertisements to users but you'd like to display one
advertisement to males and another advertisement to females. Or maybe you'd like
to display a set of news articles to users who are less than thirty years old
and another set of news articles to users who are age thirty or older. Liferay's
Audience Targeting app, available from [Liferay Marketplace](http://www.liferay.com/marketplace),
allows you to create multiple user segments and to define multiple rules (such
as the gender rule and age rule) for each one. The Audience Targeting app also
allows you to create campaigns that target a specific user segment. Campaigns
last for a fixed period of time. They allow you to configure different assets to
be displayed at different periods of time to the targeted user segment. The
Audience Targeting app adds an *Audience Targeting* section to the Configuration
section of the Site Administration area of the Control Panel. The following four
portlets are also included with the Audience Targeting app:

- User Segment Content Display
- User Segment Content List
- Campaign Content Display
- Content Targeting Simulator

You can add these portlets to any portal page. In the next sections, we'll
explain how to use the *Audience Targeting* section of the Configuration section
of the Site Administration area of the Control Panel to create user segments and
campaigns. Then we'll explain how to use each of Audience Targeting portlets.

### Managing User Segments and Campaigns

The *Audience Targeting* section of the Configuration section of the Site
Administration area of the Control Panel allows you to create user segments and
campaigns.

![Figure 6.x: Click on *Site Administration* &rarr; *Configuration* &rarr; *Audience Targeting* to manage user segments and campaigns.](../../images/audience-targeting.png)

A user segment represents a subset of the total group of portal users. In order
for a user segment to be useful, you need to configure one or more rules for it.
Custom rules can be created by developers and deployed as OSGi plugins. See the
[Creating Custom Audience Targeting Rules](www.liferay.com) tutorial for
details. Click on *Site Administration* &rarr; *Configuration* &rarr; *Audience
Targeting* &rarr; *New User Segment* to add a new user segment.  All the rules
that have been deployed appear under the Rules heading. Drag a rule to the right
to apply the rule to the user segment. Once a rule has been applied, you can
adjust the rule's parameters. E.g., once the Gender rule has been applied, you
can select *Male* or *Female*. Once the Age rule has been applied, you can
select an *Older than* value and a *Younger than* value. For example, you could
define a *Women over 30* user segment by applying the Gender rule and selecting
*Female* and applying the Age rule and setting the *Older than* attribute to
*30*. Once you've customized the rules for the new user segment, entered a name,
and, optionally, a description, click *Save* to actually create the user
segment.

A campaign represents an effort to expose a certain user segment to a certain
set of assets within a specific period of time. To create a new campaign, you
need to select a user segment to target, a start date and an end date, and a
priority, as well as a name and, optionally, a description. You also have to
indicate whether or not the campaign you create should be active or inactive.
When you've entered the required information, click *Save*. The user segment you
select when creating a campaign represents the portal users targeted by the
campaign. The start and end dates together specify the duration of the campaign.
There can be multiple campaigns active at the same time that target the same
user segment. In these situations, the priority attribute of the campaigns
determines which campaign takes precedence. Finally, you can activate or
deactivate a campaign via the active attribute of a campaign. Deactivating a
campaign disables the effect of the campaign within the portal. Deactivating a
campaign is like deleting the campaign except that a deactivated campaign can be
reactivated later. It can be useful to deactivate a campaign if a problem is
found with the way content is being displayed. Once the problem has been
corrected, the campaign can be reactivated.

For example, a suppose you wanted the ability to display certain content (for
example, advertisements) to female baseball fans during the months leading up to
the World Series. To achieve this, you could use the Gender rule and the Score
Points rule to define a user segment called *Female Baseball Fans*. The Score
points rule assigns 1 point to a user each time they visit a page or view an
asset categorized under the name of the user segment: *Female Baseball Fans*.
When a user accumulates a certain number of points (specified by the value of
the Score Points rule's *Score Points Threshold* attribute), they're assigned to
the user segment. After creating this user segment, you would create a new
campaign targeting this segment, select start and end dates, choose a priority,
choose *Active*, and then click *Save*. To actually present content to the users
belonging to the *Female Baseball Fans* user segment, you need to use the
Campaign Content Display portlet or one of the other portlets provided by the
Audience Targeting app.

When managing user segments and campaigns from the Configuration section of the
Site Administration area, you can click *Actions* &rarr; *Reports* next to a
user segment or campaign. The Audience targeting application tracks the total
number of views of assets associated with a user segment or campaign by users
who fit the user segment or campaign. (You can "associate" an asset with a user
segment or campaign by configuring one of the Audience Targeting portlets to
display the asset to users who fit the user segment or campaign; see the
following section for details.) If no reports have been generated, a *No reports
for [user segment|campaign] content were found* message appears. Click on the
*Update Report* button to generate a report or update an existing report. The
report displays a list of the assets that have been targeted to the user segment
or campaign along with a graph that displays the number of views of each asset.

![Figure 6.x: Site Administrators can generate reports that display the total number of views of each asset that has been targeted to a user segment or has been associated with a campaign.](../../images/user-segment-report.png)

When you click *Actions* &rarr; *Reports* for a newly created user segment or
campaign, a *No reports for user segment content were found* message appears.
You need to associate specific assets with the user segment or campaign so that
the Audience Targeting application knows which data to track and display in the
reports. To select specific assets to display to different user segments or
campaigns, use the Audience targeting portlets. In the next section, we explain
how to use and configure these portlets.

### Using the Audience Targeting Portlets

The Audience Targeting app not only adds the Audience Targeting portlet to the
Site Administration area of the Control Panel, it also includes the following
four instanceable portlets which can be added to any portal page:

- User Segment Content Display
- User Segment Content List
- Campaign Content Display
- Content Targeting Simulator

The User Segment Content Display portlet allows administrators to specify
exactly which content to display to a user based on the user segments that the
user fits in. You can specify multiple rules according to the following format:

- If the user \[belongs|does not belong\] to \[any|all\] of the following user
  segments [specify a list of user segments], then display this content:
  \[specify a specific asset\].
  
You can specify any number of *if* clauses when configuring the User Segment
Content Display portlet's rules. However, an *otherwise* clause always follows
the last *if* clause so that the portlet knows what to display if the user
doesn't match any user segments. *Don't display anything* is an option for the
*otherwise* clause.

![Figure 6.x: You can configure the User Segment Content Display portlet to display content according to rules that you define in the portlet's configuration window.](../../images/user-segment-content-display-config.png)

For example, you can add a User Segment Content Display portlet to a page and
configure the following rules for it:

- If the user *belongs* to *any* of the following user segments: *Males*, then
  display this content: *tennis_site.jpg*.
- If the user *belongs* to *any* of the following user segments: *Basket*, then
  display this content: *basket_site_1.jpg*.
- If the user *belongs* to *any* of the following user segments: *Biking*, then
  display this content: *bike_site.jpg*.
- Otherwise, *Don't display anything*.

Once a User Segment Content Display portlet has been added to a page and been
configured this way, portal users who are male will see a certain image in the
portlet. Users who fit the Basket user segment will see a different image in the
portlet. User who fit fit the Biking user segment will see yet another different
image in the portlet. Users who don't match any of the user segments won't see
anything in the portlet. If the portlet's borders are turned off, the portlet
won't even be rendered on the page.

The User Segment Content List portlet displays content that matches the user
segments to which the current user belongs. It uses categories as the underlying
mechanism that determines which assets to display. When a new user segment is
created, a corresponding category (i.e., a category with the same name as the
user segment) is also created. When creating or editing assets, content authors
can apply any of the categories that correspond to existing user segments.

![Figure 6.x: The User Segment Content List portlet displays content that matches the user segments which fit the current user. You can configure the User Segment Content List portlet to display assets of any kind or only specific kinds of assets.](../../images/user-segment-content-list.png)

By default, the User Segment Content List portlet is configured to display
assets of any type that have been categorized with the category of a user
segment that matches the current user. However, you can configure the User
Segment Content List portlet to display only assets of specific types. For
example, you can configure the User Segment Content List portlet to only display
web content articles and documents.

The Campaign Content Display portlet is similar to the User Segment Content
Display portlet except that instead of displaying an asset based on the user
segments that fit a user, it displays an asset based on the campaigns that fit a
user. However, the Campaign Content Display portlet's display rules are simpler
than those of the User Segment Content Display portlet. You can specify multiple
rules for the Campaign Content Display portlet according to the following
format:

- If the user belongs to this campaign: [select a campaign], then display this
  content: \[specify a specific asset\].

As with the User Segment Content Display portlet, you can specify any number of
*if* clauses when configuring the Campaign Content Display portlet but an
*otherwise* clause always follows the last *if* clause so the Campaign Content
Display portlet knows what to display if the user doesn't match any campaigns.

![Figure 6.x: The rules for configuring the Campaign Content Display portlet to
display content are similar to the rules of the User Segment Content Display
portlet, but simpler.](../../images/campaign-content-display-config.png)

For example, suppose that you've created two campaigns called *World Cup* and
*Stanley Cup*, designed to target users who are interested in the World Cup of
soccer and the Stanley Cup of hockey. You could add a Campaign Content Display
portlet to a page and configure it with the following rules:

- If the user belongs to this campaign: *World Cup*, then display this content:
  *soccer-jersey.jpg*.
- If the user belongs to this campaign: *Stanley Cup*, then display this
  content: *hockey-jersey.jpg*.
- Otherwise, display this content: *generic-sports-jersey.jpg*

Once a Campaign Content Display portlet has been added to a page and been
configured this way, portal users who belong to the user segment targeted by the
World Cup campaign will see a certain image in the portlet. Users who belong to
the user segment targeted by the Stanley cup will see a different image in the
portlet. Users who don't belong to either user segment will see the default
image. Of course, once the campaigns have ended, all users will see the default
image.

The Content Targeting Simulator portlet is useful when creating user segments
and campaigns and when configuring User Segment Content Display and Campaign
Content Display portlets. It has two tabs: *User Segments* and *Campaigns*. The
User Segments tab displays a list of all the user segments matched by the
current user and all the user segments not matched by the current user. The
Content Targeting Simulator is especially useful when added to a page that
already has some configured User Segment Content Display or Campaign Content
Display portlets. In this case, clicking on *simulate* next to one or more of
the *not matched* user segments renders the portal page the way it would appear
to a member of the selected user segments. This can be very useful when
configuring and testing the User Segment Content Display and Campaign Content
Display portlets.

The Campaigns tab of the Content Targeting Simulator simply displays all matched
campaigns and all campaigns that are not matched. It does not offer a *simulate*
option. However, since campaigns target specific user segments, it's easy to
simulate how the portal page would look if a different campaign were matched:
Just click on the *User Segments* tab and select *simulate* next to the user
segment that's targeted by the campaign that you'd like to simulate. If you have
multiple user segments selected for simulation, click the *Clear simulated User
Segments* button to remove the simulation and load the portal page using the
user segments matched by the current user.

