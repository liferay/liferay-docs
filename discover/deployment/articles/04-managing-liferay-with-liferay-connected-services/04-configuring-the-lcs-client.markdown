# Configuring the LCS Client [](id=configuring-the-lcs-client)

To take advantage of the features offered by LCS, you need to install the LCS 
client in your Liferay instance. The LCS client connects your Liferay instance 
to LCS and provides a simple UI for managing that connection. You can also use 
the LCS client to directly access your LCS project, environment, and server from 
within your Liferay instance. 

## Downloading the LCS Client [](id=downloading-the-lcs-client)

There are multiple places in LCS where you can download the LCS client. This 
ensures that you have quick access whenever you need the client. The first and 
most prominent download location is presented after you sign in to LCS on 
[https://lcs.liferay.com](https://lcs.liferay.com). Choose your portal version 
and edition on the Welcome screen and then click *Download*. In the following 
screenshot, Liferay Portal 6.2 EE is selected. 

![Figure 4.4: You can download the LCS client from the LCS Welcome screen.](../../images/lcs-welcome-client-download.png)

You can also download the LCS client from your LCS project's Dashboard. To get 
to your Dashboard, click the *Go to My Dashboard* link on the Welcome screen, or 
click your project's link in a deployed and registered LCS client. By letting 
you view and manage the environments and servers in your project, the Dashboard 
is at the heart of LCS. The following screenshot shows the Dashboard with its 
LCS client download links highlighted in a red box. 

![Figure 4.5: The LCS Dashboard contains links to download the client.](../../images/lcs-dashboard-client-download.png)

The LCS client is also available for download from the User menu in your LCS 
account's Dockbar. To download it from here, click your name in the top-right 
corner of your LCS account and then click *Download Client* in the menu that 
appears. This is shown in the following screenshot. You then see a screen
similar to the Welcome screen, where you select your portal's version, edition, 
and then click *Download*. 

![Figure 4.6: You can also download the LCS client from the User menu in your LCS account's Dockbar.](../../images/lcs-user-menu-client-download.png)

There's one final place where you can download the LCS client: when creating a 
new server in an environment. When you click the *Add Server* tab in an 
environment, you're presented with a screen similar to the Welcome screen. 
Select your portal's version and edition, and then click *Download*.

![Figure 4.7: Adding a new server in an environment also presents you with the LCS client download screen.](../../images/lcs-add-server-client-download.png)

Super! Now you know all the places where you can download the LCS client. Before 
you deploy it though, there are a few other things you should check. If you 
connect to the web through a proxy or need to fine-tune how the client connects 
to LCS, proceed to the next section. Otherwise, you can skip to the final 
section in this article for instructions on registering the client with LCS. 

## Preconfiguring the LCS Client [](id=preconfiguring-the-lcs-client)

In most cases, deploying and configuring the LCS client is simple. If, however,
you connect to the web through a proxy, or you want to change how the client 
communicates with LCS, there are some properties you need to set in the 
client's WAR file before deploying it. Specifically, you need to set these 
properties in the client's `portlet-ext.properties` file. Regardless of the 
properties you're setting, the procedure doing so is the same.

1. In the LCS client's WAR file, open the 
   `WEB-INF/classes/portlet-ext.properties` file.
 
2. Make your changes in the file.

3. Update the LCS client WAR with the modified `portlet-ext.properties` file.
 
4. Deploy the LCS client WAR or redeploy it if it's already deployed. 

To connect to LCS through a proxy, add the following properties at the end of 
`portlet-ext.properties` and set them to the appropriate values for your proxy.
   
        proxy.host.name=
        proxy.host.port=

If your proxy uses authentication, you should also add the following properties 
and set them to the appropriate values for your proxy.
   
        proxy.host.login=
        proxy.host.password= 

While the default values are sufficient in most cases, you can also set other 
properties in `portlet-ext.properties` to fine tune the client's communication 
with LCS. As with the proxy properties, you should set these prior to deploying 
the client. The communication properties are shown here with their default 
settings. The values are specified in milliseconds. 

- The Heartbeat Interval is the communication interval with LCS that keeps the 
  client's connection alive, even when there's nothing else to report. It's set 
  by the following property.
  
        communication.heartbeat.interval=60000

- The handshake properties control how the client handles its initial handshake 
  connection with LCS. The `communication.handshake.wait.time` property sets the 
  maximum amount of time the client can wait to receive a response from LCS. The 
  `communication.handshake.reply.reads` property sets the maximum number of 
  times the client can check for a response from LCS during that time. 

        communication.handshake.wait.time=60000
        communication.handshake.reply.reads=6        

- The LCS client depends on a gateway to connect with LCS. If it can't reach the 
  gateway, the client waits a predetermined amount of time before attempting the 
  connection again. This length of time is set by the following property.
  
        communication.lcs.gateway.unavailable.wait.time=60000

Great! Now you're ready to deploy the client and register your portal with LCS.

## Registering the Client with LCS [](id=registering-the-client-with-lcs)

Once you deploy the LCS client, you're ready to register your server with LCS. 
Access the client by clicking on *Liferay Connected Services* under the *Apps* 
section of the *Control Panel*. 

![Figure 4.8: LCS appears in your Control Panel as Liferay Connected Services, in the Apps section.](../../images/lcs-post-install-01.png)

After clicking *Authorize Access* on the screen that appears, you are then taken 
to the LCS website. Log in with your credentials and then click *Grant Access*.

![Figure 4.9: The LCS client requires access to your LCS account.](../../images/lcs-auth-access.png)

Next, you need to register your server with LCS by filling out the following 
fields:

1. Connected Services Project: Choose your project from the menu. 

2. Environment: Select an environment from the menu or create a new one by
   clicking the *Add New Environment* button. An environment should be a logical
   group of servers. A group of development servers or a cluster are good 
   examples of environments. If you elect to create a new environment, a popup 
   asks you to enter its *Name*, *Location*, and *Description*.

    ![Figure 4.10: The Add New Environment pop-up.](../../images/lcs-new-environment.png)

3. Server Name: Give your server a name that you can use to distinguish it in 
   your LCS account.

4. Server Location: This can be a city, location in an office building, or 
   anywhere else that lets you know where the server is physically located.

5. Server Description: Type in any description that you want to give your 
   server. You can use this field to help differentiate the servers you are 
   managing using LCS.

![Figure 4.11: The server registration screen.](../../images/lcs-register-server.png)

Upon clicking *Register*, your LCS client shows your connection status. Note 
that it can take up to 10 or 20 seconds to perform the initial handshake and 
send the first batch of information to LCS. If you don't see any change in 
status after 20 seconds, try refreshing the page. If you see a red symbol 
indicating no data is being transmitted, please contact the Liferay team for 
support. 

![Figure 4.12: The server is connecting to LCS.](../../images/lcs-server-connecting.png)

Once a successful connection is established, some statistics and links are
displayed. Here's a description of what's displayed:

- **Heartbeat Interval:** The interval of the communication that maintains the 
  connection with LCS. This regular communication keeps the client's LCS 
  connection alive, even when there's nothing else to report. The value is 
  listed in hours, minutes, and then seconds. For example, if this value is 
  `00:01:00`, the client communicates with LCS once every minute. 
- **Message Task Interval:** The interval at which the client checks LCS for new 
  messages. For example, LCS messages are used to instruct the client to 
  download new fix packs.
- **Metrics Task Interval:** The interval at which server statistics and metrics 
  are sent to LCS.
- **Last Message Received:** The time the latest message was received from LCS.
- **Connection Uptime:** The duration of the client's connection with LCS.
- **Project Home:** This link takes you to this server's registered 
  project.
- **Environment:** This link takes you to this server's registered 
  environment.
- **Server Dashboard:** This link takes you to the server on LCS.
- **Disconnect:** Disconnects this Liferay instance from LCS.
- **Reset Credentials:** Removes the authorizing account from registration with LCS. 
  It's important to note that your credentials are not human readable in the 
  portal instance. This is because OAuth, which uses tokens, is used for 
  authentication.

![Figure 4.13: The server is connected to LCS.](../../images/lcs-server-connected.png)

This article's previous section contains instructions for changing the heartbeat 
interval. Currently, the message and metrics task intervals are fixed and cannot 
be changed. 

Awesome! Now that you've registered your server with your LCS account, you can 
dig in to the features of LCS. 
