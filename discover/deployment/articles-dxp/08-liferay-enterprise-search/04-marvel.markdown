# Installing Marvel for Elasticsearch [](id=monitoring-elasticsearch-with-marvel)

Marvel is a monitoring tool for Elasticsearch. It lets you view the performance
and health of your Elasticsearch cluster, so you can anticipate issues ahead of
time, troubleshoot them quickly, and scale your cluster appropriately.

To use Marvel with @product@, you need the *Enterprise Search-Standard*
subscription. With it you'll get an adapter plugin for configuring @product@ for
Marvel, and a Marvel Portlet that can be added to a page in your @product@
installation. This makes it easy to monitor your Elasticsearch cluster right
from @product@, since the Marvel portlet gives you access to all of Marvel's
functionality. If you don't yet have an Enterprise Search subscription, contact
your sales representative.

In addition to your subscription with @product@, you need a license from
Elasticsearch if you want to use Marvel in production. Visit Elasticsearch's
[website for more details](https://www.elastic.co/products/marvel).

This article shows you how to install and configure Marvel for @product@--and
@product@ for Marvel--with these general steps:

-   [Install Marvel and Kibana on Elasticsearch.](#installing-kibana-and-marvel)

     +$$$

     **Note:** If you're wondering what
     [Kibana](https://www.elastic.co/products/kibana) is, it's the visualization
     piece of the equation. Elasticsearch is the search engine, and a Marvel
     agent in Elasticsearch collects and sends data from Elasticsearch to
     Kibana. Kibana, including a Marvel UI plugin, displays the Marvel agent's
     data.

     $$$

-   [Configure Kibana to work with Shield and to be accessed through the Marvel Portlet.](#configuring-kibana-for-elasticsearch)

-   [Configure @product@'s Marvel adapter plugin.](#configuring-liferays-marvel-adapter)

-   [Add the Marvel portlet to a page and start monitoring your cluster.](#accessing-marvels-ui-in-liferay)

+$$$

**Note:** This tutorial shows you how to get Marvel up and running by installing
it onto your Elasticsearch cluster directly. The best approach is to follow
Elastic's guide and [set up the Marvel cluster separately from the production
cluster](https://www.elastic.co/guide/en/marvel/2.4/installing-marvel.html#monitoring-cluster),
so that a troublesome Elasticsearch cluster does not inhibit your ability to use
Marvel to diagnose its problems. In short, it involves these steps:

1. Install the Marvel agent and License plugins on the production Elasticsearch
   cluster.
2. Install a separate Elasticsearch cluster (the monitoring cluster).
3. Configure your production cluster's nodes to send Marvel data to the
   monitoring cluster.
4. Download and install Kibana on the monitoring cluster's machine.
5. Install the Marvel app into Kibana.
6. Configure Kibana to connect to the monitoring cluster.
7. Live long and prosper.

$$$

These terms will be useful to understand as you read this guide:

- *Elasticsearch Home* refers to the root folder of your unzipped Elasticsearch 
   installation (for example, `elasticsearch-2.4.0`).
- *Liferay Home* refers to the root folder of your @product@ installation. It 
   contains the `osgi`, `deploy`, `data`, and `license` folders.
- *Kibana Home* refers to the root folder of your Kibana installation.

## Installing Kibana and Marvel [](id=installing-kibana-and-marvel)

Before you install Kibana or Marvel, make sure you've read and followed the
instructions on
[installing](/discover/deployment/-/knowledge_base/7-0/installing-elasticsearch)
and
[configuring](/discover/deployment/-/knowledge_base/7-0/configuring-elasticsearch)
Elasticsearch for @product@.

1.  Install the `marvel-agent` plugin on Elasticsearch by navigating to
    Elasticsearch Home and entering

        ./bin/plugin install marvel-agent

2.  Download a
    [compatible](https://web.liferay.com/documents/14/21598941/Liferay+DXP+Compatibility+Matrix.pdf)
    version of [Kibana](https://www.elastic.co/downloads/kibana) and extract it
    to your Liferay Home folder.

    Note: Your Liferay Home folder should now have the usual @product@ folders, 
    as well as the `elasticsearch-[version]` (Elasticsearch Home) and
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

3.  Install Marvel on Kibana by navigating to Kibana Home and entering

        ./bin/kibana plugin --install elasticsearch/marvel/[version]

The next step is to configure Kibana to connect with Elasticsearch. The
instructions vary depending on whether you are using
[Shield](/discover/deployment/-/knowledge_base/7-0/shield) or not.

## Configuring Kibana for Elasticsearch [](id=configuring-kibana-for-elasticsearch)

Now you need to configure Kibana. Since you'll use @product@'s Marvel Portlet as
a proxy servlet to view the Kibana UI and Marvel, you'll start configuring that
here.

If you're [using Shield](https://www.elastic.co/guide/en/shield/current/kibana.html), follow all
the steps below. If not, skip the steps that begin with *[Shield]*.

1.  *[Shield]* Set the user name and password in 
    `Kibana_Home/config/kibana.yml`,
    by entering these lines:

        elasticsearch.username: liferay
        elasticsearch.password: liferay
        elasticsearch.url: "https://<your_elasticsearch_host>:9200"

2.  *[Shield]* Also in `kibana.yml`, you need to add Elasticsearch's Certificate
    Authority (CA) property. If you're using a self-signed certificate as
    demonstrated in the Shield article, export the certificate from the JKS file
    and use it as the CA.

    From `Elasticsearch_Home/config/path-to-your-JKS`, execute 

        keytool -v -importkeystore -srckeystore es-ssl.keystore.jks -srcalias es-shield -destkeystore es-ssl.PKCS12.p12 -deststoretype PKCS12

    This command converts the JKS file to a PKCS12 file, which is more portable.

    Note: You'll be prompted to enter a password for the new PKCS12 file. Enter
    *liferay*. You'll also be prompted for the password of the JKS file, which
    is also *liferay* if you followed the instructions from the Shield article.

4. *[Shield]* From `Elasticsearch_Home/config/path-to-your-PKCS12` enter 

        openssl pkcs12 -in es-ssl.PKCS12.p12 -out es-ssl.CA.pem

     to export the certificate from the PKCS12 file. When prompted for the
     password to access the PKCS12 file, enter *liferay*. Likewise, enter
     *liferay* when prompted to create a password for the PEM file.

4.  *[Shield]* Move the PEM file created above to `Kibana_Home/config`.

5.  *[Shield]* Add the following line to `Kibana_Home/config/kibana.yml`:

        elasticsearch.ssl.ca: /path/to/Kibana_Home/config/es-ssl.CA.pem

7. Configure Kibana to be accessed through the @product@ Marvel Portlet by
   adding the following to `kibana.yml`:

        server.basePath: "/o/portal-search-elasticsearch-marvel-web/marvel-proxy"

    Note: the `/o` prefix is a default. Change it if you're running @product@
    under a different
    [web context path](/discover/deployment/-/knowledge_base/7-0/installing-liferay-manually#making-liferay-coexist-with-other-java-ee-applications).

7.  Start Elasticsearch by running 

        ./bin/elasticsearch

    from Elasticsearch Home.

8.  Start Kibana by running 

        ./bin/kibana

    from Kibana Home.

9.  Start @product@.

## Configuring SSL on Kibana [](id=configuring-ssl-on-kibana)

To run Kibana with SSL encryption you'll need to do these things:

-   Place a proxy server in front of it.
-   Tell the proxy's firewall to allow port 5601 (Kibana's port).
-   If using a self signed certificate in the proxy server, `ProxyServlet` won't
    be able to trust the connection, so add the certificate to the JVM
    truststore.

+$$$

**Note:** @product@ does not support protecting your Kibana connection with
Shield. If you try to secure Kibana with Shield, Marvel will not work inside
@product@'s portlet.

$$$

## Configuring @product@'s Marvel Adapter [](id=configuring-liferays-marvel-adapter)

Now that you have Marvel and Kibana configured, you can configure the Marvel
adapter in @product@.

There's a *Marvel* entry in the System Settings application (*Control Panel*
&rarr; *Configuration* &rarr; *System Settings*) under the Foundation heading. 

![Figure 1: The Marvel adapter in @product@ can be configured directly from System Settings. This is most useful during testing and development.](../../images-dxp/marvel-system-settings.png)

There are several configuration options for the Marvel adapter plugin. While you
can make edits directly in System Settings, in production environments you'll
want to make edits to the defaults through a `.cfg` configuration file. Name it
`com.liferay.portal.search.elasticsearch.marvel.web.configuration.MarvelWebConfiguration.cfg`
and place it in `Liferay_Home/osgi/configs`.

Here are the settings you can configure:

`kibanaURL=http://localhost:5601`
: Set the String URL for the remote Kibana server where Marvel is deployed. The
URL should only contain the host and port. If you're using a Proxy Server in
front of Kibana, set the Proxy Server's URL here instead of Kibana's URL.

`proxyServletLogEnable=false`
: Set this boolean to `true` to show debug logging for the Marvel proxy.  This
is useful for troubleshooting URL mappings passing through the proxy from
@product@ to Kibana, and to display the error codes from requests that Kibana
denies.

`shieldUserName=liferay`
: If Shield is being used, set the String value for the user name used for
authenticating to Shield. This setting is ignored if Shield isn't installed.

`shieldPassword=liferay`
: Set the String password used for Shield authentication. This setting is
ignored if Shield isn't configured.

## Accessing Marvel's UI in @product@ [](id=accessing-marvels-ui-in-liferay)

As mentioned earlier, Liferay provides a Marvel Portlet that displays an
embedded, proxied version of the Marvel UI from Kibana.

![Figure 2: You can monitor your Elasticsearch cluster from @product@ using the Marvel Portlet.](../../images-dxp/marvel-portlet.png)

If you've followed the steps outlined above, and you have Elasticsearch, Marvel,
Kibana, and @product@ configured correctly, you can just add the Marvel Portlet
to a page in @product@:

1.  Click the page's *Add* button (![Add](../../images-dxp/icon-add.png)) and
    select *Applications*.

2.  Enter *Marvel* in the search box, and click *Add* next to the Marvel entry 
    in the Tools category. First you'll see a message about Kibana loading, then
    the Marvel UI appears.

    ![Figure 3: When you first add the Marvel Portlet to the page, Kibana fetches the Marvel content in the Marvel Portlet, and then your Marvel UI appears](../../images-dxp/marvel-kibana-loading.png)

3.  You're ready to start exploring Marvel's monitoring interface. For an
    overview of your cluster, click on its name in the *Your Clusters* table (if
    you left it at the default, it's `LiferayElasticsearchcluster`).

   ![Figure 4: You can monitor the health of your cluster using the Marvel Portlet.](../../images-dxp/marvel-portlet-overview.png)

For more information on what Marvel offers you, refer to [Elasticsearch's Marvel guide](https://www.elastic.co/guide/en/marvel/2.4/index.html).

With @product@'s *Enterprise Search-Standard* subscription, you not only have a
powerful search engine, but you have security and monitoring tools at your
disposal. You can now diagnose, troubleshoot, and fix problems more easily than
ever.
