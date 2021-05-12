---
header-id: installing-liferay-enterprise-search-security
---

# Installing Liferay Enterprise Search Security

[TOC levels=1-4]

## Enabling X-Pack Security

| **Elasticsearch 6.x:** If you're using Elasticsearch 6, you'll need a Liferay
| Enterprise Search (LES) subscription to use X-Pack Security in your Liferay DXP-Elasticsearch stack.
| Starting with the _Liferay Connector to Elasticsearch 7_ (available on Liferay Marketplace), X-Pack security
| is included by default. Liferay Enterprise Search Monitoring still requires LES.

The first thing to do is enable X-Pack security. Add this setting in
`elasticsearch.yml`:

    xpack.security.enabled: true

Now you can set up X-Pack users.

## Setting Up X-Pack Users in Elasticsearch

In a system using Security and Monitoring, these built-in
X-Pack users of Elasticsearch are important to enable authentication in clients like Liferay or Kibana:

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

## Encrypting Communications in Elasticsearch

The following instructions for enabling SSL/TLS use `liferay` as the password
whenever one is needed. Use your own passwords for your installation. 

| **Important:** Elasticsearch and @product@ must share the keys and certificates
| used to configure SSL. Copy them between servers and point to the local copy in
| the corresponding configuration files.

### Generate Node Certificates

