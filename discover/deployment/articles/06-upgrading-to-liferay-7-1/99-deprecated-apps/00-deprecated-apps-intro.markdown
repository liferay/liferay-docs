---
header-id: deprecated-apps-in-7-1-what-to-do
---

# Deprecated Apps in 7.1: What to Do

[TOC levels=1-4]

During the development of any software product, it's sometimes necessary to stop 
development on or remove outdated or unpopular features. @product-ver@ is no 
different. Liferay has deprecated several apps in @product-ver@. It's important 
to note what, exactly, *deprecated* means here. 

| **Note:** All apps deprecated by Liferay are no longer in active development.
| You should therefore plan to stop using these apps. Such apps, however, may
| still be available for download.

There are three types of deprecated apps: 

1.  Deprecated apps that remain in @product@, but will be removed in a future
    release. 

2.  Deprecated apps that have been removed from @product@, yet are still 
    available for download via 
    [Liferay Marketplace](https://web.liferay.com/marketplace) 
    or 
    [Liferay's public Nexus repository](https://repository.liferay.com). For 
    instructions on installing apps, see the 
    [Managing Apps](/docs/7-1/user/-/knowledge_base/u/managing-apps) 
    documentation. 

3.  Deprecated apps that have been removed from @product@ and aren't available 
    for download. 

This article lists the apps deprecated in @product-ver@, along with their 
availability. 

## Collaboration

| App | &nbsp;Availability | &nbsp;Notes |
| --- | ------------------ | ----------- | 
| Bookmarks | Marketplace |  |
| Chat | Removed |  |
| Directory | Nexus |  |
| Documentum Connector | Removed |  |
| Events List | Removed |  |
| Friend Requests | Removed |  |
| Group Statistics | Nexus |  |
| IP Geocoder | Removed |  |
| Invitation | Removed |  |
| JAMWiki | Removed |  |
| JSPWiki | Removed |  |
| Media Wiki | Removed |  |
| Message Boards Subscription Manager | Removed |  |
| Microblogs | Nexus |  |
| Private Messaging | Removed |  |
| Quick Note | Nexus |  |
| Recently Downloaded | Nexus |  |
| Social Activity | Nexus |  |
| Social Networking | Nexus | Formerly available as an unsupported Labs app via Marketplace. |
| Twitter | Removed |  |
| User Statistics | Nexus |  |

## Foundation

| App | &nbsp;Availability | &nbsp;Notes |
| --- | ------------------ | ----------- |
| AlloyUI | Release (bundle) | Replaced by [Metal.js](https://metaljs.com/). | 
| CMIS Store | Will be removed | Migrate to another [Document Repository Store option](/docs/7-1/deploy/-/knowledge_base/d/document-repository-configuration). Before [upgrading to @product-ver@](/docs/7-1/deploy/-/knowledge_base/d/upgrading-to-liferay-71), migrate your document store data using [Data Migration in Server Administration](/docs/7-1/user/-/knowledge_base/u/server-administration). |
| Columns Layout | Removed | |
| Google Login | Release (bundle) | Replaced by OpenID Connect. |
| JCRStore | Will be removed | Migrate to another [Document Repository Store option](/docs/7-1/deploy/-/knowledge_base/d/document-repository-configuration). Before [upgrading to @product-ver@](/docs/7-1/deploy/-/knowledge_base/d/upgrading-to-liferay-71), migrate your document store data using [Data Migration in Server Administration](/docs/7-1/user/-/knowledge_base/u/server-administration). |
| Marvel Integration | Removed | Replaced by X-Pack. |
| OpenID | Release (bundle) | Replaced by OpenID Connect. |
| OpenSocial Gadget | Removed | |
| Plugins SDK | [GitHub](https://github.com/liferay/liferay-plugins) | Deprecated in 7.0. Replaced by [Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace). |
| Ruby Console | Removed | |
| Search Portlet | Release (bundle) | Will be removed in a future release. Replaced by the [Search widgets](/docs/7-1/user/-/knowledge_base/u/whats-new-with-search). |
| Shield Integration | Removed | Replaced by X-Pack. |
| Template Engines | Release (bundle) | Replaced by Freemarker. |
| vLDAP plugin (Virtual LDAP Server EE) | Removed | |
| WSRP | Removed | |
| WYSIWYG | [Nexus](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.wysiwyg.web/) &rarr; Will be removed. |  Final version released for 7.0. |

## Web Experience

| App | &nbsp;Availability | &nbsp;Notes |
| --- | ------------------ | ----------- |
| RSS Publisher | Release (bundle) | See [the article](/docs/7-1/user/-/knowledge_base/u/the-rss-publisher-widget) on enabling and using this widget. |
| User Group Pages (Copy Mode) | Release (bundle) |  |
| Web Proxy Portlet | [Nexus](https://repository.liferay.com/nexus/service/local/repositories/liferay-public-releases/content/com/liferay/com.liferay.web.proxy.web/2.0.0/com.liferay.web.proxy.web-2.0.0.jar) |  |
| Web Content Search Portlet | [Nexus](https://repository.liferay.com/nexus/service/local/repositories/liferay-public-releases/content/com/liferay/com.liferay.journal.content.search.web/2.0.0/com.liferay.journal.content.search.web-2.0.0.jar) | The new Search widget can be configured to replace all the unique functionality of the Web Content Search |
| Web Content List | Removed | The Asset Publisher can be configured to replace all the unique functionality of the Web Content List |

## Forms and Workflow

| App | &nbsp;Availability | &nbsp;Notes |
| --- | ------------------ | ----------- |
| Web Form | Nexus &rarr; Will be removed. | Final version released for 7.0. |

## Security

| App | &nbsp;Availability | &nbsp;Notes |
| --- | ------------------ | ----------- |
| OAuth 1.0a | Marketplace | Replaced by OAuth 2.0, which is included in the bundle. |
| OpenID | Release (bundle) | Low usage. Use OpenID Connect instead. |
