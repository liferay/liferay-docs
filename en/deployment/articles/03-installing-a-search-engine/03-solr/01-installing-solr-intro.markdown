---
header-id: installing-solr
---

# Installing Solr

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/using-search/installing-and-upgrading-a-search-engine/solr/installing-solr.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

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

Liferay DXP 7.2, Fix Pack 1, supports Solr 7.5.x through the Liferay
Connector to Solr 7 application, version 2.0.0.

Liferay DXP 7.2, Service Pack 1/Fix Pack 2 and later, supports Solr 7.5.x
through the Liferay Connector to Solr 7 application, version 2.0.1.

Liferay Portal CE 7.2, GA2 and later (not available at time of writing), support
Solr 7.5.x through the Liferay CE Connector to Solr 7 application.

| **Upgrading to Service Pack 1 or Fix Pack 2 (or later) requires installation of
| a new Solr connector:** If you were running version 2.0.0 of the Liferay
| Connector to Solr 7 application, and you want to install Service Pack 1/Fix Pack
| 1 (or later), you must install version 2.0.1 of the Liferay Connector to Solr 7
| application.

## Blacklisting Elasticsearch-Only Features

Before installing Solr, you must 
[blacklist](/docs/7-2/user/-/knowledge_base/u/blacklisting-osgi-bundles-and-components) 
certain DXP 
[features that only work with Elasticsearch](/docs/7-2/deploy/-/knowledge_base/d/installing-a-search-engine#choosing-a-search-engine). 

1.  Create a configuration file named

    ```sh
    com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config
    ```

2.  Give it these contents:

    ```properties
    blacklistBundleSymbolicNames=["com.liferay.portal.search.tuning.web.api","com.liferay.portal.search.tuning.web","com.liferay.portal.search.tuning.synonyms.web","com.liferay.portal.search.tuning.rankings.web"]
    ```

3. Place the file in `Liferay Home/osgi/configs`. 

It is required during the Solr installation process to also 
[stop the Elasticsearch Connectors](https://portal.liferay.dev/docs/7-2/deploy/-/knowledge_base/d/installing-solr-basic-installation#stopping-the-elasticsearch-connector) 
that ship with @product@. If you're ready to blacklist those bundles now, use
these contents in the blacklist configuration file:

```properties
    blacklistBundleSymbolicNames=["com.liferay.portal.search.tuning.web.api","com.liferay.portal.search.tuning.web","com.liferay.portal.search.tuning.synonyms.web","com.liferay.portal.search.tuning.rankings.web","com.liferay.portal.search.elasticsearch6.spi","com.liferay.portal.search.elasticsearch6.api","com.liferay.portal.search.elasticsearch6.impl","Liferay Enterprise Search Monitoring ","Liferay Enterprise Search Security "]
```

The Liferay Enterprise Search bundles must be excluded if you don't have a LES subscription.
