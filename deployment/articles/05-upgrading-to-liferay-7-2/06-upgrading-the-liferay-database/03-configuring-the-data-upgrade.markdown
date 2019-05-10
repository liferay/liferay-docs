---
header-id: configuring-the-data-upgrade
---

# Configuring the Data Upgrade

[TOC levels=1-4]

The upgrade tool provides the easiest way to upgrade the core and installed
modules. You can use text files or the tool's command line interface to
configure your upgrade. The upgrade tool can upgrade everything---the core
and all the modules---together or separately. 

@product-ver@ bundles include the upgrade tool. If you installed @product-ver@
manually, you can download the upgrade tool separately. 

-   *Liferay DXP 7.2*: Go to the
    [*Downloads* page](https://customer.liferay.com/group/customer/downloads)
    and select the *DXP 7.2* product and the *Product/Service Packs* file type.
    In the listing that appears, click *Download* for the *Liferay DXP Upgrade
    Client*. 

-   *Liferay Portal CE 7.2*: Go to the
    [*Downloads* page](https://www.liferay.com/downloads-community)
    and select *Download* for *Liferay Portal Tools for 7.2*. 

Before starting the data upgrade process, configure the upgrade tool for the
core upgrade and specify whether the upgrade tool should upgrade non-core module
data automatically. 

## Configuring the Core Upgrade

The core upgrade requires configuration. You can configure it at runtime via the
command line interface or pre-configure it in these files in `[Liferay
Home]/tools/portal-tools-db-upgrade-client/`:

-   `app-server.properties`: Specifies the server's location and libraries.
-   `portal-upgrade-database.properties`: Configures the database connection.
-   `portal-upgrade-ext.properties`: Sets the rest of the portal properties that
    the upgrade requires. You might want to copy your current portal properties
    (except your database properties) into this file. Before copying your
    current properties, make sure you've
    [updated the portal properties for @product-ver@](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database). 

Each file's properties are described next. 

### Configuring app-server.properties

Specify the following information to configure @product-ver@'s app server: 

`dir:` the absolute path of the application server folder. *(required)*

`extra.lib.dirs:` a comma delimited list of extra directories containing any
binaries or resources to add to the class path. Use all absolute paths OR all 
paths relative to `dir`. *(required)*

`global.lib.dir:` the application server's global library directory. Use 
the absolute path or a path relative to `dir`. *(required)*

`portal.dir:` the directory where portal is installed in your app server. Use
the absolute path or a path relative to `dir`. *(required)*

`server.detector.server.id:` ID of a supported application server. 
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

```properties
dir=D:\
extra.lib.dirs=Liferay/liferay-portal-master/tomcat-9.0.10/bin
global.lib.dir=Liferay/liferay-portal-master/tomcat-9.0.10/lib
portal.dir=Liferay/liferay-portal-master/tomcat-9.0.10/webapps/ROOT
server.detector.server.id=tomcat
```

These properties, for example, are for Linux and use all absolute paths:

```properties
dir=/
extra.lib.dirs=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/bin
global.lib.dir=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/lib
portal.dir=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/webapps/ROOT
server.detector.server.id=tomcat
```

### Configuring portal-upgrade-database.properties

Specify the following information to configure the database you're upgrading.
Note that these properties correspond exactly to the
[JDBC portal properties](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#JDBC)
you'd use in a `portal-ext.properties` file. 

`jdbc.default.driverClassName` *(required)*

`jdbc.default.url` *(required)*

`jdbc.default.username` *(required)*

`jdbc.default.password` *(required)*

### Configuring portal-upgrade-ext.properties

Specify the following information to configure the upgrade: 

`liferay.home:` The [Liferay home folder](/docs/7-2/deploy/-/knowledge_base/d/liferay-home) *(required)*

`dl.store.impl:` The implementation for persisting documents to the document
library store. This property is mandatory if you're using a `*FileSystemStore`
implementation. If you
[updated this property in your `portal-ext.properties`](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade),
copy it here. Otherwise, set the property one of these ways:

```properties
dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore
dl.store.impl=com.liferay.portal.store.db.DBStore
dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore
dl.store.impl=com.liferay.portal.store.s3.S3Store
```

`hibernate.jdbc.batch_size:` The JDBC batch size used to improve performance;
set to *250* by default *(optional)* 

### Example Upgrade Configuration

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
You can put this information into configuration files to configure the tool
manually.

Here are example upgrade configuration files that you can customize and copy
into `[Liferay Home]/tools/portal-tools-db-upgrade-client/`: 

-   `app-server.properties`:

    ```properties
	dir=../../tomcat-8.0.32
	global.lib.dir=/lib
	portal.dir=/webapps/ROOT
	server.detector.server.id=tomcat
	extra.lib.dirs=/bin
    ```

-   `portal-upgrade-database.properties`:

    ```properties
    jdbc.default.url=jdbc:mysql://lportal62?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.username=root
    jdbc.default.password=
    ```

-   `portal-upgrade-ext.properties`:

    ```properties
    liferay.home=/home/user/servers/liferay7
    module.framework.base.dir=/home/user/servers/liferay7/osgi
    dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore
    ```

Next, decide if the upgrade tool should upgrade non-core modules automatically.

## Configuring Non-Core Module Data Upgrades

You can configure the upgrade tool to upgrade all installed modules
automatically or to open a Gogo shell (after core upgrade completes) for you to
execute module upgrades manually. 

If the upgrade tool's `autoUpgrade` property is set to `true` (the default
setting), upgrade processes for all installed modules are run too. 

If you set `autoUpgrade="false"` in a file called
`com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration.config`
and copy the file into the `[Liferay Home]/osgi/configs` folder, the upgrade
tool opens Gogo shell after the core upgrade. In the Gogo shell, you can 
[administer module upgrades](/docs/7-2/deploy/-/knowledge_base/d/upgrading-modules-using-gogo-shell). 

It's time to run the upgrade tool. 
