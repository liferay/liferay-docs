# Search Results Behavior

The previous article covered ways to display search results. This
article covers these additional concepts and configurations: 

- [Filtering search results with facets](#filtering-results-with-facets)
- [Understanding search results relevance](#search-results-relevance)
- [The effect of permissions on search results](#permissions-and-search-results)
- [Search results in the staging environment](#search-and-staging)
- [Search results summaries](#result-summaries)
- [Search results term highlighting](#highlighting)

## Filtering Results with Facets [](id=filtering-results-with-facets)

Results are filtered using *facets*. Most Users will have encountered similar
filtering capabilities in other online applications, particularly during online
commerce activities. Users enter a search term, are presented with a list of
results and search facets, which you can think of as buckets that group results
together if they share a common characteristic.

Administrators can configure facets. Read about 
[configuring facets](/discover/portal/-/knowledge_base/7-1/configuring-facets) 
to learn more.

## Search Results Relevance [](id=search-results-relevance)

How does the search engine decide which results to return at the top of the
list? It uses the concept of *relevance*. Relevance is based on a score
calculated by the search engine. There are numerous factors contributing to the
total score of a returned document, and all of the implementation details of how
relevance scoring works are dependent algorithms provided by the 
[search engine](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html#relevance-intro).

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

See the 
[Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/guide/current/scoring-theory.html#tfidf) 
for more information on 
[relevancy scoring](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html#relevance-intro).

## Permissions and Search Results [](id=permissions-and-search-results)

Users lacking
[VIEW permission](/discover/portal/-/knowledge_base/7-1/roles-and-permissions) 
on an asset won't see it in the search results. A logged in User with the Site
Administrator role will likely see more search results than a guest User to the
site. 

Prior to presenting results in the UI, there's a final round of permission
checking, referred to as *post filtering*. For example, the User searches for
*liferay*, and the search engine returns all relevant forum posts. As the Search
Portlet iterates through the list of relevant forum posts, it performs one last
permission check of the post to ensure the User can view the post and the
categories it exists within. If a matching forum post exists in a category the
User doesn't have permission to view, it isn't displayed in the list of search
results.

Search permission checking behavior is configurable:

1.  *Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; *Search*
    &rarr; *Default Search Result Permission Filter* includes two settings.

    The first setting, Permission Filtered Search Result Accurate Count
    Threshold, specifies the maximum number of search results to permission
    filter before results are counted. A higher threshold increases count
    accuracy, but decreases performance. Any value below the search results
    pagination delta effectively disables this behavior.

    The second setting, Search Query Result Window Limit, sets a limit to the
    number of search results returned from the search engine.

    Note that a high number in either of these settings will cause a decrase in
    performance.

2.  *Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; *Search* &rarr;
    *Permission Checker* includes two settings to configure how search processes
    User permissions.

    The first setting, Include Inherited Permissions, specifies whether
    inherited permissions (from User Group or Organization membership) are
    considered when permissions filtering search results.
    <!-- Can we provide a case where this isn't desirable? -->

    The second setting, Permissions Term Limit, limits the number of permission
    search terms added to the search query. If exceeded, no permission search
    terms are added. Instead, permission checking falls back to the search
    permission filter.
    <!-- Ripped from the system settings help text, do not understand. -->

## Search and Staging [](id=search-and-staging)

@product@ supports the concept of
[staging](/discover/portal/-/knowledge_base/7-1/staging-content-for-publication),
where content is first placed in a preview and testing environment before being
published for consumption by end Users (on the live site). Content added to the
search index is marked so that the search API can decipher whether an item is
live or not. In the live version of the site, it's quite simple: only content
that's marked for the live site is searchable. 

In the staged version of the site, all content, whether live or staged, is
searchable.

## Result Summaries [](id=result-summaries)

Search results must be displayed to Users to be useful. If each result was
displayed in its JSON document form, Users would faint and User Experience
Designers around the world might spontaneously combust. Liferay values end Users
and User Experience Designers alike, so a list of result summaries is returned
instead. 

![Figure 5: Highlighting is useful for drawing attention to your search terms,
where they appear in the result summary.](../../images/search-highlight-summary.png)

So what's included in a result summary? The information from a document that the
asset's developer felt is most useful to end Users searching for the asset.
That means that each asset can have different fields included in their search
result summaries. For assets with text content, a common summary format is to
include the title and the content of the asset. The title is displayed first.
The asset type (for example, Document in the example image above) is always
displayed on the second line, and a snippet of the content that includes a match
to the search term on the last line. Some assets, like Documents and Media
documents, display the description field if no content is available to display.

Users are different. Only the User's full name and the asset type (User) are
displayed in User result summaries.

![Figure 6: User summaries contain only the User's full name.](../../images/search-User.png)

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
<mark>highlighted</mark> by default. This is disabled in the widget
configuration screen. 

![Figure 9: Some document summaries have lots of highlights, if the search term matches text that appears in the summary.](../../images/search-highlights.png)

Highlighting is a helpful visual cue that hints at why the result is returned,
but beware. A hit can score well, and thus be returned near the top of the
results, without having any highlights. That's because not all indexed fields
appear in the summary. Consider a User named Arthur C. Clarke. He has an email
address of *acc@authors.org*, which is searchable. Because results summaries for
Users only contain the full name of the User, searching for Mr. Clarke by his
email address returns the User, but no term is highlighted. 

![Figure 10: Results that match the search term won't always have highlights.](../../images/search-no-highlights.png)

There are additional cases where search results won't have highlighting.

