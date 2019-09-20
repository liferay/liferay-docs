# 7.1で廃止予定のアプリケーション：対処方法[](id=deprecated-apps-in-7-1-what-to-do)

ソフトウェア製品の開発中は、開発を中止したり、古くなった機能や人気のない機能を削除したりする必要がある場合があります。@product-ver@にも同じことが言えます。Liferayは@product-ver@のいくつかのアプリを非推奨にしました。ここでは、*非推奨*とはどういう意味かを注意することが重要です。

+$$$

**注：**Liferayによって非推奨とされたすべてのアプリは、現在はもう開発されていません。
したがって、これらのアプリの使用中止を計画してください。ただし、まだダウンロードは可能です。

$$$

非推奨のアプリは3種類あります。

1. @product@に残っている非推奨のアプリで、今後のリリースで削除される予定のもの。

2. @product@から削除された非推奨のアプリで、まだ[Liferayマーケットプレイス](https://web.liferay.com/marketplace)または[Liferayの公開Nexusリポジトリ](https://repository.liferay.com)からダウンロードできるもの。アプリのインストール手順については、[アプリの管理](/discover/portal/-/knowledge_base/7-1/managing-apps)のドキュメントを参照してください。

3. @product@から削除された非推奨のアプリで、もうダウンロードできないもの。

この記事では、@product-ver@で廃止予定のアプリとその可用性を一覧で示しています。

## コラボレーション [](id=collaboration)

| アプリ | &nbsp;可用性 | &nbsp;注 |
| --- | ------------------ | ----------- | 
| Bookmarks | マーケットプレイス |  |
| Chat | 削除されました |  |
| Directory | Nexus |  |
| Documentum Connector | 削除されました |  |
| Events List | 削除されました |  |
| Friend Requests | 削除されました |  |
| Group Statistics | Nexus |  |
| IP Geocoder | 削除されました |  |
| Invitation | 削除されました |  |
| JAMWiki | 削除されました |  |
| JSPWiki | 削除されました |  |
| Media Wiki | 削除されました |  |
| Message Boards Subscription Manager | 削除されました |  |
| Microblogs | Nexus |  |
| Private Messaging | 削除されました |  |
| Quick Note | Nexus |  |
| Recently Downloaded | Nexus |  |
| Social Activity | Nexus |  |
| Social Networking | Nexus | 以前はマーケットプレイスを通じて、サポートされていないLabsアプリとして利用可能でした。 |
| Twitter | 削除されました |  |
| User Statistics | Nexus |  |

## Foundation [](id=foundation)

| アプリ | &nbsp;可用性 | &nbsp;注 |
| --- | ------------------ | ----------- |
| AlloyUI | リリース (バンドル) | [Metal.js](https://metaljs.com/)に置き換えられました。 |
| Columns Layout | 削除されました |  |
| Google Login | リリース (バンドル) | OpenID Connectに置き換えられました。 |
| Marvel Integration | 削除されました | X-Packに置き換えられました。 |
| OpenID | リリース (バンドル) | OpenID Connectに置き換えられました。 |
| OpenSocial Gadget | 削除されました |  |
| Plugins SDK | [GitHub](https://github.com/liferay/liferay-plugins) | 7.0では推奨されていません。[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)に置き換えられました。 |
| Ruby Console | 削除されました |  |
| Search Portlet | リリース (バンドル) | 今後のリリースで削除される予定です。[検索ウィジェット](/discover/portal/-/knowledge_base/7-1/whats-new-with-search)に置き換えられました。 |
| Shield Integration | 削除されました | X-Packに置き換えられました。 |
| Template Engines | リリース (バンドル) | Freemarkerに置き換えられました。 |
| vLDAPプラグイン（仮想LDAPサーバーEE） | 削除されました |  |
| WSRP | 削除されました |  |
| WYSIWYG | [Nexus](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.wysiwyg.web/) →削除されます。 | 7.0用にリリースされた最終バージョンです。 |

## Web Experience [](id=web-experience)

| アプリ | &nbsp;可用性 | &nbsp;注 |
| --- | ------------------ | ----------- |
| RSS Publisher | リリース (バンドル) | このウィジェットの有効化と使用に関する[記事](/discover/portal/-/knowledge_base/7-1/the-rss-publisher-widget)を参照してください。 |
| User Group Pages (Copy Mode) | リリース (バンドル) |  |
| Webプロキシポートレット | [Nexus](https://repository.liferay.com/nexus/service/local/repositories/liferay-public-releases/content/com/liferay/com.liferay.web.proxy.web/2.0.0/com.liferay.web.proxy.web-2.0.0.jar) |  |
| Webコンテンツ検索ポートレット | [Nexus](https://repository.liferay.com/nexus/service/local/repositories/liferay-public-releases/content/com/liferay/com.liferay.journal.content.search.web/2.0.0/com.liferay.journal.content.search.web-2.0.0.jar) | 新しい検索ウィジェットは、Webコンテンツ検索の独自機能をすべて置き換えるように設定できます。 |
| Web Content List | 削除されました | Asset Publisherは、Webコンテンツリストの独自機能をすべて置き換えるように設定できます。 |

## フォームとワークフロー[](id=forms-and-workflow)

| アプリ | &nbsp;可用性 | &nbsp;注 |
| --- | ------------------ | ----------- |
| Web Form | Nexus → 削除されます。 | 7.0用にリリースされた最終バージョンです。 |

## セキュリティー[](id=security)

| アプリ | &nbsp;可用性 | &nbsp;注 |
| --- | ------------------ | ----------- |
| OAuth 1.0a | マーケットプレイス | バンドルに含まれているOAuth 2.0に置き換えられました。 |
| OpenID | リリース (バンドル) | 使用率が低いです。代わりに、OpenID Connectを使用してください。 |
