---
header-id: registering-entries-with-the-search-framework
---

# 検索フレームワークにエントリを登録する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">エントリの検索とインデックス作成を有効にする</p><p>手順1/5</p>
</div>

エントリの検索レジストラは、ゲストブック用に作成されたものと非常に似ています。 同じパッケージ（`com.liferay.docs.guestbook.search`）に入れることもできます。

`EntrySearchRegistrar`作成します。

1.  `com.liferay.docs.guestbook.search`、 `EntrySearchRegistrar` という新しいクラスを作成し、 `activate` と `deactivate`2つのメソッドを設定します。
   
        @Component(immediate = true)
        public class EntrySearchRegistrar {
       
            @Activate
            protected void activate(BundleContext bundleContext) {
       
                _serviceRegistration = modelSearchRegistrarHelper.register(
                    Entry.class, bundleContext, modelSearchDefinition -> {
                        modelSearchDefinition.setDefaultSelectedFieldNames(
                            Field.COMPANY_ID, Field.ENTRY_CLASS_NAME,
                            Field.ENTRY_CLASS_PK, Field.UID, 
                            Field.SCOPE_GROUP_ID, Field.GROUP_ID);
       
                        modelSearchDefinition.setDefaultSelectedLocalizedFieldNames(
                            Field.TITLE, Field.CONTENT);
       
                        modelSearchDefinition.setModelIndexWriteContributor(
                            modelIndexWriterContributor);
                        modelSearchDefinition.setModelSummaryContributor(
                            modelSummaryContributor);
                        modelSearchDefinition.setSelectAllLocales(true);
       
                    });
            }
       
            @Deactivate
            protected void deactivate() {
                _serviceRegistration.unregister();
            }

ゲストブックで行ったように、検索エンジンから結果ドキュメントを取得するために使用されるデフォルトの選択フィールド名を設定します。 エントリの場合、タイトルおよびコンテンツフィールドに対して `setDefaultSelectedLocalizedFieldNames` を呼び出します。 これにより、フィールドのローカライズされたバージョンが検索されて返されます。 エントリとのその他の唯一の違いは、 `setSelectAllLocales（true）`呼び出しです。 `setDefaultSelectedLocalizedFieldNames` で設定されたフィールドを取得し、検索リクエストの `stored_fields` パラメーターで利用可能な各ロケールのフィールドを設定します。 `true`設定されていない場合、単一のロケールのみが検索されます。

2.  クラスのサービス参照を指定します。

    ``` 
        @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Entry)")
        protected ModelIndexerWriterContributor<Entry> modelIndexWriterContributor;

        @Reference
        protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;

        @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Entry)")
        protected ModelSummaryContributor modelSummaryContributor;

        private ServiceRegistration<?> _serviceRegistration;

    }
    ```

    コントリビュータークラスへの参照を検索しながら、 `エントリ` モデルをターゲットにします。 後で、これらの貢献者クラスを作成するときに、モデル名を再度指定して円を完成させます。

エントリの検索とインデックスクラスの登録が完了しました。 次に、検索およびインデックス作成ロジックを記述します。
