# Individual Page Settings [](id=individual-page-settings)

On the Configure page are four tabs: General, SEO, Look and Feel, and 
Advanced. Options selected here have no effect on the rest of the 
Site; just the page you've selected. Many of these options are the same as
those that configure the complete page set, so you can view more details in the 
[Configuring Page Sets](/discover/portal/-/knowledge_base/7-1/configuring-page-sets) 
article.

Note that many of the options are localizable, so you can provide translations
based on the user's locale.

## General [](id=general)

The *General* tab lets you configure the basic information and design for the 
page. You can change the *Name*, *Friendly URL*, and *Page Layout*. 

### Name and Friendly URL [](id=name-and-friendly-url)

The *Name* is the title that appears in the browser's title bar, and how the 
page is identified in the navigation. The *Friendly URL* defines the page's
link. It is a best practice to have the URL match the name of the Page, so
these two should generally be updated together.

### Page Layout [](id=page-layout)

For Widget Pages, you can select a Layout Template that defines droppable
locations for widgets. Layout templates define a number of sections with
columns and rows. Widgets added to a section expand (or contract) 
horizontally to fill the space and can be stacked vertically. 

![Figure 1: Setting a layout template for your page.](../../../../../images/page-select-layout.png)

## Categorization and SEO [](id=categorization-and-seo)

Managing your page's content drastically improves your page's organization and
user experience. The Site page's configuration options offers some opportunities
to organize page content.

### Categories [](id=categories)

The *Categories* tab shows the categorization options. These tools help
administrators organize the page so users can find your page and its
content through search and navigation. For more information on using tags and
categories, see 
[Organizing Content with Tags and Categories](/discover/portal/-/knowledge_base/7-1/organizing-content-with-tags-and-categories).

### SEO [](id=seo)

*SEO* provides several ways to optimize the data the page provides to an indexer
that's crawling the page. You can set the various meta tags for description,
keywords and robots. There's also a separate Robots section for telling robots
how frequently the page is updated and how it should be prioritized. If the page
is localized, you can select a box to generate canonical links by language. If
you want to set some of these settings for the entire Site, you can specify them
from the Sitemaps and Robots tabs of the Manage Site Settings dialog box (see
below).

Each asset (web content article, blog entry, etc.) has a unique URL. From the
search engine's point of view, this makes your pages rank higher since any
references to variations of a specific URL are considered references to the same
page.

## Look and Feel [](id=look-and-feel)

*Look and Feel* lets you set a page-specific theme. You can inherit what you
already have configured for your Page Set's theme, or you can define a theme per
page. See 
[Customizing the Look and Feel of Site Pages](/discover/portal/-/knowledge_base/7-1/page-set-look-and-feel)
for more details.

![Figure 2: Viewing the Look and Feel page configuration. .](../../../../../images/page-look-and-feel.png)

## Advanced Settings [](id=advanced-settings)

*Advanced Settings* contains options useful for specific cases. Some of these
are the same as the options available at the Site or Page Set level, but *Custom
Fields*, *Embedded Widgets*, and *Customization Settings* are unique to the
individual page configuration.

### Query String [](id=query-string)

You can set a query string to provide parameters to the page. This can become
useful to web content templates. You can set a target for the page so that it
pops up in a particularly named window or appears in a frameset. And you
can set an icon for the page that appears in the navigation menu.

### Custom Fields [](id=custom-fields)

*Custom Fields* lets you edit the custom fields you already have configured for
the *Page* resource. If you don't have any custom fields configured in your
Site, this option doesn't appear. In this case, navigate to the Control
Panel &rarr; *Custom Fields* located under the *Configuration* tab. These are
metadata about the page and can be anything you like, such as author or creation
date. For more information on Custom Fields, see 
[Custom Fields](/discover/portal/-/knowledge_base/7-1/custom-fields).

### Mobile Device Rules [](id=mobile-device-rules)

Apply rules for how this page should render for various mobile devices here.
Create them by navigating to Site Administration menu and selecting
*Configuration* &rarr; *Mobile Device Families*.

### Embedded Widgets [](id=embedded-portlets)

This option only appears if you have embedded one or more widgets on
the page. 

Widgets can be embedded on a page via web content template or fragment. To
learn more about this, see 
[Adding Templates](/discover/portal/-/knowledge_base/7-1/adding-templates).
You can embed a widget on a page layout or theme programmatically. If you're
interested in learning more about this, visit the
[Embedding Portlets in Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-1/embedding-portlets-in-themes-and-layout-templates)
tutorial.

### Customization Settings [](id=customization-settings)

This configuration option in the *Advanced* tab lets you mark specific
sections of the page you want users to be able to customize. You can learn more
about page customizations in 
[Personalizing Pages](/discover/portal/-/knowledge_base/7-1/personalizing-pages).

### JavaScript [](id=javascript)

This shows a JavaScript editor for code that's executed at the bottom of your
page. If your Site's theme uses JavaScript (as is usually the case), it's best
to add custom JavaScript code to the theme instead. This way, all your Site's
JavaScript code remains in one place.

This configuration option is also available for Page Sets like Public Pages and
Private Pages. Visit 
[Executing JavaScript in Site Pages](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages#executing-javascript-in-site-pages)
for more information on doing this for Page Sets.
