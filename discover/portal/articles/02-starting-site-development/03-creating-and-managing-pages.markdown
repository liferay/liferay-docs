# Creating and Managing Pages

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

![Figure 2.5: The *Site Pages* interface allows you to edit your site pages as a whole.](../../images/04-managing-site-pages.png)

To add new pages to your site, click the *Add* icon from the left palette and
select the *Page* tab. This is the *Page* interface, which offers a plethora of
options for your new page including name, site layout, and site template.

To manage the specific page of the site you've navigated to, click the *Edit*
icon from the left palette. This will only edit the specific page you're
currently on.

![Figure 2.6: The *Edit Page* interface allows you to edit the current page you're on.](../../images/04-web-content-managing-single-page.png)

*Site Pages* is an interface to view existing pages, create new pages, view
pages and export or import pages using Liferay Archive (LAR) files. Note that
you can switch between managing a set of pages and managing a single page using
the left-hand side navigation menu. Click on *Public Pages* or *Private Pages*
to manage the group or click on an individual page to manage just that one.
Switching views like this changes the list of available tabs to the right. By
default, liferay.com, which we renamed to lunar-resort.com, contains a single
public page called *Welcome*.

Liferay's page groups are always associated with sites. Even users' personal
pages are part of their personal sites. All pages belong to one of two types of
page sets: public pages and private pages. By default, public pages are
accessible to anyone, even non-logged in users (guests). Private pages are
accessible only to users who are members of the site which owns the pages. This
means the private pages of an organization's site would only be viewable by
site members and members of the organization. 

Regardless of whether the pages are public or private, Liferay uses the same
interface to manage them. Let's look at this interface more closely. 

### More Page Management Tools [](id=more-page-management-tools)

From the Site Pages interface in Site Administration, you can add a page to the
site by clicking the *Add Page* button. Because *Public Pages* is selected on
the left, clicking *Add Page* here adds a top level page next to the Welcome
page. You can, however, nest pages as deeply as you like. To create a sub-page
under the Welcome page, select the *Welcome* page first and then create your
page. If you later decide you don't like the order of your pages, you can drag
and drop them in the list to put them in whatever order you want. Let's go ahead
and add another top level page and name it *Community*. We'll use this page for
the Recent Bloggers and Wiki portlets.

![Figure 2.7: You can add a page to your site by giving it a name, page template, and page type.](../../images/04-web-content-add-page.png)

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

#### Backing up and Restoring Pages [](id=backing-up-and-restoring-pages)

Next to the *Add Page* button on the Site Pages screen, there are two buttons
labeled *Export* and *Import*. The Export button allows you to export the your
site's data as a single file, called a LAR (Liferay Archive) file. When
importing data into a site, it's best to use a newly created site to avoid
potential conflicts between the existing site data and the data about to be
imported. When exporting site data, you can specify exactly what data should be
included in the LAR:

- Site pages (you can select exactly which ones)
- Page settings
- Theme
- Theme settings
- Logo
- Application configurations
- Application content
- Archived setups
- User preferences

Once you've created a LAR file, you can import it into a site on another Liferay
server. The data included in the LAR file, including all the site pages, will
be imported into the site. Exporting and importing LARs is a great way to take
content from a site in one environment (say, a development or QA environment)
and move it all in one shot to a site on another server. You can use LARs to
import data onto production servers, but you should not make this a regular
occurrence. If you want to regularly move pages from one server to another, you
should use Liferay's staging environment, which we discuss in the
[Advanced Web Content Management](/discover/portal/-/knowledge_base/6-2/advanced-web-content-management)
chapter.

LARs can be a good way to back up your site's content. You can export them to a
specific location on your server which is backed up. If you ever have to restore
your site, all you need to do is import the latest LAR file. However, please be
careful! If there's any content that exists both in the LAR and in the site
that's importing the data, there may be a conflict, and data could be
corrupted.  If you'd like to restore a Liferay site using a LAR file, it's best
to delete the site entirely, create a new site with the same name as the old
one (i.e., re-create the site), and then import the LAR file into the new site.
This way, there's no chance for there to be a data conflict.

Liferay can handle some kinds of naming collisions when importing a LAR file
into a site. For example, suppose you're importing a LAR file into a site and
the LAR file has a page with a certain friendly URL. If an existing page in the
site has the same friendly URL there will be a collision. Liferay resolves the
collision by adding a number to the end of the friendly URL and incrementing
until there's no collision. This behavior takes place for friendly URL
translations as well. Similarly, if importing a LAR into a site causes a
category name collision, Liferay renames the imported categories.

+$$$

**Note:** LAR files are version dependent. You can't import a LAR file that was
exported from one version of Liferay into a Liferay server that's running a
different version of Liferay. Also, note that periodically exporting LARs is
*not* a complete backup solution; please refer to the
[Backing up a Liferay Installation](/discover/deployment/-/knowledge_base/6-2/backing-up-a-liferay-installation)
section for information on backing up Liferay.

$$$

Let's be good administrators and export a LAR file for backup purposes. Click on
the *Export* button and then name the file `lunarresortv1.lar`. Use the check
boxes to determine what you'd like to export. For this initial export, select
everything. Note that if you select one of the *Choose* radio selectors or
*Change* links, you're given checkboxes for options to choose. The applications'
content can also be selected for export, including the Documents and Media
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

#### Customizing the Look and Feel of Site Pages [](id=customizing-the-look-and-feel-of-site-pages)

When you open *Site Pages* from within Site Administration, it defaults to the
Look and Feel tab. On this tab, you're presented with an interface that allows
you to choose a theme for the current site. Themes can transform the entire look
of the portal. They are created by developers and are easily installed using the
Liferay Marketplace. Since we don't have any themes beyond the default one
installed yet, we'll use the default theme for our pages.

![Figure 2.8: The Look and Feel interface allows you to choose a theme for the current site.](../../images/04-look-and-feel.png)

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
[Displaying Site Pages to Mobile Devices](/discover/portal/-/knowledge_base/6-2/displaying-site-pages-to-mobile-devices)
section. You can enable the WAP functionality for your portal's Look and Feel
section by opening/creating your `portal-ext.properties` file in your
`${LIFERAY_HOME}` directory and setting
`mobile.device.styling.wap.enabled=true`. WAP functionality will be completely
removed from Liferay in the next release.

The *CSS* section allows you to enter custom CSS that will also be served up by
your theme. In this way, you can tweak a theme in real time by adding new styles
or overriding existing ones. 

The next option configures the logo that appears for your site. 

#### Using a Custom Logo for a Site [](id=using-a-custom-logo-for-a-site)

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
the name display. 

#### JavaScript [](id=javascript)

If you click on *JavaScript* from the Site Pages interface for a page set
(either Public Pages or Private Pages), you'll find a window where you can enter
JavaScript code the will be executed at the bottom of every page in the site.
If your site's theme uses JavaScript (as is usually the case), it's best to add
custom JavaScript code to the theme and *not* in this window. This way, all of
your site's JavaScript code remains in one place.

Using the JavaScript window of your site's Site Pages interface may be useful if
your site's theme does *not* use JavaScript. In this case, the JavaScript window
of your site's Site Pages interface will contain *all* of your site's JavaScript
and you can add some dynamic features to your site's pages. 

Next, let's look at an advanced feature of the Site Pages interface: merging the
current site's pages with the pages of the default site.

#### Advanced [](id=advanced)

If you click on *Advanced* from the Site Pages interface for a public page set,
you'll find an option to merge the public pages of your portal's default site
with the public pages of the current site. If you enable this option, the pages
of the default site appear in the current site's navigation bar, along with the
current site's pages. Also, the pages of the current site appear in the
navigation bar of the default site, along with the default site's pages. This
"merging" of pages only affects the list of pages in the default site's and the
current site's *navigation bars*. This allows users to more easily navigate
from the current site to the default site, and vice versa. This option can be
enabled for the public pages of both personal sites and regular sites.

Note that this "merging" of pages is not a "hard merge". For example, suppose
that the site administrators of twenty different sites on your portal all
enabled the *Merge default site's public pages* option. Would the pages of all
these different sites be merged into each site's navigation bar? No, that would
make a mess! Instead, the portal keeps track of the current `scopeGroupId` (the
ID of the current site) and the previous `scopeGroupId` (the ID of the
previously visited site). If the *Merge default site's public pages* option is
enabled for either the current site or the previous site, the pages of the
default site are merged in the pages of the other site.

For example, suppose that your portal has three sites: the default site, site A,
and site B. All three sites have some public pages. Site A has the *Merge
default site's public pages* option enabled, site B does not. When a user first
logs in, he's directed to the default site. The `scopeGroupId` is that of the
default site and there is no previous `scopeGroupId`, so no additional pages
appear in the default site's navigation bar. Then suppose the user navigates to
site A. Site A has the *Merge default site's public pages* option enabled, so
the default site's pages are added to site A's navigation bar. Now if the user
goes back to the default site, site A becomes the previous site so site A's
pages are added to the default site's navigation bar. If the user navigates to
site B, no additional pages appear in site B's navigation bar because site B
does not have the *Merge default site's public pages* option enabled. And if the
user navigates back to the default site, site B becomes the previous site, and,
again, since site B does not have the *Merge default site's public pages* option
enabled, no additional pages are added to the default site's navigation menu.

Next, let's examine how to configure individual pages.

### Changing Options for Individual Pages [](id=changing-options-for-individual-pages)

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

+$$$

**Note:** In previous versions of Liferay, it was possible that a single page
could be indexed multiple times. In Liferay 6.1, all URLs that direct to the
same page will only create one entry in the index. Previously, the simple URL
*http\://www.lunar-resort.com/web/guest/blog/-/blogs/themoon* and different
versions of the URL which provided additional information about the referring
page had different entries in the index. As of Liferay 6.1, each asset (web
content article, blog entry, etc.) has a unique URL. From the search engine's
point of view, this will make your pages rank higher since any references to
variations of a specific URL will all be considered references to the same
page.] 

