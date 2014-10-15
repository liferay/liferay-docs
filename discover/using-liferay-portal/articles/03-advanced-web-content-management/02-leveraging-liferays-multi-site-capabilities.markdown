# Leveraging Liferay's Multi-Site Capabilities [](id=leveraging-liferays-multi-site-capabilities)

As stated in chapter 1, a site is a set of pages that can be used to publish
content or applications. Sites can be independent or they can be associated with
an organization and serve as the website for that organization. With Liferay,
you can create as many different sites as you like within the context of a
single portal.

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

Liferay's sites have two categories of pages called page sets. There are two
kinds of page sets: public pages and private pages. A site can have only public
pages, only private pages or both. Private pages can only be accessed by site
members. Public pages can be accessed by anyone, including users who haven't
logged in. It's possible to restrict access to pages at the page set level or at
the level of individual pages through the permission system. Public pages and
private pages have different URLs and can have different content, applications,
themes, and layouts.

Building a corporate Intranet provides a typical use case for Liferay sites. A
corporate Intranet could have sites for all the organizations in the company:
Sales, Marketing, Information Technology, Human Resources and so on. But what
about the corporate health and fitness center? That's something everybody in the
company, regardless of organization, may want to join. This makes it a good
candidate for an open and independent site. Similarly, the home page for a
corporate intranet should probably be placed in an open independent site so any
member of the portal can access it.

For other kinds of web sites, you may want to use independent sites to bring
people together who share a common interest. If you were building a photo
sharing web site, you might have independent sites based on the types of photos
people want to share. For example, those who enjoy taking pictures of landscapes
could join a Landscapes site and those who enjoy taking pictures of sunsets
could join a Sunsets site.

Liferay always provides one default site, which is also known as the main site
of the portal. This site does not have its own name but rather takes the name of
the portal. By default the portal name is *liferay.com* but this value can be
changed through the simple configuration of the setup wizard. The portal name
can also be changed at any time through the Control Panel within *Portal
Settings*.

+$$$

**Tip:** Prior to Liferay 6.1, there were two ways of creating sites: 
organizations and communities. This has been simplified to provide more ease of 
use and allow for more flexibility. The main role of organizations is still to 
organize the users of the portal in a hierarchy but they can also have 
associated sites. Communities can still be created through independent sites but 
the new name reflects the fact that sites can be used for many different 
purposes besides communities.

$$$

Sites can be created through the Control Panel by a portal administrator. To add
a site, click on *Sites* under the Portal section of the Control Panel and then
click *Add*. If there is at least one site template available, a dropdown menu
appears. Site templates provide a preconfigured set of pages, portlet
applications, and content that can be used as the basis of a site's public or
private page set. To create a site from scratch, select *Blank Site*. Otherwise,
select the name of the site template you'd like to use. If you opt to create a
site from a site template, you have to choose whether to copy the site
template's pages as your new site's public or private page set. If other site
templates are created, they will appear in the Add menu as they become
available. The following figure shows the form that needs to be filled when
creating a *Blank Site*.

![Figure 3.9: Adding a Site](../../images/01-add-site-screen.png)

**Name:** is the name of the site you wish to create.

**Description:** describes the site's intended function.

**Membership Type:** can be open, restricted or private. An open site appears in
the My Sites portlet and users can join and leave the site whenever they want. A
restricted site is the same except users must request membership. A site
administrator must then explicitly grant or deny users' requests to join. A
private site does not appear in the My Sites portlet and users must be added to
it manually by a site administrator.

**Active:** determines whether a site is active or inactive. Inactive sites are
inaccessible but can be activated whenever a site administrator wishes.

Once you've created a site, it appears in the Sites page of the Control Panel.
Once the site has been created you can specify more details about the site using
three categories: Basic Information, Search Engine Optimization and Advanced.

![Figure 3.10: Editing a Site](../../images/01-site-editor.png)

**Details:** lets you edit the information you entered when you created the site
and allows you to choose a site template for the public or private pages of your
site. If you select a site template, leave the *Enable propagation of changes
from the site template* box checked to automatically update your site if the
associated site template changes. The update will only be done to pages which
have not been changed within the specific site. If you uncheck this box but
recheck it later, the template pages are then reapplied to your site,
overwriting any changes that may have been made. Only users who have the
permission "Unlink Site Template" will be able to disable the propagation of
changes. When the propagation is enabled, the site template might prevent
modification of some or all pages to ensure the propagation occurs.

**Categorization:** allows you to apply categories and tags to the site.

**Site URL:** lets you set friendly URLs and virtual hosts for your web site.

**Site Template:** provides additional information about the site template
associated to the pages of the site (if any).

**Sitemap:** lets you use the sitemap protocol to notify search engines your web
site is available for crawling.

**Robots:** lets you use a `robots.txt` file to specify certain pages and links
you don't want to be indexed by search engines. You need to set a virtual host
before you set a `robots.txt` file.

**Staging:** lets you turn on either Local Live staging or Remote Live staging.
To enable staging, the *Enable propagation of changes from the site template*
box on the Details tab must be unchecked. With staging enabled, changes to the
site template are automatically propagated to the staged site, not to the live
site. The changes still must be approved before the site is published to live.

**Analytics:** lets you set a Google Analytics ID that is used for your site.

When creating a site from a site template, the initial form provides a new
option that lets you decide if you want to copy the pages from the template as
public pages or as private pages. By default, the site is linked to the site
template and changes to the site template propagate to any site based on it. A
checkbox appears that allows users to unlink the site template if the user has
permission to do so.

![Figure 3.11: When creating a site from a site template, you need to choose whether the site template should be copied into the site's public pages or private pages.](../../images/creating-site-from-site-template.png)

Site templates are a powerful tool for managing many similar sites. Let's
examine how they work.
