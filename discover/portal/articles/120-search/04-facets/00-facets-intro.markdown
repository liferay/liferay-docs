# Facets [](id=facets)

Enter a keyword in the Search Bar and click the Search button
(![Search](../../../images/icon-search.png)). The default search experience
redirects to a page with results on the right and a collection of *facets* on
the left. 

![Figure 1: *Site* and *Type* are two of the facet sets you'll encounter.](../../../images/search-faceted-search.png)

A Facet aggregates search results by some common characteristic, with each facet
holding search results that share something in common. After scanning the full
list of results, a User might decide the results from just one facet are more
appropriate (for example, all the results from a particular site, or all the
results that are Blogs Entries). So what facets are included by default?

- **Site Facet** for filtering results by their site. 
- **Type Facet** for filtering results by the Asset Type.
- **Tag Facet** for filtering results by Tag.
- **Category Facet** for filtering results by Category.
- **Folder Facet** for filtering results by Folder.
- **User Facet** for filtering results by the content creator.
- **Modified Facet** for filtering results by the Last Modified Date.
- **Custom Facet** for filtering results by some other indexed field. See
    [here](/discover/portal/-/knowledge_base/7-1/custom-facet) 
    for more information.

Each item in a facet (selected using the checkbox) is called a *Facet Term*
(*term* for short).

In this tutorial, you'll explore how facets and their terms are used and how to
find a facet's configuration. The remaining articles show the configurations
available for each facet.

## Using Facets [](id=using-facets)

If you're not actually an accomplished oboe player, pretend for a moment. You're
visiting a site for classical musicians. You remember reading a great technical
analysis of Johann Bach's compositions, but you forgot to bookmark it (or would
it be a *bachmark*?). You enter the keyword *bach* into the search bar and,
because Johann Bach was a very important and famous composer, you get lots of
results: too many, in fact. At first you're discouraged, but you remember that
there's a site member who produces most of the site's good technical content,
who's named *back2bach*. You see that his name is listed in the User facet, and
there aren't many results in the facet count (the number in parentheses next to
the facet). You click into the facet and quickly find the content you wanted.

![Figure 2: When presented lots of search results, facets narrow down the results list so users can find relevant
content.](../../../images/search-facets1.png)

Clicking on a facet narrows down the search results. It's added to the filter
list in the search query, and the results list is refined by the selected
facets. 

![Figure 3: After clicking the *Web Content Article* type in the Asset Types facet, it's the only asset type listed.](../../../images/search-facet-wc.png).

## Multiple Facet Selection [](id=multiple-facet-selection)

Facet term selections within one facet are additive. Clicking more terms in the
same facet expands the search results, because it's processed as if you want to
see results matching *Term-1* OR *Term-2*, OR etc. To remove all the term
selections from a facet, click the *Clear* link. 

![Figure 4: Facet terms are additive when applied in the same facet. Any Blogs Entry OR Web Content article matching the keyword is shown here.](../../../images/search-multiple-terms.png)

Facet term selections from different facets are exclusive. Clicking facet terms
from multiple facets narrows the results because they're processed as if you
want to see results matching *Facet-1* AND *Facet-2*, AND etc. This is
intuitive. The facets 

![Figure 5: Facet terms selected from different facets are exclusive. These results must be of type Blogs Entry AND be from the User Marvin Smart.](../../../images/search-multiple-facets.png)

Considering a case where you make two term selections in the Type Facet: (Blogs
Entry and Web Content Article), and two term selections in the User Facet (James
Jeffries and Marvin Smart). What results are displayed? 

*Blogs Entries OR Web Content Articles AND authored by James Jeffries OR Marvin
Smart*. 

If Marvin and James each created four pieces of content (two blogs and two Web
Content Articles), all eight would appear in the Search Results. Any Blogs or
Web Content created by other Users are not shown, and assets of other Type
created by Marvin and James are not displayed. Content that isn't Blog Entries
or Web Content Articles created by other Users are obviously not searched.

![Figure 6: Both intra-facet and inter-facet selection is possible.](../../../images/search-facet-selections.png)

+$$$

**Note:** The new Search Facet widgets support the multiple selection of facet
terms. Multiple facet selection is not supported in the classic Search portlet.

$$$

## Facets and Friendly URLs [](id=facets-and-friendly-urls)

In the classic, monolithic Search portlet, URLs like this were not uncommon:

    http://localhost:8080/web/guest/home?_com_liferay_portal_search_web_portlet_SearchPortlet_formDate=1529671834606&p_p_id=com_liferay_portal_search_web_portlet_SearchPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_portal_search_web_portlet_SearchPortlet_mvcPath=%2Fsearch.jsp&_com_liferay_portal_search_web_portlet_SearchPortlet_redirect=http%3A%2F%2Flocalhost%3A7011%2Fweb%2Fguest%2Fhome%3Fp_p_id%3Dcom_liferay_portal_search_web_portlet_SearchPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview&_com_liferay_portal_search_web_portlet_SearchPortlet_keywords=test&_com_liferay_portal_search_web_portlet_SearchPortlet_scope=this-site

The new Search functionality uses friendly search URLs for facet filtering. With
the default settings, here's the default main search URL when searching for
keyword _test_:

    http://localhost:8080/web/guest/search?q=test

Selecting a facet term causes a new parameter to the above URL. For example,
selecting _Blogs Entry_ from the Type facet results in this URL:

    http://localhost:8080/web/guest/search?q=test&type=com.liferay.blogs.model.BlogsEntry

Selecting another facet term from the same facet category appends the same
parameter again, but with the newly selected value:

    http://localhost:8080/web/guest/search?q=test&type=com.liferay.blogs.model.BlogsEntry&type=com.liferay.portal.kernel.model.User

The rest of the facets work the same way. Filtering by the last hour option in
the Last Modified facet portlet produces this URL:

    http://localhost:8080/web/guest/search?q=test&modified=past-hour

The parameter names are configurable for each facet.

Now that you know how facets work, read about configuring each of the included
facets.
