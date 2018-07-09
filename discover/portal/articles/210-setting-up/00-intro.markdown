# Setting Up [](id=setting-up)

If @product@ is anything, it's configurable. As the core is shrinking
due to its increased modularity, it's important that all the applications in
Liferay are also configurable. 

Breaking it down, three types of applications must be configurable:

1.  The platform itself
2.  Liferay's out-of-the-box applications 
3.  Custom applications

To this end, Liferay's engineers have made the platform and its applications
configurable, and created a mechanism for developers to make their
[applications configurable](/develop/tutorials/-/knowledge_base/7-1/configurable-applications).

## Where Configuration Happens [](id=where-configuration-happens)

Liferay's configuration takes place in the following places: 

**User Interface:** configuration through Liferay's UI is stored in a database. The
values set in the UI always override configurations set in properties files.

**Properties files:** properties files that set default behavior may be included in
the platform or the modules. Keep in mind that these settings can always be
overridden by a system administrator in the UI. To find what properties are
configurable this way, visit
[docs.liferay.com](https://docs.liferay.com/portal/7.1-latest/propertiesdoc).

The UI location where these configuration options appear depends on the scope
you want to affect with the settings you choose.

## Configuration Scope [](id=configuration-scope)

Depending on the configuration scope of a setting you change, you'll impact the
platform and its applications with more or less granularity. At one end of the
spectrum, you can affect the scope of the whole system. Configurations made at
the system scope affect all virtual instances, Sites, and widgets. At the
opposite end of the spectrum, configurations made at the widget level provide
configuration settings only for that instance of the widget. 

Take Language settings, for example. You can set the default language used by
the virtual instance. You can also set the default language of a Site. Some
applications even let you set the default language used by a specific piece of
content. 

Here's an overview of the available configuration scopes:

**System:** Configuring Liferay and its applications through System Settings
provides system scoped configurations and sets default values for all virtual
instances, sites, or widgets.

**Virtual Instance:** Configuring in Instance Settings provides settings that
act on the specific virtual instance for which they are made, including Sites
and widgets in the virtual instance.

**Site:** Configurations made at the Site scope, where you select the Site to
configure in the Site selector, provide settings that take place only in that
Site. Alternate configurations can be made in different Sites.

**Widget Scope:** Configuring a specific widget only provides a
configuration for that particular widget.

Scopes in Liferay are hierarchical so that one scope can set the default values
for the underlying sub-scopes. For example, making a system-wide configuration
sets the default values for all virtual instances, sites or widgets of
the system. If a different configuration is set at a level with more granularity
(for example, the widget scope), it takes precedence over the settings made
at less granular scopes (for example, the virtual instance scope).

This section contains articles on configuring Liferay at the System and Instance
scopes:

System wide configuration:

- System Settings is the primary location for system configuration.

- Server Administration contains some lower-level server configuration options, such
  as logging.

Setting up a virtual instance:

- Virtual Instances is where virtual instances are added and
  edited.

- Instance Settings is the primary location for a virtual instance's
  configuration.

- Custom Fields is where additional database fields are added to existing
  virtual instance entities.

All of these are accessed through the Control Panel. Start by learning to
configure modules system-wide in the System Settings Control Panel app.
