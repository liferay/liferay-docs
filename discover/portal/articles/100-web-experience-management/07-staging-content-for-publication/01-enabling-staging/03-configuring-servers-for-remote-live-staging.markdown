# Configuring Servers for Remote Live Staging [](id=configuring-servers-for-remote-live-staging)

Before you can enable Remote Live staging for a site, you must satisfy some
necessary requirements:

- Add the remote Liferay server to the current Liferay server's list of allowed
  servers, and vice versa.
- Specify an authentication key to be shared by your current and remote server.
- Enable each Liferay server's tunneling servlet authentication verifier.
- Update the Tunnel Auth Verifier Configuration of your remote Liferay instance.

Follow the steps below to configure your servers for Remote Live staging.

1.  Add the following lines to your current Liferay server's
    `portal-ext.properties` file:

        tunneling.servlet.shared.secret=[secret]
        tunneling.servlet.shared.secret.hex=true

2.  Add the same lines to your remote Liferay server's `portal-ext.properties`
    file:

        tunneling.servlet.shared.secret=[secret]
        tunneling.servlet.shared.secret.hex=true

    @product@'s use of a pre-shared key between your staging and production
    environments helps secure the remote publication process. It also removes
    the need to send the publishing user's password to the remote server for web
    service authentication. Using a pre-shared key creates an authorization
    context (permission checker) from the provided email address, screen name,
    or user ID *without* the user's password.

3.  Specify the values for the servers' `tunneling.servlet.shared.secret`
    property.

    The values for these properties depend on the chosen configured encryption
    algorithm, since different encryption algorithms support keys of different
    lengths. See the
    [HTTP Tunneling](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#HTTP%20Tunneling)
    properties documentation for more information. Note that the following key
    lengths are supported by the available encryption algorithms:

    - AES: 128, 192, and 256 bit keys
    - Blowfish: 32 - 448 bit keys
    - DESede (Triple DES): 56, 112, or 168 bit keys (However, Liferay places an
      artificial limit on the minimum key length and does not support the 56 bit
      key length)

    To prevent potential character encoding issues, you can use one of the
    following two strategies:

    3a. Use hexadecimal encoding (recommended). For example, if your password
       was *abcdefghijklmnop*, you'd use the following settings in your
       `portal-ext.properties` file:

        tunneling.servlet.shared.secret=6162636465666768696a6b6c6d6e6f70
        tunneling.servlet.shared.secret.hex=true

    3b. Use printable ASCII characters (less secure). This degrades the password
       entropy.

    If you don't use hexadecimal encoding (i.e., if you use the default setting
    `tunneling.servlet.shared.secret.hex=false`), the
    `tunneling.servlet.shared.secret` property's value *must* be ASCII
    compliant.

    Once you've chosen a key, make sure the value of your current server matches
    the value of your remote server.

    **Important:** Do not share the key with any user. It is used exclusively
    for communication between staging and production environments. Any user with
    possession of the key can manage the production server, execute server-side
    Java code, etc.

4.  Update the *TunnelAuthVerfierConfiguration* of your remote Liferay instance.
    To do this, navigate to the Control Panel &rarr; *Configuration* &rarr;
    *System Settings* &rarr; *Foundation* &rarr; *Tunnel Auth Verifier*. Click
    */api/liferay/do* and insert the additional IP addresses you're using in the
    *Hosts allowed* field. Then select *Update*.

    Alternatively, you can also write this configuration into an OSGi file (e.g.,
    `osgi/configs/com.liferay.portal.security.auth.verifier.tunnel.module.configuration.TunnelAuthVerifierConfiguration-default.config`)
    in your @product@ instance:

        enabled=true
        hostsAllowed=127.0.0.1,SERVER_IP,[Local server IP address]
        serviceAccessPolicyName=SYSTEM_USER_PASSWORD
        urlsIncludes=/api/liferay/do

5.  Restart both Liferay servers after making these configuration updates. After
    restarting, log back in to your local Liferay instance as a site
    administrator.

That's all you need to do to configure Remote Live Staging! You can now
[enable it](/discover/portal/-/knowledge_base/7-1/enabling-remote-live-staging)!

+$$$

**Note:** Never clone your @product@ database; doing this can duplicate
important data used by Staging (e.g., UUID), causing the Remote Publication
process to fail.

$$$

For additional information on configuring Remote Live staging, see the topics
below.

## Applying Patches When Using Remote Staging [](id=applying-patches-when-using-remote-staging)

When applying patches to a remote staging environment, you must apply them to
all your servers. Having servers on different patch levels is not a good
practice and can lead to import failures and data corruption. It is essential
that all servers are updated to the same patch level to ensure remote staging
works correctly.

## Configuring Remote Staging's Buffer Size [](id=configuring-remote-stagings-buffer-size)

Similar to Local Live staging, it is a good idea to turn remote staging on at
the beginning of your site's development for good performance. When you're using
Remote Live staging, and you are publishing a large amount of content, your
publication could be slow and cause a large amount of network traffic.
@product@'s system is very fast for the amount of data being transferred over
the network. This is because the data transfer is completed piecemeal, instead
of one large data dump. You can control the size of data transactions by setting
the following portal property in your `portal-ext.properties` file:

    staging.remote.transfer.buffer.size

This property sets the file block sizes for remote staging. If a LAR file used
for remote staging exceeds this size, the file will be split into multiple files
prior to transmission and then reassembled on the remote server. The default
buffer size is 10 megabytes.

## Validating IPv6 Addresses [](id=validating-ipv6-addresses)

If your instance is set up to validate IPv6 addresses, you'll need to configure
your Remote Live Connection Settings. Restart your Liferay instance and navigate
back to the Staging page. Select the *Remote Live* radio selector and specify
the fields for your remote site. The *Remote Host/IP* field should match the
host you specified as your `tunnel.servlet.hosts.allowed` property in the
`portal-ext.properties` file (e.g., *[0:0:0:0:0:0:0:1]*). Make sure to include
the brackets. Fill in the rest of the information relevant to your site and
click *Save*.

To check if the remote site is running on an IPv6 address, add a new application
to the staged site, and then select *Staging* &rarr; *Publish to Live* from the
top Control Menu. The changes are published to your remote staged site.
