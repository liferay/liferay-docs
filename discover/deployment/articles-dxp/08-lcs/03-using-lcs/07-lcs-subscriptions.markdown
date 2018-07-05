# Managing Liferay DXP Subscriptions [](id=managing-liferay-dxp-subscriptions)

LCS lets you use and view your @product@ subscriptions. Recall that when you 
[create an environment](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/managing-lcs-environments#creating-environments), 
you assign its subscription type. Registering a server in that environment 
consumes an activation key from that subscription type. You can also view your 
project's available subscriptions and see how they're being used. 

Depending on your subscription agreement, LCS also lets you register servers via 
*elastic subscriptions*. Elastic subscriptions let you register an unlimited 
number servers. This is invaluable in auto-scaling environments, where servers 
are automatically created and destroyed in response to load. 

You can access these features from the *Subscriptions* tab on the upper-left of 
the LCS site. This tab contains two other tabs: *Details* and *Elastic 
Subscriptions*. 

![Figure 1: LCS lets you view and manage your subscriptions.](../../../images-dxp/lcs-subscriptions.png)

There are four tables in the *Details* tab: 

1.  **Subscriptions:** Shows a list of the available subscriptions in your LCS 
    project. For each subscription, this table shows the following information: 

    - Subscription Type
    - Start Date
    - Expiration Date
    - Support End Date
    - Platform
    - Product
    - Processor Cores Allowed
    - Servers Allowed
    - Servers Used

    Note that *Processor Cores Allowed* shows the number of processor cores that 
    the subscription allows for each server. 

2.  **Subscriptions Summary:** Shows how your subscriptions are currently used 
    in your project. For each subscription type, this table shows the number of 
    servers allowed, used, and available. 

3.  **Project Environments:** Shows your project's environments and their 
    assigned subscription types. Each environment must have a subscription type. 

4.  **Project Servers:** Shows the environment and subscription type for each 
    server in your LCS project. 

If any of the information in these tables is missing or incorrect, contact 
Liferay Support. 

+$$$

**Note:** If you try to register a server that exceeds the number of processor 
cores that your subscription allows per server, the registration fails and the 
server is locked down. A console error also indicates the server's core count. 
You can compare this with your subscription's processor cores allowed in LCS's 
Subscriptions table. To register the server, you can either reduce the number of 
cores it uses (e.g., by deploying to different server hardware, or reducing the 
number of virtual processors in a VM or container), or contact Liferay Sales to 
increase the number of processor cores that your subscription allows per server. 

$$$

## Decommissioning Servers [](id=decommissioning-servers)

To decommission a server and free its activation key for reuse, select the 
server's environment on the left and then select the server. In the server's 
*Server Settings* tab, select *Unregister*. Also note that when you shut down a 
server normally, its activation key is immediately freed for reuse. If the 
server crashes or its shutdown is forced (e.g., kill), its activation key is 
freed for reuse within six minutes. 

## Elastic Subscriptions [](id=elastic-subscriptions)

Elastic subscriptions let you register an unlimited number of servers. This is 
crucial for auto-scaling environments where servers are created and destroyed 
automatically. You can view data on your elastic servers from the 
*Subscriptions* tab's *Elastic Subscriptions* tab. 

+$$$

**Note:** To register elastic servers in an environment, that environment must 
be set as elastic when it's created. For more information, see the 
[documentation on creating environments](#creating-an-environment).

$$$

![Figure 2: The *Elastic Subscriptions* tab shows details about your project's elastic servers.](../../../images-dxp/lcs-elastic-subscriptions.png)

The *Elastic Subscriptions* tab displays the number of elastic servers online 
and the uptime details for each. A graph shows the number of elastic servers 
online each day, while a table lists each elastic server's start time, end time, 
and duration. The total duration for servers is below the table. To download a 
report of the table's data, click *Download Report*. Also, you can use the 
*Environment* and *Month* selectors above the graph to select the environment 
and month to show data from, respectively. The data in both the graph and the 
table reflect your selections here. 
