# Search Results

Search results, called *hits* in the backend search infrastructure, are
philosophical entities, really. To some, they're the end of the road, the
destination. To others, they're just the beginning of the journey. 

Enough of that philosophical stuff. The ideal search experience involves a user
entering a search term, waiting an infinitesimally small amount of time, and
having the perfectly matching asset delivered to them at the top of a list of
extremely relevant hits. Like this:

![Figure x: The goal is to return the perfect results to users searching your site.](../../images/search-results-perfect.png)

Here's a secret: the developers of each asset control much about how the asset's
information is stored in the search engine (this process is called *indexing*),
and how its information is searched and returned in the search results.
Developers who dislike how a particular asset behaves in search can use an
*Indexer Post Processor* to modify the asset's indexing behavior and how search
queries are constructed to look up the assets in @product@.

## Configuring Results Display

<!-- ## Displaying Search Results [](id=displaying-search-results) -->

The search application has a nice format for displaying search results. It also
allows users to click on a specific result so they can look at it in more
detail. You can configure the application's display options by clicking its
options menu
(![Options](../../images/icon-options.png)) and selecting
*Configuration*. The tab displayed by default is *Display Settings*.

The Scope setting is really important here. By default, searching is done on
*This Site*, which means only the assets associated with the site where the
search is executed. To expand the scope of the search to the entire @product@
instance, select *Everything*, To let the user choose which scope they want
to search, select *Let the User Choose*.

![Figure 1: The *Let the User Choose* scope option enables a drop-down menu in the search bar where users can set the scope of their search.](../../images/search-scope.png)

The list of facet settings on this page is also quite important. To learn more
about facets and their configuration options read
[here](/discover/portal/-/knowledge_base/7-0/facet-settings). 

For more display options, click the *Other Settings* tab. There are several
options here:

**Display Results in Document Form**
: Display results as [search
documents](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search).
Never use this in production. Developers use this feature to view search
responses in their generic, Document-based format. Part of a developer's job
when writing search indexers is to convert Documents (the objects that get
indexed) to the actual object and back again. This option allows developers to
see how their objects are being indexed.

+$$$

**Note:** You can identify available indexed fields by enabling the Search
application's
*Display Results in Document Form* configuration setting and then expanding
individual results by clicking the *+* symbol to the left of their titles.

$$$

**View in Context**
: When an asset is clicked, show it in the app to which it belongs. For example,
if you click on a blog post in the search results, you'll be taken to the page
where the blog is posted in the Blogs application. Note that you will no longer
be in the search context after clicking on a search result. When this option is
unchecked, the asset displays in an Asset Publisher window while still in the
search context. If you have the right permissions, you can even edit the content
directly from the Search context. Click the back arrow to return to the search
results.

**Display Main Query**
: Show the exact search query that the app generated to the search engine. Never
use this in production; this is for development purposes only.

**Display Open Search Results**
: Show results from third party Open Search plugins, if they are installed. This
is for backward compatibility only: developers are encouraged to re-design their
search code, and then custom assets are aggregated with native @product@ assets
seamlessly.

To learn about returning suggestions for better search terms (for example, "Did
you mean...") if not enough results are returned, read [here](LINK TO SPELL
CHECK CONFIG).

## Filtering Results with Facets

## Search Results Relevance

## Permissions and Search Results
