# What's New in Search

## New and Improved Widgets

### Custom Filter

**New Widget**

Add a widget to the page for each of the filters you'd like applied to the
search results. Let search page users see and manipulate the filters or make
them invisible and/or immutable (this is just a cool word for "they can't be
changed").

For example, add a custom filter to ensure that all returned results have the
keyword _street_ in the content field.

### Sort

**New Widget**

The Sort widget reorders the results based on the value of certain `keyword`
fields in the index. For example, show results in alphabetic order of the Title
field. The default order is determined by the search engine's _Relevance_
calculation.

Add more fields to the sort widget if the default options aren't enough. Click
the widget Options (![Options](../../images/icon-app-options.png)) menu &rarr;
Configuration. Enter a human readable label and the `fieldName` to sort by. Just
make sure it's a `keyword` field.

### Search Insights

**Improved**

Past versions of Search Insights showed you the full query string sent to the
search engine, but now it also displays the response from the search engine, and
an explanation of the score for each search hit.

### Widget Configuration: Federated Search Key

A new and continuously improving feature is _Federated Search_, the ability to
use Liferay Search to search data sources other than the @product@ index. To
this end, most of the search widgets now contain a new widget configuration
option, _Federated Search Key_. This is the key (usually equal to the index
name) where you would like the widget's search functions to apply.

## New Search Admin Functionality

Control Panel &rarr; Configuration &rarr; Search

### Synonyms

Add a list of synonyms. At search time, the list is parsed to match results for
synonymous search keywords.

### Pinned Results

Pin search results to ensure that, when matched during a search request, they're
always placed at the top of the results list.

### Search Engine Info

Improved Search Engine information, showing the client and node information as
well as the vendor and operation mode.

### Field Mappings

Field Mappings for all indexes in the search engine. IS THIS IN CE TOO? I'M
LOOKING AT MASTER-PRIVATE

### Indexing Progress

Indexing now displays a progress bar so you can see right in the UI when the
reindexing action has completed.

## New System Settings

The Search System Settings are found at Control Panel &rarr; Configuration
&rarr; System Settings &rarr; Search.

There's a new entry in the search category: _Title Field Query Builder_. It
gives you some special ability to configure how search responds to matches on
the Title field of a document.

**Exact Match boost:** Give an additional boost when searched keywords exactly
match the Title field of a document.

**Maximum Expansions:** Limit the number of unique titles to return when the
searched keywords match a title as a phrase prefix. WHAT DOES THIS MEAN?

## New Infrastructure

You'll notice more search indexes in @product-ver@. That's because you can now
configure application-specific indexes. At the time of this writing, the
additional indexes are all related to the DXP Workflow Metrics feature. More
will likely appear in future versions, and third party developers can use the
`portal-search` APIs to create their own indexes. It's under development, so
visit the [Search Framework
documentation](/docs/7-2/frameworks/-/knowledge_base/f/search) 
frequently to discover new search
infrastructure changes that expose more functionality for developers to use in
your code.

In addition to application-specific indexes, other enhancements to the search
infrastructure include:

- Low level indexing and queries
- Operations directly on documents (no need for the Indexer framework)

## Multi-Language Search

@product@'s support for multi-language search took a step forward, with
improvements to Documents and Media and Web Content. More improvements are
necessary in this area and will be prioritized in future releases. See the
[Multi-Language Search article for more
information](/docs/7-2/user/-/knowledge_base/u/searching-for-localized-content). 

<!-- At time of writing 2 days before code freeze, not yet in master-private-->
## Search

