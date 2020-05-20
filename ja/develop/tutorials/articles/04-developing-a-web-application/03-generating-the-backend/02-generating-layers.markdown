---
header-id: generating-model-service-and-persistence-layers
---

# モデル、サービス、永続レイヤーの生成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">バックエンドの生成</p><p>手順2/3</p>
</div>

永続レイヤーはモデルデータの保存および取得を行います。 サービスレイヤーは、アプリケーションと永続レイヤーの間のバッファーです。サービスレイヤー内の呼び出し以外を変更することなく、永続レイヤーを異なる実装に置換できます。

ゲストブックとエントリをモデル化するには、ゲストブックとエントリモデルクラスを作成します。 ただし、Javaでこれを直接行うことはありません。 代わりに、オブジェクトモデルを生成し、@product@がサポートするすべてのSQLデータベースにそれをマップするサービスビルダーで、それらを定義します。

このアプリケーションの設計では、それぞれが異なるエントリセットを含む複数のゲストブックを使用できます。 アプリケーションにアクセスする権限を持つすべてのユーザーはエントリを追加できますが、ゲストブックを追加できるのは管理ユーザーだけになります。

それでは、始めましょう。 最初に`Guestbook`エンティティを作成します。

1.  `guestbook-service`プロジェクトで、`service.xml`を開きます。 その際、*Source*タブが選択されていることを確認してください。

