---
header-id: installing-solr
---

# Installing Solr

[TOC levels=1-4]

Solr is a popular enterprise search platform built on Apache Lucene. It's
reliable, scalable, and fault tolerant. Read more about it
[here](http://lucene.apache.org/solr/).

[Elasticsearch](/docs/7-2/deploy/-/knowledge_base/d/configuring-the-liferay-elasticsearch-connector)
is the default search engine that ships with @product@, and some Liferay Search
features are only available on Elasticsearch. It's valid, however, to use Solr
instead. In particular, if you've already been using Solr with a previous
version of @product@, or your platform (for example, your OS or JVM) 
[isn't supported by Elasticsearch](https://www.elastic.co/support/matrix), you might
choose to use Solr to search and index your @product@ data.

There are circumstances that force you to use Elasticsearch instead of Solr.
Read
[here](/docs/7-2/deploy/-/knowledge_base/d/installing-a-search-engine#choosing-a-search-engine)
for more information.

Liferay DXP 7.2, Fix Pack 1 and later, supports Solr 7.5.x through the Liferay
Connector to Solr 7 application. 
