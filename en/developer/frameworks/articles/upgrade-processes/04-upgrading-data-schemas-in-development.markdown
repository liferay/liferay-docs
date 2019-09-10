---
header-id: upgrading-data-schemas-in-development
---

# Upgrading Data Schemas in Development

[TOC levels=1-4]

As you develop modules, you might need to iterate through several database 
schema changes. Before you release new module versions with your finalized 
schema changes, you must create a formal 
[data upgrade process](/docs/7-2/frameworks/-/knowledge_base/f/creating-an-upgrade-process-for-your-app). 
Until then, you can use the Build Auto Upgrade feature to test schema changes on 
the fly. 

Follow these steps to use the Build Auto Upgrade feature to test schema changes 
in development:

1.  Create a `portal-ext.properties` file in your app server's 
    `[Liferay_Home]/` folder if it doesn't already exist.
    
2.  [Enable Developer Mode in your app server](/docs/7-2/frameworks/-/knowledge_base/f/using-developer-mode-with-themes) 
    by adding the following line to the properties file:
    
    ```properties
    include-and-override=portal-developer.properties;
    ```
    
    The Build Auto Upgrade feature is a global property 
    `schema.module.build.auto.upgrade` in the file 
    `[Liferay_Home]/portal-developer.properties`, so enabling Developer Mode 
    automatically enables this property as well. 

    Alternatively, if you prefer not to enable all the 
    [other properties included in Developer Mode](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-impl/src/portal-developer.properties), 
    you can just add the `schema.module.build.auto.upgrade` property to your 
    `portal-ext.properties` file and set it to `true`:
    
    ```properties
    schema.module.build.auto.upgrade = true;
    ```

Setting the global property `schema.module.build.auto.upgrade` to `true` applies 
module schema changes for redeployed modules whose service build numbers have 
incremented. The `build.number` property in the module's `service.properties` 
file indicates the service build number. Build Auto Upgrade executes schema 
changes without massaging existing data. It leaves data empty for created 
columns, drops data from deleted and renamed columns, and orphans data from 
deleted and renamed tables. 

Although Build Auto Upgrade updates databases quickly and automatically, it 
doesn't guarantee a proper data upgrade--you implement that via 
[data upgrade processes](/docs/7-2/frameworks/-/knowledge_base/f/creating-an-upgrade-process-for-your-app). 
Build Auto Upgrade is for development purposes only. 

| **WARNING**: DO NOT USE the Build Auto Upgrade feature in production. @product@
| DOES NOT support Build Auto Upgrade in production. Build Auto Upgrade is for
| development purposes only. Enabling it in production can result in data loss and
| improper data upgrade. In production environments, leave the property
| `schema.module.build.auto.upgrade` in `portal-developer.properties` set to
| `false`.

By default, `schema.module.build.auto.upgrade` is set to `false`. On any 
module's first deployment, the module's tables are generated regardless of the 
`schema.module.build.auto.upgrade` value. 

The table below summarizes Build Auto Upgrade's handling of schema changes:

Schema Change | Result | 
:------------ | :----------- | 
Add column    | Create a new empty column. |
Rename column | Drop the existing column and delete all its data. Create a new empty column. |
Delete column | Drop the existing column and delete all its data. |
Create or rename a table in @product@'s built-in data source. | Orphan the existing table and all its data. Create the new table. |

Great! Now you know how to use the Build Auto Upgrade developer feature. 

## Related Topics

[Creating Data Upgrade Process for Modules](/docs/7-2/frameworks/-/knowledge_base/f/creating-an-upgrade-process-for-your-app)
