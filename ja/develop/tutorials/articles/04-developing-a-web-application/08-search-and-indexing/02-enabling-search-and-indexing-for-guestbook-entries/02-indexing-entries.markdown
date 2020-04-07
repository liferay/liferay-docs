---
header-id: indexing-entries
---

# インデックス作成エントリ

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">エントリの検索とインデックス作成を有効にする</p><p>手順2/5</p>
</div>

エントリオブジェクトを検索エンジンドキュメントに変換する方法を制御するには、検索パッケージに次のクラスを作成します。

1.  `ModelDocumentContributor`：エントリの検索可能な主なフィールドは、 *Name* および *Message*です。 エントリに関連付けられたゲストブック名にもインデックスが付けられます。

2.  `ModelIndexerWriterContributor` は、エントリのバッチインデックス付け動作を構成します。 このコードは、コントロールパネルの[検索管理]セクションからエントリのインデックスが再作成されたときに実行されます。

3.  新しいインターフェース `EntryBatchReindexer`、その実装 `EntryBatchReindexerImpl`。 これらのクラスには、ゲストブックが更新されたときにエントリのインデックスが再作成されるようにするコードが含まれています。

## `実装ModelDocumentContributor`

`EntryModelDocumentContributor` を作成し、これを入力します。

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Entry",
            service = ModelDocumentContributor.class
    )
    public class EntryModelDocumentContributor
        implements ModelDocumentContributor<Entry> {
    
        @Override
        public void contribute(Document document, Entry entry) {
            try {
                Locale defaultLocale = PortalUtil.getSiteDefaultLocale(
        entry.getGroupId());
    
                document.addDate(Field.MODIFIED_DATE, entry.getModifiedDate());
                document.addText("entryEmail", entry.getEmail());
    
                String localizedTitle = LocalizationUtil.getLocalizedName(
        Field.TITLE, defaultLocale.toString());
                String localizedContent = LocalizationUtil.getLocalizedName(
        Field.CONTENT, defaultLocale.toString());
    
                document.addText(localizedTitle, entry.getName());
                document.addText(localizedContent, entry.getMessage());
    
                long guestbookId = entry.getGuestbookId();
    
                Guestbook guestbook = _guestbookLocalService.getGuestbook(
        guestbookId);
    
                String guestbookName = guestbook.getName();
    
                String localizedGbName = LocalizationUtil.getLocalizedName(
        Field.NAME, defaultLocale.toString());
    
                document.addText(localizedGbName, guestbookName);
            } catch (PortalException pe) {
                if (_log.isWarnEnabled()) {
                    _log.warn("Unable to index entry " + entry.getEntryId(), pe);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        private static final Log _log = LogFactoryUtil.getLog(
        EntryModelDocumentContributor.class);
    
        @Reference
        private GuestbookLocalService _guestbookLocalService;
    
    }

ゲストブックと同様に、翻訳される可能性のあるフィールドにローカライズされた値を追加します。 サイトロケールがフィールドに追加されるため（たとえば、 `title_en_US`）、フィールドは検索エンジンに渡され、正しい分析と [トークン化](https://www.elastic.co/guide/en/elasticsearch/reference/2.4/analysis-tokenizers.html)ます。

## `ModelIndexerWriterContributor`実装

`EntryModelIndexerWriterContributor` を作成し、これを入力します。

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Entry",
            service = ModelIndexerWriterContributor.class
    )
    public class EntryModelIndexerWriterContributor
        implements ModelIndexerWriterContributor<Entry> {
    
        @Override
        public void customize(
            BatchIndexingActionable batchIndexingActionable,
            ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {
    
            batchIndexingActionable.setPerformActionMethod((Entry entry) -> {
                Document document = modelIndexerWriterDocumentHelper.getDocument(
        entry);
    
                batchIndexingActionable.addDocuments(document);
    
            });
        }
    
        @Override
        public BatchIndexingActionable getBatchIndexingActionable() {
            return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
        entryLocalService.getIndexableActionableDynamicQuery());
        }
    
        @Override
        public long getCompanyId(Entry entry) {
            return entry.getCompanyId();
        }
    
        @Reference
        protected DynamicQueryBatchIndexingActionableFactory
        dynamicQueryBatchIndexingActionableFactory;
    
        @Reference
        protected EntryLocalService entryLocalService;
    
    }

興味深い作業は、すべてのエントリを取得してコレクションに追加する `カスタマイズ` メソッドで行われます。

## `EntryBatchReindexer`実装

`reindex`という1つのメソッドを使用して、新しいインターフェイスクラス `EntryBatchReindexer`作成します。

    package com.liferay.docs.guestbook.search;
    
    public interface EntryBatchReindexer {
    
        public void reindex(long guestbookId, long companyId);
    
    }

次に、実装クラス `EntryBatchReindexerImpl`作成します。

    @Component(immediate = true, service = EntryBatchReindexer.class)
    public class EntryBatchReindexerImpl implements EntryBatchReindexer {
    
        @Override
        public void reindex(long guestbookId, long companyId) {
            BatchIndexingActionable batchIndexingActionable =
        indexerWriter.getBatchIndexingActionable();
    
            batchIndexingActionable.setAddCriteriaMethod(dynamicQuery -> {
                Property guestbookIdPropery = PropertyFactoryUtil.forName(
        "guestbookId");
    
                dynamicQuery.add(guestbookIdPropery.eq(guestbookId));
            });
    
            batchIndexingActionable.setCompanyId(companyId);
    
            batchIndexingActionable.setPerformActionMethod((Entry entry) -> {
                Document document = indexerDocumentBuilder.getDocument(entry);
    
                batchIndexingActionable.addDocuments(document);
            });
    
            batchIndexingActionable.performActions();
    
        }
    
        @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Entry)")
        protected IndexerDocumentBuilder indexerDocumentBuilder;
    
        @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Entry)")
        protected IndexerWriter<Entry> indexerWriter;
    
    }
    
    The `reindex` method of the interface is called when a Guestbook is updated.
    The entry documents are re-indexed to include the update Guestbook title.

インデックスの再作成の動作が整ったら、検索エンジンからエントリドキュメントを照会する方法を制御するコードに進みます。
