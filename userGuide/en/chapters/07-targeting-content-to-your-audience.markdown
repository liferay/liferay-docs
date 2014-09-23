## Targeting Content To Your Audience

Liferay's Audience Targeting App allows you to segment your audience,
target specific content to different user segments, create campaigns for them
and measure quickly the effectivity. 

User Segments allow you to configure your website to display different assets
to different users. Campaigns will allow you to reach several user segments in
a period of time to display different content to them and measure their 
interactions with this content.

Maybe you'd like to display advertisements to users in your website, but you'd
like to display one advertisement to one user segment and another advertisement
to another user segment, and maybe this may change dynamically every week based
on your marketing goals. Or maybe you have created sets of content in your 
website that may be interesting to different user segments and you'd like
to display a set of news articles to users based on all the user segments they
belong to. 
 
Liferay's Audience Targeting app, available from [Liferay Marketplace](http://www.liferay.com/marketplace),
allows you to create multiple user segments which are defined by multiple rules
based on session attributes, profile attributes, behavior and information from
social networks (more rules and rule types can easily be added with very little
coding efforts).
The Audience Targeting App also allows you to create campaigns that target a 
specific user segment. Campaigns last for a fixed period of time and have a 
priority. If you are running several campaigns in your website at the same time, 
the priority field will help you have a smooth transition between them.
Campaigns allow you to configure different assets to be displayed at different
periods of time to the targeted user segment.
 
The Audience Targeting App adds an *Audience Targeting* section to the
Configuration section of the Site Administration area of the Control Panel and a
Simulator UI to the dockbar of your site. The following three applications are
also included with the Audience Targeting app:

- User Segment Content Display
- User Segment Content List
- Campaign Content Display

You can add these applications to any portal page. In the next sections, we'll
explain how to use the *Audience Targeting* section of the Configuration section
of the Site Administration area of the Control Panel to manage user segments and
campaigns. Then we'll explain how to use each of Audience Targeting 
applications.

### Managing User Segments

The *Audience Targeting* section of the Configuration section of the Site
Administration area of the Control Panel allows you to manage user segments and
campaigns.

![Figure 6.x: Click on *Site Administration* &rarr; *Configuration* &rarr; *Audience Targeting* to manage user segments and campaigns.](../../images/07-audience-targeting-admin.png)

A user segment represents a subset of the total group of portal users (logged in
or not). A User Segment is defined by one or more rules that users will have to 
macth in order to belong to that User Segment. 
Click on *Site Administration* &rarr; *Configuration* &rarr; *Audience
Targeting* &rarr; *New User Segment* to add a new user segment. All the rules
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

![Figure 6.x: Drag a rule to the right to create a user segment](../../images/07-audience-targeting-rules.png)


Permissions for creation, edition and deletion of user segments can be found in
the Roles Administration UI searching for "Audience Targeting Resources".

Both User Segments and Campaign are inherited in your Site hierarchy. Therefore,
User Segments and Campaigns defined in the Global Scope will be available to any
site and User Segments and Campaigns created in a site will be available to all
its child sites.

#### User Segment Rules

Custom rules can be created by developers and deployed as OSGi plugins. See the
[Creating Custom Audience Targeting Rules](www.liferay.com) tutorial for
details.
 
 These are some of the rules that come by default with the app:
 - User Attributes
  - Age (from the User Profile)
  - Gender (from the User Profile)
  - Role (Regular Role, Organization Role or Site Role)
  - Memberhip (Site Member, Organization Member, User Group Member)
 - Social
  - Like of a page in facebook
  - Number of friends in facebook
  - City, Age, Gender, Education... from your facebook profile
 - Session Attributes
  - Location (obtained from the IP)
  - Browser, Device, Operating System
  - Time
 - Behavior
  - Visited Content or Page
  - Score Points Rule. This rule will give 1 point to users every time they see
  a page or content categorized for this user segment. Once they exceed the 
  threshold configured they will match this rule. For example, imagine your 
  website is about sports and you have pages and content about basketball, 
  tennis and soccer. You would like to segment your audience into these three 
  user segments in order to present them more relevant news in the front page: 
  Basketball fans, Tennis fans and Soccer fans. After creating these three user
  segments using the Score Points rule with a threshold of 20 each (for example)
  , we should categorize all the content with the user segment that will be most
  interested on it. Now, every time a user (even a guest user) visits a page or
  a piece of content categorized for a user segment, she will start getting 
  points for it and at some point will belong to that user segment. After a 
  user has visited more than 20 pages or pieces of content related to basketball
  she will belong to the basketball fans user segment and now we can use that 
  information to show her more relevant information to her in our website using
  the User Segment Content Display application.
  

#### Categorizing Content for User Segments

