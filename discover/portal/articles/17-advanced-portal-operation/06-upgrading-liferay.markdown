# Upgrading Liferay [](id=upgrading-liferay)

Liferay upgrades are fairly straightforward. A consistent set of steps is all
you need to follow to upgrade a standard Liferay installation. Things do get
more complicated if your organization has used Ext plugins to customize Liferay.
It's possible that API changes in the new version will break your existing code.
This, however, is usually pretty easy for your developers to fix. Portlet
plugins which use Liferay APIs should be reviewed and their services rebuilt
against the new release. Theme plugins may require some modifications to take
advantage of new features, and if they're using Liferay APIs, they should be
reviewed. Much effort has been made to make upgrades as painless as possible;
however, this is not a guarantee everything will work without modification. Ext
plugins are the most complicating factor in an upgrade, so it is important to
test as much as possible.

As a general rule, you can upgrade from one major release to the next major
release. For example, you can upgrade directly from Liferay 5.2.x to 6.0.x, but
not from 5.2.x to 6.1.x. If you need to upgrade over several major releases,
you'll need to run the upgrade procedure for each major release until you reach
the release you want. This doesn't mean you need to run the procedure for every
point release or service pack; you only need to run the procedure for the major
releases. A good practice is to use the latest version of each major release to
upgrade your system. 

Now that we've gotten the general philosophy of upgrading out of the way, let's
outline the procedure you'll undergo for upgrading a Liferay 6.0 installation to
a 6.1 installation. If you're running a previous version of Liferay and need to
upgrade to 6.0 first, please see the instructions in the previous version of
this document. 

## Preparing to Upgrade Liferay Portal 6.0 to Liferay Portal 6.1 [](id=preparing-to-upgrade-liferay-portal-6-0-to-liferay-portal-6-1)

There are a few things you should prepare before you actually perform the
upgrade. Specifically, you need to make sure you've migrated to permission
algorithm 6, reviewed your image gallery usage, reviewed new Liferay 6.1
defaults, and cataloged all the plugins you have installed. After you've
performed these three tasks, you're ready to upgrade. Let's look at them one by
one. 

## Migrate to Algorithm 6 [](id=migrate-to-algorithm-6)

If your Liferay installation has existed for a while, you may be on a different
permission algorithm than the one that's available in Liferay Portal 6.1.
Permission algorithms 1-5 were deprecated in Liferay Portal 6.0, and they've now
been removed in 6.1, which means you must migrate *before* you upgrade. 

