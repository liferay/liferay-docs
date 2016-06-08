# Configuring the LCS Client [](id=configuring-the-lcs-client)

To use LCS, the LCS client app must be installed in your Liferay DXP instance 
and registered with LCS. The LCS client connects your DXP instance to LCS and 
provides a simple UI for managing that connection. You can also use the LCS 
client to directly access your LCS project, environment, and server from within 
your DXP instance. This article covers the following steps to help you get up 
and running with LCS:

1. **Downloading the LCS Client:** In many cases, you won't need to do this. The 
   LCS client app is part of Liferay DXP bundles. If you installed DXP manually 
   from a WAR file, however, then you must download and install the LCS client 
   separately. There may also be other cases, such as updating the client, where 
   you need to download and install the client separately. You can download the 
   LCS client app from the 
   [Liferay Marketplace](https://web.liferay.com/marketplace). 
   For instructions on using Marketplace to download and install apps, see 
   [this user guide article](/discover/portal/-/knowledge_base/7-0/using-the-liferay-marketplace). 

2. [**Preconfiguring the LCS Client to Connect Through a Proxy:**](/discover/portal/-/knowledge_base/7-0/configuring-the-lcs-client#preconfiguring-the-lcs-client-to-connect-through-a-proxy)
   If your server connects to the Internet through a proxy, you must 
   preconfigure the LCS client app before it deploys to your DXP instance. This 
   section shows you how to do this. 

3. [**Registering the Client with LCS:**](/discover/portal/-/knowledge_base/7-0/configuring-the-lcs-client#registering-the-client-with-lcs)
   Once you've installed the LCS client app, you must register your DXP instance 
   with LCS. You can do this manually or automatically. Manual registration may 
   work, but is deprecated and not recommended. You should instead 
   [register automatically with *environment tokens*](/discover/portal/-/knowledge_base/7-0/using-lcs#using-environment-tokens). 
   If you still want to manually register your DXP instance with LCS, however, 
   this section shows you how to do so. 

Note that to use LCS, you must have an account at 
[Liferay.com](http://www.liferay.com/). 

## Preconfiguring the LCS Client to Connect Through a Proxy [](id=preconfiguring-the-lcs-client-to-connect-through-a-proxy)

If you connect to the web through a proxy, there are some properties you need to 
set inside the LCS client app's WAR file before it deploys. If you downloaded a 
Liferay DXP bundle, this means that you should set these properties prior to 
starting your bundle for the first time. Starting the bundle automatically 
deploys the LCS client app. Not all is lost though if you've already started 
your bundle: you can download the client app from 
[Liferay Marketplace](https://web.liferay.com/marketplace), 
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

Great! Now you're ready to deploy the client and register your Liferay DXP 
instance with LCS. 

## Registering the Client with LCS [](id=registering-the-client-with-lcs)

Once the LCS client app is deployed, you're ready to register your server with 
LCS. Automatic registration via an environment token is the preferred way to do 
this. For instructions on this, see 
[the environment tokens section in the article on using LCS](/discover/portal/-/knowledge_base/7-0/using-lcs#using-environment-tokens). 

Manual registration, although possible, is deprecated and may not work. If you 
wish to attempt manual registration, continue with the following instructions. 
Otherwise, you can move on to the next article to learn how to use LCS. 

+$$$

**Warning:** The manual registration steps in this section are deprecated, and 
may not work. 
[Using an environment token](/discover/portal/-/knowledge_base/7-0/using-lcs#using-environment-tokens) 
is the preferred way to register your server with LCS. 

$$$

First, access the client by clicking on *Liferay Connected Services* under the 
*Configuration* section of the *Control Panel*. 

![Figure 1: LCS appears in the Control Panel's Configuration section.](../../images-dxp/lcs-control-panel-dxp.png)

The LCS client first asks you to choose the LCS services you want to enable for 
this DXP instance. The *Enable All Services* checkbox is selected by default. 
This enables portal analytics, fix pack management, and portal property 
analysis. Unchecking this checkbox presents you with additional checkboxes for 
enabling each of those services. Note that although 
[LCS doesn't access security sensitive properties](/discover/deployment/-/knowledge_base/7-0/using-lcs#what-lcs-stores-about-your-liferay-servers), 
you may have additional properties you want to prevent LCS from analyzing. If 
you select *Portal Properties Analysis*, a text box appears for you to enter any 
properties you don't want LCS to analyze. Click *Next* when you finish enabling 
the LCS services you want to use. 

![Figure 2: If you unselect *Enable All Services*, you can enable each LCS service that you want to use with your DXP instance.](../../images-dxp/lcs-enable-services.png)

Next, LCS asks you to authorize access to your LCS account. After clicking 
*Authorize Access*, LCS takes you to the LCS website. Log in with your 
credentials and then click *Grant Access*. 

![Figure 3: The LCS client requires access to your LCS account.](../../images-dxp/lcs-auth-access.png)

Now you must register your server with LCS by filling out the following fields:

1. Connected Services Project: Choose an existing LCS project from the menu. If 
   you don't yet have an LCS project, you can use the default *My LCS Project* 
   that LCS creates for you (you can change this project's name later). 

2. Environment: An environment should be a logical group of servers. A group of 
   development servers or a cluster are good examples of environments. Select an 
   existing environment from the menu, or create a new one by clicking the *Add 
   New Environment* button. If you don't yet have an environment, you can use 
   the default *My Environment* that LCS creates for you (you can change this 
   environment's name later). If you elect to create a new environment, a popup 
   asks you to enter the new environment's *Name*, *Location*, and 
   *Description*. 

    ![Figure 4: The Add New Environment pop-up.](../../images-dxp/lcs-new-environment.png)

3. Server Name: Give your server a name that you can use to distinguish it LCS. 
   Note that server names in LCS aren't case sensitive. Also, you can't have 
   duplicate server names in an environment. For example, you can't have servers 
   named *server01* and *SERVER01* in the same environment. 

4. Server Location: This can be a city, location in an office building, or 
   anywhere else that lets you know where the server is physically located.

5. Server Description: Type in any description that you want to give your 
   server. You can use this field to help differentiate the servers you manage 
   with LCS.

![Figure 5: The server registration screen.](../../images-dxp/lcs-register-server.png)

Upon clicking *Register*, your LCS client shows your connection status. Note 
that it can take up to 10 or 20 seconds to perform the initial handshake and 
send the first batch of information to LCS. If you don't see any change in 
status after 20 seconds, try refreshing the page. If you see a red symbol 
indicating no data is being transmitted, please contact the Liferay team for 
support. 

Once a successful connection is established, some statistics and links are
displayed. Note that you can change which LCS services are enabled for your DXP 
instance by clicking the *Configure Services* link.

Here's a full description of what's displayed:

- **Heartbeat Interval:** The interval of the communication that maintains the 
  connection with LCS. This regular communication keeps the client's LCS 
  connection alive, even when there's nothing else to report. The value is 
  listed in hours, minutes, and then seconds. For example, if this value is 
  `00:01:00`, the client communicates with LCS once every minute. This article's 
  previous section contains instructions for changing the heartbeat interval.
- **Message Task Interval:** The interval at which the client checks LCS for new 
  messages. For example, LCS messages are used to instruct the client to 
  download new fix packs. Currently, this interval is fixed and can't be 
  changed. 
- **Metrics Task Interval:** The interval at which server statistics and metrics 
  are sent to LCS. Currently, this interval is fixed and can't be changed. 
- **Last Message Received:** The time the latest message was received from LCS.
- **Connection Uptime:** The duration of the client's connection with LCS.
- **Project Home:** This link takes you to this server's registered LCS project. 
  The project home in LCS is also called the *dashboard*.
- **Environment:** This link takes you to this server's registered environment.
- **Server Dashboard:** This link takes you to the server on LCS.
- **Configure Services:** This link lets you change which LCS services are 
  enabled for your DXP instance. Doing so triggers reconnection with the new 
  settings. 
- **Disconnect:** Disconnects this DXP instance from LCS. 

![Figure 6: The server is connected to LCS.](../../images-dxp/lcs-server-connected.png)

Awesome! Now that you've registered your server with your LCS account, you can 
dig in to the features of LCS. 
