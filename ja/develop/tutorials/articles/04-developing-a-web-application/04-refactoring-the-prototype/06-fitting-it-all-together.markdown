---
header-id: fitting-it-all-together
---

# すべてを適合させる

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">プロトタイプのリファクタリング</p><p>手順6/6</p>
</div>

バックエンドからディスプレイまでの、完全なデータ駆動型アプリケーションを作成しました。 これまでに行ったことが、どのように結び付いているかを確認してみましょう。

## エントリー

最初に、Service Builderの設定ファイルで、`service.xml`モデルを定義しました。 これに関する主な部分は、`Entry`オブジェクトです。

    <entity local-service="true" name="Entry" uuid="true">
    
        <!-- PK fields -->
    
        <column name="entryId" primary="true" type="long" />
    
        <!-- Group instance -->
    
        <column name="groupId" type="long" />
    
        <!-- Audit fields -->
    
        <column name="companyId" type="long" />
        <column name="userId" type="long" />
        <column name="userName" type="String" />
        <column name="createDate" type="Date" />
        <column name="modifiedDate" type="Date" />
        <column name="name" type="String" />
        <column name="email" type="String" />
        <column name="message" type="String" />
        <column name="guestbookId" type="long" />
    
        <finder name="G_G" return-type="Collection">
            <finder-column name="groupId" />
            <finder-column name="guestbookId" />
        </finder>
    </entity>

次に、`EntryLocalServiceImpl`にエントリを取得および保存する方法を定義したサービス実装を作成しました。 定義したすべてのフィールドは、`addEntry`メソッドで説明されました。

    public Entry addEntry(long userId, long guestbookId, String name, String email,
            String message, ServiceContext serviceContext)
            throws PortalException {
    
            long groupId = serviceContext.getScopeGroupId();
    
            User user = userLocalService.getUserById(userId);
    
            Date now = new Date();
    
            validate(name, email, message);
    
            long entryId = counterLocalService.increment();
    
            Entry entry = entryPersistence.create(entryId);
    
            entry.setUuid(serviceContext.getUuid());
            entry.setUserId(userId);
            entry.setGroupId(groupId);
            entry.setCompanyId(user.getCompanyId());
            entry.setUserName(user.getFullName());
            entry.setCreateDate(serviceContext.getCreateDate(now));
            entry.setModifiedDate(serviceContext.getModifiedDate(now));
            entry.setExpandoBridgeAttributes(serviceContext);
            entry.setGuestbookId(guestbookId);
            entry.setName(name);
            entry.setEmail(email);
            entry.setMessage(message);
    
            entryPersistence.update(entry);
    
            return entry;
    }

Service Builderで記述したすべてのフィールド（ `uuid`などを含む）がここにあることを確認してください。

また、エントリを取得する方法も追加しました。

``` 
    public List<Entry> getEntries(long groupId, long guestbookId) {
        return entryPersistence.findByG_G(groupId, guestbookId);
    }

    public List<Entry> getEntries(
        long groupId, long guestbookId, int start, int end, OrderByComparator<Entry> obc) {

        return entryPersistence.findByG_G(groupId, guestbookId, start, end, obc);
    }

    public List<Entry> getEntries(long groupId, long guestbookId, int start, int end)
        throws SystemException {

        return entryPersistence.findByG_G(groupId, guestbookId, start, end);
    }
```

`service.xml`で、`groupId`および`guestbookId`を2つのファインダーフィールドとして定義しましたが、これらの方法では、永続レイヤーに生成されたメソッドを呼び出しました。

これらすべてを実装した後、Service Builderは実装をインターフェイスに伝播し、それらを呼び出すことができるようにしました。 次に、ポートレットクラスで、Service Builderが生成したサービスクラスへの参照を作成し、サービスにアクセスしてエントリを追加できるようそれらの参照を使用しました。

    _entryLocalService.addEntry( serviceContext.getUserId(), guestbookId, 
        userName, email,message, serviceContext);

最後に、これらすべてをユーザーインターフェイスにまとめ、ユーザーが必要な情報を入力し、入力したデータを表示できるようにしました。

アプリケーションを構築し、すべてがどのように機能するかを明確に把握できたので、今度はテストしてみます。

## アプリケーションのデプロイとテスト

1.  `guestbook-api`モジュールをサーバーにドラッグアンドドロップします。

2.  `guestbook-service`モジュールをサーバーにドラッグアンドドロップします。

3.  コンソールからSTARTEDメッセージを探します。

4.  ブラウザの`localhost:8080`にある@product@インスタンスに移動して、アップデートされたアプリケーションをテストします。

5.  *Add Entry*をクリックします。

6.  *Name*、*Message*および*Email Address*を入力します。

7.  *Submit*をクリックします。

8.  エントリが表示されることを確認します。

![図1：最初のゲストブックとエントリが表示されます。  ](../../../images/guestbook-entry-test.png)

##

動作するWebアプリケーションを作成し、@<product@>にデプロイしました。 ただし、以前にWebアプリケーションを作成したことがある場合は、いくつかの重要な機能が欠けていることがわかります。セキュリティ、フロントエンドの検証、管理者がポートレットごとに複数のゲストブックを作成するためのインターフェイスなどです。 次のセクションでは、これらの機能の追加を開始します。
