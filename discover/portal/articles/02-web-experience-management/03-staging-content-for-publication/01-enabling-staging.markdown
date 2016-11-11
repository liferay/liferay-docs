# Enabling Staging [](id=enabling-staging)

@product@ provides site administrators with two different ways to set up
staging: Local Live and Remote Live. With Local Live staging, both your staging
environment and your live environment are hosted on the same server. When Local
Live staging is enabled for a site, a clone of the site is created containing
copies of all of the site's existing pages. Typically, this means the staging
and live environments share the same JVM, database, portlet data (depending on
which portlets are selected when staging is enabled), and setup configurations,
such as the properties set in the `portal-ext.properties` file. The cloned site
becomes the staging environment and the original site becomes the live
environment.

When Remote Live staging is enabled for a site, a connection is established
between the current site and another site on a remote Liferay server. The remote
site becomes the live environment and the current site becomes the staging
environment--an instance of Liferay used solely for staging. Content
creators can use the staging server to make their changes while the live server
handles the incoming user traffic. When changes to the site are ready to be
published, they are pushed over the network to the remote live server. Whether
you enable Local Live or Remote Live staging, the interface for managing and
publishing staged pages is the same.

So when should you use Local Live staging and when should you use Remote Live
staging? Local Live staging lets you publish site changes very quickly, since
the staged and live environments are on the same server. It's also easier to
switch between the staged and live environments using Local Live staging. Since
the staged content, however, is stored in the same database as the production
content, your server needs to have more resources, and the content isn't as well
protected or backed up as with Remote Live staging. Also, you can't install new
versions of apps for testing purposes in a Local Live staging environment, since
only one version of an app can be installed at any given time on a single
Liferay server.

With Remote Live staging, your staging and live environments are hosted on
separate servers, so your data is separated. This lets you deploy new versions
of apps and content to your staging environment without interfering with your
live environment. However, publishing is slower with Remote Live staging since
data must be transferred over a network. Of course, you also need more
hardware to run a separate staging server.

## Enabling Local Live Staging [](id=enabling-local-live-staging)

Site administrators can enable Staging for a site by navigating to the *Site
Administration* &rarr; *Publishing* menu and selecting *Staging*. A new page
loads where you can select the staging type, either *Local Live* or *Remote
Live*, after which additional options appear. Staging allows changes to be made
in a staging environment so that work can be reviewed, possibly using a
workflow, before it's published to a live site. Enabling Local Live staging is
easy. Just select *Local Live* and decide whether you'd like to enable page
versioning and what app data will be included on the staging site.

You can enable page versioning on a site's public pages, private pages, both, or
neither. Page versioning allows you to work in parallel on different versions of
pages and maintains a history of all page modifications. You'll learn about page
versioning in more detail below.

To get some hands-on experience with enabling Local Live staging, you can
complete a brief example which creates a Local Live staging environment for the
Lunar Resort home page. Before beginning, you'll want to add a new page.
Navigate to the Pages menu in the Lunar Resort's Site Administration menu and
add a new page named *News and Events*. Next, click *News and Events* to view
the page. Then add the Alerts and Announcements apps to the News and Events
page. Navigate to the Staging menu under Publishing, select *Local Live*, and
click *Save*. You've officially begun the staging process.

When you activate Local Live staging, @product@ creates a clone of your site.
This clone becomes the staging environment. Because of this, it is recommended
to only activate staging on new, clean sites. Having a few pages and some apps
(like those of the example site you created) is no big deal. If you've already
created a large amount of content, however, enabling staging can take a lot of
time since it's a resource intensive operation. Also, if you intend to use page
versioning to track the history of updates to your site, it's recommended that
you enable it as early as possible, *before* your site has many pages and lots
of content. Your site's update history won't be saved until you enable page
versioning. Page versioning requires staging (either Local Live or Remote Live)
to be enabled.

If you ever need to turn off the staging environment, return back to *Staging*
from the Publishing dropdown. The processes you've created are displayed by
default. Navigate to the *Options* icon
(![Options](../../../images/icon-options.png)) from the upper right corner of
the page and select *Staging Configuration*. Select the *None* radio button to
turn Local Live staging off. Please note that this operation removes the staging
environment altogether, so all content that was not published to your live site
will be lost!

Next you'll learn about enabling Remote Live staging.

## Enabling Remote Live Staging [](id=enabling-remote-live-staging)

