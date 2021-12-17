---
header-id: patching-tool
---

# Using the Patching Tool

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/maintaining-a-liferay-installation/reference/installing-the-patching-tool.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

The Patching Tool installs, removes, compares, and prepares @product@ patches.
It is pre-installed in @product@ bundles, easy to install into @product@ manual
installations, and easy to update. The Patching Tool's executable scripts
facilitate patching. 

Here are the essentials to get started using the Patching Tool:

- [Installing the Patching Tool](#installing-the-patching-tool) (for manual installations only)
- [Executables](#executables)

## Installing the Patching Tool

@product@ bundles come with the Patching Tool pre-installed (in `[Liferay
Home]/patching-tool`) and pre-configured with the default settings. Skip this
section if you're using a bundle. 

If you installed @product@ manually, however, you must also install the Patching
Tool manually. 

1.  Download the Patching Tool from the
    [Customer Portal](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191019&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191066).

2.  Unzip the Patching Tool to your
    [Liferay Home](/docs/7-2/deploy/-/knowledge_base/d/liferay-home)
    folder (recommended) or to another folder. 

After installing the Patching Tool, you must
[configure it to use your @product@ installation](/docs/7-2/deploy/-/knowledge_base/d/configuring-the-patching-tool).
The `patching-tool` folder you extracted from the Patching Tool ZIP file
contains the Patching Tool, including its executable scripts. 

## Executables

The Unix shell and Windows batch scripts distributed with the Patching Tool make
it easier to use. On Unix systems, run

```bash
./patching-tool.sh parameters
```

On Windows, run

```bash
patching-tool parameters
```

The Windows command `patching-tool` is used in the examples that follow. On
Unix, replace the name of the executable before running the commands. 

Installing patches is next! 
