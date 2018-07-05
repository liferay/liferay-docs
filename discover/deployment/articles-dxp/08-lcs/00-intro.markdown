# Managing Liferay DXP with Liferay Connected Services [](id=managing-liferay-dxp-with-liferay-connected-services)

Liferay Connected Services (LCS) is a set of tools and services for managing and
monitoring your @product@ instances. LCS can help you install fix packs, monitor
your instances' performance, activate your instances, and help you manage your
subscriptions. In other words, LCS is like a butler for the mansion that is
@product@. Even better, the features of LCS work regardless of whether your
instance is on a single discreet server or distributed across a cluster. It's
like having a single butler that can serve several mansions at once! You can
find more information about LCS on its 
[official product page](http://www.liferay.com/products/liferay-connected-services). 

+$$$

**Note:** You must use LCS for activation of containerized instances, cloud 
deployments, and instances that use Liferay Analytics Cloud and/or Elastic 
subscriptions. Otherwise, you don't have to use LCS for activation. You can 
instead request an XML activation key from Liferay Support. 

$$$

Before going any further, you should take note of a few key terms used 
throughout this guide: 

**Project:** Represents a group of users belonging to a company or organization.
For example, a project can consist of all the users from a project team or
business unit, or it can include the entire company. 

**Environment**: Represents a physical cluster of servers or a virtual or
logical aggregation of servers. 

**Server**: Describes a concrete @product@ instance. It can be a standalone
server or a cluster node. 

As you go through this guide, you'll cover the following topics: 

- LCS Preconfiguration 
- Activating Your @product@ Server with LCS 
- Using LCS 
- Troubleshooting Your LCS Connection

You'll get started with the configuration steps required to use LCS with 
@product@. 
