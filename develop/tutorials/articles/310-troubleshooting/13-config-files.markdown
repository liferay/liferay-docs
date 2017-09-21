# Using Files to Configure @product@ Modules

@product@ lets you configure modules via the following: 

-   [Felix Configuration Admin](http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html): 
    Lets you use configuration files to configure service components. These 
    files contain the configuration as a list of name-value pairs. 

-   [Felix File Install](http://felix.apache.org/documentation/subprojects/apache-felix-file-install.html): 
    Monitors a folder in the file system for new/updated modules and 
    configuration files. 

You can use File Install in conjunction with Configuration Admin to deploy 
configuration files for your modules. For example, you can set File Install to 
monitor a specific folder, and then deploy a configuration file to that folder. 
Since @product@ runs on the 
[Felix OSGi implementation](http://felix.apache.org/), 
Configuration Admin and File Install are available out-of-the-box. 

## Configuration File Formats

There are two different file formats you can use for configuration files: 

-   `.cfg`: An older, simple format that only supports string values as 
    properties. 
-   `.config`: A format that supports type information and other non-string 
    values in its properties, in addition to strings. 

Although @product@ supports both formats, we recommend you use `.config` files 
for their flexibility. A `.cfg` file lacks type information and usually needs a 
properties utility class with functions like `getInt(String)`, 
`getDouble(String)`, and so on. Since `.config` files can include type 
information, using them eliminates this need. For a detailed explanation of 
these file formats, including their syntax, see the following: 

-   [Understanding System Configuration Files](/discover/portal/-/knowledge_base/7-0/understanding-system-configuration-files)
-   [Configurations](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html#configuration-files-config)

## Managed Services vs Managed Service Factories
 <!-- Do we have an article on managed service and managed service factories?
 
 How to know whether a service is a singleton or managed by a factory? Is it the service's cardinality attribute?
  -->
 
With configuration files, you can define configurations for the following: 

-   **Managed Service:** A service that implements the interface 
    `org.osgi.service.cm.ManagedService`. Since only one such service instance 
    can exist, a managed service is a singleton that provides a module with 
    a single configuration, and corresponds to a single `Configuration` object 
    in Configuration Admin. 
    [Click here](http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html#managedservice-example) 
    to see an example of a managed service. 

-   **Managed Service Factory:** A service that implements the interface 
    `org.osgi.service.cm.ManagedServiceFactory`. Many such service instances can 
    exist, as well as their corresponding `Configuration` objects in 
    Configuration Admin, thus providing a module with multiple configurations. 
    [Click here](http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html#managedservicefactory-example)
    to see an example of a managed service factory. 

Note that configuration file names differ between a managed service and a 
managed service factory. For a managed service, use the file name form 
`<PID>.config` or `<PID>.cfg` (PID stands for *persistent identity*). For a 
managed service factory, use the file name form `<PID>-<subname>.config` or 
`<PID>-<subname>.cfg`. @product@'s convention for a managed service's default 
configuration is `<PID>-default.config`. 

For example, the configuration file `com.foo.bar.config` specifies a 
configuration for a managed service with the PID `com.foo.bar`. Likewise, the 
configuration file `com.foo.bar-default.config` specifies a default 
configuration for a managed service factory with the PID `com.foo.bar`. 

[Click here](http://enroute.osgi.org/services/org.osgi.service.cm.html) 
for more detailed information about Configuration Admin, managed services, and 
managed service factories. 

## Resolving OSGi Configuration Admin File Deployment Failures

Deploying a `.cfg` or `.config` file can sometimes fail with an `IOException`: 

    Failed to install artifact: [path to .config or .cfg file]
    java.io.IOException: Unexpected token 78; expected: 61 (line=0, pos=107)

To resolve this, make sure the configuration file uses the suffix (`.config` or 
`.cfg`) the DXP instance expects. To remedy, match the suffix other 
configuration files in [Liferay HOME]/osgi/configs use.
