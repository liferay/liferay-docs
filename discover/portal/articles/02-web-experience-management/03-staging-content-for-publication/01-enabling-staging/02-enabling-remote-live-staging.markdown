# Enabling Remote Live Staging [](id=enabling-remote-live-staging)

When Remote Live staging is enabled for a site, a connection is established
between the current site and another site on a remote Liferay server. The remote
site becomes the live environment and the current site becomes the staging
environment--an instance of Liferay used solely for staging. The remote (live)
Liferay server and the local (staging) Liferay server should be completely
separate systems. They should not, for example, share the same database. When
Remote Live staging is enabled, all the necessary information is transferred
over the network connecting the two servers. Content creators can use the
staging server to make their changes while the live server handles the incoming
user traffic. When changes to the site are ready to be published, they are
pushed over the network to the remote live server. 

When applying patches to a remote staging environment, you must
apply patches to all of your servers being used. Having servers on different
patch levels is not a good practice and can lead to import failures and data
corruption. It is essential that all servers are updated to the same patch level
to ensure remote staging works correctly.

Before a site administrator can enable Remote Live staging for a site, the
remote Liferay server must be added to the current Liferay server's list of
allowed servers. The current Liferay server must also be added to the remote
Liferay server's list of allowed servers. You also need to specify an
authentication key to be shared by your current and your remote server and
enable each Liferay server's tunneling servlet authentication verifier. You can
make all of these configurations in your Liferay servers'
`portal-ext.properties` files. Your first step should be to add the following
lines to your current Liferay server's `portal-ext.properties` file:

    tunneling.servlet.shared.secret=[secret]
    tunneling.servlet.shared.secret.hex=true

Then add the same lines to your remote Liferay server's `portal-ext.properties`
file:

    tunneling.servlet.shared.secret=[secret]
    tunneling.servlet.shared.secret.hex=true

@product@'s use of a pre-shared key between your staging and production
environments helps secure the remote publication process. It also removes the
need to send the publishing user's password to the remote server for web service
authentication. Using a pre-shared key allows @product@ to create an
authorization context (permission checker) from the provided email address,
screen name, or user ID *without* the user's password.

