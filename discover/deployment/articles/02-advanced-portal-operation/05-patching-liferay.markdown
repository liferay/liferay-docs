# Patching Liferay [](id=patching-liferay)

![EE Only Feature](../../images/ee-feature-web.png)

While we strive for perfection with every release of Liferay Portal, the reality
of the human condition dictates that releases of the product may not be as
perfect as originally intended. But we've planned for that. Included with every
Liferay bundle is a patching tool that can handle the installation of two types
of patches: hot fixes and fix packs. 

A hot fix is provided to a customer when a customer contacts Liferay about an
issue, and Liferay's support team--working with the customer--determines that
the problem is indeed an issue with the product that needs to be fixed. Support
fixes the bug and provides a hot fix to the customer immediately. This is a
short-term fix that solves the issue for the customer as quickly as possible. 

On a regular schedule, these hot fixes are bundled together into fix packs. Fix
packs are provided to all of Liferay's customers and are component-based. This
means any issues with the content management system will be bundled together
separately from issues with another component, such as the message boards. This
lets you determine which patches are critical and which are not, based on your
usage. Of course, if Liferay issues a security advisory, that's something you're
always going to want to patch. 

Now that you know what patching is all about, let's check out the tool. 

## Installing the patching tool [](id=installing-the-patching-tool)

If you're using a Liferay bundle, congratulations! The patching tool is already
installed. Your job isn't done yet, however, because Liferay *might* have
updated the patching tool. Always check the Customer Portal to see if the
patching tool has been updated first. But even if you forget to check, the
patching tool will tell you if it needs to be updated when you run it. A lot of
planning and forethought has gone into the patching system to make it run as
smoothly as possible.

You follow the same procedure whether you're installing or upgrading the
patching tool. Once you've obtained it from the customer portal, unzip it to the
Liferay Home folder. This is the folder where you've placed your
`portal-ext.properties` file and where by default the `data` folder resides.
This is generally one folder up from where your application server is installed,
but some application servers are different. If you don't know where Liferay Home
is on your system, check chapter 14 to see where this folder is for your
specific application server.

If you're upgrading the patching tool, all you need to do is unzip the new
version on top of the old version. Note that if you're doing this on LUM (Linux,
Unix, Mac) machines, you'll need to make the `patching-tool.sh` script
executable.

After the patching tool is installed, you need to let it auto-discover your
Liferay installation. Then it will determine what your release level is and what
your application server environment is. This is a simple command to run on LUM:

    ./patching-tool.sh auto-discovery
 
or on Windows: 

    patching-tool auto-discovery
 
From here on, for brevity we'll use the LUM version of the command. Why? Because
Liferay is open source; there's no open source variant of Windows (ReactOS is
still in alpha, so it doesn't count); and therefore my (RS) unscientific
impression is that more people will run Liferay on open source technology than
not. If I'm wrong, I'm wrong, but there are still many other examples of
documentation that defaults to Windows, so we still get to be different. 

If you've installed the patching tool in a non-standard location, you'll have to
give this command another parameter to point it to your Liferay installation.
For example, if you've installed a Liferay/Tomcat bundle in `/opt/Liferay`,
you'd issue this command: 

    ./patching-tool.sh auto-discovery /opt/Liferay/tomcat-7.0.21
 
In all, this is pretty simple. Now let's see how to use the patching tool to get
your patches installed. 

## Installing patches [](id=installing-patches)

The absolute first thing you must do when installing one or more patches is to
shut down your server. On Windows operating systems, files that are in use are
locked by the OS, and won't be patched. On LUM systems, you can generally
replace files that are running, but of course that still leaves the old ones
loaded in memory. So your best bet is to shut down the application server that's
running Liferay before you install a patch. 

Liferay distributes patches as `.zip` files, whether they are hot fixes or fix
packs. When you receive one, either via a LESA ticket (hot fix) or through
downloading a fix pack from the customer portal, you'll need to place it in the
`patches` folder, which is inside the patching tool's home folder. Once you've
done that, it's a simple matter to install it. First, execute

    ./patching-tool.sh info
 
This shows you a list of patches you've already installed, along with a list of
patches that *can* be installed, from what's in the `patches` folder. To install
the available patches, issue the following command: 

    ./patching-tool.sh install

Liferay copies files into the plugins in deployment time. If these files are
patched in the portal, they need to be updated in the plugins as well. In these
cases, the patching tool notifies you about the change. You can run the
following command to update these files automatically:

    ./patching-tool.sh update-plugins

If you do not wish to have the patching tool update the plugins, it's enough to
re-deploy them. If there are new indexes created by the patch, the patching tool
notifies you to update them. To get the list, run this command:

    ./patching-tool.sh index-info

