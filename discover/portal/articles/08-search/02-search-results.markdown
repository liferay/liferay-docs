# Search Results [](id=search-results)

The ideal search experience involves a user entering a search term, waiting an
infinitesimally small amount of time, and having the perfectly matching asset
delivered to them at the top of a list of other extremely relevant hits. Like
this:

![Figure 1: The goal is to return the perfect results to users searching your site.](../../images/search-results-perfect.png)

The developers of each asset control much about how the asset's information is
stored in the search engine (this process is called *indexing*), and how its
information is searched and returned in the search results. Developers who
dislike how a particular asset behaves in search can use an *Indexer Post
Processor* to modify the asset's indexing behavior, and how search queries are
constructed to look up the assets in @product@.

There are also ways to influence the way search results are displayed from the
user interface. This article covers the following topics:

- [Configuring the display of search results](#configuring-results-display)
- [Understanding search results relevance](#search-results-relevance)
- [Filtering search results with facets](#filtering-results-with-facets)
- [The effect of permissions on search results](#permissions-and-search-results)
- [How search results works in the staging environment](#search-and-staging)

Search results, called *hits* in the backend search infrastructure, are
philosophical entities, really. To some, they're the end of the road, the
destination. To others, they're just the beginning of the journey. Either way,
you can configure how they're displayed.

## Configuring Results Display [](id=configuring-results-display)

The search application has a nice format for displaying search results. It also
allows users to click on a specific result so they can look at it in more
detail. Configure the application's display options by clicking its options menu
(![Options](../../images/icon-options.png)) and selecting *Configuration*. The
tab displayed is *Display Settings*.

The Scope setting is really important here. By default, searching is done on
*This Site*, which means only the assets associated with the site where the
search is executed. To expand the scope of the search to the entire @product@
instance, select *Everything*. To let the user choose which scope they want
to search, select *Let the User Choose*.

![Figure 2: The *Let the User Choose* scope option enables a drop-down menu in the search bar where users can set the scope of their search.](../../images/search-scope.png)

The list of facet settings on this page is also quite important. Learn more
about facets and their configuration options in a [separate
article](/discover/portal/-/knowledge_base/7-0/configuyring-facets).

For more display options, click the *Other Settings* tab. There are several
options:

**Display Results in Document Form**
: Display results as [search
documents](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search).
Never use this in production. Developers use this feature to view search
responses in their indexed, document-based format. Part of a developer's job
when writing [search
indexers](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search#indexers)
is to convert documents (the objects that get indexed) to the actual object and
back again. This option allows developers to see how their objects are being
indexed. Once enabled, expand each individual result whose document form you'd
like to view by clicking the *Details...* link below the result summary.

![Figure 3: Viewing results in their document form lets you inspect exactly
what's being indexed for a particular asset. This screenshot shows just a small portion of one document.](../../images/search-result-document.png)

**View in Context**
: When an asset is clicked, show it in its native application. For example, if
you click on a blog post in the search results, you'll be taken to the page
where the blog entry is posted in the Blogs application. Note that you will no
longer be in the search context after clicking on a search result. When this
option is unchecked, the asset displays in an Asset Publisher window while still
in the search context. If you have the right permissions, you can even edit the
content directly from the Search context. Click the back arrow to return to the
search results.

**Display Main Query**
: Show the exact search query that the app generated to the search engine. Never
use this in production; this is for development purposes only.

**Display Open Search Results**
: Show results from third party Open Search plugins, if they are installed. This
is for backward compatibility only: developers are encouraged to re-design their
search code, and then custom assets are aggregated with native @product@ assets
seamlessly.

For further reading, check out how to [return suggestions for better search
terms](/discover/portal/-/knowledge_base/7-0/searching-for-assets#spell-checking-user-queries)
(for example, "Did you mean...") when not enough results are returned initially.

## Filtering Results with Facets [](id=filtering-results-with-facets)

Results are filtered using *facets*. The usage by end users is quite simple and
intuitive. Most users will have encountered similar filtering capabilities in
other online applications, particularly during online commerce activities. Users
enter a search term, are presented with a list of results and search facets,
which you can think of as buckets that group results together if they share a
common characteristic.

Administrators can configure facets. Read about [configuring facets](/discover/portal/-/knowledge_base/7-0/configuring-facets) to learn more.

## Search Results Relevance [](id=search-results-relevance)

How does the search engine decide which results to return at the top of the
list? It uses the concept of *relevance*. Relevance is based on a score
calculated by the search engine. There are numerous factors contributing to the
total score of a returned document. This section aims to give an overview and
provide general understanding on the calculation of relevance. 

The relevance scoring approach used in @product@ can be distilled into three
principles:

1.  Term Frequency: If a term appears more than once in the fields of a
    document, the document's relevancy score is higher than if it only appeared
    once. Recall that the document is the entity being searched for in the
    search engine. It's like the corresponding entity in the database, but may
    not include all of the same fields.
    Example: 
    ![Figure 4: Search relevance goes up with the frequency of the term in a document's fields.](../../images/search-results-term-freq.png)

2.  Inverse Document Frequency: Matching terms that are rare in the index
    provide a higher relevance score than those that are more common.
    Example:

3.  Field-Length Norm: Matches from shorter fields, like title, score higher
    than those in longer fields, like content.

<!-- Show one good picture with a series of results that include TF IDF FLN  -->

Those principles determine the order of results returned in the search portlet.
To look in depth at the relative contribution of each to a result set's
documents, access Elasticsearch's API via URL, like this generalized form:

    http://host:port/index-name/type/_search?q=title:searchTerm&explain

Consider a specific example for an Elasticsearch running on `localhost:9200`,
with an index name of `liferay-20116`, with a type of `LiferayDocumentType`, and
searching the title field for the word *ziti*.  Importantly, the `explain`
option is appended to the URL, ensuring that the scoring details are returned
for each result:

    http://localhost:9200/liferay-20116/LiferayDocumentType/_search?q=title:ziti&explain

The results are returned in JSON format:

![Figure 5: The scoring explanation of search results, displayed in JSON.](../../images/search-results-scoring-json.png)

A logical outcome of these three scoring principles is that *title is king*. A
match in the title field will produce a good score, because of the field length
norm principle. Title fields are usually short, so a match there will score
well. A phrase match in the title field, where multiple consecutive words from
the search term match a phrase in the title field, is even better. An exact
match between the search term and the title is pretty much the supreme overlord
of search scoring.

See the [Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/guide/current/scoring-theory.html#tfidf) for more information on [relevancy scoring](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html#relevance-intro).

## Permissions and Search Results [](id=permissions-and-search-results)

It's important that users lacking permission to view an asset also can't see it
in the search results.

## Search and Staging [](id=search-and-staging)

@product@ supports the concept of [staging](LINK), where content is first placed
in a preview and testing environment before being published for consumption by
end users (on the live site). When content is added to the search index, it's
marked so that the search API can decipher whether an item is live or not. In
the live version of the site, it's quite simple: only content that's marked for
the live site is searchable. 

Here's what to expect from search results in a staging environment:
- 

THIS WON'T BE DIFFERENT FOR REMOTE OR LOCAL, AS PERTAINS TO SEARCH, RIGHT?

Add highlighting and discussion of Result Summaries
