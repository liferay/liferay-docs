---
header-id: adding-updating-and-deleting-assets
---

# アセットの追加、更新、削除

[TOC levels=1-4]

Liferayのアセットフレームワークをエンティティで使用するには、作成、変更、削除する各エンティティインスタンスについてアセットフレームワークに通知する必要があります。 この意味で、それは、通知に似て [Liferayの権限のフレームワーク](/docs/7-1/tutorials/-/knowledge_base/t/defining-application-permissions) 新しいリソースについて。 Liferayがエンティティを資産として追跡できるように、 `AssetEntry` をエンティティに関連付けるアセットフレームワークのメソッドを呼び出すだけです。 エンティティを更新するときは、資産を同時に更新します。

アセットを活用するには、ポートレットのエンティティのインデクサーも実装する必要があります。 Liferayのアセットフレームワークは、インデクサーを使用してアセットを管理します。

このチュートリアルでは、カスタムエンティティのアセットを有効にし、インデックスを実装する方法を示します。 始めましょう\！

## Asset Framework用のプロジェクトの準備

プロジェクトの `service.xml` ファイルで、カスタムエンティティのアセットエントリエンティティ参照を追加します。 カスタムエンティティの終了 `</entity>` タグの前に、次の `参照` タグを追加します。

    <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />

次に、 [でService Builderを実行します。](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder)

これで、アセットの追加と更新を実装する準備が整いました！

## 資産の追加と更新

`-LocalServiceImpl` Javaクラスは、その親ベースクラスから `AssetEntryLocalService` インスタンスを継承します。変数 `assetEntryLocalService`割り当てられます。 Liferayアセットとしてカスタムエンティティを追加するには、 `assetEntryLocalService`の `updateEntry` メソッドを呼び出す必要があります。

