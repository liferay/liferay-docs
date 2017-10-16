# Understanding System Configuration Files [](id=understanding-system-configuration-files)

@product@'s [System Settings
application](/discover/portal/-/knowledge_base/7-0/system-settings) is
convenient for making system-scoped configuration changes and setting default
configurations for other
[scopes](/discover/portal/-/knowledge_base/7-0/configuring-liferay#configuration-scope).
But there's another supported configuration approach: configuration files.
Configuration files are useful for transferring configurations from
pre-production systems to production systems, or between any other @product@
systems. Sometimes application developers might even choose to distribute the
default configuration for their application via configuration file. Whatever the
reason, configuration files are a straightforward way of configuring @product@.

## Creating Configuration Files [](id=creating-configuration-files)

System Settings provides an
[*Export*](/discover/portal/-/knowledge_base/7-0/system-settings#exporting-and-importing-configurations)
option that becomes available once you modify a configuration entry. Exporting
lets you download a `.config` file with all of the entry's settings in a
`key=value` format, and it's the recommended way to create `.config` files for
@product@. All available configuration keys and values are exported for an entry,
even if only one value was changed. It's possible to export either a single
configuration entry or the entire set of modified configurations in @product@.

Configuration files in @product@ must be named using a unique identifier so that
configuration files for different entries don't conflict. For example, this is
the name of the configuration file for the Journal Service entry, which backs
Liferay's Web Content capabilities:

    com.liferay.journal.configuration.JournalServiceConfiguration.config

![Figure 1: The Web Content System Settings entry has the backend ID `com.liferay.journal.configuration.JournalServiceConfiguration`.](../../../images/config-web-content-entry.png)

Configuration files use the `.config` property value format defined by the [Apache
Felix Configuration Admin
framework](http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html).

@product@ also supports the `.cfg` file format, which is common in some OSGi
environments. However, `.config` files are preferable, as they allow for
multi-valued properties and the ability to specify a property value's type. The
syntax described below is for `.config` files.

### Key/Value Syntax [](id=key-value-syntax)

The general syntax for all keys and values in a `.config` file is the same: 

    configurationName="value"

For single value configurations without special characters, that's all there is
to know. Settings with multiple values and certain characters require slight
modifications. 

### Multi-Value Settings [](id=multi-value-settings)

Some configuration entries have properties that accept multiple values. For
example, a configuration property for specifying supported file extensions needs
more than one value. Here's how to write a multi-value setting in a `.config`
file: 

    multiValueSetting=["Value 1", "Value 2", ...]

Open the Web Content entry from System Settings and you'll see what looks like
multiple single value entries for *Charactersblacklist*: 

![Figure 3: The Web Content System Settings entry has many *Charactersblacklist* fields.](../../../images/config-web-content-blacklist.png)

In the configuration file, this is really a single key with an array of 
comma-separated values: 

    charactersblacklist=["&","'","@","\\","]","}",":","\=",">","/","<","[","{","%","+","#","`","?","\"",";","*","~"]

### Escaping Characters [](id=escaping-characters)

Double quotes (`"`) and equals signs (`=`) must be *escaped* in `.config` files. 
*Escaping* is using another character to denote that a character shouldn't be used 
in its normal way. Since double quotes and equals signs are already used in 
`.config` files, escaping them tells the framework not to read them the normal 
way, but to pass them through as part of the value. Use a `\` to escape 
characters in the `.config` file: 

    charactersblacklist=["&","\"","\="]

This setting illustrates a multi-value setting with a regular, unescaped 
character (`&`), and two escaped ones (`\"` and `\=`). 

### Deploying a Configuration File [](id=deploying-a-configuration-file)

Once you have a configuration file, deploy it so @product@ recognizes it and 
updates the targeted configuration values. 

To deploy the `.config` file, place it in your 
[Liferay Home's](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home) 
`osgi/configs` folder. To change the configuration further, you can edit the 
`.config` file directly or use System Settings. 

### Typed Values [](id=typed-values)

The `.config` file format supports specifying the type of a configuration value
by inserting a special type marker character. Because @product@ already knows
the correct type for each configuration property, the type characters are only
useful for informational purposes. For example, a configuration with a boolean
type has *B* just before the value to mark it as a boolean type:

    addDefaultStructures=B"true"

If you see type markers in `.config` files (they'll show up under certain
conditions), you can safely ignore them. The example included above functions
identically without the type marker: 

    addDefaultStructures="true"

## Configuration Files and Clustering [](id=configuration-files-and-clustering)

In a clustered environment, each node needs the same configuration values for
each entry. For example, all nodes should use the same *Blogs* configuration
settings. To accomplish this, deploy a `.config` file to *one* node. @product@
uses an internal system for making sure all the nodes in the cluster hear about
the configuration change and apply it.

## Factory Configurations

Some configurable services in @product@ support the concept of *factory
configuration*. In these cases, providing multiple configuration files
guarantees the creation of multiple service instances (each configured by
separate files). This becomes much clearer with an example:

A service has the following configuration property: 

    port="8080"

Maybe this service is useful in most cases, but what if another consumer of the
service is created, and it needs to have the service configured with a different
port specified? Because the first configuration instance is still necessary, you
can't just change the existing `.config` file to 

    port="9080"

What you really need is for both services to exist simultaneously, each with a
different configuration. Creating a second configuration file ensures that both
service instances are available.

Be careful! Any configurable component can be forced into a factory
configuration scenario, but they aren't all designed for it. If a service is
safe for factory configuration, its configuration class is marked for it during
development. You don't have to look in the code though: it's clear in the System
Settings entry for the configuration. The presence of an Add button
(![Add](../../../images/icon-add.png)) in the configuration's System Settings
entry means the service supports factory configuration.

![Figure x: If a System Settings entry has an Add button, it's suitable
for factory configuration.](../../../images/factory-configuration-entry.png)

Once you determine that you need to write a factory configuration file, how do
you do it? It's all in the name. A standard single-instance
configuration file is named like this:

    my.service.ServiceConfiguration.config

If this service supported factory configuration, how would you name it
differently? Liferay uses a convention of calling the first instance of the
configuration `-default.config`:

    my.service.ServiceConfiguration-default.config

The next instance would contain a unique *subname* (something other than
*default*). It's smart to use a descriptive name:

    my.service.ServiceConfiguration-port9080.config

To follow the example described above, the default instance would use
`port="8080` and the `-port9080.config` would specify `port="9080"`.

Keep in mind that all this talk of factory configurations might be more
information than you need. If you are just changing the existing service
configuration, it doesn't matter if it supports factory configuration or not:
just change the configuration through System Settings or a configuration file
(exported from System Settings, as usual).

