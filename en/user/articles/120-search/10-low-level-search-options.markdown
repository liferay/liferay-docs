---
header-id: low-level-search-options-searching-additional-or-alternate-indexes
---

# Low Level Search Options: Searching Additional or Alternate Indexes

[TOC levels=1-4]

Low level search is a new concept in @product@ version 7.2: it's a search that
doesn't go through the 
[Search and Indexing Framework](/docs/7-2/frameworks/-/knowledge_base/f/model-entity-indexing-framework),
which is infrastructure used for searching documents in the Liferay Index.

A common use case for a low level search is to query an index other than the
@product@ index. By default, 
[Search Pages](/docs/7-2/user/-/knowledge_base/u/configuring-search-pages) search the
@product@ index, but you can also search another index, as long as it's in the
same Elasticsearch cluster (this feature does not work with Solr). 

Add the Low Level Search Options widget to a search page and configure it to
direct the search to the alternate index. To search multiple indexes from the
same page, you can add multiple Low Level Search Options widgets and configure
each one with its own Index Name and Federated Search Key.

Searching alternate indexes is a low level operation that bypasses the @product@
permission checking mechanisms, presenting whatever results the search engine
returns. For this reason, only administrators can add and configure the Low
Level Search Options widget. 

To use the Low Level Search Options widget, add it to a Search Page:

1.  Click the Add menu (![Add](../../images/icon-add-widget.png)) on the page to
    open the Add Widgets menu.

2.  Drag the Low Level Search Options widget (from the Search section), and drop
    it on the page.

It doesn't do anything unless you configure it.

## Configuring Low Level Search

There are several configuration options for the widget. Access them by clicking
the widget Options menu (![Options](../../images/icon-app-options.png)) &rarr;
Configuration, or by clicking the hypertext URL in the widget body:

_Configure additional low level search options in this page._

![Figure 1: The Low Level Options widget has several configuration options.](../../images/search-lowlvl-options.png)

**Indexes:**
: Enter the comma-separated names of the alternative indexes to search. Do not
enter the standard Liferay index name.

**Fields to Return:**
: Enter the names of the stored fields to be returned from the search engine in
a comma-separated list. Leave it blank to return all stored fields.

**Contributors to Include:**
: Enter the ids of registered search contributors to be included in this search
in a comma-separated list of each `SearchRequestContributor`'s Fully Qualified
Class Name (e.g.,
`com.liferay.docs.request.contributor.MySearchRequestContributor`). If not set,
all registered search contributors are applied.

**Contributors to Exclude:**
: Enter the ids of registered search contributors to be excluded from this
search, in a comma-separated list. If not set, all registered search
contributors are applied.

| **Note:** These _Contributors_ are components implementing the
| `com.liferay.portal.search.spi.searcher.SearchRequestContributor` interface
| (provided by the `com.liferay.portal.search.spi` artifact), which is an
| extension point (SPI) that intercepts search requests and adds query parts.

**Federated Search Key:**
: Enter the key of an alternate search this widget is participating in. If not
set, this widget participates in the default search. This value is usually the
name of an application-defined index.

## Example: Searching an Alternate Index 

1.  Whether testing on the default search page or creating a new 
    [Search Page](/docs/7-2/user/-/knowledge_base/u/configuring-search-pages), include
    the following widgets (removing extra widgets simplifies the exercise, but
    is not required for it to work):

    - Low Level Search Options
    - Custom Filter
    - Search Bar
    - Search Results

2.  Configure all the widgets to participate in an alternate search, by opening
    the widget's Options menu (![Options](../../images/icon-app-options.png)) and
    clicking _Configuration_. For each, enter _liferay-0_ in the Federated
    Search Key setting.

    All the search widgets expected to react appropriately to the alternate
    search must be configured with the Federated Search Key. The following steps
    detail additional configuration.

3.  Make an additional configuration in the Low Level Search Options widget,
    adding the index name of the alternate index:

    Enter at least one index name in the _Indexes_ setting. To follow this
    example, use _liferay-0_.

4.  Configure the Custom Filter to use the search bar's default query parameter
    (*q*) and add a query to the search:

    Enter _title_ under field name to add the title field to the query.

    Choose a Filter Query Type (e.g., Match) for the field.

    Since you're overriding the default query to search an alternate index,
    there's nothing in the query by default. Add any query clauses using the
    Custom Filter widget(s).

If you're using _liferay-0_ in your Federated Search Key and Indexes settings,
search for _dynamic_ in the search bar. You'll see results like this:

![Figure 2: Configure the search page to search a different index.](../../images/search-federated.png)

Now you're able to configure the out of the box search widgets to participate in
searches against any Elasticsearch index in the cluster.
