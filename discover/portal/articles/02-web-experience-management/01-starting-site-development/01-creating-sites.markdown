# Creating Sites [](id=creating-sites)

A site contains a set of pages that can be used to publish content or
applications. By default, @product@ starts with a single site that has a
single page. You can build any website you wish out of this, complete with
multi-nested page hierarchies. The site can stand independently or can be
associated with an organization to serve as the website for that organization.
Liferay's framework provides all the site-building tools you need to manage a
successful site.

## Understanding Site Management [](id=understanding-site-management)

Whether you're building a large corporate website or a small site designed to
facilitate collaboration among team members, supporting different kinds of
collaboration and social scenarios is a must. Liferay's sites provide three
membership types:

**Open:** Users can become members of the site at any time.

**Restricted:** Users can request site membership but site administrators must
approve requests in order for users to become members.

**Private:** Users are not allowed to join the site or request site membership.
Site administrators can still manually select users and assign them as site
members.

In addition to these memberships, when a site is associated with an
organization, all the users of that organization are automatically considered
members of the site.

You can view all the available open and restricted sites by adding the My Sites
application to a page and accessing the *Available Sites* tab. You can request
access to any of the sites you're not already a member of by
selecting the site's *Options* button
(![Options](../../../images/icon-actions.png)) and clicking *Join*.

Members of a site can be given additional privileges within the site by using
@product@'s permission settings. It is also possible to assign different roles
within the site to different members. This can be done through *site roles*,
which are defined equally for all sites or *teams* which are unique for each
site. These concepts will be discussed later in the chapter.

@product@ separates site-scoped information from the Control Panel by placing it
in the Sites menu. From this menu, you can select the specific site to work on.
The Site Administration panel is available for your site, which includes Pages,
Content, Members, Configuration, and Publishing.

