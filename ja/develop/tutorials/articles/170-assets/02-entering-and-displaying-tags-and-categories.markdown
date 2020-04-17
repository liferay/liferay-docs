---
header-id: implementing-asset-categorization-and-tagging
---

# 資産の分類とタグ付けの実装

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

このチュートリアルでは、一連のJSPタグを使用して、UIでタグとカテゴリエンティティを有効にします。 開始する前に、エンティティを [アセット対応](/docs/7-1/tutorials/-/knowledge_base/t/adding-updating-and-deleting-assets) 、それらに対してアセットレンダラーを有効にする必要があります。

![図1：カテゴリとタグの入力オプションを追加すると、作成者はカスタムエンティティを集約してラベル付けできます。](../../images/asset-fw-categories-and-tags-options.png)

さあ、始めましょう\！

## タグとカテゴリを追加する

カスタムエンティティを追加/編集するために提供するJSPで次のタグを使用できます。 Blogsポートレットの [edit \ _entry.jsp](https://github.com/liferay/liferay-portal/blob/master/modules/apps/blogs/blogs-web/src/main/resources/META-INF/resources/blogs/edit_entry.jsp) のタグは次のようになります。

    <liferay-ui:asset-categories-error />
    <liferay-ui:asset-tags-error />
    ...
    <aui:fieldset-group markupView="lexicon">
        ...
        <aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="categorization">
            <liferay-asset:asset-categories-selector name="categories" type="assetCategories" />
    
            <liferay-asset:asset-tags-selector name="tags" type="assetTags" />
        </aui:fieldset>
        ...
    </aui:fieldset-group>

`liferay-asset：asset-categories-selector` および `liferay-asset：asset-tags-selector` タグは、ユーザーがエンティティのカテゴリを参照/選択、タグを参照/選択、および/または新しいタグを作成できるフォームコントロールを生成しますエンティティに関連付けます。

`liferay-ui：asset-categories-error` および `liferay-ui：asset-tags-error` タグは、アセットカテゴリまたはタグ入力プロセス中に発生したエラーのメッセージを表示します。 `aui：fieldset` タグは、ユーザーがカテゴリおよびタグ入力オプションを非表示または表示できるコンテナを使用します。

スタイリングのために、 `aui：fieldset-group` タグには `レキシコン` マークアップビューが与えられます。

## タグとカテゴリの表示

タグとカテゴリは、アセットのコンテンツとともに表示される必要があります。 タグとカテゴリを表示する方法は次のとおりです。

    <p><liferay-ui:message key="categories" />:</p>
    
    <div class="entry-categories">
        <liferay-ui:asset-categories-summary
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
            portletURL="<%= renderResponse.createRenderURL() %>"
        />
    </div>
    
    ...
    
    <div class="entry-tags">
        <p><liferay-ui:message key="tags" />:</p>
    
        <liferay-ui:asset-tags-summary
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
            portletURL="<%= renderResponse.createRenderURL() %>"
        />
    </div>

`portletURL` パラメーターは、タグとカテゴリーの両方に使用されます。 このパラメーターを使用する各タグは、 `portletURL` *および* `タグ` または `categoryId` パラメーター値を含むリンクになります。 これを実装するには、ポートレットコードにルックアップ機能を実装する必要があります。 これを行うには、これらの2つのパラメーターの値を読み取り、 `AssetEntryService` を使用して、指定されたタグまたはカテゴリーに基づいてデータベースのクエリを実行します。

変更をデプロイし、UIでカスタムエンティティを追加/編集します。 フォームには、ユーザーが非表示/表示できるパネルに分類とタグ入力オプションが表示されます。

すばらしいです\！ これで、カテゴリとタグの入力オプションをアプリのコンテンツ作成者が利用できるようにする方法がわかりました。

## 関連トピック

[資産の関連付け](/docs/7-1/tutorials/-/knowledge_base/t/relating-assets)

[アセットの追加、更新、削除](/docs/7-1/tutorials/-/knowledge_base/t/adding-updating-and-deleting-assets)

[サービスビルダーとは](/docs/7-1/tutorials/-/knowledge_base/t/what-is-service-builder)
