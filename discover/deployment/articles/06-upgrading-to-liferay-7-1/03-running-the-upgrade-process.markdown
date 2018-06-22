# Running the Upgrade Process [](id=running-the-upgrade-process)

Now you're ready to run the upgrade process. It updates the database schema for
the core and your installed modules. Verification processes test the upgrade.
Configured verifications for the core and modules run afterwards, but can be run
manually too. 

Here are the ways to run upgrade processes:

-   **Upgrade everything in one shot**:
    [Use the upgrade tool](#running-the-upgrade) to upgrade the core and all the
    modules. 

-   **Upgrade the core and the modules separately**:
    [Use the upgrade tool](#running-the-upgrade) (recommended) or
    [Gogo shell](#gogo-shell-commands-for-module-upgrades) to upgrade the core. Then use Gogo shell to upgrade each module. 

If you are upgrading from Liferay Portal 6.2 or earlier, it's recommended to use
the  upgrade tool to upgrade everything. It's the easiest, most comprehensive
way to upgrade from those versions. Since version 7.0, however, @product@'s
modular framework lets you upgrade modules--even the core--individually.
Focusing first on upgrading the core and your most important modules might be
better for you. The point is, @product-ver@ upgrade process is flexible. 

## Running the Upgrade Tool [](id=running-the-upgrade)

The upgrade tool provides the easiest way to upgrade the core and installed
modules. You can configure the upgrade from files or inside the tool's command
line interface. The upgrade tool lets you upgrade everything--the core and all
the modules--together or separately. 

@product-ver@ bundles include the upgrade tool. If you installed @product-ver@
manually, you can download the upgrade tool separately from the same location on
[liferay.com](https://www.liferay.com/). 

To upgrade only the core, add a file called
`com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration.cfg`
to the `[Liferay Home]/osgi/configs` folder with the following content:

    autoUpgrade=false

This configuration prevents automatic module upgrade, but causes the upgrade
tool to open a Gogo shell after finishing the core upgrade. 

The upgrade tool resides in the `[Liferay
Home]/tools/portal-tools-db-upgrade-client` folder. 

This command starts the upgrade tool: 

    java -jar com.liferay.portal.tools.db.upgrade.client.jar

+$$$

**Warning**: To prevent the tool's expanded command from growing too large for
Windows, execute the initial command in the
`com.liferay.portal.tools.db.upgrade.client.jar` file's folder.

$$$

Here are the tool's default Java parameters:
    
    -Dfile.encoding=UTF8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx2048m 

The `-j` option lets you override the JVM parameters. For example, these options
set the JVM memory to 10GB, which is a good starting point for this process
type:

    java -jar com.liferay.portal.tools.db.upgrade.client.jar -j "-Dfile.encoding=UTF8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx10240m"

The `-l` option lets you specify the tool's log file name: 

    java -jar com.liferay.portal.tools.db.upgrade.client.jar -l "output.log"

Here are all the upgrade tool command line options:

**--help** or **-h**: Prints the tool's help message.

**--jvm-opts** or **-j** + **[arg]**: Sets any JVM options for the upgrade process.

**--log-file** or **-l** + **[arg]**: Specifies the tool's log file name.

**--shell** or **-s**: Automatically connects you to the Gogo shell after
finishing the upgrade process.

### Configuring the Upgrade [](id=configuring-the-core-upgrade)

The core upgrade requires configuration. You can configure it at runtime via the
command line interface or pre-configure it in these files:

-   `app-server.properties`: Specifies the server's location and libraries.
-   `portal-upgrade-database.properties`: Configures the database connection.
-   `portal-upgrade-ext.properties`: Sets the rest of the portal properties that
    the upgrade requires. You might want to copy your current portal properties
    (except your database properties) into this file. Before copying your
    current properties, make sure you've
    [updated the portal properties for @product-ver@](/discover/portal/-/knowledge_base/7-1/preparing-an-upgrade-to-liferay-7#step-4-update-your-portal-properties). 

Each file's properties are described next. 

#### Configuring app-server.properties [](id=configuring-app-server-properties)

Specify the following information to configure the app server on which @product-ver@ is installed: 

**dir:**  the application server directory *(required)*

**extra.lib.dirs:**  a comma delimited list of extra directories containing any
binaries or resources to add to the  class path *(required)*

**global.lib.dir:**  the application server's global library directory 
*(required)*

**portal.dir:**  the directory where portal is installed in your app server
*(required)*

#### Configuring portal-upgrade-database.properties [](id=configuring-portal-upgrade-database-properties)

Specify the following information to configure the database you're upgrading.
Note that these properties correspond exactly to the
[JDBC portal properties](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#JDBC)
you'd use in a `portal-ext.properties` file. 

**jdbc.default.driverClassName ***(required)*

**jdbc.default.url ***(required)*

**jdbc.default.username ***(required)*

**jdbc.default.password ***(required)*

#### Configuring portal-upgrade-ext.properties [](id=configuring-portal-upgrade-ext-properties)

Specify the following information to configure the upgrade itself: 

**liferay.home:** the [Liferay home folder](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home) *(required)*

**hibernate.jdbc.batch_size:** the JDBC batch size used to improve performance;
set to *250* by default *(optional)* 

#### Example Upgrade Configuration [](id=example-upgrade-configuration)

You can either configure the upgrade via property files or via the command line interface. 

Here's an example interaction with the upgrade tool's command line interface:

    Please enter your application server (tomcat): 
    tomcat
    Please enter your application server directory (../../tomcat-8.0.32): 

    Please enter your extra library directories (../../tomcat-8.0.32/bin): 

    Please enter your global library directory (../../tomcat-8.0.32/lib): 

    Please enter your portal directory (../../tomcat-8.0.32/webapps/ROOT): 

    [ db2 mariadb mysql oracle postgresql sqlserver sybase ]
    Please enter your database (mysql): 
    mariadb
    Please enter your database host (localhost):

    (etc.)

The command line interface creates the configuration files based on your input.
If you want to set all of this up ahead of time, however, you'll want to put
this information into configuration files.

Here are example upgrade configuration files that you can customize: 

-   `app-server.properties`:

    	dir=../../tomcat-8.0.32
    	global.lib.dir=/lib
    	portal.dir=/webapps/ROOT
    	server.detector.server.id=tomcat
    	extra.lib.dirs=/bin

-   `portal-upgrade-database.properties`:

        jdbc.default.url=jdbc:mysql://lportal62?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
        jdbc.default.driverClassName=com.mysql.jdbc.Driver
        jdbc.default.username=root
        jdbc.default.password=

-   `portal-upgrade-ext.properties`:

        liferay.home=/home/user/servers/liferay7
        module.framework.base.dir=/home/user/servers/liferay7/osgi

The upgrade tool first executes the core's upgrade processes and verifiers.

If the upgrade tool's `autoUpgrade` property is set to `true` (the default
setting), upgrade processes for all installed modules are run too. 

If you set `autoUpgrade=false` in a file called
`com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration.cfg`
and copy the file into the `[Liferay Home]/osgi/configs` folder, the upgrade tool opens Gogo shell
automatically after the core upgrade.

The Gogo shell lets you upgrade modules, check module upgrade status, and verify
upgrades.  Read on to learn how to use Gogo shell commands to use the
upgrade-related commands. 

## Gogo shell commands for module upgrades [](id=gogo-shell-commands-for-module-upgrades)

@product@'s Gogo shell commands let you upgrade modules, check module status, or
execute verify processes.

+$$$

**Note**: [Configuring the core upgrade](#configuring-the-core-upgrade)
is required before using Gogo shell commands to upgrade the core. 

$$$

If you ran the upgrade tool and it opened Gogo shell, you're already connected.
Otherwise, you can connect to Gogo shell via telnet:

`telnet localhost 11311`

Here are the commands available in the `upgrade` namespace:

**exit** or **quit:** exits the Gogo shell

**upgrade:help:** displays upgrade commands

**upgrade:check:** list upgrades pending to execute because they failed in 
the past or the module hasn't reached its final version

**upgrade:execute {module_name}:** executes upgrades for that module

**upgrade:executeAll:** executes all pending module upgrade processes

**upgrade:list:** lists all registered upgrades

**upgrade:list {module_name}:** lists the module's required upgrade steps

**upgrade:list | grep Registered:** lists registered upgrades and their versions

**verify:help:** displays verify commands

**verify:check {module_name}:** lists the latest execution result for the
module's verify process

**verify:checkAll:** lists the latest execution results for all verify processes

**verify:execute {module_name}:** executes the module's verifier

**verify:executeAll:** executes all verifiers

**verify:list:** lists all registered verifiers

There are many useful
[Liferay commands and standard commands available in Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell).
The following sections describe Liferay upgrade commands. 

### Listing module upgrade processes [](id=listing-module-upgrade-processes)

Before upgrading modules, you should find which have unresolved dependencies,
which are resolved and available to upgrade, and examine the module upgrade
processes. 

Executing `upgrade:list` in the Gogo shell lists the modules whose upgrade
dependencies are satisfied. These modules can be upgraded. 

If a module is active but not listed, its dependencies need to be upgraded. The
Gogo shell command `scr:info [upgrade_step_class_qualified_name]` shows the
upgrade step class's unsatisfied dependencies. Here's an example `scr:info`
command:

    scr:info com.liferay.journal.upgrade.JournalServiceUpgrade

Invoking `upgrade:list [module_name]` lists the module's upgrade processes, in
no particular order.  For example, executing `upgrade:list
com.liferay.bookmarks.service` (for the Bookmarks Service module), lists this:

    Registered upgrade processes for com.liferay.bookmarks.service 1.0.0
            {fromSchemaVersionString=0.0.0, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.portal.spring.extender.internal.context.ModuleApplicationContextExtender$ModuleApplicationContextExtension$1@6e9691da}
            {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0-step-3, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradePortletId@5f41b7ee}
            {fromSchemaVersionString=1.0.0-step-1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradePortletSettings@53929b1d}
            {fromSchemaVersionString=1.0.0-step-2, toSchemaVersionString=1.0.0-step-1, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradeLastPublishDate@3e05b7c8}
            {fromSchemaVersionString=1.0.0-step-3, toSchemaVersionString=1.0.0-step-2, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradeClassNames@6964cb47}

An application's upgrade step class names typically reveal their intention. For
example, the example's `com.liferay.bookmarks.upgrade.v1_0_0.UpgradePortletId`
upgrade step class updates the app's portlet ID. The other example upgrade step
classes update class names, the `LastPublishDate`, and `PortletSettings`.  The
example's step from `0.0.0` to `1.0.0` upgrades the module from an empty
database.

To examine a module's upgrade process better, you can sort the listed upgrade
steps mentally or in a text editor. Here's the upgrade step order for a
Bookmarks Service module to be upgraded from Liferay Portal 6.2 (the module's
database exists) to schema version `1.0.0`: 

- `0.0.1` to `1.0.0-step-3`
- `0.0.1-step-3` to `1.0.0-step-2`
- `0.0.1-step-2` to `1.0.0-step-1`
- `0.0.1-step-1` to `1.0.0`

The overall module upgrade process starts at version `0.0.1` and finishes at version
`1.0.0`. The first step starts on the initial version (`0.0.1`) and finishes on
the target version's highest step (`step-3`). The last step starts on the target
version's lowest step (`step-1`) and finishes on the target version (`1.0.0`). 

Once you understand the module's upgrade process, you can execute it with
confidence. 

### Executing module upgrades [](id=executing-module-upgrades)

Executing `upgrade:execute [module_name]` upgrades the module. You might run
into upgrade errors that you must resolve. Executing the command again starts
the upgrade from the last successful step. 

You can check upgrade status by executing `upgrade:list [module_name]`. For
example, entering `upgrade:list com.liferay.iframe.web` outputs this:

    Registered upgrade processes for com.liferay.iframe.web 0.0.1
	   {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.iframe.web.upgrade.IFrameWebUpgrade$1@1537752d}

The first line lists the module's name and current version. The example module's
current version is `0.0.1`. The `toSchemaVersionString` value is the target
version. 

Executing `upgrade:list [module_name]` on the module after successfully
upgrading it shows the module's name followed by the version you targeted. 

For example, if you successfully upgraded `com.liferay.iframe.web` to version
`1.0.0`, executing `upgrade:list com.liferay.iframe.web` shows the module's
version is `1.0.0`:

    Registered upgrade processes for com.liferay.iframe.web 1.0.0
	   {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.iframe.web.upgrade.IFrameWebUpgrade$1@1537752d}

For module upgrades that don't complete, you can check their status and resolve
their issues. 

### Checking upgrade status [](id=checking-the-upgrade-status)

It's good to know things still need upgrading and why. You might have forgotten
to upgrade a module or its upgrade failed. In any case, it's important to know
where your upgrade stands. 

The command `upgrade:check` lists modules that have impending upgrades. 

For example, if module  `com.liferay.dynamic.data.mapping.service` failed in a
step labeled `1.0.0-step-2`. Executing `upgrade:check` shows this: 

    Would upgrade com.liferay.dynamic.data.mapping.service from 1.0.0-step-2 to
    1.0.0 and its dependent modules

Modules often depend on other modules to complete upgrading. Executing `scr:info
[upgrade_step_class_qualified_name]` shows the upgrade step class's
dependencies. You must upgrade dependency modules to successfully upgrade
dependent modules. 

To resolve and activate a module, its upgrade must complete. The
[Apache Felix Dependency Manager](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager/tutorials/leveraging-the-shell.html)
Gogo shell command `dm wtf` reveals unresolved dependencies. If your module
requires a certain data schema version (e.g., its `bnd.bnd` specifies
`Liferay-Require-SchemaVersion: 1.0.2`) but the module hasn't completed upgrade
to that version, `dm wtf` shows that the schema version is not registered. 

    1 missing dependencies found.
    -------------------------------------
    The following service(s) are missing:
     * com.liferay.portal.kernel.model.Release (&(release.bundle.symbolic.name=com.liferay.journal.service)(release.schema.version=1.0.2)) is not found in the service registry

The `dm wtf` command can also help detect errors in portlet definitions and
custom portlet `schemaVersion` fields. 

Browsing the @product@ database `Release_` table can help you determine a
module's upgrade status too. The core's `servletContextName` field value is
`portal`. If the core's `schemaVersion` field matches your new @product@ version
(e.g., `7.1.1` for Liferay Portal CE GA2) and the `verified` field is `1`
(true), the core upgrade completed successfully. 

Each module has one `Release_` table record, and the value for its
`schemaVersion` field must be `1.0.0` or greater (`1.0.0` is the initial version
for @product-ver@ modules, except for those that were previously traditional
plugins intended for Liferay Portal version 6.2 or earlier). 

## Executing verify processes [](id=executing-verify-processes)

Verify processes make sure the upgrade executed successfully. Verify processes
in the core are automatically executed after upgrading @product@. You can also
execute them by configuring the
[`verify.*` portal properties](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Verify)
and restarting your server.

Also, some modules have verify processes. To check for available verify
processes, enter the Gogo shell command `verify:list`. To run a verify process,
enter `verify:execute [verify_qualified_name]`. 

## Post-Upgrade Tasks [](id=post-upgrade-tasks)

After upgrading and running verify processes, you should reindex @product@'s
search indexes. Don't just do this blindly, however. By default, @product@ ships
with an embedded configuration for Elasticsearch. This configuration works great
for demo purposes, but is not supported in production. Make sure to
[install and configure a standalone Elasticsearch instance to run in production](/discover/deployment/-/knowledge_base/7-1/installing-elasticsearch).

Once you've configured search and reindexed your search index, your upgraded
system is ready for action! Congratulations! 