[Generate a
certificate](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/configuring-tls.html#node-certificates)
for each node, or generate a certificate to be used on all nodes and
clients---like Liferay. Alternatively, use your certificate authority to obtain
node certificates.

1. Generate X-Pack certificate authority using the X-Pack's
   [`certutil`](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/certutil.html)
   command:

    ```bash
    ./bin/elasticsearch-certutil ca --ca-dn CN=elastic-ca
    ```

   This generates a file called `elastic-stack-ca.p12`. 

   To generate the certificate authority cert and private key in PEM format,

   ```bash
   ./bin/elasticsearch-certutil ca --pem --ca-dn CN=elastic-ca
   ```

1. Move the certificate authority file(s) file to the `[Elasticsearch
   Home]/config/certs` folder.

1. Generate X.509 certificates and private keys using the CA you created:


   To generate certificates and keys in `PKCS#12` format, run

   ```bash
   ./bin/elasticsearch-certutil cert --ca config/certs/elastic-stack-ca.p12 --ca-pass liferay --dns localhost --ip 127.0.0.1 --name elastic-nodes
   ```

   To generate certificates and keys in `PEM` format, run
    
   ```bash
   ./bin/elasticsearch-certutil cert --pem --ca-cert config/certs/ca.crt --ca-key config/certs/ca.key --dns localhost --ip 127.0.0.1 --name elastic-nodes
   ```

   To generate `PEM` format node certificates and keys from the `PKSC#12`
   certificate authority, run

   ```bash
   ./bin/elasticsearch-certutil cert --pem --ca config/certs/elastic-stack-ca.p12 --ca-pass liferay --dns localhost --ip 127.0.0.1 --name elastic-nodes
   ```

   To generate a certificate that works with multiple hosts (for example to use
   the same certificate on all Elasticsearch and Liferay servers), use
   comma-separated lists when listing the DNS names and IP addresses:

   ```bash
   ./bin/elasticsearch-certutil cert --ca config/certs/elastic-stack-ca.p12 --ca-pass liferay --dns localhost,example.com,es-node1,es-node2,es-node3 --ip 127.0.0.1,<IPv4-address-2>,<IPv4-address-3>,<IPv4-address-4>
   ```

   The `elasticsearch-certutil cert` command generates another file called
   `elastic-nodes.p12` (feel free to name it differently).

   | **Note:** The `certutil` command defaults to using the PKCS#12 format
   | for certificate generation, which works with your Elastic Stack 7.x. Kibana
   | 6.x does not work with PKCS#12 certificates, so the `--pem` option
   | (generates the certificate in PEM format) is important if you're using
   | Liferay 7.1 and Kibana 6.x with *Liferay Enterprise Search Monitoring*. The
   | PEM command  for each case generates two ZIP files: `ca.crt` and
   | `ca.key`, `elastic-nodes.crt` and `elastic-nodes.key`. Unzip the
   | archive contents in `[Elasticsearch Home]/config/certs`.

1. Move `elastic-nodes.p12` to the `[Elasticsearch Home]/config/certs` folder.

    **Checkpoint:** You now have the following files in your `[Elasticsearch
    Home]/config/certs` folder:

    ```bash
    elastic-nodes.p12
    elastic-stack-ca.p12
    ```
    
    or
    
    ```bash
    ca.crt
    ca.key
    elastic-nodes.crt
    elastic-nodes.key
    ```

1. Copy the files to the same folder on each Elasticsearch node, and to an
   appropriate location on each Liferay server node.

The certificates and keys are ready to use in your Elasticsearch configuration.

### Enable TLS in Elasticsearch

Enable
TLS ([6.x](https://www.elastic.co/guide/en/elasticsearch/reference/6.x/configuring-tls.html#enable-ssl), [7.x](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/configuring-tls.html)
on each node via its `[Elasticsearch Home]/config/elasticsearch.yml` file.

1. Enable transport layer TLS with these settings in `elasticsearch.yml` for
   inter-node communication:

    ```yaml
    xpack.security.transport.ssl.enabled: true
    ```

1. Add the certificate, key and certificate authority paths to each node's
   `elasticsearch.yml`:

    ```yaml
    # PKCS#12
    xpack.security.transport.ssl.keystore.path: certs/elastic-nodes.p12
    xpack.security.transport.ssl.keystore.password: liferay
    xpack.security.transport.ssl.truststore.path: certs/elastic-nodes.p12
    xpack.security.transport.ssl.truststore.password: liferay
    xpack.security.transport.ssl.verification_mode: certificate
    ```

    The example paths above assume you added the certificates to
    `[Elasticsearch Home]/config/certs`. 

1. Enable TLS on the HTTP layer to encrypt client communication:

    ```yaml
    xpack.security.http.ssl.enabled: true
    ```

1. Configure the certificate, key, and certificate authority paths to each
   node's `elasticsearch.yml`:

    ```yaml
    # PKCS#12
    xpack.security.http.ssl.keystore.path: certs/elastic-nodes.p12
    xpack.security.http.ssl.keystore.password: liferay
    xpack.security.http.ssl.truststore.path: certs/elastic-nodes.p12
    xpack.security.http.ssl.truststore.password: liferay
    ```

To configure Elasticsearch using `PEM` format certificates, please refer to the examples at the bottom of this page. After TLS is enabled, configure the X-Pack Security in @product@.

## Configuring Security in Liferay's Elasticsearch Connector

| **Elasticsearch 6.x:** If you have a Liferay Enterprise Search subscription, 
| [download](https://web.liferay.com/group/customer/dxp/downloads/enterprise-search) 
| the Liferay Enterprise Search Security app. Install the LPKG
| file by copying it into the `Liferay Home/deploy` folder. 

| **Elasticsearch 7.x:** The Liferay Connector to Elasticsearch 7 includes
| security integration by default, so you don't need to download and install the LES Security app separately.

To configure security, navigate to *Control Panel* &rarr; *Configuration*
&rarr; *System Settings*. Find the *Search* category and click on the *X-Pack
Security* entry. You can enter the property values here, but it's more common
to use a 
[configuration file](/docs/7-1/user/-/knowledge_base/u/understanding-system-configuration-files)
deployed to `[Liferay Home]/osgi/configs`.

On Elasticsearch 7.x, for the security adapter, create a file
called


    com.liferay.portal.search.elasticsearch7.configuration.XPackSecurityConfiguration.config

For Elasticsearch 6.x installations the file is named 

    com.liferay.portal.search.elasticsearch6.xpack.security.internal.configuration.XPackSecurityConfiguration.config

The exact contents of the file depend on your X-Pack setup in Elasticsearch. To configure the
adapter according to the Elasticsearch setup documented here, populate the file
like this (`PKCS#12`):

```properties
certificateFormat="PKCS#12"
sslKeystorePath="/PATH/TO/elastic-nodes.p12"
sslKeystorePassword="liferay"
sslTruststorePath="/PATH/TO/elastic-nodes.p12"
sslTruststorePassword="liferay"
requiresAuthentication=B"true"
username="elastic"
password="liferay"
transportSSLVerificationMode="certificate"
transportSSLEnabled=B"true"
```

Use settings like this if you're using `PEM` format certificates:

```properties
certificateFormat="PEM"
sslKeyPath="/PATH/TO/elastic-nodes.key"
sslCertificatePath="/PATH/TO/elastic-nodes.crt"
requiresAuthentication=B"true"
username="elastic"
password="liferay"
sslCertificateAuthoritiesPaths="/PATH/TO/ca.crt"
transportSSLVerificationMode="certificate"
transportSSLEnabled="true"
```

Note that the `password` should match what you set during the X-Pack password
setup. 

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

## Example Elasticsearch Security Configurations

Here are the complete Elasticsearch 7.x configurations from this article (`elasticsearch.yml`; applies equally to Elasticsearch 6.5.x+):

### Example Elasticsearch Security Configuration - PKCS#12

```yaml
cluster.name: LiferayElasticsearchCluster

# X-Pack Security
xpack.security.enabled: true

## TLS/SSL settings for Transport layer (PKCS#12)
xpack.security.transport.ssl.enabled: true
xpack.security.transport.ssl.keystore.path: certs/elastic-nodes.p12
xpack.security.transport.ssl.keystore.password: liferay
xpack.security.transport.ssl.truststore.path: certs/elastic-nodes.p12
xpack.security.transport.ssl.truststore.password: liferay
xpack.security.transport.ssl.verification_mode: certificate

# TLS/SSL settings for HTTP layer (PKCS#12)
xpack.security.http.ssl.enabled: true
xpack.security.http.ssl.keystore.path: certs/elastic-nodes.p12
xpack.security.http.ssl.keystore.password: liferay
xpack.security.http.ssl.truststore.path: certs/elastic-nodes.p12
xpack.security.http.ssl.truststore.password: liferay

# Comment out when Kibana and Liferay's LES Monitoring are also configured
#xpack.monitoring.collection.enabled: true
```

## Example Elasticsearch Security Configuration - PEM

```yaml
cluster.name: LiferayElasticsearchCluster

# X-Pack Security
xpack.security.enabled: true

## TLS/SSL settings for Transport layer (PEM)
xpack.security.transport.ssl.enabled: true
xpack.security.transport.ssl.certificate: certs/elastic-nodes.crt
xpack.security.transport.ssl.certificate_authorities: ["certs/ca.crt"]
xpack.security.transport.ssl.key: certs/elastic-nodes.key
xpack.security.transport.ssl.key_passphrase: liferay
xpack.security.transport.ssl.verification_mode: certificate

# TLS/SSL settings for HTTP layer (PEM)
xpack.security.http.ssl.enabled: true
xpack.security.http.ssl.certificate: certs/elastic-nodes.crt
xpack.security.http.ssl.certificate_authorities: ["certs/ca.crt"]
xpack.security.http.ssl.key: certs/elastic-nodes.key
xpack.security.http.ssl.key_passphrase: liferay

# Comment out when Kibana and Liferay's LES Monitoring are also configured
#xpack.monitoring.collection.enabled: true
```
