# Creating Sites

As stated in the [Building a site with Liferay Web Content](/discover/portal/-/knowledge_base/6-2/building-a-site-with-liferay-web-content)
section, a site is a set of pages that can be used to publish content or
applications. Sites can be independent or they can be associated with an
organization and serve as the website for that organization. With Liferay, you
can create as many different sites as you like within the context of a single
portal.

You can use sites in Liferay to build many different kinds of websites. Whether
you're building a large corporate website, a company intranet, or a small site
designed to facilitate collaboration among team members, Liferay's framework
provides all the tools you need. To support different kinds of collaboration and
social scenarios, Liferay's sites provide three membership types:

- Open: Users can become members of the site at any time. Users can join sites
from the *My Sites* portlet.

- Restricted: Users can request site membership but site administrators must
approve requests in order for users to become members. Requests can be made from
the *My Sites* portlet.

- Private: Users are not allowed to join the site or request site membership.
Private sites don't appear in the *My Sites* portlet. Site administrators can
still manually select users and assign them as site members.

In addition to these memberships, when a site is associated with an
organization, all the users of that organization are automatically considered
members of the site.

Members of a site can be given additional privileges within the site by using
Liferay's permission settings. It is also possible to assign different roles
within the site to different members. This can be done through *site roles*
which are defined equally for all sites or *teams* which are unique for each
site.

As of Liferay 6.2, sites can be organized hierarchically, just like
organizations. The difference between sites and organizations, of course, is
that sites are used to organize pages, content, application data, and users (via
site memberships) whereas organizations are only used to group users. Content
sharing is available for sites within the same hierarchy. For instance, if a
parent site has a document called *Lunar Goals and Objectives* and would like
for all its subsites to have a copy, the parent site's administrator can enable
content sharing to automatically share the document with its subsites, instead
of having to send each site the document individually. Also, content sharing
privileges can be set to let every site administrator share content across sites
they manage.

Please refer to the Sites Admin Portlet section of Liferay's `portal.properties`
file for a list of relevant configurable properties. For example, the
`sites.content.sharing.with.children.enabled` property allows you to disable
content sharing between sites and subsites, disable it by default while allowing
site administrators to enable it per site, or to enable it by default while
allowing administrators to disable it per site.

The Sites Directory portlet is a configurable portlet that can allow users to
view a hierarchy of sites and subsites. It enables users to navigate to any of
the displayed sites. To use this portlet to display site hierarchies, add it to
a page, open its Configuration window, and under Display Style, select *List
Hierarchy*. The Site Map portlet is another configurable portlet that's intended
to help users navigate among pages within a site. When configuring this portlet,
a site administrator can select a root page and a display depth. Just as sites
can be organized hierarchically, so can the pages within a site. The display
depth of the Site Map portlet determines how many levels of nested pages to
display.

![Figure 2.3: The Site Directory portlet can allow users to navigate between sites organized hierarchically. The Site Map portlet can allow users to navigate among pages of site organized hierarchically.](../../images/site-directory-site-map.png)

Liferay's sites have two categories of pages called page sets. There are two
kinds of page sets: public pages and private pages. A site can have only public
pages, only private pages or both. Private pages can only be accessed by site
members. Public pages can be accessed by anyone, including users who haven't
logged in. It's possible to restrict access to pages at the page set level or at
the level of individual pages through the permission system. Public pages and
private pages have different URLs and can have different content, applications,
themes, and layouts.

Building a corporate intranet is a typical use case for Liferay sites. A
corporate intranet could have sites for all the organizations in the company:
Sales, Marketing, Information Technology, Human Resources and so on. But what
about the corporate health and fitness center? That's something everybody in the
company, regardless of organization, may want to join. This makes it a good
candidate for an open and independent site. Similarly, the home page for a
corporate intranet should probably be placed in an open independent site so any
member of the portal can access it.

+$$$

**Tip:** Prior to Liferay 6.1, there were two ways of creating sites:
organizations and communities. This has been simplified to provide more ease of
use and allow for more flexibility. The main role of organizations is still to
organize the users of the portal in a hierarchy but they can also have
associated sites. Communities can still be created through independent sites but
the new name reflects the fact that sites can be used for many different
purposes besides communities.

$$$

For other kinds of web sites, you may want to use independent sites to bring
users together who share a common interest. If you were building a photo sharing
web site, you might have independent sites based on the types of photos people
want to share. For example, those who enjoy taking pictures of landscapes could
join a Landscapes site and those who enjoy taking pictures of sunsets could join
a Sunsets site.

Liferay always provides one default site, which is also known as the main site
of the portal. This site does not have its own name but rather takes the name of
the portal. By default the portal name is *liferay.com* but this value can be
changed through the simple configuration of the setup wizard. The portal name
can also be changed at any time through the Control Panel within *Portal
Settings*.

