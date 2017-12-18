# Enabling Local Live Staging

Site administrators can enable Staging for a site by navigating to the *Site
Administration* &rarr; *Publishing* menu and selecting *Staging*. A new page
loads where you can select the staging type, either *Local Live* or *Remote
Live*, after which additional options appear. Staging allows changes to be made
in a staging environment so that work can be reviewed, possibly using a
workflow, before it's published to a live site. Enabling Local Live staging is
easy. Just select *Local Live* and decide whether you'd like to enable page
versioning and what app data will be included on the staging site.

You can enable page versioning on a site's public pages, private pages, both, or
neither. Page versioning allows you to work in parallel on different versions of
pages and maintains a history of all page modifications. You'll learn about page
versioning in more detail below.

To get some hands-on experience with enabling Local Live staging, you can
complete a brief example which creates a Local Live staging environment for the
Lunar Resort home page. Before beginning, you'll want to add a new page.
Navigate to the Pages menu in the Lunar Resort's Site Administration menu and
add a new page named *News and Events*. Next, click *News and Events* to view
the page. Then add the Alerts and Announcements apps to the News and Events
page. Navigate to the Staging menu under Publishing, select *Local Live*, and
click *Save*. You've officially begun the staging process.

When you activate Local Live staging, @product@ creates a clone of your site.
This clone becomes the staging environment. Because of this, it is recommended
to only activate staging on new, clean sites. Having a few pages and some apps
(like those of the example site you created) is no big deal. If you've already
created a large amount of content, however, enabling staging can take a lot of
time since it's a resource intensive operation. Also, if you intend to use page
versioning to track the history of updates to your site, it's recommended that
you enable it as early as possible, *before* your site has many pages and lots
of content. Your site's update history won't be saved until you enable page
versioning. Page versioning requires staging (either Local Live or Remote Live)
to be enabled.

If you ever need to turn off the staging environment, return back to *Staging*
from the Publishing dropdown. The processes you've created are displayed by
default. Navigate to the *Options* icon
(![Options](../../../images/icon-options.png)) from the upper right corner of
the page and select *Staging Configuration*. Select the *None* radio button to
turn Local Live staging off. Please note that this operation removes the staging
environment altogether, so all content that was not published to your live site
will be lost!

Great! Now your ready to use Local Live Staging.
