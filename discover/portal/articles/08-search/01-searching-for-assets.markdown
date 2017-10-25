# Searching for Assets

As explained in the [introduction to this section](LINK), all indexed assets can be
returned as search results. Since any developer can create their own assets,
your @product@ instance might have additional asset types beyond the ones that
@product@ ships with by default. 

+$$$

**Searching for Users:** When you click an asset in the search results, it's displayed
in an Asset Publisher (unless you use the *View in Context* option for the
asset). Users are different, though. Think of them as invisible assets, not
intended for display in the Asset Publisher application. While users appear as
search results with other indexed assets, when you click one you're taken to the
user's profile page. If [public personal
pages](/discover/portal/-/knowledge_base/7-0/creating-sites#customizing-personal-sites)
have been disabled, clicking on a user from the list of search results shows you a
blank page.

$$$

+$$$

**Versioning Note:** If there are multiple versions of a piece of content, the
latest approved version is the only one searched.
<!-- IS THIS A SEARCH THING OR AN INDEXING DECISION THAT'S UP TO THE COMPONENT
TEAM?-->

$$$

Searching for Assets https://github.com/rbohl/liferay-docs/blob/lrdocs-3595-new-search-portlet/discover/portal/articles/08-search/00-intro.markdown#searching-for-assets-

## Search Bar

The search bar is where users enter the search context. It's pretty
straightforward. Users enter search terms, click enter or on the magnifying
glass icon, and they're taken to a maximized view of the search portlet
displaying any results and facets that apply.

### Entering Search Terms 

@product@, backed by Elasticsearch, supports *full text search*. It does not,
however, support advanced search syntax, such as Elasticsearch's [query string
syntax](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-query-string-query.html#query-string-syntax).
That means you can't search specific fields, use wildcards like *\**, or boolean
operators like *AND/OR/NOT*. Liferay is considering enabling the use of string
queries out of the box in a future version of @product@.

### Title prefix searching, e.g. “engineer” finds titles with “engineering”, “engineered”

One of the cool features of @product@ search is *prefix* searching. For example,
if you're searching in a site for classical musicians, you might search for the
term *instrument*. This search will of course return documents with the full
word in them, but also other variants of the word, like *instruments*,
*instrumental*, and *instrumentation*.

![Figure x: Searching for *data* in @product@'s Search application also returns *database*.](../../images/search-prefix.png)

### Configuring the Search Bar

## Spell Checking user Queries https://github.com/rbohl/liferay-docs/blob/lrdocs-3595-new-search-portlet/discover/portal/articles/08-search/01-default-search-behavior.markdown#spell-checking-user-queries-

## Spell Checking User Queries [](id=spell-checking-user-queries)
CONTEXT NOT PROVIDED
Next to the Other Settings tab lives the Spell Check Settings tab. Here you can
configure the Search application to help users when their initial query doesn't
yield many results.

![Figure 2: Configure the spell check settings to allow for user input mistakes and help lead users to results.](../../images/search-spell-check-settings.png)

There's really three main settings here:

**Display "Did you mean..." if the number of search results does not meet the
threshold.**
: Present users alternate, spell checked search queries if their search did not
return a minimum number of results (50 by default).

**Display Related Queries**
: Display up to a maximum number of alternative queries (5 by default) if the
user's query doesn't return a minimum number of results (0 by default).

**Add New Related Queries Based on Successful Queries**
: Index a user's search query if it produces a minimum number of results (50 by
default). If the Display Related Queries setting is enabled, it can be used as a
related query for similar search queries that don't produce enough results.

+$$$

**Note:** To enable the spell checking behavior described above, you must first
trigger a reindex of the spell check indexes. Navigate to the *Control Panel*
&rarr; *Configuration* &rarr; *Server Administration*. Find the Index Actions
entry for *Reindex all spell check indexes.* Click *Execute*, and look for a
message in your log your indicating that the spell checking indexes were created
successfully:

    16:19:35,793 INFO  [liferay/search_writer/SYSTEM_ENGINE-24][BaseSpellCheckIndexWriter:257] 
        Start indexing dictionary for com/liferay/portal/search/dependencies/spellchecker/en_US.txt
    16:19:41,932 INFO  [liferay/search_writer/SYSTEM_ENGINE-24][BaseSpellCheckIndexWriter:2910] 
        Finished indexing dictionary for com/liferay/portal/search/dependencies/spellchecker/en_US.txt

Once the spell checking indexes are created, your spell check settings become
effective.

$$$

In addition to enabling or disabling any of these settings, the thresholds for
all of the spell check settings can also be changed to produce the desired
behavior.
<!--Any notes about performance or other warnings with these numbers? -->

<!--Search is a powerful component of @product@'s asset framework. Administrators
can customize the display options for content, and configure the spell check
settings to ensure users don't go away feeling frustrated after an unsuccessful
search query. Facets are Another important component of @product@'s search
functionality for the opposite situation, where users might be overwhelmed by
the number of search results, and unable to find the proverbial needle in the
haystack. To learn more, read the next article on how facets let users narrow
down search results to find just what they were looking for.
-->

<!-- For 7.1 only ## Search Display Page - good way to segway into Search
Results section -->


