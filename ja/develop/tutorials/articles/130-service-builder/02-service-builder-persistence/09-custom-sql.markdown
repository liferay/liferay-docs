---
header-id: custom-sql
---

# カスタムSQL

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Service Builderは、属性（列値）によってエンティティを取得するファインダーメソッドを作成します。 `service.xml` ファイルにファインダーのパラメーターとして列を追加してService Builderを実行すると、永続レイヤーでファインダーメソッドが生成され、ファインダーを呼び出すメソッドがサービスレイヤーに追加されます。 クエリが非常に単純な場合は、 [動的クエリ](/docs/7-1/tutorials/-/knowledge_base/t/dynamic-query) を使用してLiferayのデータベースにアクセスすることを検討してください。 JOINのようなもっと複雑なことをしたい場合は、独自のカスタムSQLクエリを作成できます。 このチュートリアルでその方法を学びます。

ゲストブック用とゲストブックエントリ用の2つのテーブルを持つゲストブックアプリケーションがあるとします。 エントリエンティティのゲストブックへの外部キーは、ゲストブックのIDです。 つまり、エントリエンティティテーブル `GB_Entry`は、テーブルの `guestbookId` 列の長整数IDによってエントリのゲストブックを追跡します。 名前、メッセージ、ゲストブック名に基づいてゲストブックエントリを検索する場合は、エントリのゲストブックの *名* にアクセスする必要があります。 もちろん、SQLを使用して、エントリテーブルとゲストブックテーブルを結合して、ゲストブック名を含めることができます。 Service Builderでは、SQLを *LiferayカスタムSQL* として指定し、 *カスタムファインダーメソッド*介してサービスで呼び出すことにより、これを行うことができます。

LiferayカスタムSQLは、永続層のファインダーメソッドからカスタムSQLを呼び出すことにより、データベースに対してカスタムの複雑なクエリを実行するためのService Builderがサポートするメソッドです。 Service Builderは、finderメソッドへのインターフェイスを生成するのに役立ちます。 次の手順に従うことで簡単に行えます。

