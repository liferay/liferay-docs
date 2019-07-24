---
header-id: configuring-search-pages
---

# Configuring Search Pages

[TOC levels=1-4]

There are multiple ways to skin the search cat (disclaimer: no actual cats were
harmed during the writing of this article). 

If you're unsure which approach to take, use the
[default](#default-search-pages) configuration. It provides a sensible starting
point that can be modified later, as needed. 

If you've been using @product@ for a long time and like the search experience
you've always used, use the [legacy approach](#legacy-search-experience). 

If you're in need of a fully customized experience, 
[manually configure](#manual-search-page-configuration) the search experience. 

After choosing your approach and reading here to get it up and running, find
the articles on the [Search
Bar](/docs/7-2/user/-/knowledge_base/u/searching-for-assets#search-bar),
[Search Facets](/docs/7-2/user/-/knowledge_base/u/facets), and [Search Results](/docs/7-2/user/-/knowledge_base/u/search-results) to understand the full suite of
configuration options. 

Search display pages are where users go to enter search terms and browse search
results. 

##  Search Page Templates

The default search page is backed by a Search Page Template, and manually
configured search pages can use the template, too. The template can be used in
two ways:

1.  Enable inheriting changes to the template, if you want the search page to
    get any updates made to the template at a later date. 

2.  Create the page based on the template, but independently configured after
    the initial creation.

Out of the box, the Search Page Template includes these widgets:

- [Search Bar](/docs/7-2/user/-/knowledge_base/u/searching-for-assets#search-bar)

- [Suggestions](/docs/7-2/user/-/knowledge_base/u/searching-for-assets#search-suggestions)

- [Search Results](/docs/7-2/user/-/knowledge_base/u/search-results)

- [Search Options](/docs/7-2/user/-/knowledge_base/u/whats-new-with-search#search-options)

- [Site Facet](/docs/7-2/user/-/knowledge_base/u/site-facet): This widget is
    hidden unless the Search Bar is configured to search the _Everything_ scope
    and results from multiple sites are returned.

- [Type Facet](/docs/7-2/user/-/knowledge_base/u/type-facet)

- [Tag Facet](/docs/7-2/user/-/knowledge_base/u/tag-and-category-facets)

- [Category Facet](/docs/7-2/user/-/knowledge_base/u/tag-and-category-facets)

- [Folder Facet](/docs/7-2/user/-/knowledge_base/u/folder-facet)

- [User Facet](/docs/7-2/user/-/knowledge_base/u/user-facet)

- [Modified Facet](/docs/7-2/user/-/knowledge_base/u/modified-facet)

Out of the box, widgets use the _Barebone_ Application Decorators: unless
there's content to render in the widget, the widget body is hidden. The
header is displayed if you hover over it. 

![Figure 1: At first glance, not much is happening on the search page. But, there's more than meets the eye.](../../images/search-barebone-widgets.png)

Because of this, when you visit a search page created from the default search
page template, you won't see certain widgets fully rendered.

By contrast, when you add a search widget to a page manually, they use the
_Borderless_ decorator (by default), which shows more of the widget even when
there is no content to display.

## Default Search Pages

Using the default site and the default theme with the default search settings,
the out-of-the-box search experience has two components for end users:

1. A search bar embedded on each page.
2. A default search page where search requests are routed.

Behind the scenes, The search bar widget points to a hidden search page with the
friendly URL `/search`.

![Figure 2: By default, the embedded search bar points to the pre-configured `/search` destination page.](../../images/search-dest-page.png)

Enter a search term and you're redirected to the default search page, where
results are displayed in the Search Results widget.

![Figure 3: The default page is pre-configured with the Search Results widget and the various Facet widgets to provide a full search experience.](../../images/search-default-page.png)

The default search page is based on a Search page template, but it doesn't
inherit changes from the page template by default. That means you can customize
the search page directly without changing the template's inheritance
configuration.

![Figure 4: Configure the Search page. By default, it doesn't inherit changes from the page template.](../../images/search-page-config.png)

If you require just a few changes to the default page, don't abandon it and
create one manually. Just make the configuration changes you need, including
adding, configuring, and removing widgets on the page. On the other hand, if you
want a clean break from the default search page, starting from scratch is also
an option.

## Manual Search Page Configuration

It's reasonable to create the search experience from the ground up. If you're
working from a newly created site, it's a necessity. These steps show you how to
switch to a manually configured search experience in the default site, but you
can skip the step on deleting the default search page if you're starting with
a new site:

1.  Delete the existent search page by navigating to the default site's menu and
    clicking *Site Builder* &rarr; *Pages*. Click the Search page's Actions menu
    icon (![Actions](../../images/icon-actions.png)) and select *Delete*.
    Confirm you want to delete the page, and it's gone.

    Once deleted, the search bar disappears from your site pages, replaced by a
    warning message visible only to site administrators:

    ![Figure 5: The search bar is only visible if it points to an existent page.](../../images/search-bar-warning.png)

2.  Create a new page named whatever you want (_Finders Keepers_, perhaps). Make
    it hidden or add it to the navigation as you please (the default search
    page is hidden from the navigation).

    If you want a pre-configured search page, create it from the Search page
    template. Find the template in the Add Site Page form. It's under *Global
    Templates*.

    ![Figure 6: There's a handy page template for creating search pages.](../../images/search-page-template.png)

3.  If you're creating a page not backed by the template, add and configure all
    the widgets you need. You'll find all the available search widgets in the
    Add Widget menu's Search section. Lay them out however you want on the page.

4.  Configure the search bar at the top of the page, making sure it points to your
    new search page's friendly URL (for example, `/finders-keepers`). 

    Click the Search Bar widget's Options menu (![Options](../../images/icon-app-options.png)).

    Click *Configuration* and set the Destination Page to the search page's
    friendly URL.

    Click *Save*.

Now your search page is up and running.

## Legacy Search Experience

In prior versions, the search experience was encapsulated in one application,
*Search*. It was embedded in the default theme, just like the search bar is now.
It looked very similar, with only the search bar visible in the default view of
the application. Once a search term is entered, the maximized view of the
application is presented, with all the search facets and results now in view. It
looks a lot like the new search behavior, only its monolithic structure means
it's difficult to customize. If you liked the old application, it's still
available. Enable it with these steps:

1.  Delete the default search page. From the site menu, click *Site Builder*
    &rarr; *Pages*. Click the Actions menu
    (![Actions](../../images/icon-actions.png)) for the Search page and choose
    *Delete*. 

2.  Enable the legacy search application. Go to Control Panel &rarr;
    Configuration &rarr; System Settings &rarr; Search &rarr; Search Web and
    check the box for *Classic Search Widget in Front Page*.

Now your portal's search is backed by the legacy Search application, and it's
embedded on each page in the default theme. To add the legacy Search application
to a page, open the Add Widget menu, find the Search widget under the Tools
category, and drag and drop it onto the page.

Configure the portal's search behavior to suit your needs. Here you've seen
three distinct search configurations.
