# Sorting Search Results with the Sort Widget

The Sort widget gives Users configurable control over the order of returned
results, no code necessary.

Add it to a page and begin sorting results.

By default, results are sorted by the [relevance
score](https://www.elastic.co/guide/en/elasticsearch/guide/master/scoring-theory.html)
returned by the search engine. Users can choose from one of the out-of-the-box
alternative sorting strategies or configure you own. The alternatives complete
the statement

Order results in these ways:

- alphabetically by Title
- by the Modified date (oldest first)
- by the Create date (newest first)
- by the Create date (oldest first)
- alphabetically by the User that created each matching asset

If the out-of-the-box alternatives aren't enough, an administrator can can
create additional sort options from the widget's configuration.

If you'd rather a Sort option not appear to the search Users, remove it from the
widget's configuration.

## Adding and Configuring the Sort Widget

To get started with the Sort widget,

1. Open the Add menu (![Add](../../images/icon-add-widget.png)) for the page and
   expand the Widgets section.

2.  From the Search section, drag a Sort widget onto the page.

![Figure x: Users can re-order search results with the Sort widget.](../../images/search-sort.png)

### Configuring the Sort Widget

Three things can be done from the Configuration screen:

- Editing existing Sort options
- Deleting options
- Adding options

![Figure x: From the Sort widget's configuration, add, edit, or remove Sort
options.](../../images/search-sort-configuration.png)

To access the widget configuration screen, open the widget Options menu (![Options](../../images/icon-app-options.png)) and click _Configuration_.

Each Sort option has two fields: _Label_ and _Field_.

**Label**
: Set the displayed label for the type of sort being configured.

**Field**
: The `fieldName` of the indexed field to provide sorting by. This must be a
[keyword](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/keyword.html)
field.

To edit an existing option, edit the text in its configuration section.

To delete an existing option, use the minus symbol below its _Field_
configuration.

To add an existing option, use the plus symbol below any option's _Field_
configuration. The order of options here in the configuration screen matches the
order Users see in the select list while configuring the widget for their
search.
