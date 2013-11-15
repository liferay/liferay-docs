# Liferay Portal 6.2 Release Notes

The Liferay Portal 6.2 release is a major release, containing many additional
features over the previous release. Liferay recommends that all users
upgrade to this release so they can take advantage of these features. This
release addresses several usability issues, provides enhancements to existing
functionality, and gives developers an easier way to work with mobile clients.
What follows is a summarized list of the important enhancement highlights. For a
full list, please see the release notes document in our issue tracker
[here](https://issues.liferay.com/secure/StructureBoard.jspa?s=155). 

## Mobile Devices

![When building pages, Liferay Portal 6.2 provides an always-available preview.](images/device-preview.png)

Mobile devices are first-class citizens in Liferay Portal 6.2. Site
administrators have easy access on every page to preview what their pages look
like on different screen sizes. Layouts are responsive, and out of the box
applications convert app menus to drop down menus on small screens. 

![On the desktop, menus take advantage of available screen real estate.](images/message-boards-desktop.png)

![On mobile devices, menus collapse to make way for content.](images/message-boards-mobile.png)

Mobile Device Rules now make it much easier to set up Mobile Device Families to
detect classifications of devices to present the most optimized view for that
device type. It's much easier to set up classification rules for mobile device
families in Liferay Portal 6.2. 

## User Experience

The default theme has been completely redesigned with usability in mind. In
addition to the enhancements mentioned above for mobile devices, the underlying
framework is now based on Bootstrap. For front-end developers, this makes
Liferay Portal much easier to theme. Developers can take advantage of a
large library of existing Bootstrap-based themes and apply them to Liferay
Portal. 

User interface items have been cleaned up and standardized. Site administration
has been divided into four semantic sections. A site switcher is available in a
standard place on the UI, so you never have to go looking for it. Pages can be
edited in context. The list of UI enhancements is so big that we'd bore you to
death if we listed it all, so we highly recommend you check it out for yourself. 

![Some of the many UI improvements are available while administering sites.](images/ui-improvements.png)

Liferay Portal's administration interface has undergone a complete re-think and
overhaul. Listening to user feedback, we realized that many users were
unnecessarily disabling the Dockbar, so we made it more unobtrusive, and we
separated portal administration functions from site administration functions.
Edit controls for sites now run down the side of a page, and items in the
Dockbar only appear if a user has access to them. This reduces the size of the
Dockbar and makes it show only information that's pertinent to the current user. 

![The new Dockbar is small and shows only necessary information for the current user.](images/new-dockbar.png)

As you can see, the user's personal account is kept separate from the Control
Panel, and sites can be administered without access to the Control Panel. Users'
personal sites' public and private pages have been renamed to My Profile and My
Dashboard, respectively, and they appear under the user's name. This sets a
user's personal area off from the rest of the sites he or she has access to. 

## Recycle Bin

Liferay Portal 6.2 now has a Recycle Bin where deleted content goes. All the
relevant out of the box applications have been enhanced to use the Recycle Bin,
and there is a new API that allows developers to enable the Recycle Bin in their
applications. 

![The Recycle Bin can support any Liferay asset.](images/recycle-bin-overview.png)

By default, items in the Recycle Bin are permanently deleted after 30 days, but
this is fully configurable on a per-site basis through the site settings. 

## Application Display Templates

Users frequently wanted to customize the way a built-in application looked, and
Liferay Portal has always offered that through hook or ext plugins. Now it's
even easier to do that with Application Display Templates. These are simple
FreeMarker templates that site administrators can enter right from the site
administration screen. Nothing needs to be deployed, and the templates can be
applied immediately. 

![Application Display Templates let you customize built-in applications quickly.](images/adts.png)

Once a template is written, it is applied through a particular application's
*Configuration* menu. 

## Localization Improvements

Friendly URLs are now location-aware, and can be defined on a per-language
basis. In addition to this, a default language can be set for each site. 

## Page Management

In the past, creating and managing pages and their content was done from
different screens. Liferay Portal 6.2 pulls all that functionality together into
a single panel on the left side of the screen. This is an incredible convenience
for site administrators as they build their pages. 

![The side panel lets you create your site without ever leaving it.](images/side-panel.png)

The panel's Content pane has a search facility that makes finding content to add
to your site fairly effortless. You can create content to add to your page right
from the side panel. It's also easy to find existing content to add to your
pages: it's all conveniently listed for you, complete with previews. Once you've
found your content, a single click places it on the page. 

## Staging

Staging received a major enhancement in Liferay Portal 6.1, and in version 6.2,
it continues to be refined. During updates from staging to production, sometimes
inconsistencies can occur, particularly if someone has already changed content
in production without staging it first. Liferay Portal 6.2 now validates staging
updates and warns of potential inconsistencies. To further prevent this problem,
you can now disable live changes when remote staging is enabled. 

![Staging has received important enhancements in Liferay Portal 6.2.](images/web-content-staging-publish.png)

Instead of a user name and password combination, you can now set up a pre-shared
key between your staging server and your production server, which is more
secure. 

## Site Membership Management

Liferay 6.2 introduces a new framework for automatic site memberships. This lets
you disable manual site membership management and, out of the box, restrict
memberships only to those who are members of a parent site. Developers can use
this framework to create any kind of site membership rules they want. 

## Site Hierarchies

Many Liferay Portal users wanted a way to group related sites together. With
version 6.2, you now can: site hierarchies enable you to create related groups
of sites and organize them the way you want. 

![Sites can be organized the way they make sense in the real world.](images/site-hierarchies.png)

You can organize sites to unlimited levels of depth, and the Site Membership
Management framework mentioned above makes it easy for you to make sure only the
proper users have access to the relevant parts of the hierarchy. 

![Sites can be set up to share content.](images/content-sharing.png)

Site hierarchies give you more than just a way to organize sites. Sites that are
part of a hierarchy can share content inside the hierarchy. This prevents you
from unnecessarily having to copy content from one site to another, just to
publish the same thing on two sites. Sites in hierarchies can also take
advantage of navigation up and down the hierarchy, making it easier for users
to find relevant content on all sites in the hierarchy. 

## Web Content Management

It's much easier to organize web content than it ever was before. Liferay Portal
6.2 introduces web content folders. You can now create folders and sub-folders
for web content the same way you can create them for Documents and Media. 

![Organize your content in folders with Liferay Portal 6.2.](images/web-content-folders.png)

As you can see, the web content interface has been redesigned, inspired by the
Documents and Media interface. But there's more than just a new, pretty face. 

Liferay Portal 6.2 includes a new template editor, complete with syntax
highlighting, a palette with available variables that can be used in templates,
and autocomplete. 

![The template editor is a development environment for web content templates.](images/template-editor.png)

This template editor is available not only in web content, but also in
Application Display Templates and Dynamic Data lists. 

## Asset Publisher

The Asset Publisher has received numerous improvements. Its performance has been
improved significantly, as now it uses the search index (rather than database
queries) to return entries. Users can subscribe to any list of content, and its
UI has been simplified. 

## Document Management

Documents and Media received some small refinements in Liferay Portal 6.2.
Drag and Drop from your operating system to your browser is now supported. 

![Drag and drop works on all operating systems, even Linux.](images/docs-media-drag-drop.png)

In addition to this, users can now subscribe to be notified of changes to
folders or documents stored in the Docs and Media library. 

## New Calendar Application

The Calendar application has been completely redesigned and replaced in Liferay
Portal 6.2. It is now a plugin, which means you can undeploy it if you aren't
using the calendar. It now has a much more modern UI, can display multiple,
sharable calendars, and supports resource reservations. 

![The new calendar's improved UI lets you drag events around to change them.](images/new-calendar-event-invitations.png)

In addition to this, calendars can be shared via RSS feeds, so you can make a
calendar available to users outside of Liferay Portal. 

## Platform Improvements

In addition to these visible improvements, Liferay Portal 6.2 has received
numerous improvements under the hood. The first version of our OSGi container is
available in Liferay Portal 6.2. Users can deploy OSGi bundles into Liferay and
can develop plugins as OSGi bundles. 

![OSGi bundles coexist with regular Liferay plugins seamlessly.](images/osgi.png)

Liferay Portal 6.2 also serves as an OAuth server. This lets you use your
Liferay Portal installation as an authentication service. Your users can
sign in to multiple websites with their credentials from your site. 

For larger deployments, Liferay Portal 6.2 introduces portlet sandboxing. This
lets you decide to run certain applications in their own JVMs. You can use this
feature to make your portal more resilient: if you have some unstable
applications, place them in their own JVMs, and if they crash, they can't bring
down your portal. 

Liferay Portal 6.2 also includes integration hooks for real-time analytics
software. This lets you easily profile your server while it's running in order
to debug issues you may be having with a custom application. 

Liferay Portal 6.2 is a major release that incorporates many enhancements. These
enhancements make upgrading your site to the latest release a worthwhile
endeavor, and we highly recommend that you upgrade your site to this latest
release. 
