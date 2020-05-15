---
header-id: configuring-search
---

# 検索の構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

*検索* 構成は、さまざまなことを意味します。

  - システムスコープの検索構成
  - データベースの検索インデックスが最新であることを確認するための再インデックス
  - ページに追加された検索ウィジェットを微調整する
  - 新しい検索ページの作成
  - @product@と検索エンジンを通信させるコネクタの設定

実際、 *検索の構成* はこれらすべてのことを意味します。 これは、すぐに構成可能な検索動作の概要であり、重要なのは *で、* は検索構成オプションを見つけるためのものです。

## システムスコープ検索構成

システムスコープの検索構成は、主に [システム設定](/docs/7-1/user/-/knowledge_base/u/system-settings)ます。

1.  *コントロールパネル* → *構成* → *システム設定*ます。

2.  プラットフォームセクションの下の *Search* カテゴリをクリックします。

    または、 *検索*検索します。

![図1：システム設定の検索には、システムスコープのエントリが多数あります。](../../images/search-category-system-settings.png)

これらのシステムスコープ設定は、システム設定で使用できます。

### デフォルトのキーワードクエリ

デフォルトのキーワードクエリエントリには、1つの設定が含まれています。

`disabledEntryClassNames`： `DefaultKeywordQueryContributor` コードは、キーワード検索クエリに `説明` `userName`、および `タイトル` フィールドを自動的に追加します。 エントリクラス名を指定してください `DefaultKeywordQueryContributor` は無視する必要があります。

構成ファイル： `com.liferay.portal.search.configuration.DefaultKeywordQueryConfiguration.config`

### デフォルトの検索結果許可フィルター

既定の検索結果のアクセス許可フィルターエントリでは、 *のフィルター後のアクセス許可チェック* （検索インデックスから結果が返された後に発生するデータベースのアクセス許可チェック）を構成できます。 これらの設定の詳細については、ここでは</a> をお読みください

</p> 

  - `permissionFilteredSearchResultAccurateCountThreshold`

  - `searchQueryResultWindowLimit`

構成ファイル： `com.liferay.portal.search.configuration.DefaultSearchResultPermissionFilterConfiguration.config`



### インデックスステータスマネージャー

Index Status Managerエントリには、1つの設定があります。

`indexReadOnly`：すべてのインデックス作成操作を一時停止し、検索エンジンに書き込みます。 検索では、すでに索引付けされているドキュメントのみが返されます。 これは、大きなデータのインポートを高速化するのに役立ちますが、インポートが終了したら無効にして、完全な再インデックスを実行する必要があります。

構成ファイル： `com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config`



### インデクサーライターヘルパー