1.  [カスタムSQLを指定します。](#step-1-specify-your-custom-sql)

2.  [finderメソッドを実装します。](#step-2-implement-your-finder-method)

3.  [サービスからファインダーメソッドにアクセスします。](#step-3-access-your-finder-method-from-your-service)

次に、Guestbookアプリケーションを例として使用して、これらの手順を実行する方法を学習します。

## ステップ1：カスタムSQLを指定する

SQLをテストした後、Liferayがアクセスするために特定のファイルで指定する必要があります。 `CustomSQL` クラス（モジュール [`からcom.liferay.portal.dao.orm.custom.sql.api`](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.dao.orm.custom.sql.api/)）は、サービスモジュールの `src / main / resources / METAにある <code>default.xml` というファイルからSQLを取得します。 -INF / custom-sql /</code> フォルダー。 `custom-sql` フォルダーを作成し、その `custom-sql` フォルダーに `default.xml` ファイルを作成する必要があります。 `default.xml` ファイルは、次の形式に従う必要があります。

    <custom-sql>
        <sql id="[fully-qualified class name + method]">
        SQL query wrapped in <![CDATA[...]]>
        No terminating semi-colon
        </sql>
    </custom-sql>

アプリケーションで必要なすべてのSQLクエリに対して `custom-sql` 要素を作成し、各クエリに一意のIDを与えます。 ID値のために使用することが推奨慣例は、ドットに続くファインダの完全修飾クラス名である（`。`）文字とファインダメソッドの名前。 ファインダクラスとファインダメソッドの詳細については、手順2で説明します。

たとえば、Guestbookアプリケーションでは、次のID値を使用してクエリを指定できます。

    com.liferay.docs.guestbook.service.persistence.\
    EntryFinder.findByEntryNameEntryMessageGuestbookName

カスタムSQLは、 `sql` エレメントの文字データ（`CDATA`）でラップする必要があります。 重要なのは、SQLをセミコロンで終了しないことです。 これらのルールに従って、Guestbookアプリケーションの `default.xml` ファイルは、 `GB_Entry` テーブルと `GB_Guestbook` テーブルを結合するSQLクエリを指定します。

    <?xml version="1.0" encoding="UTF-8"?>
    <custom-sql>
        <sql id="com.liferay.docs.guestbook.service.persistence.EntryFinder.findByEntryNameEntryMessageGuestbookName">
            <![CDATA[
                SELECT GB_Entry.*
                FROM GB_Entry
                INNER JOIN 
                    GB_Guestbook ON GB_Entry.guestbookId = GB_Guestbook.guestbookId
                WHERE
                    (GB_Entry.name LIKE ?) AND
                    (GB_Entry.message LIKE ?) AND
                    (GB_Guestbook.name LIKE ?)
            ]]>
        </sql>
    </custom-sql>

いくつかのカスタムSQLを指定したので、次のステップはファインダーメソッドを実装してそれを呼び出すことです。 ファインダーのメソッド名は、 `sql` エレメントに指定したIDと一致する必要があります。

## ステップ2：Finderメソッドを実装する

次に、永続化レイヤーにfinderメソッドを実装して、カスタムSQLクエリを呼び出します。 Service BuilderはAPIモジュールでファインダーのインターフェースを生成しますが、実装を作成する必要があります。

最初のステップは、サービス永続化パッケージに `* FinderImpl` クラスを作成することです。 Guestbookアプリケーションの場合、 `com.liferay.docs.guestbook.service.persistence.impl` パッケージに `EntryFinderImpl` クラスを作成できます。 クラスは `BasePersistenceImpl<Entry>`拡張する必要があり` 。</p>

<p spaces-before="0"><a href="/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder">Service Builder</a> を実行して、 <code>* FinderImpl` クラスに基づいて `* Finder` インターフェイスを生成します。 `* FinderImpl` クラスを変更して、生成したばかりの `* Finder` インターフェイスを実装するようにします。

    public class EntryFinderImpl extends BasePersistenceImpl<Event>
        implements EntryFinder {
    
    }

これで、 `EntryFinderImpl` クラスでファインダーメソッドを作成できます。 finderメソッドと静的フィールドを `* FinderImpl` クラスに追加します。 たとえば、 `EntryFinderImpl` クラスを作成する方法は次のとおりです。

    public List<Entry> findByEntryNameEntryMessageGuestbookName(
        String entryName, String entryMessage, String guestbookName,
        int begin, int end) {
    
        Session session = null;
        try {
            session = openSession();
    
            String sql = _customSQL.get(
                getClass(),
                FIND_BY_ENTRYNAME_ENTRYMESSAGE_GUESTBOOKNAME);
    
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("GB_Entry", EntryImpl.class);
    
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(entryName);
            qPos.add(entryMessage);
            qPos.add(guestbookName);
    
            return (List<Entry>) QueryUtil.list(q, getDialect(), begin, end);
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
    
        return null;
    }
    
    public static final String FIND_BY_ENTRYNAME_ENTRYMESSAGE_GUESTBOOKNAME =
        EntryFinder.class.getName() +
            ".findByEntryNameEntryMessageGuestbookName";
    
    @ServiceReference(type=CustomSQL.class)
    private CustomSQL _customSQL;

カスタムfinderメソッドは、新しいHibernateセッションを開き、Liferayの `CustomSQL.get（Class<？> clazz、String id）` メソッドを使用して、データベースクエリに使用するカスタムSQLを取得します。 `FIND_BY_ENTRYNAME_ENTRYMESSAGE_GUESTBOOKNAME` 静的フィールドには、カスタムSQLクエリのIDが含まれています。 `FIND_BY_EVENTNAME_EVENTDESCRIPTON_LOCATIONNAME` 文字列は、 `* Finder` インターフェイス（`EventFinder`）の完全修飾クラス名と、finderメソッドの名前（`findByEntryNameEntryMessageGuestbookName`）に基づいています。

驚くばかり\！ カスタムSQLが配置され、ファインダーメソッドが実装されます。 次に、サービスからfinderメソッドを呼び出します。

## ステップ3：サービスからFinderメソッドにアクセスする

これまでに、 `* FinderImpl` クラスを作成し、 `* Finder` インターフェイスを生成し、カスタムSQLを取得するカスタムfinderメソッドを作成しました。 最後のステップは、ファインダーを呼び出すサービスメソッドを追加することです。

カスタムfinderメソッドを定義した後にService Builderを実行すると、 `* Finder` インターフェイスが生成されました（ `GuestbookFinder`）。 ただし、ポートレットクラスは `* Finder` インターフェイスを呼び出さないでください。サービスモジュールのローカルまたはリモートサービス実装（つまり、 `* LocalServiceImpl` または `* ServiceImpl`）のみが `* Finder` クラスを呼び出す必要があります。 これにより、懸念事項を適切に分離できます。アプリケーションのWebモジュール内のポートレットクラスは、アプリケーションのサービスモジュールから公開されたサービスのビジネスロジックを呼び出します。 サービスは、永続層のファインダークラスを使用してデータモデルにアクセスします。

| **注：** Liferay Portal 6.2は、静的メソッドを介してファインダーメソッドにアクセスできるようにしました。 `* FinderUtil` ユーティリティクラス。 Finderメソッドは、アプリの|に注入されます。ローカルサービス。ファインダーユーティリティを呼び出す必要がありません。

したがって、 `* LocalServiceImpl` クラスにメソッドを追加し、 `* Finder` クラスを介してファインダーメソッドの実装を呼び出します。 次に、Webモジュール内のポートレットクラスとJSPがサービスにアクセスできるように、アプリケーションのサービスレイヤーを再構築します。

たとえば、Guestbookアプリケーションの場合、次のメソッドを `EntryLocalServiceImpl` クラスに追加します。

    public List<Entry> findByEntryNameGuestbookName(String entryName,
        String guestbookName) throws SystemException {
    
        return entryFinder.findByEntryNameGuestbookName(String entryName,
            String guestbookName);
    }

`findBy-` メソッドを `* LocalServiceImpl` クラスに追加したら、Service Builderを実行してインターフェイスを生成し、 `EntryLocalService` クラスでファインダーメソッドを使用できるようにします。

これで、ポートレットクラスまたはWebモジュールのJSPからファインダメソッドを間接的に呼び出すことができます。 たとえば、Guestbookアプリケーションでfinderメソッドを呼び出すには、 `entryLocalService.findByEntryNameEntryMessageGuestbookName（...）`\！を呼び出すだけです。

アプリケーション用のカスタムSQLクエリとカスタムファインダーの開発おめでとうございます！

## 関連トピック

[Liferayサービスのカスタマイズ](/docs/7-1/tutorials/-/knowledge_base/t/customizing-liferay-services-service-wrappers)

[Service Builder Webサービス](/docs/7-1/tutorials/-/knowledge_base/t/service-builder-web-services)
