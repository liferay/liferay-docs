# Running the Upgrade Tool [](id=running-the-upgrade)

The upgrade tool provides the easiest way to upgrade the core and installed
modules. You can configure the upgrade from files or inside the tool's command
line interface. The upgrade tool lets you upgrade everything--the core and all
the modules--together or separately. 

@product-ver@ bundles include the upgrade tool. If you installed @product-ver@
manually, you can download the upgrade tool separately. 

- *Liferay DXP 7.1*: Go to the
[*Liferay DXP 7.1 Downloads* page](https://web.liferay.com/group/customer/dxp/downloads/7-1),
select *Admin Tools*, and click *Download*. 

- *Liferay Portal CE 7.1*: Go to the
[*Downloads* page](https://www.liferay.com/downloads-community)
and select *Download* for *Liferay Portal Tools for 7.1*. 

Before running the upgrade tool, learn the tool's usage and how to configure the
core upgrade and non-core module upgrades. 

- [Upgrade Tool Usage](#upgrade-tool-usage)
- [Configuring Non-Core Module Upgrades](#configuring-module-upgrades)
- [Configuring the Core Upgrade](#configuring-the-core-upgrade)

Start with the tool's usage. 

## Upgrade Tool Usage [](id=upgrade-tool-usage)

The `db_upgrade.sh` script (`db_upgrade.bat` on Windows) invokes the upgrade
tool. It resides in the  `[Liferay Home]/tools/portal-tools-db-upgrade-client`
folder. 

This command prints the upgrade tool usage: 

    db_upgrade.sh --help

To upgrade only the core, add a file called
`com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration.config`
to the `[Liferay Home]/osgi/configs` folder with the following content:

    autoUpgrade="false"

This configuration prevents automatic module upgrade, but causes the upgrade
tool to open a Gogo shell for
[upgrading modules](/discover/deployment/-/knowledge_base/7-1/gogo-shell-commands-for-module-upgrades)
after finishing the core upgrade. 
 
Here are the tool's default Java parameters:
    
    -Dfile.encoding=UTF-8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx2048m 

The `-j` option lets you override the JVM parameters. For example, these options
set the JVM memory to 10GB, which is a good starting point for this process
type:

    db_upgrade.sh -j "-Dfile.encoding=UTF-8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx10240m"

The `-l` option lets you specify the tool's log file name: 

    db_upgrade.sh -l "output.log"

Here are all the upgrade tool command line options:

**--help** or **-h**: Prints the tool's help message.

**--jvm-opts** or **-j** + **[arg]**: Sets any JVM options for the upgrade process.

**--log-file** or **-l** + **[arg]**: Specifies the tool's log file name---the 
default name is `upgrade.log`.

**--shell** or **-s**: Automatically connects you to the Gogo shell after
finishing the upgrade process.

+$$$

**Note:** Only execute the upgrade process on a server with ideal memory, CPU,
and database connection configuration. If executing an upgrade remotely using
`ssh`, make sure to guard against interruptions: 

- If you're executing the upgrade using `ssh`, ignore hangups (connection loss) 
by using `nohup` or something similar. 
- On the machine you're connecting from, disable settings that shutdown or sleep
that machine. 

Since DB Upgrade Tool 2.0.1 (included in Liferay Portal CE 7.1 GA2 and
downloadable from help center for EE subscribers), the upgrade process continues
on the server even if you lose connection to it. If you lose connection,
reconnect and monitor upgrade status via the log (default log file is
`upgrade.log`). If you're using an earlier version of @product-ver@ and upgrade
execution is interrupted, check your log file for where execution stopped. 

- If execution stopped during an upgrade process for Core 7.1 or higher, or any
  module upgrade process, restart the upgrade tool to continue the upgrade from
  that point. You can also use Gogo shell to
  [check module upgrade status](/discover/deployment/-/knowledge_base/7-1/gogo-shell-commands-for-module-upgrades#checking-the-upgrade-status)
  and continue upgrading modules. 
- If execution stopped during an upgrade process for Core 7.0 or lower, you must
  [restore the data from a backup](/discover/deployment/-/knowledge_base/7-1/backing-up-a-liferay-installation)
  and start the upgrade again. 

$$$

+$$$

**Warning:** To prevent the tool's expanded command from growing too large for
Windows, execute the upgrade tool script from the `[Liferay
Home]/tools/portal-tools-db-upgrade-client` folder.

$$$

Before
[starting the upgrade](#running-and-managing-the-core-upgrade),
decide how to execute non-core module upgrades. 

## Configuring Non-Core Module Upgrades [](id=configuring-module-upgrades)

You can configure the upgrade tool to upgrade all installed modules
automatically or to open a Gogo shell (after core upgrade completes) for you to
execute module upgrades manually. 

If the upgrade tool's `autoUpgrade` property is set to `true` (the default
setting), upgrade processes for all installed modules are run too. 

If you set `autoUpgrade="false"` in a file called
`com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration.config`
and copy the file into the `[Liferay Home]/osgi/configs` folder, the upgrade
tool opens Gogo shell after the core upgrade. In the Gogo shell, you can 
[administer module upgrades](/discover/deployment/-/knowledge_base/7-1/gogo-shell-commands-for-module-upgrades). 

Now that you've decided how to do non-core module upgrades, examine the core
upgrade configuration options. 

## Configuring the Core Upgrade [](id=configuring-the-core-upgrade)

The core upgrade requires configuration. You can configure it at runtime via the
command line interface or pre-configure it in these files in `[Liferay
Home]/tools/portal-tools-db-upgrade-client/`:

-   `app-server.properties`: Specifies the server's location and libraries.
-   `portal-upgrade-database.properties`: Configures the database connection.
-   `portal-upgrade-ext.properties`: Sets the rest of the portal properties that
    the upgrade requires. You might want to copy your current portal properties
    (except your database properties) into this file. Before copying your
    current properties, make sure you've
    [updated the portal properties for @product-ver@](/discover/deployment/-/knowledge_base/7-1/preparing-an-upgrade-to-liferay-7#step-4-update-your-portal-properties). 

Each file's properties are described next. 

### Configuring app-server.properties [](id=configuring-app-server-properties)

Specify the following information to configure the app server on which 
@product-ver@ is installed: 

**dir:**  the absolute path of the application server directory. *(required)*

**extra.lib.dirs:**  a comma delimited list of extra directories containing any
binaries or resources to add to the class path. Use all absolute paths OR all 
paths relative to **dir**. *(required)*

**global.lib.dir:**  the application server's global library directory. Use 
the absolute path or a path relative to **dir**. *(required)*

**portal.dir:**  the directory where portal is installed in your app server. Use
the absolute path or a path relative to **dir**. *(required)*

**server.detector.server.id:** ID of a supported application server. 
(*required*) Here are the IDs:

- `jboss`
- `jonas`
- `resin`
- `tomcat`
- `weblogic`
- `websphere`
- `wildfly`

Relative paths must use Unix style format. The following properties, for
example, are for Windows and use relative paths:

    dir=D:\
    extra.lib.dirs=Liferay/liferay-portal-master/tomcat-9.0.10/bin
    global.lib.dir=Liferay/liferay-portal-master/tomcat-9.0.10/lib
    portal.dir=Liferay/liferay-portal-master/tomcat-9.0.10/webapps/ROOT
    server.detector.server.id=tomcat

These properties, for example, are for Unix and use all absolute paths:

    dir=/
    extra.lib.dirs=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/bin
    global.lib.dir=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/lib
    portal.dir=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/webapps/ROOT
    server.detector.server.id=tomcat

### Configuring portal-upgrade-database.properties [](id=configuring-portal-upgrade-database-properties)

Specify the following information to configure the database you're upgrading.
Note that these properties correspond exactly to the
[JDBC portal properties](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#JDBC)
you'd use in a `portal-ext.properties` file. 

**jdbc.default.driverClassName** *(required)*

**jdbc.default.url** *(required)*

**jdbc.default.username** *(required)*

**jdbc.default.password** *(required)*

### Configuring portal-upgrade-ext.properties [](id=configuring-portal-upgrade-ext-properties)

Specify the following information to configure the upgrade itself: 

**liferay.home:** the [Liferay home folder](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home) *(required)*

**dl.store.impl:** the document library server will use this implementation to persist documents. Copy the same value you are using in your current portal-ext.properties (required when you do not use the default implementation `com.liferay.portal.store.file.system.FileSystemStore`)

**hibernate.jdbc.batch_size:** the JDBC batch size used to improve performance;
set to *250* by default *(optional)*

### Example Upgrade Configuration [](id=example-upgrade-configuration)

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

Here are example upgrade configuration files that you can customize and copy
into `[Liferay Home]/tools/portal-tools-db-upgrade-client/`: 

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

It's time to start the core upgrade. 

## Running and Managing the Core Upgrade [](id=running-and-managing-the-core-upgrade)

Start the upgrade tool, as explained in the 
[upgrade tool usage](#upgrade-tool-usage). 
Here are the core upgrade stages:

1.  Show the upgrade patch level

2.  Execute the core upgrade processes

3.  Execute the core verifiers

Monitor the upgrade via the upgrade tool log file (default file is
`upgrade.log`). If a core upgrade process fails, analyze the failure and resolve
it. If a core upgrade step for @product@ 7.1 (or newer) fails, executing the
upgrade tool again starts it from that step. 

If you
[configured the upgrade tool to upgrade non-core modules](#configuring-module-upgrades),
the tool opens a
Gogo shell and starts upgrading them. The Gogo shell lets you upgrade modules,
check module upgrade status, verify upgrades, and restart module upgrades. Read
on to learn how to use Gogo shell commands to complete @product@ upgrades. 
