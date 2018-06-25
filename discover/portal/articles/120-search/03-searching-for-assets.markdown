# Searching for Assets [](id=searching-for-assets)

As explained in the 
[Search introduction](/discover/portal/-/knowledge_base/7-1/search), 
all indexed assets can be returned as search results. Developers can create
their own assets, so your installation might have additional asset types beyond
the ones included by default. 

+$$$

**Searching for Users:** When you click an asset in the search results, it's
displayed in an Asset Publisher (unless the *View in Context* option is selected
in the Search Results portlet). Users are different, though. Think of them as
invisible assets, not intended for display in the Asset Publisher application.
While Users appear as search results with other indexed assets, when you click
one you're taken to the User's profile page. If 
[public personal pages](/discover/portal/-/knowledge_base/7-1/creating-sites#customizing-personal-sites)
have been disabled, clicking on a User from the list of search results shows you a
blank page.

$$$

## Search Bar [](id=search-bar)

Users enter the search context in the search bar. Users enter search terms, hit
their *Enter* button (or click the magnifying glass icon), and they're taken to
a [search page](/discover/portal/-/knowledge_base/7-1/configuring-search-pages)
with various search widgets deployed. 

If using the Search Bar in the legacy 
[search portlet](discover/portal/-/knowledge_base/7-1/configuring-search-pages#legacy-search-experience),
users see a maximized view of the search portlet displaying any results and
facets that apply. See the article on 
[configuring search pages](discover/portal/-/knowledge_base/7-1/configuring-search-pages#legacy-search-experience)
to learn more about these options.

![Figure 1: The default search configuration displays a search bar in its default view,
beckoning users to enter the search context.](../../images/search-bar.png)

### Entering Search Terms [](id=entering-search-terms)

Liferay's search infrastructure supports full text search as implemented by its
supported search engines 
([Elasticsearch]((https://www.elastic.co/guide/en/elasticsearch/reference/6.1/full-text-queries.html)
and 
[Solr](http://lucene.apache.org/solr/features.html)).

Full text search compares all the words entered in a search query (for example,
*space vacation*) to all the words in each index document. A search engine like
Elasticsearch calculates relevance scores to ensure the best results are
returned first (like a Blogs Entry titled *Is a vacation in space right for
you?*) and lots of matching results are returned (anything with either the
word *vacation* or *space* is returned). 

In addition to full text search, advanced search syntax is supported. @product@
relies on the underlying search engine for this behavior, so consult the
[Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/query-dsl-query-string-query.html#query-string-syntax)
or 
[Solr](https://lucene.apache.org/solr/guide/6_6/query-syntax-and-parsing.html)
documentation for the details.

![Figure 2: Search for text in a specific field using Elasticsearch's Query String syntax.](../../images/search-advanced-syntax.png)

### Matching Exact Phrases: Quoted Search [](id=matching-exact-phrases-quoted-search)

What if users want their search terms (for example, _space vacation_) to produce
only results with the exact phrase, as typed? In a regular full text search,
searching _space vacation_ returns search results containing just the terms
_space_ and _vacation_, and hits containing both terms but separated by other
text, as well as results with the exact phrase match. To ensure that only hits
with the exact phrase are returned, enclose it in quotes: _"space vacation"_.

![Figure 3: Search for exact phrase matches by enclosing search terms in quotes. If a user searched for _"space vacation"_, this result would not be returned.](../../images/search-quoted.png)

### Prefix Searching [](id=prefix-searching)

If you're searching in a site for classical musicians, you might search for the
term *instrument*. This search returns documents with the full word in them, but
it also returns variants with *instruments* as the prefix. For example, results
with *instruments*, *instrumental*, and *instrumentation* are also returned.

![Figure 4: Searching for *data* also returns *database*.](../../images/search-prefix.png)

+$$$

**Note:** Prefix searching is available for many fields out of the box, but as
with most things related to search behavior, it's more complicated under the
hood. The details of the field mapping, including the analyzer used on the field
and any transformations performed, determine the final behavior.

$$$

Another way to ensure users see results is through 
[search suggestions](#search-suggestions).

### Configuring the Search Bar [](id=configuring-the-search-bar)

Configure the Search Bar's behavior via its portlet configuration screen.

![Figure 5: Configure the search bar behavior in its configuration screen.](../../images/search-bar-configuration.png)

There are several options:

**Keywords Parameter Name**
: Edit the parameter name for the keywords entered in the search. For example,
the default URL when searching for the keyword term _data_ looks like
this: 

    http://localhost:8080/web/guest/search?q=data

If you change the Keywords Parameter Name to _keyword_ it looks like this:

    http://localhost:8080/web/guest/search?keyword=data

**Scope** 
: Choose between three options: This Site (default), Everything, and Let the
User Choose. *This Site* means only the assets associated with the site where the
search is executed are searched. Expand the scope of the search to all sites by
selecting *Everything*. To let users choose which scope they want to search,
select *Let the User Choose*.

![Figure 6: Let the user choose which scope the search is executed for.](../../images/search-scope.png)

**Scope Parameter Name** : Set the URL parameter name for the scope where the
search is taking place. This parameter only appears in the URL if the scope _Let
the User Choose_ is selected. The default value is _scope_, so searching for the
word _data_ produces the default URL of

    http://localhost:8080/web/guest/search?q=data&scope=this-site

Changing _scope_ to _target_ would produce this URL:

    http://localhost:8080/web/guest/search?q=data&target=this-site

**Destination Page**
: Provide a friendly URL to the 
[search page](/discover/portal/-/knowledge_base/7-1/configuring-search-pages).
If not configured or if it points to a page that doesn't exist, a message
appears for administrators that the search bar must be configured for it to
appear to users.

**Use Advanced Search Syntax**
: If using Elasticsearch, enabling this allows users to enter 
[Query String Syntax](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/query-dsl-query-string-query.html#query-string-syntax) 
into the Search Bar. If using Solr, consult its documentation for the 
[proper syntax](https://lucene.apache.org/solr/guide/6_6/query-syntax-and-parsing.html).

## Search Suggestions [](id=search-suggestions)

Suggest search terms to users when their initial queries are suboptimal. Spell
check settings allow administrators to configure the Search application so that
if a user types a search term that doesn't return many results (for example,
a slightly misspelled werd), the user can be prompted to improve the search. 

To configure the spell check settings, 

1.  You must first reindex the spell check indexes. Go to *Control Panel* &rarr;
    *Configuration* &rarr; *Search*, then click *Execute* next to *Reindex all
    spell check indexes*.

2.  Add the Suggestions widget to the search page.

3.  Open its configuration screen. Click the widget Options button (![Options](../../images/icon-app-options.png)) and select *Configuration*.

![Figure 7: Configure the suggestion settings to allow for user input mistakes and help lead users to results.](../../images/search-suggestions.png)

There are three main settings here:

**Display "Did you mean..." if the number of search results does not meet the
threshold.**
: Present users alternate, spell checked search queries if their search did not
return a minimum number of results (50 by default).

**Display Related Queries**
: If the number of search results doesn't meet the specified threshold (50 by
default), display up to a maximum number of alternative queries (10 by default).

**Add New Related Queries Based on Successful Queries**
: Index a user's search query if it produces a minimum number of results (50 by
default), so it can be displayed to users as a suggestion. If the Display
Related Queries setting is enabled, it's used as a related query for similar
search queries that don't produce enough results.

