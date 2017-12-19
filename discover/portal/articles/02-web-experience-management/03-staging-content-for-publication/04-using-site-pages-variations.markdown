# Using Site Pages Variations [](id=using-site-pages-variations)

Let's say you're working on a product-oriented site where you'll have several
major changes to a page or a set of pages over a short period of time. Also you
need to be working on multiple versions of the site at the same time to ensure
everything has been properly reviewed before it goes live. With staging, you can
do this using *Page Variations*.

Notice that there are two page variation options available from the Staging bar:
*Site Pages Variation* and *Page Variations*. Site Pages Variation is used to
create different variations for the set of site pages. For instance, you could
use this if you had three separate pages and wanted to modify these pages while
keeping them together as a set. The Page Variations option only works with a
single page.

For example, you can create several site pages variations, enabling the
marketing team to give your site a completely different look and feel for
Christmas. At the same time, the product management team can work on a different
version that will be published the day after Christmas for the launching of a
new product. Additionally, the product management team is considering two
different ideas for the home page of the site, so they can create several page
variations of the home page inside their product launch site.

Variations only affect pages and not the content, which means all the existing
content in your staging site is shared by all your variations. In different site
page variations, you can have different logos, different look and feel for your
pages, different applications on these pages, different configuration of these
applications and even different pages. One page can exist in just one site page
variation or in several of them. Modifying the layout type (e.g., Layout, Panel,
Embedded, etc.) or friendly URL of a page, however, **does** affect every site
page variation.

You must enable page versioning when turning on Local or Remote Live staging.
You can enable page versioning for public and private pages. When you turn
staging on with page versioning enabled, the page variation options are
available in the staging menu bar. By default, you only have one site page
variation and page variation which are both called *Main Variation*. To create a
new one, select the *Options* icon
(![Options](../../../images/icon-options.png)) in the Staging bar and select the
variation option. For example, select the *Site Pages Variation* option. This
brings you to a list of the existing site page variations for your site. Click
*Add Site Pages Variation* to create a new one.

![Figure 1: When selecting the *Site Pages Variation* link from the staging toolbar, you're able to add and manage your site pages variations.](../../../images/staging-page-variations.png)

From the *Add Site Pages Variation* screen, you can set a Name, Description, and
also set your new variation to copy the content from an existing variation.
There are several options to choose in this selector.

**All Site Pages Variations:** creates a new variation that contains the last
version marked as ready for publication from any single page existing in any
other variation.

**None (Empty Site Pages Variation):** creates a new, empty variation.

**Main Variation:** creates a new site page variation that contains only the
last version of all the pages that exist in this variation. The current
variation must be marked as ready for publication.

You are also able to rename any variation. For example, edit the Main Variation
and change its name to something that makes more sense in your site, such as
*Basic*, *Master*, or *Regular* and create a variation for.

You can switch between different variations by clicking on them from the staging
menu bar. It's also possible to set permissions on each variation, so certain
users have access to manage some, but not all variations.

You can now go to the home page of your Christmas variation and change the logo,
apply a new theme, move applications around, change the order of the pages and
configure different apps. The other variations won't be affected. You can even
delete existing pages or add new ones (remember to *Mark as Ready for
Publication* when you are finished with your changes).

<!--
When you delete a page, it is deleted only in the current variation. The same
happens when you add a new page. If you try to access a page which was deleted
in the current variation, Liferay informs you this page is not *enabled* in this
variation and you must enable it. To enable it, navigate to the Site
Administration &rarr; *Pages* &rarr; *Site Pages* screen. Here all the existing
pages for all the variations are shown in a tree. Pages not enabled for the
current variation are shown in a lighter color. You can also access Staging
options from the Site Pages screen by clicking the *Staging* drop-down menu
located above the pages tree.
----
Above paragraph feature did not work. Check if text is accurate or if feature
changed. This feature, however, does working when adding a page. -Cody -->

To publish a variation to the live site, click on *Publish to Live* in the
staging menu and then select *Publish to Live*. Publications can also be
scheduled independently for different variations. For example, you could have a
variation called *Mondays* which is published to the live site every Monday and
another one called *Day 1* which is published to the live site every first day
of each month.

You can also have variations for a single page inside a site page variation,
which allows you to work in parallel in different versions of a page. For
example, you might work on two different proposals for the design of the home
page for the Christmas variation. These page variations only exist inside a site
page variation.

To create a new page variation, click *Page Variations* on the staging toolbar.
This brings you to a list of existing page variations for the current page (by
default, there is only one called *Main Variation*). You can create more or
rename the existing one. You can switch between different page variations using
the dropdown menu on the staging toolbar. When you decide which page variation
should be published, mark it as *Ready for Publication*. Only one page variation
can be marked as ready for publication and that is the one that gets published
to the live site.

For example, you could create a page variation called Thanksgiving for a page
inside of the Christmas variation and another one called Christmas Day to
display different content on those particular days.

![Figure 2: This is an example of a Thanksgiving page variation.](../../../images/page-variation-thanksgiving.png)

Another powerful feature is the possibility of *merging* Site Pages Variations.
To merge two Site Pages Variations, you need to go to the Site Pages Variation
screen. From there, click on *Merge* on the Site Pages Variation you want to use
as the base. You will be asked to choose the Site Pages Variation to merge on
top of it. Merging works in the following way:

- New pages that don't exist in the base Variation will be added.
- If a page exists in both Site Pages Variations, and at least one version of
  the page was marked as ready for publication, then the latest version marked
  as ready will be added as a new Page Variation in the target page of the base
  Variation. (Note that older versions or page variations not marked as ready
  for publication won't be copied. Merge can be executed, however, as many times
  as needed and will create the needed pages variations in the appropriate page
  of the base Site Pages Variation).
- Merging does not affect content nor will overwrite anything in the base
  Variation, it will just add more versions, pages and page variations as
  needed.

@product@'s staging environment is extremely easy to use and makes maintaining a
content-rich web site a snap. You'll learn about scheduling web content next.
