# Monitoring Elasticsearch with Marvel [](id=monitoring-elasticsearch-with-marvel)

Marvel is a monitoring tool for Elasticsearch. It lets you view the performance
and health of your Elasticsearch cluster, so you can anticipate issues ahead of
time, troubleshoot them quickly, and scale your cluster appropriately.

To use Marvel with @product@, you need the *Enterprise Search-Standard*
subscription. With it you'll get an adapter plugin for configuring Liferay for
Marvel, and a Marvel Portlet that can be added to a page in your @product@
installation. This makes it easy to monitor your Elasticsearch cluster right
from Liferay, since the Marvel portlet gives you access to all of Marvel's
functionality. If you don't yet have an Enterprise Search subscription, contact
your sales representative.

In addition to your subscription with Liferay, you need a license from
Elasticsearch if you want to use Marvel in production. Visit Elasticsearch's
[website for more details](https://www.elastic.co/products/marvel).

This article shows you how to install and configure Marvel for Liferay--and
Liferay for Marvel--with these general steps:

-  Install Marvel and Kibana on Elasticsearch.

    +$$$

    **Note:** If you're wondering what
    [Kibana](https://www.elastic.co/products/kibana) is, it's the visualization
    piece of the equation. Elasticsearch is the search engine, and a Marvel
    agent in Elasticsearch collects and sends data from Elasticsearch to Kibana.
    Kibana, including a Marvel UI plugin, displays the Marvel agent's data.

    $$$

-  Configure Kibana to work with Shield and to be accessed through the Marvel
   Portlet.

-  Configure Liferay's Marvel adapter plugin.

-  Add the Marvel portlet to a page and start monitoring your cluster.

These terms will be useful to understand as you read this guide:

-  *Elasticsearch Home* refers to the root folder of your unzipped Elasticsearch 
   installation (for example, `elasticsearch-2.2.0`).
-  *Liferay Home* refers to the root folder of your Liferay installation. It 
   contains the `osgi`, `deploy`, `data`, and `license` folders.
-  *Kibana Home* refers to the root folder of your Kibana installation.

## Installing Kibana and Marvel [](id=installing-kibana-and-marvel)

Before you install Kibana or Marvel, make sure you've read and followed the
instructions on [installing and configuring Elasticsearch for
Liferay](/discover/deployment/-/knowledge_base/7-0/configuring-elasticsearch).

1. Install the `marvel-agent` plugin on Elasticsearch by navigating to
   Elasticsearch Home and entering

        ./bin/plugin install marvel-agent

2. Download a
   [compatible](https://web.liferay.com/documents/14/21598941/Liferay+DXP+Compatibility+Matrix.pdf)
   version of [Kibana](https://www.elastic.co/downloads/kibana) and extract it
   to your Liferay Home folder.

   Note: Your Liferay Home folder should now have the usual Liferay folders, as
   well as the `elasticsearch-[version]` (Elasticsearch Home) and
   `kibana-[version]` (Kibana Home) folders. A Liferay Home folder for a Tomcat
   bundle would look like this:

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

3. Install Marvel on Kibana by navigating to Kibana Home and entering

        ./bin/kibana plugin --install elasticsearch/marvel/2.2.1

   +$$$

   **Note:** The latest version of Marvel does not work with Kibana 4.4.2, so
   make sure you install Marvel 2.2.1 if you're using Kibana 4.4.2. See the
   [compatibility matrix](https://web.liferay.com/documents/14/21598941/Liferay+DXP+Compatibility+Matrix.pdf)
   to find compatible versions of Marvel and Kibana.

   $$$

The next step is to configure Kibana to connect with Elasticsearch. The
instructions vary depending on whether you are using
[Shield](/discover/deployment/-/knowledge_base/7-0/shield) or not.

## Configuring Kibana for Elasticsearch [](id=configuring-kibana-for-elasticsearch)

Now you need to configure Kibana. Since you'll use Liferay's Marvel Portlet as a
proxy servlet to view the Kibana UI and Marvel, you'll start configuring that
here.

If you're using Shield, follow all the steps below. If not, skip the steps that
begin with *[Shield]*.

2. *[Shield]* Set the user name and password in `Kibana_Home/config/kibana.yml`,
   by entering these lines:

        elasticsearch.username: liferay
        elasticsearch.password: liferay
        elasticsearch.url: "https://<your_elasticsearch_host>:9200"

3. *[Shield]* Also in `kibana.yml`, you need to add Elasticsearch's Certificate
   Authority (CA) property. If you're using a self-signed certificate as
   demonstrated in the Shield article, export the certificate from the JKS file
   and use it as the CA.

   From Elasticsearch Home, execute 

        keytool -v -importkeystore -srckeystore es-ssl.keystore.jks -srcalias
        es-ssl -destkeystore es-ssl.PKCS12.p12 -deststoretype PKCS12

   This command converts the JKS file to a PKCS12 file, which is more portable.

   Note: You'll be prompted to enter a password for the new PKCS12 file. Enter
   *liferay*. You'll also be prompted for the password of the JKS file, which
   is also *liferay* if you followed the instructions from the Shield article.

4. *[Shield]* From Elasticsearch Home enter 

        openssl pkcs12 -in es-ssl.PKCS12.p12 -out es-ssl.CA.pem

    to export the certificate from the PKCS12 file. When prompted for the
    password to access the PKCS12 file, enter *liferay*. Likewise, enter
    *liferay* when prompted to create a password for the PEM file.

5. *[Shield]* Move the PEM file created above to `Kibana_Home/config`.

6. *[Shield]* Add the following line to `Kibana_Home/config/kibana.yml`:

        elasticsearch.ssl.ca: config/es-ssl.CA.pem

7. Configure Kibana to be accessed through the Liferay Marvel Portlet.

        server.basePath: "/o/portal-search-elasticsearch-marvel-web/marvel-proxy"

    Note: the `/o` prefix is a default. Change it if you're running Liferay under
    a different [web context path](/discover/deployment/-/knowledge_base/7-0/installing-liferay-manually#making-liferay-coexist-with-other-java-ee-applications).

8. Start Elasticsearch by running 

        ./bin/elasticsearch

   from Elasticsearch Home.

9. Start Kibana by running 

        ./bin/kibana

   from Kibana Home.

10. Start Liferay.

## Configuring SSL on Kibana [](id=configuring-ssl-on-kibana)

To run Kibana with SSL encryption you'll need to do these things:

-  Place a proxy server in front of it.
-  Tell the proxy's firewall to allow port 5601 (Kibana's port).
-  If using a self signed certificate in the proxy server, `ProxyServlet` won't
   be able to trust the connection, so add the certificate to the JVM
   truststore.

+$$$

**Note:** Liferay does not support protecting your Kibana connection with
Shield. If you try to secure Kibana with Shield, Marvel will not work inside
Liferay's portlet.

$$$

## Configuring Liferay's Marvel Adapter [](id=configuring-liferays-marvel-adapter)

Now that you have Marvel and Kibana configured, you can configure the Marvel
adapter in Liferay.

There's a *Marvel* entry in the System Settings application (*Control Panel*
&rarr; *Configuration* &rarr; *System Settings*) under the Foundation heading. 

![Figure 1: The Marvel adapter in Liferay can be configured directly from System
Settings. This is most useful during testing and development.](../../../images-dxp/marvel-system-settings.png)

There are several configuration options for the Marvel adapter plugin. While you
can make edits directly in System Settings, in production environments you'll
want to make edits to the defaults through a `.cfg` configuration file. Name it
`com.liferay.portal.search.elasticsearch.marvel.web.configuration.MarvelWebConfiguration.cfg`
and place it in `Liferay_Home/osgi/configs`.

Here are the settings you can configure:

`kibanaURL=http://localhost:5601`
: Set the String URL for the remote Kibana server where Marvel is deployed. The
URL should only contain the host and port. If you’re using a Proxy Server in
front of Kibana, set the Proxy Server's URL here instead of Kibana's URL.

`proxyServletLogEnable=false`
: Set this boolean to `true` to show debug logging for the Marvel proxy.  This
is useful for troubleshooting URL mappings passing through the proxy from
Liferay to Kibana, and to display the error codes from requests that Kibana
denies.

`shieldUserName=liferay`
: If Shield is being used, set the String value for the user name used for
authenticating to Shield. This setting is ignored if Shield isn't installed.

`shieldPassword=liferay`
: Set the String password used for Shield authentication. This setting is
ignored if Shield isn't configured.

## Accessing Marvel's UI in Liferay [](id=accessing-marvels-ui-in-liferay)

As mentioned earlier, Liferay provides a Marvel Portlet that displays an
embedded, proxied version of the Marvel UI from Kibana.

![Figure 2: You can monitor your Elasticsearch cluster from Liferay using the
Marvel Portlet.](../../../images-dxp/marvel-portlet.png)

If you've followed the steps outlined above, and you have Elasticsearch, Marvel,
Kibana, and Liferay configured correctly, you can just add the Marvel Portlet to
a page in Liferay:

1. Click the page's *Add* button (![Add](../../../images-dxp/icon-add.png)) and
   select *Applications*.

2. Enter *Marvel* in the search box, and click *Add* next to the Marvel entry in
   the Tools category. First you'll see a message about Kibana loading, then the
   Marvel UI appears.

   ![Figure 3: When you first add the Marvel Portlet to the page, Kibana fetches
   the Marvel content in the Marvel Portlet, and then your Marvel UI 
   appears](../../../images-dxp/marvel-kibana-loading.png)

3. You're ready to start exploring Marvel's monitoring interface. For an
   overview of your cluster, click on its name in the *Your Clusters* table (if
   you left it at the default, it's `LiferayElasticsearchcluster`).

   ![Figure 4: You can monitor the health of your cluster using the Marvel
   Portlet.](../../../images-dxp/marvel-portlet-overview.png)

For more information on what Marvel offers you, refer to [Elasticsearch's Marvel guide](https://www.elastic.co/guide/en/marvel/2.2/index.html).

With Liferay's *Enterprise Search-Standard* subscription, you not only have a
powerful search engine, but you have security and monitoring tools at your
disposal. You can now diagnose, troubleshoot, and fix problems more easily than
ever.
