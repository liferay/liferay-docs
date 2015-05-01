# Virtual LDAP Server

![EE-only](../../images/ee-feature-web.png)

The Virtual LDAP Server EE app, available from
[Liferay Marketplace](https://liferay.com/marketplace), 

Lightweight Directory Access Protocol (LDAP) is an application protocol for
accessing and maintaining distributed directory information services over an
Internet Protocol (IP) network. Out-of-box, Liferay allows users to be imported
from or exported to an external LDAP server. Please see Liferay's
[LDAP](discover/portal/-/knowledge_base/6-2/integrating-existing-users-into-liferay#ldap)
documentation for details. The Virtual LDAP Server EE app, available from
[Liferay Marketplace](https://liferay.com/marketplace), adds an internal LDAP
server to Liferay that runs as a service. The LDAP server added by the app
exposes Liferay's user data store via the LDAP protocols. This allows third
party tools to access Liferay user information (profile, roles, groups, etc.) as
if accessing an LDAP server.

By default, the LDAP server added by the Virtual LDAP Server EE app runs on port
11389 but this can be configured by overriding the following line of the
plugin's `portlet.properties` file:

    bind.port=11389

When overriding Liferay portal or Liferay plugin properties, remember not to
edit the `portal.properties` or `portlet.properties` directly. Instead, create a
`portal-ext.properties` or `portlet-ext.properties` in the same directory as the
original file and specify the properties to override in the new file.

Once you've installed the Virtual LDAP Server app, check your Liferay log for
a success message like this:

    17:38:16,022 INFO  [localhost-startStop-2][HookHotDeployListener:821] Hook for vldap-web is available for use

When you see such a message, open your favorite LDAP client and connect to port
11389 (or the custom port that you configured). For example,
[JXplorer](http://jxplorer.org) is a free
and open-source cross-platform LDAP browser and editor. When you use an LDAP
client to connect to Liferay, you'll see a tree like this:

![Figure 1: Depending on your LDAP client and the contents of your portal, you should see a tree like this when you connect to Liferay's LDAP server.](../../images/virtual-ldap-ee-jxplorer.png)

Click on a user or other Liferay entity to view its LDAP attribute types and
values. The Virtual LDAP Server EE app includes logic for mapping Liferay entity
attributes to LDAP entity attributes.

![Figure 2: Click on a user or other Liferay entity to view its LDAP attribute types and values.](../../images/virtual-ldap-ee-jxplorer2.png)

To view the distinguished name of an LDAP entity, use your LDAP client to export
an LDIF file, either of the full LDAP directory tree or a subtree.

For example, here's the distinguished name of the default Liferay administrator
user:

    DN: cn=test,ou=User,ou=Roles,ou=liferay.com,o=Liferay

If a user or other Liferay entity is added, edited, or deleted while you're
browsing Liferay's LDAP server, simply refresh your LDAP client to see the
update.
