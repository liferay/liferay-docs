# LCS Preconfiguration [](id=lcs-preconfiguration)

To take full advantage of Liferay Connected Services (LCS), you must first 
register your server with LCS. Before doing so, however, you must configure it. 
There are five steps: 

1. [Downloading the LCS Client App](/discover/deployment/-/knowledge_base/6-2/lcs-preconfiguration#downloading-the-lcs-client-app)
2. [Preconfiguring the LCS Client to Connect Through a Proxy](/discover/deployment/-/knowledge_base/6-2/lcs-preconfiguration#preconfiguring-the-lcs-client-to-connect-through-a-proxy)
3. [Ensuring Access to LCS](/discover/deployment/-/knowledge_base/6-2/lcs-preconfiguration#ensuring-access-to-lcs)
4. [NTP Server Synchronization](/discover/deployment/-/knowledge_base/6-2/lcs-preconfiguration#ntp-server-synchronization)
5. [Configuring the Patching Tool](/discover/deployment/-/knowledge_base/6-2/lcs-preconfiguration#configuring-the-patching-tool)

First, you'll download the LCS client app. 

## Downloading the LCS Client App [](id=downloading-the-lcs-client-app)

To connect to LCS, you must first download the latest version of the LCS client 
app for Liferay Portal 6.2 EE. You can do this via Liferay Marketplace. If you 
haven't purchased the app yet, do so now from 
[here in Liferay Marketplace](https://web.liferay.com/marketplace/-/mp/application/71774947). 
On the app's Marketplace page, click the *Free* button and then select the 
project you wish to associate the app with. Accept the license agreement and 
then click *Purchase* (don't worry, the app is still free). 

To download the app once you've purchased it, select *Purchased Apps* from the 
User menu at the top-right of the screen in Marketplace. 

![Figure 1: To access your purchased apps, click *Purchased Apps* from the User menu.](../../images/lcs-purchased-apps.png)

On the Purchased Apps screen, select the project you associated with the LCS app 
and then select the LCS app. The screen that appears lets you choose the version 
of the app to download. To download the latest version of the app for Liferay 
Portal 6.2 EE, click the *App* button next to Liferay Portal 6.2 EE. Note that 
your server should always be running the latest version of the app. This ensures 
that your server can connect to LCS and use the latest LCS features. Also note 
that when deploying a new version of the client, you may also need to regenerate 
[the environment token](/discover/deployment/-/knowledge_base/7-0/using-lcs#using-environment-tokens) 
that you use to connect. 

![Figure 2: Click the *App* button next to Liferay Portal 6.2 EE.](../../images/lcs-app-version-download.png)

+$$$

**Note:** If your server connects to the Internet through a proxy, you must 
configure the LCS client app **before** deploying it to your Liferay Portal 
instance. The following section contains instructions on this. 

$$$

The next section shows you how to preconfigure the LCS client app to connect 
through a proxy. If your server doesn't connect through a proxy, you can deploy 
the LCS client app and skip this section. 

## Preconfiguring the LCS Client to Connect Through a Proxy [](id=preconfiguring-the-lcs-client-to-connect-through-a-proxy)

If your server connects to the Internet through a proxy, you must configure the 
LCS client app **before** deploying it to your Liferay Portal instance. You'll 
do this by setting some properties. There are two ways to set these properties: 

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

2. Inside the LCS client app's WAR file, before it deploys. You can find the WAR 
   file inside the app's LPKG file that downloads to your machine from 
   Marketplace. The client's WAR file is `lcs-portlet-[version].war`. 

   You must set the properties in the WAR file's `portlet-ext.properties` file. 
   Follow these steps to do so: 

   a. In the LCS client's WAR file, open the file 
        `WEB-INF/classes/portlet-ext.properties`. 

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
      file. 

   d. Deploy the LCS client WAR or redeploy it if it's already deployed. 

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

For LCS to work properly, the application server running Liferay Portal should 
be synchronized with a time server. If it's not, you may get log errors similar 
to these: 

    ERROR [pool-6-thread-3][HandshakeTask:68] java.lang.RuntimeException: Handshake expired. 
    Check that the server is synchronized with an NTP server. 

    WARN [liferay/hot_deploy-1][LCSHotDeployMessageListener:186] LCS portlet is not connected 
    java.lang.RuntimeException: com.liferay.jsonwebserviceclient.JSONWebServiceInvocationException: 
    com.fasterxml.jackson.core.JsonParseException: Unrecognized token 'oauth_problem': was expecting 
    ('true', 'false' or 'null')_ at [Source: oauth_problem=timestamp_refused&oauth_acceptable_timestamps=1477311475-1477312075; 
    line: 1, column: 14] [Sanitized]

For information on how to synchronize your application server with a time 
server, see your application server's documentation. 

Next, you'll learn how to configure Liferay Portal's patching tool. 

## Configuring the Patching Tool [](id=configuring-the-patching-tool)

LCS uses Liferay Portal's patching tool to apply updates. In Liferay Portal 6.2 
EE bundles, the patching tool is pre-installed. If you're not running a bundle, 
[click here to download the patching tool](https://web.liferay.com/group/customer/products/patching-tool). 
Then see 
[how to install the patching tool](/discover/deployment/-/knowledge_base/6-2/patching-liferay#installing-the-patching-tool). 
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

        -Dpatching.tool.home=/opt/liferay-portal-6.2-ee-sp14/patching-tool/

There are also a few other things to consider when using the agent. Due to class
loading issues, the agent starts in a separate JVM. You can specify options for
it by using the `patching.tool.agent.jvm.opts` property. 

        -Dpatching.tool.agent.jvm.opts="-Xmx1024m -Xms512m -Dfile.encoding=UTF8"

You may also experience issues on Windows if the user starting the app server
doesn’t have administrator privileges. Here are some examples of the errors you 
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

Great! Now you're all set to connect your Liferay Portal server to LCS. 
