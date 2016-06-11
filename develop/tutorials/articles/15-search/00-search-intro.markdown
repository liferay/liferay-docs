# Search [](id=search)

As of version 7, Liferay Portal will rely on [Elasticsearch](https://www.elastic.co/) to index its content. **Elasticsearch** is a flexible and powerful open source, distributed, real-time search and analytics engine. This will enhance the search functionality without breaking compatibility as Elastic Search is backed up by Lucene, which was used by Liferay Portal previously.

By default, Liferay uses Elasticsearch in an embedded mode (or *operation mode*). The [2.2.0 version of Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/index.html) is compatible with the current version of Liferay 7.

The embedded operation mode is recommended only in the development environment and in some cases in test environments. In all other environments (integration-test, pre-production and production) you should configure a remote Elasticsearch service.

For configure a remote Elasticsearch service, you must Navigate to the Control Panel and then click on *System → System Settings*. Then click on *Foundation*, find the *Elasticsearch* configuration link, and click on it.
