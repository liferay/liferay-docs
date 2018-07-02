# Creating Configuration Files [](id=creating-configuration-files)

System Settings provides an
[*Export*](/discover/portal/-/knowledge_base/7-1/system-settings#exporting-and-importing-configurations)
option that becomes available once you modify a configuration entry. Exporting
is the recommended way to create `.config` files: you download a `.config` file
containing the entry's settings in a `key=value` format. @product@ exports an
entry's total available configuration keys and values, even if only one value
was changed. You can export a single configuration entry or the entire set of
modified configurations. 

To avoid a file name conflict, name configuration files using a unique 
identifier. For example, the Journal Service entry, which backs Web Content 
functionality, has this file name: 

    com.liferay.journal.configuration.JournalServiceConfiguration.config

![Figure 1: The Web Content System Settings entry has the back-end ID `com.liferay.journal.configuration.JournalServiceConfiguration`.](../../../../images/config-web-content-entry.png)

## Key/Value Syntax [](id=key-value-syntax)

The syntax for all keys and values in a `.config` file is the same: 

    configurationName="value"

For single value configurations without special characters, that's all there is
to know. Settings with multiple values and certain characters require slight
modifications. 

## Multi-Value Settings [](id=multi-value-settings)

Configuration entries can have properties that accept multiple values. For 
example, a configuration property for specifying supported file extensions needs 
more than one value. Here's how to write a multi-value setting in a `.config` 
file: 

    multiValueSetting=["Value 1","Value 2", ...]

Do not use a space character between values (after the comma). The property
won't be loaded.

Open the Web Content category in System Settings (under the Content section), and
select *Web Content* for the virtual instance scope. You'll see what looks like
multiple single value entries for *Characters Blacklist*: 

![Figure 2: The Web Content System Settings entry has many *Characters Blacklist* fields.](../../../../images/config-web-content-blacklist.png)

In the configuration file, this is really a single key with an array of 
comma-separated values: 

    charactersblacklist=["&","'","@","\\","]","}",":","\=",">","/","<","[","{","%","+","#","`","?","\"",";","*","~"]

## Escaping Characters [](id=escaping-characters)

Double quotes (`"`) and equals signs (`=`) must be *escaped* in `.config` files. 
Escaping is using another character to denote that a character shouldn't be used 
in its normal way. Since double quotes and equals signs are already used in 
`.config` files, escaping them tells the framework not to read them the normal 
way, but to pass them through as part of the value. Use a `\\` to escape 
characters in the `.config` file: 

    charactersblacklist=["&","\"","\="]

This setting illustrates a multi-value setting with a regular, unescaped 
character (`&`), and two escaped ones (`\"` and `\=`). 

Along with the mandatory escaping of double quotes and equals characters, it's
beneficial to escape spaces inside values to avoid problems. In this example, a 
`\\` is used before each space character to ensure it's read and processed 
properly: 

    blacklistBundleSymbolicNames=["Liferay\ Marketplace","Liferay\ Sharepoint\ Connector"]

If you don't escape spaces yourself, the framework adds the backslash for you 
after deployment. 

## Typed Values [](id=typed-values)

The `.config` file format supports specifying the type of a configuration value
by inserting a special type marker character. Because @product@ already knows
the correct type for each configuration property, the type characters are only
useful for informational purposes. For example, a configuration with a boolean
type has *B* just before the value to mark it as a boolean type:

    addDefaultStructures=B"true"

If you see type markers in `.config` files, you can safely ignore them. The
example included above functions identically without the type marker: 

    addDefaultStructures="true"

## Deploying a Configuration File [](id=deploying-a-configuration-file)

Once you have a configuration file, deploy it. It's registered and 
the targeted configuration values are updated automatically. 

To deploy the `.config` file, place it in your 
[Liferay Home's](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home) 
`osgi/configs` folder. To change the configuration further, you can edit the 
`.config` file directly or use System Settings. 

## Configuration Files and Clustering [](id=configuration-files-and-clustering)

In a clustered environment, each node needs the same configuration values for
each entry. For example, all nodes should use the same *Blogs* configuration
settings. To accomplish this, deploy a `.config` file to *one* node.  An
internal system applies the change to all nodes in the cluster. 
