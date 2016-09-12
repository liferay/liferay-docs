# Patching Liferay [](id=patching-liferay)

While we strive for perfection with every release of @product@, the reality
of the human condition dictates that releases of the product may not be as
perfect as originally intended. But we've planned for that. Included with every
Liferay bundle is a Patching Tool that can handle the installation of two types
of patches: fix packs and hotfixes.

On a regular schedule, the latest fixes that patch the core are bundled together
into fix packs, which are provided to all of Liferay's customers. If there's a
fix you need, and it's for one of the application suites, the way to get the
update is to retrieve and install it from Marketplace. If there's a fix you need
that affects only the core, a fix pack will be provided.

A hotfix is provided to a customer when a customer contacts Liferay about an
emergency situation, and Liferay's support team--working with the customer--
determines that the problem is indeed an issue with the product that needs to be
fixed very quickly. Support fixes the bug and provides a hotfix to the customer
immediately. This is a short-term fix that solves the issue for the customer as
quickly as possible. Hotfixes can patch both the core and the application
suites.

Now that you know what patching is all about, let's check out the tool. 

## Installing the Patching Tool [](id=installing-the-patching-tool)

If you're using a Liferay bundle, congratulations! The Patching Tool is already
installed. Your job isn't done yet, however, because Liferay *might* have
updated the Patching Tool. Always check the Customer Portal to see if the
Patching Tool has been updated first. Even if you forget to check, the
Patching Tool tells you if it needs to be updated when you run it. A lot of
planning and forethought has gone into the patching system to make it run as
smoothly as possible.

You follow the same procedure whether you're installing or upgrading the
Patching Tool. Once you've obtained it from the customer portal, unzip it to the
Liferay Home folder. This is the folder where you've placed your
`portal-ext.properties` file and where by default the `data` folder resides.
This is generally one folder up from where your application server is installed,
but some application servers are different. If you don't know where Liferay Home
is on your system, check [the installation documentation](/discover/deployment/-/knowledge_base/7-0/installation-and-setup) 
to see where this folder is for your specific application server.

If you're upgrading the Patching Tool, all you need to do is unzip the new
version on top of the old version. Note that if you're doing this on LUM (Linux,
Unix, Mac) machines, you'll need to make the `patching-tool.sh` script
executable.

## Auto Discovery [](id=auto-discovery)

After the Patching Tool is installed, you need to let it auto-discover your
Liferay installation. Then it will determine what your release level is and what
your application server environment is. To do this, you first have to define for
the tool where [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home)
is by creating a hidden file there. If you're using a Liferay bundle, this step
has been completed for you. If, however, you've installed @product@ manually,
you'll have to create this file manually. 

Go to your Liferay Home folder (the folder that by default contains the `data`,
`osgi`, and `tools` folders) and create a file called `.liferay-home`. Because
it starts with a dot, this file is a hidden file on Unix-style operating
systems. This file serves as a marker for your Liferay Home folder. 

Now you're ready to run auto-discovery:

    ./patching-tool.sh auto-discovery
 
or on Windows: 

    patching-tool auto-discovery
 
If you've installed the Patching Tool in a non-standard location, you'll have to
give this command another parameter to point it to your Liferay installation.
For example, if you've installed a Liferay/Tomcat bundle in `/opt/Liferay`,
you'd issue this command: 

    ./patching-tool.sh auto-discovery /opt/Liferay/tomcat-8.0.32
 

This writes your server's configuration to a file called `default.properties`.
The Patching Tool uses the properties in this file to find the files it needs to
patch. When you're finished, your `default.properties` file looks similar to
this: 

    patching.mode=binary
    war.path=../tomcat-8.0.32/webapps/ROOT
    global.lib.path=../tomcat-8.0.32/lib/ext
    liferay.home=/opt/liferay-dxp-digital-enterprise-7.0-ga1/

