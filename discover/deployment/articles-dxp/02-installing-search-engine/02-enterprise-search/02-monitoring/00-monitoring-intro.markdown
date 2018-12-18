# Installing Liferay Enterprise Search Monitoring [](id=installing-liferay-enterprise-search-monitoring)

Monitor Elasticsearch with X-Pack Monitoring. First 
[install X-Pack onto Elasticsearch](discover/deployment/-/knowledge_base-7-1/installing-x-pack)
and configure security if you're using X-Pack's security features. Then come
back here for instructions on installing and configuring Kibana (the monitoring
server) with X-Pack so that Elasticsearch (secured with X-Pack), Kibana (secured
with X-Pack), and @product@ can communicate effortlessly and securely. A Liferay
Enterprise Search Standard subscription (included with Premium) is necessary for
this integration.  Contact 
[Liferay's Sales department for more information](https://www.liferay.com/contact-us#contact-sales).

1.  Tell Elasticsearch to enable data collection.

2.  Download and install Kibana.

3.  Configure Kibana with the proper security settings.

4.  Install the 
    [Liferay Connector to X-Pack Monitoring](https://web.liferay.com/marketplace/-/mp/application/106163750) [Elastic Stack 6.x].

5.  Configure the connector to communicate with Elasticsearch.

This document assumes you're enabling security *and* monitoring, though
differences in the process are noted as appropriate.

For the X-Pack installation procedure, refer to the 
[X-Pack security article](/discover/deployment/-/knowledge_base-7-1/installing-x-pack-security).

Start by enabling data collection in Elasticsearch.

## Enable Data Collection [](id=enable-data-collection)

Monitoring is enabled on Elasticsearch by default, but data collection isn't.
Enable data collection by adding this line to `elasticsearch.yml`.

    xpack.monitoring.collection.enabled: true

Now install Kibana.

## Install Kibana [](id=install-kibana)

Make sure to install the correct version of Kibana. Check the 
[Liferay Enterprise Search compatibility matrix](https://web.liferay.com/group/customer/dxp/support/compatibility-matrix/enterprise-search)
for details.

1.  [Download Kibana](https://www.elastic.co/downloads/kibana)
    and extract it. The root folder is referred to as *Kibana Home*.

2.  Tell Kibana where to send monitoring data by setting Elasticsearch's URL in
    `kibana.yml`:

        elasticsearch.url: "http://localhost:9200"

    If SSL is enabled on Elasticsearch, this is an `https` URL.

3.  If not using X-Pack security, start Kibana by opening a command prompt to 
    Kibana Home and entering this command:

        ./bin/kibana

If you're using X-Pack's security features on the Elasticsearch server, there's
additional configuration required before starting Kibana.

### Configure Kibana with Authentication [](id=configure-kibana-with-authentication)

If X-Pack requires authentication to access the Elasticsearch cluster, follow
these steps or refer to 
[Elastic's documentation](https://www.elastic.co/guide/en/kibana/6.5/monitoring-xpack-kibana.html). 

1.  Set the password for the built-in `kibana` user in `Kibana
    Home/config/kibana.yml`:

        elasticsearch.username: "kibana"
        elasticsearch.password: "liferay"

    Use your `kibana` user password from your X-Pack setup. Once Kibana is
    installed, you can change the built-in user passwords from the
    *Management* user interface.

2.  If you're not encrypting communication with the Elasticsearch cluster, start
    Kibana from Kibana home. 

        ./bin/kibana

3.  Go to `localhost:5601` and make sure you can sign in as a 
    [user](https://www.elastic.co/guide/en/x-pack/6.5/native-realm.html#native-add)
    who has the `kibana_user` 
    [role](https://www.elastic.co/guide/en/x-pack/6.5/built-in-roles.html) 
    or a superuser (like the `elastic` user).

### Configuring Kibana with Encryption [](id=configuring-kibana-with-encryption)

Follow these steps to configure Kibana if X-Pack encrypts communication with the
Elasticsearch cluster. Consult 
[Elastic's guide](https://www.elastic.co/guide/en/kibana/6.5/using-kibana-with-security.html#using-kibana-with-security)
for more information.

Add these settings to `kibana.yml`:

    xpack.security.encryptionKey: "xsomethingxatxleastx32xcharactersx"
    xpack.security.sessionTimeout: 600000

    elasticsearch.ssl.verificationMode: certificate
    elasticsearch.url: "https://localhost:9200"
    elasticsearch.ssl.certificateAuthorities: [ "/path/to/ca.crt" ]

    server.ssl.enabled: true
    server.ssl.certificate: /path/to/[Elasticsearch Home]/config/localhost.crt
    server.ssl.key: /path/to/[Elasticsearch Home]/config/localhost.key

For more information about monitoring and security best practices in a clustered
environment, refer to 
[Elastic's documentation](https://www.elastic.co/guide/en/x-pack/6.5/secure-monitoring.html).

After this step you can access Kibana at `https://localhost:5601` and sign in
with a Kibana user. The last step is to connect Kibana to @product@.

## Configuring the Liferay Connector to X-Pack Monitoring [](id=configuring-the-liferay-connector-to-x-pack-monitoring)

If you have a Liferay Enterprise Search (Premium or Standard) subscription,
download the Liferay Connector to X-Pack Monitoring [Elastic Stack 6.x]. Install
the LPKG file by copying it into the `Liferay Home/deploy` folder. 

1.  Once the connector is installed and Kibana and Elasticsearch are securely
    configured, create a 
    [configuration file](/discover/portal/-/knowledge_base/7-1/understanding-system-configuration-files)
    named

        com.liferay.portal.search.elasticsearch6.xpack.monitoring.web.internal.configuration.XPackMonitoringConfiguration.config

2.  Place these settings in the `.config` file:

        kibanaPassword="liferay"
        kibanaUserName="elastic"
        kibanaURL="http://localhost:5601"

    The values depend on your Kibana configuration. For example, use a secure
    URL such as `kibanaURL="https://localhost:5601"` if you're using X-Pack
    Security features.

    Alternatively, configure the monitoring adapter from
    [System Settings](/discover/portal/-/knowledge_base/7-1/system-settings).
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

        server.rewriteBasePath: false
        server.basePath: "/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy"

    Note that once you set the `server.basePath`, you cannot access the Kibana
    UI through Kibana's URL (e.g., `https://localhost:5601`). All access to the
    Kibana UI is through the Monitoring portlet, which is only accessible to
    signed in @product@ users. Navigate directly to the portlet using this URL:

    [http://localhost:8080/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy/app/monitoring](http://localhost:8080/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy/app/monitoring)

5.  Because you're using the Monitoring portlet in @product@ as a proxy to
    Kibana's UI, if you are using X-Pack Security, you must configure the
    application server's startup JVM parameters to recognize a valid
    *truststore* and *password*.

    First, navigate to Elasticsearch Home and generate a PKSC#12 certificate
    from the CA you created when setting up X-Pack security:

        ./bin/elasticsearch-certutil cert --ca-cert /path/to/ca.crt --ca-key /path/to/ca.key --ip 127.0.0.1 --dns localhost --name localhost --out /path/to/Elasticsearch_Home/config/localhost.p12

    Next use the `keytool` command to generate a truststore:

        keytool -importkeystore -deststorepass liferay -destkeystore /path/to/truststore.jks -srckeystore /path/to/Elasticsearch_Home/config/localhost.p12 -srcstoretype PKCS12 -srcstorepass liferay

    Add the trustore path and password to your application server's startup JVM
    parameters. Here are example truststore and path parameters for appending to
    a Tomcat server's `CATALINA_OPTS`:

        -Djavax.net.ssl.trustStore=/path/to/truststore.jks -Djavax.net.ssl.trustStorePassword=liferay

Restart @product@ and Kibana.

## Monitoring in @product@ [](id=monitoring-in-product)

Once Kibana and X-Pack are successfully installed and configured and all the
servers are running, add the X-Pack Monitoring portlet to a page:

1.  Open the *Add* menu on a page and choose *Widgets*

2.  Search for *monitoring* and drag the *X-Pack Monitoring* widget from
    the Search category onto the page.

See the Elastic documentation for information on 
[monitoring Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/es-monitoring.html).
