# Site Facet [](id=site-facet)

The Site Facet narrows search results down to those existing in a certain site.
Each Site with content matching the searched keyword appears as a facet term.

![Figure 1: Each Site with matching content is a facet term.](../../../images/search-site-facet.png)

For the Site Facet to display multiple sites, the search bar must be configured
to search *Everything*. See more about search scope
[here](/discover/portal/-/knowledge_base/7-1/searching-for-assets#configuring-the-search-bar).
If not searching for everything, only the current site is searched, and the Site
Facet has nothing to display. When this occurs, the Site Facet is hidden on the
page.

Note that all search bar portlet instances pointing to the search page,
including the one on the search page, must be configured to search for
Everything. The global Search Bar widget embedded on each page by default is
only one instance, so configuring it once is sufficient. If there are
several separate Search Bar instances throughout Site Pages, they must be
configured individually to use the Everything scope. 

The Site Facet contains several configuration options:

![Figure 2: The Site Facet is configurable.](../../../images/search-site-facet-config.png)

**Site Parameter Name**
: Set the URL parameter name for the Facet. The default is *site*. Searching for
*lunar resort* and clicking on a site facet produces the URL

    http://localhost:8080/web/guest/search?q=lunar resort&site=20126

**Max Terms**
: Set the maximum number of facet terms to display, regardless of how many
matching terms are found for the facet.

**Frequency Threshold**
: Set the minimum frequency required for terms to appear in the list of facet
terms. For example, if the frequency threshold of a facet is set to `3`, a term
with two matching results doesn't appear in the term result list.

**Display Frequencies**
: Choose whether or not to display the term frequencies.

