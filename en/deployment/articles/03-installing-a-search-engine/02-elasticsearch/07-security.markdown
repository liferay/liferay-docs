---
header-id: installing-liferay-enterprise-search-security
---

# Installing Liferay Enterprise Search Security

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/using-search/installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

The very first thing you must do to secure Elasticsearch is enable X-Pack
Security. After that you can begin configuring authentication and Transport
Layer Security.

| **Elasticsearch 6.x:** If you're using Elasticsearch 6, you'll need a Liferay
| Enterprise Search (LES) subscription to use X-Pack. Starting with the Liferay
| Connector to Elasticsearch 7 (available on Liferay Marketplace), X-Pack security
| is included by default. X-Pack monitoring still requires LES.

## Enabling X-Pack Security

To enable security, add this setting in `elasticsearch.yml`:

```yaml
xpack.security.enabled: true
```

Now you can set up X-Pack users.

## Setting Up X-Pack Users

In a system using X-Pack Security and X-Pack Monitoring, these built-in
X-Pack users are important:

- `kibana`
- `elastic`

Set the passwords for all X-Pack's 
[built-in users](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/built-in-users.html).
The `setup-passwords` command is the simplest method to set the built-in users'
first-use passwords for the first time. To update a password subsequently, use
Kibana's UI or the 
[Change Password API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/security-api-change-password.html).

The `interactive` argument sets the passwords for all built-in users. The
configuration shown in these articles assumes you set all passwords to
*liferay*. Of course, that's not recommended for production systems.

```bash
./bin/elasticsearch-setup-passwords interactive
```

Elastic's 
[setup-passwords command](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/setup-passwords.html) 
documentation describes additional options.

Since you're securing Elasticsearch, remember the `elastic` user's password. 

Enable transport layer security on each node.

## Enabling Transport Layer Security

The following instructions for enabling TLS use `liferay` as the password
whenever one is needed. Use your own passwords for your installation. 

| **Important:** Elasticsearch and @product@ must share the keys and certificates
| used to configure TLS. Copy them between servers and point to the local copy in
| the corresponding configuration files.

### Generate Node Certificates

