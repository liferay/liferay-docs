# Configuring the LCS Client [](id=configuring-the-lcs-client)

Once you deploy the LCS client portlet, you can use it to register your server 
with your LCS account. Access the portlet by clicking on *Cloud Services* under 
the *Apps* section of the *Control Panel*.

![Figure 6.3: LCS appears in your Control Panel as Cloud Services, in the Apps section.](../../images/lcs-post-install-01.png)

After clicking *Authorize Access* on the screen that appears, you are then taken 
to the LCS website. Log in with your credentials and then click *Grant Access*.

![Figure 6.4: The LCS client portlet requires access to your LCS account.](../../images/lcs-auth-access.png)

Next, you need to register your server with LCS by filling out the following 
fields:

1. Cloud Services Project: Choose your project from the menu. 

2. Environment: Select an environment from the menu or create a new one by
   clicking the *Add New Environment* button. An environment should be a logical
   group of servers. A group of development servers or a cluster are good examples
   of environments. If you elect to create a new environment, a popup asks you to
   enter its *Name*, *Location*, and *Description*.
   
    ![Figure 6.5: The Add New Environment pop-up.](../../images/lcs-new-environment.png)

3. Server Name: Give your server a name that you can use to distinguish it in 
   your LCS account.

4. Server Location: This can be a city, location in an office building, or 
   anywhere else that lets you know where the server is physically located.

5. Server Description: Type in any description that you want to give your 
   server. You can use this field to help differentiate the servers you are 
   managing using LCS.

![Figure 6.6: The server registration screen.](../../images/lcs-register-server.png)

Upon clicking *Register*, your LCS client portlet shows your connection status. 
Note that it can take up to 10 or 20 seconds to perform the initial handshake 
with LCS and send the first batch of information to the cloud. If you don't see 
any change in status after 20 seconds, try refreshing the page. If you see a 
red symbol indicating no data is being transmitted, please contact the Liferay 
team for support.

![Figure 6.7: The server is connecting to LCS.](../../images/lcs-server-connecting.png)

Once a successful connection is established, some statistics and links are
displayed. Here's a description of what's displayed:

- Heartbeat Interval: The communication interval with LCS. For example, if this 
  value is `00:01:00`, then the portlet communicates with LCS once every minute.
- Message Task Interval: The message interval with LCS. Messages received from 
  LCS let the portlet know about any available updates. 
- Metrics Task Interval: The interval at which server statistics and metrics are 
  taken.
- Project: Clicking this link takes you to this server's registered project.
- Environment: Clicking this link takes you to this server's registered
  environment.
- Server: Clicking this link takes you to the server on LCS.
- Disconnect: Disconnects this Liferay instance from LCS.
- Reset Credentials: Removes the authorizing account from registration with LCS. 
  It's important to note that your credentials are not human readable in the 
  portal instance. This is because OAuth, which uses tokens, is used for 
  authentication.
 
![Figure 6.8: The server is connected to LCS.](../../images/lcs-server-connected.png)

Great! Now that you've registered your server with your LCS account, you can dig 
in to the features of LCS.
