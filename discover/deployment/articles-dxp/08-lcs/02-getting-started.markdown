# Getting Started with LCS

The first time you log in to 
[lcs.liferay.com](https://lcs.liferay.com), 
LCS presents you with a wizard that walks you through the environment creation 
process. Click *Get Started* to begin. 

![Figure 1: Click *Get Started* to begin the wizard.](../../images-dxp/lcs-onboarding-00.png)

Each of these steps corresponds to a step in the wizard: 

1.  Select the LCS project to create the environment in. You can select any of 
    your available LCS projects. Note that each project lists its available 
    subscriptions and whether it supports elastic subscriptions. 

    ![Figure 2: Select the LCS project you want to create the environment in, and click *Next*.](../../images-dxp/lcs-onboarding-01.png)

2.  Give your environment a name and description. The name is mandatory, but the 
    description is optional. Although you can enter anything you wish in these 
    fields, it's best to choose a name and description that accurately describe 
    the environment (e.g., Development, Production, Test, etc.). 

    ![Figure 3: Supply the environment's name and description, then click *Next*.](../../images-dxp/lcs-onboarding-02.png)

3.  Select the environment's subscription type from the project's available 
    subscriptions. Even if you won't use LCS to activate the servers you connect 
    to this environment, you must still select a subscription type. Also note 
    that you can't change this selection after creating the environment. 

    ![Figure 4: Select the environment's subscription type, then click *Next*.](../../images-dxp/lcs-onboarding-03.png)

4.  Select whether servers that connect to this environment will be part of a 
    cluster. LCS provides additional tools in clustered environments that help 
    you manage the cluster. Note that each clustered environment can only 
    support a single cluster. 

    ![Figure 5: Select whether this is a clustered environment, then click *Next*.](../../images-dxp/lcs-onboarding-04.png)

5.  Select whether the environment allows elastic subscriptions. Elastic 
    subscriptions let you register an unlimited amount of servers. This is 
    critical for auto-scaling situations in which servers are created and 
    destroyed automatically in response to demand. 

    ![Figure 6: Select whether this is an elastic environment, then click *Next*.](../../images-dxp/lcs-onboarding-05.png)

6.  Select the LCS services you want to use with servers that connect to this 
    environment. You can select the following services: 

    -   **Liferay Instance Activation:** Enabling this lets LCS activate any 
        Liferay DXP instances that connect to the environment. Note that if you 
        disable this service, the servers that connect to the environment must 
        be running version 5.0.0 or newer of the LCS Client app. 

        Also note that you **must** use LCS for activation of containerized 
        instances, cloud deployments, and instances that use Liferay Analytics 
        Cloud and/or Elastic subscriptions. Otherwise, you don't have to use LCS 
        for activation. 

    -   **Portal Analytics:** Enabling this lets LCS collect and display 
        performance metrics for any servers that connect to the environment. 

    -   **Fix Pack Management:** Enabling this lets you use LCS to check for and 
        install Liferay DXP fix packs. 

    -   **Portal Properties Analysis:** Enabling this lets LCS compare the 
        current portal property settings to their default values for any servers 
        that connect to the environment. Note that LCS doesn't access 
        [security sensitive properties](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/what-lcs-stores-about-your-liferay-dxp-servers). 
        Click *Blacklisted Properties* to view and change any additional 
        properties that are blacklisted from analysis. 

    ![Figure 7: Enable or disable the LCS services you want to use for servers that connect to the environment, then click *Next*.](../../images-dxp/lcs-onboarding-06.png)

7.  Review your selections and make any changes. Click *Create Environment* when 
    you're finished. 

    ![Figure 8: This form contains each of your selections from the previous steps. Make any changes you want, then click *Create Environment*.](../../images-dxp/lcs-onboarding-07.png)

After creating your environment, LCS presents you with a screen that lets you 
download the LCS Client app, download the environment's token file, and go to 
your project's dashboard in LCS. For instructions on connecting your server to 
your LCS environment, see the article 
[Registering Your Liferay DXP Server with LCS](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/activating-your-liferay-dxp-server-with-lcs). 
