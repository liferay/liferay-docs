---
header-id: generating-staged-models-using-service-builder
---

# Service Builderを使用した段階的モデルの生成

[TOC levels=1-4]

ステージングモデルは、アプリケーションにステージングフレームワークとエクスポート/インポートフレームワークを実装するための重要な構成要素です。 アプリのステージングモデルを手動で作成する代わりに、 [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder) を活用して、必要なステージングモデルロジックを生成できます。 このチュートリアルに入る前に、ステージングされたモデルの仕組みについての情報を得るために、 [ステージングされたモデルを理解する](/docs/7-1/tutorials/-/knowledge_base/t/understanding-staged-models) チュートリアルを必ず読んでください。 また、アプリでLiferayのService Builderを使用しない場合は、プロジェクトで設定する必要があります。 これを行うのに支援が必要な場合は、「Service Builder</a> でオブジェクトリレーショナルマップを定義する

チュートリアル」に従ってください。</p> 

このチュートリアルでは、 `* api` および `* service` モジュールのService Builderプロジェクトがあることを前提としています。 このチュートリアルを読みたい場合は、 [staged-model-example](/documents/10184/656312/staged-model-example.zip/5b2a8b0f-fa04-ca4a-2345-015871c0a14a) Service Builderプロジェクトをダウンロードしてください。 これは、Service Builderの実行によって生成されたステージング関連の変更を確認するためにテストできる最低限のプロジェクトです。 このチュートリアルは、プロジェクトがGradleでビルドされていることを前提としています。 サンプルプロジェクトの `service.xml` ファイルには、次の構成が含まれています。

    <service-builder package-path="com.liferay.docs">
        <namespace>FOO</namespace>
        <entity local-service="true" name="Foo" remote-service="true" uuid="true">
    
            <!-- PK fields -->
    
            <column name="fooId" primary="true" type="long" />
    
            <!-- Group instance -->
    
            <column name="groupId" type="long" />
    
            <!-- Audit fields -->
    
            <column name="companyId" type="long" />
            <column name="createDate" type="Date" />
            <column name="modifiedDate" type="Date" />
    
            ...
            ...
    
        </entity>
    </service-builder>
    

簡単にするために、エンティティモデルファイルに適用されたService Builderで生成された変更を追跡して、ステージングされたモデルがエンティティにどのように割り当てられるかを観察します。 各段階モデルに必要な特定の [段階属性](/docs/7-1/tutorials/-/knowledge_base/t/understanding-staged-models#important-attributes-in-staging) に留意してください。 `service.xml` ファイルで定義されている属性に応じて、Service Builderはエンティティモデルを特定の段階的なモデルタイプに割り当てます。

1.  コマンドラインでプロジェクトの `* service` モジュールに移動します。 Service Builder（例： `gradlew buildService`）を実行して、現在の `service.xml` 構成に基づいてプロジェクトのモデルを生成します。

2.  プロジェクトの `[Entity]Model.java` インターフェイスを開き、継承されたインターフェイスを確認します。
   
        public interface FooModel extends BaseModel<Foo>, ShardedModel, StagedModel {
       
   
   モデルが段階的モデルとして生成されました\！ UUIDに設定されているからである `真` 及び `企業ID`、 `CREATEDATE`、及び `modifiedDate` 列が定義されています。 背後でアプリに対して生成されるロジックははるかに多くありますが、これは、Service Builderがエンティティをステージングフレームワークおよびエクスポート/インポートフレームワークに適格と見なしたことを示しています。

3.  `userId` および `userName` 列を `service.xml` ファイルに追加します。 
   
   

    ``` 
        <column name="userId" type="long" />
        <column name="userName" type="String" />
    ```


4.  Service Builderを再実行し、 `[Entity]Model.java` インターフェイスをもう一度観察します`</p>

<pre><code> public interface FooModel extends BaseModel<Foo>, GroupedModel, ShardedModel,
     StagedAuditedModel {
`</pre> 
   
   これで、モデルは段階的な監査モデルになりました\！</li> 
   
   5  `lastPublishDate` 列を `service.xml` ファイルに追加します。
  
       <column name="lastPublishDate" type="Date" />
      

6  Service Builderを再実行し、 `[Entity]Model.java` インターフェイスをもう一度観察します`</p>

<pre><code> public interface FooModel extends BaseModel<Foo>, ShardedModel,
     StagedGroupedModel {
`</pre> 
  
  これで、モデルは段階的なグループ化モデルになりました！ `StageId` 列は、 `StagedGroupedModel` インターフェースを拡張するためにも必要ですが、元の `service.xml` ファイルで既に定義されていました。</li> </ol> 
  
  ファンタスティック\！ Service Builderを使用して段階的モデルを生成することがどれほど簡単かを直接目にしました。
