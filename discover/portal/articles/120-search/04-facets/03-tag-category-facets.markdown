# Tag and Category Facets [](id=tag-and-category-facets)

If tags or categories were applied to an asset appearing in the result set,
they're displayed in the Tag or Category facet, respectively. Like other facets
with the Frequency Threshold configuration option, not all tags necessarily
appear. By default the top 10 tags or categories are listed.

![Figure 1: Each Tag or Category with matching content is a facet term.](../../../images/search-tag-facet.png)

Tag and Category Facets contain identical configuration options:

![Figure 2: Tag and Category Facets are configurable.](../../../images/search-tag-facet-config.png)

**Tag/Category Parameter Name**
: Set the URL parameter name for the Facet. The default is *tag*/*category*.
Searching for *lunar resort* and clicking on a *moon* Tag Facet term produces
the URL

    http://localhost:8080/web/guest/search?q=lunar resort&tag=moon

**Display Style**
: Choose whether to display the facet terms in Cloud or List style.

**Max Terms**
: Set the maximum number of facet terms to display, regardless of how many
matching terms are found for the facet.

**Frequency Threshold**
: Set the minimum frequency required for terms to appear in the result list. For
example, if the frequency threshold of a facet is set to `3`, a term with two
matching results doesn't appear in the term result list.

**Display Frequencies**
: Choose whether or not to display the term frequencies.

