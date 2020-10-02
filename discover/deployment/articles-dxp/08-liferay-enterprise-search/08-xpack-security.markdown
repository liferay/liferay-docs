---
header-id: securing-elasticsearch-6-with-x-pack
---

# Securing Elasticsearch 6.5 with X-Pack

[TOC levels=1-4]

X-Pack is an 
[Elastic extension](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/setup-xpack.html)
for securing and monitoring Elasticsearch clusters. If you use Elasticsearch,
you should secure it with X-Pack. The security features of X-Pack include
authenticating access to the Elasticsearch cluster's data and encrypting
Elasticsearch's internal and external communications. These are necessary
security features for most production systems. A Liferay Enterprise Search
subscription gets you access to both monitoring and security. Contact
[Liferay's Sales department for more information](https://www.liferay.com/contact-us#contact-sales).

| **Compatibility:** To use X-Pack Security and/or Monitoring with Elasticsearch
| 6.5 and @product@, you must use the proper connector to Elasticsearch.  
| 
| The [_Liferay Connector to Elasticsearch 6_, version
| 1.1.0+](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191013&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191060) is required to set up Elasticsearch
| 6.5 with security and monitoring.

Here's an overview of using X-Pack to secure the data indexed in Elasticsearch:

1.  Get an Enterprise Search subscription.

2.  Configure X-Pack to require authentication and 
    [encryption](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/configuring-tls.html#configuring-tls).

3.  Download and install the [Liferay Enterprise Search
    Security](https://web.liferay.com/group/customer/dxp/downloads/enterprise-search). 

4.  Configure the LES Secuirty app with the proper credentials and encryption
    information.

5.  Restart Elasticsearch. These steps require a full cluster restart.

Following these instructions gives you a basic working installation of
Elasticsearch communicating freely with @product@, but read Elastic's
documentation to learn about additional configuration options, features, and the
architecture of
[X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/configuring-security.html). 

## Setting Up X-Pack Users

In a system using X-Pack Security and X-Pack Monitoring, two of the built-in
X-Pack users are important: `kibana` and `elastic`.

Set the passwords for all X-Pack's 
[built-in users](https://www.elastic.co/guide/en/x-pack/current/setting-up-authentication.html#built-in-users).
The `setup-passwords` command is the simplest method to set the built-in users'
first-use passwords for the first time. To update a password subsequently, use
Kibana's UI or the 
[Change Password API](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/security-api-change-password.html).

The `interactive` argument lets you set the passwords for all built-in users.
The configuraiton shown in these articles assumes you set all of the
passwords to *liferay*. Of course, that's not recommended for production systems.

    ./bin/elasticsearch-setup-passwords interactive

See Elastic's documentation on the 
[setup-passwords command](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/setup-passwords.html) 
for additional options.

Since you're securing Elasticsearch, make sure you keep track of the password
set for the `elastic` user. 

We recommend enabling transport layer security on each node.

## Enabling Transport Layer Security

The following instructions for enabling TLS use `liferay` as the password
whenever one is needed. Customize these as appropriate for your installation. 

| **Important:** Elasticsearch and @product@ must share the keys and certificates
| used to configure SSL. Copy them between servers and point to the local copy in
| the corresponding configuration files.

### Generate Node Certificates

[Generate a node certificate](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/configuring-tls.html#node-certificates)
for each node. You can, of course, use a Certificate Authority toobtain node 
certificates.

1.  Create a certificate authority, using 
      [X-Pack's `certutil`](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/certutil.html)
      command:

        ./bin/elasticsearch-certutil ca --pem --ca-dn CN=localhost

    This generates a ZIP file. Unzip the contents somewhere safe.

2. Generate X.509 certificates and private keys using the CA from Step 1. For
   example:

        ./bin/elasticsearch-certutil cert --pem --ca-cert /path/to/ca.crt --ca-key /path/to/ca.key --dns localhost --ip 127.0.0.1 --name localhost

    This generates another ZIP file. Extract the contents somewhere in the
    `Elasticsearch Home/config` folder.

| **Note:** The `certutil` command defaults to using the *PKSC#12* format
| for certificate generation. Kibana does not work with PKSC#12
| certificates, so the `--pem` option (to generate the certificate in PEM
| format) is important if you're using X-Pack monitoring.

### Enable TLS

[Enable TLS](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/configuring-tls.html#enable-ssl) 
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

After X-Pack is installed and TLS is enabled, configure the LES Security
app in @product@.

## Install and Configure the Liferay Enterprise Search Security app

If you have a Liferay Enterprise Search subscription, 
[download](https://web.liferay.com/group/customer/dxp/downloads/enterprise-search) 
the Liferay Enterprise Search Security app. Install the LPKG
file by copying it into the `Liferay Home/deploy` folder. That's all there is to
it.

To configure the secuirty app, navigate to *Control Panel*
&rarr; *Configuration* &rarr; *System Settings*. Find the *Foundation* category and
click on the *X-Pack Security* entry. You can enter the property values here, but
it's more common to use a 
[configuration file](/docs/7-0/user/-/knowledge_base/u/understanding-system-configuration-files)
deployed to `Liferay Home/osgi/configs`. Create a file
called

    com.liferay.portal.search.elasticsearch6.xpack.security.internal.configuration.XPackSecurityConfiguration.config

The exact contents of the file depend on your X-Pack setup. To configure the adapter
according to the Elasticsearch setup documented here, populate the file with
these contents:

    sslKeyPath="/path/to/localhost.key"
    sslCertificatePath="/path/to/config/localhost.crt"
    certificateFormat="PEM"
    requiresAuthentication="true"
    username="elastic"
    password="liferay"
    sslCertificateAuthoritiesPaths="/path/to/ca.crt"
    transportSSLVerificationMode="certificate"
    transportSSLEnabled="true"

The certificate and key files referenced here are the same ones used on the
Elasticsearch server. Copy them to the @product@ server and update their paths
in the configuration accordingly.

Enable authentication by setting authentication to `required` and providing the
credentials for the Elasticsearch user. For SSL, enable transport SSL, set the
certificate verification mode and certificate format, and provide the path to
the certificate, key, and certificate authority. Of course, the exact values
will differ if you configured X-Pack differently.

Here's the complete list of configuration options:

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
- `sslKeystorePassword`
- `sslTruststorePath`
- `sslTruststorePassword`

When you're finished configuring X-Pack Security, restart Elasticsearch. These
steps require a full cluster restart.
