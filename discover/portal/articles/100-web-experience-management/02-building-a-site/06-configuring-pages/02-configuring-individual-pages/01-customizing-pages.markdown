# Individual Page Settings

On the Configure page, you have four tabs: General, SEO, Look and Feel, and 
Advanced. Each contains options for the configuration of the specific page you
are configuring. Options selected here will have no effect on the rest of the 
site. Many of these options are the same as the ones that you have for configuring the complete page set, so you can view more details in the [Configuring Page Sets](/discover/portal/-/knowledge_base/7-1/configuring-page-sets)
section.

Note that many of the options are localizable. This means that you can 
configure different text for things like page titles and friendly URLs based on 
the user's locale.

## General [](id=general)

The *General* tab lets you configure the basic information and design for the 
page. You can change the *Name*, *Friendly URL*, and *Page Layout*. 

### Name and Friendly URL

The *Name* is the title that will appear at the top of the page and how the 
page will be identified in the navigation. The *Friendly URL* is the URL where
the page can be viewed. It is a best practice to have the URL match the name of 
the Page, so these two should generally be updated together.

### Page Layout

For Widget Pages, you can select a Layout Template which defines where you can 
add widgets to the page. Layout templates define a number of sections with 
columns and rows. Widgets added to a section will expand (or contract) 
horizontally to fill the space, and can be stacked vertically. 

![Figure X: Setting a layout template for your page.](../../../../../images/page-select-layout.png)

## Categorization and SEO [](id=categorization-and-seo)

Managing your page's content drastically improves your page's organization and
user experience. The site page's configuration options offers some opportunities
to organize page content for your Lunar Resort site.

### Categories [](id=categories)

The *Categories* tab shows the categorization options. These tools help
administrators organize the page and helps users to find your page and its
content through search and navigation. For more information on using tags and
categories, visit the 
[Organizing Content with Tags and Categories](/discover/portal/-/knowledge_base/7-1/organizing-content-with-tags-and-categories)
section.

### SEO [](id=seo)

*SEO* provides several ways to optimize the data the page provides to an indexer
that's crawling the page. You can set the various meta tags for description,
keywords and robots. There's also a separate Robots section that lets you tell
indexing robots how frequently the page is updated and how it should be
prioritized. If the page is localized, you can select a box to generate
canonical links by language. If you want to set some of these settings for the
entire site, you can specify them from the Sitemaps and Robots tabs of the
Manage Site Settings dialog box (see below).

Each asset (web content article, blog entry, etc.) has a unique URL. From the
search engine's point of view, this makes your pages rank higher since any
references to variations of a specific URL are considered references to the same
page.

## Look and Feel [](id=look-and-feel)

The *Look and Feel* tab lets you set a page-specific theme. You can inherit
what you already have configured for your page sets' theme, or you can define
them per page. You can see the Page Set's
[Customizing the Look and Feel of Site Pages](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages#customizing-the-look-and-feel-of-site-pages)
section for more details.

![Figure X: Viewing the Look and Feel page configuration. .](../../../../../images/page-look-and-feel.png)

## Advanced Settings [](id=advanced-settings)

Under the *Advanced Settings* tab you'll find options that may be useful for
specific cases. Some of these are the same as the options available at the Site 
or Page Set level, but *Custom Fields*, *Embedded Widgets*, and *Customization 
Settings* are unique to the individual page configuration.

### Query String [](id=query-string)

You can set a query string to provide parameters to the page. This can become
useful to web content templates. You can set a target for the page so that it
either pops up in a particularly named window or appears in a frameset. And you
can set an icon for the page that appears in the navigation menu.

### Custom Fields [](id=custom-fields)

*Custom Fields* lets you edit the custom fields you already have configured for
the *Page* resource. If you don't have any custom fields configured in your
site, this option doesn't appear. In this case, navigate to the Control
Panel &rarr; *Custom Fields* located under the *Configuration* tab. These are
metadata about the page and can be anything you like, such as author or creation
date. For more information on Custom Fields, see the 
[Custom Fields](/discover/portal/-/knowledge_base/7-1/custom-fields) section.

### Mobile Device Rules [](id=mobile-device-rules)

Apply rules for how this page should render for various mobile devices here.
Create them by navigating to Site Administration menu and selecting
*Configuration* &rarr; *Mobile Device Families*.

### Embedded Widgets [](id=embedded-portlets)

This option only appears if you have embedded one or more widgets on
the page. 

Widgets can be embedded on a page via web content template or fragment. To
learn more about this, see the 
[Adding Templates](/discover/portal/-/knowledge_base/7-1/designing-uniform-content#adding-templates)
section. Also, you can embed a widget on a page layout or theme
programmatically. If you're interested in learning more about this, visit the
[Embedding Portlets in Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-1/embedding-portlets-in-themes-and-layout-templates)
tutorial.

### Customization Settings [](id=customization-settings)

This configuration option in the *Advanced* tab lets you mark specific
sections of the page you want users to be able to customize. You can learn more
about page customizations in the
[Personalizing Pages](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages#personalizing-pages)
section.

### JavaScript [](id=javascript)

This shows a JavaScript editor for code that's executed at the bottom of your
page. If your site's theme uses JavaScript (as is usually the case), it's best
to add custom JavaScript code to the theme instead. This way, all of your site's
JavaScript code remains in one place.

This configuration option is also available for page sets like Public Pages and
Private Pages. Visit the
[Executing JavaScript in Site Pages](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages#executing-javascript-in-site-pages)
section for more information on doing this for page sets.
