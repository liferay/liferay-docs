# Faceted Search and Customized Search Filtering

Faceted search is a search mechanism that allows search results to be narrowed
down by applying a set of filters to the result of a search query. Liferay's
Search portlet supports faceted search. Its default configuration contains
several facets including site, asset type, tag, category, folder, user, and
modified date. When you use Liferay's Search portlet to perform a search, a
result set is displayed. You can refine your search by clicking on one or more
facets to apply a search filter. For example, the Search portlet displays
results from any site, by default. In the Search portlet, you can click on the
name of a specific site to filter the search to display only results from the
selected site. In addition to using faceted search in the Search portlet,
Liferay transparently uses faceted search in other places throughout the portal.
In this tutorial, you'll learn how to create custom facets and configure them in
Liferay's Search portlet. You'll also learn how to use Liferay's search API to
create custom search filters that aren't suited to being implemented as facets.

## Configuring Custom Facets

There are two ways to configure custom facets for search in Liferay:

1. Configure custom facets in Liferay's Search portlet JSON configuration.

2. Use Liferay's search API to programatically create facets and add them to a
   search context.

Setting up facet configurations using a JSON definition is the most flexible
solution since the configuration can be dynamically changed at runtime. However,
this option is only available in Liferay's Search portlet. Programatically
adding facets to a search context allows developers to tightly control how the
search is used. This option is available to developers of custom Liferay
portlets.

Suppose you need to provide a means for users to search only for images in
Liferay. You can easily configure custom facets in Liferay's Search portlet to
achieve this. To configure the Search portlet to search only for images, open
the Search portlet's Configuration window. add the
following two facets to its JSON configuration:

    {
            "displayStyle": "asset_entries",
            "static": true,
            "weight": 1.5,
            "order": "OrderHitsDesc",
            "data": {
                    "values": [
                            "com.liferay.portlet.documentlibrary.model.DLFileEntry"
                    ],
                    "frequencyThreshold": 0
            },
            "className": "com.liferay.portal.kernel.search.facet.AssetEntriesFacet",
            "label": "asset-type",
            "fieldName": "entryClassName"
    },
    {
            "displayStyle": "asset_entries",
            "static": true,
            "weight": 1.5,
            "order": "OrderHitsDesc",
            "data": {
                    "values": [
                            "bmp", "gif", "jpeg", "jpg", "odg", "png", "svg"
                    ],
                    "frequencyThreshold": 0
            },
            "className": "com.liferay.portal.kernel.search.facet.MultiValueFacet",
            "label": "images",
            "fieldName": "extension"
    }