Every time a new user segment is created, the user segment can be used to 
categorize content or pages. If you go to the Categorization section of pages, 
web content, documents or any other asset you will find a button called "User 
Segments" that will allow you assign one or more user segments to the content.
This categorization has mainly two purposes:
- Showing dynamic lists of content in the User Segment Content List
- Assigning points to users using the Score Points Rule

![Figure 6.x: Pages and content can be categorized for User Segmetns](../../images/07-audience-targeting-categorization.png)


#### User Segment Reports

When managing user segments you can click *Actions* &rarr; *Reports* next to a
user segment to see the list of reports available for each user segment. Reports
display a summary of interesting information related to each user segment. For
example, the "Content Views" report shows the asset most viewed by users that
belong to each user segment.

![Figure 6.x: the "Content Views" report shows the asset most viewed by users 
              that belong to a user segment](../../images/07-audience-targeting-user-segment-report.png)

More reports can be created by developers and deployed as OSGI plugins.  
See the [Creating Custom Audience Targeting Reports](www.liferay.com) tutorial
for details.
Reports are generated daily by default, but they can be run at any time clicking
on the "update report" button in the top right corner.


### Managing Campaigns

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

![Figure 6.x: Campaigns Management](../../images/07-audience-targeting-user-campaigns.png)

For example, suppose you wanted the ability to display certain content (for
example, advertisements about your new Android app) to female baseball
fans during the months leading up to the World Series. To achieve this, you 
could use the Gender rule (configured to female), the Device Rule (configured
for Android devices) and the Score Points rule to define a user segment 
called *Female Baseball Fans*. The Score points rule assigns 1 point to a user 
each time they visit a page or view an asset categorized under the name of the
user segment: *Female Baseball Fans*.
When a user accumulates a certain number of points (specified by the value of
the Score Points rule's *Score Points Threshold* attribute), they will match 
this rule. After creating this user segment, you would create a new
campaign targeting this segment, select start and end dates, choose a priority,
choose *Active*, and then click *Save*. To actually present content to the users
belonging to the *Female Baseball Fans* user segment, you need to use the
Campaign Content Display application.

Permissions for creation, edition and deletion of campaigns can be found in
the Roles Administration UI searching for "Audience Targeting Resources".

#### Campaigns Priority

The priority of your campaigns will be a very useful tool if you run more than
one campaign at the same time in your website.

The Campaign Content Display application can be configured to display content based
on the campaign your users match. When a user matches more than one campaign, 
then the one with more priority will preceed. 
 
If you have several Campaign Content Display applications around your website 
configured to display different content per Campaign, changing the priority of
one campaign will automatically affect all the Campaign Content Display 
applications, and the same will happen if a Campaign is deactivated or if the date
range is exceeded.


#### Tracking Actions

One of the most interesting features of Campaigns is that they allow you to
measure the effectivity of a Campaign so that your marketing team can get real
feedback from users.

When creating a campaign you can define those actions from users in your website
that you want track and the App will show you reports of how those actions are 
executed. For example, imagine you want to run a Campaign for an event that your
company is hosting next mont. For this event, let's imagine you have created a
main page of the event which contains a youtube video and a banner which says
"Register now", then you have blog entry about the event displayed in several 
places of your website and a "register" page which contains the form to pay for
the event. In this Campaing, your goal is to get as
many people to register as possible, but you will probably be interested in 
tracking all this information to see if there is something not working as your
team expected:

 - Visits to the main page of the event
 - Clicks to view the video
 - Watch the video until the end
 - Click on the Banner "Register now"
 - See the blog entry about the event
 - See the Register Form
 - Start to fill the Register Form
 - Finish the registration
 
![Figure 6.x: Drag and drop tracking actions to the right to configure a Campaign](../../images/07-audience-targeting-tracking-actions.png)

 
You could drag and drop the "tracking actions" from the palette to track all the
actions mentioned above.
More types of "tracking actions" can be created by developers and deployed as 
OSGI plugins. See the 
[Creating Custom Audience Targeting Tracking Actions](www.liferay.com) tutorial
for details.

Those tracking actions use an analytics Engine called "Audience Targeting 
Analytics" that can be configured per Site (in Site Administration &rarr; Site
Settings) and per Portal Instance (in Control Panel &rarr; Portal 
Configuration). Tracking all the actions from all your users (even guest users)
can be a very heavy load for your server and more traffic for your users, 
therefore it is recommended to disable the tracking of those actions that you 
don't need to track.

#### Campaign Reports

There are also reports available for Campaigns. You can click 
*Actions* &rarr; *Reports* next to a campaign to see the list of reports 
available. More reports can be created by developers and deployed as OSGI 
plugins. See the 
[Creating Custom Audience Targeting Reports](www.liferay.com) tutorial for 
details. 

