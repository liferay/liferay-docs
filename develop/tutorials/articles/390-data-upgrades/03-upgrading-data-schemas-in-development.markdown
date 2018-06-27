# Upgrading Data Schemas in Development [](id=upgrading-data-schemas-in-development)

As you develop modules, you might need to iterate through several database 
schema changes. Before you release new module versions with your finalized
schema changes, you must create a formal 
[data upgrade process](/develop/tutorials/-/knowledge_base/7-1/creating-an-upgrade-process-for-your-app). 
Until then, you can use the Build Auto Upgrade feature to test schema changes on 
the fly. 

+$$$

**Note**: In Liferay Portal 6.x Service Builder portlets, the 
`build.auto.upgrade` property in `service.properties` applies Liferay Service 
schema changes upon rebuilding services and redeploying the portlets. As of 
@product-ver@, this property is deprecated. 

The Build Auto Upgrade feature is now in a global property
`schema.module.build.auto.upgrade` in the file
`[Liferay_Home]/portal-developer.properties`. 

$$$

Setting the global property `schema.module.build.auto.upgrade` to `true` applies 
module schema changes for redeployed modules whose service build numbers have 
incremented. The `build.number` property in the module's `service.properties` 
file indicates the service build number. Build Auto Upgrade executes schema 
changes without massaging existing data. It leaves data empty for created 
columns, drops data from deleted and renamed columns, and orphans data from 
deleted and renamed tables. 

Although Build Auto Upgrade updates databases quickly and automatically, it
doesn't guarantee a proper data upgrade--you implement that via 
[data upgrade processes](/develop/tutorials/-/knowledge_base/7-1/creating-an-upgrade-process-for-your-app).
Build Auto Upgrade is for development purposes only. 

+$$$

**WARNING**: DO NOT USE the Build Auto Upgrade feature in production. @product@ 
DOES NOT support Build Auto Upgrade in production. Build Auto Upgrade is for 
development purposes only. Enabling it in production can result in data loss and 
improper data upgrade. In production environments, leave the property 
`schema.module.build.auto.upgrade` in `portal-developer.properties` set to 
`false`. 

$$$

By default, `schema.module.build.auto.upgrade` is set to `false`. On any 
module's first deployment, the module's tables are generated regardless of the 
`schema.module.build.auto.upgrade` value. 

The following table summarizes Build Auto Upgrade's handling of schema changes: 

Schema Change | Result | 
:------------ | :----------- | 
Add column    | Create a new empty column. |
Rename column | Drop the existing column and delete all its data. Create a new empty column. |
Delete column | Drop the existing column and delete all its data. |
Create or rename a table in @product@'s built-in data source. | Orphan the existing table and all its data. Create the new table. |

Great! Now you know how to use the Build Auto Upgrade developer feature. 

## Related Topics [](id=related-topics)

[Creating Data Upgrade Process for Modules](/develop/tutorials/-/knowledge_base/7-1/creating-an-upgrade-process-for-your-app)
