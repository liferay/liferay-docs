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

## Multiple Configuration Files

The Apache Felix Configuration Admin framework supports multiple configuration
files per component. Such situations are called *factory configurations* because
providing multiple configuration files guarantees the creation of multiple
instances of the configured service. To avoid naming conflicts, a subname is
included in each file name. @product@'s naming convention uses `-default` as the
subname for the first instance. Any additional instances can use whatever
subname you like. There are a few default configurations shipped with @product@.
Here's an example:

    com.liferay.portal.security.auth.verifier.basic.auth.header.module.configuration.BasicAuthHeaderAuthVerifierConfiguration-default.cfg

Because this configuration supports factory configuration, its initial instance
uses the `-default` subname. To change the configuration of a `*-default.config`
file, make the changes as you would for any other `*.config` file. Do not create
a new configuration file yourself if you simply want to make a configuration
change. This will create a new service instance, and that decision is best left
to the developer of the service.

What if you must configure a service, and you find that there are already
mutliple configuration files present in the `Liferay_Home/osgi/configs` folder?
For example:

    my.service.ServiceConfiguration-default.config
    my.service.ServiceConfiguration-alternate.config

If it's not clear which one to modify, consult the creator of the service
configurations to determine which service instance is used for your situation.

Now you know the basics of configuring @product@ using portable, deployable
configuration files. 
