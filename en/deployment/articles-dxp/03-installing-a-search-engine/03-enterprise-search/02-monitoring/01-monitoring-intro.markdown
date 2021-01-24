---
header-id: installing-liferay-enterprise-search-monitoring
---

# Installing Liferay Enterprise Search Monitoring

[TOC levels=1-4]

First configure security if you're using X-Pack's security features. Then come
back here for instructions on installing and configuring Kibana (the monitoring
server) with X-Pack so that Elasticsearch (secured with X-Pack), Kibana
(secured with X-Pack), and @product@ can communicate effortlessly and securely.
A Liferay Enterprise Search (LES) subscription is necessary for this
integration.  Contact 
[Liferay's Sales department for more information](https://www.liferay.com/contact-us#contact-sales).

| **The same monitoring connector is used for Elasticsearch 6 and 7**: When first
| created, the Liferay Enterprise Search Monitoring app  was
| intended to be used only with Elasticsearch 6. However, testing revealed that
| the same connector works with Elasticsearch 7, so a new connector is not
| necessary if you're using Elasticsearch 7.

To install X-Pack monitoring,

1.  Tell Elasticsearch to enable data collection.

2.  Download and install Kibana.

3.  Configure Kibana with the proper security settings.

4.  Install the Liferay Enterprise Search Monitoring app.

5.  Configure the connector to communicate with Elasticsearch.

This document assumes you're enabling security (with authentication and encrypted communication) *and* monitoring for
Elasticsearch 7, but differences in the process for Elasticsearch 6 are noted
where necessary.

## Enable Encrypting Communication (TLS/SSL) in Elasticsearch and in @product@

Start by following the steps in this [article](/docs/7-2/deployment/-/knowledge_base/u/installing-liferay-enterprise-search-security) to enable TLS/SSL in your Elasticsearch and @product@ installation.

Then continue by enabling data collection in Elasticsearch.

## Enable Data Collection

Monitoring is enabled on Elasticsearch by default, but data collection isn't.
Enable data collection by adding this line to `elasticsearch.yml`.

```yaml
xpack.monitoring.collection.enabled: true
```

Now install Kibana.

## Install Kibana

Make sure to install the correct version of Kibana. Check the 
[Liferay Enterprise Search compatibility matrix](https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-Enterprise-Search)
for details.

1.  [Download Kibana](https://www.elastic.co/downloads/kibana)
    and extract it. The root folder is referred to as *Kibana Home*.

2.  Tell Kibana where to send monitoring data by setting Elasticsearch's URL in
    `kibana.yml`:

    ```yaml
    elasticsearch.hosts: [ "https://localhost:9200" ]
    ```

    On 6.5 and below, use

    ```yaml
    elasticsearch.url: "https://localhost:9200"
    ```

    If TLS/SSL is not enabled on Elasticsearch, this is an `http` URL, otherwise use `https`.

3.  If not using X-Pack security, start Kibana by opening a command prompt to 
    Kibana Home and entering this command:

    ```bash
    ./bin/kibana
    ```

If you're using X-Pack's security features on the Elasticsearch server, there's
additional configuration required before starting Kibana.

### Configure Kibana with Authentication

If X-Pack requires authentication to access the Elasticsearch cluster, follow
these steps or refer to 
[Elastic's documentation](https://www.elastic.co/guide/en/kibana/7.x/monitoring-xpack-kibana.html). 

1.  Set the password for the built-in `kibana` user in `[Kibana
    Home]/config/kibana.yml`:

    ```yaml
    elasticsearch.username: "kibana"
    elasticsearch.password: "liferay"
    ```

    Use your `kibana` user password from your X-Pack setup. Once Kibana is
    installed, you can change the built-in user passwords from the
    *Management* user interface.

2.  If you're not encrypting communication with the Elasticsearch cluster, start
    Kibana from Kibana home. 

    ```bash
    ./bin/kibana
    ```

3.  Go to `http://localhost:5601` and make sure you can sign in as a 
    [user](https://www.elastic.co/guide/en/elasticsearch/reference/current/realms.html)
    who has the `kibana_user` 
    [role](https://www.elastic.co/guide/en/elasticsearch/reference/current/built-in-roles.html) 
    or a superuser (like the `elastic` user).

### Configuring Kibana with Encryption (TLS/SSL)

Follow these steps to configure Kibana if X-Pack encrypts communication with the
Elasticsearch cluster. Consult 
[Elastic's guide](https://www.elastic.co/guide/en/kibana/7.x/using-kibana-with-security.html#using-kibana-with-security)
for more information.

1. Copy the `[Elasticsearch Home]/config/certs` folder into the `[Kibana Home]/config/` folder.

    This example reuses the certificate files [created for Elasticsearch
    itself](/docs/7-2/deployment/-/knowledge_base/u/installing-liferay-enterprise-search-security).
    If you wish to generate a separate certificate for your Kibana instance, make
    sure it is signed by the same CA as the Elasticsearch node certificates.

2. Add these settings to `kibana.yml`:

    ```yaml
    xpack.security.encryptionKey: "xsomethingxatxleastx32xcharactersx"
    xpack.security.sessionTimeout: 600000

    elasticsearch.hosts: [ "https://localhost:9200" ]

    elasticsearch.ssl.verificationMode: certificate
    elasticsearch.ssl.certificateAuthorities: [ "config/certs/ca.crt" ]
    elasticsearch.ssl.certificate: config/certs/localhost.crt
    elasticsearch.ssl.key: config/certs/localhost.key

    server.ssl.enabled: true
    server.ssl.certificateAuthorities: [ "config/certs/ca.crt" ]
    server.ssl.certificate: config/certs/localhost.crt
    server.ssl.key: config/certs/localhost.key
    ```

Elasticsearch/Kibana 6.5 and below use a  different property for specifying the
host URL. Replace the `elasticsearch.hosts` property with

```yaml
elasticsearch.url: "https://localhost:9200"
```

For more information about monitoring and security best practices in a clustered
environment, refer to 
[Elastic's documentation](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/setup-xpack.html).

After this step you can access Kibana at `https://localhost:5601` and sign in
with a Kibana user. The last step is to connect Kibana to @product@.

## Configuring the Liferay Enterprise Search Monitoring app

If you have a LES subscription,
download the Liferay Enterprise Search Monitoring app . Install
the LPKG file by copying it into the `Liferay Home/deploy` folder. 

1.  Once the connector is installed and Kibana and Elasticsearch are securely
    configured, create a 
    [configuration file](/docs/7-2/user/-/knowledge_base/u/understanding-system-configuration-files)
    named

        com.liferay.portal.search.elasticsearch6.xpack.monitoring.web.internal.configuration.XPackMonitoringConfiguration.config

2.  Place these settings in the `.config` file:

        kibanaPassword="liferay"
        kibanaUserName="elastic"
        kibanaURL="https://localhost:5601"

    The values depend on your Kibana configuration. For example, use a URL such
    as `kibanaURL="http://localhost:5601"` if you are not using X-Pack Security
    TLS/SSL features.

    Alternatively, configure the monitoring adapter from
    [System Settings](/docs/7-2/user/-/knowledge_base/u/system-settings).
    Navigate to *Control Panel* &rarr; *Configuration* &rarr; *System Settings* 
    and find the X-Pack Monitoring entry in the Search category. All the
    configuration options for the monitoring connector appear there.

3.  Deploy this configuration file to `Liferay Home/osgi/configs`, and your 
    running instance applies the settings. There's no need to restart the
    server.

4.  There are two more settings to add to Kibana itself. The first forbids 
    Kibana from rewriting requests prefixed with `server.basePath`. The second
    sets Kibana's base path for the Monitoring portlet to act as a proxy for
    Kibana's monitoring UI. Add this to `kibana.yml`:

    ```yaml
    server.rewriteBasePath: false
    server.basePath: "/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy"
    ```

    Note that once you set the `server.basePath`, you cannot access the Kibana
    UI through Kibana's URL (e.g., `https://localhost:5601`). All access to the
    Kibana UI is through the Monitoring portlet, which is only accessible to
    signed in @product@ users. Navigate directly to the portlet using this URL:

    [http://localhost:8080/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy/app/monitoring](http://localhost:8080/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy/app/monitoring)

5.  Because you're using the Monitoring portlet in @product@ as a proxy to
    Kibana's UI, if you are using X-Pack Security with TLS/SSL, you must configure the
    application server's startup JVM parameters to recognize a valid
    *truststore* and *password*.

    First, navigate to Elasticsearch Home and generate a PKSC#12 certificate
    from the CA you created when setting up X-Pack security:

    ```bash
    ./bin/elasticsearch-certutil cert --ca-cert /path/to/ca.crt --ca-key /path/to/ca.key --ip 127.0.0.1 --dns localhost --name localhost --out /path/to/Elasticsearch_Home/config/localhost.p12
    ```

    Next use the `keytool` command to generate a truststore:

    ```bash
    keytool -importkeystore -deststorepass liferay -destkeystore /path/to/truststore.jks -srckeystore /path/to/Elasticsearch_Home/config/localhost.p12 -srcstoretype PKCS12 -srcstorepass liferay
    ```

    Add the trustore path and password to your application server's startup JVM
    parameters. Here are example truststore and path parameters for appending to
    a Tomcat server's `CATALINA_OPTS`:

    ```properties
    -Djavax.net.ssl.trustStore=/path/to/truststore.jks -Djavax.net.ssl.trustStorePassword=liferay
    ```

Restart @product@ and Kibana.

## Monitoring in @product@

Once Kibana and X-Pack are successfully installed and configured and all the
servers are running, add the X-Pack Monitoring portlet to a page:

1.  Open the *Add* menu on a page and choose *Widgets*

2.  Search for *monitoring* and drag the *X-Pack Monitoring* widget from
    the Search category onto the page.

See the Elastic documentation for information on 
[monitoring Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/es-monitoring.html).

## Example Kibana Configuration

Here are the `kibana.yml` properties demonstrated in this article, for
convenient copy/pasting:

```yaml
# X-Pack Security enabled (Basic Auth)
elasticsearch.username: "kibana"
elasticsearch.password: "liferay"

# When TLS/SSL is enabled in X-Pack Security
xpack.security.encryptionKey: "xsomethingxatxleastx32xcharactersx"
xpack.security.sessionTimeout: 600000

# If on Elasticsearch 6.5 or below, replace the next property with:
# elasticsearch.url: "http://localhost:9200"
elasticsearch.hosts: [ "https://localhost:9200" ]

# Enable TLS/SSL for out-bound traffic: from Kibana to Elasticsearch
elasticsearch.ssl.verificationMode: certificate
elasticsearch.ssl.certificateAuthorities: [ "config/certs/ca.crt" ]
elasticsearch.ssl.certificate: config/certs/localhost.crt
elasticsearch.ssl.key: config/certs/localhost.key

# Enable TLS/SSL for in-bound traffic: from browser or
#  DXP (X-Pack Monitoring widget, proxy) to Kibana
server.ssl.enabled: true
server.ssl.certificateAuthorities: [ "config/certs/ca.crt" ]
server.ssl.certificate: config/certs/localhost.crt
server.ssl.key: config/certs/localhost.key

# To use Kibana inside the X-Pack Monitoring widget
server.rewriteBasePath: false
server.basePath: "/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy"
```
