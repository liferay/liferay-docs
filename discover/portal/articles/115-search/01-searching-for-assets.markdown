# Searching for Assets [](id=searching-for-assets)

As explained in the [Search
introduction](/discover/portal/-/knowledge_base/7-0/search), all indexed assets
can be returned as search results. Since any developer can create their own
assets, your @product@ instance might have additional asset types beyond the
ones that @product@ ships with by default. 

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

**Versioning Note:** If there are multiple versions of the same content, the
latest approved version is the only one searched.  <!-- IS THIS A SEARCH THING
OR AN INDEXING DECISION THAT'S UP TO THE COMPONENT
TEAM?-->

$$$

## Search Bar [](id=search-bar)

The search bar is where users enter the search context. Users enter search
terms, hit their *Enter* button (or click the magnifying glass icon), and
they're taken to a maximized view of the search portlet displaying any results
and facets that apply.

![Figure 1: The Search application displays just the search bar in its default view,
beckoning users to enter the search context.](../../images/search-bar.png)

### Entering Search Terms [](id=entering-search-terms)

@product@, backed by Elasticsearch, supports *full text search*. It does not,
however, support advanced search syntax, such as Elasticsearch's [query string
syntax](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-query-string-query.html#query-string-syntax).
That means you can't search specific fields, use wildcards like *\**, or boolean
operators like *AND/OR/NOT*. Liferay is weighing the costs and benefits of
enabling the use of string queries out of the box in a future version of
@product@.

### Prefix Searching [](id=prefix-searching)

*Prefix* searching is cool. If you're searching in a site for classical
musicians, you might search for the term *instrument*. This search of course
returns documents with the full word in them, but it also returns variants with
*instruments* as the prefix. For example, results with *instruments*,
*instrumental*, and *instrumentation* would also be returned.

![Figure 2: Searching for *data* in @product@'s Search application also returns
*database*.](../../images/search-prefix.png)

Another way to ensure users see results is using the spell check settings.

<!-- A 7.1 feature, not 7.0 ### Configuring the Search Bar [](id=configuring-the-search-bar) -->
## Spell Checking User Queries [](id=spell-checking-user-queries)

Spell check settings allow administrators to configure the Search application so
that if a user types a search term that doesn't return many results (for
example, a slightly misspelled werd), the user can be prompted to improve their
search. 

To configure the spell check settings, click the options
(![Options](../../images/icon-options.png)) button and select *Configuration*.
The tab displayed is *Display Settings*, and next to it is the Spell Check
Settings tab. Here you can configure the Search application to help users when
their initial query doesn't yield many results.

![Figure 3: Configure the spell check settings to allow for user input mistakes and help lead users to results.](../../images/search-spell-check-settings.png)

There are three main settings here:

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

In addition to enabling or disabling the spell check settings, thresholds for
all of the settings are changeable, to produce the desired behavior.

<!-- For 7.1 only ## Search Display Page - good way to segway into Search
Results section -->


