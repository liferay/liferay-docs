---
header-id: implementing-a-custom-finder-method-using-dynamic-query
---

# 動的クエリを使用したカスタムFinderメソッドの実装

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

あなたがしたら [カスタムfinderメソッド定義された](/docs/7-1/tutorials/-/knowledge_base/t/defining-a-custom-finder-method)、あなたはそれであなたのクエリを実行するために、動的クエリAPIを使用することができます。 finderメソッドで行う必要があることは次のとおりです。

1.  [Hibernateセッションを開く](#using-a-hibernate-session)

2.  [これらのHibernate機能を使用して動的クエリを作成します](#creating-dynamic-queries)：

      - *制限*：SQLクエリの `where` 句と同様に、制限は基準に基づいて結果を制限します。
      - *プロジェクション*：クエリが返す結果の種類を変更します。
      - *注文*：結果を整理します。

3.  [動的クエリを実行して結果を返す](#executing-the-dynamic-query)

独自のファインダメソッドで動的クエリを実装する前に、例を調べると役立ちます。 次のメソッド例では、複数の動的クエリとすべてのHibernate機能を使用します。 独自のファインダメソッドを実装する手順は、例に従ってください。

## Finderメソッドの例：findByGuestbookNameEntryName

Guestbookアプリケーションのこのファインダーメソッドは、特定の名前を持ち、特定の名前のGuestbookにも属するGuestbookエントリのリストを取得します。

    public List<Entry> findByEntryNameGuestbookName(String entryName, String guestbookName) {
    
        Session session = null;
        try {
            session = openSession();
    
            ClassLoader classLoader = getClass().getClassLoader();
    
            DynamicQuery guestbookQuery = DynamicQueryFactoryUtil.forClass(Guestbook.class, classLoader)
                .add(RestrictionsFactoryUtil.eq("name", guestbookName))
                .setProjection(ProjectionFactoryUtil.property("guestbookId"));
    
            Order order = OrderFactoryUtil.desc("modifiedDate");
    
            DynamicQuery entryQuery = DynamicQueryFactoryUtil.forClass(Entry.class, classLoader))
                .add(RestrictionsFactoryUtil.eq("name", entryName))
                .add(PropertyFactoryUtil.forName("guestbookId").in(guestbookQuery))
                .addOrder(order);
    
            List<Entry> entries = _entryLocalService.dynamicQuery(entryQuery);
    
            return entries;
        }
        catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                se.printStackTrace();
            }
        }
        finally {
            closeSession(session);
        }
    }

このメソッドは最初にHibernateセッションを開きます。 セッションは `try` ブロックで開かれている間、動的クエリを作成して実行し、すべてがうまくいけば結果（ゲストブック `エントリ` オブジェクトのリスト）を返します。

finderメソッドには、2つの異なる動的クエリがあります。

1.  最初のクエリは、finderメソッドの `guestbookName` パラメーターに一致するゲストブック名に対応するゲストブックIDのリストを取得します。

2.  2番目のクエリは、 `entryName` パラメータに一致し、最初のクエリで返されたリストに属する `guestbookId` 外部キーを持つエントリ名を持つゲストブックエントリのリストを取得します。

最初のクエリは次のとおりです。

    DynamicQuery guestbookQuery = DynamicQueryFactoryUtil.forClass(Guestbook.class, classLoader))
        .add(RestrictionsFactoryUtil.eq("name", guestbookName))
        .setProjection(ProjectionFactoryUtil.property("guestbookId"));

デフォルトでは、 `DynamicQueryFactoryUtil.forClass（Guestbook.class、classLoader））` は、すべてのゲストブックエンティティのリストを取得するクエリを返します。 `.add（RestrictionsFactoryUtil.eq（ "name"、guestbookName））` 制限を追加すると、結果は、ゲストブック名が `guestbookName` パラメーターと一致するゲストブックのみに制限されます。 `.setProjection（ProjectionFactoryUtil.property（ "guestbookId"））` プロジェクションは、結果セットをゲストブックエントリのリストからゲストブックIDのリストに変更します。 ゲストブックIDは完全なゲストブックエンティティよりもはるかに安価に取得でき、エントリクエリにはゲストブックIDのみが必要なため、これは便利です。

次に注文が表示されます：

    Order order = OrderFactoryUtil.desc("modifiedDate");

これにより、クエリエンティティの `modifiedDate` 属性の降順で結果リストが配置されます。 したがって、最後に変更されたエンティティ（この例ではゲストブックエントリ）が最初に表示され、最後に変更されたエンティティが最後に表示されます。

2番目のクエリは次のとおりです。

    DynamicQuery entryQuery = DynamicQueryFactoryUtil.forClass(Entry.class, classLoader))
        .add(RestrictionsFactoryUtil.eq("name", entryName))
        .add(PropertyFactoryUtil.forName("guestbookId").in(guestbookQuery))
        .addOrder(order);

