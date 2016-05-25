# Managing Liferay with Liferay Connected Services [](id=managing-liferay-with-liferay-connected-services)

![EE Only Feature](../../images/ee-feature-web.png)

Liferay Connected Services (LCS) is a set of online tools and services that lets 
you manage and monitor your Liferay EE installations. While Liferay's patching 
tool lets you apply fix packs and other updates, the install process is still 
manual. LCS simplifies this process by automatically installing any fix packs 
that you *choose*. That last point is an important one--LCS won't install 
anything that you don't specifically choose for installation. You still have 
control over what gets applied to your Liferay installations. LCS just automates 
the process by enabling one-click downloading and updating. You can also use LCS 
to monitor the performance of your Liferay instances. This includes data on 
pages, portlets, memory usage, JVM performance, and much more. Even better, the 
features of LCS work regardless of whether your Liferay instance is on a single 
discreet server or distributed across a node. You can find more information 
about LCS on its 
[official product page](http://www.liferay.com/products/liferay-connected-services).

Before going any further, you should make sure that your Liferay instances meet 
the requirements for LCS--you must be running Liferay Portal 6.2 EE. 

Also, you should take note of a few key terms used throughout this guide: 

- *Project*: Represents a group of users belonging to a company or organization. 
  For example, a project can consist of all the users from a project team or 
  business unit, or it can include the entire company.
- *Environment*: Represents a physical cluster of servers or a virtual or 
  logical aggregation of servers.
- *Server*: Describes a concrete portal instance. It can be a standalone server 
  or a cluster node.

As you go through this guide, you'll cover the following sections on LCS:

- Patching Tool Configuration
- LCS Client Configuration
- Using LCS

You'll get started by configuring your Liferay instance's patching tool for use 
with LCS. 
