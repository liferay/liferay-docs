# Enabling Local Live Staging [](id=enabling-local-live-staging)

Staging allows changes to be made in a staging environment so that work can be
reviewed, possibly using a workflow, before it's published to a live site. With
Local Live staging, both your staging environment and your live environment are
hosted on the same server. When Local Live staging is enabled for a site, a
clone of the site is created containing copies of all of the site's existing
pages. Typically, this means the staging and live environments share the same
JVM, database, portlet data (depending on which portlets are selected when
staging is enabled), and setup configurations, such as the properties set in the
`portal-ext.properties` file. The cloned site becomes the staging environment
and the original site becomes the live environment.

Site administrators can enable local staging for a site by navigating to the
*Publishing &rarr; *Staging* menu. To get some hands-on experience with enabling
Local Live staging, you can complete a brief example which creates a Local Live
staging environment for your site. 

1.  Navigate to the Product Menu (left side) and select *Publishing* &rarr;
    *Staging*.

2.  Select *Local Live*. You also have the option to enable page versioning and
    select staged content.

    You can enable page versioning on a site's public pages, private pages, both, or
    neither. Page versioning allows you to work in parallel on different versions of
    pages and maintains a history of all page modifications. You can also choose
    content for the staging environment to manage on the Staging Configuration page.
    You can learn more about these options in the
    [Enabling Page Versioning and Staged Content](/discover/portal/-/knowledge_base/7-0/enabling-page-versioning-and-staged-content)
    article.

3.  Click *Save*.

You've officially begun the staging process!

Because Local Live staging creates a clone of your site, it's recommended to
only activate staging on new, clean sites. Having a few pages and some apps
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
(![Options](../../../../images/icon-options.png)) from the upper right corner of
the page and select *Staging Configuration*. Select the *None* radio button to
turn Local Live staging off. Please note that this operation removes the staging
environment altogether, so all content that was not published to your live site
will be lost!

Great! Now you're ready to use Local Live Staging.
