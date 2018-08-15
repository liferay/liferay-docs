# Breccia [](id=breccia)

The Breccia site initializer adopts the site model used by most major retailers.
Its home page provides the functionality to search for individual products,
while a series of links connect to product category pages. Familiar cart and
checkout features are also included. Breccia gives you a ready-made theme,
a complete set of customer-facing pages, a sample catalog divided into basic
categories, and populated country and currency lists.

## Theme [](id=theme)

Several functions are embedded in the Breccia theme, including a search bar and
several links. Users can access the site's catalog, their personal accounts, or
their virtual carts. In addition to the search bar, the *Explore Products* menu
allows users to quickly navigate to product categories from any page on the
site.

![Figure 1: The Breccia theme models a basic site that prioritizes ease of navigation.](../../images/breccia-theme.png)

For more on navigating store sites, see [Setting up a Storefront](/web/liferay-emporio/documentation/-/knowledge_base/1-0/setting-up-a-storefront).

## Pages [](id=pages)

Running the Breccia initializer creates several pages and places various
applications---furnished with appropriate display templates---on them. To see
Breccia's pages, go to *Site Menu* &rarr; *Build* &rarr; *Pages*.

![Figure 2: To see a page's layout and widgets, click [options](../../images/icon-kebab-blue-on-white.png) &rarr; *View*.](../../images/breccia-pages.png)

In general, these pages work as described in [Setting up a
Storefront](/web/liferay-emporio/documentation/-/knowledge_base/1-0/setting-up-a-storefront)
. The site's *Catalog* page, however, is a little different. The page contains
a category detail widget, so it is the target of all the links in the site's
*category navigation* widgets (as well those in the embedded *Explore Products*
menu). When a user selects a category from one of those links, she is directed
to the *Catalog* page, which displays content appropriate to the selected
category.

![Figure 3: The *Catalog* page takes whatever shape is called for.](../../images/breccia-catalog-page.png)

The *Catalog* page can also be accessed from the embedded link that appears at
the top of every page. In this case, however, there is no option to filter
results by category. As a result, the category detail widget remains invisible,
and the search results widget displays all of the products in the site's
catalog.

![Figure 4: Search results can be filtered using the facets widgets to the left of the page.](../../images/breccia-facets.png).

Keep in mind that there is no option to sort products by category in this case.
However, you can offer that functionality by dropping a *category facets* widget
onto the page.

![Figure 5: *Category facets* will still appear---displaying a single option---even when the *Catalog* page is only being used to display a single category.](../../images/breccia-category-facet.png)

## Catalog [](id=catalog)

Not to be confused with Breccia's catalog page---a display page for
customers---is the site's actual catalog, which stores data on its products.
Breccia's catalog includes a range of sample products divided into a handful of
categories; these products have been assigned options, specifications, images,
and SKUs.

![Figure 6: Of course, sample products are no help if you're setting up a live store---but they can save you a lot of time if you're working to understand the functionality described in [Catalog Management](/web/liferay-emporio/documentation/-/knowledge_base/1-0/catalog-management)](../../images/breccia-catalog.png).

## Settings [](id=settings)

Breccia populates some of the fields in *Site Menu* &rarr; *Commerce* &rarr;
*Settings*.

**Countries:** Breccia populates the countries list in *Settings* &rarr;
*Countries* tab. With the exception of the US and Italy, *regions* lists are
not populated. See
[Countries](/web/liferay-emporio/documentation/-/knowledge_base/1-0/countries)
for more details.

**Currencies:** The currency list in *Settings* &rarr; *Currencies* tab is
populated with several of the world's most widely-used currencies. See
[Currencies](/web/liferay-emporio/documentation/-/knowledge_base/1-0/currencies)
for information on updating exchange rates and other details.
