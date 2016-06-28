# Activating Your DXP Server with LCS [](id=registering-your-dxp-server-with-lcs)

Once the LCS client app is deployed, you're ready to activate your DXP server 
with LCS. You'll use an 
[LCS environment token](/discover/deployment/-/knowledge_base/7-0/using-lcs#using-environment-tokens) 
to do this automatically: 

1. Log in to 
   [lcs.liferay.com](https://lcs.liferay.com). This takes you to your company's 
   LCS project. If your company has multiple projects, select the one you want 
   to activate this server under from the user menu at the top right.

    ![Figure 1: Your company's LCS projects are shown under *Manage Projects* in your user menu.](../../images-dxp/lcs-user-menu-manage-projects.png)

2. Ensure that an environment exists to activate your server under. If you have 
   sufficient permissions in your company's project, you can create a new 
   environment by selecting the *Add Environment* tab. 

    ![Figure 2: You must activate your DXP server in an LCS environment. The red box in this screenshot highlights an environment.](../../images-dxp/lcs-registration-select-environment.png)

3. Select the *Subscriptions* tab, and check the *Environment Subscriptions* 
   table to ensure that the environment you want to activate your server in has 
   a subscription type. If the environment doesn't have a subscription type, you 
   can assign it one by selecting the *No Subscriptions* link and then choosing 
   a subscription type. You should **use caution** when setting an environment's 
   subscription type. All the servers in an environment **must be shut down** to 
   assign that environment's subscription type. Also, **once set, you can't 
   change an environment's subscription type**.

    ![Figure 3: The Environment Subscriptions table shows the subscription types assigned to your environments, and lets you make such assignments.](../../images-dxp/lcs-environment-subscriptions.png)

4. Select the *Connection* tab and then select *Automatic Registration*. In the 
   table, download the token for the environment you want to activate your 
   server in. Do this by selecting *Actions* &rarr; *Download* next to the 
   token. If the environment doesn't have a token, you can generate one by 
   clicking *Generate* in the table. 

    ![Figure 4: Click *Generate* to generate an environment token. Upon doing so, the *Generate* link is replaced by an *Actions* button that you can use to download the token. The token is required to activate your DXP server in LCS.](../../images-dxp/lcs-licensing-token-generate.png)

5. Shut down your DXP instance if it's running. Place the token file in your 
   instance's `[Liferay_Home]/data` folder, and then start the instance. On 
   startup, the LCS client app uses the environment token to activate your DXP 
   instance in the LCS environment that corresponds to the token. 

6. Celebrate! Your DXP server is activated and connected to LCS. 

In your DXP instance, you can view your LCS connection status in the LCS client 
app. Access the client by clicking *Control Panel* &rarr; *Configuration* &rarr; 
*Liferay Connected Services*. Note that you can change which LCS services are 
enabled for your DXP instance by clicking the *Configure Services* link. 

Here's a full description of what a connected LCS client app displays: 

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
- **Project Home:** This link takes you to this server's LCS project. 
  The project home in LCS is also called the *dashboard*. 
- **Environment:** This link takes you to this server's LCS environment.
- **Server Dashboard:** This link takes you to the server on LCS.
- **Configure Services:** This link lets you change which LCS services are 
  enabled for your DXP instance. Doing so triggers reconnection with the new 
  settings. 
- **Disconnect:** Disconnects this DXP instance from LCS. 

![Figure 5: The server is connected to LCS.](../../images-dxp/lcs-server-connected.png)

By default, all LCS services are enabled for your DXP instance. You can change 
this by clicking the *Configure Services* link in the connected client app. When 
you click this link, the *Enable All Services* checkbox is selected by default. 
This enables portal analytics, fix pack management, and portal property 
analysis. Unchecking this checkbox presents you with additional checkboxes for 
enabling each of those services separately. Note that although 
[LCS doesn't access security sensitive properties](/discover/deployment/-/knowledge_base/7-0/using-lcs#what-lcs-stores-about-your-liferay-servers), 
you may have additional properties you want to prevent LCS from analyzing. If 
you select *Portal Properties Analysis*, a text box appears for you to enter any 
properties you don't want LCS to analyze. Click *Save* when you finish enabling 
the LCS services you want to use. 

![Figure 6: In a connected LCS client app, you can enable or disable specific LCS services for your DXP instance.](../../images-dxp/lcs-configure-services.png)

Awesome! Now you know how to use environment tokens in LCS to activate your DXP 
server. 

For information on using LCS, see 
[the next article](/discover/deployment/-/knowledge_base/7-0/using-lcs). 
