# Managing Liferay with Liferay Connected Services [](id=managing-liferay-with-liferay-connected-services)

Liferay Connected Services (LCS) is a set of online tools and services that lets 
you manage and monitor your Liferay DXP installations. While DXP's patching tool 
lets you apply fix packs and other updates, the install process is still manual. 
LCS simplifies this process by automatically installing any fix packs that you 
*choose*. That last point is an important one--LCS won't install anything that 
you don't specifically choose for installation. You still have control over what 
gets applied to your DXP installations. LCS just automates the process by 
enabling one-click downloading and updating. You can also use LCS to monitor the 
performance of your DXP instances. This includes data on pages, portlets, 
memory usage, JVM performance, and much more. Even better, the features of LCS 
work regardless of whether your DXP instance is on a single discreet server 
or distributed across a node. You can find more information about LCS on its 
[official product page](http://www.liferay.com/products/liferay-connected-services).

Before going any further, you should take note of a few key terms used 
throughout this guide: 

- *Project*: Represents a group of users belonging to a company or organization. 
  For example, a project can consist of all the users from a project team or 
  business unit, or it can include the entire company.
- *Environment*: Represents a physical cluster of servers or a virtual or 
  logical aggregation of servers.
- *Server*: Describes a concrete DXP instance. It can be a standalone server 
  or a cluster node.

As you go through this guide, you'll cover the following topics: 

- Patching Tool Configuration
- LCS Client Configuration
- Using LCS

You'll get started with a few configuration steps that are required to use LCS 
with your Liferay DXP instances.
