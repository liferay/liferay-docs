# Managing LCS Environments [](id=managing-lcs-environments)

Environments are the key components of your LCS project. When you register a 
server in LCS, you do so in an environment. An environment is therefore the 
gateway to managing and monitoring your servers in LCS. 

## Creating Environments [](id=creating-environments)

You can create an environment by clicking the *Add Environment* tab from the
Dashboard. This opens the New Environment pop-up. 

+$$$

**Note:** When creating an environment, make your selections carefully for the 
*Subscription Type*, *Cluster*, and *Elastic* fields. You can't change them 
after creating the environment.

$$$

Complete these fields to create your environment: 

**Environment Name:** The environment's name.

**Description:** The environment's description.

**Subscription Type:** The environment's subscription type. Servers activated in
the environment consume an activation key from this subscription type. Note that
you can't change this after creating the environment. 

**Cluster:** Whether this is a clustered environment. Only clustered
environments can contain clustered servers. Note that you can't change this
after creating the environment. Also note that you must set the portal property
`cluster.link.enabled` to `true` in any servers that connect to a clustered
environment. 

**Elastic:** Whether this environment permits the use of *elastic
subscriptions*. Note that you can't change this after creating the environment.
Elastic subscriptions let you register an unlimited number of servers. This is
crucial for auto-scaling environments in which servers are created and destroyed
automatically. For more information, see 
[the documentation on elastic subscriptions](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/managing-liferay-dxp-subscriptions#elastic-subscriptions). 

**Services:** The LCS services to enable in the environment. Note that although
[LCS doesn't access security sensitive properties](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/what-lcs-stores-about-your-liferay-dxp-servers),
you may have additional properties you want to prevent LCS from analyzing. Enter
them into the pop-up that appears when you click *Blacklisted Properties*. This
Blacklisted Properties pop-up lists the currently blacklisted properties and
lets you add additional ones to the list. 

When you're finished with your selections, click *Create Environment*. 

![Figure 1: The New Environment pop-up lets you create environments.](../../../images-dxp/lcs-new-environment.png)

## Working with Environments [](id=working-with-environments)

Clicking an environment on the left-hand side of the Dashboard takes you to the
environment view. The environment view manages an environment in your LCS
project. 

The UI is segmented into three tabs: 

1.  **Fix Packs:** View and apply fix packs for the environment's 
    servers. This tab only appears if a server is registered in the environment. 
    A table displays the following information for each fix pack: 

    -   **Name:** The fix pack's name.
    -   **Status:** The fix pack's status.
    -   **Server:** The server the fix pack can be applied to.
    -   **Size:** The fix pack's size. This only appears if the server is 
        running. 
    -   **Download:** A button to download the fix pack to the server. This only 
        appears if the server is running. 

    Once a fix pack downloads, LCS prompts you to restart your server, which 
    installs any downloaded fix packs. Note that you must start your server with 
    the privileges required to write to the disk location where patches are 
    stored and processed (the `patching-tool` folder). To use LCS to install fix 
    packs across a cluster, follow the same procedure. LCS downloads and
    installs fix packs simultaneously across all nodes---you don't have to
    handle each separately. 

2.  **Registration:** Generate and download 
    [*environment tokens*](#using-environment-tokens) 
    that connect your servers to LCS. 

3.  **Environment Settings:** Change the environment's name, location, and
    description. You can also see if the environment allows clustered servers
    and view the environment's subscription type. Click the *Save* button to
    save any changes you make in the Environment Settings tab. You can also
    delete the environment by clicking *Delete Environment*. 

![Figure 2: The LCS environment view shows an overview of an LCS environment.](../../../images-dxp/lcs-environment-view.png)
