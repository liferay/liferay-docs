# Deprecated Apps in 7.2: What to Do 

During the development of any software product, it's sometimes necessary to stop
development on or remove outdated or unpopular features. @product-ver@ is no
different. Liferay has deprecated several apps in @product-ver@. It's important
to note what, exactly, *deprecated* means here. 

| **Note:** All apps deprecated by Liferay are no longer in active development.
| You should therefore plan to stop using these apps. Such apps, however, may
| still be available for download.

There are three types of deprecated apps: 

1.  Deprecated apps that remain in @product@, but will be removed in a future
    release. (Availability: *Release \(Bundle\)*)

2.  Deprecated apps that have been removed from @product@, yet are still 
    available for download via [Liferay
    Marketplace](https://web.liferay.com/marketplace) (Availability:
    *Marketplace*) or [Liferay's public Nexus
    repository](https://repository.liferay.com/nexus/index.html#view-repositories)
    (Availability: *Nexus*). For instructions on installing apps, see the
    [Managing Apps](/docs/7-2/user/-/knowledge_base/u/managing-apps)
    documentation. 

3.  Deprecated apps that have been removed from @product@ and aren't available 
    for download. (Availability: *Removed*) 

This article lists the apps deprecated in @product-ver@, along with their 
availability. 

| **Note:** For information on apps deprecated in previous versions, please see their TODO

## Collaboration

| App |  Availability |  Notes |
| --- | ------------- | ------ |
| Chat | TBD |  |
| Directory | TBD |  |
| Group Statistics | TBD |  |
| Microblogs | TBD |  |
| Quick Note | TBD |  |
| Recently Downloaded | TBD |  |
| Social Activity | TBD |  |
| Social Networking | TBD | Formerly available as an unsupported Labs app via Marketplace. | 
| User Statistics | TBD |  |

## Foundation

| App |  Availability |  Notes |
| --- | ------------- | ------ |
| AlloyUI | TBD | Replaced by [Metal.js](https://metaljs.com/). | 
| Google Login | TBD | Replaced by OpenID Connect. |
| OpenID | TBD | Replaced by OpenID Connect. |
| Plugins SDK | Removed; 7.0 version on [GitHub](https://github.com/liferay/liferay-plugins) | Deprecated in 7.0. Replaced by [Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace).  |
| Search Portlet | TBD | Will be removed in a future release. Replaced by the [Search widgets](/docs/7-1/user/-/knowledge_base/u/whats-new-with-search). |
| Template Engines | TBD | Replaced by Freemarker. |
| WYSIWYG | TBD [Nexus](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.wysiwyg.web/) &rarr; Will be removed. |  Final version released for 7.0. |

## Web Experience

| App |  Availability |  Notes |
| --- | ------------- | ------ |
| RSS Publisher | TBD | See [the article](/docs/7-1/user/-/knowledge_base/u/the-rss-publisher-widget) on enabling and using this widget. |
| User Group Pages (Copy Mode) | TBD |  |
| Web Proxy Portlet | TBD [Nexus](https://repository.liferay.com/nexus/service/local/repositories/liferay-public-releases/content/com/liferay/com.liferay.web.proxy.web/2.0.0/com.liferay.web.proxy.web-2.0.0.jar) |  |
| Web Content Search Portlet | TBD [Nexus](https://repository.liferay.com/nexus/service/local/repositories/liferay-public-releases/content/com/liferay/com.liferay.journal.content.search.web/2.0.0/com.liferay.journal.content.search.web-2.0.0.jar) | The new Search widget can be configured to replace all the unique functionality of the Web Content Search |

## Forms and Workflow

| App |  Availability |  Notes |
| --- | ------------- | ------ |
| Web Form | TBD | Final version released for 7.0. |

## Security

| App |  Availability |  Notes |
| --- | ------------------ | ----------- |
| OAuth 1.0a | TBD | Replaced by OAuth 2.0, which is included in the bundle. |
| OpenID | TBD | Low usage. Use OpenID Connect instead. |
