# Activating Your Liferay DXP Server with LCS [](id=activating-your-liferay-dxp-server-with-lcs)

+$$$

**Note:** You must use LCS for activation of containerized instances, cloud 
deployments, and instances that use Liferay Analytics Cloud and/or Elastic 
subscriptions. Otherwise, you don't have to use LCS for activation. You can 
instead request an XML activation key from Liferay Support. 

$$$

Follow these steps to activate your @product@ server with LCS: 

1.  Ensure that you've completed the 
    [LCS preconfiguration steps](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/lcs-preconfiguration). 

2.  Log in to 
    [lcs.liferay.com](https://lcs.liferay.com). 
    This takes you to your company's LCS project. If your company has multiple 
    projects, select the project you're activating this server for from the
    menu to the right of the Dashboard tab. 

    ![Figure 1: Select your LCS project from the menu highlighted by the red box in this screenshot.](../../images-dxp/lcs-select-project.png)

3.  Select or create the environment you want this server to join. When
    activated, your server consumes an activation key from the subscription
    type assigned to the environment. Note that a subscription type can only be
    assigned to an environment when creating the environment. If you have 
    sufficient permissions in your company's project, you can 
    [create a new environment](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/managing-lcs-environments#creating-environments) 
    by selecting *Add Environment*. 

    ![Figure 2: You must activate your server in an LCS environment. The red box in this screenshot highlights environments.](../../images-dxp/lcs-registration-select-environment.png)

4.  Select the environment's *Registration* tab. This is where you manage and 
    download the 
    [environment's token file](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/understanding-environment-tokens), 
    that activates servers in the environment. 

    In the Registration tab's *Services* section, change the LCS service 
    selections, if needed. Note that if you change the LCS service selections 
    and there are servers already activated in the environment, you must
    regenerate the token file and use it to reconnect those servers to LCS.
    You'll regenerate and/or download the token in the next step. 

    The selected services are enabled for all servers that connect to this 
    environment. Portal Analytics, Fix Pack Management, and Portal Property 
    Analysis are selected by default. If Portal Property Analysis is selected, 
    you can prevent LCS from analyzing specific properties. Enter them into the 
    box that appears when you select *Show Blacklisted Properties*. Note that 
    [LCS doesn't access security sensitive properties](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/what-lcs-stores-about-your-liferay-dxp-servers). 

    ![Figure 3: An environment's Registration tab lets you manage the token file used to activate your server in the environment.](../../images-dxp/lcs-registration.png)

5.  What you do now depends on what you did in the previous step: 

    -   **Changes to LCS service selections:** Regenerate and download the 
        token. Regenerating a token causes all servers using the old token to 
        disconnect from LCS. You must reconnect them using the new token. 
    -   **No changes to LCS service selections:** Download the token. 

6.  Place the token file in your server's `[Liferay Home]/data` folder. Note 
    that 
    [Liferay Home](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/installing-liferay#liferay-home) 
    is usually the parent folder of the application server's folder. If your 
    server is running, it should connect to LCS in about one minute. If your 
    server isn't running, it connects to LCS on startup. Upon connection, your 
    server activates by consuming an activation key from the environment's 
    subscription type. You should see this in your LCS project's Subscriptions 
    tab. 

7.  Celebrate! Your @product@ server is activated and connected to LCS. If for 
    some reason it isn't, see the 
    [LCS troubleshooting article](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/troubleshooting-your-lcs-connection). 

+$$$

**Note:** You may be wondering what happens if LCS goes offline. Don't worry, 
this doesn't cause a rift in the space-time continuum. LCS is deployed on
a global cloud infrastructure set up for automatic failure recovery. The
potential for non-availability is very low. In the event of an outage, however,
registered servers maintain a local copy of their uptime information to
transmit to LCS when it comes back online. Active subscriptions also have
a 30-day grace period to re-establish connectivity and remain valid. This is
ample time for LCS to come back online. Should an extended LCS outage occur,
Liferay Support can provide activation keys that don't require LCS. 

$$$

## Determining Your Server's LCS Connection Status [](id=determining-your-servers-lcs-connection-status)

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

**Project Home:** A link to this server's LCS project. 

**Environment:** A link to this server's LCS environment. 

**Server Dashboard:** A link to the server on LCS. 

![Figure 4: The server is connected to LCS.](../../images-dxp/lcs-server-connected.png)