Index Writer Helperエントリには、有効なエントリが1つだけ含まれています。 2番目の `indexReadOnly`は非推奨であり、使用されていないため、設定しても効果はありません。 代わりに、 [Index Status Manager](#index-status-manager) `indexReadOnly` を使用してください。

`indexCommitImmediately`： *true* （デフォルト）の場合、各書き込み要求は検索エンジンにインデックスリーダーを強制的に更新させ、トランザクションをディスクにフラッシュする可能性があります。 これは、検索エンジンのパフォーマンスに悪影響を及ぼす可能性があります。 デフォルトの動作では、個々のアセットへのインデックス書き込み（ブログの追加、ブログの更新など）をすぐにコミットしますが、すべてのエントリが検索エンジンに送信されるまで、一括インデックス書き込み操作（たとえば、すべてのユーザーのインデックス、すべてのフォームエントリのインデックス）のコミットを遅らせます。 。 これをfalseに設定すると、個々のインデックス操作の動作が変更され、Asset Publisherなどのアプリケーションで、新しく追加されたコンテンツを表示するときに応答が遅れる場合があります。 詳細については、 [Elasticsearchのドキュメント](https://www.elastic.co/guide/en/elasticsearch/guide/current/near-real-time.html) を参照してください。

構成ファイル： `com.liferay.portal.search.configuration.IndexWriterHelperConfiguration.config`



### インデックスレジストリ

インデックス要求のバッファリングを構成します。

`buffered`：インデックス作成要求のバッファリングを無効にするか構成します。 インデックス要求のバッファリングを停止するには、 *無効*選択します。

`maximumBufferSize`：バッファリングが有効な場合、追加のインデックス作成リクエストがすぐに実行されるように、最大バッファサイズを設定します。

`minimumBufferAvailabilityPercentage`：バッファーの容量に指定された割合のスペースしか残っていない場合、バッファー内の既存のリクエストは1つのバッチで実行され、バッファーから削除されます。

構成ファイル： `com.liferay.portal.search.configuration.IndexerRegistryConfiguration.config`



### インデックスクエリプリプロセッサ

このエントリには、1つの繰り返し可能なプロパティがあります（ [OSGi構成ファイル](/docs/7-1/user/-/knowledge_base/u/creating-configuration-files)介して定義する場合は、配列構文を使用します）。

`fieldNamePatterns`：ここで設定したパターンに一致する名前のフィールドは、分析されていないキーワードフィールドとして扱われます。 スコアリングされたフルテキストクエリの代わりに、スコアリングされていないワイルドカードクエリによってマッチングが実行されます。 これはリソースを大量に消費する操作であり、インデックスが大きくなると検索エンジンのパフォーマンスが低下します。 サブストリングのマッチングでは、 [ngramトークナイザー](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/analysis-ngram-tokenizer.html) に依存すると、通常、パフォーマンスが向上します。

構成ファイル： `com.liferay.portal.search.configuration.QueryPreProcessConfiguration.config`



### 再インデックス

このエントリには1つのプロパティのみが含まれます。

`indexingBatchSizes`：バッチインデックスをサポートするモデルタイプのバッチごとにインデックスが作成されるドキュメントの数を設定します---デフォルトは `com.liferay.journal.model.JournalArticle = 10000`です。 大きなドキュメントを持つモデルの場合、この値を小さくすると、完全な再インデックスを実行するときの安定性が向上する可能性があります。

構成ファイル： `com.liferay.portal.search.configuration.ReindexConfiguration.config`



### エンジンヘルパー

このエントリには、1つの繰り返し可能なプロパティがあります（ [OSGi構成ファイル](/docs/7-1/user/-/knowledge_base/u/creating-configuration-files)介して定義する場合は、配列構文を使用します）。

`excludeEntryClassNames`：検索アプリケーション用に作成されたキャッチオールクエリで検索からアセットタイプを除外します。 たとえば、組織アセットのフィールドは、ユーザーおよび組織アプリケーションから検索できるようにインデックスを作成する必要がありますが、検索アプリケーションでは検索しないでください。 したがって、組織は、 `excludedEntryClassNames`追加され` 。</p>

<p spaces-before="0">構成ファイル： <code>com.liferay.portal.search.configuration.SearchEngineHelperConfiguration.config`



### 権限チェッカー

*フィルタリング前の権限チェック* （検索インデックスの権限チェック）の動作を構成します。 これらのプロパティの詳細については、ここの</a> を参照してください </p> 

  - `includeInheritedPermission`

  - `permissionTermsLimit`

構成ファイル： `com.liferay.portal.search.configuration.SearchPermissionCheckerConfiguration.config`



### Elasticsearch 6

@product@とElasticsearch 6の間の接続を構成します。 これらのプロパティの詳細については、ここの</a> を参照してください </p> 

  - `clusterName`
  - `operationMode`
  - `indexNamePrefix`
  - `numberOfIndexReplicas`
  - `numberOfIndexShards`
  - `bootstrapMlocakAll`
  - `logExceptionsOnly`
  - `retryOnConflict`
  - `zenDiscoveryUnicastHostsPort`
  - `networkHost`
  - `networkBindHost`
  - `networkPublishHost`
  - `transportTcpPort`
  - `transportAddresses`
  - `clientTransportSniff`
  - `clientTransprtIgnoreClusterName`
  - `clientTransportPingTimeout`
  - `clientTransportNodesSamplerInterval`
  - `HttpEnabled`
  - `HttpCrsEnabled`
  - `HttpCorsAllowOrigin`
  - `HttpCorsConfigurations`
  - `additionalConfigurations`
  - `additionalIndexConfigurations`
  - `overrideTypeMappings`
  - `synchronizedSearch`

構成ファイル： `com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config`



### ウェブを検索

：このエントリは、1つのプロパティが含ま `classicSearchPortletInFrontPage`：過去のリリースで標準だった古典的な検索ポートレットに新しい検索ウィジェットを使用してから元に戻すデフォルトの検索体験を。 参照してください [ここでは](/docs/7-1/user/-/knowledge_base/u/configuring-search-pages#legacy-search-experience) より多くの情報のために。

構成ファイル： `com.liferay.portal.search.web.internal.configuration.SearchWebConfiguration.config`



### 検索管理からの再インデックス

検索のシステム設定に加えて、検索インデックスを再作成するアクションは、システムスコープのアクションです。

1.  *コントロールパネル* → *設定* → *検索*ます。

2.  次のいずれかのインデックスを再作成します。
   
         - すべてのインデックス可能なアセット
      - 個別のインデックス可能なアセット
      - すべてのスペルチェックインデックス

ポータルプロパティもシステムスコープの構成です。 [Lucene Search](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#Lucene%20Search) ポータルプロパティは、低レベルの検索動作を構成します。 プロパティとその説明を確認し、それらが検索要件に適用されるかどうかを判断します。



## サイトスコープ検索の構成

[サイトスコープ構成](/docs/7-1/user/-/knowledge_base/u/setting-up#configuration-scope)厳密な定義では、サイトスコープで検索を構成できません。 ただし、 [検索ページ](/docs/7-1/user/-/knowledge_base/u/configuring-search-pages) サイト固有の検索動作に影響します。 通常、検索ページには、特定のサイト内のすべてのコンテンツを検索するように構成された検索ウィジェットが含まれています。

さらに、ヘッダー検索（デフォルトですべてのサイトページに埋め込まれている検索バー）は、新しい検索バーウィジェットまたはレガシー検索ポートレットのいずれが入力されたかに関係なく、サイトスコープです。 ヘッダー検索アプリケーションのインスタンスはサイトごとに1つだけ存在し、1つのページコンテキストで構成すると、サイト全体で構成されます。

検索はモジュール化されているため、新しい検索ウィジェットを使用する際に知っておくべき重要な構成上のニュアンスがいくつかあります。

  - ヘッダー検索で検索バーウィジェットを使用する場合、その構成では常に *宛先ページ* を設定する必要があります。この場合、ユーザーは検索アクティビティを完了するためにリダイレクトされ、他の検索ウィジェット（結果、ファセット、提案など）と対話します。 [検索先ページ](/docs/7-1/user/-/knowledge_base/u/configuring-search-pages) は、検索ウィジェットを保持する通常のページです。 サイト全体で、検索ウィジェットを備えたページをいくつでも持つことができます。

  - 従来の検索ポートレットとは異なり、新しい検索バーウィジェットはインスタンス化可能であるため、1つのページに異なる構成の複数の検索バーウィジェットを含めることができます。 すべての検索バーインスタンスが有効になるには、検索ページをポイントする必要があります。

  - **重要**：宛先検索ページに埋め込まれたヘッダー検索のほかに検索バーウィジェットインスタンスがある場合、ヘッダー検索の構成がページのウィジェットインスタンスよりも優先されます。
    
    逆に、他のページの検索バーウィジェットインスタンスから検索すると、それらがヘッダー検索の構成と異なっていても、それらの構成が優先されます。

| **注：** Liferay Portal 7.1 CE GA1およびLiferay DXP 7.1では、次のことを行う必要があります。 |で検索ページの検索バーとヘッダー検索の両方を設定します。一致する構成で一貫した検索動作を実現します（そうでない場合、どちらかが優先される場合があります。） [LPS-83193](https://issues.liferay.com/browse/LPS-83193) となる|次のフィックスパックまたはGAリリースで利用可能です。

詳細については、検索バー</a> 構成 に関するドキュメントを参照してください。</p> 



## ウィジェットスコープ検索設定

いくつかの検索ウィジェットが利用可能で、それぞれに独自の構成オプションがあります。

**検索結果** ：検索結果の表示方法を設定します。 読む [ここでは](/docs/7-1/user/-/knowledge_base/u/search-results) より多くの情報のために。

**検索バー** ：検索キーワードの処理方法の動作を構成します。 参照してください [ここでは](/docs/7-1/user/-/knowledge_base/u/searching-for-assets#configuring-the-search-bar) より多くの情報のために。

**検索ファセット** ：各ファセットの動作とURLパラメーターを構成します。 参照してください [ここでは](/docs/7-1/user/-/knowledge_base/u/facets) より多くの情報のために。

**検索オプション** ：これは特殊なケースで、このウィジェットを構成するとページスコープの動作が定義されます。 検索オプションウィジェットをページに追加し、検索ページに2つのブール値を定義します。

  - 空の検索を許可：デフォルトでは、キーワードを入力しないと結果は返されません。 これを有効にすると、検索バーにキーワードが入力されていないときに *すべて* 結果が返されます。

  - 基本的なファセット選択：デフォルトでは、ファセット数は各ファセット選択後に再計算されます。 これを有効にすると、ファセットの再カウントがオフになります。

**検索候補** ：より適切なクエリとスペルチェッククエリを提案します。 参照してください [ここでは](/docs/7-1/user/-/knowledge_base/u/searching-for-assets#search-suggestions) より多くの情報のために。

**検索インサイト** ：これを検索ページに追加して、ユーザーがキーワードを入力したときにバックエンド検索コードによって構築される完全なクエリ文字列を検査します。 テストと開発にのみ役立ちます。
