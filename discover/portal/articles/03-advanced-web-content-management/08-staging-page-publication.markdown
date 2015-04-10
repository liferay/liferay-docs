# Staging Page Publication [](id=staging-page-publication)

Staging is an important feature of Liferay WCM. The concept of staging is a
simple one: you can modify your site behind the scenes and then publish all your
updates in one shot. You don't want users seeing your web site change before
their eyes as you're modifying it, do you? Liferay's staging environment allows
you to make changes to your site in a specialized *staging area*. When you're
finished, you can publish all your site changes at once.

Liferay provides site administrators with two different ways to set up staging:
Local Live and Remote Live. With Local Live staging, both your staging
environment and your live environment are hosted on the same server. When Local
Live staging is enabled for a site, a clone of the site is created containing
copies of all of the site's existing pages. Portlet data is also copied,
depending on which portlets are selected when staging is enabled. The cloned
site becomes the staging environment and the original site becomes the live
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

Liferay also offers the Page Versioning feature. This feature works with both
Local Live and Remote Live staging and allows site administrators to create
multiple variations of staged pages. This allows several different versions of
sites and pages to be developed at the same time. Variations can be created,
merged, and published using a Git-like versioning system. Let's jump in to see
how to use staging.

## Enabling Local Live Staging [](id=enabling-local-live-staging)

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
of all page modifications. We discuss page versioning in more detail below.

## Enabling Remote Live Staging [](id=enabling-remote-live-staging)

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
user ID *without* the user's password. You can specify any value for the
`tunneling.servlet.shared.secret` property; the value for your current server
just has to match the value of your remote server. Remember to restart both
Liferay servers after making these portal properties updates. After restarting,
log back in to your local Liferay portal instance as a site administrator. Then
navigate to the *Site Administration* &rarr; *Configuration* page for your site.
Next, click on *Site Settings* in the left menu and then on *Staging* listed
under the Advanced tab. Select *Remote Live* under Staging Type and additional
options appear.

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

