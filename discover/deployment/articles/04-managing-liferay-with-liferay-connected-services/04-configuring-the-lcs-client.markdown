# Configuring the LCS Client [](id=configuring-the-lcs-client)

To take advantage of the features offered by LCS, you need to install the LCS 
client in your Liferay instance. The LCS client connects your Liferay instance 
to LCS and provides a simple UI for managing that connection. You can also use 
the LCS client to directly access your LCS project, environment, and server from 
within your Liferay instance. 

You can download the LCS client from your LCS project's Dashboard. You're 
taken to the Dashboard when you log in to your LCS project on the web, or click
your project's link in the LCS client. By letting you view and manage the 
environments and servers in your project, the Dashboard is at the heart of LCS. 
The following screenshot shows the Dashboard with its LCS client download links 
highlighted in a red box. 

![Figure 4.4: The LCS Dashboard contains links to download the client.](../../images/lcs-dashboard-client-download.png)

In most cases, deploying and configuring the LCS client is simple. However, if 
you connect to the web through a proxy, then there are some properties you need
to set in the client's WAR file before deploying it. If you don't connect
through a proxy, then you can skip these steps.

1. In the LCS client's WAR file, open the 
   `WEB-INF/classes/portlet-ext.properties` file.
 
2. At the end of the file, add the following properties and set them to the 
   appropriate values for your proxy.
   
        proxy.host.name=
        proxy.host.port=

3. If your proxy also uses authentication, you should also add the following 
   properties and set them to the appropriate values for your proxy.
   
        proxy.host.login=
        proxy.host.password=
        
4. Update the LCS client WAR with the modified `portlet-ext.properties` file.
 
5. Deploy the LCS client WAR or redeploy it if it's already deployed. 

While the default values are sufficient in most cases, you can also set other 
properties in `portlet-ext.properties` to fine tune the client's communication 
with LCS. As with the proxy properties, you should set these prior to deploying 
the client. The communication properties are shown here with their default 
settings. The values are specified in milliseconds. 

- The Heartbeat Interval is the communication interval with LCS that keeps the 
  client's connection alive, even when there's nothing else to report. It's set 
  by the following property.
  
    communication.heartbeat.interval=60000

- The handshake properties set the maximum time the client waits to receive a 
  response from LCS on the initial connection. Added together, these two 
  properties determine the total time the client can wait for the handshake to 
  complete.
  
    communication.handshake.reply.reads=6
    communication.handshake.wait.time=60000

- When the connection to LCS is terminated by the user, the stop properties set 
  the maximum time the client can wait to receive an acknowledgement from LCS. 
  Added together, these two properties determine the total time the client can 
  wait for a response from LCS. 
  
    communication.stop.reply.reads=6
    communication.stop.wait.time=15000

- The LCS client depends on a gateway to connect with LCS. If it can't reach the 
  gateway, it displays a message indicating that fact. The following property 
  sets the amount of time the client can wait for a response before displaying 
  that message.
  
    communication.lcs.gateway.unavailable.wait.time=60000

Once you deploy the LCS client, you're ready to register your server with LCS. 
Access the client by clicking on *Liferay Connected Services* under the *Apps* 
section of the *Control Panel*. 

![Figure 4.5: LCS appears in your Control Panel as Liferay Connected Services, in the Apps section.](../../images/lcs-post-install-01.png)

After clicking *Authorize Access* on the screen that appears, you are then taken 
to the LCS website. Log in with your credentials and then click *Grant Access*.

![Figure 4.6: The LCS client requires access to your LCS account.](../../images/lcs-auth-access.png)

Next, you need to register your server with LCS by filling out the following 
fields:

1. Connected Services Project: Choose your project from the menu. 

2. Environment: Select an environment from the menu or create a new one by
   clicking the *Add New Environment* button. An environment should be a logical
   group of servers. A group of development servers or a cluster are good 
   examples of environments. If you elect to create a new environment, a popup 
   asks you to enter its *Name*, *Location*, and *Description*.

    ![Figure 4.7: The Add New Environment pop-up.](../../images/lcs-new-environment.png)

3. Server Name: Give your server a name that you can use to distinguish it in 
   your LCS account.

4. Server Location: This can be a city, location in an office building, or 
   anywhere else that lets you know where the server is physically located.

5. Server Description: Type in any description that you want to give your 
   server. You can use this field to help differentiate the servers you are 
   managing using LCS.

![Figure 4.8: The server registration screen.](../../images/lcs-register-server.png)

Upon clicking *Register*, your LCS client shows your connection status. Note 
that it can take up to 10 or 20 seconds to perform the initial handshake and 
send the first batch of information to LCS. If you don't see any change in 
status after 20 seconds, try refreshing the page. If you see a red symbol 
indicating no data is being transmitted, please contact the Liferay team for 
support. 

![Figure 4.9: The server is connecting to LCS.](../../images/lcs-server-connecting.png)

Once a successful connection is established, some statistics and links are
displayed. Here's a description of what's displayed:

- **Heartbeat Interval:** The communication interval with LCS. For example, if 
  this value is `00:01:00`, the client communicates with LCS once every minute. 
  This regular communication keeps the client's LCS connection alive, even when 
  there's nothing else to report. 
- **Message Task Interval:** The interval at which the client checks LCS for new 
  messages. For example, LCS messages are used to let the client know that new 
  fix packs are available.
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
 
![Figure 4.10: The server is connected to LCS.](../../images/lcs-server-connected.png)

Great! Now that you've registered your server with your LCS account, you can dig 
in to the features of LCS.
