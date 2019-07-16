# Low Level Search Options

Low level search is a new concept in @product@ version 7.2: it's a search that
doesn't go through the [Search and Indexing
Framework](/docs/7-2/frameworks/-/knowledge_base/f/model-entity-indexing-framework),
which is infrastructure used for searching documents in the Liferay Index.

A common use case for a low level search is to search a different index. If you
have an index you'd like to search that is in the same Elasticsearch cluster as
the @product@ index, the Low Level Search Options widget lets you add that
search activity as part of the overall search from the Search Application's
search bar.

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

![Figure x: The Low Level Options widget has several configuration options.](../../images/search-lowlvl-options.png)


**Indexes:**
: Enter the comma-separated names of the indexes to search. Do not enter the
standard Liferay index name.

**Fields to Return:**
: Enter the names of the stored fields to be returned from the search engine, in
a comma-separated list. Leave it blank to return all stored fields are returned.

**Contributors to Include:**
: Enter the ids of registered search contributors to be included at this search,
in a comma-separated list. If not set, all registered search contributors are
applied.

**Contributors to Exclude:**
: Enter the ids of registered search contributors to be excluded from this
search, in a comma-separated list. If not set, all registered search
contributors are applied.

**Federated Search Key:**
: Enter the key of an alternate search this widget is participating on. If not
set, this widget will participate on the default search. This value will
typically be the name of an application-defined index.







