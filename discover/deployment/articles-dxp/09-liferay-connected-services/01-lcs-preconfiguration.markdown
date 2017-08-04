# LCS Preconfiguration [](id=lcs-preconfiguration)

To activate your @product@ server and take full advantage of Liferay Connected 
Services (LCS), you must first register your server with LCS. Before doing so, 
however, there are a few things you must take care of. The sections in this 
guide walk you through these steps: 

1. [Downloading the LCS Client App](/discover/deployment/-/knowledge_base/7-0/lcs-preconfiguration#downloading-the-lcs-client-app)
2. [Preconfiguring LCS to Connect Through a Proxy](/discover/deployment/-/knowledge_base/7-0/lcs-preconfiguration#preconfiguring-the-lcs-client-to-connect-through-a-proxy)
3. [Ensuring Access to LCS](/discover/deployment/-/knowledge_base/7-0/lcs-preconfiguration#ensuring-access-to-lcs)
4. [NTP Server Synchronization](/discover/deployment/-/knowledge_base/7-0/lcs-preconfiguration#ntp-server-synchronization)
5. [Configuring the Patching Tool](/discover/deployment/-/knowledge_base/7-0/lcs-preconfiguration#configuring-the-patching-tool)
6. [Installing the LCS Client App](/discover/deployment/-/knowledge_base/7-0/lcs-preconfiguration#installing-the-lcs-client-app)

Also, 
[the last section](/discover/deployment/-/knowledge_base/7-0/lcs-preconfiguration#upgrading-the-lcs-client-app) 
in this guide shows you how to upgrade the LCS client app once your @product@ 
instance is registered and activated with LCS. You must do this upgrade whenever 
Liferay releases a new version of the app. 

+$$$

**Note:** These steps show how to preconfigure LCS and your @product@ servers 
for use in production environments. To activate @product@ on a local workstation 
for testing or development purposes, you don't need to use LCS. Instead, 
navigate to 
[@product@'s activation page](https://web.liferay.com/group/customer/dxp/getting-started/activation) 
and select your subscription project. After downloading your key, place it in 
your local @product@ instance's `deploy` folder. 

$$$

First, you'll download the LCS client app. 

## Downloading the LCS Client App [](id=downloading-the-lcs-client-app)

The LCS client app is included in each @product@ bundle and autodeploys when the 
bundle starts. The included version of the app, however, may be outdated. You 
must therefore download and install the latest version of the LCS client app. To 
do this, you must first purchase the app. 

+$$$

**Note:** Even though Liferay Marketplace and this guide use the term 
*purchase*, the LCS client app is free of charge. The purchase process for a 
free app in Liferay Marketplace adds the app to your Liferay project, much like 
downloading a free app in a mobile app store adds the app to your account. 

$$$

Use these steps to purchase and download the app (if you've already 
purchased the app, you can skip to step three to download it): 

1. [Click here](https://web.liferay.com/marketplace/-/mp/application/71774947) 
   to go to the LCS client app in Liferay Marketplace. Sign in to Marketplace, 
   then click the the LCS client app's *Free* button. 

    ![Figure 1: Click the app's *Free* button to begin the purchase process.](../../images-dxp/lcs-client-app-marketplace.png)

2. Select the project you want to associate the app with, accept the license 
   agreement, and then click the *Purchase* button. Marketplace then displays 
   your receipt. 

    ![Figure 2: Liferay Marketplace displays your receipt for the LCS client app.](../../images-dxp/lcs-client-app-receipt.png)

3. On the receipt, click *See Purchased*. This takes you to the page where you 
   can download the LCS client app. To download the app, click the *App* button 
   next to the version of the app you want to download. 

    +$$$

    **Note:** If you need to download the LCS client app later, such as when 
    [upgrading it](/discover/deployment/-/knowledge_base/7-0/lcs-preconfiguration#upgrading-the-lcs-client-app), 
    select *Purchased Apps* from the User menu at the top-right of the screen. 
    This menu is available anywhere in liferay.com once you're logged in; you 
    don't have to be in Marketplace to access it. On the Purchased Apps screen, 
    select the project you associated with the LCS client app and then select 
    the app. This takes you to the same download page shown in the screenshot. 

    $$$

    ![Figure 3: Click the *App* button next to the version of the app you want to download.](../../images-dxp/lcs-client-download-page.png)

Great! You've successfully downloaded the LCS client app to your machine. Before 
installing it, however, there are a few additional preconfiguration steps you 
should complete. The following sections of this guide walk you through these, 
and then show you how to install the app. 

+$$$

**Note:** If your server connects to the Internet through a proxy, you must 
configure either your server or the LCS client app **before** deploying the app 
to your @product@ instance. The following section contains instructions on this. 
If your server doesn't connect through a proxy, you can skip this section. 

$$$

## Preconfiguring LCS to Connect Through a Proxy [](id=preconfiguring-the-lcs-client-to-connect-through-a-proxy)

If your server connects to the Internet through a proxy, you must set some 
properties in either your server or the LCS client app **before** deploying the 
app to your @product@ instance. There are therefore two ways to set these 
properties, depending on whether you set them in your server or the LCS client 
app: 

1. As JVM app server arguments. Set these properties to the appropriate values 
   for your proxy: 

        -Dhttp.proxyHost=
        -Dhttp.proxyPort=
        -Dhttp.proxyUser=
        -Dhttp.proxyPassword=
        -Dhttps.proxyHost=
        -Dhttps.proxyPort=

    Note that the user, password, and https properties are only needed if your 
    proxy requires authentication. 

    +$$$

    **Note:** If you use JVM app server arguments as instructed in this step, 
    then you don't need to preconfigure the LCS client app to connect through 
    the same proxy. 

    $$$

2. Inside the LCS client app's WAR file, before deploying the app. You can find 
   this WAR file inside the app's LPKG file that downloads to your machine from 
   Liferay Marketplace. Expand the LPKG file, then locate and expand the 
   client's WAR file: `lcs-portlet-[version].war`. 

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

LCS uses @product@'s patching tool to apply updates. In @product@ bundles, the
patching tool is pre-installed. If you're not running a bundle, 
[click here to download the patching tool](https://web.liferay.com/group/customer/dxp/downloads/digital-enterprise/patching-tool). 
Then see 
[how to install the patching tool](/discover/deployment/-/knowledge_base/7-0/patching-liferay). 
Once installed, there are a few steps you must complete before LCS can use the 
patching tool. Note that the commands below apply to Linux, Unix, and Mac. If 
you're running Windows, drop the `.sh` from each command that has it. 

1. Navigate to the `patching-tool` directory on the command line. It's typically 
   located in the Liferay Home folder.

2. To let the patching tool discover your @product@ installation, run 
   `patching-tool.sh auto-discovery`. 

3. To configure the patching tool, run `patching-tool.sh setup`. 

4. On server startup, the patching tool agent installs the patches downloaded by 
   LCS. For the agent to start with your server, you must set the `javaagent` 
   property in the JVM options to point to the `patching-tool-agent.jar` in your 
   patching tool installation. Be sure to specify the correct file path to your 
   patching tool installation's `patching-tool-agent.jar`: 

        -javaagent:../../patching-tool/lib/patching-tool-agent.jar

5. If your patching tool is installed in a location other than the Liferay Home 
   folder, you must also specify the `patching-tool` folder's path as an app 
   server JVM argument. Do this with the `patching.tool.home` property: 

        -Dpatching.tool.home=/opt/liferay-dxp-digital-enterprise-7.0/patching-tool/

There are also a few other things to consider when using the agent. Due to class
loading issues, the agent starts in a separate JVM. You can specify options for
it by using the `patching.tool.agent.jvm.opts` property. 

        -Dpatching.tool.agent.jvm.opts="-Xmx1024m -Xms512m -Dfile.encoding=UTF8"

You may also experience issues on Windows if the user starting the app server
doesn’t have administrator privileges.
Here are some examples of the errors you may see:

`java.nio.file.FileSystemException: ..\webapps\ROOT\WEB-INF\lib\util-java.jar: Not a file!`
`java.io.FileNotFoundException: java.io.IOException: Access refused`

To solve this, set the `java.io.tmpdir` system property as follows in the
`patching.tool.agent.jvm.opts` property:

        -Dpatching.tool.agent.jvm.opts="-Xmx1024m -Xms512m -Dfile.encoding=UTF8 -Djava.io.tmpdir=%TMP%"

The agent also has some flags you can set to control how it behaves:

    `debug`: Provides verbose output in the console.
    `nohalt`: Starts the portal even if the agent encounters an issue.

You can specify these as follows:

        -Dpatching.tool.agent.properties=debug,nohalt

## Installing the LCS Client App [](id=installing-the-lcs-client-app)

Once you've addressed the above preconfiguration steps, you're ready to install 
the LCS client app. Follow these steps to install the app:

1. In your @product@ bundle's 
   [Liferay Home](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/installing-liferay-dxp#liferay-home) 
   folder (usually the parent folder of the application server's folder), delete 
   this file: 

        osgi/marketplace/Liferay Connected Services Client.lpkg

2. Place the new `Liferay Connected Services Client.lpkg` in `osgi/marketplace`. 

Great! Now you're all set to activate your @product@ instance with LCS. For 
instructions on this, 
[click here](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/registering-your-dxp-server-with-lcs) 
to proceed to the next article. 

The next section shows you how to upgrade the LCS client app. You'll need to do 
this whenever Liferay releases a new version of the app. 

## Upgrading the LCS Client App [](id=upgrading-the-lcs-client-app)

Your @product@ instance should always be running the latest version of the LCS 
client app. You must therefore upgrade the app when Liferay releases a new 
version of it. There are two ways to do this, depending on the exact LCS 
preconfiguration steps you followed: 

1.  Via Liferay Marketplace *inside* your running @product@ instance. Use this 
    method if you don't need to configure the LCS client app (e.g., to connect 
    through a proxy) before it deploys. 

    +$$$

    **Note:** If you choose this method and have a clustered environment, you 
    must perform the upgrade separately on each node in your cluster. Therefore, 
    you may prefer to upgrade manually as detailed in step two to ensure that 
    all your nodes are running the exact same version of the LCS client app. 

    $$$

    To perform the upgrade, first navigate to *Control Panel* &rarr; *Apps* 
    &rarr; *Purchased*. Apps needing an update are listed first. Click *Update* 
    next to the LCS client app. Note that you may need to restart your server 
    for the upgrade to complete. 

2.  Manually, after downloading the LCS client app's LPKG file to your machine. 
    Use this method if you need to preconfigure the LCS client app to connect 
    through a proxy. 

    +$$$

    **Note:** If you used JVM app server arguments to configure your server to 
    connect through a proxy, then you don't need to preconfigure the LCS client 
    app to connect through the same proxy. 

    $$$

    To update the LCS client app manually, shut down your @product@ server and 
    follow the previous sections in this guide for downloading, preconfiguring, 
    and installing the app. Before restarting your server, you should also 
    delete the contents of the `osgi/state` folder. If you run into errors on 
    startup and deploy, it may also help to clear the app server's `work` and 
    `temp` folders. 

Contact Liferay support if you need additional assistance with the upgrade 
process. 