**Tip:** In general, remote staging should be
 enabled for a site as early as possible. It's generally *not* a good idea to
 add gigabytes of data into Liferay's CMS and then decide to turn on remote
 staging.  There's an existing issue that limits Liferay to less than 2G of data
 for publishing data to a remote staging server:
 [http://issues.liferay.com/browse/LPS-35317](http://issues.liferay.com/browse/LPS-35317).
 You can check this issue to see if it's been resolved and to find out which
 versions of Liferay it affects.

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

#### Validating IPv6 Addresses [](id=validating-ipv6-addresses)

If your portal is set up to validate IPv6 addresses, you'll need to configure
your Remote Live Connection Settings. Restart your Liferay instance and navigate
back to the Staging page. Select the *Remote Live* radio selector and specify
the fields for your remote site. The *Remote Host/IP* field should match the
host you specified as your `tunnel.servlet.hosts.allowed` property in the
`portal-ext.properties` file (e.g., *[0:0:0:0:0:0:0:1]*). Make sure to include
the brackets. Fill in the rest of the information relevant to your site and
click *Save*. 

To check if the remote site is running on an IPv6 address, add a new portlet to
the staged site, and then select *Staging* &rarr; *Publish to Remote Live* from
the Dockbar. The changes are published to your remote staged site. 

Your portal instance now validates the IPv6 address you specified for your
remote live site. Great job! 

Next, you'll learn how to enable local live staging.

## Example: Enabling Local Live Staging [](id=example-enabling-local-live-staging)

Let's create a Local Live staging environment for the Lunar Resort home page. Before
we begin, let's add a new page. Click *Add &rarr; Page* from the left side menu
in the default site and name the new page *News and Events*. Next, click *News
and Events* to view the page. Then add the Alerts and Announcements portlets to
the News and Events page.

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

## Enabling Page Versioning and Staged Portlets [](id=enabling-page-versioning-and-staged-portlets)

Enabling page versioning for a site allows site administrators to work in
parallel on multiple versions of the site's pages. Page versioning also
maintains a history of all updates to the site from the time page versioning was
enabled. Site administrators can revert to a previous version of the site at any
time. This flexibility is very important in cases where a mistake is found and
it's important to quickly publish a fix. If you're following the Lunar Resort
example, check *Enabled On Public Pages* to enable page versioning for the
Lunar Resort site and then click *Save*.

![Figure 3.21: You can decide to use versioning and choose what content should be staged.](../../images/04-web-content-staging.png)

Before you activate staging, you can choose which portlets' data should be
copied to staging. We'll cover many of the collaboration portlets listed under
the Staged Portlets heading when we come to the
[Collaboration Suite](/discover/portal/-/knowledge_base/6-2/collaboration-suite)
chapter. For now, you just need to be aware that you can enable or disable
staging for any of these portlets. Why might you want to enable staging for some
portlet types but not others? In the case of collaborative portlets, you
probably *don't* want to enable staging since such portlets are designed for
user interaction. If their content were staged, you'd have to manually publish
your site whenever somebody posted a message on the message boards to make that
message appear on the live site. Generally, you'll want web content to be staged
because end users aren't creating that kind of content--web content is the stuff
you publish to your site. But portlets like the Message Boards or Wiki would
likely benefit from *not* being staged. Notice which portlets are marked for
staging by default: if you enable staging and accept the defaults, staging is
*not* enabled for the collaborative portlets.

## Using the Staging Environment [](id=using-the-staging-environment)

After enabling staging (either Local Live or Remote Live) for a site, you'll
notice a colored bar with some new menus on the Dockbar when you navigate to
the site. These new menus help us manage staged pages. You'll also notice that
most of your page management options have been removed, because now you can't
directly edit live pages. You now must use the staging environment to make
changes. Click on the *Staging* button to view the staged area. Your
management options are restored and you can access some new options related to
staging. If you're following along with the Lunar Resort example, navigate back
to the News and Events page and click on *Staging* to get your page editing
capabilities back.

![Figure 3.22: You can see the new staging options added to the top of your screen.](../../images/04-web-content-staging-live-page.png)

Add the Bookmarks portlet and then click on *Live* from the Dockbar. Notice that
the Bookmarks portlet isn't there. That's because you've staged a change to the
page but haven't published that change yet to the live site. Go back to the
staged page and click on the *Staging* drop-down menu to look at the options you
have available. From here you have many options to choose from to help in your
staging conquest.

**Manage Site Pages Variations:** allows you to work in parallel on multiple
versions of a staged site page. We'll explain this later.

**Manage Page Variations:** allows you to work in parallel on multiple versions
of a staged page. We'll explain this later.

**Undo/Redo:** allows you to step back/forward through recent changes to a page,
which can save you the time of manually adding or removing portlets if you make
a mistake.

**History:** shows you the list of revisions of the page, based on publication
dates. You can go to any change in the revision history and see how the pages
looked at that point.

**Mark as Ready for Publication:** After you're done making changes to the
staged page, click this button. The status of the page changes from *Draft* to
*Ready for Publication* and any changes you've made can be published to the Live
Site. When you publish a page to live, only the version which was *Marked as
Ready for Publication* is published.

The *Publish to Live* button gives you the option to publish to Live *Now* or
*Schedule* a publication to Live.

**Now:** immediately pushes any changes to the Live Site.

**Schedule:** lets you set a specific date to publish or to set up recurring
publishing. You could use this, for example, to publish all changes made during
the week every Monday morning without any further intervention.

The *Publish to Live* button also gives you options to select pages,
applications, content, and permissions. Furthermore, you're given the *Current
and Previous* and *Scheduled* tabs. The *Current and Previous* tab lets you view
past publications along with their authors and create/completion dates. The
*Scheduled* tab lets you view what publication processes are scheduled for
publishing.

**Pages** gives you the option to choose which pages to include when you
publish. You can see the default settings in the gray text below the header.
Click the *Change* button to configure these options to suite your needs.

**Application Configuration** allows you to select which applications you want
to publish. Select the *Choose Applications* radio button to configure
the applications and settings to be published.

**Content** allows you to configure the content to be published. Clicking the 
*select* option under All Content allows you to delete the portlet metadata
before publishing.

**Permissions** allows you to include permissions for the pages and portlets
when the changes are published.

The *Date Range* feature provides you with another way to select specific
content to publish. Specifying a date range allows you to choose content to
publish based on when it was created. You can change the date range of content
by selecting *Choose Content* in the *Content* section. Then click *Change* next
to *Date Range*. You have several options to choose from:

-  *All:* publishes content regardless of its creation date.

-  *From Last Publish Date:* publishes content that was created since the last
   publish date.

-  *Date Range:* publishes content based on a specified date range. You can set a
   start and end date/time window. The content created within that window of
   time is published.

-  *Last:* publishes content based on a set amount of time since the current
   time.  For example, you can set the date range to the past 48 hours,
   starting from the current time.

Click on *Mark as Ready for Publication*, then click *Publish to Live* &rarr;
*Now*, and select *Publish* to publish your Bookmarks portlet to the live site.

Content publication can be also controlled using staging. Bookmarks are staged
by default (this can be changed in Staging Configuration). If you create a
bookmark in the staged site, it isn't visible in the live site until you publish
it to the live site following the same steps you just performed (you can select
which types of content are published when you publish to the live site). If
workflow is enabled for any new resource, the resource needs to go through the
workflow process before it can be published to the live site.

![Figure 3.23: Ready to publish to the live site.](../../images/04-web-content-staging-publish.png)

Web content tends to be frequently updated, often more so than other kinds of
content. For some web content articles, this can result in very high numbers of
versions, sometimes hundreds. Such high version numbers can make it very slow to
publish web content articles. Liferay addresses this issue by allowing site
administrators to choose whether or not to publish a web content article's
version history when a staged article is ready to be published. To use this
feature, staging must be enabled. Edit a web content article that's being
displayed in a Web Content Display portlet on one of your pages and then click
*Staging* &rarr; *Publish to Live* from the Dockbar. In the popup, expand the
Content heading and find the section for Web Content. You can click the *Change*
button to select/deselect options to publish dealing with your new Web Content
instance.

![Figure 3.24: Click the *Change* button and uncheck the version history box to only publish the latest approved version of web content articles that have multiple versions.](../../images/web-content-version-history-box.png)

Liferay 6.2 also added a portal property,
`journal.publish.version.history.by.default`, that sets the default behavior. By
default, this property is set to `true` so site administrators have to manually
uncheck the *Version History* box mentioned above if they only want to publish 
the latest approved version of web content articles. If you'd like to change the 
default behavior, add the following line to your `portal-ext.properties` file:

    journal.publish.version.history.by.default=false

One of the most powerful features of staging is page variations. Let's see how
to use them to create multiple different variations of your site's pages for
different purposes.

## Using Site Pages Variations [](id=using-site-pages-variations)

Let's say you're working on a product-oriented site where you'll have several
major changes to a page or a set of pages over a short period of time. Also you
need to be working on multiple versions of the site at the same time to ensure
everything has been properly reviewed before it goes live. With staging in
Liferay 6.2 you can do this using *Page Variations*.

For example, you can create several page variations, enabling the marketing team
to give your site a completely different look and feel for Christmas. At the
same time, the product management team can work on a different version that will
be published the day after Christmas for the launching of a new product.
Additionally, the product management team is considering two different ideas for
the home page of the site, so they can create several page variations of the
home page inside their product launch site.

Variations only affect pages and not the content, which means all the existing
content in your staging site is shared by all your variations. In different site
page variations you can have different logos, different look and feel for your
pages, different applications on these pages, different configuration of these
applications and even different pages. One page can exist in just one site page
variation or in several of them.

By default, we only have one site page variation which is called *Main
Variation*. To create a new one, use the dropdown next to the *Staging* link
and click on *Manage Site Pages Variations*. This brings you to a list of the
existing site page variations for your site. Click *Add Site Pages Variation* to
create a new one. From the *Add Site Pages Variation* screen, you can set a
Name, Description, and also set your new variation to copy the content from an
existing variation. There are several options to choose in this selector.

**All Site Pages Variations:** creates a new variation that contains the last
version marked as ready for publication from any single page existing in any
other variation.

**None (Empty Site Pages Variation):** creates a new, empty variation.

**Main Variation:** creates a new site page variation that contains only the
last version of all the pages that exist in this variation. The current
variation must be marked as ready for publication.

You are also able to rename any variation. For example, edit the Main Variation
and change its name to something that makes more sense in your site, such as
*Basic*, *Master*, *Regular* and create a variation for Christmas.

You can switch between different variations by clicking on them from the staging
menu bar. It's also possible to set permissions on each variation, so certain
users have access to manage some, but not all variations.

You can now go to the home page of your Christmas variation and change the logo,
apply a new theme, move portlets around, change the order of the pages and
configure different portlets. The other variations won't be affected. You can
even delete existing pages or add new ones (remember to *Mark as Ready for
Publication* when you are finished with your changes).

When you delete a page, it is deleted only in the current variation. The same
happens when you add a new page. If you try to access a page which was deleted
in the current variation, Liferay informs you this page is not *enabled* in this
variation and you must enable it. To enable it, navigate to the *Site
Administration* &rarr; *Pages* &rarr; *Site Pages* screen. Here all the existing
pages for all the variations are shown in a tree. Pages not enabled for the
current variation are shown in a lighter color. You can also access Staging
options from the Site Pages screen by clicking the *Staging* drop-down menu
located above the pages tree.

To publish a variation to the live site, click on *Publish to Live* &rarr;
*Publish* in the dropdown next to the variation name. Publications can also be
scheduled independently for different variations. For example, you could have a
variation called *Mondays* which is published to the live site every Monday and
another one called *Day 1* which is published to the live site every first day
of each month.

You can also have variations for a single page inside a site page variation,
which allows you to work in parallel in different versions of a page. For
example, you might work on two different proposals for the design of the home
page for the Christmas variation. These page variations only exist inside a site
Page variation.

To create a new page variation, click *Manage Page Variations* on the staging
toolbar. This brings you to a list of existing page variations for the current
page (by default, there is only one called *Main Variation*). You can create
more or rename the existing one. You can switch between different page
variations using the toolbar containing the page variations below the site pages
variations toolbar. When you decide which page variation should be published,
mark it as *Ready for Publication*. Only one page variation can be marked as
ready for publication and that is the one that gets published to the live site.

For example, we could create a page variation called Thanksgiving for the News
and Events page inside of the Christmas variation and another one called
Christmas Day to display different content on those particular days.

![Figure 3.25: This is an example of a Thanksgiving Page Variation.](../../images/04-web-content-branch-thanksgiving.png)

Another powerful feature is the possibility of *merging* Site Pages Variations.
To merge two Site Pages Variations, you need to go to the Manage Site Pages
Variations screen. From there, click on *Merge* on the Site Pages Variation you
want to use as the base. You will be asked to choose the Site Pages Variation to
merge on top of it. Merging works in the following way:

* New pages that don't exist in the base Variation, will be added.
* If a page exists in both Site Pages variations, and at least one version of
the page was marked as ready for publication, then the latest version marked as
ready will be added as a new Page Variation in the target page of the base
Variation. (Note that older versions or page variations not marked as ready for
publication won't be copied. However, merge can be executed as many times as
needed and will create the needed pages variations in the appropriate page of
the base Site Pages Variation).
* Merging does not affect content nor will overwrite anything in the base
Variation, it will just add more versions, pages and page variations as needed.

Let's finish our discussion of staging by outlining a few more features.

## Wrapping up Staging [](id=wrapping-up-staging)

You can enable staging on an individual site basis, depending on your needs.
This makes it easy to put strict controls in place for your public web site,
while opening things up for individual sites that don't need such strict
controls. Liferay's staging environment is extremely easy to use and makes
maintaining a content-rich web site a snap.

Let's examine teams next.
