---
header-id: implementing-an-add-method
---

# Addメソッドの実装

[TOC levels=1-4]

あなたの `* LocalServiceImpl` 、エンティティあなたの上で動作 `service.xmlに` 定義。 したがって、実装する最初の方法は、エンティティを作成する方法です。 Liferayの規則は、これを実現することである `追加*` 後の部分方法、 `アドオン` エンティティ名（またはその短縮版）です。 `add *` メソッドを実装する手順は次のとおりです。

1.  エンティティを作成するためのパラメーターを持つ `add *` メソッドを宣言します。

2.  パラメーターを検証します。

3.  主キーを生成します。

4.  エンティティインスタンスを作成します。

5.  エンティティの属性を設定します。

6.  エンティティを永続化します。

7.  エンティティインスタンスを返します。

このチュートリアルでは、例として [`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java) からBookmarksアプリケーションの `addEntry` メソッドを参照します。 簡単にするために、アセット、ソーシャルブックマークなど、Liferayサービスと統合するコードを除外しました。

以下に、ブックマークアプリケーションの `addEntry` メソッドを示します。

    public BookmarksEntry addEntry(
            long userId, long groupId, long folderId, String name, String url,
            String description, ServiceContext serviceContext)
        throws PortalException {
    
        // Entry
    
        User user = userLocalService.getUser(userId);
    
        if (Validator.isNull(name)) {
            name = url;
        }
    
        validate(url);
    
        long entryId = counterLocalService.increment();
    
        BookmarksEntry entry = bookmarksEntryPersistence.create(entryId);
    
        entry.setUuid(serviceContext.getUuid());
        entry.setGroupId(groupId);
        entry.setCompanyId(user.getCompanyId());
        entry.setUserId(user.getUserId());
        entry.setUserName(user.getFullName());
        entry.setFolderId(folderId);
        entry.setTreePath(entry.buildTreePath());
        entry.setName(name);
        entry.setUrl(url);
        entry.setDescription(description);
        // More assignments ... 
    
        bookmarksEntryPersistence.update(entry);
    
        // Integrate with more Liferay services here ...
    
        return entry;
    }

このメソッドは、パラメーターを使用して `BookmarksEntry`を作成します。 パラメータを検証し、生成されたエントリID（プライマリキー）を使用してエントリを作成し、エントリを設定し、エントリを永続化して返します。 独自の `add *` メソッドを作成するときに、このメソッドを参照できます。

## ステップ1：エンティティを作成するためのパラメーターを使用してaddメソッドを宣言します

アプリケーションのエンティティに</em> 追加（作成）する *パブリックメソッドを作成します。 作成したエンティティを返すパブリックメソッドにします。</p>

``` 
public [ENTITY] add[ENTITY](...) {

} 
```

たとえば、 `addEntry` メソッドシグネチャは次のとおりです。

    public BookmarksEntry addEntry(
            long userId, long groupId, long folderId, String name, String url,
            String description, ServiceContext serviceContext)
        throws PortalException {
            ...
    }

このメソッドは、 `BookmarksEntry`を作成および設定するために必要なすべてのパラメーターを指定します。 パラメーターが無効であるか、処理例外が発生した場合、 `PortalException` がスローされます（これについては後のステップで説明します）。

addメソッドは、 `service.xml` ファイルで指定されたエンティティの属性を満たすパラメーターを指定する必要があります。 他の関連エンティティの主キーを必ず考慮してください。 たとえば、上記の `addEntry` メソッドには、新しい `BookmarksEntry` を `BookmarksFolder`に関連付けるパラメーター `long folderId` が含まれています。

## ステップ2：パラメーターを検証する

必要に応じてパラメーターを検証します。 パラメーターが空または `null`でないこと、またはパラメーター値が有効な範囲内にあることを確認する必要がある場合があります。 無効なパラメーターに対しては、 [`PortalException`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/exception/PortalException.html) または `PortalException` 拡張子をスローします。

たとえば、 `addEntry` メソッドは、次の `validate` メソッドを呼び出して、URLパラメーターが `null`かどうかを確認します。

    protected void validate(String url) throws PortalException {
        if (!Validator.isUrl(url)) {
            throw new EntryURLException();
        }
    }

次に、作成しているエンティティインスタンスの主キーを生成します。

## ステップ3：主キーを生成する

すべてのエンティティインスタンスには一意のプライマリキーが必要です。 Liferayの [`CounterLocalService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/counter/kernel/service/CounterLocalService.html) は、エンティティごとにそれらを生成します。 すべての `* BaseLocalServiceImpl` は、エンティティの `CounterLocalService` オブジェクトを参照する `counterLocalService` フィールドがあります。 カウンターインスタンスの `increment` メソッドを呼び出して、エンティティインスタンスのプライマリキーを生成します。

    long id = counterLocalService.increment();

