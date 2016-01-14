# Upgrading to Liferay 7 [](id=upgrading-to-liferay-7)

In this article, you'll learn how to upgrade to Liferay 7. Please see the
[Upgrading to Liferay 6.2](https://dev.liferay.com/discover/deployment/-/knowledge_base/6-2/upgrading-liferay)
article for information on upgrading to Liferay 6.2.

To upgrade your portal to Liferay 7, we recommend that you to add this portal
property to your `portal-ext.properties` file (besides the ones from your
previous portal):

    index.read.only=true

By setting the property above, you can avoid faulty indexing and save time
during the upgrade process. Once you have upgraded your portal, remove this
property or set it to `false` so that you can index all objects from Liferay's
Control Panel.

## Running an Upgrade Manually [](id=running-an-upgrade-manually)

All Liferay servers must be shut down before performing an upgrade.

Download the Liferay Upgrade helper script. This script invokes the new Liferay 
upgrade process. 

If you're on Linux or OS X, [download run.sh](https://raw.githubusercontent.com/liferay/liferay-portal/master/tools/db-upgrade/run.sh) 
and save it to your bundle directory 
If you're on Windows, [download build.xml](https://raw.githubusercontent.com/liferay/liferay-portal/master/tools/db-upgrade/build.xml)
and save it to your bundle directory. 
You may need to right-click and select "Save as..." to save the contents of the (relatively short) files to disk.

Modify your `portal-ext.properties` file with your custom settings so that the
upgrade tool can connect to your database. Also, set your `liferay.home`
property in your `portal-ext.properties` file.

On Windows, replace the following section in `build.xml`

    <path id="lib.classpath">
        <fileset dir="lib" includes="*.jar" />
    </path>

with

    <path id="lib.classpath">
       <fileset dir="$TOMCAT_DIR/lib" includes="*.jar" />
       <fileset dir="$TOMCAT_DIR/lib/ext" includes="*.jar" />
       <fileset dir="$TOMCAT_DIR/webapps/ROOT/WEB-INF/lib" includes="*.jar" />
    </path>

To run the upgrade tool in a Windows environment, use Ant and execute the
command `ant upgrade`. Please refer to the [Ant](http://ant.apache.org/)
documentation to learn how to set up Ant for your environment.

To run the upgrade tool in a UNIX environment, execute `run.sh`:

    ./run.sh --classpath $TOMCAT_DIR/lib,$TOMCAT_DIR/lib/ext,$TOMCAT_DIR/webapps/ROOT/WEB-INF/lib --liferay_home .

Running the command above executes the upgrades and verifiers of Liferay's
core. It also runs the upgrades for each of the installed modules if they are
in automatic mode. If the modules are not in automatic mode, they can be
upgraded individually as explained below.

## Optional: Upgrading Modules Individually [](id=upgrading-modules-individually)

You can specify that the portal should just upgrade the core and not the
modules by adding a file called
`com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration.cfg`
in the `/osgi/configs` folder with the following content:

    autoUpgrade=false

To run the upgrades for the modules you can use the Gogo shell.

1. Connect to the Gogo shell by executing `telnet localhost 11311` from a
   terminal.
2. Use the available commands in the `upgrade` namespace. For example:

        upgrade:list
        upgrade:execute
        verify:list
        verify:execute

Entering `upgrade:list` at the Gogo shell shows you the modules that have all
of their upgrade dependencies satisfied. These are the modules that you can
upgrade.

If you do not see a module, that means you need to upgrade its dependencies.
You could enter the command `scr:info {upgrade_qualified_class_name}` to find
the names of the unsatisfied dependencies. Here's an example:

    scr:info com.liferay.journal.upgrade.JournalServiceUpgrade

Entering `upgrade:list {module_name}` at the Gogo shell shows you the steps you
need to take for upgrading your module. They are listed from highest to lowest
with respect to how close you are to finishing the whole upgrade process.

Entering `upgrade:execute {module_name}` upgrades a module. It is important to
take into account that if there is an error during the process, you will be
able to restart the process from the last step executed successfully. This
means that you don't have to execute the entire process again. You can check
the status of your upgrade by executing `upgrade:list {module_name}`.

For example entering `upgrade:list com.liferay.iframe.web` will result in the following output:

    Registered upgrade processes for com.liferay.iframe.web 0.0.1
	   {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.iframe.web.upgrade.IFrameWebUpgrade$1@1537752d}
       
Note the version at the end of the first line "0.0.1".

Entering `upgrade:execute com.liferay.iframe.web` followed by `upgrade:list com.liferay.iframe.web` again will result in the following output
with the version now being 1.0.0:

    Registered upgrade processes for com.liferay.iframe.web 1.0.0
	   {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.iframe.web.upgrade.IFrameWebUpgrade$1@1537752d}

Also, you can run a verify process from command line by entering `verify:list`
to check all available verify processes and `verify:execute
{verify_qualified_name}` to run it.
