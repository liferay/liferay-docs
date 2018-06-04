# What's New with Search? [](id=whats-new-with-search)

There are lots of new features in the @product-ver@ search functionality. Use
this brief overview to get you familiar with the changes, and learn more details
in the remaining articles.

**Modularized Search Functionality**
: Users of search in prior versions will think of all the search functionality
existing in one place: the Search portlet. Now each separate feature is
available in a separate portlet, so you can drag and drop your way to the
perfectly customized search page. The search configurations that existed in the
previous version are still available, but the setting you're looking for might
be in a slightly different location due to the modularization of search in
@product-ver@. The articles here will help familiarize you with the new look.

![Figure 1: The search functionality is now distributed across several widgets.](../../images/search-widgets.png)

**Search Administration**
: Carry out search administration tasks (like reindexing the search indexes) in
the Search administration application in the Control Panel (Control Panel
&rarr; Configuration &rarr; Search).

![Figure 2: Reindexing content now happens in the Search administration application.](../../images/search-admin.png)

**Default/Customizable Search Pages**
: There's a pre-configured search page template you can use to get a sensible
default search page up and running.

![Figure 3: Use the search page template to create your site's dedicated search page.](../../images/search-page-template.png)

**Advanced Search Syntax**
: Use Elasticsearch's Query String syntax to create advanced search queries (if
Elasticsearch is your Search Engine). The actual query syntax and further
processing are dependent on the search engine's implementation details. Consult
your search provider's documentation for more information.

![Figure 4: Use Elastic's Query String syntax to construct advanced search queries.](../../images/search-advanced-syntax.png)

**Multiple Facet and Term Selection**
: Narrow search results using Facets. In @product-ver@, continue refining
results by selecting as many facets and as many terms as necessary. Previously,
only one facet term could be used at a time.

![Figure 5: Facets and their terms can be selected in multiples to refine search results.](../../images/search-multiple-facet-selection.png)

**Search System Settings**
: Configure system wide search behavior from the Search Web System Settings
entry and other search-related System Settings entries, all found in Control
Panel &rarr; Configuration &rarr; System Settings &rarr; Search.

![Figure 6: Use the Search Web entry in System Settings to configure system wide search behavior.](../../images/search-system-settings.png)

**Custom Facets**
: Configure a custom facet to create a new result aggregation on any
non-analyzed keyword field. 

![Figure 7: Configure a custom facet on the Class Type ID field.](../../images/search-custom-facet.png)

**Code Changes and Improvements**
: See the 
[developer guide](/develop/tutorials/-/knowledge_base/7-1/search) 
to learn about the new, deprecated, and removed APIs and extension points for
search infrastructure.

**Legacy Search**
: We're cheating with this one. It isn't a new feature, but the old way of doing
search, inside one standalone application, is still just one checkbox away
(located in Control Panel &rarr; Configuration &rarr; System Settings &rarr;
Search &rarr; Search Web). This causes the embedded search application to revert
to the old-style search portlet. The legacy search application can also be added
to any page from the Add Widget menu (it's under the *Tools* category).

To learn more about using and configuring these new features, see the remaining
documentation in this section. 
