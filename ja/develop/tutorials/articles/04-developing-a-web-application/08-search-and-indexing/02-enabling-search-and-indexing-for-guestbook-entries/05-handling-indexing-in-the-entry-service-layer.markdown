---
header-id: handling-indexing-in-the-entry-service-layer
---

# エントリサービスレイヤーでのインデックス作成の処理

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">エントリの検索とインデックス作成を有効にする</p><p>手順5/5</p>
</div>

エントリが追加、更新、または削除されるたびに、対応するドキュメントも更新または削除する必要があります。 それぞれのマイナーアップデート `addEntry`、 `updateEntry`、及び `deleteEntry` エントリーのサービス方法は、それが取るすべてです。

メソッドを更新するには、次の手順に従います。

1.  `guestbook-service` モジュールの `com.liferay.docs.guestbook.service.impl` パッケージで `EntryLocalServiceImpl` を開き、 `addEntry` 署名の上に注釈 `@Indexable（type = IndexableType.REINDEX）` を追加します。および `updateEntry` メソッド：
   
        @Indexable(type = IndexableType.REINDEX)
        public Entry addEntry(...)
       
        @Indexable(type = IndexableType.REINDEX)
        public Entry updateEntry(...)

    `@Indexable` アノテーションは、メソッドの実行後にインデックスの更新が必要であることを示します。 インデックス作成クラスは、インデックス作成の方法を正確に制御します。 設定 `@Indexable` に注釈の種類を `IndexableType.REINDEX` アップデートインデックス付き文書をその更新エントリに対応します。

2.  `deleteEntry` メソッドの署名の上に `@Indexable（type = IndexableType.DELETE）` 注釈を追加し` 。 インデックス可能なタイプ <code>IndexableType.DELETE` は、エントリがインデックスから削除されることを保証します。
   
        @Indexable(type = IndexableType.DELETE)
        public Entry deleteEntry(...)

3.  必要なインポートを追加します。
   
        import com.liferay.portal.kernel.search.Indexable;
        import com.liferay.portal.kernel.search.IndexableType;

    ファイルを保存します。

4.  Liferay @ide@の右側にあるGradleタスクペインで、 `guestbook-service` → `build``buildService` ダブルクリックします。 これにより、Service Builderが再実行され、 `EntryLocalServiceImpl`への変更が組み込まれます。

ゲストブックとそのエントリは、バックエンドで検索とインデックス作成をサポートするようになりました。 次に、ゲストブックポートレットのフロントエンドで検索を有効にします。
