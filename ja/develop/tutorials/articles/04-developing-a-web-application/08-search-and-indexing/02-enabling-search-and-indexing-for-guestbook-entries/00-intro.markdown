---
header-id: enabling-search-and-indexing-for-entries
---

# エントリの検索とインデックス作成を有効にする

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

このセクションでは、検索機能のこれらの側面を制御するクラスを作成します。

  - 登録：

      - `EntrySearchRegistrar` は、エントリエンティティの検索サービスを登録します。

  - インデックス付け：

      - `EntryModelDocumentContributor` は、検索エンジンでどのエントリフィールドにインデックスを付けるかを制御します。

      - `EntryModelIndexerWriterContributor` 、エントリの再インデックス付けとバッチ再インデックス付けの動作を構成します。

      - `EntryBatchReindexer`、インターフェース、および `EntryBatchReindexerImpl`ゲストブックが更新されたときにエントリーのインデックスを再作成します。

  - クエリ：

      - `EntryKeywordQueryContributor` は、進行中の検索クエリに句を提供します。

      - `EntryModelPreFilterContributor` は、検索結果が検索エンジンから返される前にフィルターされる方法を制御します。

  - 結果サマリーの生成：

      - `EntryModelSummaryContributor` は、使用するフィールドの指定など、エントリの結果の概要を作成します。

検索クラスを作成した後、エントリが保持されているときにサービスレイヤーを変更して検索インデックスを更新します。

  - 更新 `EntryLocalServiceImpl`の `addEntry`、 `updateEntry`、及び `deleteEntry` がdatabseに一致するように、方法は、インデックスを更新します。

| **注：** @product@の以前のバージョンでは、検索とインデックス作成は完了していました| `BaseIndexer`を拡張した1つの `* Indexer` クラス。 この学習パス| |の代わりに [コンポジションに依存する新しいパターンを示します継承](https://stackoverflow.com/questions/2399544/difference-between-inheritance-and-composition)。 |古いアプローチを使用する場合は、 `BaseIndexer`自由に拡張してください。 |まだサポートされています。

<a class="go-link btn btn-primary" href="/docs/7-1/tutorials/-/knowledge_base/t/registering-entries-with-the-search-framework">行こう\！<span class="icon-circle-arrow-right"></span></a>

