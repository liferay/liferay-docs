# Working with Patches [](id=working-with-patches)

TODO

## Including '*support-info*' in LESA Tickets [](id=including-support-info-in-lesa-tickets)

To enable Liferay to reproduce subscriber issues, it is critical that the patch
level in a given environment be made available to Liferay. 

To generate the patch level for your environment, use the following command: 

    patching-tool support-info

A text file called `patching-tool-support-info-actual-timestamp.txt` is created
in the patching-tool folder. Please upload this file to the LESA ticket.

## Removing or Reverting Patches [](id=removing-or-reverting-patches)

Have you noticed that the Patching Tool only seems to have an `install` command?
This is because patches are managed not by the command, but by what appears in
the `patches` folder. You manage the patches you have installed by adding or
removing patches from this folder. If you currently have a patch installed that
you don't want, remove it from the `patches` folder. When you run the
`patching-tool install` command, and the patch is removed. 

If you want to remove all patches you've installed, use the `./patching-tool.sh
revert` command. This removes all patches from your installation.

Prior to Fix Pack 13, the OSGi state folder could retain obsolete bundles in
its cache. If you're running a version prior to Fix Pack 13, delete the
*osgi/state* folder in Liferay Home.

## Comparing Patch Levels [](id=comparing-patch-levels)

If you're a developer, the Patching Tool can show you what changed between
different versions. These commands show you information about the different
patch levels:

`patching-tool diff`: Prints the differences between two patch levels. At least
one stored patch level must be available. This command accepts options for
filtering the output: 

- `source`: Shows the source differences between the two patch levels.
- `files`: Shows a list of the modified files.
- `fixed-issues`: Shows a list of LPS/LPE issues from our issue tracking system.

For detailed usage information, run `patching-tool help diff`.

`patching-tool store`: Manages patching level information for diff command. Your
patches must contain source code to store the patch level and to prepare usable information for
the "diff" command. This command has four options: 

- `info`: Prints the list of patches which make up the stored patch level.
- `add`: Stores the patch level that can be found in the patches directory.
- `update`: Adds or updates patch level information.
- `rm`: Removes previously stored patch level information.

For detailed usage information, run `patching-tool help store`.

## Separating the Patches from the @product@ Installation [](id=separating-the-patches-from-the-product-installation)

As of Patching Tool 2.0.6, there's a feature that helps reduce the patched
@product@ bundle size. If the bundle has been patched, you can make it smaller
by moving the restore files out of it.

Patched bundles are large because the restore files by default are stored inside
the web application's WEB-INF folder. These files are required for patching the
@product@ instance again.

If these files were removed, subsequent patching processes would fail. Because
of this, Liferay added an option to separate the patching files from the
@product@ bundle while still preserving restoring them safely when new patches
arrive. To do this, you use this command: 

    patching-tool separate [separation_name] 
 
This command produces a `liferay-patching-files-[separation-name].zip`file in
the Patching Tool's `patches` folder. It contains the necessary files and
metadata for patching, verification, and validation. Once you create this file,
the patch files are removed from their default location and are now only
available in this file. You can now move the file elsewhere to make the bundle's
size smaller. 

**WARNING:** If the product is separated from its patches in this way, you
cannot run most of the Patching Tool commands until the patches are restored.

After the separation process only the following commands can be used:

- auto-discovery
- info
- setup

Any other command returns this:

    This installation does not include data for patching. Please copy the
    liferay-patching-files-[separation-name].zip file into the 'patches' directory
    and run patching-tool setup. 
 
This is how you restore the patch files to your system. Details below. 

## Restoring the Separated Patch Files [](id=restoring-the-separated-patch-files)
 
When you need to patch @product@ again, you must restore the
separated patch artifact. To do this, copy the
`liferay-patching-files-[separation-name].zip` back to the
Patching Tool's `patches` folder and run `patching-tool setup` command.

If the command finds the necessary patching artifact, it restores the patch
files to the bundle. After that, the Patching Tool works like it did prior to
separating the patches. 
