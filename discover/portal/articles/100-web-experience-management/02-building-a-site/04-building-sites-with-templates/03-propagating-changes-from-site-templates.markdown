# Propagating Changes from Site Templates to Sites [](id=propagating-changes-from-site-templates-to-sites)

It's possible for site template administrators to add, update, or delete site
template pages. Changes made to a site template can be propagated to sites whose
page sets are linked to the site template. When you create a site based on a 
site template with the *Enable propagation of changes from the site template* 
box checked this link is created. To configure propagation of changes:

1.  Select the site from the Sites dropdown in the Menu by selecting the
    *Site Selector* button (![Compass](../../../../images/icon-compass.png)). 
    
2.  Navigate to the *Configuration* &rarr; *Site Settings* page and uncheck or 
    recheck the *Enable propagation of changes from the site template* checkbox.
    
In this section, you'll learn about the propagation of changes from site 
templates to sites and discuss the options available to site administrators and 
site template administrators.

## Site Template Page Behavior

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
(![Information](../../../../images/icon-control-menu-information.png)) appears 
in the Control Menu with the following message:

    This page has been changed since the last update from the site template. No
    further updates from the site template will be applied.

![Figure 3: You can click the Information icon to view important information about your site template.](../../../../images/site-template-update-message.png)

## Merging and Reseting Changes

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

![Figure 4: This type of warning is given when there are friendly URL conflicts with site template pages.](../../../../images/friendly-url-propagation-failure.png)

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