When you enable Remote Live staging, the remote site becomes the live
environment and the current site becomes the staging environment. The remote
(live) Liferay server and the local (staging) Liferay server should be
completely separate systems. They should not, for example, share the same
database. When Remote Live staging is enabled, all the necessary information is
transferred over the network connecting the two servers.

When applying patches to a remote staging environment, you must
apply patches to all of your servers being used. Having servers on different
patch levels is not a good practice and can lead to import failures and data
corruption. It is essential that all servers are updated to the same patch level
to ensure remote staging works correctly.

Before a site administrator can enable Remote Live staging for a site, the
remote Liferay server must be added to the current Liferay server's list of
allowed servers. The current Liferay server must also be added to the remote
Liferay server's list of allowed servers. You also need to specify an
authentication key to be shared by your current and your remote server and
enable each Liferay server's tunneling servlet authentication verifier. You can
make all of these configurations in your Liferay servers'
`portal-ext.properties` files. Your first step should be to add the following
lines to your current Liferay server's `portal-ext.properties` file:

    tunnel.servlet.hosts.allowed=127.0.0.1,SERVER_IP,[Remote server IP address]
    tunneling.servlet.shared.secret=[secret]
    tunneling.servlet.shared.secret.hex=true
    auth.verifier.TunnelingServletAuthVerifier.hosts.allowed=
    auth.verifier.pipeline=com.liferay.portal.security.auth.TunnelingServletAuthVerifier,com.liferay.portal.security.auth.BasicAuthHeaderAutoLogin,com.liferay.portal.security.auth.DigestAuthenticationAuthVerifier,com.liferay.portal.security.auth.ParameterAutoLogin,com.liferay.portal.security.auth.PortalSessionAuthVerifier

Then add the following lines to your remote Liferay server's
`portal-ext.properties` file:

    tunnel.servlet.hosts.allowed=127.0.0.1,SERVER_IP,[Local server IP address]
    tunneling.servlet.shared.secret=[secret]
    tunneling.servlet.shared.secret.hex=true
    auth.verifier.TunnelingServletAuthVerifier.hosts.allowed=
    auth.verifier.pipeline=com.liferay.portal.security.auth.TunnelingServletAuthVerifier,com.liferay.portal.security.auth.BasicAuthHeaderAutoLogin,com.liferay.portal.security.auth.DigestAuthenticationAuthVerifier,com.liferay.portal.security.auth.ParameterAutoLogin,com.liferay.portal.security.auth.PortalSessionAuthVerifier

@product@'s use of a pre-shared key between your staging and production
environments helps secure the remote publication process. It also removes the
need to send the publishing user's password to the remote server for web service
authentication. Using a pre-shared key allows @product@ to create an
authorization context (permission checker) from the provided email address,
screen name, or user ID *without* the user's password.

