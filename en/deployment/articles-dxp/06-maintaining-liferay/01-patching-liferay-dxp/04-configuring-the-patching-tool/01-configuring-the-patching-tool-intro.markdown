---
header-id: configuring-the-patching-tool
---

# Configuring the Patching Tool

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/maintaining-a-liferay-installation/reference/configuring-the-patching-tool.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

The Patching Tool installs @product@ patches. It ships with prepackaged
@product@ bundles. If any of the following scenarios describes your @product@
installation, however, you must configure the Patching Tool manually:

- Installed @product@ manually on an existing application server
- Customized your @product@ folder structure
- Running in a cluster

If none of the above scenarios describe your installation, you can skip this
section.

If you installed @product@ manually, you must also install the Patching Tool
manually. Download it from the
[Customer Portal](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191019&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191066).
Unzipping it to your
[Liferay Home](/docs/7-2/deploy/-/knowledge_base/d/liferay-home)
folder is the easiest way to use it. 

Read on to configure the Patching Tool for your environment. 
