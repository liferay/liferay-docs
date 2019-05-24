---
header-id: custom-filters
---

# Custom Filters

Sometimes you need to exert control over the displayed search results. A viable
approach is to develop your own search portlets using the @product@ APIs.
However, that can be overkill if you just want to make a slight modification to
how the search is executed. Many of the search widgets are designed to give you
this type of control (Search Options, Custom Facet, and more). In @product-ver@,
new widgets have been added: Sort and Custom Filter.

With Custom Filters, add queries that will be contributed to the main search
query, and applied to the search results. These filters can either be visible or
invisible to the Users of your search page, and they can be change-able by end
Users or immutable.

To explore all the options you have with the Custom Filter widget, you need one
on the page. Let's start there.

## Adding and Configuring Custom Filters

To get started with Custom Filters,

1. Open the Add menu (![Add](../../images/icon-add-widget.png)) for the page and
   expand the Widgets section.

2.  From the Search section, drag a Custom Filter onto the page.

![Figure x: A custom filter has no impact until it's configured.](../../images/search-custom-filter.png)

Once it's on the page, mold it like soft clay into the beautiful sculpture
you've envisioned. Maybe it's not that flexible, but there are so many things
you can do with Custom Filters it's impossible to list them all. What follows is
a tour of the options you can configure, to give you a better idea of the power
of Custom Filters.

### Custom Filter Configuration Options

Open the widget Options menu (![Options](../../images/icon-app-options.png)) and
click _Configuration_.

**Filter Field (text)**
: Most often, filters operate on a specific field. Set the name of the indexed
field to be filtered (for example, `title`). You won't need this if the Filter
Query Type is set to a type that doesn't require a field, such as _Regexp_.

**Filter Value (text)**
: For most filters, you'll need to enter a text value here that specifies the
text to apply the filter on in the specified field (for example, set a _Match_
query to the text _street_ on the `title_en_US` field). Some Filter Query Types
require special notation, as in the case of the _Regexp_ filter. 

**Filter Query Type (select list)**
: Select the query type to filter results by. Available types include Bool,
Exists, Fuzzy, Match, Match Phrase, Match Phrase Prefix, Multi Match, Prefix,
Query String, Regexp, Script, Simple Query String, Term, Wildcard. To learn more
about these queries, visit the [Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/query-dsl.html).

**Occur (select list)**
: Set the occurrence type for the query being contributed to the search. Options
include Filter, must, must_not, and should. To understand each type, see the
[Elasticsearch
documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/query-dsl-bool-query.html). 

**Query Name (text)**
: Set the name of the contributed query. This is unnecessary unless you're
planning to use other custom filters to contribute child clauses, using this as
the Parent Query. This parent/child behavior is only available for filters of
type Bool.

**Parent Query Name (text)**
: When contributing a child clause to a Bool query, set this to match the Query
Name configured in the parent Custom Filter widget. Otherwise, leave it blank.

**Boost (number)**
: Boost the score of the results matching this query. Specify a whole or decimal
number here. Although the field is not validated, certain values make no sense,
so be careful to enter sensible numbers. For example, 1,353 isn't sensible, but
1.5 is.

**Custom Heading (text)**
: Enter the heading to display for this filter. If not set, the Filter Field's
value is displayed.

**Custom Parameter Name (text)**
: Specify a URL parameter name for the filter. If not set, the Filter Field's
value is used.

**Invisible (boolean)**
: If checked, the widget is invisible to regular users. The Filter Value from
the configuration is applied by default, but users can still filter for other
values via URL Parameter. Don't worry, you can shut that down if you need to.

**Immutable (boolean)**
: Enable this to ensure that the Filter Value cannot be changed by regular
users at all. The widget becomes invisible to them, and filter values set via URL
parameter are not accepted. The Filter Value set in the widget configuration is
applied at all times.

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
your search page. Two how-to articles will be documented to show you some of
the capabilities at your disposal:

- _Refine to one_ will show you how to add user-operated filters to the page so
    results can be refined down to just the result they were looking for.

- _Complex filtering_ shows you some more advanced filters and how they work.

Check out the Custom Filter and see what it adds to your search page.

STOP REVIEWING HERE

---

### Refine to one from ticket
Control Panel  Configuration > Search > Reindex all search indexes.

Site Builder > Pages > + > Content Page

(Add Page) Name = Refine to One > Add

Click icon (right side): Widgets

Search > Add: Search Bar, Search Results, Search Options

Click icon (right side): Section Builder

Layouts > Add: 4-column Layout (between Search Bar and Search Results)

Click icon (right side): Widgets

Search > Add: Sort, Custom Facet, Custom Filter (one per column of 4-column Layout)

Enter Settings of: Search Bar

Scope = Everything

Save.

Enter Settings of: Search Results

Display Results in Document Form = true

Save.

Enter Settings of: Custom Filter

Filter Field = content_en_US

Filter Value = street

Filter Query Type = Match

Custom Heading = Content must have

Custom Parameter Name = content

Immutable = true

Save.

Enter Settings of: Search Options

Allow Empty Searches = true

Save.

Click button (top right): Publish

Click icon (top right): Exit edit mode

Expected: 118 results, "street" in highlight.

First result, expand: Details...

Find and copy ddm field name for neighborhood, e.g. ddm_text36971_neighborhood_en_US

Click button (top right): Enter edit mode

Enter Settings of: Custom Facet

Custom Heading = Neighborhood

Max Terms = 20

Custom Parameter Name = neigh

Aggregation Field = ddm_text36971_neighborhood_en_US_String_sortable

(important: aforementioned ddm field name for neighborhood, plus suffix `_String_sortable`

Save.

Click icon (right side): Widgets

Search > Add: Custom Filter (last free slot in 4-column layout)

Enter Settings of: Custom Filter #2

Filter Value = ddm_text36971_neighborhood_en_US:ch*

Filter Query Type: Query String

Custom Heading: Free form

Custom Parameter Name = qs

Save.

Click button (top right): Publish

Click icon (top right): Exit edit mode

Expected: 4 results.

Sort by > Title

Search... = globe

Expected: 1 result, "GLOBE" in highlight.

Search... > (leave empty)

Expected: 4 results.

Free form > substitute b* for ch*

Expected: 36 results, "street" in highlight. 6 Neighborhood buckets with b* words.

Search... = today

Expected: 1 result. 1 Neighborhood bucket, "east boston".

---

### Complex Filters from LPS

Initial setup: same as Use Case #1. (Load the Boston dataset, create "Refine to One" page with widgets as described)

Navigate to page: "Refine to One" (if already in page, leave it and return - no parameters should be present in URL)

Click button (top right): Enter edit mode

Click icon (right side): Widgets

Search > Add: Custom Filter

Enter Settings of: Custom Filter just added

Custom Heading = Complex Query

Query Name = complex

Filter Query Type = Bool

Occur = Filter

Invisible = true

Save.

Search > Add: Custom Filter

Enter Settings of: Custom Filter just added

Filter Field = content_en_US

Filter Value = animal

Filter Query Type = Match

Occur = should

Parent Query Name = complex

Custom Parameter Name = either

Save.

Enter Settings of: Custom Filter "Free form"

Filter Value: Substitute west for ch*

Occur = should

Parent Query Name = complex

Save.

Click button (top right): Publish

Click icon (top right): Exit edit mode

Expected: 30 results, either with "animal" highlighted, or from West Roxbury.

Click bucket: "west roxbury"

Expected: 2 results from West Roxbury, even though they miss the word "animal".

Click button (top right): Enter edit mode

Enter Settings of: Custom Filter "Complex Query"

Occur = must_not

Save.

Click button (top right): Publish

Click icon (top right): Exit edit mode

Expected: 88 results, word "animal" missing from all of them, no bucket for "west roxbury".

Search... = animal

Expected: 0 results.

Click button (top right): Enter edit mode

Enter Settings of: Custom Filter "Complex Query"

Occur = should

Boost = 3

Save.

Click button (top right): Publish

Click icon (top right): Exit edit mode

Expected: 118 results, all top hits either have "animal" highlighted, or are from West Roxbury.

Click button (top right): Enter edit mode

Enter Settings of: Custom Filter "content_en_US" "animal"

Disabled = true

Save.

Click button (top right): Publish

Click icon (top right): Exit edit mode

Expected: 118 results, 2 top hits are from West Roxbury, no highlight on "animal".

