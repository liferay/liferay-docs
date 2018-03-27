# Installing X-Pack Monitoring for Elasticsearch 6 [](id=installing-x-pack-monitoring-for-elasticsearch-6)

To monitor Elasticsearch, use X-Pack Monitoring. First [install X-Pack onto
Elasticsearch](discover/deployment/-/knowledge_base-7-1/securing-elasticsearch-6-with-x-pack),
and configure security if you're using X-Pack's security features. Then come
back here for instructions on installing and configuring Kibana (the monitoring
server) with X-Pack so that Elasticsearch (secured with X-Pack), Kibana (secured
with X-Pack), and @product@ can communicate effortlessly and securely.

1.  Download and install Kibana.
2.  Install X-Pack onto Kibana, and configure Kibana with the proper security
    settings.
3.  Download and install the [Liferay Connector for X-Pack Monitoring](MP LINK).
4.  Configure the connector to communicate with Elasticsearch.

This document assumes you're enabling security *and* monitoring, though it's not
a requirement. Differences in the process are noted, as appropriate.

For the X-Pack installation procedure, refer to the [X-Pack security
article](/discover/deployment/-/knowledge_base-7-1/securing-elasticsearch-6-with-x-pack).

This guide starts with the installation of Kibana.

## Install Kibana [](id=install-kibana)

1.  [Download Kibana](https://www.elastic.co/downloads/kibana) and extract it.
    The root folder will be referred to as *Kibana Home*.

2.  Install X-Pack into Kibana:

        ./bin/kibana-plugin install x-pack

    If you're not using X-Pack Security on the Elasticsearch cluster, skip this
    step.

3. Tell Kibana where to send monitoring data by setting Elasticsearch's URL in
   `kibana.yml`:

        http://localhost:9200

    If SSL is enabled on Elasticsearch, this will be an `HTTPS` URL.

4. If not using X-Pack security, start Kibana by entering

        ./bin/kibana

    from Kibana Home.

If you're using X-Pack's security features, there's additional configuration
required before starting Kibana.

### Configure Kibana with Authentication [](id=configure-kibana-with-authentication)

If X-Pack is requiring authentication to access the Elasticsearch cluster,
follow these steps, or refer to [Elastic's
documentation](https://www.elastic.co/guide/en/kibana/6.1/monitoring-xpack-kibana.html). 

1. Set the password for the built-in `kibana` user in `Kibana
   Home/config/kibana.yml`:

        elasticsearch.username: "kibana"
        elasticsearch.password: "liferay"

2. If you're not encrypting communication with the Elasticsearch cluster, start
   Kibana with 

        ./bin/kibana

and navigate to `localhost:5601`. Log in with a
[user](https://www.elastic.co/guide/en/x-pack/6.1/native-realm.html#native-add) who has the `kibana_user`
[role](https://www.elastic.co/guide/en/x-pack/6.1/built-in-roles.html).

### Configuring Kibana with Encryption [](id=configuring-kibana-with-encryption)

Follow these steps to configure Kibana if X-Pack is encrypting communication
with the Elasticsearch cluster. Consult [Elastic's
guide](https://www.elastic.co/guide/en/kibana/6.2/using-kibana-with-security.html#using-kibana-with-security)
for more information.

Add these settings to `kibana.yml`:

    xpack.security.encryptionKey: "xsomethingxatxleastx32xcharactersx"
    xpack.security.sessionTimeout: 600000

    elasticsearch.ssl.verificationMode: certificate
    elasticsearch.url: "https://localhost:9200"
    elasticsearch.ssl.certificateAuthorities: [ "/path/to/ca.crt" ]

    server.ssl.enabled: true
    server.ssl.certificate: /path/to/[Elasticsearch Home]/config/ca.crt
    server.ssl.key: /path/to/[Elasticsearch Home]/config/ca.key

For more information about monitoring and security best practices in a clustered
environment, refer to [Elastic's
documentation](https://www.elastic.co/guide/en/x-pack/6.1/secure-monitoring.html).

After this step you can access Kibana at `https://localhost:5601` and sign in
with a Kibana user. The last step is to hook kibana up with @product@.

## Installing and Configuring the X-Pack Monitoring Connector [](id=installing-and-configuring-the-x-pack-monitoring-connector)

Once Kibana and Elasticsearch are securely configured, create a
[configuration file](/discover/portal/-/knowledge_base/7-1/understanding-system-configuration-files) named

    com.liferay.portal.search.elasticsearch6.xpack.monitoring.web.internal.configuration.XPackMonitoringConfiguration.config

Place these settings in the file:

    kibanaPassword="liferay"
    kibanaUserName="elastic"
    kibanaURL="https://localhost:5601"

The values will differ depending on your Kibana configuration. Deploy this
configuration file to `Liferay Home/osgi/configs`, and the settings will be
picked up by your running instance. There's no need to restart the server.

There's one more setting to add to Kibana itself. It sets the base path of
Kibana to let the Monitoring Portlet act as a proxy for Kibana's monitoring UI.
Add this to `kibana.yml`:

    server.basePath: "/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy"

Restart Kibana.

## Monitoring in @product@ [](id=monitoring-in-product)

Once Kibana and X-Pack are successfully installed and configured, and all the
servers are up and running, add the X-Pack Monitoring portlet to a page:

1. Open the Add menu on a page and choose *Applications*
2. Search for *monitoring* and drag the *X-Pack Monitoring* application from the
   Search category onto the page.

See the Elastic documentation for information on [monitoring
Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/es-monitoring.html)
and [monitoring production
systems](https://www.elastic.co/guide/en/x-pack/6.1/monitoring-production.html).