これで、エンティティインスタンスの一意のIDが作成されました。

## ステップ4：エンティティインスタンスを作成する

エンティティに関連付けられた `* Peristence` インスタンスには、指定されたIDでエンティティインスタンスを作成する `create（long id）` メソッドがあります。 すべての `* BaseLocalServiceImpl` は、エンティティの `* Persistence` オブジェクトを参照する `* Persistence` フィールドがあります。 たとえば、 [`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java) は [`BookmarksEntryLocalServiceBaseImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/base/BookmarksEntryLocalServiceBaseImpl.java)のフィールド `bookmarksEntryPersistence`にアクセスできます。これは、 `BookmarksEntryPersistence` インスタンスへの参照です。

    @BeanReference(type = BookmarksEntryPersistence.class)
    protected BookmarksEntryPersistence bookmarksEntryPersistence;

`BookmarksEntryLocalServiceImpl`の `addEntry` メソッドは、この呼び出しを使用して `BookmarksEntry` インスタンスを作成します。

    BookmarksEntry entry = bookmarksEntryPersistence.create(entryId);

エンティティのインスタンスを作成するには、エンティティに関連付けられた `* Persistence` フィールドで `create` メソッドを呼び出し、前の手順で生成したエンティティのプライマリキーを必ず渡します。

``` 
 [ENTITY_NAME] entity = [ENTITY_NAME]Persistence.create(id);
```

新しいエンティティインスタンスにデータを入力します。

## ステップ5：エンティティの属性を設定します

`add *` メソッドのパラメーター値とエンティティのセッターメソッドを使用して、エンティティの属性を設定します。 たとえば、 `BookmarksEntry` 属性の割り当ては次のとおりです。

    entry.setUuid(serviceContext.getUuid());
    entry.setGroupId(groupId);
    entry.setCompanyId(user.getCompanyId());
    entry.setUserId(user.getUserId());
    entry.setUserName(user.getFullName());
    entry.setFolderId(folderId);
    entry.setTreePath(entry.buildTreePath());
    entry.setName(name);
    entry.setUrl(url);
    entry.setDescription(description);

通常、 `ServiceContext` はエンティティのUUIDを伝達するために使用され、 `User` は会社に関連付けられていることに注意してください。

## ステップ6：エンティティを永続化する

エンティティを保存します。 エンティティオブジェクトを渡して、 `* Persistence` フィールドの `update` メソッドを呼び出します。 たとえば、新しい `BookmarksEntry` 永続化方法は次のとおりです。

    bookmarksEntryPersistence.update(entry);

エンティティはアプリケーションに対して永続化されます。

## ステップ7：エンティティを返す

最後に、呼び出し側が使用できるように、作成したエンティティを返します。

[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder) を実行して、新しいサービスメソッドを `* LocalService` インターフェイスに伝達します。

ローカルサービスの `add *` メソッドを実装して、アプリケーションのエンティティを作成および永続化しました。
