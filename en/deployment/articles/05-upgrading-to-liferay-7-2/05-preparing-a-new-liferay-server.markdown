---
header-id: preparing-a-new-product-server-for-data-upgrade
---

# Preparing a New @product@ Server for Data Upgrade

[TOC levels=1-4]

To upgrade your @product@ database, prepare a new server for hosting
@product-ver@. You'll use this server to run the database upgrade and run
@product-ver@. Then you can run your production server while you're configuring
a new server to host @product-ver@ exactly the way you want. 

| **Note:** these steps can be done in parallel with any of the upgrade 
| preparation steps: planning for deprecated apps, testing upgrades on a 
| @product@ backup copy, or preparing to upgrade the @product@ database. 

Get the latest fixes for @product-ver@ by requesting an upgrade patch. 

## Request an Upgrade Patch from Liferay Support (Liferay DXP only)

An *upgrade patch* contains the latest fix pack and hot fixes planned for the
next service pack. Upgrade patches provide the latest fixes available for your
data upgrade. 

## Install Liferay

[Install @product@ on your application server](/docs/7-2/deploy/-/knowledge_base/d/deploying-product)
or
[use @product@ bundled with your application server of choice](/docs/7-2/deploy/-/knowledge_base/d/installing-product). 

| **Important:** Do not start your application server. It's not ready to start 
| until after the @product@ database upgrade. 

## Install the Latest Upgrade Patch or Fix Pack (Liferay DXP only)