$$$

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

+$$$

**Note:** Usually, you don't want the web content display portlet that you're
using to embed a portlet to be visible. To make the web content display portlet
invisible, click on the gear icon of the web content display portlet, select
*Look and Feel*, set *Show Borders* to *No*, and click *Save*. Once you've
refreshed the page, only the embedded portlet will be visible.

$$$

**Customization Settings:** lets you mark specific sections of the page you want
users to be able to customize.

Next, we'll run practice modifying page layouts!

### Modifying Page Layouts [](id=modifying-page-layouts)

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
case, use the Nested Portlets portlet to embed a layout inside another layout.
This portlet is a container for other portlets. It lets you select from any of
the layouts installed in Liferay, just like the layouts for a page. This gives
you virtually unlimited options for laying out your pages.

The next option we'll explore is page customizations. 

### Page Customizations [](id=page-customizations)

With page customizations, any user with the appropriate permissions can create
personalized versions of any public page. Before users can create personalized
versions of pages, customizations must first be enabled by an administrator.
Administrators can activate or deactivate customizations for any row or column
on any page. When users customize a page, they have the option to use either
their version or the default version of a page. Users can't see alternate
versions of pages other than their own.

![Figure 2.9: During page customization, individual columns change colors to indicate whether they are selected or not.](../../images/04-web-content-personal-customization.png)

