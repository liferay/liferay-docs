# Running the Upgrade Process [](id=running-the-upgrade-process)

Now you're ready to run the upgrade process. It updates the database schema for
the core and your installed modules. Verification processes test the upgrade.
Configured verifications for the core and modules run afterwards, but can be run
manually too. 

Here are the ways to run upgrade processes:

-   **Upgrade everything in one shot**:
    [Use the upgrade tool](/discover/deployment/-/knowledge_base/7-1/running-the-upgrade) to upgrade the core and all the
    modules. 

-   **Upgrade the core and the modules separately**:
    [Use the upgrade tool](/discover/deployment/-/knowledge_base/7-1/running-the-upgrade) (recommended) or
    [Gogo shell](/discover/deployment/-/knowledge_base/7-1/gogo-shell-commands-for-module-upgrades) to upgrade the core. Then use Gogo shell to upgrade each module. 

If you are upgrading from Liferay Portal 6.2 or earlier, it's recommended to use
the upgrade tool to upgrade everything. It's the easiest, most comprehensive
way to upgrade from those versions. Since version 7.0, however, @product@'s
modular framework lets you upgrade modules--even the core--individually.
Focusing first on upgrading the core and your most important modules might be
better for you. The point is, the @product-ver@ upgrade process is flexible. 

**Note:** Liferay enterprise subscribers can use the upgrade tool to execute 
upgrades for fix packs. As of @product@ 7.1, a fix pack's micro upgrade
processes (core database schema micro version changes) are not mandatory. This
means you can install a fix pack (i.e., core code) without having to execute
the database schema micro version changes---you can execute micro version
changes when you want, even outside of a major or minor version upgrade. Before
using the upgrade tool to execute a fix pack's micro upgrade process, however,
you must shut down the server, install the fix pack, and 
[back up the @product@ database, installation, and Document Library store](/discover/deployment/-/knowledge_base/7-1/backing-up-a-liferay-installation). 
