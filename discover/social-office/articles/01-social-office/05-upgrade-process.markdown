# Upgrade Process 

Social Office 1.5b runs on Liferay Portal 5.2.7. If you're running Social Office
1.5b and you'd like to upgrade to 2.0, you first need to upgrade your portal to
version 6.1.1 CE. Please see the Upgrading Liferay section in chapter 17 of
Liferay's User Guide for details about Liferay's upgrade process:
[http://www.liferay.com/documentation/liferay-portal/6.1/user-guide](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide).
Before starting the following Social Office upgrade process, make sure you've
already upgraded your portal to version 6.1.1 CE, and backed up your old
`portal-ext.properties` file, data folder, and database.

1. In your new 6.1.1 portal bundle, add the following properties into your
   `portal-ext.properties` file:

   5.2.7 uses permission algorithm 5 (comment out once data migration is
   complete).
   
    permissions.user.check.algorithm=5
    permissions.view.dynamic.inheritance=false

   6.1.1 uses permission algorithm 6 (comment in once data migration is
   complete).
   
    permissions.user.check.algorithm=6
    permissions.view.dynamic.inheritance=false

   The following property ensures that the document library uses the same
   storage method.
   
    dl.hook.impl=com.liferay.documentlibrary.util.FileSystemHook

   You do not want to index on startup/upgrade, so make sure reindex is disabled
   when you deploy the Social Office packager.
   
    index.on.startup=false index.on.upgrade=false

   Point Liferay to data folder of SO 1.5 (this may be different then your new
   data folder).
   
    resource.repositories.root={your data folder}

   The following values are taken from the default 5.2 legacy properties in the
   portal folder.
   
    Default 5.2 users.image.max.height=0 users.image.max.width=0

    locale.prepend.friendly.url.style=0

    auth.token.check.enabled=false

    image.hook.impl=com.liferay.portal.image.DatabaseHook

    cdn.host.http=${cdn.host} cdn.host.https=${cdn.host}

    virtual.hosts.default.community.name=

    breadcrumb.show.guest.group=false breadcrumb.show.parent.groups=false

    wiki.page.titles.regexp=([^/\\\\\\[\\]%&?@]+)
    wiki.page.titles.remove.regexp=([/\\\\\\[\\]%&?@]+)
	
2. Make sure that your root directory is set to SO 1.5's root directory and that
   permission algorithm 5 is being used.

3. Start up your application server. (Your permission algorithm must be set to
   5.)

4. After the upgrade has completed and your portal is running, log in as an
   administrator and navigate to Control Panel &rarr; Server Administration
   &rarr; Data Migration.

5. Flag *Generate Custom Roles*.

6. Click *Execute* to update the permission alogrithm.

7. Shut down your application server.

8. In your `portal-ext.properties` file, comment out permission algorithm 5 and
   comment in permission algorithm 6.

9. Start your application server back up.

10. Deploy the Social Office 2.0 Packager or install Social Office 2.0 through
    Liferay Marketplace.

11. Check that all previous content and files are accessible and present.
