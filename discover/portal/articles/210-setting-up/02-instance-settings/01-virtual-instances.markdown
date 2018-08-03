# Virtual Instances [](id=virtual-instances)

Here's a quick scenario: you already have a server hosting a @product@ 
installation and a hosted database. It has many 
[users](/discover/portal/-/knowledge_base/7-1/users-and-organizations), 
[sites](/discover/portal/-/knowledge_base/7-1/building-a-site), 
and specific 
[instance settings](/discover/portal/-/knowledge_base/7-1/general-settings). 
If you need another such installation, then adding a *Virtual Instance* might be 
right for you. 

You can run more than one Virtual Instance on a single server with a shared 
database, but separate data and configurations. Users are directed to the 
correct Virtual Instance via its unique domain name. And because Virtual 
Instances share an application server and OSGi container, they also share these 
customizations: 

-   Custom code deployed by developers and administrators.
-   [System-scoped configurations](/discover/portal/-/knowledge_base/7-1/system-settings) 
    (e.g., `.config` files, changes made in *Control Panel* &rarr; 
    *Configuration* &rarr; *System Settings*). 
-   Application server configuration.

Administrators can manage Virtual Instances in *Control Panel* &rarr; 
*Configuration* &rarr; *System Settings*.  

![Figure 1: Add and manage virtual instances of Liferay in the Control Panel's *Configuration* &rarr; *Virtual Instances* section.](../../../images/virtual-instances.png)

Follow these steps to create a Virtual Instance: 

1.  Before you create a Virtual Instance, configure its domain name in your 
    network. 

2.  Go to *Control Panel* &rarr; *Configuration* &rarr; *Virtual Instances*. 

3.  Click the *Add* button 
    (![Add](../../../images/icon-add.png)). 
    This opens the *New Instance* form. 

4.  Complete the New Instance form as follows:

    -   **Web ID:** The instance's ID. Using the domain name is a common 
        convention. 

    -   **Virtual Host:** The domain name you configured in your network. When 
        users are directed to your server via this domain name, they'll be sent 
        to the Virtual Instance that contains their data. 

    -   **Mail Domain:** The mail host's domain name for the Virtual Instance. 
        Email notifications are sent from the instance using this domain. 

    -   **Max Users:** The maximum number of user accounts the Virtual Instance 
        supports. Enter *0* to support unlimited users. 

    -   **Active:** Whether the Virtual Instance is active. Note that inactive 
        Virtual Instances aren't accessible to anyone, even the administrator. 

    Click *Save* when you're done filling out the form. 

Now you can navigate to the instance using its domain name. You're brought to 
what looks like a clean install of @product@. This is your new Virtual Instance! 
You can configure it any way you like. The remaining articles in this section 
show you how to configure an instance's settings. 