デフォルトでは、 `DynamicQueryFactoryUtil.forClass（Entry.class、classLoader））` はすべてのゲストブックエントリエンティティのリストを返します。 `.add（RestrictionsFactoryUtil.eq（ "name"、entryName））` 制限は、検索メソッドの `entryName` パラメーターと一致する名前を持つゲストブックエントリのみに結果を制限します。 [`PropertyFactoryUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/PropertyFactoryUtil.html) は、メソッド `forName（String propertyName）` が指定されたプロパティを返すLiferayユーティリティクラスです。 このプロパティは、別のLiferay動的クエリに渡すことができます。 これは、この例の次の行で発生することです。

    .add(PropertyFactoryUtil.forName("guestbookId").in(guestbookQuery))

ここで、コードは、 `entityQuery` エントリエンティティのゲストブックID（外部キー）が `guestbookQuery`によって返されるゲストブックIDのリストに属することを確認します。 1つのクエリのエンティティプロパティが別のクエリの結果リストに属している必要があることを宣言することは、SQL結合と同様に、動的クエリAPIを使用して複雑なクエリを作成する方法です。

最後に、前に定義された順序は、 `findByEntryNameGuestbookName` finderメソッドによって返されるエントリに適用されます。

    .addOrder(order);

これにより、 `modifiedDate` 属性でゲストブックエンティティのリストが最新から最古の順に並べられます。

最後に、 `EntryLocalService` インスタンスで動的クエリが呼び出されます。 `Entry` オブジェクトのリストが返され、finderメソッドによって返されます。

    List<Entry> entries = _entryLocalService.dynamicQuery(entryQuery);
    
    return entries;

動的クエリを使用するために、finderメソッドを実装します。 Hibernateセッションを開いて管理することから始めます。

## Hibernateセッションを使用する

`* FinderImpl` クラスにカスタムfinderメソッドを実装する最初のステップは、新しいHibernateセッションを開くことです。 `* FinderImpl` クラスは `BasePersistenceImpl<Entity>`拡張し、 `BasePersistenceImpl<Entity>` にはセッションファクトリオブジェクトと [`openSession`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/service/persistence/impl/BasePersistenceImpl.html#openSession--) メソッドが含まれるため、 `* FinderImpl`の親の `openSession` メソッドを単純に呼び出すことができます。新しいHibernateセッションを開くクラス。 finderメソッドの構造は次のようになります。

    public List<Entity> findBy-(...) {
    
        Session session = null;
        try {
                session = openSession();
    
                /*
                create a dynamic
                query to retrieve and return the desired list of entity
                objects
                */
        }
        catch (Exception e) {
                // Exception handling
        }
        finally {
                closeSession(session);
        }
    
        return null;
        /*
        Return null only if there was an error returning the
        desired list of entity objects in the try block
        */
    
    }

次に、tryブロックで、動的クエリオブジェクトを作成します。

## 動的クエリの作成

Liferayでは、Hibernateセッションから直接条件オブジェクトを作成しません。 代わりに、Liferayの [`DynamicQueryFactoryUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/DynamicQueryFactoryUtil.html) サービスを使用して動的クエリオブジェクトを作成します。 したがって、代わりに

    Criteria entryCriteria = session.createCriteria(Entry.class);

あなたが使う

    DynamicQuery entryQuery = DynamicQueryFactoryUtil.forClass(Entry.class, classLoader));

finderメソッドで、エンティティクラスの動的クエリを初期化します。

制限、予測、順序など、HibernateのCriteria APIのほとんどの機能は、Liferay動的クエリオブジェクトで使用できます。 各基準はクエリに適用できます。 最初に制限基準タイプについて説明します。

### 制限基準

HibernateのCriteria APIの制限は、SQLクエリの `where` 句にほぼ対応しています。これらは、クエリによって返される結果を制限するさまざまな方法を提供します。 たとえば、制限を使用して、特定のフィールドに特定の値、特定の範囲内の値、またはnull以外の値などがある場合にのみクエリが結果を返すようにすることができます。

