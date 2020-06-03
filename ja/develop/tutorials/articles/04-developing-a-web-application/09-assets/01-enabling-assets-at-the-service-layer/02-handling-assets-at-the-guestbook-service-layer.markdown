---
header-id: handling-assets-at-the-guestbook-service-layer
---

# ゲストブックサービスレイヤーでのアセットの処理

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">サービスレイヤーでのアセットの有効化</p><p>手順2/3</p>
</div>

サービスレイヤーを更新してアセットレンダラーを追加する前に、 `build.gradle` を更新して、アセットリンクサービスが機能するために必要な `portlet-api` および `javax.servlet-api` ライブラリを提供する必要があります。

1.  `guestbook-service` モジュールで `build.gradle` ファイルを開きます。

2.  `依存関係` セクションに次の2行を追加します。
   
        compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"

ここで、ゲストブックサービスレイヤーを更新して、アセットを使用します。 あなたは、更新する必要があります `アドオン`、 `更新`、および `削除` プロジェクトの方法 `GuestbookLocalServiceImpl`：

1.  プロジェクトの `GuestbookLocalServiceImpl` クラスを開き、 `addGuestbook` メソッドを見つけます。 呼び出しを追加して、リソースを追加する呼び出しの下にアセットエントリを追加します。

    ``` 
      AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
                          groupId, guestbook.getCreateDate(),
                          guestbook.getModifiedDate(), Guestbook.class.getName(),
                          guestbookId, guestbook.getUuid(), 0,
                          serviceContext.getAssetCategoryIds(),
                          serviceContext.getAssetTagNames(), true, true, null, null, null, null,
                          ContentTypes.TEXT_HTML, guestbook.getName(), null, null, null,
                          null, 0, 0, null);

      assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                          serviceContext.getAssetLinkEntryIds(),
                          AssetLinkConstants.TYPE_RELATED);
    ```

    `assetEntryLocalService.updateEntry` を呼び出すと、@product@のデータベースの `AssetEntry` テーブルに新しい行（追加されるゲストブックに対応）が追加されます。 `AssetEntryLocalServiceImpl`の `updateEntry` メソッドは、アセットエントリがデータベースに既に存在するかどうかを確認してから適切なアクションを実行するため、アセットエントリを追加および更新します。 `AssetEntryLocalServiceUtil.updateEntry`の [Javadoc](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portlet/asset/service/impl/AssetEntryLocalServiceImpl.html) をチェックすると、このメソッドがオーバーロードされていることがわかります。 さて、なぜこのような長いメソッドシグネチャを持つこのメソッドのバージョンを使用したのですか？ `タイトル` パラメーター（アセットエントリのタイトルを設定するため）を受け取る `updateEntry` バージョンは1つしかないためです。 アセットのタイトルを `guestbook.getName（）`に設定するので、それが使用するバージョンです。

    後で、ゲストブック管理ポートレットのゲストブックを追加するフォームを更新して、データベースの `AssetLink` テーブルに保存されている関連アセットを選択できるようにします。 `assetLinkLocalService.updateLinks` 呼び出しは、適切なエントリをテーブルに追加して、ゲストブックエンティティに対して関連するアセットが機能するようにします。 `updateEntry` メソッドは、 `updateLink` がアセットリンクを追加および更新するのと同じ方法で、アセットエントリを追加および更新します。

2.  次に、リソース呼び出しの直後に、 `GuestbookLocalServiceImpl`の `updateGuestbook` メソッドにアセット呼び出しを追加します。

    ``` 
      AssetEntry assetEntry = assetEntryLocalService.updateEntry(guestbook.getUserId(),
                          guestbook.getGroupId(), guestbook.getCreateDate(),
                          guestbook.getModifiedDate(), Guestbook.class.getName(),
                          guestbookId, guestbook.getUuid(), 0,
                          serviceContext.getAssetCategoryIds(),
                          serviceContext.getAssetTagNames(), true, true, guestbook.getCreateDate(), 
                          null, null, null, ContentTypes.TEXT_HTML, guestbook.getName(), null, null, 
                          null, null, 0, 0, serviceContext.getAssetPriority());

      assetLinkLocalService.updateLinks(serviceContext.getUserId(),
                          assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(),
                          AssetLinkConstants.TYPE_RELATED);
    ```

    ここで、 `assetEntryLocalService.updateEntry` は既存のアセットエントリを更新し、 `assetLinkLocalService.updateLinks` はそのエントリのアセットリンク（関連アセット）を追加または更新します。

3.  次に、リソース呼び出しの直後に、資産呼び出しを `deleteGuestbook` メソッドに追加します。

    ``` 
      AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
                          Guestbook.class.getName(), guestbookId);

      assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

      assetEntryLocalService.deleteEntry(assetEntry);
    ```

    ここでは、ゲストブックのクラス名とIDを使用して、対応するアセットエントリを取得します。 次に、そのアセットエントリのアセットリンクとアセットエントリ自体を削除します。

4.  最後に、インポートを整理し、ファイルを保存し、Service Builderを実行して変更を適用します。

次に、ゲストブックエントリにも同じことを行います。
