# Configuring Remote Staging in a Clustered Environment [](id=configuring-remote-staging-in-a-clustered-environment)

If you're running @product@ as a
[clustered environment](/discover/deployment/-/knowledge_base/7-1/liferay-clustering)
and you want to use remote staging, you must configure it properly for a
seamless experience. In this tutorial, you'll learn how to set up remote staging
in an example clustered environment scenario. The example environment assumes
you have

- a Staging instance with database configurations and a file repository
  different from the cluster nodes.
- a balancer responsible for managing the traffic flow between the cluster's
  nodes.
- two nodes that call two Liferay app servers (e.g., *App Server 1* and *App
  Server 2*), both of which are connected to the same database.

![Figure 1: This is the assumed setup for your clustered environment.](../../../images/remote-staging-clustering.png)

The steps below also assume your web tier, application tier, and cluster
environment are already configured. You may need to adjust the configurations in
this tutorial to work with your specific configuration.

Let's begin!

1.  You must secure the communication made between your nodes and Staging
    server. Add the following property to both app servers' and Staging server's
    `portal-ext.properties` file:

        tunneling.servlet.shared.secret=[secret]

    This secret key denies other portals access to your configured portal
    servers. If you'd like to set your secret key using hexadecimal encoding,
    also set the following property in your `portal-ext.properties` files:

        tunneling.servlet.shared.secret.hex=true

    +$$$

    **Note:** The following key lengths are supported by the available
    encryption algorithms:

    - *AES:* 128, 192, and 256-bit keys
    - *Blowfish:* 32-448 bit keys
    - *DESede (Triple DES):* 56, 112, or 168 bit keys (Liferay places an
      artificial limit on the minimum key length and does not support the 56-bit
      key length)

    For example, you can use [OpenSSL](https://www.openssl.org/) to generate a
    128-bit AES key:

        openssl enc -aes-128-cbc -k abc123 -P -md sha1

    $$$

2.  You must allow the connection between the configured IPs of your app servers
    and the Staging server. Open your remote Liferay server's
    `portal-ext.properties` file and add the following properties:

        tunnel.servlet.hosts.allowed=127.0.0.1,SERVER_IP,[STAGING_IP]
        tunnel.servlet.https.required=false

    The `[STAGING_IP]` variable must be replaced by the staging server's IP
    addresses. The `SERVER_IP` constant can remain set for this property; it's
    automatically replaced by the Liferay server's IP addresses.
    
3.  Validate your remote host IP addresses. For example, validating IPv6
    addresses requires the following remote live connection configurations:

    3a. Configure the app server's JVM to not force the usage of IPv4 addresses.
       For example, if you're using Tomcat, add the
       `-Djava.net.preferIPv4Stack=false` attribute in the
       `$TOMCAT_HOME\bin\setenv.[bat|sh]` file.

    3b. Select the *Remote Live* radio selector from the Staging page and
       specify the fields for your remote site. The *Remote Host/IP* field
       should match the	host you specified as your
       `tunnel.servlet.hosts.allowed` property in the `portal-ext.properties`
       file (e.g., *[0:0:0:0:0:0:0:1]*). The brackets for the *Remote Host/IP*
       field are required.

    Follow this process (excluding step 3a) to set up IPv4 address validation.
    Be sure to follow the standard IPv4 address syntax too.

3.  Restart both app servers for the new properties to take effect.

4.  Configure the *TunnelAuthVerifier* property for your nodes' app servers.
    There are two ways to do this:

    - **Use a `.config` file (recommended):** In the `$LIFERAY_HOME/osgi/configs`
      folder of one of your node @product@ instances, create (if necessary) a
      `com.liferay.portal.security.auth.verifier.tunnel.module.configuration.TunnelAuthVerifierConfiguration-default.config`
      file and insert the properties listed below. Creating one `.config` file
      configures all cluster nodes the same way. For more information on
      `.config` files, see the
      [Understanding System Configuration Files](/discover/portal/-/knowledge_base/7-1/understanding-system-configuration-files)
      article.

            enabled=true
            hostsAllowed=127.0.0.1,SERVER_IP,STAGING_IP
            serviceAccessPolicyName=SYSTEM_USER_PASSWORD
            urlsIncludes=/api/liferay/do

    - **Via System Settings:** Navigate to the *Control Panel* &rarr;
      *Configuration* &rarr; *System Settings* &rarr; *Foundation* &rarr;
      *Tunnel Auth Verifiers*. Click on the */api/liferay/do* configuration
      entry and add the Staging IP address to the *Hosts allowed* field. If you
      choose to configure the *TunnelAuthVerifier* this way, you **must** do
      this for all nodes (e.g., App Server 1 and App Server 2).

5.  On your Staging instance, navigate to the Site Administration portion of the
    Product Menu and select *Publishing* &rarr; *Staging*. Then select *Remote
    Live*.

    ![Figure 2: When selecting the Remote Staging radio button, you're given a list of options to configure.](../../../images/remote-staging-menu.png)

6.  For the Remote Host/IP field, insert the balancer's IP of your web tier.
    Configuring the Staging instance with the balancer's IP ensures the
    availability of the environment at the time of publication from staging to
    live.

7.  Enter the port on which the balancer is running into the Remote Port field.

8.  Insert the remote site ID of your app servers into the Remote Site ID field.
    The site ID of all your app servers are the same since they are configured
    for the same database and are shared between nodes.

    Navigate to the Site Administration portion of the Product Menu and select
    *Configuration* &rarr; *Site Settings* to find the site ID.

9. Save the Remote Live settings.

That's it! You've configured remote staging in your clustered environment.
