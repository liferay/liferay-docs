---
header-id: developing-staged-model-data-handlers
---

# 段階的モデルデータハンドラーの開発

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

実装できるデータハンドラには2つのタイプがあります *ポートレットデータハンドラ* および *ステージングモデルデータハンドラ*。 Liferayのデータハンドラーの背後にある基礎とLARファイルの構築方法の詳細については、「 [データハンドラーについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-data-handlers) チュートリアル」を参照してください。 このチュートリアルでは、ブックマークアプリケーションのステージングモデルデータハンドラーを作成する方法を学習します。

| **注：** データを使用するようにアプリケーションが適切に構成されていることを確認する必要があります。ハンドラー。 これを行う方法の詳細については、を参照してください| [データハンドラの基礎](/docs/7-1/tutorials/-/knowledge_base/t/understanding-data-handlers#data-handler-fundamentals) |セクション。

ステージングモデルデータハンドラーは、ステージングモデル（エンティティ）に関する情報をエクスポート/インポートフレームワークに提供し、UIの表示名を定義し、エンティティを削除します。 参照コンテンツのエクスポートも担当します。 たとえば、BookmarksエントリがBookmarksフォルダーにある場合、 `BookmarksEntry` ステージングモデルデータハンドラーは `BookmarksFolder`エクスポートを呼び出します。

このチュートリアルでは、 [段階のモデル](/docs/7-1/tutorials/-/knowledge_base/t/understanding-staged-models)がすでに作成されていることを前提としています。 ブックマークアプリケーションには、エントリとフォルダの2つの段階的なモデルがあります。 これら2つのエンティティのデータハンドラーの作成は似ているので、ブックマークエントリに対してこれがどのように行われるかを調べます。

1.  データハンドラクラスの既存のService Builderプロジェクトに新しいパッケージを作成します。 たとえば、Bookmarksアプリケーションのデータハンドラクラスは、 `bookmarks-service` モジュールの `com.liferay.bookmarks.internal.exportimport.data.handler` パッケージにあります。

2.  `-exportimport.data.handler` パッケージに `-StagedModelDataHandler` クラスを作成します。 ステージングされたモデルデータハンドラークラスは、 [BaseStagedModelDataHandler](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BaseStagedModelDataHandler.html) クラスを拡張し、エンティティタイプをパラメーターとして指定する必要があります。 `BookmarksEntryStagedModelDataHandler` クラスでこれがどのように行われたかを以下で確認できます。
   
        public class BookmarksEntryStagedModelDataHandler
            extends BaseStagedModelDataHandler<BookmarksEntry> {

3.  クラス宣言の上に `@Component` 注釈セクションを作成します。 この注釈は、クラスをポートレットデータハンドラと同様のステージングモデルデータハンドラとして登録する役割を果たします。
   
        @Component(immediate = true, service = StagedModelDataHandler.class)

    `immediate` 要素は、提供されたモジュールが開始されるとすぐにコンポーネントをアクティブ化するようにコンテナに指示します。 `サービス` 要素は、 `StagedModelDataHandler.class` インターフェイスを指す必要があります。

    | **注：** @product@の以前のバージョンでは、ステージング|を登録する必要がありました。ポートレットの `liferay-portlet.xml` ファイルのモデルデータハンドラー。 |を使用して、OSGiによって登録プロセスが自動的に完了します。 `@Component` アノテーション。

4.  データハンドラを提供するステージングモデルのローカルサービスの取得メソッドと設定メソッドを作成します。
   
        @Override
        protected BookmarksEntryLocalService getBookmarksEntryLocalService() {
            return _bookmarksEntryLocalService;
        }
       
        @Reference(unbind = "-")
        protected void setBookmarksEntryLocalService(
            BookmarksEntryLocalService bookmarksEntryLocalService) {
       
            _bookmarksEntryLocalService = bookmarksEntryLocalService;
        }
       
        private BookmarksEntryLocalService _bookmarksEntryLocalService;

    これらのメソッドは、このデータハンドラーをブックマークエントリのステージングモデルにリンクするために使用されます。

    **重要：** @product@の公式ブックマークアプリは、ステージングされたモデルデータハンドラーでローカルサービスを使用しません。代わりに、 [StagedModelRepository](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/staged/model/repository/StagedModelRepository.html) フレームワークを使用します。 これは新しいフレームワークですが、ステージングモデルデータハンドラーをセットアップする際の実行可能なオプションです。 この詳細については、「ステージング</a> エンティティ固有のローカルサービスを提供する

チュートリアルセクションを参照してください。 ローカルサービスはカスタムアプリでより広く使用されているため、このチュートリアルでは代わりにそれらを取り上げます。</p></li> 
   
   5  データハンドラが追跡するモデルのクラス名を指定する必要があります。 これを行うには、 [StagedModelDataHandler](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandler.html)の `getClassnames（）` メソッドをオーバーライドします。
  
       public static final String[] CLASS_NAMES = {BookmarksEntry.class.getName()};
      
       @Override
       public String[] getClassNames() {
           return CLASS_NAMES;
       }
      
  
  ベストプラクティスとして、ステージングモデルごとに1つのステージングモデルデータハンドラーが必要です。 複数のクラスタイプを使用することは可能ですが、これは推奨されません。

6  ステージングされたモデルの表示名を取得するメソッドを追加します。
  
       @Override
       public String getDisplayName(BookmarksEntry entry) {
           return entry.getName();
       }
      
  
  表示名は、エクスポート/インポートプロセス中に進行状況バーに表示されます。
  
  ![図1：ステージングモデルデータハンドラーは、エクスポート/インポートUIで表示名を提供します。](../../images/staged-model-display-name.png)

7  段階的モデルデータハンドラーは、その操作に必要なすべてのものもエクスポートされるようにする必要があります。 たとえば、ブックマークアプリケーションでは、フォルダ構造をそのまま保持するためにエントリにフォルダが必要です。 したがって、最初にフォルダをエクスポートしてから、エントリをエクスポートする必要があります。
  
  ステージングモデルとその参照をインポートおよびエクスポートするメソッドを追加します。
  
       @Override
       protected void doExportStagedModel(
               PortletDataContext portletDataContext, BookmarksEntry entry)
           throws Exception {
      
           if (entry.getFolderId() !=
                   BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
      
               StagedModelDataHandlerUtil.exportReferenceStagedModel(
                   portletDataContext, entry, entry.getFolder(),
                   PortletDataContext.REFERENCE_TYPE_PARENT);
           }
      
           Element entryElement = portletDataContext.getExportDataElement(entry);
      
           portletDataContext.addClassedModel(
               entryElement, ExportImportPathUtil.getModelPath(entry), entry);
       }
      
       @Override
       protected void doImportStagedModel(
               PortletDataContext portletDataContext, BookmarksEntry entry)
           throws Exception {
      
           Map<Long, Long> folderIds =
               (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
                   BookmarksFolder.class);
      
           long folderId = MapUtil.getLong(
               folderIds, entry.getFolderId(), entry.getFolderId());
      
           ServiceContext serviceContext =
               portletDataContext.createServiceContext(entry);
      
           BookmarksEntry importedEntry = null;
      
           if (portletDataContext.isDataStrategyMirror()) {
      
               BookmarksEntry existingEntry =
                   _bookmarksEntryLocalService. fetchBookmarksEntryByUuidAndGroupId(
                       entry.getUuid(), portletDataContext.getScopeGroupId());
      
               if (existingEntry == null) {
      
                   serviceContext.setUuid(entry.getUuid());
                   importedEntry = _bookmarksEntryLocalService.addEntry(                   
                     userId, portletDataContext.getScopeGroupId(), folderId, entry.getName(), entry.getUrl(), entry.getDescription(), serviceContext);
               }
               else {
                   importedEntry = _bookmarksEntryLocalService.updateEntry(
                       userId, existingEntry.getEntryId(), portletDataContext.getScopeGroupId(), folderId, entry.getName(), entry.getUrl(), entry.getDescription(),    serviceContext);
               }
           }
           else {
               importedEntry = _bookmarksEntryLocalService.addEntry(userId, portletDataContext.getScopeGroupId(), folderId,entry.getName(), entry.getUrl(), entry.getDescription(),    serviceContext);
           }
      
           portletDataContext.importClassedModel(entry, importedEntry);
       }
      
  
  `doExportStagedModel` メソッドは、 [PortletDataContext](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataContext.html) からBookmarkエントリのデータ要素を取得し、そのデータ要素によって特徴付けられるクラスモデルを `PortletDataContext`追加します。 `PortletDataContext` は、エクスポートプロセス中にLARファイルにアプリケーションのデータを取り込むために使用されます。 エンティティがエクスポートされると、エクスポートメソッドへの後続の呼び出しでは、実際にエクスポートプロセスが複数回繰り返されることはないため、最適なパフォーマンスが保証されます。
  
  インポートプロセスの重要な機能は、ブックマークの例でエクスポートされたすべての参照要素が必要なときに自動的にインポートされることです。 `doImportStagedModel` メソッドでは、参照要素を手動でインポートする必要はありません。エントリをインポートする前に、フォルダに割り当てられた新しいIDを見つけるだけです。
  
  `PortletDataContext` は、インポートプロセス中にこの情報と他の多数のデータを最新の状態に保ちます。 例に示すように、 `portletDataContext.getNewPrimaryKeysMap（）` メソッドを使用すると、古いIDと新しいIDのマッピングに到達できます。 この方法では、インポートモード（たとえば、 *Copy As New* または *Mirror*）のチェックが続行され、プロセス構成と既存の環境に応じて、エントリが追加または更新されます。

8  LARをインポートする（つまり、ライブサイトに公開する）場合、インポートプロセスは、エンティティのすべての参照が利用可能であることを期待し、それらの存在を検証します。
  
  たとえば、更新されたブックマークフォルダーをライブサイトに再発行し、その既存のエントリの一部をパブリケーションに含めなかった場合、これらのエントリは参照の欠落と見なされます。 これのより実用的な例は、Webコンテンツ記事に含まれる画像です。 Webコンテンツに含まれる画像が別のサイトにある場合（つまり、画像が別のグループに含まれている場合）、または公開プロセスに含まれていない場合、Webコンテンツ記事の参照が欠落していると見なされます。
  
  異なるIDを持つ2つの別個のサイトからの参照があるため、システムは公開中にそれらを一致させることはできません。 たとえば、主キー（ID）が `1`欠落した参照としてブックマークエントリをエクスポートするとします。 その情報をインポートするとき、LARはIDのみを提供し、エントリ自体は提供しません。 そのため、インポートプロセス中に、データハンドラフレームワークはUUIDで置換するエントリを検索しますが、置換するエントリのID（プライマリキー）は `2`です。 これらの欠落した参照を処理する方法を提供する必要があります。
  
  これを行うには、インポート中に、不足している参照のプライマリキーをエクスポートから既存のプライマリキーにマップするメソッドを追加する必要があります。 参照のUUIDはシステム全体で一定であるため、異なる主キーのマッピングを完了するために使用されます。 参照は、既に公開されている場合にのみ、ライブサイトに存在しないことに注意してください。 したがって、ブックマークフォルダーを初めて公開するとき、システムは参照の欠落をチェックしません。
  
  このメソッドをクラスに追加します。
  
       @Override
       protected void doImportMissingReference(
               PortletDataContext portletDataContext, String uuid, long groupId,
               long entryId)
           throws Exception {
      
           BookmarksEntry existingEntry = fetchMissingReference(uuid, groupId);
      
           if (existingEntry == null) {
               return;
           }
      
           Map<Long, Long> entryIds =
               (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
                   BookmarksEntry.class);
      
           entryIds.put(entryId, existingEntry.getEntryId());
       }
      
  
  このメソッドは、既存のステージングモデルを参照要素の古いIDにマップします。 参照が欠落しているとしてエクスポートされると、データハンドラフレームワークはインポートプロセス中にこのメソッドを呼び出し、ポートレットデータコンテキストの新しいプライマリキーマップを更新します。</ol> 

ファンタスティック\！ ステージングモデルのデータハンドラーを作成しました。 エクスポート/インポートフレームワークは、エンティティの動作とデータを追跡できるようになりました。 ポートレット固有のデータを管理するために、ポートレットデータハンドラも実装してください。 [ポートレットデータハンドラーの開発](/docs/7-1/tutorials/-/knowledge_base/t/developing-portlet-data-handlers)参照してください。 ブックマークアプリでこれを行うには。
