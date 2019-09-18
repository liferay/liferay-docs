# アップグレード後の作業[](id=post-upgrade-tasks)

検証プロセスをアップグレードして実行した後には、アップグレード後のタスクがあります。

1. 検索インデックスを再度有効にし、検索インデックスのインデックスを再作成します。
2. Webコンテンツの表示権限を有効にします。

まず、検索インデックスについて説明します。

## 検索インデックスの再有効化と検索インデックスの再インデックス[](id=re-enabling-search-indexing-and-reindexing-search-indexes)

`com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config`ファイルを`[Liferay Home]/osgi/configs`フォルダから削除するか、プロパティを設定して、検索インデックスを再度有効にします。

    indexReadOnly="false"

その後、@product@の検索インデックスのインデックスを再作成する必要があります。ただし、むやみに行わないでください。デフォルトでは、@product@にはElasticsearchの組み込み設定が付属しています。この設定はデモ目的には最適ですが、本番環境ではサポートされていません。本番環境で実行するには、必ず[スタンドアロンのElasticsearchインスタンスをインストールして設定](/discover/deployment/-/knowledge_base/7-1/installing-elasticsearch)してください 。

## Webコンテンツの表示権限を有効にする[](id=enabling-web-content-view-permissions)

@product@ 7.1より前は、すべてのユーザーがデフォルトでWebコンテンツの記事を閲覧できました。現在、表示権限はデフォルトでチェックされています。表示権限を開くためのオプションは次のとおりです。

オプション1：ロールごとのWebコンテンツ記事ごとに、表示権限を編集する。

オプション2：*[システム設定] &rarr; [Webエクスペリエンス] &rarr; [Webコンテンツ]*に行き、*[記事の閲覧権限のチェックを有効にする]*の選択を解除して 、すべてのWebコンテンツの閲覧権限を表示する。

検索を設定し、検索インデックスのインデックスを再作成し、Webコンテンツの表示権限を設定すると、アップグレードしたシステムで対応できるようになります。
