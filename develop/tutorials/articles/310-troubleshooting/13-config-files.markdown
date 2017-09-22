# Using Files to Configure @product@ Modules

@product@ lets you use files to configure module service components. It uses the
[Felix OSGi implementation](http://felix.apache.org/)
of
[Configuration Admin](http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html)
to support using files to configure components and
[Felix File Install](http://felix.apache.org/documentation/subprojects/apache-felix-file-install.html)
to monitor file system folders for new/updated configuration files. 

Using configuration files involves these things:

-   [Understanding configuration files](/discover/portal/-/knowledge_base/7-0/understanding-system-configuration-files)
-   [Choosing a file format](#configuration-file-formats)
-   [Naming and creating configuration files](#naming-configuration-files)
-   [Resolving deployment failures](#resolving-configuration-file-deployment-failures)

## Configuration File Formats [](id=configuration-file-formats)

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
-   [Configuration file (`.config`) syntax](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html#configuration-files-config)
-   [Properties file(`.cfg`) syntax](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html#property-files-cfg)

## Naming Configuration Files [](id=naming-configuration-files)

Before you
[create a configuration file](/discover/portal/-/knowledge_base/7-0/understanding-system-configuration-files#creating-and-deploying-a-configuration-file),
you should determine whether multiple instances of the component can be created
or whether the component is intended to be a singleton. Follow these steps to
make that determination:

1.  Deploy the component's module if you haven't done so already. 
2.  In the @product@ UI, go to *Control Panel* &rarr; *Configuration* &rarr;
    *System Settings*. 
3.  Find the component's settings by searching or browsing for it. 
4.  If the component's settings page has a section called *Configuration
    Entries*, you can create multiple instances of the component configured
    however you like. Otherwise, the component should be treated as a singleton.

![Figure 1.0: You can create multiple instances of components whose System Settings page has a *Configuration Entries* section.](../../images/system-settings-page-lists-configuration-entries.png)

*All* configuration file names must start with the component's PID (PID stands
for *persistent identity*) and end with `.config` or `.cfg`. 

For example, this class uses Declarative Services to define a component:

    package com;
    @Component
    class Foo {}

The component's PID is `com.Foo`. All the component's configuration files must
start with the PID `com.Foo`.

For each non-singleton component instance you want to create or update with a
configuration, you must use a uniquely named configuration file that starts with
the component's PID and ends with `.config` or `.cfg`. 

Creating configurations for multiple component instances requires the
configuration files use different *subnames*. A subname is the part of a
configuration file name after the PID and before the suffix `.config` or `.cfg`.
Here is the configuration file name pattern for non-singleton components:

-   `[PID]-[subname1].config`
-   `[PID]-[subname2].config`
-   etc. 

For example, you could configure two different instances of component `com.Foo`
using configuration files with these names:

-   `com.Foo-one.config`
-   `com.Foo-two.config`

Each configuration file creates and / or updates an instance of the component
that matches the PID and subname. 

To create a component's first instance, it's common to use a configuration file
name that follows this pattern: `[PID]-default.properties`. 

A singleton component's configuration file must also start with `[PID]` and end
with `.config` or `.cfg`. Here is the common pattern used for singleton
component configuration file names: 

    [PID].config

When you're done creating a configuration file, you can
[deploy](/discover/portal/-/knowledge_base/7-0/understanding-system-configuration-files#deploying-a-configuration-file)
it. 

## Resolving Configuration File Deployment Failures [](id=resolving-configuration-file-deployment-failures)

The following `IOException` hints that the configuration file has a syntax
issue: 

    Failed to install artifact: [path to .config or .cfg file]
    java.io.IOException: Unexpected token 78; expected: 61 (line=0, pos=107)

To resolve this, fix the [configuration's syntax](#configuration-file-formats). 

You now know how to configure module components using configuration files. 

## Related Articles [](id=related-articles)

-   [Understanding System Configuration Files](/discover/portal/-/knowledge_base/7-0/understanding-system-configuration-files)
