---
header-id: activating-your-liferay-dxp-server-with-lcs
---

# Registering Your Liferay DXP Server with LCS

[TOC levels=1-4]

| **Note:** LCS is deprecated and will be shut down on December 31, 2021. 
| Customers who activate LCS are advised to replace it with our latest activation
| key type which is suitable for virtualized environments. 
|
| For further information, please see [Changes to Liferay Product Activation](https://help.liferay.com/hc/en-us/articles/4402347960845-Changes-to-Liferay-Product-Activation).

Follow these steps to register your @product@ server with LCS: 

1.  Ensure that you've completed the 
    [LCS preconfiguration steps](/docs/7-2/deploy/-/knowledge_base/d/lcs-preconfiguration). 

2.  Log in to 
    [lcs.liferay.com](https://lcs.liferay.com). 
    This takes you to your company's LCS project. If your company has multiple
    projects, from the menu to the right of the Dashboard tab select the project
    that's getting a new server. 

    ![Figure 1: Select your LCS project from the menu highlighted by the red box in this screenshot.](../../images-dxp/lcs-select-project.png)

3.  Select or create the environment in which to register this server. If you're 
    using LCS for activation, upon connection to LCS your server consumes an 
    activation key from the subscription type assigned to the environment. Note 
    that a subscription type can only be assigned to an environment when 
    creating the environment. If you have sufficient permissions in your 
    company's project, you can 
    [create a new environment](/docs/7-2/deploy/-/knowledge_base/d/managing-lcs-environments#creating-environments) 
    by selecting *Add Environment*. 

    ![Figure 2: You must register your server in an LCS environment. The red box in this screenshot highlights environments.](../../images-dxp/lcs-registration-select-environment.png)

4.  Select the environment's *Registration* tab. This is where you manage and 
    download the 
    [environment's token file](/docs/7-2/deploy/-/knowledge_base/d/understanding-environment-tokens), 
    that registers servers in the environment. 

    In the Registration tab's *Services* section, change the Liferay Instance Activation setting, 
    if needed. Note that if you change this option 
    and there are servers already registered in the environment, you must 
    regenerate the token file and use it to reconnect those servers to LCS.
    You'll regenerate and/or download the token in the next step. 

     Additionally, If you disable this service, you must activate via an XML file from Liferay support, and 
    such instances must run version 5.0.0 or newer of the LCS client app. 

    Liferay Instance Activation is either enabled or disabled for all servers that connect to this 
    environment. If Portal Property Analysis is selected, you can prevent LCS environment.

    ![Figure 3: An environment's Registration tab lets you manage the token file used to register your server in the environment.](../../images-dxp/lcs-registration.png)

5.  What you do now depends on what you did in the previous step: 

    **Changes to Liferay Instance Activation:** Regenerate and download the token. 
    Regenerating a token causes all servers using the old token to disconnect 
    from LCS. You must reconnect them using the new token. 

    **No changes to LCS service selections:** Download the token. 

6.  Place the token file in your server's `[Liferay Home]/data` folder. Note 
    that 
    [Liferay Home](/docs/7-2/deploy/-/knowledge_base/d/liferay-home) 
    is usually the parent folder of the application server's folder. If your 
    server is running, it should connect to LCS in about one minute. If your 
    server isn't running, it connects to LCS on startup. 

7.  Celebrate! Your @product@ server is registered in LCS. If for some reason it 
    isn't, see the 
    [LCS troubleshooting article](/docs/7-2/deploy/-/knowledge_base/d/troubleshooting-your-lcs-connection). 

| **Note:** You may be wondering what happens if LCS goes offline. Don't worry, 
| this doesn't cause a rift in the space-time continuum. LCS is deployed on
| a global cloud infrastructure set up for automatic failure recovery. The
| potential for non-availability is very low. In the event of an outage, 
| however, registered servers maintain a local copy of their uptime information 
| to transmit to LCS when it comes back online. If you use LCS for activation, 
| active subscriptions have a 30-day grace period to re-establish connectivity 
| and remain valid. This is ample time for LCS to come back online. 

## Determining Your Server's LCS Connection Status

In @product@, you can view your LCS connection status in the LCS client app.
Access the client by clicking *Control Panel* &rarr; *Configuration* &rarr;
*Liferay Connected Services*. 

Here's a full description of what a connected LCS client app displays: 

**Connection Uptime:** The duration of the client's connection with LCS.

**Last Message Received:** The time the LCS client received the latest 
connection message from LCS. These messages occur only upon 
connection/reconnection and are unrelated to server metrics. It's therefore 
common for a long period of time to pass before the client receives another 
such message for a reconnection event. 

**Services:** The LCS services enabled for this server. Note that all 
servers in an environment use the same set of LCS services. LCS services 
can't be controlled on a server-by-server basis. 

Note: Portal Analytics, Fix Pack Management and Portal Properties Analysis have been removed from the list of available services. 
For more information about this change, please read 
[this article](https://help.liferay.com/hc/en-us/articles/360037317691-Liferay-Connected-Services-Feature-Deprecation-Update-March-2020)

**Project Home:** A link to this server's LCS project. 

**Environment:** A link to this server's LCS environment. 

**Server Dashboard:** A link to the server on LCS. 

![Figure 4: The server is connected to LCS.](../../images-dxp/lcs-server-connected.png)
