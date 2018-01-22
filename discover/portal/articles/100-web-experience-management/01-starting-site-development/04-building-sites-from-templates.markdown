# Building Sites from Templates [](id=building-sites-from-templates)

Site Templates can be administered from the Control Panel. They let Liferay
administrators create multiple sites with the same default set of pages and
content. Site templates can contain multiple pages, each with its own theme,
layout template, applications, and app configurations. Site templates can also
contain content just like actual sites. This allows administrators to use site
templates to create new sites that are each created with the same default pages,
applications, and content. After they've been created, these sites and their
pages can be modified by site administrators. Using site templates can save site
administrators a lot of work even if each site that was created from a given
site template ends up being very different.

To get started, click on *Site Templates* in the Sites section of the Control
Panel. Here, you can add, manage, or delete site templates. You can also
configure the permissions of site templates. As long as a site is linked to the
site template it was created from, changes to the site template's pages, apps,
and app configurations are propagated to the site. Changes to a site template's
content, however, are not propagated to existing sites that are linked to the
site template. You'll learn about the propagation of changes between site
templates and sites in more detail in the section on site template use cases
below.

To manage the pages of a site template, click on *Site Templates* in the Control
Panel and select the *Actions* icon
(![Actions](../../../images/icon-actions.png)) and then *Manage* for an existing
template. If you open the main Menu on the left side of your screen (if
necessary), the site template is selected in the Site Administration dropdown
menu. You're provided some similar options as a regular site which include
*Navigation*, *Content*, *Configuration*, and *Publishing*. By default, the
Manage Interface begins with the template's *Navigation*. From here, you can add
or remove pages from a site template or select themes and layout templates to
apply to the site template. Click on a specific page's *Options* icon
(![Options](../../../images/icon-options.png)) &rarr; *Edit* from the left menu
if you'd like to select a different theme or layout template for that page, or
manage any of the page's extensive settings. To edit the pages themselves, click
the page link from the left menu. You can add specific applications to each page
of a site template and configure the preferences of each app. Each page can have
any theme, any layout template, and any number of applications, just like a page
of a regular site. As with site pages, you can organize the pages of a site
template into hierarchies. When you create a site using a site template, the
configuration of pages and apps is copied from the template to the site. By
default, all changes made to the site template are automatically copied to sites
based on that template.

+$$$

**Tip:** If you want to publish a piece of web content to many sites and ensure
modifications are applied to all, don't use site template content for that
purpose. Instead, place the content in the global scope and then reference it
from a *Web Content Display* application in each site.

$$$

The Content section offers separate repositories for content related apps
based on your site template. For instance, by clicking *Polls* from the Content
section, you can create a poll question that is only available for that specific
site template. Assets created within your template's Content section can
only be accessed by sites using the template.

The Configuration section includes Application Display Templates and
Mobile Device configuration options for your site template. Also, nested in the
Configuration section is the *Site Template Settings*. This option allows you to
edit the template's name and description while also offering boolean options for
activating your site template and allowing site administrators to modify pages
associated with your template.

The following figure displays the form shown when editing the *Community Site*
template's settings:

![Figure 1: Site templates have several configurable options including the option to allow site administrators to modify pages accociated with the site template.](../../../images/site-template-settings.png)

By default, the following site templates are provided:

- **Intranet Site:** Provides a preconfigured site for an intranet. The Home
  page displays the activities of the members of the site, search, a language
  selector, and a list of the recent content created in the intranet. It also
  provides two additional pages for Documents and Media and external News
  obtained through public feeds.

- **Community Site:** Provides a preconfigured site for building online
  communities. The Home page of a *community site* provides message boards,
  search, a display of a poll and statistics of the activity of community
  members. The site will also be created with a page for a wiki.

Now that you know the basics for creating and managing your site templates,
you'll put your knowledge to the test by completing an example next.

## Site Templates Example [](id=site-templates-example)

Suppose you need to create the following three sites for the Lunar Resort's
internal use: Engineering, Marketing, and Legal. These should be private sites
that are only accessible to members of these respective departments. You could
design each site separately but you can save yourself some work if you create a
site template to use instead.

