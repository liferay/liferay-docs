# Faceted Search [](id=faceted-search)

To get started using faceted search, enter a search term in the Search
application's search bar. You'll see a page with results on the right and a
collection of *facets*, with the number of search results for each facet (in
parentheses) on the left. 

![Figure 1: *Sites* and *Asset Entries* are two of the facet sets you'll encounter. They let you drill down to results that contain the search terms you entered.](../../images/search-faceted-search.png)

Facets allow users of the Search application to filter search results. Think of
facets as umbrellas, under which search results take shelter. You might want to
see the results under all the umbrellas, but after scanning the results, you
might decide that the results under just one of the umbrellas better represent
what you're looking for. So what facets are included in @product by default?

- Site
- Asset type
- Asset tag
- Asset category
- Folder
- User
- Modified date

To configure the Search application's facets, click the Search application's
options menu (![Options](../../images/icon-options.png)) and select
*Configuration*. The tab displayed by default is *Display Settings*, and after
the Scope setting, the Facets are listed with a Configure link you can click to
expand the list of configurations for a given facet.

![Figure 2: Click a facet's *Configure* link to expand its list of settings.](../../images/search-facet-configuration.png)

So how do users make the most of facets?

## Using Facets [](id=using-facets)

After a search is executed, clicking on a specific site from the Site facet
filters the search results to only display assets within the specified site.
Clicking on a specific user filters the search results to only display assets
added by the specified user. The frequency with which the term was found for
each facet is listed in parentheses after the facet. Sometimes, an [asset's
tags or categories](/discover/portal/-/knowledge_base/7-0/organizing-content-with-tags-and-categories)
may be helpful to you, and there are facets for those, too.

![Figure 3: Asset tag facets let you see how many assets contain the terms for which you searched *and* contain certain tags. Click on a specific tag to see only content to which the tag has been applied.](../../images/faceted-search-tags.png)

+$$$

**Example:** Pretend you're an accomplished oboe player (maybe you really are,
but it doesn't matter, showoff), and you're visiting a site for classical
musicians. You remember reading a great technical analysis of Johann Bach's
compositions, but you forgot to bookmark it (or would it be a *bachmark*?). You
enter the term *bach* into the search bar, and, because Johann Bach was a very
important and famous composer, you get lots of results: too many, in fact. At
first you're discouraged but you remember that there's a site member who
produces most of the site's good technical content, who's named *back2bach*. You
see that his name is listed in the User facet, and there aren't many results in
the facet count (the number in parentheses next to the facet).  You click into
the facet and quickly find the content you were looking for.

![Figure 4: When presented with lots of search results, facets are used to narrow down the results list so users can find relevant content.](../../images/search-facets1.png)

$$$

Clicking on a facet narrows down the search results. It's added to the filter
list and the results to the right are refined by the selected facets. If you
need to narrow the results further, click another facet (do this as much as you
want to drill down into the search results). To remove any of the facets from
the filtering of results, click the *Any...* link for the facet type.

The default facet behavior is quite useful, but you can configure the facets to
your liking.

## Facet Settings [](id=facet-settings)

Facets can be configured to a considerable degree. The following configuration
options are available:

**Display Facet:** Specifies whether the facet appears in search results. 

**Weight**
: A floating point (or double) value used to determine the order facets appear
in the search application. Facets with the largest values are positioned at the
top.

**Frequency Threshold**
: The minimum frequency required for terms to appear in the result list. For
example, if the frequency threshold of a facet is set to `3`, a term appearing
twice won't appear in the term result list.

**Max Terms**
: The maximum number of terms included in the search result regardless of how
many matching terms are found for the facet.

**Show Asset Count**
: Display the number of terms returned for the facet in the search results.

**Current/Available Assets (Asset Type facet only)**
: Add or remove asset types to be included in the Asset Type facet.

**Display Style (Tag facet only)**
: Display tags as a Cloud or List (default).

**Label (Modified Date facet only)**
: The language key used for localizing the title of the facet when it's
rendered.

**Range (Modified Date facet only)**
: Define an interval within all the matching terms frequencies' are summed.

## Asset Tags and Categories [](id=asset-tags-and-categories)

If tags or categories have been applied to any asset that appears in the result
set, it may be displayed in the Asset Tag or Asset Category facet, respectively.
Similarly to asset types, not all tags necessarily appear. There may be many
more than the 10 tags listed, but the default configuration for this facet is to
show the top 10 most frequent terms. As with asset types, this can be modified
by setting the `Max Terms` property, described above.

There's no use in having great content if your site's users can't find it. The
search functionality included in @product@ is robust, configurable, and easy to
use. It accounts for cases that include an overwhelming number of results, very
few results, and anything in between.
