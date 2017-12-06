# Using Files to Configure Module Components [](id=using-files-to-configure-product-modules)

@product@ uses 
[Felix File Install](http://felix.apache.org/documentation/subprojects/apache-felix-file-install.html)
to monitor file system folders for new/updated configuration files, and the
[Felix OSGi implementation](http://felix.apache.org/)
of
[Configuration Admin](http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html)
to let you use files to configure module service components. 

To learn how to work with configuration files, first review 
[Understanding System Configuration Files](/discover/portal/-/knowledge_base/7-0/understanding-system-configuration-files). 

## Configuration File Formats [](id=configuration-file-formats)

There are two different configuration file formats: 

-   `.cfg`: An older, simple format that only supports string values as 
    properties. 
-   `.config`: A format that supports strings, type information, and other
    non-string values in its properties. 

Although @product@ supports both formats, we recommend `.config` files 
for their flexibility. Since `.cfg` file lacks type information, if you want to
store anything but a String, you'll need a properties utility class that casts
the Strings to their proper types (and you must carefully document properties
that aren't Strings). Since `.config` files can include type information, using
them eliminates this need. The articles below provide a detailed explanation of
these file formats, including their syntax: 

-   [Understanding System Configuration Files](/discover/portal/-/knowledge_base/7-0/understanding-system-configuration-files)
-   [Configuration file (`.config`) syntax](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html#configuration-files-config)
-   [Properties file(`.cfg`) syntax](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html#property-files-cfg)

## Naming Configuration Files [](id=naming-configuration-files)

Before you
[create a configuration file](/discover/portal/-/knowledge_base/7-0/understanding-system-configuration-files#creating-configuration-files),
you should determine whether multiple instances of the component can be created, 
or if the component is intended to be a singleton. Follow these steps to make 
that determination:

1.  Deploy the component's module if you haven't done so already. 

2.  In @product@'s UI, go to *Control Panel* &rarr; *Configuration* &rarr; 
    *System Settings*. 

3.  Find the component's settings by searching or browsing for the component. 

4.  If the component's settings page has a section called *Configuration 
    Entries*, you can create multiple instances of the component configured 
    however you like. Otherwise, you should treat the component as a singleton. 

![Figure 1: You can create multiple instances of components whose System Settings page has a *Configuration Entries* section.](../../images/system-settings-page-lists-configuration-entries.png)

*All* configuration file names must start with the component's PID (PID stands
for *persistent identity*) and end with `.config` or `.cfg`. 

For example, this class uses 
[Declarative Services](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity-for-liferay-6-developers#osgi-services-and-dependency-injection-with-declarative-services) 
to define a component:

    package com;
    @Component
    class Foo {}

The component's PID is `com.Foo`. All the component's configuration files must
start with the PID `com.Foo`. 

For each non-singleton component instance you want to create or update with a
configuration, you must use a uniquely named configuration file that starts with
the component's PID and ends with `.config` or `.cfg`. Creating configurations 
for multiple component instances requires that the configuration files use 
different *subnames*. A subname is the part of a configuration file name after 
the PID and before the suffix `.config` or `.cfg`. Here's the configuration file 
name pattern for non-singleton components: 

-   `[PID]-[subname1].config`
-   `[PID]-[subname2].config`
-   etc. 

For example, you could configure two different instances of the component 
`com.Foo` by using configuration files with these names: 

-   `com.Foo-one.config`
-   `com.Foo-two.config`

Each configuration file creates and/or updates an instance of the component 
that matches the PID. The subname is arbitrary--it doesn't have to match a 
specific component instance. This means you can use whatever subname you like. 
For example, these configuration files are just as valid as the two above: 

-   `com.Foo-puppies.config`
-   `com.Foo-kitties.config`

Using the subname `default`, however, is @product@'s convention for configuring 
a component's first instance. The file name pattern is therefore: 

    [PID]-default.config

A singleton component's configuration file must also start with `[PID]` and end
with `.config` or `.cfg`. Here's the common pattern used for singleton component 
configuration file names: 

    [PID].config

When you're done creating a configuration file, you can
[deploy it](/discover/portal/-/knowledge_base/7-0/understanding-system-configuration-files#deploying-a-configuration-file). 

## Resolving Configuration File Deployment Failures [](id=resolving-configuration-file-deployment-failures)

The following `IOException` hints that the configuration file has a syntax
issue: 

    Failed to install artifact: [path to .config or .cfg file]
    java.io.IOException: Unexpected token 78; expected: 61 (line=0, pos=107)

To resolve this, fix the 
[configuration file's syntax](/develop/tutorials/-/knowledge_base/7-0/using-files-to-configure-product-modules#configuration-file-formats). 

Great! Now you know how to configure module components using configuration 
files. 

## Related Articles [](id=related-articles)

[Understanding System Configuration Files](/discover/portal/-/knowledge_base/7-0/understanding-system-configuration-files)