To create a site template, navigate to the Control Panel and click *Sites*
&rarr; *Site Templates*. Then click the *Add* icon
(![Add Site Template](../../../images/icon-add.png)) and enter a name for your
template: you'll use *Department* for this example. Leave the *Active* and
*Allow site administrators to modify pages associated with this site
template...\* boxes checked. The *Active* box must be checked for your template
to be usable. If your template is still a work in progress, you can uncheck it
to ensure that no one uses it until it's ready. Checking *Allow site
administrators to modify pages associated with this site template...\* allows
site administrators to modify or remove the pages and apps that the template
introduces to their sites--if you want the templates to be completely static,
you should uncheck this. Click *Save* to create your site template.

From the left menu, notice that your site template is now selected from the Site
Administration dropdown. You can now begin editing your site template. For this
example, you want your site template to include four pages. First, create a
*Home* page with the Activities, Announcements, and Calendar apps. Next, create
a *Documents and Media* page with the Documents and Media app. Finally, create a
*Wiki* page with the Wiki and Tag Cloud apps and a *Message Boards* page with
the Message Boards and Tag Cloud apps. The changes you made to your site
template above are completed in real time, so there's no need to navigate back
to the Site Templates page of the Control Panel and select *Save*.

![Figure 2: You can see the name of the site template you're currently editing.](../../../images/editing-site-template.png)

Next, you'll use your site template to create the Engineering, Marketing and
Legal sites. Go to the Control Panel and click on *Sites* &rarr; *Sites*. Then
click the *Add* icon (![Add Site](../../../images/icon-add.png)) &rarr;
*Department*. Enter *Engineering* for the site name and set the Membership Type
to *Private*. Recall that private sites don't appear in the My Sites application
so that regular users won't even know that the Engineering site exists. Also,
the only way users can be added to a private site is via an invitation from a
site administrator. Leave the *Active* selector enabled so that your site can be
used immediately. Select the *Copy as Private Pages* option since the
Engineering site is intended for internal use only. Leave the *Enable
propagation of changes from the site template* box enabled so that the
Engineering site receives updates if the Department site template is modified.
Finally, click *Save* to create your Engineering site.

Repeat these steps to create the Marketing and Legal sites. The new sites have
all the pages and apps you created in the site template. To view the pages of
the new sites, click on *Sites* &rarr; *Sites* in the Control Panel and then
click on *Actions* &rarr; *Go to Private Pages* next to one of your new sites.
Using site templates streamlines the site creation process for administrators,
making it easy to create sites quickly. Now each department of the Lunar Resort
has its own calendar, documents and media library, wiki, and message boards
application. Although the pages and apps of each department's site are the
same, each site will quickly be filled with department-specific information as
users add and share content within the sites. Also, site administrators can add
new pages, apps, and content to their sites, further differentiating each
department's site from the others.

## Propagating Changes from Site Templates to Sites [](id=propagating-changes-from-site-templates-to-sites)

It's possible for site template administrators to add, update, or delete site
template pages. Changes made to a site template can be propagated to sites whose
page sets are linked to the site template. Such a link is created when you
create a site based on a site template and leave the *Enable propagation of
changes from the site template* box checked. To disable or re-enable this link
for a site, select the site from the Sites dropdown in the Menu by selecting the
*Site Selector* button (![Compass](../../../images/icon-compass.png)). Navigate
to the *Configuration* &rarr; *Site Settings* page and uncheck or recheck the
*Enable propagation of changes from the site template* checkbox. In this
section, you'll learn about the propagation of changes from site templates to
sites and discuss the options available to site administrators and site template
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
and refreshes the page, the following Information icon
(![Information](../../../images/icon-control-menu-information.png)) appears in
the Control Menu with the following message:

    This page has been changed since the last update from the site template. No
    further updates from the site template will be applied.

![Figure 3: You can click the Information icon to view important information about your site template.](../../../images/site-template-update-message.png)

If the site administrator clicks the *Reset Changes* button, changes are
propagated from the site template page to the corresponding site page that was
imported from the site template. Clicking the *Reset Changes* button makes two
kinds of updates to a page. First, changes made by site administrators to the
site page are undone. Second, changes made by site template administrators to
the site template page are applied to the site page. Note: clicking the *Reset
Changes* button only resets one page. If multiple site pages have been modified
and you'd like to re-apply the site template pages to them, you'll need to click
the *Reset Changes* button for each page.

Site template administrators can set preferences for apps on site template
pages. When a Liferay administrator creates a site from a site template, the app
preferences are copied from the site template's apps, overriding any default app
preferences. When merging site template and site changes (e.g., when resetting),
app preferences are copied from site template apps to site apps. Only global app
preferences or local app preferences which don't refer to IDs are overwritten.

<!-- Need to review from here down. I need to figure how how to receive the
merge fail error so I can take a screenshot for Figure 4. -Cody -->

In some cases, merging site template and site changes fails. For example, if
pages from a site template cannot be propagated because their friendly URLs are
in conflict, @product@ could try to continuously merge the site changes. Instead
of entering into an infinite loop of merge fails, @product@ stops the merge
after several unsuccessful attempts. @product@, however, doesn't stop there:
your merge is temporarily paused, you're given an indication of the current
merge fail, and then you have the opportunity to fix your merge conflicts. After
you've squared away your conflict, navigate to your site's *Site Administration*
&rarr; *Configuration* &rarr; *Site Settings* and click the *Reset and
Propagate* button.

![Figure 4: This type of warning is given when there are friendly URL conflicts with site template pages.](../../../images/friendly-url-propagation-failure.png)

The *Reset and Propagate* button resets the merge fail count and attempts to
propagate your site changes again. This process gives the Liferay administrator
the opportunity to detect and fix a merge fail, when problems arise. This
helpful process can also be done with page template merges, which follows
similar steps.

Site administrators can also add data to site template applications. For
example, site template administrators can add the Wiki app to a site template
page and use the Wiki to create lots of articles. When a Liferay administrator
creates a site from a site template, data is copied from the site template's
apps to the site's apps. The preferences of the site's apps are updated with the
IDs of the copied data. For example, if a site is created from a site template
that has a Wiki app with lots of wiki articles, the wiki articles are copied
from the site template's scope to the site's scope and the site's Wiki app is
updated with the IDs of the copied wiki articles.

+$$$

**Important:** App data, related resources, and permissions on resources are
only copied from a site template to a site when that site is *first* created
based on the template. No changes made in a a template's portlet data, related
resources, or permissions are propagated to the site after the site is created.
Neither are such changes propagated to a site by the *Reset* or *Reset and
Propagate* features.

$$$

For example, consider a site template administrator who includes a Message
Boards app as part of a site template. They even create Message Board
categories and configures permissions over the actions of the categories. The
first time a site is created based on the site template, the categories (app
data) and related permissions are copied to the site. If the site template
administrator adds, removes, or deletes some categories, however, such changes
*aren't* propagated to the site. 

Now that you've learned how site templates work, you'll learn how to share site
templates. 

## Sharing Site Templates [](id=sharing-site-templates)

If you want to export a site that uses site or page templates to a different
environment (through a LAR file or remote publication), the templates must be
exported and imported manually in advance or the import will fail.

To export a Site using a Site Template, use the following process:

1. Go to the *Control Panel* &rarr; *Sites* &rarr; *Site Templates* menu.

2. Click the *Actions* icon (![Actions](../../../images/icon-actions.png))
   and then *Export* for the site template your site is using. You'll use the
   *Export* screen to obtain a LAR file with the content of the site template.
   Be sure to choose the applications and data you want exported.

3. In your target environment, go to *Control Panel* &rarr; *Sites* &rarr; *Site
   Templates* and create a new site template.

4. Click *Actions* &rarr; *Import* for that site template and upload the LAR
   file containing your site template's content.

Now the site can be exported and imported normally to this new environment. For
more information on exporting/importing content, visit the
[Importing/Exporting Pages and Content](/discover/portal/-/knowledge_base/7-0/importing-exporting-pages-and-content)
article.

In the next section, you'll learn about page templates.
