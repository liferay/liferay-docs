---
header-id: installing-patches-on-the-liferay-de-war
---

# Installing patches on the @product-ver@ WAR

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/maintaining-a-liferay-installation/patching-dxp-7-3-and-earlier/installing-patches-for-dxp-7-3-and-earlier.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

If you
[installed @product@ manually](/docs/7-1/deploy/-/knowledge_base/d/installing-product-on-tomcat)
as a WAR file on a supported application server, you must apply patches to the
WAR file and supporting files and re-deploy them. This article shows you how to
do that.

## Prerequisites

Download the necessary artifacts from the 
[Customer Portal:](https://customer.liferay.com/downloads)

- @product@ WAR file (`liferay-dxp-[version].war`)
- Dependencies ZIP file (`liferay-dxp-dependencies-[version].zip`)
- OSGi JARs ZIP file (`liferay-dxp-osgi-[version].zip`) 
- [Latest Patching Tool](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191019&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191066)

## Install the patch on the @product@ WAR and artifacts

1.  Create an arbitrary folder. Unzip the dependency artifacts and the 
    Patching Tool into it. The folder contents should look like this:

    - `[patching-home]/`
        - `liferay-dxp-dependencies-[version]/` &larr; Unzipped Dependencies
        - `osgi/` &larr; Unzipped OSGi JARs
        - `patching-tool/` &larr; Unzipped Patching Tool
        - `liferay-dxp-[version].war/` &larr; @product@ WAR File

2.  Create the default profile configuration file in the Patching Tool folder:
    `patching-home/patching-tool/default.properties`. The contents should look
    like this:

```properties
patching.mode=binary
war.path=../../patching-home/liferay-dxp-[version].war
global.lib.path=../../patching-home/liferay-dxp-dependencies-[version]
liferay.home=../../patching-home
```

    If you're using a different OSGi folder structure, you can specify it as
    the [Patching Tool Advanced Configuration](/docs/7-2/deploy/-/knowledge_base/d/patching-tool-advanced-configuration)
    documentation describes: 

```properties
module.framework.core.path=/osgi-home/osgi/core
module.framework.marketplace.path=/osgi-home/osgi/marketplace
module.framework.modules.path=/osgi-home/osgi/modules
module.framework.portal.path=/osgi-home/osgi/portal
module.framework.static.path=/osgi-home/osgi/static	
```

3.  Download the patch (fix pack or hotfix) to install and put it in a folder 
    called `patches` in your Patching Tool folder 
    (i.e. `[patching-home]/patching-tool/patches`).

4.  Execute the Patching Tool's `info` command:

```bash
/patching-home/patching-tool> patching-tool info
Loading product and patch information...
Product information:
  * installation type: binary
  * build number: 7210
  * service pack version:
    - available SP version: Not available
    - installable SP version: Not available
  * patching-tool version: 2.0.12
  * time: 2019-06-03 18:30Z
  * host: 91WRQ72 (8 cores)
  * plugins: no plugins detected

Currently installed patches: -
...
```

5.  Install the patch. 

```bash
/patching-home/patching-tool> patching-tool.sh  install
One patch is ready to be installed. Applying dxp...
Cleaning up: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
Installing patches: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%...100%]
The installation was successful. One patch is installed on the system.
```

Great! You have successfully patched the artifacts, and they are ready to be
deployed on any supported Application Server.

## Related Topics

[Patching Tool Advanced Configuration](/docs/7-2/deploy/-/knowledge_base/d/patching-tool-advanced-configuration)

[Deploying @product@](/docs/7-2/deploy/-/knowledge_base/d/deploying-product)
