---
header-id: indexing-framework
---

# インデックス作成フレームワーク

[TOC levels=1-4]

データベースクエリを使用してモデルエンティティを検索する場合を除き（ほとんどの場合は推奨されません）、@product@の各アセットは検索エンジンでインデックス化する必要があります。アセットの開発者はインデックス作成するフィールドと検索クエリに適用するフィルターを把握しているので、インデックスコードは各アセットに固有です。

@product@の過去のバージョンでは、アセットにインデックス作成が必要な場合、`com.liferay.portal.kernel.search.BaseIndexer<T>`を拡張して新しいインデクサーを実装していました。@product-ver@では、[継承ではなく構成](https://stackoverflow.com/questions/2399544/difference-between-inheritance-and-composition)に依存する新しいパターンを導入しています。
古いアプローチを使用する場合は、`BaseIndexer`を拡張できます。これも引き続きサポートされています。

## 検索とインデックス作成の概要

LiferayのオリジナルのSearch APIは、Lucene検索およびインデックス作成ライブラリを中心に構築されました。今までは、Luceneに精通していれば、LiferayのSearch APIについてもスムーズに理解することが可能でした。しかし、@product@の7.0バージョンから、Luceneに密接に関連する部分がより汎用的になるように、Search APIが改訂されています。Elasticsearchのサポートが追加され（Solrに加えて）、新しい検索およびインデックス作成APIのほとんどはElasticsearch APIを活用/マップすることを目的としています。これは、多くの場合（`Query`タイプなど）、Liferay APIとElasticsearch APIの間に1対1のマッピングがあることを意味します。

Elasticsearch中心のAPIに加えて、Liferayの検索インフラストラクチャには、以下の目的に役立つ追加のAPIが含まれています。

- インデックス付きドキュメントに、@product@に必要なフィールドが含まれている（例：`entryClassName`、`entryClassPK`、`assetTagNames`、`assetCategories`、`companyId`、`groupId`、ステージングステータス）。
- 適切なフィルターを検索要求に適用することにより、返される検索結果の範囲を適切にする。
- 権限チェックとヒットサマリーを提供して、検索ポートレットに表示する。

検索およびインデックス作成コードがカスタムモデルをシームレスに検索可能にする方法を理解するには、検索およびインデックス作成サイクルの各部分に影響を与える方法を知る必要があります。

### インデックス作成

モデルエンティティは、データベースにデータフィールドを格納します。たとえば、ゲストブックには_名前_フィールドが保存されます。サイクルのインデックス作成ステップでは、検索エンジンに送信され、後で検索時に使用されるモデルのフィールドを定義することにより、検索可能なモデルエンティティを準備します。

**検索エンジンドキュメントでモデルエンティティフィールドのインデックスを作成する方法を制御するには、**

[`ModelDocumentContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/index/contributor/ModelDocumentContributor.java)クラスは、モデルエンティティの検索エンジンドキュメントでインデックスを作成するデータベースフィールドを指定します。このクラスの`contribute`メソッドは、エンティティのサービスレイヤーで`add`および`update`メソッドが呼び出されるたびに呼び出されます。

[`ModelIndexerWriterContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/index/contributor/ModelIndexerWriterContributor.java)クラスは、モデルエンティティに対するインデックス再作成とバッチインデックス再作成の動作を構成します。このクラスのメソッドが呼び出されるのは、[コントロールパネル] &rarr; [設定] &rarr; [Search]にある検索管理アプリケーションからインデックスの再作成がトリガーされたとき、 または`modelIndexed`メソッドがコントリビュータに実装されている_場合_は、エンティティでCRUD操作が行われたときです。

`DocumentContributor`クラス（`indexer.class.name`コンポーネントプロパティまたは型パラメーターなし）は、インデックス付けされているベースエンティティに関係なく、特定のフィールドをすべてのインデックスドキュメントに提供します。たとえば、[`GroupedModelDocumentContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/contributor/document/GroupedModelDocumentContributor.java)は、`GroupedModel`でもあるバッキングエンティティを持つすべてのドキュメントの`GROUP_ID`および`SCOPE_GROUP_ID`フィールドを提供します。

### 検索

検索は、ユーザーが検索バーにキーワードを入力することから始まります。入力されたキーワードは、バックエンド検索インフラストラクチャによって処理され、検索エンジンが理解できる*クエリ*に変換され、各検索ドキュメントのフィールドとの照合に使用されます。

**モデルエンティティドキュメントの検索方法を制御するには、**

[`KeywordQueryContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/query/contributor/KeywordQueryContributor.java)クラスは、進行中の検索クエリに句を提供します。これは検索時に呼び出され、インデックスを作成したすべてのフィールドも検索対象になるようにします。
たとえば、サイトロケールが追加されたフィールド（`title_en_us`など）にインデックスを作成する場合に、ドキュメントの検索時に検索クエリに同じロケールを含めるとします。検索クエリに別のロケール（`title_es_ES`など）が含まれているか、プレーンフィールド（`title`）が検索される場合、不正確な結果が返されます。

検索するベースエンティティに関係なく、すべての検索にクエリ句を提供するには、`indexer.class.name`コンポーネントプロパティなしで登録された`KeywordQueryContributor`クラスを実装します。例については、[`AlwaysPresentFieldsKeywordQueryContributor`](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/contributor/query/AlwaysPresentFieldsKeywordQueryContributor.java)を参照してください。

[`ModelPreFilterContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/query/contributor/ModelPreFilterContributor.java)は、検索エンジンから返される前に検索結果をフィルター処理する方法を制御します。たとえば、クエリにワークフローステータスを追加すると、ごみ箱内のエンティティが検索結果に返されなくなります。

検索するベースエンティティに関係なく、すべての検索にフィルター句を提供するには、`QueryPreFilterContributor`を実装します。
`QueryPreFilterContributor`は、検索中にルートフィルターの下に1回だけ構築されます。例については、[`AssetCategoryTitlesKeywordQueryContributor`](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/contributor/query/AssetCategoryTitlesKeywordQueryContributor.java)を参照してください。

`QueryPreFilterContributor`と`ModelPreFilterContributor`の違いは何でしょうか。 `QueryPreFilterContributor`は検索中にルートフィルターの下に1回だけ構築されますが、`ModelPreFilterContributor`はモデルエンティティごとに1回構築され、各特定のエンティティのサブフィルターの下に追加されます。

### 結果を返す

検索要求中にモデルエンティティのインデックス作成済み検索ドキュメントが取得されると、モデルエンティティのサマリーに変換されます。

**モデルエンティティドキュメントの結果の概要を制御するには、**

[`ModelSummaryContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/result/contributor/ModelSummaryContributor.java)クラスは各検索ドキュメント用に作成された`Summary`オブジェクトを取得するため、特定のフィールドを追加するか、表示されるコンテンツの長さを設定することでオブジェクトを操作できます。

[`ModelVisibilityContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/result/contributor/ModelVisibilityContributor.java)クラスは、検索コンテキストで、他のアセットタイプに添付できるモデルエンティティの可視性を制御します（たとえば、ファイルエントリをWikiページに添付できます）。

上記のクラスが検索フレームワークによって検出されることを確認するために、1つの重要なステップを実行する必要があります。

### 検索サービスの登録

**Liferayの検索フレームワークにモデルエンティティを登録するには、**

`SearchRegistrar`は、[検索フレームワークのレジストリ](https://github.com/liferay/liferay-portal/tree/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/registrar)を使用して、検索エンジンからドキュメントを取得するためにデフォルトで使用されるフィールドや、エンティティに登録されているオプションの検索サービスなど、モデルエンティティの[`ModelSearchDefinition`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/registrar/ModelSearchDefinition.java)に関する特定の項目を定義します。登録は、コンポーネントがアクティブ化されるとすぐに行われます（ポータルの起動時またはバンドルのデプロイ時）。

## 複合検索とインデックス作成フレームワークの`Indexer`/`BaseIndexer`コードへのマッピング

カスタムエンティティのインデックス作成の古い方法（`BaseIndexer`の拡張、`Indexer`の抽象的な実装）に慣れている方のために、下の表に、`Indexer`インターフェイスのメソッドがいくつかの新しいクラスとメソッドにどのように分解されたかについての簡単な概要を示します。

| Indexer/BaseIndexerメソッド | 同等の複合インデクサー | 例 |
:-------------------------- | :-------------------------- | :--------------- |
| クラスコンストラクター | `SearchRegistrar` | [`GuestbookSearchRegistrar`](/docs/7-1/tutorials/-/knowledge_base/t/registering-guestbooks-with-the-search-framework) |
| `setDefaultSelectedFieldNames` | `SearchRegistrar.activate` | [`GuestbookSearchRegistrar`](/docs/7-1/tutorials/-/knowledge_base/t/registering-guestbooks-with-the-search-framework) |
| `setDefaultSelectedLocalizedFieldNames` | `SearchRegistrar.activate` | [`GuestbookSearchRegistrar`](/docs/7-1/tutorials/-/knowledge_base/t/registering-guestbooks-with-the-search-framework) |
| `setPermissionAware` | `ModelResourcePermissionRegistrar` | [`GuestbookModelResourcePermissionRegistrar`](/docs/7-1/tutorials/-/knowledge_base/t/registering-your-defined-permissions#registering-your-entities-with-the-permissions-service) |
| `setFilterSearch` | `ModelResourcePermissionRegistrar` | [`GuestbookModelResourcePermissionRegistrar`](/docs/7-1/tutorials/-/knowledge_base/t/registering-your-defined-permissions#registering-your-entities-with-the-permissions-service) |
| `getDocument`/`doGetDocument` | `ModelDocumentContributor` | [`GuestbookModelDocumentContributor`](/docs/7-1/tutorials/-/knowledge_base/t/indexing-guestbooks#implementing-modeldocumentcontributor) |
| `reindex`/`doReindex` | `ModelIndexerWriterContributor` | [`GuestbookModelIndexerWriterContributor`](/docs/7-1/tutorials/-/knowledge_base/t/indexing-guestbooks#implementing-modelindexerwritercontributor) |
| `addRelatedEntryFields` | `RelatedEntryIndexer` | [`DLFileEntryRelatedEntryIndexer`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryRelatedEntryIndexer.java) |
| `postProcessContextBooleanFilter`/`PostProcessContextQuery` | `ModelPreFilterContributor` | [`DLFileEntryModelPreFilterContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryModelPreFilterContributor.java) |
| `postProcessSearchQuery` | `KeywordQueryContributor` | [`GuestbookKeywordQueryContributor`](/docs/7-1/tutorials/-/knowledge_base/t/querying-for-guestbook-documents#implementing-keywordquerycontributor) |
| `getFullQuery` | `SearchContextContributor` | [`DLFileEntryModelSearchContextContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryModelSearchContextContributor.java) |
| `isVisible`/`isVisibleRelatedEntry` | `ModelVisibilityContributor` | [`DLFileEntryModelVisibilityContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryModelVisibilityContributor.java) |
| `getSummary`/`createSummary`/`doGetSummary` | `ModelSummaryContributor` | [`GuestbookModelSummaryContributor`](/docs/7-1/tutorials/-/knowledge_base/t/generating-results-summaries) |
| `Indexer.search`/`searchCount` | 変更なし | [ゲストブック`view_search.jsp`](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-search-results-jsp-for-the-guestbook-portlet) |
| `Indexer.delete`/`doDelete` | 変更なし | [`MBMessageLocalServiceImpl.deleteDiscussionMessages`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/message-boards/message-boards-service/src/main/java/com/liferay/message/boards/service/impl/MBMessageLocalServiceImpl.java#L687) |

さらに、`ExpandoBridge`属性にインデックスを作成することができます。これは以前、`BaseIndexer`の`getBaseModelDocument`で行われていました。 ここで、`ExpandoBridgeRetriever`を実装します。実装例については、[`DLFileEntryExpandoBridgeRetriever`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryExpandoBridgeRetriever.java)を参照してください。

## 権限に対応した検索とインデックス作成

以前のバージョンの@product@では、アプリケーション開発者が`Indexer`クラスのコンストラクターで次の行を指定した場合、検索は_権限のみに対応_していました（エンティティの権限でインデックス作成され、権限はそのままで検索される）。

    setPermissionAware(true);

現在、[これらのチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/defining-application-permissions)で説明されている_新しい権限アプローチ_がアプリケーションに実装されている場合、検索はデフォルトで権限を認識します。

## インデックス作成をトリガーするサービスメソッドに注釈を付ける

エンティティをデータベースエンティティ_と_検索エンジンドキュメントに変換すると、データベースと検索エンジン間で状態が一致しない可能性があります。 たとえば、データベースでブログエントリの追加、更新、または削除が行われた場合、対応する変更を検索エンジンで行う必要があります。これを行うには、サービスレイヤーに介入する必要があります。Service Builderエンティティの場合、これは`LocalServiceImpl`クラスで発生します。これを簡素化するアノテーションは`@Indexable`です。これは、`REINDEX`または`DELETE`の2つの値を持つことができる`type`プロパティを取ります。一般に、サービスレイヤーの`deleteEntity`メソッドには次のように注釈が付けられます。


    @Indexable(type = IndexableType.DELETE)
    @Override
    @SystemEvent(type = SystemEventConstants.TYPE_DELETE)
    public BlogsEntry deleteEntry(BlogsEntry entry) throws PortalException {
    ...
    }

`@Indexable`アノテーションはLiferayのSpringインフラストラクチャによって実行されます。そのため、そのアノテーションを持つメソッドがある場合は、スプリングでラップされたロジックでサービスインスタンス変数を使用して呼び出す必要があります。参照は、Service Builderで生成された`*LocalServiceImpl`クラスでデフォルトで使用可能です。この宣言は親の`*LocalServiceBaseImpl`で行われたものだからです。

    @BeanReference(type = BlogsEntryLocalService.class)
    protected BlogsEntryLocalService blogsEntryLocalService;

これは、`*LocalServiceImpl`で以下を呼び出してはいけないことを意味しています。

    this.deleteEntry(...)

アノテーションは実行されず、検索エンジンドキュメントとデータベース列の状態は一致しないままとなります。代わりに、@product@のコードのパターンに従い、サービスインスタンス変数を使用してサービスメソッドを呼び出します。

    blogsEntryLocalService.deleteEntry(entry);

モデルエンティティのインデックス作成の詳細な手順については、[Webアプリケーションの開発チュートリアルの「検索とインデックス作成」セクション](/docs/7-1/tutorials/-/knowledge_base/t/search-and-indexing)をご覧ください。
