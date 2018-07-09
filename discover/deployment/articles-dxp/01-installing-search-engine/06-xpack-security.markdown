# Installing X-Pack Security [](id=installing-x-pack-security)

X-Pack is an 
[Elastic extension](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/setup-xpack.html)
for securing and monitoring Elasticsearch clusters. If you use Elasticsearch,
you should secure it with X-Pack. The security features of X-Pack include
authenticating access to the Elasticsearch cluster's data and encrypting
Elasticsearch's internal and external communications. These are necessary
security features for most production systems. A Liferay Enterprise Search
Premium subscription gets you access to two X-Pack Connectors for @product@:
monitoring and security. A Liferay Enterprise Search Standard subscription gets
you the monitoring integration. Contact
[Liferay's Sales department for more information](https://www.liferay.com/contact-us#contact-sales).

Here's an overview of using X-Pack to secure the data indexed in Elasticsearch:

1.  Get an Enterprise Search subscription.

2.  [Install X-Pack into Elasticsearch](https://www.elastic.co/guide/en/x-pack/6.1/installing-xpack.html) 
    and configure it to require authentication and 
    [encryption](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-tls.html#configuring-tls).

3.  Download and install the 
[Liferay Connector to X-Pack Security](https://web.liferay.com/group/customer/dxp/downloads/enterprise-search) 
[Elastic Stack 6.x]. 

4.  Configure the X-Pack connector with the proper credentials and encryption
    information.

5.  Restart Elasticsearch. These steps require a full cluster restart.

Following these instructions gives you a working installation of Elasticsearch
communicating freely with @product@. Read Elastic's documentation to learn about
additional configuration options, features, and the architecture of
[X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-security.html). 

## Installing X-Pack [](id=installing-x-pack)

1.  To 
    [install X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/installing-xpack-es.html)
    and automatically grant it the required permissions (recommended), run

        bin/elasticsearch-plugin install x-pack --batch

    on each Elasticsearch node. The `--batch` option bypasses
    installation prompts for granting permissions to X-Pack. 

    You'll see log output detailing the permissions granted, finishing with
    `Installed x-pack`:

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

    See more about the permissions X-Pack needs
    [here](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/installing-xpack-es.html). 

2.  Make sure Elasticsearch allows the automatic creation of indexes. If
    you're unsure, check `elasticsearch.yml` for this property:

        action.auto_create_index: false

    This property is `true` by default, so if you don't see it in
    `elasticsearch.yml`, there's nothing to worry about. See [Elastic's
    documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/docs-index_.html#index-creation) 
    for more information on automatic index creation.

3.  Restart Elasticsearch. Make sure @product@ is shut down during this step.

Once X-Pack is installed, configure its built-in user passwords.

## Setting Up X-Pack Users [](id=setting-up-x-pack-users)

In a system using X-Pack Security and X-Pack Monitoring, two of the built-in
X-Pack users are important: `kibana` and `elastic`.

Set the passwords for all X-Pack's 
[built-in users](https://www.elastic.co/guide/en/x-pack/6.1/setting-up-authentication.html#built-in-users).
The `setup-passwords` command is the simplest method to set the built-in users'
first-use passwords for the first time. To update a password subsequently, use
Kibana's UI or the 
[Change Password API](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/security-api-change-password.html).

The `interactive` argument sets the passwords for all built-in users. The
configuration shown in these articles assumes you set all of the passwords to
*liferay*. Of course, that's not recommended for production systems.

    ./bin/x-pack/setup-passwords interactive

See Elastic's documentation on the 
[setup-passwords command](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/setup-passwords.html) 
for additional options.

Since you're securing Elasticsearch, make sure you keep track of the `elastic`
user's password. 

We recommend enabling transport layer security on each node.

## Enabling Transport Layer Security [](id=enabling-transport-layer-security)

The following instructions for enabling TLS use `liferay` as the password
whenever one is needed. Customize these as appropriate for your installation. 

### Generate Node Certificates [](id=generate-node-certificates)

[Generate a node certificate](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-tls.html#node-certificates)
for each node. You can, of course, use a Certificate Authority to obtain node 
certificates.

1.  Create a certificate authority, using 
      [X-Pack's `certutil`](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/certutil.html)
      command:

        ./bin/x-pack/certutil ca --pem --ca-dn CN=localhost

    This generates a ZIP file. Unzip the contents somewhere safe.

2. Generate X.509 certificates and private keys using the CA from Step 1. For
   example:

        ./bin/x-pack/certutil cert --pem --ca-cert /path/to/ca.crt --ca-key /path/to/ca.key --dns localhost --ip 127.0.0.1 --name localhost

    This generates another ZIP file. Extract the contents somewhere in the
    `Elasticsearch Home/config` folder.

+$$$

**Note:** The `certutil` command defaults to using the *PKSC#12* format for
certificate generation. Kibana does not work with PKSC#12 certificates, so the
`--pem` option (to generate the certificate in PEM format) is important if
you're using X-Pack monitoring.

$$$

### Enable TLS [](id=enable-tls)

[Enable TLS](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-tls.html#enable-ssl) 
on each node via its `elasticsearch.yml`.

1.  Add the certificate, key and certificate authority paths to each node's
    `elasticsearch.yml`:

        xpack.ssl.certificate: /path/to/[Elasticsearch Home]/config/localhost.crt
        xpack.ssl.key: /path/to/[Elasticsearch Home]/config/localhost.key
        xpack.ssl.certificate_authorities: ["/path/to/ca.crt"]

    The example paths above assume you added the certificate to `Elasticsearch
    Home/config/`. 

2.  Enable transport layer TLS with these settings in `elasticsearch.yml`:

        xpack.security.transport.ssl.enabled: true
        xpack.security.transport.ssl.verification_mode: certificate

3.  Enable TLS on the HTTP layer to encrypt client communication:

        xpack.security.http.ssl.enabled: true

After X-Pack is installed and TLS is enabled, configure the X-Pack Security
adapter in @product@.

## Install and Configure the Liferay Connector to X-Pack Security [](id=install-and-configure-the-liferay-connector-to-x-pack-security)

If you have a Liferay Enterprise Search Premium subscription, 
[download](https://web.liferay.com/group/customer/dxp/downloads/enterprise-search) 
the Liferay Connector to X-Pack Security [Elastic Stack 6.x]. Install the LPKG
file by copying it into the `Liferay Home/deploy` folder. That's all there is to
it.

To configure the X-Pack adapter, navigate to *Control Panel* &rarr;
*Configuration* &rarr; *System Settings*. Find the *Search* category and click
on the *X-Pack Security* entry. You can enter the property values here, but
it's more common to use a 
[configuration file](/discover/portal/-/knowledge_base/7-1/understanding-system-configuration-files)
deployed to `Liferay Home/osgi/configs`. For the X-Pack adapter, create a file
called

    com.liferay.portal.search.elasticsearch6.xpack.security.internal.configuration.XPackSecurityConfiguration.config

The exact contents of the file depend on your X-Pack setup. To configure the adapter
according to the Elasticsearch setup documented here, populate the file with
these contents:

    sslKeyPath="/path/to/[Elasticsearch Home]/config/localhost.key"
    sslCertificatePath="/path/to/[Elasticsearch Home]/config/localhost.crt"
    certificateFormat="PEM"
    requiresAuthentication="true"
    username="elastic"
    password="liferay"
    sslCertificateAuthoritiesPaths="/path/to/[Elasticsearch Home]/config/ca.crt"
    transportSSLVerificationMode="certificate"
    transportSSLEnabled="true"

Note that the `password` should match what you set during the X-Pack password
setup above. 

Enable authentication by setting authentication to `required` and providing the
credentials for the Elasticsearch user. For SSL, enable transport SSL, set the
certificate verification mode and certificate format, and provide the path to
the certificate, key, and certificate authority. Of course, the exact values
differ if you configured X-Pack differently.

Here's the complete list of configuration options for the X-Pack Connector:

- `sslKeyPath`
- `sslCertificatePath`
- `sslCertificateAuthoritiesPaths`
- `certificateFormat`
- `requiresAuthentication`
- `username`
- `password`
- `transportSSLVerificationMode`
- `transportSSLEnabled`
- `sslKeystorePath`
- `sslKeyStorePassword`
- `sslTruststorePath`
- `sslTruststorePassword`

When you're finished configuring X-Pack Security, restart Elasticsearch. These
steps require a full cluster restart.