動的クエリに制限を追加する必要がある場合は、Hibernateの `Restrictions` クラスを直接呼び出さないでください。 代わりに、 [`RestrictionsFactoryUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/RestrictionsFactoryUtil.html) サービスを使用してください。 `RestrictionsFactoryUtil` あなたはHibernateのからに慣れているのと同じ方法を有する `制限` クラス： `で`、 `間の`、 `のように`、 `当量`、 `NE`、 `、GT`、 `GE`、 `、LT`、 `ル`、等

したがって、ゲストブックに特定の名前を付ける必要があることを指定するために次の呼び出しを使用する代わりに、

    entryCriteria.add(Restrictions.eq("name", guestbookName));

あなたが使う

    entryQuery.add(RestrictionsFactoryUtil.eq("name", guestbookName));

上記の制限により、結果は、 `name` 属性が変数 `guestbookName`値と一致するゲストブックエントリに制限されます。 必要な結果を得るために必要な制限を追加します。

射影は次の基準タイプです。 クエリ結果を変換して、必要なフィールドタイプを返すことができます。

### 投影基準

HibernateのCriteria APIのプロジェクションにより、クエリによって返される結果の種類を変更できます。 たとえば、クエリでエンティティオブジェクトのリストを返したくない場合（デフォルト）、クエリに射影を設定して、特定のエンティティフィールドの値のリストのみを返すことができます。 クエリで射影を使用して、エンティティフィールドの最大値または最小値、フィールドのすべての値の合計、または平均などを返すこともできます。 制限と予測の詳細については、Hibernateの [ドキュメント](http://docs.jboss.org/hibernate/orm/3.6/reference/en-US/html/querycriteria.html)を参照してください。

同様に、投影を設定するには、Hibernateの `Property` クラスではなく、Liferayの [PropertyFactoryUtil](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/PropertyFactoryUtil.html) サービスを使用してプロパティを作成します。 したがって、代わりに

    entryCriteria.setProjection(Property.forName("guestbookId"));

あなたが使う

    entryQuery.setProjection(PropertyFactoryUtil.forName("guestbookId"));

上記のプロジェクションでは、 `guestbookId` エンティティフィールドを指定して、結果セットをそれらのフィールド値のリストに変更します。 エンティティから特定のフィールドタイプを返す場合は、そのプロジェクションを追加します。

最後の基準タイプでは、結果を自分の方法で整理できます。

### 注文基準

HibernateのCriteria APIの順序により、クエリが返すリスト内の要素の順序を制御できます。 昇順か降順かだけでなく、順序が適用されるプロパティを選択できます。

このコードは、エンティティの `modifiedDate` 属性によって注文を作成します。

    Order order = OrderFactoryUtil.desc("modifiedDate");

この順序を適用すると、結果はクエリエンティティの `modifiedDate` 属性の降順に並べられます。 したがって、最後に変更されたエンティティ（この例ではゲストブックエントリ）が最初に表示され、最後に変更されたエンティティが最後に表示されます。

Hibernateの基準と同様に、Liferayの動的クエリは *チェーン対応*です。適切なメソッド呼び出しをクエリオブジェクトに追加するだけで、Liferayの動的クエリオブジェクトに基準を追加、投影を設定、順序を追加できます。 たとえば、次のスニペットは、制限条件と動的クエリオブジェクト宣言への投影の連鎖を示しています。

    DynamicQuery guestbookQuery = DynamicQueryFactoryUtil.forClass(Guestbook.class)
        .add(RestrictionsFactoryUtil.eq("name", guestbookName))
        .setProjection(ProjectionFactoryUtil.property("guestbookId"));

動的クエリを実行します。

## 動的クエリの実行

前のチュートリアルでは、カスタムファインダー</a>定義した

後にService Builderを実行しました。 Service Builderは、 `* LocalServiceBaseImpl` クラスで `dynamicQuery（DynamicQuery dynamicQuery）` メソッドを生成しました。 `* LocalService` インスタンスを使用して、 `dynamicQuery` メソッドを呼び出し、動的クエリを渡します。 動的クエリの実行例を次に示します。</p> 

    List<Entity> entities = _someLocalService.dynamicQuery(entityQuery);
    
    return entities;
    

動的クエリの実行はエンティティのリストを返し、finderメソッドはそのリストを返します。

| **注：** Service Builderは、 `パブリックリストを生成するだけではありません。 dynamicQuery（DynamicQuery dynamicQuery） <code>` メソッド* LocalServiceBaseImpl</code> but |また、 `public List dynamicQuery（DynamicQuery dynamicQuery、int | start、int end）` および `public List dynamicQuery（DynamicQuery dynamicQuery、int | start、int end、OrderByComparator orderByComparator）` メソッドも生成します。 戻ることができます|に| [カスタムファインダーメソッドの定義](/docs/7-1/tutorials/-/knowledge_base/t/defining-a-custom-finder-method) |ファインダーメソッドを変更するか、オーバーロードバージョンを作成してこれらの追加メソッドとそのパラメーターの利点。 `int start` および `int | end` パラメーターは、結果リストをページ分割するときに役立ちます。 `開始` が低い|モデルエンティティインスタンスの範囲の下限及び `端` 上限です。 | `OrderByComparator orderByComparator` は、|を並べ替えるコンパレータです。結果。

`* FinderImpl` クラスの（オプションでオーバーロードされた）カスタムファインダーで `* LocalServiceBaseImpl` クラスのオーバーロードされた `dynamicQuery` メソッドを使用するには、動的クエリを実行するための適切なメソッドを選択します： `dynamicQuery（entryQuery）`または `dynamicQuery（entryQuery、start、end）` または `dynamicQuery（entryQuery、start、end、orderByComparator）`。

すばらしいです\！ LiferayのDynamic Query APIを使用して、finderメソッドを作成しました。 最後のステップは、ファインダーを呼び出すサービスメソッドを追加することです。
