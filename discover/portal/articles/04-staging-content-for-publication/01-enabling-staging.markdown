# Enabling Staging

Liferay provides site administrators with two different ways to set up staging:
Local Live and Remote Live. With Local Live staging, both your staging
environment and your live environment are hosted on the same server. When Local
Live staging is enabled for a site, a clone of the site is created containing
copies of all of the site's existing pages. Typically, this means the staging
and live enviornments share the same JVM, database, portlet data (depending on
which portlets are selected when staging is enabled), and setup configurations,
such as the properties set in the `portal-ext.properties` file. The cloned site
becomes the staging environment and the original site becomes the live
environment.

When Remote Live staging is enabled for a site, a connection is established
between the current site and another site on a remote Liferay server. The remote
site becomes the live environment and the current site becomes the staging
environment--an instance of Liferay Portal used solely for staging. Content
creators can use the staging server to make their changes while the live server
handles the incoming user traffic. When changes to the site are ready to be
published, they are pushed over the network to the remote live server. Whether
you enable Local Live or Remote Live staging, the interface for managing and
publishing staged pages is the same. 

So when should you use Local Live staging and when should you use Remote Live
Staging? Local Live staging allows you to publish site changes very quickly,
since the staged and live environments are on the same server. It's also easier
to switch between the staged and live environments using Local Live staging.
However, since the staged content is stored in the same database as the
production content, the content isn't as well protected or backed up as with
Remote Live staging. Also, you can't install new versions of portlets for
testing purposes in a Local Live staging environment since only one version of a
portlet can be installed at any given time on a single Liferay server.

With Remote Live staging, your staging and live environments are hosted on
separate servers. This allows you to deploy new versions of portlets and
content to your staging environment without worrying about interfering with your
live environment. With Remote Live staging, you can also use one Liferay
instance as the staging server for multiple production servers. However,
publishing is slower with Remote Live than with Local Live since data needs to
be transferred over a network. And, of course, you need more hardware to run a
separate staging server.

## Enabling Local Live Staging

Site administrators can enable Staging for a site via the Site Settings UI. To
reach this interface, navigate to the *Site Administration* &rarr;
*Configuration* page of your site, make sure *Site Settings* is highlighted in
the left menu, and click on *Staging* listed under the Advanced tab. Under
Staging Type, select either *Local Live* or *Remote Live* and additional options
appear. Staging allows changes to be made in a staging environment so that work
can be reviewed, possibly using a workflow, before it's published to a live
site. Enabling Local Live staging is easy. Just select *Local Live* and decide
whether you'd like to enable page versioning. You can enable page versioning on
a site's public pages, private pages, both, or neither. Page versioning allows
you to work in parallel on different versions of pages and maintains a history
of all page modifications. You'll learn about page versioning in more detail
below.

To get some hands-on experience with enabling Local Live staging, you can
complete a brief example which creates a Local Live staging environment for the
Lunar Resort home page. Before beginning, you'll need to add a new page. Click
*Add &rarr; Page* from the left side menu in the default site and name the new
page *News and Events*. Next, click *News and Events* to view the page. Then add
the Alerts and Announcements portlets to the News and Events page.

