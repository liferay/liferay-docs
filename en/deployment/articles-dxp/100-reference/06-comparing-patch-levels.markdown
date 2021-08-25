---
header-id: comparing-patch-levels
---

# Comparing Patch Levels

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/maintaining-a-liferay-dxp-installation/patching-liferay/advanced-patching/custom-code-and-patch-compatibility.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

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
- `collisions`: Shows a list of modified files which collide with deployed plugins.

For detailed usage information, run `patching-tool help diff`.

`patching-tool store`: Manages patching level information for the `diff`
command. Your patches must contain source code to store the patch level and to
prepare usable information for the `diff` command. Here are the `store` command
options: 

- `info`: Prints the list of patches which make up the stored patch level.
- `add`: Stores the patch level that can be found in the patches directory.
- `update`: Adds or updates patch level information.
- `rm`: Removes previously stored patch level information.

For detailed usage information, run `patching-tool help store`.
