# Upgrading Liferay [](id=upgrading-liferay-lp-6-2-use-useportal)

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

Prior to Liferay 6.1 SP2, you could upgrade only from one major release to the
next major release. For example, you could upgrade directly from Liferay 5.2.x
to 6.0.x, but not from 5.1.x to 6.0.x. If you needed to upgrade over several
major releases, you needed to run the upgrade procedure for each major release
until you reached the release you want. This doesn't mean you needed to run the
procedure for every point release or service pack; you only needed to run the
procedure for the major releases. A good practice was to use the latest version
of each major release to upgrade your system. 

Liferay introduced the *seamless upgrade* feature with Liferay 6.1. Seamless
upgrades allow Liferay to be upgraded more easily. In most cases, pointing the
latest version of Liferay to the database of the older version is enough. Of
course, before upgrading, you should test the upgrade in a non-production
environment. You should also always back up your database and other important
information and make all the other appropriate preparations that we'll discuss
in the section.

Now that we've discussed the general philosophy of upgrading, let's outline the
procedure for upgrading to Liferay 6.2.

## Preparing for an Upgrade

The first thing you need to do is size up your situation. You can do this by
asking yourself a few questions from the chart below. First: What version of
Liferay was the first version you installed? If it was 6.0 or 6.1, there are
fewer steps, because you won't have to worry about migrating your permission
algorithm. If, however, you never upgraded to permissions algorithm 6 or you're
still running a 5.x Liferay, you need to migrate to algorithm 6 before
attempting to upgrade to Liferay 6.2. 

Next, if you're upgrading from a version of Liferay older than 6.1, you'll have
to migrate your image gallery over to Documents and Media. Finally, take note of
all the plugins you have installed. Every plugin must be updated to run on the
current release. This is easy to do with Marketplace: after you bring up
Liferay 6.2, install from Marketplace any of the plugins you had installed
previously. For custom plugins, have your development team update them to run on
the new version of Liferay. 

![Figure 18.9: Use this flowchart to determine the steps to take for your upgrade.](../../images/upgrade-decisions.png)

The flowchart illustrates the procedure described above. Use it to determine
your course of action for the upgrade. Each step is described fully below so
that you can perform your upgrade as efficiently as possible. Be sure to test
the upgrade in a non-production environment before upgrading your production
Liferay instance. Let's look at the preparatory tasks you should perform one by
one. 

## Migrate to Algorithm 6

If your Liferay installation has existed for a while, you might be on a
different permission algorithm than the one that's available in Liferay Portal
6.1. Permission algorithms 1-5 were deprecated in Liferay Portal 6.0 and
were removed in 6.1, which means you must migrate *before* you upgrade.

---

 ![Tip](../../images/tip.png) **Important**: Before upgrading a Liferay instance
 that's using one of permissions algorithms 1-5, you *must* migrate to
 permissions algorithm 6 before attempting to upgrade to Liferay 6.2. You can't
 use the seamless upgrade feature to upgrade directly to 6.2 because Liferay's
 permissions migration tool is not included with Liferay 6.2. Follow the
 instructions in this section to migrate to permissions algorithm 6 before
 continuing with your upgrade.

---