As there's no database connection at patching time, the patches needed to be
created at portal startup. In order to get the indexes automatically created,
add the following line to the `portal-ext.properties` file if the server has
permissions to modify the indexes on the database:

    database.indexes.update.on.startup=true

Otherwise, you have to create the indexes manually. Check the output of the
`./patching-tool index-info` command for more details.

Once your patches have been installed, you can verify them by using the
`./patching-tool.sh info` command, which now shows your patch in the list of
installed patches. Next, let's look now at how to manage your patches. 

### Handling hot fixes and patches [](id=handling-hot-fixes-and-patches)

As stated above, hot fixes are short term fixes provided as quickly as possible
and fix packs are larger bundles of hot fixes provided to all customers at
regular intervals. If you already have a hot fix installed, and the fix pack
which contains that hot fix is released, you can rest assured the patching tool
will manage this for you. Fix packs always supersede hot fixes, so when you
install your fix pack, the hot fix that it already contains is uninstalled, and
the fix pack version is installed in its place. 

Sometimes there can be a fix to a fix pack. This is also handled automatically.
If a new version of a fix pack is released, you can use the patching tool to
install it. The patching tool uninstalls the old fix pack and installs the new
version in its place. 

### Fix pack dependencies [](id=fix-pack-dependencies)

Some fix packs require other fix packs to be installed first. If you attempt to
install a fix pack that depends on another fix pack, the patching tool will
notify you of this so you can go to the customer portal and obtain the fix pack
dependency. Once all the necessary fix packs are available in the `patches`
folder, the patching tool will install them. 

The patching tool can also remove patches. 

## Removing or reverting patches [](id=removing-or-reverting-patches)

Have you noticed that the patching tool only seems to have an `install` command?
This is because patches are managed not by the command, but by what appears in
the `patches` folder. You manage the patches you have installed by adding or
removing patches from this folder. If you currently have a patch installed and
you don't want it installed, remove it from the `patches` folder. Then run the
`./patching-tool.sh install` command, and the patch is removed. 

If you want to remove all patches you've installed, use the `./patching-tool.sh
revert` command. This removes all patches from your installation.

What we've described so far is the simplest way to use the patching tool, but
you can also use the patching tool in the most complex, multi-VM, clustered
environments. This is done by using profiles. 

## Using profiles with the patching tool [](id=using-profiles-with-the-patching-tool)

When you ran the auto-discovery task after installing the patching tool, it
created a default profile that points to the application server it discovered.
This is the easiest way to use the patching tool, and is great for smaller,
single server installations. But we realize many Liferay installations are sized
accordingly to serve millions of pages per day, and the patching tool has been
designed for this as well. So if you're running a small, medium, or large
cluster of Liferay machines, you can use the patching tool to manage all of them
using profiles. 

The auto-discovery task creates a properties file called `default.properties`.
This file contains the detected configuration for your application server. But
you're not limited to only one server which the tool can detect. You can have it
auto-discover other runtimes, or you can manually create new profiles yourself. 

To have the patching tool auto-discover other runtimes, you'll need to use a few
more command line parameters: 

    ./patching-tool.sh [name of profile] auto-discovery [path/to/runtime]
 
This will run the same discovery process, but on a path you choose, and the
profile information will go into a `[your profile name].properties` file. 

Alternatively, you can manually create your profiles. Using a text editor,
create a `[profile name].properties` file in the same folder as the patching
tool script. You can place the following properties in the file: 

**patching.mode:** This can be `binary` (the default) or `source`, if you're
patching the source tree you're working with. Liferay patches contain both
binary and source patches. If your development team is extending Liferay, you'll
want to provide the patches you install to your development team so they can
patch their source tree. 

**patches.folder:** Specify the location where you'll copy your patches. By
default, this is `./patches`. 

**war.path:** No, no one's angry. This is a property for which you specify the
location of the Liferay installation inside your application server.
Alternatively, you can specify a .war file here, and you'll be able to patch a
Liferay .war for installation to your application server. 

**global.lib.path:** Specify the location where .jar files on the global
classpath are stored. If you're not sure, search for your `portal-service.jar`
file; it's on the global classpath. This property is only valid if your
`patching.mode` is `binary`. 

**source.path:** Specify the location of your Liferay source tree. This property
is only valid if your `patching.mode` is `source`. 

You can have as many profiles as you want, and use the same patching tool to
patch all of them. This helps to keep all your installations in sync. 

Now that you know how to patch an existing installation of Liferay, let's turn
to how you'd upgrade Liferay from an older release to the current release.
