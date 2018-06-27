# Search Results [](id=search-results)

The ideal search experience involves a user entering a search term, waiting an
infinitesimal amount of time, and having the perfectly matching asset delivered
to them at the top of a list of other extremely relevant hits. Like this:

![Figure 1: The goal is to return the perfect results to users searching your site.](../../images/search-results-perfect.png)

The developers of each asset control much about how the asset's information is
stored in the search engine (this process is called
[*indexing*](/develop/tutorials/-/knowledge_base/7-0/understanding-search-and-indexing)),
and how its information is searched and returned in the search results.
Developers who dislike how a particular asset behaves in search can use an
[*Indexer Post
Processor*](/develop/reference/-/knowledge_base/7-0/indexer-post-processor) to
modify the asset's indexing behavior, and how search queries are constructed to
look up the assets in @product@.

There are also ways to influence the way search results are displayed from the
user interface. This article covers the following topics:

- [Configuring the display of search results](#configuring-results-display)
- [Understanding search results relevance](#search-results-relevance)
- [Filtering search results with facets](#filtering-results-with-facets)
- [The effect of permissions on search results](#permissions-and-search-results)
- [Search results in the staging environment](#search-and-staging)
- [Search results summaries](#result-summaries)
- [Search term highlighting](#highlighting)

Search results, called *hits* in the backend search infrastructure, are the end
of the road, the destination, to some users. To others, they're just the
beginning of the journey. Either way, you can configure how they're displayed.

## Configuring Results Display [](id=configuring-results-display)

The search application has a nice format for displaying search results. It also
allows users to click on a specific result so they can look at it in more
detail. Configure the application's display options by clicking its options menu
(![Options](../../images/icon-options.png)) and selecting *Configuration*. The
tab displayed is *Display Settings*.

The Scope setting is really important. By default, searching is done on
*This Site*, which means only the assets associated with the site where the
search is executed. To expand the scope of the search to the entire @product@
instance, select *Everything*. To let the user choose which scope they want
to search, select *Let the User Choose*.

![Figure 2: The *Let the User Choose* scope option enables a drop-down menu in the search bar where users can set the scope of their search.](../../images/search-scope.png)

The list of facet settings on this page is also quite important. Learn more
about facets and their configuration options in a [separate
article](/discover/portal/-/knowledge_base/7-0/configuring-facets).

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

2.  Inverse Document Frequency: Matching terms that are rare in the index
    provide a higher relevance score than those that are more common.
    Example:

3.  Field-Length Norm: Matches from shorter fields, like title, score higher
    than those in longer fields, like content.

Those principles determine the order of results returned in the search portlet.
To look in depth at the relative contribution of each to a result set's
documents, access Elasticsearch's API via URL, like this generalized form:

    http://host:port/index-name/type/_search?q=title:searchTerm&explain

Consider a specific example for an Elasticsearch running on `localhost:9200`,
with an index name of `liferay-20116`, with a type of `LiferayDocumentType`, and
searching the title field for the word *ziti*. Importantly, the `explain`
option is appended to the URL, ensuring that the scoring details are returned
for each result:

    http://localhost:9200/liferay-20116/LiferayDocumentType/_search?q=title:ziti&explain

The results are returned in JSON format:

![Figure 4: The scoring explanation of search results, displayed in JSON.](../../images/search-results-scoring-json.png)

A logical outcome of these three scoring principles is that *title is king*. A
match in the title field will produce a good score, because of the field length
norm principle. Title fields are usually short, so a match there will score
well. A phrase match in the title field, where multiple consecutive words from
the search term match a phrase in the title field, is even better. An exact
match between the search term and the title is pretty much the supreme overlord
of search scoring.

See the [Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/guide/current/scoring-theory.html#tfidf) for more information on [relevancy scoring](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html#relevance-intro).

## Permissions and Search Results [](id=permissions-and-search-results)

@product@ includes a robust [role-based permissions
system](/discover/portal/-/knowledge_base/7-0/roles-and-permissions). It's
important that users lacking permission to view an asset also can't see it in
the search results. A logged in user with the Site Administrator role will
likely see more search results than a guest user to the site. To understand
whether a user has permissions to see an asset in the search results, the answer
to this question must be *yes*:

*Does the user occupy a role that has VIEW permission on an asset?*

If the user has VIEW permission on an asset, then the asset is returned in the
search results.

Another important concept is that of *post filtering*. Search results are
returned from the search index to the Search portlet, and a final round of
permission checks is performed prior to presenting results on the UI. For
example, the user searches for the term *liferay*, and the search engine returns
all relevant forum posts. As the Search Portlet iterates thru the list of
relevant forum posts, it performs one last permission check of the post to
ensure the user can view the post and the categories it exists within. If a
matching forum post exists in a category the user doesn't have permission to
view, it isn't displayed in the list of search results. Post filtering is done
prior to display, so the facet count is not updated. This can result in
inaccurate result counts in the Search Portlet. This behavior will be fixed in
the next version of the Search application.

## Search and Staging [](id=search-and-staging)

@product@ supports the concept of
[staging](/discover/portal/-/knowledge_base/7-0/staging-content-for-publication),
where content is first placed in a preview and testing environment before being
published for consumption by end users (on the live site). Content added to the
search index is marked so that the search API can decipher whether an item is
live or not. In the live version of the site, it's quite simple: only content
that's marked for the live site is searchable. 

In the staged version of the site, all content, whether live or staged, is
searchable.

<!--@product@ implements its staging functionality by creating a copy of the entire
index when staging is enabled. for the live site, and one for the staged site.
The live site index includes all documents that include the field
`stagingGroup:false`. Published content automatically including a field in each
document called `stagingGroup`, which is either `true` or `false`. If set to
false, the document is searchable in the live site. Documents only in the
staging site -->

## Result Summaries [](id=result-summaries)

Search results must be displayed to users to be useful. If each result was
displayed in its JSON document form, users would faint and User Experience
Designers around the world might spontaneously combust. Liferay values end users
and User Experience Designers alike, so a list of result summaries is returned
instead. 

![Figure 5: Highlighting is useful for drawing attention to your search terms,
where they appear in the result summary.](../../images/search-highlight-summary.png)

So what's included in a result summary? The information from a document that the
asset's developer felt is most useful to end users searching for the asset.
That means that each asset can have different fields included in their search
result summaries. For assets with text content, a common summary format is to
include the title and the content of the asset. The title is displayed first.
The asset type (for example, Document in the example image above) is always
displayed on the second line, and a snippet of the content that includes a match
to the search term on the last line. Some assets, like Documents and Media
documents, display the description field if no content is available to display.

Users are different. Only the user's full name and the asset type (User) are
displayed in user result summaries.

![Figure 6: User summaries contain only the user's full name.](../../images/search-user.png)

For assets that contain other assets (Web Content, Documents & Media, and
Bookmarks folders) or whose content is not amenable to display (Dynamic Data
List Records and Calendar Events), it makes more sense to display the title,
asset type, and description in results summaries. There'd never be anything in a
content field for these assets.

![Figure 7: Documents and Media, Web Content, and Bookmarks folders include
titles and descriptions in their summaries.](../../images/search-folder.png)

Bookmarks entries show the title and the URL.

![Figure 8: Bookmarks Entries summaries show the title and the URL.](../../images/search-bookmarks.png)

## Highlighting [](id=highlighting)

By now you've probably noticed that search terms appearing in the summary are
<mark>highlighted</mark>. 

![Figure 9: Some document summaries have lots of highlights, if the search term matches text that appears in the summary.](../../images/search-highlights.png)

Highlighting is a helpful visual cue that hints at why the result is returned,
but beware. A hit can score well, and thus be returned near the top of the
results, without having any highlights. That's because not all indexed fields
appear in the summary. Consider a user named Arthur C. Clarke. He has an email
address of *acc@authors.org*, which is searchable. Because results summaries for
users only contain the full name of the user, searching for Mr. Clarke by his
email address returns the user, but no term is highlighted. 

![Figure 10: Results that match the search term won't always have highlights.](../../images/search-no-highlights.png)

There are additional cases where search results won't have highlighting, so
don't automatically assume the Search application is revolting if you see
results summaries with no highlighted terms in them. On the other hand, if the
search results list returns a list of results that display only "I'm sorry,
[Your Name], I'm afraid I can't do that"<sup>[1](#footnote1)</sup>, then the
Search application is definitely revolting. Kill your @product@ instance
immediately and/or hide under your desk until the AI revolution is thwarted or
completed.

<a name="footnote1">1</a> This is a nod to [HAL 9000](http://www.imdb.com/title/tt0062622/quotes), supercomputer of *2001: A Space Odyssey* fame. 

