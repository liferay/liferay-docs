# Using the Audience Targeting Applications [](id=using-the-audience-targeting-applications)

The Audience Targeting app not only adds the Audience Targeting application to
the Site Administration area of the Control Panel, it also includes the
following instanceable applications which can be added to any portal page:

- Campaign Content Display
- User Segment Content Display
- User Segment Content List

All of these applications support Application Display Templates (ADTs) so that
site administrators can customize the look and feel of the application. Any of
Liferay Portal's out-of-the-box Asset Publisher ADTs can actually be re-used for
these Audience Targeting applications.

## User Segment Content Display [](id=user-segment-content-display)

The User Segment Content Display application allows administrators to specify
exactly which content to display to a user based on the user segments that the
user belongs to. You can specify multiple rules according to the following
format:

- If the user \[belongs|does not belong\] to \[any|all\] of the following user
  segments [specify a list of user segments], then display this content:
  \[specify a specific asset\].
 
You can specify any number of *if* clauses when configuring the User Segment
Content Display application's rules. However, an *otherwise* clause always
follows the last *if* clause so that the application knows what to display if
the user doesn't match any user segments. *Don't display anything* is an option
for the *otherwise* clause.

![Figure 1: You can configure the User Segment Content Display application to display content according to rules that you define in the application's configuration window.](../../images-dxp/user-segment-content-display-config.png)

For example, you can add a User Segment Content Display application to a page and
configure the following rules for it:

- If the user *belongs* to *any* of the following user segments: *Tennis fans*,
  then display this content: *tennis_picture.jpg*.
- If the user *belongs* to *any* of the following user segments: 
  *Basketball fans*, then display this content: *basketball_picture.jpg*.
- If the user *belongs* to *any* of the following user segments: *Soccer*, then
  display this content: *soccer_picture.jpg*.
- Otherwise, *Don't display anything*.

Once a User Segment Content Display application has been added to a page and
been configured this way, users (even guest users) will see a different image
based on the user segment to which they belong. The application won't even be
visible to a user if the user doesn't belong to any of the configured user
segments.

![Figure 2: In the User Segment Content Display application, site administrators can preview the various assets that have been configured to be displayed to different user segments.](../../images-dxp/audience-targeting-uscd.png)

The User Segment Content Display application allows site administrators to
preview the various assets that have been configured to be displayed to
different user segments.

+$$$

**Note:** You can also provide similar functionality from the Asset Publisher
application. You're able to configure a User Segments Filter that will only
display content for particular user segments. To learn more about this, see the
[Categorizing Pages and Content for User Segments](/develop/tutorials/-/knowledge_base/7-0/categorizing-pages-and-content-for-user-segments)
tutorial.

$$$

## User Segment Content List [](id=user-segment-content-list)

The User Segment Content List application displays content that has been
categorized for the user segments that match the user segments to which the
current user belongs. For example, suppose that your website has several assets
categorized under the following user segments: *Tennis fans*, *Soccer fans*, and
*Basketball fans*. When a user that belongs to the Tennis fans user segment
views this application, the application displays a list of assets categorized
for the Tennis fans user segment. If the user belongs to multiple user segments,
then a list of articles that have been categorized for any of the matching user
segments will be shown.

![Figure 3: The User Segment Content List application displays content that matches the user segments which fit the current user. You can configure the User Segment Content List application to display assets of any kind or only specific kinds of assets.](../../images-dxp/user-segment-content-list.png)

By default, the User Segment Content List application is configured to display
assets of any type that have been categorized for any user segment that matches
the current user. However, you can configure the User Segment Content List
application to display only assets of specific types. For example, you can
configure the User Segment Content List application to only display web content
articles and documents. The User Segment Content List application also provides
filtering for categories, tags, scopes, etc., offering a wide variety of
filtering options so you can display the specific content you want.

## Campaign Content Display [](id=campaign-content-display)

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

![Figure 4: The rules for configuring the Campaign Content Display application to display content are similar to the rules of the User Segment Content Display application, but simpler.](../../images-dxp/campaign-content-display-config.png)

For example, suppose that you've created two campaigns called *World Cup* and
*Stanley Cup*, designed to target users who are interested in the World Cup of
soccer and the Stanley Cup of hockey. You could add a Campaign Content Display
application to a page and configure it with the following rules:

- If the user belongs to this campaign: *World Cup*, then display this content:
  *soccer-ball.jpg*.
- If the user belongs to this campaign: *Stanley Cup*, then display this
  content: *hockey-.png*.
- Otherwise, display this content: *generic-sports-jersey.jpg*

Once a Campaign Content Display application has been added to a page and been
configured this way, portal users who match the World Cup campaign will see a
certain image in the application. Users who match the Stanley cup campaign will
see a different image in the application. Users who don't match either campaign
will see the default image. Of course, once a campaign has ended, no users will
match that campaign. Once all campaigns have ended, all users will see the
default image.

+$$$

**Note:** When Audience Targeting is enabled with a large number of rules,
tracking actions, and reports, you may need to adjust some JVM parameters of
your server for optimal performance.

<!-- Visit the
[Performance Tuning](/discover/deployment/-/knowledge_base/6-2/performance-tuning)
section for details.

Update link above, when available. -Cody -->

$$$

The Campaign Content Display application, like the User Segment Content Display
application, allows site administrators to preview the different assets that
will be displayed for different campaigns.

Next you'll learn how to simulate your user segments and campaigns.