When you activate staging Local Live staging, Liferay creates a clone of your
site. This clone became the staging environment. Because of this, we recommend
only activating staging on new, clean sites. Having a few pages and some
portlets (like those of the example site we've created) is no big deal. However,
if you have already created a large amount of content you might not be able to
enable staging on that site. Also, if you intend to use page versioning to track
the history of updates to your site, we recommend that you enable it as early as
possible, *before* your site has many pages and lots of content. Your site's
update history won't be saved until you enable page versioning. Page versioning
requires staging (either Local Live or Remote Live) to be enabled.

Now you're ready to activate staging for this site. Go to *Admin* &rarr; *Site
Administration* &rarr; *Configuration* &rarr; *Site Settings* and select 
*Staging* from under the *Advanced* heading. We'll assume you don't have a 
separate staging server, so select the *Local Live* staging type. If you do have
a separate server to use for staging, follow the instructions in the previous
section for configuring it and your local server for remote staging. Either
way, once you make a selection (either *Local Live* or *Remote Live*), more
options become available for page versioning and staged portlets.

## Enabling Remote Live Staging

When you enable Remote Live staging, the remote site becomes the live
environment and the current site becomes the staging environment. The remote
(live) Liferay server and the local (staging) Liferay server should be
completely separate systems. They should not, for example, share the same the
database. When Remote Live staging is enabled, all the necessary information is
transferred over the network connecting the two servers.

Before a site administrator can enable Remote Live staging for a site, the
remote Liferay server must be added to the current Liferay server's list of
allowed servers. The current Liferay server must also be added to the remote
Liferay server's list of allowed servers. You also need to specify an
authentication key to be shared by your current and your remote server and
enable each Liferay server's tunneling servlet authentication verifier. You can
make all of these configurations in your Liferay servers'
`portal-ext.properties` files.  Your first step should be to add the following
lines to your current Liferay server's `portal-ext.properties` file:

    tunnel.servlet.hosts.allowed=127.0.0.1,SERVER_IP,[Remote server IP address]
    axis.servlet.hosts.allowed=127.0.0.1,SERVER_IP,192.168.0.16,[Remote server IP address]
    tunneling.servlet.shared.secret=[secret]
    auth.verifier.TunnelingServletAuthVerifier.hosts.allowed=

Then add the following lines to your remote Liferay server's
`portal-ext.properties` file:

    tunnel.servlet.hosts.allowed=127.0.0.1,SERVER_IP,[Local server IP address]
    axis.servlet.hosts.allowed=127.0.0.1,SERVER_IP,192.168.0.16,[Local server IP address]
    tunneling.servlet.shared.secret=[secret]
    auth.verifier.TunnelingServletAuthVerifier.hosts.allowed=

Liferay's use of a pre-shared key between your staging and production
environments helps secure the remote publication process. It also removes the
need to send the publishing user's password to the remote server for web service
authentication. Using a pre-shared key allows Liferay to create an authorization
context (permission checker) from the provided email address, screen name, or
user ID *without* the user's password.

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

Remember to restart both Liferay servers after making these portal properties
updates. After restarting, log back in to your local Liferay portal instance as
a site administrator. Then navigate to the *Site Administration* &rarr;
*Configuration* page for your site. Next, click on *Site Settings* in the left
menu and then on *Staging* listed under the Advanced tab. Select *Remote Live*
under Staging Type and additional options appear.

![Figure 3.20: After your remote Liferay server and local Liferay server have been configured to communicate with each other, you have to specify a few Remote Live connection settings.](../../images/remote-live-staging-settings.png)

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
your local Liferay server. You can find any site's ID by selecting *Actions
&rarr; Edit* next to the site's name on the Sites page of the Control Panel.
Finally, it's best to check the *Use a Secure Network Connection* field to use
HTTPS for the publication of pages from your local (staging) Liferay server to
your remote (live) Liferay server.

+$$$

**Tip:** In general, remote staging should be enabled for a site as early as
possible. It's generally *not* a good idea to add gigabytes of data into
Liferay's CMS and then decide to turn on remote staging. There's an existing
issue that limits Liferay to less than 2G of data for publishing data to a
remote staging server:
[http://issues.liferay.com/browse/LPS-35317](http://issues.liferay.com/browse/LPS-35317).
You can check this issue to see if it's been resolved and to find out which
versions of Liferay it affects.

<!-- Check into this issue, and find out if there is still a 2G limit for data
publishing. -Cody -->

$$$

That's all you need to do to enable Remote Live Staging! Note that if you fail
to set the tunneling servlet shared secret or the values of these properties on
your current and remote servers don't match, you won't be able to enable staging
and an error message appears. When a user attempts to publish changes from the
local (staging) server to the remote (live) server, Liferay passes the user's
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
For this reason, we recommend that you use LDAP to copy selected user accounts
from your local (staging) Liferay server to your remote (live) Liferay server.
Liferay's Virtual LDAP Server application (EE-only), available on Liferay
Marketplace, makes this easy.

Next, you'll learn how to configure your portal to use IPv6 addresses for
Remote Live Staging. 

+$$$

**Important:** If your portal is set up to validate IPv6 addresses, you'll need
to configure your Remote Live Connection Settings. Restart your Liferay instance
and navigate back to the Staging page. Select the *Remote Live* radio selector
and specify the fields for your remote site. The *Remote Host/IP* field should
match the host you specified as your `tunnel.servlet.hosts.allowed` property in
the `portal-ext.properties` file (e.g., *[0:0:0:0:0:0:0:1]*). Make sure to
include the brackets. Fill in the rest of the information relevant to your site
and click *Save*. 

To check if the remote site is running on an IPv6 address, add a new portlet to
the staged site, and then select *Staging* &rarr; *Publish to Remote Live* from
the Dockbar. The changes are published to your remote staged site. 

$$$

Next, you'll learn how to enable page versioning and staged portlets.

## Enabling Page Versioning and Staged Content

Enabling page versioning for a site allows site administrators to work in
parallel on multiple versions of the site's pages. Page versioning also
maintains a history of all updates to the site from the time page versioning was
enabled. Site administrators can revert to a previous version of the site at any
time. This flexibility is very important in cases where a mistake is found and
it's important to quickly publish a fix. If you're following the Lunar Resort
example, check *Enabled On Public Pages* to enable page versioning for the
Lunar Resort site and then click *Save*.

![Figure 3.21: You can decide to use versioning and choose what content should be staged.](../../images/04-web-content-staging.png)

Choosing content to be staged may sound self-explanatory, but content must have
specific attributes in Liferay Portal to use it in a staged environment. Content
or an entity should be site-scoped, so they are always part of a site;
otherwise, they are not eligible for staging. Liferay Portal supports the
following content groups for staging, by default:

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

Before you activate staging, you can choose which of these applications' data
you'd like to copy to staging. You'll learn about many of the collaboration
portlets listed under the Staged Portlets heading when we come to the
[Collaboration Suite](/discover/portal/-/knowledge_base/6-2/collaboration-suite)
chapter. For now, you just need to be aware that you can enable or disable
staging for any of these applications. Why might you want to enable staging for
some application types but not others? In the case of collaborative portlets,
you probably *don't* want to enable staging since such applications are designed
for user interaction. If their content were staged, you'd have to manually
publish your site whenever somebody posted a message on the message boards to
make that message appear on the live site. Generally, you'll want web content to
be staged because end users aren't creating that kind of content--web content is
the stuff you publish to your site. But applications like the Message Boards or
Wiki would likely benefit from *not* being staged. Notice which applications are
marked for staging by default: if you enable staging and accept the defaults,
staging is *not* enabled for the collaborative portlets.

The listed applications, or content groups, contain one or more specific
entities. For example, selecting the Web Content application does not mean
you're only selecting web content itself, but also web content folders.

Certain content types can be linked together and can reference each other on
different levels. One of the responsibilities of staging is to discover and
maintain these references when publishing. Site administrators and content
creators have control over the process on different levels: staging can be
enabled for a content group and a content group can be selected for publication.

Besides managing the portlet-specific content, the portal also operates with
several special content types such as pages or users. For instance, pages are a
part of the site and can reference other content types, but in a special
way. The page references portlets, which means publishing a page also implies
publishing its portlets.

The content gives the backbone of the site; however, content alone is useless.
To display content to the end user, you'll need portlets as the building blocks
for your site.

Before you begin exploring the Staging UI, it's important to understand the
publishing process for Staging, and making informed decisions so you use the
staging environment efficiently and effectively.

## Publishing Staged Content Efficiently

Now that you have a firm grasp on how staging works, you'll dive deeper into the
publication process and some prerequisites you should follow before publishing.
By understanding how the process works, you can make smart and informed
decisions about how you want to publish your site's content.

### Understanding the Publication Process

In simple terms, publication is the process where all content, referenced
content, portlets and their preferences, pages, users, etc. is transferred from
the staging scope to the live site. If you've enabled remote staging, this
process involves network communication with another remote site. From a low
level perspective, staging is an equivalence relation where entities are being
mirrored to a different location. From a high level perspective, you can think
of the staging publication process in three phases: export, validation, and
import. These parts are executed sequentially.

During the export phase, the publication configuration is processed, which
defines the site's contents and portlets. This phase also gathers the obligatory
referenced entities that will be required on the live site. Then everything
according to the publication parameters has been processed into the portal's own
file format, and that file has been stored locally or transferred to the remote
live portal instance.

Next, the validation phase determines if it's possible to start the import
process. This phase verifies the file's version and its integrity, checks for
additional system information like language settings, and validates there is no
missing content.

Lastly, the import phase makes any necessary updates or additions to the site's
content, layouts, and portlets according to the publishing parameters. If
everything is verified and correct, the staged content is published to your live
site.

A crucial factor for successfully publishing staged content is data integrity.
If anything is not successfully verified during the publication process, all
staged data is saved, and the orginal state of the site is restored, discarding
the current publication. This is a necessary action to safeguard against
publishing incomplete information, which could break an otherwise well-designed
live site.

Next, you'll learn about staging best practices and prerequisites to follow for
a seamless staging experience.

### Planning Ahead for Staging

