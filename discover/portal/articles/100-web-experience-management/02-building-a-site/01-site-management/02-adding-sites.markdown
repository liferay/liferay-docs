# Adding Sites [](id=adding-Sites)

Sites can be created through the Control Panel by a Liferay administrator. The
Control Panel provides an administrative interface for managing your Liferay
instance. There are four main sections of the Liferay Control Panel: Users,
Sites, Apps, and Configuration. In this section, you'll learn how to use the
Control Panel to manage Sites.  For information about the Apps, Users, and
Configuration sections of the Control Panel, see the 
[Leveraging the Liferay Marketplace](/discover/portal/-/knowledge_base/7-1/leveraging-the-liferay-marketplace),
[User Management](/discover/portal/-/knowledge_base/7-1/managing-users), and
[Using the Control Panel](/discover/portal/-/knowledge_base/7-1/using-the-control-panel) sections,
respectively.

+$$$

**Tip:** If you're signed in as an administrator, you can access all Sites by
navigating to the Site Administration menu from the Control Panel. To manage a
single Site, navigate to the Site by going to the Menu and clicking the *Site
Selector* button (![Compass](../../../../images/icon-compass.png)) from the Sites
dropdown menu and selecting the appropriate Site name. Once finished, the Site
administration options (i.e., Navigation, Content, Members, etc.) for that Site
are available.

$$$

Now, you'll add a Site for the Lunar Resort.

1.  Navigate to the Control Panel and select *Sites* &rarr; *Sites*.

2.  Click the Add icon (![Add Site](../../../../images/icon-add.png)) at the 
    top right of the page.
     
3.  Select a *Blank Site*.

    Any available Site templates appear for you to select. Site templates
    provide a preconfigured set of pages, applications, and content that can be
    used as the basis of a Site's public or private page set. To create a Site
    from scratch, select *Blank Site*. Otherwise, select the name of the Site
    template you want to use. If you opt to create a Site from a Site template,
    you have to choose whether to copy the Site template's pages as your new
    Site's public or private page set. If other Site templates are created, they
    will appear in the Add menu as they become available. 

4.  Name your Site "The Lunar Resort"
 
After you enter the name, you will be prompted to enter additional information 
about the Site and configure certain Site settings.

**Name:** names the Site you wish to create. You also have the option to
translate the name for many different languages. This can be done by selecting
the language flag under the Name field, and inserting the name in the selected
language. Liferay saves the name translation for each language and displays the
translated Site name when that specific language is selected for the instance.
If a name translation is not provided, the default instance language's name is
displayed.

**Description:** describes the Site's intended function. The description can
also be translated to other languages; see the Name description for more
information on translating the Site's description.

**Active:** determines whether a Site is active or inactive. Inactive Sites are
inaccessible but can be activated whenever a Site administrator wishes.

**Membership Type:** can be open, restricted, or private. An open Site appears
in the My Sites app and users can join and leave the Site whenever they want. A
restricted Site is the same except users must request membership. A Site
administrator must then explicitly grant or deny users' requests to join. A
private Site does not appear in the My Sites app and users must be added to it
manually by a Site administrator.

**Allow Manual Membership Management:** determines whether to allow or disallow
users to be manually added or removed from the Site. By default, manual Site
membership management is enabled. This allows administrators to manually assign
users to the Site. It also allows users to join open Sites or request membership
from restricted Sites using the My Sites app. For organization Sites, manual
Site membership management is disabled, by default. This causes organization
members to be automatically assigned membership following the organization's
membership policy. Also, because manual membership management is disabled for
organization Sites, by default, the *Users* section of *Sites* is unavailable.
To activate the *Users* functionality for your organization Site, you'll need to
check *Allow Manual Membership Management* after creating the organization Site
by navigating to its *Site Settings* menu.

+$$$

**Note:** It's possible for Site memberships to be handled automatically by a
membership policy. The membership policy can check various pieces of information
from each user, such as their first names, last names, birthdays, job titles,
organizations, and user groups. Using this information, the Site membership
policy can automatically assign members to the Site. If your Site will implement
a membership policy, your Site administrators can disallow manual membership
management for their Site. When the Allow Manual Membership Management option is
disabled, the *Members* section of Site Administration (Site Memberships and
Site Teams) is hidden, even from administrators.

$$$

**Parent Site:** lets you select a parent Site for the Site that's being
created. Sites can be organized hierarchically. Using hierarchical Sites
provides a simplified way to manage Site memberships and Site content sharing.
For organizations that have attached Sites, the organization hierarchy should
match the Site hierarchy. When you select a parent Site, an additional option
appears: *Limit membership to members of the parent Site*. If this option is
enabled, the Site's membership policy performs a check so that you can only
assign members to the current Site if they're already members of the parent
Site.

2.  Set the *Membership Type* as *Restricted*.

3.  Leave the remain defaults and click *Save*.

When creating a blank Site or organization Site, the Site is not immediately
viewable. This is because Sites without a page are impossible to view.
Therefore, before you can view your Site, you must first create a page for it.
To add a page for your temporarily invisible Site, navigate to the *Navigation*
option from Site Administration. Then add a public page. After adding your
Site's first page, it renders and your Site is viewable. For more information
about adding pages, see the
[Creating and Managing Pages](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages)
section.

You can also categorize your Site template using tags and categories by
selecting the *Categorization* menu from the bottom of the page. To learn more
about using tags and categories in Liferay, see the 
[Organizing Content with Tags and Categories](/discover/portal/-/knowledge_base/7-1/organizing-content-with-tags-and-categories)
section. Lastly, at the top of the page is an additional tab named *Social*.
This tab manages whether users of your Site can mention other users. You'll
learn about mentioning users later in the Social Collaboration sections.

When creating a Site from a Site template, you're asked if you want to copy
the pages from the template as public pages or as private pages. By default, the
Site is linked to the Site template and changes to the Site template propagate
to any Site based on it. A checkbox appears for unlinking the Site template if
the User has permission to do so.

Once the Site has been created, you should configure its settings to fit your
needs. You can learn more about Site Settings in [Configuring Sites](/discover/portal/-/knowledge_base/7-1/configuring-sites).

Next, you'll learn about creating pages.
