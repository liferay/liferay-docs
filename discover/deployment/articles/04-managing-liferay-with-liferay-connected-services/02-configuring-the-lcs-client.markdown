# Configuring and Connecting the LCS Client [](id=configuring-the-lcs-client)

To use LCS, the LCS client app must be installed in your Liferay instance and 
registered with LCS. The LCS client connects your Liferay instance to LCS and 
provides a simple UI for managing that connection. You can also use the LCS 
client to directly access your LCS project, environment, and server from within 
your Liferay instance. This article covers the following topics to help you get 
your client up and running with LCS:

- [**Downloading the LCS Client:**](/discover/deployment/-/knowledge_base/6-2/configuring-the-lcs-client#downloading-the-lcs-client)
  This section shows you where you can download the LCS client app. 

- [**Preconfiguring the LCS Client:**](/discover/deployment/-/knowledge_base/6-2/configuring-the-lcs-client#preconfiguring-the-lcs-client)
  If your server connects to the Internet through a proxy, you must preconfigure 
  the LCS client app before you deploy it to your Liferay instance. This section 
  shows you how to do this.

- [**Registering the Client with LCS:**](/discover/deployment/-/knowledge_base/6-2/configuring-the-lcs-client#registering-the-client-with-lcs)
  Once you've installed the LCS client app, you must register your Liferay 
  instance with LCS. You can do this by 
  [using *environment tokens*](/discover/deployment/-/knowledge_base/6-2/using-lcs#using-environment-tokens). 

Note that to use LCS, you must have an account at 
[Liferay.com](http://www.liferay.com/). 

## Downloading the LCS Client [](id=downloading-the-lcs-client)

You can download the LCS client app from several places. One is the 
[Liferay Marketplace](https://web.liferay.com/marketplace). 
For instructions on using Marketplace, see 
[this user guide section](/discover/portal/-/knowledge_base/6-2/leveraging-the-liferay-marketplace). 

+$$$

**Note:** For LCS to work properly, your Liferay instances must be running 
version 153 or higher of the LCS client app. To determine the client version in 
a Liferay instance already connected to LCS, navigate to *Liferay Connected 
Services* under the *Apps* section of the *Control Panel*. The client version is 
displayed at the bottom of the app. Also, if you use 
[environment tokens](/discover/deployment/-/knowledge_base/6-2/using-lcs#using-environment-tokens) 
to connect, you should generate and use new tokens when upgrading the client 
from versions 152 or lower to versions 153 or higher. 

$$$

You can also download the LCS client app from the *Downloads* tab on 
[lcs.liferay.com](https://lcs.liferay.com). The following screenshot shows this: 

![Figure 4.1: You can also download the LCS client from the Downloads tab inside of LCS.](../../images/lcs-client-download-connection.png)

Super! Now you know where to get the LCS client. Before you deploy it though, 
there are a few other things you should check. If you connect to the web through 
a proxy, proceed to the next section. Otherwise, you can skip ahead to the 
section on registering the client with LCS. 

## Preconfiguring the LCS Client [](id=preconfiguring-the-lcs-client)

If you connect to the Internet through a proxy, there are some properties you 
must set for the LCS client app to connect to LCS. You can do this two different 
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

2. Inside the LCS client app, before deploying it. If you've already deployed 
   it, download it again, make your changes in its WAR file, and redeploy it. If 
   you downloaded the LCS client app from the Liferay Marketplace, you can find 
   the WAR file inside the app's LPKG file that downloads to your machine. In 
   either case, the client's WAR file is `lcs-portlet.war`. 

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

    c. Repackage the LCS client WAR with the modified `portlet-ext.properties` 
        file. 

    d. Deploy the LCS client WAR, or redeploy it if it's already deployed. 

Great! Now you're ready to deploy the client and register your Liferay instance 
with LCS. 

## Registering the Client with LCS [](id=registering-the-client-with-lcs)

Once you deploy the LCS client, you're ready to register your server with LCS. 
You'll use an 
[LCS environment token](/discover/deployment/-/knowledge_base/6-2/using-lcs#using-environment-tokens) 
to do this: 

1. Log in to 
   [lcs.liferay.com](https://lcs.liferay.com). This takes you to your company's 
   LCS project. If your company has multiple projects, select the one you want 
   to register this server under from the user menu at the top right. 

    ![Figure 4.2: Your company's LCS projects are shown under *Manage Projects* in your user menu.](../../images/lcs-user-menu-manage-projects.png)

2. Ensure that an environment exists to activate your server under. If you have 
   sufficient permissions in your company's project, you can create a new 
   environment by selecting the *Add Environment* tab. Note that you must 
   activate a clustered server in a clustered environment. LCS environments can 
   only be designated as clustered when they are created. To create a clustered 
   environment, click *Add Environment* and select the *Cluster* checkbox when 
   filling out the environment's information. Note that you must set the portal 
   property `cluster.link.enabled` to `true` in any servers that connect to a 
   clustered environment. 

    ![Figure 4.3: To connect to LCS, you must register your Liferay server in an LCS environment. The red box in this screenshot highlights an environment.](../../images/lcs-registration-select-environment.png)

3. If you don't want to use LCS's subscriptions feature to activate your Liferay 
   instance, you can skip this step. Otherwise, select the *Subscriptions* tab, 
   and check the *Assign Subscription Type* table to ensure that the environment 
   you want to activate your server in has a subscription type. If it doesn't, 
   you can assign it one by selecting the *No Subscriptions* link and then 
   choosing a subscription type. You should **use caution** when setting an 
   environment's subscription type. All the servers in an environment **must be 
   shut down** to assign that environment's subscription type. Also, **once set, 
   you can't change an environment's subscription type**. 
   [Click here](/discover/deployment/-/knowledge_base/6-2/using-lcs#managing-liferay-ee-subscriptions) 
   for more information on using subscriptions in LCS. 

    ![Figure 4.4: The Environment Subscriptions table shows the subscription types assigned to your environments, and lets you make such assignments.](../../images/lcs-environment-subscriptions.png)

4. Select the environment you want to register your server under, and then 
   select the *Registration* tab. This tab is where you can download and manage 
   the environment's token. In the Registration tab's *Services* section, first 
   select the LCS services that you want to use with all Liferay servers that 
   connect to this environment. All services are selected by default. Your 
   selections of LCS services here are embedded in the token file. 

    ![Figure 4.5: An environment's Registration tab lets you manage the token file used to connect Liferay instances to the environment.](../../images/lcs-registration.png) 

5. Now you must use the Registration tab to generate, regenerate, and/or 
   download the token file. The actions you take here depend on two things: what 
   you did in the previous step and whether the environment has a token file 
   already. Note that if the environment already has a token file, the Generate 
   Token button is replaced by the Download Token button: 

    - **No existing token file:** Generate the token by clicking *Generate 
      Token*. 
    - **Existing token file, and changed selections of LCS services:** 
      Regenerate the token by clicking *Regenerate Token*. Note that if you 
      regenerate the token, all Liferay servers that currently use it will be 
      disconnected from LCS and won't be able to reconnect until receiving the 
      new token. 
    - **Existing token file, and no changes to selections of LCS services:** No 
      token generation or regeneration is required.

    Once you've taken the appropriate action, download the token file by 
    clicking the *Download Token* button at the bottom of the screen. 
    [Click here](/discover/deployment/-/knowledge_base/6-2/using-lcs#using-environment-tokens) 
    for more information on environment tokens.

6. Shut down your Liferay instance if it's running. Place the token file in your 
   instance's `[Liferay_Home]/data` folder, and then start the instance. On 
   startup, the LCS client app uses the environment token to connect your 
   Liferay instance to the LCS environment that corresponds to the token. 

7. Celebrate! Your Liferay server is connected to LCS. 

In your Liferay instance, you can view your LCS connection status in the LCS 
client app. Access the client by clicking *Liferay Connected Services* under the 
*Apps* section of the *Control Panel*. 

Here's a full description of what a connected LCS client app displays: 

- **Connection Uptime:** The duration of the client's connection with LCS.
- **Last Message Received:** The time the latest message was received from LCS.
- **Services:** The LCS services enabled for this Liferay instance. Note that 
  all Liferay instances that connect to the same LCS environment must use the 
  same set of LCS services. LCS services can't be enabled on an 
  instance-by-instance basis. 
- **Project Home:** This link takes you to this server's LCS project. The 
  project home in LCS is also called the *dashboard*. 
- **Environment:** This link takes you to this server's LCS environment.
- **Server Dashboard:** This link takes you to the server on LCS. 

![Figure 4.6: The server is connected to LCS.](../../images/lcs-server-connected.png)

Awesome! Now that you've registered your server with LCS, you can dig in to the 
features of LCS. 
