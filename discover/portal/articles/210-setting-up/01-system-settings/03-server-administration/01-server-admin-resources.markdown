---
header-id: server-administration-resources
---

# Server Administration: Resources

[TOC levels=1-4]

The Server Administration app's Resources tab contains several server wide 
actions that an administrator can execute. These include the following items: 

**Run the garbage collector:** Tells the JVM to free memory by running the 
garbage collector. 

**Generate a thread dump:** Generates a thread dump for later scrutiny to 
determine the presence and location of any deadlocks. Useful during 
performance testing, but you must add a logger category for
`com.liferay.server.admin.web.internal.portlet.action.EditServerMVCActionCommand`
and set it to `INFO` before executing.

**Clear content cached by this VM:** Clears content stored in the local 
cache. Only local JVM scope Ehcache content is cleared, not clustered 
Ehcache. [1](#one)

**Clear content cached across the cluster:** Clears the content of the 
entire clustered Ehcache. [1](#one)

**Clear the database cache:** Clears the database cache. Does not clear any 
Ehcache content except database results at the persistence layer. [1](#one)

**Clear the direct servlet cache:** Clears the direct servlet cache. In case 
emergency fixes must be applied, this action allows an administrator to 
clear out the cache manually to force JSPs to reload.

The direct servlet context optimizes JSP serving performance by caching and 
accessing the generated servlets directly instead of accessing them over the 
application server's dispatcher chain. This function is only suitable for 
cases where no filter is required for the JSPs; it should be enabled for 
production mode to improve performance, but disabled for development mode to 
allow JSP servlets to be reloaded on the fly. See the Direct Servlet Context 
section of the `portal.properties` file for details. [1](#one)

**Verify database tables of all plugins:** Checks all tables against their 
indexes for data retrieval accuracy. 

**Verify membership policies:** Checks that existing Site membership 
policies were correctly applied and automatically makes updates. If the
@product@ database is changed manually or is hacked---resulting in a user
assigned to a Site in violation of a site membership policy---this action
triggers the verification methods of all implemented Site membership policies.
Changes are automatically made to any memberships in violation. 

**Reset preview and thumbnail files for Documents and Media:** Regenerates 
previews of each item in your Documents and Media libraries. 

**Clean up permissions:** Removes permissions on the Guest, User, and Power 
User Roles to simplify the management of User Customizable Pages. The Add To 
Page permission is removed from the Guest and User Roles for all portlets, 
and is reduced in scope for Power Users from portal-wide to User Personal 
Site.

**Clean up portlet preferences:** This action cleans up database entries if 
portlet preferences become orphaned in the @product@ database. 

![Figure 1: The Resources tab of Server Administration lets you execute several server maintenance tasks.](../../../../images/server-admin-resources.png)

[<a name="one">1</a>] Caching occurs at multiple levels. Some higher caching 
layers aren't aware of lower caching layers. Always clear the cache at the
lowest (most granular) layer possible, even if you've already cleared a higher
level cache.
