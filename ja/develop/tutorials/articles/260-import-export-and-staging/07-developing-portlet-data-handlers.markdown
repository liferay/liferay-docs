---
header-id: developing-portlet-data-handlers
---

# ポートレットデータハンドラーの開発

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

実装できるデータハンドラには2つのタイプがあります *ポートレットデータハンドラ* および *ステージングモデルデータハンドラ*。 Liferayのデータハンドラーの背後にある基礎とLARファイルの構築方法の詳細については、「 [データハンドラーについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-data-handlers) チュートリアル」を参照してください。 このチュートリアルでは、ブックマークアプリケーション用のポートレットデータハンドラーを作成します。

| **注：** データを使用するようにアプリケーションが適切に構成されていることを確認する必要があります。ハンドラー。 これを行う方法の詳細については、を参照してください| [データハンドラの基礎](/docs/7-1/tutorials/-/knowledge_base/t/understanding-data-handlers#data-handler-fundamentals) |セクション。

ポートレットデータハンドラは、ポートレット固有のデータをLARファイルにインポート/エクスポートします。 これらのクラスは、 [段階のモデルデータハンドラー](/docs/dev/tutorials/-/knowledge_base/t/lop/tutorials/-/knowledge_base/developing-staged-model-data-handlers)間でクエリと調整を行う役割のみを持ちます。 たとえば、ブックマークアプリケーションのポートレットデータハンドラは、ブックマークエンティティを処理するシステムイベントを追跡します。 また、ブックマークアプリケーションのエクスポート/インポートUIオプションも構成します。

次の手順では、Bookmarksアプリケーションに使用する `BookmarksPortletDataHandler` クラスを作成します。

1.  データハンドラクラスの既存のService Builderプロジェクトに新しいパッケージを作成します。 たとえば、Bookmarksアプリケーションのデータハンドラクラスは、 `bookmarks-service` モジュールの `com.liferay.bookmarks.internal.exportimport.data.handler` パッケージにあります。

2.  新しい `-exportimport.data.handler` パッケージでアプリケーションの `-PortletDataHandler` クラスを作成し、 [BasePortletDataHandler](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BasePortletDataHandler.html) クラスを拡張して [PortletDataHandler](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataHandler.html) インターフェースを実装します。 例えば、
   
        public class BookmarksPortletDataHandler extends BasePortletDataHandler {

3.  クラス宣言の上に `@Component` 注釈セクションを作成します。 この注釈は、このクラスをOSGiサービスレジストリのポートレットデータハンドラとして登録します。
   
        @Component(
            immediate = true,
            property = {
                "javax.portlet.name=" + BookmarksPortletKeys.BOOKMARKS,
                "javax.portlet.name=" + BookmarksPortletKeys.BOOKMARKS_ADMIN
            },
            service = PortletDataHandler.class
        )

    設定すべき注釈属性がいくつかあります。

      - `immediate` 要素は、提供されたモジュールが開始されるとすぐにコンポーネントをアクティブ化するようにコンテナに指示します。
      - `property` 要素は、コンポーネントサービスのさまざまなプロパティを設定します。 エクスポート/インポート環境で適切に機能するように、処理するポートレットをこのサービスに関連付ける必要があります。 たとえば、ブックマークデータハンドラは2つのポートレットに使用されるため、両方とも `javax.portlet.name` プロパティを使用して構成されます。
      - `サービス` 要素は、 `PortletDataHandler.class` インターフェースを指す必要があります。

    | **注：** @product@の以前のバージョンでは、ポートレットを登録する必要がありました|ポートレットの `liferay-portlet.xml` ファイルのデータハンドラ。 登録|プロセスは、OSGiが `@Component` |を使用して自動的に完了するようになりました。注釈。

4.  `activate` メソッドを追加して、ポートレットデータハンドラーが制御するものと、ポートレットのエクスポート/インポートUIを設定します。

        @Activate protected void activate（）{ setDataPortletPreferences（ "rootFolderId"）; setDeletionSystemEventStagedModelTypes（ 新しいStagedModelType（BookmarksEntry.class）、 新しいStagedModelType（BookmarksFolder.class））; setExportControls（ new PortletDataHandlerBoolean（ NAMESPACE、 "entries"、true、false、null、 BookmarksEntry.class.getName（）））; setImportControls（getExportControls（））; }

    このメソッドは、 [@Activate](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Activate.html) アノテーションを使用して、コンポーネントの初期化中に呼び出されます。 このメソッドは、依存関係が設定された後、サービスが登録される前に呼び出されます。

    `BookmarksPortletDataHandler`の `activate` メソッドで呼び出される4つの `set` メソッドを以下に説明します。

      - `setDataPortletPreferences`：ブックマークアプリケーションが処理するポートレットプリファレンスを設定します。
      - `setDeletionSystemEventStagedModelTypes`：ポートレットデータハンドラーが追跡する段階的なモデルの削除を設定します。 ブックマークアプリケーションの場合、ブックマークのエントリとフォルダが追跡されます。
      - `setExportControls`：エクスポートUIでレンダリングされるエクスポート動作を細かく制御します。 ブックマークアプリケーションの場合、エクスポートするブックマークコンテンツ（エントリ）を選択するためのチェックボックスが追加されます。
      - `setImportControls`：インポートUIでレンダリングされるインポート動作を細かく制御します。 ブックマークアプリケーションの場合、インポートするブックマークコンテンツ（エントリ）を選択するためのチェックボックスが追加されます。

    ![図1：UIでエクスポート/インポートするコンテンツタイプを選択できます。](../../images/export-import-controls.png)

    これらのメソッドの詳細については、 [PortletDataHandler](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataHandler.html) APIをご覧ください。

5.  ブックマークポートレットデータハンドラがそのエントリおよびフォルダステージングモデルを正常に参照するには、クラスでそれらを設定する必要があります。
   
        @Reference(unbind = "-")
        protected void setBookmarksEntryLocalService(
            BookmarksEntryLocalService bookmarksEntryLocalService) {
       
            _bookmarksEntryLocalService = bookmarksEntryLocalService;
        }
       
        @Reference(unbind = "-")
        protected void setBookmarksFolderLocalService(
            BookmarksFolderLocalService bookmarksFolderLocalService) {
       
            _bookmarksFolderLocalService = bookmarksFolderLocalService;
        }
       
        private BookmarksEntryLocalService _bookmarksEntryLocalService;
        private BookmarksFolderLocalService _bookmarksFolderLocalService;

    `セット` メソッドには、 [@Reference](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Reference.html) アノテーションを付ける必要があります。 @product@で `@Reference` アノテーションを使用する方法の詳細については、 [Invoking Local Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-local-services) チュートリアルをご覧ください。

    **重要：** @product@の公式ブックマークアプリは、ポートレットデータハンドラーでローカルサービスを使用しません。代わりに、 [StagedModelRepository](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/staged/model/repository/StagedModelRepository.html) フレームワークを使用します。 これは新しいフレームワークですが、ポートレットデータハンドラを設定する際の実行可能なオプションです。 この詳細については、「ステージング</a> エンティティ固有のローカルサービスを提供する

チュートリアルセクションを参照してください。 ローカルサービスはカスタムアプリでより広く使用されているため、このチュートリアルでは代わりにそれらを取り上げます。</p></li> 
   
   6  You must create a namespace for your entities so the Export/Import framework can identify your application's entities from other entities in @product@. Bookmarksアプリケーションの名前空間宣言は次のようになります。
  
       public static final String NAMESPACE = "bookmarks";
      
  
  この名前空間が後でどのように使用されるかがわかるでしょう。

7  ポートレットデータハンドラは、ステージングされたモデルエンティティに関連するデータを取得して、適切にエクスポート/インポートできるようにする必要があります。 次のメソッドを挿入して、この機能を追加します。
  
       @Override
       protected String doExportData(
               final PortletDataContext portletDataContext, String portletId,
               PortletPreferences portletPreferences)
           throws Exception {
      
           Element rootElement = addExportDataRootElement(portletDataContext);
      
           if (!portletDataContext.getBooleanParameter(NAMESPACE, "entries")) {
               return getExportDataRootElementString(rootElement);
           }
      
           portletDataContext.addPortletPermissions(
               BookmarksConstants.RESOURCE_NAME);
      
           rootElement.addAttribute(
               "group-id", String.valueOf(portletDataContext.getScopeGroupId()));
      
           ExportActionableDynamicQuery folderActionableDynamicQuery =
               _bookmarksFolderLocalService.
                   getExportActionableDynamicQuery(portletDataContext);
      
           folderActionableDynamicQuery.performActions();
      
           ActionableDynamicQuery entryActionableDynamicQuery =
               _bookmarksEntryLocalService.
                   getExportActionableDynamicQuery(portletDataContext);
      
           entryActionableDynamicQuery.performActions();
      
           return getExportDataRootElementString(rootElement);
       }
      
       @Override
       protected PortletPreferences doImportData(
               PortletDataContext portletDataContext, String portletId,
               PortletPreferences portletPreferences, String data)
           throws Exception {
      
           if (!portletDataContext.getBooleanParameter(NAMESPACE, "entries")) {
               return null;
           }
      
           portletDataContext.importPortletPermissions(
               BookmarksConstants.RESOURCE_NAME);
      
           Element foldersElement = portletDataContext.getImportDataGroupElement(
               BookmarksFolder.class);
      
           List<Element> folderElements = foldersElement.elements();
      
           for (Element folderElement : folderElements) {
               StagedModelDataHandlerUtil.importStagedModel(
                   portletDataContext, folderElement);
           }
      
           Element entriesElement = portletDataContext.getImportDataGroupElement(
               BookmarksEntry.class);
      
           List<Element> entryElements = entriesElement.elements();
      
           for (Element entryElement : entryElements) {
               StagedModelDataHandlerUtil.importStagedModel(
                   portletDataContext, entryElement);
           }
      
           return null;
       }
      
  
  `doExportData` メソッドは、まず何かをエクスポートする必要があるかどうかをチェックします。 `portletDataContext.getBooleanParameter（...）` メソッドは、ユーザーがエクスポート用のブックマークエントリを選択したかどうかを確認します。 後で、 `ExportImportActionableDynamicQuery` フレームワークはブックマークフォルダーとエントリに対してクエリを実行し、LARファイルにエクスポートする必要があるものを見つけます。
  
  `-ActionableDynamicQuery` クラスはService Builderによって自動的に生成され、アプリケーションのローカルサービスで使用できます。 特定のステージング固有のパラメーター（例： `createDate` および `modifiedDate`）を検索するデータベースを照会し、それらのパラメーターに基づいて、ステージングされたモデルデータハンドラーからエクスポート可能なレコードのリストを見つけます。
  
  <!-- Dynamic Queries should be documented elsewhere for LRDOCS-328 and 
 linked here. -Cody -->
  
  `doImportData` は、データベースに追加する必要があるインポートされたLARファイル内のブックマークエントリとフォルダーデータを照会します。 これは、 [StagedModelDataHandlerUtil](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandlerUtil.html) クラスのユーティリティメソッドを使用して、LARファイルからXML要素を抽出することにより行われます。 抽出された要素は、@product@にLARファイルからインポートするデータを伝えます。

8  ポートレットのデータを削除するメソッドを追加します。 ステージングフレームワークには、 *インポート前にポートレットデータを削除* というオプションがあり、ユーザーは新しいデータをインポートする前にポートレットデータを削除できます。 この削除操作を実行するために、 `doDeleteData（...）` メソッドが呼び出されます。
  
       @Override
       protected PortletPreferences doDeleteData(
               PortletDataContext portletDataContext, String portletId,
               PortletPreferences portletPreferences)
           throws Exception {
      
           if (portletDataContext.addPrimaryKey(
                   BookmarksPortletDataHandler.class, "deleteData")) {
      
               return portletPreferences;
           }
      
           _bookmarksEntryLocalService.deleteEntries(
               portletDataContext.getScopeGroupId(),
               BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);
      
           _bookmarksFolderLocalService.deleteFolders(
               portletDataContext.getScopeGroupId());
      
           return portletPreferences;
       }
      
  
  このメソッドは、存在しないデータへの参照が含まれている場合、ポートレット設定の変更バージョンを返すこともできます。
  
  | **注：** これは、削除が実行されなかったときに便利なレガシー機能です。サイト間で伝播されます。 これにより、ポートレットのデータが消去され、次のことが可能になりました。すべての公開中に、ポートレットに関連付けられているすべてを参照してください。 |ステージングですべてのサイトの削除を認識できるようになったため、不要になりました。 それは、|ただし、まだステージングの機能として提供されており、| Liferayのブックマークアプリなので、ここに含まれています。

9  現在のエクスポートまたはステージングプロセスに基づいて、影響を受けるエンティティの数をカウントするメソッドを追加します。
  
       @Override
       protected void doPrepareManifestSummary(
               PortletDataContext portletDataContext,
               PortletPreferences portletPreferences)
           throws Exception {
      
           ActionableDynamicQuery entryExportActionableDynamicQuery =
               _bookmarksEntryLocalService.
                   getExportActionableDynamicQuery(portletDataContext);
      
           entryExportActionableDynamicQuery.performCount();
      
           ActionableDynamicQuery folderExportActionableDynamicQuery =
               _bookmarksFolderLocalService.
                   getExportActionableDynamicQuery(portletDataContext);
      
           folderExportActionableDynamicQuery.performCount();
       }
      
  
  この番号は、エクスポートおよびステージングUIに表示されます。 ステージングフレームワークはエクスポート中にエンティティグラフを走査するため、組み込みコンポーネントは場合によってはおおよその値を提供することに注意してください。
  
  ![図2：変更されたブックマークエンティティの数がエクスポートUIに表示されます。](../../images/manifest-summary-count.png)

10 エクスポートされたLARファイルに含まれるXMLファイルのXMLスキーマバージョンを設定します。
  
       public static final String SCHEMA_VERSION = "1.0.0";
      
       @Override
       public String getSchemaVersion() {
           return SCHEMA_VERSION;
       }
      
       @Override
       public boolean validateSchemaVersion(String schemaVersion) {
           return _portletDataHandlerHelper.validateSchemaVersion(
               schemaVersion, getSchemaVersion());
       }
      
  
  スキーマバージョンは、データをインポートする前にコンポーネント関連の検証を実行するために使用されます。 処理中の各アプリケーションの [LARファイル](/docs/7-1/tutorials/-/knowledge_base/t/understanding-data-handlers#liferay-archive-lar-file) に追加されます。 インポート中に、環境のスキーマバージョンがLARファイルのスキーマバージョンと比較されます。 スキーマバージョンの検証により、インポート時に破損したデータを回避できます。 詳細については、 [PortletDataHandler.getVersionScheme（）](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataHandler.html#getSchemaVersion--) メソッドのJavadocを参照してください。</ol> 

驚くばかり\！ ポートレットデータハンドラを設定し、アプリケーションでエクスポート/インポートフレームワークをサポートし、UIを表示できるようになりました。 ステージングモデルには、ステージングモデルデータハンドラーも必ず実装してください。 [ステージングモデルデータハンドラーの開発](/docs/7-1/tutorials/-/knowledge_base/t/developing-staged-model-data-handlers) を参照して、ブックマークアプリでこれを行います。
