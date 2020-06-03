---
header-id: implementing-methods-to-get-and-count-entities
---

# エンティティを取得およびカウントするメソッドの実装

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Service Builderは、 `service.xml` ファイルの [ファインダー](/docs/7-1/tutorials/-/knowledge_base/t/defining-service-entity-finder-methods)基づいて、 `個のfindBy *` メソッドと `個のcountBy *` メソッドを [`* Persistence` クラス](/docs/7-1/tutorials/-/knowledge_base/t/understanding-the-code-generated-by-service-builder) に生成します。 ローカルサービスのファインダーメソッドを活用して、エンティティを取得およびカウントできます。

  - [Getters](#getter-methods)： `get *` メソッドは、条件に一致するエンティティインスタンスを返します。
  - [カウンター](#counter-methods)： `get * Count` メソッドは、条件に一致するインスタンスの数を返します

基準に一致するエンティティの取得から始めます。

## ゲッターメソッド

`findByPrimaryKey` メソッドと `findBy *` メソッドは、条件に基づいてエンティティインスタンスを検索して返します。 ローカルサービスの実装は、必要なものを取得するファインダーメソッドへの呼び出しのみをラップする必要があります。

ID（主キー）に基づいてエンティティを取得するメソッドを作成する方法は次のとおりです。

1.  この形式を使用してメソッドを作成します。
   
        public [ENTITY] get[ENTITY_NAME](long id) {
            return [ENTITY]Persistence.findByPrimaryKey(id);
        }

2.  `[ENTITY]` および `[ENTITY_NAME]` をそれぞれのエンティティタイプとエンティティ名（またはニックネーム）に置き換え` 。</p></li>
<li><p spaces-before="0"> Service Builderを実行して、メソッドをローカルサービスインターフェイスに伝達します。</p></li>
</ol>

<p spaces-before="0">基準に基づいてエンティティを取得する方法は次のとおりです。</p>

<ol start="1">
<li><p spaces-before="0"> エンティティインスタンスを見つけるための基準を特定します。</p></li>
<li><p spaces-before="0"> 基準に <a href="/docs/7-1/tutorials/-/knowledge_base/t/defining-service-entity-finder-methods"><code>finder` 要素</a> がない場合は、その要素を作成してService Builderを実行します。

3.  呼び出す [`* Persistence` class](/docs/7-1/tutorials/-/knowledge_base/t/understanding-the-code-generated-by-service-builder) `findBy *` メソッドを決定します。 `ファインダー` 要素の列によっては、Service Builderがメソッドをオーバーロードしてこれらのパラメーターを含める場合があります。

      - `int start` および `int end` の範囲のエンティティを指定するパラメーター。
      - `com.liferay.portal.kernel.util.OrderByComparator orderByComparator` 一致するエンティティを配置するためのパラメーター。

4.  `get *` メソッドシグネチャを指定し、 `* Persistence` クラス `findBy *` メソッドパラメーターを必ず満たしてください。 次のメソッド形式を使用します。

        public List <[ENTITY]> get[DESCRIBE_THE_ENTITIES](...){

        }

    `[ENTITY]` をエンティティタイプに置き換え` 。 <code>[DESCRIBE_THE_ENTITIES]` を、取得するエンティティのわかりやすい名前に置き換えます。

5.  `* Persistence` class `findBy *` メソッドを呼び出して、一致するエンティティのリストを返します。

6.  Service Builderを実行します。

たとえば、メソッド `getGroupEntries` from [`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/7.1.0-a1/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java) は、 `Group` プライマリキーに関連付けられた `BookmarksEntry`の範囲を返します。

    public List<BookmarksEntry> getGroupEntries(
        long groupId, int start, int end) {
    
        return bookmarksEntryPersistence.findByG_S(
            groupId, WorkflowConstants.STATUS_APPROVED, start, end,
            new EntryModifiedDateComparator());
    }

ワークフローに関連付けられている `BookmarksEntry`のうち、このメソッドは承認されたもの（`WorkflowConstants.STATUS_APPROVED`）のみに一致します。 新しい `EntryModifiedDateComparator` を使用して、一致する `BookmarksEntry`を変更日順に並べます。

これで、ファインダーメソッドを活用してエンティティを取得する方法がわかりました。 エンティティをカウントするメソッドが次にあります。

## カウンターメソッド

エンティティのカウントは、取得するのと同じくらい簡単です。 `* Persistence` class `countBy *` メソッドがすべての作業を行います。 Service Builderは、各ファインダーとその列に基づいて `countBy *` メソッドを生成します。

1.  カウントしているエンティティインスタンスの基準を特定し、基準を満たす [`* Persistence` class](/docs/7-1/tutorials/-/knowledge_base/t/understanding-the-code-generated-by-service-builder) `countBy *` メソッドを決定します。

2.  この形式に従って、 `get * Count` メソッドシグネチャを作成します。
   
        public int get[DESCRIBE_THE_ENTITIES]Count(...) {
       
        }

    `[DESCRIBE_THE_ENTITIES]` を、カウントするエンティティのわかりやすい名前に置き換えます。

3.  `* Persistence` クラスの `countBy` メソッドを呼び出し、値を返します。 たとえば、メソッド `getEntriesCount` from [`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/7.1.0-a1/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java) は、ワークフローステータスが `ステータス` で、グループ（ `一致するgroupId`）およびフォルダー（ `一致する）に関連付けられた <code>BookmarksEntry`の数を返します。 folderId</code>）。
   
        public int getEntriesCount(long groupId, long folderId, int status) {
            return bookmarksEntryPersistence.countByG_F_S(
                groupId, folderId, status);
        }

これで、ローカルサービスは条件に一致するエンティティを取得し、素早いエンティティカウントを返すことができます。

## 関連トピック

[ローカルサービスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-local-services)

[addメソッドの実装](/docs/7-1/tutorials/-/knowledge_base/t/implementing-an-add-method)

[サービスエンティティファインダーメソッドの定義](/docs/7-1/tutorials/-/knowledge_base/t/defining-service-entity-finder-methods)

[Service Builderによって生成されたコードを理解する](/docs/7-1/tutorials/-/knowledge_base/t/understanding-the-code-generated-by-service-builder)
