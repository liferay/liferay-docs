# Working with Patches [](id=working-with-patches)

Here are some things you might need to do with patches:

- [Report Patch Levels to Liferay Support](#including-support-info-in-support-tickets)
- [Uninstall Patches](#uninstalling-patches) 
- [Compare Patch Levels](#comparing-patch-levels)
- [Separate Patches from your Installation](#separating-patches-from-the-installation)

Start with reporting patch levels to Liferay Support.

## Including support-info in Support Tickets [](id=including-support-info-in-support-tickets)

Providing your environment's patch level to Liferay Support is critical for
reproducing issues you report. Write your support information (includes your
patch level) to a file by executing this command: 

    patching-tool support-info

The support information is written to file
`patching-tool-support-info-actual-timestamp.txt` in your `patching-tool`
folder. Please upload this file to the LESA ticket.

## Uninstalling Patches [](id=uninstalling-patches)

Have you noticed that the Patching Tool only seems to have an `install` command?
This is because patches are managed not by the command, but by what appears in
the `patches` folder. You manage the patches you have installed by adding or
removing patches from this folder.

Here's how to uninstall (remove) a patch:

1.  Remove the patch from your `patches` folder. 

2.  Run the `patching-tool install` command. 

To revert ALL patches, run this command:

    patching-tool revert

Now you know how to remove and revert patches. 

## Comparing Patch Levels [](id=comparing-patch-levels)

If you're a developer, the Patching Tool can show you what changed between
different @product@ patches and versions. These commands show you information
about the different patch levels:

`patching-tool diff`: Prints the differences between two patch levels. At least
one stored patch level must be available. This command accepts options for
filtering the output: 

- `source`: Shows the source differences between the two patch levels.
- `files`: Shows a list of the modified files.
- `fixed-issues`: Shows a list of LPS/LPE issues from our issue tracking system.
- `html`: Specify this along with one of the filtering options (`source`, 
    `files`, or `fixed-issues`) and after the patch levels, to write the
    differences to an HTML file
    (`<stored-name-1>-<stored-name-2>-[type]-diff.html`) in the `diffs` folder.
    Additions are colored green and deletions are colored red.

For detailed usage information, run `patching-tool help diff`.

`patching-tool store`: Manages patching level information for diff command. Your
patches must contain source code to store the patch level and to prepare usable
information for the `diff` command. Here are the `store` command options: 

- `info`: Prints the list of patches which make up the stored patch level.
- `add`: Stores the patch level that can be found in the patches directory.
- `update`: Adds or updates patch level information.
- `rm`: Removes previously stored patch level information.

For detailed usage information, run `patching-tool help store`.

## Separating Patches from the Installation [](id=separating-patches-from-the-installation)

The Patching Tool's `separate` command helps reduce the patched @product@
installation size. If the installation has been patched, you can make it smaller
by moving the restore files out of it.

Patched installations are large because the restore files are stored inside the
web application's `WEB-INF` folder by default. These files are required for
patching the installation again.

If these files are removed, subsequent patching processes fail. Because of
this, Liferay added an option to separate the patching files from the
installation while still preserving and restoring them safely when new patches
arrive. To do this, use this command: 

    patching-tool separate [separation_name] 
 
This command produces a `liferay-patching-files-[separation-name].zip` file in
the Patching Tool's `patches` folder. It contains the necessary files and
metadata for patching, verification, and validation. Once you create this file,
the patch files are removed from their default location and are now only
available in this file. You can store this file elsewhere to reduce your
installation's size. 

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
 
When you need to patch @product@ again, you must restore the separated patch
artifact. To do this, copy the `liferay-patching-files-[separation-name].zip`
back to the Patching Tool's `patches` folder and run `patching-tool setup`
command.

The command finds the necessary patching artifact and restores the patch files
to the installation. After that, the Patching Tool works like it did prior to
separating the patches. 