[Generate a node certificate](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/configuring-tls.html#node-certificates)
for each node. Alternatively, use a Certificate Authority to obtain node
certificates.

1.  Create a certificate authority, using 
    [X-Pack's `certutil`](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/certutil.html)
    command:

    ```bash
    ./bin/elasticsearch-certutil ca --pem --ca-dn CN=localhost
    ```

    This generates a ZIP file. Unzip the contents in the `[Elasticsearch
    Home]/config/certs` folder.

2.  Generate X.509 certificates and private keys using the CA from Step 1:

    ```bash
    ./bin/elasticsearch-certutil cert --pem --ca-cert /path/to/ca.crt --ca-key /path/to/ca.key --dns localhost --ip 127.0.0.1 --name localhost
    ```

    This generates another ZIP file. Extract the contents in the
    `[Elasticsearch Home]/config/certs` folder.

| **Note:** The `certutil` command defaults to using the *PKSC#12* format for
| certificate generation. Since Kibana does not work with PKSC#12 certificates,
| the `--pem` option (generates the certificate in PEM format) is important if
| you're using X-Pack monitoring.

**Checkpoint:** You now have the following files in your `[Elasticsearch Home]/config/certs` folder:

```bash
ca.crt
ca.key
localhost.crt
localhost.key
```

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

### Example Elasticsearch Security Configuration

For ease of copying and pasting, here is the complete Elasticsearch
configuration (`elasticsearch.yml`) used in this guide (with the Elasticsearch 6
example commented out):

```yaml
# For Elasticsearch 7.3/7.4
cluster.name: LiferayElasticsearchCluster

# X-Pack Security
xpack.security.enabled: true

## TLS/SSL settings for Transport layer
xpack.security.transport.ssl.enabled: true
xpack.security.transport.ssl.verification_mode: certificate 
xpack.security.transport.ssl.key: certs/localhost.key
xpack.security.transport.ssl.certificate: certs/localhost.crt
xpack.security.transport.ssl.certificate_authorities : [ "certs/ca.crt" ]

# TLS/SSL settings for HTTP layer
xpack.security.http.ssl.enabled: true
xpack.security.http.ssl.verification_mode: certificate 
xpack.security.http.ssl.key: certs/localhost.key
xpack.security.http.ssl.certificate: certs/localhost.crt
xpack.security.http.ssl.certificate_authorities : [ "certs/ca.crt" ]

# Comment out when Kibana and Liferay's X-Pack Monitoring are also configured
#xpack.monitoring.collection.enabled: true

# For Elasticsearch 6.5/6.8
#cluster.name: LiferayElasticsearchCluster
#
# X-Pack Security
#xpack.security.enabled: true
#
# Enable TLS/SSL
#xpack.security.transport.ssl.enabled: true # To enable Transport level SSL for internode-communication
#xpack.security.http.ssl.enabled: true # To enable HTTP level SSL required by Kibana
#
## General TLS/SSL settings for both Transport and HTTP levels
#xpack.ssl.verification_mode: certificate 
#xpack.ssl.key: certs/localhost.key
#xpack.ssl.certificate: certs/localhost.crt
#xpack.ssl.certificate_authorities : [ "certs/ca.crt" ]
#
# Comment out when Kibana and Liferay's X-Pack Monitoring are also configured
#xpack.monitoring.collection.enabled: true
```

For both Elasticsearch 6 and Elasticsearch 7, the Liferay Connector settings
are the same.

## Install and Configure the Liferay Enterprise Search Security app

If you have a Liferay Enterprise Search subscription, 
[download](https://web.liferay.com/group/customer/dxp/downloads/enterprise-search) 
the Liferay Enterprise Search Security app . Install the LPKG
file by copying it into the `Liferay Home/deploy` folder. 

To configure the X-Pack adapter, navigate to *Control Panel* &rarr;
*Configuration* &rarr; *System Settings*. Find the *Search* category and click
on the *X-Pack Security* entry. You can enter the property values here, but it's
more common to use a 
[configuration file](/docs/7-2/user/-/knowledge_base/u/understanding-system-configuration-files)
deployed to `[Liferay Home]/osgi/configs`. For the X-Pack security connector, create a file
called

```
com.liferay.portal.search.elasticsearch7.configuration.XPackSecurityConfiguration.config
```

The exact contents of the file depend on your X-Pack setup. To configure the
adapter according to the Elasticsearch setup documented here, populate the file
like this:

```properties
sslKeyPath="/path/to/localhost.key"
sslCertificatePath="/path/to/localhost.crt"
certificateFormat="PEM"
requiresAuthentication="true"
username="elastic"
password="liferay"
sslCertificateAuthoritiesPaths="/path/to/ca.crt"
transportSSLVerificationMode="certificate"
transportSSLEnabled="true"
```

The `password` should match what you set during the X-Pack password
setup above. 

The certificate and key files referenced here are the same ones used on the
Elasticsearch server. Copy them to the @product@ server and update their paths
in the configuration accordingly.

Enable authentication by setting `requiresAuthentication` to `true` and providing the
credentials for the Elasticsearch user. For TLS, enable transport TLS, set the
certificate verification mode and certificate format, and provide the path to
the certificate, key, and certificate authority. Of course, the exact values
depend on your X-Pack configuration. 

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
- `sslKeystorePassword`
- `sslTruststorePath`
- `sslTruststorePassword`

When you're finished configuring X-Pack Security, restart Elasticsearch. These
steps require a full cluster restart.

### Disabling Elasticsearch Deprecation Logging

Some Elasticsearch APIs used by Liferay's Elasticsearch 6 connector were
deprecated as of Elasticsearch 6.6 and 6.7. This can result WARN log entries in
Elasticsearch's deprecation log when @product@ is configured with Elasticsearch
6.8.x and X-Pack Security is enabled:

```sh
2019-07-16T14:47:05,779][WARN ][o.e.d.c.j.Joda           ] [
ode_name]'y' year should be replaced with 'u'. Use 'y' for year-of-era. Prefix your date format with '8' to use the new specifier.
[2019-07-16T14:47:06,007][WARN ][o.e.d.c.s.Settings       ] [
ode_name][xpack.ssl.certificate] setting was deprecated in Elasticsearch and will be removed in a future release! See the breaking changes documentation for the next major version.
[2019-07-16T14:47:06,007][WARN ][o.e.d.c.s.Settings       ] [
ode_name][xpack.ssl.certificate_authorities] setting was deprecated in Elasticsearch and will be removed in a future release! See the breaking changes documentation for the next major version.
[2019-07-16T14:47:06,008][WARN ][o.e.d.c.s.Settings       ] [
ode_name][xpack.ssl.key] setting was deprecated in Elasticsearch and will be removed in a future release! See the breaking changes documentation for the next major version.
[2019-07-16T14:47:06,463][WARN ][o.e.d.x.c.s.SSLService   ] [
ode_name]SSL configuration [xpack.http.ssl] relies upon fallback to another configuration for [key configuration, trust configuration], which is deprecated.
[2019-07-16T14:47:06,464][WARN ][o.e.d.x.c.s.SSLService   ] [
ode_name]SSL configuration [xpack.security.transport.ssl.] relies upon fallback to another configuration for [key configuration, trust configuration], which is deprecated.
```

These warnings do not signal any functional issues, and can be disabled (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/logging.html#deprecation-logging)
to learn how).