## Creating and Managing Sites [](id=creating-and-managing-sites)

Sites can be created through the Control Panel by a portal administrator.
Liferay's Control Panel provides an administrative interface for managing your
portal. There are four main sections of the Liferay's Control Panel: Users,
Sites, Apps, and Configuration. In this section, we'll learn how to use the
Control Panel to manage sites. In the next section, we'll learn about using the
Control Panel to manage site templates and page templates. For information about
the Apps, Users, and Configuration sections of the Control Panel, please see the
[Leveraging the Liferay Marketplace](/discover/portal/-/knowledge_base/6-2/leveraging-the-liferay-marketplace),
[User Management](/discover/portal/-/knowledge_base/6-2/user-management), and
[Using the Control Panel](/discover/portal/-/knowledge_base/6-2/using-the-control-panel)
sections, respectively.

+$$$

**Tip:** Prior to Liferay 6.2, the Control Panel included interfaces both for
site administration and for portal administration. In Liferay 6.2, these
interfaces have been separated. If you're signed in as an administrator, you can
access the Liferay 6.2 Control Panel by clicking *Admin* &rarr; *Control Panel*.
To manage a single site, navigate to the site by clicking on *My Sites* and
clicking on the site's name. Then click on *Admin* &rarr; *Site Administration*.
The Site Administration interface allows to configure site settings and manage
the pages, content, and users of the site.

$$$

To add a site, click on *Sites* under the Sites section of the Control Panel and
then click *Add*. If there is at least one site template available, a dropdown
menu appears. Site templates provide a preconfigured set of pages, portlet
applications, and content that can be used as the basis of a site's public or
private page set. To create a site from scratch, select *Blank Site*. Otherwise,
select the name of the site template you'd like to use. If you opt to create a
site from a site template, you have to choose whether to copy the site
template's pages as your new site's public or private page set. If other site
templates are created, they will appear in the Add menu as they become
available. The following figure shows the form that needs to be filled when
creating a *Blank Site*.

![Figure 2.4: The New Site window aids in your new site development.](../../images/01-add-site-screen.png)

**Name:** is the name of the site you wish to create.

**Description:** describes the site's intended function.

**Active:** determines whether a site is active or inactive. Inactive sites are
inaccessible but can be activated whenever a site administrator wishes.

**Membership Type:** can be open, restricted or private. An open site appears in
the My Sites portlet and users can join and leave the site whenever they want. A
restricted site is the same except users must request membership. A site
administrator must then explicitly grant or deny users' requests to join. A
private site does not appear in the My Sites portlet and users must be added to
it manually by a site administrator.

**Allow Manual Membership Management:** determines whether to allow or disallow
users to be manually added or removed from the site. By default, manual site
membership management is enabled. This allows administrators to manually assign
users to the site. It also allows users to join open sites or request membership
from restricted sites using the My Sites portlet. For organization sites, manual
site membership management is disabled, by default. This causes organization
members to be automatically assigned membership following the organization's
membership policy. Also, because manual membership management is disabled for
organization sites, by default, the *Users* section of *Site Administration* is
unavailable. To activate the *Users* functionality for your organization site,
you'll need to check *Allow Manual Membership Management* after creating the
organization site by navigating to its *Site Settings* menu.

+$$$

**Note:** It's possible for site memberships to be handled automatically by a
membership policy. The membership policy can check various pieces of information
from each user, such as their first names, last names, birthdays, job titles,
organizations, and user groups. Using this information, the site membership
policy can automatically assign members to the site. If your site will implement
a membership policy, your site administrators can disallow manual membership
management for their site. When the Allow Manual Membership Management option is
disabled, the *Users* section of *Site Administration* (Site Memberships and
Site Teams) is hidden, even from administrators.

$$$

