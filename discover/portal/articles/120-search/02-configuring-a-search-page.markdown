# Configuring Search

In @product-ver@ there are multiple options for the default search experience.
If you're unsure which approach to take, use the
[default](#default-search-pages). It's meant to provide a sensible starting
point. If you've been using @product@ for a long time and like the search
experience you've always used, use the [legacy
approach](#legacy-search-experience). If you're in need of a fully customized
experience, [manually configure](#manual-search-page-configuration) the search
experience. In that case, read here and then find the articles on the [Search
Bar](), [Search Facets](), and [Search Results]() to understand the full suite
of configuration options. 

This article gives a higher level view of configuring two aspects of the search
experience: 

1.  Using search display pages.

2.  Where to locate the various search configuration options.

## Search Display Pages

Search display pages are where users go to enter search terms and browse search
results. 

There are three options when deciding on the best search display page for your
portal:

1.  Default search page
2.  Manual search page
3.  Legacy search page

### Default Search Pages

The out-of-the-box (including the default theme) search experience  has two
components:

1. A search bar embedded on each page.
2. A default search page where search requests are routed.

The search bar widget is configured to point to the default search page with the
friendly URL `/search`.

![Figure x: By default, the embedded search bar points to the pre-configured `/search` destination page.](../../images/search-dest-page.png)

When a user enters a search term, they're redirected to the default page, where
results are displayed.

![Figure x: The default page is pre-configured for a full search experience.](../../images/search-default-page.png)

The default search page is just a regular old page that you can customize as
much as you'd like, so if there are a few changes you must make to the default
page, don't abandon it and create one manually. Just make the configuration
changes you need, including adding more widgets to the page and configuring
widgets on the page.

### Manual Search Page Configuration

It's reasonable to create the search experience form the ground up. Here's how:

1.  System Settings, 

### Legacy Search Experience


## Configuring Search Behavior

Configure the default search behavior to suit your needs. There are several
places to make configuration changes with the default search experience:

1.  *Search Bar*: 
2.  *Search Page*: Layout, portlet configurations
3.  *Add Widget Menu*: Add new widgets
4.  *System Settings*:

