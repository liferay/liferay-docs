---
header-id: indexing-guestbooks
---

# ゲストブックのインデックス作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">ゲストブックの検索とインデックス作成を有効にする</p><p>手順3/6</p>
</div>

Guestbookオブジェクトを検索エンジンドキュメントに変換する方法を制御するには、新しい検索パッケージに2つのクラスを作成します。

1.  検索エンジンによってインデックス付けされた検索ドキュメントにフィールドを「提供」する `ModelDocumentContributor` を実装します。 ゲストブックの主な検索可能なフィールドは、ゲストブック名です。

2.  `ModelIndexerWriterContributor` は、ゲストブックのバッチインデックス作成動作を構成します。 このコードは、コントロールパネルの[検索管理]セクションからゲストブックのインデックスが再作成されたときに実行されます。

## `実装ModelDocumentContributor`

`GuestbookModelDocumentContributor` を作成し、これを入力します。

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
            service = ModelDocumentContributor.class
    )
    public class GuestbookModelDocumentContributor
        implements ModelDocumentContributor<Guestbook> {
    
        @Override
        public void contribute(Document document, Guestbook guestbook) {
            try {
                document.addDate(Field.MODIFIED_DATE, guestbook.getModifiedDate());
    
                Locale defaultLocale = PortalUtil.getSiteDefaultLocale(
        guestbook.getGroupId());
    
                String localizedTitle = LocalizationUtil.getLocalizedName(
        Field.TITLE, defaultLocale.toString());
    
                document.addText(localizedTitle, guestbook.getName());
            } catch (PortalException pe) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
        "Unable to index guestbook " + guestbook.getGuestbookId(), pe);
                }
            }
        }
    
        private static final Log _log = LogFactoryUtil.getLog(
        GuestbookModelDocumentContributor.class);
    
    }

@product@はローカライズをサポートしているため、あなたもそうすべきです。 上記のコードは、 `Guestbook`のグループIDを `getSiteDefaultLocale` メソッドに渡し、それを使用してGuestbookのタイトルフィールドのローカライズされた名前を取得することにより、サイトからデフォルトロケールを取得します。 取得されたサイトロケールはフィールドに追加されるため（たとえば、 `title_en_US`）、フィールドは検索エンジンに渡され、正しい分析と [トークン化](https://www.elastic.co/guide/en/elasticsearch/reference/2.4/analysis-tokenizers.html)ます。

## `ModelIndexerWriterContributor`実装

`GuestbookModelIndexerWriterContributor` を作成し、これを入力します。

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
            service = ModelIndexerWriterContributor.class
    )
    public class GuestbookModelIndexerWriterContributor
        implements ModelIndexerWriterContributor<Guestbook> {
    
        @Override
        public void customize(
            BatchIndexingActionable batchIndexingActionable,
            ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {
    
            batchIndexingActionable.setPerformActionMethod((Guestbook guestbook) -> {
                Document document = modelIndexerWriterDocumentHelper.getDocument(
        guestbook);
    
                batchIndexingActionable.addDocuments(document);
            });
        }
    
        @Override
        public BatchIndexingActionable getBatchIndexingActionable() {
            return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
        guestbookLocalService.getIndexableActionableDynamicQuery());
        }
    
        @Override
        public long getCompanyId(Guestbook guestbook) {
            return guestbook.getCompanyId();
        }
    
        @Override
        public void modelIndexed(Guestbook guestbook) {
            entryBatchReindexer.reindex(
        guestbook.getGuestbookId(), guestbook.getCompanyId());
        }
    
        @Reference
        protected DynamicQueryBatchIndexingActionableFactory
        dynamicQueryBatchIndexingActionableFactory;
    
        @Reference
        protected EntryBatchReindexer entryBatchReindexer;
    
        @Reference
        protected GuestbookLocalService guestbookLocalService;
    
    }

最初に `カスタマイズ` メソッドを見てください。 `BatchIndexingActionable` メソッドを呼び出して、エンティティのドキュメントのバッチインデックス作成動作を構成します。 このコードは、ゲストブックのアクション可能な動的クエリヘルパーメソッドを使用して、仮想インスタンス（会社IDで識別される）内のすべてのゲストブックを取得します。 サービスの構築時に、Service Builderがこのクエリメソッドを生成しました。 次に、各ゲストブックのドキュメントが取得され、コレクションに追加されます。

エントリのインデックスクラスを作成するとき、ゲストブックのタイトルをエントリ文書に追加します。 したがって、ゲストブックのタイトルが変更された場合に、索引付けされたエントリー文書を更新する方法を提供する必要があります。 `modelIndexed` メソッドは、後でエントリ用に作成されるインターフェイスから `reindex` メソッドを呼び出します。

インデックスの再作成の動作が整ったら、検索エンジンからのゲストブックドキュメントのクエリ方法の制御に進むことができます。