Install the upgrade patch (if you requested it from Liferay Support) or the 
[latest Fix Pack](https://help.liferay.com/hc/en-us/articles/360028810452-Patching-Liferay-DXP). 

## Migrate Your OSGi Configurations (7.0+)

Copy your
[OSGi configuration files](/docs/7-2/user/-/knowledge_base/u/understanding-system-configuration-files)
(i.e., `.config` files) to your new server's `[Liferay Home]/osgi/configs`
folder. 

## Migrate Your Portal Properties 

It is likely that you have overridden portal properties to customize your
installation. If so, you must update the properties files (e.g.,
`portal-setup-wizard.properties` and `portal-ext.properties`) to @product-ver@.
For features that use OSGi Config Admin, you must convert your properties to
OSGi configurations. As you do this, you must account for property changes in
all versions of @product@ since your current version up to and including
@product-ver@. Start with updating your portal properties. 

### Update Your Portal Properties 

If you're coming from a version prior to Liferay Portal 6.2, start with these
property-related updates:

-   If you're on Liferay Portal 6.1,
    [adapt your properties to the new defaults that Liferay Portal 6.2 introduced](/docs/6-2/deploy/-/knowledge_base/d/upgrading-liferay#review-the-liferay-6). 

-   If you're on Liferay 6.0.12, 
    [migrate the Image Gallery](/docs/6-2/deploy/-/knowledge_base/d/upgrading-liferay#migrate-your-image-gallery-images).

-   If you have a sharded environment,
    [configure your upgrade to generate a non-sharded environment](/docs/7-2/deploy/-/knowledge_base/d/upgrading-a-sharded-environment).

-   Liferay's image sprite framework is deprecated as of 7.2 and is disabled by 
    default. The framework requires scanning plugins for image sprites. If you
    don't use the framework, there's no need for it to scan for images sprites.
    If you use the framework, enable it by overriding the default
    `sprite.enabled` portal property (new in 7.2) value with the following
    setting in a
    [`portal-ext.properties`](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
    file: 

    ```properties
    sprite.enabled=true
    ```

| **Note:** You can build image sprites using any framework you like and deploy
| them in your plugins. 

When a new version of @product@ is released, there are often changes to default
settings, and this release is no different. If you rely on the defaults from
your old version, you should review the changes and decide to keep the defaults
from your old version or accept the defaults of the new. 

Because no existing properties changed from 7.1 to 7.2, here's a list of the 6.2
properties that have changed in 7.2: 

```properties
users.image.check.token=false
organizations.types=regular-organization,location
organizations.rootable[regular-organization]=true
organizations.children.types[regular-organization]=regular-organization,location
organizations.country.enabled[regular-organization]=false
organizations.country.required[regular-organization]=false
organizations.rootable[location]=false
organizations.country.enabled[location]=true
organizations.country.required[location]=true
layout.set.prototype.propagate.logo=true
editor.wysiwyg.portal-web.docroot.html.taglib.ui.discussion.jsp=simple
web.server.servlet.check.image.gallery=true
blogs.trackback.enabled=true
discussion.comments.format=bbcode
discussion.max.comments=0
dl.file.entry.thumbnail.max.height=128
dl.file.entry.thumbnail.max.width=128
```

This property was removed:

```properties
organizations.children.types[location]
```

The latest
[portal properties reference](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html)
provides property details and examples. Some properties are replaced by OSGi
configurations. 

### Convert Applicable Properties to OSGi Configurations 

Properties in modularized features have changed and must now be deployed
separately in
[OSGi configuration files](/docs/7-2/user/-/knowledge_base/u/system-settings#exporting-and-importing-configurations) 
(OSGi Config Admin).

Use the
[`blade upgradeProps`](/docs/7-2/reference/-/knowledge_base/r/blade-cli)
command to scan your `portal-ext.properties` file to discover which properties
are now set via OSGi Config Admin. You can also check the upgrade log from
previous attempts for traces like these:

```properties
2019-03-09 17:05:17.678 ERROR [main][VerifyProperties:161] Portal property "layout.first.pageable[link_to_layout]" is obsolete
2019-03-09 17:05:17.679 ERROR [main][VerifyProperties:136] Portal property "journal.article.check.interval" was modularized to com.liferay.journal.web as "check.interval"
```

| **Tip:** The Control Panel's *Configuration &rarr; System Settings* screens 
| are the most accurate way to create `.config` files. Use them to
| [export a screen's configuration](/docs/7-2/user/-/knowledge_base/u/system-settings#exporting-and-importing-configurations)
| to a `.config` file. 

## Update Your Database Driver 

Install the recommended database driver and update your database connection
driver specified in your `portal-ext.properties`. See the [Database
Templates](/docs/7-2/deploy/-/knowledge_base/d/database-templates). 

## Configure Your Documents and Media File Store 

General document store configuration (e.g., `dl.store.impl=[File Store Impl
Class]`) continues to be done using `portal-ext.properties`. But here's what's
changed for document storage:

-   Store implementation class package names changed from 
    `com.liferay.portlet.documentlibrary.store.*` in Liferay Portal 6.2 to
    `com.liferay.portal.store.*` in @product@ 7.0+. Make sure your
    `portal-ext.properties` file sets `dl.store.impl` in one of these ways:

    ```properties
    dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore
    dl.store.impl=com.liferay.portal.store.db.DBStore
    dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore
    dl.store.impl=com.liferay.portal.store.s3.S3Store
    ```

-   JCR Store was deprecated in @product@ 7.0. The
    [Document Repository
    Configuration](/docs/7-2/deploy/-/knowledge_base/d/document-repository-configuration)
    documentation describes other store options. [Migrate to a supported
    document store](/docs/7-2/user/-/knowledge_base/u/server-administration)
    before upgrading your data. 

-   CMIS Store was deprecated since 7.0.10 Fix Pack 14 and was removed in 
    @product@ 7.2. The [Document Repository
    Configuration](/docs/7-2/deploy/-/knowledge_base/d/document-repository-configuration)
    documentation describes other store options. [Migrate to a supported
    document store](/docs/7-2/user/-/knowledge_base/u/server-administration)
    before upgrading your data. 

-   Since @product@ 7.0, document store type-specific configuration (e.g., 
    specific to Simple File Store, Advanced File Store, S3, etc.) is done in the
    Control Panel at *Configuration &rarr; System Settings &rarr; File Storage*
    or using OSGi configuration files (`.config` files). Type specific
    configuration is no longer done using `portal-ext.properties`. 

For example, these steps to create a `.config` file specifying a root file
location for a Simple File Store or Advanced File Store:
 
1.  Create a `.config` file named after your store implementation class.

    Simple File Store: 
    `com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration.config`

    Advanced File Store:
    `com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.config`
 
2.  Set the following `rootDir` property and replace 
    `{document_library_path}` with  your file store's path.

    ```properties
    rootDir="{document_library_path}"
    ```

3.  Copy the `.config` file to your `[Liferay Home]/osgi/configs` folder.

The
[Document Repository Configuration](/docs/7-2/deploy/-/knowledge_base/d/document-repository-configuration)
provides more document store configuration details. 

## Configure Kerberos in place of NTLM 

If you're using NTLM to authenticate Microsoft Windows &trade; accounts with
@product@, switch to using
[Kerberos](/docs/7-2/deploy/-/knowledge_base/d/authenticating-with-kerberos).
Security vulnerabilities persist with NTLM. NTLM has been deprecated and removed
from the bundle, but you can still [build and deploy the module](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-security-sso-ntlm).

## Disable Indexing

Before starting the upgrade process in your new installation, you must disable
indexing to prevent upgrade process performance issues that arise when the
indexer attempts to re-index content. 

To disable indexing, create a file called
`com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config`
in your `[Liferay Home]/osgi/configs` folder and add the following content: 

```properties
indexReadOnly="true"
```

After you complete the upgrade, re-enable indexing by removing the `.config`
file or setting `indexReadOnly="false"`. 

Your new @product-ver@ server is ready for upgrading your database. 
