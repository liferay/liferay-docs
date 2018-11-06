# Installing Solr [](id=installing-solr)

Solr is a popular enterprise search platform build on Apache Lucene. It's
popular for its reliability, scalability, and fault tolerance. Read more about
it [here](http://lucene.apache.org/solr/).

Although 
[Elasticsearch](/discover/deployment/-/knowledge_base/7-1/configuring-elasticsearch-for-liferay-0)
is the default search engine that ships with @product@, it's perfectly valid to
use Solr instead. In particular, if you've already been using Solr with a
previous version of @product@, or your deployment system (for example, your OS or
JVM) [isn't supported by Elasticsearch](https://www.elastic.co/support/matrix),
you might choose to use Solr to search and index your @product@ data.

There are circumstances that force you to use Elasticsearch instead of Solr.
Read
[here](/discover/deployment/-/knowledge_base/7-1/installing-a-search-engine#choosing-a-search-engine)
for more information.

@product-ver supports Solr 7.x through the Liferay Connector to Solr. 
