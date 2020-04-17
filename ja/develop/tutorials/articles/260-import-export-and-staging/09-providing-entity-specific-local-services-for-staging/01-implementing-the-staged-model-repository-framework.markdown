---
header-id: implementing-the-staged-model-repository-framework
---

# 段階的モデルリポジトリフレームワークの実装

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

アプリのステージング機能に専用のローカルサービスを提供すると、ステージング固有の追加情報をデータハンドラーから抽象化できます。 アプリでStaged Model Repositoryフレームワークの使用を開始する前に、それを実装する必要があります。

以下は、ステージングモデルに使用する `StagedModelRepository` インターフェイスの実装を示す簡単な例です。 この例では、Liferayのブックマークアプリとブックマークエントリエンティティを参照しています。

1.  アプリの `サービス` バンドルで、ステージングモデルリポジトリクラスを保持するパッケージを作成します（例： `com.liferay.bookmarks.exportimport.staged.model.repository`）。 `サービス` バンドルがない場合は、 [サービスビルダー](/docs/7-1/tutorials/-/knowledge_base/t/service-builder) チュートリアルでアプリのサービスの生成に関する情報をご覧ください。 ほとんどのステージング機能を活用するには、それらが必要です。

2.  新しいパッケージに `-StagedModelRepository` クラスを作成し、クラスの宣言に `StagedModelRepository` インターフェイスを実装します。 例えば、
   
        public class BookmarksEntryStagedModelRepository
            implements StagedModelRepository<BookmarksEntry> {

    また、このリポジトリの段階的なモデルタイプパラメーター（例： `BookmarksEntry`）も含めるようにしてください。

3.  次のようなステージングモデルリポジトリクラスの `@Component` アノテーションを追加します。
   
        @Component(
            immediate = true,
            property = "model.class.name=FULLY_QUALIFIED_MODEL_CLASS",
            service = StagedModelRepository.class
        )

    設定すべき注釈属性がいくつかあります。

      - `immediate` 要素は、提供されたモジュールが開始されるとすぐにコンポーネントをアクティブ化するようにコンテナに指示します。
      - `property` 要素は、コンポーネントサービスのさまざまなプロパティを設定します。 処理するモデルクラスをこのサービスに関連付けて、それを利用するデータハンドラーによって認識されるようにする必要があります。 これについては後で詳しく説明します。
      - `サービス` 要素は、 `StagedModelRepository.class` インターフェースを指す必要があります。

    `BookmarksEntryStagedModelRepository`の `@Component` アノテーションは次のようになります。

        @Component（ immediate = true、 property = "model.class.name = com.liferay.bookmarks.model.BookmarksEntry"、 service = StagedModelRepository.class ）

4.  ステージングモデルリポジトリに `StagedModelRepository` インターフェイスのメソッドを実装します。 このインターフェイスの [Javadoc](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/staged/model/repository/StagedModelRepository.html) を参照して、各メソッドの目的を確認できます。

    例として、いくつかのメソッド実装をステップスルーして、それがどのように機能するかを理解します。

    Bookmarksエントリに `addStagedModel（...）` メソッドを実装すると、次のようになります。

        @Override public BookmarksEntry addStagedModel（ PortletDataContext portletDataContext、 BookmarksEntry bookmarksEntry） throws PortalException {
   
          long userId = portletDataContext.getUserId(
              bookmarksEntry.getUserUuid());
       
          ServiceContext serviceContext = portletDataContext.createServiceContext(
              bookmarksEntry);
       
          if (portletDataContext.isDataStrategyMirror()) {
              serviceContext.setUuid(bookmarksEntry.getUuid());
          }
       
          return _bookmarksEntryLocalService.addEntry(
              userId, bookmarksEntry.getGroupId(), bookmarksEntry.getFolderId(),
              bookmarksEntry.getName(), bookmarksEntry.getUrl(),
              bookmarksEntry.getDescription(), serviceContext);
        }

    このメソッドは、ポートレットデータコンテキストに基づいてユーザーIDとサービスコンテキストを設定します。 [`PortletDataContext`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataContext.html) は、エクスポートプロセス中にLARファイルにアプリケーションのデータを取り込むために使用されます。 次に、サイト間でステージングされたコンテンツを区別するために必要なUUIDを設定します。 最後に、エンティティのローカルサービスが呼び出されます。

    `BookmarksEntryLocalService.addEntry（...）` メソッドを呼び出すだけでは、ステージングされたモデルデータハンドラーのニーズ（つまり、UUID要件）を満たすのに十分ではありません。 ただし、ステージングモデルリポジトリレイヤーを使用すると、現在のローカルサービスの上にステージング固有の要件を追加して、データハンドラーのニーズを満たすことができます。

    すべてのメソッド実装が追加のステージング情報を必要とするわけではありません。 たとえば、ブックマークエントリとブックマークエントリステージングモデルの削除は機能的に同じであるため、ステージングモデルリポジトリのメソッドは次のようになります。

        @Override public void deleteStagedModels（PortletDataContext portletDataContext） はPortalExceptionをスローします{
   
          _bookmarksEntryLocalService.deleteEntries(
              portletDataContext.getScopeGroupId(),
              BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);
        }

    ステージングモデルを削除するために追加する必要はないため、ステージングモデルリポジトリは、ローカルサービスの `deleteEntries（...）` メソッドを追加の変更なしで呼び出します。

    `StagedModelRepository` 実装を完了して、データハンドラーで使用できるようにします。

驚くばかり\！ アプリにStaged Model Repositoryフレームワークを実装しました\！ あなたが実装プロセスの後に、このフレームワークを活用に興味があるなら、見る [モデルリポジトリFrameworkの段階的な使い方](/docs/7-1/tutorials/-/knowledge_base/t/using-the-staged-model-repository-framework) チュートリアルを。
