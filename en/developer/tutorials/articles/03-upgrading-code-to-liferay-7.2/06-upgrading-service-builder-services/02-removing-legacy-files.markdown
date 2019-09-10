---
header-id: removing-legacy-files
---

# Removing Legacy Files

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Upgrading Service Builder Services</p><p>Step 1 of 3</p>
</div>

The first step in upgrading your Service Builder services is to delete legacy
files. These legacy files include

- `portlet-spring.xml`
- `shard-data-source-spring.xml`
- `/src/main/resources/META-INF/` (folder)

When initiating this step, these files/folders are automatically removed from
your Service Builder project.

If you're manually upgrading your code, delete the listed files/folders above.

Next, you'll convert your Service Builder Module from Spring DI to OSGi DS.
