---
header-id: installing-liferay-enterprise-search-security
---

# Installing Liferay Enterprise Search Security

[TOC levels=1-4]

Once X-Pack is installed, start securing Elasticsearch by configuring the
built-in user passwords.

## Enabling X-Pack Security

| **Elasticsearch 6.x:** If you're using Elasticsearch 6, you'll need a Liferay
| Enterprise Search (LES) subscription to use X-Pack. Starting with the Liferay
| Connector to Elasticsearch 7 (available on Liferay Marketplace), X-Pack security
| is included by default. X-Pack monitoring still requires LES.

The first thing to do is enable X-Pack security. Add this setting in
`elasticsearch.yml`:

    xpack.security.enabled: true

Now you can set up X-Pack users.

## Setting Up X-Pack Users

In a system using X-Pack Security and X-Pack Monitoring, these built-in
X-Pack users are important:

- `kibana_system`
- `elastic`

Set the passwords for all X-Pack's 
[built-in users](https://www.elastic.co/guide/en/x-pack/6.5/setting-up-authentication.html#built-in-users).
The `setup-passwords` command is the simplest method to set the built-in users'
first-use passwords for the first time. To update a password subsequently, use
Kibana's UI or the 
[Change Password API](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/security-api-change-password.html).

The `interactive` argument sets the passwords for all built-in users. The
configuration shown in these articles assumes you set all passwords to
*liferay*. Of course, that's not recommended for production systems.

    ./bin/elasticsearch-setup-passwords interactive

Elastic's 
[setup-passwords command](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/setup-passwords.html) 
documentation describes additional options.

Since you're securing Elasticsearch, remember the `elastic` user's password. 

Enable transport layer security on each node.

## Enabling Transport Layer Security

The following instructions for enabling TLS use `liferay` as the password
whenever one is needed. Use your own passwords for your installation. 

| **Important:** Elasticsearch and @product@ must share the keys and certificates
| used to configure SSL. Copy them between servers and point to the local copy in
| the corresponding configuration files.

### Generate Node Certificates

[Generate a node certificate](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/configuring-tls.html#node-certificates)
for each node. You can, of course, use a Certificate Authority to obtain node 
certificates.

1.  Create a certificate authority, using 
    [X-Pack's `certutil`](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/certutil.html)
    command:

        ./bin/elasticsearch-certutil ca --pem --ca-dn CN=localhost

    This generates a ZIP file. Unzip the contents somewhere safe.

2.  Generate X.509 certificates and private keys using the CA from Step 1:

        ./bin/elasticsearch-certutil cert --pem --ca-cert /path/to/ca.crt --ca-key /path/to/ca.key --dns localhost --ip 127.0.0.1 --name localhost

    This generates another ZIP file. Extract the contents somewhere in the
    `[Elasticsearch Home]/config` folder.

| **Note:** The `certutil` command defaults to using the *PKSC#12* format for
| certificate generation. Since Kibana does not work with PKSC#12 certificates,
| the `--pem` option (generates the certificate in PEM format) is important if
| you're using X-Pack monitoring.

### Enable TLS

### Enable TLS for Elasticsearch 7

[Enable TLS](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/configuring-tls.html#enable-ssl) 
on each node via its `elasticsearch.yml`.

1.  Enable transport layer TLS with these settings in `elasticsearch.yml` for
    inter-node communication:

    ```yaml
    xpack.security.transport.ssl.enabled: true
    ```

2.  Add the certificate, key and certificate authority paths to each node's
    `elasticsearch.yml`:

    ```yaml
    xpack.security.transport.ssl.certificate: certs/localhost.key
    xpack.security.transport.ssl.certificate_authorities: [ "certs/ca.crt" ]
    xpack.security.transport.ssl.key: certs/localhost.crt
    xpack.security.transport.ssl.verification_mode: certificate
    ```

    The example paths above assume you added the certificate to `Elasticsearch
    Home/config/`. 

3.  Enable TLS on the HTTP layer to encrypt client communication:

    ```yaml
    xpack.security.http.ssl.enabled: true
    ```
4.  Configure the certificate, key, and certificate authority paths to each
    node's `elasticsearch.yml`:

    ```yaml
    xpack.security.http.ssl.certificate_authorities: [ "certs/ca.crt" ]
    xpack.security.http.ssl.certificate: certs/localhost.crt
    xpack.security.http.ssl.key: certs/localhost.key
    xpack.security.http.ssl.verification_mode: certificate
    ```

### Elasticsearch 6 TLS

The settings on Elasticsearch 6 were slightly different than those presented
above for Elasticsearch 7.
[Enable TLS](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/configuring-tls.html#enable-ssl)
on each node via its `elasticsearch.yml`.

1.  Add the certificate, key and certificate authority paths to each node's
    `elasticsearch.yml`:

        xpack.ssl.certificate: certs/localhost.crt
        xpack.ssl.certificate_authorities: [ "certs/ca.crt" ]
        xpack.ssl.key: certs/localhost.key
        xpack.ssl.verification_mode: certificate 

    The example paths above assume you added the certificate to `Elasticsearch
    Home/config/`. 

2.  Enable transport layer TLS with these settings in `elasticsearch.yml`:

        xpack.security.transport.ssl.enabled: true

3.  Enable TLS on the HTTP layer to encrypt client communication:

        xpack.security.http.ssl.enabled: true

After X-Pack is installed and TLS is enabled, configure the X-Pack Security
adapter in @product@.


## Install and Configure the Liferay Enterprise Search Security app

If you have a Liferay Enterprise Search subscription, 
[download](https://web.liferay.com/group/customer/dxp/downloads/enterprise-search) 
the Liferay Enterprise Search Security app. Install the LPKG
file by copying it into the `Liferay Home/deploy` folder. 

To configure security, navigate to *Control Panel* &rarr;
*Configuration* &rarr; *System Settings*. Find the *Search* category and click
on the *X-Pack Security* entry. You can enter the property values here, but it's
more common to use a 
[configuration file](/docs/7-1/user/-/knowledge_base/u/understanding-system-configuration-files)
deployed to `[Liferay Home]/osgi/configs`. For the security adapter, create a file
called

    com.liferay.portal.search.elasticsearch7.configuration.XPackSecurityConfiguration.config

For Elasticsearch 6 installations the file is named 

    com.liferay.portal.search.elasticsearch6.xpack.security.internal.configuration.XPackSecurityConfiguration.config

The exact contents of the file depend on your X-Pack setup. To configure the
adapter according to the Elasticsearch setup documented here, populate the file
like this:

    sslKeyPath="/path/to/localhost.key"
    sslCertificatePath="/path/to/localhost.crt"
    certificateFormat="PEM"
    requiresAuthentication="true"
    username="elastic"
    password="liferay"
    sslCertificateAuthoritiesPaths="/path/to/ca.crt"
    transportSSLVerificationMode="certificate"
    transportSSLEnabled="true"

Note that the `password` should match what you set during the X-Pack password
setup above. 

The certificate and key files referenced here are the same ones used on the
Elasticsearch server. Copy them to the @product@ server and update their paths
in the configuration accordingly.

Enable authentication by setting `requiresAuthentication` to `true` and providing the
credentials for the Elasticsearch user. For SSL, enable transport SSL, set the
certificate verification mode and certificate format, and provide the path to
the certificate, key, and certificate authority. Of course, the exact values
depend on your X-Pack configuration. 

Here's the complete list of security configuration options:

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
