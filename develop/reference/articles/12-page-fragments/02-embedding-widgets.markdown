---
header-id: embedding-widgets-in-page-fragments
---

# Embedding Widgets in Page Fragments

[TOC levels=1-4]

You can embed both a selection of Liferay widgets and your own custom widgets in Page Fragments. For a more information on embedding custom widgets, see the [Fragment Specific Tags](/docs/7-1/tutorials/-/knowledge_base/t/fragment-specific-tags) tutorial.

## Embedding Liferay Widgets

Many Liferay widgets can be embedded in Page Fragments. Each embeddable widget
has a specific tag for use in fragments which looks like `<lfr-widget-[widget-name]>`. When you embed the widget, the complete opening and closing tags must be used like this:

    <lfr-widget-[widget-name]>
    </lfr-widget-[widget-name]>

Here is the full list of widgets that can be embedded:

| Widget Name    | Tag |
| -------- | --- |	
|DDL Display	|`<lfr-widget-dynamic-data-list>`  |
|Form           |`<lfr-widget-form>`               |
|Asset Publisher|`<lfr-widget-asset-list>`     |
|Breadcrumb	    |`<lfr-widget-breadcrumb>` |
|Categories Navigation |`<lfr-widget-categories-nav>` |
|Flash	|`<lfr-widget-flash>`|
|Media Gallery	|`<lfr-widget-media-gallery>`|
|Navigation Menu	|`<lfr-widget-nav>`|
|Polls Display	|`<lfr-widget-polls>`|
|Related Assets	|`<lfr-widget-related-assets>`|
|Site Map	|`<lfr-widget-site-map>`|
|Tag Cloud	|`<lfr-widget-tag-cloud>`|
|Tags Navigation	|`<lfr-widget-tags-nav>`|
|Web Content Display	|`<lfr-widget-web-content>`
|Rss Publisher (Deprecated)	|`<lfr-widget-rss>`|
|Iframe	|`<lfr-widget-iframe>`|


## Embedding Liferay Widgets multiple times in a fragment

If we need to include the same `<lfr-widget-[widget-name]>` more than once in a fragment, we will need to add an `id` attribute with a different value for each repetition

    <lfr-widget-[widget-name] id="myFirstWidget"></lfr-widget-[widget-name]>
    <lfr-widget-[widget-name] id="mySecondWidget"></lfr-widget-[widget-name]>
    
For the id is necessary an alphanumeric value (we can't use symbols like `_`or `-`)
