# Setting Up

If @product@ is anything, it's configurable. As the core is shrinking
due to its increased modularity, it's important that all the applications in
Liferay are also configurable. 

Breaking it down, three types of applications must be configurable:

1.  The portal itself
2.  Liferay's native applications 
3.  Custom applications

To this end, Liferay's engineers have made the platform and its own applications
configurable, and created a mechanism for developers to make their
[applications configurable](/develop/tutorials/-/knowledge_base/7-1/configurable-applications).

## Where Configuration Happens [](id=where-configuration-happens)

Liferay's configuration takes place in the following places: 

Properties files: properties files  that set default behavior may be included in
the platform or the modules. Keep in mind that these settings can always be
overwritten by a system administrator in the UI. To find what properties are
configurable this way, visit 
[docs.liferay.com](https://docs.liferay.com/portal/7.1-latest/propertiesdoc).

Database: configuration through Liferay's UI is stored in a database.  The
values in the database always override the default configurations set through
properties files.

The UI location where these database configuration options appear depends on the
scope you want to affect with the settings you choose.

## Configuration Scope [](id=configuration-scope)

Depending on the configuration scope of a setting you change, you'll impact
Liferay and its applications with more or less granularity. At one end of the
spectrum, you can affect the scope of the whole *System*. Configurations made at
the System scope affect all portal instances, sites, and portlet instances. At
the opposite end of the spectrum, configurations made at the *Portlet Instance*
level provide configuration settings only for that particular instance of the
portlet. 

Take Language settings, for example. You can set the default language used by
the portal instance. You can also set the default language of a site. Some
applications even let you set the default language used by a specific piece of
content. 

Here's an overview of the available configuration scopes:

*System:* configuring Liferay and its applications through System Settings
provides default settings for all portal instances, sites, or portlet instances.

*Portal Instance:* Configuring Liferay in Instance Settings provides settings that
act on the specific portal instance for which they are made, including sites
and portlet instances in the portal instance.

*Site:* Configurations made at the site scope, where you select the site to
configure in the site selector, provide settings that take place only in that
site. Alternate configurations can be made in different sites.

*Portlet Instance:* configuring a specific portlet instance only provides a
configuration for that particular instance.

Scopes in Liferay are hierarchical so that one scope can set the default values
for the underlying sub-scopes. For example, making a system-wide configuration
sets the default values for all portal instances, sites or portlet instances of
the system. If a different configuration is set at a level with more granularity
(for example, the portlet instance), it takes precedence over the settings made
at less granular scopes (for example, the portal instance scope).

This section contains articles on configuring Liferay at the System and Instance
scopes:

System wide configuration:

- System Settings

- Server Administration

- Custom Fields

Setting up a portal instance:

- Virtual Instances

- Instance Settings

All of these are accessed through the Control Panel. Start by learning to
configure modules system-wide in the System Settings Control Panel app.
