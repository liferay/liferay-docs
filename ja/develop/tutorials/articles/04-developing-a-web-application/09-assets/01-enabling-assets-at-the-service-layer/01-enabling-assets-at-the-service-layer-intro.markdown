---
header-id: enabling-assets-at-the-service-layer
---

# サービスレイヤーでのアセットの有効化

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p>サービスレイヤーでのアセットの有効化</p><p>手順1/3</p>
</div>

`AssetEntry` テーブルの各行は資産を表します。 `classNameId` と `classPK` 外部キーと共に、 `entryId` プライマリキーがあります。 `classNameId` は、アセットのタイプを指定します。 例えば、資産と `classNameId` の `JournalArticle` 資産は、Webコンテンツの記事（表していることを意味`JournalArticle` Webコンテンツの記事のバックエンド名を）。 資産の `classPK` は、資産によって表されるエンティティの主キーです。

次の手順に従って、エンティティのサービスレイヤーでアセットサービスを利用できるようにします。

1.  `guestbook-service` モジュールの `service.xml` ファイルで、 `Guestbook` および `Entry`の終了タグ `</entity>` タグのすぐ上に次の参照を追加します。
   
        <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />
        <reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

    上記のように、 `AssetEntry` サービスを使用して、アプリケーションがゲストブックおよびゲストブックエントリに対応するアセットエントリを追加できるようにする必要があります。 また、 `AssetLink` サービスを使用して、関連するアセットをサポートします。 *アセットリンク* は、関連するアセットの@product@のバックエンド用語です。

2.  次の2つのために---ファインダーを追加する必要があります `ゲストブック`秒とするための2つ `エンティティ`それがでエンティティを検索するので、あなたの資産は、資産Publisherで表示して---の `ステータス` （すなわち、それはワークフロー承認されました？）そして、 `groupId` （つまり、このサイトにありますか？） `ゲストブック` および `エントリ` エンティティの既存のファインダーの下にこれらを追加します。
   
        <finder name="Status" return-type="Collection">
       <finder-column name="status" /> </finder> 
   
        <finder name="G_S" return-type="Collection">
            <finder-column name="groupId" />
       <finder-column name="status" /> </finder>

3.  `buildService` Gradleタスクを実行します。 このタスクは、上記で参照したオブジェクトを使用するサービスに注入します。

4.  `build.gradle` を右クリックし、 *Gradle* → *Gradle Project*更新を選択します。

すばらしいです\！ 次に、サービスレイヤーでアセットを処理します。
