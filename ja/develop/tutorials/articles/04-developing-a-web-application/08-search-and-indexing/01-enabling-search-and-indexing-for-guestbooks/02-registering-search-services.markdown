---
header-id: registering-guestbooks-with-the-search-framework
---

# 検索フレームワークでのゲストブックの登録

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">ゲストブックの検索とインデックス作成を有効にする</p><p>手順2/6</p>
</div>

まず、 `build.gradle` を更新して、必要なすべてのインポートを行います。

1.  `guestbook-service` プロジェクトで `build.gradle` ファイルを開きます。

2.  Search Service Provider InterfaceとAPIの依存関係を `build.gradle` ファイルに追加します。
   
        compileOnly group: "com.liferay", name: "com.liferay.portal.search.spi", version: "2.0.0"
        compileOnly group: "com.liferay", name: "com.liferay.portal.search.api", version: "2.0.0"

3.  ファイルを保存し、 `Refresh Gradle Project`を実行します。

依存関係を構成したら、エンティティの `ModelSearchDefinition`を構築する検索サービスを登録します。

`* SearchRegistrar` 指定したエンティティが使用する建物に貢献することをクラス `ModelSearchDefinition`。 `SearchRegistrar` コンポーネントをアクティブにすると、検索フレームワークで一連のアクティビティが発生し、 `DefaultIndexer`構築されます。 `DefaultIndexer` は、レジストラで定義されたクラス名で登録され、そのクラスのオブジェクトのインデックス作成/検索に使用されます。

`GuestbookSearchRegistrar`作成します。

1.  `ゲストブックサービス` モジュールプロジェクトの `src / main / java` フォルダーに `com.liferay.docs.guestbook.search`という新しいパッケージを作成します。 このパッケージでは、 `GuestbookSearchRegistrar` と呼ばれる新しいクラスを作成し、 `activate` と `deactivate``つのメソッドを設定します。</p>

<pre><code> @Component(immediate = true)
 public class GuestbookSearchRegistrar {

     @Activate
     protected void activate(BundleContext bundleContext) {

         _serviceRegistration = modelSearchRegistrarHelper.register(
             Guestbook.class, bundleContext, modelSearchDefinition -> {
                 modelSearchDefinition.setDefaultSelectedFieldNames(
                     Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.CONTENT,
                     Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
                     Field.GROUP_ID, Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID,
                     Field.TITLE, Field.UID);

                 modelSearchDefinition.setModelIndexWriteContributor(
                     modelIndexWriterContributor);
                 modelSearchDefinition.setModelSummaryContributor(
                     modelSummaryContributor);
             });
     }

     @Deactivate
     protected void deactivate() {

         _serviceRegistration.unregister();
     }
`</pre>

    アノテーション `@Activate` および `Deactivate` は、コンポーネントが開始されるとすぐに（アクティブ化される）、または停止されるとすぐに（非アクティブ化される）各メソッドが呼び出されるようにします。 コンポーネントをアクティブ化すると、検索クラスとインデックスクラスのチェーンがゲストブックエンティティに登録されます。 検索エンジンから結果ドキュメントを取得するために使用されるデフォルトの選択フィールド名を設定します。 次に、モデル検索定義の作成に使用される貢献者を設定します。</li>

2

クラスのサービス参照を指定します。

    ``` 
        @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Guestbook)")
        protected ModelIndexerWriterContributor<Guestbook> modelIndexWriterContributor;

        @Reference
        protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;

        @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Guestbook)")
        protected ModelSummaryContributor modelSummaryContributor;

        private ServiceRegistration<?> _serviceRegistration;

    }
    ```

    貢献者クラスへの参照を検索しながら、 `Guestbook` モデルをターゲットにします。 後で、これらの貢献者クラスを作成するときに、モデル名を再度指定して円を完成させます。

3

インポートの整理（Ctrl-Shift-O）でインポートを追加します。

4

`ゲストブックサービス` モジュールの `bnd.bnd` ファイルにある `com.liferay.docs.guestbook.search` パッケージをエクスポートします。 エクスポートセクションは次のようになります。

    ``` 
     Export-Package: com.liferay.docs.guestbook.search
    ```
</ol>

ゲストブックの検索とインデックスクラスの登録が完了しました。 次に、検索およびインデックス作成ロジックを記述します。
