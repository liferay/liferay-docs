---
header-id: implementing-any-other-business-logic
---

# その他のビジネスロジックの実装

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

このセクションの以前のローカルサービスチュートリアルは、CRUDの方法に焦点を当てる：方法その **C**reate（ADD） **R**EAD（GET）、 **U**pdate、及び **D**eleteエンティティ。 ただし、ビジネスロジックを提供するメソッドも必要になる場合があります。

例えば、Liferayのブックマークアプリケーションユーザー *オープン* それらをクリックしてブックマーク（URLへの移動）。 [`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/7.1.0-a1/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)の `openEntry` メソッドは、この機能をサポートしています。

    public BookmarksEntry openEntry(long userId, BookmarksEntry entry) {
        entry.setVisits(entry.getVisits() + 1);
    
        bookmarksEntryPersistence.update(entry);
    
        assetEntryLocalService.incrementViewCounter(
            userId, BookmarksEntry.class.getName(), entry.getEntryId(), 1);
    
        return entry;
    }

`openEntry` メソッドは、 `BookmarksEntry`のURLへのアクセス数を追跡および保持し、アセットとして `BookmarksEntry` ビュー数を増やし、 `BookmarksEntry`を返します。 このメソッドは、CRUDメソッドを補完する必要なビジネスロジックを実装します。

*便利な方法* もアプリに適している場合があります。通常、これらの特性があるため、使いやすくなっています。

  - 短いパラメーターリスト
  - 直感的な名前

短いパラメーターリストは満たすのが簡単で、直感的な名前を持つメソッドはJavadocで見つけるのが簡単です。

たとえば、ブックマークアプリケーションを使用すると、ユーザーはブックマークを別のフォルダーに移動できます。 ブックマークの移動は、サービスの `updateEntry（...）` メソッドを使用して行うことができますが、操作が必要なのはブックマークエントリと移動先のフォルダだけなので、その長いパラメータリストは過剰です。 次の `update *` メソッド呼び出しを便利なメソッド呼び出しと比較してください。

**更新方法**：

    bookmarksEntryLocalService.updateEntry(userId, entryId, groupId, folderId, name, url, description, serviceContext);

**便利な方法**：

    bookmarksEntryLocalService.moveEntry(entryId, folderId);

通常、便利なメソッドは簡単に作成できます。 [`moveEntry` メソッド](https://github.com/liferay/liferay-portal/blob/7.1.0-a1/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)次のとおりです。

    public BookmarksEntry moveEntry(long entryId, long parentFolderId)
        throws PortalException {
    
        BookmarksEntry entry = getBookmarksEntry(entryId);
    
        entry.setFolderId(parentFolderId);
        entry.setTreePath(entry.buildTreePath());
    
        bookmarksEntryPersistence.update(entry);
    
        return entry;
    }

`moveEntry` メソッドは、IDで `BookmarksEntry` エンティティを取得し、新しい親フォルダーを割り当て、ツリーパスを更新し、すべてのエンティティの変更を保持し、エンティティを返します。 このような便利なメソッドは、エンティティの属性のサブセットの更新を容易にします。

カスタムビジネスメソッドを実装した後、 [はService Builder](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder) を実行して、それらをインターフェイスに伝達します。

ローカルサービスでは、アプリケーションに合ったビジネスロジックメソッドを実装できます。

**関連トピック**

[ローカルサービスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-local-services)

[ローカルサービスの呼び出し](/docs/7-1/tutorials/-/knowledge_base/t/invoking-local-services)

[Service Builderコードからのサービスの呼び出し](/docs/7-1/tutorials/-/knowledge_base/t/invoking-services-from-service-builder-code)