The values that you can specify for the `tunneling.servlet.shared.secret`
property depend on the configured encryption algorithm, since different
encryption algorithms support keys of different lengths. Please see the
[HTTP Tunneling](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#HTTP%20Tunneling)
properties documentation for more information. Note that the following key
lengths are supported by the available encryption algorithms:

- AES: 128, 192, and 256 bit keys
- Blowfish: 32 - 448 bit keys
- DESede (Triple DES): 56, 112, or 168 bit keys (However, Liferay places an
  artificial limit on the minimum key length and does not support the 56 bit key
  length)

To prevent potential character encoding issues, you can use one of the following
two strategies:

1. Use hexadecimal encoding (recommended). E.g., if your password was
   *abcdefghijklmnop*, you'd use the following settings in your
   `portal-ext.properties` file:

        tunneling.servlet.shared.secret=6162636465666768696a6b6c6d6e6f70
        tunneling.servlet.shared.secret.hex=true

2. Use printable ASCII characters (less secure). This degrades the password
   entropy.

    If you don't use hexadecimal encoding, i.e. if you use the default setting
    `tunneling.servlet.shared.secret.hex=false`, the value of the
    `tunneling.servlet.shared.secret` property *must* be ASCII compliant.

Once you've chosen a key, make sure that value of your current server matches
the value of your remote server.

**Important:** Do not share the key with any user. It is used exclusively for 
communication between staging and production environments. Any user with 
possession of the key can manage the production server, execute server-side
Java code, or worse.

One last thing you'll need to do is update the *TunnelAuthVerfierConfiguration*
of your remote Liferay instance. To do this, navigate to the Control Panel
&rarr; *Configuration* &rarr; *System Settings* &rarr; *Foundation* &rarr;
*Tunnel Auth Verifier*. Click */api/liferay/do* and insert the additional IP
addresses you're using in the *Hosts allowed* field. Then select *Update*.

Alternatively, you can also write this configuration into an OSGi file (e.g.,
`osgi/configs/com.liferay.portal.security.auth.verifier.tunnel.module.configuration.TunnelAuthVerifierConfiguration-default.config`)
in your @product@ instance:

    enabled=true
    hostsAllowed=127.0.0.1,SERVER_IP,[Local server IP address]
    serviceAccessPolicyName=SYSTEM_USER_PASSWORD
    urlsIncludes=/api/liferay/do

Remember to restart both Liferay servers after making these portal properties
updates. After restarting, log back in to your local Liferay instance as
a site administrator. Then navigate to the *Publishing* option in Site
Administration and select *Staging*. Select *Remote Live* and additional options
appear.

![Figure 1: After your remote Liferay server and local Liferay server have been configured to communicate with each other, you have to specify a few Remote Live connection settings.](../../../../images/remote-live-staging-settings.png)

First, enter your remote Liferay server's IP address into the Remote Host/IP
field. If the remote Liferay server is a cluster, you can set the Remote Host/IP
to the load balanced IP address of the cluster in order to increase the
availability of the publishing process. Next, enter the port on which the remote
Liferay instance is running into the Remote Port field. You only need to enter a
Remote Path Context if a non-root portal servlet context is being used on the
remote Liferay server. Finally, enter the site ID of the site on the remote
Liferay server that will be used for the Live environment. If a site hasn't
already been prepared for you on the remote Liferay server, you can log in to
the remote Liferay server and create a new blank site. After the site has been
created, note the site ID so you can enter it into the Remote Site ID field on
your local Liferay server. You can find any site's ID by selecting the site's
name on the Sites page of the Control Panel. Finally, it's best to check the
*Use a Secure Network Connection* field to use HTTPS for the publication of
pages from your local (staging) Liferay server to your remote (live) Liferay
server.

Similar to Local Live staging, it is generally a good idea to turn remote
staging on at the beginning of your site's development for good performance.
When you're using Remote Live staging, and you are publishing a large amount of
content, your publication could be slow and cause a large amount of network
traffic. @product@'s system is very fast for the amount of data being
transferred over the network. This is because the data transfer is completed
piecemeal, instead of one large data dump. You can control the size of data
transactions by setting the following portal property in your
`portal-ext.properties` file:

    staging.remote.transfer.buffer.size

That's all you need to do to enable Remote Live Staging! Note that if you fail
to set the tunneling servlet shared secret or the values of these properties on
your current and remote servers don't match, you won't be able to enable staging
and an error message appears. When a user attempts to publish changes from the
local (staging) server to the remote (live) server, @product@ passes the user's
email address, screen name, or user ID to the remote server to perform a
permission check. In order for a publishing operation to succeed, the operation
must be performed by a user that has identical credentials and permissions on
both the local (staging) and the remote (live) server. This is true regardless
of whether the user attempts to publish the changes immediately or attempts to
schedule the publication for later.

If only a few users should have permission to publish changes from staging to
production, it's easy enough to create a few user accounts on the remote server
that match a selected few on the local server. However, the more user accounts
that you have to create, the more tedious this job becomes and the more likely
you are to make a mistake. And you not only have to create identical user
accounts, you also have to ensure that these users have identical permissions.
For this reason, it's recommended that you use LDAP to copy selected user
accounts from your local (staging) Liferay server to your remote (live) Liferay
server. Liferay's Virtual LDAP Server application, available on Liferay
Marketplace, makes this easy.

+$$$

**Important:** If your instance is set up to validate IPv6 addresses, you'll
need to configure your Remote Live Connection Settings. Restart your Liferay
instance and navigate back to the Staging page. Select the *Remote Live* radio
selector and specify the fields for your remote site. The *Remote Host/IP* field
should match the host you specified as your `tunnel.servlet.hosts.allowed`
property in the `portal-ext.properties` file (e.g., *[0:0:0:0:0:0:0:1]*). Make
sure to include the brackets. Fill in the rest of the information relevant to
your site and click *Save*.

To check if the remote site is running on an IPv6 address, add a new application
to the staged site, and then select *Staging* &rarr; *Publish to Live* from the
top Control Menu. The changes are published to your remote staged site.

$$$

## Remote Live Staging Verification [](id=remote-live-staging-verification)

Before publishing *any* content, verify that **all** the necessary steps above
have been completed. Otherwise, Remote Staging will fail.

1.  The `tunnel.servlet.hosts.allowed` values are set in the
    `portal-ext.properties` file in both the staging and the production
    environment.

2.  The `tunneling.servlet.shared.secret` values are set in the
    `portal-ext.properties` file in both the staging and the production
    environment.

3.  The *TunnelAuthVerfierConfiguration* of your @product@ instance is updated.
    This is a major change between legacy Portal and @product@. To do this:

    a) Navigate to the Control Panel &rarr; *Configuration* &rarr; *System
       Settings* &rarr; *Foundation*.
    b) Search for *Tunnel Auth Verifier*.
    c) Click */api/liferay/do* and insert the additional IP addresses you're
       using in the *Hosts allowed* field.
    d) Click *Update*.

4.  The users who initiate the publishing processes exist on both the remote and
    staging environments. In addition, the users must have the same credentials
    (e.g., screen name, email, roles, and password). 

**Important:** Never clone your @product@ database; doing this can duplicate
important data used by Staging (e.g., UUID), causing the Remote Publication
process to fail.

You should proceed only when all four major steps have been completed. 
