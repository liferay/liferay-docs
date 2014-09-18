# Using Site Templates [](id=using-site-templates)

Site Templates can be administered from the Control Panel. They allow portal
administrators to create multiple sites with the same default set of pages and
content. Site templates can contain multiple pages, each with its own theme,
layout template, portlets, and portlet configurations. Site templates can also
contain content just like actual sites. This allows administrators to use site
templates to create new sites that are each created with the same default pages,
portlets, and content. After they've been created, these sites and their pages
can be modified by site administrators. Using site templates can save site
administrators a lot of work even if each site that was created from a given
site template ends up being very different.

To get started, click on *Site Templates* in the Sites section of the Control
Panel. Here, you can add, manage, or delete site templates. You can also
configure the permissions of site templates. As long as a site is linked to the
site template it was created from, changes to the site template's pages,
portlets, and portlet configurations are propagated to the site. Changes to a
site template's content, however, are not propagated to existing sites that are
linked to the site template. We discuss the propagation of changes between site
templates and sites in more detail in the section on site templates use cases
below.

To manage the pages of a site template, click on *Site Templates* in the Control
Panel and then click *Actions* &rarr; *Manage*. You're provided a left menu
which contains the *Pages*, *Content*, and *Configuration* sections for each
site. By default, the Manage Interface begins with the template's *Site Pages*.
From here, you can add or remove pages from a site template or select themes and
layout templates to apply to the site template. Click on a specific page if
you'd like to select a different theme or layout template for that page. To edit
the pages themselves, click *Actions* &rarr; *View Pages*. You can add specific
portlets to each page of a site template and configure the preferences of each
portlet. Each page can have any theme, any layout template, and any number of
portlet applications, just like a page of a regular site. As with site pages,
you can organize the pages of a site template into hierarchies. When you create
a site using a site template, the configuration of pages and portlets is copied
from the template to the site. By default, all changes made to the site template
are automatically copied to sites based on that template.

+$$$

**Tip:** If you want to publish a piece of web
content to many sites and ensure modifications are applied to all, don't use
site template content for that purpose. Instead, place the content in the global
scope and then reference it from a *Web Content Display* application in each
site.

$$$

The Content section offers separate repositories for content related portlets
based on your site template. For instance, by clicking *Polls* from the Content
section, you can create a poll question that is only available for that specific
site template. Assets created within your template's Content section can
only be accessed by sites using the template.

Lastly, the Configuration section includes Application Display Template and
Mobile Device configuration options for your site template. Also, nested in the
Configuration section is the *Site Template Settings*. This option allows you to
edit the template's name and description while also offering boolean options for
activating your site template and allowing site administrators to modify pages
associated with your template.

The following figure displays the form shown when editing the *Community Site*
template's settings:

![Figure 3.11: Site templates have several configurable options including the option to allow site administrators to modify pages accociated with the site template.](../../images/01-site-templates.png)

By default, the following site templates are provided:

- **Community Site:** Provides a preconfigured site for building online
  communities. The Home page of a *community site* provides message boards,
  search, a display of a poll and statistics of the activity of community
  members. The site will also be created with a page for a community calendar
  and a page for a wiki.

- **Intranet Site:** Provides a preconfigured site for an intranet. The Home
  page displays the activities of the members of the site, search, a language
  chooser and a list of the recent content created in the intranet. It also
  provides 3 additional pages for *Documents and Media*, *Calendar* and external
  *News* obtained through public feeds.

Now that you know the basics for creating and managing your site templates,
let's put your knowledge to the test by completing an example.

## Site Templates Example 

Suppose we need to create the following three sites for the Lunar Resort's
internal use: Engineering, Marketing, and Legal. These should be private sites
that are only accessible to members of these respective departments. We could
design each site separately but can save ourselves some work if we create a
site template to use instead.

To create a site template, navigate to the Control Panel and click *Site
Templates*. Then click *Add* and enter a name for your template: we'll use
*Department* for our example. Leave the *Active* and *Allow Site Administrators
to Modify the Pages Associated with This Site Template* boxes checked. The
*Active* box must be checked for your template to be usable. If your template is
still a work in progress, you can uncheck it to ensure that no one uses it until
it's ready. Checking *Allow Site Administrators to Modify the Pages Associated
with This Site Template* allows site administrators to modify or remove the
pages and portlets that the template introduces to their sites--if you want the
templates to be completely static, you should uncheck this. Click *Save* to
create your site template.

From the left menu, select the *Pages* section and click *Site Pages*. Then
click on the *View Pages* button to begin adding pages and portlets and
configuring the layouts. When you click this button, the site template opens in
a new browser tab or window. For our example, we would like our site template to
include four pages. First, we'd like a Home page with the Activities,
Announcements, and Calendar portlets. Next, we'd like a Documents and Media page
with the Documents and Media portlet. Finally, we should create a Wiki page with
the Wiki and Tag Cloud portlets and a Message Boards page with the Message
Boards and Tag Cloud portlets. When you're done creating and configuring the
pages of your site template, just close the browser tab or window that opened
when you clicked *View Pages*. Changes to site templates are automatically saved
as you make them, so you don't need to return to the Site Templates page of the
Control Panel and select *Save*.

