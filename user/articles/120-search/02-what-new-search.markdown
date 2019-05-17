# What's New in Search

## New Widgets

### Custom filter

### Sort

### Search Insights

Search Insights isn't new, but it's improved. In past versions it showed you
the full query string sent to the search engine, but now it also displays the
response from the search engine, and an explanation of the score for each
search hit.

## New Search Admin Functionality

Control Panel &rarr; Configuration &rarr; Search

### Search Engine Info

Improved Search Engine information, showing the client and node information as
well as the vendor and operation mode.

### Field Mappings

Field Mappings for all indexes in the search engine. IS THIS IN CE TOO? I'M
LOOKING AT MASTER-PRIVATE

### Indexing Progress

Indexing now displays a progress bar so you can see right in the UI when the
reindexing action has completed.

<!-- ###Custom Rankings UI? Will it make it in?-->

## New System Settings

Control Panel &rarr; Configuration &rarr; System Settings &rarr; Search

NOTE TO SME: Nothing here. I surveyed the top level headings but didn't dive into each System
Setting entry for search to discover new settings

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

