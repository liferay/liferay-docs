---
header-id: working-with-patches
---

# Working with Patches

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/maintaining-a-liferay-installation/patching-dxp-7-3-and-earlier/advanced-patching-for-dxp-7-2.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Here are some things you might need to do with patches:

- Report Patch Levels to Liferay Support
- Uninstall Patches
- Show collisions between patches and deployed plugins
- Separate Patches from your Installation

Start with reporting patch levels to Liferay Support.

## Including support-info in Support Tickets

Providing your environment's information (e.g., hardware architecture) and patch
level to Liferay Support is critical for reproducing your issues. Write your
support information (including your patch level) to a file by executing this
command: 

```bash
patching-tool support-info
```

The support information is written to file
`patching-tool-support-info-actual-timestamp.txt` in your `patching-tool`
folder. Please upload this file to the
[Help Center](https://help.liferay.com/hc)
ticket.

## Uninstalling Patches

Have you noticed that the Patching Tool only seems to have an `install` command?
This is because patches are managed not by the command, but by what appears in
the `patches` folder. You manage the patches you have installed by adding or
removing patches from this folder.

Here's how to uninstall (remove) a patch:

1.  Remove the patch from your `patches` folder. 

2.  Run the `patching-tool install` command. 

To revert ALL patches, run this command:

```bash
patching-tool revert
```

Now you know how to remove and revert patches. You can also 
compare patch levels. See the [reference guide](/docs/7-2/deploy/-/knowledge_base/d/comparing-patch-levels) 
for a list of the available commands. 

## Showing collisions between patches and deployed plugins

Some patches update files you might have customized via a plugin. The
`patching-tool list-collisions` command lists differences (collisions) between
installed patch files and your plugin's version of them. Here's the command:

```bash
patching-tool list-collisions
```

It is an alias for the following diff command:

```bash
patching-tool diff collisions files _base
```

`_base` is the literal patch level name. Collisions are only listed for
installed patches that contain source code files. 

| **Note:** As of Patching Tool 2.0.9, `patching-tool list-collisions` lists only
| JSP file collisions in fragment bundles.

## Separating Patches from the Installation

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

```bash
patching-tool separate [separation_name] 
```

This command produces a `liferay-patching-files-[separation-name].zip` file in
the Patching Tool's `patches` folder. It contains the necessary files and
metadata for patching, verification, and validation. Once you create this file,
the patch files are removed from their default location and are now only
available in this file. You can store this file elsewhere to reduce your
installation's size. 

**WARNING:** If the product is separated from its patches in this way, you
cannot run most of the Patching Tool commands until the patches are restored.

After the separation process only the following commands can be used:

- `auto-discovery`
- `info`
- `setup`

Any other command returns this:

```bash
This installation does not include data for patching. Please copy the
liferay-patching-files-[separation-name].zip file into the 'patches' directory
and run patching-tool setup. 
```

This is how you restore the patch files to your system. Details below. 

## Restoring the Separated Patch Files
 
When you need to patch @product@ again, you must restore the separated patch
artifact. To do this, copy the `liferay-patching-files-[separation-name].zip`
back to the Patching Tool's `patches` folder and run `patching-tool setup`
command.

The command finds the necessary patching artifact and restores the patch files
to the installation. After that, the Patching Tool works like it did prior to
separating the patches. 
