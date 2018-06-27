# Propagating Changes from Site Templates to Sites [](id=propagating-changes-from-Site-templates-to-Sites)

Site Template administrators can add, update, or delete Site Template pages.
Changes made to a Site Template can be propagated to Sites whose page sets are
linked to the Site Template. When you create a Site based on a Site Template
with the *Enable propagation of changes from the Site template* box checked this
link is created. To configure propagation of changes:

1.  Select the Site from the Sites dropdown in the Menu by selecting the
    *Site Selector* button (![Compass](../../../../images/icon-compass.png)). 
 
2.  Navigate to the *Configuration* &rarr; *Site Settings* page and uncheck or 
    recheck the *Enable propagation of changes from the Site template* checkbox.
 
In this section, you'll learn about the propagation of changes from Site 
templates to Sites and discuss the options available to Site administrators and 
Site template administrators.

## Site Template Page Behavior [](id=site-template-page-behavior)

If a Site's page set has been created from a Site template and the propagation
of changes from the Site template is enabled, Site administrators can add new
pages but cannot remove or reorder the pages imported from the Site Template.
If a Site has both pages imported from a Site template and custom Site pages,
the Site Template pages always appear first; custom pages added by Site
administrators appear after the Site template pages. Only Site template
administrators can remove, reorder, or add Site template pages. Site
administrators can add or remove custom Site pages. They can also reorder custom
Site pages as long as they're all positioned after the Site template pages. Site
template administrators cannot add, remove, or reorder custom Site pages.

If a Site administrator changes a page that was imported from a Site Template
and refreshes the page, the following Information icon
(![Information](../../../../images/icon-control-menu-information.png)) appears 
in the Control Menu with the following message:

    This page has been changed since the last update from the Site template. No
    further updates from the Site template will be applied.

![Figure 1: You can click the Information icon to view important information about your Site template.](../../../../images/site-template-update-message.png)

## Merging and Reseting Changes [](id=merging-and-reseting-changes)

If the Site administrator clicks the *Reset Changes* button, changes are
propagated from the Site template page to the corresponding Site page that was
imported from the Site template. Clicking the *Reset Changes* button makes two
kinds of updates to a page. First, changes made by Site administrators to the
Site page are undone. Second, changes made by Site template administrators to
the Site template page are applied to the Site page. Note: clicking the *Reset
Changes* button only resets one page. If multiple Site pages have been modified
and you'd like to re-apply the Site template pages to them, you'll need to click
the *Reset Changes* button for each page.

Site template administrators can set preferences for apps on Site template
pages. When a Liferay administrator creates a Site from a Site template, the app
preferences are copied from the Site template's apps, overriding any default app
preferences. When merging Site template and Site changes (e.g., when resetting),
app preferences are copied from Site template apps to Site apps. Only global app
preferences or local app preferences which don't refer to IDs are overwritten.

In some cases, merging Site template and Site changes fails. For example, if
pages from a Site template cannot be propagated because their friendly URLs are
in conflict, @product@ could try to continuously merge the Site changes. Instead
of entering into an infinite loop of merge fails, @product@ stops the merge
after several unsuccessful attempts. @product@, however, doesn't stop there:
your merge is temporarily paused, you're given an indication of the current
merge fail, and then you have the opportunity to fix your merge conflicts. After
you've squared away your conflict, navigate to your Site's *Site Administration*
&rarr; *Configuration* &rarr; *Site Settings* and click the *Reset and
Propagate* button.

![Figure 2: This type of warning is given when there are friendly URL conflicts with Site template pages.](../../../../images/friendly-url-propagation-failure.png)

The *Reset and Propagate* button resets the merge fail count and attempts to
propagate your Site changes again. This process gives you the opportunity to
detect and fix a merge fail when problems arise. This helpful process can also
be done with page template merges, which follows similar steps.

Site administrators can also add data to Site template applications. For
example, Site template administrators can add the Wiki app to a Site template
page and use the Wiki to create lots of articles. When a Liferay administrator
creates a Site from a Site template, data is copied from the Site template's
apps to the Site's apps. The preferences of the Site's apps are updated with the
IDs of the copied data. For example, if a Site is created from a Site template
that has a Wiki app with lots of wiki articles, the wiki articles are copied
from the Site template's scope to the Site's scope and the Site's Wiki app is
updated with the IDs of the copied wiki articles.

+$$$

**Important:** App data, related resources, and permissions on resources are
only copied from a Site template to a Site when that Site is *first* created
based on the template. No changes made in a a template's portlet data, related
resources, or permissions are propagated to the Site after the Site is created.
Neither are such changes propagated to a Site by the *Reset* or *Reset and
Propagate* features.

$$$

For example, consider a Site template administrator who includes a Message
Boards app as part of a Site template. They even create Message Board
categories and configures permissions over the actions of the categories. The
first time a Site is created based on the Site template, the categories (app
data) and related permissions are copied to the Site. If the Site template
administrator adds, removes, or deletes some categories, however, such changes
*aren't* propagated to the Site. 

Now that you've learned how Site templates work, you'll learn how to share Site
templates. 
