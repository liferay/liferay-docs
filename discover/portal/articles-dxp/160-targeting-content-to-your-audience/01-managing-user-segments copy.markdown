# Managing User Segments [](id=managing-user-segments)

The *Audience Targeting* section of the Configuration section of the Site
Administration area of the Menu allows you to manage user segments and
campaigns.

A user segment represents a subset of the total group of portal users (logged in
or not). A user segment is defined by one or more rules that users must match
to belong to that user segment. Once the user segment is created,
only users who visit the applicable site(s) are added to it. To add a new User 
Segment,

1.  Open Site Administration.

2.  Go to *Configuration* &rarr; *Audience Targeting*

3.  Under the *User Segments* tab, click *Add User Segment* (![Add User Segment](../../images-dxp/icon-add.png)).

4.  Enter "Women over 30 who use Safari" in the *Name* field and enter a 
    *Description*.

5.  Open *Behavior* and click and drag *Browser* to the right side.

![Figure 2: After applying the rule, click the rule to expland/collapse it.](../../images-dxp/audience-targeting-rules.png)

6.  Select *Safari* in the *Browser* dropdown.

7.  Open *User Attributes* and drag *Age* and *Gender* under where you set the
    browser.
    
8.  Set the *Age* to older than 30 and younger than 100.

9.  Set the *Gender* to *Female*.

10. Click *Save*.

Each rule has parameters that can be adjusted. Those parameters can be in 
different format. Some rules are also instanceable, meaning you can apply more 
than one of the same type of rule to a user segment. This allows you to create 
scenarios where you need to segment your audience based on actions that might 
occur multiple times (e.g., visiting multiple pages, viewing multiple banners, 
having several roles, etc.).

![Figure 3: Instanceable and non-instanceable rules have different icons.](../../images-dxp/instanceable-icons.png)


Once you've created a user segment, you can open its summary view, which
displays relevant data and configurations.

![Figure 3: Select a pre-existing user segment to view its Summary page.](../../images-dxp/user-segment-summary.png)

To configure permissions for creating, editing, and deleting user segments,

1.  Go to *Users* &rarr; *Roles* section of the Control Panel.

2.  Click on the *Actions* &rarr; *Define Permissions* button corresponding to 
    the role you'd like to configure permissions for, and search for
    *Audience Targeting*.

Both user segments and campaigns are inherited in your site hierarchy.
Therefore, user segments and campaigns defined in the Global scope will be
available to any site. User segments and campaigns created in a site will be
available to all of its child sites.

## User Segment Rules [](id=user-segment-rules)

Custom rules can be created by developers and deployed as OSGi plugins. See the
[Creating New Audience Targeting Rule Types](/develop/tutorials/-/knowledge_base/7-1/creating-new-audience-targeting-rule-types)
tutorial for details.
 
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
[Liferay Audience Targeting Rules](/develop/tutorials/-/knowledge_base/7-1/liferay-audience-targeting-rules)
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
