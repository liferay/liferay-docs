# Patching Liferay [](id=patching-liferay)

# The Patching Tool

## Installing the Patching Tool [](id=installing-the-patching-tool)

If you're using a Liferay bundle, the Patching Tool is already
installed. When an update is necessary to install a patch, the patching tool will automatically ask for an update.

You follow the same procedure whether you're installing or upgrading the
Patching Tool. Once you've obtained it from the Customer Portal, unzip it anywhere in the filesystem. Generally it's a good idea to keep it together with the @product@ installation.

Upgrading is easy: override the previous Patching Tool with newest one by overriding the files.

## Executables [](id=executables)

The Patching Tool is a Java based application. The distribution contains shell / .bat scripts to make it easier to use. On Unix systems you can simply run:

    ./patching-tool.sh parameters

On Windows you should run:

    patching-tool parameters

This document will continue using the latter method, on Unix, please replace the name of the executable before running the scripts.

## Installing Patches [](id=installing-patches)

The absolute first thing you must do when installing one or more patches is to
shut down your server. On Windows operating systems, files that are in use are
locked by the OS, and won't be patched. On Unix-style systems, you can usually
replace files that are running, but of course that still leaves the old ones
loaded in memory. So your best bet is to shut down the application server that's
running Liferay before you install a patch. 

Liferay distributes patches as `.zip` files, whether they are hotfixes or fix
packs. When you receive one, either via a LESA ticket (hotfix) or through
downloading a fix pack from the customer portal, you'll need to place it in the
`patches` folder, which is inside the Patching Tool's home folder. Once you've
done that, it's a simple matter to install it. First, execute

    patching-tool info
 
This shows you a list of patches you've already installed, along with a list of
patches that *can* be installed, from what's in the `patches` folder. 

There are two ways to install patches: 

- You can install patches manually

To install the available patches manually, use the following steps. First, 
issue the following command: 

    patching-tool install

+$$$

**Checkpoint:**
After a successful patch installation you have to delete the *osgi/state* folder
if it exists in your Liferay Home directory.  

$$$

If there are new indexes created by the patch, the Patching Tool notifies you
to update them. To get the list, run this command:

    patching-tool index-info

As there's no database connection at patching time, the indexes need to be
created at portal startup. In order to have the indexes created automatically,
add the following line to the `portal-ext.properties` file if the server has
permissions to modify the indexes on the database:

    database.indexes.update.on.startup=true

Otherwise, you have to create the indexes manually. Check the output of the
`patching-tool index-info` command for more details.

Once your patches have been installed, you can verify them by using the
`patching-tool info` command, which now shows your patch in the list of
installed patches. 

+$$$

Note: If there are any issues with the installed fixes, verify that there aren't any remaining files within the application server cache from the previous patch installation of a fix pack or hotfix.

$$$

During the installation, patching-backup-deps.zip and patching-backup.zip files will be created and stored in the ROOT/WEB-INF folder. Do NOT delete the patching-backup-deps.zip and patching-backup.zip files.

The patching-backup.zip file is necessary for installing future patches. This is because the patching-tool reverts the installed fix pack before installing a new one. In order to revert the installed fix pack, it examines the contents of the patching-backup.zip in order to determine what changes have been made that it needs to revert. 

While removing the patching-backup.zip file does not affect the patching-tool's report of what's installed in the bundle, we do not recommend removing the file from the ROOT/WEB-INF because doing so will cause future installations to fail.

### Comparing patch levels
1) The diff command: 
 `patching-tool diff`:
This command prints the differences between two patch levels to the output. At least one stored patch level must be available for the tool.

You can pass options to filter the output to "source", "files" and "fixed-issues":

- source: You will receive an output of the source differences between the two patch levels.
- files: The output will contain only the file names.
- fixed-issues: You will receive a list of LPS/LPEs.

Liferay Support uses these numbers to track certain fixes.

For the detailed usage information, please run `patching-tool help diff` command.

2) Storing a patch level:
`patching-tool store:` Manages patching level information for diff command. The command needs patches with sources to be able to prepare usable information for the "diff" command and to be able to store the actual patch level.

There are four options of this command:

- info: Prints the list of patches which build up the stored patch level.
- add: This option stores the patch level which can be found in the patches directory.
- update: Add or update patch level information.
- rm: Removes a previously stored patch level information.

Please find the usage details by running the `patching-tool help store` command.


### Handling Hotfixes and Patches [](id=handling-hot-fixes-and-patches)

As stated above, hotfixes are short term fixes provided as quickly as possible
and fix packs are larger bundles of hotfixes provided to all customers at
regular intervals. If you already have a hotfix installed and the fix pack
which contains that hotfix is released, the Patching Tool will manage this for
you. Fix packs always supersede hotfixes, so when you install your fix pack,
the hotfix that it already contains is uninstalled, and the fix pack version is
installed in its place. 

Sometimes there can be a fix to a fix pack. This is also handled automatically.
If a new version of a fix pack is released, you can use the Patching Tool to
install it. The Patching Tool uninstalls the old fix pack and installs the new
version in its place. 

### Including '*support-info*' in LESA Tickets

In the process of reproducing subscriber issues, it is critical that the patches in a given environment be made available to Liferay. 

To output the patching information for your environment, the following command must be utilized: `patching-tool support-info`
 
A text file will be created in the patching-tool folder:
*patching-tool-support-info-actual-timestamp.txt*
Please upload this file to the LESA ticket.


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
`patching-tool install` command, and the patch is removed. 

If you want to remove all patches you've installed, use the `./patching-tool.sh
revert` command. This removes all patches from your installation.

The OSGi state folder may contain obsolete bundles in its cache that need to be removed. Delete the *osgi/state* folder in your Liferay Home if it exists.

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