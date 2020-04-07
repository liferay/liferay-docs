---
header-id: enabling-search-and-indexing-for-guestbooks
---

# ゲストブックの検索とインデックス作成を有効にする

[TOC levels=1-4]

このセクションでは、検索機能のこれらの側面を制御するクラスを作成します。

  - 登録：

      - `GuestbookSearchRegistrar` は、Guestbookエンティティの検索フレームワークに検索サービスを登録します。

  - インデックス付け：

      - `GuestbookModelDocumentContributor` は、検索エンジンでどのGuestbookフィールドにインデックスを付けるかを制御します。

      - `GuestbookModelIndexerWriterContributor` は、ゲストブックの再インデックス付けとバッチ再インデックス付けの動作を構成します。

  - クエリ：

      - `GuestbookKeywordQueryContributor` は、進行中の検索クエリに句を提供します。

      - `GuestbookModelPreFilterContributor` は、検索エンジンから返される前の検索結果のフィルター方法を制御します。

  - 結果サマリーの生成：

      - `GuestbookModelSummaryContributor` は、使用するフィールドの指定など、ゲストブックの結果の概要を作成します。

検索クラスを作成した後、ゲストブックが永続化されるときにサービスレイヤーを変更して検索インデックスを更新します。 具体的には、 `GuestbookLocalServiceImpl`の `addGuestbook`、 `updateGuestbook`、及び `deleteGuestbook` の方法は、ゲストブックインデクサを呼び出すために更新されます。

@product@の以前のバージョンでは、検索とインデックス作成は、 `BaseIndexer`を拡張した</code> つの `* Indexer `クラスで実行されました。 @product-ver@には、継承</a>ではなく

構成に依存する新しいパターンがあります。 古いアプローチを使用する場合は、 `BaseIndexer`自由に拡張してください。 引き続きサポートされています。</p> 

UIでゲストブックを検索する理由はないため、バックエンドの作業のみが必要です。

<a class="go-link btn btn-primary" href="/docs/7-1/tutorials/-/knowledge_base/t/understanding-search-and-indexing">行こう\！<span class="icon-circle-arrow-right"></span></a>

