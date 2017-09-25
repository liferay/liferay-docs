# Understanding System Configuration Files [](id=understanding-system-configuration-files)

To make system-scoped configuration changes and set default configurations for 
other 
[scopes](/discover/portal/-/knowledge_base/7-0/configuring-liferay#configuration-scope) 
in @product@, use the 
[System Settings app](/discover/portal/-/knowledge_base/7-0/system-settings). 
This app's *Export* option becomes available once you modify a configuration 
entry. This lets you download a `.config` file with all of the settings in a 
`key=value` format. Export is available for either a single configuration entry 
or the entire set of modified configurations in @product@. 

If you don't have direct access to System Settings, or if you need portable
configurations that can be deployed to multiple @product@ systems quickly and
easily, configure @product@ with an OSGi configuration file. 

There are several things to understand about configuration files: 

-   They're part of the
    [Apache Felix Configuration Admin](http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html)
    framework. Find out more about their syntax
    [here](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html#configuration-files-config). 
-   The `.config` file format supports richer encoding than `.cfg` files, which 
    allow only strings. 
-   If the System Settings app's default values are desirable, no configuration
    file is necessary. 
-   System Settings exports all configuration keys and values for an entry,
    even if only one value has been modified. 
-   If you place a configuration file in your @product@ installation's 
    `osgi/configs` folder, any changes you make to the entry in System Settings
    are automatically propagated to the configuration file. 
-   @product@ uses
    [Felix File Install](http://felix.apache.org/documentation/subprojects/apache-felix-file-install.html)
    to dynamically apply configuration files to your @product@ instance. 

+$$$

**Note:** Because the `.config` file supports types, special type marker 
characters are inserted into the files under certain conditions. For example, a
configuration with a boolean type might have a `B` just before the value to mark
the boolean type. The type markers aren't necessary or problematic--you can 
safely ignore them. @product@ doesn't rely on the configuration marker to 
determine what type the configuration is, because the backend code that creates 
the setting already knows what the type must be. In short, a `.config` file with 
`addDefaultStructures=B"true"` functions no differently than one with 
`addDefaultStructures="true"`. 

$$$

## Creating and Deploying a Configuration File [](id=creating-and-deploying-a-configuration-file)

Configuration files consist of two things: 

1.  A file name. 
2.  One or more key/value pairs, with the value enclosed in double quotes: 

        configurationName="value"

Components have configuration objects that end in `*Configuration`. The object's 
ID is the component name followed by `Configuration`. 

For example, here's the backend ID for the *Web Content* configuration entry:
    
    com.liferay.journal.configuration.JournalServiceConfiguration

The configuration file name must use the ID as its prefix and `.config` as its 
suffix. So the proper way to configure the Web Content entry with a 
configuration file is to create this file: 

    com.liferay.journal.configuration.JournalServiceConfiguration.config

![Figure 1: The Web Content System Settings entry has the backend ID `com.liferay.journal.configuration.JournalServiceConfiguration`.](../../../images/config-web-content-entry.png)

### Finding the Configuration ID [](id=finding-the-configuration-id)

The configuration's backend ID isn't intuitive from the reader-friendly entry 
name you see in the System Settings UI. For example, here's the Web Content 
entry's ID: 

    com.liferay.journal.configuration.JournalServiceConfiguration

The easiest way to find the ID is to make a change in the System Settings entry, 
then 
[export the configuration file](/discover/portal/-/knowledge_base/7-0/system-settings#exporting-and-importing-configurations). 
The exported configuration file is named properly and has all the entry's keys
and their current values. 

### Key/Value Syntax [](id=key-value-syntax)

The general syntax for all keys and values is the same: 

    configurationName="value"

For single value configurations without special characters, that's all there is
to know. Settings with multiple values and certain characters require slight
modifications. 

### Multi-Value Settings [](id=multi-value-settings)

Open the Web Content entry from System Settings and you'll see what looks like
multiple single value entries for *Charactersblacklist*: 

![Figure 2: The Web Content System Settings entry has many *Charactersblacklist* fields.](../../../images/config-web-content-blacklist.png)

In the configuration file, this is really a single key with an array of 
comma-separated values: 

    charactersblacklist=["&","'","@","\\","]","}",":","\=",">","/","<","[","{","%","+","#","`","?","\"",";","*","~"]

In general, here's how to write a multi-value setting: 

    multiValueSetting=["Value 1", "Value 2", ...]

### Escaping Characters [](id=escaping-characters)

According to the 
[Apache Sling documentation](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html#configuration-files-config), 
double quotes (`"`) and equals signs (`=`) must be *escaped* in `.config` files. 
Escaping is using another character to denote that a character shouldn't be used 
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

Great! Now you know the basics of configuring @product@ using portable, 
deployable configuration files. 
