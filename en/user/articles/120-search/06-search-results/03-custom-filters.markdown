---
header-id: filtering-search-results-with-the-custom-filter-widget
---

# Filtering Search Results with the Custom Filter Widget

[TOC levels=1-4]

You often need to exert control over the displayed search results. One viable
approach is to develop your own search portlets using the @product@ APIs. That
can be overkill if you just want to make a slight modification to how the search
is executed, so many of the out-of-the-box search widgets give you this type of
control without coding anything (Search Options, Custom Facet, and more). In
@product-ver@, new widgets have been added: Sort and Custom Filter.

With Custom Filters, you can contribute queries to the main search query,
exerting control over the search results. Make the filter widgets visible or
invisible to the search Users, and decide if they're changeable or immutable.

To explore all the options you have with the Custom Filter widget, you need one
on the page.

## Adding and Configuring Custom Filters

To get started with Custom Filters,

1. Open the Add menu (![Add](../../../images/icon-add-widget.png)) for the page and
   expand the Widgets section.

2.  From the Search section, drag a Custom Filter onto the page.

![Figure 1: A custom filter has no impact until it's configured.](../../../images/search-custom-filter.png)

Custom filters can do so many things, it's impossible to list them all. What
follows is a widget configuration tour. Separate documentation will be written
to provide a how-to demonstration of Custom Filters.

### Custom Filter Configuration Options

Open the widget Options menu (![Options](../../../images/icon-app-options.png)) and
click _Configuration_.

![Figure 2: Once the Custom Filter is added to the page, mold it like soft clay into the beautiful sculpture you've envisioned.](../../../images/search-custom-filter-configuration.png)


**Filter Field (text)**
: Most often, filters operate on a specific field. Set the name of the indexed
field to be filtered (for example, `title`). You won't need this if the Filter
Query Type is set to a type that doesn't require a field, such as _Regexp_.

> The Query String and Script queries do not require a Filter Field to be set.
> All other queries require at least one field. 
<!--Note: Multi Match and Simple Query String take an array of fields accoring to the Elasticsearch docs, but our config doesn't seem to support it afaict.? -->

**Filter Value (text)**
: For most filters, you must enter a text value here that specifies the
text to apply the filter on in the specified field (for example, set a _Match_
query to the text _street_ on the `title_en_US` field). Some Filter Query Types
require special notation, as in the case of the _Regexp_ filter. 

**Filter Query Type (select list)**
: Select the query type to filter results by. Available types include Bool,
Exists, Fuzzy, Match, Match Phrase, Match Phrase Prefix, Multi Match, Prefix,
Query String, Regexp, Script, Simple Query String, Term, Wildcard. To learn more
about these queries, visit the [Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl.html).

**Occur (select list)**
: Set the occurrence type for the query being contributed to the search. Options
include Filter, must, must_not, and should. To understand each type, see the
[Elasticsearch
documentation](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl-bool-query.html). 

**Query Name (text)**
: Set the name of the contributed query. This is unnecessary unless this filter
acts as a parent query to another filter that contributes child clauses; in
that case set this filter's Query Name as the child filter's Parent Query Name.
This parent/child behavior is only available for filters of type Bool.

**Parent Query Name (text)**
: When contributing a child clause to a Bool query, set this to match the Query
Name configured in the parent Custom Filter widget. Otherwise, leave it blank.

**Boost (number)**
: [Boost](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl-term-query.html#term-field-params)
the score of the results matching this query. Specify any whole or decimal
number here that makes sense. If you always want results matching this at the
top, set the Boost value really high (e.g., _1000_).

**Custom Heading (text)**
: Enter the heading to display for this filter. If not set, the Filter Field's
value is displayed.

**Custom Parameter Name (text)**
: Specify a URL parameter name for the filter. If not set, the Filter Field's
value is used.

**Invisible (boolean)**
: If checked, the widget is invisible to regular users. The Filter Value from
the configuration is applied by default, but users can still filter for other
values via URL Parameter. Don't worry, you can shut that down if you need to
with the Immutable setting (see below).

**Immutable (boolean)**
: Enable this to ensure that the Filter Value cannot be changed by regular
users. The widget becomes invisible to them _and_ filter values set via URL
parameters are not accepted. The Filter Value set in the widget configuration is
applied at all times (unless it's disabled).

**Disabled (boolean)**
: If checked, the query is ignored and doesn't participate in searches. This
gives you a quick way to stop the filter, but keep the configuration so it can
be re-enabled later.

**Federated Search Key (text)**
: Enter the key of an alternate Search this widget is participating on. If it's
set, be aware that the default @product@ index isn't searched at all. If not
set, this widget participates on the default search. Values in this field
typically match the name of an application-defined index.

There are many use cases you can satisfy by incorporating Custom Filters into
your search page. Two demonstrative articles are planned to show you some of
the filtering capabilities at your disposal:

- _Refine to One_ (or _Needle in a Haystack_) will show you how to add
  user-operated filters to the page so results can be refined down to just the
  result they were looking for.

- _Complex filtering_ shows you some more advanced filters and how they work.

Check out the Custom Filter and see what it adds to your search page.

