# Installing Patches [](id=installing-patches)

The first thing you must do when installing patches is to shut down your server.
On Windows operating systems, files that are in use are locked by the OS, and
can't be patched. On Unix-style systems, you can usually replace files that are
running, but that still leaves the old ones loaded in memory. So your best bet
is to shut down the application server that's running @product@ before you install
a patch. 

Liferay distributes patches as `.zip` files, whether they are hotfixes or fix
packs. When you receive one, either via a LESA ticket (hotfix) or through
downloading a fix pack from the [Customer Portal](https://web.liferay.com/group/customer), 
place it in the `patches` folder that's inside the Patching Tool's home
folder. Once you've done that, it's a simple matter to install it. First,
execute

    patching-tool info
 
This displays a list of patches you've already installed, along with a list of
patches that *can* be installed from what's in the `patches` folder. 

To install the available patches, use the following steps. First, 
issue the following command: 

    patching-tool install

To make sure the all changed OSGi bundles replace the existing ones, it is
recommended to delete the `osgi/state` folder from the
[Liferay Home folder](/discover/deployment/-/knowledge_base/7-0/installing-product#liferay-home). 

+$$$

**Important**: The `osgi/state` folder should ONLY be deleted when working in a 
development environment or when applying a fix pack or hot fix. 

$$$

+$$$

**Note**: The `osgi/state` folder in the contains OSGi bundle state information.
If an OSGi bundle's changes in a hot fix or fix pack are internal only and are,
therefore, invisible to the OSGi framework, that OSGi bundle stays installed and
its state information stays unchanged. Hot fixes, for example, may contain
in-place changes that do not use the API---the framework cannot detect such
changes. A fix pack's changes may also be transparent to the framework. For
these reasons, deleting the `osgi/state` folder after applying fix packs and hot
fixes is recommended. 

$$$

If there are new database indexes created by the patch, the Patching Tool tells
you to update them. To get the list, run this command:

    patching-tool index-info

Since there's no database connection at patching time, the indexes must be
created at portal startup. To have the indexes created automatically, add the
following line to the `portal-ext.properties` file if the server has permissions
to modify the indexes on the database:

    database.indexes.update.on.startup=true

Otherwise, you must create the indexes manually. Check the output of the
`patching-tool index-info` command for more details.

Once your patches have been installed, you can verify them by using the
`patching-tool info` command, which now shows your patches in the list of
installed patches. 

+$$$

**Note:** If there are any issues with the installed fixes, verify that there aren't
any remaining files from the previous patch installation of a fix pack or
hotfix within the application server cache.

$$$

During the installation, `patching-backup-deps.zip` and `patching-backup.zip`
files are created and stored in the `ROOT/WEB-INF` folder. These files are
necessary to restore the @product@'s original state; removing them would disable
further patching. 

+$$$

**Note:** When installing patches, @product@'s `web.xml` is always overwritten
by the one contained in the patch. If you've customized `web.xml`, you must
re-implement your customizations after installing a patch. 

$$$

The `patching-backup.zip` file is necessary for installing future patches,
because the Patching Tool reverts the installed fix pack before installing a new
one. To revert the installed fix pack, it examines the contents of the
`patching-backup.zip` to determine the changes that it needs to revert. 

## Handling Hotfixes and Patches [](id=handling-hot-fixes-and-patches)

As stated above, hotfixes are short term fixes provided as quickly as possible,
and fix packs are larger bundles of hotfixes provided to all customers at
regular intervals. If you already have a hotfix installed and the fix pack
that contains that hotfix is released, the Patching Tool can manage this for
you. Fix packs always supersede hotfixes, so when you install your fix pack,
the hotfix that it contains is uninstalled, and the fix pack version is
installed in its place. 

Sometimes there can be a fix to a fix pack. This is also handled automatically.
If a new version of a fix pack is released, you can use the Patching Tool to
install it. The Patching Tool uninstalls the old fix pack and installs the new
version in its place. 

## Fix Pack Dependencies [](id=fix-pack-dependencies)

Some hotfixes require a fix pack to be installed first. If you attempt to
install a hotfix that depends on a fix pack, the Patching Tool notifies
you. Go to the customer portal and obtain the hotfix dependency. Once all the
necessary patches are available in the `patches` folder, the Patching Tool 
installs them. 

The Patching Tool can also remove patches. 

## Cleaning Up [](id=cleaning-up)

After you've performed your patching procedure (whether you've installed or
removed patches), it's important to clean up @product@'s cache of deployed code.
This ensures that when you start the server, you're using the revision you've
just installed the patches for. This is really easy to do. 

In the Liferay Home folder is a folder called `work`. Remove the contents of
this folder to clear out the cached code. Now you're ready to start your server.
