
# Web Content Management

Liferay's Web Content Management system allows non-technical users to publish
content to the web without having advanced knowledge of web technology or
programming of any sort. Liferay WCM empowers you to publish your content with a
simple point and click interface and it helps you to keep your site fresh.
You'll find yourself easily creating, editing and publishing content within just
a few minutes of being exposed to its features. But Liferay WCM doesn't
sacrifice power for simplicity. If need be, you can use your developer skills to
create complex presentation layer templates that make your content "pop" with
dynamic elements. Once these templates have been deployed into the portal, your
non-technical users can manage content using these templates as easily as they
would manage static content. All of this makes Liferay WCM an appropriate choice
both for sites with only a few pages and for sites with gigabytes of content. 

Nearly all Liferay users use Liferay's Web Content Management system (WCM).
After all, all every web site has content that needs to be managed. Liferay's
WCM empowers you to manage all the content on your site quickly and easily
within your browser. Beyond managing existing content, Liferay's WCM lets users
easily create and manage everything from simple articles containing text and
images to fully functional web sites. Web publishing works alongside Liferay
Portal's larger collection of applications, which means you can add shopping
cart functionality, visitor polls, web forms, site collaboration tools and more.
Everything is done with our collection of easy-to-use tools with familiar
rich-text editors and an intuitive interface. 

In Liferay, you can create multiple sites within a single portal instance. Each
site can have distinct sets of users, content, and application data. Once set
up, non-technical users can administer a site. Liferay's fine-grained
permissions system ensures that your content and applications can be accessed
only by appropriate sets of users. To manage a site, no programming is required.
With Liferay's WCM, you have the ability to create, edit, stage, approve, and
publish content with easy-to-learn yet powerful tools. Liferay's WCM streamlines
the content creation process for end users. It's much faster to use Liferay's
WCM than it would be to create all the content for your site in HTML. WCM is
integrated with Liferay's services so advanced template developers can use them
to query for data stored elsewhere in Liferay. In this chapter, we'll cover the
following topics: 

- Leveraging Liferay's multi-site capabilities to create and administer sites
- Creating pages and selecting page types and layout templates
- Creating and publishing web content
- Exporting and importing content
- WCM workflow
- Site memberships and permissions
- Page and content permissions

By the time we're done, you should be able to apply all these concepts to your
own content. To demonstrate Liferay's Web Content Management features, we'll
create and manage content on the portal for *Nose-ster*, a new social network
where people are connected based on what their noses look like. Once you're
familiar with Liferay WCM, you'll wonder how you ever got along without it. 

## Setting up the Nose-ster Example Portal

Suppose that you've been assigned the task of building a web site for an
innovative new social networking site called Nose-ster. You've decided to take
advantage of Liferay Portal and its rapid deployment features as well as its
ability to get a fully functional, content-rich web site with integrated social
features up and running in little time. We can get you started. We'll walk you
through the creation of Nose-ster's web site, starting by creating and
publishing some simple content using Liferay's built-in WYSIWYG editor. In the
next chapter, we'll take advantage of Liferay's robust structure editor. Then
we'll use templates to display the content and then explore some of the advanced
publishing features such as the built-in workflow and Asset Publisher.

First, a little housekeeping. If we're going to be *Nose-ster*, our portal
should also be called Nose-ster. To set general information about your portal
like the name and mail domain, go to the Control Panel and select *Portal
Settings* under the Configuration heading. You could set up the configuration
for Nose-ster as follows.

![Figure 2.1: You can change the portal settings by navigating to the Control Panel and selecting *Portal Settings*.](../../images/04-web-content-changing-settings.png)

You can also customize the logo in the top left corner of every page by
selecting *Display Settings* under the *Miscellaneous* tab on the panel to the
right. Once you've made the changes, we can begin creating pages. With most
products, you would learn what the software can do in terms of setting up your
users and security model and then start building your system. You'd design your
infrastructure and get your server environment up and running while your
developers write the applications that live on your web site. With Liferay
Portal, however, you start farther ahead. Liferay Portal is more than just a
*container* for applications with a robust security model. It already includes
many of the applications you'll need, out of the box. These applications are
ready to go and are integrated with the rest of Liferay's user management and
security features.

## Leveraging Liferay's Multi-site Capabilities

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

As of Liferay 6.2, sites can be organized hierarchically, just like
organizations. The difference between sites and organizations, of course, is
that sites are used to organize pages, content, application data, and users (via
site memberships) whereas organizations are only used to group users. Please
refer to the Sites Admin Portlet section of Liferay's `portal.properties` file
for a list of relevant configurable properties. For example, the
`sites.content.sharing.with.children` property determines allows to disable
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

![Figure 2.2: The Site Directory portlet can allow users to navigate between sites organized hierarchically. The Site Map portlet can allow users to navigate among pages of site organized hierarchically.](../../images/site-directory-site-map.png)

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

---

 ![Tip](../../images/01-tip.png) **Tip:** Prior to Liferay 6.1, there were two
 ways of creating sites: organizations and communities. This has been simplified
 to provide more ease of use and allow for more flexibility. The main role of
 organizations is still to organize the users of the portal in a hierarchy but
 they can also have associated sites. Communities can still be created through
 independent sites but the new name reflects the fact that sites can be used for
 many different purposes besides communities.

---

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

### Creating and Managing Sites

Sites can be created through the Control Panel by a portal administrator. To add
a site, click on *Sites* under the Sites section of the Control Panel and then
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

![Figure 2.3: The New Site window aids in your new site development.](../../images/01-add-site-screen.png)

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

**Allow Manual Membership Management:** determines whether Site Membership
Policy Management is enabled or disabled. Some portal administrators may want to
have membership policies be handled automatically without having to manually add
or remove site members of their site. When disabled, the Site Membership section
as well as various other options to manually assign/remove members are hidden.

