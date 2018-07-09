# Running the Upgrade Tool [](id=running-the-upgrade)

The upgrade tool provides the easiest way to upgrade the core and installed
modules. You can configure the upgrade from files or inside the tool's command
line interface. The upgrade tool lets you upgrade everything--the core and all
the modules--together or separately. 

@product-ver@ bundles include the upgrade tool. If you installed @product-ver@
manually, you can download the upgrade tool separately from the same location on
[liferay.com](https://www.liferay.com/). 

Before running the upgrade tool, learn the tool's usage and how to configure the
core upgrade and non-core module upgrades. 

- [Upgrade Tool Usage](#upgrade-tool-usage)
- [Configuring Non-Core Module Upgrades](#configuring-module-upgrades)
- [Configuring the Core Upgrade](#configuring-the-core-upgrade)

Start with the tool's usage. 

## Upgrade Tool Usage [](id=upgrade-tool-usage)

To upgrade only the core, add a file called
`com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration.config`
to the `[Liferay Home]/osgi/configs` folder with the following content:

    autoUpgrade="false"

This configuration prevents automatic module upgrade, but causes the upgrade
tool to open a Gogo shell for
[upgrading modules](/discover/deployment/-/knowledge_base/7-1/gogo-shell-commands-for-module-upgrades)
after finishing the core upgrade. 

The `db_upgrade.sh` script (`db_upgrade.bat` on Windows) invokes the upgrade
tool. It resides in the  `[Liferay Home]/tools/portal-tools-db-upgrade-client`
folder. 

This command prints the upgrade tool usage: 

    db_upgrade.sh --help

+$$$

**Warning**: To prevent the tool's expanded command from growing too large for
Windows, execute the upgrade tool script from the `[Liferay
Home]/tools/portal-tools-db-upgrade-client` folder.

$$$
 
Here are the tool's default Java parameters:
    
    -Dfile.encoding=UTF8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx2048m 

The `-j` option lets you override the JVM parameters. For example, these options
set the JVM memory to 10GB, which is a good starting point for this process
type:

    db_upgrade.sh -j "-Dfile.encoding=UTF8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx10240m"

The `-l` option lets you specify the tool's log file name: 

    db_upgrade.sh -l "output.log"

Here are all the upgrade tool command line options:

**--help** or **-h**: Prints the tool's help message.

**--jvm-opts** or **-j** + **[arg]**: Sets any JVM options for the upgrade process.

**--log-file** or **-l** + **[arg]**: Specifies the tool's log file name.

**--shell** or **-s**: Automatically connects you to the Gogo shell after
finishing the upgrade process.

Before starting the upgrade, decide how to execute non-core module upgrades. 

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
command line interface or pre-configure it in these files:

-   `app-server.properties`: Specifies the server's location and libraries.
-   `portal-upgrade-database.properties`: Configures the database connection.
-   `portal-upgrade-ext.properties`: Sets the rest of the portal properties that
    the upgrade requires. You might want to copy your current portal properties
    (except your database properties) into this file. Before copying your
    current properties, make sure you've
    [updated the portal properties for @product-ver@](/discover/deployment/-/knowledge_base/7-1/preparing-an-upgrade-to-liferay-7#step-4-update-your-portal-properties). 

Each file's properties are described next. 

### Configuring app-server.properties [](id=configuring-app-server-properties)

Specify the following information to configure the app server on which @product-ver@ is installed: 

**dir:**  the application server directory *(required)*

**extra.lib.dirs:**  a comma delimited list of extra directories containing any
binaries or resources to add to the  class path *(required)*

**global.lib.dir:**  the application server's global library directory 
*(required)*

**portal.dir:**  the directory where portal is installed in your app server
*(required)*

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

**hibernate.jdbc.batch_size:** the JDBC batch size used to improve performance;
set to *250* by default *(optional)* 

### Example Upgrade Configuration [](id=example-upgrade-configuration)

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

The upgrade tool shows the patch level at the beginning of the upgrade. Then it
executes the core upgrade processes and verifiers. 

If a core upgrade step for @product@ 7.1 (or newer) fails, executing the upgrade
tool again starts it from that step. 

The Gogo shell lets you upgrade modules, check module upgrade status, and verify
upgrades. Read on to learn how to use Gogo shell commands to use the
upgrade-related commands. 
