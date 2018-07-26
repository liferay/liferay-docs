# Virtual Instances [](id=virtual-instances)

Here's a quick scenario: you already have a server hosting an @product@
installation, and a hosted database. It has lots of Users, Sites, and other
global portal settings defined. If you need another installation with its own
[Users](/discover/portal/-/knowledge_base/7-1/users-and-organizations),
[Sites](/discover/portal/-/knowledge_base/7-1/building-a-site),
[Instance Settings](/discover/portal/-/knowledge_base/7-1/general-settings),
and other specific customizations, adding a Virtual Instance might be right for
you. 

Run more than one Virtual Instance on a single server, with a shared shared
database, but separate data and configurations. Because Virtual Instances share
an application server and an OSGi container, the following customizations are
also shared between Virtual Instances:

- All 
    [custom code](/develop/tutorials/-/knowledge_base/7-1/introduction-to-liferay-development)
    custom code your developers have deployed
- All 
    [System scoped](/discover/portal/-/knowledge_base/7-1/system-settings)
    configurations (for example, `.config` files)
- All application server configuration

Manage Virtual Instances in the Virtual Instances section of the Control Panel,
under the Configuration heading. Each instance's data is kept separate from
every other instance's data, though all virtual Instances share a database.

Users are directed to the proper Virtual Instance because each one has its own
domain name. Before you configure an instance, configure its domain name in your
network. To add an instance, 

1.  Go to Control Panel &rarr; Configuration &rarr; Virtual Instances.

2.  Click the *Add* (![Add](../../../images/icon-add.png)) button.

![Figure 1: Add and manage virtual instances of Liferay in the Control Panel's *Configuration* &rarr; *Virtual Instances* section.](../../../images/virtual-instances.png)

You'll be prompted to fill out four fields and a check box:

**Web ID** 
: Enter an ID for the instance. A common convention is to use the domain name.

**Virtual Host** 
: Enter the domain name you configured in your network here. When users are
directed to your server via this domain name, they'll be sent to the proper
Virtual Instance.

**Mail Domain** 
: Enter the domain name for the mail host for the Virtual Instance.  Email
notifications are sent from the instance using this domain.

**Max Users** 
: Enter the maximum number of user accounts the Virtual Instance supports.
Enter *0* if the virtual instance supports unlimited Users.

**Active** 
: Use this check box to choose whether to create an active or an inactive
virtual instance. Inactive Virtual Instances aren't accessible to anyone, even
the Administrator.

When finished filling out the form, click *Save* and navigate to the instance
using its domain name. You're brought to what looks like a clean install of
@product@. This is your new Virtual Instance which can be configured any way you
like. Read the remaining articles in this section on configuring an instance's
settings.