**Directory Indexing Enabled:** allows site administrators to browse the site's
documents and media files and folders. For example, a site administrator of a
site called Nose-ster could can browse documents at
[http://localhost:8080/documents/nose-ster](http://localhost:8080/documents/nose-ster) if this option is enabled.

**Parent Site:** lets you select a parent site for the site that's being
created. As of Liferay 6.2, sites can be organized hierarchically. Using
hierarchical sites provides a simplified way to manage site memberships and site
content sharing. For organizations that have attached sites, the organization
hierarchy should match the site hierarchy. 

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

### Creating and Managing Pages

You have a few options for accessing and configuring your site's page editing
interface. There are three interfaces to be aware of: *Site Pages*, *Page*, and
*Edit Page*. These interfaces all deal with your site's pages, however, each
interface is configurable in a different place and completes different
objectives. 

From the Site Administration page, your site pages can be accessed and
configured. If you're already on your desired site, you can reach the Site
Administration page by navigating to the *Admin* tab in the Dockbar and
selecting *Site Administration*. If you're not currently on the site you'd like
to edit, go to *My Sites* in the Dockbar and select your desired site. Once
you're on the Site Administration page, select *Site Pages* (if necessary) under
the Pages tab from the left panel. You can also use the *Pages* shortcut which
is also listed under the Admin tab.

![Figure 2.4: The *Site Pages* interface allows you to edit your site pages as a whole.](../../images/04-managing-site-pages.png)

To add new pages to your site, click the *Add* icon from the left palette and
select the *Page* tab. This is the *Page* interface, which offers a plethora of
options for your new page including name, site layout, and site template.

To manage the specific page of the site you've navigated to, click the *Edit*
icon from the left palette. This will only edit the specific page you're
currently on.

![Figure 2.5: The *Edit Page* interface allows you to edit the current page you're on.](../../images/04-web-content-managing-single-page.png)

*Site Pages* is an interface to view existing pages, create new pages, view
pages and export or import pages using Liferay Archive (LAR) files. Note that
you can switch between managing a set of pages and managing a single page using
the left-hand side navigation menu. Click on *Public Pages* or *Private Pages*
to manage the group or click on an individual page to manage just that one.
Switching views like this changes the list of available tabs to the right. By
default, liferay.com, which we renamed to nosester.com, contains a single public
page called *Welcome*.

Liferay's page groups are always associated with sites. Even users' personal
pages are part of their personal sites. All pages belong to one of two types of
page sets: public pages and private pages. By default, public pages are
accessible to anyone, even non-logged in users (guests). Private pages are
accessible only to users who are members of the site which owns the pages. This
means the private pages of an organization's site would be viewable only by
members of the organization. 

Regardless of whether the pages are public or private, Liferay uses the same
interface to manage them. Let's look at this interface more closely. 

#### More Page Management Tools

From the Site Pages interface in Site Administration, you can add a page to the
site by clicking the *Add Page* button. Because *Public Pages* is selected on
the left, clicking *Add Page* here adds a top level page next to the Welcome
page. You can, however, nest pages as deeply as you like. To create a sub-page
under the Welcome page, select the *Welcome* page first and then create your
page. If you later decide you don't like the order of your pages, you can drag
and drop them in the list to put them in whatever order you want. Let's go ahead
and add another top level page and name it *Community*. We'll use this page for
the Recent Bloggers and Wiki portlets.

![Figure 2.6: You can add a page to your site by giving it a name, page template, and page type.](../../images/04-web-content-add-page.png)

When you create a new page, you can create either a blank page or a page
prepopulated with portlets from a page template. When you're entering the name
of the page, you can select from a list of page templates that are currently
available. To view the pages once you add them, click the *View Pages* button.
This is how you'd populate your pages with content and applications. This is
covered in succeeding chapters. Page types include **Layout**, **Panel**,
**Embedded**, **Link to URL**, and **Link to a Page of This Site**. By default,
all pages are created as portlet pages but in some situations you might want to
use one of the other options. 

**Layout:** the pages we're usually talking about. They have a layout
which you can drag and drop portlets into. Most of the pages you create will be
layout pages.

**Panel:** can have any number of portlets on them, as selected by an
administrator, but only one will be displayed at a time. Users select which
portlet they want to use from a menu on the left side of the page and the
selected portlet takes up the entire page. 

**Embedded:** display content from another website inside of your portal.
An administrator can set a URL from in the page management interface and that
page will appear in the context and within the navigation of your Liferay
portal.

**Link to URL:** are just redirects to any URL specified by an administrator.
You can use URL pages to create links to pages belonging to other sites of your
portal or to pages of an external site. Use URL pages cautiously since blind
redirects create a poor user experience.

**Link to a Page of This Site:** creates a portal page which functions as an
immediate redirect to another page within the same site. You can select which
page to link to from a dropdown in the page management interface. You could use
a *Link to a Page of This Site* to place a deeply nested page in the primary
navigation menu of your site, for example.

To use the Edit Page interface to modify an existing page, navigate to the left
palette and select the *Edit* icon. Notice that it's not possible to add a new
page. This is because you're only managing the current page.

Once you've created pages and populated them with content, Liferay provides a
way for you to back them up to separate files. Let's see how that works. 

##### Backing up and Restoring Pages

Next to the *Add Page* button on the Site Pages screen, there are two buttons
labeled *Export* and *Import*. The Export button exports the pages you create
into a single file, called a LAR (Liferay Archive). You can then import this
file into any server running Liferay to re-create the pages. If you have a LAR
you would like to import, use the *Import* button. Exporting and Importing LARs
is a great way to take content from one environment (say, a development or QA
environment) and move it all in one shot to your production server. Note that
you should not make this a regular occurrence. If you want to regularly move
pages from one server to another, you should use Liferay's staging environment,
which is covered in chapter 3.

LARs are also a good way to back up your site's content. You can export them to
a specific location on your server which is backed up, and if you ever have to
restore your site, all you need to do is import the latest LAR file. One
limitation on LAR files, however, is that they are version dependent, so you
can't use an export from an old version of Liferay and import it into a newer
version.

Let's be good administrators and export a LAR file for backup purposes. Click on
the *Export* button and then name the file `nosesterv1.lar`. Use the check boxes
to determine what you'd like to export. For this initial export, select
everything. Note that if you select one of the *Choose* radio selectors or
*Change* links, you're given checkboxes for options to choose. The applications'
content can also be selcted for export, including the Documents and Media
Library, Message Boards, and Web Content assets. You can even export the theme
you're using!

Once you click *Export*, your browser prompts you to save the file. Once you
have the file, you can copy it to a backup location for safekeeping or import it
into another installation of Liferay Portal. If you must rebuild or wish to
revert back to this version of your site, you can import this file by clicking
the *Import* button from the Site Pages dialog box, browsing to it and
selecting it. 

Next, we'll look at the options on the right side menu, starting with Look and
Feel. 

##### Customizing the Look and Feel of Site Pages

When you open *Site Pages* from within Site Administration, it defaults to the
Look and Feel tab. On this tab, you're presented with an interface that allows
you to choose a theme for the current site. Themes can transform the entire look
of the portal. They are created by developers and are easily installed using the
Liferay Marketplace. Since we don't have any themes beyond the default one
installed yet, we'll use the default theme for our pages.

![Figure 2.7: The Look and Feel interface allows you to choose a theme for the current site.](../../images/04-look-and-feel.png)

Many themes include more than one color scheme. This allows you to keep the
existing look and feel while giving your site a different flavor. Change the
color scheme from blue to green by selecting *Dark* under *Color Schemes*. If
you now go back to the site (by clicking the left arrow in the top left
corner of the Dockbar), you'll see some parts of the page are now tinged
with a darker hue. 

If you apply a color scheme to a set of public or private pages, it is, by
default, applied to each page in the set. If, however, you click the *Edit Page*
button from the left palette of a specific page, you can select *Define a
specific look and feel for this page* from the *Look and Feel* tab to make the
color scheme apply to this page only. You can use this feature to choose a
different color scheme for a particular page than the one defined for the set of
public or private pages to which it belongs.

There are a few more configurable settings for your theme. You can switch the
bullet style between dots and arrows and you can choose whether or not to show
portlet borders by default.

Starting in Liferay 6.2, WAP related technologies have been deprecated. In
particular, the ability to modify themes for regular browsers and mobile devices
can now only be accomplished using Mobile Device Rules, which can be found in
the right menu. You can learn more about using Mobile Device Rules in the
*Advanced Content Management* chapter. You can enable the WAP functionality for
your portal's Look and Feel section by opening/creating your
`portal-ext.properties` file in your ${LIFERAY_HOME} directory and setting
`mobile.device.styling.wap.enabled=true`. WAP functionality will be completely
removed from Liferay in the next release.

The *CSS* section allows you to enter custom CSS that will also be served up by
your theme. In this way, you can tweak a theme in real time by adding new styles
or overriding existing ones. 

The next option configures the logo that appears for your site. 

##### Using a Custom Logo for a Site

By default, the Liferay logo is used for your site pages' logo. If you want to
use your own logo for a specific site, use the *Logo* tab. Adding a custom logo
is easy: select the Logo tab from the Site Pages interface and browse to the
location of your logo. Make sure your logo fits the space in the top left corner
of the theme you're using for your web site. If you don't, you could wind up
with a site that's difficult to navigate, as other page elements are pushed
aside to make way for the logo.

In the logo tab, you can also choose whether or not to display the site name on
the site. If you check the box labeled *Show Site Name* the site name will
appear next to the logo. This option is enabled by default and cannot be
disabled if the *Allow Site Administrators to set their own logo* option is
disabled in *Portal Settings*. Removing the site name is not available for the
default site -- only newly created sites and user pages have the option to have
the name display. Let's take a look at how to edit individual pages.

#### Changing Options for Individual Pages

When you use the *Edit Page* interface for a single page, some different options
appear. Let's look at what these do. 

**Details:** lets you name the page for any localizations you need, set whether
the page is hidden on the navigation menu, set an easy to remember, friendly URL
for the page, and select the page type. Plus you can specify how portlets are
arranged on a page. Choose from the available installed templates to modify the
layout. It's very easy for developers to define custom layouts and add them to
the list. This is covered more thoroughly in both the *Liferay Developer's
Guide* and in [*Liferay in Action*](http://manning.com/sezov).

**SEO:** provides several means of optimizing the data the page provides to an
indexer that's crawling the page. You can set the various meta tags for
description, keywords and robots. There's also a separate Robots section that
lets you tell indexing robots how frequently the page is updated and how it
should be prioritized. If the page is localized, you can select a box to make
Liferay generate canonical links by language. If you want to set some of these
settings for the entire site, you can specify them from the Sitemaps and Robots
tabs of the Manage Site Settings dialog box (see below).

---

 ![Tip](../../images/01-tip.png) In previous versions of Liferay, it was
 possible that a single page could be indexed multiple times. In Liferay 6.1,
 all URLs that direct to the same page will only create one entry in the index.
 Previously, the simple URL
 *http://www.nosester.com/web/guest/blog/-/blogs/thenose* and different versions
 of the URL which provided additional information about the referring page had
 different entries in the index. As of Liferay 6.1, each asset (web content
 article, blog entry, etc.) has a unique URL. From the search engine's point of
 view, this will make your pages rank higher since any references to variations
 of a specific URL will all be considered references to the same page.] 

---

**Look and Feel:** lets you set a page-specific theme.  

**JavaScript:** gives you the ability to paste custom JavaScript code to be
executed on this page. 

**Custom Fields:** If custom fields have been defined for pages (which can be
done from the *Custom Fields* page of the Control Panel), they appear here.
These are metadata about the page and can be anything you like, such as author
or creation date. 

**Advanced:** contains several optional features. You can set a query string to
provide parameters to the page. This can become useful to web content templates,
which you'll see in the next chapter. You can set a target for the page so that
it either pops up in a particularly named window or appears in a frameset. And
you can set an icon for the page that appears in the navigation menu. 

**Mobile Device Rules:** allows you to apply rules for how this page should be
rendered for various mobile devices. You can set these up in the *Mobile Device
Rules* section of Site Administration. 

**Embedded Portlets:** only appears if you have embedded one or more portlets on
the page. To embed a portlet on a page, first look up its portlet name in
Liferay's `WEB-INF/portlet-custom.xml` file. Portlet names in
`portlet-custom.xml` are sometimes referred to as portlet IDs. What we usually
mean by "portlet names," `portlet-custom.xml` refers to as "display names".
Next, add a web content display content to the page, create a new web content
article, switch to source, and paste in the following:

	<runtime-portlet name="" />

Then add the portlet name (ID) inside of the quotation marks, publish the web
content article, and select the article in the web content display portlet. Once
you've selected the new web content article, the embedded portlet appears on the
page.

---

 ![Tip](../../images/01-tip.png) Usually, you don't want the web content display
 portlet that you're using to embed a portlet to be visible. To make the web
 content display portlet invisible, click on the wrench icon of the web content
 display portlet, select *Look and Feel*, set *Show Borders* to *No*, and click
 *Save*. Once you've refreshed the page, only the embedded portlet will be
 visible.

---

**Customization Settings:** lets you mark specific sections of the page you want
users to be able to customize.

Next, we'll run practice modifying page layouts!

#### Modifying Page Layouts

Page layouts allow you to arrange your pages so the content appears the way you
want it to. Liferay comes with many layouts already defined. Developers can
create more and they can be deployed to your portal for your use.

To prepare for the portlets we'll soon be adding, let's change the layout of the
Collaboration page. To access layouts, select the *Edit* icon from the left
palette and click the *Details* tab (if necessary).

Now, select the *2 Columns (70/30)* layout and click *Save*. Once saved, you'll
return to the page and it'll seem as though nothing has happened. Once we start
adding portlets, however, you'll notice the page is now equally divided into two
columns. You can stack portlets on top of each other in these columns. There
are, of course, more complicated layouts available and you can play around with
them to get the layout you want.

Sometimes a particular layout is *almost* what you want but not quite. In this
case use the Nested Portlets portlet to embed a layout inside another layout.
This portlet is a container for other portlets. It lets you select from any of
the layouts installed in Liferay, just like the layouts for a page. This gives
you virtually unlimited options for laying out your pages.

The next option we'll explore is page customizations. 

#### Page Customizations

With page customizations, any user with the appropriate permissions can create
personalized versions of any public page. Before users can create personalized
versions of pages, customizations must first be enabled by an administrator.
Administrators can activate or deactivate customizations for any row or column
on any page. When users customize a page, they have the option to use either
their version or the default version of a page. Users can't see alternate
versions of pages other than their own.

![Figure 2.8: During page customization, individual columns change colors to indicate whether they are selected or not.](../../images/04-web-content-personal-customization.png)

To activate page customizations, click the *Edit Page* button from the left
palette and select the *Customization Settings* tab. Then select *Show
Customizable Sections* to view and modify sections on your page.

When an administrator activates page customizations for a page, any portlets
that are in a *Customizable* row or column can be moved around the page or
removed from the page. Users can add new portlets of their own choosing to these
columns of the page and can also customize portlet configurations. If at any
time users determine they don't like their customizations, they can click *Reset
My Customizations* to revert their pages back to the default. For more
information about page customizations, please refer to the Page Customizations
section of chapter 6.

Now that you know how to enable page customizations, let's look at the settings
for the site as a whole.

#### Configuring Site Settings

As with Site Pages, you can access Site Settings by navigating to Site
Administration and clicking *Site Settings* from the Configuration section on
the left panel. You can also select the Site Administration sub-tab
*Configuration* from the *Admin* drop-down.

![Figure 2.9: The Site Settings window offers a plethora of options for your site.](../../images/web-content-site-settings.png)

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

For instance, if we set this up for Nose-ster's Development Network, users in
that site could use developers.nosester.com to get to their site, provided
Nose-ster's network administrators created the domain name and pointed it to the
Liferay server.

To set this up, the DNS name *developers.nosester.com* should point to your
portal's IP address first. Then enter *http://developers.noseter.com* in the
Virtual Host tab for the Developers site. This helps users quickly access their
site without having to recall an extended URL.

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
about creating roles and/or teams, visit the *Advanced Web Content Management*
chapter. 

**Staging:** enables you to edit and revise a page behind the scenes, then
publish changes to your site once they have been completed and reviewed. For a
full explanation of Staging, see chapter 3: *Advanced Web Content Management*.

**Analytics:** allows you to integrate your pages with Google Analytics. Liferay
provides seamless integration with Google Analytics, allowing you to place your
ID in one place, then it will get inserted on every page. This enables you
to focus your efforts on building the page, rather than remembering to put the
code everywhere. Google Analytics is a free service which lets you do all kinds
of traffic analysis on your site so you can see who visits, where visitors are
from and what pages they most often visit. This helps you tweak your site so you
can provide the most relevant content to your users.

**Content Sharing:** lets you configure whether sub-sites can display content
from this site.

**Recycle Bin:** provides the option to enable/disable the Recycle Bin for your
site. You can also regulate the age (in days) for which content is able to be
stored in the Recycle Bin until it is permanently deleted. For a full explantion
of the Recycle Bin, see the *Configuring Liferay Applications* chapter.

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

#### Page Templates and Site Templates

*Page Templates* and *Site Templates* are invaluable tools for building similar
pages on larger portals. As you continue to add pages to sites in your portal,
you'll notice repeatable patterns in the designs of those pages. Page templates
enable you to preconfigure a single page and then apply it to any new page you
create. Site templates allow you to do the same thing but on the scale of a
site--if you have multiple sites that use a similar structure of pages, you can
create a single site template and use it to create as many sites as desired. For
more information on page templates and site templates, see the *Advanced Web
Content Management* chapter. 

#### Site Content

Liferay 6.2 separates Web Content management from the Control Panel by placing
it on the *Admin* &rarr; *Site Administration* page. From Site Administration,
you'll notice the Content heading where all your portal's content can be
managed, including web content. 

![Figure 2.10: Your site's content resides on the Site Administration page.](../../images/web-content-site-content.png)

For details about Liferay's social collaboration suite, see chapter 10. Next,
let's learn more details about creating pages.

### Creating Pages

There are a lot of other things you can do beyond placing portlets on a page. So
let's start working on the Nose-ster site. You can do this by going up to the
Dockbar and clicking *My Sites* &rarr; *Nose-ster*.

We'll use the *Community* page you created earlier in the chapter. Navigate to
the *Community* page and select the *Add* button from the left palette and then
the *Page* tab.

This screen's options should look familiar to you from previous sections, but
let's briefly go through how to create a new page: 

The Page interface allows you to:

- Give the name of the page
- Hide the page from the theme navigation
- Choose the page template
- Link to another website
- Link to another page in the current site
- Copy an existing page

When you select *Add Page* at the bottom of the menu, your new page will
appear on the navigation menu of your site. You can drag the page names to their
correct order as you see fit.

You can also create new pages from the Site Pages interface. Navigate to Site
Administration &rarr; *Site Pages*. If you navigate to *Public Pages* or
*Private Pages* on your site hierarchy, you'll notice the *Add Page* button,
which we discussed earlier in the chapter. If you navigate to a specific site,
you'll notice the *Add Child Page* button.

The *Add Child Page* lets you create child pages underneath the page you've
selected. You can nest pages as deep as you like but for every page below the
top level hierarchy you must provide navigation to it via a Navigation or
Breadcrumb portlet, at least with most themes (including the default).
Developers can create themes which have cascading menu bars which show the full
hierarchy. Some examples of that are in Liferay's plugin repositories.

For now, click the back arrow. You should be able to define and manage pages in
Liferay at this point so let's look at what you'd put on a page.

#### Adding Portlets to a Page

As we discussed earlier, Liferay Portal pages are composed of portlets. All of
your site's functionality, from blogs to shopping, is composed of portlets. Even
static web content can be displayed through Web Content Display portlets. To add
a portlet to a page, just click the *Add* button from the left palette and
select the *Applications* tab. You can either browse through the categories of
available portlets until you find the one you're looking for or you can search
for portlets by name. Once you've found a portlet, click the *Add* button to add
it the current page. Once it's been added to the page, you can drag it to a new
position. Alternatively, you can drag the portlet directly from the Applications
menu to a specific location on the page. Let's add some portlets to the
Collaboration page of the Nose-ster site.

1. From the left palette, select *Add* &rarr; *Applications*.
2. In the menu that appears, expand the *Collaboration* category.
3. Drag the *Blogs Aggregator* portlet off the Add Application window onto the
   right column of our page.
4. Next, drag the *Wiki* portlet to the *left column*.

See how easy it is to add applications to your pages? We've added the Wiki
portlet and Blogs Aggregator portlet to the Community page. 

![Figure 2.11: Yeah, we're showoffs. But as you can see, your page layout options are virtually limitless.](../../images/04-web-content-portlet-layout.png)

You'll find it's easy to make your pages look exactly the way you want them to.
If the layout options provided aren't enough, you can even develop your own.
More information about that can be found in Liferay's official guide to
development, [*Liferay in Action*](http://manning.com/sezov).

#### Page Permissions

By default, public pages are just that: public. They can be viewed by anybody,
logged in or not logged in. And private pages are really only private from
non-members of the site. If someone has joined your site or is a member of your
organization, that person can see all the private pages. You can, however,
modify the permissions on individual pages in either page group so only certain
users can view them.

Let's say we wanted to create a page only for administrators to see. We can do
this with the following procedure:

1. Go to the Dockbar and select *Admin* &rarr; *Site Administration*.
2. Click the *Site Pages* link (if necessary).
3. Click the *Private Pages* tab to switch to the Private Pages. Remember,
   these pages by default are viewable only by members of the site.
4. Create a page called *Admin Tips*.
5. Click on the page in the tree on the left and then click *Permissions*.
6. Uncheck the *View* and *Add Discussion* permissions next to the Site Member
   role.
7. Click the *Save* button.

![Figure 2.12: The Permissions offer a plethora of options for each role.](../../images/04-web-content-page-permissions.png)

Congratulations! You've just changed the permissions for this page so only site
administrators can view it. Any users you add to this role can now see the page.
Other users, even members of this site, won't have permission to see it.

Pages in Liferay are as flexible as pages you'd create manually without a
portal. Using a point and click interface, you can define your site any way you
want. You can create and remove pages, export and import them, set their
layouts, define how they are indexed by search engines and more. You've also
been introduced to Liferay's concept of sites. Again, using a point and click
interface, you can create multiple web sites and define how users can access
them, whether they are linked to a domain name and create all of their pages.

You now understand how to manage pages in Liferay Portal. It's time to move on
to adding content to those pages. Liferay's Web Content Management (WCM) is a
highly powerful, yet flexible, set of tools that enables you to successfully
manage your web site.

You'll soon discover that Liferay's WCM is easy to learn and highly
configurable. If you already have experience with WCM, you'll see some new
features and improvements to old ones. If you're new to Liferay's WCM, then
you'll be surprised at how fast you will be adding, editing and scheduling
content on your site. Once you're familiar with portlets such as Web Content
Display and Asset Publisher, your ability to manage an immense site with a large
amount of content will simply amaze you.

We'll be using Liferay's WCM to publish simple pieces of content, develop
templates to define how content is to be displayed, set up a workflow for
content to be approved, schedule when content is to be published and much, much
more.

## Features of Liferay's WCM

Liferay's WCM offers a host of features that makes managing the content of your
site easier:

- **WYSIWYG Editor:** A complete HTML editor that allow you to modify fonts, add
  color, insert images and much more.

- **Structure Editor:** Easily add and remove fields you want available to
  content creators and then dynamically move them around. This editor includes
  an entire suite of form controls you can drag and drop onto your structure.

- **Template Editor:** Import template script files that inform the system how
  to display the content within the fields determined by the structure.

- **Web Content Display:** A portlet that allows you place web content on a page
  in your portal.

- **Asset Publisher:** A portlet which can aggregate different types of content
  together in one view.

- **Scheduler:** Lets you schedule when content is reviewed, displayed and
  removed.

- **Workflow Integration:** Run your content through an approval or review
  process.

- **Staging:** Use a separate staging server or stage your content locally so
  you can keep your changes separate from the live site. 

Liferay's Web Content Management is a powerful and robust tool for creating and
organizing content on your web site. Let's begin by examining some basic
concepts involving sites and pages.

As you'll see, Liferay's WCM is a full-featured solution for managing your web
site. We'll start with an overview of what it has to offer and then we'll dive
down into its features. Note that web content is just one kind of asset on
Liferay. Other types of content (blog posts, wiki articles, message board posts,
etc.) are also considered assets. Liferay provides a general framework for
handling assets that includes tags, categories, comments, ratings, and more.
Please see chapter 5 for more information on Liferay's asset framework.

### Authoring (Basic) Content

As we've already discussed, content is the reason web sites exist. Liferay
Portal has made it easier than ever to get content published to your site.
Because Liferay Portal is so flexible, you can use basic authoring tools right
away or take advantage of the more advanced features. It's adaptable to your
needs.

We'll begin by creating some simple content using Liferay's WYSIWYG editor. Then
we'll publish it to the home page of Nose-ster's web site. This is a fast and
straightforward process that demonstrates how easy it is to create and publish
content on your Liferay Portal instance. Let's learn about the Web Content
section in Site Administration so we can create and publish our first pieces of
content.

When you manage web content from the Site Administration page, you can select
the location where the content resides. For instance, you can add content that's
available to a specific site or globally across the portal. If you're on the
site you wish to add content to, simply navigate to the *Admin* tab on the
Dockbar and select *Site Administration*. Conversely, if you need to switch
sites or would like to add content globally, navigate to the Control Panel and
select *Sites*. From this window, you can change the scope of where you'd like
to view, edit, or create content.

![Figure 2.13: You can choose where to create content by navigating to the Control Panel and selecting *Sites*.](../../images/sites-selector.png)

Once you have the Nose-ster site selected, click on the *Web Content* link in
Site Administration. You'll see a folder structure containing all of the web
content articles that exist in the currently selected scope (the Nose-ster
site). You can click *Add* &rarr; *Folder* to create a new folder. For sites
with lots of content and web content articles, it can be very useful to use
folders to group certain kinds of web content articles together. Click *Add*
&rarr; *Basic Web Content* to create a new web content article.

![Figure 2.14: Click *Add* &rarr; *Basic Web Content* to create a new simple web content article. To create a new web content article based on an existing web content structure, click *Add* and then click on the name of the structure you'd like to use.](../../images/web-content-add-menu.png)

Existing web content structures also appear in the *Add* menu. This provides
users with shortcuts for creating specific kinds of web content articles. For
example, if a web content structure called *FAQ* has been created for Frequently
Asked Questions articles in your currently selected scope, you can create a new
FAQ article by clicking *Add* &rarr; *FAQ*.

Click *Manage* &rarr; *Structures* to view a list of web content structures that
have already been created in your chosen scope. You can add new web content
structures here. Web Content templates are always associated with a particular
web content structure, so you can click *Actions* &rarr; *Manage Templates* to
view the web content templates associated with a structure or add a new
template to a structure. In the next chapter, we'll cover advanced features such
as structures, templates, and content scheduling in detail. 

#### Rich, WYSIWYG Editing

Once you've clicked *Add* &rarr; *Basic Web Content*, you'll find a highly
customizable form that by default has two fields: a title and a powerful WYSIWYG
editor. We could customize this form to contain whatever fields our content
needs but we'll keep things simple for now. If web content structures have
already been created in your currently selected scope, you can select one for
your new web content article by clicking *Select* next to the *Structure*
heading.

![Figure 2.15: If you'd like to select a web content structure for your web content article, click *Select* next to the *Structure* heading.](../../images/web-content-structure-select.png)

Type the words *Welcome to Nose-ster* in the *Name* field. Notice that content
can be localized in whatever language you want. If you click on the *localize*
checkbox, two select boxes appear which allow you to pick the language you're
working in and the default language. You can enter translations of your content
for any language in the list. The figure below shows this interface but for now,
we won't be using it, so you can leave it unchecked. In the content field, add a
short sentence announcing the web site is up and running.

Getting a new web site up and running is an exciting step for anyone, whether it
is a large corporation or a small non-profit charity. To celebrate this
momentous achievement at Nose-ster, let's give our announcement some of the pomp
and circumstance we think it deserves!

Using the editor, select all the text and then change the style to *Heading 1*
and the color to *Dark Green*.

You could insert an image here or even more text with a different style, as
demonstrated in the screenshot below. You can also add bullets, numbering, links
to another site or custom images. You can even add an emoticon. Let's add a
smiley face at the end of our announcement.

![Figure 2.16: View your content changes directly in the editor.](../../images/04-web-content-example2.png)

The WYSIWYG editor is a flexible tool that gives you the ability to add text,
images, tables, links and more. Additionally, you can modify the display to
match the purpose of the content. Plus it's integrated with the rest of Liferay
Portal: for example, when you upload an image to be added to a page, that image
can be viewed and manipulated in the Documents and Media portlet.

If you're HTML savvy, Liferay WCM doesn't leave you out in the cold. You can
click the *Source* button and write your own HTML if you wish.

On the right of the New Web Content form are options that allow you to
customize your web content. 

![Figure 2.17: New web content can be customized in various ways using the menu on the right.](../../images/wcm-menu.png)

**Abstract:** lets you to create a brief summary of the web content. You can
also pair the text with a small image.

**Categorization:** specifies the content type from a list of options. They are
*Announcements*, *Blogs*, *General*, *News*, *Press Release*, and *Test*. You
can also create tags to make the content easier to find in a search. Note that
these categories are defined by a property in the properties file; see the
`journal.article.types` property in chapter 20 for further information. 

**Schedule:** customizes the date and time your content publishes and/or
expires.

**Display Page:** lets you determine where the web contents are displayed when
linked from other pages. The Canonical URL can be used here. The Canonical URL
is unique for articles that redirect the visitor to the article's default
display page.

Imagine you have a newspaper with a sports section and a technology section. You
add a Sports page and a Tech page to your site, each one with a specific banner
and look and feel. You want the articles to appear in the appropriate pages, but
you know in Liferay articles are not related to pages. You can add an article as
often as you like in different web content display portlets or in configured
Asset Publishers. But if you have a *View in context* link, where will you show
your article? This is where you'd use a default display page. Articles that have
a default display page defined are shown with other related articles in
the same display page.

Imagine you have 100 sports articles and 100 tech articles. In previous versions
of Liferay you'd need to create a page for each article to show it. Now with
only one sports page and one tech page, you can show all articles in one place
in a consistent fashion.

##### Creating a Display Page

There are two ways of creating a display page. You can use a *Content Display
Page* template, which automatically creates everything you need, or you can
create one manually. The Content Display Page template is found under *Page
Templates* in the Sites section of the Control Panel.

To create a display page manually, add an Asset Publisher to a page. Then make
it the Default Asset Publisher for the page. This defines this Asset Publisher
as the one that displays the content if several Asset Publishers are on the same
page. Set this up by clicking *Configuration* on your Asset Publisher. Under the
*Setup* tab, navigate to *Display Settings* and check the checkbox labeled *Set
as the Default Asset Publisher for This Page*.

Once you've given an article its default display page, links to the article 
redirect the user to its default display page. To see how this works, add an
Asset Publisher to another page, like the Home page of the newspaper, and
configure it to *View in a Specific Portlet*. This setting is found in the
*Asset Link Behavior* menu under Display Settings. If you click on the link,
you'll be redirected to the Default Display Page of the article.

You now see that the link looks something like this:

www.nosester.com/nose-article

This is an example of a canonical URL, and it's a nice enhancement for Search
Engine Optimization (SEO) because the article's URL becomes the page URL. To a
search engine that's crawling your site, this means that the location of your
article never changes. And if you decide to use the content on another page in
the future, the article is still available at this URL. This feature is used in
search results, in related assets and in Asset Publishers. For more information
on Liferay's Display Pages, see chapter 5.

**Related Assets:** enables you to connect any number of assets within a site or
across the portal, even if they don't share any tags and aren't in the same
category. You can connect your content to a Blogs Entry, Message Boards Message,
Web Content, Calendar Event, Bookmarks Entry, Documents and Media Document, and
a Wiki Page.

![Figure 2.18: This blog entry has links to three Related Assets: one web content and two message board entries.](../../images/related-assets-link.png)

You'll learn how to publish links to related assets using the Related Assets
portlet in the *Defining content relationships* section of chapter 5.

**Permissions:** customize who has access to the content. By default, content is
viewable by Anyone (Guest Role). You can limit viewable permissions by selecting
any Role from the drop-down or in the list. Additionally, Liferay Portal
provides the ability to customize permissions in more detail. Select the *More
Options* link next to the drop down button and you'll find the different
activities you can grant or deny to your web content.

**Custom fields:** customize metadata about the web content. The fields can
represent anything you like, such as the web content's author or creation date.
If custom fields have been defined for web content (which can be done from the
*Custom Fields* page of the Control Panel), they appear here.

For more information on Custom Fields see the Custom Fields section in chapter
16.

For this piece of web content, we don't need to change anything. After you're
finished with permissions, click *Save as Draft*. This saves the content in
draft form. Once you're satisfied with your changes, select *Publish*. This
makes the content available for display, but we still have some work to do to
enable users to see it. In Liferay WCM, all content resides in a container,
which is one of two portlets: Web Content Display or Web Content List. By far
the most frequently used is the *Web Content Display* portlet. Let's look at how
it works.

### Publishing (Basic) Content

Now that we've created and published our first piece of web content for
Nose-ster, it's time to display it. First, add the *Web Content Display* portlet
to our Welcome page by selecting the *Add* button from the left palette and
selecting the *Applications* tab.

![Figure 2.19: Adding the Web Content Display Portlet](../../images/add-web-content-display.png)

Once the portlet appears, drag it to the position on the page where you want
your content to appear. You can have as many Web Content Display portlets on a
page as you need, which gives you the power to lay out your content exactly the
way you want it. 

To add existing web content, click the *Select Web Content* button on the lower
left of the portlet. You will see the message *Please select a web content from
the list below*. You have several options here.

Naturally, if your content appears in the list, you can simply select it. If
there is lots of published content available, you could search for the content
by name, ID, type, version, content and site (click the *Advanced* gear to see
all the options). You can also show the available locales for your content. If
you're working on the page for a particular language, you can select the
translation of your content that goes with your locale.

![Figure 2.20: Publishing web content is a snap. At a minimum, you only have to select the content you wish to publish. You can also enable lots of optional features to let your users interact with your content.](../../images/04-web-content-choosing-web-content.png)

If you have enabled OpenOffice.org integration with your portal, you can also
enable document conversion for your content. This gives your users the ability
to download your content in their format of choice. This is especially handy if
you are running a research or academically oriented site; users can very quickly
download PDFs of your content for their research projects.

Note that you also have other options, such as enabling a Print button, enabling
ratings so users can rate the content, enabling comments and enabling ratings on
comments.

The Print button pops the content up in a separate browser window that contains
just the content, without any of the web site navigation. This is handy for
printing the content. Enabling ratings shows one of two ratings interfaces
Liferay has: five stars or thumbs up and thumbs down. This can be set globally
in the `portal-ext.properties` file. See chapter 12 for further information
about this.

Enabling comments creates a discussion forum attached to your content which
users can use to discuss your content. Enabling ratings on comments gives your
users the ability to rate the comments. You may decide you want one, some or
none of these features, which is why they're all implemented as simple check
boxes to be enabled or disabled at need.

If you click the *Supported Clients* tab, you'll see you can choose the type of
client to which you want to expose content. This lets you target the large
screens of users' computers for expansive graphics and lots of special effects
or target the small screens of mobile devices with pertinent information and a
lightweight page. For now, leave both checked and click the *Save* button. You
can now close the configuration window.

To publish new content, select the *Add* button on the lower left of the
portlet. This launches the same full-featured editor you've already seen in the
Control Panel, which lets you add and edit content in place as you are working
on your page.

This is another example of the flexibility that Liferay Portal offers. At times,
you may want to add content directly into the Web Content Display portlet of the
page you're managing, especially if you are in the process of building the page.
At other times, you may want to navigate to Site Administration to create
content, because at that moment you're more concerned with the creation of the
content and not where the content will later be displayed. Liferay WCM supports
both processes. 

Editing content that's already been published is just as easy as creating new
content is. You'll use the same exact tools. 

##### Editing Content  

Once the content is displayed--whether you've selected content or created it in
the Web Content Display portlet--you can edit the content directly from the Web
Content Display portlet or from the Control Panel. To edit it from the Web
Content Display portlet, select the *Edit* button to the lower left of the
portlet. This launches the WYSIWYG editor and from there you can make any
necessary changes.

![Figure 2.21: The *Edit*, *Select Web Content*, and *Add* buttons appear when hovering over their icons.](../../images/web-content-display-icons.png)

When you publish your content this way, it becomes available immediately
(unless, of course, you have a workflow enabled, which we'll see below). This
happens whether you edit it in place or in Site Administration.

Note: if you want to view your page the way your users will see it (i.e.,
without all those portlet controls and icons), go up to the left palette and
select the *Edit Controls* icon. This makes all those extra controls you see as
a portal administrator disappear. You'll also notice the green "eye" transforms
into an orange "eye". If you need to use those controls again, just select *Edit
Controls* to return to the original format.

That's pretty much all there is to simple content creation. Whole sites have
been created this way. But if you want to take advantage of the full power of
Liferay's WCM, you'll want to use structures and templates found in chapter 3.
Next, let's see how you can manage your content with an approval process called
workflow. 

## Using Liferay's Workflow with WCM  

Workflow is essentially a predetermined sequence of connected steps. In Liferay
WCM, workflow is designed to manage the creation, modification and publication
of web content. You can set up a workflow so content can't be published without
going through an approval process you design. In this way, content is published
to the site only after it has been reviewed and approved.

Liferay's workflow engine is called Kaleo workflow and it ships with Liferay CE.
If you have uninstalled it or are using EE, it needs to be installed and
configured separately. This is covered in chapter 6. Since we have somewhat of a
"What came first--the chicken or the egg?" problem, for now, we'll assume it's
installed and look at how you can take advantage of workflow in getting your
content through any approval steps between creation and publication.

You may have noticed something appears to be missing from the staging process
discussed above. In particular, you might be asking the question, "How do I
reject changes?" Staging is integrated with Liferay's Workflow engine. To have a
review process for staged pages, you need to make sure you have a workflow
engine configured and you have staging set up in the workflow. To do this,
select the workflow definition desired for page revisions in the Workflow
Configuration.

When using a workflow, clicking *Submit for Publication* submits the staged
pages into the workflow. Once all necessary approvals have been completed, the
page status is marked as ready for publication. The *Publish to Live Now* and
*Schedule for Publication* options publish the last version of the selected
pages marked as ready for publication.

To enable workflow for Web Content, navigate to the Control Panel and select
*Workflow Configuration*. From there, select a workflow that has been deployed
to Liferay.

![Figure 2.22: You can select the pre-made *Single Approver* workflow to experiment with workflow management.](../../images/04-web-content-workflow-config.png)

As you'll discover in chapter 10, you can design workflows to suit your
organization's approval process. For Nose-ster's implementation we'll use the
*Single Approver* workflow which ships with the product.

#### Defining Workflows for Web Content  

Let's set up Liferay's Workflow for the Nose-ster web site. You must have the
Kaleo workflow plugin installed in order for the workflow categories to appear
in the Control Panel. Liferay's Kaleo workflow engine ships with CE versions of
Liferay. For installation instructions for Liferay EE, please see chapter 10.

1. Go to the Site Administration page and select *Workflow Configuration* from
the *Configuration* section.

2. From the select box, choose *Single Approver* for Web Content. Click *Save*.
Note that you can add workflow to many of Liferay's portlets.

That's all it takes to set up workflow for web content. Now that workflow is
enabled, publishing content works a little bit differently. Let's go through the
process of publishing details for new class offerings at Nose-ster. Return to
the home page and click the *Add Web Content* icon on the Web Content Display
portlet. Call the new content *Course Offerings* and enter some content. Notice
that the Publish button is now gone. In its place is a *Submit for Publication*
button. Go ahead and click it.

Next, go to the *Workflow Tasks* in Site Administration and select *My Workflow
Tasks*. You will see the option to Review Content for Sales Goals. It shows
because you are logged in as an Administrator. There is also a Content Approvers
role which is defined by this workflow and anyone in this role can approve
content as well.

To approve the content, you must first take ownership of it. Click on the task.
You should see the screen below.

Taking ownership of, reviewing and approving content is very easy:

1.  Click the *Assign to Me* button. Alternatively, you could assign it to
    someone else in the Content Approvers role or create / update a due date for
    the content's approval.
2.  Once you've assigned it to yourself, buttons allowing you to approve or
    reject the content appear. Click *Approve*.
3.  You're asked to submit a comment. You'd have to do this for either *Approve*
    or *Reject*. Add a comment and click *Save*.
4.  The content is now approved.

In a real world situation, you obviously wouldn't want the person who created
the content to be the one who approves it. Instead, you would have one or more
roles designed for users who will be creating content and you would have
specific users assigned to one or more roles for approving content. Our example
was of a very straightforward workflow, as it has only a single approver. Kaleo
workflow allows you to design workflows that go through as many steps as you
need to conform to your business processes. We look at Kaleo workflow in more
detail in chapter 6.

## Summary  

This chapter has provided an introduction to Liferay site management and web
content management. We've learned how you can use Liferay to create multiple
sites with different membership types. We've seen how easy it is to create and
manage sites and to create and manage pages within a site in Liferay. We've also
seen how easy it is to create and edit web content using Liferay's rich WYSIWYG
editor. This powerful tool enables users who don't have much experience with
HTML and CSS to easily create and style web content of any type that you'd like
to publish on the web. 

Liferay WCM also includes a powerful workflow engine, allowing you to set up
custom publishing rules to fit your organization. You can set up custom approval
processes for different sites as well as for different kinds of content within a
site. We'll examine sites in more detail in chapter 3. We'll also cover some
more advanced web content management tools such as web content structures and
templates, page templates and site templates, staging, and mobile device rules.
