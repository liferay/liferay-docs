# Running the Upgrade Process [](id=running-the-upgrade-process)

Now you're ready to run the upgrade process. This involves two steps: running
the upgrade tool on the newly installed @product@, and starting your upgraded
instance. 

## Optional: Upgrading Modules Individually [](id=upgrading-modules-individually)

You can choose to upgrade the core and all the modules in one shot or upgrade
just the core and do the modules manually. You'll make this choice depending on
what you have installed on your system. If you are upgrading from a previous
version, you should skip this section and upgrade everything. Going forward,
however, @product@'s modular framework allows you to upgrade modules--even the
core--individually. 

If you want to upgrade only the core, add a file called
`com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration.cfg`
to the `/osgi/configs` folder with the following content:

    autoUpgrade=false

To run the upgrades for the modules, you'll use the [Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell).
In this case, the upgrade tool opens a Gogo shell automatically after it finishes
upgrading the core. 

## Running the Upgrade [](id=running-the-upgrade)

In case of the @product@ bundle, the upgrade tool can be found in the `tools`
folder inside `liferay.home` directory, in a folder called
`portal-tools-db-upgrade-client`. If you've installed @product@ manually, you
can download the tool as standalone application from the same location where you
downloaded @product@. Use the following command to start it: 

    java -jar com.liferay.portal.tools.db.upgrade.client.jar

By default, the tool is executed with the following Java parameters:
    
    -Dfile.encoding=UTF8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx2048m 

If you need to modify these parameters, you can use the option `-j`. For
example, to increase the Java memory in the upgrade process to 4GB:

    java -jar com.liferay.portal.tools.db.upgrade.client.jar -j "-Dfile.encoding=UTF8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx4096m"

You can also set the location of the log file that prints the output: 

    java -jar com.liferay.portal.tools.db.upgrade.client.jar -l "output.log"

Here's the complete list of command line options:

**--help** or **-h**: prints help message for using the tool

**--jvm-opts** or **-j** + [arg]: sets any JVM options for the upgrade process

**--log-file** or **-l** + [arg]: uses a custom name for your log file

**--shell** or **-s**: automatically connect to the GoGo shell after finishing
the upgrade

The upgrade requires configuration before it can run. This configuration
can be done at runtime, or you can pre-configure it in three files:

- `app-server.properties`: Contains properties that define for the tool the
    server's location and libraries.
- `portal-upgrade-database.properties`: Contains properties for connecting 
to the database that will be upgraded.
- `portal-upgrade-ext.properties`: Contains the rest of the @product@
properties you need to perform the upgrade.

Below you'll learn about the properties that can go in these files. 

## Configuring app-server.properties [](id=configuring-app-server-properties)

Specify the following information to configure the app server that contains the
installed new release of @product@: 

**dir:**  the application server directory *(required)*

**extra.lib.dirs:**  a comma delimited list of extra directories you want to add to the classpath *(required)*

**global.lib.dir:**  the global lib directory of your application server *(required)*

**portal.dir:**  the portal directory *(required)*

## Configuring portal-upgrade-database.properties [](id=configuring-portal-upgrade-database-properties)

Specify the following information to configure the database that needs to be
upgraded. Note that these properties correspond exactly to the properties you'd
use in `portal-ext.properties` to connect @product@ to its database. 

**jdbc.default.driverClassName ***(required)*

**jdbc.default.url ***(required)*

**jdbc.default.username ***(required)*

**jdbc.default.password ***(required)*

## Configuring portal-upgrade-ext.properties [](id=configuring-portal-upgrade-ext-properties)

Specify the following information to configure the upgrade itself: 

**liferay.home:** the Liferay home directory *(required)*

**hibernate.jdbc.batch_size:** the JDBC batch size used to improve performance;
set to *250* by default *(optional)* 

## Example Upgrade Configuration [](id=example-upgrade-configuration)

Examples of what you'd put in these files are below, but don't worry: the tool
asks you for the information if you don't have the files, so it may be
easier simply to run it. If you do this, the tool asks you to supply the
information at runtime like this: 

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

    (etc)

The nice thing about doing it this way is the tool creates the configuration
files for you. If you want to set all of this up ahead of time, however, you'll
want to put this information into the configuration files. Here's an example
configuration that you can customize for your use:

