---
header-id: using-the-staged-model-repository-framework
---

# 段階的モデルリポジトリフレームワークの使用

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

あなたがしたらあなたのアプリでモデルリポジトリフレームワークを段階的に活用することは容易である [作成したモデルリポジトリ実装クラス上演](/docs/7-1/tutorials/-/knowledge_base/t/implementing-the-staged-model-repository-framework)。

簡単な例を使用して、ステージングモデルデータハンドラーで `StagedModelRepository` インターフェイスを活用する方法を示します。 コードスニペットは、Liferayのブックマークアプリとブックマークエントリに由来します。

1.  `BookmarksEntry` エンティティで使用可能な `StagedModelRepository` オブジェクトを作成するゲッターおよびセッターメソッドを作成します。
   
        @Override
        protected StagedModelRepository<BookmarksEntry> getStagedModelRepository() {
            return _stagedModelRepository;
        }
       
        @Reference(
            target = "(model.class.name=com.liferay.bookmarks.model.BookmarksEntry)",
            unbind = "-"
        )
        protected void setStagedModelRepository(
            StagedModelRepository<BookmarksEntry> stagedModelRepository) {
       
            _stagedModelRepository = stagedModelRepository;
        }
       
        private StagedModelRepository<BookmarksEntry> _stagedModelRepository;

    これは、ステージングモデルデータハンドラーが `BookmarksEntry` CRUD操作にアクセスするために使用できる `_stagedModelRepository` オブジェクトをインスタンス化します。 セッターメソッドの `@Reference` アノテーションに注目してください。 これにより、 `BookmarksEntryStagedModelRepository` のコンポーネントサービスが `_stagedModelRepository` オブジェクトに注入されます。 コンポーネントサービスは、ステージングモデルリポジトリに `@Component` アノテーションを設定するときに、「 [ステージングモデルリポジトリフレームワーク](/docs/7-1/tutorials/-/knowledge_base/t/implementing-the-staged-model-repository-framework) 実装」チュートリアルで作成されました。

2.  `_stagedModelRepository` オブジェクトを呼び出して、専用のステージングロジックを活用します。 `_stagedModelRepository` オブジェクトを介してCRUD操作にアクセスできるようになったので、ローカルサービスで多数のパラメーターと追加機能を提供するという頭痛をスキップして、ブックマークエントリの追加などの簡単な操作を実行できます。 たとえば、古い方法は次のとおりです。
   
        serviceContext.setUuid(entry.getUuid());
       
        newEntry = _bookmarksEntryLocalService.addEntry(
            userId, portletDataContext.getScopeGroupId(), folderId, entry.getName(), entry.getUrl(), entry.getDescription(), serviceContext);

    エントリの段階的なモデルリポジトリにアクセスできるようになり、データハンドラが使用できるエントリを更新すると次のようになります。
   
        newEntry = _stagedModelRepository.updateStagedModel(portletDataContext, importedEntry);

    ステージングモデルリポジトリはデータハンドラーからこれらの要件を抽象化するため、ステージングモデルリポジトリを活用する場合、ローカルサービスメソッドに必要な多数のパラメーターとUUIDセッターは必要ありません。 `_bookmarksEntryLocalService.addEntry（...）` メソッドは、 `BookmarksEntryStagedModelRepository` クラスから呼び出されます。

すばらしいです\！ データハンドラーからステージングモデルリポジトリを正常に活用しました\！
