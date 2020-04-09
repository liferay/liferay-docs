---
header-id: implementing-update-and-delete-methods
---

# 更新および削除メソッドの実装

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

サービスエンティティを作成するための [`add *` メソッド](/docs/7-1/tutorials/-/knowledge_base/t/implementing-an-add-method) を実装したら、それらを更新および削除するための [`update *`](#implementing-an-update-method) および [`delete *`](#implementing-a-delete-method) メソッドを作成する必要があります。 実装は簡単です。 それらと `add *` メソッドの主な違いは、更新または削除するエンティティを知る必要があることです。

## 更新メソッドの実装

ローカルサービスの `update *` メソッドは、エンティティ属性値を設定するためのパラメーターがあるため、 [`add *` メソッド](/docs/7-1/tutorials/-/knowledge_base/t/implementing-an-add-method) 最も似ています。 この方法で `アップデート*` メソッドを作成します。

1.  エンティティを更新するためのパラメーターを持つ `update *` メソッドを宣言します。

2.  パラメーターを検証します。

3.  必要に応じて、エンティティインスタンスを取得します。

4.  エンティティの属性を更新します。

5.  更新されたエンティティを保持します。

6.  Service Builderを実行します。

[`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)の `updateEntry` メソッドからの次のコードスニペットは、調べるのに役立ちます。

    public BookmarksEntry updateEntry(
            long userId, long entryId, long groupId, long folderId, String name,
            String url, String description, ServiceContext serviceContext)
        throws PortalException {
    
        // Entry
    
        BookmarksEntry entry = bookmarksEntryPersistence.findByPrimaryKey(
            entryId);
    
        if (Validator.isNull(name)) {
            name = url;
        }
    
        validate(url);
    
        entry.setFolderId(folderId);
        entry.setTreePath(entry.buildTreePath());
        entry.setName(name);
        entry.setUrl(url);
        entry.setDescription(description);
        //...
    
        bookmarksEntryPersistence.update(entry);
    
        // Integrate with more Liferay services here ...
    
        return entry;
    }

このメソッドには、優れた `アップデート*` メソッドのすべての特徴があります。

  - エンティティインスタンスを検索するためのパラメーター
  - エンティティ属性を更新するためのパラメーター
  - パラメータ検証
  - エンティティ属性の更新
  - エンティティの永続性
  - エンティティインスタンスを返します

手順に従って独自の `update *` メソッドを作成する際には、上記のメソッド例を参照してください。

### 手順1：エンティティを更新するためのパラメーターを使用して更新メソッドを宣言する

アプリケーションのエンティティを更新するためのパブリックメソッドを作成します。

``` 
public [ENTITY] update[ENTITY](...)
    throws PortalException {

} 
```

`[ENTITY]` をエンティティの名前またはニックネームに置き換えます。 更新するエンティティの属性を満たすパラメーターリストを作成します。 エンティティインスタンスを取得するためのエンティティインスタンスパラメータまたはIDパラメータを含めます。

たとえば、 `BookmarksEntryLocalServiceImpl`の `updateEntry` メソッドシグネチャには、 `BookmarksEntry` エンティティインスタンスを取得するためのIDパラメーター（`entryId`）があります。 また、パラメータ有し `フォルダID`、 `名`、 `URL`、及び `の説明` 更新する `BookmarksEntry`のそれぞれの属性を。

``` 
public BookmarksEntry updateEntry(
        long userId, long entryId, long groupId, long folderId, String name,
        String url, String description, ServiceContext serviceContext)
    throws PortalException {...} 
```

ユーザーID、グループID、およびサービスコンテキストパラメーターは、Liferayのサービスと統合するのに便利です。 それについては後で詳しく説明します。

### ステップ2：パラメーターを検証する

[`add *` method](/docs/7-1/tutorials/-/knowledge_base/t/implementing-an-add-method) パラメーターの検証と同様に、 `update *` パラメーターを検証します。 `add *` および `update *` メソッドは、同じ検証コードを使用できる場合があります。 無効なパラメーターに対しては、 [`PortalException`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/exception/PortalException.html) または `PortalException` 拡張子をスローします。

### ステップ3：エンティティインスタンスを取得する

エンティティインスタンスを渡す場合は、直接更新できます。 それ以外の場合は、エンティティID（主キー）を渡します。 `* Persistence` クラスService Builderは、 `* BaseLocalServiceImpl` クラスに注入します。IDでインスタンスを取得する `findByPrimaryKey（long）` メソッドがあります。 たとえば、 [`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java) は、プライマリキー `entryId`一致する `BookmarksEntry` を取得します。

    BookmarksEntry entry = bookmarksEntryPersistence.findByPrimaryKey(
        entryId);

`* Persistence` クラスの `findByPrimaryKey（long id）` メソッドを呼び出して、主キーパラメータに一致するエンティティインスタンスを取得します。

``` 
 [ENTITY] entity = [ENTITY]Persistence.findByPrimaryKey(id);
```

エンティティの属性を更新します。

### ステップ4：エンティティの属性を更新する

エンティティのセッターメソッドを呼び出して、属性値を置き換えます。

### ステップ5：更新されたエンティティインスタンスを永続化して返す

更新されたエンティティをデータベースに永続化し、インスタンスを呼び出し元に返します。

    [ENTITY]Persistence.update(entity);
    
    ...
    
    return entity;

### ステップ6：Service Builderを実行する

最後に、Service Builderを実行して、新しいサービスメソッドを `* LocalService` インターフェイスに伝達します。

エンティティを更新するサービスメソッドを作成しました。 それが簡単だと思ったら、 `delete *` メソッドを実装するのがさらに簡単です。

## 削除メソッドの実装

エンティティの `* Persistence` クラスの `remove` メソッドは、データベースからエンティティインスタンスを削除します。 ローカルサービスの `delete *` メソッドで使用します。 `delete *` メソッドは次のようになります。

    public [ENTITY] delete[ENTITY](ENTITY entity) throws PortalException
    {
        [ENTITY]Persistence.remove(entity);
    
        // Clean up related to additional Liferay services goes here ... 
    
        return entity;
    }

`[ENTITY]` をエンティティの名前またはニックネームに置き換えてください。

たとえば、 [`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)の `deleteEntry` メソッドからの言い換えコードは次のとおりです。

    public BookmarksEntry deleteEntry(BookmarksEntry entry)
        throws PortalException {
    
        bookmarksEntryPersistence.remove(entry);
    
        // Clean up related to additional Liferay services goes here ...  
    
        return entry;
    }

`delete *` メソッドを実装したら、Service Builderを実行して、新しいサービスメソッドを `* LocalService` インターフェイスに伝達します。

## 関連トピック

[addメソッドの実装](/docs/7-1/tutorials/-/knowledge_base/t/implementing-an-add-method)

getterおよびcounterメソッドの実装

Liferayサービスとの統合
