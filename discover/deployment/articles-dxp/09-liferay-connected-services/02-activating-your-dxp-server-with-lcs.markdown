# Activating Your @product@ Server with LCS [](id=registering-your-dxp-server-with-lcs)

Once you've addressed 
[the LCS preconfiguration steps](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/lcs-preconfiguration), 
you're ready to activate your @product@ server with LCS. You'll use an 
[LCS environment token](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/using-lcs#using-environment-tokens) 
to do this: 

1.  Log in to 
    [lcs.liferay.com](https://lcs.liferay.com). 
    This takes you to your company's LCS project. If your company has multiple 
    projects, select the one you want to activate this server under from the 
    user menu at the top right. 

    ![Figure 1: Your company's LCS projects are shown under *Manage Projects* in your user menu.](../../images-dxp/lcs-user-menu-manage-projects.png)

2.  Select or create the environment to activate your server under. When 
    activated, your server will consume an activation key from the subscription 
    type assigned to the environment. Note that a subscription type can only be 
    assigned to an environment when creating the environment. If you have 
    sufficient permissions in your company's project, you can create a new 
    environment by selecting the *Add Environment* tab. 
    [Click here](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/using-lcs#creating-an-environment) 
    for instructions on creating an environment. 

    ![Figure 2: You must activate your @product@ server in an LCS environment. The red box in this screenshot highlights environments.](../../images-dxp/lcs-registration-select-environment.png)

3.  Select the environment's *Registration* tab. This is where you download and 
    manage the environment's token file, which you'll use to activate servers in 
    the environment. In the Registration tab's *Services* section, ensure that 
    the LCS services you want to use in this environment are selected. These 
    service selections are embedded in the token file. For details on 
    environment tokens and LCS services, see the documentation section 
    [Using Environment Tokens](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/using-lcs#using-environment-tokens). 

    Now you must generate, regenerate, and/or download the token file. The 
    actions you take here depend on two things: whether you changed the LCS 
    service selections, and whether the environment already has a token file. 
    Follow the instructions for the situation that applies to you (note that if 
    the environment already has a token file, the *Generate Token* button is 
    instead the *Download Token* button): 

    -   **No existing token file:** Generate the token by clicking *Generate 
        Token*. 
    -   **Existing token file, and changed selections of LCS services:** 
        Regenerate the token by clicking *Regenerate Token*. Note that if you 
        regenerate the token, all @product@ servers that currently use it will 
        be disconnected from LCS and won't be able to reconnect until receiving 
        the new token. 
    -   **Existing token file, and no changes to selections of LCS services:** 
        No token generation or regeneration is required. 

    Once you've taken the appropriate action, download the token file by 
    clicking the *Download Token* button at the bottom of the screen. 

    ![Figure 3: An environment's Registration tab lets you manage the token file used to activate your server in the environment.](../../images-dxp/lcs-registration.png) 

4.  Shut down your @product@ instance if it's running. Place the token file in 
    your instance's `[Liferay Home]/data` folder, and then start the instance. 
    On startup, the LCS client app uses the environment token to activate your 
    @product@ instance in the LCS environment that corresponds to the token. 

5.  Celebrate! Your @product@ server is activated and connected to LCS. 

+$$$

**Note:** You may be wondering what happens when your server can't reach LCS. 
Don't worry, this won't cause a rift in the space-time continuum. Because LCS is 
deployed on a global cloud infrastructure set up for automatic failure recovery, 
the potential for non-availability is very low. In the event of an outage, 
however, registered @product@ instances maintain a local copy of their uptime 
information to transmit to LCS when it comes back online. Active @product@ 
subscriptions also have a seven-day grace period to re-establish connectivity 
and remain valid. This is ample time for LCS to come back online. Should an 
extended LCS outage occur, Liferay support can provide temporary subscription 
keys that don't require LCS. 

$$$

In your @product@ instance, you can view your LCS connection status in the LCS 
client app. Access the client by clicking *Control Panel* &rarr; 
*Configuration* &rarr; *Liferay Connected Services*. Note that you can change 
which LCS services are enabled for your @product@ instance by clicking the 
*Configure Services* link. 

Here's a full description of what a connected LCS client app displays: 

-   **Connection Uptime:** The duration of the client's connection with LCS.
-   **Last Message Received:** The time the LCS client received the latest 
    connection message from LCS. These messages occur only upon 
    connection/reconnection and are unrelated to server metrics. It's therefore 
    common for a long period of time to pass before the client receives another 
    such message for a reconnection event. 
-   **Services:** The LCS services enabled for this @product@ instance. Note 
    that all @product@ instances that connect to the same LCS environment must 
    use the same set of LCS services. LCS services can't be enabled on an 
    instance-by-instance basis. 
-   **Project Home:** This link takes you to this server's LCS project. The 
    project home in LCS is also called the *dashboard*. 
-   **Environment:** This link takes you to this server's LCS environment. 
-   **Server Dashboard:** This link takes you to the server on LCS. 

![Figure 4: The server is connected to LCS.](../../images-dxp/lcs-server-connected.png)

Awesome! Now you know how to use environment tokens in LCS to activate your 
@product@ server. 

For information on using the other features of LCS, see 
[the next article](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/using-lcs). 
