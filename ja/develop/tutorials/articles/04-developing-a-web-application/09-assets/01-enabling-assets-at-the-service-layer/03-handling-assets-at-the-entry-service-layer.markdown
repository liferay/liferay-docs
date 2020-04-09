---
header-id: handling-assets-at-the-entry-service-layer
---

# エントリーサービスレイヤーのアセットの処理

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">サービスレイヤーでのアセットの有効化</p><p>[fuzzy]手順3/3</p>
</div>

ここで、ゲストブックエントリエンティティのサービスメソッドを更新する必要があります。 これらのメソッドでは、 `assetEntryLocalService` および `assetLinkLocalService` に対して行う呼び出しは、 `Entry` エンティティにアセットを指定することを除いて、ゲストブックエンティティのサービスメソッドで行った呼び出しと同じです。

1.  `EntryLocalServiceImpl` を開き、リソースの呼び出し後にアセット呼び出しを `addEntry` メソッドに追加します。

    ``` 
      AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
                          groupId, entry.getCreateDate(), entry.getModifiedDate(),
                          Entry.class.getName(), entryId, entry.getUuid(), 0,
                          serviceContext.getAssetCategoryIds(),
                          serviceContext.getAssetTagNames(), true, true, null, null, null, null,
                          ContentTypes.TEXT_HTML, entry.getMessage(), null, null, null,
                          null, 0, 0, null);

      assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                          serviceContext.getAssetLinkEntryIds(),
                          AssetLinkConstants.TYPE_RELATED);
    ```

2.  次に、リソースの呼び出し後にアセットの呼び出しを `updateEntry` メソッドに追加します。

    ``` 
          AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
                          serviceContext.getScopeGroupId(),
                          entry.getCreateDate(), entry.getModifiedDate(),
                          Entry.class.getName(), entryId, entry.getUuid(),
                          0, serviceContext.getAssetCategoryIds(),
                          serviceContext.getAssetTagNames(), true, true,
                          entry.getCreateDate(), null, null, null,
                          ContentTypes.TEXT_HTML, entry.getMessage(), null,
                          null, null, null, 0, 0,
                          serviceContext.getAssetPriority());

          assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                          serviceContext.getAssetLinkEntryIds(),
                          AssetLinkConstants.TYPE_RELATED);
    ```

3.  リソースの呼び出し後にアセットの呼び出しを `deleteEntry` メソッドに追加します。
   
        AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
                              Entry.class.getName(), entryId);
       
        assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
       
        assetEntryLocalService.deleteEntry(assetEntry);

4.  インポートを整理し、ファイルを保存して、Service Builderを実行します。

5.  最後に、これらの言語キーを `guestbook-web / src / main / resources / content / Language.properties` ファイルに追加します。
   
        model.resource.com.liferay.docs.guestbook.model.Guestbook=Guestbook
        model.resource.com.liferay.docs.guestbook.model.Entry=Guestbook Entry

優れた\！ サービスレイヤーでゲストブックとゲストブックエントリエンティティをアセット対応にしました。 次のステップは、これらのエンティティにアセットレンダラーを実装して、アセットフレームワークに完全に統合できるようにすることです。 すべてのアセットには、Asset Publisherポートレットが表示できるようにアセットレンダラークラスが必要です。
