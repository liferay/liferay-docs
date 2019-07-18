---
header-id: solr-settings
---

# Solr Settings

[TOC levels=1-4]

![Figure x: Solr has three System Settings entries.](../../images/solr-system-settings.png)

Solr can be configured for use with @product-ver@. Liferay Marketplace includes
a Solr connector app called the Liferay Connector to Solr 7. The connector is
configurable through System Settings or an OSGi configuration file named
`com.liferay.portal.search.solr.configuration.SolrConfiguration.cfg` and
deployed to `[Liferay_Home]/osgi/configs`.

The list below is all the configuration settings for Liferay's Solr connector, in
the order they appear in the System Settings application:

## Solr 7

`authenticationMode=BASIC`
: A String with the value of *BASIC* or *CERT*. Use BASIC when connecting using
the [Basic Authentication
plugin](https://cwiki.apache.org/confluence/display/solr/Basic+Authentication+Plugin),
otherwise select CERT to connect using [2-way SSL
authentication](https://cwiki.apache.org/confluence/display/solr/Enabling+SSL).

`clientType=REPLICATED`
: A String with the value of *REPLICATED* or *CLOUD*. Use the default
(REPLICATED) when connecting to a single-node Solr server. Specify CLOUD to
connect to SolrCloud (see the next section, titled *High Availability with
SolrCloud* for more information).

`logExceptionsOnly=true`
: A boolean value that, when set to true, only logs exceptions from Solr,
without rethrowing them.

`readURL=http://localhost:8983/solr/liferay`
: A String array with the URLs to which Liferay will send search requests.  This
will be different from the `writeURL` if you use separate servers for indexing
(write) and searching (read).

`writeURL=http://localhost:8983/solr/liferay`
: A String array with the URLs to which Liferay will send indexing requests.
This will be different from the `readURL` if you use separate servers for
indexing (write) and searching (read).

`zkHost=localhost:9983`
: A String with the ZooKeeper host and port. This is required when using the
adapter in CLOUD mode.

## Solr 7 HTTP Client Factory

`basicAuthPassword=solr`
: 

`basicAuthUserName=solr`
: 

`defaultMaxConnectionsPerRoute=20`
: 

`maxTotalConnections=20`
: 

## Solr 7 SSL Factory

`keyStorePassword=secret`
: 

`keyStorePath=classpath:/keystore.jks`
: 

`keyStoreType=JKS`
: 

`trustStorePassword=secret`
: 

`trustStorePath=classpath:/truststore.jks`
: 

`trustStoreType=JKS`
: 

`verifyServerCertificate=true`
: 

`verifyServerName=true`
: 