The properties above (described fully [below](#using-profiles-with-the-patching-tool)) 
define the location of Liferay Home, the patching mode
(binary or source), the path to where WAR files are deployed in the app server,
and the global library path. If auto-discovery found your Liferay Home folder,
the location of Liferay's OSGi-based module framework can be calculated from
this. If, however, you customized the folder structure, you'll have to specify
manually the following properties: 

    module.framework.core.path=path_to_modules_core_dir
    module.framework.marketplace.path=path_to_modules_marketplace_dir
    module.framework.modules.path=path_to_modules_modules_dir
    module.framework.portal.path=path_to_modules_portal_dir
    module.framework.static.path=path_to_modules_static_dir

For most installations, you don't have to do this, as the `osgi` folder is in
its default location. If you've customized the location of the module framework,
however, you'll have to specify the above locations. Since you moved them, you
should know where they are. 

There's one more property you'll have to specify, again, only if you have
installed @product@ manually: 

    target.platform.indexer.path=/opt/liferay-dxp-digital-enterprise-7.0-ga1/tools/portal-tools-target-platform-indexer-client/

If you don't have the indexer client, you'll have to download it from the same
location where you downloaded @product@, install it, and then use this property
to point to it. 

Now that you've installed the Patching Tool and run auto-discovery, you're ready
to download and install patches. You can install patches manually or
automatically. For automatic patch installation, you need to set up the Patching
Tool Agent. This is presented next.

### Configuring the Patching Tool Agent [](id=configuring-the-patching-tool-agent)

The Patching Tool Agent automatically installs downloaded patches on server 
startup. For the agent to start with your server, you need to set the `javaagent` 
property in the JVM options. Make sure that you specify the correct file path to 
the `patching-tool-agent.jar`. Here's an example of setting the `javaagent` 
property:

    -javaagent:../../patching-tool/lib/patching-tool-agent.jar
    
When the agent runs, it tries to find the Patching Tool's home folder. If your 
Patching Tool is installed in a location other than the Liferay Home folder, you 
must specify the path of the `patching-tool` folder as a JVM argument for the 
app server. This is done with the `patching.tool.home` property. For example:

    -Dpatching.tool.home=/opt/liferay-dxp-digital-enterprise-7.0-ga1/tools/patching-tool
    
There are also a few other things to consider when using the agent. Due to class 
loading issues, the agent starts in a separate JVM. You can specify options for 
it by using the `patching.tool.agent.jvm.opts` property. For example:

    -Dpatching.tool.agent.jvm.opts="-Xmx1024m -Xms512m"
    
You may also experience issues on Windows if the user starting the app server 
doesn't have administrator privileges. Here are some examples of the errors you 
may see:

    `java.nio.file.FileSystemException: ..\tomcat-8.0.32\webapps\ROOT\WEB-INF\lib\util-java.jar: Not a file!`
    `java.io.FileNotFoundException: java.io.IOException: Access refused`

To solve this, set the `java.io.tmpdir` system property as follows in the 
`patching.tool.agent.jvm.opts` property:

    -Dpatching.tool.agent.jvm.opts="-Xmx1024m -Xms512m -Djava.io.tmpdir=%TMP%"

The agent also has some flags you can set to control how it behaves:

- **debug**: Provides verbose output in the console.
- **nohalt**: Starts the portal even if the agent encounters an issue.

You can specify these as follows:

    -Dpatching.tool.agent.properties=debug,nohalt

Now let's see how to use the Patching Tool to get your patches installed. 

## Installing Patches [](id=installing-patches)

The absolute first thing you must do when installing one or more patches is to
shut down your server. On Windows operating systems, files that are in use are
locked by the OS, and won't be patched. On Unix-style systems, you can usually
replace files that are running, but of course that still leaves the old ones
loaded in memory. So your best bet is to shut down the application server that's
running Liferay before you install a patch. 

**Note:** Liferay Connected Services (LCS) installs patches for you. See the 
[LCS documentation](/discover/deployment/-/knowledge_base/7-0/managing-liferay-with-liferay-connected-services) 
for more information.

Liferay distributes patches as `.zip` files, whether they are hotfixes or fix
packs. When you receive one, either via a LESA ticket (hotfix) or through
downloading a fix pack from the customer portal, you'll need to place it in the
`patches` folder, which is inside the Patching Tool's home folder. Once you've
done that, it's a simple matter to install it. First, execute

    ./patching-tool.sh info
 
This shows you a list of patches you've already installed, along with a list of
patches that *can* be installed, from what's in the `patches` folder. 

There are two ways to install patches: 

- You can use the agent
- You can install patches manually

If you've set up the agent, it installs new patches that have been placed in the
`patches` folder on server restarts. To use the agent to install the patches,
therefore, restart the server. The agent takes care of the rest. 

To install the available patches manually, use the following steps. First, 
issue the following command: 

    ./patching-tool.sh install

If there are new indexes created by the patch, the Patching Tool notifies you
to update them. To get the list, run this command:

    ./patching-tool.sh index-info

As there's no database connection at patching time, the indexes need to be
created at portal startup. In order to have the indexes created automatically,
add the following line to the `portal-ext.properties` file if the server has
permissions to modify the indexes on the database:

    database.indexes.update.on.startup=true

Otherwise, you have to create the indexes manually. Check the output of the
`./patching-tool index-info` command for more details.

Once your patches have been installed, you can verify them by using the
`./patching-tool.sh info` command, which now shows your patch in the list of
installed patches. 

Next, you'll learn how to manage your patches. 

### Handling Hotfixes and Patches [](id=handling-hot-fixes-and-patches)

As stated above, hotfixes are short term fixes provided as quickly as possible
and fix packs are larger bundles of hotfixes provided to all customers at
regular intervals. If you already have a hotfix installed and the fix pack
which contains that hotfix is released, the Patching Tool will manage this for
you. Fix packs always supersede hotfixes, so when you install your fix pack,
the hotfix that it already contains is uninstalled, and the fix pack version is
installed in its place. 

Note that this is *not* the case for hotfixes applied to the application
suites. If you have installed a hotfix that then becomes part of a new release
on Marketplace, you will have to remove the hotfix first (see below) before you
can upgrade to the new release. 

Sometimes there can be a fix to a fix pack. This is also handled automatically.
If a new version of a fix pack is released, you can use the Patching Tool to
install it. The Patching Tool uninstalls the old fix pack and installs the new
version in its place. 

### Fix Pack Dependencies [](id=fix-pack-dependencies)

Some hotfix require a fix pack to be installed first. If you attempt to
install a hotfix that depends on a fix pack, the Patching Tool notifies
you of this so you can go to the customer portal and obtain the hotfix
dependency. Once all the necessary patches are available in the `patches`
folder, the Patching Tool will install them. 

The Patching Tool can also remove patches. 

## Removing or Reverting Patches [](id=removing-or-reverting-patches)

Have you noticed that the Patching Tool only seems to have an `install` command?
This is because patches are managed not by the command, but by what appears in
the `patches` folder. You manage the patches you have installed by adding or
removing patches from this folder. If you currently have a patch installed and
you don't want it installed, remove it from the `patches` folder. Then run the
`./patching-tool.sh install` command, and the patch is removed. 

If you want to remove all patches you've installed, use the `./patching-tool.sh
revert` command. This removes all patches from your installation.

What we've described so far is the simplest way to use the Patching Tool, but
you can also use the Patching Tool in the most complex, multi-VM, clustered
environments. This is done by using profiles. 

## Cleaning Up

After you've performed your patching procedure (whether you've installed or
removed patches), it's important to clean up Liferay's cache of deployed code.
This ensures that when you start the server, you're using the revision you've
just installed the patches for. This is really easy to do. 

In the Liferay Home folder is a folder called `work`. Remove the contents of
this folder to clear out the cached code. Now you're ready to start your server. 

## Using Profiles with the Patching Tool [](id=using-profiles-with-the-patching-tool)

When you ran the auto-discovery task after installing the Patching Tool, it
created a default profile that points to the application server it discovered.
This is the easiest way to use the Patching Tool, and is great for smaller,
single server installations. But many Liferay installations are sized to serve
millions of pages per day, and the Patching Tool has been designed for this as
well. So if you're running a small, medium, or large cluster of Liferay
machines, you can use the Patching Tool to manage all of them using profiles. 

The auto-discovery task creates a properties file called `default.properties`.
This file contains the detected configuration for your application server. But
you're not limited to only one server which the tool can detect. You can have it
auto-discover other runtimes, or you can manually create new profiles yourself. 

To have the Patching Tool auto-discover other runtimes, you'll need to use a few
more command line parameters: 

    ./patching-tool.sh [name of profile] auto-discovery [path/to/runtime]
 
This will run the same discovery process, but on a path you choose, and the
profile information goes into a `[your profile name].properties` file. 

Alternatively, you can manually create your profiles. Using a text editor,
create a `[profile name].properties` file in the same folder as the Patching
Tool script. You can place the following properties in the file: 

**patching.mode:** This can be `binary` (the default) or `source`, if you're
patching the source tree you're working with. Liferay patches contain both
binary and source patches. If your development team is extending Liferay, you'll
want to provide the patches you install to your development team so they can
patch their source tree. 

**patches.folder:** Specify the location where you'll copy your patches. By
default, this is `./patches`. 

**war.path:** This is a property for which you specify the
location of the Liferay installation inside your application server.
Alternatively, you can specify a .war file here, and you'll be able to patch a
Liferay .war for installation to your application server. 

**global.lib.path:** Specify the location where .jar files on the global
classpath are stored. If you're not sure, search for your `portal-kernel.jar`
file; it's on the global classpath. This property is only valid if your
`patching.mode` is `binary`. 

**liferay.home:** Specify the location where by default the `data`,
`osgi`, and `tools` folders reside.

**source.path:** Specify the location of your Liferay source tree. This property
is only valid if your `patching.mode` is `source`. 

You can have as many profiles as you want, and use the same Patching Tool to
patch all of them. This helps to keep all your installations in sync. 

Great! Now you know how to keep @product@ up to date with the latest patches.
