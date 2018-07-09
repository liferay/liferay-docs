# What's New with Search? [](id=whats-new-with-search)

There are lots of new features in the search functionality. This brief overview
can get you familiar with the changes. More details are in the remaining
articles.

**Modularized Search Functionality** 
: In prior versions, all the search functionality was in one place: the Search
portlet. Now each separate feature is available in a separate portlet, and you
can drag and drop search widgets onto a page, creating a perfectly customized
search page. The search configurations that existed in the previous version are
still available, but the setting you're looking for might be in a slightly
different location due to the modularization of search in @product-ver@. The
articles here help familiarize you with the new look.

![Figure 1: The search functionality is now distributed across several widgets.](../../images/search-widgets.png)

**Elasticsearch 6**
: Support for Elasticsearch 6 was added as an *opt-in* feature for @product@
version 7.0. In the current version, Elasticsearch 6 is the default, embedded
search engine. See the article on 
[Installing Elasticsearch](/discover/deployment/-/knowledge_base/7-1/installing-elasticsearch)
to learn how to install a remote Elasticsearch server for a production
environment.

![Figure 2: Elasticsearch 6 is the default search engine.](../../images/search-elasticsearch6.png)

**Search Administration**
: Carry out search administration tasks (like reindexing the search indexes) in
the Search administration application in the Control Panel (Control Panel
&rarr; Configuration &rarr; Search).

![Figure 3: Reindexing content now happens in the Search administration application.](../../images/search-admin.png)

**Default/Customizable Search Pages**
: Use a pre-configured search page template to get a sensible default search
page up and running.

![Figure 4: Use the search page template to create your site's dedicated search page.](../../images/search-page-template.png)

**Advanced Search Syntax**
: Use Elasticsearch's Query String syntax to create advanced search queries (if
using Elasticsearch as your Search Engine). The actual query syntax and further
processing are dependent on the search engine's implementation details. Consult
your search provider's documentation for more information.

![Figure 5: Use Elastic's Query String syntax to construct advanced search queries.](../../images/search-advanced-syntax.png)

**Multiple Facet and Term Selection**
: Narrow search results using Facets. Continue refining results by selecting as
many facets and as many terms as necessary. Previously, only one facet term
could be used at a time.

![Figure 6: Facets and their terms can be selected in multiples to refine search results.](../../images/search-multiple-facet-selection.png)

**Search System Settings**
: Configure system wide search behavior from the Search System Settings category
found in Control Panel &rarr; Configuration &rarr; System Settings &rarr;
Search. The Search Web entry reverts the default search experience from using
the new Search Widgets to the classic Search Portlet that was standard in past
releases. 

![Figure 7: Use the Search Web entry in System Settings to enable the classic, monolithic Search widget.](../../images/search-web-system-settings.png)

**Custom Facets**
: Configure a custom facet to create a new result aggregation on any
non-analyzed keyword field. 

![Figure 8: Configure a custom facet on the Class Type ID field.](../../images/search-custom-facet.png)

**Search Options**
: Configure page scoped search behavior by adding the Search Options widget from
the Add Widget menu.

![Figure 9: The Search Options widget configures the search experience for
the page it's deployed on.](../../images/search-options.png)

**Search Facet Friendly URLs**
: Using the new Search widgets, filter some search results by applying a facet
term. The URL is now friendly:

    http://localhost:8080/web/guest/search?q=test&modified=past-hour

**Code Changes and Improvements**
: See the developer guide
<!--(/develop/tutorials/-/knowledge_base/7-1/search)--> to learn about the new,
deprecated, and removed APIs and extension points for search infrastructure.

**Legacy Search**
: We're cheating with this one. It isn't a new feature, but the old way of doing
search, inside one standalone application, is still just one checkbox away
(located in Control Panel &rarr; Configuration &rarr; System Settings &rarr;
Search &rarr; Search Web). This causes the embedded search application to revert
to the old-style search portlet. The legacy search application can also be added
to any page from the Add Widget menu (it's under the *Tools* category).

To learn more about using and configuring these new features, see the remaining
documentation in this section. 