The "Content Views" report displays the number of times that different assets 
have been viewed by users in the context of the current campaign (using the
Campaign Content Display). For example, if you configured five "Campaign Content
Display" around your website to display content for certain campaign, you would
see in this report how many times was that content shown to different users.

The "Tracking Actions" report shows the number of times that the actions for the
current campaign have been triggered by users. Following the previous example,
it would show you how many users visited the event page, how many watched the 
video, clicked on the banner, started filling the form...etc and that would help
you measure the effectivity of your campaign and evaluate if the users are 
following the engagement path you had prepared.


### Using the Audience Targeting Applications

The Audience Targeting App not only adds the Audience Targeting application to
the Site Administration area of the Control Panel, it also includes the 
following instanceable applications which can be added to any portal page:

- User Segment Content Display
- User Segment Content List
- Campaign Content Display

All these applications support Aplication Display Templates (ADT) in order to
allow Site Administrators to customize the look and feel of the application.
They can actually reuse any of the existing Templates for Asset Publisher.


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
on the user segment they belong to or won't even see the application if they don't
belong to any of the user segments configured.

Site administrators will be able to see a preview of the different assets that 
will be shown to different user segments right in the application.

![Figure 6.x: Preview of the different rules configured](../../images/07-audience-targeting-uscd.png)


#### User Segment Content List

The User Segment Content List application displays content that has been categorized
for the user segments that matches the user segments to which the current user 
belongs.

For example, if my website has several web content articles categorized under
"tennis fans", "soccer fans" and "basketball fans", when a user that belongs to
the tennis fans user segment see this application, a list of articles categorized 
for this user segment will be shown. If the user belongs to more than one user
segment, then content categorized for all those user segments will be shown.

![Figure 6.x: The User Segment Content List application displays content that matches the user segments which fit the current user. You can configure the User Segment Content List application to display assets of any kind or only specific kinds of assets.](../../images/user-segment-content-list.png)

By default, the User Segment Content List application is configured to display
assets of any type that have been categorized with the category of a user
segment that matches the current user. However, you can configure the User
Segment Content List application to display only assets of specific types. For
example, you can configure the User Segment Content List application to only display
web content articles and documents.

#### Campaign Content Display

The Campaign Content Display application is similar to the User Segment Content
Display application except that instead of displaying an asset based on the user
segments that a user belongs to, it displays an asset based on the campaigns
that a user matches. However, the Campaign Content Display application's display 
rules are simpler than those of the User Segment Content Display application. You 
can specify multiple rules for the Campaign Content Display application according to
the following format:

- If the user belongs to this campaign: [select a campaign], then display this
  content: \[specify a specific asset\].

As with the User Segment Content Display application, you can specify any number of
*if* clauses when configuring the Campaign Content Display application but an
*otherwise* clause always follows the last *if* clause so the Campaign Content
Display application knows what to display if the user doesn't match any campaigns.
A notable difference with the User Segment Content Display application is that these
rules can not be ordered manually. The order will be based on the priority of
the campaign.

![Figure 6.x: The rules for configuring the Campaign Content Display application to
display content are similar to the rules of the User Segment Content Display
application, but simpler.](../../images/campaign-content-display-config.png)

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
certain image in the application. Users who match the Stanley cup campaign will see
a different image in the application. Users who don't match either campaign will see
the default image. Of course, once a campaigns has ended, no user will match 
that campaign and once all campaign have ended, all users will see the default
image.

Site administrators will be able to see a preview of the different assets that 
will be shown for different campaigns right in the application.

### Simulating User Segments and Campaigns

The Audience Targeting Simulator is useful for testing how different users 
would see your website based on the user segments they may belong to or the 
campaigns they may match.
 
A new entry is added to the dockbar by the App that will open a panel which has
two tabs: *User Segments* and *Campaigns*. This entry will be visible by 
default to Site Administrator and any user with permission to VIEW the 
"Audience Targeting Simulator", which can be granted through the permission UI
of the Roles Administration.
The User Segments tab displays a list of all the user segments matched by the
current user and all the user segments not matched by the current user. Any 
matched user segment can be unselected and any not matched user segment can be 
selected in order to simulate what other users would see. Cliking on the 
"Simulate" button will show us our website simulating the user segments selected
and this simulation will be kept until we click "Stop simulation" which will 
allow us to navigate around our website as if we belong to other user segments 
or campaigns.

![Figure 6.x: Audience Targeting Simulator](../../images/07-audience-targeting-simulator.png)

The Campaigns tab of the Content Targeting Simulator also displays all matched
campaigns and all campaigns that are not matched. It does not offer a *simulate*
option yet. However, since campaigns target specific user segments, it's easy to
simulate how the portal page would look if a different campaign were matched.


