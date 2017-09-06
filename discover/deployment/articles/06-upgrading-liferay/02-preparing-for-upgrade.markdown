# Preparing an Upgrade to @product-ver@ [](id=preparing-an-upgrade-to-liferay-7)

Before upgrading, you should have a synchronized backup of your database and
file system for your document library. Next, you'll need to consider your
current installation. It is likely that you have customized your installation's
properties to create a configuration that works with your requirements, so
you'll need to look at your `portal-setup-wizard.properties` and your
`portal-ext.properties` files and decide whether @product@'s new defaults are
compatible with your settings.

+$$$

**Important**: Before performing the upgrade, for every @product@ plugin you've
installed from Marketplace (Kaleo, Calendar, Notifications, etc.), make sure
you've got the latest versions installed. This will allow you to run the upgrade
without unnecessary issues.

$$$

After that, you'll need to look at your Documents and Media file store and make
sure the upgrade takes your configuration into account. 

Next, you'll actually install the new version of @product@ onto your server, or
use a bundle to jump start your installation process. 

Finally, to make sure the upgrade process runs smoothly and quickly, you'll
disable indexing temporarily during the upgrade. 

These steps are described in detail below. 

Although you can perform the upgrade in one step (if you're coming from Liferay Portal
6.0.12 or higher), you should take into account previous upgrade configurations.

## Previous upgrade configurations [](id=previous-upgrade-configurations)

If you're coming from Liferay Portal 6.2, skip this section. In any other case, please
follow these instructions to configure your `portal-ext.properties` properly:

- If you're on Liferay Portal 6.1, check the
[6.2 upgrade properties](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay#review-the-liferay-6)
information to learn about the new defaults introduced in Liferay Portal 6.2. 

- If you're on Liferay 6.0.12, you'll also need to
[migrate the image gallery](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay#migrate-your-image-gallery-images).

- If you have a sharded environment, please refer to [how to configure the upgrade for sharding](/discover/deployment/-/knowledge_base/7-0/upgrading-sharded-environment).

- If you've used Web Content Management extensively, you may have structures
  that lack unique name fields. Refer to 
  [this](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay#find-and-remove-duplicate-field-names)
  to resolve the problem before upgrading. If you didn't follow the instructions
  in this article before upgrading, you'll see this stack trace: 

        19:29:35,298 ERROR [main][VerifyProcessTrackerOSGiCommands:221] com.liferay.portal.verify.VerifyException: com.liferay.dynamic.data.mapping.validator.DDMFormValidationException$MustNotDuplicateFieldName: The field name page cannot be defined more than once
        com.liferay.portal.verify.VerifyException: com.liferay.dynamic.data.mapping.validator.DDMFormValidationException$MustNotDuplicateFieldName: The field name page cannot be defined more than once
 
  If this is the case, roll back to your previous backup of Liferay 6.2 and 
  [follow the instructions here](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay#find-and-remove-duplicate-field-names).


## Legacy Properties [](id=legacy-properties)

When a new version of @product@ is released, there are often changes to default
settings, and this release is no different. If you rely on the defaults from
your old version, you'll want to review the changes and decide whether you want
to keep the defaults from your old version or accept the defaults of the new. 

Here's a list of the 6.2 properties that have changed in 7.0: 
    
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

You can find a complete reference for what these properties mean by viewing the
[properties documentation](http://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html).

## Configuring Your Documents and Media File Store [](id=configuring-your-documents-and-media-file-store)

Your next task is to review your Documents and Media configuration. Look at
[Document Library documentation](/discover/portal/-/knowledge_base/7-0/liferay-repository-types)
to see all the options in @product-ver@ before executing an upgrade process. There
are, however, two important pieces of configuration that have changed: 

- The way you specify the location for the file store is no longer in the
  `portal-ext.properties` file.
- Package names for the store implementations have changed from
  `com.liferay.portlet.documentlibrary.store` to `com.liferay.portal.store.*`
  and you'll need to configure the appropriate updated implementation name
  rather than copying over the name from your 6.2 `portal-ext.properties`.

If you use the default store but don't want to store the files in the default
location (`[Liferay Home]/data/document_library`), you need to create a file
called
`com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration.cfg`
in your `[Liferay Home]/osgi/configs` folder with the following content:

    rootDir={document_library_path}

If you use Advanced File System Store method to persist document library files,
you'd call that file
`com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.cfg`.

The name of the configuration file matches the name of the class that implements
the configuration. 

For more information about the other available stores, look at [Document Repository Configuration](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration).

## Install the New Version of @product@

Next, [follow the instructions](/discover/deployment/-/knowledge_base/7-0/preparing-for-install)
to install @product@ on your application server or use @product@ bundled with your
application server of choice. Once you have @product@ installed, DON'T START IT! 

If you've upgraded @product@ in the past, once you've prepared your system for the
upgrade, the upgrade process ran when you started the new version for the first
time. @product@ instead ships with a tool for upgrading. You must use this tool
to upgrade your database. Starting the server to run the upgrade is no longer
supported. The following exception is thrown if you try to do that:

    [MainServlet:237] java.lang.RuntimeException: You must first upgrade to @product@ 7000

Apply the settings you identified above for `portal-ext.properties` and
your Documents and Media store. Once you've finished that, you're almost ready
to run the upgrade process. 

## Disable Indexing During the Upgrade Process [](id=disable-indexing-during-the-upgrade-process)

Before starting the upgrade process, you must disable indexing so the indexer
doesn't try to run during the upgrade. Create a file called
`com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.cfg` in
your `[Liferay Home]/osgi/configs` folder with the following content: 

    indexReadOnly=true

Setting the property above disables indexing. By disabling indexing, you prevent
performance issues arising from the indexer attempting to reindex your content
during the upgrade process. When the upgrade is complete, make sure you set this
property to `false` so that you can index all objects from the Control Panel.

Ready to upgrade? The next article shows you how. 
