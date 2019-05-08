---
header-id: upgrading-the-product-data
---

# Upgrading the @product@ Data

[TOC levels=1-4]

Now you're ready to upgrade the @product@ data. The upgrade processes update the
database schema for the core and your installed modules. Verification processes
test the upgrade. Configured verifications for the core and modules run
afterwards, but can be run manually too. 

Here are the ways to upgrade:

-   **Upgrade everything in one shot**:
    Use the upgrade tool to upgrade the core and all the modules. 

-   **Upgrade the core and the modules separately**:
    Use the upgrade tool (recommended) or
    [Gogo shell](/docs/7-2/deploy/-/knowledge_base/d/upgrading-modules-using-gogo-shell) to upgrade the core. Then use Gogo shell to upgrade each module. 

If you are upgrading from Liferay Portal 6.2 or earlier, use the upgrade tool to
upgrade everything. It's the easiest, most comprehensive way to upgrade from
those versions. Since version 7.0, however, @product@'s modular framework lets
you upgrade modules---even the core---individually. A helpful practice for large
databases is to focus first on upgrading the core and your most important
modules; then back up your database before continuing upgrades. Upgrading is
a flexible process that adjusts to your preferences.

| **Note:** Liferay enterprise subscribers can use the upgrade tool to execute 
| upgrades for fix packs. Since @product@ 7.1, a fix pack's micro upgrade
| processes in the core (database schema micro version changes) are not
| mandatory. This means you can install a fix pack (i.e., core code) without
| having to execute the database schema micro version changes. You can execute
| micro version changes when you want, even outside of major or minor version
| upgrades. Before using the upgrade tool to execute a fix pack's micro upgrade
| process, however, you must shut down the server, install the fix pack, and 
| [back up the @product@ database, installation, and Document Library store](/docs/7-2/deploy/-/knowledge_base/d/backing-up-a-liferay-installation). 
| 
| Module micro database schema version changes in fix packs execute
| automatically on server startup unless the
| [`autoUpgrade` setting](/docs/7-2/deploy/-/knowledge_base/d/configuring-the-data-upgrade)
| is `false` (the default is `true`). 
