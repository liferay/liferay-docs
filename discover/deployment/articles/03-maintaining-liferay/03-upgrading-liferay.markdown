# Upgrading to Liferay 7 [](id=upgrading-to-liferay-7)

In this article, you'll learn how to upgrade to @product@ 7. Please see the
[Upgrading to Liferay 6.2](https://dev.liferay.com/discover/deployment/-/knowledge_base/6-2/upgrading-liferay)
article for information on upgrading to Liferay 6.2.

The instructions covered in this article apply to both the commercial and open 
source versions of Liferay.

To upgrade your instance to Liferay 7, you should create a file called
`com.liferay.portal.search.configuration.IndexWriterHelperConfiguration.cfg` in
your `[Liferay Home]/osgi/configs` folder with this contents:

    index.read.only=true

Setting the property above disables indexing. By disabling indexing, you avoid
the possibility of faulty indexing and you save time during the upgrade
process. Once you have upgraded your @product@ instance, remove this property or set it to
`false` so that you can index all objects from Liferay's Control Panel.

## Running an Upgrade Manually [](id=running-an-upgrade-manually)

The upgrade tool is located in Liferay's source code in the
`liferay-portal/tools/db-upgrade` folder.

All Liferay servers must be shut down before performing an upgrade.

Add your custom settings to your `portal-ext.properties` file so that the
upgrade tool can connect to your database. Also, set your `liferay.home`
property in your `portal-ext.properties` file.

On Unix, you can set your classpath like this:

    export LIFERAY_CLASSPATH=$TOMCAT_DIR/lib,$TOMCAT_DIR/lib/ext,$TOMCAT_DIR/bin,$TOMCAT_DIR/webapps/ROOT/WEB-INF/lib

On Windows, replace the following section in `build.xml`

    <path id="lib.classpath">
        <fileset dir="lib" includes="*.jar" />
    </path>

with

    <path id="lib.classpath">
       <fileset dir="$TOMCAT_DIR/lib" includes="*.jar" />
       <fileset dir="$TOMCAT_DIR/lib/ext" includes="*.jar" />
       <fileset dir="$TOMCAT_DIR/bin" includes="*.jar" />
       <fileset dir="$TOMCAT_DIR/webapps/ROOT/WEB-INF/lib" includes="*.jar" />
    </path>

To run the upgrade tool in a UNIX environment, execute `run.sh`.

To run the upgrade tool in a Windows environment, use Ant and execute the
command `ant upgrade`. Please refer to the [Ant](http://ant.apache.org/)
documentation to learn how to set up Ant for your environment.

Running the appropriate command above executes the upgrades and verifiers of
@product@'s core. It also runs the upgrades for each of the installed modules if
they are in automatic mode. If the modules are not in automatic mode, they can
be upgraded individually as explained below.

## Optional: Upgrading Modules Individually [](id=upgrading-modules-individually)

You can specify that the portal should just upgrade the core and not the
modules by adding a file called
`com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration.cfg`
in the `/osgi/configs` folder with the following content:

    autoUpgrade=false

To run the upgrades for the modules, you can use the Gogo shell.

## Gogo shell commands for module upgrades [](id=gogo-shell-commands-for-module-upgrades)

To run the upgrades for the modules, check them or execute verify processes,
you can use the Gogo shell.

1. Connect to the Gogo shell by executing `telnet localhost 11311` from a
   terminal.
2. Use the available commands in the `upgrade` namespace. For example:

        upgrade:list
        upgrade:execute
        upgrade:dryRun (available from 7.0 CE GA2 onwards)
        verify:list
        verify:execute

### Listing module upgrade processes [](id=listing-module-upgrade-processes)

Entering `upgrade:list` at the Gogo shell shows you the modules that have all
of their upgrade dependencies satisfied. These are the modules that you can
upgrade.

If a module is active but you do not see it in the list, that means you need to
upgrade its dependencies first. You could enter the command
`scr:info {upgrade_qualified_class_name}` to find the names of the unsatisfied
dependencies. Here's an example:

    scr:info com.liferay.journal.upgrade.JournalServiceUpgrade

Entering `upgrade:list {module_name}` at the Gogo shell shows you the steps you
need to take for upgrading your module. They are listed from highest to lowest
with respect to how close you are to finishing the whole upgrade process.
Here's an example: if you execute `upgrade:list com.liferay.bookmarks.service`
(for the bookmarks service module), you get this:

    Registered upgrade processes for com.liferay.bookmarks.service 1.0.0
            {fromSchemaVersionString=0.0.0, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.portal.spring.extender.internal.context.ModuleApplicationContextExtender$ModuleApplicationContextExtension$1@6e9691da}
            {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0-step-3, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradePortletId@5f41b7ee}
            {fromSchemaVersionString=1.0.0-step-1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradePortletSettings@53929b1d}
            {fromSchemaVersionString=1.0.0-step-2, toSchemaVersionString=1.0.0-step-1, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradeLastPublishDate@3e05b7c8}
            {fromSchemaVersionString=1.0.0-step-3, toSchemaVersionString=1.0.0-step-2, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradeClassNames@6964cb47}

The step from `0.0.0` to `1.0.0` is for the case where you're coming from an
empty database. If you're coming from an existing database where the Bookmarks
tables had already been created (e.g., if you're coming from a 6.2 database),
the upgrade steps would execute in this order:

- `0.0.1` to `1.0.0-step-3`
- `0.0.1-step-3` to `1.0.0-step-2`
- `0.0.1-step-2` to `1.0.0-step-1`
- `0.0.1-step-1` to `1.0.0`

This means that there is an available process to upgrade Bookmarks from version
`0.0.1` to version `1.0.0`. To complete this process, you would need to execute
four steps. The first step is the one which starts on the initial version and
finishes on the first step of the target version. The first step of the target
version is the highest step number (`step-3`). In this example, the first step
is `UpgradePortletId`. The last step is the one which starts on the last step
of the target version (`step-1`) and finishes on the target version (`1.0.0`).
In this example, the last step is `UpgradePortletSettings`.

### Executing module upgrades [](id=executing-module-upgrades)

Entering `upgrade:execute {module_name}` upgrades a module. It is important to
take into account that if there is an error during the process, you will be
able to restart the process from the last step executed successfully. This
means that you don't have to execute the entire process again. You can check
the status of your upgrade by executing `upgrade:list {module_name}`.

For example, entering `upgrade:list com.liferay.iframe.web` results in the
following output:

    Registered upgrade processes for com.liferay.iframe.web 0.0.1
	   {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.iframe.web.upgrade.IFrameWebUpgrade$1@1537752d}

Note the version at the end of the first line: `0.0.1`.

Entering `upgrade:execute com.liferay.iframe.web` followed by `upgrade:list
com.liferay.iframe.web` again results in the following output with the version
now being `1.0.0`:

    Registered upgrade processes for com.liferay.iframe.web 1.0.0
	   {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.iframe.web.upgrade.IFrameWebUpgrade$1@1537752d}

### Checking the upgrade status [](id=checking-the-upgrade-status)

The `release_` table in the database contains the information to check the
upgrade status:

- There is one row for the core recognizable by the value `portal` at
`servletContextName` field. If the `schemaVersion` field matches your new
Liferay version (`7.0.1` in case of Liferay 7 CE GA2) and `verified` field
values 1 (true) would mean that the upgrade for the core has been completed
successfully. Anyway the upgrade will stop if there is relevant error during
this process.

- There is one row per OSGi module and the value for `schemaVersion` has to
be equal or higher than 1.0.0 (1.0.0 is the initial version in Liferay 7 for
most of the modules except for those that were plugins in earlier versions of
Liferay Portal). You can check the final expected status for a module by typing
`upgrade:list {module_name}`

Additionaly typing `upgrade:dryRun` at the Gogo shell shows you the modules that
have not reached its final version. Thus you will have a way to know the modules
whose upgrade has failed at the end of the process.

To show how this command works, please, take a look at this example: picture
that the upgrade for module `com.liferay.dynamic.data.mapping.service` fails in
the step `1.0.0-step-2`, if you execute the command `upgrade:dryRun` at this
momment you will get:

    Would upgrade com.liferay.dynamic.data.mapping.service from 1.0.0-step-2 to 1.0.0 and its dependent modules

That means that you will need to fix the issue and execute the upgrade for that
module again. Notice that dependent modules for
`com.liferay.dynamic.data.mapping.service` need to be upgraded once the first
one is upgrade properly.

### Executing verify processes [](id=executing-verify-processes)

We still keep verify processes in the core that are automatically executed after
upgrading your portal. You can also execute them configuring the portal
properties `verify.*` properly and restarting your server.

Also, there are other verify processes included in some modules. You can run a
verify process from command line by entering `verify:list` to check all
available verify processes and `verify:execute {verify_qualified_name}` to run
it. Restart your server is not needed to execute this kind of verifiers.
