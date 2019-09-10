---
header-id: deprecated-apps-in-7-2-what-to-do
---

# Deprecated Apps in 7.2: What to Do

[TOC levels=1-4]

During the development of any software product, it's sometimes necessary to stop
development on or remove outdated or unpopular features. @product-ver@ is no
different. In @product-ver@, Liferay has deprecated several apps and features. 

There are three types of deprecated apps: 

1.  Deprecated apps that remain in @product@, but will be removed in a future
    release. (Availability: *Bundled*)

2.  Deprecated apps that have been removed from @product@, yet are still 
    available for download via [Liferay
    Marketplace](https://web.liferay.com/marketplace) (Availability:
    *Marketplace*) 

3.  Deprecated apps that have been removed from @product@ and aren't available 
    for download. (Availability: *Removed*) 

| **Note:** All apps deprecated by Liferay are no longer in active development.
| You should therefore plan to stop using these apps. Such apps, however, may
| still be available for download.

| **Note:** For information on apps deprecated in @product@ 7.1, please see 
| [Deprecated Apps in 7.1: What to Do](/docs/7-1/deploy/-/knowledge_base/d/deprecated-apps-in-7-1-what-to-do)

Here are the apps deprecated in @product-ver@. 

## Foundation

| App |  Availability |  Notes |
| --- | ------------- | ------ |
| AlloyUI | Bundled | Replaced by [MetalJS](https://metaljs.com/) (temporary) exposed as [ClayUI tag](/docs/7-2/reference/-/knowledge_base/r/front-end-taglibs) equivalents. |  
| CMIS Store | Removed | Migrate to another [Document Repository Store option](/docs/7-2/deploy/-/knowledge_base/d/document-repository-configuration). Before [upgrading to @product-ver@](/docs/7-2/deploy/-/knowledge_base/d/upgrading-to-product-ver), migrate your document store data using [Data Migration in Server Administration](/docs/7-2/user/-/knowledge_base/u/server-administration). |
| JCRStore | Removed | Migrate to another [Document Repository Store option](/docs/7-2/deploy/-/knowledge_base/d/document-repository-configuration). Before [upgrading to @product-ver@](/docs/7-2/deploy/-/knowledge_base/d/upgrading-to-product-ver), migrate your document store data using [Data Migration in Server Administration](/docs/7-2/user/-/knowledge_base/u/server-administration). |
| Legacy Search Portlet | Bundled | Will be removed in a future release. Replaced by the [Search widgets](/docs/7-2/user/-/knowledge_base/u/search). |
| Sprite framework | Bundled | Liferay's image sprite framework is deprecated and is disabled by default via the `sprite.enabled` [portal property](/docs/7-2/deploy/-/knowledge_base/d/portal-properties). You can still build image sprites using any framework you like and deploy them in your plugins. |

## Personalization

| App |  Availability |  Notes |
| --- | ------------- | ------ |
| Audience Targeting | Removed | Replaced by [Personalization](/docs/7-2/user/-/knowledge_base/u/segmentation-and-personalization). | 

## Web Experience

| App |  Availability |  Notes |
| --- | ------------- | ------ |
| RSS Publisher | Bundled | See [the article](/docs/7-1/user/-/knowledge_base/u/the-rss-publisher-widget) on enabling and using this widget. |
| User Group Pages (Copy Mode) | Bundled | See the [Legacy User Group Sites Beahavior](/docs/7-1/user/-/knowledge_base/u/user-group-sites#legacy-user-group-sites-behavior) instructions on how to enable it. |
| Resources Importer | Bundled | Deprecated as of 7.1 with no direct replacement |

## Forms

| App | Availability | Notes |
| --- | ------------------ | ----------- |
| Web Form | Removed | Final version released for 7.0. |

## Security

| App |  Availability |  Notes |
| --- | ------------------ | ----------- |
| Central Authentication Service | Bundled | Migrate to [SAML based authentication](https://help.liferay.com/hc/en-us/articles/360028711032-Introduction-to-Authenticating-Using-SAML). |
| Google Login | Marketplace | Replaced by [OpenID Connect](/docs/7-2/deploy/-/knowledge_base/d/authenticating-with-openid-connect). |
| NTLM | Marketplace | Replaced by [Kerberos](/docs/7-2/deploy/-/knowledge_base/d/authenticating-with-kerberos). |
| OAuth 1.0a | Marketplace | Replaced by OAuth 2.0, which is included in the bundle. |
| OpenAM / OpenSSO | Bundled | Migrate to [SAML based authentication](https://help.liferay.com/hc/en-us/articles/360028711032-Introduction-to-Authenticating-Using-SAML). |
| OpenID | Marketplace | Replaced by [OpenID Connect](/docs/7-2/deploy/-/knowledge_base/d/authenticating-with-openid-connect). |

## User and System Management

| App |  Availability | 
| --- | ------------------ |
| Live Users | Enabled through the [`live.users.enabled`](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html) [portal property](/docs/7-2/deploy/-/knowledge_base/d/portal-properties). | 

## Related Topics 

[Apps in Maintenance Mode](/docs/7-2/deploy/-/knowledge_base/d/apps-in-maintenance-mode)
