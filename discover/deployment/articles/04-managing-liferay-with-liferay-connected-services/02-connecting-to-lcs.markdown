---
header-id: connecting-to-lcs
---

# Connecting to LCS

[TOC levels=1-4]

Follow these steps to connect your Liferay Portal server to LCS: 

1.  Ensure that you've completed the 
    [LCS preconfiguration steps](/docs/6-2/deploy/-/knowledge_base/d/lcs-preconfiguration). 

2.  Log in to 
    [lcs.liferay.com](https://lcs.liferay.com). 
    This takes you to your company's LCS project. If your company has multiple 
    projects, select the one you want to register this server under from the 
    menu to the right of the Dashboard tab.  

    ![Figure 4.4: Select your LCS project from the menu highlighted by the red box in this screenshot.](../../images/lcs-select-project.png)

3.  Select or create the environment to connect your server to. If you're 
    activating your server with LCS, your server will consume an activation key 
    from the subscription type assigned to the environment. Note that a 
    subscription type can only be assigned to an environment when creating the 
    environment. If you have sufficient permissions in your company's project, 
    you can 
    [create a new environment](/docs/6-2/deploy/-/knowledge_base/d/using-lcs#creating-an-environment) 
    by selecting *Add Environment*. 

    ![Figure 4.5: To connect to LCS, you must register your Liferay Portal server in an LCS environment. The red box in this screenshot highlights environments.](../../images/lcs-registration-select-environment.png)

4.  Select the environment's *Registration* tab. This is where you manage and 
    download the 
    [environment's token file](/docs/6-2/deploy/-/knowledge_base/d/using-lcs#using-environment-tokens), 
    which you'll use to connect servers to the environment. 

    In the Registration tab's *Services* section, change the LCS service 
    selections, if needed. Note that if you change the LCS service selections 
    and there are servers already connected to the environment, then you must 
    regenerate the token file and use it to reconnect those servers to LCS. 
    You'll regenerate and/or download the token in the next step. 

    The selected services are enabled for all servers that connect to this 
    environment. Portal Analytics, Fix Pack Management, and Portal Property 
    Analysis are selected by default. If Portal Property Analysis is selected, 
    you can prevent LCS from analyzing specific properties. Enter them into the 
    box that appears when you select *Show Blacklisted Properties*. Note that 
    [LCS doesn't access security sensitive properties](/docs/6-2/deploy/-/knowledge_base/d/using-lcs#what-lcs-stores-about-your-liferay-servers). 

    ![Figure 1: An environment's Registration tab lets you manage the token file used to connect your server to the environment.](../../images/lcs-registration.png)

5.  What you do now depends on what you did in the previous step: 

    -   **Changes to LCS service selections:** Regenerate and download the 
        token. Regenerating a token causes all servers using the old token to 
        disconnect from LCS. You must reconnect them using the new token. 
    -   **No changes to LCS service selections:** Download the token. 

6.  Place the token file in your server's `[Liferay Home]/data` folder. Note 
    that 
    [Liferay Home](/docs/6-2/deploy/-/knowledge_base/d/liferay-home) 
    is usually the parent folder of the application server's folder. If your 
    server is running, it should connect to LCS in about 1 minute. If your 
    server isn't running, it connects to LCS on startup. Upon connection, your 
    server appears in LCS. If you're activating your server with LCS, your 
    server consumes an activation key from the environment's subscription type. 
    You should see this in your LCS project's Subscriptions tab. 

7.  Celebrate! Your Liferay Portal server is connected to LCS. 

| **Note:** You may be wondering what happens if LCS goes offline. Don't worry,
| this won't cause a rift in the space-time continuum. LCS is deployed on a global
| cloud infrastructure set up for automatic failure recovery. The potential for
| non-availability is very low. In the event of an outage, however, registered
| servers maintain a local copy of their uptime information to transmit to LCS
| when it comes back online. If you're using LCS for activation, active
| subscriptions have a 30-day grace period to re-establish connectivity and remain
| valid. This is ample time for LCS to come back online. Should an extended LCS
| outage occur, Liferay Support can provide activation keys that don't require
| LCS.

In your Liferay Portal instance, you can view your LCS connection status in the 
LCS client app. Access the client by clicking *Control Panel* &rarr; *Apps* 
&rarr; *Liferay Connected Services*. 

Here's a full description of what a connected LCS client app displays: 

- **Connection Uptime:** The duration of the client's connection with LCS.
- **Last Message Received:** The time the LCS client received the latest 
  connection message from LCS. These messages occur only upon 
  connection/reconnection and are unrelated to server metrics. It's therefore 
  common for a long period of time to pass before the client receives another 
  such message for a reconnection event. 
- **Services:** The LCS services enabled for this Liferay Portal instance. Note 
  that all Liferay Portal instances that connect to the same LCS environment 
  must use the same set of LCS services. LCS services can't be enabled on an 
  instance-by-instance basis. 
- **Project Home:** This link takes you to this server's LCS project. The 
  project home in LCS is also called the *dashboard*. 
- **Environment:** This link takes you to this server's LCS environment. 
- **Server Dashboard:** This link takes you to the server on LCS. 

![Figure 4.6: The server is connected to LCS.](../../images/lcs-server-connected.png)

Awesome! Now you're ready to use LCS. The next article shows you how to do this. 
