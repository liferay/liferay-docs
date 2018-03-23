# Securing Elasticsearch 6 with X-Pack

X-Pack is an [Elastic
extension](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/setup-xpack.html)
for securing Elasticsearch clusters. If you're using Elasticsearch as
@product@'s search engine, it's advisable to secure it with X-Pack. The security
features of X-Pack include authenticating access to the Elasticsearch cluster's
data and encrypting Elasticsearch's internal and external communications. These
are necessary security features for most production systems. An Enterprise
Search-Standard subscription is necessary for this integration.
<!--Need confirmation of subscription-->

Here's the generalized process for installing X-Pack into Elasticsearch:

- Get an Enterprise Search subscription and Deploy the X-Pack Security adapter
- https://www.elastic.co/guide/en/x-pack/6.1/installing-xpack.html Install
   X-Pack and configure it to require authentication and [encryption](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-tls.html#configuring-tls).
- Download and configure the [Liferay Connector for X-Pack Security](MP LINK)
    with the proper credentials and certificate details.
- Restart Elasticsearch and @product@.

Following these instructions gives you a secure installation of Elasticsearch,
communicating freely with @product@, but read Elastic's documentation to learn
about additional configuration options, features, and the architecture of
[X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-security.html). 

## Installing X-Pack:

1.  To [install
    X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/installing-xpack-es.html)
    and automatically grant it the required permissions (recommended), run

        bin/elasticsearch-plugin install x-pack --batch

    on each cluster node. The `--batch` option bypasses installation prompts for
    granting permissions to X-Pack. 

    You'll see log output detailing the permissions granted, finsihing with
    `Installed X-Pack`:

        -> Downloading x-pack from elastic
        [=================================================] 100%   
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        @     WARNING: plugin requires additional permissions     @
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        * java.io.FilePermission \\.\pipe\* read,write
        * java.lang.RuntimePermission accessClassInPackage.com.sun.activation.registries
        * java.lang.RuntimePermission getClassLoader
        * java.lang.RuntimePermission setContextClassLoader
        * java.lang.RuntimePermission setFactory
        * java.net.SocketPermission * connect,accept,resolve
        * java.security.SecurityPermission createPolicy.JavaPolicy
        * java.security.SecurityPermission getPolicy
        * java.security.SecurityPermission putProviderProperty.BC
        * java.security.SecurityPermission setPolicy
        * java.util.PropertyPermission * read,write
        See http://docs.oracle.com/javase/8/docs/technotes/guides/security/permissions.html
        for descriptions of what these permissions allow and the associated risks.
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        @        WARNING: plugin forks a native controller        @
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        This plugin launches a native controller that is not subject to the Java
        security manager nor to system call filters.
        Elasticsearch keystore is required by plugin [x-pack], creating...
        -> Installed x-pack

    X-Pack needs permission to set the threat context loader, so Watcher can
    send email notifications, and to enable Elasticsearch to launch the machine
    learning analytical engine. See more about these permissions
    [here](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/installing-xpack-es.html)

2.  Make sure Elasticsearch is allowing the automatic creation of indexes. If
   you're unsure, check `elasticsearch.yml` for this property:

        action.auto_create_index: false

    This property is `true` by default, so if you don't see it in
    `elasticsearch.yml`, there's nothing to worry about. See [Elastic's
    documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/docs-index_.html#index-creation) for more information on automatic index creation.

Once X-Pack is installed, configure its built-in user passwords.

## Setting Up X-Pack Users

In a system with X-Pack Security and X-Pack Monitoring, two of the built-in
X-Pack users are important: `kibana` and `elastic`.

Set the passwords for all X-Pack's [built-in
users](https://www.elastic.co/guide/en/x-pack/6.1/setting-up-authentication.html#built-in-users).
The `setup-passwords` command is the simplest method to set the built-in users'
passwords for the first time. It's only valid for the first use. To update a
password subsequently, use Kibana's UI or the [Change Password
API](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/security-api-change-password.html).

You can even let X-Pack randomly genrate passwords for these users by passing in the `auto` argument:

    ./bin/x-pack/setup-passwords auto
        Changed password for user kibana
        PASSWORD kibana = Y?v1Jv^0AO*SKXveriGr

        Changed password for user logstash_system
        PASSWORD logstash_system = U^zLGC9$N6%6KhUHz^qb

        Changed password for user elastic
        PASSWORD elastic = GqhoaEUyTM@tp1*wQd~F

