# Understanding Site Management [](id=understanding-Site-management)

Whether you're building a large corporate webSite or a small Site for
facilitating collaboration among team members, supporting different kinds of
collaboration and social scenarios is a must. Liferay's Sites provide three
membership types:

**Open:** Users can become members of the Site at any time.

**Restricted:** Users can request Site membership but Site administrators must
approve requests for users to become members.

**Private:** Users cannot join the Site or request Site membership. Site
administrators must manually select users and assign them as Site members.

In addition to these memberships, when a Site is associated with an
organization, all the users of that organization are automatically considered
members of the Site.

You can view all the available open and restricted Sites by adding the My Sites
application to a page and accessing the *Available Sites* tab. You can request
access to any of the Sites you're not already a member of by selecting the
Site's *Options* button

(![Options](../../../../images/icon-actions.png)) and clicking *Join*.

## Site Scope [](id=site-scope)

Members of a Site can be given additional privileges in the Site by using
permissions. It is also possible to assign different roles within the
Site to different members. This can be done through *Site Roles*, which are
defined equally for all Sites or *Teams* which are unique for each Site. These
concepts are discussed later.

@product@ separates Site-scoped information from the Control Panel by placing it
in the Site menu. From this menu, you can select the specific Site to work on.
The Site Administration panel is available for your Site, which includes Build,
Content, Categorization, Recycle Bin, Members, Configuration, and Publishing.

![Figure 1: Your Site's content resides in the Site Administration menu.](../../../../images/web-content-site-content.png)

## Site Hierarchies [](id=site-hierarchies)

Sites can also be organized hierarchically, just like Organizations. The
difference between Sites and Organizations, of course, is that Sites organize
pages, content, application data, and users (via Site memberships) whereas
organizations only group users. Content sharing is available for Sites within
the same hierarchy. For instance, if a parent Site has a document called *Lunar
Goals and Objectives* and want all its child Sites to have a copy, the parent
Site's administrator can enable content sharing to share the document
automatically with its child Sites. Also, content sharing privileges can be set
to let every Site administrator share content across Sites they manage. Some
examples of content you can share across Sites include web content structures
and templates, categories, application display templates, etc.

Please refer to the 
[Sites Admin Portlet](https://docs.liferay.com/portal/7.1/propertiesdoc/portal.properties.html#Sites%20Admin%20Portlet)
section of Liferay's `portal.properties` file for a list of relevant
configurable properties. For example, the
`Sites.content.sharing.with.children.enabled` property can disable content
sharing between Sites and child Sites, disable it by default while allowing Site
administrators to enable it per Site, or to enable it by default while allowing
administrators to disable it per Site.

The Sites Directory application is a configurable app that shows a hierarchy of
Sites and child Sites. It enables users to navigate to any of the displayed
Sites. To use this app to display Site hierarchies, add it to a page, open its
Configuration window, and under Display Style, select *List Hierarchy*. The My
Sites Directory application is similar to the Sites Directory application,
except that it lists only the Sites a user belongs to.

Each child Site in the hierarchy has its own administrator, and the Site
Administrator role permissions do not flow down to child Sites in the hierarchy.
If a Site Administrator creates a child Site, he or she has the same permissions
in that child Site. This is not, however, because of inheritance. It is only
because creating a Site makes you the Owner of that Site. A Site Administrator
or a parent Site has no default role in any child Sites created by other Site
Administrators. 

If you wanted a user to have administrative access to all Sites in a Site/child
Site hierarchy, you must create a role based on the Site Administrator role that
has the permission *Manage SubSites*. 

The Site Map application helps users navigate a Site. A Site administrator can
configure a root page and a display depth. Just as Sites can have hierarchies,
so can the pages within a Site. The display depth of the Site Map application
determines how many levels of nested pages to display.

![Figure 2: The Site Map application lets users navigate among pages of a Site organized hierarchically.](../../../../images/site-directory-site-map.png)

## Site Members [](id=site-members)

Another useful administrative application is the Site Members application. This
enables administrators to survey all the users, organizations, and user groups
that reside in the Site. Similarly, Liferay provides the Portal Directory
application, which functions the same as the Site Members app, but globally
scoped for all Sites in the instance.

## Page Sets [](id=page-sets)

Sites have two categories of pages called page sets. There are two kinds of page
sets: public pages and private pages. A Site can have only public pages, only
private pages, or both. Private pages can only be accessed by Site members.
Public pages can be accessed by anyone, including users who haven't logged in.
It's possible to restrict access to pages at the page set level or at the level
of individual pages through the permissions system. Public pages and private
pages have different URLs and can have different content, applications, themes,
and layouts.

Building a corporate intranet is a typical use case for Sites. A corporate
intranet could have Sites for all the organizations in the company: Sales,
Marketing, Information Technology, Human Resources and so on. But what about the
corporate health and fitness center? That's something everybody in the company,
regardless of organization, may want to join. This makes it a good candidate for
an open and independent Site. Similarly, the home page for a corporate intranet
should probably be placed in an open independent Site so any member of the
instance can access it.

For other kinds of websites, you may want to use independent Sites to bring
users together who share a common interest. If you were building a photo sharing
website, you might have independent Sites based on the types of photos people
want to share. For example, those who enjoy taking pictures of landscapes could
join a Landscapes Site and those who enjoy taking pictures of sunsets could join
a Sunsets Site.

There is always one default Site, which is also known as the main Site of the
instance. This Site does not have its own name but rather takes the name of the
instance. By default the instance name is *Liferay* but this value can be
changed through the configuration of the setup wizard. The instance name can
also be changed at any time through the Control Panel within *Configuration
&rarr; *Instance Settings*.
