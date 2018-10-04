# Using the Audience Targeting Widgets [](id=using-the-audience-targeting-applications)

Audience Targeting includes widgets that can be added to any portal page:

- Campaign Content Display
- User Segment Content Display
- User Segment Content List

All of these widgets support Application Display Templates (ADTs), and any of
@product@'s out-of-the-box Asset Publisher ADTs can be re-used for these
Audience Targeting widgets.

## User Segment Content Display [](id=user-segment-content-display)

User Segment Content Display displays content to users based on their user
segment membership. Administrators can specify multiple rules according to the
following format:

- If the user `[belongs|does not belong]` to `[any|all]` of the following user
  segments `[specify a list of user segments]`, then display this content:
  `[specify a specific asset]`.
 
You can specify any number of *if* clauses when configuring the User Segment
Content Display widget's rules. However, an *otherwise* clause always
follows the last *if* clause so the widget knows what to display if the user
doesn't match any user segments. *Don't display anything* is an option for the
*otherwise* clause.

![Figure 1: You can configure the User Segment Content Display widget to display content according to rules that you define in the widget's configuration window.](../../images-dxp/user-segment-content-display-config.png)

For example, you can add a User Segment Content Display widget to a page 
and configure the following rules for it:

- If the user *belongs* to *any* of the following user segments: *Tennis fans*,
  display this content: *tennis_picture.jpg*.
- If the user *belongs* to *any* of the following user segments: 
  *Basketball fans*, display this content: *basketball_picture.jpg*.
- If the user *belongs* to *any* of the following user segments: *Soccer*, 
  display this content: *soccer_picture.jpg*.
- Otherwise, *Don't display anything*.

Once the widget has been configured this way, users (even guest users) see
a different image based on their user segment. The widget isn't visible to users
who don't belong to any of the configured user segments.

![Figure 2: In the User Segment Content Display widget, site administrators can preview the various assets that have been configured to be displayed to different user segments.](../../images-dxp/audience-targeting-uscd.png)

Administrators can preview the various assets that have been configured for 
different user segments.

## Campaign Content Display [](id=campaign-content-display)

Campaign Content Display is similar to the User Segment Content Display except
that instead of displaying assets based on user segment membership, it displays
an asset based on the campaigns that a user matches. However, the Campaign
Content Display widget's display rules are simpler than those of the User
Segment Content Display widget. You can specify multiple rules for the Campaign
Content Display widget according to the following format:

- If the user belongs to this campaign: `[select a campaign]`, then display this
  content: `[specify a specific asset]`.

As with the User Segment Content Display, you can specify any number of *if* 
clauses when configuring the Campaign Content Display widget. An 
*otherwise* clause always follows the last *if*. These rules cannot be ordered 
manually as they can with the User Segment Content Display widget. The 
order of the rules is based on the priority of the campaigns.

![Figure 3: The rules for configuring the Campaign Content Display widget to display content are similar to the rules of the User Segment Content Display widget, but simpler.](../../images-dxp/campaign-content-display-config.png)

For example, if you created two campaigns called *World Cup* and *Stanley Cup*
that targets users interested in the World Cup of soccer and the Stanley Cup of
hockey, you could add a Campaign Content Display widget to a page and configure
it with the following rules:

- If the user belongs to this campaign: *World Cup*, display this content:
  *soccer-ball.jpg*.
- If the user belongs to this campaign: *Stanley Cup*, display this
  content: *hockey-.png*.
- Otherwise, display this content: *generic-sports-jersey.jpg*

Once a Campaign Content Display has been added to a page and been configured
this way, users matching the World Cup campaign see the soccer image. Users
matching the Stanley cup campaign see the hockey image. Users who don't match
either see the default image. Of course, once a campaign has ended, no users
match that campaign. Once all campaigns have ended, all users see the default
image.

+$$$

**Note:** When Audience Targeting is enabled with a large number of rules,
tracking actions, and reports, you may need to adjust some JVM parameters of
your server for optimal performance.

$$$

The Campaign Content Display widget, like the User Segment Content Display
widget, allows Site administrators to preview the different assets that are
displayed for different campaigns.

## Asset Publisher [](id=asset-publisher)

For @product-ver@ the Asset Publisher replaces the functionality of the User 
Segment Content List. When Audience Targeting is installed, the Asset Publisher 
gains an additional configuration option: *User Segments Filter*. When *User 
Segments Filter* is activated, the only assets displayed are those that match
the filters configured in the widget and are categorized under any of the user
segments for the current user. Assets not categorized with User Segments don't
appear in any Asset Publisher with the *User Segments Filter* enabled. The Asset
Publisher can be configured to display these assets in a list or to display the
full content.

Next you'll learn how to simulate your user segments and campaigns.