If you're on Liferay 5.2 or below, you must upgrade to the latest available
release of Liferay 6.0 first. Please follow the instructions in the [*Liferay
Portal Administrator's Guide*](https://www.liferay.com/documentation/liferay-portal/6.0/administration/-/ai/upgrading-lifer-4)
to do this. We will assume for the rest of this section that you have upgraded
to Liferay 6.0 but that's it's configured to use an older algorithm than
algorithm 6. 

The first thing you need to do, if this is not done already, is to upgrade your
Liferay installation to algorithm 5. If you've already done that, great! You can
skip the rest of this paragraph. If not, shut down your server, edit your
`portal-ext.properties` file, and modify/add the following property so that it
reads like this: 

	permissions.user.check.algorithm=5

Restart your server. As Liferay starts, it upgrades your permissions algorithm
to algorithm 5. Review your system to make sure that your permissions
configuration is working properly (it should be). 

Next, log in as an administrator and navigate to the Control Panel. Go to
*Server Administration* and select *Data Migration* from the menu along the top
of the screen. A section entitled *Legacy Permissions Migration* appears at the
bottom of the page.

![Figure 18.10: Update your permissions algorithm by clicking the *Execute* button.](../../images/17-convert-permissions-algorithm.png)

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
might need to explicitly set your Image Gallery storage option. 

## Migrate Your Image Gallery Images

Liferay 6.1 introduced a major change to how Liferay handles files. Liferay 6.0
and previous versions had a separate Document Library and Image Gallery. In
Liferay 6.1 and 6.2, these are combined into the Documents and Media repository.
If you were using Liferay's Image Gallery to store images, these can be migrated
over during an upgrade, but you'll have to take some extra steps first. 

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

## Review the Liferay 6.2 Properties Defaults

The next thing you'll need to look at are the defaults that have changed between
your old Liferay instance's version and Liferay 6.2. These are preserved in a
`portal-legacy-[version].properties` file in Liferay's `/WEB-INF/classes` folder
and in the `portal-impl/src` folder of Liferay's source code. For example, here
are some 6.1 legacy properties:

    hibernate.cache.use_query_cache=true
    hibernate.cache.use_second_level_cache=true
    locale.prepend.friendly.url.style=1
    passwords.encryption.algorithm.legacy=SHA
    mobile.device.styling.wap.enabled=true
    journal.articles.search.with.index=false

The `passwords.encryption.algorithm.legacy` and
`mobile.device.styling.wap.enabled` properties do not exist in 6.1. In 6.2, the
default values of some properties have changed and some new properties have been
added: 

    hibernate.cache.use_query_cache=false
    hibernate.cache.use_second_level_cache=false
    locale.prepend.friendly.url.style=3
    passwords.encryption.algorithm.legacy=
    mobile.device.styling.wap.enabled=false
    journal.articles.search.with.index=true

Please refer to the 6.1 and 6.2 versions of Liferay's `portal.properties` file
for explanations of each of these properties. This file can be found in the your
Liferay instance's `/WEB-INF/lib/portal-impl.jar` file. Online versions can also
be found at
[http://docs.liferay.com/portal/6.1/propertiesdoc/portal.properties.html](http://docs.liferay.com/portal/6.1/propertiesdoc/portal.properties.html)
and
[http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html).
Please also note the following changes in behavior:

1. By default, Liferay 6.1 used the DES encryption algorithm with a 56 bit key
   size for the company level encryption algorithm.
   
        company.encryption.algorithm=DES
        company.encryption.key.size=56
   
    By default, Liferay 6.2 uses the much stronger AES encryption algorithm with
    a 128 bit key size for the company level encryption algorithm. AES-128 is
    believed to be secure, is fast, and is a standard for symmetric key
    encryption. 
   
        company.encryption.algorithm=AES
        company.encryption.key.size=128
   
    However, the upgrade for the `company.encryption.algorithm` property is only
    performed if the value for this properties was not customized, i.e., if it
    was still set to DES. The upgrade doesn't make any changes if a different
    algorithm was explicitly selected. (Note that this does not affect password
    encryption which a different property handles:
    `passwords.encryption.algorithm`.)

2. By default, Liferay 6.1 used the SHA algorithm for password encryption.

        passwords.encryption.algorithm=SHA

    By default, Liferay 6.2 uses a stronger algorithm,
    PBKDF2WithHmacSHA1/160/128000, for password encryption. PBKDF2
    (Password-Based Key Derivation Function 2) is a key derivation function
    that's part of RSA's PKCS (Public-Key Cryptography Standards) series: PKCS
    \#5, version 2.0. It's also described in the IETF's [RFC
    2898](http://tools.ietf.org/html/rfc2898). The
    `PBKDF2WithHmacSHA1/160/128000` algorithm uses a keyed-hash message
    authentication code using SHA-1 and generates 160-bit hashes using 128,000
    rounds. One round is a single iteration of the key derivation function.
        
        passwords.encryption.algorithm=PBKDF2WithHmacSHA1/160/128000

    Performance is affected by password encryption during sign-in and password
    changes. In 2012, [OWASP](http://www.owasp.org), the Open Web Application
    Security Project, recommended to use 64,000 rounds and to double the number
    of rounds each year. If using PBKDF2 with 128,000 rounds is too expensive
    for the hardware on which you're running Liferay, you can downgrade your
    security algorithm to improve performance by choosing a smaller number. For
    example, you set the following:

        passwords.encryption.algorithm=PBKDF2WithHmacSHA1/160/64000

    If you'd like your upgrade to migrate your password encryption algorithm,
    you need to specify the legacy password encryption algorithm from which
    you're migrating. For example, if you were using the 6.1 default before your
    upgrade, you'd set the following property:

        passwords.encryption.algorithm.legacy=SHA

    Set this property before performing an upgrade so that both existing users'
    and new users' passwords are re-encrypted with the new algorithm.

3. After upgrading from Liferay 6.1 to Liferay 6.2, users must sign back in to
   the portal even if they were using the *Remember Me* feature of the Sign In
   portlet. After the upgrade, the *Remember Me* feature works correctly: users
   can log in to the portal, close their browser, open a new browser window,
   navigate to the portal, and still be logged in.

If you don't like the 6.2 default properties, you can change them back in one
shot by adding a system property to your JVM's startup. This differs by
application servers. In Tomcat, you'd modify `setenv.sh`/`setenv.bat` and append
the option `-Dexternal-properties=portal-legacy-[version].properties` to the
environment variable JAVA_OPTS. The scripts `setenv.sh` or `setenv.bat` are not
delivered with default Tomcat, but do exist in the bundles. If they're there,
Tomcat uses them in the startup process, so it's a nice way to separate your own
settings from Tomcat's default shell scripts. Alternatively, of course, you can
override some or all of them in your `portal-ext.properties` along with your
other overrides.

If you're not using Tomcat, check your application server's documentation to see
how to modify runtime properties. Your final task is to catalog all the plugins
you have installed, so you can install the new versions in your upgraded system. 

## Catalog All Installed Plugins

Finally, you need to take note of any plugins you have installed. Liferay's
plugins are usually version-specific, so you'll need to obtain new versions of
them for the new release of Liferay. If you have custom plugins created by your
development team, they'll need to build, test, and optionally modify them to
work with the new release of Liferay. Don't attempt an upgrade without
collecting all the plugins you'll need first. 

For Liferay 6.2, the Web Content List portlet is deprecated. During the
deprecation period, the code will still be part of the product, but will be
disabled by default. To enable Web Content List, you'll need to modify the
`liferay-portlet.xml` file by setting the `<include>false</include>` tag to
`true`. However, all the functionality of this portlet is provided by the Asset
Publisher portlet. The Web Content List portlet is expected to be removed in the
next release.

Once you've upgraded your permissions algorithm, reviewed your properties, and
collected all the plugins you'll need, you're ready to follow the upgrade
procedure. Remember to back up your system before you begin. 

## Upgrade Choices: Upgrade a Bundle or Upgrade Manually

There are two different procedures to upgrade Liferay. The first one, upgrading
a Liferay bundle, is the most common. The second procedure is for manually
upgrading a Liferay installation on an application server. We'll discuss both.

In both cases, Liferay auto-detects whether the database requires an upgrade the
first time the new version is started. When Liferay does this, it upgrades the
database to the format required by the new version. To perform this task,
Liferay *must* be accessing the database with a database user account that can
create, drop and modify tables. Make sure you have granted these permissions to
the database user account before you attempt to upgrade Liferay. And, of course,
we'll run the risk of overly repeating ourselves: back up your database.

Let's look at upgrading a bundle, which is the easiest upgrade path. 

### Upgrading a Bundle

If you're running a Liferay bundle, the best way to do the upgrade is to follow
the steps below. The new Liferay is installed in a newer version of your bundle
runtime. For example, the Liferay bundle for 6.1 uses a different version of
Tomcat than the Liferay bundle for 6.2. We generally recommend you use the
latest version of your runtime bundle, as it will be supported the longest. 

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
      are designed for Liferay 6.2. If you have your own plugins, your
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

### Upgrading Manually

Running a manual upgrade is almost as easy as upgrading a bundle: 

   1. Verify your application server is supported by Liferay. You can do this by
      viewing the appropriate document on the Customer Portal (EE), in chapter
      14 (because there are installation instructions for it), or on liferay.com
      (CE).  If your application server isn't supported by Liferay 6.2, *do not
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
      old version of Liferay. Make sure you use the versions of those plugins
      designed for Liferay 6.2. If you have your own plugins, your development
      team will need to migrate the code in these ahead of time and provide .war
      files to you. 

   8. Browse around in your new installation and verify everything is working.
      Have your QA team test everything. If all looks good, you're finished. 

That's all there is to it. Most everything is handled by Liferay's upgrade
procedure. Note as stated above, if you have to upgrade over several Liferay
versions, you will need to repeat these steps for each major release. 

## Post-Upgrade Tasks

After upgrading to Liferay 6.2, you should reindex your portal's search indexes.
Liferay 6.2 indexes new information in many places, including Documents and
Media, Web Content, and Bookmarks. To reindex all search indexes, navigate to
the *Control Panel* &rarr; *Server Administration* and click on *Reindex all
search indexes*. This invokes each of your portal's indexer classes, ensuring
that your search indexes contain the updated data that 6.2 indexes. 

Do you have some troublesome required portlets running in your portal? Wouldn't
it be great if you could isolate them so they wouldn't affect the overall health
of your portal? We'll show you how to use Liferay's Sandboxing feature to pen up
those pesky portlets, next.
