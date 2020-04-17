---
header-id: implementing-asset-priority
---

# 資産優先度の実装

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[アセットパブリッシャー](/docs/7-1/user/-/knowledge_base/u/publishing-assets) では、優先順位に従ってアセットを注文できます。 ただし、これが機能するためには、ユーザーがアセットを作成または編集するときにアセットの優先度を設定できる必要があります。 たとえば、Webコンテンツを作成または編集する場合、ユーザーは[メタデータ]セクションの[優先度]フィールドで優先度を割り当てることができます。

![図1：優先度フィールドでは、ユーザーがアセットの優先度を設定できます。](../../images/web-content-categorization.png)

アセットを作成するとき、このフィールドは有効になりません。 サポートを手動で追加する必要があります。 幸いなことに、これは非常に簡単です。 このチュートリアルでは、その方法を示します。 オンワード\！

## JSPに優先度フィールドを追加する

アセットを追加および編集するためのJSPで、ユーザーがアセットの優先度を設定できる次の入力フィールドを追加します。 また、この例では、入力を検証して、ユーザーが設定する値がゼロより大きい数値であることを確認します。

    <aui:input label="priority" name="assetPriority" type="text" value="<%= priority %>">
        <aui:validator name="number" />
    
        <aui:validator name="min">[0]</aui:validator>
    </aui:input>

ビューレイヤーについては以上です！ ユーザーがアセットを作成または編集するときに、優先度を入力できるようになりました。 次に、サービスレイヤーでその値を使用する方法を学習します。

## サービスレイヤーでの優先値の使用

優先度の値を機能させるには、ビューから取得して、データベースのアセットに追加する必要があります。 優先度の値は、 `ServiceContext` 変数 `serviceContext`介してサービス層で自動的に利用可能です。 `serviceContext.getAssetPriority（）`で取得し、 `-LocalServiceImpl``assetEntryLocalService.updateEntry` 呼び出しに最後の引数として渡します。 あなたには、この例を見ることができます [ `BlogsEntryLocalServiceImpl` クラス](https://github.com/liferay/liferay-portal/blob/master/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/service/impl/BlogsEntryLocalServiceImpl.java) @製品のを@のブログアプリ。 `updateAsset` メソッドは、優先度 `引数` 取ります。この引数は、 `assetEntryLocalService.updateEntry` 呼び出しの最後の引数として渡します。

    @Override
    public void updateAsset(
            long userId, BlogsEntry entry, long[] assetCategoryIds,
            String[] assetTagNames, long[] assetLinkEntryIds, Double priority)
        throws PortalException {
    
        ...
    
        AssetEntry assetEntry = assetEntryLocalService.updateEntry(
            userId, entry.getGroupId(), entry.getCreateDate(),
            entry.getModifiedDate(), BlogsEntry.class.getName(),
            entry.getEntryId(), entry.getUuid(), 0, assetCategoryIds,
            assetTagNames, true, visible, null, null, null, null,
            ContentTypes.TEXT_HTML, entry.getTitle(), entry.getDescription(),
            summary, null, null, 0, 0, priority);
    
        ...
    }

`BlogsEntryLocalServiceImpl` クラスは、ブログエントリを追加または更新するときに、この `updateAsset` メソッドを呼び出します。 `serviceContext.getAssetPriority（）` が優先度を取得することに注意してください。

    updateAsset(
            userId, entry, serviceContext.getAssetCategoryIds(),
            serviceContext.getAssetTagNames(),
            serviceContext.getAssetLinkEntryIds(),
            serviceContext.getAssetPriority());

甘い\！ これで、アプリのアセットの優先順位を有効にする方法がわかりました。

## 関連トピック

[アセットの追加、更新、削除](/docs/7-1/tutorials/-/knowledge_base/t/adding-updating-and-deleting-assets)

[資産の分類とタグ付けの実装](/docs/7-1/tutorials/-/knowledge_base/t/implementing-asset-categorization-and-tagging)

[資産の関連付け](/docs/7-1/tutorials/-/knowledge_base/t/relating-assets)

[アセットのレンダリング](/docs/7-1/tutorials/-/knowledge_base/t/rendering-an-asset)

[アセットの公開](/docs/7-1/user/-/knowledge_base/u/publishing-assets)
