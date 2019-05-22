# Installing a Search Engine [](id=installing-a-search-engine)

A search engine is a critical component of your @product@ installation. If
you're here, you probably know the basics already and want to configure a search
engine for your @product@ deployment. 

@product@ ships with Elasticsearch, a highly scalable, full-text search engine.
Elasticsearch is well-supported and almost certainly meets any search and
indexing need you have. For deployment settings, learn to configure
a standalone or remote Elasticsearch server or cluster
[here](/discover/deployment/-/knowledge_base/7-1/installing-elasticsearch).

[Solr](http://lucene.apache.org/solr) is also supported in @product@. 

## Choosing a Search Engine [](id=choosing-a-search-engine)

Elasticsearch and Solr are both supported, but there are some differences in how
they work with @product@. In certain cases, you must choose Elasticsearch. 

If you answer _yes_ to either of these questions, you must choose Elasticsearch:

1.  You're using 
    [@commerce@, Liferay's commerce solution](/web/commerce/documentation/-/knowledge_base/1-0/getting-started).

2.  Your custom search code requires the use of the `TermsSetFilter` API or the
    Geolocation APIs that are implemented in the Liferay Connector to
    Elasticsearch.

@commerce@ requires the `TermsSetFilter` implementation available in the
Elasticsearch connector, so you must use Elasticsearch if you're using
@commerce@.

Both of these Elasticsearch-only developer features are not currently
implemented in the Liferay Connector to Solr, but may be added in the future.
If you must use either of those features in your search solution's code, use
Elasticsearch. If you're using @commerce@, use Elasticsearch.
Otherwise, feel free to use Elasticsearch or Solr to index your portal content.

Another factor to consider in your search engine selection is JDK version. The
search engine and @product@ must use the same JDK version and distribution
(e.g., Oracle Open JDK 1.8.0_201). Consult the 
[Elasticsearch compatibility matrix](https://www.elastic.co/support/matrix#matrix_jvm)
and the 
[@product@ compatibility matrix](https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.1+Compatibility+Matrix/9f9c917a-c620-427b-865d-5c4b4a00be85) 
to learn more about supported JDK distributions and versions. This consideration
is not necessary for Solr, because no JVM level serialization happens between
the servers. All communication occurs at the HTTP level.