-`app-server.properties`:

	dir=../../tomcat-8.0.32
	global.lib.dir=/lib
	portal.dir=/webapps/ROOT
	server.detector.server.id=tomcat
	extra.lib.dirs=/bin

The `dir` setting is the folder where your app server is installed. The 
`global.dir.lib` is the app server's library folder. The `portal.dir` 
setting is the folder where @product@ is installed in your app
server. The `server.detector.server.id` defines the application server. The
`extra.lib.dirs` is the app server's binary folder.

-`portal-upgrade-database.properties`:

    jdbc.default.url=jdbc:mysql://lportal62?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.username=root
    jdbc.default.password=

-`portal-upgrade-ext.properties`:

    liferay.home=/home/user/servers/liferay7
    module.framework.base.dir=/home/user/servers/liferay7/osgi

When you run the tool, it executes the upgrades and verifiers from @product@'s
core. It also runs the upgrades for each of the installed modules if they are in
automatic mode. If the modules are not in automatic mode, they can be upgraded
individually as explained below.

After performing the upgrade, a Gogo shell is automatically opened in case you
need to upgrade some modules manually or if there was an error during the
process. Read on to learn how to do that. 

## Gogo shell commands for module upgrades [](id=gogo-shell-commands-for-module-upgrades)

To run the upgrades for the modules, check their status, or execute verify
processes, you can use the Gogo shell.

1.  Connect to the Gogo shell by executing `telnet localhost 11311` from a
    terminal.
2.  Use the available commands in the `upgrade` namespace:

    **exit** or **quit:** exits the Gogo shell

    **upgrade:help:** displays upgrade commands

    **upgrade:execute _{module_name}_:** executes upgrade for that module

    **upgrade:check:** list upgrades pending to execute because they failed in the past or
    the module haven't reached its final version

    **upgrade:list:** lists all registered upgrades

    **upgrade:list _{module_name}_:** lists the upgrade steps required for that module

    **upgrade:list | grep Registered:** lists registered upgrades and their versions

    **verify:execute _{module_name}_:** executes a verifier

    **verify:list:** lists all registered verifiers

For further information about the Gogo shell, please see our [reference document](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell). 
Below are the details of how upgrade processes work in the Gogo shell. 

### Listing module upgrade processes [](id=listing-module-upgrade-processes)

Entering `upgrade:list` at the Gogo shell shows you the modules that have all
of their upgrade dependencies satisfied. These are the modules that you can
upgrade.

If a module is active but you do not see it in the list, that means you need to
upgrade its dependencies first. You could enter the command
`scr:info [upgrade_qualified_class_name]` to find the unsatisfied dependencies.
Here's an example:

    scr:info com.liferay.journal.upgrade.JournalServiceUpgrade

Entering `upgrade:list [module_name]` at the Gogo shell shows you the steps you
need to take to upgrade your module. They are listed from highest to lowest
with respect to how close you are to finishing the whole upgrade process.
Here's an example: if you execute `upgrade:list com.liferay.bookmarks.service`
(for the bookmarks service module), you get this:

    Registered upgrade processes for com.liferay.bookmarks.service 1.0.0
            {fromSchemaVersionString=0.0.0, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.portal.spring.extender.internal.context.ModuleApplicationContextExtender$ModuleApplicationContextExtension$1@6e9691da}
            {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0-step-3, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradePortletId@5f41b7ee}
            {fromSchemaVersionString=1.0.0-step-1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradePortletSettings@53929b1d}
            {fromSchemaVersionString=1.0.0-step-2, toSchemaVersionString=1.0.0-step-1, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradeLastPublishDate@3e05b7c8}
            {fromSchemaVersionString=1.0.0-step-3, toSchemaVersionString=1.0.0-step-2, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradeClassNames@6964cb47}

The shell doesn't return the steps in order, so you'll have to sort them
yourself either mentally or in a text editor to determine the order in which to
run the upgrade. The step from `0.0.0` to `1.0.0` is for the case where you're
coming from an empty database. If you're coming from an existing database where
the Bookmarks tables had already been created (e.g., if you're coming from a 6.2
database), you'd execute the upgrade steps in this order:

- `0.0.1` to `1.0.0-step-3`
- `0.0.1-step-3` to `1.0.0-step-2`
- `0.0.1-step-2` to `1.0.0-step-1`
- `0.0.1-step-1` to `1.0.0`

