# Managing LCS Environments [](id=managing-lcs-environments)

Environments are the key components of your LCS project. When you register a 
server in LCS, you do so in an environment. An environment is therefore the 
gateway to managing and monitoring your servers in LCS. 

## Creating Environments [](id=creating-environments)

The first time you log in to LCS, a wizard walks you through each step required 
to create your project's first environment. The 
[getting started article](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/getting-started-with-lcs) 
explains this in detail. You can create additional environments via the same 
wizard or a simple form. 

To create an environment, click the *Add Environment* button from the Dashboard. 
This opens the New Environment form. Each section in this form corresponds to a 
step in the wizard. If you want to use the wizard instead, click the 
*Open Wizard* link at the top of the form. See the 
[getting started article](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/getting-started-with-lcs) 
for a description of each setting in the form and wizard. 

+$$$

**Note:** When creating an environment, make your selections carefully for the 
*Subscription Type*, *Cluster*, and *Elastic* fields. You can't change them 
after creating the environment. 

$$$

![Figure 1: The New Environment form lets you create environments.](../../../images-dxp/lcs-new-environment.png)

## Working with Environments [](id=working-with-environments)

Clicking an environment on the left-hand side of the Dashboard takes you to the
environment view, which lets you manage an environment in your LCS project. 

The UI is segmented into three tabs: 

1.  **Fix Packs:** View and apply fix packs for the environment's servers. This 
    tab only appears if a server is registered in the environment. A table 
    displays the following information for each fix pack: 

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
    [*environment tokens*](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/understanding-environment-tokens) 
    that connect your servers to LCS. 

3.  **Environment Settings:** Change the environment's name, location, and
    description. You can also see if the environment allows clustered servers
    and view the environment's subscription type. Click the *Save* button to
    save any changes you make in the Environment Settings tab. You can also
    delete the environment by clicking *Delete Environment*. 

![Figure 2: The LCS environment view shows an overview of an LCS environment.](../../../images-dxp/lcs-environment-view.png)