![Figure 1: Your site's content resides in the Site Administration menu.](../../../images/web-content-site-content.png)

For details about @product@'s social collaboration suite, see the
[Social Collaboration](/discover/portal/-/knowledge_base/6-2/collaboration-suite)
section.

<!-- TODO: Replace link above. -Cody -->

Sites can also be organized hierarchically, just like organizations. The
difference between sites and organizations, of course, is that sites are used to
organize pages, content, application data, and users (via site memberships)
whereas organizations are only used to group users. Content sharing is available
for sites within the same hierarchy. For instance, if a parent site has a
document called *Lunar Goals and Objectives* and would like for all its subsites
to have a copy, the parent site's administrator can enable content sharing to
automatically share the document with its subsites, instead of having to send
each site the document individually. Also, content sharing privileges can be set
to let every site administrator share content across sites they manage. Some
examples of content you can share across site include web content structures and
templates, categories, application display templates, etc.

Please refer to the [Sites Admin Portlet](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Sites%20Admin%20Portlet)
section of Liferay's `portal.properties` file for a list of relevant
configurable properties. For example, the
`sites.content.sharing.with.children.enabled` property allows you to disable
content sharing between sites and subsites, disable it by default while allowing
site administrators to enable it per site, or to enable it by default while
allowing administrators to disable it per site.

The Sites Directory application is a configurable app that can allow users to
view a hierarchy of sites and subsites. It enables users to navigate to any of
the displayed sites. To use this app to display site hierarchies, add it to
a page, open its Configuration window, and under Display Style, select *List
Hierarchy*. The My Sites Directory application is very similar to the Sites
Directory application, except that it lists only the sites a user belongs to.

The Site Map application is another configurable app that's intended to help
users navigate among pages within a site. When configuring this app, a site
administrator can select a root page and a display depth. Just as sites can be
organized hierarchically, so can the pages within a site. The display depth of
the Site Map application determines how many levels of nested pages to display.

![Figure 2: The Sites Directory application lets users navigate between sites organized hierarchically. The Site Map application lets users navigate among pages of a site organized hierarchically.](../../../images/site-directory-site-map.png)

Another useful administrative application is the Site Members application. This
enables administrators to survey all the users, organizations, and user groups
that reside in the site. Similarly, Liferay provides the Portal Directory
application, which functions the same as the Site Members app, but globally
scoped for all sites in the instance.

<!-- TODO: Portal Directoy app still has "portal" name, although most other
entities in Liferay have changes to "instance". Check back with this name before
release of @product-ver@. -Cody -->

@product@'s sites have two categories of pages called page sets. There are two
kinds of page sets: public pages and private pages. A site can have only public
pages, only private pages, or both. Private pages can only be accessed by site
members. Public pages can be accessed by anyone, including users who haven't
logged in. It's possible to restrict access to pages at the page set level or at
the level of individual pages through the permissions system. Public pages and
private pages have different URLs and can have different content, applications,
themes, and layouts.

Building a corporate intranet is a typical use case for Liferay sites. A
corporate intranet could have sites for all the organizations in the company:
Sales, Marketing, Information Technology, Human Resources and so on. But what
about the corporate health and fitness center? That's something everybody in the
company, regardless of organization, may want to join. This makes it a good
candidate for an open and independent site. Similarly, the home page for a
corporate intranet should probably be placed in an open independent site so any
member of the instance can access it.

+$$$

**Tip:** Prior to Liferay Portal 6.1, there were two ways of creating sites:
organizations and communities. This has been simplified to provide more ease of
use and allow for more flexibility. The main role of organizations is still to
organize the users of the instance in a hierarchy but they can also have
associated sites. Communities can still be created through independent sites but
the new name reflects the fact that sites can be used for many different
purposes besides communities.

$$$

For other kinds of web sites, you may want to use independent sites to bring
users together who share a common interest. If you were building a photo sharing
website, you might have independent sites based on the types of photos people
want to share. For example, those who enjoy taking pictures of landscapes could
join a Landscapes site and those who enjoy taking pictures of sunsets could join
a Sunsets site.

@product@ always provides one default site, which is also known as the main site
of the instance. This site does not have its own name but rather takes the name
of the instance. By default the instance name is *Liferay* but this value can be
changed through the simple configuration of the setup wizard. The instance name
can also be changed at any time through the Control Panel within *Configuration
&rarr; *Instance Settings*.

## Adding Sites [](id=adding-sites)

Sites can be created through the Control Panel by a Liferay administrator.
Liferay's Control Panel provides an administrative interface for managing your
Liferay instance. There are four main sections of the Liferay Control Panel:
Users, Sites, Apps, and Configuration. In this section, you'll learn how to use
the Control Panel to manage sites. In a later section, you'll learn about using
the Control Panel to manage site templates. Site templates allow Liferay
administrators to create multiple sites with the same default set of pages and
content. For information about the Apps, Users, and Configuration sections of
the Control Panel, see the
[Leveraging the Liferay Marketplace](/discover/portal/-/knowledge_base/6-2/leveraging-the-liferay-marketplace),
[User Management](/discover/portal/-/knowledge_base/7-0/user-management), and
[Using the Control Panel](/discover/portal/-/knowledge_base/6-2/using-the-control-panel)
sections, respectively.

<!-- TODO: Replace links above, when available -Cody -->

+$$$

**Tip:** If you're signed in as an administrator, you can access all sites by
navigating to the Site Administration menu from the Control Panel. To manage a
single site, navigate to the site by going to the Menu and clicking the *Site
Selector* button (![Compass](../../../images/icon-compass.png)) from the sites
dropdown menu and selecting the appropriate site name you'd like to manage. Once
finished, the site administration options (i.e., Navigation, Content, Members,
etc.) for that site are available.

$$$

To add a site for the Lunar Resort instance, navigate to the Control Panel and
select *Sites* &rarr; *Sites*. Then click the Add icon
(![Add Site](../../../images/icon-add.png)) at the bottom right of the page. If
there is at least one site template available, a dropdown menu appears. Site
templates provide a preconfigured set of pages, applications, and content that
can be used as the basis of a site's public or private page set. To create a
site from scratch, select *Blank Site*. Otherwise, select the name of the site
template you'd like to use. If you opt to create a site from a site template,
you have to choose whether to copy the site template's pages as your new site's
public or private page set. If other site templates are created, they will
appear in the Add menu as they become available. The following figure shows the
form that needs to be filled when creating a *Blank Site*.

![Figure 3: The New Site window aids in your new site development.](../../../images/add-site-screen.png)

**Name:** names the site you wish to create. You also have the option to
translate the name for many different languages. This can be done by selecting
the language flag under the Name field, and inserting the name in the selected
language. Liferay saves the name translation for each language and displays the
translated site name when that specific language is selected for the instance.
If a name translation is not provided, the default instance language's name is
displayed.

**Description:** describes the site's intended function. The description can
also be translated to other languages; see the Name description for more
information on translating the site's description.

**Active:** determines whether a site is active or inactive. Inactive sites are
inaccessible but can be activated whenever a site administrator wishes.

**Membership Type:** can be open, restricted, or private. An open site appears
in the My Sites app and users can join and leave the site whenever they want. A
restricted site is the same except users must request membership. A site
administrator must then explicitly grant or deny users' requests to join. A
private site does not appear in the My Sites app and users must be added to it
manually by a site administrator.

**Allow Manual Membership Management:** determines whether to allow or disallow
users to be manually added or removed from the site. By default, manual site
membership management is enabled. This allows administrators to manually assign
users to the site. It also allows users to join open sites or request membership
from restricted sites using the My Sites app. For organization sites, manual
site membership management is disabled, by default. This causes organization
members to be automatically assigned membership following the organization's
membership policy. Also, because manual membership management is disabled for
organization sites, by default, the *Users* section of *Sites* is unavailable.
To activate the *Users* functionality for your organization site, you'll need to
check *Allow Manual Membership Management* after creating the organization site
by navigating to its *Site Settings* menu.

+$$$

**Note:** It's possible for site memberships to be handled automatically by a
membership policy. The membership policy can check various pieces of information
from each user, such as their first names, last names, birthdays, job titles,
organizations, and user groups. Using this information, the site membership
policy can automatically assign members to the site. If your site will implement
a membership policy, your site administrators can disallow manual membership
management for their site. When the Allow Manual Membership Management option is
disabled, the *Members* section of Site Administration (Site Memberships and
Site Teams) is hidden, even from administrators.

$$$

**Parent Site:** lets you select a parent site for the site that's being
created. Sites can be organized hierarchically. Using hierarchical sites
provides a simplified way to manage site memberships and site content sharing.
For organizations that have attached sites, the organization hierarchy should
match the site hierarchy. When you select a parent site, an additional option
appears: *Limit membership to members of the parent site*. If this option is
enabled, the site's membership policy performs a check so that you can only
assign members to the current site if they're already members of the parent
site.

When creating a blank site or organization site, the site is not immediately
viewable. This is because sites without a
page are impossible to view. Therefore, before you can view your site, you must
first create a page for it. To add a page for your temporarily invisible site,
navigate to the *Navigation* option from Site Administration. Then add a public
page. After adding your site's first page, it renders and your site is viewable.
For more information about adding pages, see the
[Creating and Managing Pages](/discover/portal/-/knowledge_base/7-0/creating-and-managing-pages)
section.

You also have the option to categorize your site template using tags and
categories by selecting the *Categorization* menu from the bottom of the page.
To learn more about using tags and categories in Liferay, see the 
[Organizing Content with Tags and Categories](/discover/portal/-/knowledge_base/7-0/organizing-content-with-tags-and-categories)
section. Lastly, you'll notice at the top of the page an additional tab named
*Social*. This tab lets you manage whether users of your site can mention other
users. You'll learn about mentioning users later in the Social Collaboration
sections.

When creating a site from a site template, the initial form provides a new
option that lets you decide if you want to copy the pages from the template as
public pages or as private pages. By default, the site is linked to the site
template and changes to the site template propagate to any site based on it. A
checkbox appears that lets users unlink the site template if the user has
permission to do so.

Once the site has been created, you'll want to configure its settings to fit
your needs. A site's settings are broken into four categories: General, Social,
Languages, and Advanced. You'll learn more about your site's settings in the
next section.

## Configuring Site Settings [](id=configuring-site-settings)

You can access Site Settings by navigating to the Site Administation dropdown
menu and selecting *Configuration* &rarr; *Site Settings*.

![Figure 4: The Site Settings window offers a plethora of options for your site.](../../../images/site-settings-overview.png)

You'll find options to organize site content, manage site users, and improve
your site's overall intelligence. You'll explore the Site Settings categories
available, which are available from the *General*, *Social*, *Languages*, and
*Advanced* tabs, and simulate configuring those settings for your sample Lunar
Resort site.

### Organizing Site Content [](id=organizing-site-content)

Organizing your site's content drastically improves the usability of your site
for users. The Site Settings menu offers some configuration options that aid in
your organizational goals.

#### Categorization [](id=categorization)

You can explore ways to tag and categorize your site by clicking the
*Categorization* tab under General. These tools help administrators organize the
site and allows for users to easily find your site and its content through
search and navigation. To take full advantage of tags and categories, you'll
need to add pages with content to your site. For more information on using tags
and categories, visit the
[Organizing Content with Tags and Categories](discover/portal/-/knowledge_base/7-0/organizing-content-with-tags-and-categories)
section.

#### Site Template [](id=site-template)

The last tab listed under the Basic Information category is *Site Template*. If
you created your Lunar Resort site as a Blank Site, this option is not available
in Site Settings. If you did, however, create your site using a site template,
this section displays information about the link between the site template and
the site. Specifically, you can see which site template was used and whether or
not it allows modifications to the pages inherited from it by site
administrators. To learn more about site templates and how to create your own,
see the [Building Sites from Templates](discover/portal/-/knowledge_base/7-0/building-sites-from-templates)
section.

#### Content Sharing [](id=content-sharing)

If you select the *Content Sharing* tab from the Advanced tab, you can
configure whether sub-sites can display content from this site. Administrators
of this site's sub-sites can use all structures, templates, categories,
application display templates, etc. from this parent site. Even if you
initially allowed content sharing between the parent site and its sub-sites,
you're able to disable this option and immediately revoke content sharing from
all sub-sites.

#### Recycle Bin [](id=recycle-bin)

The *Recycle Bin* option under the Advanced tab provides the option to
enable/disable the Recycle Bin for your site. You can also regulate the age (in
minutes) for which content is able to be stored in the Recycle Bin until it is
permanently deleted. For a full explanation of the Recycle Bin, see the
[Restoring Deleted Assets](/discover/portal/-/knowledge_base/7-0/restoring-deleted-assets)
section.

#### Custom Fields [](id=custom-fields)

*Custom Fields* lets you edit the custom fields you already have configured
for the Site resource. If you don't have any custom fields configured for the
Site resource, you can navigate to the Control Panel &rarr; *Custom Fields*
located under the *Configuration* tab. The Custom Fields tab does not display in
Site Settings unless you have existing custom fields. For more information on
Custom Fields, see the
[Custom Fields](https://dev.liferay.com/discover/portal/-/knowledge_base/6-2/custom-fields)
section.

<!-- TODO: replace link above. -Cody -->

#### Documents and Media [](id=documents-and-media)

The last option that relates to organizing your site's content is *Documents and
Media*, which is found under General. This lets you enable/disable Directory
Indexing, which allows site administrators to browse your site's documents and
media files and folders. For example, a site administrator of a site called
*Lunar Resort* can browse documents at
http://localhost:8080/documents/lunar-resort if this option is enabled.

Now that you can organize your site's content using Site Settings, move on to
the next section to learn how to manage site users.

### Managing Site Users [](id=managing-site-users)

An always important job for site administrators is managing site users. There
are configuration options in Site Settings that allow for easier user
management.

#### Details [](id=details)

*Details* is the leading option under the General tab, which provides
the same menu you filled out when first creating your Lunar Resort site. This
allows an administrator to change the description and membership type of a site.
The membership type can be set as open, restricted, or private based on the
privacy needs of the site. Users can join and leave an open site at will. To
join a restricted site, a user has to be added by the site administrator. A user
can also request to be added through the Sites section of the Control Panel. A
private site is like a restricted site but doesn't appear in the Sites section
of the Control Panel for users who aren't members.

You also have the ability to organize sites into hierarchies. At the bottom of
the Details sub-section is the Parent Site section. This feature allows you to
select the parent site for the site you're currently on. After selecting a
parent site, you have a checkbox option to limit membership to members of the
parent site.

#### Default User Associations [](id=default-user-associations)

Once you have the basic details of your site saved, you can begin assigning your
users to roles and teams. *Default User Associations*, the leading option when
opening the Advanced tab, lets you configure site roles and teams that newly
assigned site members will have by default. If you'd like to learn more about
creating roles and/or teams, visit the
[Roles and Permissions](/discover/portal/-/knowledge_base/6-2/roles-and-permissions)
and
[Creating Teams for Advanced Site Membership Management](/discover/portal/-/knowledge_base/7-0/creating-teams-for-advanced-site-membership-management)
sections, respectively.

#### Ratings [](id=ratings)

To allow your site's users to rate content in your site, you can use the
*Ratings* option to select what ratings type to use for applications like
Documents and Media, Web Content, Comments, etc. Ratings types include Stars,
Likes, and Thumbs. This is the leading option when opening the Social tab.

<!-- TODO Is there a link we can point to for this? Coordinate with Jim. -Cody
-->

#### Mentions [](id=mentions)

The last configuration option in Site Settings related to managing users is
*Mentions*. *Mentions* is found under the Social tab. This option allows you to
enable/disable the Mentioning functionality, which is used to *mention* (notify
and/or draw attention to) friends and colleagues by entering the "@" character
followed by their user name. You can learn more about the mentioning feature by
visiting the
[Mentioning Users](/discover/portal/-/knowledge_base/7-0/mentioning-users)
article.

Now that you're aware of your Site Settings abilities to improve User and
Content management, you'll learn how to improve your site's overall intelligence
using Site Settings.

### Improving Site Intelligence [](id=improving-site-intelligence)

Site Settings offers several miscellaneous configuration options that improve
your site's usability, overall display, and data tracking. To make your Lunar
Resort site smarter, explore and configure the options below.

#### Site URL [](id=site-url)

One of the most important aspects of your site is the simplicity of your site
URLs. Long obscure site URLs are a hassle for users to deal with, and can
negatively affect your site's probability of being featured by search engines.
Having a human-readable friendly URL assists indexing bots and is critical to
good search engine optimization. For the Lunar Resort site, you can select the
*Site URL* tab to set a friendly URL and/or a virtual host. The *Friendly URL*
option lets you manage the path to your site in the instance's URL. Friendly
URLs are used for both public and private pages. For public pages, the friendly
URL is appended to http://localhost:8080/web. For private pages, the friendly
URL is appended to http://localhost:8080/group. Each friendly URL needs to be a
unique name, of course.

For example, suppose you want to set a friendly URL for your Lunar Resort site.
If you set the friendly URL of your instance's default site to /lunar-resort,
the URL of your default site's public home page would change to
http://localhost:8080/web/lunar-resort/home. If your instance's default site had
private pages, the URL of the default private home page would change to
http://localhost:8080/group/lunar-resort/home.

Note that if you're adding a friendly URL for your instance's home page, you
should update your instance's Home URL field so that page requests to
http://localhost:8080 redirect properly. To do this, navigate to the
*Configuration* &rarr; *Instance Settings* page of the Control Panel and find the
Home URL field in the Navigation section. For the Lunar Resort example, you
would enter */web/lunar-resort/home* into the Home URL field. Once you've
entered this setting, page requests to localhost:8080 will redirect to the
friendly URL of your Liferay instance's new homepage:
http://localhost:8080/web/lunar-resort/home.

The other setting you can configure under the Site URL tab is *Virtual Hosts*,
which makes web navigation much easier for your users by connecting a domain
name to a site. This allows you to define a domain name (i.e.,
www.lunar-resort.com) for your site. This can be a full domain or a subdomain.
This enables you to host a number of web sites as separate sites on one Liferay
server.

For instance, if you set this up for the Lunar Resort's development network,
users in that site could use *developers.lunar-resort.com* to get to their site,
provided that the Lunar Resort instance's network administrators created the
domain name and pointed it to the Liferay server.

To set this up, the DNS name *developers.lunar-resort.com* should point to your
instance's IP address first. Then enter *http://developers.lunar-resort.com* in
the Virtual Host tab for the Developers site. This helps users quickly access
their site without having to recall an extended URL. The *Site URL* option is
listed under the General tab.

#### Analytics [](id=analytics)

Analyzing site traffic is another huge advantage for site administrators that
want to monitor what content is most popular. @product@ includes built-in
support for Google Analytics, allowing administrators to make use of Google's
tool set for analyzing site traffic data. When you sign up for Google Analytics,
a snippet of code is provided which needs to be added to your web pages to allow
Google's system to register the page hit. It can be a tedious process to add
this code to every page on a site, especially if it's a large site and there is
a lot of user-generated content.

This problem can be solved in Liferay by putting Google's code into a custom
theme written specifically for the web site on which Liferay is running. Doing
this, however, requires a theme developer to make specific changes to the theme
and it prevents users from using the many themes that are freely available for
@product@ "out of the box."

Because of this, support for Google Analytics has been built into @product@, and
can be turned on through a simple user interface. This allows Liferay
administrators to make use of Google Analytics on a site by site basis and turn
it on and off when needed. You can sign up for Google Analytics at the Google
Analytics site here:
[http://www.google.com/analytics](http://www.google.com/analytics).

To enable Google Analytics support, navigate to the *Analytics* tab in Site
Settings, which loads a very simple form, pictured below.

![Figure 5: Setting up Google Analytics for your site is very easy: sign up for Google Analytics, receive an ID, and then enter it into the Google Analytics ID field.](../../../images/maintaining-google-analytics.png)

Enter your Google Analytics ID (which should have been provided to you when you
signed up for the service) in the field and click *Save*. All the pages in the
site you selected will now have the Google Analytics code in them and will be
tracked. 

This is a fairly simple procedure, and it gives you the ability to take
advantage of some great tools to help you visualize who's coming to your site
and from where. The *Analytics* option is listed under the Advanced tab.

#### Maps [](id=maps)

The *Maps* options lets you configure the maps API provider used by your Liferay
instance when displaying geolocalized assets. Geolocalized assets can be
displayed for documents, web content articles, DDL records, etc. Maps is
available under the Advanced tab.

<!-- TODO: Provide link to where Maps feature is documented once it's available.

This is Jim's area. Good blog on this:
https://www.liferay.com/web/juan.fernandez/blog/-/blogs/adding-geolocation-support-to-liferay-7-0
-Cody -->

#### Languages [](id=languages)

The *Languages* option lets you configure the language options for your
site. This is the leading option when opening the Languages tab. You have
options to use the default language options or define a custom default language.

Now that you know how to configure sites, you'll learn how to customize your
personal sites.

## Customizing Personal Sites [](id=customizing-personal-sites)

By default, newly created users in @product@ are each granted a personal site.
Each user functions as the site administrator of his or her personal site.
Personal sites are fully customizable but cannot have more than one member. The
public pages of personal sites provide a space for users to add content and
applications that they'd like to make accessible to anyone, including guests.
User blogs are often placed on public personal site pages. Content and
applications that users would like to reserve for personal use are often placed
on the private pages of personal sites. For example, each user can add a
Documents and Media application to his or her private pages and use it as an
online private file repository.

If you'd like to disable personal sites for your Liferay instance, just add the
following properties to your `portal-ext.properties` file:

    layout.user.public.layouts.enabled=false
    layout.user.private.layouts.enabled=false

+$$$

**Note:** The public and private page sets of personal sites are handled
separately. You can leave one page set enabled while disabling the other.

$$$

What if you initially had user personal sites enabled for your instance but then
disabled them? Each existing user's personal site remains on your Liferay
instance until the next time they log in, at which point it's removed.

You can allow users to create personal sites but not have them automatically
created for new users. To do this, first make sure that
`layout.user.public.layouts.enabled` and `layout.user.private.layouts.enabled`
are not set to `false`. You don't need to explicitly set them to `true`--`true`
is the default. Then add the following properties to your
`portal-ext.properties` file:

    layout.user.public.layouts.auto.create=false
    layout.user.private.layouts.auto.create=false

If the properties `layout.user.public.layouts.enabled`,
`layout.user.private.layouts.enabled`, `layout.user.public.layouts.auto.create`,
and `layout.user.private.layouts.auto.create` are all set to `true`, which is
the default, then users will have personal sites and public and private pages
will be automatically created for new users. There are a number of portal
properties you can use to customize the automatically created pages. You can
customize the names of the default pages, the applications that appear on the pages,
the themes and layout templates of the default pages, and more. Please refer to
the
[Default User Public Layouts](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Default%20User%20Public%20Layouts)
and
[Default User Private Layouts](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Default%20User%20Private%20Layouts)
sections of the `portal.properties` file for details.

+$$$

**Note:** By default, users are able to modify the pages and applications of
their personal sites. Administrators, however, can customize the modifiable
portions of personal sites through @product@'s permissions system by removing
permissions from roles. To disallow all Liferay users from modifying something,
remove the relevant permission from the User role.

$$$

Historically (prior to Liferay 5.1), only power users received personal sites.
Back then, they were called personal communities. If you'd like only power users
to receive personal sites, add the following properties to your
`portal-ext.properties` file:

    layout.user.public.layouts.power.user.required=true
    layout.user.private.layouts.power.user.required=true

Personal sites are a dynamic feature of @product@. They allow users to manage
and customize their own pages and content on your Liferay instance.

You've officially been introduced to Liferay's concept of sites. Using a point
and click interface, you can create multiple web sites and define how users can
access them, whether they are linked to a domain name, and create all of their
pages. You can learn more about creating site pages in the next section.
