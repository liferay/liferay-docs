---
header-id: installing-a-search-engine
---

# Installing a Search Engine

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

A search engine is a critical component of your @product@ installation. If
you're here, you probably know the basics already and want to configure a
search engine for your @product@ deployment.

Elasticsearch, a highly scalable, full-text search engine, is installed by
default, as an embedded server. Elasticsearch is well-supported and almost
certainly meets any search and indexing need you have, but you must not use the
[embedded version in your production deployment](/docs/7-2/deploy/-/knowledge_base/d/elasticsearch#embedded-vs-remote-operation-mode).

Learn to configure a remote Elasticsearch server or cluster
[here](/docs/7-2/deploy/-/knowledge_base/d/installing-elasticsearch).

[Solr](http://lucene.apache.org/solr)
is another capable and popular search engine supported in @product@.

Learn to configure a remote Solr server or cluster
[here](/docs/7-2/deploy/-/knowledge_base/d/installing-solr). But first, make
sure you understand the disparity in functionality between the supported search
engines.

## Choosing a Search Engine

Elasticsearch and Solr are both supported, but there are limitations to
Liferay's Solr integration. To make use of some features, you must choose
Elasticsearch.

### End User Feature Limitations of Liferay's Solr Integration

- [Liferay Commerce](https://learn.liferay.com/commerce-2.x/index.html)
- [Workflow Metrics](https://help.liferay.com/hc/en-us/articles/360029042071-Workflow-Metrics-The-Service-Level-Agreement-SLA-)
- [Custom Filter search widget](/docs/7-2/user/-/knowledge_base/u/filtering-search-results-with-the-custom-filter-widget)
- [The Low Level Search Options widget](/docs/7-2/user/-/knowledge_base/u/low-level-search-options-searching-additional-or-alternate-indexes)
- [Search Tuning: Customizing Search Results](https://help.liferay.com/hc/en-us/articles/360034473872-Search-Tuning-Customizing-Search-Results)
- [Search Tuning: Synonyms](https://help.liferay.com/hc/en-us/articles/360034473852-Search-Tuning-Synonym-Sets)

### Developer Feature Limitations of Liferay's Solr Integration

Implementation for the following APIs may be added in the future, but they are
not currently supported by Liferay's Solr connector.

- From Portal Core (Module: `portal-kernel`, Artifact:
    `com.liferay.portal.kernel`):
    - `com.liferay.portal.kernel.search.generic.NestedQuery`
    - `com.liferay.portal.kernel.search.filter`:
        - `ComplexQueryPart`
        - `GeoBoundingBoxFilter`
        - `GeoDistanceFilter`
        - `GeoDistanceRangeFilter`
        - `GeoPolygonFilter`
- From the Portal Search API (Module: `portal-search-api`, Artifact:
    `com.liferay.portal.search.api`):
    - `com.liferay.portal.search.filter`:
        - `ComplexQueryPart`
        - `TermsSetFilter`
    - `com.liferay.portal.search.geolocation.*`
    - `com.liferay.portal.search.highlight.*`
    - `com.liferay.portal.search.query.function.*`
    - `com.liferay.portal.search.query.*`:
    - `com.liferay.portal.search.script.*`
    - `com.liferay.portal.search.significance.*`
    - `com.liferay.portal.search.sort.*`: only `Sort`,`FieldSort`, and
        `ScoreSort` are supported
- Portal Search Engine Adapter API (Module: `portal-search-engine-adapter-api`,
    Artifact: `com.liferay.portal.search.engine.adapter.api`)
    - `com.liferay.portal.search.engine.adapter.cluster.*`
    - `com.liferay.portal.search.engine.adapter.document.UpdateByQueryDocumentRequest`
    - `com.liferay.portal.search.engine.adapter.index.*`: only `RefreshIndexRequest` is supported
    - `com.liferay.portal.search.engine.adapter.search.*`:
        - `MultisearchSearchRequest`
        - `SuggestSearchRequest`
    - `com.liferay.portal.search.engine.adapter.snapshot.*`

Liferay Commerce requires the `TermsSetFilter` implementation, only available
in the Elasticsearch connector.

### Elasticsearch Java Distribution Compatibility

Another factor to consider in your search engine selection is JDK version. The
search engine and @product@ must use the same Java version and distribution
(e.g., Oracle Open JRE 1.8.0_201). Consult the
[Elasticsearch compatibility matrix](https://www.elastic.co/support/matrix#matrix_jvm)
and the
[@product@ compatibility matrix](https://help.liferay.com/hc/en-us/sections/360002103292-Compatibility-Matrix)
to learn more about supported JDK distributions and versions. This consideration
is not necessary for Solr, because no JVM level serialization happens between
the servers. All communication occurs at the HTTP level.

