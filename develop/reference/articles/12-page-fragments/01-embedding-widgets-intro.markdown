# Embedding Widgets in Page Fragments

You can embed both a selection of Liferay widgets and your own custom widgets in Page Fragments. For a more information on embedding custom widgets, see the [Fragment Specific Tags](/develop/tutorials/-/knowledge_base/7-1/fragment-specific-tags) tutorial.

## Embedding Liferay Widgets

Many Liferay widgets can be embedded in Page Fragments. Each embeddable widget
has a specific tag for use in fragments which looks like `<lfr-widget-[widget-name]>`. When you embed the widget, the complete opening and closing tags must be used like this:

    <lfr-widget-[widget-name]>
    </lfr-widget-[widget-name]>

Here is the full list of widgets that can be embedded:

| Widget Name    | Tag |
| -------- | --- |	
|Blogs          |`<lfr-widget-blogs>`              |
|DDL Display	|`<lfr-widget-dynamic-data-list>`  |
|Form           |`<lfr-widget-form>`               |
|Message Boards	|`<lfr-widget-message-boards>`     |
|Recent Bloggers|`<lfr-widget-recent-bloggers>`|
|Page Comments	|`<lfr-widget-page-comments>`      |
|Page Flags	    |`<lfr-widget-page-flags>`         |
|Page Ratings	|`<lfr-widget-page-ratings>`       |
|Asset Publisher|`<lfr-widget-asset-list>`     |
|Breadcrumb	    |`<lfr-widget-asset-breadcrumb>` |
|Categories Navigation |`<lfr-widget-asset-categories-nav>` |
|Flash	|`<lfr-widget-asset-flash>`|
|Knowledge base Article	|`<lfr-widget-asset-kb-article>`|
|Knowledge base Display	|`<lfr-widget-asset-kb>`|
|Knowledge base Search	|`<lfr-widget-asset-kb-search>`|
|Media Gallery	|`<lfr-widget-asset-media-gallery>`|
|Navigation Menu	|`<lfr-widget-asset-nav>`|
|Nested Portlets	|`<lfr-widget-asset-nested-portlets>`|
|Polls Display	|`<lfr-widget-asset-polls>`|
|Related Assets	|`<lfr-widget-asset-related-assets>`|
|Site Map	|`<lfr-widget-asset-site-map>`|
|Tag Cloud	|`<lfr-widget-asset-tag-cloud>`|
|Tags Navigation	|`<lfr-widget-asset-tags-nav>`|
|Web Content Display	|`<lfr-widget-asset-web-content>`
|Rss Publisher (Deprecated)	|`<lfr-widget-asset-rss>`|
|Weather	|`<lfr-widget-asset-weather>`|
|Iframe	|`<lfr-widget-asset-iframe>`|
|Folder Facet	|`<lfr-widget-asset-search-facet-folder>`|
|Search Bar	|`<lfr-widget-asset-search-bar>`|
|Search Insights	|`<lfr-widget-asset-search-insights>`|
|Search results	|`<lfr-widget-asset-search-results>`|
|Site Facet	|`<lfr-widget-asset-search-facet-site>`|
|Type Facet	|`<lfr-widget-asset-search-facet-type>`|
|User Facet	|`<lfr-widget-asset-search-facet-user>`|
|Sign in	|`<lfr-widget-asset-sign-in>`|
|Wiki	|`<lfr-widget-asset-wiki>`|