See Elastic's documentation on the [setup-passwords
command](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/setup-passwords.html) for additional options.

Since you're securing Elasticsearch, make sure you remember the password you set
for the `elastic` user. 

Enabling transport layer security on each node is highly recommended.

## Enabling Transport Layer Security

The following instructions for enabling TLS use the default file names generated
by X-Pack and `liferay` as the password whenever one is needed. You should
customize these as appropriate for your installation. To enable TLS/SSL in
Elasticsearch:

1.  [Generate a node
    certificate](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-tls.html#node-certificates)
    for each node.

    - Create a certificate authority, using [X-Pack's
     certutil](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/certutil.html):

        ./bin/x-pack/certutil ca
        ./bin/x-pack/certutil ca --ca-dn CN=localhost --pem

    - Generate a certificate and private key for for each Elasticsearch node:

        ./bin/x-pack/certutil cert --ca elastic-stack-ca.p12
        ./bin/x-pack/certutil cert --pem --multiple --out output.zip
    <!--    Enter password for CA (elastic-stack-ca.p12) : liferay
            Please enter the desired output file [elastic-certificates.p12]: 
            Enter password for elastic-certificates.p12 : liferay
    -->
        Note that you cannot use the default PKSC#12 format if you are
        using monitoring, because Kibana does not currently support PKSC#12
        certificates. 

        Copy the node certificate to the appropriate locations.

    - Copy the applicable `.p12` file into any directory under `Elasticsearch
        Home/config` on each node. Keep the CA file in a secure location.

2.  [Enable TLS](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-tls.html#enable-ssl) on each node via its `elasticsearch.yml`.

- Add the keystore and truststore paths to each node's `elasticsearch.yml`

        xpack.ssl.keystore.path: x-pack/elastic-certificates.p12 
        xpack.ssl.truststore.path: x-pack/elastic-certificates.p12 

    The example paths above assume you added the certificate to `Elasticsearch
    Home/config/x-pack/`. The `certutil` output includes the certificate
    authority certificate inside the `.p12` file, so you can use the same file
    for the keystore and truststore.

- If you used a password for the certificate that contains the keystore and
    truststore, add the password by running

        ./bin/elasticsearch-keystore add xpack.ssl.keystore.secure_password

    and then

        ./bin/elasticsearch-keystore add xpack.ssl.truststore.secure_password

-  Enable transport layer TLS with these settings in `elasticsearch.yml`:

        xpack.security.transport.ssl.enabled: true
        xpack.security.transport.ssl.verification_mode: certificate

- Enable TLS on the HTTP layer to encrypt client communication:

        xpack.security.http.ssl.enabled: true

- Restart Elasticsearch. These steps require a full cluster restart. 

After X-Pack is installed and TLS is enabled, configure the X-Pack Security
adapter in @product@.

## Configuring the X-Pack Security Adapter

If you'd like to configure the X-Pack adapter in the @product@ UI, navigate to
Control Panel &rarr; Configuration &rarr; System Settings. Find the Foundation
category, and click on the X-Pack Security entry. You can enter the property
values here, but it's more common to use a [configuration
file](https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/understanding-system-configuration-files)
deployed to `Liferay Home/osgi/configs`. For the X-Pack adapter, create a file
called

    com.liferay.portal.search.elasticsearch6.xpack.security.internal.configuration.XPackSecurityConfiguration.config

Populate the file with these contents:

    sslKeystorePath="/home/russell/liferay-bundles/elasticsearch-6.1.3/config/x-pack/elastic-certificates.p12"
    certificateFormat="PKCS#12"
    sslTruststorePath="/home/russell/liferay-bundles/elasticsearch-6.1.3/config/x-pack/elastic-certificates.p12"
    requiresAuthentication=B"true"
    username="elastic"
    sslKeystorePassword="liferay"
    password="GqhoaEUyTM@tp1*wQd~F"
    transportSSLVerificationMode="certificate"
    transportSSLEnabled=B"true"
    sslTruststorePassword="liferay"

Of course, the exact values will differ if you configured X-Pack differently. To
see all of the configuration optins available, refer to the System Settings
entry.

Once completed, restart Elasticsearch.
