# Securing Elasticsearch with Shield [](id=securing-elasticsearch-with-shield)

Elasticsearch makes storing, searching, and analyzing your @product@ search data
easy. When it comes to securing that data, use Elasticsearch's [Shield
plugin](https://www.elastic.co/guide/en/shield/2.4/index.html). To use Shield
with @product@, you need the *Enterprise Search-Standard* subscription.  This
subscription gives you access to an adapter plugin for configuring @product@ for
Shield. 

With Shield you can prevent unauthorized users from accessing the Elasticsearch
cluster, preserve data integrity, and create an audit trail to inspect
suspicious activity. This guide shows you the basics of how to install and
configure Shield, and then how to configure @product@ for Shield, using a
convenient Shield adapter plugin.

+$$$

**Note:** The Shield plugin can only be used when you're running Elasticsearch
in *remote mode*. If you're not sure what that means refer to the [Configuring
Elasticsearch article](/discover/deployment/-/knowledge_base/7-0/configuring-elasticsearch).
It's not possible to install Shield into @product@'s default embedded
Elasticsearch--and you shouldn't be using embedded Elasticsearch in production
anyway.

$$$

Here's the process for configuring Shield:

-  Install the Shield plugin on Elasticsearch.
-  Create a user for @product@, with user name and password.
-  Install your [Shield license](https://www.elastic.co/guide/en/shield/current/license-management.html).
-  Install @product@'s Shield adapter plugin and configure it.
-  Enable Transport Layer Security (TLS) to encrypt your connection between
    @product@ and Elasticsearch.

These terms will be useful to understand as you read this guide:

-  *Elasticsearch Home* refers to the root folder of your unzipped Elasticsearch
  installation (for example, `elasticsearch-2.4.0`).
-  *Liferay Home* refers to the root folder of your @product@ installation. It 
  contains the `osgi`, `deploy`, `data`, and `license` folders.

## Installing Shield on Elasticsearch [](id=installing-shield-on-elasticsearch)

First install the Shield plugin on your Elasticsearch cluster.

1. Navigate to Elasticsearch Home and install the license plugin and the Shield
   plugin by executing.

        ./bin/plugin install license

    and then

        ./bin/plugin install shield

2. Next, you need to prepare for Shield to authenticate requests. 

    Users making requests to an Elasticsearch installation protected by Shield
    must be part of the *realm*, a user database configured for Shield. You
    can use the native user management system built into Shield, called
    *esusers*, or you can use an external system like LDAP.

    *Roles* for Shield are defined in
    `[Elasticsearch_Home]/config/shield/roles.yml` and include these:

    `admin`: Has permission to perform any cluster or index action.

    `power_user`: Has permission to monitor the cluster and perform any index
    action.

    `user`: Has permission to perform read actions on any index.

    So who is the user you need to configure for Liferay? It's Liferay itself,
    and it needs the `admin` role. @product@'s Elasticsearch client sends its
    authentication token (in other words, its user name and password) to
    Shield. Since Shield also has the authentication token stored in its user
    database, Liferay is a recognized user and has no problems communicating
    with the Elasticsearch cluster.

    From Elasticsearch Home, add an `admin` user named *liferay* to the esusers
    database:

        ./bin/shield/esusers useradd liferay -r admin

    When prompted, enter the password *liferay*.

    Note: Of course you can change these values if desired. Consider naming the
    user *shieldy_mcshieldface*, for example.<sup>[1](#footnote1)</sup>

3. Start Elasticsearch.

        ./bin/elasticsearch

4. Install your license file:

        curl -XPUT -u liferay 'http://localhost:9200/license?acknowledge=true' -d @license.json

    Enter the password you configured, and you'll get a confirmation message
    that looks like this:

        {"acknowledged":true}

5. To test that you have access to Elasticsearch, enter

        curl -u liferay:liferay -XGET 'http://localhost:9200/'

    and you'll see the Elasticsearch node information printed in the console:

        {
          "name" : "Amphibius",
          "cluster_name" : "LiferayElasticsearchCluster",
          "version" : {
            "number" : "2.4.0",
            "build_hash" : "8ff36d139e16f8720f2947ef62c8167a888992fe",
            "build_timestamp" : "2016-01-27T13:32:39Z",
            "build_snapshot" : false,
            "lucene_version" : "5.5.2"
          },
          "tagline" : "You Know, for Search"

For more information on installing Shield, see the [Elasticsearch
documentation](https://www.elastic.co/guide/en/shield/2.4/installing-shield.html).

Once Shield is installed, you can configure @product@'s Shield adapter.

## Installing and Configuring @product@'s Shield Adapter [](id=installing-and-configuring-liferays-shield-adapter)

On the @product@ side of the equation, you need to configure the authentication
token for the *liferay* Shield user you created in the previous section.
@product@ has a Shield adapter plugin for this purpose. 

First install the Shield adapter plugin (called *@product-ver@ Search
Elasticsearch Shield*). Once the plugin is installed, there's a new Shield
Configuration entry in the System Settings application (*Control Panel* &rarr;
*Configuration* &rarr; *System Settings*), under the Foundation heading.
Configure it so that its user name and password match the *liferay* user you
added to Shield.

You can configure the Shield adapter in the System Settings section in the
Control panel, or through an OSGi configuration file. 

Follow these steps to configure the Shield adapter using an OSGi configuration
file:

1. Create a file named `com.liferay.portal.search.elasticsearch.shield.configuration.ShieldConfiguration.cfg` in `[Liferay_Home]/osgi/configs`.

2. Add this content:

        password=liferay
        requiresAuthentication=true
        requiresSSL=false
        username=liferay

    For a description of all the Shield adapter's configuration options, see the
    section *Available Shield Adapter Configurations*.

3. Start @product@.

Follow these steps to configure the Shield adapter using System Settings:

1. Navigate to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*,
   and click on the *Foundation* heading. Navigate to, or search for, the
   *Shield Configuration* entry and click on it.

2. Set the password to *liferay*, check the *Requires authentication* box,
   uncheck the *Requires SSL* box, and make sure the user name is *liferay*.
   Click *Update* when you're done.

    Note: If you set a different user name and password while configuring
    Shield, make sure those match the user name and password you configure
    here.

    ![Figure 1: You can configure the @product-ver@ Search Elasticsearch Shield plugin from System Settings.](../../images-dxp/shield-adapter-settings.png)

3. Now you can re-index against Elasticsearch, and your data is secured by
   Shield. To re-index, go to the Control Panel's Configuration section and click
   *Server Administration*. Find the Index Actions heading and click *Execute*
   next to *Reindex all search indexes.*

For a complete list of the Shield adapter's available configuration options, see
[here](/reference/-/official_documentation/reference/shield-adapter-settings).

## Encrypting Elasticsearch Connections [](id=encrypting-elasticsearch-connections)

Your Elasticsearch connection now uses Shield to require authentication, but the
authentication token is sent in plain text. For additional security, enable
Transport Layer Security (TLS) encryption.

These instructions set up a *wildcard* certificate to be used across the entire
cluster. See the [Elasticsearch
documentation](https://www.elastic.co/guide/en/shield/2.4/ssl-tls.html) for
alternative configuration approaches and more information. 

Note that for Elasticsearch to access your keystore, it must be placed under the
`config` directory. Run the following commands under `Elasticsearch_Home/config`
to configure SSL with an Elasticsearch cluster running on `localhost`, for
example.

1. Stop @product@ and Elasticsearch.

2. Set up a Certificate Authority (CA) for Shield. Refer to [Elastic's article on Setting Up a Certificate Authority](https://www.elastic.co/guide/en/shield/2.4/certificate-authority.html#certificate-authority) 
for the details.

    **Note for Windows:** In step 2 of the [linked documentation on setting up a
    certificate](https://www.elastic.co/guide/en/shield/2.4/certificate-authority.html#certificate-authority),
    ensure that the `serial` file contains *01* with no quotation marks. Otherwise
    you'll encounter errors when you follow the steps below on signing the CSR.

    $$$

3. Use the Java `keytool` command to create a new Java Keystore, import the
   CA that will issue the wildcard certificate:

        keytool -importcert -keystore es-ssl.keystore.jks -file certs/cacert.pem -trustcacerts -storepass liferay -alias ca_cert

4. Create a private key in the Java Keystore:

        keytool -storepass liferay -genkey -alias es-shield -keystore es-ssl.keystore.jks -keyalg RSA -keysize 2048 -validity 3650 -dname "cn=localhost"

5. Create a certificate signing request (CSR) for requesting a certificate from
   the issuing CA:

        keytool -storepass liferay -certreq -alias es-shield -keystore es-ssl.keystore.jks -keyalg RSA -keysize 2048 -validity 3650 -dname "cn=localhost" > es-ssl.keystore.csr

6. Sign the CSR using [Elastic's guide](https://www.elastic.co/guide/en/shield/2.4/certificate-authority.html#sign-csr).

7. Once the CA has signed the CSR and returned the certificate in PEM format,
   import it into the Java Keystore:

        keytool -storepass liferay -importcert -keystore es-ssl.keystore.jks -alias es-shield -file certs/01.pem

8. Add the following lines to `[Elasticsearch_Home]/config/elasticsearch.yml`:

        shield.ssl.keystore.path: /path/to/es-ssl.keystore.jks
        shield.ssl.keystore.password: liferay
        shield.ssl.keystore.key_password: liferay
        shield.transport.ssl: true
        shield.http.ssl: true

    Here you're configuring Shield's SSL properties, including pointing to the
    keystore file you just generated. For more information on these settings,
    read [here](https://www.elastic.co/guide/en/shield/2.4/ssl-tls.html) and
    [here](https://www.elastic.co/guide/en/shield/2.4/shield-settings.html).

9. Update the Shield adapter configuration file you created earlier in
   `Liferay_Home/osgi/configs` by adding these lines:

        requiresSSL=true
        sslKeystorePath=/path/to/es-ssl.keystore.jks
        sslKeystorePassword=liferay

    Now, in addition to enabling authentication, you're enabling SSL encryption
    and pointing @product@ at the keystore file you created for Shield.
    
    Alternatively, you can configure these settings in System Settings. This
    will be more useful during development and testing.

10. Start Elasticsearch and @product@.

Now Shield is fully configured, with both authentication and encryption
protecting your Elasticsearch cluster. Next, you can learn how to 
[install and configure Marvel](/discover/deployment/monitoring-elasticsearch-with-marvel),
Elasticsearch's monitoring plugin, to visualize the health and performance of
your Elasticsearch cluster.

<a name="footnote1">1</a> This is, of course, a nod to all those fans of [Boaty Mcboatface]
