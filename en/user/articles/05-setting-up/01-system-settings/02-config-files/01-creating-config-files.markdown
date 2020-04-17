---
header-id: creating-configuration-files
---

# Creating Configuration Files

[TOC levels=1-4]

System Settings provides an
[*Export*](/docs/7-2/user/-/knowledge_base/u/system-settings#exporting-and-importing-configurations)
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

## Key/Value Syntax

The syntax for all keys and values in a `.config` file is the same: 

```properties
configurationName="value"
```

For single value configurations without special characters, that's all there is
to know. Settings with multiple values and certain characters require slight
modifications. 

## Multi-Value Settings

Configuration entries can have properties that accept multiple values. For 
example, a configuration property for specifying supported file extensions needs 
more than one value. Here's how to write a multi-value setting in a `.config` 
file: 

```properties
multiValueSetting=["Value 1","Value 2", ...]
```

Do not use a space character between values (after the comma). The property
won't be loaded.

Open the Web Content category in System Settings (under the Content section), and
select *Web Content* for the virtual instance scope. You'll see what looks like
multiple single value entries for *Characters Blacklist*: 

![Figure 2: The Web Content System Settings entry has many *Characters Blacklist* fields.](../../../../images/config-web-content-blacklist.png)

In the configuration file, this is really a single key with an array of 
comma-separated values: 

```properties
charactersblacklist=["&","'","@","\\","]","}",":","\=",">","/","<","[","{","%","+","#","`","?","\"",";","*","~"]
```

## Escaping Characters

Double quotes (`"`) and equals signs (`=`) must be *escaped* in `.config` files. 
Escaping is using another character to denote that a character shouldn't be used 
in its normal way. Since double quotes and equals signs are already used in 
`.config` files, escaping them tells the framework not to read them the normal 
way, but to pass them through as part of the value. Use a backslash to escape 
characters in the `.config` file: 

```properties
charactersblacklist=["&","\"","\="]
```

This setting illustrates a multi-value setting with a regular, unescaped 
character (`&`), and two escaped ones (`\"` and `\=`). 

Along with the mandatory escaping of double quotes and equals characters, it's
beneficial to escape spaces inside values to avoid problems. In this example, a
backslash is used before each space character to ensure it's read and processed
properly: 

```properties
blacklistBundleSymbolicNames=["Liferay\ Marketplace","Liferay\ Sharepoint\ Connector"]
```

If you don't escape spaces yourself, the framework adds the backslash for you 
after deployment. 

## Typed Values

The `.config` file format supports specifying the type of a configuration value
by inserting a special type marker character. Because @product@ already knows
the correct type for each configuration property, the type characters are only
useful for informational purposes. For example, a configuration with a boolean
type has *B* just before the value to mark it as a boolean type:

```properties
addDefaultStructures=B"true"
```

If you see type markers in `.config` files, you can safely ignore them. The
example included above functions identically without the type marker: 

```properties
addDefaultStructures="true"
```

## Deploying a Configuration File

Once you have a configuration file, deploy it. It's registered and 
the targeted configuration values are updated automatically. 

To deploy the `.config` file, place it in your 
[Liferay Home's](/docs/7-2/deploy/-/knowledge_base/d/liferay-home) 
`osgi/configs` folder. To change the configuration further, you can edit the 
`.config` file directly or use System Settings. 

## Configuration Files and Clustering

In a clustered environment, each node needs the same configuration values for
each entry. For example, all nodes should use the same *Blogs* configuration
settings. To accomplish this, deploy copies of the same `.config` file to each node's `[Liferay Home]/osgi/configs` folder.