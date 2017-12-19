# Enabling Page Versioning and Staged Content [](id=enabling-page-versioning-and-staged-content)

Enabling page versioning for a site lets site administrators work in parallel on
multiple versions of the site's pages. Page versioning also maintains a history
of all updates to the site from the time page versioning was enabled. Site
administrators can revert to a previous version of the site at any time. This
flexibility is very important in cases where a mistake is found and it's
important to quickly publish a fix. 

You can enable page versioning for public pages or private pages on the Staging
Configuration page below the menu for selecting your staging environment (Local
or Remote). If you've already enabled staging, you can navigate to the Product
Menu &rarr; *Publishing* &rarr; *Staging* and click the
(![Options](../../../../images/icon-options.png)) button and select *Staging
Configuration*.

![Figure 1: You can decide to use versioning and choose what content should be staged.](../../../../images/staging-page-versioning-staged-content.png)

You can also choose content for the staging environment to manage on the Staging
Configuration page.

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
