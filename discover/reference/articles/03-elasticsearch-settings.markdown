# Elasticsearch Settings

Elasticsearch is the default search engine for Liferay 7. The *Liferay
Foundation* suite includes an adapter for Elasticsearch called *Liferay Portal
Search Elasticsearch*. The adapter is configurable through System Settings or an
OSGi configuration file named
`com.liferay.portal.search.elasticsearch.configuration.ElasticsearchConfiguration.cfg`
and deployed to `[Liferay_Home]/osgi/configs`.

The list below is all the configuration settings for Liferay's Elasticsearch
adapter, in the order they appear in the System Settings application:

`clusterName=LiferayElasticsearchCluster`
: A String value that sets the name of the cluster to integrate with. This name
should match the remote cluster when Operation Mode is set to remote.  (See
also: remote operation mode)

`operationMode=EMBEDDED`
: There are two operation modes you can choose from: EMBEDDED or REMOTE. Set to
REMOTE to connect to a remote standalone Elasticsearch cluster. Set to EMBEDDED
to start Liferay with an internal Elasticsearch instance. Embedded operation
mode is unsupported for production environments.

`indexNamePrefix=liferay-`
: Set a String value to use as the prefix for the search index name. The default
value should not be changed under normal conditions. If you change it, you must
also perform a *reindex all* operation for the portal and then manually delete
the old index using the Elasticsearch administration console.

`bootstrapMlockAll=false`
: A boolean setting that, when set to `true`, tries to lock the process address
space into RAM, preventing any Elasticsearch memory from being swapped out (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/setup-configuration.html#setup-configuration-memory))
for more information)

`logExceptionsOnly=true`
: A boolean setting that, when set to true, only logs exceptions from
Elasticsearch, and does not rethrow them.

`retryOnConflict=5`
: Set an int value for the number of retries to attempt if a version conflict
occurs because the document was updated between getting it and updating it (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/docs-update.html#_parameters_3)
for more information).

`discoveryZenPingUnicastHostsPort=9300-9400`
: Set a String value for the range of ports to use when building the value for
discovery.zen.ping.unicast.hosts. Multiple Elasticsearch nodes on a range of
ports can act as gossip routers at the same computer (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-discovery-zen.html)
for more information).

`networkHost=`
: Set this String value to instruct the node to bind to this hostname or IP
address and publish (advertise) this host to other nodes in the cluster. This is
a shortcut which sets the bind host and the publish host at the same time (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-network.html#common-network-settings)
for more information).

`networkBindHost=`
: Set the String value of the network interface(s) a node should bind to in order
to listen for incoming requests (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-network.html#advanced-network-settings)
for more information).

`networkPublishHost=`
: Set the String value of a single interface that the node advertises to other
nodes in the cluster, so that those nodes can connect to it (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-network.html#advanced-network-settings)
for more information).

`transportTcpPort=`
: Set the String value for the port to bind for communication between nodes.
Accepts a single value or a range
(see [here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-transport.html#_tcp_transport)
for more information).

`transportAddresses=localhost:9300`
: Set the String values for the addresses of the remote Elasticsearch nodes to
connect to. This value is required when Operation Mode is set to remote (see
[here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/2.2/transport-client.html)
for more information). Specify as many or few nodes as you see fit.

`clientTransportSniff=true`
: Set this booleant to true to enable cluster sniffing and dynamically discover
available data nodes in the cluster
(see [here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/2.2/transport-client.html)
for more information).

`clientTransportIgnoreClusterName=false`
: Set this boolean to true to ignore cluster name validation of connected nodes
(see [here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/2.2/transport-client.html)
for more information).

`clientTransportNodesSamplerInterval=5s`
: Set this String value to instruct the client node on how often to sample / ping
the nodes listed and connected (see
[here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/2.2/transport-client.html)
for more information).

`httpEnabled=true`
: Set this boolean to false to disable the http layer entirely on nodes which are
not meant to serve REST requests directly (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-http.html#_disable_http)
for more information).

`httpCORSEnabled=true`
: Set this boolean to false to disable cross-origin resource sharing, i.e. whether
a browser on another origin can do requests to Elasticsearch. If disabled, web
front end tools like elasticsearch-head may be unable to connect (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-http.html#_settings_2)
for more information).

`httpCORSAllowOrigin=/https?:\\/\\/localhost(:[0-9]+)?/`
: Set the String origins to allow when HTTP CORS is enabled (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-http.html#_settings_2)
for more information).

`httpCORSConfigurations=`
: Set the String values for custom settings for HTTP CORS, in YML format
(elasticsearch.yml) (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-http.html#_settings_2)
for more information).

`additionalConfigurations=`
: Set the String values for custom settings for embedded Elasticsearch, in YML
format. See: Adding Settings to the Liferay Elasticsearch Adapter

`additionalIndexConfigurations=`
: Set the String values for custom settings for the Liferay index, in JSON or YML
format (refer to the Elasticsearch Create Index API for more information).
See: Adding Settings to the Liferay Elasticsearch Adapter

`additionalTypeMappings=`
: Set the String values for custom mappings for the `LiferayDocumentType`, in JSON
format (refer to the Elasticsearch Put Mapping API for more information)
See: Adding Settings to the Liferay Elasticsearch Adapter

You can easily configure these settings in the System Setting application, or
as mentioned above, you can specify them in a deployable OSGi `.cfg` file.
