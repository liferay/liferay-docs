---
header-id: handling-indexing-in-the-guestbook-service-layer
---

# Guestbook Serviceレイヤーでのインデックス処理

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">ゲストブックの検索とインデックス作成を有効にする</p><p>手順6/6</p>
</div>

Guestbookデータベースエンティティが追加、更新、または削除されるたびに、それに応じて検索インデックスを更新する必要があります。 @product@アノテーション `@Indexable` は、 `IndexableType` と組み合わせてサービスメソッドをマークし、ドキュメントを更新または削除できるようにします。 注釈 `addGuestbook`、 `updateGuestbook`、及び `deleteGuestbook` サービス方法。

1.  `guestbook-service` モジュールの `com.liferay.docs.guestbook.service.impl` パッケージで `GuestbookLocalServiceImpl` を開き、 `addGuestbook` および `updateGuestbook` メソッドのメソッドシグネチャの上に次の注釈を追加します。
   
        @Indexable(type = IndexableType.REINDEX)
        public Guestbook addGuestbook(...)
       
        @Indexable(type = IndexableType.REINDEX)
        public Guestbook updateGuestbook(...)

    `@Indexable` アノテーションは、メソッドの実行後にインデックスの更新が必要であることを示します。 インデックスクラスは、インデックスのタイプを制御します `@Indexable` アノテーションタイプを `設定すると、IndexableType.REINDEX` が、更新されたゲストブックに対応するインデックス内のドキュメントを更新します。

2.  `deleteGuestbook` メソッドのメソッドシグネチャの上に次の注釈を追加します。
   
        @Indexable(type = IndexableType.DELETE)
        public Guestbook deleteGuestbook(...)

    データベースからゲストブックを削除する場合、そのドキュメントは検索インデックスに残らないようにする必要があります。 これにより、確実に削除されます。

3.  必要なインポートを追加します。
   
        import com.liferay.portal.kernel.search.Indexable;
        import com.liferay.portal.kernel.search.IndexableType;

    ファイルを保存します。

4.  Liferay @ide@の右側にあるGradleタスクペインで、 `guestbook-service` → `build``buildService` ダブルクリックします。 これにより、Service Builderが再実行され、 `GuestbookLocalServiceImpl`への変更が組み込まれます。

次に、ゲストブックエントリの検索とインデックス作成を有効にします。
