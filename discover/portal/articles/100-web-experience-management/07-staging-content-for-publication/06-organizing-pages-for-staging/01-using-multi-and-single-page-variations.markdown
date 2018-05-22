# Using Multi and Single Page Variations

There are two page variation options available from the Staging Bar:

- *Site Pages Variation:* used to create different variations for a set of site
  pages. For instance, you could use this if you had three separate pages and
  wanted to modify these pages while keeping them together as a set.
- *Page Variations:* used to create different variations for a single page. A
  page variation can only exist inside a site pages variation.

You must enable page versioning in the Staging Configuration menu before the
below options are available in the Staging Bar. You'll learn more about this
later.

Variations only affect pages and not the content, which means all the existing
content in your staging site is shared by all your variations. For example, in
different site page variations, you can have different logos, a different look
and feel for your pages, different applications on these pages, different
configuration of these applications, and even different pages. One page can
exist in just one site page variation or in several of them. Modifying the
layout type (e.g., Layout, Panel, Embedded, etc.) or friendly URL of a page,
however, **does** affect every site page variation.

You'll learn about enabling page versioning next.

## Enabling Page Versioning

Page Versioning is enabled on the Staging Configuration screen when first
enabling staging.

![Figure 1: You can enable page versioning for public and/or private pages.](../../../../images/page-versioning.png)

You can enable page versioning for public and private pages. When page
versioning is enabled, the page variation options are available in the Staging
Bar. By default, you only have one site page variation and page variation which
are both called *Main Variation*. 

If you did not enable page versioning during the initial setup of your staging
environment, navigate to the Product Menu &rarr; *Publishing* &rarr; *Staging*
&rarr; *Options* (![Options](../../../../images/icon-options.png)) &rarr; *Staging
Configuration*. You can enable the page versioning options there.

## Using Site Pages Variations

Site pages variations are useful when you must plan multiple page sets for your
site at once. For example, consider this scenario:

If there were separate teams in your company that needed to create three
drastically different page sets for your site at the same time, they would need
to create three site pages variations. For example,

- The marketing team can give your site a completely different look and feel for
  the Holidays.
- The product management team can work on a version that will be published on
  the first of the year for a new product launch.
- The product management team is also considering two different ideas for the
  home page of the site, so they can create several page variations of the home
  page inside their product launch site.

With this use case, having a site pages variation for each planned page set
would allow three ideas to be fully planned and implemented before publication.

Once you've
[created a site pages variation](/discover/portal/-/knowledge_base/7-1/creating-multi-and-single-page-variations),
you can now navigate to its home page and change the logo, apply a new theme,
move applications around, change the order of the pages, configure different
apps, etc. The other variations won't be affected. You can even delete existing
pages or add new ones (remember to *Mark as Ready for Publication* when you are
finished with your changes). When you delete a page, it is deleted only in the
current variation. The same happens when you add a new page. If you try to
access a page which was deleted in the current variation, Liferay informs you
this page is not *enabled* in this variation and you must enable it. 

![Figure 2: Select the *Enable* button to create a missing page in the current site pages variation.](../../../../images/enable-unavailable-page.png)

To publish a variation to the live site, click on *Publish to Live* in the
staging menu and then select *Publish to Live*. Publications can also be
scheduled independently for different variations. For example, you could have a
variation called *Mondays* which is published to the live site every Monday and
another one called *Day 1* which is published to the live site every first day
of each month.

## Using Page Variations

You can also have variations for a single page inside a site pages variation,
which lets you work in parallel in different versions of a page. For example,
you might work on two different proposals for the design of the home page for a
Holidays site pages variation. Page variations only exist inside a site pages
variation.

Once you've
[created a page variation](/discover/portal/-/knowledge_base/7-1/creating-multi-and-single-page-variations),
you can choose it from the dropdown menu on the Staging Bar. You can always
switch between different variations by clicking on them from the Staging Bar.
It's also possible to set permissions on each variation, so certain users have
access to manage some, but not all variations.

Once you've modified the page variation to the way you want, mark it as *Ready
for Publication*. Only one page variation can be marked as ready for publication
and that is the one that gets published to the live site. To publish a variation
to the live site, click on *Publish to Live* in the staging menu and then select
*Publish to Live*.