For an explanation of the JSON configuration of Liferay's Search
portlet, including an explanation of this example, please refer to the
[Search portlet documentation](https://dev.liferay.com/discover/portal/-/knowledge_base/6-2/searching-for-content-in-liferay).

Faceted search in Liferay is not restricted to Liferay's Search portlet. If
you're implementing a search feature for a custom Liferay portlet, you can still
implement faceted search by programatically creating facets and adding them to a
search context. Liferay provides several facet implementation classes that
developers can instantiate and use for custom portlet development. These facet
implementation classes belong to Liferay's
`com.liferay.portal.kernel.search.facet` package. They include the following
ones:

- `SimpleFacet`
- `MultiValueFacet`
    - `AssetEntriesFacet`
    - `ScopeFacet`
- `RangeFacet`
    - `ModifiedFacet`

`SimpleFacet`, `MultiValueFacet`, and `RangeFacet` extend `BaseFacet`.
`BaseFacet` is an abstract class that implements the `Facet` interface.
`SimpleFacet`, `MultiValueFacet`, and `RangeFacet` are facet implementation
classes that can be re-used with any indexed fields. You should use
`SimpleFacet` when you're interested in filtering on a single-valued field and
`MultiValueFacet` when you're interested in filtering a multi-valued field. Use
`RangeFacet` when you're interested in filtering by specifying a range of values
for a field. `AssetEntriesFacet` and `ScopeFacet` extend `MultiValueFacet` and
operate on specific indexed fields. `AssetEntriesFacet` operates on the
`entryClassName` field and `ScopeFacet` operates on the `groupId` (or
`scopeGroupId`) field. `ModifiedFacet` extends `RangeFacet` and operates on
another specific indexed field: `modifiedDate`.

Suppose, for example, that you're developing a custom search portlet that should
only search for a specific types of assets in specific sites. The following
example shows how you can add the appropriate facets to your search context and
configure your search context to achieve this:

    Facet assetEntriesFacet = new AssetEntriesFacet(searchContext);
    assetEntriesFacet.setStatic(true);            
    searchContext.addFacet(assetEntriesFacet);

    String[] entryClassNames = { oneCustomEntity.class.getName(), anotherCustomEntity.class.getName() }; // Replace this value
    searchContext.setEntryClassNames(entryClassNames);

    Facet scopeFacet = new ScopeFacet(searchContext);
    scopeFacet.setStatic(true);            
    searchContext.addFacet(scopeFacet);

    long[] groupIds = { oneGroupId, anotherGroupId } // Replace this value
    searchContext.setGroupIds(groupIds);

Remember that `AssetEntriesFacet` only operates on the `entryClassName` field
and `ScopeFacet` only operates on the `groupId` (or `scopeGroupId` field). Also,
recall from the [Search portlet documentation](https://dev.liferay.com/discover/portal/-/knowledge_base/6-2/searching-for-content-in-liferay)
that static facets are not rendered in the UI. Static facets use pre-set values
rather than inputs dynamically applied by users. When programatically
configuring facets that won't be configurable by users, you should declare the
facets to be static. Since static facets aren't configurable by users, you need
to specify the types of assets and group IDs that can be searched. To do this,
you populate the `entryClassNames` and `groupIds` arrays and add them to the
search context via `searchContext.setEntryClassNames(...)` and
`searchContext.setGroupIds(...)` method calls. Any indexed document whose asset
type name does not belong to the `entryClassName` array is filtered out of the
search results. Likewise, any indexed document whose `groupId` does not belong
to the `groupIds` array is filtered out of the search results.

## Customized Search Filtering

Sometimes, you might be required to implement very specific kinds of search
filters. For example, suppose you need to search both web content articles and
only PDF files from the Documents and Media library. This kind of requirement is
not suited to being implemented as facets. Since facets are metrics that are
calculated across an entire result set, using facets' ability to drill down
as a means of filtering will likely lead to poor performance and overly complex
facet configurations. *Drilling down* means manually selecting filters to apply
to a search.

![Figure 1: Here, the user has *drilled down* (filtered the search results) by manually selecting the *Liferay* site and the *Document* asset type.](../../images/drilling-down.png)

If you need to implement specific filters that are not suited to being
implemented as facets, you can still use Liferay's search API. Instead of
adding facets to a search context, you can set boolean clauses on the search
context. That is, instead of using `searchContext.addFacet(Facet facet)`, you
would use `searchContext.setBooleanClauses(BooleanClause[] booleanClauses)`.
This method allows you to pass any number of filter criteria to the search
context as an array of boolean clauses. Filtering implemented this way is
several times for efficient than anything done via the facet API. Another
advantage of the boolean clause API is that it supports features like exclusions
(e.g., `(-field:not_this_value)`) which are not supported by facets.

Consider again the case where you need to search both web content articles and
only PDF files from the Documents and Media library. You could develop a custom
search portlet to satisfy this use case but it's easier to customize the Search
portlet. The following steps explain how to create a JSP hook to customize the
Search portlet to satisfy this use case.

1. Create a new Liferay hook project. To create a new Liferay hook project using
   Liferay IDE, select *File* &rarr; *New Liferay Plugin Project*, select the
   *Hook* plugin type, then click *Finish*.

2. If you're using Liferay IDE, right-click on your project in the Package
   Explorer and select *New Liferay Hook Configuration*. Check the *Custom JSPs*
   box and click *Next*. Leave the Custom JSP folder set to `/custom_jsps` and
   click *Add from Liferay...* next to JSP files to override. Select
   `html/portlet/search/init.jsp` and `html/portlet/search/main_search.jsp`,
   then click *Finish*.

    If you're not using Liferay IDE, create a `liferay-hook.xml` file in your
    project's `docroot/WEB-INF` folder and add the following contents to it:

        <?xml version="1.0"?>
        <!DOCTYPE hook PUBLIC "-//Liferay//DTD Hook 6.2.0//EN" "http://www.liferay.com/dtd/liferay-hook_6_2_0.dtd">

        <hook>
                <custom-jsp-dir>/custom_jsps</custom-jsp-dir>
        </hook>

    Then create a `custom_jsps` directory in your project's `docroot` folder and
    add copy the `html/portlet/search/init.jsp` and
    `html/portlet/search/main_search.jsp` files into this folder from Liferay's
    source.

3. Edit your `docroot/custom_jsps/html/portlet/search/main_search.jsp` file and
   find the following line:

        searchContext.setStart(mainSearchSearchContainer.getStart());

    Add the following lines just below the line above:

        Query stringQuery = StringQueryFactoryUtil.create("entryClassName:com.liferay.portlet.journal.model.JournalArticle (+entryClassName:com.liferay.portlet.documentlibrary.model.DLFileEntry +extension:pdf)");

        BooleanClause clause = BooleanClauseFactoryUtil.create(searchContext, stringQuery, BooleanClauseOccur.MUST.getName());

        searchContext.setBooleanClauses(new BooleanClause[] {clause});

    In the query that you construct above, you're specifying that you're
    searching for indexed documents with either an `entryClassName` equal to
    `JournalArticle` or both an `entryClassName` equal to `DLFileEntry` and an
    `extension` equal to `pdf`. For more information on Lucene query syntax,
    please refer to Lucene's [documentation](http://lucene.apache.org/core/3_5_0/queryparsersyntax.html).

4. Then edit your `docroot/custom_jsps/html/portlet/search/main_search.jsp` file
   to add the required imports.

    Find the following line:

        <%@ page import="java.util.LinkedList" %>

    Add the following lines just below the line above:

        <%@ page import="com.liferay.portal.kernel.search.SearchContext" %>
        <%@ page import="com.liferay.portal.kernel.search.BooleanClause" %>
        <%@ page import="com.liferay.portal.kernel.search.BooleanClauseFactoryUtil" %>
        <%@ page import="com.liferay.portal.kernel.search.BooleanClauseOccur" %>
        <%@ page import="com.liferay.portal.kernel.search.StringQueryFactoryUtil" %>
        <%@ page import="com.liferay.portal.kernel.search.Query" %>

When you're finished following the above steps, deploy your hook plugin and test
the Search portlet. Check that it only returns web content articles or documents
and media files with the `.pdf` file extension as search results.
