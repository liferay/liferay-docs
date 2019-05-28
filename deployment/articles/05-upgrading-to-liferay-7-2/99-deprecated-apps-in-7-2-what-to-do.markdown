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

| **Note:** For information on apps deprecated in @product@ 7.1, please see 
| [Deprecated Apps in 7.1: What to Do](/docs/7-1/deploy/-/knowledge_base/d/deprecated-apps-in-7-1-what-to-do)

Here are the apps deprecated in @product-ver@. 

## Collaboration

| App |  Availability |  Notes |
| --- | ------------- | ------ |
| Bookmarks | Marketplace |  |
| Directory | Nexus |  |
| Documentum | Nexus |  |
| Microblogs | Nexus |  |
| Quick Note | Nexus |  |
| Recent Documents | Nexus |  |
| Social Bookmarks | Marketplace | |
| Social Networking | Nexus | |

## Foundation

| App |  Availability |  Notes |
| --- | ------------- | ------ |
| AlloyUI | TBD | Replaced by [Metal.js](https://metaljs.com/). | 
| Google Login | Marketplace | Replaced by [OpenID Connect]((/docs/7-2/deploy/-/knowledge_base/d/authenticating-with-openid-connect). |
| JCRStore | Removed | Migrate to another [Document Repository Store option](/docs/7-2/deploy/-/knowledge_base/d/document-repository-configuration). Before [upgrading to @product-ver@](/docs/7-2/deploy/-/knowledge_base/d/upgrading-to-product-ver), migrate your document store data using [Data Migration in Server Administration](/docs/7-2/user/-/knowledge_base/u/server-administration). |
| OpenID | Marketplace | Replaced by [OpenID Connect]((/docs/7-2/deploy/-/knowledge_base/d/authenticating-with-openid-connect). |
| Plugins SDK | Removed; 7.0 version on [GitHub](https://github.com/liferay/liferay-plugins) | Deprecated in 7.0. Replaced by [Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/liferay-workspace).  |
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
| NTLM | Source code at `modules/apps/portal-security-sso-ntlm`. Marketplace release planned. | Replaced by [Kerberos](/docs/7-2/deploy/-/knowledge_base/d/authenticating-with-kerberos). |
| OAuth 1.0a | Marketplace | Replaced by OAuth 2.0, which is included in the bundle. |
| OpenID | Marketplace | [OpenID Connect]((/docs/7-2/deploy/-/knowledge_base/d/authenticating-with-openid-connect). |
