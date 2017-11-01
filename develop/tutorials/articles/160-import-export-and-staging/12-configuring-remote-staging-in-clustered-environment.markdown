# Configuring Remote Staging in a Clustered Environment

If you have a @product@ instance running as a
[clustered environment](/discover/deployment/-/knowledge_base/7-0/liferay-clustering),
and you want to use remote staging, you must configure it properly for a
seamless experience. In this tutorial, you'll learn how to set up remote staging
in an example clustered environment scenario. The example environment assumes
you have

- a Staging instance with database configurations and a file repository
  different from the cluster nodes.
- a balancer responsible for the traffic between the cluster's nodes.
- two nodes that call two app servers (e.g., *App Server 1* and *App Server 2*),
  both of which are connected to the same database.

![Figure 1: This is the assumed setup for your clustered environment.](../../images/remote-staging-clustering.png)

The steps below also assume your web tier, application tier, and cluster
environment are already configured. You may need to adjust the configurations in
this tutorial to work with your specific configuration.

Let's begin!

1.  You should secure the communication made between your nodes and Staging
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

    - *AES:* 128, 192, and 256 bit keys
    - *Blowfish:* 32-448 bit keys
    - *DESede (Triple DES):* 56, 112, or 168 bit keys (Liferay places an
      artificial limit on the minimum key length and does not support the 56 bit
      key length)

    For example, if you're using a Unix based OS, you can use the following
    command to generate a 128 bit AES key.

        openssl enc -aes-128-cbc -k abc123 -P -md sha1

    $$$

2.  You must allow the connection between the configured IPs of your portal
    instances and the Staging server. Open both of your app servers'
    `portal-ext.properties` files and add the following properties:

        tunnel.servlet.hosts.allowed=127.0.0.1,SERVER_IP,STAGING_IP
        tunnel.servlet.https.required=false

    The *SERVER_IP* must be replaced by the current instance's IP you're setting
    the property for and the *STAGING_IP* must be replaced by the IP of the
    Staging instance.

    <!-- Restart both portal instances for the new properties to take effect. -->

3.  Configure the *TunnelAuthVerifier* property for your node's app servers. To
    do this, navigate to the *Control Panel* &rarr; *Configuration* &rarr;
    *System Settings* &rarr; *Foundation* &rarr; *Tunnel Auth Verifiers*. Click
    on the */api/liferay/do* configuration entry and add the Staging IP address
    to the *Hosts allowed* field.

4.  
