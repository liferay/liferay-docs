# Upgrading to Liferay 7 [](id=upgrading-to-liferay-7)

In this article, you'll learn how to upgrade to @product@ 7. Please see the
[Upgrading to Liferay 6.2](https://dev.liferay.com/discover/deployment/-/knowledge_base/6-2/upgrading-liferay)
article for information on upgrading to Liferay 6.2. Take into account that
information too if you come from prior versions than 6.2 and want to upgrade
your portal to Liferay 7.

## Preparing an upgrade to Liferay 7 [](id=upgrading-to-liferay-7)

As any upgrade you should have a syncronized backup of your database and
file system for your document library.

You should need to check legacy properties (`portal-legacy-6.2.properties`) in
case you want to keep previous behaviour for certain funcionalities:
    
    users.image.check.token=false
    layout.set.prototype.propagate.logo=true
    editor.wysiwyg.portal-web.docroot.html.taglib.ui.discussion.jsp=simple
    web.server.servlet.check.image.gallery=true
    blogs.trackback.enabled=true
    discussion.comments.format=bbcode
    discussion.max.comments=0
    dl.file.entry.thumbnail.max.height=128
    dl.file.entry.thumbnail.max.width=128

You should also set the document library location if you do not store the files
in the default path (`[Liferay Home]/data/document_library`). For achiving that
you need to create a file called
`com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration.cfg`
in your `[Liferay Home]/osgi/configs` folder with this contents:

    root.dir={document_library_path}

Call that file `com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.cfg`
if you use this method to persist the document library files.

To upgrade your portal to Liferay 7, you should create a file called
`com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.cfg` in
your `[Liferay Home]/osgi/configs` folder with this contents:

    indexReadOnly=true

Setting the property above disables indexing. By disabling indexing, you avoid
the possibility of faulty indexing and you save time during the upgrade
process. Once you have upgraded your @product@ instance, remove this property or set it to
`false` so that you can index all objects from Liferay's Control Panel.

## Running the Upgrade [](id=running-the-upgrade)

Starting up the server to upgrade the portal is no longer supported. The
following exception is thrown if you try to do that:

    [MainServlet:237] java.lang.RuntimeException: You must first upgrade to Liferay Portal 7000

The proper way to run the upgrade is using the new Java standalone tool that,
as the portal, **needs to run using Java 8**:

    java -jar com.liferay.portal.tools.db.upgrade.client.jar

By default, the tool is executed with the following Java parameters:
    
    -Dfile.encoding=UTF8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx2048m 

If you need to modify these parameters you can use the option `-jvmOpts`, for
example, for increasing the Java memory in the upgrade process to 4GB:

    java -jar com.liferay.portal.tools.db.upgrade.client.jar -jvmOpts="-Dfile.encoding=UTF8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx4096m"

You can also set the log file where you need that the tool prints the output. By
default it prints it in `upgrade.log` file but you can change it in this way:

    java -jar com.liferay.portal.tools.db.upgrade.client.jar -logFile="output.log"

The upgrade requires three files to be configured before it can run:
- `app-server.properties`: it should contain the properties to tell the tool
where the server and libs are.
- `portal-upgrade-datasource.properties`: it should contain the properties to
connect to the database which is going to be upgraded.
- `portal-upgrade-ext.properties`: it should contain the rest of the Liferay
properties you need to perform the upgrade.

However, if you do not create those files the tool will ask you to get the
information needed to run the upgrade at first time.

Here we are some examples for the content of those files so that you can fill
them properly in your installation:
-`app-server.properties`:

    dir=/home/user/servers/liferay7/tomcat-8.0.32
    portal.dir=webapps/ROOT
    global.dir.lib=lib

-`portal-upgrade-datasource.properties`:

    jdbc.default.url=jdbc:mysql://lportal62?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.username=root
    jdbc.default.password=

-`portal-upgrade-ext.properties`:

    liferay.home=/home/user/servers/liferay7
    module.framework.base.dir=/home/user/servers/liferay7/osgi

Running the appropriate command above executes the upgrades and verifiers of
@product@'s core. It also runs the upgrades for each of the installed modules if
they are in automatic mode. If the modules are not in automatic mode, they can
be upgraded individually as explained below.

After performing the upgrade a Gogo shell is automatically opened.

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
