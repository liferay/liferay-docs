# Preparing for Upgrade to @product-ver@ [](id=preparing-an-upgrade-to-liferay-7)

Now that you've completed the
[pre-upgrade process of cleaning and normalizing your data](/discover/deployment/-/knowledge_base/7-1/pre-upgrade-speed-up-the-process),
you're ready to prepare your environment for upgrading to @product-ver@. Here's
a summary of the preparation steps: 

**Step 1**: Upgrade your Marketplace apps 

**Step 2**: Remove duplicate web content structure field names

**Step 3**: Synchronize a complete @product@ backup

**Step 4**: Update your portal properties

**Step 5**: Configure your Documents and Media file store

**Step 6**: Install @product-ver@ 

**Step 7**: Disable indexing during the upgrade process

This tutorial describes these steps in detail. 

## Step 1: Upgrade Your Marketplace Apps [](id=step-1-upgrade-your-marketplace-apps)

Upgrade each Marketplace app (Kaleo, Calendar, Notifications, etc.) that you're
using to its latest version for your installation. Before proceeding with the
upgrade, troubleshoot any issues regarding these apps. 

## Step 2: Remove Duplicate Web Content Structure Field Names [](id=step-2-remove-duplicate-web-content-structure-field-namesidstep-2-remove-du)

If you've used Web Content Management extensively, you might have structures
whose field names aren't unique. You must 
[find and remove duplicate field names](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay#find-and-remove-duplicate-field-names)
before upgrading. If you upgraded to Liferay Portal 6.2 previously and skipped doing this, you'll encounter this error: 

    19:29:35,298 ERROR [main][VerifyProcessTrackerOSGiCommands:221] com.liferay.portal.verify.VerifyException: com.liferay.dynamic.data.mapping.validator.DDMFormValidationException$MustNotDuplicateFieldName: The field name page cannot be defined more than once
    com.liferay.portal.verify.VerifyException: com.liferay.dynamic.data.mapping.validator.DDMFormValidationException$MustNotDuplicateFieldName: The field name page cannot be defined more than once
 
If this is the case, roll back to your previous backup of Liferay 6.2 and 
[find and remove duplicate field names](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay#find-and-remove-duplicate-field-names). 

## Step 3: Synchronize a Complete Backup @product@ [](id=step-2-synchronize-a-complete-backup-product)

[Back up your @product@ database, installation, and Document Library store](/discover/deployment/-/knowledge_base/7-1/backing-up-a-liferay-installation). 

## Step 4: Update Your Portal Properties [](id=step-4-update-your-portal-properties)

It is likely that you have overridden portal properties to customize your
installation to your requirements. If so, you must update the properties files
(e.g., `portal-setup-wizard.properties` and `portal-ext.properties` files) to be
compatible with @product-ver@. As you do this, you should account for property
changes in all versions of @product@ since your current version up to and
including @product-ver@.

If you're coming from a version prior to Liferay Portal 6.2, start with
these property-related updates:

-   If you're on Liferay Portal 6.1,
    [adapt your properties to the new defaults that Liferay Portal 6.2 introduced](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay#review-the-liferay-6). 

-   If you're on Liferay 6.0.12, 
    [migrate the Image Gallery](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay#migrate-your-image-gallery-images).

-   If you have a sharded environment,
    [configure your upgrade for sharding](/discover/deployment/-/knowledge_base/7-1/upgrading-sharded-environment).

When a new version of @product@ is released, there are often changes to default
settings, and this release is no different. If you rely on the defaults from
your old version, you'll want to review the changes and decide whether you want
to keep the defaults from your old version or accept the defaults of the new. 

Here's a list of the 6.2 properties that have changed in 7.1: 
    
    users.image.check.token=false
    organizations.types=regular-organization,location
    organizations.rootable[regular-organization]=true
    organizations.children.types[regular-organization]=regular-organization,location
    organizations.country.enabled[regular-organization]=false
    organizations.country.required[regular-organization]=false
    organizations.rootable[location]=false
    #organizations.children.types[location]=
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

Properties in features that have been modularized have changed and must now be
deployed separately in
[OSGi configuration files](/discover/portal/-/knowledge_base/7-1/system-settings#exporting-and-importing-configurations). 
The
[7.1 portal properties reference docs](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html)
provide property details and examples. 

## Step 5: Configuring Your Documents and Media File Store [](id=configuring-your-documents-and-media-file-store)

Your next task is to review your Documents and Media configuration. Look at
[Document Library documentation](/discover/portal/-/knowledge_base/7-1/liferay-repository-types)
to see all the options in @product-ver@ before executing an upgrade process.
There are, however, two important pieces of configuration that have changed: 

-   The file store location is no longer specified in a portal properties file
    (e.g., `portal-ext.properties`).
-   Store implementation package names have changed from
    `com.liferay.portlet.documentlibrary.store` to `com.liferay.portal.store.*`
    and you must configure the appropriate updated implementation name rather
    than copying over the name from your 6.2 `portal-ext.properties`.

If you use the default store but don't want to store the files in the default
location (`[Liferay Home]/data/document_library`), you must create a file called
`com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration.cfg`
in your `[Liferay Home]/osgi/configs` folder and add the following content:

    rootDir={document_library_path}

If you use the Advanced File System Store method to persist document library
files, you'd call that file
`com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.cfg`.

The configuration file name must match the name of the class that implements the
configuration. 

The
[Document Repository Configuration documentation](/discover/deployment/-/knowledge_base/7-1/document-repository-configuration)
provides more information.

## Step 6: Install @product-ver@ [](id=install-the-new-version-of-product)

Next,
[install @product@ on your application server](/discover/deployment/-/knowledge_base/7-1/deploying-product)
or
[use @product@ bundled with your application server of choice](/discover/deployment/-/knowledge_base/7-1/installing-product).

**Important**: Once you have installed @product-ver@, **DON'T START IT!** In
previous versions, once you prepared your system for an upgrade, the upgrade
process ran when you started the new version for the first time. Now
@product-ver@ ships with an upgrade tool (described in the next article) that
you must use to upgrade your database.

+$$$

**Note**: @product@ throws the following exception if you attempt to start
@product-ver@ before running the upgrade tool: 

    [MainServlet:237] java.lang.RuntimeException: You must first upgrade to @product@ 7000

$$$

Copy your custom portal properties files (e.g., `portal-ext.properties`) that
you updated in previous steps and your Documents and Media store into your new
installation. 

## Step 7: Disable Indexing During the Upgrade Process [](id=disable-indexing-during-the-upgrade-process)

Before starting the upgrade process in your new installation, you must disable
indexing to prevent upgrade process performance issues that arise when the
indexer attempts to reindex content. 

To disable indexing, create a file called
`com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.cfg` in
your `[Liferay Home]/osgi/configs` folder and add the following content: 

    indexReadOnly=true

After you complete the upgrade (described in the next article), re-enable
indexing by setting this property to `false`. 

Ready to upgrade? The next article shows you how. 