If you're on Liferay 5.2 or below, you need to upgrade to the latest available
release of Liferay 6.0 first. Please follow the instructions in the [*Liferay
Portal Administrator's
Guide*](https://www.liferay.com/documentation/liferay-portal/6.0/administration/-/ai/upgrading-lifer-4)
to do this. We will assume for the rest of this section that you have 6.0
running, and that it's configured to use an older algorithm than algorithm 6. 

The first thing you need to do, if this is not done already, is to upgrade your
installation to algorithm 5. If you've already done that, great! You can skip
the rest of this paragraph. If not, shut down your server, edit your
`portal-ext.properties` file, and modify/add the following property so that it
reads like this: 

	permissions.user.check.algorithm=5

Restart your server. As Liferay starts, it upgrades your permissions algorithm
to algorithm 5. Review your system to make sure that your permissions
configuration is working properly (it should be). 

Next, log in as an Administrator and navigate to the Control Panel. Go to *Server
Administration* and select *Data Migration* from the menu along the top of the
screen. A section entitled *Legacy Permissions Migration* appears at the
bottom of the page.

![Figure 17.9: Update your permissions algorithm by clicking the *Execute*
button.](../../images/17-convert-permissions-algorithm.png)

Algorithms 5 and 6 do not support adding permissions at the user level. If you
have permissions set for individual users, the converter can simulate this for
you. To do this, it auto-generates roles for each individual permission, and
then assigns those roles to the users who have individualized permissions. If
you have a lot of these, you'll likely want to go through and clean them up
after the conversion process. To generate these roles, check the *Generate
Custom Roles* box. If you do not generate the roles, all custom permissions set
for individual users are discarded. 

Click *Execute* to convert all existing users and roles to algorithm 6. When the
process completes, shut down your server. Edit your `portal-ext.properties` file
and modify the algorithm property to show that you're now using algorithm 6: 

	permissions.user.check.algorithm=6

Restart your server. Congratulations! You've successfully migrated your
installation to use the latest, highest performing permissions algorithm. Next,
you'll need to explicitly set your Image Gallery storage option. 

## Migrate Your Image Gallery Images [](id=migrate-your-image-gallery-images)

Liferay 6.1 introduces a major change to how Liferay handles files. No longer do
we have a separate Document Library and Image Gallery; instead, these have been
combined into Documents and Media. If you were using Liferay's Image Gallery to
store images, these can be migrated over during an upgrade, but you'll have to
take some extra steps first. 

In Liferay 6.0, you had three ways you could store images in the Image Gallery.
You could use the `DatabaseHook` and store them as BLOBs in the database; you
could use the `DLHook` to store them in the Document Library, or you could use
the `FileSystemHook` to store them in a folder on your server's file system.
Before you upgrade, you'll need to set whichever property you were using in your
6.0 `portal-ext.properties` file, because by default, none of them are enabled
in 6.1. Setting one of the properties triggers the migration during the upgrade
process. Below are the three properties; you'll need to set only *one* of them
(the one you were using). 

    image.hook.impl=com.liferay.portal.image.DatabaseHook
    image.hook.impl=com.liferay.portal.image.DLHook
    image.hook.impl=com.liferay.portal.image.FileSystemHook

By default, Liferay 6.0 used the `FileSystemHook`. If you never customized this
property for your installation, you'd use the `FileSystemHook` property above.
If you customized the property, you should know which one you used, and it is
likely already in your `portal-ext.properties` file. 

The third thing you need to do to prepare for your upgrade is to review the new
property defaults. 

## Review the New 6.1 Properties Defaults [](id=review-the-new-6-1-properties-defaults)

The next thing you'll need to look at are the defaults that have changed from
6.0 to 6.1. These are preserved in `portal-legacy-6.0.properties` in the source.
The 6.0 values are:

    users.last.name.required=true
    layout.types=portlet,panel,embedded,article,url,link_to_layout
    editor.wysiwyg.portal-web.docroot.html.portlet.message_boards.edit_message.bb_code.jsp=bbcode
    setup.wizard.enabled=false discussion.subscribe.by.default=false
    message.boards.subscribe.by.default=false

The 6.1 values have changed to: 

    users.last.name.required=false
    layout.types=portlet,panel,embedded,url,link_to_layout
    editor.wysiwyg.portal-web.docroot.html.portlet.message_boards.edit_message.bb_code.jsp=ckeditor_bbcode
    setup.wizard.enabled=true discussion.subscribe.by.default=true
    message.boards.subscribe.by.default=true
 
If you don't like the defaults, you can change them back in one shot by adding a
system property to your JVM's startup. This differs by application servers. In
Tomcat, you'd modify `setenv.sh`/`setenv.bat` and append the option
`-Dexternal-properties=portal-legacy-6.0.properties` to the environment variable
JAVA_OPTS. The scripts `setenv.sh` or `setenv.bat` are not delivered with
default Tomcat, but do exist in the bundles. If they're there, Tomcat uses them
in the startup process, so it's a nice way to separate your own settings from
Tomcat's default shell scripts. Alternatively, of course, you can override some
or all of them in your `portal-ext.properties` along with your other overrides.

If you're not using Tomcat, check your application server's documentation to see
how to modify runtime properties. Your final task is to catalog all the plugins
you have installed, so you can install the new versions in your upgraded system. 

## Catalog All the Plugins You Have Installed [](id=catalog-all-the-plugins-you-have-installed)

Finally, you need to take note of any plugins you have installed. Liferay's
plugins are usually version-specific, so you'll need to obtain new versions of
them for the new release of Liferay. If you have custom plugins created by your
development team, they'll need to build, test, and optionally modify them to
work with the new release of Liferay. Don't attempt an upgrade without
collecting all the plugins you'll need first. 

Once you've upgraded your permissions algorithm, reviewed your properties, and
collected all the plugins you'll need, you're ready to follow the upgrade
procedure. Remember to back up your system before you begin. 

There are two different procedures to upgrade Liferay. The first one, upgrading
a Liferay bundle, is the most common. The second procedure is for upgrading a
Liferay installation on an application server. We'll go over both.

In both cases, Liferay auto-detects whether the database requires an upgrade the
first time the new version is started. When Liferay does this, it upgrades the
database to the format required by the new version. To perform this task,
Liferay *must* be accessing the database with an ID that can create, drop and
modify tables. Make sure you have granted these permissions to the ID before you
attempt to upgrade Liferay. And, of course, we'll run the risk of overly
repeating ourselves: back up your database. 

Let's look at upgrading a bundle, which is the easiest upgrade path. 

### Upgrading a Bundle [](id=upgrading-a-bundle)

If you're running a Liferay bundle, the best way to do the upgrade is to follow
the steps below. The new Liferay is installed in a newer version of your bundle
runtime. For example, the Liferay/Tomcat bundle for 6.0 used Tomcat 6 by
default; the 6.1 bundle uses Tomcat 7. We generally recommend you use the
latest version of your runtime bundle, as it will be supported the
longest. 

   1. Obtain the new bundle. Unzip the bundle to an appropriate location on your
      system.

   2. Copy your `portal-ext.properties` file and your `data` folder to the new
      bundle. 

   3. Review your `portal-ext.properties` file as described above. Make sure
	  you're using permissions algorithm 6. If you were using the Image Gallery,
      make the necessary modifications so your files are migrated to Documents and
      Media. Review the new defaults and decide whether you want to use them. Review
      any other modifications you've made. 	

   4. Start your application server. Watch the console as Liferay starts: it
      upgrades the database automatically.

   5. When the upgrade completes, install any plugins you were using in your old
      version of Liferay. Make sure you use the versions of those plugins that
      are designed for Liferay 6.1. If you have your own plugins, your
      development team will need to migrate the code in these ahead of time and
      provide .war files for you.  

   6. Browse around in your new installation and verify everything is working.
      Have your QA team test everything. If all looks good, you can delete the
      old application server with the old release of Liferay in it from the
      bundle directory. You have a backup of it anyway, right? 
   
As you can see, upgrading a bundle is generally pretty simple. But not everybody
can use bundles: sometimes, specific application servers or application server
versions are mandated by the environment you're in or by management. For this
reason, Liferay also ships as an installable .war file that can be used on any
supported application server. 

### Upgrading Using a .war File [](id=upgrading-using-a-war-file)

Running a manual upgrade is almost as easy as upgrading a bundle: 

   1. Verify your application server is supported by Liferay. You can do this by
      viewing the appropriate document on the Customer Portal (EE), in chapter
      14 (because there are installation instructions for it), or on liferay.com
      (CE).  If your application server isn't supported by Liferay 6.1, *do not
      continue!* You'll need to upgrade or switch to a supported application
      server first. 

   2. Obtain the Liferay Portal .war file and the dependency .jars archive. 

   3. Copy your customized `portal-ext.properties` file to a safe place and
      review it as described above, making all the appropriate changes.  

   4. Undeploy the old version of Liferay and shut down your application server.

   5. Copy the new versions of Liferay's dependency .jars to a location on your
      server's class path, overwriting the ones you already have for the old
      version of Liferay. This location is documented for your application
      server in chapter 14. 

   6. Deploy the new Liferay .war file to your application server. Follow the
      deployment instructions in chapter 14.

   7. Start (or, if your app server has a console from which you've installed
      the .war, restart) your application server. Watch the console as Liferay
      starts: it should upgrade the database automatically. Verify your portal
      is operating normally, and then install any plugins you were using in your
      old version of Liferay. Make sure you use the versions of theose plugins
      designed for Liferay 6.1. If you have your own plugins, your development
      team will need to migrate the code in these ahead of time and provide .war
      files to you. 

   8. Browse around in your new installation and verify everything is working.
      Have your QA team test everything. If all looks good, you're finished. 

That's all there is to it. Most everything is handled by Liferay's upgrade
procedure. Note as stated above, if you have to upgrade over several Liferay
versions, you will need to repeat these steps for each major release. 
