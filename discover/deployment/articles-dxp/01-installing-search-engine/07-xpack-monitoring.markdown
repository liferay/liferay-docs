# Installing X-Pack Monitoring [](id=installing-x-pack-monitoring)

Monitor Elasticsearch with X-Pack Monitoring. First 
[install X-Pack onto Elasticsearch](discover/deployment/-/knowledge_base-7-1/installing-x-pack-security)
and configure security if you're using X-Pack's security features. Then come
back here for instructions on installing and configuring Kibana (the monitoring
server) with X-Pack so that Elasticsearch (secured with X-Pack), Kibana (secured
with X-Pack), and @product@ can communicate effortlessly and securely. A Liferay
Enterprise Search Standard subscription (included with Premium) is necessary for
this integration.  Contact 
[Liferay's Sales department for more information](https://www.liferay.com/contact-us#contact-sales).

1.  Download and install Kibana.

2.  Install X-Pack onto Kibana and configure Kibana with the proper security
    settings.

3.  Download and install the 
    [Liferay Connector to X-Pack Monitoring](https://www.liferay.com/marketplace) [Elastic Stack 6.x].

4.  Configure the connector to communicate with Elasticsearch.

This document assumes you're enabling security *and* monitoring, though
differences in the process are noted as appropriate.

For the X-Pack installation procedure, refer to the 
[X-Pack security article](/discover/deployment/-/knowledge_base-7-1/installing-x-pack-security).

Start with the installation of Kibana.

## Install Kibana [](id=install-kibana)

Make sure to install the correct version of Kibana. Check the 
[Liferay Enterprise Search compatibility matrix](https://web.liferay.com/group/customer/dxp/support/compatibility-matrix/enterprise-search)
for details.

1.  [Download Kibana](https://www.elastic.co/downloads/kibana) and extract it.
    The root folder is referred to as *Kibana Home*.

2.  Install X-Pack into Kibana:

        ./bin/kibana-plugin install x-pack

    If you're not using X-Pack Security on the Elasticsearch cluster, skip this
    step.

3.  Tell Kibana where to send monitoring data by setting Elasticsearch's URL in
    `kibana.yml`:

        elasticsearch.url: "http://localhost:9200"

    If SSL is enabled on Elasticsearch, this is an `https` URL.

4. If not using X-Pack security, start Kibana by entering

        ./bin/kibana

    from Kibana Home.

If you're using X-Pack's security features, there's additional configuration
required before starting Kibana.

### Configure Kibana with Authentication [](id=configure-kibana-with-authentication)

If X-Pack requires authentication to access the Elasticsearch cluster, follow
these steps or refer to 
[Elastic's documentation](https://www.elastic.co/guide/en/kibana/6.1/monitoring-xpack-kibana.html). 

1.  Set the password for the built-in `kibana` user in `Kibana
    Home/config/kibana.yml`:

        elasticsearch.username: "kibana"
        elasticsearch.password: "liferay"

    The password is whatever you set it to when initially setting up X-Pack.
    Once Kibana is installed, you can change the built-in user passwords from the
    *Management* user interface.

2.  If you're not encrypting communication with the Elasticsearch cluster, start
    Kibana with 

        ./bin/kibana

    and navigate to `localhost:5601`. Log in with a 
    [user](https://www.elastic.co/guide/en/x-pack/6.1/native-realm.html#native-add)
    who has the `kibana_user` 
    [role](https://www.elastic.co/guide/en/x-pack/6.1/built-in-roles.html).

### Configuring Kibana with Encryption [](id=configuring-kibana-with-encryption)

Follow these steps to configure Kibana if X-Pack encrypts communication with the
Elasticsearch cluster. Consult 
[Elastic's guide](https://www.elastic.co/guide/en/kibana/6.1/using-kibana-with-security.html#using-kibana-with-security)
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
[Elastic's documentation](https://www.elastic.co/guide/en/x-pack/6.1/secure-monitoring.html).

After this step you can access Kibana at `https://localhost:5601` and sign in
with a Kibana user. The last step is to hook Kibana up with @product@.

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
        kibanaURL="https://localhost:5601"

    Alternatively, configure the monitoring adapter from the Control Panel.
    Navigate to *Configuration* &rarr; *System Settings* and find the X-Pack
    Monitoring entry in the Search category. All the configuration 
    options for the monitoring connector appear there.

    The values differ depending on your Kibana configuration. 

3.  Deploy this configuration file to `Liferay Home/osgi/configs`, and the
    settings are picked up by your running instance. There's no need to restart
    the server.

4.  There's one more setting to add to Kibana itself. It sets Kibana's base path
    to let the Monitoring Portlet act as a proxy for Kibana's monitoring UI. Add
    this to `kibana.yml`:

        server.basePath: "/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy"

    Note that once you set the `server.basePath`, you cannot access the Kibana
    UI through Kibana's URL (for example, `https://localhost:5601`). All access
    to the Kibana UI is through the monitoring portlet, which is only accessible
    to logged in @product@ users. Navigate directly to the portlet using this
    URL:

    [http://localhost:8080/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy/app/monitoring](http://localhost:8080/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy/app/monitoring)

5.  Because you're using the Monitoring portlet in @product@ as a proxy to
    Kibana's UI, you must configure the application server's startup JVM
    parameters to recognize a valid *truststore* and *password*.

    First, navigate to Elasticsearch Home and generate a PKSC#12 certificate
    from the CA you created when setting up X-Pack security:

        ./bin/x-pack/certutil cert --ca-cert /path/to/ca.crt --ca-key /path/to/ca.key --ip 127.0.0.1 --dns localhost --name localhost --out /path/to/Elasticsearch_Home/config/localhost.p12

    Next use the `keytool` command to generate a truststore:

        keytool -importkeystore -deststorepass liferay -destkeystore /path/to/truststore.jks -srckeystore /path/to/Elasticsearch_Home/config/localhost.p12 -srcstoretype PKCS12 -srcstorepass liferay

    Add the trustore path and password to your application server's startup JVM
    parameters. For a Tomcat server, append this to your existing `CATALINA_OPTS`:

        -Djavax.net.ssl.trustStore=/path/to/truststore.jks -Djavax.net.ssl.trustStorePassword=liferay

Restart @product@ and Kibana.

## Monitoring in @product@ [](id=monitoring-in-product)

Once Kibana and X-Pack are successfully installed and configured and all the
servers are up and running, add the X-Pack Monitoring portlet to a page:

1.  Open the *Add* menu on a page and choose *Applications*

2.  Search for *monitoring* and drag the *X-Pack Monitoring* application from
    the Search category onto the page.

See the Elastic documentation for information on 
[monitoring Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/es-monitoring.html)
and 
[monitoring production systems](https://www.elastic.co/guide/en/x-pack/6.1/monitoring-production.html).

