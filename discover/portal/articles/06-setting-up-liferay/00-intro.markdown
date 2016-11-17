# Configuring @product@ [](id=configuring-liferay)

If @product@ is anything, it's configurable. As the core of Liferay is shrinking
due to its increased modularity<!--Link to modularity docs-->, it's important
that all the applications in Liferay are also configurable. 

Breaking it down, three types of applications must be configurable:

1.  Liferay itself
2.  Liferay's native applications <!--Find a better term if this is
inappropriate-->
3.  Custom applications <!-- Better term needed, avoid custom-->

To this end, Liferay's engineers have made the platform and its own applications
configurable, and created a mechanism for developers to make their own
applications configurable<!--Link to dev docs-->.

In this article you'll learn how to mix the usual (`portal-ext.properties`,
anyone?) configuration options with some new ones (*System Settings* in the
Control Panel, for example) to configure Liferay, Liferay's applications, and
any applications added by your enterprise's developers.

## Where Configuration Happens [](id=where-configuration-happens)

Liferay's configuration takes place in the following places: 

Properties files: properties files  that set default behavior may be included in
the platform or the modules. Keep in mind that these settings can always be
overwritten by a system administrator in the UI. 

Database: configuration through Liferay's UI is stored in a database.  The
values in the database always override the default configurations set through
properties files.

So where in the UI do these configuration options appear? Well, that depends on
the scope you want to affect with the settings you choose.

At what level do you want to configure Liferay and its apps? Find out about
configuration scope next.

## Configuration Scope [](id=configuration-scope)

Scope is an important concept to understand when configuring Liferay. Take
Language settings, for example. You can set the default language used by the
portal instance. You can also set the default language of a site. Some
applications even let you set the default language used by a specific piece of
content. 

Depending on the scope you choose, you'll impact Liferay and its applications
with more or less granularity. At one end of the spectrum, you can affect the
scope of the whole *System*. Configurations made at the System scope affect all
portal instances, sites, and portlet instances. At the opposite end of the
spectrum, configurations made at the *Portlet Instance* level provide
configuration settings only for that particular instance of the portlet. For
details on configuring a scope for a particular application instance, visit the
[Application Scope](/discover/portal/-/knowledge_base/7-0/application-scope)
article. Here's an overview of the available configuration scopes:

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

<!-- Some sort of diagram for scope hierarchy -->

Scopes in Liferay are hierarchical so that one scope can set the default values
for the underlying subscopes. For example, it is possible to set the default
values for all portal instances, sites or portlet instances at configuration at
the system level. Think of the settings made at higher levels as being defaults.
If a different configuration setting is made at a level with more granularity
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

- Integrating Existing Users

All of these are accomplished through the Control Panel. Start by learning to
configure modules system-wide in the System Settings Control Panel app.

<!--The purpose of this document is to explain the mechanism available to configure
@product-ver@. It is oriented towards new administrators but will also mention the
differences with Liferay Portal 6.2 when appropriate for those who are used to it.
Concepts Liferay is a very configurable platform and it promotes its
applications to be very configurable. Both the platform and the applications are
build in a modular way, which means that they are formed by several
independently deployed modules and each of these modules will have its own
configuration.

























Liferay's configuration can leave in the following places: Properties files:
these properties files may be included in the platform or the modules, although
they can always be overwritten by a system administrator.  Database: the
configuration that can be modified through Liferay's UI is stored in a database.
The values in the database always override those set through properties files,
which at that point act as the defaults.

Another important concept to understand Liferay's configuration is scopes.
Scopes allow having different configurations depending on the context. Here are
the existing scopes:

System
Portal Instance
Site
Portlet Instance

Scopes in Liferay are hierarchical so that one scope can set the default values
for the underlying subscopes. For example, it is possible to set the default
values for all portal instances, sites or portlet instances at configuration at
the system level. 

Developers interested in learning more about how to make their apps configurable
can read
https://docs.google.com/document/d/1oLYzZfLQwCeGY_i4zFfk34tlzAjnx15z2fMao71CFII/edit#heading=h.u00c482ufmfw

Best Practices
Prior to 7.0
Prior to 7.0, Liferay's configuration management was in the form of:
portal.properties
portlet.properties

The best practice 

7.0 and Beyond

How can an administrator change a configuration option?  As mentioned above
there are several scopes. Depending on the scope you want the tool to change the
configuration is different:

System scope:

Core Platform configuration: It can be modified by using the portal-ext.properties file
Module configuration: for configuration of any deployed module at the system
scope there are two ways of doing it:

Use the System Settings tool in the Control Panel

Deploy a file which has the following name: [ConfigurationObject].cfg

Module properties: Some modules still use properties for configuration instead
of Configuration objects. In these cases the way to modify their configuration
is through a Fragment Bundle. This is just a bundle that contains a
portlet-ext.properties file with the overridden configuration. The MANIFEST of
the bundle can be generated with bnd easily. Here is an example bnd.bnd file:

Bundle-SymbolicName: com.liferay.journal.service.ext
Fragment-Host: com.liferay.journal.service;bundle-version=1.0.0
-includeresource: src/main/resources
And then place the overridden config in src/main/resources/portlet-ext.properties as follows:
    journal.article.view.permission.check.enabled=true 

    For more information, check the full example of a module that configures the journal-service module.

Portal Instance scope: 

Use Portal Settings in the Control Panel. Other
applications in the configuration section also allow configuring specific
functionalities

Site scope:

Use Site settings in the site administration menu.
Portlet instance scope:
Choose the "Configuration" option in the portlet menu accessed by clicking the three elipsis in the upper right corner.

-->


