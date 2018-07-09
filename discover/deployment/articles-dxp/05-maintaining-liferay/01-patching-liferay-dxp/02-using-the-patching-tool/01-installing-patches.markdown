# Installing Patches [](id=installing-patches)

Before installing any patches, you must shut down your server. On Windows
operating systems, files in use are locked by the OS, and can't be patched. On
Unix-style systems, you can usually replace files that are running, but the old
ones reside in memory. For these reasons, it is best to shut down @product@
before installing patches. 

Liferay distributes all patches (fix packs and hotfixes) as ZIP files. When you
download a patch, either from a LESA ticket (hotfix) or from the
[Customer Portal](https://web.liferay.com/group/customer)
(fix pack), place it in the Patching Tool's `patches` folder (e.g., `[Liferay
Home]/patching-tool/patches`). To list your installed patches and available
local patches, execute this command: 

    patching-tool info
 
This displays a list of patches you've already installed, along with a list of
patches that *can* be installed from what's in the `patches` folder. 

To install the available patches, use the following steps. First, 
issue the following command: 

    patching-tool install

To make sure the all changed OSGi bundles replace the existing ones, delete the
`osgi/state` folder from the 
[Liferay Home folder](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home). 

+$$$

**Note**: The `osgi/state` folder contains OSGi bundle state information. If an
OSGi bundle's changes in a hot fix or fix pack are internal only, they are
invisible to the OSGi framework, that OSGi bundle stays installed, and its state
information stays unchanged. Hot fixes, for example, may contain in-place
changes that do not use the API. The framework cannot detect such changes.
A fix pack's changes may also be transparent to the framework. For these
reasons, deleting the `osgi/state` folder after applying fix packs and hot fixes
is recommended. 

$$$

+$$$

**Important**: The `osgi/state` folder should ONLY be deleted when working in a 
development environment or when applying a fix pack or hot fix. 

$$$

If there are new database indexes created by the patch, the Patching Tool tells
you to update them. To get the list, run this command:

    patching-tool index-info

Since there's no database connection at patching time, the indexes must be
created at portal startup. If the server has permissions to modify the database
indexes, instruct @product@ to create the indexes automatically at startup by
adding this setting to your `portal-ext.properties` file:

    database.indexes.update.on.startup=true

Otherwise, you must create the indexes manually. Check the 
`patching-tool index-info` command output for more details.

After installing patches, you can execute the `patching-tool info` command to
verify them. 

+$$$

**Note:** If there are any issues with the installed patches, verify that there 
aren't any remaining files from the previous patch installation of a fix pack or
hotfix within the application server cache.

$$$

During the installation, `patching-backup-deps.zip` and `patching-backup.zip`
files are created and stored in the web application's `WEB-INF` folder. These
files are required to restore the @product@'s original state; removing them
disables patching. 

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

As stated previously, hotfixes are short term fixes provided as quickly as
possible, and fix packs are larger bundles of hotfixes provided to all customers
at regular intervals. If you already have a hotfix installed and the fix pack
that contains that hotfix is released, the Patching Tool can manage this for
you. Fix packs always supersede hotfixes; so when you install your fix pack, the
hotfix it contains is uninstalled and the fix pack version is installed in
its place. 

The Patching Tool applies fixes to fix packs automatically. If a new (fixed)
version of a fix pack is released, install it with the Patching Tool. The
Patching Tool uninstalls the old fix pack and installs the new version in its
place. 

## Fix Pack Dependencies [](id=fix-pack-dependencies)

Some hotfixes depend on fix packs. If you attempt to install a hotfix that
depends on a fix pack, the Patching Tool notifies you. Go to the 
[Customer Portal](https://web.liferay.com/group/customer/dxp/downloads/7-1)
and obtain the hotfix dependency. Once all the necessary patches are available
in the `patches` folder, the Patching Tool installs them. 

## Updating the Patching Tool [](id=updating-the-patching-tool)

When a patch you're trying to install requires a Patching Tool update, the
Patching Tool tells you. To update the Patching Tool, download the latest one
from the
[Customer Portal](https://web.liferay.com/group/customer/dxp/downloads/7-1/patching-tool).
Overwrite the existing Patching Tool by unzipping the new one to the
`patching-tool` folder's parent folder. 

## Cleaning Up [](id=cleaning-up)

After you've performed your patching procedure (whether you've installed or
[removed patches](/discover/deployment/-/knowledge_base/7-1/working-with-patches#uninstalling-patches)),
it's important to clean up @product@'s cache of deployed code. This ensures that
you're using the revision you've just installed the patches for when you start
the server. This is really easy to do. 

To clear out the cached code, remove the contents of the `[Liferay Home]/work`
folder. Now you're ready to start your server.