![Figure 3.12: You can see the name of the site template you're currently editing.](../../images/editing-site-template.png)

Next, let's use our site template to create our Engineering, Marketing and Legal
sites. Go to the Control Panel and click on *Sites*. Then click *Add* &rarr;
*Department*. Enter *Engineering* for the site name and set the Membership Type
to *Private*. Recall that private sites don't appear in the My Sites portlet so
that regular portal users won't even know that the Engineering site exists.
Also, the only way users can be added to a private site is via an invitation
from a site administrator. Leave the *Active* box checked so that your site can
be used immediately. Select the *Copy as Private Pages* option since our
Engineering site is intended for internal use only. Leave the *Enable
propagation of changes from the site template* box checked so that the
Engineering site receives updates if the Department site template is modified.
Finally, click *Save* to create your Engineering site.

Repeat these steps to create the Marketing and Legal sites. The new sites have
all the pages and portlets you created in the site template. To view the pages
of the new sites, click on *Sites* in the Control Panel and then click on
*Actions* &rarr; *Go to Private Pages* next to one of your new sites. Using
site templates streamlines the site creation process for administrators, making
it easy to create sites quickly. Now each department of the Lunar Resort has
its own calendar, documents and media library, wiki, and message boards
application.  Although the pages and portlets of each department's site are the
same, each site will quickly be filled with department-specific information as
users add and share content within the sites. Also, site administrators can add
new pages, portlets, and content to their sites, further differentiating each
department's site from the others.

## Propagating Changes from Site Templates to Sites 

It's possible for site template administrators to add, update, or delete site
template pages. Changes made to a site template can be propagated to sites whose
page sets are linked to the site template. Such a link is created when you
create a site based on a site template and leave the *Enable propagation of
changes from the site template* box checked. To disable or re-enable this link
for a site, select the site from *My Sites* in the Dockbar. Navigate to *Admin*
&rarr; *Configuration* to open the *Site Settings* page and uncheck or recheck
the *Enable propagation of changes from the site template* checkbox. In this
section, we explain the propagation of changes from site templates to sites and
discuss the options available to site administrators and site template
administrators.

If a site's page set has been created from a site template and the propagation
of changes from the site template is enabled, site administrators can add new
pages but cannot remove or reorder the pages imported from the site template.
If a site has both pages imported from a site template and custom site pages,
the site template pages always appear first; custom pages added by site
administrators appear after the site template pages. Only site template
administrators can remove, reorder, or add site template pages. Site
administrators can add or remove custom site pages. They can also reorder custom
site pages as long as they're all positioned after the site template pages. Site
template administrators cannot add, remove, or reorder custom site pages.

If a site administrator changes a page that was imported from a site template
and refreshes the page, the following message appears:

        This page has been changed since the last update from the site template.
        No further updates from the site template will be applied. Click *Reset*
        to overwrite the changes and receive updates from the site template.

If the site administrator clicks the *Reset* button, changes are propagated from
the site template page to the corresponding site page that was imported from the
site template. Clicking the *Reset* button makes two kinds of updates to a page.
First, changes made by site administrators to the site page are undone. Second,
changes made by site template administrators to the site template page are
applied to the site page. Note: clicking the *Reset* button only resets one
page. If multiple site pages have been modified and you'd like to re-apply the
site template pages to them, you'll need to click the *Reset* button for each
page.

Site template administrators can set preferences for portlets on site template
pages. When a portal administrator creates a site from a site template, the
portlet preferences are copied from the site template's portlets, overriding any
default portlet preferences. When merging site template and site changes, e.g.,
when resetting, portlet preferences are copied from site template portlets to
site portlets. Only global portlet preferences or local portlet preferences
which don't refer to IDs are overwritten.

In some cases, merging site template and site changes fails. For example, if
pages from a site template cannot be propagated because their friendly URLs are
in conflict, the portal could try to continuously merge the site changes.
Instead of entering into an infinite loop of merge fails, Liferay stops the
merge after several unsuccessful attempts. However, Liferay doesn't stop there:
your merge is temporarily paused, you're given an indication of the current
merge fail, and then you have the  opportunity to fix your merge conflicts.
After you've squared away your conflict, navigate to *Site Administration*
&rarr; *Configuration* &rarr; *Site Settings* and click the *Reset and
Propagate* button.

![Figure 3.13: You can reset and propagate the merge fail count by navigationg to *Site Settings*.](../../images/reset-and-propagate.png)

The *Reset and Propagate* button resets the merge fail count and attempts to
propagate your site changes again. This process gives the portal administrator
the opportunity to detect and fix a merge fail, when problems arise. This
helpful process can also be done with page template merges, which follows
similar steps.

Site administrators can also add data to site template portlets. For example,
site template administrators can add the Wiki portlet to a site template page
and use the Wiki to create lots of articles. When a portal administrator creates
a site from a site template, data is copied from the site template's portlets to
the site's portlets. The preferences of the site's portlets are updated with the
IDs of the copied data. For example, if a site is created from a site template
that has a Wiki portlet with lots of wiki articles, the wiki articles are copied
from the site template's scope to the site's scope and site's Wiki portlet is
updated with the IDs of the copied wiki articles. Portlet data is only copied
from a site template to a site when the site is first created; data is not
copied during a site reset.

Now that we've learned how site templates work, let's discuss how to use page
templates. 