2.  Liferay @ide@がプロジェクトを生成すると、このファイルはダミーのエンティティで埋められ、置き換えられます。 最初に、ファイルの開始コンテンツ（`DOCTYPE` 以下）を次のコードに置き換えます。
   
        <service-builder auto-namespace-tables="true" package-path="com.liferay.docs.guestbook"><author>liferay</author>
            <namespace>GB</namespace> <entity name="Guestbook" local-service="true" uuid="true">

    これは、作成者、名前空間、およびエンティティ名を定義しています。 名前空間は、データベースフィールド名と矛盾しないようにします。 最後のタグは、`Guestbook`エンティティ定義の開始タグです。 このタグでは、エンティティのローカルサービスを有効にし、その名前を定義して、[universally unique identifier (UUID)](https://en.wikipedia.org/wiki/Universally_unique_identifier)を持つように指定します。

3.  次に、PKフィールドセクションを置き換えます。
   
        <column name="guestbookId" primary="true" type="long" />

    これにより、タイプ`long`のエンティティのプライマリキーとして、`guestbookId`が定義されます。

4.  グループインスタンスはそのまま残しておけます。
   
        <column name="groupId" type="long" />

    これは、エンティティインスタンスが属する@product@のサイトIDを定義します（これについては後ほど説明します）。

5.  監査フィールドセクションはデフォルトのままにします。 状態フィールドを追加してください。
    
        <!-- Status fields -->
        
        <column name="status" type="int" />
        <column name="statusByUserId" type="long" />
        <column name="statusByUserName" type="String" />
        <column name="statusDate" type="Date" />

    監査セクションでは、@product@メタデータを定義します。 `companyId`は、 [ポータルインスタンス](/docs/7-1/user/-/knowledge_base/u/setting-up)のプライマリキーです。 `userId`はユーザーのプライマリキーです。 `createDate`および `modifiedDate`は、エンティティインスタンスが作成および変更されるそれぞれの日付を格納します。 状態セクションは後でワークフローを実装するために使用されます。

6.  その他のフィールドセクションで、生成されたフィールドを次のフィールドに置き換えます。

    ``` 
     <column name="name" type="String" />
    ```

7.  次に、ゲストブックエンティティから他のすべてを削除します。 末尾`</entity>`タグの前に、このファインダー定義を追加します。

    ``` 
        <finder name="GroupId" return-type="Collection">
            <finder-column name="groupId" />
        </finder>
    ```

    ファインダーは、ゲストブックエンティティを取得するために使用する`get`メソッドを生成します。 ファインダーによって使用されるフィールドは、取り出されるデータの範囲を定義します。 このファインダーは、アプリケーションが存在する[サイト](/docs/7-1/user/-/knowledge_base/u/building-a-site)に対応する`groupId`で、すべてのゲストブックを取得します。 これにより、管理者はゲストブックを複数のサイトに配置でき、各`Guestbook`で、そのサイトを対象とする独自のデータを持つことができます。

`Guestbook`エンティティはこれで完成です。 次に、 `Entry`エンティティを作成します。

1.  開始エンティティタグを追加します。
   
        <entity name="Entry" local-service="true" uuid="true">

    `Guestbook`エンティティと同様に、ローカルサービスを有効にし、エンティティの名前を定義して、UUIDが必要であることを指定します。

2.  タグを追加して、主キーと`groupId`を定義します。
   
        <column name="entryId" primary="true" type="long" />
       
        <column name="groupId" type="long" />

3.  `Guestbook`エンティティのフィールドに、一致する監査フィールドを追加します。
   
        <column name="companyId" type="long" />
        <column name="userId" type="long" />
        <column name="userName" type="String" />
        <column name="createDate" type="Date" />
        <column name="modifiedDate" type="Date" />

4.  ゲストブックで行ったように、状態フィールドを追加します。

    ``` 
     <!-- Status fields -->
    
     <column name="status" type="int" />
     <column name="statusByUserId" type="long" />
     <column name="statusByUserName" type="String" />
     <column name="statusDate" type="Date" />
    ```

5.  `Entry`を定義するフィールドを追加します。
   
        <column name="name" type="String" />
        <column name="email" type="String" />
        <column name="message" type="String" />
        <column name="guestbookId" type="long" />

    `name`、`email`、および`message`フィールドは、`Entry`を含みます。 これらのフィールドは、エントリ、メールアドレス、ゲストブックメッセージを作成する人の名前をそれぞれ定義します。 `guestbookId`は、記述するコードによって自動的に割り当てられ、`Guestbook`は外部キーとなります。 これにより、`Entry`が特定の`Guestbook`に結び付けられます。

6.  ファインダーと終了エンティティタグを追加します。

    ``` 
        <finder name="G_G" return-type="Collection">
            <finder-column name="groupId" />
            <finder-column name="guestbookId" />
        </finder>
    </entity>
    ```

    ここでは、`groupId`および`guestbookId`によってゲストブックエントリを取得するファインダーを定義します。 前回と同様に、`groupId`は、アプリケーションが存在する[サイト](/docs/7-1/user/-/knowledge_base/u/building-a-site)に対応します。 `guestbookId`は、エントリの元となるゲストブックを定義します。 このファインダーは、エントリーの`Collection`を戻します。

7.  `<entity>`タグの外側、`</service-builder>`終了タグの直前に、例外タイプを定義します。<exceptions> <exception>EntryEmail</exception> <exception>EntryMessage</exception> <exception>EntryName</exception> <exception>GuestbookName</exception> </exceptions> 

    これらは、後でtry/catchステートメントで使用する例外クラスを生成します。

8.  `service.xml`ファイルを保存します。

これで、Service Builderを実行して、モデル、サービス、および永続レイヤーを生成する準備が整いました。\!

1.  @ide@の右側にあるGradleタスクペインで、`guestbook-service` → `build`を開きます。

2.  `buildService`を右クリックし、*Run Gradle Tasks*を選択して実行します。 Gradleは最初に実行したときに依存関係をダウンロードするため、インターネットに接続していることを確認してください。

3.  Project Explorerで`guestbook-service`モジュールを右クリックし、*Refresh*を選択します。 `guestbook-api`モジュールに対してこの手順を繰り返します。 これにより、Service Builderによって生成された新しいクラスとインターフェイスが@ide@に表示されます。

4.  Project Explorerで`guestbook-service`モジュールを右クリックして、*Gradle* → *Refresh Gradle Project*を選択します。 `guestbook-api`モジュールに対してこの手順を繰り返します。 これにより、モジュールのGradle依存関係が最新のものになります。

Service Builderは、疎結合と呼ばれる設計哲学に基づいています。 モデル、サービス、および永続レイヤーの、アプリケーションの3つのレイヤーを生成します。 疎結合とは、モデルレイヤーとサービスレイヤーでほとんどまたはまったく変更せずに、永続レイヤーを交換できるということを意味します。 モデルは`-api`モジュールにあり、サービスレイヤーと永続レイヤーは`-service`モジュールにあります。

![図1：モデル、サービス、および永続レイヤーは疎結合設計で構成されています。](../../../images/model-service-persistence.png)

各レイヤーは、Javaインターフェイスとそれらのインターフェイスの実装を使用して実装されます。 モデルを表す`Entry`クラスを持つのではなく、Service Builderは`Guestbook`インターフェイス、サービスビルダーが管理する`GuestbookBaseImpl`抽象クラス、およびカスタマイズ可能な`GuestbookImpl`クラスを含むクラスのシステムを生成します。 この設計により、モデルをカスタマイズすることができますが、サービスビルダーは作成が面倒なコードを生成します。 これが、サービスビルダーがコードジェネレーター嫌いのコードジェネレーターである理由です。

次に、サービス実装を作成します。
