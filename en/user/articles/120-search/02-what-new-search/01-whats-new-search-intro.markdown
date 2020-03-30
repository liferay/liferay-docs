---
header-id: whats-new-in-search
---

# What's New in Search

[TOC levels=1-4]

Lots of new and improved search capabilities are present in @product-ver@, from
new widgets to new APIs and infrastructure. 

## New and Improved Widgets

Add search widgets by clicking the Add
(![Add](../../../images/icon-add-widget.png)) icon on the page. Then expand
the Widgets &rarr; Search section.

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
the widget Options (![Options](../../../images/icon-app-options.png)) menu &rarr;
Configuration. Enter a human readable label and the `fieldName` to sort by. Just
make sure it's a `keyword` field.

### Search Insights

**Improved**

Past versions of Search Insights showed you the full query string sent to the
search engine, but now it also displays the response from the search engine with
an explanation of the score for each search hit.

## New Search Admin Functionality

The Search Admin functionality is found in Control Panel &rarr; Configuration
&rarr; Search.

<!-- COMMENTING: Will be added by FP-1d### DXP Only: Synonyms

Add a list of synonyms. At search time, the list is parsed to match results for
synonymous search keywords.
-->
### Search Engine Info

The displayed Search Engine information is enhanced, showing the client and node
information as well as the vendor and operation mode.

### Field Mappings

The Field Mappings tab shows the field mappings for all indexes in the search
engine. 

### Indexing Progress

Indexing now displays a progress bar so you can see in the UI when the
re-indexing action has completed.

## New System Settings

Access the Search System Settings at Control Panel &rarr; Configuration
&rarr; System Settings &rarr; Search.

There's a new entry in the search category: _Title Field Query Builder_. Use it
to configure how search responds to matches on the Title field of a document.

**Exact Match boost:** Give an additional boost when searched keywords exactly
match the `title` field of a document.

**Maximum Expansions:** Limit the number of documents to return when matching
searched keywords to the `title` field as a phrase prefix. See Elasticsearch's
[Match Phrase Query
documentation](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl-match-query-phrase.html)
for more information.

## New Infrastructure

There are some important search infrastructure changes to know about.

### Elasticsearch Support

@product-ver@ supports Elasticsearch 6.5.x through 6.8.x, and 6.5.0 is included
as the embedded version to use for testing out-of-the-box search behavior. See
the 
[deployment guide](/docs/7-2/deploy/-/knowledge_base/d/elasticsearch)
for more information.

### Application-Specific Indexes

You'll notice more search indexes in @product-ver@. That's because you can now
configure application-specific indexes. At the time of this writing, the
additional indexes are all related to the DXP Workflow Metrics feature. More
will likely appear in future versions, and third party developers can use the
`portal-search` APIs to create their own indexes. It's under development, so
visit the [Search Framework
documentation](/docs/7-2/frameworks/-/knowledge_base/f/search) 
frequently to discover new search infrastructure changes that expose more
functionality for developers.

### API Enhancements

Enhancements to the [search
framework](/docs/7-2/frameworks/-/knowledge_base/f/search) APIs include

- Low level indexing and queries
- Operations directly on indexed documents (no need for the Indexer framework)
- New Aggregation types

## Multi-Language Search

@product@'s support for multi-language search took a step forward, with
improvements to Documents and Media and Web Content. More improvements are
necessary in this area and will be prioritized in future releases. See the
[Multi-Language Search article for more
information](/docs/7-2/user/-/knowledge_base/u/searching-for-localized-content). 

