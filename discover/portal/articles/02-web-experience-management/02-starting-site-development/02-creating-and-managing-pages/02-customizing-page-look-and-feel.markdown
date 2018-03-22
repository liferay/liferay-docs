# Customizing Page Options

To configure options for the entire page set, select *Configure* from the menu 
in the top right corner of *Site Pages*. This page will default to *Look and 
Feel*. On this tab, you're presented with an interface that allows you to 
choose a theme for the current site. Themes can transform the entire look of 
the portal. They are created by developers and are easily installed using the 
Liferay Marketplace. Since you don't have any themes beyond the default one 
installed yet, you'll use the default theme for your pages.

![Figure 5: The Look and Feel interface allows you to choose a theme for the current site.](../../../images/look-and-feel-pages.png)

You can apply themes to individual pages as well by selecting the 
*Configure* option for a page and selecting the 
*Define a specific look and feel for this page* option under the *Look and Feel* category. 

![Figure 6: You can define a specific look and feel for a page.](../../../images/define-a-specific-look-and-feel.png)

Many themes include more than one color scheme. This allows you to keep the
existing look and feel while giving your site a different flavor. The *Color
Schemes* option is not available for the default theme.

There are a few more configurable settings for your theme. You can switch the
bullet style between dots and arrows and you can choose whether or not to show
maximize/minimize application links by default. The *CSS* section allows you to
enter custom CSS that will also be served up by your theme. In this way, you can
tweak a theme in real time by adding new styles or overriding existing ones.

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
the site. If you check the box labeled *Show Site Name*, the site name will
appear next to the logo. This option is enabled by default and cannot be
disabled if the *Allow Site Administrators to set their own logo* option is
disabled in *Instance Settings*. Removing the site name is not available for the
default site -- only newly created sites and user pages have the option to have
the name display. 

## Executing JavaScript in Site Pages [](id=executing-javascript-in-site-pages)

If you click on *Advanced* for a page set (either Public Pages or Private
Pages), you'll find a window where you can enter JavaScript code that will be
executed at the bottom of every page in the site. If your site's theme uses
JavaScript (as is usually the case), it's best to add custom JavaScript code to
the theme and *not* in this window. This way, all of your site's JavaScript code
remains in one place.

Using the JavaScript window may be useful if your site's theme does *not* use
JavaScript. In this case, the JavaScript window will contain *all* of your
site's JavaScript and you can add some dynamic features to your site's pages.

Next, you'll look at an advanced features of the *Configure* interface: merging
the current site's pages with the pages of the default site.

## Rendering Pages for Mobile Devices [](id=rendering-pages-for-mobile-devices)

You can configure your page set for mobile devices by selecting the *Advanced*
tab and selecting *Mobile Device Rules* option. The ability to modify themes for
regular browsers and mobile devices can only be accomplished by using this
option. Mobile device rules are inherited from your Public Pages, but you can
define specific rules per page. With the ability to define different rules per
page, you can edit the Look and Feel of specific pages for mobile devices,
including the theme. The set up for this option is explained in the
[Displaying Site Pages to Mobile Devices](/discover/portal/-/knowledge_base/7-0/displaying-pages-on-mobile-devices) section.

## Configuring Rules for Virtual Hosting [](id=configuring-rules-for-virtual-hosting)

If you're using virtual hosting for this site, you can configure `robots.txt`
rules for the domain by selecting the *Robots* option from the *Advanced* tab.
The Robots page gives you the option to configure your `robots.txt` for both
public and private pages on a site. If you don't have Virtual Hosting set up,
this tab is rather boring.

## Notifying Search Engines of Site Pages [](id=notifying-search-engines-of-site-pages)

If you select the *Sitemap* option from the *Advanced* tab for a page set, you
can send a sitemap to some search engines so they can crawl your site. It uses
the sitemap protocol, which is an industry standard. You can publish your site
to Yahoo or Google and their web crawlers will use the sitemap to index your
site. @product@ makes this very simple for administrators by generating the
sitemap XML for all public web sites.

By selecting one of the search engine links, the sitemap will be sent to them.
It's only necessary to do this once per site. The search engine crawler will
periodically crawl the sitemap once you've made the initial request.

If you're interested in seeing what is being sent to the search engines, select
the *preview* link to see the generated XML.

Next, you'll learn how to customize individual site pages.
