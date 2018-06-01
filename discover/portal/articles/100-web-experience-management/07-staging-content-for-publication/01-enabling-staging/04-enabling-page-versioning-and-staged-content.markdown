# Enabling Page Versioning and Staged Content [](id=enabling-page-versioning-and-staged-content)

Enabling page versioning for a site lets site administrators work in parallel on
multiple versions of the site's pages. Page versioning also maintains a history
of all updates to the site from the time page versioning was enabled. Site
administrators can revert to a previous version of the site at any time. This
flexibility is very important in cases where a mistake is found and it's
important to publish a fix quickly. 

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
they are not eligible for staging. @product@ by default supports the following
content groups for staging:

- Application Display Templates
- Blogs
- Bookmarks
- Calendar
- Documents and Media
- Dynamic Data Lists
- Forms
- Knowledge Base
- Message Boards
- Mobile Device Families
- Polls
- Web Content
- Wiki

Before you activate staging, choose which of these applications' data you'd
like to copy to staging. You'll learn about many of the collaboration apps
listed under the Staged Portlets heading when you read the
[Collaboration Suite's](/discover/portal/-/knowledge_base/7-1/collaboration)
section of articles. For now, be aware that you can enable or disable staging
for any of these applications. Why might you want to enable staging for some
application types but not others? In the case of collaborative apps, you
probably *don't* want to enable staging since such applications are designed
for user interaction. If their content were staged, you'd have to 
publish your site manually whenever somebody posted a message on the message
boards to make that message appear on the live site. Generally, you want web
content to be staged because end users aren't creating that kind of
content---web content is the stuff you publish to your site. But applications
like the Message Boards or Wiki should *not* be staged. Notice which
applications are marked for staging by default: if you enable staging and
accept the defaults, staging is *not* enabled for the collaborative apps.

The listed applications, or content groups, contain one or more specific
entity. For example, selecting the Web Content application does not mean you're
only selecting web content itself, but also web content folders.

Certain content types can be linked together and can reference each other on
different levels. One of the responsibilities of staging is to discover and
maintain these references when publishing. Site administrators and content
creators have control over the process on different levels: staging can be
enabled for a content group and a content group can be selected for publication.

Turning Staging on and off for individual portlet data could cause data
inconsistencies between the staging and live sites. Because of this, it's not
possible to modify the individual portlet configuration once you enable staging.
In case you need adjustments later on, you must turn staging off and re-enable
it with your new configuration.

Besides managing the app-specific content, @product@ also has several special
content types such as pages or users. For instance, pages are a part of the
site and can reference other content types, but in a special way. The page
references apps, which means publishing a page also implies publishing its
apps. The content gives the backbone of the site; however, content alone is
useless. To display content to the end user, you'll need apps as the building
blocks for your site.

Before you begin exploring the Staging UI, it's important to understand the
publishing process for staging to make informed decisions so you use the
staging environment efficiently and effectively.