[`updateEntry`](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portlet/asset/service/impl/AssetEntryLocalServiceImpl.html#updateEntry-long-long-java.util.Date-java.util.Date-java.lang.String-long-java.lang.String-long-long:A-java.lang.String:A-boolean-boolean-java.util.Date-java.util.Date-java.util.Date-java.util.Date-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-int-int-java.lang.Double-) メソッドのシグネチャは次のようになります。

    AssetEntry updateEntry(
        long userId, long groupId, Date createDate, Date modifiedDate,
        String className, long classPK, String classUuid, long classTypeId,
        long[] categoryIds, String[] tagNames, boolean listable,
        boolean visible, Date startDate, Date endDate, Date publishDate,
        Date expirationDate, String mimeType, String title,
        String description, String summary, String url, String layoutUuid,
        int height, int width, Double priority)
    throws PortalException

`updateEntry` メソッドの各パラメーターの説明は次のとおりです。

  - `userId`：コンテンツを更新するユーザーを識別します。
  - `groupId`：作成されたコンテンツの範囲を識別します。 コンテンツがスコープをサポートしていない場合（非常にまれ）、値として `0` を渡します。
  - `createDate`：エンティティが作成された日付。
  - `modifiedDate`：エンティティに対するこの変更の日付。
  - `className`：エンティティのクラスを識別します。 推奨される規則は、コンテンツタイプを表すJavaクラスの名前を使用することです。 たとえば、 `[YourClassName].class.getName（）`から返された値を渡すことができます。
  - `classPK`：特定のエンティティインスタンスを識別し、同じタイプの他のインスタンスと区別します。 通常、エンティティが保存されるテーブルの主キーです。
  - `classUuid`：普遍的に一意であることが保証されているセカンダリ識別子として機能します。 スコープ全体でエンティティインスタンスを関連付けます。 コンテンツが個別のポータル間でエクスポートおよびインポートされる場合に特に役立ちます。
  - `classTypeId`：バリエーションがある場合、このクラスの特定のバリエーションを識別します。 それ以外の場合は、 `0`使用します。
  - `categoryIds`：エンティティに選択されたカテゴリを表します。 アセットフレームワークはそれらを自動的に保存します。
  - `tagNames`：エンティティに選択されたタグを表します。 アセットフレームワークはそれらを自動的に保存します。
  - `listable`：コンテンツの動的リスト（動的に構成されたアセット発行者など）にエンティティを表示できるかどうかを指定します。
  - `可視`：エンティティが承認されるかどうかを指定します。
  - `startDate`：エンティティの公開日。 これを使用して、Asset Publisherがエンティティのコンテンツを表示するタイミングを指定できます。
  - `endDate`：エンティティが削除される日付。 これを使用して、Asset Publisherがエンティティのコンテンツの表示を停止するタイミングを指定できます。
  - `publishDate`：エンティティの表示が開始される日付。
  - `expirationDate`：エンティティが表示されなくなる日付。
  - `mimetype`：コンテンツに使用される [ContentTypes.TEXT \ _HTML](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ContentTypes.html#TEXT_HTML)などの多目的インターネットメール拡張機能タイプ。
  - `タイトル`：エンティティの名前。
  - `description`： `エンティティの文字列`ベースのテキスト記述。
  - `概要`：エンティティのコンテンツの短縮または切り捨てられたサンプル。
  - `url`：エンティティにオプションで関連付けるURL。
  - `layoutUuid`：エントリのデフォルト表示ページのレイアウトの普遍的に一意のID。
  - `高さ`：これは `0`設定できます。
  - `幅`：これは `0`設定できます。
  - `優先度`：エンティティがピアエンティティインスタンス間でランク付けされる方法を指定します。 低い数値は、高い数値よりも優先されます。

LiferayのWikiアプリケーションの [`WikiPageLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/wiki/wiki-service/src/main/java/com/liferay/wiki/service/impl/WikiPageLocalServiceImpl.java) Javaクラスの次のコードは、 `WikiPage`というWikiページエンティティで `updateEntry` メソッドを呼び出す方法を示しています。 `add-` メソッドでは、エンティティのリソースを追加した後に `updateEntry` 呼び出すことができます。 同様に、 `update-` メソッドでは、 `super.update-` メソッドを呼び出した後に `updateEntry` 呼び出すことができます。 以下のコードは、 `WikiPageLocalServiceImpl` クラスの `updateStatus（...）` メソッドで呼び出されます。

    AssetEntry assetEntry = assetEntryLocalService.updateEntry(
        userId, page.getGroupId(), page.getCreateDate(),
        page.getModifiedDate(), WikiPage.class.getName(),
        page.getResourcePrimKey(), page.getUuid(), 0,
        assetCategoryIds, assetTagNames, true, true, null, null,
        page.getCreateDate(), null, ContentTypes.TEXT_HTML,
        page.getTitle(), null, null, null, null, 0, 0, null);
    
    Indexer<WikiPage> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
        WikiPage.class);
    
    indexer.reindex(page);

`updateEntry` メソッドを呼び出した直後に、それぞれのアセットを更新し、エンティティインスタンスにインデックスを付ける必要があります。 上記のコードは、インデクサーを呼び出してエンティティのインデックスを作成します（更新する場合はインデックスを再作成します）。 これですべてです。

| **ヒント：** 現在のユーザーのIDとスコープグループIDは一般的に作成されます|サービスコンテキストパラメータで利用可能。 使用するサービスコンテキストに含まれている場合|それらの場合、次のような呼び出しでそれらにアクセスできます。 | long userId = serviceContext.getUserId（）; | long groupId = serviceContext.getScopeGroupId（）;

次に、アセットに関連付けられているエンティティを削除するために必要なものを学習します。

## 資産を削除する

エンティティを削除するときは、関連するアセットとインデックスを同時に削除する必要があります。 これにより、保存されたアセットとインデックス情報がクリーンアップされ、削除したエンティティの情報がAsset Publisherに表示されなくなります。

`-LocalServiceImpl` Javaクラスで、 `delete-` メソッドを開きます。 エンティティのリソースを削除するコードの後に、エンティティインスタンスのアセットエントリとインデックスを削除します。

以下は、ポートレットのエンティティに関連付けられたアセットエントリとインデックスを削除するコードです。

    assetEntryLocalService.deleteEntry(
        ENTITY.class.getName(), assetEntry.getEntityId());
    
    Indexer<ENTITY> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ENTITY.class);
    indexer.delete(assetEntry);

`-LocalServiceImpl` クラスでは、同様のコードを記述できます。 *ENTITY* クラス名と変数をエンティティの名前に置き換えます。

| **重要：** LiferayのAsset Publisherアプリケーションでエンティティを表示するには、|エンティティにはアセットレンダラーが必要です。 | |また、アセットレンダラーは、ユーザーのコンポーネントを表示する方法であることに注意してください。 Asset Publisherのエンティティ。 アセット、インデクサー、ポートレットを使用してポートレットをデプロイするときアセットレンダリングの実装を適切に行うと、アセットパブリッシャーはカスタムエンティティ\！

![図1：このWikiページエンティティのようなカスタムエンティティをJSPまたはAsset Publisherに表示すると便利です。](../../images/basic-asset-in-asset-publisher.png)

すばらしいです\！ これで、アプリでアセットを追加、更新、削除する方法がわかりました！

## 関連トピック

[資産の関連付け](/docs/7-1/tutorials/-/knowledge_base/t/relating-assets)

[サービスビルダーとは](/docs/7-1/tutorials/-/knowledge_base/t/what-is-service-builder)
