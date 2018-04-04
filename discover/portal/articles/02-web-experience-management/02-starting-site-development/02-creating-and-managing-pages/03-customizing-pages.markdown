# Customizing Pages [](id=customizing-pages)

When you decide to customize a single page, some options that were not
available when initially creating a page appear. Customizing a specific page can
be done by navigating to *Site Pages* in the the Navigation menu and selecting 
the *Options* icon next to the specific page you'd like to edit from the 
navigation tree. From the Options dropdown, select *Configure*. There are three 
groups that the options for customizing a specific page can be defined under. 
You'll learn what each group offers for your site pages.

## General

The *General* tab will give you the ability to change the basic design of the 
page. You can change the *Page Title*, *Friendly URL*, and *Page Layout*. 
Changing the page layout after content has been added to the page.


## Categorization and SEO

Managing your page's content drastically improves your page's organization and
user experience. The site page's configuration options offers some opportunities
to organize page content for your Lunar Resort site.

### Categories

You can explore ways to tag and categorize your page by clicking the *SEO* tab,
which shows how the categorization options by default. These tools help
administrators organize the page and allows for users to easily find your page
and its content through search and navigation. For more information on using
tags and categories, visit the
[Organizing Content with Tags and Categories](/discover/portal/-/knowledge_base/7-0/organizing-content-with-tags-and-categories)
section.

### SEO [](id=seo)

*SEO* provides several means of optimizing the data the page provides to an
indexer that's crawling the page, which is accessible from the *SEO* tab. You
can set the various meta tags for description, keywords and robots. There's also
a separate Robots section that lets you tell indexing robots how frequently the
page is updated and how it should be prioritized. If the page is localized, you
can select a box to make @product@ generate canonical links by language. If you
want to set some of these settings for the entire site, you can specify them
from the Sitemaps and Robots tabs of the Manage Site Settings dialog box (see
below).

In previous versions of Liferay, it was possible that a single page
could be indexed multiple times. In Liferay Portal 6.1, all URLs that direct to the
same page will only create one entry in the index. Previously, the simple URL
*http\://www.lunar-resort.com/web/guest/blog/-/blogs/themoon* and different
versions of the URL which provided additional information about the referring
page had different entries in the index. In @product@ 7.0, each asset (web
content article, blog entry, etc.) has a unique URL. From the search engine's
point of view, this will make your pages rank higher since any references to
variations of a specific URL will all be considered references to the same
page.

## Look and Feel [](id=look-and-feel)

The *Look and Feel* option lets you set a page-specific theme. You can inherit
what you already have configured for your page sets' theme, or you can uniquely
define them per page. You can see the Page Set's
[Customizing the Look and Feel of Site Pages](/discover/portal/-/knowledge_base/7-0/creating-and-managing-pages#customizing-the-look-and-feel-of-site-pages)
section for more details.

##Advanced Settings

There are many options for page configuration found under the *Advanced 
Settings* tab. The options found here range from usability features to various 
types of customization.

### Query String 

You can set a query string to provide parameters to the page. This can become 
useful to web content templates, which you'll see in the next chapter. You can 
set a target for the page so that it either pops up in a particularly named 
window or appears in a frameset. And you can set an icon for the page that 
appears in the navigation menu.

### Custom Fields [](id=custom-fields)

*Custom Fields* lets you edit the custom fields you already have configured for
the *Page* resource. If you don't
have any custom fields configured in your site, this option is not available. If
you don't have any custom fields configured for the Page resource, you can
navigate to the Control Panel &rarr; *Custom Fields* located under the
*Configuration* tab. These are metadata about the page and can be anything you
like, such as author or creation date. For more information on Custom Fields,
see the [Custom Fields](/discover/portal/-/knowledge_base/6-2/custom5-fields)
section.

### Mobile Device Rules [](id=mobile-device-rules)

This option allows you to apply rules for how this page should be rendered for
various mobile devices. You can set these up by navigating to your Site
Administration dropdown menu and selecting *Configuration* &rarr; *Mobile Device
Families*.

<!-- TODO: Device Recognition app is no longer provided on Marketplace. Need to
figure out how this is changing Mobile Device Rules (section still available in
Page Settings. -Cody -->

### Embedded Portlets [](id=embedded-portlets)

This option only appears if you have embedded one or more portlets on
the page. Previous to @product_ver@, you were able to embed portlets on a page 
by acquring their portlet ID and using a `runtime-portlet` tag in the Web 
Content Display app to embed a portlet on a page. This is no longer possible.

Applications can now be embedded on a page via web content template or fragent. To  learn more about this, see the
[Adding Templates](/discover/portal/-/knowledge_base/7-0/designing-uniform-content#adding-templates)
section. Also, you can embed a portlet on a page layout or theme programtically.
If you're interested in learning more about this, visit the
[Embedding Portlets in Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-0/embedding-portlets-in-themes-and-layout-templates)
tutorial.

### Customization Settings [](id=customization-settings)

This configuration option located in the *Advanced* tab lets you mark specific
sections of the page you want users to be able to customize. You can learn more
about page customizations in the
[Personalizing Pages](/discover/portal/-/knowledge_base/7-0/creating-and-managing-pages#personalizing-pages)
section.

### JavaScript [](id=javascript)

If you click on *JavaScript*, you'll find a window where you can enter 
JavaScript code that will be executed at the bottom of your page. If your 
site's theme uses JavaScript (as is usually the case), it's best to add custom 
JavaScript code to the theme and *not* in this window. This way, all of your 
site's JavaScript code remains in one place.

This configuration option is also available for page sets like Public Pages and
Private Pages. Visit the
[Executing JavaScript in Site Pages](/discover/portal/-/knowledge_base/7-0/creating-and-managing-pages#executing-javascript-in-site-pages)
section for more information on doing this for page sets.

Next you'll learn how to add applications to a site page.
