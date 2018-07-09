# LCS Preconfiguration [](id=lcs-preconfiguration)

To activate @product@ and take full advantage of Liferay Connected Services
(LCS), you must first register your server with LCS. Before doing so, however,
there are a few things you must configure. The sections in this guide walk you
through these steps: 

1. [Downloading the LCS Client App](#downloading-the-lcs-client-app)
2. [Preconfiguring LCS to Connect Through a Proxy](#preconfiguring-lcs-to-connect-through-a-proxy)
3. [Ensuring Access to LCS](#ensuring-access-to-lcs)
4. [NTP Server Synchronization](#ntp-server-synchronization)
5. [Configuring the Patching Tool](#configuring-the-patching-tool)
6. [Installing the LCS Client App](#installing-the-lcs-client-app)

[The last section](#upgrading-the-lcs-client-app) 
in this guide shows you how to upgrade the LCS client app once your server is 
registered and activated with LCS. We highly recommend that you upgrade the app 
whenever Liferay releases a new version of it. 

+$$$

**Note:** You must use LCS for activation of containerized instances, cloud 
deployments, and instances that use Liferay Analytics Cloud and/or elastic 
subscriptions. Otherwise, you don't have to use LCS for activation. You can 
instead request an XML activation key from Liferay Support. 

$$$

## Downloading the LCS Client App [](id=downloading-the-lcs-client-app)

The LCS client app is included in each @product@ bundle and autodeploys when the 
bundle starts. The included version of the app, however, may be outdated. To get
the latest version of the LCS client app, you must first download it via Liferay
Marketplace. 

+$$$

**Note:** Even though Liferay Marketplace and this guide use the term 
*purchase*, the LCS client app is free of charge. The purchase process for a 
free app in Liferay Marketplace adds the app to your Liferay project, much like 
downloading a free app in a mobile app store adds the app to your account. 

$$$

Use these steps to purchase and download the app (if you've already purchased 
the app, you can skip to step 3 to download it): 

1.  Navigate to 
    [the LCS client app in Liferay Marketplace](https://web.liferay.com/marketplace/-/mp/application/71774947). 
    Sign in to Marketplace, then click the LCS client app's *Free* button. 

    ![Figure 1: Click the app's *Free* button to begin the purchase process.](../../images-dxp/lcs-client-app-marketplace.png)

2.  Select your project, accept the license agreement, and then click the
    *Purchase* button. Marketplace then displays your receipt. 

    ![Figure 2: Liferay Marketplace displays your receipt for the LCS client app.](../../images-dxp/lcs-client-app-receipt.png)

3.  On the receipt, click *See Purchased*. This shows where you can download the
    LCS client app. To download the app, click the *App* button next to the
    latest version of the app. 

    +$$$

    **Note:** If you must download the LCS client app later, such as when
    [upgrading it](#upgrading-the-lcs-client-app), select *Purchased Apps* from
    the User menu at the top-right of Liferay Marketplace. On the Purchased Apps
    screen, select the project you associated with the LCS client app and then
    select the app. This takes you to the same downloads page shown in the
    screenshot. 

    $$$

    ![Figure 3: Click the *App* button next to the version of the app you want to download.](../../images-dxp/lcs-client-download-page.png)

Great! You've successfully downloaded the LCS client app. Before installing it,
however, there are a few additional pre-configuration steps you should complete.
The appear next, and then you'll learn how to install the app. 

+$$$

**Note:** If your server connects to the Internet through a proxy, you must 
configure your server or the LCS client app **before** deploying the app. The
following section contains instructions on this. If your server doesn't connect
through a proxy, skip this section. 

$$$

## Preconfiguring LCS to Connect Through a Proxy [](id=preconfiguring-lcs-to-connect-through-a-proxy)

If your server connects to the Internet through a proxy, you must set some 
properties in either your server or the LCS client app **before** deploying the 
app. There are therefore two ways to set these properties, depending on whether 
you set them in your server or the LCS client app. 

1.  To set the properties in your server, set them as JVM app server arguments. 
    Set these properties to the appropriate values for your proxy: 

        -Dhttp.proxyHost=
        -Dhttp.proxyPort=
        -Dhttp.proxyUser=
        -Dhttp.proxyPassword=
        -Dhttps.proxyHost=
        -Dhttps.proxyPort=

    Note that the `user`, `password`, and `https` properties are only needed if your 
    proxy requires authentication. 

    +$$$

    **Note:** If you use JVM app server arguments as instructed in this step,
    you don't need to pre-configure the LCS client app to connect through the
    same proxy. 

    $$$

2.  To set the properties inside the LCS client app's WAR file, you must first 
    extract it from the app's LPKG file (the app downloads from Liferay 
    Marketplace as an LPKG file). Expand the LPKG file, then locate and expand 
    the client's WAR file: `lcs-portlet-[version].war`. 

    You must set the properties in the WAR's `portlet-ext.properties` file. 
    Follow these steps to do so: 

    a. In the LCS client's WAR file, create the file 
        `WEB-INF/classes/portlet-ext.properties` (or open this file if it 
        already exists). 

    b. Add the following properties at the end of `portlet-ext.properties` and 
       set them to the appropriate values for your proxy: 
   
            proxy.host.name=
            proxy.host.port=

      If your proxy requires authentication, you should also add the following 
      properties and set them to the appropriate values for your proxy: 

            proxy.host.login=
            proxy.host.password=

      If your proxy requires NTLM authentication, you must also add the 
      following properties: 

            proxy.auth.type=ntlm
            proxy.domain=
            proxy.workstation=

      Be sure to set `proxy.domain` and `proxy.workstation` to the appropriate 
      values for your proxy. Note that you can leave `proxy.workstation` blank 
      if you don't need it. 

    c. Repackage the LCS client WAR with the modified `portlet-ext.properties` 
       file, then repackage the LPKG file with the LCS client WAR. Make sure the 
       repackaged LPKG file has the same name as the original LPKG file 
       downloaded from Liferay Marketplace. 

Next, you'll learn how to ensure that the LCS client can access LCS. 

## Ensuring Access to LCS [](id=ensuring-access-to-lcs)

For the LCS client app to work, it must be able to access the following DNS 
names. If your server is behind a proxy and/or a firewall, then you must open 
access to these: 

- `lcs.liferay.com`
- `lcs-gateway.liferay.com`

As an added security measure, you can also restrict traffic to HTTPS. 

The next section discusses NTP server synchronization. 

## NTP Server Synchronization [](id=ntp-server-synchronization)

For LCS to work properly, the application server running @product@ should be 
synchronized with a time server. If it's not, you may get log errors similar to 
these: 

    ERROR [pool-6-thread-3][HandshakeTask:68] java.lang.RuntimeException: Handshake expired. 
    Check that the server is synchronized with an NTP server. 

    WARN [liferay/hot_deploy-1][LCSHotDeployMessageListener:186] LCS portlet is not connected 
    java.lang.RuntimeException: com.liferay.jsonwebserviceclient.JSONWebServiceInvocationException: 
    com.fasterxml.jackson.core.JsonParseException: Unrecognized token 'oauth_problem': was expecting 
    ('true', 'false' or 'null')_ at [Source: oauth_problem=timestamp_refused&oauth_acceptable_timestamps=1477311475-1477312075; 
    line: 1, column: 14] [Sanitized]

For information on how to synchronize your application server with a time 
server, see your application server's documentation. 

Next, you'll learn how to configure @product@'s patching tool. 

## Configuring the Patching Tool [](id=configuring-the-patching-tool)

LCS uses @product@'s patching tool to apply updates. In bundles, the patching
tool is pre-installed. If you're not running a bundle, you must 
[download](https://web.liferay.com/group/customer/dxp/downloads/7-1/patching-tool) 
and 
[install](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/patching-liferay) 
the patching tool separately. 

Once installed, there are a few steps you must complete before LCS can use the 
patching tool. Note that the commands below apply to Linux, Unix, and Mac. If 
you're running Windows, drop the `.sh` from each command that has it. 

1.  Navigate to the `patching-tool` directory on the command line. It's 
    typically located in the 
    [Liferay Home](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/installing-liferay#liferay-home) 
    folder. Liferay Home is usually the parent folder of the application 
    server's folder. 

2.  To let the patching tool discover your @product@ installation, run this 
    command: 

        patching-tool.sh auto-discovery

3.  To configure the patching tool, run this command: 

        patching-tool.sh setup

4.  On server startup, the patching tool agent installs the patches downloaded 
    by LCS. For the agent to start with your server, you must set the 
    `javaagent` property in the JVM options to point to your patching tool 
    installation's `patching-tool-agent.jar`. Be sure to specify the correct 
    path to this file: 

        -javaagent:../../patching-tool/lib/patching-tool-agent.jar

5.  If your patching tool is installed in a location other than the Liferay Home 
    folder, you must also specify the `patching-tool` folder's path as an app 
    server JVM argument. Do this with the `patching.tool.home` property: 

        -Dpatching.tool.home=/opt/liferay-dxp-7.1/patching-tool/

There are also a few other things to consider when using the agent. Due to class
loading issues, the agent starts in a separate JVM. You can specify options for
it with the `patching.tool.agent.jvm.opts` property. 

        -Dpatching.tool.agent.jvm.opts="-Xmx1024m -Xms512m -Dfile.encoding=UTF8"

You may also experience issues on Windows if the user starting the app server
doesn't have administrator privileges. Here are some examples of the errors you 
may see:

    java.nio.file.FileSystemException: ..\webapps\ROOT\WEB-INF\lib\util-java.jar: Not a file!
    java.io.FileNotFoundException: java.io.IOException: Access refused

To solve this, set the `java.io.tmpdir` system property as follows in the
`patching.tool.agent.jvm.opts` property:

        -Dpatching.tool.agent.jvm.opts="-Xmx1024m -Xms512m -Dfile.encoding=UTF8 -Djava.io.tmpdir=%TMP%"

The agent also has some flags you can set to control how it behaves:

- `debug`: Provides verbose output in the console.
- `nohalt`: Starts the portal even if the agent encounters an issue.

You can specify these as follows:

        -Dpatching.tool.agent.properties=debug,nohalt

## Installing the LCS Client App [](id=installing-the-lcs-client-app)

Once you've addressed the above pre-configuration steps, you're ready to install
the LCS client app. Follow these steps to install the app:

1.  In your 
    [Liferay Home](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/installing-liferay#liferay-home) 
    folder (usually the parent folder of the application server's folder), 
    delete this file: 

        osgi/marketplace/Liferay Connected Services Client.lpkg

2.  Place the new `Liferay Connected Services Client.lpkg` in 
    `osgi/marketplace`. 

Great! Now you're all set to 
[activate your server with LCS](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/activating-your-liferay-dxp-server-with-lcs). 

The next section shows you how to upgrade the LCS client app. We highly 
recommend that you do this whenever Liferay releases a new version of the app. 

## Upgrading the LCS Client App [](id=upgrading-the-lcs-client-app)

Your server should always be running the latest version of the LCS client app. 
There are two ways to upgrade the app, depending on the exact LCS
pre-configuration steps you followed: 

1.  Via Liferay Marketplace *inside* @product@. Use this method if you don't
    need to configure the LCS client app (e.g., to connect through a proxy)
    before it deploys. 

    +$$$

    **Note:** If you choose this method and have a clustered environment, you 
    must perform the upgrade separately on each node in your cluster. Therefore, 
    you may prefer to upgrade manually as detailed in step 2 to ensure that all 
    your nodes are running the exact same version of the LCS client app. 

    $$$

    To perform the upgrade, first navigate to *Control Panel* &rarr; *Apps* 
    &rarr; *Purchased*. Apps needing an update are listed first. Click *Update* 
    next to the LCS client app. Note that you may need to restart your server 
    for the upgrade to complete. 

2.  Manually, after downloading the LCS client app's LPKG file to your machine.
    Use this method if you must pre-configure the LCS client app to connect
    through a proxy. 

    +$$$

    **Note:** If you used JVM app server arguments to configure your server to
    connect through a proxy, then you don't need to pre-configure the LCS client
    app to connect through the same proxy. 

    $$$

    To update the LCS client app manually, follow the previous sections in this
    guide for downloading and pre-configuring the app. Then deploy it to
    `[Liferay Home]/deploy` as you would any other app. 

Contact Liferay Support if you need additional assistance with the upgrade 
process. 