This means that there is an available process to upgrade Bookmarks from version
`0.0.1` to version `1.0.0`. To complete this process, you would need to execute
four steps. They are executed in reverse order. If it helps, think of this like
the countdown to a rocket launch. The first step starts on the initial version
and finishes on the first step of the target version, which is the highest step
number (`step-3`). In this example, the first step is `UpgradePortletId`. The
last step starts on the last step of the target version (`step-1`) and finishes
on the target version (`1.0.0`). In this example, the last step is
`UpgradePortletSettings`. 

The reason for this is that every upgrade process is unique to the application
being upgraded. This application requires updating a `PortletId`, class names,
the `LastPublishDate`, and finally `PortletSettings`. Other applications might
have these and/or other steps, depending on whatever enhancements are in the new
version. 

Once you know the steps, it's time to execute the upgrade. 

### Executing module upgrades [](id=executing-module-upgrades)

Entering `upgrade:execute [module_name]` upgrades a module. If there is an error
during the process, you will be able to restart the process from the last step
executed successfully. This means that you don't have to execute the entire
process again. You can check the status of your upgrade by executing
`upgrade:list [module_name]`.

For example, entering `upgrade:list com.liferay.iframe.web` results in the
following output:

    Registered upgrade processes for com.liferay.iframe.web 0.0.1
	   {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.iframe.web.upgrade.IFrameWebUpgrade$1@1537752d}

Note the version at the end of the first line: `0.0.1`.

Entering `upgrade:execute com.liferay.iframe.web` followed by `upgrade:list
com.liferay.iframe.web` results in the following output with the version
now being `1.0.0`:

    Registered upgrade processes for com.liferay.iframe.web 1.0.0
	   {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.iframe.web.upgrade.IFrameWebUpgrade$1@1537752d}

This indicates that the upgrade was completed. 

### Checking the upgrade status [](id=checking-the-upgrade-status)

You can also browse @product@'s database to determine the upgrade status. 
The `release_` table in the database contains upgrade status information. 

There is one row for the core recognizable by the value `portal` in the 
`servletContextName` field. If the `schemaVersion` field matches your new
@product@ version (`7.0.1` in case of @product-ver@ CE GA2) and `verified` field
contains `1` (true), this means that the upgrade for the core has been completed
successfully. 

There is one row per OSGi module, and the value for `schemaVersion` must be
equal or greater than 1.0.0 (1.0.0 is the initial version in @product-ver@ for most
of the modules except for those that were plugins in earlier versions of Liferay
Portal). You can check the final expected status for a module by typing
`upgrade:list [module_name]`

If you need to know which modules failed to upgrade, you can use
`upgrade:check` to list those that have not reached their final version. This
command returns a list of modules that failed to upgrade the last time the
process ran. 

Here's an example of how this works. Say that that the upgrade for module
`com.liferay.dynamic.data.mapping.service` failed in a step labeled
`1.0.0-step-2`. If you execute the command `upgrade:check`, you'll get the
following output: 

    Would upgrade com.liferay.dynamic.data.mapping.service from 1.0.0-step-2 to
    1.0.0 and its dependent modules

That means that you will need to fix the issue and execute the upgrade for that
module again. One clue as to what might have happened is that this upgrade is
dependent on the upgrade of other modules. Dependent modules for
`com.liferay.dynamic.data.mapping.service` need to be upgraded once the first
one is upgrade properly.

### Executing verify processes [](id=executing-verify-processes)

Verify processes test the upgraded data to be sure the upgrade executed
successfully. Verify processes in the core are automatically executed after
upgrading @product@. You can also execute them configuring the [portal properties](http://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Verify) 
`verify.*` property and restarting your server.

Also, there are other verify processes included in some modules. You can run a
verify process from the Gogo shell by entering `verify:list` to check all
available verify processes and `verify:execute [verify_qualified_name]` to run
it. Restarting your server is not needed to execute these kinds of verifiers.

## Post-Upgrade Tasks [](id=post-upgrade-tasks)

After upgrading, you should reindex @product@'s search indexes. Don't just do
this blindly, however: by default, @product@ ships with an embedded
configuration for Elasticsearch. This configuration works great for demo
purposes, but is not supported in production. 

To configure search, follow the [instructions](/discover/deployment/-/knowledge_base/7-0/installing-elasticsearch) 
in the installation section to create a standalone instance of Elasticsearch to
run in production. 

Once you've configured search and reindexed your search index, your system is upgraded! Congratulations! 
