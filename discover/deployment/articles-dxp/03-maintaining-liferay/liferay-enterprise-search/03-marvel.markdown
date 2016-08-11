# Monitoring Elasticsearch with Marvel

Marvel is a monitoring tool for Elasticsearch. It lets you view the performance
and health of your Elasticsearch cluster, so you can anticipate issues ahead of
time, troubleshoot them quickly, and scale your cluster appropriately.

You need a license if you want to use Marvel in production. Visit
Elasticsearch's [website for more
details](https://www.elastic.co/products/marvel).

Install and configure Marvel for Liferay, and Liferay for Marvel, following these steps:

-  Install Marvel and Kibana on Elasticsearch
    +$$$

    **Note:** If you're wondering what Kibana is, it's the visualization piece
    of the Marvel monitoring plugin. 

    $$$
-  Configure Kibana
-  Install and configure Liferay's Marvel adapter plugin
    -  The included Marvel Portlet application let's you set up an Elasticsearch
        monitoring page in Liferay itself.
    -  The portlet displays an embedded, proxied version of the Marve UI from
        Kibana.
    -  By running Marvel in Liferay, you can ensure access to the cluster's
        monitoring data is private.
- ksjfkljsdklfjl

These terms will be useful to understand as you read this guide:

-  *Elasticsearch Home* refers to the root folder of your unzipped Elasticsearch installation (for example, elasticsearch-2.2.0).
-  *Liferay Home* refers to the root folder of your Liferay installation. It will contain the osgi, deploy, data, and license folders.
-  *Kibana Home* refers to the root folder of your Kibana installation.

## Installing Kibana and Marvel

Before you install Kibana or Marvel, make sure you've read and followed the
instructions on [installing and configuring Elasticsearch for
Liferay](/discover/deployment/-/knowledge_base/7-0/configuring-elasticsearch).

1. Install the `marvel-agent` plugin on Elasticsearch by navigating to
   Elasticsearch Home and entering

        ./bin/plugin install marvel-agent

2. Download [Kibana 4.4.2](https://www.elastic.co/downloads/past-releases/kibana-4-4-2) and
   extract it to your Liferay Home folder.

    Note: Your Liferay Home folder should now have the usual Liferay folders, as
    well as the `elasticsearch-[version]` and `kibana` folders. A Liferay Home
    folder for a Tomcat bundle would look like this:

        Liferay_Home/
            data/
            deploy/
            Elasticsearch_Home/
            Kibana_Home/
            license/
            logs/
            osgi/
            patching-tool
            tomcat-[version]

3. Install Marvel on Kibana by navigating to Elasticsearch Home and entering

        ./bin/kibana plugin -install elasticsearch/marvel/2.2.1

    +$$$

    **Note:** The latest version of Marvel does not work with Kibana 4.4.2, so
    make sure you install Marvel 2.2.1.

    $$$

The next step is to configure Kibana to connect with Elasticsearch. The
instructions will vary depending on whether you are using
[Shield](/discover/deployment/-/knowledge_base/7-0/shield) or not.

## Configuring Kibana for Elasticsearch

To configure Kibana, the exact steps will depend on whether you're using Shield
or not. If you are, follow all the steps below. If not, ignore the steps that
begin with *[Shield]*.

<!-- 1. Create a new Elasticsearch user with the `kibana4_server` role. Navigate to
   Elasticsearch home and enter
    
        bin/shield/esusers useradd kibana4-server -r kibana4_server -p liferay

I DO NOT THINK THIS STEP IS NECESSARY. WAITING FOR CLARIFICATION FROM TIBOR.
-->

2. *[Shield]* Set the username and password in `Kibana_Home/config/kibana.yml`, by entering
   these lines:

        elasticsearch.username: liferay
        elasticsearch.password: liferay
        elasticsearch.url: “https://<your_elasticsearch_host>:9200”
    <!-- NOTE: WHY ARE WE NOT USING THE KIBANA USER WE JUST CONFIGURED IN STEP 1?-->

3. *[Shield]* Also in `kibana.yml`, you need to add Elasticsearch's Certificate Authority
   (CA) property. If you're using a self-signed certificate as demonstrated in
   the Shield article, export the certificate from the JKS file and use it as
   the CA.

    From Elasticsearch Home, execute 

        keytool -v -importkeystore -srckeystore es-ssl.keystore.jks -srcalias
        es-ssl -destkeystore es-ssl.PKCS12.p12 -deststoretype PKCS12

    This command converts the JKS file to a PKCS12 file, which is more portable.

    Note: You'll be prompted to enter a password for the new PKCS12 file. Enter
    *liferay*. You'll also be prompted for the password of the JKS file, which
    is also *liferay* if you followed the instructions form the Shield article.

4. *[Shield]* From Elasticsearch Home enter 

        openssl pkcs12 -in es-ssl.PKCS12.p12 -out es-ssl.CA.pem

    to export the certificate form the PKCS12 file. When prompted for the
    password to access the PKCS12 file, enter *liferay*. Likewise, enter
    *liferay* when prompted to create a password for the PEM file.

5. *[Shield]* Move the PEM file created above to `Kibana_Home/config`.

6. *[Shield]* Add the following line to `Kibana_Home/config/kibana.yml`:

        elasticsearch.ssl.ca: config/es-ssl.CA.pem

7. Configure Kibana for the Liferay Marvel Portlet/Proxy Servlet.
<!--???-->

        server.basePath: “/o/portal-search-elasticsearch-marvel-web/marvel-proxy”

    Note: the `/o` prefix is a default. Change it if you're running Liferay under
    a different [web context path](/discover/deployment/-/knowledge_base/7-0/installing-liferay-manually#making-liferay-coexist-with-other-java-ee-applications).

8. Start Elasticsearch by running 

        ./bin/elasticsearch

    from Elasticsearch Home.

9. Start Kibana by running 

        ./bin/kibana

    from Kibana Home.

10. Start Liferay.

## Configuring SSL on Kibana

To run Kibana with encryption you'll need to do two things:

-  Place a proxy server in front of it.
-  Tell the proxy's firewall to allow port 5601 (Kibana's port).
-  If using a self signed certificate in the proxy server, `ProxyServlet` won't
    be able to trust the connection, so add the certificate to the JVM
    truststore.
<!-- Is this accurate?-->

+$$$

**Note:** While it might be possible otherwise, Liferay does not support
protecting your Kibana connection with Shield. If you try, Marvel will not work
inside Liferay's portlet.
<!--Clarify this-->

$$$

## Configuring Liferay's Marvel Adapter

Now that you have Marvel and Kibana configured, it's time to install the Marvel
adapter into Liferay.

To install the Marvel adapter (called *Liferay Portal Search Elasticsearch
Marvel*) from [Liferay Marketplace](https://web.liferay.com/marketplace/),
either visit the website or navigate to *Control Panel* &rarr; *Apps* &rarr;
*Store*. Sign in to Liferay Marketplace and install the Marvel adapter directly
into your Liferay instance. If you download the Marvel adapter from Marketplace,
just deploy the JAR file to Liferay by copying it into the
`[Liferay_Home]/deploy` folder.

You can confirm the Marvel adapter is installed properly by typing `lb "Marvel"`
into the Gogo shell. It should be listed as `Active`.

    ID |State      |Level|Name
    517|Active     |   10|Liferay Portal Search Elasticsearch Marvel (1.0.0)

Once the plugin is installed, there's a new *Marvel Configuration* entry in the
System Settings application (*Control Panel* &rarr; *Configuration* &rarr;
*System Settings*), under the Foundation heading. 

There are several configuration options for the Marvel adapter plugin. In most
cases you'll want to make edits to the defaults through a `.cfg` file. Name it 
`com.liferay.portal.search.elasticsearch.marvel.web.configuration.MarvelWebConfiguration.cfg` and place it in `Liferay_Home/osgi/configs`.



<!-- Picture of System Settings Marvel entry needed-->

-  `kibanaURL=http://localhost:5601`

    Set the String URL for the remote Kibana server where Marvel is deployed.
    The URL should only contain the host and port. If you’re using a Proxy
    Server in front of Kibana, set the Proxy Server’s URL here instead of
    Kibana’s URL.

-  `proxyServletLogEnable=false`

    Set this boolean to `true` to show debug logging for the Marvel proxy.  This
    is useful for troubleshooting URL mappings passing through the proxy from
    Liferay to Kibana, and to display the error codes from requests that Kibana
    denies.

-  `shieldUserName=liferay`

    If Shield is being used, set the String value for the username used for
    authenticating to Shield.

-  `shieldPassword=liferay`

    Set the String password used for Shield authentication.



