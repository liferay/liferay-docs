# Preconfiguring the LCS Client to Connect Through a Proxy [](id=preconfiguring-the-lcs-client-to-connect-through-a-proxy)

If you connect to the Internet through a proxy, there are some properties you 
must set for the LCS client app to connect to LCS. You'll do this via your 
server's JVM system properties. You can do this two different ways:

1. As JVM app server arguments:

        -Dhttps.proxyHost=your.proxy.host
        -Dhttps.proxyPort=your.proxy.port

2. In a Liferay `system-ext.properties` file: Create this file in your 
   [Liferay Home folder](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home), 
   and set the following properties in this file: 

        https.proxyHost=your.proxy.host
        https.proxyPort=your.proxy.port

Be sure to replace the values `your.proxy.host` and `your.proxy.port` with the 
appropriate values for your proxy. 

Great! Now you're ready to connect your Liferay DXP instance to LCS. 

<!-- 
If you connect to the web through a proxy, there are some properties you need to 
set inside the LCS client app's WAR file before it deploys. If you downloaded a 
Liferay DXP bundle, this means that you should set these properties prior to 
starting your bundle for the first time. Starting the bundle automatically 
deploys the LCS client app. Not all is lost though if you've already started 
your bundle: you can download the client app from 
[here in the Liferay Marketplace](https://web.liferay.com/marketplace/-/mp/application/71774947), 
make your changes in its WAR file, and redeploy it. 

If you used Liferay Marketplace to download the client app, then you can find 
the WAR file inside the app's LPKG file that downloaded to your machine from 
Marketplace. If you downloaded a Liferay DXP bundle, then you can find the LCS 
client's WAR file in the bundle's `[Liferay_Home]/deploy` folder prior to 
starting the bundle for the first time. In either case, the client's WAR file is 
named `lcs-portlet-[version].war`. 

To preconfigure the LCS client app to connect through your proxy, you must set 
some properties in the client's `portlet-ext.properties` file. Follow these 
steps to do so: 

1. In the LCS client's WAR file, open the 
   `WEB-INF/classes/portlet-ext.properties` file.

2. To connect to LCS through a proxy, add the following properties at the end of 
   `portlet-ext.properties` and set them to the appropriate values for your 
   proxy: 
   
        proxy.host.name=
        proxy.host.port=

    If your proxy uses authentication, you should also add the following 
    properties and set them to the appropriate values for your proxy.

        proxy.host.login=
        proxy.host.password=

3. Update the LCS client WAR with the modified `portlet-ext.properties` file.

4. Deploy the LCS client WAR or redeploy it if it's already deployed. 

Great! Now you're ready to connect your Liferay DXP instance to LCS. 
-->