The values that you can specify for the `tunneling.servlet.shared.secret`
property depend on the configured encryption algorithm, since different
encryption algorithms support keys of different lengths. Please see the
[HTTP Tunneling](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#HTTP%20Tunneling)
properties documentation for more information. Note that the following key
lengths are supported by the available encryption algorithms:

- AES: 128, 192, and 256 bit keys
- Blowfish: 32 - 448 bit keys
- DESede (Triple DES): 56, 112, or 168 bit keys (However, Liferay places an
  artificial limit on the minimum key length and does not support the 56 bit key
  length)

To prevent potential character encoding issues, you can use one of the following
two strategies:

1. Use hexadecimal encoding (recommended). E.g., if your password was
   *abcdefghijklmnop*, you'd use the following settings in your
   `portal-ext.properties` file:

        tunneling.servlet.shared.secret=6162636465666768696a6b6c6d6e6f70
        tunneling.servlet.shared.secret.hex=true

2. Use printable ASCII characters (less secure). This degrades the password
   entropy.

    If you don't use hexadecimal encoding, i.e. if you use the default setting
    `tunneling.servlet.shared.secret.hex=false`, the value of the
    `tunneling.servlet.shared.secret` property *must* be ASCII compliant.

Once you've chosen a key, make sure that value of your current server matches
the value of your remote server.

One last thing you'll need to do is update the *TunnelAuthVerfierConfiguration*
of your Liferay instance. To do this, navigate to the Control Panel &rarr;
*Configuration* &rarr; *System Settings* &rarr; *Platform* &rarr; *Tunnel Auth
Verifier*. Click */api/liferay/do* and insert the additional IP addresses you're
using in the *Hosts allowed* field. Then select *Update*.

Remember to restart both Liferay servers after making these portal properties
updates. After restarting, log back in to your local Liferay instance as
a site administrator. Then navigate to the *Publishing* option in Site
Administration and select *Staging*. Select *Remote Live* and additional options
appear.

![Figure 1: After your remote Liferay server and local Liferay server have been configured to communicate with each other, you have to specify a few Remote Live connection settings.](../../../images/remote-live-staging-settings.png)

First, enter your remote Liferay server's IP address into the Remote Host/IP
field. If the remote Liferay server is a cluster, you can set the Remote Host/IP
to the load balanced IP address of the cluster in order to increase the
availability of the publishing process. Next, enter the port on which the remote
Liferay instance is running into the Remote Port field. You only need to enter a
Remote Path Context if a non-root portal servlet context is being used on the
remote Liferay server. Finally, enter the site ID of the site on the remote
Liferay server that will be used for the Live environment. If a site hasn't
already been prepared for you on the remote Liferay server, you can log in to
the remote Liferay server and create a new blank site. After the site has been
created, note the site ID so you can enter it into the Remote Site ID field on
your local Liferay server. You can find any site's ID by selecting the site's
name on the Sites page of the Control Panel. Finally, it's best to check the
*Use a Secure Network Connection* field to use HTTPS for the publication of
pages from your local (staging) Liferay server to your remote (live) Liferay
server.

Similar to Local Live staging, it is generally a good idea to turn remote
staging on at the beginning of your site's development for good performance.
When you're using Remote Live staging, and you are publishing a large amount of
content, your publication could be slow and cause a large amount of network
traffic. @product@'s system is very fast for the amount of data being
transferred over the network. This is because the data transfer is completed
piecemeal, instead of one large data dump. You can control the size of data
transactions by setting the following portal property in your
`portal-ext.properties` file:

    staging.remote.transfer.buffer.size

That's all you need to do to enable Remote Live Staging! Note that if you fail
to set the tunneling servlet shared secret or the values of these properties on
your current and remote servers don't match, you won't be able to enable staging
and an error message appears. When a user attempts to publish changes from the
local (staging) server to the remote (live) server, @product@ passes the user's
email address, screen name, or user ID to the remote server to perform a
permission check. In order for a publishing operation to succeed, the operation
must be performed by a user that has identical credentials and permissions on
both the local (staging) and the remote (live) server. This is true regardless
of whether the user attempts to publish the changes immediately or attempts to
schedule the publication for later.

If only a few users should have permission to publish changes from staging to
production, it's easy enough to create a few user accounts on the remote server
that match a selected few on the local server. However, the more user accounts
that you have to create, the more tedious this job becomes and the more likely
you are to make a mistake. And you not only have to create identical user
accounts, you also have to ensure that these users have identical permissions.
For this reason, it's recommended that you use LDAP to copy selected user
accounts from your local (staging) Liferay server to your remote (live) Liferay
server. Liferay's Virtual LDAP Server application, available on Liferay
Marketplace, makes this easy.

+$$$

**Important:** If your instance is set up to validate IPv6 addresses, you'll
need to configure your Remote Live Connection Settings. Restart your Liferay
instance and navigate back to the Staging page. Select the *Remote Live* radio
selector and specify the fields for your remote site. The *Remote Host/IP* field
should match the host you specified as your `tunnel.servlet.hosts.allowed`
property in the `portal-ext.properties` file (e.g., *[0:0:0:0:0:0:0:1]*). Make
sure to include the brackets. Fill in the rest of the information relevant to
your site and click *Save*.

To check if the remote site is running on an IPv6 address, add a new application
to the staged site, and then select *Staging* &rarr; *Publish to Live* from the
top Control Menu. The changes are published to your remote staged site.

$$$

Next, you'll learn how to enable page versioning and staged apps.

## Enabling Page Versioning and Staged Content [](id=enabling-page-versioning-and-staged-content)

Enabling page versioning for a site allows site administrators to work in
parallel on multiple versions of the site's pages. Page versioning also
maintains a history of all updates to the site from the time page versioning was
enabled. Site administrators can revert to a previous version of the site at any
time. This flexibility is very important in cases where a mistake is found and
it's important to quickly publish a fix. If you're following the Lunar Resort
example, check *Enabled On Public Pages* to enable page versioning for the
Lunar Resort site and then click *Save*.

![Figure 2: You can decide to use versioning and choose what content should be staged.](../../../images/staging-page-versioning-staged-content.png)

Choosing content to be staged may sound self-explanatory, but content must have
specific attributes in @product@ to use it in a staged environment. Content or
an entity should be site-scoped, so they are always part of a site; otherwise,
they are not eligible for staging. @product@ supports the following content
groups for staging, by default:

- Application Display Templates
- Blogs
- Bookmarks
- Calendar
- Documents and Media
- Dynamic Data Lists
- Message Boards
- Mobile Device Families
- Polls
- Shopping
- Web Content
- Wiki

<!-- Below content is additional info that we can add for each supported app.
These will need to be incorporated into the list above, once we can explain more
about the other supported apps, as well. -Cody

- Blogs
    - *Referenced content:* see web content for details
    - Web Content
    - *Referenced content:* web content can reference different other entities
    in Liferay. Structures and templates for the most obvious examples, but
    it can reference images too from the portal's document library. Staging can
    exclude some of this content during publication or export to speed up the
    process. These references will be validated during the publish process or an
    import so the images has to be published or imported first.
    - *Version history:* The instance keeps a version history of the web content
    articles. In some cases these versions are needed if the latest version gets
    expired (e.g., the latest version is not visible to the end users yet). If
    the version history is not utilized, it can be turned off for publishing,
    thus making the process faster.
- Documents and Media
    - *Previews and Thumbnails:* previews and thumbnails are automatically
    generated on the Liferay instance. Although they can be published to the
    live site as well, you should be careful about this option. Imagine a
    scenario where a site has approximately 4000 images or documents. If the
    previews and thumbnails are turned on, this could end up in 28000 physical
    files on the disk. If staging is set up to publish the previews and
    thumbnails, this would mean that instead of taking care of the 4000 images,
    it will process 7 times more files! If you still want to use the previews on
    your live environment, you can set up that Liferay instance to generate them
    automatically. It depends on your environment for whether you can use the
    publishing of the previews and thumbnails. Publishing them is an IO heavy
    operation, and also you have to transfer the LAR file over the network if
    you use remote staging. If you decide to generate them on the live site, you
    have to take into consideration that this will take some time on the live
    server, and is also a CPU intense operation.
- Generic options
    - *Comments:* The portal has the ability to provide a commenting framework
    for several entities such as Web Content Articles, or Blog entries. These
    comments are associated with that specific entity and upon publishing if we
    use the comments functionality we need to query up and put them into the LAR
    file too. Although if we are not using it or we are not planning to use them
    we can safely turn this publishing option off to save several DB queries or
    cache hits, eventually making the process faster.
    - *Ratings:* For this option the same behavior applies as for the comments
    - *Deletions:* Staging is gathering deletions (including trashed entities)
    in a site. These deletions can be published to clean up the live site. If
    it's not a problem to have lingering data on the live, or it is going to be
    processed later, this can be turned off as well to save execution time
    during the process.
-->

Before you activate staging, you can choose which of these applications' data
you'd like to copy to staging. You'll learn about many of the collaboration
apps listed under the Staged Portlets heading when you read the
[Collaboration Suite](/discover/portal/-/knowledge_base/7-0/collaboration)
chapter. For now, you just need to be aware that you can enable or disable
staging for any of these applications. Why might you want to enable staging for
some application types but not others? In the case of collaborative apps,
you probably *don't* want to enable staging since such applications are designed
for user interaction. If their content were staged, you'd have to manually
publish your site whenever somebody posted a message on the message boards to
make that message appear on the live site. Generally, you'll want web content to
be staged because end users aren't creating that kind of content--web content is
the stuff you publish to your site. But applications like the Message Boards or
Wiki would likely benefit from *not* being staged. Notice which applications are
marked for staging by default: if you enable staging and accept the defaults,
staging is *not* enabled for the collaborative apps.

The listed applications, or content groups, contain one or more specific entity.
For example, selecting the Web Content application does not mean you're only
selecting web content itself, but also web content folders.

Certain content types can be linked together and can reference each other on
different levels. One of the responsibilities of staging is to discover and
maintain these references when publishing. Site administrators and content
creators have control over the process on different levels: staging can be
enabled for a content group and a content group can be selected for publication.

Turning Staging on and off for individual portlet data could cause data
inconsistencies between the staging the live sites. Because of this, it's not
possible to modify the individual portlet configuration once you enable staging.
In case you need adjustments later on, you must turn staging off and re-enable
it with your new configuration.

Besides managing the app-specific content, @product@ also operates with several
special content types such as pages or users. For instance, pages are a part of
the site and can reference other content types, but in a special way. The page
references apps, which means publishing a page also implies publishing its apps.
The content gives the backbone of the site; however, content alone is useless.
To display content to the end user, you'll need apps as the building blocks for
your site.

Before you begin exploring the Staging UI, it's important to understand the
publishing process for staging, and making informed decisions so you use the
staging environment efficiently and effectively.

## Publishing Staged Content Efficiently [](id=publishing-staged-content-efficiently)

Now that you have a firm grasp on how staging works, you'll dive deeper into the
publication process and some prerequisites you should follow before publishing.
By understanding how the process works, you can make smart and informed
decisions about how you want to publish your site's content.

### Understanding the Publication Process [](id=understanding-the-publication-process)

In simple terms, publication is the process where all content, referenced
content, apps and their preferences, pages, etc. are transferred from
the staging scope to the live site. If you've enabled remote staging, this
process involves network communication with another remote site. From a low
level perspective, staging is an equivalence relation where entities are being
mirrored to a different location. From a high level perspective, you can think
of the staging publication process in three phases: export, validation, and
import. These parts are executed sequentially.

During the export phase, the publication configuration is processed, which
defines the site's contents and apps. This phase also gathers the obligatory
referenced entities that will be required on the live site. Then everything
according to the publication parameters has been processed into the instance's
own file format, and that file has been stored locally or transferred to the
remote live Liferay instance.

Next, the validation phase determines if it's possible to start the import
process. This phase verifies the file's version and its integrity, checks for
additional system information like language settings, and validates there is no
missing content referenced.

Lastly, the import phase makes any necessary updates or additions to the site's
content, layouts, and apps according to the publishing parameters. If everything
is verified and correct, the staged content is published to your live site.

A crucial factor for successfully publishing staged content is data integrity.
If anything is not successfully verified during the publication process, the
transactional database can revert the site back to its original state,
discarding the current publication. This is a necessary action to safeguard
against publishing incomplete information, which could break an otherwise
well-designed live site.

If the file system is not "database-stored" (e.g., DBStore), it's not
transactional and won't be reverted automatically if a staging failure occurs.
This could potentially cause a discrepancy between a file and where it's being
referenced from. Because of this, administrators should take great care if they
decide to stage the document library, making sure that regular backups of both
their database and their file system are being maintained.

Next, you'll learn about staging best practices and prerequisites to follow for
a seamless staging experience.

### Planning Ahead for Staging [](id=planning-ahead-for-staging)

Staging is a complex subsystem of @product@ that is designed to be flexible and
scalable. Before advanced users and administrators begin using it for their
site, it's important to plan ahead and remember a few tips for a seamless
process. There are several factors to evaluate.

The most obvious factor is the content itself, including its amount, type, and
structure. Depending on the content you'd like to use in your site, you can turn
on staging for only the necessary content types, and leave others turned off to
avoid unnecessary work. Publication can also be configured to publish only
certain types of content.

The next factor to consider is the hardware environment. You should plan your
environment according to the content types you're using. If your site operates
on large images and video files, you should contemplate whether to use a shared
network drive. For example, storing many large images in the Document Library
usually requires a faster network or local storage. If you're dealing with web
content, however, these are usually smaller and take up very little disk space.

The third major factor is possible customizations and custom logic for your
staging environment. Your organization's business logic is most likely
implemented in an app, and if you want to support staging for that app, you'll
need to write some code to accomplish this. You can also consider changing
default UI settings by writing new JSP code, if you want your staging
environment's look and feel to change.

Once you've finished planning for your site, it is advised to turn on staging at
the very beginning of the site creation process. This allows the site creator to
avoid waiting for huge publications that can take long periods to execute.
Taking smaller steps throughout the publication process forms an iterative
creative process as the site is built from the ground up, where content creators
can publish their changes immediately with no long wait times.

A few prerequisites to follow for staging are listed below:

- 4 GB of memory with 512 MB permgen
- 20 MB/s transfer rate minimum (disk)

Now that you know how the staging environment works and how to enable it for
your site, you'll begin using it in the next section.