To activate page customizations, click the *Edit Page* button from the left
palette and select the *Customization Settings* tab. Then select *Show
Customizable Sections* to view and modify sections on your page.

When an administrator activates page customizations for a page, any portlets
that are in a *Customizable* row or column can be moved around the page or
removed from the page. Users can add new portlets of their own choosing to these
columns of the page and can also customize portlet configurations. If at any
time users determine they don't like their customizations, they can click *Reset
My Customizations* to revert their pages back to the default. For more
information about page customizations, please refer to the
[Page Customizations](/discover/portal/-/knowledge_base/6-2/page-customizations)
section.

### Page Templates and Site Templates [](id=page-templates-and-site-templates)

*Page Templates* and *Site Templates* are invaluable tools for building similar
pages on larger portals. As you continue to add pages to sites in your portal,
you'll notice repeatable patterns in the designs of those pages. Page templates
enable you to preconfigure a single page and then apply it to any new page you
create. Site templates allow you to do the same thing but on the scale of a
site--if you have multiple sites that use a similar structure of pages, you can
create a single site template and use it to create as many sites as desired. For
more information on page templates and site templates, see the
[Using Page Templates](/discover/portal/-/knowledge_base/6-2/using-page-templates)
and
[Using Site Templates](/discover/portal/-/knowledge_base/6-2/using-site-templates)
sections. 

## Creating Pages [](id=creating-pages)

There are a lot of other things you can do beyond placing portlets on a page. So
let's start working on the Lunar Resort site. You can do this by going up to the
Dockbar and clicking *My Sites* &rarr; *Lunar Resort*.

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

### Adding Portlets to a Page [](id=adding-portlets-to-a-page)

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
Collaboration page of the Lunar Resort site.

1. From the left palette, select *Add* &rarr; *Applications*.
2. In the menu that appears, expand the *Collaboration* category.
3. Drag the *Blogs Aggregator* portlet off the Add Application window onto the
   right column of our page.
4. Next, drag the *Wiki* portlet to the *left column*.

See how easy it is to add applications to your pages? We've added the Wiki
portlet and Blogs Aggregator portlet to the Community page. 

![Figure 2.12: Yeah, we're showoffs. But as you can see, your page layout options are virtually limitless.](../../images/04-web-content-portlet-layout.png)

It's easy to make your pages look exactly the way you want them to. If the
default layout options provided aren't enough, you can even develop your own.
To find more information about developing custom layout templates, please refer
to the [Creating a Layout Template Project in the Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/creating-a-layout-template-project-in-the-plugins-sdk)
tutorial. 

### Page Permissions [](id=page-permissions)

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

![Figure 2.13: The Permissions offer a plethora of options for each role.](../../images/04-web-content-page-permissions.png)

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
