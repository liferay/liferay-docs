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

Configurations supporting multiple independent occurrences of the same
configuration entry are known as *factory configurations*.

+$$$

**Factory Configuration Example:** @product@ supports the publication of [JAX-WS
and JAX-RS web
services](/develop/tutorials/-/knowledge_base/7-0/jax-ws-and-jax-rs). These
services must use a [CXF
Endpoint](/develop/tutorials/-/knowledge_base/7-0/jax-ws-and-jax-rs#cxf-endpoints),
which is a context path where the web services are deployed and accessible.
Endpoints can be created via factory configuration by navigating to the CXF
Endpoints System Settings Entry (System Settings &rarr; Foundation &rarr; CXF
Endpoints). Using the Add button (![Add](../../../images/icon-add.png)), the
administrator enters the desired configuration values and repeats the process,
adding as many CXF Endpoint configurations as needed. The creation of CXF
Endpoint configurations also creates CXF Endpoints themselves. This is how
factory configuration works. 

$$$

If a service is meant to support factory configuration, its System Settings
entry has an Add button (![Add](../../../images/icon-add.png)).

![Figure x: If a System Settings entry has an Add button, it's suitable
for factory configuration.](../../../images/factory-configuration-entry.png)

As with regular old single-instance configurations, you can accomplish factory
configuration in the System Settings interface (as described in the example
above) or via configuration files. Once you determine that you need to write a
factory configuration file, how do you do it? It's all in the name. A standard
single-instance configuration file is named like this:

    my.service.ServiceConfiguration.config

If this service supported factory configuration, how would you name it
differently? Liferay uses a convention of calling the first instance of the
configuration `-default.config`:

    my.service.ServiceConfiguration-default.config

The next instance would contain a unique *subname* (something other than
*default*). It's smart to use a descriptive name:

    my.service.ServiceConfiguration-port9080.config

To follow the CXF Endpoints example described above, if Liferay's developers had
shipped an initial CXF Endpoint `.config` file with @product@, it would have
been named

    com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration-default.config

Perhaps the `-default.config` configuration specifies a context path for *REST*
web services, and then you create another endpoint with a different context path
for *SOAP* web services. Your second configuration file could be named

    com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration-soap.config

+$$$

**Note:** Some System Settings entries (like the CXF Endpoints entry) don't ship
with a configuration file, so anything you create is the first occurrence.
However, if you configure one and export it to obtain the `.config` file, it's
not named `-default.config`. Instead it's given a guaranteed unique identifier
for its subname, like

    com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration-a6f67e48-6dca-49c6-bf6b-8fd5e6016b2d.config

This is simply to guarantee that the file has a unique name. It's recommended to
rename the exported file to use a more descriptive subname. 

$$$

Now a note of warning. In many cases, configuration files can be used to force a
factory configuration scenario, but not all configurations are designed to be
used this way. It's best to stick to the intended use cases. Use System Settings
as described above to determine if factory configuration is a good idea. If not,
stick to the single occurrence mode of configuration (specifying only one
configuration file for the service). 
