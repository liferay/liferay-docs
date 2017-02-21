# LCS Preconfiguration [](id=lcs-preconfiguration)

To activate your @product@ server and take full advantage of Liferay Connected 
Services (LCS), you must first register your server with LCS. Before doing so, 
however, there are some configuration steps to take care of: 

1. Downloading the LCS Client App
2. Preconfiguring the LCS Client to Connect Through a Proxy
3. Ensuring Access to LCS
4. NTP Server Synchronization
5. Configuring the Patching Tool

+$$$

**Note:** These steps show how to preconfigure LCS and your @product@ servers 
for use in production environments. To activate @product@ on a local workstation 
for testing or development purposes, you don't need to use LCS. Instead, create 
a ticket in 
[LESA](https://web.liferay.com/group/customer/support/-/support/ticket) 
to request an activation key. When creating this ticket, select *Activation Key* 
in the *Select a component* field. When you receive your key, place it in your 
local @product@ instance's `deploy` folder. 

$$$

The sections in this article take you through each of these steps. First, you'll 
download the LCS client app. 

## Downloading the LCS Client App [](id=downloading-the-lcs-client-app)

The LCS client app is part of @product@ bundles and autodeploys when the bundle 
starts. The LCS client app in the @product@ bundle, however, may be outdated. 
You should therefore download and install the latest version of the LCS client 
app. You can download the LCS client app 
[here in the Liferay Marketplace](https://web.liferay.com/marketplace/-/mp/application/71774947). 
For instructions on using Marketplace to download and install apps, see 
[this user guide article](/discover/portal/-/knowledge_base/7-0/using-the-liferay-marketplace). 

+$$$

**Note:** If your server connects to the Internet through a proxy, you must 
configure the LCS client app **before** deploying it to your @product@ instance. 
The following section contains instructions on this. 

$$$

Also note that as improvements are made to LCS, older versions of the LCS client 
app may not work. You should therefore ensure that your @product@ instance is 
always running the latest version of the client. When upgrading the client, you 
may also need to regenerate the 
[environment token](/discover/deployment/-/knowledge_base/7-0/using-lcs#using-environment-tokens) 
that you use to connect. 

The next section shows you how to preconfigure the LCS client app to connect 
through a proxy. If your server doesn't connect through a proxy, you can deploy 
the LCS client app and skip this section. 

## Preconfiguring the LCS Client to Connect Through a Proxy [](id=preconfiguring-the-lcs-client-to-connect-through-a-proxy)

If your server connects to the Internet through a proxy, you must configure the 
LCS client app **before** deploying it to your @product@ instance. You'll do 
this by setting some properties. You can set these properties two different 
ways: 

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

2. Inside the LCS client app's WAR file, before it deploys. If you downloaded a 
   @product@ bundle, this means that you should set these properties prior to 
   starting your bundle for the first time. You can find the LCS client's WAR 
   file in the bundle's `[Liferay_Home]/deploy` folder. Starting the bundle 
   automatically deploys the LCS client app. If you've already started your 
   bundle, download the client app from 
   [here in the Liferay Marketplace](https://web.liferay.com/marketplace/-/mp/application/71774947), 
   make your changes in its WAR file, and redeploy it. You can find the WAR file 
   inside the app's LPKG file that downloads to your machine from Marketplace. 
   In either case, the client's WAR file is `lcs-portlet-[version].war`. 

    You must set the properties in the WAR file's `portlet-ext.properties` file. 
    Follow these steps to do so: 

    a. In the LCS client's WAR file, open the 
        `WEB-INF/classes/portlet-ext.properties` file. 

    b. Add the following properties at the end of `portlet-ext.properties` and 
        set them to the appropriate values for your proxy: 
   
            proxy.host.name=
            proxy.host.port=

       If your proxy requires authentication, you should also add the following 
       properties and set them to the appropriate values for your proxy. 

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

    d. Deploy the LCS client WAR, or redeploy it if it's already deployed. 

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

    ERROR [pool-6-thread-3][HandshakeTask:68] java.lang.RuntimeException: 
    Handshake expired. Check that the server is synchronized with an NTP server. 

    WARN [liferay/hot_deploy-1][LCSHotDeployMessageListener:186] LCS portlet is 
    not connected java.lang.RuntimeException: 
    com.liferay.jsonwebserviceclient.JSONWebServiceInvocationException: 
    com.fasterxml.jackson.core.JsonParseException: Unrecognized token 
    'oauth_problem': was expecting ('true', 'false' or 'null')_ at [Source: 
    oauth_problem=timestamp_refused&oauth_acceptable_timestamps=1477311475-1477312075; 
    line: 1, column: 14] [Sanitized]

For information on how to synchronize your application server with a time 
server, see your application server's documentation. 

Next, you'll learn how to configure @product@'s patching tool. 

## Configuring the Patching Tool [](id=configuring-the-patching-tool)

LCS leverages @product@'s patching tool to apply updates. If you're running a 
@product@ bundle, then you should already have the patching tool installed. If 
you're not running a bundle, or your bundle doesn't have the patching tool 
installed, 
[click here to download it](https://web.liferay.com/group/customer/dxp/downloads/digital-enterprise/patching-tool). 
Then see the user guide section on 
[how to install the patching tool](/discover/deployment/-/knowledge_base/7-0/patching-liferay). 
Once you have the patching tool installed, there are a few steps you need to 
complete before LCS can use it. Note that the commands below apply to Linux, 
Unix, and Mac systems. If you're running Windows, simply drop the `.sh` from 
each command that has it. 

1. Navigate to the `patching-tool` directory on the command line. It's typically 
   located in the Liferay Home folder.

2. Let the patching tool discover your @product@ installation by running 
   `patching-tool.sh auto-discovery`. 

3. Configure the patching tool by running `patching-tool.sh setup`. 

4. Patches downloaded through LCS are installed by the patching tool agent on 
   server startup. For the agent to start with your server, you need to set the 
   `javaagent` property in the JVM options. Make sure that you specify the 
   correct file path to the `patching-tool-agent.jar`. Here's an example of 
   setting the `javaagent` property: 

        -javaagent:../../patching-tool/lib/patching-tool-agent.jar

If your patching tool is installed in a location other than the Liferay Home 
folder, you must specify the path of the `patching-tool` folder as a JVM 
argument for the app server. This is done with the `patching.tool.home` 
property. For example:

    -Dpatching.tool.home=/opt/liferay-dxp-digital-enterprise-7.0/patching-tool/

Great! Now you're all set to activate your @product@ instance with LCS. 
