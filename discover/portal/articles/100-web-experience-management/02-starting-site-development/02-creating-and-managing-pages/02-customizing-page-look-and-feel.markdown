# Customizing Page Options [](id=customizing-page-options)

To configure options for the entire page set, select *Configure* from the menu 
in the top right corner of *Site Pages*. This page defaults to *Look and 
Feel*. On this tab appears an interface for choosing a theme for the current
site. Themes can transform the entire look of the portal. They are created by
developers and are easily installed using the Liferay Marketplace. Since you
don't have any themes beyond the default one installed yet, you'll use the
default theme for your pages.

![Figure 1: The Look and Feel interface allows you to choose a theme for the current site.](../../../../images/look-and-feel-pages.png)

You can apply themes to individual pages as well by selecting *Configure*
&rarr; *Define a specific look and feel for this page* option under the page's
*Look and Feel* category. 

![Figure 2: You can define a specific look and feel for a page.](../../../../images/define-a-specific-look-and-feel.png)

Many themes include more than one color scheme, which keeps the existing look
and feel while giving the site a different flavor. The Color Schemes option
is not available for the default theme.

There are a few more configurable settings for your theme. You can switch the
bullet style between dots and arrows and you can choose whether or not to show
maximize/minimize application links by default. The *CSS* section has an editor
for custom CSS for tweaking your theme. 

The next option configures the logo that appears for your site. 

## Using a Custom Logo for a Site [](id=using-a-custom-logo-for-a-site)

By default, the Liferay logo is used for your site pages' logo. If you want to
use your own logo for a specific site, use the *Logo* tab. Adding a custom logo
is easy: select the *Logo* tab from the *Configure* interface and browse
to the location of your logo. Make sure your logo fits the space in the top left
corner of the theme you're using for your web site. If you don't, you could wind
up with a site that's difficult to navigate, as other page elements are pushed
aside to make way for the logo.

In the logo tab, you can also choose whether or not to display the site name on
the site. If you check the box labeled *Show Site Name*, the site name appears
next to the logo. This option is enabled by default and cannot be disabled if
the *Allow Site Administrators to set their own logo* option is disabled in
*Instance Settings*. Removing the site name is not available for the default
site--you can configure this only for new sites and user pages. 

## Executing JavaScript in Site Pages [](id=executing-javascript-in-site-pages)

If you click on *Advanced* for a page set (either Public Pages or Private
Pages), a JavaScript editor appears. This code is executed at the bottom of
every page in the site. If your site's theme uses JavaScript (as is usually the
case), it's best to add custom JavaScript code to the theme and *not* here.
This way, all of your site's JavaScript code remains in one place.

This may be useful if your site's theme does *not* use JavaScript. In this
case, you can place *all* of your site's JavaScript here.

## Rendering Pages for Mobile Devices [](id=rendering-pages-for-mobile-devices)

Select *Advanced* &rarr;  *Mobile Device Rules* to configure your page set for
mobile devices. Mobile device rules are inherited from your Public Pages, but
you can define specific rules per page. You can edit the Look and Feel of
specific pages for mobile devices, including the theme. The set up for this
option is explained in the 
[Displaying Site Pages to Mobile Devices](/discover/portal/-/knowledge_base/7-0/displaying-pages-on-mobile-devices)
section.

## Configuring Rules for Virtual Hosting [](id=configuring-rules-for-virtual-hosting)

If you're using virtual hosting for this site, selecting the *Robots* option
from the *Advanced* tab configures `robots.txt` rules for the domain: both its
public and private pages. If you're not using Virtual Hosting, this tab is
rather boring.

## Notifying Search Engines of Site Pages [](id=notifying-search-engines-of-site-pages)

If you select the *Sitemap* option from the *Advanced* tab for a page set, you
can send a sitemap to some search engines so they can crawl your site. It uses
the industry standard sitemap protocol. 

Select a search engine link to send the sitemap to it. It's only necessary to
do this once per site.

If you're interested in seeing what is sent to the search engines, select the
*preview* link to see the generated XML.

Next, you'll learn how to customize individual site pages.