**Directory Indexing Enabled:** allows site administrators to browse the site's
documents and media files and folders. For example, a site administrator of a
site called *Lunar Resort* can browse documents at
[http://localhost:8080/documents/lunar-resort](http://localhost:8080/documents/lunar-resort) if this option is enabled.

**Parent Site:** lets you select a parent site for the site that's being
created. As of Liferay 6.2, sites can be organized hierarchically. Using
hierarchical sites provides a simplified way to manage site memberships and site
content sharing. For organizations that have attached sites, the organization
hierarchy should match the site hierarchy. When you select a parent site, an
additional option appears: *Limit membership to members of the parent site*. If
this option is enabled, the site's membership policy performs a check so that
you can only assign members to the current site if they're already members of
the parent site.

Once you've created a site, it appears in the Sites page of the Control Panel.
Once the site has been created you can specify more details about the site using
three categories: Basic Information, Search Engine Optimization, Advanced, and
Miscellaneous. We'll go into more detail for your site's settings in the *Site
Settings* section later in the chapter.

When creating a site from a site template, the initial form provides a new
option that lets you decide if you want to copy the pages from the template as
public pages or as private pages. By default, the site is linked to the site
template and changes to the site template propagate to any site based on it. A
checkbox appears that allows users to unlink the site template if the user has
permission to do so.

Now that our new site is created, lets learn how to create and manage its pages.

## Configuring Site Settings

As with Site Pages, you can access Site Settings by navigating to Site
Administration and clicking *Site Settings* from the Configuration section on
the left panel. You can also select the Site Administration sub-tab
*Configuration* from the *Admin* drop-down.

![Figure 2.10: The Site Settings window offers a plethora of options for your site.](../../images/web-content-site-settings.png)

You'll find options to specify details and metadata about your site, set up
friendly URLs and virtual hosts, configure search engine optimization settings,
turn staging on or off and specify a Google Analytics ID. Let's take a closer
look. 

**Details:** allows an administrator to change the description and membership
type of a site and also to specify tags and categories for the site. The
membership type can be set as open, restricted or private based on the privacy
needs of the site. Users can join and leave an open site at will. To join a
restricted site, a user has to be added by the site administrator. A user can
also request to be added through the Sites section of the Control Panel. A
private site is like a restricted site but doesn't appear in the Sites section
of the Control Panel for users who aren't members. 

**Categorization:** allows you to apply categories and tags to the site.

**Site URL:** Set a friendly URL and/or a virtual host for your site here. The
*Friendly URL* option lets you manage the path to your site in the portal's URL.
Friendly URLs are used for both public and private pages. For public pages, the
friendly URL is appended to http://localhost:8080/web. For private pages, the
friendly URL is appended to http://localhost:8080/group. Each friendly URL needs
to be a unique name, of course. Having a human-readable friendly URL assists
indexing bots and is critical to good search engine optimization. 

For example, suppose you were creating a portal for a bank called the Best Bank.
If you set the friendly URL of your portal's default site to /best-bank, the URL
of your default site's public home page would change to
http://localhost:8080/web/best-bank/home. If your portal's default site had
private pages, the URL of the default private home page would change to
http://localhost:8080/group/best-bank/home. 

Note that if you're adding a friendly URL for your portal's home page, you
should update your portal's Home URL field so that page requests to
http://localhost:8080 redirect properly. To do this, navigate to the Portal
Settings page of the Control Panel and find the Home URL field in the Navigation
section. For our bank example, we would enter */web/best-bank/home* into the
Home URL field. Once you've entered this setting, page requests to
localhost:8080 will redirect to the friendly URL of your portal's new homepage:
http://localhost:8080/web/best-bank/home.

*Virtual Hosts* make web navigation much easier for your users by connecting a
domain name to a site. This tab allows you to define a domain name (i.e.,
www.mycompany.com) for your site. This can be a full domain or a subdomain. This
enables you to host a number of web sites as separate sites on one Liferay
server.

For instance, if we set this up for the Lunar Resort's development network,
users in that site could use *developers.lunar-resort.com* to get to their site,
provided that the Lunar Resort portal's network administrators created the
domain name and pointed it to the Liferay server.

To set this up, the DNS name *developers.lunar-resort.com* should point to your
portal's IP address first. Then enter *http://developers.lunar-resort.com* in
the Virtual Host tab for the Developers site. This helps users quickly access
their site without having to recall an extended URL.

**Site Template:** If you've created the site from a site template, this section
displays information about the link between the site template and the site.
Specifically, you can see which site template was used and whether or not it
allows modifications to the pages inherited from it by site administrators. If
you're not using site templates for this site, you can safely ignore this
section.

**Sitemap:** lets you send a sitemap to some search engines so they can crawl
your site. It uses the sitemap protocol, which is an industry standard. You can
publish your site to Yahoo or Google and their web crawlers will use the sitemap
to index your site. Liferay Portal makes this very simple for administrators by
generating the sitemap XML for all public web sites.

By selecting one of the search engine links, the sitemap will be sent to them.
It's only necessary to do this once per site. The search engine crawler will
periodically crawl the sitemap once you've made the initial request.

If you're interested in seeing what is being sent to the search engines, select
the *Preview* link to see the generated XML.

**Robots:** If you're using virtual hosting for this site, you can configure
`robots.txt` rules for the domain. The Robots page gives you the option to
configure your `robots.txt` for both public and private pages on a site. If you
don't have Virtual Hosting set up, this tab is rather boring. 

**Default User Associations:** lets you configure site roles and teams that
newly assigned site members will have by default. If you'd like to learn more
about creating roles and/or teams, visit the
[Roles and Permissions](/discover/portal/-/knowledge_base/6-2/roles-and-permissions)
and
[Creating Teams for Advanced Site Membership Management](/discover/portal/-/knowledge_base/6-2/creating-teams-for-advanced-site-membership-management)
sections, respectively. 

**Staging:** enables you to edit and revise a page behind the scenes, then
publish changes to your site once they have been completed and reviewed. For a
full explanation of Staging, see the
[Staging Page Publication](/discover/portal/-/knowledge_base/6-2/staging-page-publication)
section.

**Analytics:** allows you to integrate your pages with Google Analytics. Liferay
provides seamless integration with Google Analytics, allowing you to place your
ID in one place, then it will get inserted on every page. This enables you
to focus your efforts on building the page, rather than remembering to put the
code everywhere. Google Analytics is a free service which lets you do all kinds
of traffic analysis on your site so you can see who visits, where visitors are
from and what pages they most often visit. This helps you tweak your site so you
can provide the most relevant content to your users.

**Content Sharing:** lets you configure whether sub-sites can display content
from this site. Even if you initially allowed content sharing between the parent
site and its sub-sites, you're able to deselect this option and immediately
revoke content sharing from all sub-sites.

**Recycle Bin:** provides the option to enable/disable the Recycle Bin for your
site. You can also regulate the age (in days) for which content is able to be
stored in the Recycle Bin until it is permanently deleted. For a full
explanation of the Recycle Bin, see the
[Recyling Assets with the Recycle Bin](/discover/portal/-/knowledge_base/6-2/recycling-assets-with-the-recycle-bin)
section.

**Custom Fields:** lets you edit the custom fields you already have configured
for the *Site* resource. If you don't have any custom fields configured for the
Site resource, you can navigate to the Control Panel &rarr; *Custom Fields*
located under the *Configuration* tab.

**Display Settings:** lets you configure the language options for your site. You
have options to use the default language options or define a new default
language.

**Pages:** From Site Settings, click on *Public Pages* or *Private Pages* to
manage some basic features of the pages on a site. If no pages have been defined
yet, you can set site templates for the public or private pages. If pages
already exist, links are provided to view them. You can also change the site's
application adapter, which is a special type of hook plugin that customizes out
of the box functionality for specific sites.

**Site Hierarchy:** New to Liferay 6.2 is the ability to organize sites into
hierarchies. At the bottom of the Site Settings page is the *Parent Site*
section. This feature allows you to select the parent site for the site you're
currently on. After selecting a parent site, you have a checkbox option to limit
membership to members of the parent site. For more information on site
hierarchies, navigate to the *Leveraging Liferay's Multi-site Capabilities*
section.

Now that you know how to configure sites, let's look at page templates and site
templates.

# User Personal Sites [](id=user-personal-sites)

By default, newly created users in Liferay are each granted a personal site.
Each user functions as the site administrator of his or her personal site.
Personal sites are fully customizable but cannot have more than one member. The
public pages of personal sites provide a space for users to add content and
applications that they'd like to make accessible to anyone, including guests.
User blogs are often placed on public personal site pages. Content and
applications that users would like to reserve for personal use are often placed
on the private pages of personal sites. For example, each user can add a
Documents and Media portlet to his or her private pages and use it as an online
private file repository.

If you'd like to disable personal sites for your portal, just add the following
properties to your `portal-ext.properties` file:

    layout.user.public.layouts.enabled=false
    layout.user.private.layouts.enabled=false
    
+$$$

**Note:** The public and private page sets of personal sites are handled
separately. You can leave one page set enabled while disabling the other.

$$$

What if you initially had user personal sites enabled for your portal but then
disabled them? Each existing user's personal site remains on your portal until
the next time they log in, at which point it's removed.

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
will be automatically created for new users. There are number of portal
properties you can use to customize the automatically created pages. You can
customize the names of the default pages, the portlets that appear on the pages,
the themes and layout templates of the default pages, and more. Please refer to
the Default User Public Layouts and Default User Private Layouts sections of
the `portal.properties` file for details. You can find an HTML version of the
this file here: [http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html).

+$$$

**Note:** Prior to Liferay 6.1, administrators could disallow users from being
able to modify the pages and portlets of their personal sites by setting the
following properties:

`layout.user.public.layouts.modifiable=true`
`layout.user.private.layouts.modifiable=true`
    
As of Liferay 6.1, this property is obsolete. However, you can customize the
modifiable portions of personal sites through Liferay's permissions system by
removing permissions from roles. To disallow all portal users from modifying
something, remove the relevant permission from the User role.

$$$

Historically (prior to Liferay 5.1), only power users received personal sites.
Back then, they were called personal communities. If you'd like only power users
to receive personal sites, add the following properties to your
`portal-ext.properties` file:

    layout.user.public.layouts.power.user.required=true
    layout.user.private.layouts.power.user.required=true
    
Personal sites are a dynamic feature of Liferay Portal. They allow users to
manage and customize their own pages and content on your portal. Next, let's
look at how users can customize applications.
